// Juan Fuentes Rufino RM557673
// Pedro Henrique Silva Batista RM558137
// Fernando Carlos Colque Huaranca RM558095 

package br.com.fiap.bean;
/**
 * Representa um foco de incêndio registrado no sistema.
 */

import java.time.LocalDate;

public class Foco {
    private int idFoco;
    private int idCidadaoVinculado;
    private boolean isAtendido;
    private LocalDate dataFoco;
    private String gravidade;

    // construtores

    public Foco() {
    }

    public Foco(int idFoco, int idCidadaoVinculado,LocalDate dataFoco ,boolean statusFoco, String gravidade) {
        this.idFoco = idFoco;
        this.idCidadaoVinculado = idCidadaoVinculado;
        this.dataFoco = dataFoco;
        this.isAtendido = statusFoco;
        this.gravidade = gravidade;
    }

    // getters/setters

    public int getIdFoco() {
        return idFoco;
    }

    public void setIdFoco(int idFoco) {
        this.idFoco = idFoco;
    }

    public int getIdCidadaoVinculado() {
        return idCidadaoVinculado;
    }

    public void setIdCidadaoVinculado(int idCidadaoVinculado) {
        this.idCidadaoVinculado = idCidadaoVinculado;
    }

    public boolean isAtendido() {
        return isAtendido;
    }

    public void setAtendido(boolean atendido) {
        isAtendido = atendido;
    }

    public LocalDate getDataFoco() {
        return dataFoco;
    }

    public void setDataFoco(LocalDate dataFoco) {
        this.dataFoco = dataFoco;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }
}



