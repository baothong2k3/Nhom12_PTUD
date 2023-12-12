package entity;

import dao.DichVu_DAO;

public class ChiTietDichVu {

    private DichVu dichVu;
    private int soLuong;
    private HoaDon hoaDon;
    private String maPhong;

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getmaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ChiTietDichVu(DichVu dichVu, int soLuong, HoaDon hoaDon, String maPhong) {
        this.dichVu = dichVu;
        this.soLuong = soLuong;
        this.hoaDon = hoaDon;
        this.maPhong = maPhong;
    }

    public ChiTietDichVu() {
    }

    public Object[] getObjectCTDV() {
        Object[] obj = {getDichVu(), getSoLuong(), getHoaDon()};
        return obj;
    }

    public double tinhThanhTienDV() {
        DichVu_DAO dv_dao = new DichVu_DAO();
        DichVu dv = dv_dao.getDichVuTheoMa(getDichVu().getMaDV());

        double thanhTienDV = getSoLuong() * dv.getDonGia();
        return thanhTienDV;
    }

    @Override
    public String toString() {
        return "ChiTietDichVu{" + "dichVu=" + dichVu + ", soLuong=" + soLuong + ", hoaDon=" + hoaDon + ", maPhong=" + maPhong + '}';
    }

}
