package QuanLyCuaHangSach.BUS;

import java.sql.Date;
import java.util.ArrayList;

import QuanLyCuaHangSach.DAO.GiamGiaDAO;
import QuanLyCuaHangSach.DTO.GiamGia;

public class GiamGiaBUS {
    
    private ArrayList<GiamGia> listGiamGia;

    public GiamGiaBUS() {
        listGiamGia = GiamGiaDAO.getListGiamGia();
    }

    // Lấy danh sách tất cả các chương trình giảm giá
    public ArrayList<GiamGia> getListGiamGia() {
        if (listGiamGia == null) {
            listGiamGia = GiamGiaDAO.getListGiamGia();
        }
        return listGiamGia;
    }

    // Thêm một chương trình giảm giá
    public boolean themGiamGia(GiamGia giamGia) {
        boolean isSuccess = GiamGiaDAO.themGiamGia(giamGia);
        if (isSuccess) {
            listGiamGia.add(giamGia);
        }
        return isSuccess;
    }

    // Cập nhật một chương trình giảm giá
    public boolean capNhatGiamGia(GiamGia giamGia) {
        boolean isSuccess = GiamGiaDAO.capNhatGiamGia(giamGia);
        if (isSuccess) {
            for (int i = 0; i < listGiamGia.size(); i++) {
                if (listGiamGia.get(i).getMa() == giamGia.getMa()) {
                    listGiamGia.set(i, giamGia);
                    break;
                }
            }
        }
        return isSuccess;
    }

    // Xóa một chương trình giảm giá
    public boolean xoaGiamGia(int ma) {
        boolean isSuccess = GiamGiaDAO.xoaGiamGia(ma);
        if (isSuccess) {
            listGiamGia.removeIf(giamGia -> giamGia.getMa() == ma);
        }
        return isSuccess;
    }

 // Tìm kiếm chương trình giảm giá theo mã, tên, ngày bắt đầu và ngày kết thúc
    public ArrayList<GiamGia> timKiemGiamGia(Integer ma, String ten, Date ngayBD, Date ngayKT) {
        ArrayList<GiamGia> result = new ArrayList<>();
        for (GiamGia giamGia : listGiamGia) {
            // Check conditions for filtering
            if ((ma == null || giamGia.getMa() == ma) &&
                (ten.isEmpty() || giamGia.getTen().contains(ten)) &&
                (ngayBD == null || giamGia.getNgayBD().compareTo(ngayBD) >= 0) &&
                (ngayKT == null || giamGia.getNgayKT().compareTo(ngayKT) <= 0)) {
                result.add(giamGia);
            }
        }
        return result;
    }
}
