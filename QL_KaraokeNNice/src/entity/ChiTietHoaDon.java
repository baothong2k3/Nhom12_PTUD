package entity;

import dao.Phong_DAO;
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

    public double tinhThoiLuong() {
        double khoangCachThoiGian = getGioKetThuc().getTime() - getGioNhanPhong().getTime();
        double soGioGiuaHaiNgay = khoangCachThoiGian / (60 * 60 * 1000);
        int scale = (int) Math.pow(10, 1);
	double roundTo1Decimal = (double) Math.round(soGioGiuaHaiNgay * scale) / scale;
//        System.out.println(roundTo1Decimal);
        return roundTo1Decimal;
    }

    public double tinhThanhTienPhong() {
        Phong_DAO p_dao = new Phong_DAO();
        Phong p = p_dao.getPhongTheoMa(getPhong().getMaPhong());
        LoaiPhong lp = p_dao.getLoaiPhongTheoMa(p.getLoaiPhong().getMaLP());
        
        double thanhTienP = tinhThoiLuong() * lp.getGiaTien();
        return thanhTienP;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "hoaDon=" + hoaDon + ", gioNhanPhong=" + gioNhanPhong + ", gioKetThuc=" + gioKetThuc + ", phong=" + phong + '}';
    }

}
