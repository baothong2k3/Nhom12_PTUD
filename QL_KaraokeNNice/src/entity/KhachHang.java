package entity;

//import java.sql.Date;
import java.util.Date;
import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String soCCCD;
	private String hoKH;
	private String tenKH;
	private Date namSinhKH;
	private boolean gioiTinhKH;
	private String sdtKH;
	private String emailKH;
	
	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getSoCCCD() {
		return soCCCD;
	}

	public void setSoCCCD(String soCCCD) {
		this.soCCCD = soCCCD;
	}

	public String getHoKH() {
		return hoKH;
	}

	public void setHoKH(String hoKH) {
		this.hoKH = hoKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public Date getNamSinhKH() {
		return namSinhKH;
	}

	public void setNamSinhKH(Date namSinhKH) {
		this.namSinhKH = namSinhKH;
	}

	public boolean isGioiTinhKH() {
		return gioiTinhKH;
	}

	public void setGioiTinhKH(boolean gioiTinhKH) {
		this.gioiTinhKH = gioiTinhKH;
	}

	public String getSdtKH() {
		return sdtKH;
	}

	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}

	public String getEmailKH() {
		return emailKH;
	}

	public void setEmailKH(String emailKH) {
		this.emailKH = emailKH;
	}

	public KhachHang() {}
	
	public KhachHang(String maKH, String soCCCD, String hoKH, String tenKH, Date namSinhKH, boolean gioiTinhKH, String sdtKH, String emailKH) {
		this.maKH = maKH;
		this.soCCCD = soCCCD;
		this.hoKH = hoKH;
		this.tenKH = tenKH;
		this.namSinhKH = namSinhKH;
		this.gioiTinhKH = gioiTinhKH;
		this.sdtKH = sdtKH;
		this.emailKH = emailKH;
	}
	
	public KhachHang(String maKH, String soCCCD, String hoKH, String tenKH, Date namSinhKH, boolean gioiTinhKH, String sdtKH) {
		this.maKH = maKH;
		this.soCCCD = soCCCD;
		this.hoKH = hoKH;
		this.tenKH = tenKH;
		this.namSinhKH = namSinhKH;
		this.gioiTinhKH = gioiTinhKH;
		this.sdtKH = sdtKH;
	}
	
	public KhachHang(String maKH) {
		this.maKH = maKH;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}
	
	public Object[] getObjectKH() {
		String gioiTinh="";
		if(isGioiTinhKH()) {
			gioiTinh = "Nam";
		}else {
			gioiTinh = "Nữ";
		}
		Object[] obj = {getMaKH(), getHoKH(), getTenKH(), getNamSinhKH(), gioiTinh, getSdtKH(), getEmailKH()};
		return obj;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", soCCCD=" + soCCCD + ", hoKH=" + hoKH + ", tenKH=" + tenKH + ", namSinhKH="
				+ namSinhKH + ", gioiTinhKH=" + gioiTinhKH + ", sdtKH=" + sdtKH + ", emailKH=" + emailKH + "]";
	}
	
}
