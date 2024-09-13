package QuanLyCuaHangSach.DAO;

import QuanLyCuaHangSach.DTO.ChiTietPhieuXuatDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ChiTietPhieuXuatDAO {
    public ArrayList<ChiTietPhieuXuatDTO> getListChiTietPhieuXuat(int maPhieuXuat) {
        ArrayList<ChiTietPhieuXuatDTO> listChiTietPhieuXuat = new ArrayList<>();
        MySQL_connect mySQLConnect = new MySQL_connect();
        Connection conn = mySQLConnect.connect();
        try {
            String sql = "SELECT * FROM chitietphieuxuat WHERE MaPhieuXuat = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, maPhieuXuat);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int ma = rs.getInt("Ma");
                int maSach = rs.getInt("MaSach");
                int soLuong = rs.getInt("SoLuong");
                int donGia = rs.getInt("DonGia");
                int thanhTien = rs.getInt("ThanhTien");

                ChiTietPhieuXuatDTO chiTietPhieuXuat = new ChiTietPhieuXuatDTO(ma, maPhieuXuat, maSach, soLuong, donGia, thanhTien);
                listChiTietPhieuXuat.add(chiTietPhieuXuat);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kết nối CSDL không thành công");
        }
        return listChiTietPhieuXuat;
    }
    
    public ChiTietPhieuXuatDTO getChiTietPhieuXuat(int maPhieuXuat) {
	    ChiTietPhieuXuatDTO ctpx = new ChiTietPhieuXuatDTO();
	    MySQL_connect mySQLConnect = new MySQL_connect();
	    Connection conn = mySQLConnect.connect();
	    try {
	        String sql = "SELECT * FROM chitietphieuxuat WHERE MaPhieuXuat = ?";
	        PreparedStatement pre = conn.prepareStatement(sql);
	        pre.setInt(1, maPhieuXuat);
	        ResultSet rs = pre.executeQuery();
	        if (rs.next()) {
	        	ctpx.setMa(rs.getInt("Ma"));
	            ctpx.setMaPhieuXuat(rs.getInt("MaPhieuXuat"));
	            ctpx.setMaSach(rs.getInt("MaSach"));
	            ctpx.setSoLuong(rs.getInt("SoLuong	"));
	            ctpx.setDonGia(rs.getInt("DonGia"));
	            ctpx.setThanhTien(rs.getInt("ThanhTien"));
	            
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Kết nối CSDL không thành công");
	    }
	    return ctpx;
	}
    
    public void addChiTietPhieuXuat(ChiTietPhieuXuatDTO ctpx) {
    	MySQL_connect mySQLConnect = new MySQL_connect();
	    Connection conn = mySQLConnect.connect();
	    try {
	        conn.setAutoCommit(false);
	        String sql = "INSERT INTO chitietphieuxuat(MaPhieuXuat, MaSach, SoLuong, DonGia, ThanhTien) VALUES(?,?,?,?,?)";
	        PreparedStatement pre = conn.prepareStatement(sql);
	        pre.setInt(1, ctpx.getMaPhieuXuat());
	        pre.setInt(2, ctpx.getMaSach());
	        pre.setInt(3, ctpx.getSoLuong());
	        pre.setInt(4, ctpx.getDonGia());
	        pre.setInt(5, ctpx.getThanhTien());
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