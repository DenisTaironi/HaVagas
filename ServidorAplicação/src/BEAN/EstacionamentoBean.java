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
public class EstacionamentoBean implements Serializable  {
    
    private int idEstacionamento;
    private String NomeFantasia;
    private Timestamp DtUltRelatorio; 

    /**
     * @return the idEstacionamento
     */
    public int getIdEstacionamento() {
        return idEstacionamento;
    }

    /**
     * @param idEstacionamento the idEstacionamento to set
     */
    public void setIdEstacionamento(int idEstacionamento) {
        this.idEstacionamento = idEstacionamento;
    }

    /**
     * @return the NomeFantasia
     */
    public String getNomeFantasia() {
        return NomeFantasia;
    }

    /**
     * @param NomeFantasia the NomeFantasia to set
     */
    public void setNomeFantasia(String NomeFantasia) {
        this.NomeFantasia = NomeFantasia;
    }

    /**
     * @return the DtUltRelatorio
     */
    public Timestamp getDtUltRelatorio() {
        return DtUltRelatorio;
    }

    /**
     * @param DtUltRelatorio the DtUltRelatorio to set
     */
    public void setDtUltRelatorio(Timestamp DtUltRelatorio) {
        this.DtUltRelatorio = DtUltRelatorio;
    }
    
}
