package entity;

//import java.sql.Date;
import java.util.Date;
import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String soCCCD;
	private String hoNV;
	private String tenNV;
	private Date namSinh;
	private boolean gioiTinhNV;
	private String sdtNV;
	private String emailNV;
	private String diaChiNV;
	private String chucVu;
	private String matKhau;
	private boolean trangThaiLamViec;
	
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getSoCCCD() {
		return soCCCD;
	}
	public void setSoCCCD(String soCCCD) {
		this.soCCCD = soCCCD;
	}
	public String getHoNV() {
		return hoNV;
	}
	public void setHoNV(String hoNV) {
		this.hoNV = hoNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public Date getNamSinh() {
		return namSinh;
	}
	public void setNamSinh(Date namSinh) {
		this.namSinh = namSinh;
	}
	public boolean isGioiTinhNV() {
		return gioiTinhNV;
	}
	public void setGioiTinhNV(boolean gioiTinhNV) {
		this.gioiTinhNV = gioiTinhNV;
	}
	public String getSdtNV() {
		return sdtNV;
	}
	public void setSdtNV(String sdtNV) {
		this.sdtNV = sdtNV;
	}
	public String getEmailNV() {
		return emailNV;
	}
	public void setEmailNV(String emailNV) {
		this.emailNV = emailNV;
	}
	public String getDiaChiNV() {
		return diaChiNV;
	}
	public void setDiaChiNV(String diaChiNV) {
		this.diaChiNV = diaChiNV;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public boolean isTrangThaiLamViec() {
		return trangThaiLamViec;
	}
	public void setTrangThaiLamViec(boolean trangThaiLamViec) {
		this.trangThaiLamViec = trangThaiLamViec;
	}
	
	public NhanVien(String maNV, String soCCCD, String hoNV, String tenNV, Date namSinh, boolean gioiTinhNV, String sdtNV, String emailNV, String diaChiNV, String chucVu, String matKhau, boolean trangThaiLamViec) {
		super();
		this.maNV = maNV;
		this.soCCCD = soCCCD;
		this.hoNV = hoNV;
		this.tenNV = tenNV;
		this.namSinh = namSinh;
		this.gioiTinhNV = gioiTinhNV;
		this.sdtNV = sdtNV;
		this.emailNV = emailNV;
		this.diaChiNV = diaChiNV;
		this.chucVu = chucVu;
		this.matKhau = matKhau;
		this.trangThaiLamViec = trangThaiLamViec;
	}
	
	public NhanVien(String maNV, String soCCCD, String hoNV, String tenNV, Date namSinh, boolean gioiTinhNV, String sdtNV, String emailNV, String diaChiNV, String chucVu, boolean trangThaiLamViec) {
		super();
		this.maNV = maNV;
		this.soCCCD = soCCCD;
		this.hoNV = hoNV;
		this.tenNV = tenNV;
		this.namSinh = namSinh;
		this.gioiTinhNV = gioiTinhNV;
		this.sdtNV = sdtNV;
		this.emailNV = emailNV;
		this.diaChiNV = diaChiNV;
		this.chucVu = chucVu;
		this.trangThaiLamViec = trangThaiLamViec;
	}
	
	public NhanVien() {
		super();
	}

	public NhanVien(String maNV) {
		this.maNV = maNV;
	}
	
	public Object[] getObjectNV() {
		String gioiTinh="";
		String tinhTrang="";
		
		if(isGioiTinhNV()) {
			gioiTinh = "Nam";
		}else {
			gioiTinh = "Nữ";
		}
		
		if(isTrangThaiLamViec()) {
			tinhTrang = "Đang làm việc";
		}else {
			tinhTrang = "Đã nghỉ việc";
		}
		Object[] obj = {getMaNV(), getSoCCCD(), getHoNV(), getTenNV(), getNamSinh(), gioiTinh, getSdtNV(), getEmailNV(), getDiaChiNV(), getChucVu(), getMatKhau(), tinhTrang};
		return obj;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", soCCCD=" + soCCCD + ", hoNV=" + hoNV + ", tenNV=" + tenNV + ", namSinh="
				+ namSinh + ", gioiTinhNV=" + gioiTinhNV + ", sdtNV=" + sdtNV + ", emailNV=" + emailNV + ", diaChiNV="
				+ diaChiNV + ", chucVu=" + chucVu + ", matKhau=" + matKhau + ", trangThaiLamViec=" + trangThaiLamViec
				+ "]";
	}
	
	
	
}
