package QuanLyCuaHangSach.BUS;

import java.util.ArrayList;
import QuanLyCuaHangSach.DAO.ChiTietPhieuXuatDAO;
import QuanLyCuaHangSach.DTO.ChiTietPhieuXuatDTO;

public class ChiTietPhieuXuatBUS {
	private ChiTietPhieuXuatDAO chiTietPhieuXuatDAO;
	private ArrayList<ChiTietPhieuXuatDTO> dsChiTietPhieuXuat;

	public ChiTietPhieuXuatBUS() {
		chiTietPhieuXuatDAO = new ChiTietPhieuXuatDAO();
		dsChiTietPhieuXuat = new ArrayList<>();
	}

	public void docChiTietPhieuXuat(int maPhieuXuat) {
		dsChiTietPhieuXuat = chiTietPhieuXuatDAO.getListChiTietPhieuXuat(maPhieuXuat);
	}

	public ArrayList<ChiTietPhieuXuatDTO> getListChiTietPhieuXuat(int maPhieuXuat) {
		ArrayList<ChiTietPhieuXuatDTO> dsChiTietPhieuXuatTheoMa = new ArrayList<>();
		for (ChiTietPhieuXuatDTO chiTiet : dsChiTietPhieuXuat) {
			if (chiTiet.getMaPhieuXuat() == maPhieuXuat) {
				dsChiTietPhieuXuatTheoMa.add(chiTiet);
			}
		}
		return dsChiTietPhieuXuatTheoMa;
	}
	

	public void themChiTietPhieuXuat(ChiTietPhieuXuatDTO ctpx) {
		chiTietPhieuXuatDAO.addChiTietPhieuXuat(ctpx);
		dsChiTietPhieuXuat.add(ctpx);
	}

}