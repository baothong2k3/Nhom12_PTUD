/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import com.itextpdf.text.Font;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import connectDB.ConnectDB;
import dao.DichVu_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
//import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC BAO THONG
 */
public class Form_HoaDon extends javax.swing.JFrame {

    public static File fontFile = new File("VietFontsWeb1_ttf/vuArial.ttf");
    //dao
    private PhieuDatPhong_DAO phieuDP;
    private KhachHang_DAO kh_dao;
    private NhanVien_DAO nv_dao;
    private DichVu_DAO dv_dao;
    private Phong_DAO phong_dao;
    private HoaDon_DAO hd_dao;
    //model
    private DefaultTableModel modelCTHD;
    //List
    private ArrayList<ChiTietHoaDon> dsCTHD;
    private ArrayList<ChiTietDichVu> dsCTDV;
    private ArrayList<DichVu> dsDV;
    private ArrayList<Phong> dsP;
    //ex
    int xacNhan;

    public Form_HoaDon(HoaDon hd, boolean check) {
        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        phieuDP = new PhieuDatPhong_DAO();
        hd_dao = new HoaDon_DAO();
        kh_dao = new KhachHang_DAO();
        nv_dao = new NhanVien_DAO();
        dv_dao = new DichVu_DAO();
        phong_dao = new Phong_DAO();
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        table(hd);
        updateLable(hd, check);
    }

    private void table(HoaDon hd) {
        java.awt.Font fo = new java.awt.Font("Cambria", Font.BOLD, 18);
        tableCTHD.getTableHeader().setFont(fo);
        tableCTHD.getTableHeader().setOpaque(false);
        tableCTHD.getTableHeader().setBackground(new Color(32, 136, 203));
        tableCTHD.getTableHeader().setForeground(new Color(255, 255, 255));

        tableCTHD.getColumnModel().getColumn(1).setCellRenderer(new CenterTableCellRenderer());
        tableCTHD.getColumnModel().getColumn(3).setCellRenderer(new CenterTableCellRenderer());
        tableCTHD.getColumnModel().getColumn(4).setCellRenderer(new CenterTableCellRenderer());

        modelCTHD = (DefaultTableModel) tableCTHD.getModel();
        napDuLieuCTHD(hd);
    }

    private static class CenterTableCellRenderer extends DefaultTableCellRenderer {

        public CenterTableCellRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER); //Căn giữa nội dung
        }
    }

    private void napDuLieuCTHD(HoaDon hd) {
//        String maHD = lblMaHD.getText().trim();
        dsCTHD = hd_dao.getAllCTHDTheoMaHD(hd.getMaHD());
        dsCTDV = hd_dao.getAllCTDVTheoMaHD(hd.getMaHD());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        for (ChiTietHoaDon cthd : dsCTHD) {
            Phong p = phong_dao.getPhongTheoMa(cthd.getPhong().getMaPhong());
            LoaiPhong lp = phong_dao.getLoaiPhongTheoMa(p.getLoaiPhong().getMaLP());
            String GNP = formatter.format(cthd.getGioNhanPhong());
            String GKT = formatter.format(cthd.getGioKetThuc());

            modelCTHD.addRow(new Object[]{cthd.getPhong().getMaPhong(), cthd.tinhThoiLuong(), lp.getGiaTien(),
                GNP + " - " + GKT, "Giờ", cthd.tinhThanhTienPhong()});
//            modelCTHD.addRow(new Object[]{cthd.getPhong().getMaPhong(), cthd.tinhThoiLuong(), lp.getGiaTien(),
//                GNP + " - " + GKT, "Giờ", cthd.tinhThoiLuong()*lp.getGiaTien()});
        }

        for (ChiTietDichVu ctdv : dsCTDV) {
            DichVu dv = dv_dao.getDichVuTheoMa(ctdv.getDichVu().getMaDV());
            modelCTHD.addRow(new Object[]{dv.getTenDV(), ctdv.getSoLuong(), dv.getDonGia(),
                "", dv.getDonViBan(), ctdv.tinhThanhTienDV()});
//            modelCTHD.addRow(new Object[]{dv.getTenDV(), ctdv.getSoLuong(), dv.getDonGia(),
//                "", dv.getDonViBan(), ctdv.getSoLuong() * dv.getDonGia()});
        }
    }

    private void updateLable(HoaDon hd, boolean check) {
        KhachHang kh = kh_dao.getKhachHangTheoMa(hd.getKhachHang().getMaKH());
        NhanVien nv = nv_dao.getNhanVienTheoMa(hd.getNhanVienLap().getMaNV());
        ChiTietHoaDon cthd = hd_dao.getCTHDTheoMaHD(hd.getMaHD());
        lblMaHD.setText(hd.getMaHD());
        lblTenKH.setText(kh.getHoKH() + " " + kh.getTenKH());
        lblTenNV.setText(nv.getHoNV() + " " + nv.getTenNV());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String sNgayLap = formatter.format(hd.getNgayLap());
        lblGN.setText(sNgayLap);

        double tongTien = hd.tinhTongTienHD();
        double tongTienVAT = hd.tinhTongTienHDVAT();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String sTongTien = currencyVN.format(tongTien);
        String sTongTienVAT = currencyVN.format(tongTienVAT);

        lblTongCong.setText("" + sTongTien);
        lblTongTienVAT.setText("" + sTongTienVAT);
        if (!check) {
            btnThanhToan.setEnabled(false);
            txtTienNhan.setEditable(false);
        }
    }

//    private double tinhTongTien(HoaDon hd) {
//        dsCTHD = hd_dao.getAllCTHDTheoMaHD(hd.getMaHD());
//        dsCTDV = hd_dao.getAllCTDVTheoMaHD(hd.getMaHD());
//        double tongTienP = 0, tongTienDV = 0;
//
//        for (ChiTietHoaDon cthd : dsCTHD) {
//            Phong p = phong_dao.getPhongTheoMa(cthd.getPhong().getMaPhong());
//            LoaiPhong lp = phong_dao.getLoaiPhongTheoMa(p.getLoaiPhong().getMaLP());
//            double thanhTienP = cthd.tinhThoiLuong() * lp.getGiaTien();
//            tongTienP = tongTienP + thanhTienP;
//        }
//
//        for (ChiTietDichVu ctdv : dsCTDV) {
//            DichVu dv = dv_dao.getDichVuTheoMa(ctdv.getDichVu().getMaDV());
//            double thanhTienDV = ctdv.getSoLuong() * dv.getDonGia();
//            tongTienDV = tongTienDV + thanhTienDV;
//        }
//        return tongTienDV + tongTienP;
//    }
//
//    private double tinhTongTienVAT(HoaDon hd) {
//        double tongTien = tinhTongTien(hd);
//        return tongTien * hd.getVAT() + tongTien;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        lblTongCong = new javax.swing.JLabel();
        lblTongTienVAT = new javax.swing.JLabel();
        btnXuatPDF = new javax.swing.JButton();
        txtTienNhan = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCTHD = new javax.swing.JTable();
        btnThanhToan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblGN = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(1200, 450));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel17.setText("Tiền nhận");

        jLabel18.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel18.setText("Tiền thừa");

        lblTienThua.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblTienThua.setText("0 đ");

        lblTongCong.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblTongCong.setText("400.000 VND");

        lblTongTienVAT.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblTongTienVAT.setText("0 VND");

        btnXuatPDF.setBackground(new java.awt.Color(204, 255, 204));
        btnXuatPDF.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnXuatPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/invoice.png"))); // NOI18N
        btnXuatPDF.setText("Xuất PDF");
        btnXuatPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatPDFActionPerformed(evt);
            }
        });

        txtTienNhan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTienNhan.setMinimumSize(new java.awt.Dimension(150, 40));
        txtTienNhan.setPreferredSize(new java.awt.Dimension(200, 30));

        jLabel25.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel25.setText("Tổng Tiền (có VAT)");

        jLabel24.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel24.setText("Tổng cộng");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tableCTHD.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tableCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Số lượng / Giờ", "Đơn giá", "Giờ vào - ra", "Đơn vị tính", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableCTHD.setRowHeight(22);
        jScrollPane1.setViewportView(tableCTHD);
        if (tableCTHD.getColumnModel().getColumnCount() > 0) {
            tableCTHD.getColumnModel().getColumn(0).setPreferredWidth(310);
            tableCTHD.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableCTHD.getColumnModel().getColumn(2).setPreferredWidth(60);
            tableCTHD.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        btnThanhToan.setBackground(new java.awt.Color(0, 255, 255));
        btnThanhToan.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.setPreferredSize(new java.awt.Dimension(128, 31));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXuatPDF))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTienThua))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTongCong))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(96, 96, 96)
                                .addComponent(lblTongTienVAT)))))
                .addGap(59, 59, 59))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(lblTongCong))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtTienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblTienThua)
                    .addComponent(jLabel25)
                    .addComponent(lblTongTienVAT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatPDF)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBackground(new java.awt.Color(121, 188, 215));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Playfair Display Medium", 1, 24)); // NOI18N
        jLabel7.setText("HÓA ĐƠN TÍNH TIỀN");
        jPanel1.add(jLabel7, new java.awt.GridBagConstraints());

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel8.setText("Nhân viên");

        lblTenNV.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblTenNV.setText("Đặng Bảo Thông");

        jLabel10.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel10.setText("Ngày lập");

        lblGN.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblGN.setText("19:30 - 27/10/2023");

        jLabel14.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel14.setText("Khách hàng");

        lblTenKH.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        lblTenKH.setText("Lionel Messi");

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel1.setText("Tên quán");

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel2.setText("Địa chỉ");

        jLabel3.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel3.setText("Karaoke NNice");

        jLabel4.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel4.setText("Nguyễn Văn Bảo, Phường 4, Gò Vấp");

        jLabel5.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel5.setText("Mã hóa đơn");

        lblMaHD.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        lblMaHD.setText("HD001");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTenKH))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMaHD)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenNV)
                    .addComponent(lblGN))
                .addGap(52, 52, 52))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel2))))
                            .addComponent(lblTenNV, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(lblGN))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(lblMaHD, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblTenKH))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPDFActionPerformed

        Object object = evt.getSource();
        if (object.equals(btnXuatPDF)) {
            xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có muốn xem file", "Thông báo",
                    JOptionPane.YES_NO_OPTION);
            xuatFilePDF(lblMaHD.getText().trim());
//            setVisible(false);
            return;
        }
    }//GEN-LAST:event_btnXuatPDFActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        Object o = evt.getSource();
        if (o.equals(btnThanhToan)) {
            String maHD = lblMaHD.getText().trim();
            HoaDon hd = hd_dao.getHoaDonTheoMa(maHD);
            dsCTHD = hd_dao.getAllCTHDTheoMaHD(maHD);
            if (validTxt()) {
                try {
                    String stienNhan = txtTienNhan.getText().trim();
                    double tienNhan = Double.parseDouble(stienNhan);
                    double tongTienVAT = hd.tinhTongTienHDVAT();
                    double tienThua = tienNhan - tongTienVAT;
                    Locale localeVN = new Locale("vi", "VN");
                    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                    String sTienThua = currencyVN.format(tienThua);
                    lblTienThua.setText(sTienThua);
                    if (hd_dao.updateTrangThai_TongTien_HD(maHD, tongTienVAT)) {
                        JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Đã có lỗi");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                return;
            }
            for (ChiTietHoaDon cthd : dsCTHD) {
                Phong p = phong_dao.getPhongTheoMa(cthd.getPhong().getMaPhong());
                LoaiPhong lp = phong_dao.getLoaiPhongTheoMa(p.getLoaiPhong().getMaLP());
                PhieuDatPhong phieu = phieuDP.getPDPTheoMaPhong(p.getMaPhong());
                if (phieu != null) {
                    if (!phieu.isTrangThai()) {
                        phong_dao.capNhatTrangThaiPhong(p.getMaPhong(), "Đã được đặt");
                    }
                } else {
                    phong_dao.capNhatTrangThaiPhong(p.getMaPhong(), "Trống");
                }
            }

            if (hd.isTrangThai()) {
                JOptionPane.showMessageDialog(null, "Hóa đơn đã thanh toán");
                return;
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    public void xuatFilePDF(String path) {
//        btnThanhToan.setVisible(false);
//        btnXuatPDF.setVisible(false);
//        path = "hoaDonPDF\\" + path + ".pdf";
//        if (!path.matches("(.)+(\\.pdf)$")) {
//            path += ".pdf";
//        }
//        Container content = this.getContentPane();
//        int height = 650;
//        int width = 1200;
//        BufferedImage img = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2d = img.createGraphics();
//        content.printAll(g2d);
//        g2d.dispose();
//        try {
//            Document d = new Document();
//            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(path));
//            PdfDocument pdfD = new PdfDocument();
//            d.setPageSize(PageSize.A2);
//            d.open();
//
//            PdfContentByte contentByte = writer.getDirectContent();
//            Image image = Image.getInstance(contentByte, scaleImage(1190, height, img), 1);
//
//            PdfTemplate template = contentByte.createTemplate(width, height);
//            image.setAbsolutePosition(0, 0);
//            template.addImage(image);
//            contentByte.addTemplate(template, 0, 100);
//            d.close();
//
//            if (xacNhan == JOptionPane.YES_OPTION) {
//                Desktop.getDesktop().open(new File(path));
//            } else {
//                JOptionPane.showMessageDialog(this, "Xuất hóa đơn " + lblMaHD.getText().trim() + " Thành công");
//            }
//        } catch (IOException | DocumentException ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Không thành công");
//        }
//        btnXuatPDF.setVisible(true);
//        btnThanhToan.setVisible(true);
//        setVisible(false);
//        dispose();

        path = "hoaDonPDF\\" + path + ".pdf";
        if (!path.matches("(.)+(\\.pdf)$")) {
            path += ".pdf";
        }
//        JFileChooser j = new JFileChooser();
//        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int x = j.showSaveDialog(this);
//        if (x == JFileChooser.APPROVE_OPTION) {
//            path = j.getSelectedFile().getPath();
//        }
        Document doc = new Document();
        doc.setPageSize(PageSize._11X17);
        try {
            Locale localeVN = new Locale("vi", "VN");
            NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

            PdfWriter.getInstance(doc, new FileOutputStream(path));

            BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 15);
            Font fontKara = new Font(bf, 25);
            Font fontDC = new Font(bf, 25);
            doc.open();

            doc.add(new Paragraph("Karaoke NNice", fontKara));
            doc.add(new Paragraph("Địa chỉ: Nguyễn Văn Bảo, Phường 4, Gò Vấp", fontDC));
            doc.add(new Paragraph("Mã hóa đơn: " + lblMaHD.getText().trim(), font));
            doc.add(new Paragraph("Tên khách hàng: " + lblTenKH.getText().trim(), font));
            doc.add(new Paragraph("Ngày Lập: " + lblGN.getText().trim(), font));
            doc.add(new Paragraph("Tên nhân viên: " + lblTenNV.getText().trim(), font));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable tbl = new PdfPTable(6);

            PdfPCell cell0 = new PdfPCell();
            Phrase phr0 = new Phrase("Tên", font);
            cell0.setPhrase(phr0);

            PdfPCell cell1 = new PdfPCell();
            Phrase phr1 = new Phrase("Số lượng / giờ", font);
            cell1.setPhrase(phr1);

            PdfPCell cell2 = new PdfPCell();
            Phrase phr2 = new Phrase("Đơn giá", font);
            cell2.setPhrase(phr2);

            PdfPCell cell3 = new PdfPCell();
            Phrase phr3 = new Phrase("Giờ vào - ra", font);
            cell3.setPhrase(phr3);

            PdfPCell cell4 = new PdfPCell();
            Phrase phr4 = new Phrase("Đơn vị tính", font);
            cell4.setPhrase(phr4);

            PdfPCell cell5 = new PdfPCell();
            Phrase phr5 = new Phrase("Thành tiền", font);
            cell5.setPhrase(phr5);

            tbl.addCell(cell0);
            tbl.addCell(cell1);
            tbl.addCell(cell2);
            tbl.addCell(cell3);
            tbl.addCell(cell4);
            tbl.addCell(cell5);

            for (int i = 0; i < tableCTHD.getRowCount(); i++) {
                String ten = tableCTHD.getValueAt(i, 0).toString();
                String sl = tableCTHD.getValueAt(i, 1).toString();
                String dg = tableCTHD.getValueAt(i, 2).toString();
                String gvr = tableCTHD.getValueAt(i, 3).toString();
                String dvt = tableCTHD.getValueAt(i, 4).toString();
                String tt = tableCTHD.getValueAt(i, 5).toString();

                PdfPCell cTen = new PdfPCell();
                Phrase phrTen = new Phrase(ten, font);
                cTen.setPhrase(phrTen);

                PdfPCell cSL = new PdfPCell();
                Phrase phrSl = new Phrase(sl, font);
                cSL.setPhrase(phrSl);

                PdfPCell cDg = new PdfPCell();
                Phrase phrDg = new Phrase(dg, font);
                cDg.setPhrase(phrDg);

                PdfPCell cGvr = new PdfPCell();
                Phrase phrGvr = new Phrase(gvr, font);
                cGvr.setPhrase(phrGvr);

                PdfPCell cDvt = new PdfPCell();
                Phrase phrDvt = new Phrase(dvt, font);
                cDvt.setPhrase(phrDvt);

                PdfPCell cTT = new PdfPCell();
                Phrase phrTT = new Phrase(tt, font);
                cTT.setPhrase(phrTT);

                tbl.addCell(cTen);
                tbl.addCell(cSL);
                tbl.addCell(cDg);
                tbl.addCell(cGvr);
                tbl.addCell(cDvt);
                tbl.addCell(cTT);
            }

            doc.add(tbl);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Tổng cộng: " + lblTongCong.getText().trim(), font));
            doc.add(new Paragraph("Tổng tiền (VAT): " + lblTongTienVAT.getText().trim(), font));
            String maHD = lblMaHD.getText().trim();
            HoaDon hd = hd_dao.getHoaDonTheoMa(maHD);
            if (txtTienNhan.getText().trim().length() > 0) {
                Double tienNhan = Double.parseDouble(txtTienNhan.getText().trim());
                String sTienNhan = currencyVN.format(tienNhan);
                doc.add(new Paragraph("Tiền nhận: " + sTienNhan, font));
                doc.add(new Paragraph("Tiền thừa: " + lblTienThua.getText().trim(), font));
            } else if (txtTienNhan.getText().trim().length() == 0) {
                doc.add(new Paragraph("Tiền nhận: " + lblTongTienVAT.getText().trim(), font));
            }

//            if (!hd.isTrangThai()) {
//                doc.add(new Paragraph("Tiền nhận: " + txtTienNhan.getText().trim(), font));
//                doc.add(new Paragraph("Tiền thừa: " + lblTienThua.getText().trim(), font));
//            } else {
//                doc.add(new Paragraph("Đã thanh toán: " + lblTongTienVAT.getText().trim(), font));
//            }
            doc.close();
            if (xacNhan == JOptionPane.YES_OPTION) {
                Desktop.getDesktop().open(new File(path));
            } else {
                JOptionPane.showMessageDialog(this, "Xuất hóa đơn " + lblMaHD.getText().trim() + " Thành công");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Form_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Form_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Form_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        setVisible(false);
        dispose();
    }

//    public BufferedImage scaleImage(int WIDTH, int HEIGHT, BufferedImage img) {
//        BufferedImage bi = null;
//        try {
//            ImageIcon ii = new ImageIcon(img);
//            bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2d = (Graphics2D) bi.createGraphics();
//            g2d.addRenderingHints(
//                    new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
//            g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return bi;
//    }
    public boolean isNumber(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean validTxt() {
        String maHD = lblMaHD.getText().trim();
        HoaDon hd = hd_dao.getHoaDonTheoMa(maHD);
        String stienNhan = txtTienNhan.getText().trim();

        if (!isNumber(stienNhan)) {
            JOptionPane.showMessageDialog(null, "Tiền nhận phải lớn hơn hoặc bằng tổng tiền có VAT");
            txtTienNhan.requestFocus();
            return false;
        }

        double tienNhan = Double.parseDouble(stienNhan);
        double tongTienVAT = hd.tinhTongTienHDVAT();
        if (!(stienNhan.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Tiền nhận phải lớn hơn hoặc bằng tổng tiền có VAT");
            txtTienNhan.requestFocus();
            return false;
        } else if (tienNhan < tongTienVAT) {
            JOptionPane.showMessageDialog(null, "Tiền nhận phải lớn hơn hoặc bằng tổng tiền có VAT");
            txtTienNhan.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXuatPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGN;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongCong;
    private javax.swing.JLabel lblTongTienVAT;
    private javax.swing.JTable tableCTHD;
    private javax.swing.JTextField txtTienNhan;
    // End of variables declaration//GEN-END:variables
}
