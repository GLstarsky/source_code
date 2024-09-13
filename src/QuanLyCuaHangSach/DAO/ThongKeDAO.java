package QuanLyCuaHangSach.DAO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongKeDAO {
	public int getSoLuongNV() {
		int soLuong =0;
		try {
			String qry = "SELECT COUNT(*) AS soluong FROM nhanvien";
			PreparedStatement prep = MySQLConnect.getConnection().prepareCall(qry);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				soLuong = rs.getInt("soluong");
			}
		}catch (SQLException ex) {
            ex.printStackTrace();
            
        }
		return soLuong;
	}
	
	public int getSoluongKH () {
		int soluongKH = 0;
		try {
			String qry = "SELECT COUNT(*) AS soluongKH FROM khachhang ";
			PreparedStatement prep = MySQLConnect.getConnection().prepareStatement(qry);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				soluongKH = rs.getInt("soluongKH");
			}
		}catch (SQLException ex) {
            ex.printStackTrace();
            
        }
		return soluongKH;
	}
	
	public int getSoluongSach() {
	    int soluongSach = 0;
	    try {
	        String qry = "SELECT SUM(SoLuong) AS tongSach FROM sach";
	        PreparedStatement prep = MySQLConnect.getConnection().prepareStatement(qry);
	        ResultSet rs = prep.executeQuery();
	        if (rs.next()) {
	            soluongSach = rs.getInt("tongSach");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return soluongSach;
	}

	
	public int getDoanhThu() {
		int doanhthu = 0;
		try {
			String qry = "SELECT SUM(TongTienPhaiTra) AS tongtien FROM phieuxuat";
			PreparedStatement prep = MySQLConnect.getConnection().prepareStatement(qry);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				doanhthu = rs.getInt("tongtien");
			}
			
		}catch (SQLException ex) {
            ex.printStackTrace();
            
        }
		return doanhthu;
	}
	
	public int getDoanhThuTheoNgay(Date TuNgay , Date DenNgay) {
		int tongDoanhThu = 0;
		try {
			String qry = "SELECT SUM(TongTienPhaiTra) AS doanhthutheongay FROM phieuxuat WHERE NgayTao BETWEEN ? AND ?";
			PreparedStatement prep = MySQLConnect.getConnection().prepareStatement(qry);
			prep.setDate(1, TuNgay);
			prep.setDate(2, DenNgay);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				tongDoanhThu = rs.getInt("doanhthutheongay");
			}
		}catch (SQLException ex) {
            ex.printStackTrace();
            
        }
		return tongDoanhThu;
	}
}
