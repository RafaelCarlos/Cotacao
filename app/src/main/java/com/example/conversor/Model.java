package com.example.conversor;

import java.io.Serializable;

/**
 * Created by rafaellcarloss on 24/10/15.
 */
public class Model implements Serializable {


    private Float cotacao;



    @Override
    public String toString() {
        return "Model{" +
                "cotacao=" + cotacao +
                '}';
    }
}
