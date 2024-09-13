package QuanLyCuaHangSach.GUI;

import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import QuanLyCuaHangSach.BUS.ChiTietPhieuXuatBUS;
import QuanLyCuaHangSach.BUS.PhieuXuatBUS;
import QuanLyCuaHangSach.BUS.Sach2BUS;
import QuanLyCuaHangSach.BUS.SachBUS;
import QuanLyCuaHangSach.DTO.ChiTietPhieuXuatDTO;
import QuanLyCuaHangSach.DTO.PhieuXuatDTO;
import QuanLyCuaHangSach.DTO.Sach2DTO;
import QuanLyCuaHangSach.GUI.HienThiKhuyenMaiGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;

public class TrangBanHangGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblGioHang;
	private JTable table_1;
	public static int tongThanhToanBanDau;
	public static Date ngayTaoHoaDon;
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

	private String formatGiaTien(int giaTien) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###.##₫");
		return decimalFormat.format(giaTien);
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

	private void xuLyClickDsGioHang(JTable tblDsGioHang, JLabel lbMaSach, JLabel lbTenSach, JLabel lbGiaBan,
			JSpinner spnSoLuong, JLabel lbTongTien, JButton btnXoaKhoiGioHang) {
		int row = tblDsGioHang.getSelectedRow();
		if (row > -1) {
			btnXoaKhoiGioHang.setVisible(true);
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
		}
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
					TrangBanHangGUI frame = new TrangBanHangGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public TrangBanHangGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1045, 758);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBounds(29, 54, 969, 657);
		contentPane.add(panel);
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

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(656, 433, 292, 166);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblTenSachGioHang = new JLabel("Tên sách:");
		lblTenSachGioHang.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblTenSachGioHang.setBounds(10, 10, 60, 25);
		panel_3.add(lblTenSachGioHang);

		JLabel lblNewLabel_2_5_1 = new JLabel("Đơn giá:");
		lblNewLabel_2_5_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_5_1.setBounds(10, 36, 60, 25);
		panel_3.add(lblNewLabel_2_5_1);

		JLabel lblNewLabel_2_5_2 = new JLabel("Số lượng:");
		lblNewLabel_2_5_2.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_5_2.setBounds(10, 63, 60, 25);
		panel_3.add(lblNewLabel_2_5_2);

		JLabel lblNewLabel_2_5_2_1 = new JLabel("Tổng tiền:");
		lblNewLabel_2_5_2_1.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblNewLabel_2_5_2_1.setBounds(10, 93, 71, 25);
		panel_3.add(lblNewLabel_2_5_2_1);

		JLabel lblGioHang_TenSach = new JLabel("");
		lblGioHang_TenSach.setBackground(SystemColor.info);
		lblGioHang_TenSach.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblGioHang_TenSach.setBounds(80, 10, 202, 25);
		panel_3.add(lblGioHang_TenSach);

		JLabel lblGioHang_DonGia = new JLabel("");
		lblGioHang_DonGia.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblGioHang_DonGia.setBackground(SystemColor.info);
		lblGioHang_DonGia.setBounds(80, 39, 202, 25);
		panel_3.add(lblGioHang_DonGia);

		JLabel lblGioHang_TongTien = new JLabel("");
		lblGioHang_TongTien.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblGioHang_TongTien.setBackground(SystemColor.info);
		lblGioHang_TongTien.setBounds(91, 93, 202, 25);
		panel_3.add(lblGioHang_TongTien);

		JButton btnXoaKhoiGioHang = new JButton("Xóa khỏi giỏ hàng");
		btnXoaKhoiGioHang.setVerticalAlignment(SwingConstants.TOP);
		btnXoaKhoiGioHang.setForeground(Color.BLACK);
		btnXoaKhoiGioHang.setFont(new Font("Palatino Linotype", Font.BOLD, 14));
		btnXoaKhoiGioHang.setBackground(SystemColor.scrollbar);
		btnXoaKhoiGioHang.setBounds(59, 128, 190, 28);
		panel_3.add(btnXoaKhoiGioHang);
		btnXoaKhoiGioHang.setVisible(false);

		JLabel lblGioHang_MaSach = new JLabel("");
		lblGioHang_MaSach.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		lblGioHang_MaSach.setBackground(SystemColor.info);
		lblGioHang_MaSach.setBounds(20, 131, 0, 25);
		panel_3.add(lblGioHang_MaSach);

		JSpinner spnGioHang_SoLuong = new JSpinner();
		spnGioHang_SoLuong
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spnGioHang_SoLuong.setFont(new Font("Palatino Linotype", Font.BOLD, 13));
		spnGioHang_SoLuong.setBounds(80, 63, 41, 24);
		panel_3.add(spnGioHang_SoLuong);

		JButton btnThayDoiSoLuongGioHang = new JButton("Thay đổi");
		btnThayDoiSoLuongGioHang.setVerticalAlignment(SwingConstants.TOP);
		btnThayDoiSoLuongGioHang.setForeground(Color.BLACK);
		btnThayDoiSoLuongGioHang.setFont(new Font("Palatino Linotype", Font.BOLD, 14));
		btnThayDoiSoLuongGioHang.setBackground(SystemColor.scrollbar);
		btnThayDoiSoLuongGioHang.setBounds(143, 62, 117, 22);
		panel_3.add(btnThayDoiSoLuongGioHang);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(668, 609, 7, 19);
		panel.add(textPane);

		JButton btnTiepTuc = new JButton("Tiếp tục");
		btnTiepTuc.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		btnTiepTuc.setBounds(656, 609, 292, 38);
		panel.add(btnTiepTuc);

		JPanel pnThemSachVaoGio = new JPanel();
		pnThemSachVaoGio.setBackground(SystemColor.text);
		pnThemSachVaoGio.setBounds(635, 10, 334, 390);
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

		JButton btnTaoHoaDonBan = new JButton("Tạo hóa đơn bán hàng");
		btnTaoHoaDonBan.setForeground(new Color(0, 0, 0));
		btnTaoHoaDonBan.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnTaoHoaDonBan.setBackground(new Color(64, 128, 128));
		btnTaoHoaDonBan.setBounds(29, 8, 182, 36);
		contentPane.add(btnTaoHoaDonBan);

		JButton btnXemDanhSachHoaDon = new JButton("Xem danh sách hóa đơn");
		btnXemDanhSachHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnXemDanhSachHoaDon.setBackground(new Color(64, 128, 128));
		btnXemDanhSachHoaDon.setBounds(221, 8, 192, 36);
		contentPane.add(btnXemDanhSachHoaDon);

		// --------------------------------------

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
						spnGioHang_SoLuong, lblGioHang_TongTien, btnXoaKhoiGioHang);

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
				lblGioHang_TenSach.setText("");
				lblGioHang_DonGia.setText("");
				spnGioHang_SoLuong.setValue(1);
				lblGioHang_TongTien.setText("");
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
				HienThiKhuyenMaiGUI khuyenmai = new HienThiKhuyenMaiGUI();
				khuyenmai.setLocationRelativeTo(TrangBanHangGUI.this);
				if (gioHang.size() == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng thêm sản phẩm vào giỏ hàng");
				} else {
					khuyenmai.setVisible(true);
				}
			}
		});
		
		btnXoaGioHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetGioHang(lblTongThanhToan, lblGioHang_MaSach, lblGioHang_TenSach, lblGioHang_DonGia, spnGioHang_SoLuong, lblGioHang_TongTien);
			}
		});
		
		// --------------------------------------
	}
}
