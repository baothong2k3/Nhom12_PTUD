package entity;

import java.util.Objects;

public class LoaiPhong {
	private String maLP;
	private String tenLoaiPhong;
	private double giaTien;
	
	public String getMaLP() {
		return maLP;
	}
	public void setMaLP(String maLP) {
		this.maLP = maLP;
	}
	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}
	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
	public LoaiPhong(String maLP, String tenLoaiPhong, double giaTien) {
		super();
		this.maLP = maLP;
		this.tenLoaiPhong = tenLoaiPhong;
		this.giaTien = giaTien;
	}
	
	public LoaiPhong() {}
	
	public LoaiPhong(String maLP) {
		this.maLP = maLP;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maLP);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiPhong other = (LoaiPhong) obj;
		return Objects.equals(maLP, other.maLP);
	}
	@Override
	public String toString() {
		return "LoaiPhong [maLP=" + maLP + ", tenLoaiPhong=" + tenLoaiPhong + ", giaTien=" + giaTien + "]";
	}
	
	
}
