/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import connectDB.ConnectDB;
import dao.DichVu_DAO;
import entity.DichVu;
import entity.KhachHang;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author PC BAO THONG
 */
public class GD_DV extends javax.swing.JPanel {

    private DichVu_DAO dichvudao;
    private ArrayList<DichVu> dsDV;
    private DefaultTableModel modelDV;

    /**
     * Creates new form GD_DV
     */
    public GD_DV() {
        try {
            ConnectDB.getInstance().connect();
//            System.out.println("Ket noi Database thanh cong");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dichvudao = new DichVu_DAO();
        initComponents();
        tableDichVu();
    }

    private void tableDichVu() {
        PromptSupport.setPrompt("Nhập mã dịch vụ", txtTim);
        tableDichVu.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        tableDichVu.getTableHeader().setOpaque(false);
        tableDichVu.getTableHeader().setBackground(new Color(32, 136, 203));
        tableDichVu.getTableHeader().setForeground(new Color(255, 255, 255));
        modelDV = (DefaultTableModel) tableDichVu.getModel();
        loadAllDV();
    }

    private void loadAllDV() {
        dsDV = dichvudao.getAllDichVu();
        String tinhtrang;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (DichVu dichVu : dsDV) {
            if (dichVu.isTinhTrang()) {
                tinhtrang = "Đang bán";
            } else {
                tinhtrang = "Ngừng bán";
            }
            String sHSD;
            if (dichVu.getHsd() != null) {
                sHSD = formatter.format(dichVu.getHsd());
            } else {
                sHSD = "";
            }
            Double tien = dichVu.getDonGia();
            modelDV.addRow(new Object[]{dichVu.getMaDV(), dichVu.getTenDV(), dichVu.getDonViBan(), dichVu.getSoLuongTon(), tien, sHSD, dichVu.getXuatXu(), tinhtrang});
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

        btngTinhTrang = new javax.swing.ButtonGroup();
        GD_DichVu = new javax.swing.JPanel();
        panelNorth = new javax.swing.JPanel();
        lblMaDV = new javax.swing.JLabel();
        txtMaDV = new javax.swing.JTextField();
        lblDonGia = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        lblTenDV = new javax.swing.JLabel();
        txtTenDV = new javax.swing.JTextField();
        lblHsd = new javax.swing.JLabel();
        hsd = new com.toedter.calendar.JDateChooser();
        lblXuatXu = new javax.swing.JLabel();
        comboXuatXu = new javax.swing.JComboBox<>();
        lblDonViBan = new javax.swing.JLabel();
        comboDVB = new javax.swing.JComboBox<>();
        lblSoLuong = new javax.swing.JLabel();
        spinSoLuong = new com.toedter.components.JSpinField();
        lblTinhTrang = new javax.swing.JLabel();
        radioDangBan = new javax.swing.JRadioButton();
        radioNgungBan = new javax.swing.JRadioButton();
        btnThemDV = new javax.swing.JButton();
        btnCapNhatDV = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        panelCenter = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        tableDichVu = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        GD_DichVu.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        GD_DichVu.setMinimumSize(new java.awt.Dimension(1200, 520));
        GD_DichVu.setPreferredSize(new java.awt.Dimension(1200, 520));
        GD_DichVu.setLayout(new java.awt.BorderLayout());

        panelNorth.setBackground(new java.awt.Color(255, 255, 255));
        panelNorth.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        panelNorth.setMinimumSize(new java.awt.Dimension(1200, 300));
        panelNorth.setPreferredSize(new java.awt.Dimension(1200, 300));
        java.awt.GridBagLayout panelNorthLayout = new java.awt.GridBagLayout();
        panelNorthLayout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        panelNorthLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        panelNorth.setLayout(panelNorthLayout);

        lblMaDV.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblMaDV.setText("Mã dịch vụ");
        lblMaDV.setMaximumSize(new java.awt.Dimension(110, 40));
        lblMaDV.setMinimumSize(new java.awt.Dimension(110, 40));
        lblMaDV.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(lblMaDV, gridBagConstraints);

        txtMaDV.setBackground(new java.awt.Color(242, 242, 242));
        txtMaDV.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtMaDV.setText("DVB001");
        txtMaDV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMaDV.setEnabled(false);
        txtMaDV.setMaximumSize(new java.awt.Dimension(250, 30));
        txtMaDV.setMinimumSize(new java.awt.Dimension(250, 30));
        txtMaDV.setPreferredSize(new java.awt.Dimension(250, 30));
        txtMaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDVActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        panelNorth.add(txtMaDV, gridBagConstraints);

        lblDonGia.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblDonGia.setText("Đơn giá");
        lblDonGia.setMaximumSize(new java.awt.Dimension(155, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(lblDonGia, gridBagConstraints);

        txtDonGia.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtDonGia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDonGia.setMaximumSize(new java.awt.Dimension(250, 30));
        txtDonGia.setMinimumSize(new java.awt.Dimension(250, 30));
        txtDonGia.setPreferredSize(new java.awt.Dimension(250, 30));
        txtDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGiaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        panelNorth.add(txtDonGia, gridBagConstraints);

        lblTenDV.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblTenDV.setText("Tên dịch vụ");
        lblTenDV.setMaximumSize(new java.awt.Dimension(110, 40));
        lblTenDV.setMinimumSize(new java.awt.Dimension(110, 40));
        lblTenDV.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(lblTenDV, gridBagConstraints);

        txtTenDV.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTenDV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTenDV.setMaximumSize(new java.awt.Dimension(250, 30));
        txtTenDV.setMinimumSize(new java.awt.Dimension(250, 30));
        txtTenDV.setPreferredSize(new java.awt.Dimension(250, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        panelNorth.add(txtTenDV, gridBagConstraints);

        lblHsd.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblHsd.setText("Hạn sử dụng");
        lblHsd.setMaximumSize(new java.awt.Dimension(110, 40));
        lblHsd.setMinimumSize(new java.awt.Dimension(110, 40));
        lblHsd.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(lblHsd, gridBagConstraints);

        hsd.setBackground(new java.awt.Color(255, 255, 255));
        hsd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        hsd.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        hsd.setMaximumSize(new java.awt.Dimension(250, 30));
        hsd.setMinimumSize(new java.awt.Dimension(250, 30));
        hsd.setPreferredSize(new java.awt.Dimension(250, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 4;
        panelNorth.add(hsd, gridBagConstraints);

        lblXuatXu.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblXuatXu.setText("Xuất xứ");
        lblXuatXu.setMaximumSize(new java.awt.Dimension(110, 40));
        lblXuatXu.setMinimumSize(new java.awt.Dimension(110, 40));
        lblXuatXu.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(lblXuatXu, gridBagConstraints);

        comboXuatXu.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        comboXuatXu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Việt Nam", "Thái Lan", "Trung Quốc", "Hoa Kỳ", "Đức" }));
        comboXuatXu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        comboXuatXu.setMaximumSize(new java.awt.Dimension(250, 30));
        comboXuatXu.setMinimumSize(new java.awt.Dimension(250, 30));
        comboXuatXu.setPreferredSize(new java.awt.Dimension(250, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 8;
        panelNorth.add(comboXuatXu, gridBagConstraints);

        lblDonViBan.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblDonViBan.setText("Đơn vị bán");
        lblDonViBan.setMaximumSize(new java.awt.Dimension(110, 40));
        lblDonViBan.setMinimumSize(new java.awt.Dimension(110, 40));
        lblDonViBan.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(lblDonViBan, gridBagConstraints);

        comboDVB.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        comboDVB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lon", "Cái", "Dĩa", "Hộp", "Chai", "Bịch" }));
        comboDVB.setMaximumSize(new java.awt.Dimension(250, 30));
        comboDVB.setMinimumSize(new java.awt.Dimension(250, 30));
        comboDVB.setPreferredSize(new java.awt.Dimension(250, 30));
        comboDVB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDVBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        panelNorth.add(comboDVB, gridBagConstraints);

        lblSoLuong.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblSoLuong.setText("Số lượng tồn");
        lblSoLuong.setMaximumSize(new java.awt.Dimension(110, 40));
        lblSoLuong.setMinimumSize(new java.awt.Dimension(110, 40));
        lblSoLuong.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(lblSoLuong, gridBagConstraints);

        spinSoLuong.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        spinSoLuong.setMaximumSize(new java.awt.Dimension(100, 30));
        spinSoLuong.setMinimum(0);
        spinSoLuong.setMinimumSize(new java.awt.Dimension(100, 30));
        spinSoLuong.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(spinSoLuong, gridBagConstraints);

        lblTinhTrang.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblTinhTrang.setText("Tình trạng");
        lblTinhTrang.setMaximumSize(new java.awt.Dimension(110, 40));
        lblTinhTrang.setMinimumSize(new java.awt.Dimension(110, 40));
        lblTinhTrang.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(lblTinhTrang, gridBagConstraints);

        radioDangBan.setBackground(new java.awt.Color(255, 255, 255));
        btngTinhTrang.add(radioDangBan);
        radioDangBan.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        radioDangBan.setText("Đang bán");
        radioDangBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDangBanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(radioDangBan, gridBagConstraints);

        radioNgungBan.setBackground(new java.awt.Color(255, 255, 255));
        btngTinhTrang.add(radioNgungBan);
        radioNgungBan.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        radioNgungBan.setText("Ngừng bán");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        panelNorth.add(radioNgungBan, gridBagConstraints);

        btnThemDV.setBackground(new java.awt.Color(61, 102, 255));
        btnThemDV.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnThemDV.setForeground(new java.awt.Color(255, 255, 255));
        btnThemDV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add-user.png"))); // NOI18N
        btnThemDV.setText("Thêm dịch vụ");
        btnThemDV.setMaximumSize(new java.awt.Dimension(170, 40));
        btnThemDV.setMinimumSize(new java.awt.Dimension(170, 30));
        btnThemDV.setPreferredSize(new java.awt.Dimension(250, 30));
        btnThemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(btnThemDV, gridBagConstraints);

        btnCapNhatDV.setBackground(new java.awt.Color(102, 255, 51));
        btnCapNhatDV.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnCapNhatDV.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatDV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/system-update.png"))); // NOI18N
        btnCapNhatDV.setText("Cập nhật thông tin");
        btnCapNhatDV.setMaximumSize(new java.awt.Dimension(210, 40));
        btnCapNhatDV.setMinimumSize(new java.awt.Dimension(210, 30));
        btnCapNhatDV.setPreferredSize(new java.awt.Dimension(250, 30));
        btnCapNhatDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatDVActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(btnCapNhatDV, gridBagConstraints);

        btnLamMoi.setBackground(new java.awt.Color(0, 255, 153));
        btnLamMoi.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setMaximumSize(new java.awt.Dimension(135, 40));
        btnLamMoi.setMinimumSize(new java.awt.Dimension(135, 30));
        btnLamMoi.setPreferredSize(new java.awt.Dimension(250, 30));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(btnLamMoi, gridBagConstraints);

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/eraser.png"))); // NOI18N
        btnXoa.setText("Xóa trắng");
        btnXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnXoa.setMaximumSize(new java.awt.Dimension(135, 40));
        btnXoa.setMinimumSize(new java.awt.Dimension(135, 30));
        btnXoa.setPreferredSize(new java.awt.Dimension(250, 30));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 16;
        panelNorth.add(btnXoa, gridBagConstraints);

        btnTim.setBackground(new java.awt.Color(121, 188, 215));
        btnTim.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnTim.setForeground(new java.awt.Color(255, 255, 255));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search-user.png"))); // NOI18N
        btnTim.setText("Tìm");
        btnTim.setMaximumSize(new java.awt.Dimension(90, 30));
        btnTim.setMinimumSize(new java.awt.Dimension(90, 30));
        btnTim.setPreferredSize(new java.awt.Dimension(135, 30));
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth.add(btnTim, gridBagConstraints);

        txtTim.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTim.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTim.setMaximumSize(new java.awt.Dimension(250, 30));
        txtTim.setMinimumSize(new java.awt.Dimension(250, 30));
        txtTim.setPreferredSize(new java.awt.Dimension(250, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 16;
        panelNorth.add(txtTim, gridBagConstraints);

        GD_DichVu.add(panelNorth, java.awt.BorderLayout.PAGE_START);

        panelCenter.setBackground(new java.awt.Color(255, 255, 255));
        panelCenter.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        panelCenter.setLayout(new java.awt.BorderLayout());

        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        scrollPane.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        scrollPane.setMinimumSize(new java.awt.Dimension(1200, 160));
        scrollPane.setPreferredSize(new java.awt.Dimension(1200, 900));

        tableDichVu.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        tableDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Đơn vị bán", "Số lượng tồn", "Đơn giá", "Hạn sử dụng", "Xuất xứ", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDichVu.setMinimumSize(new java.awt.Dimension(1200, 300));
        tableDichVu.setRowHeight(25);
        tableDichVu.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tableDichVu.setShowHorizontalLines(true);
        tableDichVu.getTableHeader().setReorderingAllowed(false);
        tableDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDichVuMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(tableDichVu);

        panelCenter.add(scrollPane, java.awt.BorderLayout.CENTER);

        GD_DichVu.add(panelCenter, java.awt.BorderLayout.CENTER);

        add(GD_DichVu, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDVActionPerformed

    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void comboDVBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDVBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDVBActionPerformed

    private void radioDangBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDangBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioDangBanActionPerformed

    private void btnThemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVActionPerformed
        if (validDV()) {
            String ma = dichvudao.maDV_Auto();
            String ten = txtTenDV.getText();
            Double gia = Double.parseDouble(txtDonGia.getText());
            Date han = hsd.getDate();
            String dvb = comboDVB.getSelectedItem().toString();
            String xuatxu = comboXuatXu.getSelectedItem().toString();
            int soluong = spinSoLuong.getValue();
            Boolean tinhtrang = false;
            if (radioDangBan.isSelected()) {
                tinhtrang = true;
            }
            DichVu dv = new DichVu(ma, ten, dvb, soluong, gia, han, xuatxu, tinhtrang);
            if (dichvudao.insertDichVu(dv)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!!!");
                modelDV.setRowCount(0);
                loadAllDV();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thêm không thành công!!");
        }
    }//GEN-LAST:event_btnThemDVActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lamMoi();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoaTrang();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        Object o = evt.getSource();
        if (o.equals(btnTim)) {
            String maDV = txtTim.getText().trim();
            DichVu dichVu = dichvudao.getDichVuTheoMa(maDV);
            modelDV.getDataVector().removeAllElements();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String tinhTrang;
            if (dichVu.isTinhTrang()) {
                tinhTrang = "Đang bán";
            } else {
                tinhTrang = "Ngừng bán";
            }
            String sHSD;
            if (dichVu.getHsd() != null) {
                sHSD = formatter.format(dichVu.getHsd());
            } else {
                sHSD = "";
            }
            Double tien = dichVu.getDonGia();
            modelDV.addRow(new Object[]{dichVu.getMaDV(), dichVu.getTenDV(), dichVu.getDonViBan(), dichVu.getSoLuongTon(), tien, sHSD, dichVu.getXuatXu(), tinhTrang});
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void tableDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDichVuMouseClicked
        // TODO add your handling code here:
        int row = tableDichVu.getSelectedRow();
        txtMaDV.setText(modelDV.getValueAt(row, 0).toString());
        txtTenDV.setText(modelDV.getValueAt(row, 1).toString());
        comboDVB.setSelectedItem(modelDV.getValueAt(row, 2).toString());
        spinSoLuong.setValue((int) modelDV.getValueAt(row, 3));
        Double gia = (Double) modelDV.getValueAt(row, 4);
        txtDonGia.setText(gia + "");
//        try {
//            //date = new SimpleDateFormat("yyy/MM/dd").parse((String) );
//            String han = modelDV.getValueAt(row, 5).toString();
//            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(han);
//            hsd.setDate(date);
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }

        if (modelDV.getValueAt(row, 5) == "") {
            hsd.setDate(null);
        } else {
            String han = modelDV.getValueAt(row, 5).toString();
            Date date;
            try {
                date = new SimpleDateFormat("dd-MM-yyyy").parse(han);
                hsd.setDate(date);

            } catch (ParseException ex) {
                Logger.getLogger(GD_DV.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        comboXuatXu.setSelectedItem(modelDV.getValueAt(row, 6).toString());
        String tinhtrang = modelDV.getValueAt(row, 7).toString();
        if (tinhtrang.equalsIgnoreCase("Đang bán")) {
            radioDangBan.setSelected(true);
            radioNgungBan.setSelected(false);
        } else {
            radioDangBan.setSelected(false);
            radioNgungBan.setSelected(true);
        }
    }//GEN-LAST:event_tableDichVuMouseClicked

    private void btnCapNhatDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatDVActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnCapNhatDVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GD_DichVu;
    private javax.swing.JButton btnCapNhatDV;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThemDV;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup btngTinhTrang;
    private javax.swing.JComboBox<String> comboDVB;
    private javax.swing.JComboBox<String> comboXuatXu;
    private com.toedter.calendar.JDateChooser hsd;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblDonViBan;
    private javax.swing.JLabel lblHsd;
    private javax.swing.JLabel lblMaDV;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenDV;
    private javax.swing.JLabel lblTinhTrang;
    private javax.swing.JLabel lblXuatXu;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelNorth;
    private javax.swing.JRadioButton radioDangBan;
    private javax.swing.JRadioButton radioNgungBan;
    private javax.swing.JScrollPane scrollPane;
    private com.toedter.components.JSpinField spinSoLuong;
    private javax.swing.JTable tableDichVu;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaDV;
    private javax.swing.JTextField txtTenDV;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
    public void xoaTrang() {
        txtMaDV.setText("");
        txtTenDV.setText("");
        txtDonGia.setText("");
        radioDangBan.setSelected(false);
        radioNgungBan.setSelected(false);
        hsd.setDate(null);
        spinSoLuong.setValue(0);
    }

    private void lamMoi() {
        xoaTrang();
        modelDV.setRowCount(0);
        loadAllDV();
    }

    private void update() {
        // TODO Auto-generated method stub

        int row = tableDichVu.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!!");
            return;
        }
        if (tableDichVu.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(null, "Chỉ được chọn 1 dòng để sửa");
            return;
        }
        if (validDV()) {
            String ma = txtMaDV.getText();
            String ten = txtTenDV.getText();
            Double gia = Double.parseDouble(txtDonGia.getText());
            Date han = hsd.getDate();
            String dvb = comboDVB.getSelectedItem().toString();
            String xuatxu = comboXuatXu.getSelectedItem().toString();
            int soluong = spinSoLuong.getValue();
            Boolean tinhtrang = false;
            if (radioDangBan.isSelected()) {
                tinhtrang = true;
            }
            DichVu dv = new DichVu(ma, ten, dvb, soluong, gia, han, xuatxu, tinhtrang);
            if (dichvudao.updateDV(dv)) {
                JOptionPane.showMessageDialog(this, "Sửa thành công!!!");
                modelDV.setRowCount(0);
                loadAllDV();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sửa không thành công!!");
        }

    }

    public boolean isNumber(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean validDV() {
        String tenDV = txtTenDV.getText().trim();
        if (!(tenDV.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Tên dịch vụ không trống");
            txtTenDV.requestFocus();
            return false;
        }
        String sDonGia = txtDonGia.getText().trim();
        double donGia = Double.parseDouble(sDonGia);
        if (!(sDonGia.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Đơn giá không trống");
            txtDonGia.requestFocus();
            return false;
        } else if (!(isNumber(sDonGia))) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải là số");
            txtDonGia.requestFocus();
            return false;
        } else if (donGia < 0) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải lớn hơn hoặc bằng 0");
            txtDonGia.requestFocus();
            return false;
        }
        int sl = spinSoLuong.getValue();
        String sSL = "" + sl;
        if (!(sSL.length() > 0)) {
            JOptionPane.showMessageDialog(null, "số lượng không trống");
            spinSoLuong.requestFocus();
            return false;
        } else if (!(isNumber(sSL))) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là số");
            spinSoLuong.requestFocus();
            return false;
        } else if (sl < 0) {
            JOptionPane.showMessageDialog(null, "số lượng phải lớn hơn hoặc bằng 0");
            spinSoLuong.requestFocus();
            return false;
        }
        return true;
    }

}
