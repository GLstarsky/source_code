package QuanLyCuaHangSach.BUS;
import java.util.ArrayList;
import java.util.Date;

import QuanLyCuaHangSach.DAO.PhieuNhapDAO;
import QuanLyCuaHangSach.DTO.PhieuNhapDTO;

public class PhieuNhapBUS {
	public static ArrayList<PhieuNhapDTO> dsphieunhap;
	
	public void themPhieu(PhieuNhapDTO pn) {
		if (dsphieunhap == null) {
			dsphieunhap = new ArrayList<>();
	    }
		PhieuNhapDAO pnDAO = new PhieuNhapDAO();
		pnDAO.themPhieu(pn);
		dsphieunhap.add(pn);
	}
	
	 public void docPhieuNhap(PhieuNhapDTO pn) {
	        PhieuNhapDAO pnDAO = new PhieuNhapDAO();
	        if (dsphieunhap == null) {
	        	dsphieunhap = new ArrayList<>(); 
	        }
	        ArrayList<PhieuNhapDTO> dsPN = pnDAO.docdsPhieu();
	        dsphieunhap.addAll(dsPN);
	 }
	 
	 public ArrayList<PhieuNhapDTO> timkiemTheoNgay(Date min2, Date max2) {
		    try {
		        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
		        for (PhieuNhapDTO pn : dsphieunhap) {
		            if (pn.getNgaylap().compareTo(max2) <= 0 && pn.getNgaylap().compareTo(min2) >= 0) {
		                result.add(pn);
		            }
		        }
		        return result;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return null;
		}
}
