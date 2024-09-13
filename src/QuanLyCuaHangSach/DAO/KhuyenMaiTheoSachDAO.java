package QuanLyCuaHangSach.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import QuanLyCuaHangSach.DTO.ChiTietPhieuXuatDTO;
import QuanLyCuaHangSach.DTO.ChuongTrinhKhuyenMai2DTO;
import QuanLyCuaHangSach.DTO.KhuyenMaiTheoSachDTO;

public class KhuyenMaiTheoSachDAO {
	public static ArrayList<KhuyenMaiTheoSachDTO> getListKMTheoSach() {
		ArrayList<KhuyenMaiTheoSachDTO> listKMTheoSach = new ArrayList<>();
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			String sql = "SELECT * FROM khuyenmaitheosanpham";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				KhuyenMaiTheoSachDTO kmtheosach = new KhuyenMaiTheoSachDTO(rs.getString("Ma"),
						rs.getInt("MaChuongTrinh"), rs.getInt("MaSach"), rs.getInt("PhanTramKM"));
				listKMTheoSach.add(kmtheosach);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Kết nối csdl không thành công");
		}
		return listKMTheoSach;
	}

	public KhuyenMaiTheoSachDTO getKMTheoSach(int maCTKM, int maSach) {
	    KhuyenMaiTheoSachDTO KMTheoSach = null; 
	    MySQL_connect mySQLConnect = new MySQL_connect();
	    Connection conn = mySQLConnect.connect();
	    try {
	        String sql = "SELECT * FROM khuyenmaitheosanpham WHERE MaChuongTrinh = ? AND MaSach = ?";
	        PreparedStatement pre = conn.prepareStatement(sql);
	        pre.setInt(1, maCTKM);
	        pre.setInt(2, maSach);
	        ResultSet rs = pre.executeQuery();
	        if (rs.next()) {
	            KMTheoSach.setMa(rs.getString("Ma"));
	            KMTheoSach.setMaChuongTrinh(rs.getInt("MaChuongTrinh"));
	            KMTheoSach.setMaSach(rs.getInt("MaSach"));
	            KMTheoSach.setPhanTramKM(rs.getInt("PhanTramKM"));
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Kết nối CSDL không thành công");
	    }
	    return KMTheoSach;
	}

	public void addKMTheoSach(KhuyenMaiTheoSachDTO kmtheosach) {
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO khuyenmaitheosanpham(MaChuongTrinh, MaSach, PhanTramKM) VALUES(?,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, kmtheosach.getMaChuongTrinh());
			pre.setInt(2, kmtheosach.getMaSach());
			pre.setInt(3, kmtheosach.getPhanTramKM());
			pre.executeUpdate();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi thao tác CSDL");
		}
	}

}
