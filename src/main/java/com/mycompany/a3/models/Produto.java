package com.mycompany.a3.models;

/**
 *
 * @author everton
 */
public class Produto {

    private int id;
    private String descricao;
    private double valor;
    private int tipo; // 1 - Perecivel; 2 - Não perecível
    private double estoque;

    public Produto(int id, String descricao, double valor, int tipo, double estoque) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.estoque = estoque;
    }
    
     public Produto( String descricao, double valor, int tipo) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        
    }

    public boolean inserirProduto(Produto produto) {

        return true;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public int getTipo() {
        return tipo;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

}
