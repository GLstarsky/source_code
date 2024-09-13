package QuanLyCuaHangSach.DTO;

import java.util.Date;

public class PhieuNhapDTO {
	public int maPN;
	public int maNCC;
	public int maNV;
	public Date ngaylap;
	public int tongtien;

	public PhieuNhapDTO() {
		
	}
	public PhieuNhapDTO(int maPN, int maNCC, int maNV, Date ngaylap, int tongtien) {
		this.maPN = maPN;
		this.maNCC = maNCC;
		this.maNV = maNV;
		this.ngaylap = ngaylap;
		this.tongtien = tongtien;
	}

	public int getMaPN() {
		return maPN;
	}

	public void setMaPN(int maPN) {
		this.maPN = maPN;
	}

	public int getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}

	public Date getNgaylap() {
		return ngaylap;
	}

	public void setNgaylap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}

	public int getTongtien() {
		return tongtien;
	}

	public void setTongtien(int tongtien) {
		this.tongtien = tongtien;
	}
}
