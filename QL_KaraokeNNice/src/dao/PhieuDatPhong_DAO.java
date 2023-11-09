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
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import java.security.Timestamp;

public class PhieuDatPhong_DAO {

    public ArrayList<PhieuDatPhong> getAllPDP() {
        ArrayList<PhieuDatPhong> dsPD = new ArrayList<PhieuDatPhong>();

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhieuDatPhong";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maPD = rs.getString(1);
                String maKH = rs.getString(2);
                String maNV = rs.getString(3);
                String maPhong = rs.getString(4);

                java.sql.Timestamp tsD = rs.getTimestamp(5);
                long timeD = tsD.getTime();
                Date dateD = new Date(timeD);
                Date gioDat = dateD;

                java.sql.Timestamp tsN = rs.getTimestamp(6);
                long timeN = tsN.getTime();
                Date dateN = new Date(timeN);
                Date gioNhan = dateN;

                Boolean trangThai = rs.getBoolean(7);

                PhieuDatPhong pdp = new PhieuDatPhong(maPD, new KhachHang(maKH), new NhanVien(maNV), new Phong(maPhong), gioDat, gioNhan, trangThai);
                dsPD.add(pdp);

            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dsPD;
    }

    public ArrayList<PhieuDatPhong> getAllPDPTheoSDT(String sdt) {
        ArrayList<PhieuDatPhong> dsPD = new ArrayList<PhieuDatPhong>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "SELECT * FROM PhieuDatPhong pd join KhachHang kh on pd.maKH = kh.maKH WHERE kh.sdtKH = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, sdt);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maPD = rs.getString(1);
                String maKH = rs.getString(2);
                String maNV = rs.getString(3);
                String maPhong = rs.getString(4);

                java.sql.Timestamp tsD = rs.getTimestamp(5);
                long timeD = tsD.getTime();
                Date dateD = new Date(timeD);
                Date gioDat = dateD;

                java.sql.Timestamp tsN = rs.getTimestamp(6);
                long timeN = tsN.getTime();
                Date dateN = new Date(timeN);
                Date gioNhan = dateN;

                Boolean trangThai = rs.getBoolean(7);

                PhieuDatPhong pdp = new PhieuDatPhong(maPD, new KhachHang(maKH), new NhanVien(maNV), new Phong(maPhong), gioDat, gioNhan, trangThai);
                dsPD.add(pdp);

            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dsPD;
    }

    public PhieuDatPhong getPDPTheoMa(String mPD) {
        PhieuDatPhong pd = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT TOP 1 * FROM PhieuDatPhong WHERE maPhieu = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mPD);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                pd = new PhieuDatPhong();
                pd.setMaPhieu(rs.getString(1));

                KhachHang kh = new KhachHang(rs.getString(2));
                pd.setKhachHang(kh);

                NhanVien nv = new NhanVien(rs.getString(3));
                pd.setNhanVienLap(nv);

                Phong p = new Phong(rs.getString(4));
                pd.setPhong(p);

                java.sql.Timestamp tsGioDat = rs.getTimestamp(5);
                long timeGD = tsGioDat.getTime();
                Date gioDP = new Date(timeGD);
                pd.setThoiGianDat(gioDP);

                java.sql.Timestamp tsGN = rs.getTimestamp(6);
                long timeGN = tsGN.getTime();
                Date gN = new Date(timeGN);
                pd.setThoiGianNhan(gN);

                pd.setTrangThai(rs.getBoolean(7));
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

        return pd;
    }

    public boolean updatePDP(PhieuDatPhong pd) {

        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("UPDATE PhieuDatPhong SET maPhong = ?, thoiGianNhan = ?, trangThai = ? WHERE maPhieu = ?");
            stmt.setString(1, pd.getPhong().getMaPhong());

            Date uTGN = pd.getThoiGianNhan();
            java.sql.Timestamp sqlTGN = new java.sql.Timestamp(uTGN.getTime());
            stmt.setTimestamp(2, sqlTGN);

            stmt.setBoolean(3, pd.isTrangThai());
            stmt.setString(4, pd.getMaPhieu());
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
