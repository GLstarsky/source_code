package QuanLyCuaHangSach.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import QuanLyCuaHangSach.DTO.ChuongTrinhKhuyenMai2DTO;
import QuanLyCuaHangSach.DTO.PhieuXuatDTO;

public class ChuongTrinhKhuyenMai2DAO {
    public static ArrayList<ChuongTrinhKhuyenMai2DTO> getListCTKhuyenMai() {
        ArrayList<ChuongTrinhKhuyenMai2DTO> listCTKhuyenMai = new ArrayList<>();
        MySQL_connect mySQLConnect = new MySQL_connect();
        Connection conn = mySQLConnect.connect();
        try {
            String sql = "SELECT * FROM chuongtrinhkhuyenmai";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                ChuongTrinhKhuyenMai2DTO ctkhuyenmai = new ChuongTrinhKhuyenMai2DTO(rs.getInt("Ma"), rs.getString("Ten"), rs.getDate("NgayBD"), rs.getDate("NgayKT"));
                listCTKhuyenMai.add(ctkhuyenmai);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kết nối csdl không thành công");
        }
        return listCTKhuyenMai;
    }

    public ChuongTrinhKhuyenMai2DTO getCTKhuyenMai(int maCTKhuyenMai) {
        ChuongTrinhKhuyenMai2DTO ctkhuyenmai = null;
        MySQL_connect mySQLConnect = new MySQL_connect();
        Connection conn = mySQLConnect.connect();
        try {
            String sql = "SELECT * FROM chuongtrinhkhuyenmai WHERE Ma = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, maCTKhuyenMai);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                ctkhuyenmai = new ChuongTrinhKhuyenMai2DTO(maCTKhuyenMai, rs.getString("Ten"), rs.getDate("NgayBD"), rs.getDate("NgayKT"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kết nối CSDL không thành công");
        }
        return ctkhuyenmai;
    }

    public void addChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai2DTO ctkm) {
		MySQL_connect mySQLConnect = new MySQL_connect();
		Connection conn = mySQLConnect.connect();
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO chuongtrinhkhuyenmai(Ten, NgayBD, NgayKT) VALUES(?,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, ctkm.getTen());
			pre.setDate(2, ctkm.getNgayBD());
			pre.setDate(3, ctkm.getNgayKT());
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