package QuanLyCuaHangSach.DAO;

import QuanLyCuaHangSach.DTO.Sach2DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Sach2DAO {
	public ArrayList<Sach2DTO> getListSach() {
		ArrayList<Sach2DTO> listSach = new ArrayList<>();
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			String sql = "SELECT * FROM sach";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
	            int maSach = rs.getInt(1);
	            Sach2DTO sach = getSach(maSach);
	            listSach.add(sach);
	        }
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Kết nối csdl không thành công");
		}
		return listSach;
	}

	public Sach2DTO getSach(int maSach) {
	    Sach2DTO sach = new Sach2DTO();
	    MySQL_connect mySQLConnect = new MySQL_connect();
	    Connection conn = mySQLConnect.connect();
	    try {
	        String sql = "SELECT sach.Ma, sach.Ten, sach.TacGia, sach.HinhAnh, phanloai.Ten AS PhanLoai, nhaxuatban.Ten AS NXB, sach.SoLuong, sach.DonGia FROM sach, nhaxuatban, phanloai WHERE sach.Ma = ? AND sach.MaNXB = nhaxuatban.Ma AND sach.maLoai = phanloai.Ma";
	        PreparedStatement pre = conn.prepareStatement(sql);
	        pre.setInt(1, maSach);
	        ResultSet rs = pre.executeQuery();
	        if (rs.next()) {
	            sach.setMaSach(rs.getInt("Ma"));
	            sach.setTenSach(rs.getString("Ten"));
	            sach.setTacGia(rs.getString("TacGia"));
	            sach.setPhanLoai(rs.getString("PhanLoai"));
	            sach.setNXB(rs.getString("NXB"));
	            sach.setSoLuong(rs.getInt("SoLuong"));
	            sach.setDonGia(rs.getInt("DonGia"));
	            sach.setHinhAnh(rs.getString("HinhAnh"));
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Kết nối CSDL không thành công");
	    }
	    return sach;
	}
	
	public void addSach(Sach2DTO sach) {
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO phieuxuat(Ten, TacGia, PhanLoai, NXB, SoLuong, DonGia, HinhAnh) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, sach.getTenSach());
			pre.setString(2, sach.getTacGia());
			pre.setString(3, sach.getPhanLoai());
			pre.setString(4, sach.getNXB());
			pre.setInt(5, sach.getSoLuong());
			pre.setInt(5, sach.getDonGia());
			pre.setString(5, sach.getHinhAnh());
			pre.executeUpdate();
			JOptionPane.showMessageDialog(null, "Thêm sách thành công");
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
	
	public void deleteSach(int maSach) {
	    MySQL_connect mySQLConnect = new MySQL_connect();
	    Connection conn = mySQLConnect.connect();
	    try {
	        conn.setAutoCommit(false);
	        String sql = "DELETE FROM sach WHERE Ma = ?";
	        PreparedStatement pre = conn.prepareStatement(sql);
	        pre.setInt(1, maSach);
	        pre.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Xóa sách thành công");
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
	
	public void updateSoLuong(int maSach, int soLuong) {
	    Sach2DTO sach = new Sach2DTO();
	    MySQL_connect mySQLConnect = new MySQL_connect();
	    Connection conn = mySQLConnect.connect();
	    try {
	        String sql = "UPDATE sach SET SoLuong = ? WHERE Ma = ?";
	        PreparedStatement pre = conn.prepareStatement(sql);
	        pre.setInt(1, soLuong);
	        pre.setInt(2, maSach);
	        int rowsAffected = pre.executeUpdate();
	        if (rowsAffected > 0) {
	        } else {
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Kết nối CSDL không thành công");
	    }
	}
	
	

}
