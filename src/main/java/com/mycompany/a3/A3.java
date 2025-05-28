/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


package com.mycompany.a3;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.mycompany.a3.Interfaces.Login;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.sql.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author evert
 */
public class A3 {

    public static void main(String[] args) {
       try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf() {});
        } catch (UnsupportedLookAndFeelException e) {
        }
        Login login = new Login(); 
        login.setVisible(true);
    }
}
