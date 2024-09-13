package QuanLyCuaHangSach.DTO;

public class Sach2DTO {
    private int maSach;
    private String tenSach;
    private String tacGia;
    private String phanLoai;
    private String NXB;
    private int soLuong;
    private int donGia;
    private String hinhAnh;

    public Sach2DTO() {
    }

    public Sach2DTO(int maSach, String tenSach, String tacGia, String phanLoai, String NXB, int soLuong, int donGia, String hinhAnh) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.phanLoai = phanLoai;
        this.NXB = NXB;
        this.soLuong = soLuong;
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

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}