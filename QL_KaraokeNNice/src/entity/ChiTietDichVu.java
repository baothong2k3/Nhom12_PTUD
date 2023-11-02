package entity;

public class ChiTietDichVu {

    private DichVu dichVu;
    private int soLuong;
    private HoaDon hoaDon;

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

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ChiTietDichVu(DichVu dichVu, int soLuong, HoaDon hoaDon) {
        this.dichVu = dichVu;
        this.soLuong = soLuong;
        this.hoaDon = hoaDon;
    }

    public ChiTietDichVu() {
    }

    public Object[] getObjectCTDV() {
        Object[] obj = {getDichVu(), getSoLuong(), getHoaDon()};
        return obj;
    }

    @Override
    public String toString() {
        return "ChiTietDichVu{" + "dichVu=" + dichVu + ", soLuong=" + soLuong + ", hoaDon=" + hoaDon + '}';
    }

}
