package QuanLyCuaHangSach.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import QuanLyCuaHangSach.DTO.ChiTietCTKM;

public class ChiTietCTKMDAO {


    
	public static List<ChiTietCTKM> getListCTCTKM() {
	    List<ChiTietCTKM> danhSachChiTiet = new ArrayList<>();
	    String query = "SELECT * FROM khuyenmaitheosanpham";
	    try (Connection connection = MySQLConnect.getConnection();
	         PreparedStatement ps = connection.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            ChiTietCTKM chiTiet = new ChiTietCTKM(rs.getString("ma"), rs.getInt("maChuongTrinh"),
	                    rs.getInt("phanTramKM"), rs.getInt("maSach"));
	            danhSachChiTiet.add(chiTiet);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return danhSachChiTiet;
	}


    // Thêm một chi tiết chương trình khuyến mãi vào cơ sở dữ liệu
    public static boolean themChiTietCTKM(ChiTietCTKM chiTiet) {
        String query = "INSERT INTO khuyenmaitheosanpham(ma, maChuongTrinh, phanTramKM, maSach) VALUES (?, ?, ?, ?)";
        try (Connection connection = MySQLConnect.getConnection();
        		PreparedStatement ps = connection.prepareStatement(query);
        		ResultSet rs = ps.executeQuery())
        {
            ps.setString(1, chiTiet.getMaCTCTKM());
            ps.setInt(2, chiTiet.getMaChuongTrinh());
            ps.setInt(3, chiTiet.getPhanTramKM());
            ps.setInt(4, chiTiet.getMaSach());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa một chi tiết chương trình khuyến mãi khỏi cơ sở dữ liệu
    public static boolean xoaChiTietCTKM(String maCTCTKM) {
        String query = "DELETE FROM khuyenmaitheosanpham WHERE ma = ?";
        try (Connection connection = MySQLConnect.getConnection();
        		PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maCTCTKM);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sửa thông tin chi tiết chương trình khuyến mãi trong cơ sở dữ liệu
    public static boolean suaChiTietCTKM(ChiTietCTKM chiTiet) {
        String query = "UPDATE khuyenmaitheosanpham SET maChuongTrinh = ?, phanTramKM = ?, maSach = ? WHERE ma = ?";
        try (Connection connection = MySQLConnect.getConnection();
        		PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, chiTiet.getMaChuongTrinh());
            ps.setInt(2, chiTiet.getPhanTramKM());
            ps.setInt(3, chiTiet.getMaSach());
            ps.setString(4, chiTiet.getMaCTCTKM());
return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<ChiTietCTKM> timKiemTheoMaChuongTrinh(int maChuongTrinh) {
        List<ChiTietCTKM> danhSachChiTiet = new ArrayList<>();
        String query = "SELECT * FROM khuyenmaitheosanpham WHERE maChuongTrinh = ?";
        try (Connection connection = MySQLConnect.getConnection();
        		PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, maChuongTrinh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietCTKM chiTiet = new ChiTietCTKM(rs.getString("ma"), rs.getInt("maChuongTrinh"),
                        rs.getInt("phanTramKM"), rs.getInt("maSach"));
                danhSachChiTiet.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachChiTiet;
    }

}