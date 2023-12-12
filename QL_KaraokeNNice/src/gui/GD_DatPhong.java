/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import Form.Form_DatPhong;
import Form.Form_NhanPhongNgay;
import entity.Phong;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;
import dao.Phong_DAO;
import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author PC BAO THONG
 */
public final class GD_DatPhong extends javax.swing.JPanel {

    private final Phong_DAO phongDAO;
    private PhieuDatPhong_DAO phieudatphongdao;
    private KhachHang_DAO khachhangDAO;
    private HoaDon_DAO hoadonDAO;
    private NhanVien_DAO nhanvienDAO;
    String maNV;

    /**
     * Creates new form GD_DatPhong
     */
    public GD_DatPhong(String maNV_GDC) {
        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        hienThiNgay();
        maNV = maNV_GDC;
        phongDAO = new Phong_DAO();
        khachhangDAO = new KhachHang_DAO();
        hoadonDAO = new HoaDon_DAO();
        nhanvienDAO = new NhanVien_DAO();
        phieudatphongdao = new PhieuDatPhong_DAO();
        loadAllPhong();
        datphong.setMnemonic(KeyEvent.VK_1);
        huydatphong.setMnemonic(KeyEvent.VK_2);
        nhanphong.setMnemonic(KeyEvent.VK_3);
        traphong.setMnemonic(KeyEvent.VK_4);
        capnhatdv.setMnemonic(KeyEvent.VK_5);
        datphong.setEnabled(false);
        huydatphong.setEnabled(false);
        nhanphong.setEnabled(false);
        traphong.setEnabled(false);
        capnhatdv.setEnabled(false);
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

    //
    public String layDiaChiIconTheoLoaiVaTrangThaiPhong(String trangThai, String loaiPhong) {
        if ("PT001".equalsIgnoreCase(loaiPhong)) {
            if ("Trống".equalsIgnoreCase(trangThai)) {
                return "/Image/PT_T.png";
            } else if ("Đã được đặt".equalsIgnoreCase(trangThai)) {
                return "/Image/PT_D.png";
            } else {
                return "/Image/PT_SD.png";
            }
        } else {
            if ("Trống".equalsIgnoreCase(trangThai)) {
                return "/Image/PV_T.png";
            } else if ("Đã được đặt".equalsIgnoreCase(trangThai)) {
                return "/Image/PV_D.png";
            } else {
                return "/Image/PV_SD.png";
            }
        }
    }

    public JPanel taoPanelPhong(Phong phong, LoaiPhong lp) {
        JPanel pnPhong = new JPanel();
        pnPhong.setPreferredSize(new Dimension(250, 140));
        pnPhong.setBackground(Color.WHITE);
        pnPhong.setName(phong.getMaPhong());

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource(layDiaChiIconTheoLoaiVaTrangThaiPhong(phong.getTrangThai(), phong.getLoaiPhong().getMaLP()))));

        String soPhong = "Phòng: " + phong.getMaPhong();
        JLabel lblNewLabel_1 = new JLabel(soPhong);
        lblNewLabel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 16));

        String trangThai = phong.getTrangThai();
        JLabel trangThaiPhong = new JLabel("Trạng thái: " + trangThai);
        trangThaiPhong.setAlignmentX(Component.LEFT_ALIGNMENT);
        trangThaiPhong.setFont(new Font("Cambria", Font.PLAIN, 16));

        String tenKH = null;
        if (trangThai.equalsIgnoreCase("Đã được đặt")) {
            String maKH = phieudatphongdao.layKHDatPhong(phong.getMaPhong());
            KhachHang kh = null;
            kh = khachhangDAO.getKhachHangTheoMa(maKH);
            tenKH = kh.getHoKH() + " " + kh.getTenKH();
        }
        if (trangThai.equalsIgnoreCase("Đang sử dụng")) {
            String maKH = hoadonDAO.layKHDungPhong(phong.getMaPhong());
            KhachHang kh = null;
            kh = khachhangDAO.getKhachHangTheoMa(maKH);
            tenKH = kh.getHoKH() + " " + kh.getTenKH();
        }
        JLabel khach = new JLabel("Tên khách: " + tenKH);
        khach.setAlignmentX(Component.LEFT_ALIGNMENT);
        khach.setFont(new Font("Cambria", Font.PLAIN, 16));

        int soNguoi = phong.getSoNguoi();
        JLabel nguoi = new JLabel("Số người: " + soNguoi);
        nguoi.setAlignmentX(Component.LEFT_ALIGNMENT);
        nguoi.setFont(new Font("Cambria", Font.PLAIN, 16));

        JLabel loaiPhong = new JLabel("Loại phòng: " + lp.getTenLoaiPhong());
        loaiPhong.setAlignmentX(Component.LEFT_ALIGNMENT);
        loaiPhong.setFont(new Font("Cambria", Font.PLAIN, 16));

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.##", symbols);
        double giaPhong = lp.getGiaTien();
        JLabel giaP = new JLabel("Giá: " + df.format(giaPhong) + " vnđ/giờ");
        giaP.setAlignmentX(Component.LEFT_ALIGNMENT);
        giaP.setFont(new Font("Cambria", Font.PLAIN, 16));
        giaP.setForeground(Color.red);

        JLabel lblTieuDe = new JLabel(phong.getMaPhong());
        lblTieuDe.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTieuDe.setFont(new Font("Cambria", Font.BOLD, 16));

        pnPhong.setLayout(new BoxLayout(pnPhong, BoxLayout.Y_AXIS));
        pnPhong.add(lblNewLabel);
        pnPhong.add(lblTieuDe);
        // Thêm sự kiện khi người dùng nhấp chuột vào phòng
        pnPhong.addMouseListener(new MouseAdapter() {
//      @Override
//      public void mouseClicked(MouseEvent e) {
//          // Xử lý sự kiện khi phòng được nhấp chuột
//          // Ví dụ: hiển thị thông tin chi tiết phòng, chuyển đến trang khác, vv.
//      }

            @Override
            public void mouseEntered(MouseEvent e) {
                pnPhong.removeAll();
                pnPhong.add(lblNewLabel_1);
                pnPhong.add(loaiPhong);
                pnPhong.add(trangThaiPhong);
                pnPhong.add(nguoi);
                pnPhong.add(khach);
                pnPhong.add(giaP);
                EmptyBorder margin = new EmptyBorder(10, 0, 5, 0);
                pnPhong.setBorder(margin);
                pnPhong.setBackground(Color.LIGHT_GRAY);
                pnPhong.revalidate();
                pnPhong.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pnPhong.removeAll();
                pnPhong.setBorder(null);
                pnPhong.setBackground(Color.WHITE);
                pnPhong.add(lblNewLabel);
                pnPhong.add(lblTieuDe);
                pnPhong.revalidate();
                pnPhong.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Lấy thông tin JPanel đã được nhấp chuột
                JPanel panel = (JPanel) e.getSource();
                // In ra tên JPanel
                String name = panel.getName();
                System.out.println("Tên JPanel: " + name);
                txtMaPhong.setText(name);
                int i = phongDAO.kiemTraTrangThaiPhong(name);
                switch (i) {
                    case 1 -> {
                        datphong.setEnabled(true);
                        nhanphong.setEnabled(true);
                        huydatphong.setEnabled(false);
                        traphong.setEnabled(false);
                        capnhatdv.setEnabled(false);
                    }
                    case 2 -> {
                        huydatphong.setEnabled(true);
                        nhanphong.setEnabled(true);
                        datphong.setEnabled(false);   //mod
                        traphong.setEnabled(false);
                        capnhatdv.setEnabled(false);
                    }
                    default -> {
                        traphong.setEnabled(true);
                        capnhatdv.setEnabled(true);
                        datphong.setEnabled(false);
                        huydatphong.setEnabled(false);
                        nhanphong.setEnabled(false);
                    }
                }

                //tinh thoi luong giua gio dat hien tai va gio nhan truoc do
//                Date gioHienTai = new Date();
//                PhieuDatPhong pdp = phieudatphongdao.getPDPTheoMaPhong(name);
//                long khoangCachThoiGian = gioHienTai.getTime() - pdp.getThoiGianNhan().getTime();
//                double thoiLuong = khoangCachThoiGian / (60 * 60 * 1000);
//                if(thoiLuong >= 1.5){
//                    datphong.setEnabled(true);
//                    nhanphong.setEnabled(true);
//                    huydatphong.setEnabled(false);
//                    traphong.setEnabled(false);
//                    capnhatdv.setEnabled(false);
//                }else{
//                    datphong.setEnabled(false);
//                    nhanphong.setEnabled(true);
//                    huydatphong.setEnabled(false);
//                    traphong.setEnabled(false);
//                    capnhatdv.setEnabled(false);
//                }
                panel.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        });
        datphong.setEnabled(false);
        huydatphong.setEnabled(false);
        nhanphong.setEnabled(false);
        traphong.setEnabled(false);
        capnhatdv.setEnabled(false);
        return pnPhong;
    }

    //
    public void loadAllPhong() {
        ArrayList<Phong> dsP = phongDAO.layDSPhong();
        for (Phong phong : dsP) {
            String maLP = phong.getLoaiPhong().getMaLP();
            LoaiPhong loaiphong = phongDAO.getLoaiPhongTheoMa(maLP);
            JPanel pnP = taoPanelPhong(phong, loaiphong);
            panelPhong.add(pnP);
        }
    }

    public void loadDSPhongTheoTrangThai(String trangThai) {
        ArrayList<Phong> dsP = phongDAO.layDSPhongTheoTrangThai(trangThai);
        for (Phong phong : dsP) {
            String maLP = phong.getLoaiPhong().getMaLP();
            LoaiPhong loaiphong = phongDAO.getLoaiPhongTheoMa(maLP);
            JPanel pnP = taoPanelPhong(phong, loaiphong);
            panelPhong.add(pnP);
        }
    }

    public void loadDSPhongTheoLoaiPhong(String loaiP) {
        String lp;
        if (loaiP.equalsIgnoreCase("Phòng thường")) {
            lp = "PT001";
        } else {
            lp = "PV001";
        }
        ArrayList<Phong> dsP = phongDAO.layDSPhongTheoLoaiPhong(lp);
        for (Phong phong : dsP) {
            String maLP = phong.getLoaiPhong().getMaLP();
            LoaiPhong loaiphong = phongDAO.getLoaiPhongTheoMa(maLP);
            JPanel pnP = taoPanelPhong(phong, loaiphong);
            panelPhong.add(pnP);
        }
    }

    public void loadDSPhongTheoSoNguoi(String soNguoi) {
        int soN = Integer.parseInt(soNguoi);
        ArrayList<Phong> dsP = phongDAO.layDSPhongTheoSoNguoi(soN);
        for (Phong phong : dsP) {
            String maLP = phong.getLoaiPhong().getMaLP();
            LoaiPhong loaiphong = phongDAO.getLoaiPhongTheoMa(maLP);
            JPanel pnP = taoPanelPhong(phong, loaiphong);
            panelPhong.add(pnP);
        }
    }

    public void loadDSPhongTheoTieuChi(String strangThai, String soNguoi, String loaiP) {
        ArrayList<Phong> dsP = phongDAO.layDSPhongTheoTieuChi(strangThai, soNguoi, loaiP);
        for (Phong phong : dsP) {
            String maLP = phong.getLoaiPhong().getMaLP();
            LoaiPhong loaiphong = phongDAO.getLoaiPhongTheoMa(maLP);
            JPanel pnP = taoPanelPhong(phong, loaiphong);
            panelPhong.add(pnP);
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

        GD_Phong = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelPhong = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        comboTrangThai = new javax.swing.JComboBox<>();
        comboLoaiPhong = new javax.swing.JComboBox<>();
        comboSoNguoi = new javax.swing.JComboBox<>();
        txtMaPhong = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        datphong = new javax.swing.JButton();
        huydatphong = new javax.swing.JButton();
        nhanphong = new javax.swing.JButton();
        traphong = new javax.swing.JButton();
        capnhatdv = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        GD_Phong.setBackground(new java.awt.Color(121, 188, 215));
        GD_Phong.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        GD_Phong.setMinimumSize(new java.awt.Dimension(1200, 520));
        GD_Phong.setPreferredSize(new java.awt.Dimension(1200, 520));
        GD_Phong.setLayout(new java.awt.BorderLayout(10, 10));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(970, 270));

        panelPhong.setBackground(new java.awt.Color(255, 255, 255));
        panelPhong.setPreferredSize(new java.awt.Dimension(840, 840));
        panelPhong.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        jScrollPane1.setViewportView(panelPhong);

        GD_Phong.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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

        comboTrangThai.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        comboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang sử dụng", "Đã được đặt", "Trống" }));
        comboTrangThai.setPreferredSize(new java.awt.Dimension(150, 30));
        comboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTrangThaiActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel13.add(comboTrangThai, gridBagConstraints);

        comboLoaiPhong.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        comboLoaiPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Phòng thường", "Phòng VIP" }));
        comboLoaiPhong.setPreferredSize(new java.awt.Dimension(150, 30));
        comboLoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLoaiPhongActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel13.add(comboLoaiPhong, gridBagConstraints);

        comboSoNguoi.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        comboSoNguoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "10", "15", "20" }));
        comboSoNguoi.setPreferredSize(new java.awt.Dimension(150, 30));
        comboSoNguoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSoNguoiActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        jPanel13.add(comboSoNguoi, gridBagConstraints);

        txtMaPhong.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtMaPhong.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        jPanel13.add(txtMaPhong, gridBagConstraints);

        jButton12.setBackground(new java.awt.Color(121, 188, 215));
        jButton12.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Tìm");
        jButton12.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jButton12, gridBagConstraints);

        jButton13.setBackground(new java.awt.Color(0, 255, 153));
        jButton13.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Làm mới");
        jButton13.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
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
        jPanel10Layout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0};
        jPanel10Layout.rowHeights = new int[] {0};
        jPanel10.setLayout(jPanel10Layout);

        jLabel12.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/phongV.png"))); // NOI18N
        jLabel12.setText("Phòng VIP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        jPanel10.add(jLabel12, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/phongT.png"))); // NOI18N
        jLabel6.setText("Phòng thường");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel10.add(jLabel14, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pT.png"))); // NOI18N
        jLabel15.setText("Phòng trống");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        jPanel10.add(jLabel15, gridBagConstraints);

        jPanel9.add(jPanel10, java.awt.BorderLayout.CENTER);

        GD_Phong.add(jPanel9, java.awt.BorderLayout.PAGE_END);

        jPanel8.setBackground(new java.awt.Color(121, 188, 215));
        jPanel8.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        jPanel8.setMinimumSize(new java.awt.Dimension(220, 320));
        jPanel8.setPreferredSize(new java.awt.Dimension(220, 320));
        jPanel8.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setMaximumSize(new java.awt.Dimension(100, 32767));
        jPanel11.setMinimumSize(new java.awt.Dimension(220, 200));
        java.awt.GridBagLayout jPanel11Layout = new java.awt.GridBagLayout();
        jPanel11Layout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0};
        jPanel11Layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0};
        jPanel11.setLayout(jPanel11Layout);

        datphong.setBackground(new java.awt.Color(64, 71, 214));
        datphong.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        datphong.setForeground(new java.awt.Color(255, 255, 255));
        datphong.setText("Đặt phòng               (1)");
        datphong.setMaximumSize(new java.awt.Dimension(200, 30));
        datphong.setMinimumSize(new java.awt.Dimension(200, 30));
        datphong.setPreferredSize(new java.awt.Dimension(200, 40));
        datphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datphongActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(datphong, gridBagConstraints);

        huydatphong.setBackground(new java.awt.Color(64, 71, 214));
        huydatphong.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        huydatphong.setForeground(new java.awt.Color(255, 255, 255));
        huydatphong.setText("Hủy đặt phòng       (2)");
        huydatphong.setMaximumSize(new java.awt.Dimension(200, 30));
        huydatphong.setMinimumSize(new java.awt.Dimension(200, 30));
        huydatphong.setPreferredSize(new java.awt.Dimension(200, 40));
        huydatphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huydatphongActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(huydatphong, gridBagConstraints);

        nhanphong.setBackground(new java.awt.Color(64, 71, 214));
        nhanphong.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        nhanphong.setForeground(new java.awt.Color(255, 255, 255));
        nhanphong.setText("Nhận phòng            (3)");
        nhanphong.setMaximumSize(new java.awt.Dimension(200, 30));
        nhanphong.setMinimumSize(new java.awt.Dimension(200, 30));
        nhanphong.setPreferredSize(new java.awt.Dimension(200, 40));
        nhanphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhanphongActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(nhanphong, gridBagConstraints);

        traphong.setBackground(new java.awt.Color(64, 71, 214));
        traphong.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        traphong.setForeground(new java.awt.Color(255, 255, 255));
        traphong.setText("Trả phòng               (4)");
        traphong.setMaximumSize(new java.awt.Dimension(200, 30));
        traphong.setMinimumSize(new java.awt.Dimension(200, 30));
        traphong.setPreferredSize(new java.awt.Dimension(200, 40));
        traphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traphongActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(traphong, gridBagConstraints);

        capnhatdv.setBackground(new java.awt.Color(64, 71, 214));
        capnhatdv.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        capnhatdv.setForeground(new java.awt.Color(255, 255, 255));
        capnhatdv.setText("Cập nhật dịch vụ   (5)");
        capnhatdv.setMaximumSize(new java.awt.Dimension(200, 30));
        capnhatdv.setMinimumSize(new java.awt.Dimension(200, 30));
        capnhatdv.setPreferredSize(new java.awt.Dimension(200, 40));
        capnhatdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capnhatdvActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel11.add(capnhatdv, gridBagConstraints);

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

        add(GD_Phong, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void datphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datphongActionPerformed
        String maP = txtMaPhong.getText();
        Phong phong = phongDAO.getPhongTheoMa(maP);
        new Form_DatPhong(phong, maNV).setVisible(true);
    }//GEN-LAST:event_datphongActionPerformed

    private void huydatphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huydatphongActionPerformed
        // TODO add your handling code here:
        phieudatphongdao = new PhieuDatPhong_DAO();
        PhieuDatPhong phieuDatPhong = phieudatphongdao.getPDPTheoMaPhong(txtMaPhong.getText());
        if (phieuDatPhong == null) {
            JOptionPane.showMessageDialog(this, "Phòng chưa được đặt");
            return;
        }
        KhachHang kh = khachhangDAO.getKhachHangTheoMa(phieuDatPhong.getKhachHang().getMaKH());
        int xacnhan = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn HUỶ phòng đặt của " + kh.getHoKH() + " " + kh.getTenKH() + "?", "Thông báo",
                JOptionPane.YES_NO_OPTION);
        if (xacnhan != JOptionPane.YES_OPTION) {
            return;
        } else {
            if (phongDAO.capNhatTrangThaiPhong(phieuDatPhong.getPhong().getMaPhong(), "Trống") && phieudatphongdao.capNhatTrangThaiPhieuDatPhong(phieuDatPhong.getMaPhieu())) {
                JOptionPane.showMessageDialog(this, "Hủy phòng đặt thành công");
                return;
            }
        }

    }//GEN-LAST:event_huydatphongActionPerformed

    private void nhanphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhanphongActionPerformed
        Phong phong = phongDAO.getPhongTheoMa(txtMaPhong.getText());
        Object[] options = {"Nhận phòng đặt trước", "Nhận phòng ngay"};
        int result = JOptionPane.showOptionDialog(null, "Chọn cách nhận phòng",
                "Lựa chọn", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        switch (result) {
            case JOptionPane.YES_OPTION -> {
                PhieuDatPhong phieuDatPhong = phieudatphongdao.getPDPTheoMaPhong(txtMaPhong.getText());
                if (phieuDatPhong == null) {
                    JOptionPane.showMessageDialog(null, "Phòng này chưa được đặt trước");
                } else {
                    Date gioHienTai = new Date();
                    double khoangCachThoiGian = phieuDatPhong.getThoiGianNhan().getTime() - gioHienTai.getTime();
                    double thoiLuong = khoangCachThoiGian / (60 * 60 * 1000);
                    int scale = (int) Math.pow(10, 1);
                    double roundTo1Decimal = (double) Math.round(thoiLuong * scale) / scale;
                    if (roundTo1Decimal > 0.2) {
                        JOptionPane.showMessageDialog(null, "Chưa đến thời gian nhận phòng");
                        return;
                    }
                    KhachHang kh = khachhangDAO.getKhachHangTheoMa(phieuDatPhong.getKhachHang().getMaKH());
                    int xacnhan = JOptionPane.showConfirmDialog(this,
                            "Xác nhận nhận phòng của " + kh.getHoKH() + " " + kh.getTenKH() + "?", "Thông báo",
                            JOptionPane.YES_NO_OPTION);
                    if (xacnhan != JOptionPane.YES_OPTION) {
                        return;
                    } else {
                        if (hoadonDAO.check_HD_ChuaThanhToan(kh.getMaKH())) {
                            HoaDon hd = hoadonDAO.getHoaDonTheoKH_TrangThai(kh.getMaKH());
                            ChiTietHoaDon ctHD = new ChiTietHoaDon(hd, phieuDatPhong.getThoiGianNhan(), new Date(), phong);
                            phieudatphongdao.capNhatTrangThaiPhieuDatPhong(phieuDatPhong.getMaPhieu());
                            phongDAO.capNhatTrangThaiPhong(phong.getMaPhong(), "Đang sử dụng");
                            hoadonDAO.themChiTietHoaDon(ctHD);
                            JOptionPane.showMessageDialog(null, "Nhận phòng thành công");
                        } else {
                            String maHD = hoadonDAO.maHD_Auto();
                            HoaDon hoaDon;
                            String maNv = phieuDatPhong.getNhanVienLap().getMaNV();
                            NhanVien nv = nhanvienDAO.getNhanVienTheoMa(maNv);
                            hoaDon = new HoaDon(maHD, kh, nv, new Date(), 0.1, 0, false);
                            ChiTietHoaDon ctHD;
                            ctHD = new ChiTietHoaDon(hoaDon, phieuDatPhong.getThoiGianNhan(), new Date(), phong);
                            phieudatphongdao.capNhatTrangThaiPhieuDatPhong(phieuDatPhong.getMaPhieu());
                            phongDAO.capNhatTrangThaiPhong(phong.getMaPhong(), "Đang sử dụng");

                            if (hoadonDAO.themHoaDon(hoaDon)) {
                                JOptionPane.showMessageDialog(null, "Nhận phòng thành công");
                            } else {
                                JOptionPane.showMessageDialog(null, "Đã có lỗi");
                            }

                            hoadonDAO.themChiTietHoaDon(ctHD);
                        }
                    }
                }

            }
            case JOptionPane.NO_OPTION -> {
                //tinh thoi luong giua go nhan hien tai va gio dat truoc do
                PhieuDatPhong pdp = phieudatphongdao.getPDPTheoMaPhong(txtMaPhong.getText().trim());
                if (pdp != null) {
                    Date gioHienTai = new Date();
                    double khoangCachThoiGian = pdp.getThoiGianNhan().getTime() - gioHienTai.getTime();
                    double thoiLuong = khoangCachThoiGian / (60 * 60 * 1000);
                    int scale = (int) Math.pow(10, 1);
                    double roundTo1Decimal = (double) Math.round(thoiLuong * scale) / scale;

                    if (roundTo1Decimal < 1.5) {
                        JOptionPane.showMessageDialog(null, "Gần đến thời gian phòng đặt trước sắp sử dụng");
                    } else {
                        new Form_NhanPhongNgay(phong, maNV).setVisible(true);
                    }
                } else {
                    new Form_NhanPhongNgay(phong, maNV).setVisible(true);
                }
            }
            default -> {
                return;
            }
        }

    }//GEN-LAST:event_nhanphongActionPerformed

    private void traphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traphongActionPerformed
        int xacnhan = JOptionPane.showConfirmDialog(this,
                "Xác nhận trả phòng?", "Thông báo",
                JOptionPane.YES_NO_OPTION);
        if (xacnhan != JOptionPane.YES_OPTION) {
            return;
        }
        String ma = txtMaPhong.getText();
        HoaDon hd = hoadonDAO.getHoaDonTheoMaPhong_TrangThai(ma);
        Date gkt = new Date();
        hoadonDAO.updateCTHD_GKT(hd.getMaHD(), gkt);
        new Form.Form_HoaDon(hd, true).setVisible(true);
    }//GEN-LAST:event_traphongActionPerformed

    private void capnhatdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capnhatdvActionPerformed
        // TODO add your handling code here:
        new Form.Form_CapNhatDVP(txtMaPhong.getText()).setVisible(true);
    }//GEN-LAST:event_capnhatdvActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        panelPhong.removeAll();
        panelPhong.repaint();
        panelPhong.revalidate();
        comboLoaiPhong.setSelectedIndex(0);
        comboSoNguoi.setSelectedIndex(0);
        comboTrangThai.setSelectedIndex(0);
        txtMaPhong.setText("");
        loadAllPhong();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void comboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTrangThaiActionPerformed
        // TODO add your handling code here:
        String trangThai = comboTrangThai.getSelectedItem().toString();
        String loaiP = comboLoaiPhong.getSelectedItem().toString();
        String soNguoi = comboSoNguoi.getSelectedItem().toString();
        panelPhong.removeAll();
        if (trangThai.equalsIgnoreCase("Tất cả") && loaiP.equalsIgnoreCase("Tất cả") && soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadAllPhong();
        } else if (!trangThai.equalsIgnoreCase("Tất cả") && loaiP.equalsIgnoreCase("Tất cả") && soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoTrangThai(trangThai);
        } else if (trangThai.equalsIgnoreCase("Tất cả") && !loaiP.equalsIgnoreCase("Tất cả") && soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoLoaiPhong(loaiP);
        } else if (trangThai.equalsIgnoreCase("Tất cả") && loaiP.equalsIgnoreCase("Tất cả") && !soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoSoNguoi(soNguoi);
        } else {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoTieuChi(trangThai, soNguoi, loaiP);
        }
    }//GEN-LAST:event_comboTrangThaiActionPerformed

    private void comboLoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLoaiPhongActionPerformed
        // TODO add your handling code here:
        String trangThai = comboTrangThai.getSelectedItem().toString();
        String loaiP = comboLoaiPhong.getSelectedItem().toString();
        String soNguoi = comboSoNguoi.getSelectedItem().toString();
        panelPhong.removeAll();
        if (trangThai.equalsIgnoreCase("Tất cả") && loaiP.equalsIgnoreCase("Tất cả") && soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadAllPhong();
        } else if (!trangThai.equalsIgnoreCase("Tất cả") && loaiP.equalsIgnoreCase("Tất cả") && soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoTrangThai(trangThai);
        } else if (trangThai.equalsIgnoreCase("Tất cả") && !loaiP.equalsIgnoreCase("Tất cả") && soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoLoaiPhong(loaiP);
        } else if (trangThai.equalsIgnoreCase("Tất cả") && loaiP.equalsIgnoreCase("Tất cả") && !soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoSoNguoi(soNguoi);
        } else {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoTieuChi(trangThai, soNguoi, loaiP);
        }
    }//GEN-LAST:event_comboLoaiPhongActionPerformed

    private void comboSoNguoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSoNguoiActionPerformed
        // TODO add your handling code here:
        String trangThai = comboTrangThai.getSelectedItem().toString();
        String loaiP = comboLoaiPhong.getSelectedItem().toString();
        String soNguoi = comboSoNguoi.getSelectedItem().toString();
        panelPhong.removeAll();
        if (trangThai.equalsIgnoreCase("Tất cả") && loaiP.equalsIgnoreCase("Tất cả") && soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadAllPhong();
        } else if (!trangThai.equalsIgnoreCase("Tất cả") && loaiP.equalsIgnoreCase("Tất cả") && soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoTrangThai(trangThai);
        } else if (trangThai.equalsIgnoreCase("Tất cả") && !loaiP.equalsIgnoreCase("Tất cả") && soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoLoaiPhong(loaiP);
        } else if (trangThai.equalsIgnoreCase("Tất cả") && loaiP.equalsIgnoreCase("Tất cả") && !soNguoi.equalsIgnoreCase("Tất cả")) {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoSoNguoi(soNguoi);
        } else {
            panelPhong.repaint();
            panelPhong.revalidate();
            loadDSPhongTheoTieuChi(trangThai, soNguoi, loaiP);
        }
    }//GEN-LAST:event_comboSoNguoiActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        String mp = txtMaPhong.getText();
        Phong p = phongDAO.getPhongTheoMa(mp);
        if (p == null) {
            return;
        }
        panelPhong.removeAll();
        panelPhong.repaint();
        panelPhong.revalidate();
        String maLP = p.getLoaiPhong().getMaLP();
        LoaiPhong loaiphong = phongDAO.getLoaiPhongTheoMa(maLP);
        JPanel pnP = taoPanelPhong(p, loaiphong);
        panelPhong.add(pnP);
    }//GEN-LAST:event_jButton12ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GD_Phong;
    private javax.swing.JButton capnhatdv;
    private javax.swing.JComboBox<String> comboLoaiPhong;
    private javax.swing.JComboBox<String> comboSoNguoi;
    private javax.swing.JComboBox<String> comboTrangThai;
    private javax.swing.JButton datphong;
    private javax.swing.JButton huydatphong;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nhanphong;
    private javax.swing.JPanel panelPhong;
    private javax.swing.JButton traphong;
    public javax.swing.JTextField txtMaPhong;
    // End of variables declaration//GEN-END:variables

}
