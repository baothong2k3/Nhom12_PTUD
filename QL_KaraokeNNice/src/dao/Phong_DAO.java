package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;
import java.sql.Statement;

public class Phong_DAO {

    public int kiemTraTrangThaiPhong(String maPhong) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement psm = null;
        int n = 0;
        try {
            String sql = "Select * from PHONG WHERE maPhong = ?";
            psm = con.prepareStatement(sql);
            psm.setString(1, maPhong);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                String tt = rs.getString(4);
                if (tt.equalsIgnoreCase("Trống")) {
                    n = 1;
                } else if (tt.equalsIgnoreCase("Đã được đặt")) {
                    n = 2;
                } else {
                    n = 3;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                psm.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return n;
    }

    public ArrayList<Phong> getAllPhong() {
        ArrayList<Phong> dsPhong = new ArrayList<Phong>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM Phong order by trangThai";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maPhong = rs.getString(1);
                String loaiPhong = rs.getString(2);
                LoaiPhong lp = new LoaiPhong(loaiPhong);
                int soNguoi = rs.getInt(3);
                String trangThai = rs.getString(4);
                boolean tinhTrang = rs.getBoolean(5);

                Phong p = new Phong(maPhong, lp, soNguoi, trangThai, tinhTrang);
                dsPhong.add(p);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dsPhong;
    }

    public boolean capNhatTrangThaiPhong(String maPhong, String trangThai) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String sql = "UPDATE Phong SET trangThai = ? WHERE maPhong = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, trangThai);
            statement.setString(2, maPhong);
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

    public ArrayList<Phong> layDSPhong() {
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

    public ArrayList<Phong> layDSPhongTheoSoNguoi(int soNguoi) {
        ArrayList<Phong> dsP = new ArrayList<Phong>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement psm = null;
        try {
            String sql = "Select * from PHONG where sucNguoi = ?";
            psm = con.prepareStatement(sql);
            psm.setInt(1, soNguoi);
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

    public ArrayList<Phong> layDSPhongTheoLoaiPhong(String loaiP) {
        ArrayList<Phong> dsP = new ArrayList<Phong>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement psm = null;
        try {
            String sql = "Select * from PHONG where maLP = ?";
            psm = con.prepareStatement(sql);
            psm.setString(1, loaiP);
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

    public ArrayList<Phong> layDSPhongTheoTrangThai(String strangThai) {
        ArrayList<Phong> dsP = new ArrayList<Phong>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement psm = null;
        try {
            String sql = "Select * from PHONG where trangThai = ?";
            psm = con.prepareStatement(sql);
            psm.setString(1, strangThai);
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

    public ArrayList<Phong> layDSPhongTheoTrangThaiSoNguoi(String strangThai, int soNguoi) {
        ArrayList<Phong> dsP = new ArrayList<Phong>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement psm = null;
        try {
            String sql = "Select * from PHONG where trangThai = ? and sucNguoi = ?";
            psm = con.prepareStatement(sql);
            psm.setString(1, strangThai);
            psm.setInt(2, soNguoi);
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

    public ArrayList<Phong> layDSPhongTheoTieuChi(String strangThai, String soNguoi, String loaiP) {
        ArrayList<Phong> dsP = new ArrayList<Phong>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement psm = null;
        String sql;
        String lphong;
        int soN = 0;
        if (!soNguoi.equalsIgnoreCase("Tất cả")) {
            soN = Integer.parseInt(soNguoi);
        }
        if (loaiP.equalsIgnoreCase("Phòng thường")) {
            lphong = "PT001";
        } else {
            lphong = "PV001";
        }
        try {
            if (strangThai.equalsIgnoreCase("Tất cả") && !soNguoi.equalsIgnoreCase("Tất cả") && !loaiP.equalsIgnoreCase("Tất cả")) {
                sql = "Select * from PHONG where sucNguoi = ? and maLP = ?";
                psm = con.prepareStatement(sql);
                psm.setInt(1, soN);
                psm.setString(2, lphong);

            } else if (!strangThai.equalsIgnoreCase("Tất cả") && soNguoi.equalsIgnoreCase("Tất cả") && !loaiP.equalsIgnoreCase("Tất cả")) {
                sql = "Select * from PHONG where trangThai = ? and maLP = ?";
                psm = con.prepareStatement(sql);
                psm.setString(1, strangThai);
                psm.setString(2, lphong);
            } else if (!strangThai.equalsIgnoreCase("Tất cả") && !soNguoi.equalsIgnoreCase("Tất cả") && loaiP.equalsIgnoreCase("Tất cả")) {
                sql = "Select * from PHONG where trangThai = ? and sucNguoi = ?";
                psm = con.prepareStatement(sql);
                psm.setString(1, strangThai);
                psm.setInt(2, soN);
            } else {
                sql = "Select * from PHONG where trangThai = ? and sucNguoi = ? and maLP = ?";
                psm = con.prepareStatement(sql);
                psm.setString(1, strangThai);
                psm.setInt(2, soN);
                psm.setString(3, lphong);
            }
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

    public boolean kiemTraMaLoaiPhong(String mLP) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {

            String sql = "SELECT * FROM LoaiPhong WHERE maLP = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, mLP);
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

    public boolean updatePhong(Phong p) {

        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            stmt = con.prepareStatement("UPDATE Phong SET maLP = ?, sucNguoi = ?, trangThai = ?, tinhTrang = ? WHERE maPhong = ?");
            stmt.setString(1, p.getLoaiPhong().getMaLP());
            stmt.setInt(2, p.getSoNguoi());
            stmt.setString(3, p.getTrangThai());
            stmt.setBoolean(4, p.isTinhTrang());
            stmt.setString(5, p.getMaPhong());
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
