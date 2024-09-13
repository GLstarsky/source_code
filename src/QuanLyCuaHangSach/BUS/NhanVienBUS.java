package QuanLyCuaHangSach.BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import QuanLyCuaHangSach.DAO.NhanVienDAO;
import QuanLyCuaHangSach.DTO.NhanVienDTO;

//import MyCustom.MyDialog;


public class NhanVienBUS {
	private NhanVienDAO nvDAO = new NhanVienDAO();
    private ArrayList<NhanVienDTO> listNhanVien = null;

    public NhanVienBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNhanVien = nvDAO.getDanhSachNhanVien();
    }

    public ArrayList<NhanVienDTO> getDanhSachNhanVien() {
        if (this.listNhanVien == null)
            docDanhSach();
        return this.listNhanVien;
    }

    public boolean themNhanVien(String ho, String ten, String gioiTinh, String chucVu,String sdt,int luong,String diaChi) {
    
        NhanVienDTO nv = new NhanVienDTO();
        nv.setho(ho);
        nv.setten(ten);
        nv.setgioiTinh(gioiTinh);
        nv.setchucVu(chucVu);
        nv.setdiaChi(diaChi);
        nv.setluong(luong);
        nv.setsdt(sdt);
        boolean flag = nvDAO.themNhanVien(nv);
        if (!flag) {
        	JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
        	JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }

    public boolean capNhatNhanVien(String ma,String ho, String ten, String gioiTinh, String chucVu,String sdt,int luong,String diaChi) {
        int maNV = Integer.parseInt(ma);
        ho = ho.trim();
        ten = ten.trim();
        chucVu = chucVu.trim();
        if (ten.equals("")) {
        	JOptionPane.showMessageDialog(null, "Tên không được để trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        NhanVienDTO nv = new NhanVienDTO();
        nv.setmaNV(maNV);
        nv.setho(ho);
        nv.setten(ten);
        nv.setgioiTinh(gioiTinh);
        nv.setchucVu(chucVu);
        nv.setdiaChi(diaChi);
        nv.setluong(luong);
        nv.setsdt(sdt);
        boolean flag = nvDAO.capNhatNhanVien(nv);
        if (flag) {
        	JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thất bại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
        	JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }

    public ArrayList<NhanVienDTO> timNhanVien(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<NhanVienDTO> dsnv = new ArrayList<>();
        for (NhanVienDTO nv : listNhanVien) {
            if (nv.getho().toLowerCase().contains(tuKhoa) || nv.getten().toLowerCase().contains(tuKhoa) ||
                    nv.getgioiTinh().toLowerCase().contains(tuKhoa) || nv.getchucVu().toLowerCase().contains(tuKhoa)) {
                dsnv.add(nv);
            }
        }
        return dsnv;
    }

    public boolean xoaNhanVien(String ma) {
        try {
            int maNV = Integer.parseInt(ma);
            boolean flag = nvDAO.xoaNhanVien(maNV);
            if (!flag) {
                JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
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
