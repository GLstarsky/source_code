package QuanLyCuaHangSach.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import QuanLyCuaHangSach.DTO.KhachHangDTO;


public class KhachHangDAO {
	public ArrayList<KhachHangDTO> getDanhSachKhachHang() {	
        ArrayList<KhachHangDTO> danhSachKhachHang = new ArrayList<>();
        try {
        	Connection connection = MySQLConnect.getConnection();
  			Statement st = connection.createStatement();
  			String sqlReadKhachHangDTO = "SELECT * FROM khachhang WHERE TinhTrang = 1" ;
  			ResultSet rs = st.executeQuery(sqlReadKhachHangDTO);
  			while(rs.next()) {
  				String firstName = rs.getString("Ho");
                  String lastName = rs.getString("Ten");
                  int idKhachHangDTO = rs.getInt("Ma");
                  String phoneNumber = rs.getString("SoDienThoai");
                  String address = rs.getString("DiaChi");
     

                  KhachHangDTO KhachHangDTO = new KhachHangDTO(firstName, lastName, idKhachHangDTO, phoneNumber, address);
                  danhSachKhachHang.add(KhachHangDTO);
  			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }

    public boolean themKhachHang(KhachHangDTO kh) {
    	 boolean result = false;
         try {
 			Connection connection = MySQLConnect.getConnection();
 			Statement st = connection.createStatement();
 			String sql = "INSERT INTO khachhang (Ho, Ten, SoDienThoai, DiaChi) "
 		             + "VALUES ('" + kh.getho() + "', '" + kh.getten() + "', '" + kh.getsdt() + "', '" + kh.getdiaChi() + "')";

 			st.executeUpdate(sql);
 			result = true;
 		

 		} catch (SQLException e2) {
 			// TODO Auto-generated catch block
 			e2.printStackTrace();
 		}
         return result;
    }

    public boolean capNhatKhachHang(KhachHangDTO kh) {
        boolean result = false;
        try {
			Connection connection = MySQLConnect.getConnection();
			Statement st = connection.createStatement();
			String sql = "UPDATE khachhang SET Ho='" + kh.getho()+ "', Ten='" + kh.getten() + "', SoDienThoai='" + kh.getsdt() + "', DiaChi='" + kh.getdiaChi() + "' WHERE Ma='" + kh.getmaKH() + "'";

			st.executeUpdate(sql);
			result = true;
		
	
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        return result;
    }

    public boolean xoaKhachHang(int maKH) {
        boolean result = false;
        try {
        	Connection connection = MySQLConnect.getConnection();
			Statement st = connection.createStatement();
			String sql = "UPDATE khachhang SET TinhTrang = 0 WHERE Ma= '" + maKH +"' ";
			st.executeUpdate(sql);
			
		
			
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

  
}
