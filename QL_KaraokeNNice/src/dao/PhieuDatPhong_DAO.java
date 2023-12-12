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
import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class PhieuDatPhong_DAO {

    public boolean capNhatTrangThaiPhieuDatPhong(String maPhieu) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String sql = "UPDATE PhieuDatPhong SET trangThai = 1 WHERE maPhieu = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, maPhieu);
            n = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return n > 0;
    }

    public PhieuDatPhong getPDPTheoMaPhong(String mP) {
        PhieuDatPhong pd = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT * FROM PhieuDatPhong WHERE maPhong = ? and trangThai = 0";
            statement = con.prepareStatement(sql);
            statement.setString(1, mP);
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

    public boolean themPhieuDatPhong(PhieuDatPhong p) {
        PreparedStatement statement = null;
        int n = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "INSERT INTO PhieuDatPhong (maPhieu, maKH, maNV, maPhong, thoiGianDat, thoiGianNhan, trangThai) VALUES (?, ?, ?, ?, ?, ?, 0)";
            statement = con.prepareStatement(sql);
            statement.setString(1, p.getMaPhieu());
            statement.setString(2, p.getKhachHang().getMaKH());
            statement.setString(3, p.getNhanVienLap().getMaNV());
            statement.setString(4, p.getPhong().getMaPhong());
            Date thoiGianNhan = p.getThoiGianNhan();
            Date thoiGianDat = p.getThoiGianDat();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateDat = dateFormat.format(thoiGianDat);
            String dateNhan = dateFormat.format(thoiGianNhan);
            try {
                Date date = dateFormat.parse(dateDat);
                Date date1 = dateFormat.parse(dateNhan);
                Instant instant = date.toInstant();
                Instant instant1 = date1.toInstant();
                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(7));
                LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant1, ZoneOffset.ofHours(7));
                Timestamp sqlTimestamp = Timestamp.valueOf(localDateTime);
                Timestamp sqlTimestamp1 = Timestamp.valueOf(localDateTime1);
                statement.setTimestamp(5, sqlTimestamp);
                statement.setTimestamp(6, sqlTimestamp1);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public String maPDP_Auto() {
        String maMoi = null;
        String maHienTai = null;
        try {

            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 maPhieu FROM PhieuDatPhong ORDER BY maPhieu DESC";
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
            maMoi = "PDP00" + kyTuMoi;
        } else if (kySoMoi < 100) {
            String kyTuMoi = Integer.toString(kySoMoi);
            maMoi = "PDP0" + kyTuMoi;
        } else {
            String kyTuMoi = Integer.toString(Integer.parseInt(kyTuCuoi) + 1);
            maMoi = "PDP" + kyTuMoi;
        }
        return maMoi;
    }

    public ArrayList<PhieuDatPhong> getAllPDP() {
        ArrayList<PhieuDatPhong> dsPD = new ArrayList<PhieuDatPhong>();

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhieuDatPhong order by trangThai";
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
    public ArrayList<String> layDSKHDatPhong(){
        ArrayList<String> dsKH = new ArrayList<String>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT DISTINCT maKH FROM [DB_karaoke].[dbo].[PhieuDatPhong] WHERE trangThai = 0";
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dsKH.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();

            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return dsKH;
    }
    public String layKHDatPhong(String ma){
        String maKh = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT maKH FROM [DB_karaoke].[dbo].[PhieuDatPhong] WHERE maPhong = ? and trangThai = 0";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                maKh = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();

            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return maKh;
    }
}
