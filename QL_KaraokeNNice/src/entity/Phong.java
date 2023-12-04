package entity;

import java.util.Objects;

public class Phong {
	private String maPhong;
	private LoaiPhong loaiPhong;
	private int soNguoi;
	private String trangThai;
	private boolean tinhTrang;
	
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}
	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}
	public int getSoNguoi() {
		return soNguoi;
	}
	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public boolean isTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	public Phong(String maPhong, LoaiPhong loaiPhong, int soNguoi, String trangThai, boolean tinhTrang) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.soNguoi = soNguoi;
		this.trangThai = trangThai;
		this.tinhTrang = tinhTrang;
	}
	
	public Phong(String maPhong, LoaiPhong loaiPhong, int soNguoi, boolean tinhTrang) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.soNguoi = soNguoi;
		this.tinhTrang = tinhTrang;
	}
	
	public Phong() {
		super();
	}
	
	public Phong(String maPhong) {
		this.maPhong = maPhong;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}
	
	public Object[] getObjectPhong() {
		String tinhTrang="";
		if(isTinhTrang()) {
			tinhTrang = "Đang dùng";
		}else {
			tinhTrang = "Trống";
		}
		Object[] obj = {getMaPhong(), getLoaiPhong(), getSoNguoi(), getTrangThai(), tinhTrang};
		return obj;
	}
	
	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", loaiPhong=" + loaiPhong + ", soNguoi=" + soNguoi + ", trangThai="
				+ trangThai + ", tinhTrang=" + tinhTrang + "]";
	}
	
	
}
