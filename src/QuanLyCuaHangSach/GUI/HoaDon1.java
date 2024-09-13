package QuanLyCuaHangSach.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import QuanLyCuaHangSach.BUS.ChiTietPhieuXuatBUS;
import QuanLyCuaHangSach.BUS.PhieuXuatBUS;
import QuanLyCuaHangSach.BUS.SachBUS;
import QuanLyCuaHangSach.DTO.ChiTietPhieuXuatDTO;
import QuanLyCuaHangSach.DTO.PhieuXuatDTO;
import QuanLyCuaHangSach.GUI.TrangBanHangGUI.NonEditableTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.SystemColor;
import java.awt.Color;

public class HoaDon1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblListHoaDon;
	private JTable tblListChiTietHoaDon;
	private JTextField txtMaKH;
	private JTextField txtMaNV;
	private JTextField txtTuTongTien;
	private JTextField txtDenTongTien;

	public class NonEditableTableModel extends DefaultTableModel {
		public NonEditableTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	private String formatGiaTien(int giaTien) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###.##₫");
		return decimalFormat.format(giaTien);
	}

	private void docListHoaDon(JTable tblListHoaDon) {
		PhieuXuatBUS phieuxuatBUS = new PhieuXuatBUS();
		ArrayList<PhieuXuatDTO> dsPhieuXuat = phieuxuatBUS.getListPhieuXuat();
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

	private void xemHoaDonTheoMaNV(JTable tblListtHoaDon, String maNVinput) {
		DefaultTableModel model = (DefaultTableModel) tblListtHoaDon.getModel();
		boolean[] keepRows = new boolean[model.getRowCount()];
		for (int i = 0; i < model.getRowCount(); i++) {
			String maNV = model.getValueAt(i, 2).toString();
			if (maNV.equals(maNVinput))
				keepRows[i] = true;
			else
				keepRows[i] = false;
		}
		for (int i = model.getRowCount() - 1; i >= 0; i--)
			if (!keepRows[i])
				model.removeRow(i);
	}

	private void xemHoaDonTheoMaKH(JTable tblListtHoaDon, String maKHinput) {
		DefaultTableModel model = (DefaultTableModel) tblListtHoaDon.getModel();
		boolean[] keepRows = new boolean[model.getRowCount()];
		for (int i = 0; i < model.getRowCount(); i++) {
			String maKH = model.getValueAt(i, 3).toString();
			if (maKH.equals(maKHinput))
				keepRows[i] = true;
			else
				keepRows[i] = false;
		}
		for (int i = model.getRowCount() - 1; i >= 0; i--)
			if (!keepRows[i])
				model.removeRow(i);
	}

	private void xemHoaDonTheoTongHD(JTable tblListtHoaDon, Double tu, Double den) {
		DefaultTableModel model = (DefaultTableModel) tblListtHoaDon.getModel();
		boolean[] keepRows = new boolean[model.getRowCount()];
		for (int i = 0; i < model.getRowCount(); i++) {
			Double tongHD = laySoTuChuoi(model.getValueAt(i, 5).toString());
			if (tu <= tongHD && den >= tongHD)
				keepRows[i] = true;
			else
				keepRows[i] = false;
		}
		for (int i = model.getRowCount() - 1; i >= 0; i--)
			if (!keepRows[i])
				model.removeRow(i);
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HoaDonGUI frame = new HoaDonGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HoaDon1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 982, 742);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 948, 685);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 31, 500, 39);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Danh sách hóa đơn");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(158, 0, 150, 39);
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 69, 553, 236);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 553, 236);
		panel_2.add(scrollPane);

		tblListHoaDon = new JTable();
		tblListHoaDon.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n", "Ng\u00E0y", "M\u00E3 NV", "M\u00E3 KH",
						"T\u1ED5ng h\u00F3a \u0111\u01A1n", "T\u1ED5ng kh\u00E1ch tr\u1EA3" }));
		tblListHoaDon.getColumnModel().getColumn(4).setPreferredWidth(131);
		tblListHoaDon.getColumnModel().getColumn(5).setPreferredWidth(137);
		tblListHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 12));
		scrollPane.setViewportView(tblListHoaDon);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(614, 31, 267, 39);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Chi tiết hóa đơn");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1.setBounds(54, 0, 133, 39);
		panel_3.add(lblNewLabel_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(573, 69, 365, 236);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Mã hóa đơn: ");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(20, 0, 84, 32);
		panel_4.add(lblNewLabel_2);

		JLabel lblChiTiet_MaHD = new JLabel("");
		lblChiTiet_MaHD.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblChiTiet_MaHD.setBounds(101, 0, 200, 32);
		panel_4.add(lblChiTiet_MaHD);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 29, 345, 207);
		panel_4.add(scrollPane_1);

		tblListChiTietHoaDon = new JTable();
		tblListChiTietHoaDon.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 chi ti\u1EBFt",
				"M\u00E3 s\u00E1ch", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1", "T\u1ED5ng ti\u1EC1n" }));
		tblListChiTietHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 12));
		scrollPane_1.setViewportView(tblListChiTietHoaDon);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(20, 315, 918, 360);
		panel.add(panel_5);
		panel_5.setLayout(null);

		JPanel pnXemHDTheoNgay = new JPanel();
		pnXemHDTheoNgay.setBackground(SystemColor.controlHighlight);
		pnXemHDTheoNgay.setBounds(10, 157, 363, 128);
		panel_5.add(pnXemHDTheoNgay);
		pnXemHDTheoNgay.setLayout(null);

		JLabel lblNewLabel_3_1_1_2 = new JLabel("Từ ngày: ");
		lblNewLabel_3_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_1_1_2.setBounds(47, 10, 72, 25);
		pnXemHDTheoNgay.add(lblNewLabel_3_1_1_2);

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
		pnXemHDTheoMaKH.setBounds(10, 10, 363, 128);
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

		JPanel pnXemHDTheoMaKH_1 = new JPanel();
		pnXemHDTheoMaKH_1.setBackground(SystemColor.controlHighlight);
		pnXemHDTheoMaKH_1.setLayout(null);
		pnXemHDTheoMaKH_1.setBounds(411, 10, 363, 128);
		panel_5.add(pnXemHDTheoMaKH_1);

		JLabel lblNewLabel_3_1 = new JLabel("Nhập vào mã nhân viên: ");
		lblNewLabel_3_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(86, 13, 188, 25);
		pnXemHDTheoMaKH_1.add(lblNewLabel_3_1);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(86, 48, 157, 25);
		pnXemHDTheoMaKH_1.add(txtMaNV);

		JButton btnXemTheoMaNV = new JButton("Xem hóa đơn theo mã nhân viên");
		btnXemTheoMaNV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnXemTheoMaNV.setBounds(50, 83, 261, 35);
		pnXemHDTheoMaKH_1.add(btnXemTheoMaNV);

		JPanel pnXemHDTheoNgay_1 = new JPanel();
		pnXemHDTheoNgay_1.setLayout(null);
		pnXemHDTheoNgay_1.setBackground(SystemColor.controlHighlight);
		pnXemHDTheoNgay_1.setBounds(411, 157, 363, 128);
		panel_5.add(pnXemHDTheoNgay_1);

		JLabel lblNewLabel_3_1_1_2_1 = new JLabel("Tổng hóa đơn từ: ");
		lblNewLabel_3_1_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_1_1_2_1.setBounds(22, 10, 113, 25);
		pnXemHDTheoNgay_1.add(lblNewLabel_3_1_1_2_1);

		JButton btnXemTheoTongHD = new JButton("Xem hóa đơn theo tổng thanh toán");
		btnXemTheoTongHD.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnXemTheoTongHD.setBounds(57, 80, 257, 35);
		pnXemHDTheoNgay_1.add(btnXemTheoTongHD);

		JLabel lblNewLabel_3_1_1_1_1_1 = new JLabel("Đến: ");
		lblNewLabel_3_1_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3_1_1_1_1_1.setBounds(90, 45, 45, 25);
		pnXemHDTheoNgay_1.add(lblNewLabel_3_1_1_1_1_1);

		txtTuTongTien = new JTextField();
		txtTuTongTien.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtTuTongTien.setColumns(10);
		txtTuTongTien.setBounds(145, 10, 157, 25);
		pnXemHDTheoNgay_1.add(txtTuTongTien);

		txtDenTongTien = new JTextField();
		txtDenTongTien.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtDenTongTien.setColumns(10);
		txtDenTongTien.setBounds(145, 45, 157, 25);
		pnXemHDTheoNgay_1.add(txtDenTongTien);

		JButton btnBanDau = new JButton("Ban đầu");
		btnBanDau.setBackground(new Color(0, 139, 139));
		btnBanDau.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnBanDau.setBounds(783, 128, 125, 46);
		panel_5.add(btnBanDau);

		docListHoaDon(tblListHoaDon);
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
				docListHoaDon(tblListHoaDon);
				String maKH = xoaKhoangTrangThua(txtMaKH.getText());
				if (maKH.equals("")) {
					docListHoaDon(tblListHoaDon);
				} else {
					xemHoaDonTheoMaKH(tblListHoaDon, maKH);
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
				docListHoaDon(tblListHoaDon);
				String maNV = xoaKhoangTrangThua(txtMaNV.getText());
				if (maNV.equals("")) {
					docListHoaDon(tblListHoaDon);
				} else {
					xemHoaDonTheoMaNV(tblListHoaDon, maNV);
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

		btnXemTheoTongHD.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				String tu = xoaKhoangTrangThua(txtTuTongTien.getText());
				String den = xoaKhoangTrangThua(txtDenTongTien.getText());
				if (!checkSoThuc(tu) || !checkSoThuc(den)) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số thực");
				} else if (checkSoThuc(tu) && checkSoThuc(den) && stringSangDouble(tu) > stringSangDouble(den)) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mốc nhỏ hơn đích");
				} else if (checkSoThuc(tu) && checkSoThuc(den) && stringSangDouble(tu) <= stringSangDouble(den)) {
					xemHoaDonTheoTongHD(tblListChiTietHoaDon, stringSangDouble(tu), stringSangDouble(den));
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
				docListHoaDon(tblListHoaDon);
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

	}
}
