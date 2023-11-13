/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import connectDB.ConnectDB;
import dao.DichVu_DAO;
import entity.DichVu;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellEditor1;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionCellRender1;
import raven.cell.TableActionEvent;
import raven.cell.TableActionEvent1;

/**
 *
 * @author PC BAO THONG
 */
public class Form_CapNhatDVP extends javax.swing.JFrame {

    private DichVu_DAO dichvudao;
    private ArrayList<DichVu> dsDV;
    private DefaultTableModel modelDV1;
    private DefaultTableModel modelDV;

    /**
     * Creates new form FormThemDVP
     */
    public Form_CapNhatDVP() {
        try {
            ConnectDB.getInstance().connect();
//            System.out.println("Ket noi Database thanh cong");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        modelDV1 = (DefaultTableModel) table1.getModel();
        modelDV = (DefaultTableModel) table.getModel();
        customTable();
        TableEvent();
    }

    private void loadAllDV() {
        dichvudao = new DichVu_DAO();
        dsDV = new ArrayList<DichVu>();
        dsDV = dichvudao.layDSPhong();
        for (DichVu dichVu : dsDV) {
            modelDV1.addRow(new Object[]{dichVu.getMaDV(), dichVu.getTenDV(), dichVu.getDonGia(), dichVu.getDonViBan(), dichVu.getSoLuongTon()});
        }
    }

    private void TableEvent() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onAdd(int row) {
                System.out.println("Add row : " + row);
                int r = table1.getSelectedRow();
                int soLuong = (int) modelDV.getValueAt(r, 2);
                soLuong++;
                Double donGia = (Double) modelDV.getValueAt(r, 1);
                Double thanhTien = donGia * soLuong;
                modelDV.setValueAt(soLuong, r, 2);
                modelDV.setValueAt(thanhTien, r, 3);

            }

            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(row);
            }

            @Override
            public void onLess(int row) {
                System.out.println("Less row : " + row);
                int r = table1.getSelectedRow();
                int soLuong = (int) modelDV.getValueAt(r, 2);
                soLuong--;
                Double donGia = (Double) modelDV.getValueAt(r, 1);
                Double thanhTien = (Double) modelDV.getValueAt(r, 3);
                thanhTien = thanhTien - donGia;
                if (soLuong == 0) {
                    modelDV.removeRow(r);
                } else {
                    modelDV.setValueAt(soLuong, r, 2);
                    modelDV.setValueAt(thanhTien, r, 3);
                }
            }
        };
        table.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });
        TableActionEvent1 event1 = new TableActionEvent1() {
            @Override
            public void onAdd(int row) {
                if (table1.isEditing()) {
                    table1.getCellEditor().stopCellEditing();
                }
//              Khi nhấn vào dấu + thì thêm vào table bên kia
                int r = table1.getSelectedRow();
                String tenDV = modelDV1.getValueAt(r, 1).toString();
                Double donGia = (Double) modelDV1.getValueAt(r, 2);
                Double thanhTien = donGia;
                modelDV.addRow(new Object[]{tenDV, donGia, 1, thanhTien});
            }
        };
        table1.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender1());
        table1.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor1(event1));
        table1.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });
    }

    private void customTable() {
        table1.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        table1.getTableHeader().setOpaque(false);
        table1.getTableHeader().setBackground(new Color(32, 136, 203));
        table1.getTableHeader().setForeground(new Color(255, 255, 255));
//        jTable1.getColumnModel().getColumn(4).setCellEditor(new JXTable.GenericEditor());

        table.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(new Color(255, 255, 255));
        loadAllDV();
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

        panelHeader = new javax.swing.JPanel();
        panelC = new javax.swing.JPanel();
        lblTittle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        lblTittle.setText("CẬP NHẬT DỊCH VỤ CHO PHÒNG");
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

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jScrollPane2.setMaximumSize(new java.awt.Dimension(590, 350));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(590, 350));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(590, 350));

        table.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên DV", "Đơn giá", "Đã thêm", "Thành tiền", "Hành động"
            }
        ));
        table.setRowHeight(25);
        table.setSelectionBackground(new java.awt.Color(204, 255, 255));
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(30);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 3, 16))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(590, 350));
        jPanel2.setMinimumSize(new java.awt.Dimension(590, 350));
        jPanel2.setPreferredSize(new java.awt.Dimension(590, 350));
        jPanel2.setLayout(new java.awt.BorderLayout(0, 5));

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

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(590, 350));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(590, 350));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(590, 350));

        table1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DV", "Tên DV", "Đơn giá", "Số lượng", "Đơn vị tính", "Thêm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.setRowHeight(25);
        table1.setSelectionBackground(new java.awt.Color(204, 255, 255));
        jScrollPane1.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setPreferredWidth(10);
            table1.getColumnModel().getColumn(2).setResizable(false);
            table1.getColumnModel().getColumn(2).setPreferredWidth(40);
            table1.getColumnModel().getColumn(3).setResizable(false);
            table1.getColumnModel().getColumn(3).setPreferredWidth(25);
            table1.getColumnModel().getColumn(4).setPreferredWidth(20);
            table1.getColumnModel().getColumn(5).setResizable(false);
            table1.getColumnModel().getColumn(5).setPreferredWidth(5);
        }

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblTittle;
    private javax.swing.JPanel panelC;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
