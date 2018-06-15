/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author OWL
 */
public class DaoEst {

    public void Atualizar(String atu, String ant) throws ClassNotFoundException {

        String atual = atu;
        String anterior = ant;
        //String est = "1";
        
        Conexao c = new Conexao();

        try {
            Connection con;
            con = c.conectar();
            for (int i = 0; i < atual.length(); i++) {
                 int setor = i/2 + 1; 
                if (atual.charAt(i)=='0' && anterior.charAt(i) == '0') {
                    //caso vaga contifnue vazia
                    System.out.println("Vaga "+i+" continua vazia");
                    System.out.println("Setor "+setor);
                    con.close();
                }else if (atual.charAt(i)=='1' && anterior.charAt(i) == '0') {
                    //caso vaga seja ocupada
                    System.out.println("Vaga "+i+" foi ocupada");
                    String sql = "UPDATE `vagas` SET `Estado` = 1,`TempOc` = TempOc + 1,"
                            + "`VezesOc` = VezesOc + 1 WHERE `idVagas` = "+i+";";
                    System.out.println("SQL "+sql);
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.executeUpdate();
                    ps.close();
                    //caso vaga permaneca ocupada
                    String sql1 = "UPDATE `setor` SET `NumeroVagasOc` = NumeroVagasOc + 1"
                            + " WHERE `idSetor` = "+setor+";";
                    System.out.println("SQL "+sql1);
                    PreparedStatement ps1 = con.prepareStatement(sql1);
                    ps1.executeUpdate();
                    ps1.close();
                    con.close();

                } else if (atual.charAt(i)=='1' && anterior.charAt(i) == '1') {
                    
                    String sql = "UPDATE `vagas` SET `TempOc` = TempOc + 1 WHERE `idVagas` = "+i+";";
                    System.out.println("SQL "+sql);
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.executeUpdate();
                    ps.close();
                    con.close();
                
                } else if (atual.charAt(i)=='0' && anterior.charAt(i) == '1') {
                    String sql = "UPDATE `vagas` SET `Estado` = 0 WHERE idVagas = "+i+";";
                    System.out.println("SQL "+sql);
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.executeUpdate();
                    ps.close();
                    

                    String sql2 ="UPDATE `setor` SET `NumeroVagasOc` = NumeroVagasOc - 1 WHERE `idSetor` = "+setor+";";
                    System.out.println("SQL "+sql2);
                    PreparedStatement ps2 = con.prepareStatement(sql2);
                    ps2.executeUpdate();
                    ps2.close();
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro na atualização");
        }

    }
}
