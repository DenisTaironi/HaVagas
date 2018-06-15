/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import java.io.Serializable;

/**
 *
 * @author OWL
 */
public class SetorBean implements Serializable  {
    
    private int idSetor;
    private int NumeroVagasTotal;
    private int NumeroVagasOc;
    private int Estacionamento_idEstacionamento;

    /**
     * @return the idSetor
     */
    public int getIdSetor() {
        return idSetor;
    }

    /**
     * @param idSetor the idSetor to set
     */
    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    /**
     * @return the NumeroVagasTotal
     */
    public int getNumeroVagasTotal() {
        return NumeroVagasTotal;
    }

    /**
     * @param NumeroVagasTotal the NumeroVagasTotal to set
     */
    public void setNumeroVagasTotal(int NumeroVagasTotal) {
        this.NumeroVagasTotal = NumeroVagasTotal;
    }

    /**
     * @return the NumeroVagasOc
     */
    public int getNumeroVagasOc() {
        return NumeroVagasOc;
    }

    /**
     * @param NumeroVagasOc the NumeroVagasOc to set
     */
    public void setNumeroVagasOc(int NumeroVagasOc) {
        this.NumeroVagasOc = NumeroVagasOc;
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
