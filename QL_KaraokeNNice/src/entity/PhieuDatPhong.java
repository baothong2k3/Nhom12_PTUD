package entity;

//import java.sql.Date;
import java.util.Date;
import java.util.Objects;

public class PhieuDatPhong {

    private String maPhieu;
    private KhachHang khachHang;
    private NhanVien nhanVienLap;
    private Phong phong;
    private Date thoiGianDat;
    private Date thoiGianNhan;
    private boolean trangThai;

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVienLap() {
        return nhanVienLap;
    }

    public void setNhanVienLap(NhanVien nhanVienLap) {
        this.nhanVienLap = nhanVienLap;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public Date getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(Date thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public Date getThoiGianNhan() {
        return thoiGianNhan;
    }

    public void setThoiGianNhan(Date thoiGianNhan) {
        this.thoiGianNhan = thoiGianNhan;
    }

    public PhieuDatPhong(String maPhieu, KhachHang khachHang, NhanVien nhanVienLap, Phong phong, Date thoiGianDat, Date thoiGianNhan, boolean trangThai) {
        this.maPhieu = maPhieu;
        this.khachHang = khachHang;
        this.nhanVienLap = nhanVienLap;
        this.phong = phong;
        this.thoiGianDat = thoiGianDat;
        this.thoiGianNhan = thoiGianNhan;
        this.trangThai = trangThai;
    }

    public PhieuDatPhong(String maPhieu, Phong phong, Date thoiGianNhan, boolean trangThai) {
        this.maPhieu = maPhieu;
        this.phong = phong;
        this.thoiGianNhan = thoiGianNhan;
        this.trangThai = trangThai;
    }

    public PhieuDatPhong() {
    }

    public PhieuDatPhong(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.maPhieu);
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
        final PhieuDatPhong other = (PhieuDatPhong) obj;
        return Objects.equals(this.maPhieu, other.maPhieu);
    }

    public Object[] getObjectPhieuDatPhong() {
        Object[] obj = {getMaPhieu(), getKhachHang(), getNhanVienLap(), getPhong(), getThoiGianDat(), getThoiGianNhan()};
        return obj;
    }

    @Override
    public String toString() {
        return "PhieuDatPhong{" + "maPhieu=" + maPhieu + ", khachHang=" + khachHang + ", nhanVienLap=" + nhanVienLap + ", phong=" + phong + ", thoiGianDat=" + thoiGianDat + ", thoiGianNhan=" + thoiGianNhan + '}';
    }

}
