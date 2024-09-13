package QuanLyCuaHangSach.DAO;

import QuanLyCuaHangSach.DTO.ChiTietPhieuXuatDTO;
import QuanLyCuaHangSach.DTO.PhieuXuatDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class PhieuXuatDAO {
	public ArrayList<PhieuXuatDTO> getListPhieuXuat() {
		ArrayList<PhieuXuatDTO> listPhieuXuat = new ArrayList<>();
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			String sql = "SELECT * FROM phieuxuat";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				PhieuXuatDTO phieuXuat = new PhieuXuatDTO(rs.getInt("Ma"), rs.getDate("NgayTao"), rs.getInt("MaNV"),
						rs.getInt("MaKH"), rs.getInt("TongTien"), rs.getInt("TongTienPhaiTra"));
				listPhieuXuat.add(phieuXuat);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Kết nối csdl không thành công");
		}
		return listPhieuXuat;
	}

	public PhieuXuatDTO getPhieuXuat(int maPhieuXuat) {
		PhieuXuatDTO phieuXuat = null;
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			String sql = "SELECT * FROM phieuxuat WHERE Ma = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, maPhieuXuat);
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				phieuXuat = new PhieuXuatDTO(maPhieuXuat, rs.getDate("NgayTao"), rs.getInt("MaNV"), rs.getInt("MaKH"),
						rs.getInt("TongTien"), rs.getInt("TongTienPhaiTra"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Kết nối CSDL không thành công");
		}
		return phieuXuat;
	}

	public int getMaPhieuXuatMoiNhat() {
		int maPhieuXuatMoiNhat = 0;
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			String sql = "SELECT MAX(Ma) AS MaMoiNhat FROM phieuxuat;";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				maPhieuXuatMoiNhat = rs.getInt("MaMoiNhat");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Kết nối CSDL không thành công");
		}
		return maPhieuXuatMoiNhat;
	}

	public void addPhieuXuat(PhieuXuatDTO px) {
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO phieuxuat(NgayTao, MaNV, MaKH, TongTien, TongTienPhaiTra) VALUES(?,?,?,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			pre.setInt(2, px.getMaNV());
			pre.setInt(3, px.getMaKH());
			pre.setInt(4, px.getTongTien());
			pre.setInt(5, px.getTongTienPhaiTra());
			pre.executeUpdate();
			JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công");
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
	
	public ArrayList<PhieuXuatDTO> getListTheoNgay(Date tuNgay, Date denNgay) {
		if (tuNgay.compareTo(denNgay) > 0) {
	        throw new IllegalArgumentException("Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.");
	    }
	    ArrayList<PhieuXuatDTO> listPhieuXuat = new ArrayList<>();
	    MySQL_connect mySQLConnect = new MySQL_connect();
	    Connection conn = mySQLConnect.connect();
	    try {
	        String sql = "SELECT * FROM phieuxuat WHERE NgayTao BETWEEN ? AND ?";
	        PreparedStatement pre = conn.prepareStatement(sql);
	        pre.setDate(1, new java.sql.Date(tuNgay.getTime()));
	        pre.setDate(2, new java.sql.Date(denNgay.getTime()));
	        ResultSet rs = pre.executeQuery();

	        while (rs.next()) {
	            PhieuXuatDTO phieuXuat = new PhieuXuatDTO(rs.getInt("Ma"), rs.getDate("NgayTao"), rs.getInt("MaNV"),
	                    rs.getInt("MaKH"), rs.getInt("TongTien"), rs.getInt("TongTienPhaiTra"));
	            listPhieuXuat.add(phieuXuat);
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Kết nối cơ sở dữ liệu không thành công");
	    }

	    return listPhieuXuat;
	}
	
	public ArrayList<PhieuXuatDTO> getListTheoMaNV(int maNV) {
	    ArrayList<PhieuXuatDTO> listPhieuXuat = new ArrayList<>();
	    MySQL_connect mySQLConnect = new MySQL_connect();
	    Connection conn = mySQLConnect.connect();
	    try {
	        String sql = "SELECT * FROM phieuxuat WHERE MaNV = ?";
	        PreparedStatement pre = conn.prepareStatement(sql);
	        pre.setInt(1, maNV);
	        ResultSet rs = pre.executeQuery();
	        while (rs.next()) {
	            PhieuXuatDTO phieuXuat = new PhieuXuatDTO(rs.getInt("Ma"), rs.getDate("NgayTao"), rs.getInt("MaNV"),
	                    rs.getInt("MaKH"), rs.getInt("TongTien"), rs.getInt("TongTienPhaiTra"));
	            listPhieuXuat.add(phieuXuat);
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Kết nối cơ sở dữ liệu không thành công");
	    }
	    return listPhieuXuat;
	}
	
	public ArrayList<PhieuXuatDTO> getListTheoMaKH(int maKH) {
	    ArrayList<PhieuXuatDTO> listPhieuXuat = new ArrayList<>();
	    MySQL_connect mySQLConnect = new MySQL_connect();
	    Connection conn = mySQLConnect.connect();
	    try {
	        String sql = "SELECT * FROM phieuxuat WHERE MaKH = ?";
	        PreparedStatement pre = conn.prepareStatement(sql);
	        pre.setInt(1, maKH);
	        ResultSet rs = pre.executeQuery();
	        while (rs.next()) {
	            PhieuXuatDTO phieuXuat = new PhieuXuatDTO(rs.getInt("Ma"), rs.getDate("NgayTao"), rs.getInt("MaNV"),
	                    rs.getInt("MaKH"), rs.getInt("TongTien"), rs.getInt("TongTienPhaiTra"));
	            listPhieuXuat.add(phieuXuat);
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Kết nối cơ sở dữ liệu không thành công");
	    }
	    return listPhieuXuat;
	}

	public ArrayList<PhieuXuatDTO> getListTheoTongTien(Double tu, Double den) {
	    ArrayList<PhieuXuatDTO> listPhieuXuat = new ArrayList<>();
	    MySQL_connect mySQLConnect = new MySQL_connect();
	    Connection conn = mySQLConnect.connect();
	    try {
	        String sql = "SELECT * FROM phieuxuat WHERE TongTienPhaiTra BETWEEN ? AND ?";
	        PreparedStatement pre = conn.prepareStatement(sql);
	        pre.setDouble(1, tu);
	        pre.setDouble(2, den);
	        ResultSet rs = pre.executeQuery();

	        while (rs.next()) {
	            PhieuXuatDTO phieuXuat = new PhieuXuatDTO(rs.getInt("Ma"), rs.getDate("NgayTao"), rs.getInt("MaNV"),
	                    rs.getInt("MaKH"), rs.getInt("TongTien"), rs.getInt("TongTienPhaiTra"));
	            listPhieuXuat.add(phieuXuat);
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Kết nối cơ sở dữ liệu không thành công");
	    }

	    return listPhieuXuat;
	}

}