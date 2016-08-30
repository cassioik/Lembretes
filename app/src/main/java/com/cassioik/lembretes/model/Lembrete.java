package com.cassioik.lembretes.model;

/**
 * Created by Cássio on 8/29/2016.
 */
public class Lembrete {
    private int id;
    private String titulo;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        String retorno = getId() +" - "+ getTitulo() +" - "+ getDescricao();
        return retorno;
    }
}
