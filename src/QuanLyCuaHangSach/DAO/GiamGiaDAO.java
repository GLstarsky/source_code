package QuanLyCuaHangSach.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import QuanLyCuaHangSach.DTO.GiamGia;

public class GiamGiaDAO {

    // Lấy danh sách tất cả các chương trình giảm giá
	public static ArrayList<GiamGia> getListGiamGia() {
        Connection conn = MySQLConnect.getConnection();
        ArrayList<GiamGia> dsGiamGia = new ArrayList<>();

        if (conn != null) {
            try {
                String sql = "SELECT Ma, Ten, NgayBD, NgayKT FROM chuongtrinhkhuyenmai";
                PreparedStatement pre = conn.prepareStatement(sql);
                ResultSet rs = pre.executeQuery();

                while (rs.next()) {
                    GiamGia giamGia = new GiamGia();
                    giamGia.setMa(rs.getInt("Ma"));
                    giamGia.setTen(rs.getString("Ten"));
                    giamGia.setNgayBD(rs.getDate("NgayBD"));
                    giamGia.setNgayKT(rs.getDate("NgayKT"));
                    dsGiamGia.add(giamGia);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return dsGiamGia;
    }

    // Thêm một chương trình giảm giá vào cơ sở dữ liệu
    public static boolean themGiamGia(GiamGia giamGia) {
        Connection conn = MySQLConnect.getConnection();
        if (conn != null) {
            try {
                String sql = "INSERT INTO chuongtrinhkhuyenmai (Ten, NgayBD, NgayKT) VALUES (?, ?, ?)";
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, giamGia.getTen());
                pre.setDate(2, giamGia.getNgayBD());
                pre.setDate(3, giamGia.getNgayKT());

                int rowsAffected = pre.executeUpdate();

                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // Cập nhật thông tin một chương trình giảm giá
    public static boolean capNhatGiamGia(GiamGia giamGia) {
        Connection conn = MySQLConnect.getConnection();
        if (conn != null) {
            try {
                String sql = "UPDATE chuongtrinhkhuyenmai SET Ten = ?, NgayBD = ?, NgayKT = ? WHERE Ma = ?";
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1, giamGia.getTen());
                pre.setDate(2, giamGia.getNgayBD());
                pre.setDate(3, giamGia.getNgayKT());
                pre.setInt(4, giamGia.getMa());

                int rowsAffected = pre.executeUpdate();

                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // Xóa một chương trình giảm giá khỏi cơ sở dữ liệu
    public static boolean xoaGiamGia(int ma) {
        Connection conn = MySQLConnect.getConnection();
        if (conn != null) {
            try {
                String sql = "DELETE FROM chuongtrinhkhuyenmai WHERE Ma = ?";
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1, ma);

                int rowsAffected = pre.executeUpdate();

                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

 // Tìm kiếm chương trình giảm giá theo mã, tên, ngày bắt đầu và ngày kết thúc
    public static ArrayList<GiamGia> timKiemGiamGia(Integer ma, String ten, Date ngayBD, Date ngayKT) {
        Connection conn = MySQLConnect.getConnection();
        ArrayList<GiamGia> dsGiamGia = new ArrayList<>();

        if (conn != null) {
            try {
                StringBuilder sql = new StringBuilder("SELECT Ma, Ten, NgayBD, NgayKT FROM chuongtrinhkhuyenmai WHERE 1=1");

                if (ma != null) {
                    sql.append(" AND Ma = ?");
                }
                if (ten != null && !ten.isEmpty()) {
                    sql.append(" AND Ten LIKE ?");
                }
                if (ngayBD != null && ngayKT != null) {
                    sql.append(" AND NgayBD >= ? AND NgayKT <= ?");
                } else if (ngayBD != null) {
                    sql.append(" AND NgayBD >= ?");
                } else if (ngayKT != null) {
                    sql.append(" AND NgayKT <= ?");
                }

                PreparedStatement pre = conn.prepareStatement(sql.toString());
                int index = 1;

                if (ma != null) {
                    pre.setInt(index++, ma);
                }
                if (ten != null && !ten.isEmpty()) {
                    pre.setString(index++, "%" + ten + "%");
                }
                if (ngayBD != null && ngayKT != null) {
                    pre.setDate(index++, ngayBD);
                    pre.setDate(index++, ngayKT);
                } else if (ngayBD != null) {
                    pre.setDate(index++, ngayBD);
                } else if (ngayKT != null) {
                    pre.setDate(index++, ngayKT);
                }

                ResultSet rs = pre.executeQuery();

                while (rs.next()) {
                    GiamGia giamGia = new GiamGia();
                    giamGia.setMa(rs.getInt("Ma"));
                    giamGia.setTen(rs.getString("Ten"));
                    giamGia.setNgayBD(rs.getDate("NgayBD"));
                    giamGia.setNgayKT(rs.getDate("NgayKT"));
                    dsGiamGia.add(giamGia);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return dsGiamGia;
    }

}
