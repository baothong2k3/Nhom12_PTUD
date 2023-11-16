/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import entity.NhanVien;
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
        PromptSupport.setPrompt("Mật khẩu", txtPassword);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jRadioButton1 = new javax.swing.JRadioButton();
        PanelDangNhap = new javax.swing.JPanel();
        img_login = new javax.swing.JLabel();
        label_dangNhap = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        btn_hide = new javax.swing.JButton();
        btn_dangNhap = new javax.swing.JButton();
        btn_out = new javax.swing.JButton();
        btn_fpasssword = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();

        jScrollPane1.setViewportView(jEditorPane1);

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(121, 188, 215));
        setSize(new java.awt.Dimension(1000, 500));

        PanelDangNhap.setBackground(new java.awt.Color(121, 188, 215));
        PanelDangNhap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PanelDangNhap.setPreferredSize(new java.awt.Dimension(520, 0));

        img_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/image_login.png"))); // NOI18N

        label_dangNhap.setFont(new java.awt.Font("Cambria", 1, 50)); // NOI18N
        label_dangNhap.setForeground(new java.awt.Color(33, 36, 71));
        label_dangNhap.setText("Đăng nhập");

        txtMaNV.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        txtMaNV.setText("NV001");
        txtMaNV.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtMaNV.setOpaque(true);
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        btn_hide.setBackground(new java.awt.Color(121, 188, 215));
        btn_hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/hide1.png"))); // NOI18N
        btn_hide.setBorder(null);
        btn_hide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hideActionPerformed(evt);
            }
        });

        btn_dangNhap.setBackground(new java.awt.Color(64, 71, 241));
        btn_dangNhap.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        btn_dangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btn_dangNhap.setText("Đăng nhập");
        btn_dangNhap.setBorder(null);
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

        btn_out.setBackground(new java.awt.Color(255, 51, 51));
        btn_out.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        btn_out.setForeground(new java.awt.Color(255, 255, 255));
        btn_out.setText("Thoát");
        btn_out.setBorder(null);
        btn_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_outActionPerformed(evt);
            }
        });

        btn_fpasssword.setBackground(new java.awt.Color(121, 188, 215));
        btn_fpasssword.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        btn_fpasssword.setText("Quên mật khẩu?");
        btn_fpasssword.setBorder(null);
        btn_fpasssword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fpassswordActionPerformed(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        txtPassword.setText("123");
        txtPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout PanelDangNhapLayout = new javax.swing.GroupLayout(PanelDangNhap);
        PanelDangNhap.setLayout(PanelDangNhapLayout);
        PanelDangNhapLayout.setHorizontalGroup(
            PanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDangNhapLayout.createSequentialGroup()
                .addComponent(img_login)
                .addGroup(PanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDangNhapLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(PanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDangNhapLayout.createSequentialGroup()
                                    .addComponent(btn_dangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_out, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_hide)
                                .addComponent(btn_fpasssword)))
                        .addGap(40, 40, 40))
                    .addGroup(PanelDangNhapLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(label_dangNhap)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PanelDangNhapLayout.setVerticalGroup(
            PanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDangNhapLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(label_dangNhap)
                .addGap(31, 31, 31)
                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_hide)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_fpasssword)
                .addGap(18, 18, 18)
                .addGroup(PanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_out, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_dangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(img_login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtMaNV.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelDangNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hideActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hideActionPerformed

    private void btn_dangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangNhapActionPerformed
        // TODO add your handling code here:
        String maNV = txtMaNV.getText();
        String matKhau = new String(txtPassword.getPassword());
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
    }//GEN-LAST:event_btn_fpassswordActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void btn_dangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dangNhapMouseClicked
        //new GiaoDienChinh().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btn_dangNhapMouseClicked

    private void btn_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_outActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btn_outActionPerformed

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
    private javax.swing.JButton btn_hide;
    private javax.swing.JButton btn_out;
    private javax.swing.JLabel img_login;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_dangNhap;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

    private boolean kiemTraDangNhap(String maNV, String matKhau) {
        NhanVien nv = nvDAO.getNhanVienTheoMa(maNV);
        if (matKhau.equals(nv.getMatKhau())) {
            return true;
        }
        return false;
    }
}
