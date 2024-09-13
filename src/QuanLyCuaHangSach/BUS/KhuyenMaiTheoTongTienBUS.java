package QuanLyCuaHangSach.BUS;

import java.util.ArrayList;

import QuanLyCuaHangSach.DAO.KhuyenMaiTheoSachDAO;
import QuanLyCuaHangSach.DAO.KhuyenMaiTheoTongTienDAO;
import QuanLyCuaHangSach.DTO.KhuyenMaiTheoSachDTO;
import QuanLyCuaHangSach.DTO.KhuyenMaiTheoTongTienDTO;

public class KhuyenMaiTheoTongTienBUS {
	private KhuyenMaiTheoTongTienDAO khuyenMaiTheoTongTien;
	private ArrayList<KhuyenMaiTheoTongTienDTO> dsKhuyenMaiTheoTongTien;

	public KhuyenMaiTheoTongTienBUS() {
		khuyenMaiTheoTongTien = new KhuyenMaiTheoTongTienDAO();
		dsKhuyenMaiTheoTongTien = new ArrayList<>();
	}

	public void docKhuyenMaiTheoTongTien() {
		dsKhuyenMaiTheoTongTien = KhuyenMaiTheoTongTienDAO.getListKMTheoTongTien();
	}

	public ArrayList<KhuyenMaiTheoTongTienDTO> getListKhuyenMaiTheoTongTien() {
		return dsKhuyenMaiTheoTongTien;
	}

	public KhuyenMaiTheoTongTienDTO layKhuyenMaiTheoTongTien(int maCTKM) {
		return khuyenMaiTheoTongTien.getKMTheoTongTien(maCTKM);
	}
}
