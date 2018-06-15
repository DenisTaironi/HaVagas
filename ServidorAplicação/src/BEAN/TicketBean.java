/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author OWL
 */
public class TicketBean implements Serializable  {
    
    private int idTicket;
    private int codBarras;
    private int Estado;
    private int Estacionamento_idEstacionamento;

    /**
     * @return the idTicket
     */
    public int getIdTicket() {
        return idTicket;
    }

    /**
     * @param idTicket the idTicket to set
     */
    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    /**
     * @return the codBarras
     */
    public int getCodBarras() {
        return codBarras;
    }

    /**
     * @param codBarras the codBarras to set
     */
    public void setCodBarras(int codBarras) {
        this.codBarras = codBarras;
    }

    /**
     * @return the Estado
     */
    public int getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(int Estado) {
        this.Estado = Estado;
    }


    /**
     * @return the Estacionamento_idEstacionamento
     */
    public int getEstacionamento_idEstacionamento() {
        return Estacionamento_idEstacionamento;
    }

    /**
     * @param Estacionamento_idEstacionamento the Estacionamento_idEstacionamento to set
     */
    public void setEstacionamento_idEstacionamento(int Estacionamento_idEstacionamento) {
        this.Estacionamento_idEstacionamento = Estacionamento_idEstacionamento;
    }
}
