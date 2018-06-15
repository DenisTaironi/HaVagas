/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BEAN.NotificacaoBean;
import BEAN.SetorBean;
import BEAN.VagasBean;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OWL
 */
public class DAOest {

    //metodo codigo 0, responsavel por devolver o mapa atual do estacionamento
    public List<VagasBean> MapaEst(int idEstacionamento) throws ClassNotFoundException {

        List<VagasBean> lvb = new ArrayList<VagasBean>();
        Conexao c = new Conexao();

        try {
            Connection con;
            con = c.conectar();
            Statement ps = con.createStatement();

            String sql = "SELECT `idVagas`,`Setor_idSetor`,`Estado`,`TempOc`,`VezesOc`\n"
                    + "FROM `vagas` inner join setor where Estacionamento_idEstacionamento ="
                    + " " + idEstacionamento + "  and Setor_idSetor = idSetor;\n"
                    + "";
            System.out.println("TESTE: " + sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                VagasBean vb = new VagasBean();
                vb.setIdVagas(rs.getInt("idVagas"));
                vb.setSetor_idSetor(rs.getInt("Setor_idSetor"));
                vb.setEstado(rs.getInt("Estado"));
                vb.setTempOc(rs.getInt("TempOc"));
                vb.setVezesOc(rs.getInt("VezesOc"));

                lvb.add(vb);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lvb;

    }

    // 

    public List<String> Rel(int idEstacionamento) throws ClassNotFoundException {

        Conexao c = new Conexao();
        Timestamp t = null;
        List<String> textos = new ArrayList<String>();
        String cab;

        try {
            Connection con;
            con = c.conectar();
            Statement ps = con.createStatement();
            Statement ps2 = con.createStatement();

            String sql = "select DtUltRelatorio from estacionamento where idEstacionamento = " + idEstacionamento + "";
            System.out.println("TESTE: " + sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                t = rs.getTimestamp("DtUltRelatorio");
            }

            String sql2 = "UPDATE `estacionamento` SET `DtUltRelatorio` = CURRENT_TIMESTAMP WHERE `idEstacionamento` = " + idEstacionamento + "";
            ps2.executeUpdate(sql2);

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cab = "Relatório a partir do dia: " + t + ".";

        System.out.println(cab);

        textos.add(cab);

        DAOest d = new DAOest();
        List<VagasBean> list = d.MapaEst(idEstacionamento);
        for (int i = 0; i < list.size(); i++) {
            int id = list.get(i).getIdVagas();
            int vezes = list.get(i).getVezesOc();
            int tempo = list.get(i).getTempOc()*15;

            System.out.println("ID " + id);
            System.out.println("vezes " + vezes);
            System.out.println("tempo " + tempo);

            int media = 0;

            if (tempo > 0) {
                media = tempo / vezes;
            }

            String texto = "A vaga " + id + " foi ocupada " + vezes + " vezes por " + tempo + " segundos, com uma média de " + media + " segundos  por ocupação";

            System.out.println(texto);

            textos.add(texto);

        }

        System.out.println("Copiando para arquivo");

        String dst = "relatorio.txt";
        File arquivo1 = new File(dst);

        try {

            System.out.println("Arquivo criado em " + arquivo1.getPath());
            FileWriter fw = new FileWriter(arquivo1);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cab);
            bw.newLine();

            for (int i = 0; i < textos.size(); i++) {
                bw.write(textos.get(i));
                bw.newLine();
            }

            bw.close();
            fw.close();

        } catch (IOException io) {
            System.out.println("Não foi possivel criar arquivo");
        }

        return textos;

    }

    public String Pagar(String CodigoBarras) throws ClassNotFoundException {
        String msg;
        Conexao c = new Conexao();

        try {
            Connection con;
            con = c.conectar();
            String sql = "UPDATE `ticket` SET `Estado` = 1 WHERE CodBarras = "+CodigoBarras+" AND `Estacionamento_idEstacionamento` = 1;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            con.close();
            msg ="Pagamento efetuado com sucesso";
        } catch (SQLException e) {
            e.printStackTrace();
            msg ="erro no pagamento, tente novamente mais tarde";
        }
        return msg;

    }

    public String CadastrarNot(String vaga, String tel) throws ClassNotFoundException {
        
        String msg = "ERRO";
        Conexao c = new Conexao();
        int id;
        
        try {
            Connection con;
            con = c.conectar();
            String sql = "INSERT INTO `notificacao`(`NumCelular`,`Vagas_idVagas`)VALUES("+tel+","+vaga+");";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            
            String sql2 = "select max(idNot) from notificacao";
            Statement ps2 = con.createStatement();
            ResultSet rs = ps2.executeQuery(sql2);
            
            while (rs.next()) {
                msg = rs.getString("max(idNot)");
            }
            
            
            con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro cadastrar notificação");
            msg = "Erro ao adicionar notificação";
        }
        return msg;
        

    }

    public String ApagarNot(String idNot) throws ClassNotFoundException {
        
        String msg;
        Conexao c = new Conexao();

        try {
            Connection con;
            con = c.conectar();
            String sql = "DELETE FROM `notificacao` WHERE idNot = "+idNot+";";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            con.close();
            msg = "Notificação deletada com sucesso";
            
        } catch (SQLException e) {
            e.printStackTrace();
            msg = "Erro ao deletar notificação";
        }
        return msg;

    }
    
    public List<NotificacaoBean> RecuperarNot() throws ClassNotFoundException {
        
        List<NotificacaoBean> notes = new ArrayList<NotificacaoBean>();
        Conexao c = new Conexao();

        try {
            Connection con;
            con = c.conectar();
            String sql = "select * from notificacao";
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                NotificacaoBean nb = new NotificacaoBean();
                nb.setIdNot(rs.getInt("idNot"));
                nb.setNumCelular(rs.getInt("NumCelular"));
                nb.setVagas_idVagas(rs.getInt("Vagas_idVagas"));

                notes.add(nb);
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;

    }
    
        public List<SetorBean> OcupSetor(String idEst) throws ClassNotFoundException{
        List<SetorBean> setor = new ArrayList<SetorBean>();
        Conexao c = new Conexao();

        try {
            Connection con;
            con = c.conectar();
            String sql = "select * from setor where Estacionamento_idEstacionamento = "+idEst+"";
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                SetorBean sb = new SetorBean();
                sb.setEstacionamento_idEstacionamento(rs.getInt("Estacionamento_idEstacionamento"));
                sb.setIdSetor(rs.getInt("idSetor"));
                sb.setNumeroVagasOc(rs.getInt("NumeroVagasOc"));
                sb.setNumeroVagasTotal(rs.getInt("NumeroVagasTotal"));

                setor.add(sb);
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setor;
        
        }

}
