package QuanLyCuaHangSach.DTO;

import java.sql.Date;

public class GiamGia {
	
	private int ma;
	private String ten;
	private Date ngayBD;
	private Date ngayKT;
	
	public GiamGia() {
		// Constructor mặc định
	}

	public GiamGia(int ma, String ten, Date ngayBD, Date ngayKT) {
		this.ma = ma;
		this.ten = ten;
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Date getNgayBD() {
		return ngayBD;
	}

	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}

	public Date getNgayKT() {
		return ngayKT;
	}

	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}
}
