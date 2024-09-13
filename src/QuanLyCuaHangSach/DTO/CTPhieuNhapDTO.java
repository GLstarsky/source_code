package QuanLyCuaHangSach.DTO;

public class CTPhieuNhapDTO {
	public int maCTPN;
	public int maPhieuNhap;
	public int maSach ;
	public int dongia;
	public int soluong;
	public int thanhtien;
	public CTPhieuNhapDTO( int maPhieuNhap, int maSach, int dongia, int soluong, int thanhtien) {
		super();

		this.maPhieuNhap = maPhieuNhap;
		this.maSach = maSach;
		this.dongia = dongia;
		this.soluong = soluong;
		this.thanhtien = thanhtien;
	}
	public CTPhieuNhapDTO () {
		
	}
	public int getMaCTPN() {
		return maCTPN;
	}
	public void setMaCTPN(int maCTPN) {
		this.maCTPN = maCTPN;
	}
	public int getMaPhieuNhap() {
		return maPhieuNhap;
	}
	public void setMaPhieuNhap(int maPhieuNhap) {
		this.maPhieuNhap = maPhieuNhap;
	}
	public int getMaSach() {
		return maSach;
	}
	public void setMaSach(int maSach) {
		this.maSach = maSach;
	}
	public int getDongia() {
		return dongia;
	}
	public void setDongia(int dongia) {
		this.dongia = dongia;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public int getThanhtien() {
		return thanhtien;
	}
	public void setThanhtien(int thanhtien) {
		this.thanhtien = thanhtien;
	}
	
}
