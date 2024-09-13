package QuanLyCuaHangSach.BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import QuanLyCuaHangSach.DAO.SachDAO;
import QuanLyCuaHangSach.DTO.Sach;

public class SachBUS {

    private SachDAO sachDAO;

    // Constructor
    public SachBUS() {
        sachDAO = new SachDAO();
    }

    // Lấy danh sách sách
    public ArrayList<Sach> getListSach() {
        try {
            return SachDAO.getListSach();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách rỗng trong trường hợp lỗi
        }
    }

    // Thêm sách
    public boolean themSach(Sach sach) {
        try {
            if (sach == null || sach.getTenSach().isEmpty() || sach.getTenTacGia().isEmpty() || sach.getSoLuong() <= 0 || sach.getDonGia() <= 0) {
                // Kiểm tra các trường thông tin sách có hợp lệ không
                throw new IllegalArgumentException("Thông tin sách không hợp lệ");
            }
            // Thêm logic kiểm tra dữ liệu ở đây nếu cần thiết
            return SachDAO.themSach(sach);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật sách

    public boolean capNhatSach(Sach sach) {
        try {
            if (sach == null || sach.getTenSach().isEmpty() || sach.getTenTacGia().isEmpty() || sach.getSoLuong() <= 0 || sach.getDonGia() <= 0) {
                // Kiểm tra các trường thông tin sách có hợp lệ không
                throw new IllegalArgumentException("Thông tin sách không hợp lệ");
            }
            // Thêm logic kiểm tra dữ liệu ở đây nếu cần thiết
            return SachDAO.capNhatSach(sach);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // Xóa sách
    public boolean xoaSach(int maSach) {
        return SachDAO.xoaSach(maSach);
    }

    // Lấy đường dẫn hình ảnh của sách
    public static String getImagePath(int maSach) {
        try {
            return SachDAO.getImagePathFromDatabase(maSach);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Sach> sapXepSach(int luaChonSapXep, boolean tangDan) {
        new SachDAO();
        ArrayList<Sach> danhSachSach = SachDAO.getListSach(); // Lấy danh sách sách từ cơ sở dữ liệu

        // Sắp xếp danh sách sách theo lựa chọn của người dùng
        switch (luaChonSapXep) {
            case 1: // Sắp xếp theo mã sách
                Collections.sort(danhSachSach, new Comparator<Sach>() {
                    @Override
                    public int compare(Sach sach1, Sach sach2) {
                        if (tangDan) {
                            return Integer.compare(sach1.getMaSach(), sach2.getMaSach());
                        } else {
                            return Integer.compare(sach2.getMaSach(), sach1.getMaSach());
                        }
                    }
                });
                break;
            case 2: // Sắp xếp theo số lượng
                Collections.sort(danhSachSach, new Comparator<Sach>() {
                    @Override
                    public int compare(Sach sach1, Sach sach2) {
                        if (tangDan) {
                            return Integer.compare(sach1.getSoLuong(), sach2.getSoLuong());
                        } else {
                            return Integer.compare(sach2.getSoLuong(), sach1.getSoLuong()); // Đảo ngược thứ tự so sánh
                        }
                    }
                });
                break;
            case 3: // Sắp xếp theo đơn giá
                Collections.sort(danhSachSach, new Comparator<Sach>() {
                    @Override
                    public int compare(Sach sach1, Sach sach2) {
                        if (tangDan) {
                            return Double.compare(sach1.getDonGia(), sach2.getDonGia());
                        } else {
                            return Double.compare(sach2.getDonGia(), sach1.getDonGia()); // Đảo ngược thứ tự so sánh
                        }
                    }
                });
                break;
            default:
                break;
        }

        return danhSachSach;
    }
    
    // Cập nhật đường dẫn ảnh vào cơ sở dữ liệu cho sách có mã sách được cung cấp
    public static boolean updateImagePathToDatabase(int maSach, String imagePath) {
        return SachDAO.updateImagePathToDatabase(maSach, imagePath);
    }

    public List<Sach> timKiemSach(Integer maSach, String tenSach, Integer maLoai, String tenTacGia, Double donGiaTu, Double donGiaDen) {
        return SachDAO.timKiemSach(maSach, tenSach, maLoai, tenTacGia, donGiaTu, donGiaDen);
    }


    public List<Sach> timKiemSachTheoSoLuong(List<Integer> selectedLimits) {
        // Gọi phương thức tìm kiếm sách nâng cao trong lớp DAO và trả về kết quả
        return SachDAO.timKiemSachTheoSoLuong(selectedLimits);
    }

    public Sach getSach(int maSach) {
        return sachDAO.getSach(maSach);
    }

	
}
