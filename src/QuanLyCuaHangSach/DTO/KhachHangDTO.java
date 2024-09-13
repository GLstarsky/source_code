package QuanLyCuaHangSach.DTO;

public class KhachHangDTO {
	private String ho;
	private String ten;
	private int maKH;
	private String sdt;
	private String diaChi;
	public KhachHangDTO(String ho, String ten, int maKH, String sdt, String diaChi) {
		super();
		this.ho = ho;
		this.ten = ten;
		this.maKH = maKH;
		this.sdt = sdt;
		this.diaChi = diaChi;
	}
	public KhachHangDTO() {
		super();
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
	public int getmaKH() {
		return maKH;
	}
	public void setmaKH(int maKH) {
		this.maKH = maKH;
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
