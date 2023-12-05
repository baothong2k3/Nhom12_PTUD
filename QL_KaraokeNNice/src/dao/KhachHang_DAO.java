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
import entity.KhachHang;

public class KhachHang_DAO {

    public KhachHang getKhachHangTheoMa(String mKH) {
        KhachHang kh = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT TOP 1 * FROM KhachHang WHERE maKH = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mKH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                kh = new KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setSoCCCD(rs.getString(2));
                kh.setHoKH(rs.getString(3));
                kh.setTenKH(rs.getString(4));
                kh.setNamSinhKH(rs.getDate(5));
                kh.setGioiTinhKH(rs.getBoolean(6));
                kh.setSdtKH(rs.getString(7));
                kh.setEmailKH(rs.getString(8));
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

        return kh;

    }

    public KhachHang layKhachHangTheoSDT(String sdt) {
        KhachHang kh = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT Top 1 * FROM KhachHang WHERE sdtKH = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, sdt);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                kh = new KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setSoCCCD(rs.getString(2));
                kh.setHoKH(rs.getString(3));
                kh.setTenKH(rs.getString(4));
                kh.setNamSinhKH(rs.getDate(5));
                kh.setGioiTinhKH(rs.getBoolean(6));
                kh.setSdtKH(rs.getString(7));
                kh.setEmailKH(rs.getString(8));
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

        return kh;
    }

    public String maKH_Auto() {
        String maMoi = null;
        String maHienTai = null;

        try {

            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 maKH FROM KhachHang ORDER BY maKH DESC";
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
            maMoi = "KH00" + kyTuMoi;
        } else if (kySoMoi < 100) {
            String kyTuMoi = Integer.toString(kySoMoi);
            maMoi = "KH0" + kyTuMoi;
        } else {
            String kyTuMoi = Integer.toString(Integer.parseInt(kyTuCuoi) + 1);
            maMoi = "KH" + kyTuMoi;
        }
        return maMoi;
    }

    public ArrayList<KhachHang> getAllKhachHang() {

        ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "SELECT * FROM KhachHang";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                String cccd = rs.getString(2);
                String ho = rs.getString(3);
                String ten = rs.getString(4);
                Date ngaySinh = rs.getDate(5);
                Boolean gioiTinh = rs.getBoolean(6);
                String sdt = rs.getString(7);
                String email = rs.getString(8);

                KhachHang kh = new KhachHang(ma, cccd, ho, ten, ngaySinh, gioiTinh, sdt, email);
                dskh.add(kh);
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
        return dskh;
    }

    public boolean insertKhachHang(KhachHang kh) {

        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {

            stmt = con.prepareStatement("INSERT INTO" + " KhachHang VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, kh.getMaKH());
            stmt.setString(2, kh.getSoCCCD());
            stmt.setString(3, kh.getHoKH());
            stmt.setString(4, kh.getTenKH());
            long time = kh.getNamSinhKH().getTime();
            java.sql.Date sqlDate = new java.sql.Date(time);
            stmt.setDate(5, sqlDate);
            stmt.setBoolean(6, kh.isGioiTinhKH());
            stmt.setString(7, kh.getSdtKH());
            stmt.setString(8, kh.getEmailKH());
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

    public boolean updateKhachHang(KhachHang kh) {

        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("UPDATE KhachHang SET CCCD= ?, hoKH = ?, tenKH = ?, namSinhKH = ?, gioitinh = ?, sdtKH = ?, emailKH = ? WHERE maKH = ?");
            stmt.setString(1, kh.getSoCCCD());
            stmt.setString(2, kh.getHoKH());
            stmt.setString(3, kh.getTenKH());
            long time = kh.getNamSinhKH().getTime();
            java.sql.Date sqlDate = new java.sql.Date(time);
            stmt.setDate(4, sqlDate);
            stmt.setBoolean(5, kh.isGioiTinhKH());
            stmt.setString(6, kh.getSdtKH());
            stmt.setString(7, kh.getEmailKH());
            stmt.setString(8, kh.getMaKH());

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

    public ArrayList<KhachHang> filterTuKhoaKH_Nam(String regex) {

        ArrayList<KhachHang> dsKh = new ArrayList<KhachHang>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT * FROM KhachHang WHERE maKH LIKE ? OR CCCD LIKE ? OR hoKH LIKE ? OR tenKH LIKE ? OR namSinhKH LIKE ? OR sdtKH LIKE ? OR emailKH LIKE ? and gioitinh = 1 Order by ten";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(2, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(3, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(4, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(5, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(6, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(7, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
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

                KhachHang kh = new KhachHang(ma, cccd, ho, ten, ngaySinh, gioiTinh, sdt, email);
                dsKh.add(kh);

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

        return dsKh;

    }

    public ArrayList<KhachHang> filterTuKhoaKH_Nu(String regex) {

        ArrayList<KhachHang> dsKh = new ArrayList<KhachHang>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT * FROM KhachHang WHERE maKH LIKE ? OR CCCD LIKE ? OR hoKH LIKE ? OR tenKH LIKE ? OR namSinhKH LIKE ? OR sdtKH LIKE ? OR emailKH LIKE ? and gioitinh = 0 Order by ten";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(2, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(3, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(4, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(5, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(6, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
            stmt.setString(7, "%" + regex + "%" + "%" + "%" + "%" + "%" + "%");
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

                KhachHang kh = new KhachHang(ma, cccd, ho, ten, ngaySinh, gioiTinh, sdt, email);
                dsKh.add(kh);

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

        return dsKh;

    }

    public ArrayList<KhachHang> sortNam() {
        ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT * FROM KhachHang where gioitinh = 1 ORDER BY ten";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                String ma = rs.getString(1);
                String cccd = rs.getString(2);
                String ho = rs.getString(3);
                String ten = rs.getString(4);
                Date ngaySinh = rs.getDate(5);
                Boolean gioiTinh = rs.getBoolean(6);
                String sdt = rs.getString(7);
                String email = rs.getString(8);

                KhachHang kh = new KhachHang(ma, cccd, ho, ten, ngaySinh, gioiTinh, sdt, email);
                dskh.add(kh);
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

        return dskh;

    }

    public ArrayList<KhachHang> sortNu() {
        ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT * FROM KhachHang where gioitinh = 0 ORDER BY ten";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                String ma = rs.getString(1);
                String cccd = rs.getString(2);
                String ho = rs.getString(3);
                String ten = rs.getString(4);
                Date ngaySinh = rs.getDate(5);
                Boolean gioiTinh = rs.getBoolean(6);
                String sdt = rs.getString(7);
                String email = rs.getString(8);

                KhachHang kh = new KhachHang(ma, cccd, ho, ten, ngaySinh, gioiTinh, sdt, email);
                dskh.add(kh);
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

        return dskh;

    }
}
