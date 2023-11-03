/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.toedter.calendar.JDateChooserCellEditor;
import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import entity.HoaDon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.PopupMenu;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.plaf.PromptTextUI;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author PC BAO THONG
 */
public class GiaoDienChinh extends javax.swing.JFrame {

    //DAO
    private HoaDon_DAO hd_dao;

    //HoaDon
    private DefaultTableModel modelHD;

    /**
     * Creates new form HomePage
     */
    public GiaoDienChinh() {
        try {
            ConnectDB.getInstance().connect();
            System.out.println("Ket noi Database thanh cong");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        hd_dao = new HoaDon_DAO();
        initComponents();
        setLocationRelativeTo(null);
        //setResizable(false);
        setTitle("Quản lý karaoke NNice");
        tableDichVu();
        tableNhanVien();
        tableKhachHang();
        tablePhieuDat();
        tableHoaDon();
        tableDanhSachPhong();
        hienThiNgay();
        dsPhong();
    }

    private void tableDichVu() {
        PromptSupport.setPrompt("Nhập mã dịch vụ", txtTim);
        tableDichVu.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        tableDichVu.getTableHeader().setOpaque(false);
        tableDichVu.getTableHeader().setBackground(new Color(32, 136, 203));
        tableDichVu.getTableHeader().setForeground(new Color(255, 255, 255));
        //đơn vị bán
        String[] donVi = {"Lon", "Cái", "Đĩa", "Hộp"};
        JComboBox comboBoxDV = new JComboBox(donVi);
        comboBoxDV.setFont(new Font("Cambria", Font.PLAIN, 16));
        comboBoxDV.setBackground(new Color(255, 255, 255));
        tableDichVu.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBoxDV));
        //xuất xứ
        String[] xuatXu = {"Việt Nam", "Thái Lan", "Trung Quốc", "Hoa Kỳ", "Đức"};
        JComboBox comboBoxXX = new JComboBox(xuatXu);
        comboBoxXX.setFont(new Font("Cambria", Font.PLAIN, 16));
        comboBoxXX.setBackground(new Color(255, 255, 255));
        tableDichVu.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(comboBoxXX));
        //tình trạng
        String[] tinhTrang = {"Đang bán", "Ngừng bán"};
        JComboBox comboBoxTT = new JComboBox(tinhTrang);
        comboBoxTT.setFont(new Font("Cambria", Font.PLAIN, 16));
        comboBoxTT.setBackground(new Color(255, 255, 255));
        tableDichVu.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(comboBoxTT));
        //HSD
        tableDichVu.getColumnModel().getColumn(5).setCellEditor(new JDateChooserCellEditor());
    }

    private void tableNhanVien() {
        PromptSupport.setPrompt("Nhập số điện thoại", txtTim1);
        tableDSNV.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        tableDSNV.getTableHeader().setOpaque(false);
        tableDSNV.getTableHeader().setBackground(new Color(32, 136, 203));
        tableDSNV.getTableHeader().setForeground(new Color(255, 255, 255));
        //chức vụ
        String[] chucvu = {"Quản lý", "Lễ tân", "Bảo vệ", "Phục vụ"};
        JComboBox comboBoxCV = new JComboBox(chucvu);
        comboBoxCV.setFont(new Font("Cambria", Font.PLAIN, 16));
        comboBoxCV.setBackground(new Color(255, 255, 255));
        tableDSNV.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(comboBoxCV));
        //giới tính
        String[] gioitinh = {"Nam", "Nữ"};
        JComboBox comboBoxGT = new JComboBox(gioitinh);
        comboBoxGT.setFont(new Font("Cambria", Font.PLAIN, 16));
        comboBoxGT.setBackground(new Color(255, 255, 255));
        tableDSNV.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(comboBoxGT));
        //trạng thái
        String[] trangthai = {"Đang làm", "Đã nghỉ"};
        JComboBox comboBoxTT = new JComboBox(trangthai);
        comboBoxTT.setFont(new Font("Cambria", Font.PLAIN, 16));
        comboBoxTT.setBackground(new Color(255, 255, 255));
        tableDSNV.getColumnModel().getColumn(11).setCellEditor(new DefaultCellEditor(comboBoxTT));
        //ngày sinh
        tableDSNV.getColumnModel().getColumn(4).setCellEditor(new JDateChooserCellEditor());
    }

    private void tableKhachHang() {
        PromptSupport.setPrompt("Nhập số điện thoại", txtTimKH);
        tableKH.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        tableKH.getTableHeader().setOpaque(false);
        tableKH.getTableHeader().setBackground(new Color(32, 136, 203));
        tableKH.getTableHeader().setForeground(new Color(255, 255, 255));
        tableKH.getColumnModel().getColumn(4).setCellEditor(new JDateChooserCellEditor());
    }

    private void tablePhieuDat() {
        PromptSupport.setPrompt("Nhập số điện thoại khách", txtTimPhieu);
        tablePhieu.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        tablePhieu.getTableHeader().setOpaque(false);
        tablePhieu.getTableHeader().setBackground(new Color(32, 136, 203));
        tablePhieu.getTableHeader().setForeground(new Color(255, 255, 255));
        tablePhieu.getColumnModel().getColumn(3).setCellEditor(new JDateChooserCellEditor());
        tablePhieu.getColumnModel().getColumn(4).setCellEditor(new JDateChooserCellEditor());
    }

    private void tableHoaDon() {
        PromptSupport.setPrompt("Nhập số điện thoại khách", txtHD);
        tableHD.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        tableHD.getTableHeader().setOpaque(false);
        tableHD.getTableHeader().setBackground(new Color(32, 136, 203));
        tableHD.getTableHeader().setForeground(new Color(255, 255, 255));
        tableHD.getColumnModel().getColumn(1).setCellEditor(new JDateChooserCellEditor());
        modelHD = (DefaultTableModel) tableHD.getModel();
        napDuLieuHD();
    }

    private void napDuLieuHD() {
        ArrayList<HoaDon> dsHD;
        dsHD = hd_dao.getAllHoaDon();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (HoaDon hd : dsHD) {
            String ngayLap = formatter.format(hd.getGioKetThuc());
            modelHD.addRow(new Object[]{hd.getMaHD(), ngayLap, hd.getKhachHang().getHoKH() + " " + hd.getKhachHang().getTenKH(),
                hd.getKhachHang().getSdtKH(), hd.getNhanVienLap().getHoNV() + " " + hd.getNhanVienLap().getTenNV(), hd.getTongTien()});
        }
    }

    private void tableDanhSachPhong() {
        PromptSupport.setPrompt("Nhập mã phòng", txtTP);
        tableDSPhong.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        tableDSPhong.getTableHeader().setOpaque(false);
        tableDSPhong.getTableHeader().setBackground(new Color(32, 136, 203));
        tableDSPhong.getTableHeader().setForeground(new Color(255, 255, 255));
        tableDSPhong.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(comboSucChua));
    }

    private void disableAllPanel() {
        GD_Chinh.setVisible(false);
        GD_DichVu.setVisible(false);
        GD_NhanVien.setVisible(false);
        GD_KhachHang.setVisible(false);
        GD_Phong.setVisible(false);
        GD_PhieuDatPhong.setVisible(false);
        GD_HoaDon.setVisible(false);
        GD_DSPhong.setVisible(false);
    }

    private void hienThiNgay() {
        // Tạo JLabel để hiển thị ngày và giờ
        jPanel12.setLayout(new GridBagLayout());
        JLabel dateLabel = new JLabel();
        JLabel timeLabel = new JLabel();

        jPanel12.add(dateLabel);
        jPanel12.add(timeLabel);
        // Định dạng cho ngày và giờ
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");

        // Tạo một GridBagConstraints để canh chỉnh phần tử vào giữa
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Khoảng cách từ các lề
        constraints.anchor = GridBagConstraints.CENTER; // Canh giữa

        constraints.gridy = 0; // Hàng 0 cho ngày
        dateLabel.setFont(new Font("Cambria", Font.PLAIN, 16));
        jPanel12.add(dateLabel, constraints);

        constraints.gridy = 1; // Hàng 1 cho giờ
        timeLabel.setFont(new Font("Cambria", Font.PLAIN, 16));
        jPanel12.add(timeLabel, constraints);

        Timer timer = new Timer(1000, e -> {
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();

            String formattedDate = dateFormatter.format(now);
            String formattedTime = timeFormatter.format(now);

            dateLabel.setText("Date: " + formattedDate);
            timeLabel.setText("Time: " + formattedTime);
        });
        timer.start();
    }

    private void dsPhong() {
        for (int i = 0; i < 10; i++) {
//            panelPhong.add(new JButton("VCC"));
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
        GD_NhanVien = new javax.swing.JPanel();
        panelNorth1 = new javax.swing.JPanel();
        lblMaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        lblCCCD = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        lblHo = new javax.swing.JLabel();
        txtHo = new javax.swing.JTextField();
        lblTen = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        lblNgaySinh = new javax.swing.JLabel();
        ngaySinh = new com.toedter.calendar.JDateChooser();
        lblGioiTinh = new javax.swing.JLabel();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        lblSDT = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtChucVu = new javax.swing.JLabel();
        comboChucVu = new javax.swing.JComboBox<>();
        lblMatKhau = new javax.swing.JLabel();
        pwMatKhau = new javax.swing.JPasswordField();
        lblTrangThai = new javax.swing.JLabel();
        radioDangLam = new javax.swing.JRadioButton();
        radioDaNghi = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnTim1 = new javax.swing.JButton();
        txtTim1 = new javax.swing.JTextField();
        btnLamMoi1 = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        panelDSNV = new javax.swing.JPanel();
        scrollPane1 = new javax.swing.JScrollPane();
        tableDSNV = new javax.swing.JTable();
        GD_KhachHang = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTimKH = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        radioNu1 = new javax.swing.JRadioButton();
        radioNam1 = new javax.swing.JRadioButton();
        ngaySinh1 = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        btnLamMoi2 = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKH = new javax.swing.JTable();
        GD_Phong = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        panelPhong = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        GD_PhieuDatPhong = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePhieu = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField10 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        txtTimPhieu = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        GD_HoaDon = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableHD = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        txtHD = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jSpinField1 = new com.toedter.components.JSpinField();
        jSpinField2 = new com.toedter.components.JSpinField();
        GD_DSPhong = new javax.swing.JPanel();
        panelNorth2 = new javax.swing.JPanel();
        lblMaDV1 = new javax.swing.JLabel();
        txtMaDV1 = new javax.swing.JTextField();
        lblDonGia1 = new javax.swing.JLabel();
        txtDonGia1 = new javax.swing.JTextField();
        lblTenDV1 = new javax.swing.JLabel();
        txtTenDV1 = new javax.swing.JTextField();
        lblHsd1 = new javax.swing.JLabel();
        lblXuatXu1 = new javax.swing.JLabel();
        comboXuatXu1 = new javax.swing.JComboBox<>();
        lblDonViBan1 = new javax.swing.JLabel();
        comboSucChua = new javax.swing.JComboBox<>();
        lblTinhTrang1 = new javax.swing.JLabel();
        radioDangBan1 = new javax.swing.JRadioButton();
        radioNgungBan1 = new javax.swing.JRadioButton();
        btnCapNhatDV1 = new javax.swing.JButton();
        btnLamMoi3 = new javax.swing.JButton();
        btnXoa3 = new javax.swing.JButton();
        btnTim2 = new javax.swing.JButton();
        txtTP = new javax.swing.JTextField();
        txtTenDV2 = new javax.swing.JTextField();
        panelCenter1 = new javax.swing.JPanel();
        scrollPane2 = new javax.swing.JScrollPane();
        tableDSPhong = new javax.swing.JTable();
        btnGenderNV = new javax.swing.ButtonGroup();
        btnGenderKH = new javax.swing.ButtonGroup();
        btnGTTDV = new javax.swing.ButtonGroup();
        btnGTTNV = new javax.swing.ButtonGroup();
        btnGPhong = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        GD_Chinh = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

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
        hsd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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
        btnGTTDV.add(radioDangBan);
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
        btnGTTDV.add(radioNgungBan);
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
        panelCenter.setMinimumSize(new java.awt.Dimension(1200, 160));
        panelCenter.setPreferredSize(new java.awt.Dimension(1200, 900));
        panelCenter.setLayout(new java.awt.BorderLayout());

        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        scrollPane.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        scrollPane.setMinimumSize(new java.awt.Dimension(1200, 160));
        scrollPane.setPreferredSize(new java.awt.Dimension(1200, 900));

        tableDichVu.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        tableDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Đơn vị bán", "Số lượng tồn", "Đơn giá", "Hạn sử dụng", "Xuất xứ", "Tình trạng"
            }
        ));
        tableDichVu.setMinimumSize(new java.awt.Dimension(1200, 300));
        tableDichVu.setPreferredSize(new java.awt.Dimension(1200, 900));
        tableDichVu.setRowHeight(25);
        tableDichVu.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tableDichVu.setShowHorizontalLines(true);
        tableDichVu.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(tableDichVu);
        if (tableDichVu.getColumnModel().getColumnCount() > 0) {
            tableDichVu.getColumnModel().getColumn(0).setResizable(false);
            tableDichVu.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableDichVu.getColumnModel().getColumn(1).setResizable(false);
            tableDichVu.getColumnModel().getColumn(1).setPreferredWidth(60);
            tableDichVu.getColumnModel().getColumn(2).setResizable(false);
            tableDichVu.getColumnModel().getColumn(2).setPreferredWidth(250);
            tableDichVu.getColumnModel().getColumn(3).setResizable(false);
            tableDichVu.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableDichVu.getColumnModel().getColumn(4).setResizable(false);
            tableDichVu.getColumnModel().getColumn(4).setPreferredWidth(60);
            tableDichVu.getColumnModel().getColumn(5).setResizable(false);
            tableDichVu.getColumnModel().getColumn(5).setPreferredWidth(60);
            tableDichVu.getColumnModel().getColumn(6).setResizable(false);
            tableDichVu.getColumnModel().getColumn(6).setPreferredWidth(60);
            tableDichVu.getColumnModel().getColumn(7).setResizable(false);
            tableDichVu.getColumnModel().getColumn(7).setPreferredWidth(60);
            tableDichVu.getColumnModel().getColumn(7).setHeaderValue("Tình trạng");
        }

        panelCenter.add(scrollPane, java.awt.BorderLayout.CENTER);

        GD_DichVu.add(panelCenter, java.awt.BorderLayout.CENTER);

        GD_NhanVien.setMaximumSize(new java.awt.Dimension(1200, 820));
        GD_NhanVien.setMinimumSize(new java.awt.Dimension(1200, 520));
        GD_NhanVien.setPreferredSize(new java.awt.Dimension(1200, 520));
        GD_NhanVien.setLayout(new java.awt.BorderLayout());

        panelNorth1.setBackground(new java.awt.Color(255, 255, 255));
        panelNorth1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        panelNorth1.setMaximumSize(new java.awt.Dimension(1200, 300));
        panelNorth1.setMinimumSize(new java.awt.Dimension(1200, 300));
        panelNorth1.setPreferredSize(new java.awt.Dimension(1200, 300));
        java.awt.GridBagLayout panelNorth1Layout = new java.awt.GridBagLayout();
        panelNorth1Layout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        panelNorth1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        panelNorth1.setLayout(panelNorth1Layout);

        lblMaNV.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblMaNV.setText("Mã nhân viên");
        lblMaNV.setMaximumSize(new java.awt.Dimension(110, 40));
        lblMaNV.setMinimumSize(new java.awt.Dimension(110, 40));
        lblMaNV.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(lblMaNV, gridBagConstraints);

        txtMaNV.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtMaNV.setText("NV001");
        txtMaNV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMaNV.setEnabled(false);
        txtMaNV.setMaximumSize(new java.awt.Dimension(150, 30));
        txtMaNV.setMinimumSize(new java.awt.Dimension(150, 30));
        txtMaNV.setPreferredSize(new java.awt.Dimension(185, 30));
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panelNorth1.add(txtMaNV, gridBagConstraints);

        lblCCCD.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblCCCD.setText("Căn cước công dân");
        lblCCCD.setMaximumSize(new java.awt.Dimension(155, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(lblCCCD, gridBagConstraints);

        txtCCCD.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtCCCD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCCCD.setMaximumSize(new java.awt.Dimension(185, 185));
        txtCCCD.setMinimumSize(new java.awt.Dimension(150, 30));
        txtCCCD.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        panelNorth1.add(txtCCCD, gridBagConstraints);

        lblHo.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblHo.setText("Họ nhân viên");
        lblHo.setMaximumSize(new java.awt.Dimension(110, 40));
        lblHo.setMinimumSize(new java.awt.Dimension(110, 40));
        lblHo.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(lblHo, gridBagConstraints);

        txtHo.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtHo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtHo.setMaximumSize(new java.awt.Dimension(150, 30));
        txtHo.setMinimumSize(new java.awt.Dimension(150, 30));
        txtHo.setPreferredSize(new java.awt.Dimension(185, 30));
        txtHo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        panelNorth1.add(txtHo, gridBagConstraints);

        lblTen.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblTen.setText("Tên nhân viên");
        lblTen.setMaximumSize(new java.awt.Dimension(110, 40));
        lblTen.setMinimumSize(new java.awt.Dimension(110, 40));
        lblTen.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(lblTen, gridBagConstraints);

        txtTen.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTen.setMaximumSize(new java.awt.Dimension(185, 185));
        txtTen.setMinimumSize(new java.awt.Dimension(150, 30));
        txtTen.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        panelNorth1.add(txtTen, gridBagConstraints);

        lblNgaySinh.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblNgaySinh.setText("Ngày sinh");
        lblNgaySinh.setMaximumSize(new java.awt.Dimension(110, 40));
        lblNgaySinh.setMinimumSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(lblNgaySinh, gridBagConstraints);

        ngaySinh.setBackground(new java.awt.Color(255, 255, 255));
        ngaySinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ngaySinh.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        ngaySinh.setMaximumSize(new java.awt.Dimension(250, 30));
        ngaySinh.setMinimumSize(new java.awt.Dimension(250, 30));
        ngaySinh.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        panelNorth1.add(ngaySinh, gridBagConstraints);

        lblGioiTinh.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblGioiTinh.setText("Giới tính");
        lblGioiTinh.setMaximumSize(new java.awt.Dimension(110, 40));
        lblGioiTinh.setMinimumSize(new java.awt.Dimension(110, 40));
        lblGioiTinh.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(lblGioiTinh, gridBagConstraints);

        radioNam.setBackground(new java.awt.Color(255, 255, 255));
        btnGenderNV.add(radioNam);
        radioNam.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        radioNam.setText("Nam");
        radioNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNamActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(radioNam, gridBagConstraints);

        radioNu.setBackground(new java.awt.Color(255, 255, 255));
        btnGenderNV.add(radioNu);
        radioNu.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        radioNu.setText("Nữ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        panelNorth1.add(radioNu, gridBagConstraints);

        lblSDT.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblSDT.setText("Số điện thoại");
        lblSDT.setMaximumSize(new java.awt.Dimension(110, 40));
        lblSDT.setMinimumSize(new java.awt.Dimension(110, 40));
        lblSDT.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(lblSDT, gridBagConstraints);

        txtSDT.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtSDT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSDT.setMaximumSize(new java.awt.Dimension(150, 30));
        txtSDT.setMinimumSize(new java.awt.Dimension(150, 30));
        txtSDT.setPreferredSize(new java.awt.Dimension(185, 30));
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        panelNorth1.add(txtSDT, gridBagConstraints);

        lblEmail.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblEmail.setText("Email");
        lblEmail.setMaximumSize(new java.awt.Dimension(110, 40));
        lblEmail.setMinimumSize(new java.awt.Dimension(110, 40));
        lblEmail.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(lblEmail, gridBagConstraints);

        txtEmail.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtEmail.setMaximumSize(new java.awt.Dimension(250, 30));
        txtEmail.setMinimumSize(new java.awt.Dimension(250, 30));
        txtEmail.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        panelNorth1.add(txtEmail, gridBagConstraints);

        lblDiaChi.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblDiaChi.setText("Địa chỉ");
        lblDiaChi.setMaximumSize(new java.awt.Dimension(110, 40));
        lblDiaChi.setMinimumSize(new java.awt.Dimension(110, 40));
        lblDiaChi.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(lblDiaChi, gridBagConstraints);

        txtDiaChi.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDiaChi.setMaximumSize(new java.awt.Dimension(250, 30));
        txtDiaChi.setMinimumSize(new java.awt.Dimension(250, 30));
        txtDiaChi.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        panelNorth1.add(txtDiaChi, gridBagConstraints);

        txtChucVu.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtChucVu.setText("Chức vụ");
        txtChucVu.setMaximumSize(new java.awt.Dimension(110, 40));
        txtChucVu.setMinimumSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(txtChucVu, gridBagConstraints);

        comboChucVu.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        comboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Lễ tân", "Bảo vệ", "Phục vụ" }));
        comboChucVu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        comboChucVu.setMaximumSize(new java.awt.Dimension(150, 30));
        comboChucVu.setMinimumSize(new java.awt.Dimension(150, 30));
        comboChucVu.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        panelNorth1.add(comboChucVu, gridBagConstraints);

        lblMatKhau.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblMatKhau.setText("Mật khẩu");
        lblMatKhau.setMaximumSize(new java.awt.Dimension(110, 40));
        lblMatKhau.setMinimumSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(lblMatKhau, gridBagConstraints);

        pwMatKhau.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        pwMatKhau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pwMatKhau.setMaximumSize(new java.awt.Dimension(185, 185));
        pwMatKhau.setMinimumSize(new java.awt.Dimension(150, 30));
        pwMatKhau.setPreferredSize(new java.awt.Dimension(185, 30));
        pwMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwMatKhauActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        panelNorth1.add(pwMatKhau, gridBagConstraints);

        lblTrangThai.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblTrangThai.setText("Trạng thái");
        lblTrangThai.setMaximumSize(new java.awt.Dimension(110, 40));
        lblTrangThai.setMinimumSize(new java.awt.Dimension(110, 40));
        lblTrangThai.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(lblTrangThai, gridBagConstraints);

        radioDangLam.setBackground(new java.awt.Color(255, 255, 255));
        btnGTTNV.add(radioDangLam);
        radioDangLam.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        radioDangLam.setText("Đang làm");
        radioDangLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDangLamActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(radioDangLam, gridBagConstraints);

        radioDaNghi.setBackground(new java.awt.Color(255, 255, 255));
        btnGTTNV.add(radioDaNghi);
        radioDaNghi.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        radioDaNghi.setText("Đã nghỉ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        panelNorth1.add(radioDaNghi, gridBagConstraints);

        btnThem.setBackground(new java.awt.Color(51, 102, 255));
        btnThem.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add-user.png"))); // NOI18N
        btnThem.setText("Thêm nhân viên");
        btnThem.setMaximumSize(new java.awt.Dimension(180, 40));
        btnThem.setMinimumSize(new java.awt.Dimension(180, 30));
        btnThem.setPreferredSize(new java.awt.Dimension(180, 30));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(btnThem, gridBagConstraints);

        btnCapNhat.setBackground(new java.awt.Color(102, 255, 51));
        btnCapNhat.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/system-update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật thông tin");
        btnCapNhat.setMaximumSize(new java.awt.Dimension(190, 40));
        btnCapNhat.setMinimumSize(new java.awt.Dimension(190, 30));
        btnCapNhat.setPreferredSize(new java.awt.Dimension(190, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        panelNorth1.add(btnCapNhat, gridBagConstraints);

        btnTim1.setBackground(new java.awt.Color(121, 188, 215));
        btnTim1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnTim1.setForeground(new java.awt.Color(255, 255, 255));
        btnTim1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search-user.png"))); // NOI18N
        btnTim1.setText("Tìm");
        btnTim1.setMaximumSize(new java.awt.Dimension(90, 40));
        btnTim1.setMinimumSize(new java.awt.Dimension(90, 30));
        btnTim1.setPreferredSize(new java.awt.Dimension(90, 30));
        btnTim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTim1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(btnTim1, gridBagConstraints);

        txtTim1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTim1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTim1.setMaximumSize(new java.awt.Dimension(150, 30));
        txtTim1.setMinimumSize(new java.awt.Dimension(150, 30));
        txtTim1.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        panelNorth1.add(txtTim1, gridBagConstraints);

        btnLamMoi1.setBackground(new java.awt.Color(0, 255, 153));
        btnLamMoi1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnLamMoi1.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        btnLamMoi1.setText("Làm mới");
        btnLamMoi1.setMaximumSize(new java.awt.Dimension(70, 40));
        btnLamMoi1.setMinimumSize(new java.awt.Dimension(70, 30));
        btnLamMoi1.setPreferredSize(new java.awt.Dimension(130, 30));
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(btnLamMoi1, gridBagConstraints);

        btnXoa1.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnXoa1.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/eraser.png"))); // NOI18N
        btnXoa1.setText("Xóa trắng");
        btnXoa1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnXoa1.setMaximumSize(new java.awt.Dimension(120, 40));
        btnXoa1.setMinimumSize(new java.awt.Dimension(70, 30));
        btnXoa1.setPreferredSize(new java.awt.Dimension(130, 30));
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth1.add(btnXoa1, gridBagConstraints);

        GD_NhanVien.add(panelNorth1, java.awt.BorderLayout.PAGE_START);

        panelDSNV.setBackground(new java.awt.Color(255, 255, 255));
        panelDSNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        panelDSNV.setMaximumSize(new java.awt.Dimension(1200, 220));
        panelDSNV.setMinimumSize(new java.awt.Dimension(1200, 220));
        panelDSNV.setPreferredSize(new java.awt.Dimension(1200, 220));
        panelDSNV.setLayout(new java.awt.BorderLayout());

        scrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N

        tableDSNV.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        tableDSNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "CCCD", "Họ", "Tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Email", "Địa chỉ", "Chức vụ", "Mật khẩu", "Trạng thái"
            }
        ));
        tableDSNV.setPreferredSize(new java.awt.Dimension(1200, 220));
        tableDSNV.setRowHeight(25);
        tableDSNV.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tableDSNV.setShowHorizontalLines(true);
        tableDSNV.getTableHeader().setReorderingAllowed(false);
        scrollPane1.setViewportView(tableDSNV);
        if (tableDSNV.getColumnModel().getColumnCount() > 0) {
            tableDSNV.getColumnModel().getColumn(0).setResizable(false);
            tableDSNV.getColumnModel().getColumn(0).setPreferredWidth(15);
            tableDSNV.getColumnModel().getColumn(1).setResizable(false);
            tableDSNV.getColumnModel().getColumn(1).setPreferredWidth(20);
            tableDSNV.getColumnModel().getColumn(2).setResizable(false);
            tableDSNV.getColumnModel().getColumn(2).setPreferredWidth(50);
            tableDSNV.getColumnModel().getColumn(3).setResizable(false);
            tableDSNV.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableDSNV.getColumnModel().getColumn(4).setResizable(false);
            tableDSNV.getColumnModel().getColumn(5).setResizable(false);
            tableDSNV.getColumnModel().getColumn(5).setPreferredWidth(20);
            tableDSNV.getColumnModel().getColumn(6).setResizable(false);
            tableDSNV.getColumnModel().getColumn(7).setResizable(false);
            tableDSNV.getColumnModel().getColumn(7).setPreferredWidth(0);
            tableDSNV.getColumnModel().getColumn(8).setResizable(false);
            tableDSNV.getColumnModel().getColumn(8).setPreferredWidth(0);
            tableDSNV.getColumnModel().getColumn(9).setResizable(false);
            tableDSNV.getColumnModel().getColumn(9).setPreferredWidth(20);
            tableDSNV.getColumnModel().getColumn(10).setResizable(false);
            tableDSNV.getColumnModel().getColumn(10).setPreferredWidth(20);
            tableDSNV.getColumnModel().getColumn(11).setResizable(false);
            tableDSNV.getColumnModel().getColumn(11).setPreferredWidth(20);
        }

        panelDSNV.add(scrollPane1, java.awt.BorderLayout.CENTER);

        GD_NhanVien.add(panelDSNV, java.awt.BorderLayout.CENTER);

        GD_KhachHang.setBackground(new java.awt.Color(255, 255, 255));
        GD_KhachHang.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        GD_KhachHang.setMaximumSize(new java.awt.Dimension(1200, 520));
        GD_KhachHang.setMinimumSize(new java.awt.Dimension(1200, 520));
        GD_KhachHang.setPreferredSize(new java.awt.Dimension(1200, 520));
        GD_KhachHang.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(1200, 300));
        jPanel3.setMinimumSize(new java.awt.Dimension(1200, 300));
        jPanel3.setPreferredSize(new java.awt.Dimension(1200, 300));
        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0};
        jPanel3Layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        jPanel3.setLayout(jPanel3Layout);

        jLabel4.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel4.setText("Mã khách hàng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel3.add(jLabel4, gridBagConstraints);

        jTextField1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField1.setEnabled(false);
        jTextField1.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField1.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel3.add(jTextField1, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel5.setText("Họ khách hàng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel3.add(jLabel5, gridBagConstraints);

        jTextField2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField2.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField2.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel3.add(jTextField2, gridBagConstraints);

        jTextField3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField3.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField3.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        jPanel3.add(jTextField3, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel7.setText("Số điện thoại");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel7, gridBagConstraints);

        jTextField4.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField4.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField4.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        jPanel3.add(jTextField4, gridBagConstraints);

        jButton2.setBackground(new java.awt.Color(121, 188, 215));
        jButton2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search-user.png"))); // NOI18N
        jButton2.setText("Tìm");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jButton2, gridBagConstraints);

        jButton3.setBackground(new java.awt.Color(102, 255, 51));
        jButton3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/system-update.png"))); // NOI18N
        jButton3.setText("Cập nhật thông tin");
        jButton3.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        jPanel3.add(jButton3, gridBagConstraints);

        jButton4.setBackground(new java.awt.Color(61, 102, 255));
        jButton4.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add-user.png"))); // NOI18N
        jButton4.setText("Thêm khách hàng");
        jButton4.setPreferredSize(new java.awt.Dimension(200, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        jPanel3.add(jButton4, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel8.setText("Tên khách hàng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel9.setText("CCCD");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel9, gridBagConstraints);

        txtTimKH.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTimKH.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        jPanel3.add(txtTimKH, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel10.setText("Ngày sinh");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel10, gridBagConstraints);

        jTextField6.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField6.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        jPanel3.add(jTextField6, gridBagConstraints);

        radioNu1.setBackground(new java.awt.Color(255, 255, 255));
        btnGenderNV.add(radioNu1);
        radioNu1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        radioNu1.setText("Nữ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        jPanel3.add(radioNu1, gridBagConstraints);

        radioNam1.setBackground(new java.awt.Color(255, 255, 255));
        btnGenderNV.add(radioNam1);
        radioNam1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        radioNam1.setText("Nam");
        radioNam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNam1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(radioNam1, gridBagConstraints);

        ngaySinh1.setBackground(new java.awt.Color(255, 255, 255));
        ngaySinh1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ngaySinh1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        ngaySinh1.setMaximumSize(new java.awt.Dimension(250, 30));
        ngaySinh1.setMinimumSize(new java.awt.Dimension(250, 30));
        ngaySinh1.setPreferredSize(new java.awt.Dimension(185, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        jPanel3.add(ngaySinh1, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel11.setText("Giới tính");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(jLabel11, gridBagConstraints);

        btnLamMoi2.setBackground(new java.awt.Color(0, 255, 153));
        btnLamMoi2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnLamMoi2.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        btnLamMoi2.setText("Làm mới");
        btnLamMoi2.setMaximumSize(new java.awt.Dimension(70, 40));
        btnLamMoi2.setMinimumSize(new java.awt.Dimension(70, 30));
        btnLamMoi2.setPreferredSize(new java.awt.Dimension(130, 30));
        btnLamMoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(btnLamMoi2, gridBagConstraints);

        btnXoa2.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnXoa2.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/eraser.png"))); // NOI18N
        btnXoa2.setText("Xóa trắng");
        btnXoa2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnXoa2.setMaximumSize(new java.awt.Dimension(120, 40));
        btnXoa2.setMinimumSize(new java.awt.Dimension(70, 30));
        btnXoa2.setPreferredSize(new java.awt.Dimension(130, 30));
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(btnXoa2, gridBagConstraints);

        GD_KhachHang.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jPanel7.setMinimumSize(new java.awt.Dimension(1200, 220));
        jPanel7.setPreferredSize(new java.awt.Dimension(1200, 220));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        jScrollPane1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1200, 600));

        tableKH.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        tableKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "CCCD", "Họ", "Tên", "Ngày sinh", "Giới tính", "Số điện thoại"
            }
        ));
        tableKH.setPreferredSize(new java.awt.Dimension(1200, 600));
        tableKH.setRowHeight(25);
        jScrollPane1.setViewportView(tableKH);

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        GD_KhachHang.add(jPanel7, java.awt.BorderLayout.CENTER);

        GD_Phong.setBackground(new java.awt.Color(121, 188, 215));
        GD_Phong.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        GD_Phong.setMinimumSize(new java.awt.Dimension(1200, 520));
        GD_Phong.setPreferredSize(new java.awt.Dimension(1200, 520));
        GD_Phong.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jPanel13.setPreferredSize(new java.awt.Dimension(1200, 80));
        java.awt.GridBagLayout jPanel13Layout = new java.awt.GridBagLayout();
        jPanel13Layout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0};
        jPanel13Layout.rowHeights = new int[] {0, 5, 0};
        jPanel13.setLayout(jPanel13Layout);

        jLabel16.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel16.setText("Trạng thái phòng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jLabel16, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel17.setText("Loại phòng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel13.add(jLabel17, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel18.setText("Mã phòng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jLabel18, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel19.setText("Số người");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel13.add(jLabel19, gridBagConstraints);

        jComboBox1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang sử dụng", "Đã đặt trước", "Trống" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jComboBox1, gridBagConstraints);

        jComboBox2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Phòng thường", "Phòng VIP" }));
        jComboBox2.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel13.add(jComboBox2, gridBagConstraints);

        jComboBox3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "5", "10" }));
        jComboBox3.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        jPanel13.add(jComboBox3, gridBagConstraints);

        jTextField5.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField5.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jTextField5, gridBagConstraints);

        jButton12.setBackground(new java.awt.Color(121, 188, 215));
        jButton12.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Tìm");
        jButton12.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jButton12, gridBagConstraints);

        jButton13.setBackground(new java.awt.Color(0, 255, 153));
        jButton13.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Làm mới");
        jButton13.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        jPanel13.add(jButton13, gridBagConstraints);

        GD_Phong.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        jPanel9.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jPanel9.setMinimumSize(new java.awt.Dimension(1200, 80));
        jPanel9.setPreferredSize(new java.awt.Dimension(1200, 80));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jPanel10.setPreferredSize(new java.awt.Dimension(1200, 50));
        java.awt.GridBagLayout jPanel10Layout = new java.awt.GridBagLayout();
        jPanel10Layout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0, 20, 0};
        jPanel10Layout.rowHeights = new int[] {0};
        jPanel10.setLayout(jPanel10Layout);

        jLabel12.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/phongV.png"))); // NOI18N
        jLabel12.setText("Phòng VIP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        jPanel10.add(jLabel12, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/phongT.png"))); // NOI18N
        jLabel6.setText("Phòng thường");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        jPanel10.add(jLabel6, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pDSD.png"))); // NOI18N
        jLabel13.setText("Đang sử dụng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel10.add(jLabel13, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pDDT.png"))); // NOI18N
        jLabel14.setText("Đã đặt trước");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel10.add(jLabel14, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pT.png"))); // NOI18N
        jLabel15.setText("Phòng trống");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel10.add(jLabel15, gridBagConstraints);

        jPanel9.add(jPanel10, java.awt.BorderLayout.CENTER);

        GD_Phong.add(jPanel9, java.awt.BorderLayout.PAGE_END);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setMinimumSize(new java.awt.Dimension(970, 370));
        jPanel14.setLayout(new java.awt.BorderLayout());

        java.awt.GridBagLayout jPanel15Layout = new java.awt.GridBagLayout();
        jPanel15Layout.columnWidths = new int[] {0};
        jPanel15Layout.rowHeights = new int[] {0};
        panelPhong.setLayout(jPanel15Layout);
        jPanel14.add(panelPhong, java.awt.BorderLayout.CENTER);

        GD_Phong.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(121, 188, 215));
        jPanel8.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        jPanel8.setMinimumSize(new java.awt.Dimension(220, 320));
        jPanel8.setPreferredSize(new java.awt.Dimension(220, 320));
        jPanel8.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setMaximumSize(new java.awt.Dimension(100, 32767));
        jPanel11.setMinimumSize(new java.awt.Dimension(220, 200));
        java.awt.GridBagLayout jPanel11Layout = new java.awt.GridBagLayout();
        jPanel11Layout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        jPanel11Layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        jPanel11.setLayout(jPanel11Layout);

        jButton5.setBackground(new java.awt.Color(64, 71, 214));
        jButton5.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Xem chi tiết (F1)");
        jButton5.setToolTipText("");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setMaximumSize(new java.awt.Dimension(200, 30));
        jButton5.setMinimumSize(new java.awt.Dimension(200, 30));
        jButton5.setPreferredSize(new java.awt.Dimension(200, 30));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(jButton5, gridBagConstraints);

        jButton6.setBackground(new java.awt.Color(64, 71, 214));
        jButton6.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Đặt trước phòng (F2)");
        jButton6.setMaximumSize(new java.awt.Dimension(200, 30));
        jButton6.setMinimumSize(new java.awt.Dimension(200, 30));
        jButton6.setPreferredSize(new java.awt.Dimension(200, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(jButton6, gridBagConstraints);

        jButton7.setBackground(new java.awt.Color(64, 71, 214));
        jButton7.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Hủy đặt phòng (F3)");
        jButton7.setMaximumSize(new java.awt.Dimension(200, 30));
        jButton7.setMinimumSize(new java.awt.Dimension(200, 30));
        jButton7.setPreferredSize(new java.awt.Dimension(200, 30));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(jButton7, gridBagConstraints);

        jButton8.setBackground(new java.awt.Color(64, 71, 214));
        jButton8.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Nhận phòng (F4)");
        jButton8.setMaximumSize(new java.awt.Dimension(200, 30));
        jButton8.setMinimumSize(new java.awt.Dimension(200, 30));
        jButton8.setPreferredSize(new java.awt.Dimension(200, 30));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(jButton8, gridBagConstraints);

        jButton9.setBackground(new java.awt.Color(64, 71, 214));
        jButton9.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Trả phòng (F5)");
        jButton9.setMaximumSize(new java.awt.Dimension(200, 30));
        jButton9.setMinimumSize(new java.awt.Dimension(200, 30));
        jButton9.setPreferredSize(new java.awt.Dimension(200, 30));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(jButton9, gridBagConstraints);

        jButton10.setBackground(new java.awt.Color(64, 71, 214));
        jButton10.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Thêm dịch vụ (F6)");
        jButton10.setMaximumSize(new java.awt.Dimension(200, 30));
        jButton10.setMinimumSize(new java.awt.Dimension(200, 30));
        jButton10.setPreferredSize(new java.awt.Dimension(200, 30));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(jButton10, gridBagConstraints);

        jButton11.setBackground(new java.awt.Color(64, 71, 214));
        jButton11.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Cập nhật dịch vụ (F7)");
        jButton11.setMaximumSize(new java.awt.Dimension(200, 30));
        jButton11.setMinimumSize(new java.awt.Dimension(200, 30));
        jButton11.setPreferredSize(new java.awt.Dimension(200, 30));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(jButton11, gridBagConstraints);

        jPanel8.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jPanel12.setMinimumSize(new java.awt.Dimension(220, 100));
        jPanel12.setPreferredSize(new java.awt.Dimension(220, 50));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        GD_Phong.add(jPanel8, java.awt.BorderLayout.WEST);

        GD_PhieuDatPhong.setBackground(new java.awt.Color(255, 255, 255));
        GD_PhieuDatPhong.setPreferredSize(new java.awt.Dimension(1200, 520));
        GD_PhieuDatPhong.setLayout(new java.awt.BorderLayout());

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phiếu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        jPanel16.setMinimumSize(new java.awt.Dimension(1200, 220));
        jPanel16.setPreferredSize(new java.awt.Dimension(1200, 220));
        jPanel16.setLayout(new java.awt.BorderLayout());

        tablePhieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu", "Tên khách hàng", "Số điện thoại", "Thời gian đặt", "Thời gian nhận", "Tên nhân viên", "Trạng thái phiếu"
            }
        ));
        tablePhieu.setRowHeight(25);
        jScrollPane2.setViewportView(tablePhieu);

        jPanel16.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        GD_PhieuDatPhong.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin phiếu đặt phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        jPanel15.setMinimumSize(new java.awt.Dimension(1200, 300));
        jPanel15.setPreferredSize(new java.awt.Dimension(1200, 300));
        java.awt.GridBagLayout jPanel15Layout1 = new java.awt.GridBagLayout();
        jPanel15Layout1.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        jPanel15Layout1.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0};
        jPanel15.setLayout(jPanel15Layout1);

        jComboBox4.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ nhận phòng", "Đã nhận" }));
        jComboBox4.setMinimumSize(new java.awt.Dimension(200, 30));
        jComboBox4.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel15.add(jComboBox4, gridBagConstraints);

        jTextField10.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField10.setEnabled(false);
        jTextField10.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField10.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel15.add(jTextField10, gridBagConstraints);

        jTextField9.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField9.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField9.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 2;
        jPanel15.add(jTextField9, gridBagConstraints);

        txtTimPhieu.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTimPhieu.setMinimumSize(new java.awt.Dimension(200, 30));
        txtTimPhieu.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        jPanel15.add(txtTimPhieu, gridBagConstraints);

        jTextField7.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField7.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField7.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        jPanel15.add(jTextField7, gridBagConstraints);

        jLabel26.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel26.setText("Trạng thái phiếu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(jLabel26, gridBagConstraints);

        jLabel25.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel25.setText("Thời gian nhận");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(jLabel25, gridBagConstraints);

        jLabel24.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel24.setText("Thời gian đặt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(jLabel24, gridBagConstraints);

        jLabel23.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel23.setText("Mã phiếu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(jLabel23, gridBagConstraints);

        jLabel22.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel22.setText("Tên nhân viên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(jLabel22, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel21.setText("Tên khách hàng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(jLabel21, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel20.setText("Số điện thoại");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(jLabel20, gridBagConstraints);

        jDateChooser1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jDateChooser1.setMinimumSize(new java.awt.Dimension(200, 30));
        jDateChooser1.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        jPanel15.add(jDateChooser1, gridBagConstraints);

        jDateChooser2.setEnabled(false);
        jDateChooser2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jDateChooser2.setMinimumSize(new java.awt.Dimension(200, 30));
        jDateChooser2.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel15.add(jDateChooser2, gridBagConstraints);

        jButton14.setBackground(new java.awt.Color(121, 188, 215));
        jButton14.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search-doc.png"))); // NOI18N
        jButton14.setText("Tìm");
        jButton14.setMinimumSize(new java.awt.Dimension(100, 30));
        jButton14.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(jButton14, gridBagConstraints);

        jButton15.setBackground(new java.awt.Color(0, 204, 204));
        jButton15.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/paper.png"))); // NOI18N
        jButton15.setText("Xem chi tiết");
        jButton15.setMinimumSize(new java.awt.Dimension(150, 30));
        jButton15.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(jButton15, gridBagConstraints);

        jButton16.setBackground(new java.awt.Color(0, 255, 213));
        jButton16.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        jButton16.setText("Làm mới");
        jButton16.setMinimumSize(new java.awt.Dimension(120, 30));
        jButton16.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(jButton16, gridBagConstraints);

        jTextField11.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField11.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField11.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        jPanel15.add(jTextField11, gridBagConstraints);

        jButton20.setBackground(new java.awt.Color(102, 255, 51));
        jButton20.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/system-update.png"))); // NOI18N
        jButton20.setText("Cập nhật");
        jButton20.setMinimumSize(new java.awt.Dimension(120, 30));
        jButton20.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        jPanel15.add(jButton20, gridBagConstraints);

        GD_PhieuDatPhong.add(jPanel15, java.awt.BorderLayout.PAGE_START);

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
        ));
        tableHD.setRowHeight(25);
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

        jTextField12.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField12.setEnabled(false);
        jTextField12.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField12.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel18.add(jTextField12, gridBagConstraints);

        jTextField13.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField13.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField13.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 2;
        jPanel18.add(jTextField13, gridBagConstraints);

        jTextField8.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField8.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField8.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        jPanel18.add(jTextField8, gridBagConstraints);

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

        jDateChooser4.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jDateChooser4.setMinimumSize(new java.awt.Dimension(200, 30));
        jDateChooser4.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        jPanel18.add(jDateChooser4, gridBagConstraints);

        jButton17.setBackground(new java.awt.Color(121, 188, 215));
        jButton17.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search-doc.png"))); // NOI18N
        jButton17.setText("Tìm");
        jButton17.setMinimumSize(new java.awt.Dimension(100, 30));
        jButton17.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(jButton17, gridBagConstraints);

        jButton18.setBackground(new java.awt.Color(0, 204, 204));
        jButton18.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/paper.png"))); // NOI18N
        jButton18.setText("Xem chi tiết");
        jButton18.setMinimumSize(new java.awt.Dimension(150, 30));
        jButton18.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(jButton18, gridBagConstraints);

        jButton19.setBackground(new java.awt.Color(0, 255, 213));
        jButton19.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        jButton19.setText("Làm mới");
        jButton19.setMinimumSize(new java.awt.Dimension(15, 30));
        jButton19.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(jButton19, gridBagConstraints);

        txtHD.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtHD.setMinimumSize(new java.awt.Dimension(200, 30));
        txtHD.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        jPanel18.add(txtHD, gridBagConstraints);

        jTextField15.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jTextField15.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField15.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 0;
        jPanel18.add(jTextField15, gridBagConstraints);

        jLabel27.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel27.setText("Giờ lập");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(jLabel27, gridBagConstraints);

        jSpinField1.setMaximum(22);
        jSpinField1.setMinimum(8);
        jSpinField1.setPreferredSize(new java.awt.Dimension(90, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel18.add(jSpinField1, gridBagConstraints);

        jSpinField2.setMaximum(59);
        jSpinField2.setMaximumSize(new java.awt.Dimension(0, 0));
        jSpinField2.setMinimum(0);
        jSpinField2.setPreferredSize(new java.awt.Dimension(90, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel18.add(jSpinField2, gridBagConstraints);

        GD_HoaDon.add(jPanel18, java.awt.BorderLayout.PAGE_START);

        GD_DSPhong.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        GD_DSPhong.setMinimumSize(new java.awt.Dimension(1200, 520));
        GD_DSPhong.setPreferredSize(new java.awt.Dimension(1200, 520));
        GD_DSPhong.setLayout(new java.awt.BorderLayout());

        panelNorth2.setBackground(new java.awt.Color(255, 255, 255));
        panelNorth2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        panelNorth2.setMinimumSize(new java.awt.Dimension(1200, 300));
        panelNorth2.setPreferredSize(new java.awt.Dimension(1200, 300));
        java.awt.GridBagLayout panelNorth2Layout = new java.awt.GridBagLayout();
        panelNorth2Layout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0};
        panelNorth2Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        panelNorth2.setLayout(panelNorth2Layout);

        lblMaDV1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblMaDV1.setText("Mã phòng");
        lblMaDV1.setMaximumSize(new java.awt.Dimension(110, 40));
        lblMaDV1.setMinimumSize(new java.awt.Dimension(110, 40));
        lblMaDV1.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth2.add(lblMaDV1, gridBagConstraints);

        txtMaDV1.setBackground(new java.awt.Color(242, 242, 242));
        txtMaDV1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtMaDV1.setText("DVB001");
        txtMaDV1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMaDV1.setEnabled(false);
        txtMaDV1.setMaximumSize(new java.awt.Dimension(250, 30));
        txtMaDV1.setMinimumSize(new java.awt.Dimension(250, 30));
        txtMaDV1.setPreferredSize(new java.awt.Dimension(200, 30));
        txtMaDV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDV1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        panelNorth2.add(txtMaDV1, gridBagConstraints);

        lblDonGia1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblDonGia1.setText("Đơn giá");
        lblDonGia1.setMaximumSize(new java.awt.Dimension(155, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth2.add(lblDonGia1, gridBagConstraints);

        txtDonGia1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtDonGia1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDonGia1.setMaximumSize(new java.awt.Dimension(250, 30));
        txtDonGia1.setMinimumSize(new java.awt.Dimension(250, 30));
        txtDonGia1.setPreferredSize(new java.awt.Dimension(200, 30));
        txtDonGia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGia1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        panelNorth2.add(txtDonGia1, gridBagConstraints);

        lblTenDV1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblTenDV1.setText("Mã loại phòng");
        lblTenDV1.setMaximumSize(new java.awt.Dimension(110, 40));
        lblTenDV1.setMinimumSize(new java.awt.Dimension(110, 40));
        lblTenDV1.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth2.add(lblTenDV1, gridBagConstraints);

        txtTenDV1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTenDV1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTenDV1.setMaximumSize(new java.awt.Dimension(250, 30));
        txtTenDV1.setMinimumSize(new java.awt.Dimension(250, 30));
        txtTenDV1.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        panelNorth2.add(txtTenDV1, gridBagConstraints);

        lblHsd1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblHsd1.setText("Tên loại phòng");
        lblHsd1.setMaximumSize(new java.awt.Dimension(110, 40));
        lblHsd1.setMinimumSize(new java.awt.Dimension(110, 40));
        lblHsd1.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth2.add(lblHsd1, gridBagConstraints);

        lblXuatXu1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblXuatXu1.setText("Tình trạng");
        lblXuatXu1.setMaximumSize(new java.awt.Dimension(110, 40));
        lblXuatXu1.setMinimumSize(new java.awt.Dimension(110, 40));
        lblXuatXu1.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth2.add(lblXuatXu1, gridBagConstraints);

        comboXuatXu1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        comboXuatXu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trống", "Đã được đặt", "Đang sử dụng" }));
        comboXuatXu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        comboXuatXu1.setMaximumSize(new java.awt.Dimension(250, 30));
        comboXuatXu1.setMinimumSize(new java.awt.Dimension(250, 30));
        comboXuatXu1.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        panelNorth2.add(comboXuatXu1, gridBagConstraints);

        lblDonViBan1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblDonViBan1.setText("Sức chứa");
        lblDonViBan1.setMaximumSize(new java.awt.Dimension(110, 40));
        lblDonViBan1.setMinimumSize(new java.awt.Dimension(110, 40));
        lblDonViBan1.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth2.add(lblDonViBan1, gridBagConstraints);

        comboSucChua.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        comboSucChua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "15", "20" }));
        comboSucChua.setMaximumSize(new java.awt.Dimension(250, 30));
        comboSucChua.setMinimumSize(new java.awt.Dimension(250, 30));
        comboSucChua.setPreferredSize(new java.awt.Dimension(200, 30));
        comboSucChua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSucChuaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        panelNorth2.add(comboSucChua, gridBagConstraints);

        lblTinhTrang1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        lblTinhTrang1.setText("Trạng thái");
        lblTinhTrang1.setMaximumSize(new java.awt.Dimension(110, 40));
        lblTinhTrang1.setMinimumSize(new java.awt.Dimension(110, 40));
        lblTinhTrang1.setPreferredSize(new java.awt.Dimension(110, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth2.add(lblTinhTrang1, gridBagConstraints);

        radioDangBan1.setBackground(new java.awt.Color(255, 255, 255));
        btnGPhong.add(radioDangBan1);
        radioDangBan1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        radioDangBan1.setText("Bảo trì");
        radioDangBan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDangBan1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth2.add(radioDangBan1, gridBagConstraints);

        radioNgungBan1.setBackground(new java.awt.Color(255, 255, 255));
        btnGPhong.add(radioNgungBan1);
        radioNgungBan1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        radioNgungBan1.setText("Sẵn sàng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        panelNorth2.add(radioNgungBan1, gridBagConstraints);

        btnCapNhatDV1.setBackground(new java.awt.Color(102, 255, 51));
        btnCapNhatDV1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnCapNhatDV1.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatDV1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/system-update.png"))); // NOI18N
        btnCapNhatDV1.setText("Cập nhật thông tin");
        btnCapNhatDV1.setMaximumSize(new java.awt.Dimension(210, 40));
        btnCapNhatDV1.setMinimumSize(new java.awt.Dimension(210, 30));
        btnCapNhatDV1.setPreferredSize(new java.awt.Dimension(210, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth2.add(btnCapNhatDV1, gridBagConstraints);

        btnLamMoi3.setBackground(new java.awt.Color(0, 255, 153));
        btnLamMoi3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnLamMoi3.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        btnLamMoi3.setText("Làm mới");
        btnLamMoi3.setMaximumSize(new java.awt.Dimension(135, 40));
        btnLamMoi3.setMinimumSize(new java.awt.Dimension(135, 30));
        btnLamMoi3.setPreferredSize(new java.awt.Dimension(130, 30));
        btnLamMoi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 16;
        panelNorth2.add(btnLamMoi3, gridBagConstraints);

        btnXoa3.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnXoa3.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/eraser.png"))); // NOI18N
        btnXoa3.setText("Xóa trắng");
        btnXoa3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnXoa3.setMaximumSize(new java.awt.Dimension(135, 40));
        btnXoa3.setMinimumSize(new java.awt.Dimension(135, 30));
        btnXoa3.setPreferredSize(new java.awt.Dimension(120, 30));
        btnXoa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        panelNorth2.add(btnXoa3, gridBagConstraints);

        btnTim2.setBackground(new java.awt.Color(121, 188, 215));
        btnTim2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnTim2.setForeground(new java.awt.Color(255, 255, 255));
        btnTim2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search-user.png"))); // NOI18N
        btnTim2.setText("Tìm");
        btnTim2.setMaximumSize(new java.awt.Dimension(90, 30));
        btnTim2.setMinimumSize(new java.awt.Dimension(90, 30));
        btnTim2.setPreferredSize(new java.awt.Dimension(135, 30));
        btnTim2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTim2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panelNorth2.add(btnTim2, gridBagConstraints);

        txtTP.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTP.setMaximumSize(new java.awt.Dimension(250, 30));
        txtTP.setMinimumSize(new java.awt.Dimension(250, 30));
        txtTP.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 16;
        panelNorth2.add(txtTP, gridBagConstraints);

        txtTenDV2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTenDV2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTenDV2.setMaximumSize(new java.awt.Dimension(250, 30));
        txtTenDV2.setMinimumSize(new java.awt.Dimension(250, 30));
        txtTenDV2.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        panelNorth2.add(txtTenDV2, gridBagConstraints);

        GD_DSPhong.add(panelNorth2, java.awt.BorderLayout.PAGE_START);

        panelCenter1.setBackground(new java.awt.Color(255, 255, 255));
        panelCenter1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        panelCenter1.setLayout(new java.awt.BorderLayout());

        scrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N
        scrollPane2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        scrollPane2.setMinimumSize(new java.awt.Dimension(1200, 160));
        scrollPane2.setPreferredSize(new java.awt.Dimension(1200, 900));

        tableDSPhong.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        tableDSPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phòng", "Mã loại phòng", "Tên loại phòng", "Đơn giá", "Sức chứa", "Tình trạng", "Trạng thái"
            }
        ));
        tableDSPhong.setMinimumSize(new java.awt.Dimension(1200, 300));
        tableDSPhong.setPreferredSize(new java.awt.Dimension(1200, 900));
        tableDSPhong.setRowHeight(25);
        tableDSPhong.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tableDSPhong.setShowHorizontalLines(true);
        tableDSPhong.getTableHeader().setReorderingAllowed(false);
        scrollPane2.setViewportView(tableDSPhong);
        if (tableDSPhong.getColumnModel().getColumnCount() > 0) {
            tableDSPhong.getColumnModel().getColumn(0).setResizable(false);
            tableDSPhong.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableDSPhong.getColumnModel().getColumn(1).setResizable(false);
            tableDSPhong.getColumnModel().getColumn(1).setPreferredWidth(60);
            tableDSPhong.getColumnModel().getColumn(2).setResizable(false);
            tableDSPhong.getColumnModel().getColumn(2).setPreferredWidth(250);
            tableDSPhong.getColumnModel().getColumn(3).setResizable(false);
            tableDSPhong.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableDSPhong.getColumnModel().getColumn(4).setResizable(false);
            tableDSPhong.getColumnModel().getColumn(4).setPreferredWidth(60);
            tableDSPhong.getColumnModel().getColumn(5).setResizable(false);
            tableDSPhong.getColumnModel().getColumn(5).setPreferredWidth(60);
            tableDSPhong.getColumnModel().getColumn(6).setResizable(false);
            tableDSPhong.getColumnModel().getColumn(6).setPreferredWidth(60);
        }

        panelCenter1.add(scrollPane2, java.awt.BorderLayout.CENTER);

        GD_DSPhong.add(panelCenter1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(121, 188, 215));
        setExtendedState(1000);
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setSize(new java.awt.Dimension(1200, 600));

        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 560));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(1200, 40));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 40));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jPanel6.setBackground(new java.awt.Color(121, 188, 215));
        jPanel6.setMaximumSize(new java.awt.Dimension(400, 40));
        jPanel6.setPreferredSize(new java.awt.Dimension(400, 40));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6);

        jPanel5.setBackground(new java.awt.Color(121, 188, 215));
        jPanel5.setMaximumSize(new java.awt.Dimension(400, 40));
        jPanel5.setMinimumSize(new java.awt.Dimension(400, 40));
        jPanel5.setPreferredSize(new java.awt.Dimension(400, 40));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Cambria", 3, 24)); // NOI18N
        jLabel2.setText("TRANG CHỦ");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel5.add(jLabel2, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel5);

        jPanel4.setBackground(new java.awt.Color(121, 188, 215));
        jPanel4.setMaximumSize(new java.awt.Dimension(400, 40));
        jPanel4.setMinimumSize(new java.awt.Dimension(400, 40));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 40));
        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0, 5, 0};
        jPanel4Layout.rowHeights = new int[] {0};
        jPanel4.setLayout(jPanel4Layout);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/user.png"))); // NOI18N
        jLabel3.setText("Quản lý - Đặng Bảo Thông");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jLabel3, gridBagConstraints);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/logout.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jButton1, gridBagConstraints);

        jPanel2.add(jPanel4);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        GD_Chinh.setBackground(new java.awt.Color(255, 255, 255));
        GD_Chinh.setMinimumSize(new java.awt.Dimension(1200, 520));
        GD_Chinh.setPreferredSize(new java.awt.Dimension(1200, 900));
        GD_Chinh.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Frame 15.png"))); // NOI18N
        GD_Chinh.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel1.add(GD_Chinh, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jMenuBar2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jMenuBar2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuBar2.setMaximumSize(new java.awt.Dimension(1200, 3276840));
        jMenuBar2.setMinimumSize(new java.awt.Dimension(1200, 40));
        jMenuBar2.setPreferredSize(new java.awt.Dimension(1200, 40));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/house.png"))); // NOI18N
        jMenu1.setText("Trang chủ");
        jMenu1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu1);

        jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/building.png"))); // NOI18N
        jMenu11.setText("Phòng");
        jMenu11.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenu11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu11MouseClicked(evt);
            }
        });
        jMenu11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu11ActionPerformed(evt);
            }
        });

        jMenuItem7.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuItem7.setText("Đặt - Trả phòng");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem7);

        jMenuItem6.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuItem6.setText("Quản lý phòng");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem6);

        jMenuBar2.add(jMenu11);

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/paper.png"))); // NOI18N
        jMenu12.setText("Tài liệu ");
        jMenu12.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenu12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu12MouseClicked(evt);
            }
        });

        jMenuItem11.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuItem11.setText("Quản lý hóa đơn");
        jMenuItem11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem11MouseClicked(evt);
            }
        });
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem11);

        jMenuItem12.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuItem12.setText("Quản lý phiếu đặt phòng");
        jMenuItem12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem12MouseClicked(evt);
            }
        });
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem12);

        jMenuBar2.add(jMenu12);

        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/food.png"))); // NOI18N
        jMenu10.setText("Dịch vụ");
        jMenu10.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenu10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu10MouseClicked(evt);
            }
        });
        jMenu10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu10ActionPerformed(evt);
            }
        });

        jMenuItem5.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuItem5.setText("Quản lý dịch vụ");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseClicked(evt);
            }
        });
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem5);

        jMenuBar2.add(jMenu10);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icon_nv.png"))); // NOI18N
        jMenu4.setText("Nhân viên");
        jMenu4.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });

        jMenuItem9.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuItem9.setText("Quản lý nhân viên");
        jMenuItem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem9MouseClicked(evt);
            }
        });
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuBar2.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/people.png"))); // NOI18N
        jMenu5.setText("Khách hàng");
        jMenu5.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });

        jMenuItem10.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuItem10.setText("Quản lý khách hàng");
        jMenuItem10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem10MouseClicked(evt);
            }
        });
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuBar2.add(jMenu5);

        jMenu13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bar-chart.png"))); // NOI18N
        jMenu13.setText("Biểu đồ");
        jMenu13.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jMenu7.setText("Thống kê doanh thu theo thời gian");
        jMenu7.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuItem2.setText("Thống kê doanh thu theo ngày");
        jMenu7.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuItem3.setText("Thống kê doanh thu theo tháng");
        jMenu7.add(jMenuItem3);

        jMenuItem4.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuItem4.setText("Thống kê doanh thu theo năm");
        jMenu7.add(jMenuItem4);

        jMenu13.add(jMenu7);

        jMenuItem8.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jMenuItem8.setText("Thống kê doanh thu theo khách hàng");
        jMenu13.add(jMenuItem8);

        jMenuBar2.add(jMenu13);

        setJMenuBar(jMenuBar2);
        jMenuBar2.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenu11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu11ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu11ActionPerformed

    private void jMenu10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu10ActionPerformed

    }//GEN-LAST:event_jMenu10ActionPerformed

    private void jMenu10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu10MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu10MouseClicked

    private void txtMaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDVActionPerformed

    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void radioDangBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDangBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioDangBanActionPerformed

    private void btnThemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemDVActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        disableAllPanel();
        jLabel2.setText("TRANG CHỦ");
        GD_Chinh.setVisible(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void txtHoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoActionPerformed

    private void radioNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioNamActionPerformed

    private void radioDangLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDangLamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioDangLamActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnTim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTim1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTim1ActionPerformed

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void pwMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pwMatKhauActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu5MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void radioNam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNam1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioNam1ActionPerformed

    private void btnLamMoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoi2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jMenu11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu11MouseClicked

    private void jMenu12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu12MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenuItem9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9MouseClicked

    private void jMenuItem10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10MouseClicked

    private void jMenuItem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5MouseClicked

    private void jMenuItem11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem11MouseClicked
        // TODO add your handling code here: 
    }//GEN-LAST:event_jMenuItem11MouseClicked

    private void jMenuItem12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12MouseClicked

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        disableAllPanel();
        GD_NhanVien.setVisible(true);
        jLabel2.setText("QUẢN LÝ NHÂN VIÊN");
        jPanel1.add(GD_NhanVien, BorderLayout.CENTER);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        disableAllPanel();
        GD_KhachHang.setVisible(true);
        jLabel2.setText("QUẢN LÝ KHÁCH HÀNG");
        jPanel1.add(GD_KhachHang, BorderLayout.CENTER);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        disableAllPanel();
        jLabel2.setText("QUẢN LÝ DỊCH VỤ");
        jPanel1.add(GD_DichVu, BorderLayout.CENTER);
        GD_DichVu.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        disableAllPanel();
        jLabel2.setText("ĐẶT - TRẢ PHÒNG");
        jPanel1.add(GD_Phong, BorderLayout.CENTER);
        GD_Phong.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        disableAllPanel();
        jLabel2.setText("QUẢN LÝ HÓA ĐƠN");
        jPanel1.add(GD_HoaDon, BorderLayout.CENTER);
        GD_HoaDon.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        disableAllPanel();
        jLabel2.setText("QUẢN LÝ PHIẾU ĐẶT PHÒNG");
        jPanel1.add(GD_PhieuDatPhong, BorderLayout.CENTER);
        GD_PhieuDatPhong.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void comboDVBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDVBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDVBActionPerformed

    private void txtMaDV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDV1ActionPerformed

    private void txtDonGia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGia1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGia1ActionPerformed

    private void comboSucChuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSucChuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSucChuaActionPerformed

    private void radioDangBan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDangBan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioDangBan1ActionPerformed

    private void btnLamMoi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoi3ActionPerformed

    private void btnXoa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoa3ActionPerformed

    private void btnTim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTim2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTim2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        disableAllPanel();
        jLabel2.setText("QUẢN LÝ PHÒNG");
        jPanel1.add(GD_DSPhong, BorderLayout.CENTER);
        GD_DSPhong.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
            java.util.logging.Logger.getLogger(GiaoDienChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDienChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDienChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDienChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDienChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GD_Chinh;
    private javax.swing.JPanel GD_DSPhong;
    private javax.swing.JPanel GD_DichVu;
    private javax.swing.JPanel GD_HoaDon;
    private javax.swing.JPanel GD_KhachHang;
    private javax.swing.JPanel GD_NhanVien;
    private javax.swing.JPanel GD_PhieuDatPhong;
    private javax.swing.JPanel GD_Phong;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCapNhatDV;
    private javax.swing.JButton btnCapNhatDV1;
    private javax.swing.ButtonGroup btnGPhong;
    private javax.swing.ButtonGroup btnGTTDV;
    private javax.swing.ButtonGroup btnGTTNV;
    private javax.swing.ButtonGroup btnGenderKH;
    private javax.swing.ButtonGroup btnGenderNV;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnLamMoi2;
    private javax.swing.JButton btnLamMoi3;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemDV;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTim1;
    private javax.swing.JButton btnTim2;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JButton btnXoa3;
    private javax.swing.JComboBox<String> comboChucVu;
    private javax.swing.JComboBox<String> comboDVB;
    private javax.swing.JComboBox<String> comboSucChua;
    private javax.swing.JComboBox<String> comboXuatXu;
    private javax.swing.JComboBox<String> comboXuatXu1;
    private com.toedter.calendar.JDateChooser hsd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.components.JSpinField jSpinField1;
    private com.toedter.components.JSpinField jSpinField2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDonGia;
    private javax.swing.JLabel lblDonGia1;
    private javax.swing.JLabel lblDonViBan;
    private javax.swing.JLabel lblDonViBan1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHo;
    private javax.swing.JLabel lblHsd;
    private javax.swing.JLabel lblHsd1;
    private javax.swing.JLabel lblMaDV;
    private javax.swing.JLabel lblMaDV1;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblTenDV;
    private javax.swing.JLabel lblTenDV1;
    private javax.swing.JLabel lblTinhTrang;
    private javax.swing.JLabel lblTinhTrang1;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblXuatXu;
    private javax.swing.JLabel lblXuatXu1;
    private com.toedter.calendar.JDateChooser ngaySinh;
    private com.toedter.calendar.JDateChooser ngaySinh1;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelCenter1;
    private javax.swing.JPanel panelDSNV;
    private javax.swing.JPanel panelNorth;
    private javax.swing.JPanel panelNorth1;
    private javax.swing.JPanel panelNorth2;
    private javax.swing.JPanel panelPhong;
    private javax.swing.JPasswordField pwMatKhau;
    private javax.swing.JRadioButton radioDaNghi;
    private javax.swing.JRadioButton radioDangBan;
    private javax.swing.JRadioButton radioDangBan1;
    private javax.swing.JRadioButton radioDangLam;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNam1;
    private javax.swing.JRadioButton radioNgungBan;
    private javax.swing.JRadioButton radioNgungBan1;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JRadioButton radioNu1;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private com.toedter.components.JSpinField spinSoLuong;
    private javax.swing.JTable tableDSNV;
    private javax.swing.JTable tableDSPhong;
    private javax.swing.JTable tableDichVu;
    private javax.swing.JTable tableHD;
    private javax.swing.JTable tableKH;
    private javax.swing.JTable tablePhieu;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JLabel txtChucVu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDonGia1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHD;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtMaDV;
    private javax.swing.JTextField txtMaDV1;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTP;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDV;
    private javax.swing.JTextField txtTenDV1;
    private javax.swing.JTextField txtTenDV2;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTim1;
    private javax.swing.JTextField txtTimKH;
    private javax.swing.JTextField txtTimPhieu;
    // End of variables declaration//GEN-END:variables

    private Object DSPhong() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
