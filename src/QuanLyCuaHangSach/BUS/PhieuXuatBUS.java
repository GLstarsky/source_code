package QuanLyCuaHangSach.BUS;

import java.sql.Date;
import java.util.ArrayList;
import QuanLyCuaHangSach.DAO.PhieuXuatDAO;
import QuanLyCuaHangSach.DTO.PhieuXuatDTO;

public class PhieuXuatBUS {
    private PhieuXuatDAO phieuXuatDAO;
    private ArrayList<PhieuXuatDTO> dsPhieuXuat;
    
    public PhieuXuatBUS() {
        phieuXuatDAO = new PhieuXuatDAO();
        dsPhieuXuat = new ArrayList<>();
    }
    
    public void docPhieuXuat() {
        dsPhieuXuat = phieuXuatDAO.getListPhieuXuat();
    }
    
    public ArrayList<PhieuXuatDTO> getListPhieuXuat() {
        return dsPhieuXuat;
    }
    
    public void themPhieuXuat(PhieuXuatDTO phieuXuat) {
        phieuXuatDAO.addPhieuXuat(phieuXuat);
        dsPhieuXuat.add(phieuXuat);
    }
    
    public int maPhieuXuatMoiNhat() {
    	return phieuXuatDAO.getMaPhieuXuatMoiNhat();
    }
    
    public PhieuXuatDTO layPhieuXuat(int maPhieuXuat) {
		return phieuXuatDAO.getPhieuXuat(maPhieuXuat);
    }
    
    public ArrayList<PhieuXuatDTO> getListPhieuXuatTheoNgay(Date tuNgay, Date denNgay) {
        return phieuXuatDAO.getListTheoNgay(tuNgay, denNgay);
    }
    
    public ArrayList<PhieuXuatDTO> getListPhieuXuatTheoTongTien(Double tu, Double den) {
        return phieuXuatDAO.getListTheoTongTien(tu, den);
    }
    
    public ArrayList<PhieuXuatDTO> getListPhieuXuatTheoMaNV(int maNV) {
        return phieuXuatDAO.getListTheoMaNV(maNV);
    }
    
    public ArrayList<PhieuXuatDTO> getListPhieuXuatTheoMaKH(int maKH) {
        return phieuXuatDAO.getListTheoMaKH(maKH);
    }
    
    
    
}