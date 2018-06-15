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
public class VagasBean implements Serializable  {
    
    private int idVagas;
    private int Setor_idSetor;
    private int Estado;
    private int TempOc;
    private int VezesOc;

    /**
     * @return the idVagas
     */
    public int getIdVagas() {
        return idVagas;
    }

    /**
     * @param idVagas the idVagas to set
     */
    public void setIdVagas(int idVagas) {
        this.idVagas = idVagas;
    }

    /**
     * @return the Setor_idSetor
     */
    public int getSetor_idSetor() {
        return Setor_idSetor;
    }

    /**
     * @param Setor_idSetor the Setor_idSetor to set
     */
    public void setSetor_idSetor(int Setor_idSetor) {
        this.Setor_idSetor = Setor_idSetor;
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
     * @return the TempoOc
     */
    public int getTempOc() {
        return TempOc;
    }

    /**
     * @param TempoOc the TempoOc to set
     */
    public void setTempOc(int TempoOc) {
        this.TempOc = TempoOc;
    }

    /**
     * @return the VezesOc
     */
    public int getVezesOc() {
        return VezesOc;
    }

    /**
     * @param VezesOc the VezesOc to set
     */
    public void setVezesOc(int VezesOc) {
        this.VezesOc = VezesOc;
    }
}
