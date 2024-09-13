package QuanLyCuaHangSach.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import QuanLyCuaHangSach.BUS.NhaXuatBanBUS;
import QuanLyCuaHangSach.BUS.SachBUS;
import QuanLyCuaHangSach.BUS.TheLoaiBUS;
import QuanLyCuaHangSach.DAO.MySQLConnect;
import QuanLyCuaHangSach.DTO.NhaXuatBan;
import QuanLyCuaHangSach.DTO.Sach;
import QuanLyCuaHangSach.DTO.TheLoai;
import QuanLyCuaHangSach.GUI.SachGUI;

public class SachGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt5;
	private JTextField txt6;
	private JTextField txt7;
	private JPanel contentPane;
	private JLabel lbHinhAnh;
	private JTable table;
	private JComboBox<String> cbBLoai;
	private JComboBox<String> cbBLoai1;
	private JComboBox<String> cbBNXB;
	private static Connection connection = MySQLConnect.getConnection();
	private JTextField txtTu;
	private JTextField txtTK1;
	private JTextField txtTK2;
	private JTextField txtTK3;
	private JTextField txtDen;
	protected String maNXB;
	private JCheckBox ckb1;
	private JCheckBox ckb2;
	private JCheckBox ckb3;
	/**
	 * Launch the application.
	 */
	


	/**
	 * Create the frame.
	 */
	public SachGUI() {
		setBackground(new Color(255, 255, 255));
     
		setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00EAm Th\u00F4ng Tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(94, 228, 65)));
		panel1.setBackground(new Color(247, 247, 247));
		panel1.setBounds(10, 63, 792, 279);
		add(panel1);
		
		JLabel lb1 = new JLabel("Mã Sách : ");
		lb1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb1.setBounds(57, 23, 78, 27);
		panel1.add(lb1);
		
		txt1 = new JTextField();
		txt1.setFont(new Font("Calibri", Font.PLAIN, 15));
		txt1.setColumns(10);
		txt1.setBounds(166, 23, 184, 24);
		panel1.add(txt1);
		
		JLabel lb2 = new JLabel("Tên Sách :");
		lb2.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb2.setBounds(57, 55, 78, 27);
		panel1.add(lb2);
		
		JLabel lb3 = new JLabel("Mã Loại : ");
		lb3.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb3.setBounds(57, 92, 78, 27);
		panel1.add(lb3);
		
		JLabel lb4 = new JLabel("Mã NXB : ");
		lb4.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb4.setBounds(57, 125, 78, 27);
		panel1.add(lb4);
		
		JLabel lb5 = new JLabel("Tên Tác Giả : ");
		lb5.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb5.setBounds(57, 159, 99, 27);
		panel1.add(lb5);
		
		JLabel lb6 = new JLabel("Số Lượng :");
		lb6.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb6.setBounds(57, 193, 88, 27);
		panel1.add(lb6);
		
		JLabel lb7 = new JLabel("Đơn Giá :");
		lb7.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb7.setBounds(57, 227, 78, 27);
		panel1.add(lb7);

		
		JButton btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(SachGUI.class.getResource("/icon/Custom-Icon-Design-Flatastic-1-Add.16.png")));
		btnThem.setForeground(new Color(0, 0, 0));
		btnThem.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnThem.setBackground(new Color(253, 251, 217));
		btnThem.setBounds(384, 46, 113, 31);
		panel1.add(btnThem);

		
		btnThem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Kiểm tra xem người dùng đã chọn ảnh hay chưa
		            String imagePath = lbHinhAnh.getText();
		            if (imagePath.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Vui lòng chọn ảnh cho sách trước khi thêm.");
		                return;
		            }

		            // Lấy thông tin từ các trường nhập liệu
		            int maSach = Integer.parseInt(txt1.getText());
		            String tenSach = txt2.getText();
		            int maLoai = Integer.parseInt(String.valueOf(cbBLoai.getSelectedItem())); // Chuyển đổi Object thành String trước khi parse
		            int maNXB = Integer.parseInt(String.valueOf(cbBNXB.getSelectedItem())); // Chuyển đổi Object thành String trước khi parse
		            String tenTacGia = txt5.getText();
		            double donGia = Double.parseDouble(txt7.getText()); // Chuyển đổi đơn giá thành kiểu số thực
		            int soLuong = Integer.parseInt(txt6.getText()); // Chuyển đổi số lượng thành kiểu số nguyên

		            if (tenSach.isEmpty() || tenTacGia.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin sách và tác giả.");
		                return;  
		            }

		            if (soLuong <= 0 || donGia <= 0) {
		                JOptionPane.showMessageDialog(null, "Số lượng và đơn giá phải là số dương.");
		                return;  
		            }

		            // Tạo một đối tượng Sach từ dữ liệu người dùng nhập vào
		            Sach sach = new Sach( soLuong, tenSach, maLoai, soLuong, maNXB, tenTacGia, donGia, imagePath); 

		            // Gọi phương thức thêm sách từ lớp BUS
		            SachBUS sachBUS = new SachBUS();
		            boolean success = sachBUS.themSach(sach);

		            if (success) {
		                JOptionPane.showMessageDialog(null, "Thêm sách thành công");
		                refreshTable(); // Gọi phương thức làm mới bảng

		                // Cập nhật dữ liệu ảnh lên database
		                SachBUS.updateImagePathToDatabase(maSach, imagePath);

		                // Hiển thị hình ảnh lên JLabel
		                showImageOnLabel(imagePath);
		            } else {
		                JOptionPane.showMessageDialog(null, "Thêm sách thất bại");
		            }
		        } catch (NumberFormatException ex) {
		            // Xử lý nếu người dùng nhập không đúng định dạng số
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số cho số lượng và đơn giá.");
		        }
		    }
		});

			
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(SachGUI.class.getResource("/icon/Awicons-Vista-Artistic-Delete.24.png")));
		btnXoa.setForeground(new Color(0, 0, 0));
		btnXoa.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnXoa.setBackground(new Color(253, 251, 217));
		btnXoa.setBounds(384, 99, 113, 31);
		panel1.add(btnXoa);
		btnXoa.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String maSachStr = txt1.getText(); // Lấy mã sách từ JTextField

		        if (maSachStr.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sách cần xóa.");
		            return;
		        }

		        int maSach;
		        try {
		            maSach = Integer.parseInt(maSachStr); // Chuyển đổi mã sách từ String sang int
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Mã sách không hợp lệ.");
		            return;
		        }

		        SachBUS sachBUS = new SachBUS(); // Tạo thể hiện của SachBUS
		        boolean deleted = sachBUS.xoaSach(maSach); // Gọi phương thức xoaSach để xóa sách

		        if (deleted) {
		            JOptionPane.showMessageDialog(null, "Xóa thành công!");
		            loadDataToTable(); // Gọi phương thức loadDataToTable hiện có để load lại dữ liệu
		        } else {
		            JOptionPane.showMessageDialog(null, "Xóa không thành công!");
		        }
		    }
		});
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(SachGUI.class.getResource("/icon/Custom-Icon-Design-Pretty-Office-10-Pencil.24.png")));
		btnSua.setForeground(new Color(0, 0, 0));
		btnSua.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnSua.setBackground(new Color(253, 251, 217));
		btnSua.setBounds(384, 151, 113, 31);
		panel1.add(btnSua);
		
		btnSua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Lấy thông tin từ các trường nhập liệu
		            int maSach = Integer.parseInt(txt1.getText());
		            String tenSach = txt2.getText();
		            int maLoai = Integer.parseInt(String.valueOf(cbBLoai.getSelectedItem())); // Chuyển đổi Object thành String trước khi parse
		            int maNXB = Integer.parseInt(String.valueOf(cbBNXB.getSelectedItem())); // Chuyển đổi Object thành String trước khi parse
		            String tenTacGia = txt5.getText();
		            double donGia = Double.parseDouble(txt7.getText()); // Chuyển đổi đơn giá thành kiểu số thực
		            int soLuong = Integer.parseInt(txt6.getText()); // Chuyển đổi số lượng thành kiểu số nguyên
		            String imagePath = lbHinhAnh.getText(); // Lấy đường dẫn của hình ảnh từ label

		            if (tenSach.isEmpty() || tenTacGia.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin sách và tác giả.");
		                return;  
		            }

		            if (soLuong <= 0 || donGia <= 0) {
		                JOptionPane.showMessageDialog(null, "Số lượng và đơn giá phải là số dương.");
		                return;  
		            }

		            // Tạo một đối tượng Sach từ dữ liệu người dùng nhập vào
		            Sach sach = new Sach(maSach, tenSach, maLoai, soLuong, maNXB, tenTacGia, donGia, imagePath); 

		            // Gọi phương thức cập nhật sách từ lớp BUS
		            SachBUS sachBUS = new SachBUS();
		            boolean success = sachBUS.capNhatSach(sach);

		            if (success) {
		                JOptionPane.showMessageDialog(null, "Cập nhật sách thành công");
		                refreshTable(); // Gọi phương thức làm mới bảng
		            } else {
		                JOptionPane.showMessageDialog(null, "Cập nhật sách thất bại");
		            }
		        } catch (NumberFormatException ex) {
		            // Xử lý nếu người dùng nhập không đúng định dạng số
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số cho số lượng và đơn giá.");
		        }
		    }
		});
		
		txt2 = new JTextField();
		txt2.setFont(new Font("Calibri", Font.PLAIN, 15));
		txt2.setColumns(10);
		txt2.setBounds(166, 57, 184, 24);
		panel1.add(txt2);
		
		txt5 = new JTextField();
		txt5.setFont(new Font("Calibri", Font.PLAIN, 15));
		txt5.setColumns(10);
		txt5.setBounds(166, 159, 184, 24);
		panel1.add(txt5);
		
		txt6 = new JTextField();
		txt6.setFont(new Font("Calibri", Font.PLAIN, 15));
		txt6.setColumns(10);
		txt6.setBounds(166, 193, 184, 24);
		panel1.add(txt6);
		
		txt7 = new JTextField();
		txt7.setFont(new Font("Calibri", Font.PLAIN, 15));
		txt7.setColumns(10);
		txt7.setBounds(166, 226, 184, 24);
		panel1.add(txt7);
		
		JButton btnChonAnh = new JButton("Chọn Ảnh");
		btnChonAnh.setBounds(384, 204, 113, 33);
		panel1.add(btnChonAnh);
		btnChonAnh.setForeground(new Color(0, 0, 0));
		btnChonAnh.setFont(new Font("Arial", Font.BOLD, 16));
		btnChonAnh.setBackground(new Color(253, 251, 217));
		

		btnChonAnh.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Tạo đường dẫn đến thư mục image
		        File imageDir = new File("D:\\BH-Evy\\JavaProject\\DA1\\src\\image");

		        // Tạo một hộp thoại để chọn tệp hình ảnh
		        JFileChooser fileChooser = new JFileChooser(imageDir);

		        // Chỉ cho phép chọn các tệp có định dạng hình ảnh
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
		        fileChooser.setFileFilter(filter);

		        int result = fileChooser.showOpenDialog(null); // Hiển thị hộp thoại chọn tệp

		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            String imagePath = selectedFile.getName(); // Lấy tên tệp hình ảnh đã chọn

		            // Hiển thị hình ảnh đã chọn lên giao diện
		            showImageOnLabel(imagePath);

		            // Lưu tên tệp của hình ảnh vào biến imagePath
		            lbHinhAnh.setText(imagePath);
		        }
		    }
		});

		
		lbHinhAnh = new JLabel("");
		lbHinhAnh.setBounds(529, 10, 229, 259);
		panel1.add(lbHinhAnh);
		
		cbBLoai = new JComboBox<>();
		cbBLoai.setBounds(166, 90, 184, 25);
		panel1.add(cbBLoai);
		loadDataIntoComboBox(MySQLConnect.getConnection(), cbBLoai);


		
		cbBNXB = new JComboBox<>();
		cbBNXB.setBounds(166, 124, 184, 25);
		panel1.add(cbBNXB);
		loadDataIntoComboBox_maNhaXuatBan(MySQLConnect.getConnection(), cbBNXB);
		
		JPanel panel3 = new JPanel();
		panel3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng Tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(94, 228, 65)));
		panel3.setBackground(UIManager.getColor("CheckBox.light"));
		panel3.setBounds(10, 343, 792, 415);
		add(panel3);
		panel3.setLayout(null);
		
		JScrollPane tableTT1 = new JScrollPane();
		tableTT1.setBounds(10, 26, 772, 330);
		panel3.add(tableTT1);
		
		table = new JTable();
		tableTT1.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel(
			    new Object[][] {},
			    new String[] {
			        "Mã Sách", "Tên Sách", "Tác Giả", "Mã Loại", "Mã NXB", "Số Lượng", "Đơn Giá", "Hình Ảnh"
			    }
			);

			// Đặt model vào table
        table.setModel(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(80); // Mã Sách
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Tên Sách
        table.getColumnModel().getColumn(2).setPreferredWidth(120); // Tác Giả
        table.getColumnModel().getColumn(3).setPreferredWidth(80); // Mã Loại
        table.getColumnModel().getColumn(4).setPreferredWidth(80); // Mã NXB
        table.getColumnModel().getColumn(5).setPreferredWidth(80); // Số Lượng
        table.getColumnModel().getColumn(6).setPreferredWidth(80); // Đơn Giá

		table.setBackground(UIManager.getColor("Button.light"));
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Mã Sách
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Mã Loại
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Mã NXB
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);
		
		JButton btnNhapExcel = new JButton("Nhập Excel");
		btnNhapExcel.setIcon(new ImageIcon(SachGUI.class.getResource("/icon/Blackvariant-Button-Ui-Ms-Office-2016-Excel-2.16.png")));
		btnNhapExcel.setForeground(new Color(0, 0, 0));
		btnNhapExcel.setFont(new Font("Arial", Font.BOLD, 14));
		btnNhapExcel.setBackground(new Color(253, 251, 217));
		btnNhapExcel.setBounds(434, 366, 146, 33);
		panel3.add(btnNhapExcel);
		btnNhapExcel.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            JFileChooser jFileChooser = new JFileChooser();
	            int returnVal = jFileChooser.showSaveDialog(contentPane);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = jFileChooser.getSelectedFile();
	                
	                int confirmResult = JOptionPane.showConfirmDialog(contentPane, "Bạn chắc chắn muốn nhập từ tệp Excel?", "Xác nhận nhập Excel", JOptionPane.YES_NO_OPTION);
	                if (confirmResult == JOptionPane.YES_OPTION) {
	                    NhapXuatExcel.readExcelToTable(file, table);
	                }
	            }
	        }
	    });
		
		JButton btnXuatExcel = new JButton("Xuất Excel");
		btnXuatExcel.setIcon(new ImageIcon(SachGUI.class.getResource("/icon/Blackvariant-Button-Ui-Ms-Office-2016-Excel-2.16.png")));
		btnXuatExcel.setForeground(new Color(0, 0, 0));
		btnXuatExcel.setFont(new Font("Arial", Font.BOLD, 14));
		btnXuatExcel.setBackground(new Color(253, 251, 217));
		btnXuatExcel.setBounds(612, 366, 135, 33);
		panel3.add(btnXuatExcel);
		btnXuatExcel.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser jFileChooser = new JFileChooser();
	                int returnVal = jFileChooser.showSaveDialog(contentPane);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    File file = jFileChooser.getSelectedFile();
	                    String filePath = file.getAbsolutePath();
	                    // Gọi hàm xuất dữ liệu ra file Excel
	                    NhapXuatExcel.exportDataToExcel(table, filePath, "Quản lí sách");
	                    // Hiển thị thông báo sau khi xuất dữ liệu
	                    JOptionPane.showMessageDialog(contentPane, "Xuất dữ liệu ra file Excel thành công!");
	                }
	            }
	        });
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1052, 53);
		add(panel);
		panel.setLayout(null);
		
		JLabel lb = new JLabel("QUẢN LÝ SÁCH");
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setBounds(414, 10, 198, 35);
		lb.setForeground(new Color(252, 151, 50));
		lb.setFont(new Font("Calibri", Font.BOLD, 27));
		panel.add(lb);
		
		JButton btnReset = new JButton("");
		btnReset.setIcon(new ImageIcon(SachGUI.class.getResource("/icon/reset.png")));
		btnReset.setBackground(Color.GREEN);
		btnReset.setForeground(SystemColor.control);
		btnReset.setBounds(622, 17, 31, 28);
		panel.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Reset các trường JTextField và JComboBox
		        txtTK1.setText("");
		        txtTK2.setText("");
		        txtTK3.setText(""); 
		        cbBLoai1.setSelectedIndex(0); // Chọn mục đầu tiên trong combobox
		        txtTu.setText("");
		        txtDen.setText("");
		        ckb1.setSelected(false);
		        ckb2.setSelected(false);
		        ckb3.setSelected(false);

		        // Refresh lại table
		        refreshTable();
		    }
		});
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(801, 63, 251, 366);
		add(panel2);
		panel2.setLayout(null);
		panel2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm Ki\u1EBFm ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(252, 151, 50)));
		panel2.setBackground(SystemColor.controlHighlight);
		
		txtTu = new JTextField();
		txtTu.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtTu.setColumns(10);
		txtTu.setBounds(111, 223, 105, 24);
		panel2.add(txtTu);
		
		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setIcon(new ImageIcon(SachGUI.class.getResource("/icon/Graphicloads-Colorful-Long-Shadow-Zoom.16.png")));
		btnTimKiem.setForeground(new Color(0, 0, 0));
		btnTimKiem.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnTimKiem.setBackground(new Color(253, 251, 217));
		btnTimKiem.setBounds(49, 311, 141, 31);
		panel2.add(btnTimKiem);
		

		btnTimKiem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy các giá trị từ các trường nhập liệu
		        Integer maSach = null;
		        if (!txtTK1.getText().isEmpty()) {
		            try {
		                maSach = Integer.parseInt(txtTK1.getText());
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Mã sách phải là số nguyên hợp lệ.");
		                return;
		            }
		        }

		        String tenSach = txtTK2.getText();
		        Integer maLoai = null;
		        if (cbBLoai1.getSelectedItem() != null && !cbBLoai1.getSelectedItem().toString().isEmpty()) {
		            try {
		            	maLoai = Integer.parseInt(cbBLoai1.getSelectedItem().toString());
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Mã loại phải là số nguyên hợp lệ.");
		                return;
		            }
		        }

		        String tenTacGia = txtTK3.getText();
		        String donGiaTuString = txtTu.getText().trim();
		        String donGiaDenString = txtDen.getText().trim();
		        
		        // Kiểm tra nếu giá từ rỗng, thì lấy giá trị min
		        double minDonGia = Double.MIN_VALUE;
		        if (!donGiaTuString.isEmpty()) {
		            try {
		                minDonGia = Double.parseDouble(donGiaTuString);
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Đơn giá từ phải là số hợp lệ.");
		                return;
		            }
		        }

		        // Kiểm tra nếu giá đến rỗng, thì lấy giá trị max
		        double maxDonGia = Double.MAX_VALUE;
		        if (!donGiaDenString.isEmpty()) {
		            try {
		                maxDonGia = Double.parseDouble(donGiaDenString);
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Đơn giá đến phải là số hợp lệ.");
		                return;
		            }
		        }

		        // Kiểm tra nếu giá từ lớn hơn giá đến
		        if (minDonGia > maxDonGia) {
		            JOptionPane.showMessageDialog(null, "Giá từ không được lớn hơn giá đến.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Gọi phương thức tìm kiếm trong lớp BUS
		        SachBUS sachBUS = new SachBUS();
		        List<Sach> dsSach = sachBUS.timKiemSach(maSach, tenSach, maLoai, tenTacGia, minDonGia, maxDonGia);
		        
		        // Xử lý kết quả tìm kiếm (hiển thị danh sách sách tìm được)
		        // Giả sử bạn có một JTable để hiển thị kết quả
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.setRowCount(0);  // Xóa tất cả các hàng hiện tại

		        for (Sach sach : dsSach) {
		            model.addRow(new Object[]{
		                sach.getMaSach(),
		                sach.getTenSach(),
		                sach.getTenTacGia(),
		                sach.getMaLoai(),
		                sach.getMaNXB(),
		                sach.getSoLuong(),
		                sach.getDonGia(),
		                sach.getHinhAnh()
		            });
		        }
		    }
		});



		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setBounds(543, 38, 195, 198);
		panel2.add(lblNewLabel_2_1);
		
		JLabel lb1_1 = new JLabel("Mã Sách : ");
		lb1_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb1_1.setBounds(10, 23, 78, 27);
		panel2.add(lb1_1);
		
		txtTK1 = new JTextField();
		txtTK1.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtTK1.setColumns(10);
		txtTK1.setBounds(97, 23, 119, 24);
		panel2.add(txtTK1);
		
		JLabel lb2_1 = new JLabel("Tên Sách :");
		lb2_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb2_1.setBounds(10, 60, 78, 27);
		panel2.add(lb2_1);
		
		txtTK2 = new JTextField();
		txtTK2.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtTK2.setColumns(10);
		txtTK2.setBounds(97, 57, 119, 24);
		panel2.add(txtTK2);
		
		JLabel lb3_1 = new JLabel("Mã Loại : ");
		lb3_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb3_1.setBounds(10, 97, 78, 27);
		panel2.add(lb3_1);
		
		cbBLoai1 = new JComboBox<String>();
		cbBLoai1.setBounds(97, 99, 119, 25);
		panel2.add(cbBLoai1);
		loadDataIntoComboBox(MySQLConnect.getConnection(), cbBLoai1);
		
		JLabel lb5_1 = new JLabel("Tên Tác Giả : ");
		lb5_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb5_1.setBounds(10, 153, 99, 27);
		panel2.add(lb5_1);
		
		txtTK3 = new JTextField();
		txtTK3.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtTK3.setColumns(10);
		txtTK3.setBounds(111, 153, 105, 24);
		panel2.add(txtTK3);
		
		JLabel lb7_1 = new JLabel("Đơn Giá :");
		lb7_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb7_1.setBounds(10, 190, 78, 27);
		panel2.add(lb7_1);
		
		JLabel lb7_1_1 = new JLabel("Từ :");
		lb7_1_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb7_1_1.setBounds(49, 222, 39, 27);
		panel2.add(lb7_1_1);
		
		JLabel lb7_1_2 = new JLabel("Đến :");
		lb7_1_2.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb7_1_2.setBounds(49, 259, 52, 27);
		panel2.add(lb7_1_2);
		
		txtDen = new JTextField();
		txtDen.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDen.setColumns(10);
		txtDen.setBounds(111, 259, 105, 24);
		panel2.add(txtDen);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "S\u1EAFp X\u1EBFp", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(252, 151, 50)));
		panel_1.setBounds(801, 430, 251, 138);
		add(panel_1);
		panel_1.setLayout(null);
		
		JComboBox<String> cbBSapXep1 = new JComboBox<String>();
		cbBSapXep1.setBounds(22, 81, 197, 29);
		cbBSapXep1.addItem("Theo mã tên");
        cbBSapXep1.addItem("Theo số lượng");
        cbBSapXep1.addItem("Theo đơn giá");
		panel_1.add(cbBSapXep1);
		
		JLabel lb1_1_1 = new JLabel("Chọn : ");
		lb1_1_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb1_1_1.setBounds(10, 31, 78, 27);
		panel_1.add(lb1_1_1);
		
		JComboBox<String> cbBSapXep2 = new JComboBox<String>();
		cbBSapXep2.setBounds(77, 29, 141, 29);
		cbBSapXep2.addItem("Tăng dần");
        cbBSapXep2.addItem("Giảm dần");
		panel_1.add(cbBSapXep2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm Ki\u1EBFm N\u00E2ng Cao", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(252, 151, 50)));
		panel_2.setBounds(801, 568, 251, 180);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lb7_1_3 = new JLabel("Số Lượng :");
		lb7_1_3.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb7_1_3.setBounds(10, 25, 100, 27);
		panel_2.add(lb7_1_3);
		
		ckb1 = new JCheckBox("0 - 30");
		ckb1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckb1.setBounds(104, 25, 71, 23);
		panel_2.add(ckb1);
		
		ckb2 = new JCheckBox("30 - 60");
		ckb2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckb2.setBounds(104, 58, 93, 23);
		panel_2.add(ckb2);
		
		ckb3 = new JCheckBox("trên 60");
		ckb3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckb3.setBounds(104, 89, 93, 23);
		panel_2.add(ckb3);
		
		JButton btnTimKiemNC = new JButton("Tìm Kiếm");
		btnTimKiemNC.setIcon(new ImageIcon(SachGUI.class.getResource("/icon/Graphicloads-Colorful-Long-Shadow-Zoom.16.png")));
		btnTimKiemNC.setForeground(new Color(0, 0, 0));
		btnTimKiemNC.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnTimKiemNC.setBackground(new Color(253, 251, 217));
		btnTimKiemNC.setBounds(55, 126, 141, 31);
		panel_2.add(btnTimKiemNC);
		
		btnTimKiemNC.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Kiểm tra các checkbox đã được chọn
		        boolean selected1 = ckb1.isSelected();
		        boolean selected2 = ckb2.isSelected();
		        boolean selected3 = ckb3.isSelected();

		        // Tạo danh sách chứa các mốc số lượng được chọn
		        List<Integer> selectedLimits = new ArrayList<>();
		        if (selected1) selectedLimits.add(1); // 1 tương ứng với 0 - 30
		        if (selected2) selectedLimits.add(2); // 2 tương ứng với 30 - 60
		        if (selected3) selectedLimits.add(3); // 3 tương ứng với trên 60

		        // Gọi phương thức tìm kiếm sách nâng cao trong lớp BUS
		        SachBUS sachBUS = new SachBUS();
		        List<Sach> dsSach = sachBUS.timKiemSachTheoSoLuong(selectedLimits);

		        // Hiển thị kết quả tìm kiếm, ví dụ: bằng cách cập nhật JTable hoặc hiển thị thông qua giao diện người dùng
		        // Ví dụ:
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.setRowCount(0); // Xóa tất cả các hàng hiện tại

		        for (Sach sach : dsSach) {
		            model.addRow(new Object[]{
		                sach.getMaSach(),
		                sach.getTenSach(),
		                sach.getTenTacGia(),
		                sach.getMaLoai(),
		                sach.getMaNXB(),
		                sach.getSoLuong(),
		                sach.getDonGia(),
		                sach.getHinhAnh()
		            });
		        }
		    }
		});
		loadDataToTable();

		// Thêm sự kiện lắng nghe cho JComboBox thứ nhất (cbBSapXep1)
				cbBSapXep1.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        // Lấy lựa chọn từ JComboBox
				        int luaChonSapXep = cbBSapXep1.getSelectedIndex() + 1; // Index bắt đầu từ 0, nên cộng thêm 1
				        // Kiểm tra lựa chọn và thực hiện sắp xếp
				        switch (luaChonSapXep) {
				            case 1:
				            case 2:
				            case 3:
				                boolean tangDan = false; // Theo mặc định, sắp xếp giảm dần
				                if (cbBSapXep2.getSelectedIndex() == 0) {
				                    tangDan = true; // Nếu lựa chọn của JComboBox thứ hai là "Tăng dần", đặt biến tangDan thành true
				                }
				                ArrayList<Sach> danhSachSach = SachBUS.sapXepSach(luaChonSapXep, tangDan); // Sắp xếp sách
				                // Cập nhật lại dữ liệu trong bảng
				                updateTableData(danhSachSach);
				                break;
				            default:
				                break;
				        }
				    }
				});

				// Thêm sự kiện lắng nghe cho JComboBox thứ hai (cbBSapXep2)
				cbBSapXep2.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        // Lấy lựa chọn từ JComboBox
				        int luaChonSapXep = cbBSapXep2.getSelectedIndex() + 1; // Sử dụng cbBSapXep2.getSelectedIndex() thay vì cbBSapXep1.getSelectedIndex()
				        // Kiểm tra lựa chọn và thực hiện sắp xếp
				        switch (luaChonSapXep) {
				            case 1:
				            case 2:
				            case 3:
				                boolean tangDan = false; // Theo mặc định, sắp xếp giảm dần
				                if (cbBSapXep2.getSelectedIndex() == 0) {
				                    tangDan = true; // Nếu lựa chọn của JComboBox thứ hai là "Tăng dần", đặt biến tangDan thành true
				                }
				                ArrayList<Sach> danhSachSach = SachBUS.sapXepSach(luaChonSapXep, tangDan); // Sắp xếp sách
				                // Cập nhật lại dữ liệu trong bảng
				                updateTableData(danhSachSach);
				                break;
				            default:
				                break;
				        }
				    }
				});
	
	
//		 Thêm sự kiện lắng nghe cho bảng
				table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				    public void valueChanged(ListSelectionEvent event) {
				        if (!event.getValueIsAdjusting()) {
				            int selectedRow = table.getSelectedRow();
				            if (selectedRow != -1) {
				                DefaultTableModel model = (DefaultTableModel) table.getModel();
				                Integer maSach = (Integer) model.getValueAt(selectedRow, 0);
				                String tenSach = (String) model.getValueAt(selectedRow, 1);
				                String tenTacGia = (String) model.getValueAt(selectedRow, 2);
				                
				                cbBLoai.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
				                cbBNXB.setSelectedItem(model.getValueAt(selectedRow, 4).toString());

				                Integer soLuong = (Integer) model.getValueAt(selectedRow, 5);
				                Double donGia = (Double) model.getValueAt(selectedRow, 6); 

				                txt1.setText(String.valueOf(maSach));
				                txt2.setText(tenSach);
				                txt5.setText(tenTacGia);
				                txt6.setText(String.valueOf(soLuong));
				                txt7.setText(String.valueOf(donGia));

				                String imagePath = SachBUS.getImagePath(maSach);
				                showImageOnLabel(imagePath);
				            }
				        }
				    }
				});
	}
	


	
	
	public void refreshTable() {
	    SachBUS sachBUS = new SachBUS();
	    ArrayList<Sach> listSach = sachBUS.getListSach();

	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Mã Sách");
	    model.addColumn("Tên Sách");
	    model.addColumn("Tác Giả");
	    model.addColumn("Mã Loại");
	    model.addColumn("Mã NXB");
	    model.addColumn("Số Lượng");
	    model.addColumn("Đơn Giá");

	    for (Sach sach : listSach) {
	        Object[] row = new Object[7]; // Số cột là 7
	        row[0] = sach.getMaSach();
	        row[1] = sach.getTenSach();
	        row[2] = sach.getTenTacGia();
	        row[3] = sach.getMaLoai();
	        row[4] = sach.getMaNXB();
	        row[5] = sach.getSoLuong();
	        row[6] = sach.getDonGia();
	        model.addRow(row);
	    }

	    table.setModel(model);
	}

	private void updateTableData(ArrayList<Sach> danhSachSach) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0); // Xóa hết các dòng hiện có trong bảng

	    // Duyệt qua danh sách sách đã được sắp xếp và thêm dữ liệu vào bảng
	    for (Sach sach : danhSachSach) {
	        Object[] row = new Object[7]; // Số cột là 7
	        row[0] = sach.getMaSach();
	        row[1] = sach.getTenSach();
	        row[2] = sach.getTenTacGia();
	        row[3] = sach.getMaLoai();
	        row[4] = sach.getMaNXB();
	        row[5] = sach.getSoLuong();
	        row[6] = sach.getDonGia();
	        model.addRow(row); // Thêm hàng mới vào bảng
	    }
	}

	public void loadDataToTable() {
	    SachBUS sachBUS = new SachBUS();
	    ArrayList<Sach> listSach = sachBUS.getListSach();

	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Mã Sách");
	    model.addColumn("Tên Sách");
	    model.addColumn("Tác Giả");
	    model.addColumn("Mã Loại");
	    model.addColumn("Mã NXB");
	    model.addColumn("Số Lượng");
	    model.addColumn("Đơn Giá");

	    for (Sach sach : listSach) {
	        Object[] row = new Object[7]; // Số cột là 7
	        row[0] = sach.getMaSach();
	        row[1] = sach.getTenSach();
	        row[2] = sach.getTenTacGia();
	        row[3] = sach.getMaLoai();
	        row[4] = sach.getMaNXB();
	        row[5] = sach.getSoLuong();
	        row[6] = sach.getDonGia();
	        model.addRow(row);
	    }

	    table.setModel(model);
	}
	
	public static void loadDataIntoComboBox_maNhaXuatBan(Connection connection, JComboBox<String> cbBNXB) {
	    NhaXuatBanBUS nhaXuatBanBUS = new NhaXuatBanBUS(connection); 
	    ArrayList<NhaXuatBan> nxbList = nhaXuatBanBUS.layTatCaNhaXuatBan(); // Lấy danh sách nhà xuất bản từ cơ sở dữ liệu
	    for (NhaXuatBan nxb : nxbList) {
	        cbBNXB.addItem(String.valueOf(nxb.getMaNXB())); // Thêm mã nhà xuất bản vào combobox
	    }
	}
	
	public void loadDataIntoComboBox(Connection connection, JComboBox<String> cbBLoai) {
	    TheLoaiBUS theLoaiBUS = new TheLoaiBUS(connection);
	    ArrayList<TheLoai> danhSachTheLoai = theLoaiBUS.layTatCaTheLoai(); 
	    if (danhSachTheLoai != null) {
	        for (TheLoai theLoai : danhSachTheLoai) {
	            cbBLoai.addItem(String.valueOf(theLoai.getMaTheLoai())); // Chèn mã thể loại vào combobox
	        }
	    } else {    
	        cbBLoai.addItem("Không có dữ liệu");
	    }
	}

	    private void showImageOnLabel(String imagePath) {
	   	 String duongdan= "src\\image\\" ;
		 String linkdung = duongdan + imagePath;
		 ImageIcon icon = new ImageIcon(linkdung);
         Image image = icon.getImage();

         int newWidth = 229;  
         int newHeight = 259;  

         // Thay đổi kích thước của hình ảnh
         Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

         // Tạo một ImageIcon mới từ hình ảnh đã được thay đổi kích thước
         ImageIcon scaledIcon = new ImageIcon(scaledImage);

         // Đặt ImageIcon mới cho JLabel
         lbHinhAnh.setIcon(scaledIcon);
	}
}

