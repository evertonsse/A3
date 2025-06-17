package com.mycompany.a3.daos;

import com.mycompany.a3.ConexaoSQLite;
import com.mycompany.a3.DataParse;
import com.mycompany.a3.models.Lote;
import com.mycompany.a3.models.LotePerecivel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LotesDAO {

    public boolean insert(Lote lote) {
        String sql = "INSERT INTO lotes (identificador, produto, estoque, tipo, validade, bloqueado, data_bloqueio, motivo_bloqueio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lote.getIdentificador());
            stmt.setInt(2, lote.getProduto());
            stmt.setDouble(3, lote.getEstoque());
            stmt.setInt(4, lote.getTipo());
            if (lote instanceof LotePerecivel lotePerecivel) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String dataFormatada = sdf.format(lotePerecivel.getValidade());

                stmt.setString(5, dataFormatada);
            }
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

                    Date dataBloqueio = new Date();
                    if (rs.getString("data_bloqueio") != null) {
                        dataBloqueio = DataParse.parseDate(rs.getString("data_bloqueio"));
                    }

                    Date dataValidade = new Date();
                    if (rs.getString("validade") != null) {
                        dataValidade = DataParse.parseDate(rs.getString("validade"));
                    }

                    LotePerecivel lote = new LotePerecivel(
                            rs.getInt("id"),
                            rs.getString("identificador"),
                            rs.getInt("produto"),
                            rs.getDouble("estoque"),
                            dataValidade,
                            rs.getInt("tipo"),
                            rs.getInt("bloqueado"),
                            dataBloqueio,
                            rs.getString("motivo_bloqueio")
                    );
                    lotes.add(lote);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lotes;
    }

    public Lote select(int id) {
        String sql = "SELECT * FROM lotes WHERE id = ?";

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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
                            DataParse.parseDate(rs.getString("validade")),
                            rs.getInt("tipo"),
                            rs.getInt("bloqueado"),
                            DataParse.parseDate(rs.getString("data_bloqueio")),
                            rs.getString("motivo_bloqueio")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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

    public double getEstoqueAtualPorProduto(int idProduto) {
        double estoque = 0.0;
        String sql = "SELECT SUM(estoque) as total FROM lotes WHERE produto = ? AND estoque > 0";

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                estoque = rs.getDouble("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estoque;
    }

    public List<Lote> selectByProduto(int idProduto) {
        List<Lote> lotes = new ArrayList<>();
        String sql = "SELECT * FROM lotes WHERE produto = ?";

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
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
                    Date dataBloqueio = null;
                    if (rs.getString("data_bloqueio") != null) {
                        dataBloqueio = DataParse.parseDate(rs.getString("data_bloqueio"));
                    }

                    Date dataValidade = null;
                    if (rs.getString("validade") != null) {
                        dataValidade = DataParse.parseDate(rs.getString("validade"));
                    }

                    LotePerecivel lote = new LotePerecivel(
                            rs.getInt("id"),
                            rs.getString("identificador"),
                            rs.getInt("produto"),
                            rs.getDouble("estoque"),
                            dataValidade,
                            rs.getInt("tipo"),
                            rs.getInt("bloqueado"),
                            dataBloqueio,
                            rs.getString("motivo_bloqueio")
                    );
                    lotes.add(lote);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lotes;
    }

    public List<Lote> listarLotesDisponiveisPorProduto(int idProduto) {
        List<Lote> lotes = new ArrayList<>();

        String sql = "SELECT * FROM lotes WHERE produto = ?   AND estoque > 0   AND (bloqueado <> 1 OR bloqueado IS NULL) ORDER BY validade ASC;";

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
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

                    Date dataBloqueio = null;
                    if (rs.getString("data_bloqueio") != null) {
                        dataBloqueio = DataParse.parseDate(rs.getString("data_bloqueio"));
                    }

                    Date dataValidade = null;
                    if (rs.getString("validade") != null) {
                        dataValidade = DataParse.parseDate(rs.getString("validade"));
                    }

                    LotePerecivel lote = new LotePerecivel(
                            rs.getInt("id"),
                            rs.getString("identificador"),
                            rs.getInt("produto"),
                            rs.getDouble("estoque"),
                            dataValidade,
                            rs.getInt("tipo"),
                            rs.getInt("bloqueado"),
                            dataBloqueio,
                            rs.getString("motivo_bloqueio")
                    );
                    lotes.add(lote);
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lotes;
    }

    public void marcarLotesInativos() {
   
        String sql = "UPDATE lotes SET bloqueado = 1, data_bloqueio = ?,  motivo_bloqueio = 'FORA DA VALIDADE' WHERE validade < ? AND (bloqueado = 0 OR bloqueado IS NULL)";
        System.out.println(sql);

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            
            String dataAtualFormatada = sdf.format(new Date());
            
            System.out.println(dataAtualFormatada);

            stmt.setString(1, dataAtualFormatada);
            stmt.setString(2, dataAtualFormatada);

            int linhasAfetadas = stmt.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

}
