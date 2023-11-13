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
import java.util.Date;

/**
 *
 * @author PC BAO THONG
 */
public class DichVu_DAO {

    public ArrayList<DichVu> layDSPhong() {
        ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement psm = null;
        try {
            String sql = "Select * from DichVu";
            psm = con.prepareStatement(sql);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                String maDV = rs.getString(1);
                String tenDV = rs.getString(2);
                String donViBan = rs.getString(3);
                int soLuong = rs.getInt(4);
                double donGia = rs.getDouble(5);
                Date hsd = rs.getDate(6);
                String xuatXu = rs.getString(7);
                Boolean tinhTrang = rs.getBoolean(8);
                DichVu dv = new DichVu(maDV, tenDV, donViBan, soLuong, donGia, hsd, xuatXu, tinhTrang);
                dsDV.add(dv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                psm.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return dsDV;
    }

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
}
