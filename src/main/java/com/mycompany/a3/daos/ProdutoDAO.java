/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.a3.daos;

import com.mycompany.a3.ConexaoSQLite;
import com.mycompany.a3.Dialogo;
import com.mycompany.a3.models.Produto;
import com.mycompany.a3.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author everton
 */
public class ProdutoDAO {

    public boolean Insert(Produto produto) {
        String sql = "INSERT INTO produtos(descricao, valor, tipo, estoque) values (?,?,?,0)";
        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getDescricao());
            pstmt.setDouble(2, produto.getValor());
            pstmt.setInt(3, produto.getTipo());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            Dialogo.exibirDialog(e.toString());
            return false;
        }
    }

    public Produto Select(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Produto(rs.getInt("id"), rs.getString("descricao"), rs.getDouble("valor"), rs.getInt("tipo"), rs.getDouble("estoque"));
            }

        } catch (SQLException e) {
            Dialogo.exibirDialog(e.getMessage());
            return null;
        }
        return null;
    }

    public List<Produto> SelectAll() {
        String sql = "SELECT * FROM produtos;";

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                Produto produto = new Produto(rs.getInt("id"), rs.getString("descricao"), rs.getDouble("valor"), rs.getInt("tipo"), rs.getDouble("estoque"));
                produtos.add(produto);
            }
            return produtos;
        } catch (SQLException e) {
            Dialogo.exibirDialog(e.getMessage());
        }
        return null;
    }

    public boolean Update(Produto produto) {
        String sql = "UPDATE produtos SET descricao = ?, valor = ?, tipo = ?, estoque = ? WHERE id = ?";
        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getDescricao());
            pstmt.setDouble(2, produto.getValor());
            pstmt.setInt(3, produto.getTipo());
            pstmt.setDouble(4, produto.getEstoque());
            pstmt.setInt(5, produto.getId());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            Dialogo.exibirDialog(e.getMessage());
            return false;
        }
    }

    public boolean Delete(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Dialogo.exibirDialog(e.getMessage());
            return false;
        }
    }

}
