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
import entity.ChiTietDichVu;
import entity.ChiTietHoaDon;
import entity.DichVu;
import entity.HoaDon;
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

public class HoaDon_DAO {

    public boolean themHoaDon(HoaDon hd) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO" + " HoaDon VALUES(?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, hd.getMaHD());
            stmt.setString(2, hd.getKhachHang().getMaKH());
            stmt.setString(3, hd.getNhanVienLap().getMaNV());

            Date uTGN = hd.getNgayLap();
            java.sql.Timestamp sqlTGN = new java.sql.Timestamp(uTGN.getTime());
            stmt.setTimestamp(4, sqlTGN);

            stmt.setDouble(5, hd.getVAT());
            stmt.setDouble(6, hd.getTongTien());
            stmt.setBoolean(7, hd.isTrangThai());

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

    public String maHD_Auto() {
        String maMoi = null;
        String maHienTai = null;
        try {

            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 maHD FROM HoaDon ORDER BY maHD DESC";
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

        maMoi = "HD" + kyTuMoi;
        return maMoi;
    }

    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon order by trangThai";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maHD = rs.getString(1);
                String maKH = rs.getString(2);
                String maNV = rs.getString(3);

                java.sql.Timestamp tsGNP = rs.getTimestamp(4);
                long timeGNP = tsGNP.getTime();
                Date dateGNP = new Date(timeGNP);
                Date NgayLap = dateGNP;

                double VAT = rs.getDouble(5);
                double tongTien = rs.getDouble(6);
                Boolean trangThai = rs.getBoolean(7);

                HoaDon hd = new HoaDon(maHD, new KhachHang(maKH), new NhanVien(maNV), NgayLap, VAT, tongTien, trangThai);
                dsHD.add(hd);

            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dsHD;
    }

    public HoaDon getHoaDonTheoMa(String mHD) {
        HoaDon hd = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT TOP 1 * FROM HoaDon WHERE maHD = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mHD);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                hd = new HoaDon();
                hd.setMaHD(rs.getString(1));

                KhachHang kh = new KhachHang(rs.getString(2));
                hd.setKhachHang(kh);
                NhanVien nv = new NhanVien(rs.getString(3));
                hd.setNhanVienLap(nv);

                java.sql.Timestamp tsGioNhanPhong = rs.getTimestamp(4);
                long timeGNP = tsGioNhanPhong.getTime();
                Date dateGioNhanPhong = new Date(timeGNP);
                hd.setNgayLap(dateGioNhanPhong);

                hd.setVAT(rs.getDouble(5));
                hd.setTongTien(rs.getDouble(6));
                hd.setTrangThai(rs.getBoolean(7));
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

        return hd;
    }

    public HoaDon getHoaDonTheoMaPhong_TrangThai(String mP) {
        HoaDon hd = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT TOP 1 * FROM HoaDon hd join ChiTietHoaDon cthd on hd.maHD = cthd.maHD join Phong p on cthd.maPhong = p.maPhong WHERE p.maPhong = ? and hd.trangThai = 0";
            statement = con.prepareStatement(sql);
            statement.setString(1, mP);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                hd = new HoaDon();
                hd.setMaHD(rs.getString(1));

                KhachHang kh = new KhachHang(rs.getString(2));
                hd.setKhachHang(kh);
                NhanVien nv = new NhanVien(rs.getString(3));
                hd.setNhanVienLap(nv);

                java.sql.Timestamp tsGioNhanPhong = rs.getTimestamp(4);
                long timeGNP = tsGioNhanPhong.getTime();
                Date dateGioNhanPhong = new Date(timeGNP);
                hd.setNgayLap(dateGioNhanPhong);

                hd.setVAT(rs.getDouble(5));
                hd.setTongTien(rs.getDouble(6));
                hd.setTrangThai(rs.getBoolean(7));
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

        return hd;
    }

    public ArrayList<HoaDon> getAllHoaDonTheoSDT(String sdt) {
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "SELECT * FROM HoaDon hd join KhachHang kh on hd.maKH = kh.maKH WHERE kh.sdtKH = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, sdt);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString(1);
                String maKH = rs.getString(2);
                String maNV = rs.getString(3);

                java.sql.Timestamp tsGNP = rs.getTimestamp(4);
                long timeGNP = tsGNP.getTime();
                Date dateGNP = new Date(timeGNP);
                Date gioNhanPhong = dateGNP;

                Date NgayLap = dateGNP;

                double VAT = rs.getDouble(5);
                double tongTien = rs.getDouble(6);
                Boolean trangThai = rs.getBoolean(7);

                HoaDon hd = new HoaDon(maHD, new KhachHang(maKH), new NhanVien(maNV), NgayLap, VAT, tongTien, trangThai);
                dsHD.add(hd);

            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dsHD;
    }

    public ArrayList<ChiTietHoaDon> getAllCTHDTheoMaHD(String mHD) {
        ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "SELECT * FROM ChiTietHoaDon where maHD = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mHD);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                java.sql.Timestamp tsGNP = rs.getTimestamp(2);
                long timeGNP = tsGNP.getTime();
                Date dateGNP = new Date(timeGNP);
                Date gioNhanPhong = dateGNP;

                java.sql.Timestamp tsGKT = rs.getTimestamp(3);
                long time = tsGKT.getTime();
                Date dateGKT = new Date(time);
                Date gioKetThuc = dateGKT;

                String maPhong = rs.getString(4);

                ChiTietHoaDon cthd = new ChiTietHoaDon(new HoaDon(mHD), gioNhanPhong, gioKetThuc, new Phong(maPhong));
                dsCTHD.add(cthd);

            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dsCTHD;
    }

    public ChiTietHoaDon getCTHDTheoMaHD(String mHD) {
        ChiTietHoaDon cthd = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "SELECT top 1 * FROM ChiTietHoaDon where maHD = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mHD);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                java.sql.Timestamp tsGNP = rs.getTimestamp(2);
                long timeGNP = tsGNP.getTime();
                Date dateGNP = new Date(timeGNP);
                Date gioNhanPhong = dateGNP;

                java.sql.Timestamp tsGKT = rs.getTimestamp(3);
                long time = tsGKT.getTime();
                Date dateGKT = new Date(time);
                Date gioKetThuc = dateGKT;

                String maPhong = rs.getString(4);

                cthd = new ChiTietHoaDon(new HoaDon(mHD), gioNhanPhong, gioKetThuc, new Phong(maPhong));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return cthd;
    }

    public ChiTietDichVu getCTDVTheoMaHD(String mHD) {
        ChiTietDichVu ctdv = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "SELECT top 1 * FROM ChiTietDichVu where maHD = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mHD);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maDV = rs.getString(1);
                int soLuong = rs.getInt(2);

                ctdv = new ChiTietDichVu(new DichVu(maDV), soLuong, new HoaDon(mHD));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ctdv;
    }

    public ArrayList<ChiTietDichVu> getAllCTDVTheoMaHD(String mHD) {
        ArrayList<ChiTietDichVu> dsCTDV = new ArrayList<ChiTietDichVu>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "SELECT * FROM ChiTietDichVu where maHD = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mHD);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maDV = rs.getString(1);
                int soLuong = rs.getInt(2);

                ChiTietDichVu ctdv = new ChiTietDichVu(new DichVu(maDV), soLuong, new HoaDon(mHD));
                dsCTDV.add(ctdv);

            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dsCTDV;
    }

    public boolean updateTrangThai_TongTien_HD(String maHD, Double tongTien) {

        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("UPDATE HoaDon SET tongTien = ?, trangThai = ? WHERE maHD = ?");
            stmt.setDouble(1, tongTien);
            stmt.setBoolean(2, true);
            stmt.setString(3, maHD);
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

    public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO ChiTietHoaDon VALUES(?, ?, ?, ?)");
            stmt.setString(1, cthd.getHoaDon().getMaHD());

            Date uTGN = cthd.getGioNhanPhong();
            java.sql.Timestamp sqlTGN = new java.sql.Timestamp(uTGN.getTime());
            stmt.setTimestamp(2, sqlTGN);

            Date uGKT = cthd.getGioKetThuc();
            java.sql.Timestamp sqlGKT = new java.sql.Timestamp(uGKT.getTime());
            stmt.setTimestamp(3, sqlGKT);
            stmt.setString(4, cthd.getPhong().getMaPhong());

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

    public boolean themChiTietDichVu(ArrayList<ChiTietDichVu> dsCTDV) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            for (ChiTietDichVu ctdv : dsCTDV) {             
                stmt = con.prepareStatement("INSERT INTO ChiTietDichVu VALUES(?, ?, ?)");
                stmt.setString(1, ctdv.getDichVu().getMaDV());
                stmt.setInt(2, ctdv.getSoLuong());
                stmt.setString(3, ctdv.getHoaDon().getMaHD());
                n = stmt.executeUpdate();
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
        return true;
    }
}
