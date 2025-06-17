/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.a3.models;

import java.util.Date;

/**
 *
 * @author evert
 */
public class LotePerecivel extends Lote {
    
  private Date validade ;
  private int  bloqueado;
  private Date	dataBloqueio ; 
  private String motivoBloqueio ;   
    
    
    public LotePerecivel(int id, String identificador, int produto, double estoque, Date validade, int tipo, int bloqueado, Date dataBloqueio, String motivoBloqueio) {               
        super(id, identificador, produto, estoque,  tipo);
        this.validade = validade; 
        this.bloqueado = bloqueado; 
        this.dataBloqueio = dataBloqueio;
        this.motivoBloqueio = motivoBloqueio;
    }
    
      public LotePerecivel( String identificador, int produto, double estoque, Date validade, int tipo) {
        super(identificador, produto, estoque,  tipo);
        this.validade = validade; 

    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Date getDataBloqueio() {
        return dataBloqueio;
    }

    public void setDataBloqueio(Date data_bloqueio) {
        this.dataBloqueio = data_bloqueio;
    }

    public String getMotivoBloqueio() {
        return motivoBloqueio;
    }

    public void setMotivo_bloqueio(String motivoBloqueio) {
        this.motivoBloqueio = motivoBloqueio;
    }
}


