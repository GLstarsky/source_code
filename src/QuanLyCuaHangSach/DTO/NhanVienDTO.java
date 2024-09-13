package QuanLyCuaHangSach.DTO;

public class NhanVienDTO {
	public String ho;
	public String ten;
	public int maNV;
	public String chucVu;
	public String gioiTinh;
	public int luong;
	public String sdt;
	public String diaChi;
	
	
	
	public NhanVienDTO() {
		super();
	}


	public NhanVienDTO(String ho, String ten, int maNV, String chucVu, String gioiTinh, int luong, String sdt,
			String diaChi) {
		super();
		this.ho = ho;
		this.ten = ten;
		this.maNV = maNV;
		this.chucVu = chucVu;
		this.gioiTinh = gioiTinh;
		this.luong = luong;
		this.sdt= sdt;
		this.diaChi = diaChi;
	}


	public String getho() {
		return ho;
	}


	public void setho(String ho) {
		this.ho = ho;
	}


	public String getten() {
		return ten;
	}


	public void setten(String ten) {
		this.ten = ten;
	}


	public int getmaNV() {
		return maNV;
	}


	public void setmaNV(int maNV) {
		this.maNV = maNV;
	}


	public String getchucVu() {
		return chucVu;
	}


	public void setchucVu(String chucVu) {
		this.chucVu = chucVu;
	}


	public String getgioiTinh() {
		return gioiTinh;
	}


	public void setgioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}


	public int getluong() {
		return luong;
	}


	public void setluong(int luong) {
		this.luong = luong;
	}


	public String getsdt() {
		return sdt;
	}


	public void setsdt(String sdt) {
		this.sdt = sdt;
	}


	public String getdiaChi() {
		return diaChi;
	}


	public void setdiaChi(String diaChi) {
		this.diaChi = diaChi;
	}


	
}