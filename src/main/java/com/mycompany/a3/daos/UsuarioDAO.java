/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.a3.daos;

import com.mycompany.a3.ConexaoSQLite;
import com.mycompany.a3.Dialogo;
import com.mycompany.a3.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public boolean Insert(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nome, senha) values (?,?)";

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getSenha());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
             Dialogo.exibirDialog(e.toString());
            return false;
        }
    }

    public Usuario Select(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"));
            }

        } catch (SQLException e) {
            Dialogo.exibirDialog(e.getMessage());
            return null;
        }
        return null;

    }

    public List<Usuario> SelectAll() {
        String sql = "SELECT * FROM usuarios;";

        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"));
                usuarios.add(usuario);

            }
            return usuarios;
        } catch (SQLException e) {
            Dialogo.exibirDialog(e.getMessage());

        }
        return null;
    }

    public boolean Update(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, senha = ? WHERE id = ?";
        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getSenha());
            pstmt.setInt(3, usuario.getId());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            Dialogo.exibirDialog(e.getMessage());
            return false;
        }
    }

    public boolean Delete(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = ConexaoSQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Dialogo.exibirDialog(e.getMessage());
            return false;
        }
    }

}
