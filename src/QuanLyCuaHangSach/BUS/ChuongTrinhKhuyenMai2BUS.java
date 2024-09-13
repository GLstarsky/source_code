package QuanLyCuaHangSach.BUS;

import java.util.ArrayList;

import QuanLyCuaHangSach.DAO.ChuongTrinhKhuyenMai2DAO;
import QuanLyCuaHangSach.DTO.ChuongTrinhKhuyenMai2DTO;

public class ChuongTrinhKhuyenMai2BUS {
	private ChuongTrinhKhuyenMai2DAO chuongTrinhKhuyenMai;
	private ArrayList<ChuongTrinhKhuyenMai2DTO> dsChuongTrinhKhuyenMai;
	
	public ChuongTrinhKhuyenMai2BUS() {
		chuongTrinhKhuyenMai = new ChuongTrinhKhuyenMai2DAO();
		dsChuongTrinhKhuyenMai = new ArrayList<>();
	}
	
	public void docChuongTrinhKhuyenMai() {
		dsChuongTrinhKhuyenMai = ChuongTrinhKhuyenMai2DAO.getListCTKhuyenMai();
	}
	
	public ArrayList<ChuongTrinhKhuyenMai2DTO> getListChuongTrinhKhuyenMai() {
        return dsChuongTrinhKhuyenMai;
    }
	
	public ChuongTrinhKhuyenMai2DTO layCTKhuyenMai(int maCTKM) {
		return chuongTrinhKhuyenMai.getCTKhuyenMai(maCTKM);
	}
}
