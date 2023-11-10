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
import entity.LoaiPhong;
import entity.Phong;

public class Phong_DAO {

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
        LoaiPhong lp = new LoaiPhong();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT TOP 1 * FROM LoaiPhong WHERE maLP = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mLP);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lp.setMaLP(mLP);
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
