package com.example.pesquisadeinteresseapp.model;

public class Preferencia {
    int id, faixa;
    String nome;

    public Preferencia(int id, String nome, int faixa) {
        this.id = id;
        this.nome = nome;
        this.faixa = faixa;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getFaixa() {
        return faixa;
    }

}
