
package com.mycompany.a3.models;

/**
 *
 * @author everton
 */
public class NaoPerecivel extends Produtos{
    
    public NaoPerecivel(int id, String descricao, double valor, int tipo, double estoque) {
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
