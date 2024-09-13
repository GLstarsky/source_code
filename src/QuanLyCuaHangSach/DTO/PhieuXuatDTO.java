package QuanLyCuaHangSach.DTO;

import java.sql.Date;

public class PhieuXuatDTO {
    private int maPhieuXuat;
    private Date ngayTao;
    private int maNV;
    private int maKH;
    private int tongTien;
    private int tongTienPhaiTra;

    public PhieuXuatDTO() {

    }

    public PhieuXuatDTO(int maPhieuXuat, Date ngayTao, int maNV, int maKH, int tongTien, int tongTienPhaiTra) {
        this.maPhieuXuat = maPhieuXuat;
        this.ngayTao = ngayTao;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
        this.tongTienPhaiTra = tongTienPhaiTra;
    }

    public int getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(int maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getTongTienPhaiTra() {
        return tongTienPhaiTra;
    }

    public void setMaCTKM(int tongTienPhaiTra) {
        this.tongTienPhaiTra = tongTienPhaiTra;
    }
}