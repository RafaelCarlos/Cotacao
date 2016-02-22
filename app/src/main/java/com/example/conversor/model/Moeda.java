package com.example.conversor.model;

/**
 * Created by rafaellcarloss on 26/10/15.
 */
public class Moeda {

    private float cotacao;
   private float variacao;

    public Moeda(float cotacao, float variacao) {
        this.cotacao = cotacao;
        this.variacao = variacao;
    }

    public float getCotacao() {
        return cotacao;
    }

    public void setCotacao(float cotacao) {
        this.cotacao = cotacao;
    }

    public float getVariacao() {
        return variacao;
    }

    public void setVariacao(float variacao) {
        this.variacao = variacao;
    }
}
