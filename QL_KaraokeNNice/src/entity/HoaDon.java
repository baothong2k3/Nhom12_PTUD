package entity;

//import java.sql.Date;
import dao.DichVu_DAO;
import dao.HoaDon_DAO;
import dao.Phong_DAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class HoaDon {

    private String maHD;
    private KhachHang khachHang;
    private NhanVien nhanVienLap;
    private Date NgayLap;
    private double VAT = 0.1;
    private double tongTien;
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

    public NhanVien getNhanVienLap() {
        return nhanVienLap;
    }

    public void setNhanVienLap(NhanVien nhanVienLap) {
        this.nhanVienLap = nhanVienLap;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public HoaDon(String maHD, KhachHang khachHang, NhanVien nhanVienLap, Date NgayLap, double VAT, double tongTien, boolean trangThai) {
        this.maHD = maHD;
        this.khachHang = khachHang;
        this.nhanVienLap = nhanVienLap;
        this.NgayLap = NgayLap;
        this.VAT = VAT;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public HoaDon() {
    }

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }
    
    public double tinhTongTienHD() {
        HoaDon_DAO hd_dao = new HoaDon_DAO();
        Phong_DAO phong_dao = new Phong_DAO();
        DichVu_DAO dv_dao = new DichVu_DAO();
        
        ArrayList<ChiTietHoaDon> dsCTHD = hd_dao.getAllCTHDTheoMaHD(getMaHD());
        ArrayList<ChiTietDichVu> dsCTDV = hd_dao.getAllCTDVTheoMaHD(getMaHD());
        double tongTienP = 0, tongTienDV = 0;

        for (ChiTietHoaDon cthd : dsCTHD) {
            Phong p = phong_dao.getPhongTheoMa(cthd.getPhong().getMaPhong());
            LoaiPhong lp = phong_dao.getLoaiPhongTheoMa(p.getLoaiPhong().getMaLP());
            double thanhTienP = cthd.tinhThoiLuong() * lp.getGiaTien();
            tongTienP = tongTienP + thanhTienP;
        }

        for (ChiTietDichVu ctdv : dsCTDV) {
            DichVu dv = dv_dao.getDichVuTheoMa(ctdv.getDichVu().getMaDV());
            double thanhTienDV = ctdv.getSoLuong() * dv.getDonGia();
            tongTienDV = tongTienDV + thanhTienDV;
        }
        return tongTienDV + tongTienP;
    }
    
    public double tinhTongTienHDVAT(){
        return tinhTongTienHD()*getVAT() + tinhTongTienHD();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.maHD);
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

    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", khachHang=" + khachHang + ", nhanVienLap=" + nhanVienLap + ", NgayLap=" + NgayLap + ", VAT=" + VAT + ", tongTien=" + tongTien + ", trangThai=" + trangThai + '}';
    }

    

}
