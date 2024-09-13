package QuanLyCuaHangSach.DTO;

public class ChiTietPhieuXuatDTO {
    private int ma;
    private int maPhieuXuat;
    private int maSach;
    private int soLuong;
    private int donGia;
    private int thanhTien;

    public ChiTietPhieuXuatDTO() {

    }

    public ChiTietPhieuXuatDTO(int ma, int maPhieuXuat, int maSach, int soLuong, int donGia, int thanhTien) {
        this.ma = ma;
        this.maPhieuXuat = maPhieuXuat;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong*donGia;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(int maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}