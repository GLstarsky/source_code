package QuanLyCuaHangSach.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import QuanLyCuaHangSach.BUS.ChiTietPhieuXuatBUS;
import QuanLyCuaHangSach.BUS.PhieuXuatBUS;
import QuanLyCuaHangSach.BUS.Sach2BUS;
import QuanLyCuaHangSach.DTO.ChiTietPhieuXuatDTO;
import QuanLyCuaHangSach.DTO.PhieuXuatDTO;
import QuanLyCuaHangSach.DTO.Sach2DTO;
//import QuanLyCuaHangSach.GUI.TrangBanHang.NonEditableTableModel;
//import QuanLyCuaHangSach.GUI.TrangBanHang.chiTietGioHang;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;

public class BanHangGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblGioHang;
	private JTable table_1;
	public static int tongThanhToanBanDau;
	public static Date ngayTaoHoaDon;
	private static int soLuongGioHangHienTai;

	private JTable tblListHoaDon;
	private JTable tblListChiTietHoaDon;
	private JTextField txtMaKH;
	private JTextField txtMaNV;
	private JTextField txtTuTongTien;
	private JTextField txtDenTongTien;
	private JDateChooser dctuNgay;
	private JDateChooser dcdenNgay;

	public static ArrayList<chiTietGioHang> gioHang = new ArrayList<>();

	public class NonEditableTableModel extends DefaultTableModel {
		public NonEditableTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
//=========================================================================

	private String formatGiaTien(int giaTien) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###.##₫");
		return decimalFormat.format(giaTien);
	}

	private void docListHoaDon(JTable tblListHoaDon, ArrayList<PhieuXuatDTO> dsPhieuXuat) {
		PhieuXuatBUS phieuxuatBUS = new PhieuXuatBUS();
		if (dsPhieuXuat == null || dsPhieuXuat.isEmpty()) {
			phieuxuatBUS.docPhieuXuat();
			dsPhieuXuat = phieuxuatBUS.getListPhieuXuat();
		}
		Object[][] rowData = new Object[dsPhieuXuat.size()][6];
		Object[] columnNames = { "Mã hóa đơn", "Ngày", "Mã NV", "Mã KH", "Tổng tiền", "Tổng khách trả" };
		for (int i = 0; i < dsPhieuXuat.size(); i++) {
			PhieuXuatDTO phieuxuat = dsPhieuXuat.get(i);
			rowData[i][0] = phieuxuat.getMaPhieuXuat();
			rowData[i][1] = phieuxuat.getNgayTao();
			rowData[i][2] = phieuxuat.getMaNV();
			rowData[i][3] = phieuxuat.getMaKH();
			rowData[i][4] = formatGiaTien(phieuxuat.getTongTien());
			rowData[i][5] = formatGiaTien(phieuxuat.getTongTienPhaiTra());
		}
		NonEditableTableModel model = new NonEditableTableModel(rowData, columnNames);
		tblListHoaDon.setModel(model);
		tblListHoaDon.getColumnModel().getColumn(0).setPreferredWidth(92);
		tblListHoaDon.getColumnModel().getColumn(1).setPreferredWidth(92);
		tblListHoaDon.getColumnModel().getColumn(2).setPreferredWidth(92);
		tblListHoaDon.getColumnModel().getColumn(3).setPreferredWidth(92);
		tblListHoaDon.getColumnModel().getColumn(4).setPreferredWidth(92);
		tblListHoaDon.getColumnModel().getColumn(5).setPreferredWidth(92);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tblListHoaDon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblListHoaDon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tblListHoaDon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tblListHoaDon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tblListHoaDon.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		tblListHoaDon.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		tblListHoaDon.setRowHeight(25);

	}

	private void docListChiTietHoaDon(JTable tblListChiTietHoaDon, int maPhieuXuat) {
		ChiTietPhieuXuatBUS chiTietPhieuXuatBUS = new ChiTietPhieuXuatBUS();
		ArrayList<ChiTietPhieuXuatDTO> dsChiTietPhieuXuat = chiTietPhieuXuatBUS.getListChiTietPhieuXuat(maPhieuXuat);

		if (dsChiTietPhieuXuat == null || dsChiTietPhieuXuat.isEmpty()) {
			chiTietPhieuXuatBUS.docChiTietPhieuXuat(maPhieuXuat);
			dsChiTietPhieuXuat = chiTietPhieuXuatBUS.getListChiTietPhieuXuat(maPhieuXuat);
		}

		Object[][] rowData = new Object[dsChiTietPhieuXuat.size()][5];
		Object[] columnNames = { "Mã chi tiết", "Mã sách", "Số lượng", "Đơn giá", "Tổng tiền" };

		for (int i = 0; i < dsChiTietPhieuXuat.size(); i++) {
			ChiTietPhieuXuatDTO chiTietPhieuXuat = dsChiTietPhieuXuat.get(i);
			rowData[i][0] = chiTietPhieuXuat.getMa();
			rowData[i][1] = chiTietPhieuXuat.getMaSach();
			rowData[i][2] = chiTietPhieuXuat.getSoLuong();
			rowData[i][3] = formatGiaTien(chiTietPhieuXuat.getDonGia());
			rowData[i][4] = formatGiaTien(chiTietPhieuXuat.getThanhTien());
		}
		NonEditableTableModel model = new NonEditableTableModel(rowData, columnNames);
		tblListChiTietHoaDon.setModel(model);
		tblListChiTietHoaDon.getColumnModel().getColumn(0).setPreferredWidth(69);
		tblListChiTietHoaDon.getColumnModel().getColumn(1).setPreferredWidth(69);
		tblListChiTietHoaDon.getColumnModel().getColumn(2).setPreferredWidth(69);
		tblListChiTietHoaDon.getColumnModel().getColumn(3).setPreferredWidth(69);
		tblListChiTietHoaDon.getColumnModel().getColumn(4).setPreferredWidth(69);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		tblListChiTietHoaDon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblListChiTietHoaDon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tblListChiTietHoaDon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tblListChiTietHoaDon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tblListChiTietHoaDon.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		tblListChiTietHoaDon.setRowHeight(25);
	}

	private void xuLyClickDsHoaDon(JTable tblListtHoaDon, JLabel lblChiTiet_MaHD, JTable tblListChiTietHoaDon) {
		int row = tblListtHoaDon.getSelectedRow();
		if (row > -1) {
			int maPhieuXuat = (int) tblListtHoaDon.getValueAt(row, 0);
			docListChiTietHoaDon(tblListChiTietHoaDon, maPhieuXuat);
			lblChiTiet_MaHD.setText(tblListtHoaDon.getValueAt(row, 0) + "");
		}
	}


	private String xoaKhoangTrangThua(String input) {
		String s = input.replaceAll("\\s+", " ");
		return s.trim();
	}

	public static boolean checkSoThuc(String s) {
		try {
			double num = Double.parseDouble(s);
			return num >= 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private Double stringSangDouble(String s) {
		return Double.parseDouble(s);
	}

	private Double laySoTuChuoi(String s) {
		return Double.parseDouble(s.replaceAll("[^0-9]", ""));
	}

////////////////////////////////////////////////////
	private void docListSach(JTable table) {
		Sach2BUS sachBUS = new Sach2BUS();
		ArrayList<Sach2DTO> dsSach = sachBUS.getListSach();
		if (dsSach == null || dsSach.isEmpty()) {
			sachBUS.docSach();
			dsSach = sachBUS.getListSach();
		}

		Object[][] rowData = new Object[dsSach.size()][4];
		Object[] columnNames = { "Mã sách", "Tên sách", "Giá bán", "SL kho" };

		for (int i = 0; i < dsSach.size(); i++) {
			Sach2DTO sach = dsSach.get(i);
			rowData[i][0] = sach.getMaSach();
			rowData[i][1] = sach.getTenSach();
			rowData[i][2] = formatGiaTien(sach.getDonGia());
			rowData[i][3] = sach.getSoLuong();
		}

		NonEditableTableModel model = new NonEditableTableModel(rowData, columnNames);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(42);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	}

	private void themHinhAnh(JLabel lblHinhAnh, String img) {
		String duongdan = "src\\image\\";
		String linkdung = duongdan + img;
		ImageIcon icon = new ImageIcon(linkdung);
		Image image = icon.getImage();
		int newWidth = 80;
		int newHeight = 117;
		Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		lblHinhAnh.setIcon(scaledIcon);
		lblHinhAnh.setHorizontalAlignment(JLabel.CENTER);
		lblHinhAnh.setVerticalAlignment(JLabel.CENTER);
		Graphics2D g2d = (Graphics2D) lblHinhAnh.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	}

	private void xuLyClickDsSach(JTable tblDsSach, JLabel lbTenSach, JLabel lblHinhAnh, JLabel lbTheLoai,
			JLabel lbTacGia, JLabel lbNXB, JLabel lbGiaBan, JLabel lbMaSach, JButton btnThemVaoGioHang) {
		int row = tblDsSach.getSelectedRow();
		if (row > -1) {
			btnThemVaoGioHang.setVisible(true);
			Sach2BUS sach = new Sach2BUS();
			int maSach = Integer.parseInt((tblDsSach.getValueAt(row, 0) + ""));
			String tenSach = sach.getSach(maSach).getTenSach();
			String theLoai = sach.getSach(maSach).getPhanLoai();
			String tacGia = sach.getSach(maSach).getTacGia();
			String NXB = sach.getSach(maSach).getNXB();
			String giaBan = formatGiaTien(sach.getSach(maSach).getDonGia()) + "";
			String img = sach.getSach(maSach).getHinhAnh();
			lbMaSach.setText(maSach + "");
			lbTenSach.setText(tenSach);
			lbTheLoai.setText(theLoai);
			lbTacGia.setText(tacGia);
			lbNXB.setText(NXB);
			lbGiaBan.setText(giaBan);
			themHinhAnh(lblHinhAnh, img);
		}
	}

	public class chiTietGioHang {
		private int maSach;
		private String tenSach;
		private int donGia;
		private int soLuong;
		private int tongTien;

		public chiTietGioHang(int maSach, String tenSach, int donGia, int soLuong, int tongTien) {
			this.maSach = maSach;
			this.tenSach = tenSach;
			this.donGia = donGia;
			this.soLuong = soLuong;
			this.tongTien = tongTien;
		}

		public int getMaSach() {
			return maSach;
		}

		public String getTenSach() {
			return tenSach;
		}

		public int getDonGia() {
			return donGia;
		}

		public int getSoLuong() {
			return soLuong;
		}

		public int getTongTien() {
			return tongTien;
		}
	}

	private void hienThiBangGioHang(JTable tblGioHang, ArrayList<chiTietGioHang> gioHang) {
		Object[][] rowData = new Object[gioHang.size()][5];
		Object[] columnNames = { "Mã sách", "Tên sách", "Giá", "SL", "Tổng tiền" };
		DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
		model.setRowCount(0);
		for (int i = 0; i < gioHang.size(); i++) {
			chiTietGioHang ctgh = gioHang.get(i);
			rowData[i][0] = ctgh.getMaSach();
			rowData[i][1] = ctgh.getTenSach();
			rowData[i][2] = formatGiaTien(ctgh.getDonGia());
			rowData[i][3] = ctgh.getSoLuong();
			rowData[i][4] = formatGiaTien(ctgh.getTongTien());
		}
		NonEditableTableModel model2 = new NonEditableTableModel(rowData, columnNames);
		tblGioHang.setModel(model2);
		tblGioHang.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblGioHang.getColumnModel().getColumn(1).setPreferredWidth(270);
		tblGioHang.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblGioHang.getColumnModel().getColumn(3).setPreferredWidth(15);
		tblGioHang.getColumnModel().getColumn(4).setPreferredWidth(80);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tblGioHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblGioHang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tblGioHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tblGioHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tblGioHang.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		for (int i = 0; i < rowData.length; i++) {
			model.addRow(rowData[i]);
		}
	}

	private void capNhatTongHoaDon(ArrayList<chiTietGioHang> gioHang, JLabel lbTongThanhToan) {
		int tongThanhToan = 0;
		for (int i = 0; i < gioHang.size(); i++) {
			int tongChiTiet = (int) gioHang.get(i).getSoLuong() * (int) gioHang.get(i).getDonGia();
			tongThanhToan += tongChiTiet;
		}
		lbTongThanhToan.setText(formatGiaTien(tongThanhToan) + "");
		tongThanhToanBanDau = tongThanhToan;
	}

	private void xuLyClickDsGioHang(JTable tblDsGioHang, JLabel lbMaSach, JLabel lbTenSach, JLabel lbGiaBan,
			JSpinner spnSoLuong, JLabel lbTongTien, JButton btnXoaKhoiGioHang, JButton btnThayDoiSoLuongGioHang) {
		int row = tblDsGioHang.getSelectedRow();
		if (row > -1) {
			btnXoaKhoiGioHang.setVisible(true);
			btnThayDoiSoLuongGioHang.setVisible(true);
			String maSach = (tblDsGioHang.getValueAt(row, 0) + "");
			String tenSach = (tblDsGioHang.getValueAt(row, 1) + "");
			String giaBan = (tblDsGioHang.getValueAt(row, 2) + "");
			String soLuong = (tblDsGioHang.getValueAt(row, 3) + "");
			String tongTien = (tblDsGioHang.getValueAt(row, 4) + "");
			lbMaSach.setText(maSach);
			lbTenSach.setText(tenSach);
			lbGiaBan.setText(giaBan);
			spnSoLuong.setValue(Integer.parseInt(soLuong));
			lbTongTien.setText(tongTien);
			soLuongGioHangHienTai = (int) (tblDsGioHang.getValueAt(row, 3));
		}
	}

	private void xuLyClickThemGioHang(JTable tblGioHang, JLabel lbChiTietMaSach, JSpinner spnChiTietSoLuong,
			ArrayList<chiTietGioHang> gioHang, JLabel lbTongThanhToan, JLabel lblGioHang_MaSach,
			JLabel lblGioHang_TongTien) {
		int maSach = Integer.parseInt(lbChiTietMaSach.getText());
		int soLuongGioHang = (int) spnChiTietSoLuong.getValue();
		Sach2BUS sach = new Sach2BUS();
		String tenSach = sach.getSach(maSach).getTenSach();
		int donGia = sach.getSach(maSach).getDonGia();
		int tongTien = soLuongGioHang * donGia;
		int tonTaiMaChiTiet = 0;
		for (int i = 0; i < gioHang.size(); i++) {
			if (gioHang.get(i).maSach == maSach) {
				gioHang.get(i).soLuong += soLuongGioHang;
				gioHang.get(i).tongTien += tongTien;
				tonTaiMaChiTiet = 1;
				break;
			}
		}
		if (tonTaiMaChiTiet == 0) {
			chiTietGioHang ctgh = new chiTietGioHang(maSach, tenSach, donGia, soLuongGioHang, tongTien);
			gioHang.add(ctgh);
		}
		hienThiBangGioHang(tblGioHang, gioHang);
		capNhatTongHoaDon(gioHang, lbTongThanhToan);
	}

	private void xuLyClickXoaChiTietGioHang(JTable tblGioHang, JLabel lbChiTietMaSach,
			ArrayList<chiTietGioHang> gioHang, JLabel lbTongThanhToan, JLabel lblGioHang_TongTien) {
		int maSach = Integer.parseInt(lbChiTietMaSach.getText());
		for (int i = 0; i < gioHang.size(); i++) {
			if (gioHang.get(i).maSach == maSach) {
				gioHang.remove(i);
				break;
			}
		}
		hienThiBangGioHang(tblGioHang, gioHang);
		capNhatTongHoaDon(gioHang, lbTongThanhToan);
	}

	private void thayDoiSoLuongGioHang(ArrayList<chiTietGioHang> gioHang, JSpinner spnGioHang_SoLuong,
			JLabel lblGioHang_MaSach, JLabel lblGioHang_TongTien) {
		for (int i = 0; i < gioHang.size(); i++) {
			if (gioHang.get(i).getMaSach() == Integer.parseInt(lblGioHang_MaSach.getText())) {
				gioHang.get(i).soLuong = (int) spnGioHang_SoLuong.getValue();
				gioHang.get(i).tongTien = gioHang.get(i).soLuong * gioHang.get(i).donGia;
				lblGioHang_TongTien.setText(formatGiaTien(gioHang.get(i).tongTien));
				break;
			}
		}
	}

	private void giamSoLuongKhoTamThoi(JTable tblSanPhamBan, int maSach, int soLuongMua) {
		for (int i = 0; i < tblSanPhamBan.getRowCount(); i++) {
			int maSachTrongKho = (int) tblSanPhamBan.getValueAt(i, 0);
			if (maSachTrongKho == maSach) {
				if ((int) tblSanPhamBan.getValueAt(i, 3) >= soLuongMua) {
					int soLuongKho = (int) tblSanPhamBan.getValueAt(i, 3) - soLuongMua;
					tblSanPhamBan.setValueAt(soLuongKho, i, 3);
				} else {
					JOptionPane.showMessageDialog(null, "Không đủ sản phẩm trong kho");
				}

			}
		}
	}
	

	private void tangSoLuongKhoTamThoi(JTable tblSanPhamBan, int maSach, int soLuongXoaKhoiGio) {
		for (int i = 0; i < tblSanPhamBan.getRowCount(); i++) {
			int maSachTrongKho = (int) tblSanPhamBan.getValueAt(i, 0);
			if (maSachTrongKho == maSach) {
				int soLuongKho = (int) tblSanPhamBan.getValueAt(i, 3) + soLuongXoaKhoiGio;
				tblSanPhamBan.setValueAt(soLuongKho, i, 3);
			}
		}
	}



	private void taoChiTietPhieuXuat(int maHD, chiTietGioHang ctgh) {
		int maPhieuXuat = maHD;
		int maSach = ctgh.getMaSach();
		int soLuong = ctgh.getSoLuong();
		int donGia = ctgh.getDonGia();
		int thanhTien = ctgh.getTongTien();
		ChiTietPhieuXuatDTO ctpx = new ChiTietPhieuXuatDTO(0, maPhieuXuat, maSach, soLuong, donGia, thanhTien);
		ChiTietPhieuXuatBUS ctpxBUS = new ChiTietPhieuXuatBUS();
		ctpxBUS.themChiTietPhieuXuat(ctpx);
	}

	private void xoaGioHang(ArrayList<chiTietGioHang> gioHang) {
		gioHang.clear();
		hienThiBangGioHang(tblGioHang, gioHang);
	}

	private void resetGioHang(JLabel lblTongThanhToan, JLabel lblGioHang_MaSach, JLabel lblGioHang_TenSach,
			JLabel lblGioHang_DonGia, JSpinner spnGioHang_SoLuong, JLabel lblGioHang_TongTien) {
		xoaGioHang(gioHang);
		lblTongThanhToan.setText("");
		lblGioHang_MaSach.setText("");
		lblGioHang_TenSach.setText("");
		lblGioHang_DonGia.setText("");
		spnGioHang_SoLuong.setValue(1);
		lblGioHang_TongTien.setText("");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BanHangGUI frame = new BanHangGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BanHangGUI() {
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 1011, 701);
		add(tabbedPane);

		JPanel panelbanhang = new JPanel();
		tabbedPane.addTab("Bán hàng", null, panelbanhang, null);
		panelbanhang.setLayout(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBounds(10, 10, 986, 670);
		panelbanhang.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-2, 10, 636, 317);
		panel_1.setBackground(SystemColor.text);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 623, 283);
		panel_1.add(scrollPane);
		JTable tblSanPhamBan = new JTable();
		scrollPane.setViewportView(tblSanPhamBan);
		tblSanPhamBan.setCellSelectionEnabled(true);
		tblSanPhamBan.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblSanPhamBan.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", "Gi\u00E1 b\u00E1n", "SL kho" }) {
			Class[] columnTypes = new Class[] { String.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		tblSanPhamBan.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		tblSanPhamBan.setColumnSelectionAllowed(true);
		tblSanPhamBan.setFillsViewportHeight(true);
		tblSanPhamBan.setForeground(new Color(0, 0, 0));
		tblSanPhamBan.setSurrendersFocusOnKeystroke(true);
		tblSanPhamBan.setBackground(SystemColor.controlHighlight);
		tblSanPhamBan.setRowHeight(25);

		docListSach(tblSanPhamBan);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		JLabel lbTitleSanPhamBan = new JLabel("Danh sách sản phẩm ");
		lbTitleSanPhamBan.setBounds(196, 0, 172, 31);
		lbTitleSanPhamBan.setFont(new Font("Times New Roman", Font.BOLD, 19));
		panel_1.add(lbTitleSanPhamBan);

		table_1 = new JTable();
		table_1.setBounds(23, 442, 1, 1);
		panel.add(table_1);

		JLabel lblChiTitGi = new JLabel("Chi tiết giỏ hàng");
		lblChiTitGi.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblChiTitGi.setBounds(730, 409, 147, 22);
		panel.add(lblChiTitGi);

		JPanel pnChiTietGH = new JPanel();
		pnChiTietGH.setBounds(656, 433, 292, 166);
		panel.add(pnChiTietGH);
		pnChiTietGH.setLayout(null);

		JLabel lblTenSachGioHang = new JLabel("Tên sách:");
		lblTenSachGioHang.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblTenSachGioHang.setBounds(10, 10, 60, 25);
		pnChiTietGH.add(lblTenSachGioHang);

		JLabel lblNewLabel_2_5_1 = new JLabel("Đơn giá:");
		lblNewLabel_2_5_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_5_1.setBounds(10, 36, 60, 25);
		pnChiTietGH.add(lblNewLabel_2_5_1);

		JLabel lblNewLabel_2_5_2 = new JLabel("Số lượng:");
		lblNewLabel_2_5_2.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_5_2.setBounds(10, 63, 60, 25);
		pnChiTietGH.add(lblNewLabel_2_5_2);

		JLabel lblNewLabel_2_5_2_1 = new JLabel("Tổng tiền:");
		lblNewLabel_2_5_2_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_5_2_1.setBounds(10, 93, 71, 25);
		pnChiTietGH.add(lblNewLabel_2_5_2_1);

		JLabel lblGioHang_TenSach = new JLabel("");
		lblGioHang_TenSach.setBackground(SystemColor.info);
		lblGioHang_TenSach.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblGioHang_TenSach.setBounds(80, 10, 202, 25);
		pnChiTietGH.add(lblGioHang_TenSach);

		JLabel lblGioHang_DonGia = new JLabel("");
		lblGioHang_DonGia.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblGioHang_DonGia.setBackground(SystemColor.info);
		lblGioHang_DonGia.setBounds(80, 39, 202, 25);
		pnChiTietGH.add(lblGioHang_DonGia);

		JLabel lblGioHang_TongTien = new JLabel("");
		lblGioHang_TongTien.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblGioHang_TongTien.setBackground(SystemColor.info);
		lblGioHang_TongTien.setBounds(91, 93, 202, 25);
		pnChiTietGH.add(lblGioHang_TongTien);

		JButton btnXoaKhoiGioHang = new JButton("Xóa khỏi giỏ hàng");
		btnXoaKhoiGioHang.setVerticalAlignment(SwingConstants.TOP);
		btnXoaKhoiGioHang.setForeground(Color.BLACK);
		btnXoaKhoiGioHang.setFont(new Font("Palatino Linotype", Font.BOLD, 14));
		btnXoaKhoiGioHang.setBackground(SystemColor.scrollbar);
		btnXoaKhoiGioHang.setBounds(59, 128, 190, 28);
		pnChiTietGH.add(btnXoaKhoiGioHang);
		btnXoaKhoiGioHang.setVisible(false);

		JLabel lblGioHang_MaSach = new JLabel("");
		lblGioHang_MaSach.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblGioHang_MaSach.setBackground(SystemColor.info);
		lblGioHang_MaSach.setBounds(20, 131, 0, 25);
		pnChiTietGH.add(lblGioHang_MaSach);

		JSpinner spnGioHang_SoLuong = new JSpinner();
		spnGioHang_SoLuong
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spnGioHang_SoLuong.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		spnGioHang_SoLuong.setBounds(80, 63, 41, 24);
		pnChiTietGH.add(spnGioHang_SoLuong);

		JButton btnThayDoiSoLuongGioHang = new JButton("Thay đổi");
		btnThayDoiSoLuongGioHang.setVerticalAlignment(SwingConstants.TOP);
		btnThayDoiSoLuongGioHang.setForeground(Color.BLACK);
		btnThayDoiSoLuongGioHang.setFont(new Font("Palatino Linotype", Font.BOLD, 14));
		btnThayDoiSoLuongGioHang.setBackground(SystemColor.scrollbar);
		btnThayDoiSoLuongGioHang.setBounds(143, 62, 117, 22);
		pnChiTietGH.add(btnThayDoiSoLuongGioHang);
		btnThayDoiSoLuongGioHang.setVisible(false);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(668, 609, 7, 19);
		panel.add(textPane);

		JButton btnTiepTuc = new JButton("Tiếp tục");
		btnTiepTuc.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		btnTiepTuc.setBounds(656, 609, 292, 38);
		panel.add(btnTiepTuc);

		JPanel pnThemSachVaoGio = new JPanel();
		pnThemSachVaoGio.setBackground(SystemColor.text);
		pnThemSachVaoGio.setBounds(644, 9, 334, 390);
		panel.add(pnThemSachVaoGio);
		pnThemSachVaoGio.setLayout(null);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(161, 5, 1, 1);
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1.setBackground(SystemColor.controlHighlight);
		pnThemSachVaoGio.add(panel_2_1);

		JLabel lblNewLabel_2_5 = new JLabel("Tên sách:");
		lblNewLabel_2_5.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_5.setBounds(35, 143, 60, 25);
		panel_2_1.add(lblNewLabel_2_5);

		JLabel lblNewLabel_2_1_1 = new JLabel("Thể loại:");
		lblNewLabel_2_1_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_1_1.setBounds(35, 178, 60, 25);
		panel_2_1.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_2_1 = new JLabel("Tác giả:");
		lblNewLabel_2_2_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_2_1.setBounds(44, 213, 51, 25);
		panel_2_1.add(lblNewLabel_2_2_1);

		JLabel lblNewLabel_2_3_2 = new JLabel("Nhà xuất bản:");
		lblNewLabel_2_3_2.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_3_2.setBounds(10, 248, 85, 25);
		panel_2_1.add(lblNewLabel_2_3_2);

		JLabel lblNewLabel_2_3_1_1 = new JLabel("Giá bán:");
		lblNewLabel_2_3_1_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_3_1_1.setBounds(35, 283, 60, 25);
		panel_2_1.add(lblNewLabel_2_3_1_1);

		JPanel pnHinhAnhSanPham_1 = new JPanel();
		pnHinhAnhSanPham_1.setBounds(27, 16, 262, 117);
		panel_2_1.add(pnHinhAnhSanPham_1);

		JLabel lblChiTietTenSach_1 = new JLabel("");
		lblChiTietTenSach_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblChiTietTenSach_1.setBackground(SystemColor.info);
		lblChiTietTenSach_1.setBounds(105, 139, 215, 25);
		panel_2_1.add(lblChiTietTenSach_1);

		JLabel lblChiTietGiaBan_1 = new JLabel("");
		lblChiTietGiaBan_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblChiTietGiaBan_1.setBackground(SystemColor.info);
		lblChiTietGiaBan_1.setBounds(105, 283, 202, 25);
		panel_2_1.add(lblChiTietGiaBan_1);

		JLabel lblChiTietTheLoai_1 = new JLabel("");
		lblChiTietTheLoai_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblChiTietTheLoai_1.setBackground(SystemColor.info);
		lblChiTietTheLoai_1.setBounds(105, 178, 215, 25);
		panel_2_1.add(lblChiTietTheLoai_1);

		JLabel lblChiTietTacGia_1 = new JLabel("");
		lblChiTietTacGia_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblChiTietTacGia_1.setBackground(SystemColor.info);
		lblChiTietTacGia_1.setBounds(101, 213, 215, 25);
		panel_2_1.add(lblChiTietTacGia_1);

		JLabel lblChiTietNXB_1 = new JLabel("");
		lblChiTietNXB_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblChiTietNXB_1.setBackground(SystemColor.info);
		lblChiTietNXB_1.setBounds(101, 248, 215, 25);
		panel_2_1.add(lblChiTietNXB_1);

		JPanel pnChiTietSach = new JPanel();
		pnChiTietSach.setLayout(null);
		pnChiTietSach.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnChiTietSach.setBackground(SystemColor.controlHighlight);
		pnChiTietSach.setBounds(10, 27, 314, 318);
		pnThemSachVaoGio.add(pnChiTietSach);

		JLabel lblNewLabel_2_6 = new JLabel("Tên sách:");
		lblNewLabel_2_6.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_6.setBounds(35, 143, 60, 25);
		pnChiTietSach.add(lblNewLabel_2_6);

		JLabel lblNewLabel_2_1_2 = new JLabel("Thể loại:");
		lblNewLabel_2_1_2.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_1_2.setBounds(35, 178, 60, 25);
		pnChiTietSach.add(lblNewLabel_2_1_2);

		JLabel lblNewLabel_2_2_2 = new JLabel("Tác giả:");
		lblNewLabel_2_2_2.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_2_2.setBounds(44, 213, 51, 25);
		pnChiTietSach.add(lblNewLabel_2_2_2);

		JLabel lblNewLabel_2_3_3 = new JLabel("Nhà xuất bản:");
		lblNewLabel_2_3_3.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_3_3.setBounds(10, 248, 85, 25);
		pnChiTietSach.add(lblNewLabel_2_3_3);

		JLabel lblNewLabel_2_3_1_2 = new JLabel("Giá bán:");
		lblNewLabel_2_3_1_2.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_3_1_2.setBounds(35, 283, 60, 25);
		pnChiTietSach.add(lblNewLabel_2_3_1_2);

		JPanel pnHinhAnhSanPham = new JPanel();
		pnHinhAnhSanPham.setBounds(27, 16, 262, 117);
		pnChiTietSach.add(pnHinhAnhSanPham);
		pnHinhAnhSanPham.setLayout(null);
		JLabel lblHinhAnh = new JLabel("");
		lblHinhAnh.setBounds(0, 0, 262, 117);
		pnHinhAnhSanPham.add(lblHinhAnh);

		JLabel lblChiTietTenSach = new JLabel("");
		lblChiTietTenSach.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblChiTietTenSach.setBackground(SystemColor.info);
		lblChiTietTenSach.setBounds(101, 143, 215, 25);
		pnChiTietSach.add(lblChiTietTenSach);

		JLabel lblChiTietGiaBan = new JLabel("");
		lblChiTietGiaBan.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblChiTietGiaBan.setBackground(SystemColor.info);
		lblChiTietGiaBan.setBounds(105, 283, 202, 25);
		pnChiTietSach.add(lblChiTietGiaBan);

		JLabel lblChiTietTheLoai = new JLabel("");
		lblChiTietTheLoai.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblChiTietTheLoai.setBackground(SystemColor.info);
		lblChiTietTheLoai.setBounds(105, 178, 215, 25);
		pnChiTietSach.add(lblChiTietTheLoai);

		JLabel lblChiTietTacGia = new JLabel("");
		lblChiTietTacGia.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblChiTietTacGia.setBackground(SystemColor.info);
		lblChiTietTacGia.setBounds(101, 213, 215, 25);
		pnChiTietSach.add(lblChiTietTacGia);

		JLabel lblChiTietNXB = new JLabel("");
		lblChiTietNXB.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblChiTietNXB.setBackground(SystemColor.info);
		lblChiTietNXB.setBounds(101, 248, 215, 25);
		pnChiTietSach.add(lblChiTietNXB);

		JLabel lblChiTietMaSach = new JLabel("");
		lblChiTietMaSach.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblChiTietMaSach.setBackground(SystemColor.info);
		lblChiTietMaSach.setBounds(304, 293, 0, 25);
		pnChiTietSach.add(lblChiTietMaSach);

		JLabel lbTitleChiTiet_1 = new JLabel("Chi tiết sách");
		lbTitleChiTiet_1.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lbTitleChiTiet_1.setBounds(114, 5, 102, 22);
		pnThemSachVaoGio.add(lbTitleChiTiet_1);

		JLabel lblNewLabel_2_4_1 = new JLabel("Chọn số lượng");
		lblNewLabel_2_4_1.setFont(new Font("Palatino Linotype", Font.BOLD, 14));
		lblNewLabel_2_4_1.setBounds(10, 355, 102, 25);
		pnThemSachVaoGio.add(lblNewLabel_2_4_1);

		JSpinner spnSoLuongThemVaoGio = new JSpinner();
		spnSoLuongThemVaoGio
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spnSoLuongThemVaoGio.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		spnSoLuongThemVaoGio.setBounds(114, 355, 41, 24);
		pnThemSachVaoGio.add(spnSoLuongThemVaoGio);

		JButton btnThemVaoGioHang = new JButton("Thêm vào giỏ");
		btnThemVaoGioHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThemVaoGioHang.setVerticalAlignment(SwingConstants.TOP);
		btnThemVaoGioHang.setForeground(Color.BLACK);
		btnThemVaoGioHang.setFont(new Font("Palatino Linotype", Font.BOLD, 14));
		btnThemVaoGioHang.setBackground(SystemColor.scrollbar);
		btnThemVaoGioHang.setBounds(188, 355, 125, 25);
		pnThemSachVaoGio.add(btnThemVaoGioHang);
		btnThemVaoGioHang.setVisible(false);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.textHighlightText);
		panel_2.setBounds(1, 330, 633, 327);
		panel.add(panel_2);
		panel_2.setLayout(null);

		tblGioHang = new JTable();
		tblGioHang.setBounds(10, 27, 613, 251);
		panel_2.add(tblGioHang);
		tblGioHang.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 s\u00E1ch",
				"T\u00EAn s\u00E1ch", "Gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "T\u1ED5ng ti\u1EC1n" }));
		tblGioHang.getColumnModel().getColumn(0).setPreferredWidth(44);
		tblGioHang.getColumnModel().getColumn(1).setPreferredWidth(295);
		tblGioHang.getColumnModel().getColumn(2).setPreferredWidth(66);
		tblGioHang.getColumnModel().getColumn(3).setPreferredWidth(48);
		tblGioHang.getColumnModel().getColumn(4).setPreferredWidth(106);
		tblGioHang.setRowHeight(25);
		tblGioHang.setSurrendersFocusOnKeystroke(true);
		tblGioHang.setForeground(Color.BLACK);
		tblGioHang.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		tblGioHang.setFillsViewportHeight(true);
		tblGioHang.setColumnSelectionAllowed(true);
		tblGioHang.setCellSelectionEnabled(true);
		tblGioHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblGioHang.setBackground(SystemColor.controlHighlight);

		JLabel lblGiHng = new JLabel("Giỏ hàng");
		lblGiHng.setBounds(222, 0, 81, 31);
		panel_2.add(lblGiHng);
		lblGiHng.setFont(new Font("Times New Roman", Font.BOLD, 19));

		JLabel lblTngCng = new JLabel("Tổng cộng:");
		lblTngCng.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTngCng.setBounds(57, 288, 91, 31);
		panel_2.add(lblTngCng);

		JLabel lblTongThanhToan = new JLabel("");
		lblTongThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTongThanhToan.setBounds(154, 288, 180, 31);
		panel_2.add(lblTongThanhToan);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(154, 114, 2, 2);
		panel_2.add(scrollPane_1);

		JButton btnXoaGioHang = new JButton("Xóa giỏ hàng");
		btnXoaGioHang.setFont(new Font("Palatino Linotype", Font.BOLD, 14));
		btnXoaGioHang.setBounds(408, 288, 171, 31);
		panel_2.add(btnXoaGioHang);

//------Hóa đơn---------------------------------------------------------------------------------
		JPanel panelHoaDon = new JPanel();
		tabbedPane.addTab("Xem hóa đơn", null, panelHoaDon, null);
		panelHoaDon.setLayout(null);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(10, 21, 500, 39);
		panelHoaDon.add(panel_1_1);

		JLabel lblNewLabel = new JLabel("Danh sách hóa đơn");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(158, 0, 150, 39);
		panel_1_1.add(lblNewLabel);

		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 69, 553, 290);
		panelHoaDon.add(panel2);
		panel2.setLayout(null);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(0, 0, 553, 270);
		panel2.add(scrollPane1);

		tblListHoaDon = new JTable();
		tblListHoaDon.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n", "Ng\u00E0y", "M\u00E3 NV", "M\u00E3 KH",
						"T\u1ED5ng h\u00F3a \u0111\u01A1n", "T\u1ED5ng kh\u00E1ch tr\u1EA3" }));
		tblListHoaDon.getColumnModel().getColumn(4).setPreferredWidth(131);
		tblListHoaDon.getColumnModel().getColumn(5).setPreferredWidth(137);
		tblListHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 12));
		scrollPane1.setViewportView(tblListHoaDon);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(614, 31, 267, 39);
		panelHoaDon.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Chi tiết hóa đơn");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1.setBounds(54, 0, 133, 39);
		panel_3.add(lblNewLabel_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(573, 69, 365, 285);
		panelHoaDon.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Mã hóa đơn: ");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(20, 0, 84, 32);
		panel_4.add(lblNewLabel_2);

		JLabel lblChiTiet_MaHD = new JLabel("");
		lblChiTiet_MaHD.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblChiTiet_MaHD.setBounds(101, 0, 200, 32);
		panel_4.add(lblChiTiet_MaHD);

		JScrollPane scrollPane_12 = new JScrollPane();
		scrollPane_12.setBounds(10, 29, 345, 240);
		panel_4.add(scrollPane_12);

		tblListChiTietHoaDon = new JTable();
		tblListChiTietHoaDon.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 chi ti\u1EBFt",
				"M\u00E3 s\u00E1ch", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1", "T\u1ED5ng ti\u1EC1n" }));
		tblListChiTietHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 12));
		scrollPane_12.setViewportView(tblListChiTietHoaDon);
//--------------------------------------------------------------
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(20, 352, 918, 323);
		panelHoaDon.add(panel_5);
		panel_5.setLayout(null);

		JPanel pnXemHDTheoNgay = new JPanel();
		pnXemHDTheoNgay.setBackground(SystemColor.controlHighlight);
		pnXemHDTheoNgay.setBounds(10, 177, 363, 128);
		panel_5.add(pnXemHDTheoNgay);
		pnXemHDTheoNgay.setLayout(null);

		JLabel lblNewLabel_3_1_1_2 = new JLabel("Từ ngày: ");
		lblNewLabel_3_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_1_1_2.setBounds(47, 10, 72, 25);
		pnXemHDTheoNgay.add(lblNewLabel_3_1_1_2);
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(117, 10, 110, 25);
		pnXemHDTheoNgay.add(panel_6);
		panel_6.setLayout(null);
		dctuNgay = new JDateChooser();
		dctuNgay.setBounds(0, 0, 110, 25);
		panel_6.add(dctuNgay);

		JPanel panel_6_1 = new JPanel();
		panel_6_1.setLayout(null);
		panel_6_1.setBounds(117, 45, 110, 25);
		pnXemHDTheoNgay.add(panel_6_1);

		dcdenNgay = new JDateChooser();
		dcdenNgay.setBounds(0, 0, 110, 25);
		panel_6_1.add(dcdenNgay);

		JButton btnXemTheoNgay = new JButton("Xem hóa đơn theo ngày");
		btnXemTheoNgay.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnXemTheoNgay.setBounds(47, 83, 257, 35);
		pnXemHDTheoNgay.add(btnXemTheoNgay);

		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Đến ngày:");
		lblNewLabel_3_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_1_1_1_1.setBounds(40, 45, 80, 25);
		pnXemHDTheoNgay.add(lblNewLabel_3_1_1_1_1);

		JPanel pnXemHDTheoMaKH = new JPanel();
		pnXemHDTheoMaKH.setBackground(SystemColor.controlHighlight);
		pnXemHDTheoMaKH.setBounds(10, 20, 363, 128);
		panel_5.add(pnXemHDTheoMaKH);
		pnXemHDTheoMaKH.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Nhập vào mã khách hàng: ");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(95, 10, 188, 25);
		pnXemHDTheoMaKH.add(lblNewLabel_3);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtMaKH.setBounds(95, 48, 157, 25);
		pnXemHDTheoMaKH.add(txtMaKH);
		txtMaKH.setColumns(10);

		JButton btnXemTheoMaKH = new JButton("Xem hóa đơn theo mã khách hàng");
		btnXemTheoMaKH.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnXemTheoMaKH.setBounds(45, 83, 261, 35);
		pnXemHDTheoMaKH.add(btnXemTheoMaKH);

		JPanel pnXemHDTheoMaNV = new JPanel();
		pnXemHDTheoMaNV.setBackground(SystemColor.controlHighlight);
		pnXemHDTheoMaNV.setLayout(null);
		pnXemHDTheoMaNV.setBounds(411, 20, 363, 128);
		panel_5.add(pnXemHDTheoMaNV);

		JLabel lblNewLabel_3_1 = new JLabel("Nhập vào mã nhân viên: ");
		lblNewLabel_3_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(86, 13, 188, 25);
		pnXemHDTheoMaNV.add(lblNewLabel_3_1);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(86, 48, 157, 25);
		pnXemHDTheoMaNV.add(txtMaNV);

		JButton btnXemTheoMaNV = new JButton("Xem hóa đơn theo mã nhân viên");
		btnXemTheoMaNV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnXemTheoMaNV.setBounds(50, 83, 261, 35);
		pnXemHDTheoMaNV.add(btnXemTheoMaNV);

		JPanel pnXemHDTheoTongHD = new JPanel();
		pnXemHDTheoTongHD.setLayout(null);
		pnXemHDTheoTongHD.setBackground(SystemColor.controlHighlight);
		pnXemHDTheoTongHD.setBounds(411, 177, 363, 128);
		panel_5.add(pnXemHDTheoTongHD);

		JLabel lblNewLabel_3_1_1_2_1 = new JLabel("Tổng hóa đơn từ: ");
		lblNewLabel_3_1_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_1_1_2_1.setBounds(22, 10, 113, 25);
		pnXemHDTheoTongHD.add(lblNewLabel_3_1_1_2_1);

		JButton btnXemTheoTongHD = new JButton("Xem hóa đơn theo tổng thanh toán");
		btnXemTheoTongHD.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnXemTheoTongHD.setBounds(57, 80, 257, 35);
		pnXemHDTheoTongHD.add(btnXemTheoTongHD);

		JLabel lblNewLabel_3_1_1_1_1_1 = new JLabel("Đến: ");
		lblNewLabel_3_1_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_1_1_1_1_1.setBounds(90, 45, 45, 25);
		pnXemHDTheoTongHD.add(lblNewLabel_3_1_1_1_1_1);

		txtTuTongTien = new JTextField();
		txtTuTongTien.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtTuTongTien.setColumns(10);
		txtTuTongTien.setBounds(145, 10, 157, 25);
		pnXemHDTheoTongHD.add(txtTuTongTien);

		txtDenTongTien = new JTextField();
		txtDenTongTien.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtDenTongTien.setColumns(10);
		txtDenTongTien.setBounds(145, 45, 157, 25);
		pnXemHDTheoTongHD.add(txtDenTongTien);

		JButton btnBanDau = new JButton("Ban đầu");
		btnBanDau.setBackground(new Color(0, 139, 139));
		btnBanDau.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnBanDau.setBounds(783, 127, 125, 46);
		panel_5.add(btnBanDau);

//------------------------------------------------------------------------------
		PhieuXuatBUS phieuxuatBUS = new PhieuXuatBUS();
		ArrayList<PhieuXuatDTO> dsHoaDon = phieuxuatBUS.getListPhieuXuat();
		docListHoaDon(tblListHoaDon, dsHoaDon);
		tblListHoaDon.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				xuLyClickDsHoaDon(tblListHoaDon, lblChiTiet_MaHD, tblListChiTietHoaDon);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		btnXemTheoMaKH.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (xoaKhoangTrangThua(txtMaKH.getText()).equals("")) {
					docListHoaDon(tblListHoaDon, dsHoaDon);
				} else {
					int maKH = Integer.parseInt(xoaKhoangTrangThua(txtMaKH.getText()));
					ArrayList<PhieuXuatDTO> dsHoaDonTheoMaKH = phieuxuatBUS.getListPhieuXuatTheoMaKH(maKH);
					docListHoaDon(tblListHoaDon, dsHoaDonTheoMaKH);
					txtTuTongTien.setText("");
					txtDenTongTien.setText("");
					txtMaNV.setText("");
				}

			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		btnXemTheoMaNV.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (xoaKhoangTrangThua(txtMaNV.getText()).equals("")) {
					docListHoaDon(tblListHoaDon, dsHoaDon);
				} else {
					int maNV = Integer.parseInt(xoaKhoangTrangThua(txtMaNV.getText()));
					ArrayList<PhieuXuatDTO> dsHoaDonTheoMaNV = phieuxuatBUS.getListPhieuXuatTheoMaNV(maNV);
					docListHoaDon(tblListHoaDon, dsHoaDonTheoMaNV);
					txtTuTongTien.setText("");
					txtDenTongTien.setText("");
					txtMaKH.setText("");
				}

			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		btnXemTheoNgay.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				Date tuNgay = new java.sql.Date(dctuNgay.getDate().getTime());
				Date denNgay = new java.sql.Date(dcdenNgay.getDate().getTime());
				if (tuNgay.compareTo(denNgay) > 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày mốc nhỏ hơn ngày đích");
				} else {
					ArrayList<PhieuXuatDTO> dsHoaDonTheoNgay = phieuxuatBUS.getListPhieuXuatTheoNgay(tuNgay, denNgay);
					docListHoaDon(tblListHoaDon, dsHoaDonTheoNgay);

				}
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		btnXemTheoTongHD.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				String strTu = xoaKhoangTrangThua(txtTuTongTien.getText());
				String strDen = xoaKhoangTrangThua(txtDenTongTien.getText());
				if (!checkSoThuc(strTu) || !checkSoThuc(strDen)) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số thực");
				} else if (checkSoThuc(strTu) && checkSoThuc(strDen)
						&& stringSangDouble(strTu) > stringSangDouble(strDen)) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mốc nhỏ hơn đích");
				} else if (checkSoThuc(strTu) && checkSoThuc(strDen)
						&& stringSangDouble(strTu) <= stringSangDouble(strDen)) {
					Double tu = stringSangDouble(strTu);
					Double den = stringSangDouble(strDen);
					ArrayList<PhieuXuatDTO> dsHoaDonTheoTongHD = phieuxuatBUS.getListPhieuXuatTheoTongTien(tu, den);
					docListHoaDon(tblListHoaDon, dsHoaDonTheoTongHD);
					txtMaKH.setText("");
					txtMaNV.setText("");
				}
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		btnBanDau.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				docListHoaDon(tblListHoaDon, dsHoaDon);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

//------------------------------------------------------------------------------	

		tblSanPhamBan.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				xuLyClickDsSach(tblSanPhamBan, lblChiTietTenSach, lblHinhAnh, lblChiTietTheLoai, lblChiTietTacGia,
						lblChiTietNXB, lblChiTietGiaBan, lblChiTietMaSach, btnThemVaoGioHang);
				spnSoLuongThemVaoGio.setValue(1);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		btnThemVaoGioHang.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				xuLyClickThemGioHang(tblGioHang, lblChiTietMaSach, spnSoLuongThemVaoGio, gioHang, lblTongThanhToan,
						lblGioHang_MaSach, lblGioHang_TongTien);
				giamSoLuongKhoTamThoi(tblSanPhamBan, Integer.parseInt(lblChiTietMaSach.getText()),
						(int) spnSoLuongThemVaoGio.getValue());
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		tblGioHang.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				xuLyClickDsGioHang(tblGioHang, lblGioHang_MaSach, lblGioHang_TenSach, lblGioHang_DonGia,
						spnGioHang_SoLuong, lblGioHang_TongTien, btnXoaKhoiGioHang, btnThayDoiSoLuongGioHang);

			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		btnXoaKhoiGioHang.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				xuLyClickXoaChiTietGioHang(tblGioHang, lblGioHang_MaSach, gioHang, lblTongThanhToan,
						lblGioHang_TongTien);
				tangSoLuongKhoTamThoi(tblSanPhamBan, Integer.parseInt(lblGioHang_MaSach.getText()),
						(int) spnGioHang_SoLuong.getValue());
				lblGioHang_TenSach.setText("");
				lblGioHang_DonGia.setText("");
				spnGioHang_SoLuong.setValue(1);
				lblGioHang_TongTien.setText("");
				btnThayDoiSoLuongGioHang.setVisible(false);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		btnThayDoiSoLuongGioHang.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {

				thayDoiSoLuongGioHang(gioHang, spnGioHang_SoLuong, lblGioHang_MaSach, lblGioHang_TongTien);
				hienThiBangGioHang(tblGioHang, gioHang);
				capNhatTongHoaDon(gioHang, lblTongThanhToan);
				tangSoLuongKhoTamThoi(tblSanPhamBan, Integer.parseInt(lblGioHang_MaSach.getText()),
						soLuongGioHangHienTai);
				giamSoLuongKhoTamThoi(tblSanPhamBan, Integer.parseInt(lblGioHang_MaSach.getText()),
						(int) spnGioHang_SoLuong.getValue());

			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		btnTiepTuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChonKhachHangNhanVien chonKH_VH = new ChonKhachHangNhanVien();
				chonKH_VH.setLocationRelativeTo(BanHangGUI.this);
				if (gioHang.size() == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng thêm sản phẩm vào giỏ hàng");
				} else {
					chonKH_VH.setVisible(true);
				}
			}
		});

		btnXoaGioHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < gioHang.size(); i++) {
					for (int j = 0; j < tblSanPhamBan.getRowCount(); j++) {
						int maSach = (int) tblSanPhamBan.getValueAt(j, 0);
						if (gioHang.get(i).getMaSach() == maSach) {
							int soLuongKho = (int) tblSanPhamBan.getValueAt(j, 3) + gioHang.get(i).getSoLuong();
							tblSanPhamBan.setValueAt(soLuongKho, j, 3);
						}
					}
				}
				resetGioHang(lblTongThanhToan, lblGioHang_MaSach, lblGioHang_TenSach, lblGioHang_DonGia,
						spnGioHang_SoLuong, lblGioHang_TongTien);

			}
		});

		// --------------------------------------

	}

}
