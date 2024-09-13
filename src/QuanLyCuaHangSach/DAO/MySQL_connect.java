package QuanLyCuaHangSach.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQL_connect {
	private String hostName = "localhost:3306";
	private String dbName = "quanlycuahangsach";
	private String username = "root";
	private String password = "";
	private String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName;

	public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionURL, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
