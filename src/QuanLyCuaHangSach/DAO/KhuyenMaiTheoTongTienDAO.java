package QuanLyCuaHangSach.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import QuanLyCuaHangSach.DTO.ChiTietPhieuXuatDTO;
import QuanLyCuaHangSach.DTO.ChuongTrinhKhuyenMai2DTO;
import QuanLyCuaHangSach.DTO.KhuyenMaiTheoTongTienDTO;

public class KhuyenMaiTheoTongTienDAO {
	public static ArrayList<KhuyenMaiTheoTongTienDTO> getListKMTheoTongTien() {
		ArrayList<KhuyenMaiTheoTongTienDTO> listKMTheoTongTien = new ArrayList<>();
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			String sql = "SELECT * FROM khuyenmaitheotongtien";
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				KhuyenMaiTheoTongTienDTO KMTheoTongTien = new KhuyenMaiTheoTongTienDTO(rs.getString("Ma"),
						rs.getInt("MaChuongTrinh"), rs.getInt("DieuKienApDung"), rs.getInt("PhanTramKM"));
				listKMTheoTongTien.add(KMTheoTongTien);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Kết nối csdl không thành công");
		}
		return listKMTheoTongTien;
	}

	public KhuyenMaiTheoTongTienDTO getKMTheoTongTien(int maCTKM) {
		KhuyenMaiTheoTongTienDTO KMTheoTongTien = null;
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			String sql = "SELECT * FROM khuyenmaitheotongtien WHERE MaChuongTrinh = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, maCTKM);
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				KMTheoTongTien.setMa(rs.getString("Ma"));
				KMTheoTongTien.setMaChuongTrinh(rs.getInt("MaChuongTrinh"));
				KMTheoTongTien.setDieuKienApDung(rs.getInt("DieuKienApDung"));
				KMTheoTongTien.setPhanTramKM(rs.getInt("PhanTramKM"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Kết nối CSDL không thành công");
		}
		return KMTheoTongTien;
	}

	public void addKMTheoTongTien(KhuyenMaiTheoTongTienDTO KMTheoTongTien) {
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO khuyenmaitheotongtien(MaChuongTrinh, DieuKienApDung, PhanTramKM) VALUES(?,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, KMTheoTongTien.getMaChuongTrinh());
			pre.setInt(2, KMTheoTongTien.getDieuKienApDung());
			pre.setInt(3, KMTheoTongTien.getPhanTramKM());
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
