/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import entity.Phong;
import gui.GD_DatPhong;
import dao.Phong_DAO;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import gui.GD_KH;
import gui.GiaoDienChinh;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author PC BAO THONG
 */
public class Form_DatPhong extends javax.swing.JFrame implements ActionListener {

    private Phong_DAO phongdao;
    private KhachHang_DAO khachhangdao;
    private PhieuDatPhong_DAO phieudatphongdao;
    private NhanVien_DAO nhanviendao;
    private Phong phong;
    String maNV_use;

    /**
     * Creates new form FormDatPhong
     */
    public Form_DatPhong(Phong phong, String maNV) {
        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        phieudatphongdao = new PhieuDatPhong_DAO();
        khachhangdao = new KhachHang_DAO();
        maNV_use = maNV;
        this.phong = phong;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        updateTextField(phong);
        ngaymai.addActionListener(this);
        homnay.addActionListener(this);
        //thietLapGio();
        txtSuggestion();
        btnDatPhong.setEnabled(false);
    }

    private void updateTextField(Phong phong) {
        phongdao = new Phong_DAO();
        txtSoPhong.setText(phong.getMaPhong());
        LoaiPhong lp = phongdao.getLoaiPhongTheoMa(phong.getLoaiPhong().getMaLP());
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.##", symbols);
        txtGiaTien.setText("" + df.format(lp.getGiaTien()) + " vnđ/giờ");
        txtSoNguoi.setText(phong.getSoNguoi() + "");
        txtLoaiPhong.setText(lp.getTenLoaiPhong());
    }

    private void txtSuggestion() {
        ArrayList<String> ds = new ArrayList<String>();
        ds = phieudatphongdao.layDSKHDatPhong();
        for (String d : ds) {
            KhachHang kh = khachhangdao.getKhachHangTheoMa(d);
            txtSDT.addItemSuggestion(kh.getSdtKH());
        }
    }

    public void thietLapGio() {
        int gio = 1;
        int phut = 0;
        if (homnay.isSelected()) {
            Date date = new Date();
            gio = date.getHours();
            phut = date.getMinutes() % 5 == 0 ? date.getMinutes() : ((date.getMinutes() / 5) * 5) + 5;
            if (phut == 60) {
                gio += 1;
                phut = 5;
            }
        }
        if (gio < 8) {
            gio = 8;
        }
        gioModel.removeAllItems();
        phutModel.removeAllItems();
        for (int i = gio; i < 23; i++) {
            gioModel.addItem(i + "");
        }
        for (int i = phut; i < 60; i += 5) {
            phutModel.addItem(i + "");
        }

    }

    public KhachHang kiemTraSDTKhach() {
        khachhangdao = new KhachHang_DAO();
        nhanviendao = new NhanVien_DAO();
        String sdt = txtSDT.getText().trim();
//        if (sdt.trim().length() == 0) {
//            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại Khách");
//            txtSDT.selectAll();
//            txtSDT.requestFocus();
//            return null;
//        }
//        if (!sdt.matches(
//                "(^(03)[2-9]\\d{7})|(^(07)[06-9]\\d{7})|(^(08)[1-5]\\d{7})|(^(056)\\d{7})|(^(058)\\d{7})|(^(059)\\d{7})|(^(09)[0-46-9]\\d{7})")) {
//            JOptionPane.showMessageDialog(this, "Số điện thoại không đúng địng dạng");
//            txtSDT.selectAll();
//            txtSDT.requestFocus();
//            return null;
//        }
        if (!(sdt.length() > 0 && sdt.matches("^\\d{10}$"))) {
            JOptionPane.showMessageDialog(null, "Error: Số điện thoại là 1 dãy số nguyên có 10 số");
            txtSDT.selectAll();
            txtSDT.requestFocus();
            return null;
        }
        KhachHang KhachHang = khachhangdao.layKhachHangTheoSDT(sdt);
        if (KhachHang == null) {
            int xacNhan = JOptionPane.showConfirmDialog(this,
                    "Khách hàng không có trong hệ thống, Bạn có muốn thêm khách hàng không?", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                new FormThemKH(txtSDT.getText()).setVisible(true);
            }
        } else {
            txtTenKhach.setText(KhachHang.getHoKH() + " " + KhachHang.getTenKH());
        }
        return KhachHang;
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

        ngayNhan = new javax.swing.ButtonGroup();
        panelHeader = new javax.swing.JPanel();
        panelC = new javax.swing.JPanel();
        lblTittle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblSoPhong = new javax.swing.JLabel();
        txtSoPhong = new javax.swing.JTextField();
        lblLoaiPhong = new javax.swing.JLabel();
        txtLoaiPhong = new javax.swing.JTextField();
        lblSoNguoi = new javax.swing.JLabel();
        txtSoNguoi = new javax.swing.JTextField();
        lblGiaTien = new javax.swing.JLabel();
        txtGiaTien = new javax.swing.JTextField();
        lblSDT = new javax.swing.JLabel();
        btnKiemTra = new javax.swing.JButton();
        lblTenKH = new javax.swing.JLabel();
        txtTenKhach = new javax.swing.JTextField();
        btnQuayLai = new javax.swing.JButton();
        btnDatPhong = new javax.swing.JButton();
        homnay = new javax.swing.JRadioButton();
        ngaymai = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        gioModel = new javax.swing.JComboBox<>();
        phutModel = new javax.swing.JComboBox<>();
        txtSDT = new textfield_suggestion.TextFieldSuggestion();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(800, 500));
        setMinimumSize(new java.awt.Dimension(800, 500));

        panelHeader.setBackground(new java.awt.Color(255, 255, 255));
        panelHeader.setMaximumSize(new java.awt.Dimension(800, 40));
        panelHeader.setMinimumSize(new java.awt.Dimension(800, 40));
        panelHeader.setPreferredSize(new java.awt.Dimension(800, 40));
        panelHeader.setLayout(new java.awt.GridLayout(1, 0));

        panelC.setBackground(new java.awt.Color(121, 188, 215));
        panelC.setPreferredSize(new java.awt.Dimension(170, 30));
        panelC.setLayout(new java.awt.GridBagLayout());

        lblTittle.setBackground(new java.awt.Color(255, 255, 255));
        lblTittle.setFont(new java.awt.Font("Cambria", 3, 24)); // NOI18N
        lblTittle.setForeground(new java.awt.Color(33, 36, 71));
        lblTittle.setText("ĐẶT PHÒNG");
        lblTittle.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        panelC.add(lblTittle, new java.awt.GridBagConstraints());

        panelHeader.add(panelC);

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 460));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 460));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 460));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0};
        jPanel1Layout.rowHeights = new int[] {0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0};
        jPanel1.setLayout(jPanel1Layout);

        lblSoPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblSoPhong.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblSoPhong.setText("Số phòng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblSoPhong, gridBagConstraints);

        txtSoPhong.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        txtSoPhong.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSoPhong.setEnabled(false);
        txtSoPhong.setMinimumSize(new java.awt.Dimension(150, 40));
        txtSoPhong.setPreferredSize(new java.awt.Dimension(200, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(txtSoPhong, gridBagConstraints);

        lblLoaiPhong.setBackground(new java.awt.Color(255, 255, 255));
        lblLoaiPhong.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblLoaiPhong.setText("Loại phòng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblLoaiPhong, gridBagConstraints);

        txtLoaiPhong.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        txtLoaiPhong.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtLoaiPhong.setEnabled(false);
        txtLoaiPhong.setMinimumSize(new java.awt.Dimension(150, 40));
        txtLoaiPhong.setPreferredSize(new java.awt.Dimension(200, 40));
        txtLoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoaiPhongActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(txtLoaiPhong, gridBagConstraints);

        lblSoNguoi.setBackground(new java.awt.Color(255, 255, 255));
        lblSoNguoi.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblSoNguoi.setText("Số người");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblSoNguoi, gridBagConstraints);

        txtSoNguoi.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        txtSoNguoi.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSoNguoi.setEnabled(false);
        txtSoNguoi.setMinimumSize(new java.awt.Dimension(150, 40));
        txtSoNguoi.setPreferredSize(new java.awt.Dimension(200, 40));
        txtSoNguoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoNguoiActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        jPanel1.add(txtSoNguoi, gridBagConstraints);

        lblGiaTien.setBackground(new java.awt.Color(255, 255, 255));
        lblGiaTien.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblGiaTien.setText("Giá tiền");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblGiaTien, gridBagConstraints);

        txtGiaTien.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        txtGiaTien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtGiaTien.setEnabled(false);
        txtGiaTien.setMinimumSize(new java.awt.Dimension(170, 40));
        txtGiaTien.setPreferredSize(new java.awt.Dimension(200, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        jPanel1.add(txtGiaTien, gridBagConstraints);

        lblSDT.setBackground(new java.awt.Color(255, 255, 255));
        lblSDT.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblSDT.setText("SĐT Khách");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblSDT, gridBagConstraints);

        btnKiemTra.setBackground(new java.awt.Color(204, 255, 255));
        btnKiemTra.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnKiemTra.setText("Kiểm tra");
        btnKiemTra.setPreferredSize(new java.awt.Dimension(120, 40));
        btnKiemTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKiemTraActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        jPanel1.add(btnKiemTra, gridBagConstraints);

        lblTenKH.setBackground(new java.awt.Color(255, 255, 255));
        lblTenKH.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblTenKH.setText("Tên khách hàng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        jPanel1.add(lblTenKH, gridBagConstraints);

        txtTenKhach.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        txtTenKhach.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTenKhach.setEnabled(false);
        txtTenKhach.setMinimumSize(new java.awt.Dimension(150, 40));
        txtTenKhach.setPreferredSize(new java.awt.Dimension(200, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        jPanel1.add(txtTenKhach, gridBagConstraints);

        btnQuayLai.setBackground(new java.awt.Color(153, 204, 255));
        btnQuayLai.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnQuayLai.setText("Quay lại");
        btnQuayLai.setPreferredSize(new java.awt.Dimension(120, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(btnQuayLai, gridBagConstraints);

        btnDatPhong.setBackground(new java.awt.Color(204, 255, 204));
        btnDatPhong.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnDatPhong.setText("Đặt phòng");
        btnDatPhong.setPreferredSize(new java.awt.Dimension(120, 40));
        btnDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatPhongActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(btnDatPhong, gridBagConstraints);

        homnay.setBackground(new java.awt.Color(255, 255, 255));
        ngayNhan.add(homnay);
        homnay.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        homnay.setText("Hôm nay");
        homnay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homnayActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(homnay, gridBagConstraints);

        ngaymai.setBackground(new java.awt.Color(255, 255, 255));
        ngayNhan.add(ngaymai);
        ngaymai.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        ngaymai.setText("Ngày mai");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(ngaymai, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel1.setText("Ngày nhận phòng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel2.setText("Giờ nhận phòng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jLabel2, gridBagConstraints);

        gioModel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        gioModel.setFocusable(false);
        gioModel.setPreferredSize(new java.awt.Dimension(80, 40));
        gioModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gioModelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(gioModel, gridBagConstraints);

        phutModel.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        phutModel.setFocusable(false);
        phutModel.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(phutModel, gridBagConstraints);

        txtSDT.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        txtSDT.setPreferredSize(new java.awt.Dimension(200, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        jPanel1.add(txtSDT, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoaiPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoaiPhongActionPerformed

    private void txtSoNguoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoNguoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoNguoiActionPerformed

    private void homnayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homnayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_homnayActionPerformed

    private void btnKiemTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraActionPerformed
        // TODO add your handling code here:
        if (kiemTraSDTKhach() != null) {
            btnDatPhong.setEnabled(true);
        }

    }//GEN-LAST:event_btnKiemTraActionPerformed

    private void btnDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatPhongActionPerformed
        // TODO add your handling code here:
        phieudatphongdao = new PhieuDatPhong_DAO();
        KhachHang khachHang = kiemTraSDTKhach();
        int gio;
        int phut;
        if (homnay.isSelected() || ngaymai.isSelected()) {
            gio = Integer.parseInt(gioModel.getSelectedItem().toString());
            phut = Integer.parseInt(phutModel.getSelectedItem().toString());
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày nhận phòng");
            return;
        }
        Date date = new Date();
        if (homnay.isSelected()) {
            if (gio < date.getDay() || (gio == date.getHours() && phut < date.getMinutes())) {
                JOptionPane.showMessageDialog(this, "Thời gian phải trước thời gian hiện tại");
                return;
            }
        } else {
            // add one day
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
        }
        date.setMinutes(phut);
        date.setHours(gio);
        System.out.println(date);
        if (khachHang == null) {
            return;
        }
        int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn đặt phòng không?", "Thông báo",
                JOptionPane.YES_NO_OPTION);
        if (xacNhan != JOptionPane.YES_OPTION) {
            return;
        }
        String maphieu = phieudatphongdao.maPDP_Auto();
        NhanVien nv = nhanviendao.getNhanVienTheoMa(maNV_use);
        PhieuDatPhong phieuDatPhong = new PhieuDatPhong(maphieu, khachHang, nv, phong, new Date(), date, false);
        if (!phongdao.capNhatTrangThaiPhong(phong.getMaPhong(), "Đã được đặt")
                || !phieudatphongdao.themPhieuDatPhong(phieuDatPhong)) {
            JOptionPane.showMessageDialog(this, "Đặt phòng KHÔNG thành công");
//            setVisible(false);
//            dispose();
//            return;
        } else {
            JOptionPane.showMessageDialog(this, "Đặt phòng thành công");
        }
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnDatPhongActionPerformed

    private void gioModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gioModelActionPerformed
        // TODO add your handling code here:
        //
        //thietLapGio();
        Date date = new Date();
        int gio = 0;
        if (gioModel.getSelectedItem() != null) {
            gio = Integer.parseInt(gioModel.getSelectedItem().toString());
        }
        if (gio != date.getHours()) {
            phutModel.removeAllItems();
            for (int i = 0; i < 60; i += 5) {
                phutModel.addItem(i + "");
            }
        }
    }//GEN-LAST:event_gioModelActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatPhong;
    private javax.swing.JButton btnKiemTra;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JComboBox<String> gioModel;
    private javax.swing.JRadioButton homnay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblGiaTien;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSoNguoi;
    private javax.swing.JLabel lblSoPhong;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTittle;
    private javax.swing.ButtonGroup ngayNhan;
    private javax.swing.JRadioButton ngaymai;
    private javax.swing.JPanel panelC;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JComboBox<String> phutModel;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtLoaiPhong;
    private textfield_suggestion.TextFieldSuggestion txtSDT;
    private javax.swing.JTextField txtSoNguoi;
    private javax.swing.JTextField txtSoPhong;
    private javax.swing.JTextField txtTenKhach;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object.equals(homnay) || object.equals(ngaymai)) {
            thietLapGio();
        }
    }
}
