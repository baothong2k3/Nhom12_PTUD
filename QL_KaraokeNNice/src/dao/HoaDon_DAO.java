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
import java.util.Calendar;
import java.util.List;

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
        int kySoMoi = Integer.parseInt(kyTuCuoi) + 1;
        if (kySoMoi < 10) {
            String kyTuMoi = Integer.toString(kySoMoi);
            maMoi = "HD00" + kyTuMoi;
        } else if (kySoMoi < 100) {
            String kyTuMoi = Integer.toString(kySoMoi);
            maMoi = "HD0" + kyTuMoi;
        } else {
            String kyTuMoi = Integer.toString(Integer.parseInt(kyTuCuoi) + 1);
            maMoi = "HD" + kyTuMoi;
        }
        return maMoi;
    }

    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon order by trangThai, NgayLap DESC";
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
        HoaDon hd = new HoaDon();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT TOP 1 * FROM HoaDon hd join ChiTietHoaDon cthd on hd.maHD = cthd.maHD join Phong p on cthd.maPhong = p.maPhong WHERE p.maPhong = ? and hd.trangThai = 0";
            statement = con.prepareStatement(sql);
            statement.setString(1, mP);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

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
                System.out.println(hd.getMaHD());
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
                String maPhong = rs.getString(4);
                ctdv = new ChiTietDichVu(new DichVu(maDV), soLuong, new HoaDon(mHD), maPhong);
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
                String maPhong = rs.getString(4);
                ChiTietDichVu ctdv = new ChiTietDichVu(new DichVu(maDV), soLuong, new HoaDon(mHD), maPhong);
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
                stmt = con.prepareStatement("INSERT INTO ChiTietDichVu VALUES(?, ?, ?, ?)");
                stmt.setString(1, ctdv.getDichVu().getMaDV());
                stmt.setInt(2, ctdv.getSoLuong());
                stmt.setString(3, ctdv.getHoaDon().getMaHD());
                stmt.setString(4, ctdv.getmaPhong());
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

    public boolean updateCTHD_GKT(String mHD, Date gkt) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("Update ChiTietHoaDon set gioKetThuc = ? where maHD = ?");

            java.sql.Timestamp sqlGKT = new java.sql.Timestamp(gkt.getTime());
            stmt.setTimestamp(1, sqlGKT);

            stmt.setString(2, mHD);
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

    public boolean check_HD_ChuaThanhToan(String maKH) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM HoaDon WHERE maKH = ? and trangthai = 0";
            statement = con.prepareStatement(sql);
            statement.setString(1, maKH);
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

    public HoaDon getHoaDonTheoKH_TrangThai(String maKH) {
        HoaDon hd = new HoaDon();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {

            String sql = "SELECT top 1 * FROM HoaDon WHERE maKH = ? and trangthai = 0";
            statement = con.prepareStatement(sql);
            statement.setString(1, maKH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

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
                System.out.println(hd.getMaHD());
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        return hd;
    }

    public ArrayList<Integer> layNamTuHoaDon() {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        ArrayList<Integer> ngayLap = new ArrayList<>();
        try {
            String sql = "select year(ngayLap) from HoaDon group by  year(ngayLap)";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ngayLap.add(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return ngayLap;
    }

    public Double[] layDoanhThuTheoNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        Double[] doanhThu = new Double[12];
        for (int i = 0; i < doanhThu.length; i++) {
            doanhThu[i] = 0.0;
        }
        try {
            String sql = "SELECT MONTH(NgayLap) AS thang, SUM(tongTien) AS tong_tien FROM [DB_karaoke].[dbo].[HoaDon] WHERE YEAR(NgayLap) = ? GROUP BY MONTH(NgayLap)";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                doanhThu[rs.getInt(1) - 1] = rs.getDouble(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return doanhThu;
    }

    public Double layTongDoanhThuTheoNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double doanhThu = 0.0;
        try {
            String sql = "SELECT SUM(tongTien) AS doanhThu FROM HoaDon WHERE YEAR(NgayLap) = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return doanhThu;
    }

    public Double layTongDoanhThuDVTheoNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double doanhThu = 0.0;
        try {
            String sql = "SELECT SUM(donGia * ChiTietDichVu.soLuong) AS doanhThu FROM HoaDon INNER JOIN ChiTietDichVu ON HoaDon.maHD = ChiTietDichVu.maHD INNER JOIN DichVu ON ChiTietDichVu.maDV = DichVu.maDV Where YEAR(HoaDon.NgayLap) = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return doanhThu;
    }

    public Double layTongDoanhThuTheoPVTheoNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double doanhThu = 0.0;
        try {
            String sql = "SELECT DATEDIFF(HOUR, gioNhanPhong, gioKetThuc) AS ThoiGian FROM HoaDon INNER JOIN ChiTietHoaDon ON HoaDon.maHD = ChiTietHoaDon.maHD INNER JOIN Phong ON ChiTietHoaDon.maPhong = Phong.maPhong INNER JOIN LoaiPhong ON Phong.maLP = LoaiPhong.maLP where Phong.maLP = 'PV001' and YEAR(NgayLap) = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                doanhThu += rs.getDouble(1) * 280000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return doanhThu;
    }
//Thống kê theo tháng

    public ArrayList<Integer> layThangTuHoaDon(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        ArrayList<Integer> thangLap = new ArrayList<>();
        try {
            String sql = "select month(ngayLap) from HoaDon where YEAR(NgayLap) = ? group by  month(ngayLap) ";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                thangLap.add(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return thangLap;
    }

    public Double layTongDoanhThuTheoThang(String nam, String thang) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double doanhThu = 0.0;
        try {
            String sql = "SELECT month(NgayLap) AS thang, SUM(tongTien) AS doanhThu FROM [DB_karaoke].[dbo].[HoaDon] WHERE YEAR(NgayLap) = ? and month(NgayLap) = ? GROUP BY month(NgayLap)";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            statement.setString(2, thang);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getDouble(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return doanhThu;
    }

    public Double[] layDoanhThuTheoThang(String nam, String thang, int lastDay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        Double[] doanhThu = new Double[lastDay];
        for (int i = 0; i < doanhThu.length; i++) {
            doanhThu[i] = 0.0;
        }
        try {
            String sql = "SELECT DAY(NgayLap) AS NgayLap, SUM(tongTien) AS TongDoanhThu FROM [DB_karaoke].[dbo].[HoaDon] WHERE YEAR(NgayLap) = ? AND MONTH(NgayLap) = ? GROUP BY DAY(NgayLap)";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            statement.setString(2, thang);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                doanhThu[rs.getInt(1) - 1] = rs.getDouble(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return doanhThu;
    }

    public Double layTongDoanhThuDVTheoThang(String nam, String thang) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double doanhThu = 0.0;
        try {
            String sql = "SELECT SUM(donGia * ChiTietDichVu.soLuong) AS doanhThu FROM HoaDon INNER JOIN ChiTietDichVu ON HoaDon.maHD = ChiTietDichVu.maHD INNER JOIN DichVu ON ChiTietDichVu.maDV = DichVu.maDV Where YEAR(HoaDon.NgayLap) = ? and month(HoaDon.NgayLap) = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            statement.setString(2, thang);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return doanhThu;
    }

    public Double layTongDoanhThuTheoPVTheoThang(String nam, String thang) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double doanhThu = 0.0;
        try {
            String sql = "SELECT DATEDIFF(HOUR, gioNhanPhong, gioKetThuc) AS ThoiGian FROM HoaDon INNER JOIN ChiTietHoaDon ON HoaDon.maHD = ChiTietHoaDon.maHD INNER JOIN Phong ON ChiTietHoaDon.maPhong = Phong.maPhong INNER JOIN LoaiPhong ON Phong.maLP = LoaiPhong.maLP where Phong.maLP = 'PV001' and YEAR(NgayLap) = ? and month(NgayLap) = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            statement.setString(2, thang);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                doanhThu += rs.getDouble(1) * 280000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return doanhThu;
    }
//  Thống kê ngày

    public Double layTongDoanhThuTheoNgay(String ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double doanhThu = 0.0;
        try {
            String sql = "SELECT SUM(tongTien) AS tongDoanhThu FROM [DB_karaoke].[dbo].[HoaDon] WHERE NgayLap BETWEEN ? AND ?";
            statement = con.prepareStatement(sql);
            String ngay1 = ngay + " 08:00:00";
            String ngay2 = ngay + " 23:59:00";
            statement.setString(1, ngay1);
            statement.setString(2, ngay2);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return doanhThu;
    }

    public Double layTongDoanhThuDVTheoNgay(String ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double doanhThu = 0.0;
        String ngay1 = ngay + " 08:00:00";
        String ngay2 = ngay + " 22:00:00";
        try {
            String sql = "SELECT SUM(donGia * ChiTietDichVu.soLuong) AS doanhThu FROM HoaDon INNER JOIN ChiTietDichVu ON HoaDon.maHD = ChiTietDichVu.maHD INNER JOIN DichVu ON ChiTietDichVu.maDV = DichVu.maDV Where NgayLap BETWEEN ? AND ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, ngay1);
            statement.setString(2, ngay2);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return doanhThu;
    }

    public Double layTongDoanhThuTheoPVTheoNgay(String ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double doanhThu = 0.0;
        String ngay1 = ngay + " 08:00:00";
        String ngay2 = ngay + " 22:00:00";
        try {
            String sql = "SELECT DATEDIFF(HOUR, gioNhanPhong, gioKetThuc) AS ThoiGian FROM HoaDon INNER JOIN ChiTietHoaDon ON HoaDon.maHD = ChiTietHoaDon.maHD INNER JOIN Phong ON ChiTietHoaDon.maPhong = Phong.maPhong INNER JOIN LoaiPhong ON Phong.maLP = LoaiPhong.maLP where Phong.maLP = 'PV001' and NgayLap BETWEEN ? AND ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, ngay1);
            statement.setString(2, ngay2);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                doanhThu += rs.getDouble(1) * 280000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return doanhThu;
    }

    //KH
    public int demSoKH() {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int so = 0;
        try {
            String sql = "SELECT COUNT(DISTINCT [maKH]) AS SoLuongKhachHang FROM [DB_karaoke].[dbo].[HoaDon]";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                so = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return so;
    }

    public int demSoKHTheoTG(int ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int so = 0;
        try {
            String sql = "SELECT COUNT(DISTINCT [maKH]) AS SoLuongKhachHang FROM [DB_karaoke].[dbo].[HoaDon] WHERE NgayLap BETWEEN DATEADD(DAY, -?, GETDATE()) AND GETDATE()";
            statement = con.prepareStatement(sql);
            statement.setInt(1, ngay);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                so = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return so;
    }

    public String[] layDoanhThuTheoKH() {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int so = demSoKH() * 2;
        String[] maKHDT = new String[so];
        try {
            String sql = "SELECT maKH, SUM(tongTien) AS tongTien FROM [DB_karaoke].[dbo].[HoaDon] GROUP BY maKH ORDER BY tongTien DESC";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            int i = 0;
            while (rs.next()) {
                maKHDT[i] = rs.getString(1);
                maKHDT[i + 1] = rs.getString(2);
                i = i + 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return maKHDT;
    }

    public String[] layDoanhThuTheoKHTheoTG(int ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int so = demSoKHTheoTG(ngay) * 2;
        String[] maKHDT = new String[so];
        try {
            String sql = "SELECT [maKH], SUM([tongTien]) AS TongDoanhThu FROM [DB_karaoke].[dbo].[HoaDon] WHERE NgayLap BETWEEN DATEADD(DAY, -?, GETDATE()) AND GETDATE() GROUP BY [maKH] ORDER BY TongDoanhThu DESC";
            statement = con.prepareStatement(sql);
            statement.setInt(1, ngay);
            ResultSet rs = statement.executeQuery();
            int i = 0;
            while (rs.next()) {
                maKHDT[i] = rs.getString(1);
                maKHDT[i + 1] = rs.getString(2);
                i = i + 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return maKHDT;
    }

    public int demSoKHTheoNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int so = 0;
        try {
            String sql = "SELECT COUNT(DISTINCT [maKH]) AS SoLuongKhachHang FROM [DB_karaoke].[dbo].[HoaDon] WHERE YEAR([NgayLap]) = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                so = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return so;
    }

    public String[] layDoanhThuTheoKHTheoNam(String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int so = demSoKHTheoNam(nam) * 2;
        String[] maKHDT = new String[so];
        try {
            String sql = "SELECT [maKH], SUM([tongTien]) AS TongDoanhThu FROM [DB_karaoke].[dbo].[HoaDon] WHERE YEAR([NgayLap]) = ? GROUP BY [maKH] ORDER BY TongDoanhThu DESC";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            ResultSet rs = statement.executeQuery();
            int i = 0;
            while (rs.next()) {
                maKHDT[i] = rs.getString(1);
                maKHDT[i + 1] = rs.getString(2);
                i = i + 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return maKHDT;
    }
//

    public double layTongDoanhThuTheoKHTheoNam(String nam, String maKH) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double tong = 0.0;
        try {
            String sql = "SELECT SUM([tongTien]) AS TongDoanhThu FROM [DB_karaoke].[dbo].[HoaDon] WHERE YEAR([NgayLap]) = ? and maKH= ? GROUP BY [maKH] ORDER BY TongDoanhThu DESC";
            statement = con.prepareStatement(sql);
            statement.setString(1, nam);
            statement.setString(2, maKH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tong = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return tong;
    }

    public double layTongDoanhThuTheoKHTheoTG(String maKH, int tg) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double tong = 0.0;
        try {
            String sql = "SELECT SUM([tongTien]) AS TongDoanhThu FROM [DB_karaoke].[dbo].[HoaDon] WHERE NgayLap BETWEEN DATEADD(DAY, -?, GETDATE()) AND GETDATE()  and maKH= ? GROUP BY [maKH] ORDER BY TongDoanhThu DESC";
            statement = con.prepareStatement(sql);
            statement.setInt(1, tg);
            statement.setString(2, maKH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tong = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return tong;
    }

    public Double layTongDoanhThuPVTheoKHTheoTG(String makh, int ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double tg = 0.0;
        try {
            String sql = "SELECT DATEDIFF(HOUR, ChiTietHoaDon.gioNhanPhong, ChiTietHoaDon.gioKetThuc) AS thoiGianSuDung FROM HoaDon INNER JOIN ChiTietHoaDon ON HoaDon.maHD = ChiTietHoaDon.maHD INNER JOIN Phong ON ChiTietHoaDon.maPhong = Phong.maPhong WHERE Phong.maLP = 'PV001' AND HoaDon.maKH = ? AND  NgayLap BETWEEN DATEADD(DAY, -?, GETDATE()) AND GETDATE()";
            statement = con.prepareStatement(sql);
            statement.setString(1, makh);
            statement.setInt(2, ngay);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tg = tg + rs.getDouble(1);
            }
            tg = tg * 280000;
            double thue = tg * 0.1;
            tg = tg + thue;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return tg;
    }

    public Double layTongDoanhThuPTTheoKHTheoTG(String makh, int ngay) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double tg = 0.0;
        try {
            String sql = "SELECT DATEDIFF(HOUR, ChiTietHoaDon.gioNhanPhong, ChiTietHoaDon.gioKetThuc) AS thoiGianSuDung FROM HoaDon INNER JOIN ChiTietHoaDon ON HoaDon.maHD = ChiTietHoaDon.maHD INNER JOIN Phong ON ChiTietHoaDon.maPhong = Phong.maPhong WHERE Phong.maLP = 'PT001' AND HoaDon.maKH = ? AND  NgayLap BETWEEN DATEADD(DAY, -?, GETDATE()) AND GETDATE()";
            statement = con.prepareStatement(sql);
            statement.setString(1, makh);
            statement.setInt(2, ngay);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tg = tg + rs.getDouble(1);
            }
            tg = tg * 280000;
            double thue = tg * 0.1;
            tg = tg + thue;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return tg;
    }

    public Double layTongDoanhThuPVTheoKHTheoALLTG(String makh) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double tg = 0.0;
        try {
            String sql = "SELECT DATEDIFF(HOUR, ChiTietHoaDon.gioNhanPhong, ChiTietHoaDon.gioKetThuc) AS thoiGianSuDung FROM HoaDon INNER JOIN ChiTietHoaDon ON HoaDon.maHD = ChiTietHoaDon.maHD INNER JOIN Phong ON ChiTietHoaDon.maPhong = Phong.maPhong WHERE Phong.maLP = 'PV001' AND HoaDon.maKH = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, makh);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tg = tg + rs.getDouble(1);
            }
            tg = tg * 280000;
            double thue = tg * 0.1;
            tg = tg + thue;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return tg;
    }

    public Double layTongDoanhThuPTTheoKHTheoALLTG(String makh) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double tg = 0.0;
        try {
            String sql = "SELECT DATEDIFF(HOUR, ChiTietHoaDon.gioNhanPhong, ChiTietHoaDon.gioKetThuc) AS thoiGianSuDung FROM HoaDon INNER JOIN ChiTietHoaDon ON HoaDon.maHD = ChiTietHoaDon.maHD INNER JOIN Phong ON ChiTietHoaDon.maPhong = Phong.maPhong WHERE Phong.maLP = 'PT001' AND HoaDon.maKH = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, makh);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tg = tg + rs.getDouble(1);
            }
            tg = tg * 280000;
            double thue = tg * 0.1;
            tg = tg + thue;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return tg;
    }

    public Double layTongDoanhThuPVTheoKHTheoNam(String makh, String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double tg = 0.0;
        try {
            String sql = "SELECT DATEDIFF(HOUR, ChiTietHoaDon.gioNhanPhong, ChiTietHoaDon.gioKetThuc) AS thoiGianSuDung FROM HoaDon INNER JOIN ChiTietHoaDon ON HoaDon.maHD = ChiTietHoaDon.maHD INNER JOIN Phong ON ChiTietHoaDon.maPhong = Phong.maPhong WHERE Phong.maLP = 'PV001' AND HoaDon.maKH = ? and YEAR([NgayLap]) = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, makh);
            statement.setString(2, nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tg = tg + rs.getDouble(1);
            }
            tg = tg * 280000;
            double thue = tg * 0.1;
            tg = tg + thue;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return tg;
    }

    public Double layTongDoanhThuPTTheoKHTheoNam(String makh, String nam) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double tg = 0.0;
        try {
            String sql = "SELECT DATEDIFF(HOUR, ChiTietHoaDon.gioNhanPhong, ChiTietHoaDon.gioKetThuc) AS thoiGianSuDung FROM HoaDon INNER JOIN ChiTietHoaDon ON HoaDon.maHD = ChiTietHoaDon.maHD INNER JOIN Phong ON ChiTietHoaDon.maPhong = Phong.maPhong WHERE Phong.maLP = 'PT001' AND HoaDon.maKH = ? and YEAR([NgayLap]) = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, makh);
            statement.setString(2, nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tg = tg + rs.getDouble(1);
            }
            tg = tg * 280000;
            double thue = tg * 0.1;
            tg = tg + thue;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return tg;
    }

    public double layTongDoanhThuTheoKH(String maKH) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        double tong = 0.0;
        try {
            String sql = "SELECT SUM([tongTien]) AS TongDoanhThu FROM [DB_karaoke].[dbo].[HoaDon] WHERE maKH= ? GROUP BY [maKH] ORDER BY TongDoanhThu DESC";
            statement = con.prepareStatement(sql);
            statement.setString(1, maKH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tong = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return tong;
    }

    public String layKHDungPhong(String ma) {
        String maKh = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT hoadon.maKH FROM [DB_karaoke].[dbo].[HoaDon] AS hoadon INNER JOIN [DB_karaoke].[dbo].[ChiTietHoaDon] AS chitiethoadon ON hoadon.maHD = chitiethoadon.maHD where hoadon.trangThai = 0 and chitiethoadon.maPhong = ?";
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

    public ArrayList<String> layDSKHDungPhong() {
        ArrayList<String> dsKH = new ArrayList<String>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT DISTINCT maKH FROM HoaDon WHERE trangThai = 0";
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

    public ArrayList<String> layDSDVTheoHD() {
        ArrayList<String> dsKH = new ArrayList<String>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT DISTINCT maKH FROM HoaDon WHERE trangThai = 0";
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

    public String layMaPhongTheoMaHD(String maHD) {
        String maP = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT [maPhong] FROM [DB_karaoke].[dbo].[ChiTietHoaDon] WHERE [maHD] = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maHD);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                maP = rs.getString(1);
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
        return maP;
    }
}
