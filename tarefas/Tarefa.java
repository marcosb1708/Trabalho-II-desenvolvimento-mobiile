package com.example.tarefas;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private int tarefaId;
    private int PastaId;
    private String tarefaNome;
    private String dataTermino;
    private String localizacao;
    private String status;

    public int getTarefaId() {
        return tarefaId;
    }

    public void setTarefaId(int tarefaId) {
        this.tarefaId = tarefaId;
    }

    public String getTarefaNome() {
        return tarefaNome;
    }

    public void setTarefaNome(String tarefaNome) {
        this.tarefaNome = tarefaNome;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPastaId() {
        return PastaId;
    }

    public void setPastaId(int pastaId) {
        PastaId = pastaId;
    }


    @Override
    public String toString() {

        return getTarefaNome().toString();
    }
}


