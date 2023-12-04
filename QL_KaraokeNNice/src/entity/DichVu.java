package entity;

//import java.sql.Date;
import java.util.Date;
import java.util.Objects;

public class DichVu {
	private String maDV;
	private String tenDV;
	private String donViBan;
	private int soLuongTon;
	private double donGia;
	private Date hsd;
	private String xuatXu;
	private boolean tinhTrang;
	
	public String getMaDV() {
		return maDV;
	}
	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}
	public String getTenDV() {
		return tenDV;
	}
	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}
	public String getDonViBan() {
		return donViBan;
	}
	public void setDonViBan(String donViBan) {
		this.donViBan = donViBan;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public Date getHsd() {
		return hsd;
	}
	public void setHsd(Date hsd) {
		this.hsd = hsd;
	}
	public String getXuatXu() {
		return xuatXu;
	}
	public void setXuatXu(String xuatXu) {
		this.xuatXu = xuatXu;
	}
	public boolean isTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	public DichVu(String maDV, String tenDV, String donViBan, int soLuongTon, double donGia, Date hsd, String xuatXu, boolean tinhTrang) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.donViBan = donViBan;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
		this.hsd = hsd;
		this.xuatXu = xuatXu;
		this.tinhTrang = tinhTrang;
	}
	
	public DichVu(String maDV, String tenDV, String donViBan, int soLuongTon, double donGia, String xuatXu, boolean tinhTrang) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.donViBan = donViBan;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
		this.xuatXu = xuatXu;
		this.tinhTrang = tinhTrang;
	}
	
	public DichVu() {}
	
	public DichVu(String maDV) {
		this.maDV = maDV;
	}
	
        
	public Object[] getObjectDV() {
		String tinhTrang="";
		if(isTinhTrang()) {
			tinhTrang = "Đang bán";
		}else {
			tinhTrang = "Ngừng bán";
		}
		Object[] obj = {getMaDV(), getTenDV(), getDonViBan(), getSoLuongTon(), getDonGia(), getHsd(), getXuatXu(), tinhTrang};
		return obj;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maDV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDV, other.maDV);
	}
	
	@Override
	public String toString() {
		return "DichVu [maDV=" + maDV + ", tenDV=" + tenDV + ", donViBan=" + donViBan + ", soLuongTon=" + soLuongTon
				+ ", donGia=" + donGia + ", hsd=" + hsd + ", xuatXu=" + xuatXu + ", tinhTrang=" + tinhTrang + "]";
	}
	
	
}
