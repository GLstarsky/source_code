package QuanLyCuaHangSach.BUS;

import java.util.ArrayList;
import QuanLyCuaHangSach.DAO.Sach2DAO;
import QuanLyCuaHangSach.DTO.Sach2DTO;

public class Sach2BUS {
	private Sach2DAO sachDAO;
    private ArrayList<Sach2DTO> dsSach;
    
    public Sach2BUS() {
        sachDAO = new Sach2DAO();
        dsSach = new ArrayList<>();
    }
    
    public void docSach() {
    	dsSach = sachDAO.getListSach();
    }
      
    public ArrayList<Sach2DTO> getListSach() {
        return dsSach;
    }
    
    public Sach2DTO getSach(int maSach) {
        return sachDAO.getSach(maSach);
    }
    
    public void updateSoLuong(int maSach, int soLuong) {
    	sachDAO.updateSoLuong(maSach, soLuong);
    }
}

