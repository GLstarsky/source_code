package QuanLyCuaHangSach.BUS;

import java.util.List;

import javax.swing.JOptionPane;

import QuanLyCuaHangSach.DAO.ChiTietCTKMDAO;
import QuanLyCuaHangSach.DTO.ChiTietCTKM;

public class ChiTietCTKMBUS {


    public static List<ChiTietCTKM> getListChiTietCTKM() {
        return ChiTietCTKMDAO.getListCTCTKM();
    }


    public boolean themChiTietCTKM(ChiTietCTKM chiTiet) {
        // Kiểm tra xem mã chi tiết chương trình khuyến mãi đã tồn tại hay chưa
        List<ChiTietCTKM> danhSachChiTiet = getListChiTietCTKM();
        for (ChiTietCTKM ct : danhSachChiTiet) {
            if (ct.getMaCTCTKM().equals(chiTiet.getMaCTCTKM())) {
                JOptionPane.showMessageDialog(null, "Mã chi tiết chương trình khuyến mãi đã tồn tại!");
                return false; // Không thêm mới nếu mã đã tồn tại
            }
        }
        return ChiTietCTKMDAO.themChiTietCTKM(chiTiet);
    }
    public static boolean xoaChiTietCTKM(String maCTCTKM) {
        return ChiTietCTKMDAO.xoaChiTietCTKM(maCTCTKM);
    }


    public boolean suaChiTietCTKM(ChiTietCTKM chiTiet) {
        return ChiTietCTKMDAO.suaChiTietCTKM(chiTiet);
    }
    public static List<ChiTietCTKM> timKiemTheoMaChuongTrinh(int maChuongTrinh) {
        return ChiTietCTKMDAO.timKiemTheoMaChuongTrinh(maChuongTrinh);
    }

}
