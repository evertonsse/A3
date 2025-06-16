package com.mycompany.a3.daos;

import com.mycompany.a3.ConexaoSQLite;
import com.mycompany.a3.models.Lote;
import com.mycompany.a3.models.LotePerecivel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LotesDAO {

    public boolean insert(Lote lote) {
        String sql = "INSERT INTO lotes (identificador, produto, estoque, tipo, validade, bloqueado, data_bloqueio, motivo_bloqueio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lote.getIdentificador());
            stmt.setInt(2, lote.getProduto());
            stmt.setDouble(3, lote.getEstoque());
            stmt.setInt(4, lote.getTipo());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Lote> selectAll() {
        List<Lote> lotes = new ArrayList<>();
        String sql = "SELECT * FROM lotes";

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int tipo = rs.getInt("tipo");
                if (tipo == 0) {
                    Lote lote = new Lote(
                            rs.getInt("id"),
                            rs.getString("identificador"),
                            rs.getInt("produto"),
                            rs.getDouble("estoque"),
                            rs.getInt("tipo")
                    );
                    lotes.add(lote);
                } else {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lotes;
    }

    public Lote select(int id) {
        String sql = "SELECT * FROM lotes WHERE id = ?";

        try (Connection conn = ConexaoSQLite.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int tipo = rs.getInt("tipo");
                if (tipo == 0) {
                    return new Lote(
                            rs.getInt("id"),
                            rs.getString("identificador"),
                            rs.getInt("produto"),
                            rs.getDouble("estoque"),
                            rs.getInt("tipo")
                    );
                } else {
                    return new LotePerecivel(
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retorna null se nenhum lote for encontrado
    }

    public void update(Lote lote) {
        String sql = "UPDATE lotes SET identificador=?, produto=?, estoque=?, tipo=?, validade=?, bloqueado=?, data_bloqueio=?, motivo_bloqueio=? WHERE id=?";

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lote.getIdentificador());
            stmt.setInt(2, lote.getProduto());
            stmt.setDouble(3, lote.getEstoque());
            stmt.setInt(4, lote.getTipo());
            stmt.setInt(9, lote.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM lotes WHERE id=?";

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
