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
}
