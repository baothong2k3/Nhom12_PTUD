/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import connectDB.ConnectDB;
import dao.DichVu_DAO;
import dao.HoaDon_DAO;
import entity.ChiTietDichVu;
import entity.DichVu;
import entity.HoaDon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowEvent;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

/**
 *
 * @author PC BAO THONG
 */
public class Form_CapNhatDVP extends javax.swing.JFrame {

    private DichVu_DAO dichvudao;
    private HoaDon_DAO hoaDonDao;
    private ArrayList<DichVu> dsDV;
    private DefaultTableModel modelDVDaThem;
    private DefaultTableModel modelDSDV;
    private String maP;

    /**
     * Creates new form FormThemDVP
     */
    public Form_CapNhatDVP(String maPhong) {
        try {
            ConnectDB.getInstance().connect();
//            System.out.println("Ket noi Database thanh cong");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        maP = maPhong;
        hoaDonDao = new HoaDon_DAO();
        dichvudao = new DichVu_DAO();
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        modelDSDV = (DefaultTableModel) tableDSDV.getModel();
        modelDVDaThem = (DefaultTableModel) tableDVDaThem.getModel();
        customTable();
        TableEvent();
        capNhatLable(maPhong);
        loadDVPDaThem(maP);
    }

    private void capNhatLable(String maPhong) {
        txtMaPhong.setText(maPhong);

        BigDecimal totalAmount = modelDVDaThem
                .getDataVector()
                .stream()
                .map(row -> (BigDecimal) ((Vector<?>) row).get(1))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total amount: " + totalAmount);
    }

    private void loadAllDV() {
        dsDV = new ArrayList<DichVu>();
        dsDV = dichvudao.getAllDichVu();
        for (DichVu dichVu : dsDV) {
            modelDSDV.addRow(new Object[]{dichVu.getMaDV(), dichVu.getTenDV(), dichVu.getDonGia(), dichVu.getSoLuongTon(), dichVu.getDonViBan()});
        }
    }

    private void loadDVPDaThem(String maP) {
        ArrayList<ChiTietDichVu> dsDVDaThem = new ArrayList<ChiTietDichVu>();
        dsDVDaThem = dichvudao.layDVDaThem(maP);
        for (ChiTietDichVu chiTietDichVu : dsDVDaThem) {
            String tenDV = chiTietDichVu.getDichVu().getTenDV();
            double donGia = chiTietDichVu.getDichVu().getDonGia();
            int soLuong = chiTietDichVu.getSoLuong();
            double thanhTien = soLuong * donGia;
            modelDVDaThem.addRow(new Object[]{tenDV, donGia, soLuong, thanhTien});
        }
    }

    private void TableEvent() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            //Nút thêm bên Danh sách dịch vụ đã thêm
            public void onAdd(int row) {
                if (tableDVDaThem.isEditing()) {
                    tableDVDaThem.getCellEditor().stopCellEditing();
                }
                System.out.println("Add row : " + row);
                int r = tableDVDaThem.getSelectedRow();
                String tenDV = (String) modelDVDaThem.getValueAt(r, 0);

                String tenDVDS;
                int rowCountDSDV = tableDSDV.getRowCount();
                for (int i = 0; i < rowCountDSDV; i++) {
                    tenDVDS = modelDSDV.getValueAt(i, 1).toString();
                    if (tenDV.equalsIgnoreCase(tenDVDS)) {
                        int soLuong = (int) modelDSDV.getValueAt(i, 3);
                        if (soLuong <= 0) {
                            JOptionPane.showMessageDialog(null, "Không đủ hàng trong kho");
                            return;
                        }
                        soLuong--;
                        modelDSDV.setValueAt(soLuong, i, 3);
                        break;
                    }
                }
                int soLuong = (int) modelDVDaThem.getValueAt(r, 2);
                soLuong++;
                Double donGia = (Double) modelDVDaThem.getValueAt(r, 1);
                Double thanhTien = donGia * soLuong;
                modelDVDaThem.setValueAt(soLuong, r, 2);
                modelDVDaThem.setValueAt(thanhTien, r, 3);
            }

            @Override
            public void onDelete(int row) {
                if (tableDVDaThem.isEditing()) {
                    tableDVDaThem.getCellEditor().stopCellEditing();
                }
                int r = tableDVDaThem.getSelectedRow();
                String tenDV = (String) modelDVDaThem.getValueAt(r, 0);
                int sl = (int) modelDVDaThem.getValueAt(r, 2);
                String tenDVDS;

                int rowCountDSDV = tableDSDV.getRowCount();
                for (int i = 0; i < rowCountDSDV; i++) {
                    tenDVDS = modelDSDV.getValueAt(i, 1).toString();
                    if (tenDV.equalsIgnoreCase(tenDVDS)) {
                        int soLuong = (int) modelDSDV.getValueAt(i, 3);
                        soLuong = soLuong + sl;
                        modelDSDV.setValueAt(soLuong, i, 3);
                        break;
                    }
                }
                modelDVDaThem.removeRow(r);
            }

            @Override
            public void onLess(int row) {
                if (tableDVDaThem.isEditing()) {
                    tableDVDaThem.getCellEditor().stopCellEditing();
                }
                System.out.println("Less row : " + row);
                int r = tableDVDaThem.getSelectedRow();

                String tenDV = (String) modelDVDaThem.getValueAt(r, 0);
                String tenDVDS;

                int rowCountDSDV = tableDSDV.getRowCount();
                for (int i = 0; i < rowCountDSDV; i++) {
                    tenDVDS = modelDSDV.getValueAt(i, 1).toString();
                    if (tenDV.equalsIgnoreCase(tenDVDS)) {
                        int soLuong = (int) modelDSDV.getValueAt(i, 3);
                        soLuong++;
                        modelDSDV.setValueAt(soLuong, i, 3);
                        break;
                    }
                }
                int soLuong = (int) modelDVDaThem.getValueAt(r, 2);
                soLuong--;
                Double donGia = (Double) modelDVDaThem.getValueAt(r, 1);
                Double thanhTien = (Double) modelDVDaThem.getValueAt(r, 3);
                thanhTien = thanhTien - donGia;
                if (soLuong == 0) {
                    modelDVDaThem.removeRow(r);
                } else {
                    modelDVDaThem.setValueAt(soLuong, r, 2);
                    modelDVDaThem.setValueAt(thanhTien, r, 3);
                }
            }
        };
        tableDVDaThem.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        tableDVDaThem.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
        tableDVDaThem.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });
        TableActionEvent1 event1 = new TableActionEvent1() {
            @Override
            public void onAdd(int row) {
                if (tableDSDV.isEditing()) {
                    tableDSDV.getCellEditor().stopCellEditing();
                }
//              Khi nhấn vào dấu + thì thêm vào table bên kia
                int r = tableDSDV.getSelectedRow();
                String tenDV = modelDSDV.getValueAt(r, 1).toString();
                String tenDVDT;
                int rowCount = tableDVDaThem.getRowCount();
                int flag = 0;
                for (int i = 0; i < rowCount; i++) {
                    tenDVDT = modelDVDaThem.getValueAt(i, 0).toString();
                    if (tenDVDT.equalsIgnoreCase(tenDV)) {
                        flag = 1;
                        int soLuong = (int) modelDVDaThem.getValueAt(i, 2);
                        int soL = (int) modelDSDV.getValueAt(r, 3);
                        if (soL <= 0) {
                            JOptionPane.showMessageDialog(null, "Không đủ hàng trong kho");
                            soL++;
                            soLuong--;
                            return;
                        }
                        soLuong++;
                        Double donGia = (Double) modelDVDaThem.getValueAt(i, 1);
                        Double thanhTien = donGia * soLuong;
                        modelDVDaThem.setValueAt(soLuong, i, 2);
                        modelDVDaThem.setValueAt(thanhTien, i, 3);
                        soL--;
                        modelDSDV.setValueAt(soL, r, 3);
                        break;
                    }
                }
                if (flag == 0) {
                    Double donGia = (Double) modelDSDV.getValueAt(r, 2);
                    Double thanhTien = donGia;
                    modelDVDaThem.addRow(new Object[]{tenDV, donGia, 1, thanhTien});
                    int soL = (int) modelDSDV.getValueAt(r, 3);
                    if (soL <= 0) {
                        JOptionPane.showMessageDialog(null, "Không đủ hàng trong kho");
                        return;
                    }
                    soL--;
                    modelDSDV.setValueAt(soL, r, 3);
                }
            }
        };
        tableDSDV.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender1());
        tableDSDV.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor1(event1));
        tableDSDV.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });

        //Tinh Thanh tien tren txt
        modelDVDaThem.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int totalAmountColumnIndex = 3;
                BigDecimal totalAmount = BigDecimal.ZERO;
                for (int i = 0; i < modelDVDaThem.getRowCount(); i++) {
                    Double priceDouble = (Double) modelDVDaThem.getValueAt(i, totalAmountColumnIndex);
                    BigDecimal price = BigDecimal.valueOf(priceDouble);
                    totalAmount = totalAmount.add(price);
                }
                txtTongTien.setText("" + totalAmount);
            }
        });
    }

    private void customTable() {
        tableDSDV.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        tableDSDV.getTableHeader().setOpaque(false);
        tableDSDV.getTableHeader().setBackground(new Color(32, 136, 203));
        tableDSDV.getTableHeader().setForeground(new Color(255, 255, 255));
//        jTable1.getColumnModel().getColumn(4).setCellEditor(new JXTable.GenericEditor());

        tableDVDaThem.getTableHeader().setFont(new Font("Cambria", Font.PLAIN, 16));
        tableDVDaThem.getTableHeader().setOpaque(false);
        tableDVDaThem.getTableHeader().setBackground(new Color(32, 136, 203));
        tableDVDaThem.getTableHeader().setForeground(new Color(255, 255, 255));
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

        panelHeader = new javax.swing.JPanel();
        panelC = new javax.swing.JPanel();
        lblTittle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaPhong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDVDaThem = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDSDV = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();

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

        txtMaPhong.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtMaPhong.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaPhong.setEnabled(false);
        txtMaPhong.setMinimumSize(new java.awt.Dimension(120, 20));
        txtMaPhong.setPreferredSize(new java.awt.Dimension(120, 30));
        txtMaPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhongActionPerformed(evt);
            }
        });
        jPanel6.add(txtMaPhong);

        jLabel3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel3.setText("Tổng tiền");
        jPanel6.add(jLabel3);

        txtTongTien.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTongTien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTongTien.setEnabled(false);
        txtTongTien.setMinimumSize(new java.awt.Dimension(120, 30));
        txtTongTien.setPreferredSize(new java.awt.Dimension(120, 30));
        jPanel6.add(txtTongTien);

        jPanel1.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jScrollPane2.setMaximumSize(new java.awt.Dimension(590, 350));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(590, 350));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(590, 350));

        tableDVDaThem.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        tableDVDaThem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên DV", "Đơn giá", "Đã thêm", "Thành tiền", "Hành động"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDVDaThem.setRowHeight(25);
        tableDVDaThem.setSelectionBackground(new java.awt.Color(204, 255, 255));
        jScrollPane2.setViewportView(tableDVDaThem);
        if (tableDVDaThem.getColumnModel().getColumnCount() > 0) {
            tableDVDaThem.getColumnModel().getColumn(2).setResizable(false);
            tableDVDaThem.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableDVDaThem.getColumnModel().getColumn(4).setResizable(false);
            tableDVDaThem.getColumnModel().getColumn(4).setPreferredWidth(50);
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

        txtTim.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        txtTim.setToolTipText("");
        txtTim.setMinimumSize(new java.awt.Dimension(0, 30));
        txtTim.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel3.add(txtTim);

        btnTim.setBackground(new java.awt.Color(153, 255, 204));
        btnTim.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnTim.setText("Tìm");
        btnTim.setMinimumSize(new java.awt.Dimension(0, 30));
        btnTim.setPreferredSize(new java.awt.Dimension(100, 30));
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        jPanel3.add(btnTim);

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setMinimumSize(new java.awt.Dimension(0, 30));
        btnXoa.setPreferredSize(new java.awt.Dimension(100, 30));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel3.add(btnXoa);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(590, 350));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(590, 350));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(590, 350));

        tableDSDV.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        tableDSDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã DV", "Tên DV", "Đơn giá", "Số lượng", "Đơn vị tính", "Thêm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDSDV.setRowHeight(25);
        tableDSDV.setSelectionBackground(new java.awt.Color(204, 255, 255));
        jScrollPane1.setViewportView(tableDSDV);
        if (tableDSDV.getColumnModel().getColumnCount() > 0) {
            tableDSDV.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableDSDV.getColumnModel().getColumn(2).setResizable(false);
            tableDSDV.getColumnModel().getColumn(2).setPreferredWidth(40);
            tableDSDV.getColumnModel().getColumn(3).setResizable(false);
            tableDSDV.getColumnModel().getColumn(3).setPreferredWidth(25);
            tableDSDV.getColumnModel().getColumn(4).setPreferredWidth(20);
            tableDSDV.getColumnModel().getColumn(5).setResizable(false);
            tableDSDV.getColumnModel().getColumn(5).setPreferredWidth(5);
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

        btnThem.setBackground(new java.awt.Color(153, 204, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setText("Cập nhật");
        btnThem.setPreferredSize(new java.awt.Dimension(140, 40));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem, new java.awt.GridBagConstraints());

        getContentPane().add(jPanel5, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String maDV = txtTim.getText().trim();
        DichVu dichVu = dichvudao.getDichVuTheoMa(maDV);
        modelDSDV.getDataVector().removeAllElements();
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
        modelDSDV.addRow(new Object[]{dichVu.getMaDV(), dichVu.getTenDV(), dichVu.getDonViBan(), dichVu.getSoLuongTon(), tien, sHSD, dichVu.getXuatXu(), tinhTrang});
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        txtTim.setText("");
        txtTim.requestFocus();
        modelDSDV.setRowCount(0);
        loadAllDV();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtMaPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPhongActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int check = 0;
        System.out.println(txtMaPhong.getText().trim());
        String maPhong = txtMaPhong.getText();

        HoaDon hd = hoaDonDao.getHoaDonTheoMaPhong_TrangThai(txtMaPhong.getText().trim());

        ArrayList<ChiTietDichVu> dsCTDV = new ArrayList<ChiTietDichVu>();

        ArrayList<ChiTietDichVu> dsDVDT = new ArrayList<ChiTietDichVu>();
        dsDVDT = dichvudao.layDVDaThem(maPhong);

        //chạy từ đầu đến cuối table
        for (int i = 0; i < modelDVDaThem.getRowCount(); i++) {
            String tenDV = modelDVDaThem.getValueAt(i, 0).toString();
            //chạy từ đầu đến cuối ds dv của database
            int flag = 0;
            for (ChiTietDichVu chiTietDichVu : dsDVDT) {
                String tenDVDaThem = chiTietDichVu.getDichVu().getTenDV();
                // nếu đã có trên database
                if (tenDVDaThem.equalsIgnoreCase(tenDV)) {
                    check = 1;
                    flag = 1;
                    int soLuongTable = (int) modelDVDaThem.getValueAt(i, 2);
                    int soLData = chiTietDichVu.getSoLuong();
                    int chenhLech = soLuongTable - soLData;
                    System.out.println("Chênh" + chenhLech);
                    dichvudao.updateSLSuDung(soLuongTable, chiTietDichVu.getDichVu().getMaDV(), hd.getMaHD(), maP);
                    if (chenhLech > 0) {
                        String maDV = chiTietDichVu.getDichVu().getMaDV();
                        DichVu dv = dichvudao.getDichVuTheoMa(maDV);
                        int slTon = dv.getSoLuongTon();
                        int slMoi = slTon - chenhLech;
                        dichvudao.updateSLTon(slMoi, maDV);
                    } else if (chenhLech < 0) {
                        String maDV = chiTietDichVu.getDichVu().getMaDV();
                        DichVu dv = dichvudao.getDichVuTheoMa(maDV);
                        int slTon = dv.getSoLuongTon();
                        int slMoi = slTon + chenhLech;
                        dichvudao.updateSLTon(slMoi, maDV);
                    }
                }
            }
            if (flag == 0) {
                DichVu dv = dichvudao.getDichVuTheoTen(tenDV);
                int sl = (int) modelDVDaThem.getValueAt(i, 2);
                int slTonMoi = dv.getSoLuongTon() - sl;
                dichvudao.updateSLTon(slTonMoi, dv.getMaDV());
                ChiTietDichVu ctdv = new ChiTietDichVu(dv, sl, hd, maPhong);
                dsCTDV.add(ctdv);
            }
        }
        ArrayList<ChiTietDichVu> dsDVDT2 = new ArrayList<ChiTietDichVu>();
        dsDVDT2 = dichvudao.layDVDaThem(maPhong);
        for (int i = 0; i < modelDVDaThem.getRowCount(); i++) {
            String tenDV = modelDVDaThem.getValueAt(i, 0).toString();
            for (ChiTietDichVu chiTietDichVu : dsDVDT2) {
                String tenDVDaThem = chiTietDichVu.getDichVu().getTenDV();
                if (tenDV.equalsIgnoreCase(tenDVDaThem)) {
                    dsDVDT2.remove(chiTietDichVu);
                    break;
                }
            }
        }
        if (dsDVDT2.size() > 0) {
            check = 1;
            for (ChiTietDichVu chiTietDichVu : dsDVDT2) {
                int sl = chiTietDichVu.getSoLuong();
                DichVu dv = dichvudao.getDichVuTheoMa(chiTietDichVu.getDichVu().getMaDV());
                int slM = dv.getSoLuongTon() + sl;
                dichvudao.updateSLTon(slM, dv.getMaDV());
            }
            dichvudao.xoaDichVuThem(dsDVDT2);
        }
        if (dsCTDV.size() > 0) {
            check = 1;
            if (hoaDonDao.themChiTietDichVu(dsCTDV)) {
                //
            } else {
                JOptionPane.showMessageDialog(null, "Đã có lỗi");
            }
        }
        if (check == 1) {
            JOptionPane.showMessageDialog(null, "Cập nhật dịch vụ thành công");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
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
    private javax.swing.JLabel lblTittle;
    private javax.swing.JPanel panelC;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JTable tableDSDV;
    private javax.swing.JTable tableDVDaThem;
    private javax.swing.JTextField txtMaPhong;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
