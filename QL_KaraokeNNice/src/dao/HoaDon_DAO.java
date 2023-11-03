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
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDon_DAO {
    ArrayList<HoaDon> dsHD;
    public HoaDon_DAO(){
        dsHD = new ArrayList<HoaDon>();
    }
    public ArrayList<HoaDon> getAllHoaDon() {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        int i =0;
        try {
            String sql = "SELECT * FROM HoaDon";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maHD = rs.getString(1);
                String maKH = rs.getString(2);
                String maNV = rs.getString(3);
                Date gioNhanPhong = rs.getDate(4);
                Date gioKetThuc = rs.getDate(5);
                double tongTien = rs.getDouble(6);
                double tienKhach = rs.getDouble(7);
                Boolean trangThai = rs.getBoolean(8);

                HoaDon hd = new HoaDon(maHD, new KhachHang(maKH), gioNhanPhong, gioKetThuc, new NhanVien(maNV), tongTien, tienKhach, trangThai);
                dsHD.add(hd);
                
                i++;
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            System.out.println("Tong cong "+i+" hoa don");
        }
        return dsHD;
    }
}
