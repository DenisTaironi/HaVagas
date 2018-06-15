/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coletor;

import DAO.DaoEst;
import static java.lang.Thread.sleep;
import java.util.Timer;

/**
 *
 * @author OWL
 */
public class Coletor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
        // TODO code application logic here
        DaoEst d = new DaoEst();
        String anterior = "0101";
        
        while(true){
            System.out.println("Fazendo coisas");
            String atual = "0000";// aqui ele lê o serial e atualiza a string  
            d.Atualizar(atual, anterior);
            anterior = atual;
            sleep(15000);
        }
    }
    
}
