package QuanLyCuaHangSach.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnect {
	private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/quanlycuahangsach";
    private static Connection conn = null;
    
    MySQLConnect() {
    }

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) { // Kiểm tra nếu kết nối đã đóng hoặc không hợp lệ
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy driver MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối MySQL.");
            e.printStackTrace();
        }
        return conn;
    }

	
    

    
}
