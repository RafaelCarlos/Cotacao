package com.example.conversor.model;

/**
 * Created by Luídne on 31/10/2015.
 */
public class Bolsa {
    private String cotacao;
    private String cotacaoEuro;


    private String variacao;

    public Bolsa() {
    }

    public Bolsa(String cotacao, String variacao, String cotacaoEuro) {
        this.cotacao = cotacao;
        this.variacao = variacao;
        this.cotacaoEuro = cotacaoEuro;
    }

    public String getCotacao() {
        return cotacao;
    }

    public void setCotacao(String cotacao) {
        this.cotacao = cotacao;
    }

    public String getVariacao() {
        return variacao;
    }

    public void setVariacao(String variacao) {
        this.variacao = variacao;
    }


    public String getCotacaoEuro() {
        return cotacaoEuro;
    }

    public void setCotacaoEuro(String cotacaoEuro) {
        this.cotacaoEuro = cotacaoEuro;
    }

    @Override
    public String toString() {
        return "Bolsa{" +
                "cotacao='" + cotacao + '\'' +
                ", cotacaoEuro='" + cotacaoEuro + '\'' +
                ", variacao='" + variacao + '\'' +
                '}';
    }
}
