
package QuanLyCuaHangSach.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import QuanLyCuaHangSach.DTO.NhaCungCapDTO;


public class NhaCungCapDAO {
	public boolean ThemNCC(NhaCungCapDTO ncc) {
		boolean  result = false;
		try {
			String qry = "INSERT INTO nhacungcap VALUES (?,?)";
            PreparedStatement prep = MySQLConnect.getConnection().prepareStatement(qry);
            prep.setInt(1, ncc.ma);
            prep.setString(2, ncc.tenNCC);
            result = prep.executeUpdate() >0;
		}catch (SQLException ex) {
            return false;
        }
		return result;
	}
	
	public ArrayList <NhaCungCapDTO> docncc(){
		ArrayList<NhaCungCapDTO> dsncc = new ArrayList<>();
		try {
			String qry = "select * from nhacungcap";
			PreparedStatement prep = MySQLConnect.getConnection().prepareStatement(qry);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				NhaCungCapDTO ncc = new NhaCungCapDTO();
				ncc.ma = rs.getInt(1);
				ncc.tenNCC = rs.getString(2);
				dsncc.add(ncc);
			}
	}
	catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin từ cơ sở dữ liệu.");
	}
		return dsncc;
	}
	
	public void xoaNhaCungCap(NhaCungCapDTO ncc) {
		try {
			String qry = "Delete from nhacungcap where ma=?";
			PreparedStatement prep = MySQLConnect.getConnection().prepareStatement(qry);
			prep.setInt(1, ncc.getMa());
			prep.executeUpdate();
		}catch (SQLException  ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	public void suaNhaCungCap(NhaCungCapDTO ncc) {
	    try {
	        String qry = "UPDATE nhacungcap SET Ten=? WHERE Ma=?";
	        Connection conn = MySQLConnect.getConnection();
	        PreparedStatement prep = conn.prepareStatement(qry);
	        prep.setString(1, ncc.tenNCC);
	        prep.setInt(2, ncc.ma); // Thêm dòng này để thiết lập giá trị cho tham số thứ hai
	        prep.executeUpdate();
	        conn.close();
	    } catch (SQLException ex) {
	        // TODO Auto-generated catch block
	        ex.printStackTrace();
	    }
	}

    

}
