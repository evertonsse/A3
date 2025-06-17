
package com.mycompany.a3.models;

public class Movimentacao {
    private int id; 
    private int lote;
    private int tipo;
    private double quantidade;

    public Movimentacao(int lote, int tipo, double quantidade) {
        this.lote = lote;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double qtd) {
        this.quantidade = qtd;
    }
    
    
    
}
