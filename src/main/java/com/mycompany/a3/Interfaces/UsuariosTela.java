/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.a3.Interfaces;

import com.mycompany.a3.Dialogo;
import com.mycompany.a3.daos.UsuarioDAO;
import com.mycompany.a3.models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class UsuariosTela extends JPanel {
   
    public UsuariosTela() {

        initComponents();
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios;
        usuarios = dao.SelectAll();
        preencheTableUsuarios(usuarios);

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labelTituloJanela = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Novo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        labelTituloJanela.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelTituloJanela.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloJanela.setText("Usuários");

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
                            .addComponent(labelTituloJanela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTituloJanela))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(btnExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CadastroUsuarioTela cadastroUsuario = new CadastroUsuarioTela();
        cadastroUsuario.setVisible(true);

        cadastroUsuario.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                UsuarioDAO dao = new UsuarioDAO();
                List<Usuario> usuarios = dao.SelectAll();
                preencheTableUsuarios(usuarios);
            }
        });


    }//GEN-LAST:event_jButton3ActionPerformed
    private void preencheTableUsuarios(List<Usuario> usuarios) {

        if (usuarios != null) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            usuarios.forEach(u -> {
                Object[] linha = {u.getId(), u.getNome()};
                model.addRow(linha);
            });
        }
    }
    private void jButton2ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int linhaSelecionada = jTable1.getSelectedRow();

        if (linhaSelecionada != -1) {
            Object usuarioObj = jTable1.getValueAt(linhaSelecionada, 0);
            int idUsuario = Integer.parseInt(usuarioObj.toString());
            UsuarioDAO usuarioDao = new UsuarioDAO();
            Usuario usuario = new Usuario();
            usuario = usuarioDao.Select(idUsuario);

            if (usuario != null) {
                CadastroUsuarioTela cadastroUsuario = new CadastroUsuarioTela(usuario);
                cadastroUsuario.setVisible(true);

                cadastroUsuario.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        UsuarioDAO dao = new UsuarioDAO();
                        List<Usuario> usuarios = dao.SelectAll();
                        preencheTableUsuarios(usuarios);
                    }
                });
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linhaSelecionada = jTable1.getSelectedRow();
        if (linhaSelecionada != -1 ) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Object usuarioObj = jTable1.getValueAt(linhaSelecionada, 0);
            int idUsuario = Integer.parseInt(usuarioObj.toString());
            if(usuarioDAO.Delete(idUsuario)) {
                Dialogo.exibirDialog("Usuário excluído com sucesso.");
                UsuarioDAO dao = new UsuarioDAO();
                List<Usuario> usuarios = dao.SelectAll();
                preencheTableUsuarios(usuarios);
                
            }
                    
        }
             
    }//GEN-LAST:event_btnExcluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelTituloJanela;
    // End of variables declaration//GEN-END:variables
}
