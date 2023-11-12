package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;

public class Phong_DAO {

    public ArrayList<Phong> layDSPhong(){
        ArrayList<Phong> dsP = new ArrayList<Phong>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement psm = null;
        try {
            String sql = "Select * from PHONG";
            psm = con.prepareStatement(sql);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                String maPhong = rs.getString(1);
                String maLP = rs.getString(2);
                int sucNguoi = rs.getInt(3);
                String trangThai = rs.getString(4);
                boolean tinhTrang = rs.getBoolean(5);
                Phong p = new Phong(maPhong, new LoaiPhong(maLP), sucNguoi, trangThai, tinhTrang);
                dsP.add(p);
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
        return dsP;
    }

    public boolean kiemTraMaPhong(String mP) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {

            String sql = "SELECT * FROM Phong WHERE maPhong = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mP);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
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

        return false;
    }

    public Phong getPhongTheoMa(String mP) {
        Phong p = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT TOP 1 * FROM Phong WHERE maPhong = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mP);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                p = new Phong();
                p.setMaPhong(mP);
                LoaiPhong lp = new LoaiPhong(rs.getString(2));
                p.setLoaiPhong(lp);
                p.setSoNguoi(rs.getInt(3));
                p.setTrangThai(rs.getString(4));
                p.setTinhTrang(rs.getBoolean(5));
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

        return p;

    }

    public LoaiPhong getLoaiPhongTheoMa(String mLP) {
        LoaiPhong lp = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "SELECT TOP 1 * FROM LoaiPhong WHERE maLP = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mLP);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lp = new LoaiPhong();
                lp.setMaLP(rs.getString(1));
                lp.setTenLoaiPhong(rs.getString(2));
                lp.setGiaTien(rs.getDouble(3));
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
        return lp;
    }
}
