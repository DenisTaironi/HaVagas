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
public class NotificacaoBean implements Serializable {
    
    private int idNot;
    private int NumCelular;
    private int Vagas_idVagas;

    /**
     * @return the idNot
     */
    public int getIdNot() {
        return idNot;
    }

    /**
     * @param idNot the idNot to set
     */
    public void setIdNot(int idNot) {
        this.idNot = idNot;
    }

    /**
     * @return the NumCelular
     */
    public int getNumCelular() {
        return NumCelular;
    }

    /**
     * @param NumCelular the NumCelular to set
     */
    public void setNumCelular(int NumCelular) {
        this.NumCelular = NumCelular;
    }

    /**
     * @return the Vagas_idVagas
     */
    public int getVagas_idVagas() {
        return Vagas_idVagas;
    }

    /**
     * @param Vagas_idVagas the Vagas_idVagas to set
     */
    public void setVagas_idVagas(int Vagas_idVagas) {
        this.Vagas_idVagas = Vagas_idVagas;
    }

}
