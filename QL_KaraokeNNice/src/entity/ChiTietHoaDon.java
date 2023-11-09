package entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ChiTietHoaDon {

    private HoaDon hoaDon;
    private Date gioNhanPhong;
    private Date gioKetThuc;
    private Phong phong;

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
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

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public ChiTietHoaDon(HoaDon hoaDon, Date gioNhanPhong, Date gioKetThuc, Phong phong) {
        this.hoaDon = hoaDon;
        this.gioNhanPhong = gioNhanPhong;
        this.gioKetThuc = gioKetThuc;
        this.phong = phong;
    }

    public ChiTietHoaDon() {
    }

    public int tinhThoiLuong() {
        Calendar calGN = Calendar.getInstance();
        Calendar calGKT = Calendar.getInstance();
        calGN.setTime(getGioNhanPhong());
        calGKT.setTime(getGioKetThuc());
        int iGN = calGN.get(Calendar.HOUR_OF_DAY);
        int iGKT = calGKT.get(Calendar.HOUR_OF_DAY);
        long khoangCachThoiGian = getGioKetThuc().getTime() - getGioNhanPhong().getTime();
        double soGioGiuaHaiNgay = khoangCachThoiGian / (60 * 60 * 1000);
        
        int validGKT, validGN;
//        if(iGKT = )
        int thoiLuong = (int) soGioGiuaHaiNgay;
        return thoiLuong;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "hoaDon=" + hoaDon + ", gioNhanPhong=" + gioNhanPhong + ", gioKetThuc=" + gioKetThuc + ", phong=" + phong + '}';
    }

}
