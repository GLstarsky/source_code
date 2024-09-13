package QuanLyCuaHangSach.DTO;

public class KhuyenMaiTheoSachDTO {
    private String Ma;
    private int MaChuongTrinh;
    private int MaSach;
    private int PhanTramKM;

    public KhuyenMaiTheoSachDTO(String ma, int maChuongTrinh, int maSach, int phanTramKM) {
        this.Ma = ma;
        this.MaChuongTrinh = maChuongTrinh;
        this.MaSach = maSach;
        this.PhanTramKM = phanTramKM;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String ma) {
        Ma = ma;
    }

    public int getMaChuongTrinh() {
        return MaChuongTrinh;
    }

    public void setMaChuongTrinh(int maChuongTrinh) {
        MaChuongTrinh = maChuongTrinh;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public int getPhanTramKM() {
        return PhanTramKM;
    }

    public void setPhanTramKM(int phanTramKM) {
        PhanTramKM = phanTramKM;
    }
}