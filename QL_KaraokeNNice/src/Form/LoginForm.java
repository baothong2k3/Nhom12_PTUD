/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import entity.NhanVien;
import gui.GiaoDienChinh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author PC BAO THONG
 */
public class LoginForm extends javax.swing.JFrame {

    private NhanVien_DAO nvDAO;

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        try {
            ConnectDB.getInstance().connect();
//            System.out.println("Ket noi Database thanh cong");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        nvDAO = new NhanVien_DAO();
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Quản lý karaoke NNice");
        label_dangNhap.requestFocus();
        PromptSupport.setPrompt("Mã nhân viên", txtMaNV);
        //PromptSupport.setPrompt("Mật khẩu", txtPassword);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        PanelDangNhap = new javax.swing.JPanel();
        img_login = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtMaNV = new textfield.TextField();
        btn_dangNhap = new javax.swing.JButton();
        btn_out = new javax.swing.JButton();
        btn_fpasssword = new javax.swing.JButton();
        passwordField1 = new textfield.PasswordField();
        label_dangNhap = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(121, 188, 215));
        setSize(new java.awt.Dimension(920, 500));

        PanelDangNhap.setBackground(new java.awt.Color(121, 188, 215));
        PanelDangNhap.setPreferredSize(new java.awt.Dimension(520, 500));
        PanelDangNhap.setLayout(new java.awt.GridBagLayout());

        img_login.setBackground(new java.awt.Color(121, 188, 215));
        img_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/image_login.png"))); // NOI18N
        PanelDangNhap.add(img_login, new java.awt.GridBagConstraints());

        getContentPane().add(PanelDangNhap, java.awt.BorderLayout.WEST);

        jPanel1.setBackground(new java.awt.Color(121, 188, 215));
        jPanel1.setPreferredSize(new java.awt.Dimension(530, 500));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0};
        jPanel1Layout.rowHeights = new int[] {0, 25, 0, 25, 0, 25, 0, 25, 0};
        jPanel1.setLayout(jPanel1Layout);

        txtMaNV.setText("NV001");
        txtMaNV.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        txtMaNV.setLabelText("Mã nhân viên");
        txtMaNV.setPreferredSize(new java.awt.Dimension(400, 70));
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel1.add(txtMaNV, gridBagConstraints);

        btn_dangNhap.setBackground(new java.awt.Color(64, 71, 241));
        btn_dangNhap.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        btn_dangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btn_dangNhap.setText("Đăng nhập");
        btn_dangNhap.setBorder(null);
        btn_dangNhap.setPreferredSize(new java.awt.Dimension(200, 55));
        btn_dangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dangNhapMouseClicked(evt);
            }
        });
        btn_dangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangNhapActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(btn_dangNhap, gridBagConstraints);

        btn_out.setBackground(new java.awt.Color(255, 51, 51));
        btn_out.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        btn_out.setForeground(new java.awt.Color(255, 255, 255));
        btn_out.setText("Thoát");
        btn_out.setBorder(null);
        btn_out.setPreferredSize(new java.awt.Dimension(150, 55));
        btn_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_outActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(btn_out, gridBagConstraints);

        btn_fpasssword.setBackground(new java.awt.Color(121, 188, 215));
        btn_fpasssword.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        btn_fpasssword.setText("Quên mật khẩu?");
        btn_fpasssword.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_fpasssword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fpassswordActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(btn_fpasssword, gridBagConstraints);

        passwordField1.setText("123");
        passwordField1.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        passwordField1.setLabelText("Mật khẩu");
        passwordField1.setPreferredSize(new java.awt.Dimension(400, 70));
        passwordField1.setShowAndHide(true);
        passwordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordField1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel1.add(passwordField1, gridBagConstraints);

        label_dangNhap.setBackground(new java.awt.Color(255, 255, 255));
        label_dangNhap.setFont(new java.awt.Font("Cambria", 1, 48)); // NOI18N
        label_dangNhap.setText("Đăng nhập");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(label_dangNhap, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_dangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangNhapActionPerformed
        // TODO add your handling code here:
        String maNV = txtMaNV.getText();
        String matKhau = new String(passwordField1.getPassword());
        // Kiểm tra đăng nhập
        if (kiemTraDangNhap(maNV, matKhau)) {
            // Đăng nhập thành công, thực hiện các thao tác cần thiết
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
            // Đóng cửa sổ đăng nhập
            this.dispose();
            // Hiển thị giao diện chính hoặc các thao tác khác
            NhanVien nv = nvDAO.getNhanVienTheoMa(maNV);
            GiaoDienChinh giaoDienChinh = new GiaoDienChinh(nv);
            giaoDienChinh.setVisible(true);
        } else {
            // Đăng nhập thất bại, hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Đăng nhập thất bại. Vui lòng kiểm tra lại tài khoản và mật khẩu.");
        }

    }//GEN-LAST:event_btn_dangNhapActionPerformed

    private void btn_fpassswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fpassswordActionPerformed
        // TODO add your handling code here:
        new Form_QuenMK().setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_fpassswordActionPerformed

    private void btn_dangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dangNhapMouseClicked
        //new GiaoDienChinh().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btn_dangNhapMouseClicked

    private void btn_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_outActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_outActionPerformed

    private void passwordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordField1ActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDangNhap;
    private javax.swing.JButton btn_dangNhap;
    private javax.swing.JButton btn_fpasssword;
    private javax.swing.JButton btn_out;
    private javax.swing.JLabel img_login;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label_dangNhap;
    private textfield.PasswordField passwordField1;
    private textfield.TextField txtMaNV;
    // End of variables declaration//GEN-END:variables

    private boolean kiemTraDangNhap(String maNV, String matKhau) {
        NhanVien nv = nvDAO.getNhanVienTheoMa(maNV);
        return matKhau.equals(nv.getMatKhau());
    }
}
