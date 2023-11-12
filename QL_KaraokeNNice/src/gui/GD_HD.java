 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import Form.Form_HoaDon;
import com.toedter.calendar.JDateChooserCellEditor;
import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author PC BAO THONG
 */
public class GD_HD extends javax.swing.JPanel {

    //DAO
    private HoaDon_DAO hd_dao;
    private KhachHang_DAO kh_dao;
    private NhanVien_DAO nv_dao;

    //HoaDon
    private DefaultTableModel modelHD;

    //List
    private ArrayList<HoaDon> dsHD;
    private ArrayList<KhachHang> dsKH;
    private ArrayList<NhanVien> dsNV;

    public GD_HD() {
        try {
            ConnectDB.getInstance().connect();
//            System.out.println("Ket noi Database thanh cong");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        hd_dao = new HoaDon_DAO();
        kh_dao = new KhachHang_DAO();
        nv_dao = new NhanVien_DAO();
        initComponents();
        tableHoaDon();
    }

    private void tableHoaDon() {
        PromptSupport.setPrompt("Nhập số điện thoại khách", txtTimSDT);
        tableHD.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        tableHD.getTableHeader().setOpaque(false);
        tableHD.getTableHeader().setBackground(new Color(32, 136, 203));
        tableHD.getTableHeader().setForeground(new Color(255, 255, 255));
        modelHD = (DefaultTableModel) tableHD.getModel();
        napDuLieuHD();
    }

    private void napDuLieuHD() {
        dsHD = hd_dao.getAllHoaDon();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (HoaDon hd : dsHD) {
            KhachHang kh = kh_dao.getKhachHangTheoMa(hd.getKhachHang().getMaKH());
            NhanVien nv = nv_dao.getNhanVienTheoMa(hd.getNhanVienLap().getMaNV());
            String ngayLap = formatter.format(hd.getNgayLap());
            modelHD.addRow(new Object[]{hd.getMaHD(), ngayLap, kh.getHoKH() + " " + kh.getTenKH(),
                kh.getSdtKH(), nv.getHoNV() + " " + nv.getTenNV(), hd.getTongTien()});
        }
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

        GD_HoaDon = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableHD = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        txtMaHD = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        btnXemCTHD = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        txtTimSDT = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        txtNgayLap = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        GD_HoaDon.setBackground(new java.awt.Color(255, 255, 255));
        GD_HoaDon.setLayout(new java.awt.BorderLayout());

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        jPanel17.setMinimumSize(new java.awt.Dimension(1200, 220));
        jPanel17.setPreferredSize(new java.awt.Dimension(1200, 220));
        jPanel17.setLayout(new java.awt.BorderLayout());

        tableHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày lập", "Tên khách hàng", "Số điện thọai", "Nhân viên", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableHD.setRowHeight(25);
        tableHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHDMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableHD);

        jPanel17.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        GD_HoaDon.add(jPanel17, java.awt.BorderLayout.CENTER);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        jPanel18.setMinimumSize(new java.awt.Dimension(1200, 300));
        jPanel18.setPreferredSize(new java.awt.Dimension(1200, 300));
        java.awt.GridBagLayout jPanel18Layout = new java.awt.GridBagLayout();
        jPanel18Layout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        jPanel18Layout.rowHeights = new int[] {0, 20, 0, 20, 0, 20, 0, 20, 0};
        jPanel18.setLayout(jPanel18Layout);

        txtMaHD.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtMaHD.setEnabled(false);
        txtMaHD.setMinimumSize(new java.awt.Dimension(200, 30));
        txtMaHD.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel18.add(txtMaHD, gridBagConstraints);

        txtTenNV.setEditable(false);
        txtTenNV.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTenNV.setMinimumSize(new java.awt.Dimension(200, 30));
        txtTenNV.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 2;
        jPanel18.add(txtTenNV, gridBagConstraints);

        txtTenKH.setEditable(false);
        txtTenKH.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTenKH.setMinimumSize(new java.awt.Dimension(200, 30));
        txtTenKH.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        jPanel18.add(txtTenKH, gridBagConstraints);

        jLabel29.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel29.setText("Ngày lập");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(jLabel29, gridBagConstraints);

        jLabel30.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel30.setText("Mã hóa đơn");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(jLabel30, gridBagConstraints);

        jLabel31.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel31.setText("Tên nhân viên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(jLabel31, gridBagConstraints);

        jLabel32.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel32.setText("Tên khách hàng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(jLabel32, gridBagConstraints);

        jLabel33.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel33.setText("Số điện thoại");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(jLabel33, gridBagConstraints);

        btnTim.setBackground(new java.awt.Color(121, 188, 215));
        btnTim.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnTim.setForeground(new java.awt.Color(255, 255, 255));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search-doc.png"))); // NOI18N
        btnTim.setText("Tìm");
        btnTim.setMinimumSize(new java.awt.Dimension(100, 30));
        btnTim.setPreferredSize(new java.awt.Dimension(100, 30));
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(btnTim, gridBagConstraints);

        btnXemCTHD.setBackground(new java.awt.Color(0, 204, 204));
        btnXemCTHD.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnXemCTHD.setForeground(new java.awt.Color(255, 255, 255));
        btnXemCTHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/paper.png"))); // NOI18N
        btnXemCTHD.setText("Xem chi tiết");
        btnXemCTHD.setMinimumSize(new java.awt.Dimension(150, 30));
        btnXemCTHD.setPreferredSize(new java.awt.Dimension(150, 30));
        btnXemCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemCTHDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(btnXemCTHD, gridBagConstraints);

        btnLamMoi.setBackground(new java.awt.Color(0, 255, 213));
        btnLamMoi.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setMinimumSize(new java.awt.Dimension(15, 30));
        btnLamMoi.setPreferredSize(new java.awt.Dimension(150, 30));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(btnLamMoi, gridBagConstraints);

        txtTimSDT.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTimSDT.setMinimumSize(new java.awt.Dimension(200, 30));
        txtTimSDT.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        jPanel18.add(txtTimSDT, gridBagConstraints);

        txtSDT.setEditable(false);
        txtSDT.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtSDT.setMinimumSize(new java.awt.Dimension(200, 30));
        txtSDT.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 0;
        jPanel18.add(txtSDT, gridBagConstraints);

        jLabel27.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel27.setText("Tổng tiền");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(jLabel27, gridBagConstraints);

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTongTien.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 2;
        jPanel18.add(txtTongTien, gridBagConstraints);

        txtNgayLap.setEditable(false);
        txtNgayLap.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtNgayLap.setPreferredSize(new java.awt.Dimension(200, 30));
        txtNgayLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayLapActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        jPanel18.add(txtNgayLap, gridBagConstraints);

        GD_HoaDon.add(jPanel18, java.awt.BorderLayout.PAGE_START);

        add(GD_HoaDon, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tableHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHDMouseClicked
        int r = tableHD.getSelectedRow();
        txtMaHD.setText(modelHD.getValueAt(r, 0).toString());
        String mHD = txtMaHD.getText().trim();
        HoaDon hd = hd_dao.getHoaDonTheoMa(mHD);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        String ngayLap = formatter.format(hd.getNgayLap());
        txtNgayLap.setText(ngayLap);
        txtTenKH.setText(modelHD.getValueAt(r, 2).toString());
        txtSDT.setText(modelHD.getValueAt(r, 3).toString());
        txtTenNV.setText(modelHD.getValueAt(r, 4).toString());
        
        Double tongTien = Double.parseDouble(modelHD.getValueAt(r, 5).toString());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String sTongTien = currencyVN.format(tongTien);
        txtTongTien.setText(sTongTien);
    }//GEN-LAST:event_tableHDMouseClicked

    private void txtNgayLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayLapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayLapActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        modelHD.getDataVector().removeAllElements();
        napDuLieuHD();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXemCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemCTHDActionPerformed
        String maHD = txtMaHD.getText().trim();
        HoaDon hd = hd_dao.getHoaDonTheoMa(maHD);
        int r = tableHD.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng");
            return;
        }
        if (tableHD.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(null, "Chỉ được chọn 1 dòng để xem");
            return;
        }
        new Form_HoaDon(hd).setVisible(true);
    }//GEN-LAST:event_btnXemCTHDActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String sdt = txtTimSDT.getText().trim();
        dsHD = hd_dao.getAllHoaDonTheoSDT(sdt);
        modelHD.getDataVector().removeAllElements();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (HoaDon hd : dsHD) {
            KhachHang kh = kh_dao.getKhachHangTheoMa(hd.getKhachHang().getMaKH());
            NhanVien nv = nv_dao.getNhanVienTheoMa(hd.getNhanVienLap().getMaNV());
            String ngayLap = formatter.format(hd.getNgayLap());
            modelHD.addRow(new Object[]{hd.getMaHD(), ngayLap, kh.getHoKH() + " " + kh.getTenKH(),
                kh.getSdtKH(), nv.getHoNV() + " " + nv.getTenNV(), hd.getTongTien()});
        }
    }//GEN-LAST:event_btnTimActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GD_HoaDon;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXemCTHD;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableHD;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimSDT;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
