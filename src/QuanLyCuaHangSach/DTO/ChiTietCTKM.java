package QuanLyCuaHangSach.DTO;

public class ChiTietCTKM {
    private String maCTCTKM; // Sửa kiểu dữ liệu thành String
    private int maChuongTrinh;
    private int phanTramKM; // Sửa kiểu dữ liệu thành int
    private int maSach;
    
    // Constructors
    public ChiTietCTKM() {
    }
    
    public ChiTietCTKM(String maCTCTKM, int maChuongTrinh, int phanTramKM, int maSach) {
        this.maCTCTKM = maCTCTKM;
        this.maChuongTrinh = maChuongTrinh;
        this.phanTramKM = phanTramKM;
        this.maSach = maSach;
    }
    
    // Getters and setters
    public String getMaCTCTKM() {
        return maCTCTKM;
    }
    
    public void setMaCTCTKM(String maCTCTKM) {
        this.maCTCTKM = maCTCTKM;
    }
    
    public int getMaChuongTrinh() {
        return maChuongTrinh;
    }
    
    public void setMaChuongTrinh(int maChuongTrinh) {
        this.maChuongTrinh = maChuongTrinh;
    }
    
    public int getPhanTramKM() {
        return phanTramKM;
    }
    
    public void setPhanTramKM(int phanTramKM) {
        this.phanTramKM = phanTramKM;
    }
    
    public int getMaSach() {
        return maSach;
    }
    
    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }
}
