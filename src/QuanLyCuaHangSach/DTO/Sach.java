package QuanLyCuaHangSach.DTO;

public class Sach {
    private int maSach;
    private String tenSach;
    private int maLoai;
    private int soLuong;
    private int maNXB;
    private String tenTacGia;
    private double donGia;
    private String hinhAnh;

    public Sach() {

    }

    public Sach(int maSach, String tenSach, int maLoai, int soLuong, int maNXB, String tenTacGia, double donGia, String hinhAnh) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maLoai = maLoai;
        this.soLuong = soLuong;
        this.maNXB = maNXB;
        this.tenTacGia = tenTacGia;
        this.donGia = donGia;
        this.hinhAnh = hinhAnh;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
