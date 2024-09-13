package QuanLyCuaHangSach.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import QuanLyCuaHangSach.BUS.CTPhieuNhapBUS;
import QuanLyCuaHangSach.BUS.PhieuNhapBUS;
import QuanLyCuaHangSach.DAO.CTPhieuNhapDAO;
import QuanLyCuaHangSach.DTO.CTPhieuNhapDTO;
import QuanLyCuaHangSach.DTO.NhaCungCapDTO;
import QuanLyCuaHangSach.DTO.PhieuNhapDTO;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

public class QuanLiNhapHangGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	DefaultTableModel model = new DefaultTableModel();

	
	public QuanLiNhapHangGUI() {

		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(0, 0, 1071, 661);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Phiếu Nhập", null, panel, null);
		tabbedPane.setForegroundAt(0, Color.white); 
		tabbedPane.setBackgroundAt(0, Color.ORANGE); 
		tabbedPane.setFont(new Font("Arial", Font.BOLD, 20)); 
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PHIẾU NHẬP SÁCH");
		lblNewLabel.setForeground(new Color(251, 135, 30));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBackground(new Color(251, 135, 30));
		lblNewLabel.setBounds(386, 20, 250, 33);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã PN:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(50, 61, 109, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã NCC:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(50, 102, 109, 23);
		panel.add(lblNewLabel_1_1);
		
		txtMaPN = new JTextField();
		txtMaPN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaPN.setBounds(164, 58, 173, 29);
		txtMaPN.setColumns(10);
		panel.add(txtMaPN);
		
		JButton btnChon = new JButton("....");
		btnChon.setBounds(347, 105, 35, 20);
		panel.add(btnChon);
		btnChon.addActionListener(new java.awt.event.ActionListener() {
			    public void actionPerformed(java.awt.event.ActionEvent evt) {
			        NhaCungCapGUI frameNCC = new NhaCungCapGUI();
			        frameNCC.setLocationRelativeTo(frameNCC);
			        frameNCC.setVisible(true);
			        frameNCC.addWindowListener(new java.awt.event.WindowAdapter() {
			            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
			                // Lấy dữ liệu từ frameNCC sau khi nó đóng lại
			                NhaCungCapDTO ncc = frameNCC.getSelectedNhaCungCap();
			                if (ncc != null) {
			                    // Hiển thị dữ liệu trên txtMaNCC
			                    txtMaNCC.setText(String.valueOf(ncc.getMa()));
			                }
			            }
			        });
			    }
			});
		
		txtMaNCC = new JTextField();
		txtMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaNCC.setColumns(10);
		txtMaNCC.setBounds(164, 99, 173, 29);
		panel.add(txtMaNCC);
		txtMaNCC.setEditable(false);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Mã NV:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(50, 145, 109, 23);
		panel.add(lblNewLabel_1_1_1);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(164, 138, 173, 29);
		panel.add(txtMaNV);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Ngày lập:");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(472, 72, 109, 23);
		panel.add(lblNewLabel_1_1_2);
		
		 dateNgayNhap = new JDateChooser();
		 dateNgayNhap.setBounds(591, 61, 173, 33);
		 panel.add(dateNgayNhap);
		 
		 JLabel lblNewLabel_1_1_2_1 = new JLabel("Tổng tiền");
		 lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblNewLabel_1_1_2_1.setBounds(472, 124, 109, 23);
		 panel.add(lblNewLabel_1_1_2_1);
		 
		 txtTongTien = new JTextField();
		 txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 txtTongTien.setColumns(10);
		 txtTongTien.setBounds(591, 121, 173, 29);
		 panel.add(txtTongTien);
		 txtTongTien.setEditable(false);
		 
		 JButton btnLuu = new JButton("LƯU");
		 btnLuu.setIcon(new ImageIcon(QuanLiNhapHangGUI.class.getResource("/icon/Custom-Icon-Design-Flatastic-9-Save-as.24.png")));
		 btnLuu.setBackground(new Color(242, 233, 40));
		 btnLuu.setFont(new Font("Tahoma", Font.BOLD, 17));
		 btnLuu.setBounds(845, 88, 127, 48);
		 panel.add(btnLuu);
		 btnLuu.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	luuPhieu(evt);
	            }
	        });
		 
		 JButton btnTaoPhieu = new JButton("Tạo CTPN");
		 btnTaoPhieu.setBackground(new Color(202, 255, 202));
		 btnTaoPhieu.setBounds(386, 169, 96, 23);
		 panel.add(btnTaoPhieu);
		 btnTaoPhieu.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseClicked(java.awt.event.MouseEvent evt) {
			    	 if (txtMaPN.getText().trim().isEmpty())  {
			             JOptionPane.showMessageDialog(null, "Ô mã phiếu nhập không được để trống!");
			             return;
			         }
			    	 if (txtMaNCC.getText().trim().isEmpty()) {
			    		 JOptionPane.showMessageDialog(null, "Ô mã NCC không được để trống!");
			             return;
			    	 }
			    	 if (txtMaNV.getText().trim().isEmpty()) {
			    		 JOptionPane.showMessageDialog(null, "Ô mã nhân viên không được để trống!");
			             return;
			    	 }
			    	 if (dateNgayNhap.getDate() == null){
			    		 JOptionPane.showMessageDialog(null, "Ô ngày nhập không được để trống!");
			             return;
			    	 }
			        try {
			            // Lấy dữ liệu từ txtMaPN và chuyển đổi thành số nguyên
			            int maPN = Integer.parseInt(txtMaPN.getText());
			            
			            // Đưa dữ liệu vào txtMaPhieuNhap
			            txtMaPhieuNhap.setText(String.valueOf(maPN)); // Chuyển số nguyên thành chuỗi trước khi đặt vào JTextField
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(null, "Nhập không hợp lệ!");
			        }
			    }
			});

		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "CHI TI\u1EBET PHI\u1EBEU NH\u1EACP", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(252, 136, 97)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(43, 237, 977, 339);
		panel.add(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 545, 244);
		panel_2.add(scrollPane);
		tblCTPN = new JTable();
		 tblCTPN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 tblCTPN.setRowHeight(20);
		 scrollPane.setViewportView(tblCTPN);
		 tblCTPN.setModel(new DefaultTableModel(
			        new Object[][] {
			            { null, null, null, null, null},
			            { null, null, null, null, null},
			            { null, null, null, null, null},
			            { null, null, null, null, null},
			            { null, null, null, null, null},
			            
			        },
			        new String[] {
			            "Mã phiếu nhập", "Mã sách", "Số lượng", "Đơn giá ", "Thành tiền"
			        }
			    ));
		 tblCTPN.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(java.awt.event.MouseEvent evt) {
		        	tblCTPN(evt);
		        }
		    });
		 scrollPane.setViewportView(tblCTPN);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Mã Phiếu Nhập:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(604, 53, 138, 23);
		panel_2.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Mã Sách:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(604, 96, 109, 23);
		panel_2.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("Đơn Giá:");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_2.setBounds(604, 135, 109, 23);
		panel_2.add(lblNewLabel_1_2_1_2);
		
		JLabel lblNewLabel_1_2_1_3 = new JLabel("Số Lượng:");
		lblNewLabel_1_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_3.setBounds(604, 178, 109, 23);
		panel_2.add(lblNewLabel_1_2_1_3);
		
		JLabel lblNewLabel_1_2_1_4 = new JLabel("Thành Tiền:");
		lblNewLabel_1_2_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1_4.setBounds(604, 226, 109, 23);
		panel_2.add(lblNewLabel_1_2_1_4);
		
		txtMaPhieuNhap = new JTextField();
		txtMaPhieuNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaPhieuNhap.setEditable(false);
		txtMaPhieuNhap.setColumns(10);
		txtMaPhieuNhap.setBounds(730, 50, 203, 29);
		panel_2.add(txtMaPhieuNhap);
		
		txtMaSach = new JTextField();
		txtMaSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaSach.setColumns(10);
		txtMaSach.setBounds(730, 93, 203, 29);
		panel_2.add(txtMaSach);
		
		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(730, 132, 203, 29);
		panel_2.add(txtDonGia);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(730, 178, 203, 29);
		panel_2.add(txtSoLuong);
		
		txtThanhTien = new JTextField();
		txtThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtThanhTien.setEditable(false);
		txtThanhTien.setColumns(10);
		txtThanhTien.setBounds(730, 223, 203, 29);
		panel_2.add(txtThanhTien);
		
		JButton btnThemCTPN = new JButton("THÊM\r\n");
		btnThemCTPN.setIcon(new ImageIcon(QuanLiNhapHangGUI.class.getResource("/icon/Custom-Icon-Design-Flatastic-1-Add.16.png")));
		btnThemCTPN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThemCTPN.setBackground(new Color(254, 235, 167));
		btnThemCTPN.setBounds(565, 285, 113, 30);
		panel_2.add(btnThemCTPN);
		btnThemCTPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	btnThemCTPhieu(evt);
            }
        });
		btnThemCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnThemActionPerformed(evt);
            }
        });
		
		JButton btnSua = new JButton("SỬA");
		btnSua.setIcon(new ImageIcon(QuanLiNhapHangGUI.class.getResource("/icon/Custom-Icon-Design-Pretty-Office-10-Pencil.24.png")));
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSua.setBackground(new Color(254, 235, 167));
		btnSua.setBounds(708, 285, 105, 30);
		panel_2.add(btnSua);
		btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnSua(evt);
            }
        });
		
		JButton btnXoa = new JButton("XÓA\r\n");
		btnXoa.setIcon(new ImageIcon(QuanLiNhapHangGUI.class.getResource("/icon/Awicons-Vista-Artistic-Delete.24.png")));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnXoa.setBackground(new Color(254, 235, 167));
		btnXoa.setBounds(843, 285, 105, 30);
		panel_2.add(btnXoa);
		btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnXoaPhieu(evt);
            }
        });
		 
		 
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Xem Phiếu Nhập", null, panel_1, null);
		tabbedPane.setForegroundAt(1, Color.white); 
		tabbedPane.setBackgroundAt(1, Color.ORANGE);
		panel_1.setLayout(null);
		
		
		JLabel lblNewLabel_2 = new JLabel("Xem Phiếu Nhập");
		lblNewLabel_2.setForeground(new Color(250, 170, 50));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_2.setBounds(412, 10, 265, 30);
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Phi\u1EBFu nh\u1EADp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(251, 125, 0)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(23, 65, 419, 549);
		panel_1.add(panel_3);
		
		JLabel lblNewLabel_1_3 = new JLabel("Mã:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3.setBounds(50, 26, 71, 21);
		panel_3.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Mã NCC:");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_3.setBounds(50, 57, 71, 21);
		panel_3.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Mã NV:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1.setBounds(50, 88, 71, 21);
		panel_3.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Ngày nhập :");
		lblNewLabel_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_2_2.setBounds(50, 119, 86, 21);
		panel_3.add(lblNewLabel_1_1_2_2);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel("Tổng tiền:");
		lblNewLabel_1_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_3_1.setBounds(50, 150, 71, 21);
		panel_3.add(lblNewLabel_1_1_3_1);
		
		txtMa = new JTextField();
		txtMa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMa.setEditable(false);
		txtMa.setColumns(10);
		txtMa.setBounds(158, 26, 149, 19);
		panel_3.add(txtMa);
		
		txtMaNCCung = new JTextField();
		txtMaNCCung.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaNCCung.setEditable(false);
		txtMaNCCung.setColumns(10);
		txtMaNCCung.setBounds(158, 56, 149, 21);
		panel_3.add(txtMaNCCung);
		
		txtMaNVien = new JTextField();
		txtMaNVien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaNVien.setEditable(false);
		txtMaNVien.setColumns(10);
		txtMaNVien.setBounds(158, 87, 149, 21);
		panel_3.add(txtMaNVien);
		
		txtNgayNhap = new JTextField();
		txtNgayNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNgayNhap.setEditable(false);
		txtNgayNhap.setColumns(10);
		txtNgayNhap.setBounds(158, 118, 149, 21);
		panel_3.add(txtNgayNhap);
		
		txtTongtien = new JTextField();
		txtTongtien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTongtien.setEditable(false);
		txtTongtien.setColumns(10);
		txtTongtien.setBounds(158, 149, 149, 21);
		panel_3.add(txtTongtien);
		
		JScrollPane jscrollPane = new JScrollPane();
		jscrollPane.setBounds(10, 334, 399, 188);
		panel_3.add(jscrollPane);
		
		tblPN = new JTable();
		tblPN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tblPN.setRowHeight(20);
		jscrollPane.setViewportView(tblPN);
		tblPN.setModel(new DefaultTableModel(
			        new Object[][] {
			            {null, null, null, null, null},
			            {null, null, null, null, null},
			            {null, null, null, null, null},
			            {null, null, null, null, null},
			            {null, null, null, null, null},
			            
			        },
			        new String[] {
			            "Mã ", "Mã NCC", "Mã NV", "Ngày nhập", "Tổng tiền "
			        }
			    ));
		tblPN.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	tblPNDSSMouseClicked(evt);
	        }
	    });
		tblPN.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	layChiTiet(evt);
	        }
	    });
		
		
		
		 jscrollPane.setViewportView(tblPN);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(null, "T\u00CCM KI\u1EBEM", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 102, 28)));
		panel_2_1.setBackground(new Color(242, 255, 242));
		panel_2_1.setBounds(10, 208, 387, 111);
		panel_3.add(panel_2_1);
		
		dateTuNgay = new JDateChooser();
		dateTuNgay.setBounds(67, 34, 98, 25);
		panel_2_1.add(dateTuNgay);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Từ ngày:");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3_1.setBounds(10, 34, 71, 21);
		panel_2_1.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Đến ngày:");
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3_1_1.setBounds(199, 34, 71, 21);
		panel_2_1.add(lblNewLabel_1_3_1_1);
		
		dateDenNgay = new JDateChooser();
		dateDenNgay.setBounds(268, 34, 98, 25);
		panel_2_1.add(dateDenNgay);
		
		JButton btnTimKiemTheoNgay = new JButton("Tìm");
		btnTimKiemTheoNgay.setIcon(new ImageIcon(QuanLiNhapHangGUI.class.getResource("/icon/Custom-Icon-Design-Flatastic-1-Search.24.png")));
		btnTimKiemTheoNgay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTimKiemTheoNgay.setBackground(new Color(251, 189, 138));
		btnTimKiemTheoNgay.setBounds(140, 80, 90, 21);
		panel_2_1.add(btnTimKiemTheoNgay);
		 btnTimKiemTheoNgay.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	TimKiemTheoNgay(evt);
	            }
	        });
		
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chi ti\u1EBFt phi\u1EBFu nh\u1EADp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 156, 108)));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(452, 65, 612, 549);
		panel_1.add(panel_1_1);
		
		JLabel lblNewLabel_1_1_4 = new JLabel("Mã phiếu nhập");
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_4.setBounds(114, 28, 98, 21);
		panel_1_1.add(lblNewLabel_1_1_4);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Mã sách:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1_1_1.setBounds(114, 57, 71, 21);
		panel_1_1.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Đơn giá");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_2_1_1.setBounds(112, 88, 86, 21);
		panel_1_1.add(lblNewLabel_1_1_2_1_1);
		
		JLabel lblNewLabel_1_1_3_1_1 = new JLabel("Số lượng");
		lblNewLabel_1_1_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_3_1_1.setBounds(114, 119, 71, 21);
		panel_1_1.add(lblNewLabel_1_1_3_1_1);
		
		txtMaPNhap = new JTextField();
		txtMaPNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaPNhap.setEditable(false);
		txtMaPNhap.setColumns(10);
		txtMaPNhap.setBounds(239, 27, 149, 21);
		panel_1_1.add(txtMaPNhap);
		
		txtMasach = new JTextField();
		txtMasach.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMasach.setEditable(false);
		txtMasach.setColumns(10);
		txtMasach.setBounds(239, 58, 149, 21);
		panel_1_1.add(txtMasach);
		
		txtDongia = new JTextField();
		txtDongia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDongia.setEditable(false);
		txtDongia.setColumns(10);
		txtDongia.setBounds(239, 87, 149, 21);
		panel_1_1.add(txtDongia);
		
		txtSoluong = new JTextField();
		txtSoluong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSoluong.setEditable(false);
		txtSoluong.setColumns(10);
		txtSoluong.setBounds(239, 119, 149, 21);
		panel_1_1.add(txtSoluong);
		
		JLabel lblNewLabel_1_1_3_1_1_1 = new JLabel("Thành tiền");
		lblNewLabel_1_1_3_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_3_1_1_1.setBounds(114, 150, 71, 21);
		panel_1_1.add(lblNewLabel_1_1_3_1_1_1);
		
		txtThanhtien = new JTextField();
		txtThanhtien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtThanhtien.setEditable(false);
		txtThanhtien.setColumns(10);
		txtThanhtien.setBounds(239, 150, 149, 21);
		panel_1_1.add(txtThanhtien);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(32, 191, 558, 177);
		panel_1_1.add(scrollPane_1);
		
		 tblCTPN1 = new JTable();
		 tblCTPN1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 tblCTPN1.setRowHeight(20);
		 scrollPane_1.setViewportView(tblCTPN1);
		 tblCTPN1.setModel(new DefaultTableModel(
			        new Object[][] {
			            {null, null, null, null, null},
			            {null, null, null, null, null},
			            {null, null, null, null, null},
			            {null, null, null, null, null},
			            {null, null, null, null, null},
			            
			        },
			        new String[] {
			             "Mã phiếu nhập", "Mã sách", "Số lượng", "Đơn giá ", "Thành tiền"
			        }
			    ));
		 tblCTPN1.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(java.awt.event.MouseEvent evt) {
		        	tblCTPNDSSMouseClicked(evt);
		        }
		    });
		 
		 scrollPane_1.setViewportView(tblCTPN1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00CCM KI\u1EBEM", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 145, 34)));
		panel_3_1.setBackground(new Color(240, 255, 240));
		panel_3_1.setBounds(42, 403, 427, 107);
		panel_1_1.add(panel_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Từ giá:");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3_2.setBounds(10, 32, 71, 21);
		panel_3_1.add(lblNewLabel_1_3_2);
		
		txtTuGia = new JTextField();
		txtTuGia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTuGia.setColumns(10);
		txtTuGia.setBounds(60, 34, 116, 21);
		panel_3_1.add(txtTuGia);
		
		JLabel lblNewLabel_1_3_2_1 = new JLabel("Đến giá:");
		lblNewLabel_1_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3_2_1.setBounds(207, 32, 71, 21);
		panel_3_1.add(lblNewLabel_1_3_2_1);
		
		txtDenGia = new JTextField();
		txtDenGia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDenGia.setColumns(10);
		txtDenGia.setBounds(279, 31, 116, 21);
		panel_3_1.add(txtDenGia);
		
		JButton btnTKDonGia = new JButton("Tìm");
		btnTKDonGia.setIcon(new ImageIcon(QuanLiNhapHangGUI.class.getResource("/icon/Custom-Icon-Design-Flatastic-1-Search.24.png")));
		btnTKDonGia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTKDonGia.setBackground(new Color(251, 189, 138));
		btnTKDonGia.setBounds(181, 76, 91, 21);
		panel_3_1.add(btnTKDonGia);
		btnTKDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	TimKiemDonGia(evt);
            }
        });
		
		
		docPhieu();
		docCTPhieu();
		
		
	}
	
		public void btnThemCTPhieu(java.awt.event.MouseEvent evt) {
			int donGia =Integer.parseInt(txtDonGia.getText());
			if (donGia <= 0 ) {
		        JOptionPane.showMessageDialog(null, "Đơn giá phải là số nguyên dương.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			if (soLuong <= 0) {
		        JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
			if (txtMaSach.getText().trim().isEmpty())  {
	             JOptionPane.showMessageDialog(null, "Ô mã sách không được để trống!");
	             return;
	         }
	        CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO();
	        ctpn.maPhieuNhap = Integer.parseInt(txtMaPhieuNhap.getText());
	        ctpn.maSach = Integer.parseInt(txtMaSach.getText());
	        ctpn.dongia = donGia;
	        ctpn.soluong = soLuong;
	        int thanhtien = ctpn.dongia * ctpn.soluong;
	        txtThanhTien.setText(String.valueOf(thanhtien));
	        DecimalFormat decimalFormat = new DecimalFormat("#,###");
			String thanhTien = decimalFormat.format(thanhtien);
	        Vector header = new Vector();
	        header.add("Mã PN");
	        header.add("Mã sách");
	        header.add("Đơn giá ");
	        header.add("Số lượng ");
	        header.add("Thành tiền");
	
	        if (model.getRowCount() == 0)
	            model = new DefaultTableModel(header, 0);
	
	        Vector row = new Vector();
	        row.add(ctpn.maPhieuNhap);
	        row.add(ctpn.maSach);
	        row.add(donGia);
	        row.add(soLuong);
	        row.add(thanhTien);
	        model.addRow(row);
	        tblCTPN.setModel(model);
	        model.fireTableDataChanged();
	        
	        int tongtien = 0;
	        for (int i = 0; i < model.getRowCount(); i++) {
	            String tien = model.getValueAt(i, 4).toString();
	            if (!tien.isEmpty()) {
	                tongtien += Integer.parseInt(tien.replaceAll(",", ""));
	            }
	        }
	        txtTongTien.setText(decimalFormat.format(tongtien));
	        
	        txtMaSach.setText("");
	        txtDonGia.setText("");
	        txtSoLuong.setText("");
	        txtThanhTien.setText("");
	   
	}
	
	private void btnThemActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	private void tblCTPN(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSVMouseClicked
	    // TODO add your handling code here:
	    int  i=tblCTPN.getSelectedRow();
	    //JOptionPane.showMessageDialog(null, "row:"+i);

	    txtMaPhieuNhap.setText(tblCTPN.getModel().getValueAt(i, 0).toString());
	    txtMaSach.setText(tblCTPN.getModel().getValueAt(i, 1).toString());
	    txtDonGia.setText(tblCTPN.getModel().getValueAt(i, 2).toString());
	    txtSoLuong.setText(tblCTPN.getModel().getValueAt(i, 3).toString());
	    txtThanhTien.setText(tblCTPN.getModel().getValueAt(i, 4).toString());
	
	}
	private void btnSua(java.awt.event.ActionEvent evt) {
	    int i = tblCTPN.getSelectedRow();
	    if (i != -1) { 
	        CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO();
	        ctpn.maSach = Integer.parseInt(txtMaSach.getText());
	        
	        // Loại bỏ dấu phẩy từ chuỗi và chuyển đổi thành số nguyên
	        String donGiaText = txtDonGia.getText().replaceAll(",", "");
	        String soLuongText = txtSoLuong.getText().replaceAll(",", "");
	        ctpn.dongia = Integer.parseInt(donGiaText);
	        ctpn.soluong = Integer.parseInt(soLuongText);
	        
	        int thanhtien = ctpn.dongia * ctpn.soluong;
	        
	        // Định dạng số thành chuỗi có dấu phẩy
	        DecimalFormat decimalFormat = new DecimalFormat("#,###");
	        String thanhTienFormatted = decimalFormat.format(thanhtien);
	        
	        txtThanhTien.setText(thanhTienFormatted);
	        
	        // Cập nhật thông tin vào hàng được chọn
	        model.setValueAt(ctpn.maSach, i, 1);
	        model.setValueAt(decimalFormat.format(ctpn.dongia), i, 2); // Định dạng lại giá trị đơn giá
	        model.setValueAt(decimalFormat.format(ctpn.soluong), i, 3); // Định dạng lại giá trị số lượng
	        model.setValueAt(thanhTienFormatted, i, 4);
	        
	        // Tính tổng tiền
	        int tongtien = 0;
	        for (int a = 0; a < model.getRowCount(); a++) {
	            String tien = model.getValueAt(a, 4).toString().replaceAll(",", ""); // Loại bỏ dấu phẩy trước khi tính toán
	            if (!tien.isEmpty()) {
	                tongtien += Integer.parseInt(tien);
	            }
	        }
	        
	        // Định dạng tổng tiền thành chuỗi có dấu phẩy
	        String tongTienFormatted = decimalFormat.format(tongtien);
	        txtTongTien.setText(tongTienFormatted);
	        
	        txtMaSach.setText("");
	        txtDonGia.setText("");
	        txtSoLuong.setText("");
	        txtThanhTien.setText("");
	        
	        tblCTPN.setModel(model);
	    }
	}

	
	private void btnXoaPhieu(java.awt.event.ActionEvent evt) {
	    int i = tblCTPN.getSelectedRow();
	    if (i != -1) { 
	        CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO();
	        ctpn.maSach = Integer.parseInt(txtMaSach.getText());
	        
	        // Loại bỏ dấu phẩy từ chuỗi và chuyển đổi thành số nguyên
	        String donGiaText = txtDonGia.getText().replace(",", "");
	        String soLuongText = txtSoLuong.getText().replace(",", "");
	        ctpn.dongia = Integer.parseInt(donGiaText);
	        ctpn.soluong = Integer.parseInt(soLuongText);
	        
	        int thanhtien = ctpn.dongia * ctpn.soluong;
	        
	        // Định dạng số thành chuỗi có dấu phẩy
	        DecimalFormat decimalFormat = new DecimalFormat("#,###");
	        String thanhTienFormatted = decimalFormat.format(thanhtien);
	        
	        txtThanhTien.setText(thanhTienFormatted);
	        
	        // Cập nhật thông tin vào hàng được chọn
	        model.setValueAt(ctpn.maSach, i, 1);
	        model.setValueAt(decimalFormat.format(ctpn.dongia), i, 2); // Định dạng lại giá trị đơn giá
	        model.setValueAt(decimalFormat.format(ctpn.soluong), i, 3); // Định dạng lại giá trị số lượng
	        model.setValueAt(thanhTienFormatted, i, 4);
	        
	        // Tính tổng tiền
	        int tienPhieuNhap = Integer.parseInt(model.getValueAt(i, 4).toString().replace(",", ""));
	        int tongTienHienTai = Integer.parseInt(txtTongTien.getText().replace(",", ""));
	        int tongTienSauKhiXoa = tongTienHienTai - tienPhieuNhap;
	        
	        // Định dạng tổng tiền thành chuỗi có dấu phẩy
	        String tongTienFormatted = decimalFormat.format(tongTienSauKhiXoa);
	        txtTongTien.setText(tongTienFormatted);
	        
	        txtMaSach.setText("");
	        txtDonGia.setText("");
	        txtSoLuong.setText("");
	        txtThanhTien.setText("");
	        
	        // Cập nhật model và giao diện
	        model.removeRow(i);
	        tblCTPN.setModel(model);
	    }
	}




	
	public void luuPhieu(java.awt.event.ActionEvent evt) {
		if (txtMaPN.getText().trim().isEmpty() || txtMaNCC.getText().trim().isEmpty() || txtMaNV.getText().trim().isEmpty() || dateNgayNhap == null ) {
			JOptionPane.showMessageDialog(null, "Các ô không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
		}
		if (model.getRowCount() ==0) {
			JOptionPane.showMessageDialog(null, "Bạn phải tạo CTPN","Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
		}
		try {
			 PhieuNhapDTO pn = new PhieuNhapDTO();
			    pn.maPN = Integer.parseInt(txtMaPN.getText());
			    pn.maNCC = Integer.parseInt(txtMaNCC.getText());
			    pn.maNV = Integer.parseInt(txtMaNV.getText());
			    Date ngayNhap = dateNgayNhap.getDate();
			    pn.ngaylap = ngayNhap;
		        pn.tongtien = Integer.parseInt(txtTongTien.getText().replace(",", ""));
			    PhieuNhapBUS bus = new PhieuNhapBUS();
			    bus.themPhieu(pn);
			    
			    ArrayList<CTPhieuNhapDTO> dsCTPhieuNhap = new ArrayList<>();
			    for (int i = 0; i < model.getRowCount(); i++) {
			        CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO();
			        ctpn.setMaPhieuNhap(Integer.parseInt(model.getValueAt(i, 0).toString()));
			        ctpn.setMaSach(Integer.parseInt(model.getValueAt(i, 1).toString()));
			        ctpn.setDongia(Integer.parseInt(model.getValueAt(i, 2).toString()));
			        ctpn.setSoluong(Integer.parseInt(model.getValueAt(i, 3).toString()));
		            ctpn.setThanhtien(Integer.parseInt(model.getValueAt(i, 4).toString().replace(",", "")));
			        dsCTPhieuNhap.add(ctpn);
			    }

			    CTPhieuNhapBUS ctpnBUS = new CTPhieuNhapBUS();
			    ctpnBUS.themCTPN(dsCTPhieuNhap);
			    docPhieu(); 
			    docCTPhieu();
			    model.setRowCount(0);
			    tblCTPN.setModel(model);
			    
			    txtMaPN.setText("");
			    txtMaNCC.setText("");
			    txtMaNV.setText("");
			    dateNgayNhap.setDate(null);
			    txtTongTien.setText("");
			    txtMaPhieuNhap.setText("");
			    txtMaSach.setText("");
			    txtDonGia.setText("");
			    txtSoLuong.setText("");
			    txtThanhTien.setText(getName());
			    
			    
			    JOptionPane.showMessageDialog(null, "Lưu phiếu nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    } catch (Exception e) {
	        // Hiển thị thông báo lưu thất bại và in ra lỗi để debug
	        JOptionPane.showMessageDialog(null, "Lưu phiếu nhập thất bại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	    }
	   
	    
	}
	
	public void setData(NhaCungCapDTO ncc) {
	    txtMaNCC.setText(String.valueOf(ncc.getMa())); // Cập nhật trường txtMaNCC với mã nhà cung cấp đã chọn
	}
	
	protected ArrayList<CTPhieuNhapDTO> layDanhSachChiTietCTPN(String maPhieuNhap) {
		// TODO Auto-generated method stub
		return null;
	}

	public void docPhieu() {
		PhieuNhapBUS bus = new PhieuNhapBUS();
		 if (PhieuNhapBUS.dsphieunhap == null) 
			 bus.docPhieuNhap(null);
		 Vector header = new Vector();
			header.add("Mã phiếu nhập");
			header.add("Mã NV");
			header.add("Mã NCC");
			header.add("Ngày lập ");
			header.add("Tổng tiền");
			DefaultTableModel model1 = new DefaultTableModel (header,0);
		for (PhieuNhapDTO pn : PhieuNhapBUS.dsphieunhap)
		{
			Vector row = new Vector();
			row.add(pn.maPN);
			row.add(pn.maNV);
			row.add(pn.maNCC);
			row.add(pn.ngaylap);
			DecimalFormat decimalFormat = new DecimalFormat("#,###");
			String tongTien = decimalFormat.format(pn.tongtien);
			row.add(tongTien);
			
			model1.addRow(row);
		}
		tblPN.setModel(model1);
		
	}
	
	private void tblPNDSSMouseClicked(java.awt.event.MouseEvent evt) {
	   int i = tblPN.getSelectedRow();
	   if (i>=0) {
		   txtMa.setText(tblPN.getModel().getValueAt(i, 0).toString());
		   txtMaNCCung.setText(tblPN.getModel().getValueAt(i, 2).toString());
		   txtMaNVien.setText(tblPN.getModel().getValueAt(i, 1).toString());
		   txtNgayNhap.setText(tblPN.getModel().getValueAt(i, 3).toString());
		   txtTongtien.setText(tblPN.getModel().getValueAt(i, 4).toString());
	   }
	}
	public void docCTPhieu() {
		CTPhieuNhapBUS bus = new CTPhieuNhapBUS();
		 if (CTPhieuNhapBUS.dsCTPN == null) 
			 bus.docCTPhieu(null);
		 Vector header = new Vector();
			header.add("Mã PN");
			header.add("Mã sách");
			header.add("Đơn giá");
			header.add("Số lượng");
			header.add("Thành tiền");
			DefaultTableModel model2 = new DefaultTableModel (header,0);
		for (CTPhieuNhapDTO ctpn : CTPhieuNhapBUS.dsCTPN)
		{
			Vector row = new Vector();
			row.add(ctpn.maPhieuNhap);
			row.add(ctpn.maSach);
			row.add(ctpn.dongia);
			row.add(ctpn.soluong);
			DecimalFormat decimalFormat = new DecimalFormat("#,###");
			String Thanhtien = decimalFormat.format(ctpn.thanhtien);
			row.add(Thanhtien);
			
			model2.addRow(row);
		}
		tblCTPN1.setModel(model2);
		
	}
	private void tblCTPNDSSMouseClicked(java.awt.event.MouseEvent evt) {
		   int i = tblCTPN1.getSelectedRow();
		   if (i>=0) {
			   txtMaPNhap.setText(tblCTPN1.getModel().getValueAt(i, 0).toString());
			   txtMasach.setText(tblCTPN1.getModel().getValueAt(i, 1).toString());
			   txtDongia.setText(tblCTPN1.getModel().getValueAt(i, 2).toString());
			   txtSoluong.setText(tblCTPN1.getModel().getValueAt(i, 3).toString());
			   txtThanhtien.setText(tblCTPN1.getModel().getValueAt(i, 4).toString());




		   }
		}
	
	private void TimKiemDonGia(java.awt.event.ActionEvent evt) {
		String giathap = txtTuGia.getText();
		String giacao = txtDenGia.getText();
		CTPhieuNhapBUS bus = new CTPhieuNhapBUS();
		ArrayList<CTPhieuNhapDTO> ketqua = bus.TimkiemNhapTheoDG(giathap, giacao);
		if (ketqua!=null) {
			DefaultTableModel modelTB = new DefaultTableModel(); 
		    modelTB.setColumnIdentifiers(new Object[]{"Mã PN", "Mã sách", "Số lượng", "Đơn giá", "Thành tiền"});
			for (CTPhieuNhapDTO ctpn : ketqua) {
				 Vector row = new Vector();
				 row.add(ctpn.maPhieuNhap);
				 row.add(ctpn.maSach);
				 row.add(ctpn.soluong);
				 row.add(ctpn.dongia);
			     int thanhtien = ctpn.dongia * ctpn.soluong;
				 DecimalFormat decimalFormat = new DecimalFormat("#,###");
				 String Thanhtien = decimalFormat.format(thanhtien);
				 row.add(Thanhtien);
				 modelTB.addRow(row);
			}
			tblCTPN1.setModel(modelTB);
		}
		else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả.");
		}
	}
	
	private void layChiTiet(MouseEvent e) {
	    int selectedRow = tblPN.getSelectedRow();
	    if (selectedRow != -1) { 
	        String maPhieuNhap = tblPN.getValueAt(selectedRow, 0).toString(); 
	        CTPhieuNhapDAO ctpnDAO = new CTPhieuNhapDAO();	        
	        try {	            
	            ArrayList<CTPhieuNhapDTO> chiTietList = ctpnDAO.layDanhSachChiTietCTPN(maPhieuNhap);	            
	            DefaultTableModel chiTietModel = (DefaultTableModel) tblCTPN1.getModel();
	            chiTietModel.setRowCount(0);
	            for (CTPhieuNhapDTO chiTiet : chiTietList)
	            {
	            	DecimalFormat decimalFormat = new DecimalFormat("#,###");
	        		String Thanhtien = decimalFormat.format(chiTiet.getThanhtien());
	                chiTietModel.addRow(new Object[] { 
	                    chiTiet.getMaPhieuNhap(),
	                    chiTiet.getMaSach(),
	                    chiTiet.getDongia(),   
	                    chiTiet.getSoluong(),
	                    (Thanhtien)
	                });
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	}
	
    private void TimKiemTheoNgay(ActionEvent evt) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date min = dateTuNgay.getDate();
            Date max = dateDenNgay.getDate();

            PhieuNhapBUS bus = new PhieuNhapBUS();
            ArrayList<PhieuNhapDTO> ketqua = bus.timkiemTheoNgay(min, max);

            if (ketqua != null) {
                DefaultTableModel modelTB = new DefaultTableModel();
                modelTB.setColumnIdentifiers(new Object[]{"Mã ", "Mã NCC", "Mã NV", "Ngày nhập", "Tổng tiền"});
                for (PhieuNhapDTO pn : ketqua) {
                    Vector row = new Vector();
                    row.add(pn.maPN);
                    row.add(pn.maNCC);
                    row.add(pn.maNV);
                    row.add(pn.ngaylap);
                    row.add(pn.tongtien);
                    modelTB.addRow(row);
                }
                tblPN.setModel(modelTB);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private JTextField txtMaPN;
	private JTextField txtMaNCC;
	private JDateChooser dateNgayNhap;
	private JTextField txtTongTien;
	private javax.swing.JTable tblCTPN;
	private JTextField txtMaPhieuNhap;
	private JTextField txtMaSach;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JTextField txtThanhTien;
	private JTextField txtMaNV;
	private JTextField txtMa;
	private JTextField txtMaNCCung;
	private JTextField txtMaNVien;
	private JTextField txtNgayNhap;
	private JTextField txtTongtien;
	
	private javax.swing.JTable tblPN;
	private JTextField txtMaPNhap;
	private JTextField txtMasach;
	private JTextField txtDongia;
	private JTextField txtSoluong;
	private JTextField txtThanhtien;
	private JTextField txtTuGia;
	private JTextField txtDenGia;
	private javax.swing.JTable tblCTPN1;
	private JDateChooser dateTuNgay;
	private JDateChooser dateDenNgay;
}
