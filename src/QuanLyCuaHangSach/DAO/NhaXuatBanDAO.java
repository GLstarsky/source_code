package QuanLyCuaHangSach.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import QuanLyCuaHangSach.DTO.NhaXuatBan;

public class NhaXuatBanDAO {
	private Connection connection;

    public NhaXuatBanDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<NhaXuatBan> layTatCaNhaXuatBan() {
    	ArrayList<NhaXuatBan> danhSachNXB = new ArrayList<>();
        String query = "SELECT ma, ten FROM nhaxuatban";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int maNXB = resultSet.getInt("ma");
                String tenNXB = resultSet.getString("ten");
                NhaXuatBan nxb = new NhaXuatBan(maNXB, tenNXB);
                danhSachNXB.add(nxb);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return danhSachNXB;
    }

}
