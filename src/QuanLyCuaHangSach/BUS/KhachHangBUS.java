package QuanLyCuaHangSach.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QuanLyCuaHangSach.DAO.KhachHangDAO;
import QuanLyCuaHangSach.DTO.KhachHangDTO;



public class KhachHangBUS {
	private ArrayList<KhachHangDTO> listKhachHang = null;
    private KhachHangDAO khachHangDAO = new KhachHangDAO();
    
    public KhachHangBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listKhachHang = khachHangDAO.getDanhSachKhachHang();
    }

    public ArrayList<KhachHangDTO> getDanhSachKhachHang() {
        if (this.listKhachHang == null)
            docDanhSach();
        return this.listKhachHang;
    }
    public ArrayList<KhachHangDTO> timKiemKhachHang(String tuKhoa){
    	ArrayList<KhachHangDTO> dskh = this.getDanhSachKhachHang();
    	ArrayList<KhachHangDTO> ketQua = new ArrayList<>();
    	if(!tuKhoa.equals("")) {
    		for (KhachHangDTO khachHangDTO : dskh) {
				if(khachHangDTO.getho().toLowerCase().contains(tuKhoa.toLowerCase()) || 
				khachHangDTO.getten().toLowerCase().contains(tuKhoa.toLowerCase()) ||
				khachHangDTO.getsdt().toLowerCase().contains(tuKhoa.toLowerCase()) ||
				khachHangDTO.getdiaChi().toLowerCase().contains(tuKhoa.toLowerCase())) {
					ketQua.add(khachHangDTO);
				}
			}
    	}
    	return ketQua;
    }
    
    public boolean themKhachHang(String ho, String ten, String diaChi,String sdt) {
        if (ten.trim().equals("")) {
        	JOptionPane.showMessageDialog(null, "Tên không được để trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        KhachHangDTO kh = new KhachHangDTO();
        kh.setho(ho);
        kh.setten(ten);
        kh.setdiaChi(diaChi);
        kh.setsdt(sdt);
        boolean flag = khachHangDAO.themKhachHang(kh);
        if (flag) {
        	JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
        	JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }

    public boolean suaKhachHang(String ma, String ho, String ten, String diaChi,String sdt) {
        if (ten.trim().equals("")) {
        	JOptionPane.showMessageDialog(null, "Tên không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int maKH = Integer.parseInt(ma);
        KhachHangDTO kh = new KhachHangDTO();
        kh.setho(ho);
        kh.setten(ten);
        kh.setsdt(sdt);
        kh.setdiaChi(diaChi);
        kh.setmaKH(maKH);
        boolean flag = khachHangDAO.capNhatKhachHang(kh);
        if (flag) {
        	JOptionPane.showMessageDialog(null, "Cập nhật khác hàng thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
        	JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thất bại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }

    public boolean xoaKhachHang(String ma) {
    	try {
            int maKH = Integer.parseInt(ma);
            boolean flag = khachHangDAO.xoaKhachHang(maKH);
            if (!flag) {
                JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            return flag;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa nhân viên: " + e.getMessage(), "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
}
