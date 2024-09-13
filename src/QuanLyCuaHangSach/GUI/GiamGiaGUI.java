package QuanLyCuaHangSach.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import QuanLyCuaHangSach.BUS.ChiTietCTKMBUS;
import QuanLyCuaHangSach.BUS.GiamGiaBUS;
import QuanLyCuaHangSach.DTO.ChiTietCTKM;
import QuanLyCuaHangSach.DTO.GiamGia;
import QuanLyCuaHangSach.GUI.GiamGiaGUI;

import javax.swing.border.EtchedBorder;

public class GiamGiaGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMGG;
	private JTextField txtTGG;
	private JTextField txtMCTGG;
	private JTextField txtMGG2;
	private JTextField txtMSGG;
	private JTextField txtPTKM;
	private JTextField txtTKMGG;
	private JTextField txtTKTGG;
	private JTable table;
	private GiamGiaBUS giamGiaBUS;
	private JTable tableCTMGG;
	private JDateChooser NgayBD;
    private JDateChooser NgayKT;
    

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public GiamGiaGUI() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 243, 243));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ch\u01B0\u01A1ng Tr\u00ECnh Khuy\u1EBFn M\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(94, 228, 65)));
		panel.setBounds(10, 53, 488, 627);
		add(panel);
		panel.setLayout(null);
		
		JLabel lbMGG = new JLabel("Mã giảm giá");
		lbMGG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbMGG.setBounds(69, 32, 126, 30);
		panel.add(lbMGG);
		
		txtMGG = new JTextField();
		txtMGG.setColumns(10);
		txtMGG.setBounds(221, 32, 145, 30);
		panel.add(txtMGG);
		
		JLabel lbTGG = new JLabel("Tên chương trình");
		lbTGG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTGG.setBounds(69, 74, 126, 30);
		panel.add(lbTGG);
		
		txtTGG = new JTextField();
		txtTGG.setColumns(10);
		txtTGG.setBounds(221, 72, 145, 30);
		panel.add(txtTGG);
		
		JLabel lnBD = new JLabel("Thời gian bắt đầu");
		lnBD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lnBD.setBounds(69, 118, 126, 30);
		panel.add(lnBD);
		
		NgayBD = new JDateChooser();
		NgayBD.setBounds(221, 118, 145, 30);
		panel.add(NgayBD);
		
		JLabel lbKT = new JLabel("Thời gian kết thúc");
		lbKT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbKT.setBounds(69, 158, 126, 30);
		panel.add(lbKT);
		
		NgayKT = new JDateChooser();
		NgayKT.setBounds(223, 158, 145, 30);
		panel.add(NgayKT);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(GiamGiaGUI.class.getResource("/icon/Custom-Icon-Design-Flatastic-1-Add.16.png")));
		btnThem.setForeground(new Color(0, 0, 0));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnThem.setBackground(new Color(252, 250, 205));
		btnThem.setBounds(69, 222, 105, 30);
		panel.add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(GiamGiaGUI.class.getResource("/icon/Awicons-Vista-Artistic-Delete.24.png")));
		btnXoa.setForeground(new Color(0, 0, 0));
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnXoa.setBackground(new Color(252, 250, 205));
		btnXoa.setBounds(203, 222, 105, 30);
		panel.add(btnXoa);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(GiamGiaGUI.class.getResource("/icon/Custom-Icon-Design-Pretty-Office-10-Pencil.24.png")));
		btnSua.setForeground(new Color(0, 0, 0));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSua.setBackground(new Color(252, 250, 205));
		btnSua.setBounds(338, 222, 94, 30);
		panel.add(btnSua);
		
		JButton btnNewButton_8 = new JButton("Xuất Excel");
		btnNewButton_8.setIcon(new ImageIcon(GiamGiaGUI.class.getResource("/icon/Blackvariant-Button-Ui-Ms-Office-2016-Excel-2.16.png")));
		btnNewButton_8.setForeground(new Color(0, 0, 0));
		btnNewButton_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_8.setBackground(new Color(252, 250, 205));
		btnNewButton_8.setBounds(328, 571, 138, 35);
		panel.add(btnNewButton_8);
		
		JButton btnNewButton_7 = new JButton("Nhập Excel");
		btnNewButton_7.setIcon(new ImageIcon(GiamGiaGUI.class.getResource("/icon/Blackvariant-Button-Ui-Ms-Office-2016-Excel-2.16.png")));
		btnNewButton_7.setForeground(new Color(0, 0, 0));
		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_7.setBackground(new Color(252, 250, 205));
		btnNewButton_7.setBounds(171, 571, 145, 35);
		panel.add(btnNewButton_7);
		
		JScrollPane tableMGG = new JScrollPane();
		tableMGG.setBounds(20, 278, 446, 275);
		panel.add(tableMGG);
		
		table = new JTable();
		tableMGG.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Ch\u01B0\u01A1ng Tr\u00ECnh", "T\u00EAn Ch\u01B0\u01A1ng Tr\u00ECnh", "Ng\u00E0y b\u1EAFt \u0111\u1EA7u", "Ng\u00E0y k\u1EBFt th\u00FAc"
			}
		));
		
		JButton btnReset = new JButton("");
		btnReset.setIcon(new ImageIcon(GiamGiaGUI.class.getResource("/icon/reset.png")));
		btnReset.setForeground(SystemColor.control);
		btnReset.setBackground(new Color(252, 250, 205));
		btnReset.setBounds(121, 578, 31, 28);
		panel.add(btnReset);
		table.getColumnModel().getColumn(0).setPreferredWidth(109);
		table.getColumnModel().getColumn(1).setPreferredWidth(179);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(243, 243, 243));
		panel_2.setLayout(null);
		panel_2.setBounds(508, 234, 452, 436);
		add(panel_2);
		
		JLabel lblNewLabel_10 = new JLabel("Mã chi tiết CTGG:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(34, 63, 137, 22);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Mã chương trình giảm giá");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(34, 95, 196, 24);
		panel_2.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Mã sách giảm giá");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_12.setBounds(34, 129, 158, 19);
		panel_2.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Phần trăm giảm giá:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_13.setBounds(34, 158, 158, 22);
		panel_2.add(lblNewLabel_13);
		
		txtMCTGG = new JTextField();
		txtMCTGG.setColumns(10);
		txtMCTGG.setBounds(181, 59, 129, 24);
		panel_2.add(txtMCTGG);
		
		JButton btnThem1 = new JButton("Thêm");
		btnThem1.setForeground(new Color(0, 0, 0));
		btnThem1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThem1.setBackground(new Color(252, 250, 205));
		btnThem1.setBounds(55, 201, 79, 19);
		panel_2.add(btnThem1);
		
		JButton btnSua1 = new JButton("sửa");
		btnSua1.setForeground(new Color(0, 0, 0));
		btnSua1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSua1.setBackground(new Color(252, 250, 205));
		btnSua1.setBounds(190, 201, 79, 19);
		panel_2.add(btnSua1);
		
		JButton btnXoa1 = new JButton("xóa");
		btnXoa1.setForeground(new Color(0, 0, 0));
		btnXoa1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoa1.setBackground(new Color(252, 250, 205));
		btnXoa1.setBounds(319, 201, 79, 19);
		panel_2.add(btnXoa1);
		
		JLabel lblNewLabel_16 = new JLabel("Chi tiết chương trình giảm giá");
		lblNewLabel_16.setForeground(new Color(94, 228, 65));
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_16.setBounds(135, 10, 239, 29);
		panel_2.add(lblNewLabel_16);
		
		txtMGG2 = new JTextField();
		txtMGG2.setColumns(10);
		txtMGG2.setBounds(221, 95, 129, 24);
		panel_2.add(txtMGG2);
		
		txtMSGG = new JTextField();
		txtMSGG.setColumns(10);
		txtMSGG.setBounds(221, 129, 129, 24);
		panel_2.add(txtMSGG);
		
		txtPTKM = new JTextField();
		txtPTKM.setColumns(10);
		txtPTKM.setBounds(221, 162, 129, 24);
		panel_2.add(txtPTKM);
		
		JScrollPane tableCTCTKM = new JScrollPane();
		tableCTCTKM.setBounds(10, 237, 431, 175);
		panel_2.add(tableCTCTKM);
		
		tableCTMGG = new JTable();
		tableCTCTKM.setViewportView(tableCTMGG);
		tableCTMGG.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableCTMGG.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 CTCTKM", "M\u00E3 Ch\u01B0\u01A1ng Tr\u00ECnh", "Ph\u1EA7m tr\u0103m gi\u1EA3m gi\u00E1", "M\u00E3 S\u00E1ch \u00E1p d\u1EE5ng"
			}
		));
		
		JButton btnThongTinSach = new JButton(". . . ");
		btnThongTinSach.setBackground(new Color(252, 250, 205));
		btnThongTinSach.setForeground(Color.WHITE);
		btnThongTinSach.setBounds(371, 129, 42, 23);
		panel_2.add(btnThongTinSach);
		btnThongTinSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create and display the new frame
                TimKiemSachGUI employeeListFrame = new TimKiemSachGUI();
                employeeListFrame.setVisible(true);
                employeeListFrame.loadDataToTable();
            }
        });
		
		
		tableCTMGG.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableCTMGG.getColumnModel().getColumn(1).setPreferredWidth(91);
		tableCTMGG.getColumnModel().getColumn(2).setPreferredWidth(128);
		tableCTMGG.getColumnModel().getColumn(3).setPreferredWidth(128);
		
		JLabel lblNewLabel_9 = new JLabel("Tìm kiếm");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_9.setBounds(681, 54, 94, 30);
		add(lblNewLabel_9);
		
		JLabel lblNewLabel_5 = new JLabel("Ngày bắt đầu:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(508, 90, 107, 39);
		add(lblNewLabel_5);
		
		JDateChooser NgayBD1 = new JDateChooser();
		NgayBD1.setBounds(625, 90, 94, 30);
		add(NgayBD1);
		
		JLabel lblNewLabel_7 = new JLabel("Ngày kết thúc:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(749, 90, 107, 39);
		add(lblNewLabel_7);
		
		JDateChooser NgayKT1 = new JDateChooser();
		NgayKT1.setBounds(866, 90, 94, 30);
		add(NgayKT1);
		
		JLabel lblNewLabel_6 = new JLabel("Mã giảm giá:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(508, 139, 102, 26);
		add(lblNewLabel_6);
		
		txtTKMGG = new JTextField();
		txtTKMGG.setColumns(10);
		txtTKMGG.setBounds(621, 142, 98, 26);
		add(txtTKMGG);
		
		JLabel lblNewLabel_8 = new JLabel("Tên chương trình:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(735, 139, 131, 26);
		add(lblNewLabel_8);
		
		txtTKTGG = new JTextField();
		txtTKTGG.setColumns(10);
		txtTKTGG.setBounds(862, 142, 98, 25);
		add(txtTKTGG);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setForeground(new Color(0, 0, 0));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTimKiem.setBackground(new Color(252, 250, 205));
		btnTimKiem.setBounds(702, 178, 107, 30);
		add(btnTimKiem);
		
		JLabel lblNewLabel = new JLabel("CHƯƠNG TRÌNH KHUYẾN MÃI");
		lblNewLabel.setForeground(new Color(252, 151, 50));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(309, 0, 347, 39);
		add(lblNewLabel);
		giamGiaBUS = new GiamGiaBUS();
		
		
		loadDataToTable();

		loadDataToTableCTCTKM();

		btnReset.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Xóa dữ liệu trong các trường nhập liệu
		        txtMGG.setText("");
		        txtTGG.setText("");
		        NgayBD.setDate(null);
		        NgayKT.setDate(null);
		        txtTKMGG.setText("");
		        txtTKTGG.setText("");
		        NgayBD1.setDate(null);
		        NgayKT1.setDate(null);
		        
		        resetTable(); 
		    }
		});
		// Thêm sự kiện cho nút Thêm
		btnThem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int ma = Integer.parseInt(txtMGG.getText());
		        String ten = txtTGG.getText();
		        java.sql.Date NgayBDValue = new java.sql.Date(NgayBD.getDate().getTime());
		        java.sql.Date ngayKTValue = new java.sql.Date(NgayKT.getDate().getTime());

		        // Kiểm tra tính hợp lệ của dữ liệu nhập vào
		        if (validateInput(ma, ten, NgayBDValue, ngayKTValue)) {
		            GiamGia giamGia = new GiamGia(ma, ten, NgayBDValue, ngayKTValue);
		            boolean isSuccess = giamGiaBUS.themGiamGia(giamGia);

		            if (isSuccess) {
		                loadDataToTable();
		                JOptionPane.showMessageDialog(null, "Thêm chương trình giảm giá thành công!");
		            } else {
		                JOptionPane.showMessageDialog(null, "Thêm chương trình giảm giá thất bại!");
		            }
		        }
		    }
		});

		// Thêm sự kiện cho nút Xóa
		btnXoa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int ma = Integer.parseInt(txtMGG.getText());
		        boolean isSuccess = giamGiaBUS.xoaGiamGia(ma);

		        if (isSuccess) {
		            loadDataToTable();
		            JOptionPane.showMessageDialog(null, "Xóa chương trình giảm giá thành công!");
		        } else {
		            JOptionPane.showMessageDialog(null, "Xóa chương trình giảm giá thất bại!");
		        }
		    }
		});

		// Thêm sự kiện cho nút Sửa
		btnSua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int ma = Integer.parseInt(txtMGG.getText());
		        String ten = txtTGG.getText();
		        java.sql.Date NgayBDValue = new java.sql.Date(NgayBD.getDate().getTime());
		        java.sql.Date ngayKTValue = new java.sql.Date(NgayKT.getDate().getTime());

		        // Kiểm tra tính hợp lệ của dữ liệu nhập vào
		        if (validateInput(ma, ten, NgayBDValue, ngayKTValue)) {
		            GiamGia giamGia = new GiamGia(ma, ten, NgayBDValue, ngayKTValue);
		            boolean isSuccess = giamGiaBUS.capNhatGiamGia(giamGia);

		            if (isSuccess) {
		                loadDataToTable();
		                JOptionPane.showMessageDialog(null, "Cập nhật chương trình giảm giá thành công!");
		            } else {
		                JOptionPane.showMessageDialog(null, "Cập nhật chương trình giảm giá thất bại!");
		            }
		        }
		    }
		});
		
		// Thêm sự kiện cho nút Tìm kiếm
		btnTimKiem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Lấy giá trị từ các trường nhập liệu
		        Integer ma = null;
		        String ten = txtTKTGG.getText().trim();
		        Date ngayBD = null;
		        Date ngayKT = null;
		        
		        try {
		            if (!txtTKMGG.getText().isEmpty()) {
		                ma = Integer.parseInt(txtTKMGG.getText().trim());
		            }
		            if (NgayBD1.getDate() != null) {
		                ngayBD = new java.sql.Date(NgayBD1.getDate().getTime());
		            }
		            if (NgayKT1.getDate() != null) {
		                ngayKT = new java.sql.Date(NgayKT1.getDate().getTime());
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã giảm giá là số nguyên!");
		            return;
		        }
		        
		        // Thêm null check cho biến ma
		        if (ma != null) {
		            // Gọi phương thức tìm kiếm từ lớp BUS
		        	ArrayList<GiamGia> result = giamGiaBUS.timKiemGiamGia(ma.intValue(), ten, ngayBD, ngayKT);
		            
		            // Hiển thị kết quả tìm kiếm trên bảng
		            displaySearchResult(result);
		            
		            // Thêm mã chương trình vào hàm timKiemTheoMaChuongTrinh
		            List<ChiTietCTKM> result1 = ChiTietCTKMBUS.timKiemTheoMaChuongTrinh(ma.intValue());
		            displaySearchResulttableCTMGG(result1);
		        } else {
		            // Nếu ma là null, không tìm kiếm theo mã
		            ArrayList<GiamGia> result = giamGiaBUS.timKiemGiamGia(null, ten, ngayBD, ngayKT);
		            
		            // Hiển thị kết quả tìm kiếm trên bảng
		            displaySearchResult(result);
		        }
		    }
		});

		
	
		// Thêm sự kiện cho nút Thêm trong panel_2
		
		btnThem1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Lấy thông tin từ các trường nhập liệu
		            String maCTCTKM = txtMCTGG.getText();
		            int maChuongTrinh = Integer.parseInt(txtMGG2.getText());
		            int phanTramKM = Integer.parseInt(txtPTKM.getText());
		            int maSach = Integer.parseInt(txtMSGG.getText());

		            // Tạo đối tượng ChiTietCTKM từ thông tin vừa lấy
		            ChiTietCTKM chiTiet = new ChiTietCTKM(maCTCTKM, maChuongTrinh, phanTramKM, maSach);

		            // Khởi tạo đối tượng ChiTietCTKMBUS
		            ChiTietCTKMBUS chiTietCTKMBUS = new ChiTietCTKMBUS();

		            // Gọi phương thức thêm chi tiết chương trình giảm giá từ BUS
		            boolean isSuccess = chiTietCTKMBUS.themChiTietCTKM(chiTiet);

		            // Xử lý kết quả thêm
		            if (isSuccess) {
		            	
		                loadDataToTableCTCTKM();
		                JOptionPane.showMessageDialog(null, "Thêm chi tiết chương trình giảm giá thành công!");
		            } else {
		                JOptionPane.showMessageDialog(null, "Thêm chi tiết chương trình giảm giá thất bại!");
		            }
		        } catch (NumberFormatException ex) {
		            // Xử lý khi người dùng nhập vào không phải số
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên vào các trường!");
		        }
		    }
		});

		// Thêm sự kiện cho nút Sửa trong panel_2
		btnSua1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Lấy thông tin từ các trường nhập liệu
		            String maCTCTKM = txtMCTGG.getText();
		            int maChuongTrinh = Integer.parseInt(txtMGG2.getText());
		            int phanTramGiamGia = Integer.parseInt(txtPTKM.getText());
		            int maSach = Integer.parseInt(txtMSGG.getText());

		            // Tạo đối tượng ChiTietCTKM từ thông tin vừa lấy
		            ChiTietCTKM chiTiet = new ChiTietCTKM(maCTCTKM, maChuongTrinh, phanTramGiamGia, maSach);

		            // Khởi tạo đối tượng ChiTietCTKMBUS
		            ChiTietCTKMBUS chiTietCTKMBUS = new ChiTietCTKMBUS();

		            // Gọi phương thức sửa chi tiết chương trình giảm giá từ BUS
		            boolean isSuccess = chiTietCTKMBUS.suaChiTietCTKM(chiTiet);

		            // Xử lý kết quả sửa
		            if (isSuccess) {
		                
						loadDataToTableCTCTKM();
						JOptionPane.showMessageDialog(null, "Sửa chi tiết chương trình giảm giá thành công!");
		            } else {
		                JOptionPane.showMessageDialog(null, "Sửa chi tiết chương trình giảm giá thất bại!");
		            }
		        } catch (NumberFormatException ex) {
		            // Xử lý khi người dùng nhập vào không phải số
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên vào các trường!");
		        }
		    }
		});


		// Thêm sự kiện cho nút Xóa
		btnXoa1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String maCTCTKM = txtMCTGG.getText();
		        boolean isSuccess = ChiTietCTKMBUS.xoaChiTietCTKM(maCTCTKM);

		        if (isSuccess) {
					loadDataToTableCTCTKM();
					JOptionPane.showMessageDialog(null, "Xóa chi tiết chương trình giảm giá thành công!");
		        } else {
		            JOptionPane.showMessageDialog(null, "Xóa chi tiết chương trình giảm giá thất bại!");
		        }
		    }
		});
		
	}

	// Phương thức hiển thị kết quả tìm kiếm lên bảng
			private void displaySearchResult(ArrayList<GiamGia> result) {
			    DefaultTableModel model = (DefaultTableModel) table.getModel();
			    model.setRowCount(0); // Xóa dữ liệu cũ

			    for (GiamGia giamGia : result) {
			        model.addRow(new Object[] {
			            giamGia.getMa(),
			            giamGia.getTen(),
			            giamGia.getNgayBD(),
			            giamGia.getNgayKT()
			        });
			    }
			}

			private void displaySearchResulttableCTMGG(List<ChiTietCTKM> result1) {
			    DefaultTableModel model = (DefaultTableModel) tableCTMGG.getModel();
			    model.setRowCount(0); // Xóa dữ liệu cũ
			    
			    for (ChiTietCTKM chiTiet : result1) {
			        Object[] rowData = {
			            chiTiet.getMaCTCTKM(),
			            chiTiet.getMaChuongTrinh(),
			            chiTiet.getPhanTramKM(),
			            chiTiet.getMaSach()
			        };
			        model.addRow(rowData);
			    }
			}

			private void loadDataToTable() {
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.setRowCount(0);
		        ArrayList<GiamGia> list = giamGiaBUS.getListGiamGia();
		        for (GiamGia giamGia : list) {
		            model.addRow(new Object[] { giamGia.getMa(), giamGia.getTen(), giamGia.getNgayBD(), giamGia.getNgayKT() });
		        }
		    
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // Get the data from the selected row
                    int ma = (int) table.getValueAt(selectedRow, 0);
                    String ten = (String) table.getValueAt(selectedRow, 1);
                    Date ngayBD = (Date) table.getValueAt(selectedRow, 2);
                    Date ngayKT = (Date) table.getValueAt(selectedRow, 3);

                    // Populate the input fields with the data from the selected row
                    txtMGG.setText(String.valueOf(ma));
                    txtTGG.setText(ten);
                    NgayBD.setDate(ngayBD);
                    NgayKT.setDate(ngayKT);
                }
            }
        });
        
        tableCTMGG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableCTMGG.getSelectedRow();
                if (selectedRow >= 0) {
                	// Lấy dữ liệu từ hàng đã chọn
                    String MaCTCTKM = (String) tableCTMGG.getValueAt(selectedRow, 0);
                    int MaChuongTrinh = (int) tableCTMGG.getValueAt(selectedRow, 1);
                    int PhanTramKm = (int) tableCTMGG.getValueAt(selectedRow, 2);
                    int MaSach = (int) tableCTMGG.getValueAt(selectedRow, 3);

                    // Điền vào các trường đầu vào với dữ liệu từ hàng đã chọn
                    txtMCTGG.setText(String.valueOf(MaCTCTKM));
                    txtMGG2.setText(String.valueOf(MaChuongTrinh));
                    txtPTKM.setText(String.valueOf(PhanTramKm));
                    txtMSGG.setText(String.valueOf(MaSach));
                    
                }
            }
        });
    }    
    public static boolean isValidDateRange(Date startDate, Date endDate) {
        return endDate.after(startDate);
    }
    public static boolean isValidTenChuongTrinh(String tenChuongTrinh) {
        if (tenChuongTrinh.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên chương trình giảm giá");
            return false;
        }
        return true;
    }

    public static boolean isValidNgayBDAndNgayKT(Date ngayBD, Date ngayKT) {
        if (ngayBD == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày bắt đầu của chương trình giảm giá");
            return false;
        }
        if (ngayKT == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày kết thúc của chương trình giảm giá");
            return false;
        }
        if (!isValidDateRange(ngayBD, ngayKT)) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu!");
            return false;
        }
        return true;
    }

    private boolean validateInput(int ma, String ten, Date NgayBDValue, Date ngayKTValue) {
        boolean valid = true;
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên chương trình giảm giá");
            valid = false;
        }
        if (NgayBDValue != null && ngayKTValue != null) {
            if (!isValidDateRange(NgayBDValue, ngayKTValue)) {
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu!");
                valid = false;
            }
        } else if (NgayBDValue == null && ngayKTValue != null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày bắt đầu của chương trình giảm giá");
            valid = false;
        } else if (NgayBDValue != null && ngayKTValue == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày kết thúc chương trình giảm giá");
            valid = false;
        }
        return valid;
    }
    private void resetTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa dữ liệu trong bảng
        loadDataToTable(); // Load lại dữ liệu vào bảng từ đầu
        loadDataToTableCTCTKM();
    }

    private void loadDataToTableCTCTKM() {
        DefaultTableModel model = (DefaultTableModel) tableCTMGG.getModel();
        model.setRowCount(0); // Clear old data
        List<ChiTietCTKM> list = ChiTietCTKMBUS.getListChiTietCTKM();

        // Load new data into the table
        for (ChiTietCTKM chiTiet : list) {
            model.addRow(new Object[] { 
                chiTiet.getMaCTCTKM(), 
                chiTiet.getMaChuongTrinh(), 
                chiTiet.getPhanTramKM(), 
                chiTiet.getMaSach()
            });
        }
    }

    
}
