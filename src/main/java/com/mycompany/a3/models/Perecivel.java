/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.a3.models;

/**
 *
 * @author everton
 */
public class Perecivel extends Produtos{

    public Perecivel(int id, String descricao, double valor, int tipo, double estoque) {
        super(id, descricao, valor, tipo, estoque);
    } 

    @Override
    public boolean retirarProdutos(double quantidade) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean inserirProdutos(double quantidade) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
