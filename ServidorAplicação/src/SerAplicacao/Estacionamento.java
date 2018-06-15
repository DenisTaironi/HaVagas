/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SerAplicacao;

import BEAN.NotificacaoBean;
import BEAN.SetorBean;
import BEAN.VagasBean;
import DAO.DAOest;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author OWL
 */
public class Estacionamento extends Thread {

    private Socket conexao;
    
    
    public Estacionamento(Socket s){
        
         conexao = s;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
    
        ServerSocket s = null;
        System.out.println("Esperando conecao");
        
        DAOest da = new DAOest();
        
        
        try{
            s = new ServerSocket(6667); 
            
            while(true){
                Socket con = s.accept();
                System.out.println("Conexao recebida");
                Thread t = new Estacionamento(con);
                t.start();
            }
        }catch(IOException e){
                
                System.out.println("IOException " + e);
                System.out.println("Erro na conexao");
            
    }
        
    }

    public void run(){
    
        try{
            ObjectInputStream is = new ObjectInputStream(conexao.getInputStream());
            String st = (String) is.readObject();
            System.out.println("String: " +st);
            
            String s[]= st.split(",");
            for(int i = 0; i < s.length; i++){
                System.out.println("Item "+i+":" + s[i]);
                
            }
            
            DAOest dao = new DAOest();
            Socket sOut;
            ObjectOutputStream os;
            int id;
            
            switch(s[0]){
                
                case "0":// caso mapa
                    System.out.println("Caso 0");
                    id = Integer.parseInt(s[1]);
                    String ip = conexao.getInetAddress().getHostAddress();
                    System.out.println("Respondendo para "+ip);
                    List<VagasBean> l = dao.MapaEst(id);
                    
                    sOut = new Socket(ip, 6668);
                    os = new ObjectOutputStream(sOut.getOutputStream());
                    os.writeObject(l);
                    break;
                    
                case "1"://relatorio
                    System.out.println("Caso 1");
                    id = Integer.parseInt(s[1]);
                    String ip2 = conexao.getInetAddress().getHostAddress();
                    List<String> a =dao.Rel(id);
                    
                    sOut = new Socket(ip2, 6668);
                    os = new ObjectOutputStream(sOut.getOutputStream());
                    os.writeObject(a);
                    break;
                
                case "2"://pagamento
                    System.out.println("Caso 2");
                    String cod = s[1];
                    String ip3 = conexao.getInetAddress().getHostAddress();
                    String Pagar = dao.Pagar(cod);
                    
                    sOut = new Socket(ip3, 6668);
                    os = new ObjectOutputStream(sOut.getOutputStream());
                    os.writeObject(Pagar);
                    break;
                    
                    
                case "3"://cadastro
                    System.out.println("Caso 3");
                    String vaga = s[1];
                    String tel = s[2];
                    dao.CadastrarNot(vaga, tel);
                    break;
                
                case "4":
                    System.out.println("Caso 4");
                    String idNot = s[1];
                    dao.ApagarNot(idNot);
                    break;
                    
                case "5":
                    System.out.println("Caso 5");
                    String idEst = s[1];
                    String ip4 = conexao.getInetAddress().getHostAddress();
                    List<SetorBean> OcupSetor = dao.OcupSetor(idEst);
                    
                    sOut = new Socket(ip4, 6668);
                    os = new ObjectOutputStream(sOut.getOutputStream());
                    os.writeObject(OcupSetor);
                    break;
                    
                case "6":
                    System.out.println("Caso 6");
                    List<NotificacaoBean> rnot = dao.RecuperarNot();
                    String ip5 = conexao.getInetAddress().getHostAddress();
                    
                    sOut = new Socket(ip5,6668);
                    os = new ObjectOutputStream(sOut.getOutputStream());
                    os.writeObject(rnot);
                    break;
            }
            
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }
    
}
