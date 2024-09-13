package QuanLyCuaHangSach.BUS;
import java.util.ArrayList;

import QuanLyCuaHangSach.DAO.NhaCungCapDAO;
import QuanLyCuaHangSach.DTO.NhaCungCapDTO;
public class NhaCungCapBUS {
public static ArrayList<NhaCungCapDTO> dsncc;
	
	
	public void themNCC(NhaCungCapDTO ncc) {
		if (dsncc == null) {
	        dsncc = new ArrayList<>();
	    }
		NhaCungCapDAO nccDAO = new NhaCungCapDAO();
		nccDAO.ThemNCC(ncc);
		dsncc.add(ncc);
	}
	
	public void docNCC(NhaCungCapDTO ncc) {
		NhaCungCapDAO nccDAO = new NhaCungCapDAO();
		if (dsncc == null) {
	        dsncc = new ArrayList<>();
	    }
        ArrayList<NhaCungCapDTO> dsCTPN = nccDAO.docncc();
		dsncc.addAll(dsCTPN);
	}
	
	public void xoaNCC (NhaCungCapDTO ncc) {
		NhaCungCapDAO nccDAO = new NhaCungCapDAO();
		nccDAO.xoaNhaCungCap(ncc);
		dsncc.remove(ncc);
	}
	
	public void suaNCC (NhaCungCapDTO ncc) {
		NhaCungCapDAO nccDAO = new NhaCungCapDAO();
		nccDAO.suaNhaCungCap(ncc);
	}
}
