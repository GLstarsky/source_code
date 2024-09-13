package QuanLyCuaHangSach.DTO;

import java.sql.Date;

public class ChuongTrinhKhuyenMai2DTO {
    private int Ma;
    private String Ten;
    private Date NgayBD;
    private Date NgayKT;

    public ChuongTrinhKhuyenMai2DTO(int ma, String ten, Date ngayBD, Date ngayKT) {
        this.Ma = ma;
        this.Ten = ten;
        this.NgayBD = ngayBD;
        this.NgayKT = ngayKT;
    }

    public int getMa() {
        return Ma;
    }

    public void setMa(int ma) {
        Ma = ma;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public Date getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(Date ngayBD) {
        NgayBD = ngayBD;
    }

    public Date getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(Date ngayKT) {
        NgayKT = ngayKT;
    }
}