package com.mycompany.a3.Interfaces;

import com.mycompany.a3.Dialogo;
import com.mycompany.a3.daos.LotesDAO;
import com.mycompany.a3.daos.MovimentacaoDAO;
import com.mycompany.a3.daos.ProdutoDAO;
import com.mycompany.a3.models.Lote;
import com.mycompany.a3.models.Movimentacao;
import com.mycompany.a3.models.Produto;
import java.util.List;

public class CadastroMovimentacoesTela extends javax.swing.JFrame {

    public CadastroMovimentacoesTela() {
        initComponents();
        preencherComboTipos();
        preencherComboProdutos();
    }

    private void gerarSaida() {
        Produto produtoSelecionado = (Produto) comboProdutos.getSelectedItem();
        double quantidadeRetirada = Double.parseDouble(edtqtdLancamento.getText());
        double quantidadeToal = Double.parseDouble(edtEstoqueAtual.getText());
        double quantidadeRestante = quantidadeRetirada;
        LotesDAO loteDAO = new LotesDAO();
        List<Lote> lotesDisponiveis = loteDAO.listarLotesDisponiveisPorProduto(produtoSelecionado.getId());

        if (lotesDisponiveis.isEmpty()) {
            Dialogo.exibirDialog("Nenhum lote disponível com estoque para esse produto.");
            return;
        }
        if (quantidadeRetirada > quantidadeToal) {
            Dialogo.exibirDialog("Estoque insuficiente. O estoque total informado é: " + quantidadeRetirada);
            return;
        }

        for (Lote lote : lotesDisponiveis) {
            if (quantidadeRestante <= 0) {
                break;
            }
            double estoqueAtual = lote.getEstoque();

            double retirar;
            if (estoqueAtual < quantidadeRestante) {
                retirar = estoqueAtual;
            } else {
                retirar = quantidadeRestante;
            }
            lote.setEstoque(estoqueAtual - retirar);
            loteDAO.update(lote);

            Movimentacao movimentacao = new Movimentacao(lote.getId(), 1, retirar);
            MovimentacaoDAO movDAO = new MovimentacaoDAO();
            movDAO.inserir(movimentacao);

            quantidadeRestante -= retirar;
        }

        if (quantidadeRestante > 0) {
            Dialogo.exibirDialog("Estoque insuficiente. Não foi possível retirar " + quantidadeRestante + " unidade(s).");
        } else {
            double novoEstoqueProduto = produtoSelecionado.getEstoque() - quantidadeRetirada;
            produtoSelecionado.setEstoque(novoEstoqueProduto);

            ProdutoDAO produtoDAO = new ProdutoDAO();

            if (produtoDAO.Update(produtoSelecionado)) {
                Dialogo.exibirDialog("Movimentação gerada com sucesso");
                this.dispose();
                return;
            }

        }

    }

    private void gerarEntrada() {
        Lote loteSelecionado = (Lote) comboLotes.getSelectedItem();
        double quantidade = Double.parseDouble(edtqtdLancamento.getText());

        int tipoSelecionado = comboTipos.getSelectedIndex();

        double novoEstoqueLote = loteSelecionado.getEstoque() + quantidade;
        loteSelecionado.setEstoque(novoEstoqueLote);
        LotesDAO loteDAO = new LotesDAO();
        loteDAO.update(loteSelecionado);

        Produto produtoSelecionado = (Produto) comboProdutos.getSelectedItem();
        if (produtoSelecionado == null) {
            Dialogo.exibirDialog("Nenhum produto selecionado.");
            return;
        }
        double novoEstoqueProduto = produtoSelecionado.getEstoque() + quantidade;
        produtoSelecionado.setEstoque(novoEstoqueProduto);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        boolean produtoAtualizado = produtoDAO.Update(produtoSelecionado);
        if (!produtoAtualizado) {
            Dialogo.exibirDialog("Erro ao atualizar o estoque do produto.");
            return;
        }

        Movimentacao movimentacao = new Movimentacao(loteSelecionado.getId(), tipoSelecionado, quantidade);
        MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
        if (movimentacaoDAO.inserir(movimentacao)) {
            Dialogo.exibirDialog("Movimentação salva com sucesso");
            this.dispose();
        } else {
            Dialogo.exibirDialog("Erro ao salvar a movimentação.");
        }
    }

    private void preencherComboTipos() {
        comboTipos.removeAllItems();
        comboTipos.addItem("Entrada");
        comboTipos.addItem("Saída");
    }

    private void preencherComboProdutos() {
        comboProdutos.removeAllItems();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.SelectAll();
        produtos.forEach(p -> {
            comboProdutos.addItem(p);
        });

    }

    private void preencherComboLotes(int idProduto) {
        comboLotes.removeAllItems();
        List<Lote> lotes = new LotesDAO().selectByProduto(idProduto);
        for (Lote lote : lotes) {
            comboLotes.addItem(lote);

        }
    }

    private void preencherEstoqueAtual() {
        int tipoSelecionado = comboTipos.getSelectedIndex();

        if (tipoSelecionado == 0) {

            Object itemSelecionado = comboLotes.getSelectedItem();
            if (itemSelecionado instanceof Lote) {
                Lote loteSelecionado = (Lote) itemSelecionado;
                edtEstoqueAtual.setText(Double.toString(loteSelecionado.getEstoque()));
            } else {
                edtEstoqueAtual.setText("0.00");

            }

        } else if (tipoSelecionado == 1) {

            Produto produtoSelecionado = (Produto) comboProdutos.getSelectedItem();
            if (produtoSelecionado != null) {
                LotesDAO lotesDAO = new LotesDAO();
                double estoque = lotesDAO.getEstoqueAtualPorProduto(produtoSelecionado.getId());
                edtEstoqueAtual.setText(Double.toString(estoque));
            } else {
                edtEstoqueAtual.setText("0.00");
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboProdutos = new javax.swing.JComboBox<>();
        comboTipos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        comboLotes = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        edtEstoqueAtual = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        edtqtdLancamento = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        comboProdutos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProdutosActionPerformed(evt);
            }
        });

        comboTipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboTipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTiposActionPerformed(evt);
            }
        });

        jLabel2.setText("Produto");

        jButton1.setText("Confirmar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        comboLotes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboLotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLotesActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");

        jLabel3.setText("Lote");

        edtEstoqueAtual.setEditable(false);
        edtEstoqueAtual.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));

        jLabel1.setText("Estoque Atual");

        jLabel4.setText("Quantidade");

        jLabel5.setText("Tipo");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Lancamento de Movimentação");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(comboLotes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(edtEstoqueAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtqtdLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(comboTipos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboLotes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtEstoqueAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtqtdLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProdutosActionPerformed
        Produto produtoSelecionado = (Produto) comboProdutos.getSelectedItem();
        if (produtoSelecionado != null) {
            int idProduto = produtoSelecionado.getId();
            preencherComboLotes(idProduto);
        }
        preencherEstoqueAtual();


    }//GEN-LAST:event_comboProdutosActionPerformed

    private void comboTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTiposActionPerformed

    }//GEN-LAST:event_comboTiposActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (comboTipos.getSelectedIndex() == 0) {
            gerarEntrada();

        } else {
            gerarSaida();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboLotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLotesActionPerformed

    }//GEN-LAST:event_comboLotesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroMovimentacoesTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroMovimentacoesTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroMovimentacoesTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroMovimentacoesTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroMovimentacoesTela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> comboLotes;
    private javax.swing.JComboBox<Object> comboProdutos;
    private javax.swing.JComboBox<Object> comboTipos;
    private javax.swing.JFormattedTextField edtEstoqueAtual;
    private javax.swing.JFormattedTextField edtqtdLancamento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
