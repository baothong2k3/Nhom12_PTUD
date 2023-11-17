/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.DichVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Time;
import java.util.Date;
import java.sql.Statement;

/**
 *
 * @author PC BAO THONG
 */
public class DichVu_DAO {

    public DichVu getDichVuTheoMa(String mDV) {
        DichVu dv = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT TOP 1 * FROM DichVu WHERE maDV = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mDV);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dv = new DichVu();
                dv.setMaDV(rs.getString(1));
                dv.setTenDV(rs.getString(2));
                dv.setDonViBan(rs.getString(3));
                dv.setSoLuongTon(rs.getInt(4));
                dv.setDonGia(rs.getDouble(5));
                dv.setHsd(rs.getDate(6));
                dv.setXuatXu(rs.getString(7));
                dv.setTinhTrang(rs.getBoolean(8));
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

        return dv;

    }
    
    public DichVu getDichVuTheoTen(String tenDV) {
        DichVu dv = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT TOP 1 * FROM DichVu WHERE tenDV = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, tenDV);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dv = new DichVu();
                dv.setMaDV(rs.getString(1));
                dv.setTenDV(rs.getString(2));
                dv.setDonViBan(rs.getString(3));
                dv.setSoLuongTon(rs.getInt(4));
                dv.setDonGia(rs.getDouble(5));
                dv.setHsd(rs.getDate(6));
                dv.setXuatXu(rs.getString(7));
                dv.setTinhTrang(rs.getBoolean(8));
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

        return dv;

    }

    public ArrayList<DichVu> getAllDichVu() {

        ArrayList<DichVu> dsdv = new ArrayList<DichVu>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM DichVu ORDER BY hsd DESC";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                String tendv = rs.getString(2);
                String dvban = rs.getString(3);
                int slton = rs.getInt(4);
                float dongia = rs.getFloat(5);
                Date hsd = rs.getDate(6);
                String xuatxu = rs.getString(7);
                Boolean tinhTrang = rs.getBoolean(8);
                DichVu dv = new DichVu(ma, tendv, dvban, slton, dongia, hsd, xuatxu, tinhTrang);
                dsdv.add(dv);
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
        return dsdv;
    }

    public ArrayList<DichVu> getBangDichVuTheoMa(String mDV) {
        ArrayList<DichVu> dsdv = new ArrayList<DichVu>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT * FROM DichVu WHERE maDV = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mDV);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                String tendv = rs.getString(2);
                String dvban = rs.getString(3);
                int slton = rs.getInt(4);
                float dongia = rs.getFloat(5);
                Date hsd = rs.getDate(6);
                String xuatxu = rs.getString(7);
                Boolean tinhTrang = rs.getBoolean(8);

                DichVu dv = new DichVu(ma, tendv, dvban, slton, dongia, hsd, xuatxu, tinhTrang);
                dsdv.add(dv);
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

        return dsdv;
    }

    public boolean insertDichVu(DichVu dv) {

        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {

            stmt = con.prepareStatement("INSERT INTO" + " DichVu VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, dv.getMaDV());
            stmt.setString(2, dv.getTenDV());
            stmt.setString(3, dv.getDonViBan());
            stmt.setInt(4, dv.getSoLuongTon());
            stmt.setDouble(5, dv.getDonGia());
            long time = dv.getHsd().getTime();
            java.sql.Date sqlDate = new java.sql.Date(time);
            stmt.setDate(6, sqlDate);
            stmt.setString(7, dv.getXuatXu());
            stmt.setBoolean(8, dv.isTinhTrang());

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

    public boolean updateDV(DichVu dv) {
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
            String sql = "update DichVu set tenDV = ?, donViBan = ? , soLuongTon = ?, donGia = ?, hsd = ?, xuatXu = ?, tinhTrang = ? where maDV = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, dv.getTenDV());
            stmt.setString(2, dv.getDonViBan());
            stmt.setInt(3, dv.getSoLuongTon());
            stmt.setDouble(4, dv.getDonGia());
            long time = dv.getHsd().getTime();
            java.sql.Date sqlDate = new java.sql.Date(time);
            stmt.setDate(5, sqlDate);
            stmt.setString(6, dv.getXuatXu());
            stmt.setBoolean(7, dv.isTinhTrang());
            stmt.setString(8, dv.getMaDV());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }
    
    public String maDV_Auto() {
        String maMoi = null;
        String maHienTai = null;

        try {

            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 maDV FROM DichVu ORDER BY maDV DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                maHienTai = rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String kyTuCuoi = maHienTai.replaceAll("[^0-9]+", "");
        String kyTuMoi = Integer.toString(Integer.parseInt(kyTuCuoi) + 1);

        maMoi = "DV" + kyTuMoi;
        return maMoi;
    }
    
    public boolean updateSLTon(int slTonMoi, String mDV) {
        PreparedStatement stmt = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
            String sql = "update DichVu set soLuongTon = ? where maDV = ?";         
            stmt.setInt(1, slTonMoi);           
            stmt.setString(2, mDV);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }
}
