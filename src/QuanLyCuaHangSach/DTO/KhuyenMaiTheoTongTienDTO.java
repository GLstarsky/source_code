package QuanLyCuaHangSach.DTO;

public class KhuyenMaiTheoTongTienDTO {
    private String Ma;
    private int MaChuongTrinh;
    private int DieuKienApDung;
    private int PhanTramKM;

    public KhuyenMaiTheoTongTienDTO(String ma, int maChuongTrinh, int dieuKienApDung, int phanTramKM) {
        this.Ma = ma;
        this.MaChuongTrinh = maChuongTrinh;
        this.DieuKienApDung = dieuKienApDung;
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

    public int getDieuKienApDung() {
        return DieuKienApDung;
    }

    public void setDieuKienApDung(int dieuKienApDung) {
        DieuKienApDung = dieuKienApDung;
    }

    public int getPhanTramKM() {
        return PhanTramKM;
    }

    public void setPhanTramKM(int phanTramKM) {
        PhanTramKM = phanTramKM;
    }
}