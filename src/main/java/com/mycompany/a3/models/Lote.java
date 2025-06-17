/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.a3.models;

import java.sql.Date;

public class Lote {

    private int id;
    private String identificador;
    private int produto;
    private double estoque;
    private int tipo;

    public Lote(int id, String identificador, int produto, double estoque, int tipo) {
        this.id = id;
        this.identificador = identificador;
        this.produto = produto;
        this.estoque = estoque;
        this.tipo = tipo;
    }

    public Lote(String identificador, int produto, double estoque, int tipo) {
        this.id = id;
        this.identificador = identificador;
        this.produto = produto;
        this.estoque = estoque;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

    public int getTipo() {
        return this.tipo;
    }

    @Override
    public String toString() {
        return identificador;
    }
}
