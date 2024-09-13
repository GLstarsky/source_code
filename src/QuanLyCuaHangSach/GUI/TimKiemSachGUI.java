package QuanLyCuaHangSach.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import QuanLyCuaHangSach.BUS.SachBUS;
import QuanLyCuaHangSach.BUS.TheLoaiBUS;
import QuanLyCuaHangSach.DAO.MySQLConnect;
import QuanLyCuaHangSach.DTO.Sach;
import QuanLyCuaHangSach.DTO.TheLoai;
import QuanLyCuaHangSach.GUI.TimKiemSachGUI;

public class TimKiemSachGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTu;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtTG;
	private JTextField txtDen;
	private static JTable table;
	private JComboBox<String> cbBLoai1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemSachGUI frame = new TimKiemSachGUI();
					frame.loadDataToTable();
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
	public TimKiemSachGUI() {
	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1030, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm Ki\u1EBFm ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel2.setBackground(SystemColor.controlHighlight);
		panel2.setBounds(10, 10, 1002, 143);
		contentPane.add(panel2);
		
		txtTu = new JTextField();
		txtTu.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtTu.setColumns(10);
		txtTu.setBounds(191, 86, 110, 31);
		panel2.add(txtTu);
		
		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setForeground(new Color(0, 0, 0));
		btnTimKiem.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnTimKiem.setBackground(new Color(255, 255, 202));
		btnTimKiem.setBounds(599, 86, 141, 31);
		panel2.add(btnTimKiem);
		btnTimKiem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy các giá trị từ các trường nhập liệu
		        Integer maSach = null;
		        if (!txtMa.getText().isEmpty()) {
		            try {
		                maSach = Integer.parseInt(txtMa.getText());
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Mã sách phải là số nguyên hợp lệ.");
		                return;
		            }
		        }

		        String tenSach = txtTen.getText();
		        Integer maLoai = null;
		        if (cbBLoai1.getSelectedItem() != null && !cbBLoai1.getSelectedItem().toString().isEmpty()) {
		            try {
		            	maLoai = Integer.parseInt(cbBLoai1.getSelectedItem().toString());
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Mã loại phải là số nguyên hợp lệ.");
		                return;
		            }
		        }

		        String tenTacGia = txtTG.getText();
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
		
		txtMa = new JTextField();
		txtMa.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtMa.setColumns(10);
		txtMa.setBounds(90, 19, 132, 31);
		panel2.add(txtMa);
		
		JLabel lb2_1 = new JLabel("Tên Sách :");
		lb2_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb2_1.setBounds(252, 26, 78, 27);
		panel2.add(lb2_1);
		
		txtTen = new JTextField();
		txtTen.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtTen.setColumns(10);
		txtTen.setBounds(339, 23, 126, 30);
		panel2.add(txtTen);
		
		JLabel lb3_1 = new JLabel("Mã Loại : ");
		lb3_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb3_1.setBounds(498, 23, 78, 27);
		panel2.add(lb3_1);
		
		cbBLoai1 = new JComboBox<String>();
		cbBLoai1.setBounds(582, 23, 126, 31);
		panel2.add(cbBLoai1);
		loadDataIntoComboBox(MySQLConnect.getConnection(), cbBLoai1);
		
		JLabel lb5_1 = new JLabel("Tên Tác Giả : ");
		lb5_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb5_1.setBounds(748, 23, 99, 27);
		panel2.add(lb5_1);
		
		txtTG = new JTextField();
		txtTG.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtTG.setColumns(10);
		txtTG.setBounds(849, 23, 126, 31);
		panel2.add(txtTG);
		
		JLabel lb7_1 = new JLabel("Đơn Giá :");
		lb7_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb7_1.setBounds(90, 60, 78, 27);
		panel2.add(lb7_1);
		
		JLabel lb7_1_1 = new JLabel("Từ :");
		lb7_1_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb7_1_1.setBounds(129, 92, 39, 27);
		panel2.add(lb7_1_1);
		
		JLabel lb7_1_2 = new JLabel("Đến :");
		lb7_1_2.setFont(new Font("Calibri", Font.PLAIN, 18));
		lb7_1_2.setBounds(340, 93, 52, 27);
		panel2.add(lb7_1_2);
		
		txtDen = new JTextField();
		txtDen.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDen.setColumns(10);
		txtDen.setBounds(402, 86, 110, 31);
		panel2.add(txtDen);
		
		
		
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(new Color(0, 0, 0));
		btnLamMoi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnLamMoi.setBackground(new Color(255, 255, 202));
		btnLamMoi.setBounds(789, 86, 141, 31);
		panel2.add(btnLamMoi);
		btnLamMoi.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Reset các trường JTextField và JComboBox
		        txtMa.setText("");
		        txtTen.setText("");
		        txtTG.setText(""); 
		        cbBLoai1.setSelectedIndex(0); // Chọn mục đầu tiên trong combobox
		        txtTu.setText("");
		        txtDen.setText("");


		        // Refresh lại table
		        refreshTable();
		    }
		});
		
		JScrollPane tableTK = new JScrollPane();
		tableTK.setBounds(10, 163, 999, 325);
		contentPane.add(tableTK);
		
		table = new JTable();
		tableTK.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 S\u00E1ch", "T\u00EAn S\u00E1ch", "T\u00E1c Gi\u1EA3", "M\u00E3 Lo\u1EA1i", "M\u00E3 NXB", "S\u1ED1 L\u01B0\u1EE3ng ", "\u0110\u01A1n Gi\u00E1"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(202);
		table.getColumnModel().getColumn(2).setPreferredWidth(125);
		table.getColumnModel().getColumn(3).setPreferredWidth(46);
		table.getColumnModel().getColumn(4).setPreferredWidth(52);
		table.getColumnModel().getColumn(5).setPreferredWidth(58);
		table.getColumnModel().getColumn(6).setPreferredWidth(94);
		
		loadDataToTable();
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

	    // Thêm sự kiện lắng nghe mới cho bảng
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent event) {
	            if (!event.getValueIsAdjusting()) {
	                int selectedRow = table.getSelectedRow();
	                if (selectedRow != -1) {
	                    DefaultTableModel model = (DefaultTableModel) table.getModel();
	                    Integer maSach = (Integer) model.getValueAt(selectedRow, 0);
	                    String tenSach = (String) model.getValueAt(selectedRow, 1);
	                    String tenTacGia = (String) model.getValueAt(selectedRow, 2);

	                    cbBLoai1.setSelectedItem(model.getValueAt(selectedRow, 3).toString());

	                    txtMa.setText(String.valueOf(maSach));
	                    txtTen.setText(tenSach);
	                    txtTG.setText(tenTacGia);
	                }
	            }
	        }
	    });
	}
}
