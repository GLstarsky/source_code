package QuanLyCuaHangSach.BUS;

import java.util.ArrayList;

import QuanLyCuaHangSach.DAO.ChuongTrinhKhuyenMai2DAO;
import QuanLyCuaHangSach.DAO.KhuyenMaiTheoSachDAO;
import QuanLyCuaHangSach.DTO.ChuongTrinhKhuyenMai2DTO;
import QuanLyCuaHangSach.DTO.KhuyenMaiTheoSachDTO;

public class KhuyenMaiTheoSachBUS {
	private KhuyenMaiTheoSachDAO khuyenMaiTheoSach;
	private ArrayList<KhuyenMaiTheoSachDTO> dsKhuyenMaiTheoSach;
	
	public KhuyenMaiTheoSachBUS() {
		khuyenMaiTheoSach = new KhuyenMaiTheoSachDAO();
		dsKhuyenMaiTheoSach = new ArrayList<>();
	}
	
	public void docKhuyenMaiTheoSach() {
		dsKhuyenMaiTheoSach = KhuyenMaiTheoSachDAO.getListKMTheoSach();
	}
	
	public ArrayList<KhuyenMaiTheoSachDTO> getListKhuyenMaiTheoSach() {
        return dsKhuyenMaiTheoSach;
    }
	
	public KhuyenMaiTheoSachDTO layKhuyenMaiTheoSach(int maCTKM, int maSach) {
		return khuyenMaiTheoSach.getKMTheoSach(maCTKM, maSach);
	}
}
