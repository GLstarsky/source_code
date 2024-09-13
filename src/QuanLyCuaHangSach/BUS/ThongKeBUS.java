package QuanLyCuaHangSach.BUS;

import java.sql.Date;

import QuanLyCuaHangSach.DAO.ThongKeDAO;

public class ThongKeBUS {
	public int getSoluongNV() {
		ThongKeDAO soluongNV = new ThongKeDAO();
		return soluongNV.getSoLuongNV();
	}
	
	public int getSoluongKH() {
		ThongKeDAO soluongKH = new ThongKeDAO();
		return soluongKH.getSoluongKH();
	}
	
	public int getSoluongSach() {
		ThongKeDAO soluongSach = new ThongKeDAO();
		return soluongSach.getSoluongSach();
	}
	
	public int getDoanhThu() {
		ThongKeDAO doanhthu = new ThongKeDAO();
		return doanhthu.getDoanhThu();
	}
	

	public int getDoanhThuTheoNgay(Date tungay, Date denngay) {
		ThongKeDAO doanhthutheongay = new ThongKeDAO();
		return doanhthutheongay.getDoanhThuTheoNgay(tungay, denngay);
	}
}
