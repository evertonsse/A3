/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.a3.daos;

import com.mycompany.a3.ConexaoSQLite;
import com.mycompany.a3.models.Movimentacao;
import com.mycompany.a3.models.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author evert
 */
public class MovimentacaoDAO {

    public boolean inserir(Movimentacao movimentacao) {
        String sql = "INSERT INTO movimentacoes (lote, tipo, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, movimentacao.getLote());
            stmt.setInt(2, movimentacao.getTipo());
            stmt.setDouble(3, movimentacao.getQuantidade());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;

        }
    }
    
   public List<Movimentacao> selectAll() {
    List<Movimentacao> movimentacoes = new ArrayList<>();
    String sql = "SELECT * FROM movimentacoes";

    try (Connection conn = ConexaoSQLite.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id");
            int lote = rs.getInt("lote");
            int tipo = rs.getInt("tipo");
            double quantidade = rs.getDouble("quantidade");

            Movimentacao mov = new Movimentacao(lote, tipo, quantidade);
            mov.setId(id);

            movimentacoes.add(mov);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return movimentacoes;
}
    
}
