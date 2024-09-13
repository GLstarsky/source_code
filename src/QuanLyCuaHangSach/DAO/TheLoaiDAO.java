package QuanLyCuaHangSach.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import QuanLyCuaHangSach.DTO.TheLoai;

public class TheLoaiDAO {

    private Connection connection;

    // Constructor
    public TheLoaiDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<TheLoai> layTatCaTheLoai() {
        ArrayList<TheLoai> theLoais = new ArrayList<>();
        Statement stmt = null;
        try {
            if (connection != null) {
                String sql = "SELECT * FROM phanloai"; // Tên bảng thể loại sách
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    TheLoai theLoai = new TheLoai(rs.getInt("ma"), rs.getString("ten"));
                    theLoais.add(theLoai);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return theLoais;
    }

    public int demTheLoai() throws SQLException {
        Statement stmt = null;
        try {
            if (connection != null) {
                stmt = connection.createStatement();

                // Thực hiện truy vấn để đếm số dòng trong bảng nhà cung cấp
                String query = "SELECT COUNT(*) AS tongSoTheLoai FROM phanloai";
                ResultSet rs = stmt.executeQuery(query);

                // Lấy số lượng nhà cung cấp
                int tongSoTheLoai = 0;
                if (rs.next()) {
                    tongSoTheLoai = rs.getInt("tongSoTheLoai");
                }

                return tongSoTheLoai;
            }
        } finally {
            if (stmt != null)
                stmt.close();
        }
        return 0;
    }
}

