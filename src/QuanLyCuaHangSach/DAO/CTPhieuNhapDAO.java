package QuanLyCuaHangSach.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import QuanLyCuaHangSach.DTO.CTPhieuNhapDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;


public class CTPhieuNhapDAO {
	public boolean themNhieuCTPhieuNhap(ArrayList<CTPhieuNhapDTO> dsCTPN) {
	    boolean result = false;

	    

	    try {
	    	String qry = "INSERT INTO chitietphieunhap (maPhieuNhap, maSach, dongia, soluong, thanhtien) VALUES (?,?,?,?,?)";
		    String qryUpdate = "UPDATE sach SET soluong = soluong + ? WHERE ma = ?";
		    PreparedStatement prep = MySQLConnect.getConnection().prepareStatement(qry);
		    PreparedStatement prepUpdate = MySQLConnect.getConnection().prepareStatement(qryUpdate);

	        

	        for (CTPhieuNhapDTO ctpn : dsCTPN) {
	            double thanhtien = ctpn.getSoluong() * ctpn.getDongia();
	            prep.setInt(1, ctpn.getMaPhieuNhap());
	            prep.setInt(2, ctpn.getMaSach());
	            prep.setInt(3, ctpn.getDongia());
	            prep.setInt(4, ctpn.getSoluong());
	            prep.setDouble(5, thanhtien);
	            prep.addBatch();
	            
	            prepUpdate.setInt(1, ctpn.getSoluong());
	            prepUpdate.setInt(2, ctpn.getMaSach());
	            prepUpdate.addBatch();
	        }

	        int[] chitiePN = prep.executeBatch();
	        int[] resultsUpdate = prepUpdate.executeBatch();

	        // Kiểm tra kết quả của batch
	        for (int r : chitiePN) {
	            if (r <= 0) {
	                
	                throw new SQLException("Thêm chi tiết phiếu nhập thất bại");
	            }
	        }
	        for (int r : resultsUpdate) {
	            if (r <= 0) {
	                
	                throw new SQLException("Cập nhật số lượng sách thất bại");
	            }
	        }

	         // Commit transaction nếu không có lỗi
	        result = true;

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return result;
	}


   
	public  ArrayList<CTPhieuNhapDTO> docCTPN() {
		ArrayList <CTPhieuNhapDTO> dsCTPN = new ArrayList<>();
		try {
			String qry= "select * from chitietphieunhap";
			PreparedStatement prep = MySQLConnect.getConnection().prepareStatement(qry);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO();
				ctpn.maPhieuNhap = rs.getInt(2);
				ctpn.maSach = rs.getInt(3);
				ctpn.dongia = rs.getInt(4);
				ctpn.soluong = rs.getInt(5);
				ctpn.thanhtien = rs.getInt(6);
				dsCTPN.add(ctpn);
			}
			
		}catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin từ cơ sở dữ liệu.");
        }
		return dsCTPN;
	}
	
	public void xoaCTPhieuNhap (CTPhieuNhapDTO ctpn){
		try {
			String qry = "DELETE FROM chitietphieunhap WHERE Ma=?";
			PreparedStatement prep = MySQLConnect.getConnection().prepareStatement(qry);
			prep.setInt(1, ctpn.getMaCTPN());
			prep.executeUpdate();
		}catch (SQLException  ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	public void suaCTPhieuNhap(CTPhieuNhapDTO ctpn) {
	    try {
	        String qry = "UPDATE chitietphieunhap SET ";
	        qry += "MaPhieuNhap=?, ";
	        qry += "maSach=?, ";
	        qry += "DonGia=?, ";
	        qry += "SoLuong=?, ";
	        qry += "ThanhTien=? ";
	        qry += "WHERE Ma=?";
	        
	        Connection conn = MySQLConnect.getConnection();
	        PreparedStatement prep = conn.prepareStatement(qry);
	        
	        // Bỏ qua việc cập nhật trường ma nếu nó là một trường tự động tăng
	        // prep.setInt(1, ctpn.maPN);
	        prep.setInt(1, ctpn.maSach);
	        prep.setFloat(2, ctpn.dongia);
	        prep.setInt(3, ctpn.soluong);
	        int thanhTien = ctpn.soluong * ctpn.dongia;
	        prep.setFloat(4, thanhTien);
	    
	        prep.setInt(5, ctpn.maCTPN);
	        
	        prep.executeUpdate();
	        
	        conn.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	
	public  ArrayList<CTPhieuNhapDTO> layDanhSachChiTietCTPN(String maPhieuNhap) throws SQLException {
	    ArrayList<CTPhieuNhapDTO> danhSachChiTietPhieuNhap = new ArrayList<>();
	    String qry = "SELECT * FROM chitietphieunhap WHERE MaPhieuNhap = ?";
	    try (PreparedStatement ps = MySQLConnect.getConnection().prepareStatement(qry)) {
	        ps.setString(1, maPhieuNhap);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                int maPN = rs.getInt(2);
	                int masach = rs.getInt(3);
	                int dongia = rs.getInt(4);
	                int soluong = rs.getInt(5);
	                int thanhtien = rs.getInt(6);
	                CTPhieuNhapDTO chiTietphieunhap = new CTPhieuNhapDTO(maPN,masach,dongia,soluong,thanhtien);
	                danhSachChiTietPhieuNhap.add(chiTietphieunhap);
	            }
	        }
	    }
	    return danhSachChiTietPhieuNhap;
	}
}
