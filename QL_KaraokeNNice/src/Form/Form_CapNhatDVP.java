/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import java.awt.Color;
import java.awt.Font;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author PC BAO THONG
 */
public class Form_CapNhatDVP extends javax.swing.JFrame {

    /**
     * Creates new form FormThemDVP
     */
    public Form_CapNhatDVP() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        table();
    }

    private void table() {
        jTable1.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable1.getTableHeader().setForeground(new Color(255, 255, 255));
//        jTable1.getColumnModel().getColumn(4).setCellEditor(new JXTable.GenericEditor());
        
        jTable2.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        jTable2.getTableHeader().setOpaque(false);
        jTable2.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable2.getTableHeader().setForeground(new Color(255, 255, 255));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        panelHeader = new javax.swing.JPanel();
        panelC = new javax.swing.JPanel();
        lblTittle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(80, 40));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jButton3.setBackground(new java.awt.Color(102, 153, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Quay lại");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, java.awt.BorderLayout.EAST);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1200, 650));
        setMinimumSize(new java.awt.Dimension(1200, 650));
        setPreferredSize(new java.awt.Dimension(1200, 650));
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        panelHeader.setBackground(new java.awt.Color(255, 255, 255));
        panelHeader.setMinimumSize(new java.awt.Dimension(400, 40));
        panelHeader.setPreferredSize(new java.awt.Dimension(400, 40));
        panelHeader.setLayout(new java.awt.GridLayout(1, 0));

        panelC.setBackground(new java.awt.Color(121, 188, 215));
        panelC.setPreferredSize(new java.awt.Dimension(170, 30));
        panelC.setLayout(new java.awt.GridBagLayout());

        lblTittle.setBackground(new java.awt.Color(255, 255, 255));
        lblTittle.setFont(new java.awt.Font("Cambria", 3, 24)); // NOI18N
        lblTittle.setForeground(new java.awt.Color(33, 36, 71));
        lblTittle.setText("CẬP NHẬT DỊCH VỤ");
        lblTittle.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        panelC.add(lblTittle, new java.awt.GridBagConstraints());

        panelHeader.add(panelC);

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách dịch vụ đã thêm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 3, 16))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(590, 350));
        jPanel1.setMinimumSize(new java.awt.Dimension(590, 350));
        jPanel1.setPreferredSize(new java.awt.Dimension(590, 350));
        jPanel1.setLayout(new java.awt.BorderLayout(0, 5));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jScrollPane2.setMaximumSize(new java.awt.Dimension(590, 350));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(590, 350));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(590, 350));

        jTable2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên DV", "Đơn giá", "Đã thêm", "Thành tiền", "Thêm", "Bớt", "Xóa"
            }
        ));
        jTable2.setMaximumSize(new java.awt.Dimension(590, 400));
        jTable2.setMinimumSize(new java.awt.Dimension(590, 400));
        jTable2.setPreferredSize(new java.awt.Dimension(590, 400));
        jTable2.setRowHeight(25);
        jTable2.setSelectionBackground(new java.awt.Color(204, 255, 255));
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(30);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(15);
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(15);
            jTable2.getColumnModel().getColumn(6).setResizable(false);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(15);
        }

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(600, 50));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 10, 10));

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel2.setText("Mã phòng");
        jPanel6.add(jLabel2);

        jTextField2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField2.setEnabled(false);
        jTextField2.setMinimumSize(new java.awt.Dimension(120, 20));
        jTextField2.setPreferredSize(new java.awt.Dimension(120, 30));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField2);

        jLabel3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel3.setText("Tổng tiền");
        jPanel6.add(jLabel3);

        jTextField3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField3.setEnabled(false);
        jTextField3.setMinimumSize(new java.awt.Dimension(120, 30));
        jTextField3.setPreferredSize(new java.awt.Dimension(120, 30));
        jPanel6.add(jTextField3);

        jPanel1.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 3, 16))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(590, 350));
        jPanel2.setMinimumSize(new java.awt.Dimension(590, 350));
        jPanel2.setPreferredSize(new java.awt.Dimension(590, 350));
        jPanel2.setLayout(new java.awt.BorderLayout(0, 5));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(590, 350));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(590, 350));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(590, 350));

        jTable1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DV", "Tên DV", "Đơn giá", "Số lượng", "Đơn vị tính", "Thêm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setMaximumSize(new java.awt.Dimension(590, 400));
        jTable1.setMinimumSize(new java.awt.Dimension(590, 400));
        jTable1.setPreferredSize(new java.awt.Dimension(590, 400));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(204, 255, 255));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(15);
        }

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(600, 50));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 50));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel1.setText("Mã dịch vụ");
        jPanel3.add(jLabel1);

        jTextField1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField1.setToolTipText("");
        jTextField1.setMinimumSize(new java.awt.Dimension(0, 30));
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel3.add(jTextField1);

        jButton1.setBackground(new java.awt.Color(153, 255, 204));
        jButton1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton1.setText("Tìm");
        jButton1.setMinimumSize(new java.awt.Dimension(0, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton2.setText("Xóa");
        jButton2.setMinimumSize(new java.awt.Dimension(0, 30));
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMaximumSize(new java.awt.Dimension(1000, 60));
        jPanel5.setMinimumSize(new java.awt.Dimension(1000, 60));
        jPanel5.setPreferredSize(new java.awt.Dimension(1000, 60));
        java.awt.GridBagLayout jPanel5Layout = new java.awt.GridBagLayout();
        jPanel5Layout.columnWidths = new int[] {0, 80, 0, 80, 0, 80, 0, 80, 0, 80, 0, 80, 0, 80, 0, 80, 0, 80, 0, 80, 0};
        jPanel5Layout.rowHeights = new int[] {0};
        jPanel5.setLayout(jPanel5Layout);

        jButton4.setBackground(new java.awt.Color(153, 204, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/back.png"))); // NOI18N
        jButton4.setText("Quay lại");
        jButton4.setPreferredSize(new java.awt.Dimension(140, 40));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel5.add(jButton4, gridBagConstraints);

        getContentPane().add(jPanel5, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Form_CapNhatDVP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_CapNhatDVP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_CapNhatDVP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_CapNhatDVP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_CapNhatDVP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblTittle;
    private javax.swing.JPanel panelC;
    private javax.swing.JPanel panelHeader;
    // End of variables declaration//GEN-END:variables
}
