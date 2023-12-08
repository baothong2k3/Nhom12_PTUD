/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {

    public String maNV_Auto() {
        String maMoi = null;
        String maHienTai = null;

        try {

            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 maNV FROM NhanVien ORDER BY maNV DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                maHienTai = rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String kyTuCuoi = maHienTai.replaceAll("[^0-9]+", "");
        int kySoMoi = Integer.parseInt(kyTuCuoi) + 1;
        if (kySoMoi < 10) {
            String kyTuMoi = Integer.toString(kySoMoi);
            maMoi = "NV00" + kyTuMoi;
        } else if (kySoMoi < 100) {
            String kyTuMoi = Integer.toString(kySoMoi);
            maMoi = "NV0" + kyTuMoi;
        } else {
            String kyTuMoi = Integer.toString(Integer.parseInt(kyTuCuoi) + 1);
            maMoi = "NV" + kyTuMoi;
        }
        return maMoi;
    }

    public ArrayList<NhanVien> getAllNhanVien() {

        ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM NhanVien";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                String cccd = rs.getString(2);
                String ho = rs.getString(3);
                String ten = rs.getString(4);
                Date namSinh = rs.getDate(5);
                Boolean gioiTinh = rs.getBoolean(6);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String diaChi = rs.getString(9);
                String chucVu = rs.getString(10);
                String matKhau = rs.getString(11);
                Boolean trangThai = rs.getBoolean(12);

                NhanVien nv = new NhanVien(ma, cccd, ho, ten, namSinh, gioiTinh, sdt, email, diaChi, chucVu, matKhau, trangThai);
                dsnv.add(nv);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return dsnv;
    }

    public boolean insertNhanVien(NhanVien nv) {

        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {

            stmt = con.prepareStatement("INSERT INTO" + " NhanVien VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, nv.getMaNV());
            stmt.setString(2, nv.getSoCCCD());
            stmt.setString(3, nv.getHoNV());
            stmt.setString(4, nv.getTenNV());
            long time = nv.getNamSinh().getTime();
            java.sql.Date sqlDate = new java.sql.Date(time);
            stmt.setDate(5, sqlDate);
            stmt.setBoolean(6, nv.isGioiTinhNV());
            stmt.setString(7, nv.getSdtNV());
            stmt.setString(8, nv.getEmailNV());
            stmt.setString(9, nv.getDiaChiNV());
            stmt.setString(10, nv.getChucVu());
            stmt.setString(11, nv.getMatKhau());
            stmt.setBoolean(12, nv.isTrangThaiLamViec());

            n = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                stmt.close();

            } catch (SQLException e2) {
                e2.printStackTrace();

            }
        }
        return n > 0;
    }

    public boolean updateNhanVien(NhanVien nv) {

        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("UPDATE NhanVien SET CCCD= ?, hoNV = ?, tenNV = ?, namSinhNV = ?, gioiTinhNV = ?, sdtNV = ?, emailNV = ?, diaChiNV = ?, chucvu = ?, matkhau = ?, trangThaiLamViec = ? WHERE maNV = ?");
            stmt.setString(1, nv.getSoCCCD());
            stmt.setString(2, nv.getHoNV());
            stmt.setString(3, nv.getTenNV());
            long time = nv.getNamSinh().getTime();
            java.sql.Date sqlDate = new java.sql.Date(time);
            stmt.setDate(4, sqlDate);
            stmt.setBoolean(5, nv.isGioiTinhNV());
            stmt.setString(6, nv.getSdtNV());
            stmt.setString(7, nv.getEmailNV());
            stmt.setString(8, nv.getDiaChiNV());
            stmt.setString(9, nv.getChucVu());
            stmt.setString(10, nv.getMatKhau());
            stmt.setBoolean(11, nv.isTrangThaiLamViec());
            stmt.setString(12, nv.getMaNV());

            n = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                stmt.close();

            } catch (SQLException e2) {
                e2.printStackTrace();

            }
        }
        return n > 0;
    }

    public ArrayList<NhanVien> filterTuKhoaNV_Nam(String regex) {

        ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT * FROM NhanVien WHERE maNV LIKE ? OR CCCD LIKE ? OR hoNV LIKE ? OR tenNV LIKE ? OR namSinhNV LIKE ? OR sdtNV LIKE ? OR emailNV LIKE ? OR diaChiNV LIKE ? OR chucvu LIKE ? OR matkhau LIKE ? OR trangThaiLamViec LIKE ? and gioitinh = 1 Order by ten";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(2, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(3, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(4, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(5, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(6, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(7, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(8, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(9, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(10, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(11, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String ma = rs.getString(1);
                String cccd = rs.getString(2);
                String ho = rs.getString(3);
                String ten = rs.getString(4);
                Date ngaySinh = rs.getDate(5);
                Boolean gioiTinh = rs.getBoolean(6);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String diaChi = rs.getString(9);
                String chucVu = rs.getString(10);
                String matKhau = rs.getString(11);
                Boolean trangThai = rs.getBoolean(12);

                NhanVien nv = new NhanVien(ma, cccd, ho, ten, ngaySinh, gioiTinh, sdt, email, diaChi, chucVu, matKhau, trangThai);
                dsnv.add(nv);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return dsnv;

    }

    public ArrayList<NhanVien> filterTuKhoaNV_Nu(String regex) {

        ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT * FROM NhanVien WHERE maNV LIKE ? OR CCCD LIKE ? OR hoNV LIKE ? OR tenNV LIKE ? OR namSinhNV LIKE ? OR sdtNV LIKE ? OR emailNV LIKE ? OR diaChiNV LIKE ? OR chucvu LIKE ? OR matkhau LIKE ? OR trangThaiLamViec LIKE ? and gioitinh = 0 Order by ten";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(2, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(3, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(4, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(5, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(6, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(7, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(8, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(9, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(10, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(11, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%" + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String ma = rs.getString(1);
                String cccd = rs.getString(2);
                String ho = rs.getString(3);
                String ten = rs.getString(4);
                Date ngaySinh = rs.getDate(5);
                Boolean gioiTinh = rs.getBoolean(6);
                String sdt = rs.getString(7);
                String email = rs.getString(8);
                String diaChi = rs.getString(9);
                String chucVu = rs.getString(10);
                String matKhau = rs.getString(11);
                Boolean trangThai = rs.getBoolean(12);

                NhanVien nv = new NhanVien(ma, cccd, ho, ten, ngaySinh, gioiTinh, sdt, email, diaChi, chucVu, matKhau, trangThai);
                dsnv.add(nv);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return dsnv;

    }

    public NhanVien getNhanVienTheoMa(String mNV) {
        NhanVien nv = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT TOP 1 * FROM NhanVien WHERE maNV = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mNV);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setSoCCCD(rs.getString(2));
                nv.setHoNV(rs.getString(3));
                nv.setTenNV(rs.getString(4));
                nv.setNamSinh(rs.getDate(5));
                nv.setGioiTinhNV(rs.getBoolean(6));
                nv.setSdtNV(rs.getString(7));
                nv.setEmailNV(rs.getString(8));
                nv.setDiaChiNV(rs.getString(9));
                nv.setChucVu(rs.getString(10));
                nv.setMatKhau(rs.getString(11));
                nv.setTrangThaiLamViec(rs.getBoolean(12));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return nv;

    }
    
    public NhanVien getNVTheoSDT(String sdt) {
        NhanVien nv = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT TOP 1 * FROM NhanVien WHERE sdtNV = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, sdt);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setSoCCCD(rs.getString(2));
                nv.setHoNV(rs.getString(3));
                nv.setTenNV(rs.getString(4));
                nv.setNamSinh(rs.getDate(5));
                nv.setGioiTinhNV(rs.getBoolean(6));
                nv.setSdtNV(rs.getString(7));
                nv.setEmailNV(rs.getString(8));
                nv.setDiaChiNV(rs.getString(9));
                nv.setChucVu(rs.getString(10));
                nv.setMatKhau(rs.getString(11));
                nv.setTrangThaiLamViec(rs.getBoolean(12));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return nv;

    }
    public boolean updateMKNhanVien(String maNV, String mk) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("UPDATE NhanVien SET matkhau = ? WHERE maNV = ?");
            stmt.setString(1, mk);
            stmt.setString(2, maNV);
            n = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                stmt.close();

            } catch (SQLException e2) {
                e2.printStackTrace();

            }
        }
        return n > 0;
    }
}
