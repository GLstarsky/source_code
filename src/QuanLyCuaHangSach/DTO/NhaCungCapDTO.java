package QuanLyCuaHangSach.DTO;

public class NhaCungCapDTO {
    public int ma;
    public String tenNCC;

    public NhaCungCapDTO() {

    }

    public NhaCungCapDTO(int ma, String tenNCC) {
        this.ma = ma;
        this.tenNCC = tenNCC;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }
}
