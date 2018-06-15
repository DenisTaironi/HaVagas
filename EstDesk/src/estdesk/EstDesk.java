/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estdesk;

import VIEW.TelaInfo;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OWL
 */
public class EstDesk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        System.out.println("INICIANDO");
        TelaInfo ti = new TelaInfo();

        while(true){
        try {
            ti.Atualizar();
            ti.AtualizarSetor();
            ti.AtualizarNot();
        } catch (IOException ex) {
            System.out.println("ERRO AO CONTACTAR SERVIDOR, REINICIE A APLICAÇÃO...");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EstDesk.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Vou Dormir");
        sleep(15000);
            System.out.println("Acordei");
        }
    }

}
