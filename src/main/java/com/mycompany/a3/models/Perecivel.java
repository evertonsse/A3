/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.a3.models;

/**
 *
 * @author everton
 */
public class Perecivel extends Produto{

    public Perecivel(int id, String descricao, double valor,  double estoque) {
        super(id, descricao, valor,1, estoque);
    } 

  
    public boolean retirarProdutos(double quantidade) {
        throw new UnsupportedOperationException("Not supported yet.");
    }  
    
}
