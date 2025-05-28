/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.a3;
import java.sql.*;

/**
 *
 * @author evert
 */
public class ConexaoSQLite {
    private static final String URL = "jdbc:sqlite:meu_banco.db";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

}
