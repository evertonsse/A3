/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.a3;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.mycompany.a3.Interfaces.LoginTela;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author evert
 */
public class A3 {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf() {
            });
        } catch (UnsupportedLookAndFeelException e) {
        }
        LoginTela login = new LoginTela();
        login.setVisible(true);
    }
}
