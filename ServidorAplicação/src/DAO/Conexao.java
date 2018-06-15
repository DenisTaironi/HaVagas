/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Pedro
 */
public class Conexao {
    
    //endereço do banco
    String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    // usuário e senha de entrada
    String DB_USER = "root";
    String DB_PASS = "admin";
    
    
    public Connection conectar() throws ClassNotFoundException, SQLException{
        
        //envocando a biblioteca
        Class.forName("com.mysql.jdbc.Driver");
        
        //passando os parametros de conexão
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        
    return con;
}}
