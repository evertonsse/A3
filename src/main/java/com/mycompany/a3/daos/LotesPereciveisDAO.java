/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.a3.daos;
import com.mycompany.a3.ConexaoSQLite;
import com.mycompany.a3.Dialogo;
import com.mycompany.a3.models.Lote;
import com.mycompany.a3.models.LotePerecivel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LotesPereciveisDAO extends LotesDAO {
    @Override
    public boolean insert(Lote lote) {
        LotePerecivel lotePerecivel = (LotePerecivel) lote;
        String sql = "INSERT INTO lotes (identificador, produto, estoque, tipo, validade, bloqueado, data_bloqueio, motivo_bloqueio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lotePerecivel.getIdentificador());
            stmt.setInt(2, lotePerecivel.getProduto());
            stmt.setDouble(3, lotePerecivel.getEstoque());
            stmt.setInt(4, lotePerecivel.getTipo());
            if (lotePerecivel.getValidade() != null) {
                stmt.setDate(5, new java.sql.Date(lotePerecivel.getValidade().getTime()));
            } else {
                stmt.setNull(5, Types.DATE);
            }
            stmt.setInt(6, lotePerecivel.getBloqueado());
            if (lotePerecivel.getDataBloqueio() != null) {
                stmt.setDate(7, new java.sql.Date(lotePerecivel.getDataBloqueio().getTime()));
            } else {
                stmt.setNull(7, Types.DATE);
            }
            stmt.setString(8, lotePerecivel.getMotivoBloqueio());
            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(LotesPereciveisDAO.class.getName()).log(Level.SEVERE, null, ex);
            Dialogo.exibirDialog("Erro ao inserir lote: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Lote> selectAll() {
        String sql = "SELECT * FROM lotes ";
        List<Lote> lotes = new ArrayList<>();

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LotePerecivel lote = new LotePerecivel(
                        rs.getInt("id"),
                        rs.getString("identificador"),
                        rs.getInt("produto"),
                        rs.getDouble("estoque"),
                        rs.getDate("validade"),
                        rs.getInt("tipo"),
                        rs.getInt("bloqueado"),
                        rs.getDate("data_bloqueio"),
                        rs.getString("motivo_bloqueio")
                );
                lotes.add(lote);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LotesPereciveisDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lotes;
    }

}
