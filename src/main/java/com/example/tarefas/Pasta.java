package com.example.tarefas;

import java.io.Serializable;

public class Pasta implements Serializable {
    private int pastaId;
    private String nomePasta;
    private String descricao;


    public int getPastaId() {
        return pastaId;
    }

    public void setPastaId(int pastaId) {
        this.pastaId = pastaId;
    }

    public String getNomePasta() {
        return nomePasta;
    }

    public void setNomePasta(String nomePasta) {
        this.nomePasta = nomePasta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String toString() {


        return getNomePasta().toString() + " descricao: "+getDescricao().toString();
    }
}
