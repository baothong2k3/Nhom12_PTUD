package entity;

//import java.sql.Date;
import java.util.Date;
import java.util.Objects;

public class HoaDon {

    private String maHD;
    private KhachHang khachHang;
    private Date gioNhanPhong;
    private Date gioKetThuc;
    private NhanVien nhanVienLap;
    private double tongTien;
    private double tienKhachTra;
    private boolean trangThai;

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public Date getGioNhanPhong() {
        return gioNhanPhong;
    }

    public void setGioNhanPhong(Date gioNhanPhong) {
        this.gioNhanPhong = gioNhanPhong;
    }

    public Date getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(Date gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public NhanVien getNhanVienLap() {
        return nhanVienLap;
    }

    public void setNhanVienLap(NhanVien nhanVienLap) {
        this.nhanVienLap = nhanVienLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getTienKhachTra() {
        return tienKhachTra;
    }

    public void setTienKhachTra(double tienKhachTra) {
        this.tienKhachTra = tienKhachTra;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public HoaDon(String maHD, KhachHang khachHang, Date gioNhanPhong, Date gioKetThuc, NhanVien nhanVienLap, double tongTien, double tienKhachTra, boolean trangThai) {
        this.maHD = maHD;
        this.khachHang = khachHang;
        this.gioNhanPhong = gioNhanPhong;
        this.gioKetThuc = gioKetThuc;
        this.nhanVienLap = nhanVienLap;
        this.tongTien = tongTien;
        this.tienKhachTra = tienKhachTra;
        this.trangThai = trangThai;
    }

    public HoaDon() {
    }

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.maHD);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.maHD, other.maHD);
    }

    public Object[] getObjectHD() {
        String trangThai = "";
        if (isTrangThai()) {
            trangThai = "Đã thanh toán";
        } else {
            trangThai = "Chưa thanh toán";
        }
        Object[] obj = {getMaHD(), getKhachHang(), getGioNhanPhong(), getGioKetThuc(), getNhanVienLap(), getTongTien(), getTienKhachTra(), trangThai};
        return obj;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", khachHang=" + khachHang + ", gioNhanPhong=" + gioNhanPhong + ", gioKetThuc=" + gioKetThuc + ", nhanVien=" + nhanVienLap + ", tongTien=" + tongTien + ", tienKhachTra=" + tienKhachTra + ", trangThai=" + trangThai + '}';
    }

}
