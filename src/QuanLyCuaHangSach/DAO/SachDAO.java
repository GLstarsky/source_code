package QuanLyCuaHangSach.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import QuanLyCuaHangSach.DTO.Sach;

public class SachDAO {

   
	public static ArrayList<Sach> getListSach() {
	    Connection conn = MySQLConnect.getConnection();

	    if (conn != null) {
	        try {
	            String sql = "SELECT Ma, Ten, TacGia, MaLoai, MaNXB, SoLuong, DonGia, HinhAnh FROM sach"; // Chọn các cột theo thứ tự trong CSDL
	            PreparedStatement pre = conn.prepareStatement(sql);
	            ResultSet rs = pre.executeQuery();
	            ArrayList<Sach> dsSach = new ArrayList<>();
	            while (rs.next()) {
	                Sach sach = new Sach();

	                sach.setMaSach(rs.getInt("Ma"));
	                sach.setTenSach(rs.getString("Ten"));
	                sach.setTenTacGia(rs.getString("TacGia"));
	                sach.setMaLoai(rs.getInt("MaLoai"));
	                sach.setMaNXB(rs.getInt("MaNXB"));
	                sach.setSoLuong(rs.getInt("SoLuong"));
	                sach.setDonGia(rs.getDouble("DonGia"));
	                sach.setHinhAnh(rs.getString("HinhAnh"));

	                dsSach.add(sach);
	            }
	            return dsSach;
	        } catch (SQLException e) {
	            e.printStackTrace(); // Xử lý lỗi
	        } 
	    }
	    return null;
	}

    // Thêm sách vào cơ sở dữ liệu
    public static boolean themSach(Sach sach) {
        Connection conn = MySQLConnect.getConnection();
        if (conn != null) {
            try {
                String sql = "INSERT INTO sach (Ten, MaLoai, MaNXB, TacGia, SoLuong, DonGia, HinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, sach.getTenSach());
                pre.setInt(2, sach.getMaLoai());
                pre.setInt(3, sach.getMaNXB());
                pre.setString(4, sach.getTenTacGia());
                pre.setInt(5, sach.getSoLuong());
                pre.setDouble(6, sach.getDonGia());
                pre.setString(7, sach.getHinhAnh());

                int rowsAffected = pre.executeUpdate();

                return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng được thêm vào cơ sở dữ liệu
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        }
        return false;
    }

    // Cập nhật thông tin sách trong cơ sở dữ liệu
    public static boolean capNhatSach(Sach sach) {
        Connection conn = MySQLConnect.getConnection();
        if (conn != null) {
            try {

                String sql = "UPDATE sach SET Ten = ?, MaLoai = ?, MaNXB = ?, TacGia = ?, SoLuong = ?, DonGia = ?, HinhAnh = ? WHERE Ma = ?";
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, sach.getTenSach());
                pre.setInt(2, sach.getMaLoai());
                pre.setInt(3, sach.getMaNXB());
                pre.setString(4, sach.getTenTacGia());
                pre.setInt(5, sach.getSoLuong());
                pre.setDouble(6, sach.getDonGia());
                pre.setString(7, sach.getHinhAnh());
                pre.setInt(8, sach.getMaSach());


                int rowsAffected = pre.executeUpdate();

                return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng được cập nhật trong cơ sở dữ liệu
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        }
        return false;
    }

    // Xóa sách khỏi cơ sở dữ liệu
    public static boolean xoaSach(int maSach) {
        Connection conn = MySQLConnect.getConnection();
        if (conn != null) {
            try {
                String sql = "DELETE FROM sach WHERE Ma = ?";
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1, maSach);

                int rowsAffected = pre.executeUpdate();

                return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng được xóa khỏi cơ sở dữ liệu
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        }
        return false;
    }
    

    
    public static List<Sach> timKiemSach(Integer maSach, String tenSach, Integer maLoai, String tenTacGia, Double donGiaTu, Double donGiaDen) {
        List<Sach> dsSach = new ArrayList<>();
        Connection conn = MySQLConnect.getConnection();


        if (conn != null) {
            PreparedStatement pre = null;
            ResultSet rs = null;
            try {
                StringBuilder sql = new StringBuilder("SELECT * FROM sach WHERE 1=1");
                List<Object> parameters = new ArrayList<>();

                if (maSach != null) {
                    sql.append(" AND Ma = ?");
                    parameters.add(maSach);
                }
                if (tenSach != null && !tenSach.isEmpty()) {
                    sql.append(" AND Ten LIKE ?");
                    parameters.add("%" + tenSach + "%");
                }
                if (maLoai != null && maLoai != 1) { // Kiểm tra nếu maLoai khác 10
                    sql.append(" AND MaLoai = ?");
                    parameters.add(maLoai);
                }
                if (tenTacGia != null && !tenTacGia.isEmpty()) {
                    sql.append(" AND TacGia LIKE ?");
                    parameters.add("%" + tenTacGia + "%");
                }
                if (donGiaTu != null) {
                    sql.append(" AND DonGia >= ?");
                    parameters.add(donGiaTu);
                }
                if (donGiaDen != null) {
                    sql.append(" AND DonGia <= ?");
                    parameters.add(donGiaDen);
                }

                pre = conn.prepareStatement(sql.toString());

                for (int i = 0; i < parameters.size(); i++) {
                    pre.setObject(i + 1, parameters.get(i));
                }

                rs = pre.executeQuery();

                while (rs.next()) {
                    Sach sach = new Sach();
                    sach.setMaSach(rs.getInt("Ma"));
                    sach.setTenSach(rs.getString("Ten"));
                    sach.setTenTacGia(rs.getString("TacGia"));
                    sach.setMaLoai(rs.getInt("MaLoai"));
                    sach.setMaNXB(rs.getInt("MaNXB"));
                    sach.setSoLuong(rs.getInt("SoLuong"));
                    sach.setDonGia(rs.getDouble("DonGia"));
                    sach.setHinhAnh(rs.getString("HinhAnh"));
                    dsSach.add(sach);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Xử lý lỗi
            } 
        }
        return dsSach;
    }



        public static List<Sach> timKiemSachTheoSoLuong(List<Integer> selectedLimits) {
            List<Sach> dsSach = new ArrayList<>();
            Connection conn = MySQLConnect.getConnection();

            if (conn != null) {
                PreparedStatement pre = null;
                ResultSet rs = null;
                try {
                    StringBuilder sql = new StringBuilder("SELECT * FROM sach WHERE 1=0"); // Không có dòng nào thỏa mãn ban đầu
                    for (Integer limit : selectedLimits) {
                        if (limit == 1) {
                            sql.append(" OR (SoLuong >= 0 AND SoLuong <= 30)"); // Thêm điều kiện cho mốc 1
                        } else if (limit == 2) {
                            sql.append(" OR (SoLuong > 30 AND SoLuong <= 60)"); // Thêm điều kiện cho mốc 2
                        } else if (limit == 3) {
                            sql.append(" OR (SoLuong > 60)"); // Thêm điều kiện cho mốc 3
                        }
                    }

                    pre = conn.prepareStatement(sql.toString());
                    rs = pre.executeQuery();

                    while (rs.next()) {
                        Sach sach = new Sach();
                        sach.setMaSach(rs.getInt("Ma"));
                        sach.setTenSach(rs.getString("Ten"));
                        sach.setTenTacGia(rs.getString("TacGia"));
                        sach.setMaLoai(rs.getInt("MaLoai"));
                        sach.setMaNXB(rs.getInt("MaNXB"));
                        sach.setSoLuong(rs.getInt("SoLuong"));
                        sach.setDonGia(rs.getDouble("DonGia"));
                        sach.setHinhAnh(rs.getString("HinhAnh"));
                        dsSach.add(sach);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } 
            }
            return dsSach;
        }
    
    // Lấy đường dẫn hình ảnh từ cơ sở dữ liệu
    public static String getImagePathFromDatabase(int maSach) {
        Connection conn = MySQLConnect.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String imagePath = null;

        if (conn != null) {
            try {
                String query = "SELECT HinhAnh FROM sach WHERE Ma = ?";
                statement = conn.prepareStatement(query);
                statement.setInt(1, maSach);

                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    imagePath = resultSet.getString("HinhAnh");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        }
        return imagePath;
    }
    

    
    public static boolean updateImagePathToDatabase(int maSach, String imagePath) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MySQLConnect.getConnection();
            String sql = "UPDATE sach SET HinhAnh = ? WHERE Ma = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, imagePath);
            preparedStatement.setInt(2, maSach);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }
    
    public Sach getSach(int maSach) {
	    Sach sach = new Sach();
	    
	    try {
	        String sql = "SELECT sach.Ma, sach.Ten, sach.TacGia, sach.img, phanloai.Ten AS PhanLoai, nhaxuatban.Ten AS NXB, sach.SoLuong, sach.DonGia FROM sach, nhaxuatban, phanloai WHERE sach.Ma = ? AND sach.MaNXB = nhaxuatban.Ma AND sach.maLoai = phanloai.Ma";
	        PreparedStatement pre = MySQLConnect.getConnection().prepareStatement(sql);
	        pre.setInt(1, maSach);
	        ResultSet rs = pre.executeQuery();
	        if (rs.next()) {
	        	 sach.setMaSach(rs.getInt("Ma"));
                 sach.setTenSach(rs.getString("Ten"));
                 sach.setTenTacGia(rs.getString("TacGia"));
                 sach.setMaLoai(rs.getInt("MaLoai"));
                 sach.setMaNXB(rs.getInt("MaNXB"));
                 sach.setSoLuong(rs.getInt("SoLuong"));
                 sach.setDonGia(rs.getDouble("DonGia"));
                 sach.setHinhAnh(rs.getString("HinhAnh"));
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Kết nối CSDL không thành công");
	    }
	    return sach;
	}
    
}
