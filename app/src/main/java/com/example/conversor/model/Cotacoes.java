package com.example.conversor.model;

/**
 * Created by Luídne on 31/10/2015.
 */
public class Cotacoes {
    private Bolsa bovespa;
    private Bolsa dolar;
    private Bolsa euro;
    private String atualizacao;

    public Cotacoes() {
    }

    public Cotacoes(Bolsa bovespa, Bolsa dolar, Bolsa euro, String atualizacao) {
        this.bovespa = bovespa;
        this.dolar = dolar;
        this.euro = euro;
        this.atualizacao = atualizacao;
    }

    public Bolsa getBovespa() {
        return bovespa;
    }

    public void setBovespa(Bolsa bovespa) {
        this.bovespa = bovespa;
    }

    public Bolsa getDolar() {
        return dolar;
    }

    public void setDolar(Bolsa dolar) {
        this.dolar = dolar;
    }

    public Bolsa getEuro() {
        return euro;
    }

    public void setEuro(Bolsa euro) {
        this.euro = euro;
    }

    public String getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(String atualizacao) {
        this.atualizacao = atualizacao;
    }

    @Override
    public String toString() {
        return "Cotacoes{" + "bovespa=" + bovespa + ", dolar=" + dolar + ", euro=" + euro + ", atualizacao=" + atualizacao + '}';
    }

}
