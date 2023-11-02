package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ChiTietHoaDon {

    private HoaDon hoaDon;
    private int thoiLuong;
    private Phong phong;

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public ChiTietHoaDon(HoaDon hoaDon, int thoiLuong, Phong phong) {
        this.hoaDon = hoaDon;
        this.thoiLuong = thoiLuong;
        this.phong = phong;
    }

    public ChiTietHoaDon() {
    }

    public int tinhThoiLuong() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        String sGioNhan = formatter.format(getHoaDon().getGioNhanPhong());
        String sGioKetThuc = formatter.format(getHoaDon().getGioKetThuc());

        int gioNhan = Integer.parseInt(sGioNhan);
        int gioKetThuc = Integer.parseInt(sGioKetThuc);
        int thoiLuongSD = gioKetThuc - gioNhan;

        return thoiLuongSD;
    }

    public Object[] getObjectCTHD() {
        Object[] obj = {getHoaDon(), getThoiLuong(), getPhong()};
        return obj;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "hoaDon=" + hoaDon + ", thoiLuong=" + thoiLuong + ", phong=" + phong + '}';
    }

}
