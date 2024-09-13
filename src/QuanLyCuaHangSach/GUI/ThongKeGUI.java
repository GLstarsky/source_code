package QuanLyCuaHangSach.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import QuanLyCuaHangSach.BUS.ThongKeBUS;

import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class ThongKeGUI extends JPanel {
    private JTextField txtSach;
    private JTextField txtNhanVien;
    private JTextField txtKhachHang;
    private JTextField txtDoanhThu;
    private javax.swing.JTable tblThongKe;
    private JDateChooser dateTuNgay;
    private JDateChooser dateDenNgay;

    public ThongKeGUI() {
    	setBackground(new Color(255, 255, 255));
        setLayout(null); // Đặt layout cho JPanel thành null hoặc sử dụng layout phù hợp với thiết kế của bạn

        JLabel lblNewLabel = new JLabel("THỐNG KÊ TỔNG QUÁT");
        lblNewLabel.setForeground(new Color(249, 135, 51));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblNewLabel.setBounds(361, 10, 335, 35);
        add(lblNewLabel);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(216, 197, 22));
		panel.setBounds(133, 55, 312, 130);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ThongKeGUI.class.getResource("/icon/Microsoft-Fluentui-Emoji-Mono-Books.96.png")));
		lblNewLabel_1.setBounds(10, 10, 113, 110);
		panel.add(lblNewLabel_1);
		
		txtSach = new JTextField();
		txtSach.setForeground(new Color(255, 255, 255));
		txtSach.setEditable(false);
		txtSach.setFont(new Font("Tahoma", Font.PLAIN, 56));
		txtSach.setBackground(new Color(216, 197, 22));
		txtSach.setBounds(140, 33, 148, 50);
		panel.add(txtSach);
		txtSach.setColumns(10);
		txtSach.setBorder(null);
		ThongKeBUS soluongSachBUS = new ThongKeBUS();
		int slSach = soluongSachBUS.getSoluongSach();
		txtSach.setText(String.valueOf(slSach));
		
		JLabel lblNewLabel_6 = new JLabel("Sách trong kho");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(140, 93, 148, 27);
		panel.add(lblNewLabel_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(38, 208, 19));
		panel_1.setBounds(553, 55, 312, 130);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setIcon(new ImageIcon(ThongKeGUI.class.getResource("/icon/Custom-Icon-Design-Silky-Line-User-Users-2-2.96.png")));
		lblNewLabel_2.setBounds(10, 10, 115, 110);
		panel_1.add(lblNewLabel_2);
		
		txtNhanVien = new JTextField();
		txtNhanVien.setForeground(new Color(255, 255, 255));
		txtNhanVien.setEditable(false);
		txtNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 56));
		txtNhanVien.setBackground(new Color(38, 208, 19));
		txtNhanVien.setColumns(10);
		txtNhanVien.setBounds(187, 29, 115, 49);
		panel_1.add(txtNhanVien);
		txtNhanVien.setBorder(null);
		ThongKeBUS soluongNVBUS = new ThongKeBUS();
		int sum = soluongNVBUS.getSoluongNV();
		txtNhanVien.setText(String.valueOf(sum));
		
		JLabel lblNewLabel_7 = new JLabel("Nhân viên");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(161, 88, 102, 32);
		panel_1.add(lblNewLabel_7);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(55, 174, 238));
		panel_2.setBounds(133, 214, 312, 130);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(ThongKeGUI.class.getResource("/icon/Custom-Icon-Design-Silky-Line-User-User.96.png")));
		lblNewLabel_3.setBounds(10, 8, 109, 110);
		panel_2.add(lblNewLabel_3);
		
		txtKhachHang = new JTextField();
		txtKhachHang.setForeground(new Color(255, 255, 255));
		txtKhachHang.setEditable(false);
		txtKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 56));
		txtKhachHang.setBackground(new Color(55, 174, 238));
		txtKhachHang.setColumns(10);
		txtKhachHang.setBounds(196, 33, 106, 46);
		panel_2.add(txtKhachHang);
		txtKhachHang.setBorder(null);
		ThongKeBUS soluongKH = new ThongKeBUS();
		int tong = soluongKH.getSoluongKH();
		txtKhachHang.setText(String.valueOf(tong));
		
		JLabel lblNewLabel_8 = new JLabel("Khách hàng");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_8.setBounds(156, 89, 123, 29);
		panel_2.add(lblNewLabel_8);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(238, 70, 113));
		panel_3.setBounds(553, 214, 312, 130);
		add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ThongKeGUI.class.getResource("/icon/Erix-Subyarko-Medical-Invoice-Bill-Payment-Billing-Hospital.96.png")));
		lblNewLabel_4.setBounds(1, 10, 114, 110);
		panel_3.add(lblNewLabel_4);
		
		txtDoanhThu = new JTextField();
		txtDoanhThu.setForeground(new Color(255, 255, 255));
		txtDoanhThu.setEditable(false);
		txtDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 39));
		txtDoanhThu.setBackground(new Color(238, 70, 113));
		txtDoanhThu.setColumns(10);
		txtDoanhThu.setBounds(129, 33, 173, 48);
		panel_3.add(txtDoanhThu);
		txtDoanhThu.setBorder(null);
		ThongKeBUS doanhthuBUS = new ThongKeBUS();
		int doanhthu = doanhthuBUS.getDoanhThu();
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		String doanhThuFormatted = decimalFormat.format(doanhthu);
		txtDoanhThu.setText(doanhThuFormatted);
		
		JLabel lblNewLabel_9 = new JLabel("Doanh thu");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_9.setBounds(148, 91, 143, 29);
		panel_3.add(lblNewLabel_9);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "TH\u1ED0NG K\u00CA TI\u1EC0N THEO NG\u00C0Y", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(239, 171, 31)));
		panel_4.setBackground(new Color(202, 231, 175));
		panel_4.setBounds(112, 376, 823, 252);
		add(panel_4);
		panel_4.setLayout(null);
		
		dateTuNgay = new JDateChooser();
		dateTuNgay.setBounds(260, 49, 109, 30);
		panel_4.add(dateTuNgay);
		
		dateDenNgay = new JDateChooser();
		dateDenNgay.setBounds(553, 49, 109, 30);
		panel_4.add(dateDenNgay);
		
		JLabel lblNewLabel_5 = new JLabel("Từ ngày:");
		lblNewLabel_5.setBounds(171, 49, 64, 30);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_4.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Đến ngày:");
		lblNewLabel_5_1.setBounds(463, 49, 70, 30);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_4.add(lblNewLabel_5_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(92, 120, 634, 69);
		panel_4.add(scrollPane);
		tblThongKe = new JTable();
		tblThongKe.setFont(new Font("Tahoma", Font.BOLD, 24));
		tblThongKe.setRowHeight(40);
		scrollPane.setViewportView(tblThongKe);
		tblThongKe.setModel(new DefaultTableModel(
			        new Object[][] {
			            {null, null, null},
			            
			            
			        },
			        new String[] {
			            "Từ ngày" , "Đến ngày" , "Doanh thu"
			        }
			    ));
		
		JButton btnThongKe = new JButton("Thống kê");
		btnThongKe.setIcon(new ImageIcon(ThongKeGUI.class.getResource("/icon/Pixelmixer-Basic-Statistics.32.png")));
		btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnThongKe.setBackground(new Color(255, 255, 255));
		btnThongKe.setBounds(357, 199, 119, 34);
		panel_4.add(btnThongKe);
		btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ThongKeDoanhThu(evt);
            }
        });
		
	}
	
	private void ThongKeDoanhThu(java.awt.event.ActionEvent evt) {
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date tungay = dateTuNgay.getDate();
	        java.util.Date denngay = dateDenNgay.getDate();
	        java.sql.Date sqlTungay = new java.sql.Date(tungay.getTime());
	        java.sql.Date sqlDenngay = new java.sql.Date(denngay.getTime());

	        ThongKeBUS doanhthuBUS = new ThongKeBUS();
	        int doanhthu = doanhthuBUS.getDoanhThuTheoNgay(sqlTungay, sqlDenngay);
	        DecimalFormat decimalFormat = new DecimalFormat("#,###");
			String doanhThuFormatted = decimalFormat.format(doanhthu);
	        DefaultTableModel model = (DefaultTableModel) tblThongKe.getModel();
	        model.setRowCount(0); 
	        model.addRow(new Object[]{dateFormat.format(tungay), dateFormat.format(denngay), doanhThuFormatted});
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}




}
