/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ui;

import app.model.Model;
import app.util.Start;
import app.util.Util;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author roberto
 */
public class Init extends javax.swing.JFrame {

    Model model;// = new Model();
    Start start = new Start();

    /**
     * Creates new form Init
     */
    public Init() {
        initComponents();
        //cbColecaoAttsSetItems();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        pnTop = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cbDelta = new javax.swing.JComboBox();
        lbStringConnection = new javax.swing.JLabel();
        lbCollection = new javax.swing.JLabel();
        flConnection = new javax.swing.JTextField();
        btConnect = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lsD = new javax.swing.JList();
        lbD = new javax.swing.JLabel();
        lbM = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lsM = new javax.swing.JList();
        lbOL = new javax.swing.JLabel();
        cbOL = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbCR = new javax.swing.JTable();
        lbFiltros = new javax.swing.JLabel();
        cbFiltroChave1 = new javax.swing.JComboBox();
        lbChave = new javax.swing.JLabel();
        lbOperador = new javax.swing.JLabel();
        lbValor = new javax.swing.JLabel();
        cbFiltroChave3 = new javax.swing.JComboBox();
        cbFiltroChave2 = new javax.swing.JComboBox();
        cbFiltroOperador1 = new javax.swing.JComboBox();
        cbFiltroOperador3 = new javax.swing.JComboBox();
        cbFiltroOperador2 = new javax.swing.JComboBox();
        txFiltroValor1 = new javax.swing.JTextField();
        txFiltroValor2 = new javax.swing.JTextField();
        txFiltroValor3 = new javax.swing.JTextField();
        btGerar = new javax.swing.JButton();
        lbC = new javax.swing.JLabel();
        cbC = new javax.swing.JComboBox();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cbDelta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDeltaItemStateChanged(evt);
            }
        });

        lbStringConnection.setText("String de Conecção");

        lbCollection.setText("Coleção");

        flConnection.setText("localhost:27017/desenv");

        btConnect.setText("Connect");
        btConnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btConnectMouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(lsD);

        lbD.setText("Dimensões");

        lbM.setText("Métrica");

        lsM.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lsM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lsMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lsM);

        lbOL.setText("Operador OLAP");

        cbOL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--", "Slice", "Dice", "Roll-Up", "Drill-Down" }));
        cbOL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbOLItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbStringConnection)
                            .addComponent(lbCollection))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbDelta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(flConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btConnect))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbM)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lbOL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbOL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btConnect)
                    .addComponent(lbStringConnection))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCollection)
                    .addComponent(cbDelta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbD)
                    .addComponent(lbM))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbOL)
                    .addComponent(cbOL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tbCR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tbCR);

        lbFiltros.setText("Filtros");

        lbChave.setText("Chave");

        lbOperador.setText("Operador");

        lbValor.setText("Valor");

        cbFiltroOperador1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=", "!=", ">", "<", ">=", "<=" }));

        cbFiltroOperador3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=", "!=", ">", "<", ">=", "<=" }));

        cbFiltroOperador2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=", "!=", ">", "<", ">=", "<=" }));

        txFiltroValor1.setMaximumSize(new java.awt.Dimension(10, 2147483647));

        btGerar.setText("Gerar");
        btGerar.setEnabled(false);
        btGerar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btGerarMouseClicked(evt);
            }
        });

        lbC.setText("Sumarizar");

        cbC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--" }));
        cbC.setEnabled(false);

        javax.swing.GroupLayout pnTopLayout = new javax.swing.GroupLayout(pnTop);
        pnTop.setLayout(pnTopLayout);
        pnTopLayout.setHorizontalGroup(
            pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTopLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(pnTopLayout.createSequentialGroup()
                .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTopLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(pnTopLayout.createSequentialGroup()
                        .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnTopLayout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbFiltroChave2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbFiltroChave3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbFiltroChave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnTopLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(lbFiltros)
                                .addGap(18, 18, 18)
                                .addComponent(lbChave)))
                        .addGap(64, 64, 64)
                        .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbOperador)
                            .addComponent(cbFiltroOperador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFiltroOperador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFiltroOperador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbValor)
                            .addComponent(txFiltroValor1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(txFiltroValor2)
                            .addComponent(txFiltroValor3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbC)
                    .addComponent(btGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnTopLayout.setVerticalGroup(
            pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTopLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTopLayout.createSequentialGroup()
                        .addComponent(lbValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txFiltroValor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txFiltroValor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txFiltroValor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnTopLayout.createSequentialGroup()
                        .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFiltros)
                            .addComponent(lbChave)
                            .addComponent(lbOperador))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbFiltroChave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFiltroOperador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbFiltroChave2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFiltroOperador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbFiltroChave3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFiltroOperador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbC)
                    .addComponent(cbC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btGerar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(558, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btConnectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btConnectMouseClicked
        model = new Model(flConnection.getText());
        cbColecaoSetItems();
    }//GEN-LAST:event_btConnectMouseClicked

    private void lsMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lsMMouseClicked
        System.out.print("DDDD");
    }//GEN-LAST:event_lsMMouseClicked

    private void cbOLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOLItemStateChanged
        
        cbCSetItems();
        if("Drill-Down".equalsIgnoreCase(cbOL.getSelectedItem().toString())){
            lbC.setText("Detalhar");
            cbC.setEnabled(true);
        }else if("Roll-Up".equalsIgnoreCase(cbOL.getSelectedItem().toString())){
            lbC.setText("Sumarizar");
            cbC.setEnabled(true);
        }else{
            cbC.setEnabled(false);
        }
        
        if (cbOL.getSelectedIndex() > 0) {
            btGerar.setEnabled(true);
            updateFiltesParams();
        } else {
            btGerar.setEnabled(false);
        }
    }//GEN-LAST:event_cbOLItemStateChanged

    private void cbDeltaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDeltaItemStateChanged
        if (cbDelta.getSelectedIndex() > 0) {
            lsAttsSetItems();
        } 
    }//GEN-LAST:event_cbDeltaItemStateChanged

    private void btGerarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btGerarMouseClicked
        //start(String OL, String Delta, String D, String M, String[] Fname, String[] Foperator, String[] Fvalue)
        start.start(cbOL.getSelectedItem().toString(),
                cbDelta.getSelectedItem().toString(),
                getD(),
                lsM.getSelectedValue().toString().trim(),
                getFilterKeys(),
                getFilterOperators(),
                getFilterValues(),
                cbC.getSelectedItem().toString());
        setTable();
    }//GEN-LAST:event_btGerarMouseClicked

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
            java.util.logging.Logger.getLogger(Init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Init().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btConnect;
    private javax.swing.JButton btGerar;
    private javax.swing.JComboBox cbC;
    private javax.swing.JComboBox cbDelta;
    private javax.swing.JComboBox cbFiltroChave1;
    private javax.swing.JComboBox cbFiltroChave2;
    private javax.swing.JComboBox cbFiltroChave3;
    private javax.swing.JComboBox cbFiltroOperador1;
    private javax.swing.JComboBox cbFiltroOperador2;
    private javax.swing.JComboBox cbFiltroOperador3;
    private javax.swing.JComboBox cbOL;
    private javax.swing.JTextField flConnection;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbC;
    private javax.swing.JLabel lbChave;
    private javax.swing.JLabel lbCollection;
    private javax.swing.JLabel lbD;
    private javax.swing.JLabel lbFiltros;
    private javax.swing.JLabel lbM;
    private javax.swing.JLabel lbOL;
    private javax.swing.JLabel lbOperador;
    private javax.swing.JLabel lbStringConnection;
    private javax.swing.JLabel lbValor;
    private javax.swing.JList lsD;
    private javax.swing.JList lsM;
    private javax.swing.JPanel pnTop;
    private javax.swing.JTable tbCR;
    private javax.swing.JTextField txFiltroValor1;
    private javax.swing.JTextField txFiltroValor2;
    private javax.swing.JTextField txFiltroValor3;
    // End of variables declaration//GEN-END:variables

    public void cbColecaoSetItems() {
        Set<String> s = model.getDbCollections();
        cbDelta.addItem("--");
        for (String item : s) {
            cbDelta.addItem(item);
        }
    }

    public void lsAttsSetItems() {
        String[] s = model.getAttsCombo(cbDelta.getSelectedItem().toString());
        DefaultListModel m = new DefaultListModel();

        for (int i = 0; i < s.length; i++) {
            m.add(i, s[i]);
        }

        lsM.setModel(m);
        lsD.setModel(m);

    }

    public void cbCSetItems() {
        for (Object chave : lsD.getSelectedValuesList()) {
            cbC.addItem(chave.toString());
        }        
    }
    
    public void updateFiltesParams() {                        
        
        if ("Slice".equalsIgnoreCase(cbOL.getSelectedItem().toString().trim())) {
            cbFiltroOperador2.setVisible(false);
            cbFiltroOperador3.setVisible(false);
            cbFiltroOperador1.setEnabled(false);
            cbFiltroChave2.setVisible(false);
            cbFiltroChave3.setVisible(false);
            txFiltroValor2.setVisible(false);
            txFiltroValor3.setVisible(false);
        } else {
            cbFiltroOperador1.setVisible(true);
            cbFiltroOperador2.setVisible(true);
            cbFiltroOperador3.setVisible(true);
            cbFiltroOperador1.setEnabled(true);
            cbFiltroChave2.setVisible(true);
            cbFiltroChave3.setVisible(true);
            txFiltroValor2.setVisible(true);
            txFiltroValor3.setVisible(true);
        }

        for (Object chave : lsD.getSelectedValuesList()) {
            cbFiltroChave1.addItem(chave.toString());
            cbFiltroChave2.addItem(chave.toString());
            cbFiltroChave3.addItem(chave.toString());
        }
    }

    public String[] getFilterValues() {
        String[] filterValues = new String[3];
        filterValues[0] = txFiltroValor1.getText();
        filterValues[1] = txFiltroValor2.getText();
        filterValues[2] = txFiltroValor3.getText();
        return filterValues;
    }

    public String[] getFilterOperators() {
        String[] filterOperators = new String[3];
        filterOperators[0] = cbFiltroOperador1.getSelectedItem().toString().trim();
        filterOperators[1] = cbFiltroOperador2.getSelectedItem().toString().trim();
        filterOperators[2] = cbFiltroOperador3.getSelectedItem().toString().trim();
        return filterOperators;
    }

    public String[] getFilterKeys() {
        String[] filterKeys = new String[3];
        filterKeys[0] = cbFiltroChave1.getSelectedItem().toString().trim();
        filterKeys[1] = cbFiltroChave2.getSelectedItem().toString().trim();
        filterKeys[2] = cbFiltroChave3.getSelectedItem().toString().trim();
        /*for(int i=0; i < 3; i++){
            //filterKeys[i] = Util.getAttKey(cbDelta.getSelectedItem().toString().trim(), filterKeys[i]);
        }*/
        
        return filterKeys;
    }

    public String getD() {
        String D = "";
        for (Object item : lsD.getSelectedValuesList()) {
            D += item.toString().trim() + ";";
        }
        return D;
    }
    

    public void setTable() {
        String collection = cbDelta.getSelectedItem().toString().trim() + "_formated_" + cbOL.getSelectedItem().toString().toLowerCase().replace("-", "");
        collection = collection.replace("rollup", "dice_rollup");
        DBCollection col = model.getCollection(collection);
        DBCursor cur = col.find();

        String[] columnNames = Util.getAttsNames(collection);
        String[] pseudoColumns = new String[columnNames.length+1];
        String[] values = new String[columnNames.length+1];
        
        for(int i = 0; i < columnNames.length; i++){
            pseudoColumns[i] = columnNames[i];
        }        
        pseudoColumns[pseudoColumns.length-1] = lsM.getSelectedValue().toString().trim();
        
        DefaultTableModel model = new DefaultTableModel(pseudoColumns, 0);

        for(int i = 0; i < pseudoColumns.length; i++){
            pseudoColumns[i] = "_key" +(1+i);
        }
        pseudoColumns[pseudoColumns.length-1] = "_value";
        
        while (cur.hasNext()) {
            DBObject obj = cur.next();
            
            for(int i=0; i < values.length-1; i++){
                values[i] = (String) obj.get(pseudoColumns[i]);
            }
            values[values.length-1] = obj.get(pseudoColumns[values.length-1]).toString();
            /*String cidade = (String) obj.get("CIDADE");
            String uf = (String) obj.get("UF");
            ObjectId id = (ObjectId) obj.get("_id");
            */
            
            //model.addRow(new Object[]{id, cidade, uf});
            //model.addRow(new Object[]{values});
            Object[] newObj = new Object[values.length];
            //{values}
            for(int i = 0; i < values.length; i++){
                newObj[i] = values[i];
            }
            model.addRow(newObj);
        }
        cur.close();
        tbCR.setModel(model);
    }    
    
}
