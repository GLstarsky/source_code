package QuanLyCuaHangSach.BUS;
import java.sql.SQLException;
import java.util.ArrayList;

import QuanLyCuaHangSach.DAO.CTPhieuNhapDAO;
import QuanLyCuaHangSach.DTO.CTPhieuNhapDTO;
public class CTPhieuNhapBUS {
	public static ArrayList<CTPhieuNhapDTO> dsCTPN;

	public void themCTPN(ArrayList<CTPhieuNhapDTO> dsCTPhieuNhap) {
	    if (dsCTPN == null)
	        dsCTPN = new ArrayList<>();

	    CTPhieuNhapDAO ctpnDAO = new CTPhieuNhapDAO();

	    boolean success = ctpnDAO.themNhieuCTPhieuNhap(dsCTPhieuNhap);
	    if (success) {
	        dsCTPN.addAll(dsCTPhieuNhap); 
	    } else {
	        System.out.println("Thêm chi tiết phiếu nhập không thành công");
	        
	    }
	}


	
	public void docCTPhieu(CTPhieuNhapDTO ctpn) {
		CTPhieuNhapDAO ctpnDAO = new CTPhieuNhapDAO();
		if (dsCTPN == null)
			dsCTPN = new ArrayList<>();
		ArrayList<CTPhieuNhapDTO> dsPhieu = ctpnDAO.docCTPN();
		dsCTPN.addAll(dsPhieu);
	}
	
	public ArrayList<CTPhieuNhapDTO> layDanhSachChiTietCTPN(String maPhieuNhap) {
        CTPhieuNhapDAO ctPhieuNhapDAO = new CTPhieuNhapDAO();
        try {
        	dsCTPN = ctPhieuNhapDAO.layDanhSachChiTietCTPN(maPhieuNhap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCTPN;
    }
	
	public void xoaCTPhieu(CTPhieuNhapDTO ctpn) {
		CTPhieuNhapDAO ctpnDAO = new CTPhieuNhapDAO();
		ctpnDAO.xoaCTPhieuNhap(ctpn);
		dsCTPN.remove(ctpn);
	}
	
	public void suaCTPhieu(CTPhieuNhapDTO ctpn) {
		CTPhieuNhapDAO ctpnDAO = new CTPhieuNhapDAO();
		ctpnDAO.suaCTPhieuNhap(ctpn);
	}
	
	public ArrayList <CTPhieuNhapDTO> TimkiemNhapTheoDG(String giathap, String gaicao){
		try {
			int min = Integer.parseInt(giathap);
			int max = Integer.parseInt(gaicao);
			ArrayList<CTPhieuNhapDTO> result = new ArrayList<>();
			for (CTPhieuNhapDTO ctpn: dsCTPN) {
				if (ctpn.getThanhtien() <= max && ctpn.getThanhtien() >= min)
					result.add(ctpn);
			}
			return result;
		}catch (Exception e) {
			 
		 }
		return null;
	}
	
}
