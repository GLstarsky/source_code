package QuanLyCuaHangSach.BUS;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import QuanLyCuaHangSach.DAO.TheLoaiDAO;
import QuanLyCuaHangSach.DTO.TheLoai;

public class TheLoaiBUS {

    private TheLoaiDAO theLoaiDAO;

    // Constructor
    public TheLoaiBUS(Connection connection) {
        this.theLoaiDAO = new TheLoaiDAO(connection);
    }

    // Lấy danh sách tất cả các thể loại sách
    public ArrayList<TheLoai> layTatCaTheLoai() {
        return theLoaiDAO.layTatCaTheLoai();
    }

    // Đếm số lượng thể loại sách
    public int demTheLoai() {
        try {
            return theLoaiDAO.demTheLoai();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Có thể thêm các phương thức khác ở đây cho các hoạt động khác cần thiết
}
