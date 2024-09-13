package QuanLyCuaHangSach.DTO;

public class ThongKeDTO {
	public int soluongSP;
	public int soluongNV;
	public int soluongKH;
	public int doanhthu;
	public ThongKeDTO(int soluongSP, int soluongNV, int soluongKH, int doanhthu) {
		super();
		this.soluongSP = soluongSP;
		this.soluongNV = soluongNV;
		this.soluongKH = soluongKH;
		this.doanhthu = doanhthu;
	}
	public ThongKeDTO() {
		
	}
	public int getSoluongSP() {
		return soluongSP;
	}
	public void setSoluongSP(int soluongSP) {
		this.soluongSP = soluongSP;
	}
	public int getSoluongNV() {
		return soluongNV;
	}
	public void setSoluongNV(int soluongNV) {
		this.soluongNV = soluongNV;
	}
	public int getSoluongKH() {
		return soluongKH;
	}
	public void setSoluongKH(int soluongKH) {
		this.soluongKH = soluongKH;
	}
	public int getDoanhthu() {
		return doanhthu;
	}
	public void setDoanhthu(int doanhthu) {
		this.doanhthu = doanhthu;
	}
	
}
