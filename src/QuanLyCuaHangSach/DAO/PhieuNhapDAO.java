package QuanLyCuaHangSach.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import QuanLyCuaHangSach.DTO.PhieuNhapDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class PhieuNhapDAO {
	public boolean themPhieu(PhieuNhapDTO pn) {
		boolean result = false;
		try {
            String qry = "INSERT INTO phieunhap VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prep = MySQLConnect.getConnection().prepareCall(qry);
            prep.setInt(1, pn.getMaPN());
            prep.setInt(2, pn.getMaNCC());
            prep.setInt(3, pn.getMaNV());
            prep.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setDouble(5, pn.getTongtien());
            result = prep.executeUpdate() >0;
		}catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
	}
	
    public ArrayList<PhieuNhapDTO> docdsPhieu(){
    	ArrayList<PhieuNhapDTO> dsPhieu = new ArrayList<>();
    	try {
    		String qry = "SELECT * FROM phieunhap";
    		PreparedStatement prep = MySQLConnect.getConnection().prepareStatement(qry);
    		ResultSet rs = prep.executeQuery();
    		while (rs.next()) {
    			PhieuNhapDTO pn = new PhieuNhapDTO();
    			pn.maPN = rs.getInt(1);
    			pn.maNCC = rs.getInt(2);
    			pn.maNV = rs.getInt(3);
    			pn.ngaylap = rs.getDate(4);
    			pn.tongtien = rs.getInt(5);
        		dsPhieu.add(pn);
    		}
    		
    	}catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin từ cơ sở dữ liệu.");
        }
    	return dsPhieu;
    }
	
}
