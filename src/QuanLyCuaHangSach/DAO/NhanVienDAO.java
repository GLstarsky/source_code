package QuanLyCuaHangSach.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import QuanLyCuaHangSach.DTO.NhanVienDTO;

public class NhanVienDAO {
		
    public ArrayList<NhanVienDTO> getDanhSachNhanVien() {	
        ArrayList<NhanVienDTO> danhSachNhanVien = new ArrayList<>();
        try {
        	Connection connection = MySQLConnect.getConnection();
  			Statement st = connection.createStatement();
  			String sqlReadNhanVienDTO = "SELECT * FROM nhanvien WHERE TinhTrang = 1 " ;
  			ResultSet rs = st.executeQuery(sqlReadNhanVienDTO);
  			while(rs.next()) {
  				String firstName = rs.getString("Ho");
                  String lastName = rs.getString("Ten");
                  int idEmployee = rs.getInt("ma");
                  String phoneNumber = rs.getString("SoDienThoai");
                  String address = rs.getString("DiaChi");
                  int wage = rs.getInt("MucLuong");
                  String position = rs.getString("ChucVu");
                  String gender = rs.getString("GioiTinh");

                  NhanVienDTO employee = new NhanVienDTO(firstName, lastName, idEmployee, position, gender, wage, phoneNumber, address);
                  danhSachNhanVien.add(employee);
  			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachNhanVien;
    }

    public boolean themNhanVien(NhanVienDTO nv) {
        boolean result = false;
        try {
            Connection connection = MySQLConnect.getConnection();
            Statement st = connection.createStatement();
            String sql = "INSERT INTO nhanvien(Ho, Ten, ChucVu, GioiTinh, MucLuong, SoDienThoai, DiaChi)"
                       + "VALUES ('" + nv.getho() + "', '" + nv.getten() + "', '" + nv.getchucVu() + "', '" + nv.getgioiTinh() + "', '" + nv.getluong() + "', '" + nv.getsdt() + "', '" + nv.getdiaChi() + "')";

            int rowsAffected = st.executeUpdate(sql);
            if (rowsAffected > 0) {
                result = true; // Cập nhật giá trị result nếu câu lệnh SQL thực thi thành công
            }
        } catch (SQLException e2) {
            // Bắt lỗi nếu có
            e2.printStackTrace();
        }
        return result;
    }

    public boolean capNhatNhanVien(NhanVienDTO nv) {
        boolean result = false;
        try {
			Connection connection = MySQLConnect.getConnection();
			Statement st = connection.createStatement();
			String sql = "UPDATE nhanvien SET " +
		             "Ho='" + nv.getho() + "', " +
		             "Ten='" + nv.getten() + "', " +
		             "ChucVu='" + nv.getchucVu() + "', " +
		             "GioiTinh='" + nv.getgioiTinh() + "', " +
		             "MucLuong='" + nv.getluong() + "', " +
		             "SoDienThoai='" + nv.getsdt() + "', " +
		             "DiaChi='" + nv.getdiaChi() + "' " +
		             "WHERE Ma='" + nv.getmaNV() + "'";

			st.executeUpdate(sql);
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        return result;
    }

    public boolean xoaNhanVien(int maNV) {
        boolean result = false;
        try {
        	Connection connection = MySQLConnect.getConnection();
			Statement st = connection.createStatement();
			String sql = "UPDATE nhanvien SET TinhTrang = 0 WHERE Ma= '" + maNV +"' ";
			st.executeUpdate(sql);
			
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ArrayList<NhanVienDTO> timKiemNhanVien(String keyword) {
        ArrayList<NhanVienDTO> danhSachKetQuaTimKiem = new ArrayList<>();
        return danhSachKetQuaTimKiem;
    }
}
