package QuanLyCuaHangSach.BUS;

import java.sql.Connection;
import java.util.ArrayList;

import QuanLyCuaHangSach.DAO.NhaXuatBanDAO;
import QuanLyCuaHangSach.DTO.NhaXuatBan;

public class NhaXuatBanBUS {

    private NhaXuatBanDAO nhaXuatBanDAO;

    // Constructor
    public NhaXuatBanBUS(Connection connection) {
        this.nhaXuatBanDAO = new NhaXuatBanDAO(connection);
    }

    // Lấy danh sách tất cả các nhà xuất bản
    public ArrayList<NhaXuatBan> layTatCaNhaXuatBan() {
        return nhaXuatBanDAO.layTatCaNhaXuatBan();
    }

    // Có thể thêm các phương thức khác ở đây cho các hoạt động khác cần thiết
}
