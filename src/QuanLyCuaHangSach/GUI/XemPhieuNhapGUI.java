package QuanLyCuaHangSach.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import QuanLyCuaHangSach.BUS.CTPhieuNhapBUS;
import QuanLyCuaHangSach.BUS.PhieuNhapBUS;
import QuanLyCuaHangSach.DAO.CTPhieuNhapDAO;
import QuanLyCuaHangSach.DTO.CTPhieuNhapDTO;
import QuanLyCuaHangSach.DTO.PhieuNhapDTO;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class XemPhieuNhapGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTable getTblPN() {
        return tblPN;
    }
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XemPhieuNhapGUI frame = new XemPhieuNhapGUI();
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
	public XemPhieuNhapGUI() {
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Xem Phiếu Nhập");
		lblNewLabel.setForeground(new Color(250, 170, 50));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(425, 10, 265, 30);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Phi\u1EBFu nh\u1EADp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(251, 125, 0)));
		panel.setBounds(10, 67, 419, 549);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(50, 26, 71, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã NCC:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(50, 57, 71, 21);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Mã NV:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(50, 88, 71, 21);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Ngày nhập :");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_2.setBounds(50, 119, 86, 21);
		panel.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Tổng tiền:");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1_3.setBounds(50, 150, 71, 21);
		panel.add(lblNewLabel_1_1_3);
		
		txtMa = new JTextField();
		txtMa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMa.setBounds(158, 26, 149, 19);
		panel.add(txtMa);
		txtMa.setColumns(10);
		txtMa.setEditable(false);
		
		txtMaNCC = new JTextField();
		txtMaNCC.setEditable(false);
		txtMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaNCC.setColumns(10);
		txtMaNCC.setBounds(158, 56, 149, 21);
		panel.add(txtMaNCC);
		
		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(158, 87, 149, 21);
		panel.add(txtMaNV);
		
		txtNgayNhap = new JTextField();
		txtNgayNhap.setEditable(false);
		txtNgayNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNgayNhap.setColumns(10);
		txtNgayNhap.setBounds(158, 118, 149, 21);
		panel.add(txtNgayNhap);
		
		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(158, 149, 149, 21);
		panel.add(txtTongTien);
		
		JScrollPane jscrollPane = new JScrollPane();
		jscrollPane.setBounds(10, 361, 399, 165);
		panel.add(jscrollPane);
		
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
		 
		 JPanel panel_2 = new JPanel();
		 panel_2.setBorder(new TitledBorder(null, "T\u00CCM KI\u1EBEM", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 102, 28)));
		 panel_2.setBackground(new Color(242, 255, 242));
		 panel_2.setBounds(10, 228, 387, 111);
		 panel.add(panel_2);
		 panel_2.setLayout(null);
		 
		  dateTuNgay = new JDateChooser();
		 dateTuNgay.setBounds(67, 34, 98, 25);
		 panel_2.add(dateTuNgay);
		 
		 JLabel lblNewLabel_1_3 = new JLabel("Từ ngày:");
		 lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		 lblNewLabel_1_3.setBounds(10, 34, 71, 21);
		 panel_2.add(lblNewLabel_1_3);
		 
		 JLabel lblNewLabel_1_3_1 = new JLabel("Đến ngày:");
		 lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		 lblNewLabel_1_3_1.setBounds(199, 34, 71, 21);
		 panel_2.add(lblNewLabel_1_3_1);
		 
		  dateDenNgay = new JDateChooser();
		 dateDenNgay.setBounds(268, 34, 98, 25);
		 panel_2.add(dateDenNgay);
		 
		 JButton btnTimKiemTheoNgay = new JButton("Tìm");
		 btnTimKiemTheoNgay.setBackground(new Color(251, 189, 138));
		 btnTimKiemTheoNgay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 btnTimKiemTheoNgay.setIcon(new ImageIcon(XemPhieuNhapGUI.class.getResource("/ThongTinSV/Custom-Icon-Design-Flatastic-1-Search.24.png")));
		 btnTimKiemTheoNgay.setBounds(140, 80, 90, 21);
		 panel_2.add(btnTimKiemTheoNgay);
		 btnTimKiemTheoNgay.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	TimKiemTheoNgay(evt);
	            }
	        });
		 
		 JPanel panel_1 = new JPanel();
		 panel_1.setLayout(null);
		 panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chi ti\u1EBFt phi\u1EBFu nh\u1EADp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 156, 108)));
		 panel_1.setBackground(Color.WHITE);
		 panel_1.setBounds(439, 82, 612, 549);
		 contentPane.add(panel_1);
		 
		 JLabel lblNewLabel_1_2 = new JLabel("Mã CTPN");
		 lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		 lblNewLabel_1_2.setBounds(114, 26, 71, 21);
		 panel_1.add(lblNewLabel_1_2);
		 
		 JLabel lblNewLabel_1_1_4 = new JLabel("Mã phiếu nhập");
		 lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		 lblNewLabel_1_1_4.setBounds(114, 57, 98, 21);
		 panel_1.add(lblNewLabel_1_1_4);
		 
		 JLabel lblNewLabel_1_1_1_1 = new JLabel("Mã sách:");
		 lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		 lblNewLabel_1_1_1_1.setBounds(114, 88, 71, 21);
		 panel_1.add(lblNewLabel_1_1_1_1);
		 
		 JLabel lblNewLabel_1_1_2_1 = new JLabel("Đơn giá");
		 lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		 lblNewLabel_1_1_2_1.setBounds(114, 119, 86, 21);
		 panel_1.add(lblNewLabel_1_1_2_1);
		 
		 JLabel lblNewLabel_1_1_3_1 = new JLabel("Số lượng");
		 lblNewLabel_1_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		 lblNewLabel_1_1_3_1.setBounds(114, 150, 71, 21);
		 panel_1.add(lblNewLabel_1_1_3_1);
		 
		 txtMaCTPN = new JTextField();
		 txtMaCTPN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 txtMaCTPN.setEditable(false);
		 txtMaCTPN.setColumns(10);
		 txtMaCTPN.setBounds(239, 26, 149, 19);
		 panel_1.add(txtMaCTPN);
		 
		 txtMaPhieuNhap = new JTextField();
		 txtMaPhieuNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 txtMaPhieuNhap.setEditable(false);
		 txtMaPhieuNhap.setColumns(10);
		 txtMaPhieuNhap.setBounds(239, 56, 149, 21);
		 panel_1.add(txtMaPhieuNhap);
		 
		 txtMaSach = new JTextField();
		 txtMaSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 txtMaSach.setEditable(false);
		 txtMaSach.setColumns(10);
		 txtMaSach.setBounds(239, 87, 149, 21);
		 panel_1.add(txtMaSach);
		 
		 txtDonGia = new JTextField();
		 txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 txtDonGia.setEditable(false);
		 txtDonGia.setColumns(10);
		 txtDonGia.setBounds(239, 118, 149, 21);
		 panel_1.add(txtDonGia);
		 
		 txtSoLuong = new JTextField();
		 txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 txtSoLuong.setEditable(false);
		 txtSoLuong.setColumns(10);
		 txtSoLuong.setBounds(239, 149, 149, 21);
		 panel_1.add(txtSoLuong);
		 
		 
		 
		 JLabel lblNewLabel_1_1_3_1_1 = new JLabel("Thành tiền");
		 lblNewLabel_1_1_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		 lblNewLabel_1_1_3_1_1.setBounds(114, 181, 71, 21);
		 panel_1.add(lblNewLabel_1_1_3_1_1);
		 
		 txtThanhTien = new JTextField();
		 txtThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 txtThanhTien.setEditable(false);
		 txtThanhTien.setColumns(10);
		 txtThanhTien.setBounds(239, 180, 149, 21);
		 panel_1.add(txtThanhTien);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(32, 218, 558, 177);
		 panel_1.add(scrollPane);
		 
		 tblCTPN1 = new JTable();
		 tblCTPN1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 tblCTPN1.setRowHeight(20);
		 scrollPane.setViewportView(tblCTPN1);
		 tblCTPN1.setModel(new DefaultTableModel(
			        new Object[][] {
			            {null, null, null, null, null, null, null, null},
			            {null, null, null, null, null, null, null, null},
			            {null, null, null, null, null, null, null, null},
			            {null, null, null, null, null, null, null, null},
			            {null, null, null, null, null, null, null, null},
			            
			        },
			        new String[] {
			            "Mã CTPN", "Mã phiếu nhập", "Mã sách", "Số lượng", "Đơn giá ", "Thành tiền"
			        }
			    ));
		 tblCTPN1.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(java.awt.event.MouseEvent evt) {
		        	tblCTPNDSSMouseClicked(evt);
		        }
		    });
		
		 scrollPane.setViewportView(tblCTPN1);
		 
		 JPanel panel_3 = new JPanel();
		 panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00CCM KI\u1EBEM", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 145, 34)));
		 panel_3.setBackground(new Color(240, 255, 240));
		 panel_3.setBounds(103, 419, 427, 107);
		 panel_1.add(panel_3);
		 panel_3.setLayout(null);
		 
		 JLabel lblNewLabel_1_3_2 = new JLabel("Từ giá:");
		 lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		 lblNewLabel_1_3_2.setBounds(10, 32, 71, 21);
		 panel_3.add(lblNewLabel_1_3_2);
		 
		 txtTuGia = new JTextField();
		 txtTuGia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 txtTuGia.setColumns(10);
		 txtTuGia.setBounds(60, 34, 116, 21);
		 panel_3.add(txtTuGia);
		 
		 JLabel lblNewLabel_1_3_2_1 = new JLabel("Đến giá:");
		 lblNewLabel_1_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		 lblNewLabel_1_3_2_1.setBounds(207, 32, 71, 21);
		 panel_3.add(lblNewLabel_1_3_2_1);
		 
		 txtDenGia = new JTextField();
		 txtDenGia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 txtDenGia.setColumns(10);
		 txtDenGia.setBounds(279, 31, 116, 21);
		 panel_3.add(txtDenGia);
		 
		 JButton btnTKDonGia = new JButton("Tìm");
		 btnTKDonGia.setIcon(new ImageIcon(XemPhieuNhapGUI.class.getResource("/ThongTinSV/Custom-Icon-Design-Flatastic-1-Search.24.png")));
		 btnTKDonGia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 btnTKDonGia.setBackground(new Color(251, 189, 138));
		 btnTKDonGia.setBounds(181, 76, 91, 21);
		 panel_3.add(btnTKDonGia);
		 btnTKDonGia.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	TimKiemDonGia(evt);
	            }
	        });
		 
		 docPhieu();
		 docCTPhieu();
		 
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
		model = new DefaultTableModel (header,0);
		for (PhieuNhapDTO pn : PhieuNhapBUS.dsphieunhap)
		{
			Vector row = new Vector();
			row.add(pn.maPN);
			row.add(pn.maNV);
			row.add(pn.maNCC);
			row.add(pn.ngaylap);
			row.add(pn.tongtien);
			
			model.addRow(row);
		}
		tblPN.setModel(model);
		
	}
	
	private void tblPNDSSMouseClicked(java.awt.event.MouseEvent evt) {
	   int i = tblPN.getSelectedRow();
	   if (i>=0) {
		   txtMa.setText(tblPN.getModel().getValueAt(i, 0).toString());
		   txtMaNCC.setText(tblPN.getModel().getValueAt(i, 1).toString());
		   txtMaNV.setText(tblPN.getModel().getValueAt(i, 2).toString());
		   txtNgayNhap.setText(tblPN.getModel().getValueAt(i, 3).toString());
		   txtTongTien.setText(tblPN.getModel().getValueAt(i, 4).toString());
	   }
	}
	public void docCTPhieu() {
		CTPhieuNhapBUS bus = new CTPhieuNhapBUS();
		 if (CTPhieuNhapBUS.dsCTPN == null) 
			 bus.docCTPhieu(null);
		 Vector header = new Vector();
			header.add("Mã CTPN");
			header.add("Mã PN");
			header.add("Mã sách");
			header.add("Đơn giá");
			header.add("Số lượng");
			header.add("Thành tiền");
		model = new DefaultTableModel (header,0);
		for (CTPhieuNhapDTO ctpn : CTPhieuNhapBUS.dsCTPN)
		{
			Vector row = new Vector();
			row.add(ctpn.maCTPN);
			row.add(ctpn.maPhieuNhap);
			row.add(ctpn.maSach);
			row.add(ctpn.dongia);
			row.add(ctpn.soluong);
			row.add(ctpn.thanhtien);
			
			model.addRow(row);
		}
		tblCTPN1.setModel(model);
		
	}
	private void tblCTPNDSSMouseClicked(java.awt.event.MouseEvent evt) {
		   int i = tblCTPN1.getSelectedRow();
		   if (i>=0) {
			   txtMaCTPN.setText(tblCTPN1.getModel().getValueAt(i, 0).toString());
			   txtMaPhieuNhap.setText(tblCTPN1.getModel().getValueAt(i, 1).toString());
			   txtMaSach.setText(tblCTPN1.getModel().getValueAt(i, 2).toString());
			   txtDonGia.setText(tblCTPN1.getModel().getValueAt(i, 3).toString());
			   txtSoLuong.setText(tblCTPN1.getModel().getValueAt(i, 4).toString());
			   txtThanhTien.setText(tblCTPN1.getModel().getValueAt(i, 5).toString());




		   }
		}
	
	private void TimKiemDonGia(java.awt.event.ActionEvent evt) {
		String giathap = txtTuGia.getText();
		String giacao = txtDenGia.getText();
		CTPhieuNhapBUS bus = new CTPhieuNhapBUS();
		ArrayList<CTPhieuNhapDTO> ketqua = bus.TimkiemNhapTheoDG(giathap, giacao);
		if (ketqua!=null) {
			DefaultTableModel modelTB = new DefaultTableModel(); 
		    modelTB.setColumnIdentifiers(new Object[]{"Mã CTPN", "Mã PN", "Mã sách", "Số lượng", "Đơn giá", "Thành tiền"});
			for (CTPhieuNhapDTO ctpn : ketqua) {
				 Vector row = new Vector();
				 row.add(ctpn.maCTPN);
				 row.add(ctpn.maPhieuNhap);
				 row.add(ctpn.maSach);
				 row.add(ctpn.soluong);
				 row.add(ctpn.dongia);
				 row.add(ctpn.thanhtien);
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
	            for (CTPhieuNhapDTO chiTiet : chiTietList) {
	                chiTietModel.addRow(new Object[] { 
	                    chiTiet.getMaCTPN(),
	                    chiTiet.getMaPhieuNhap(),
	                    chiTiet.getMaSach(),
	                    chiTiet.getDongia(),   
	                    chiTiet.getSoluong(),
	                    chiTiet.getThanhtien()
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



	private JPanel contentPane;
	private JTextField txtMa;
	private JTextField txtMaNCC;
	private JTextField txtMaNV;
	private JTextField txtNgayNhap;
	private JTextField txtTongTien;
	private javax.swing.JTable tblPN;
	private javax.swing.JTable tblCTPN1;
	private JTextField txtMaCTPN;
	private JTextField txtMaPhieuNhap;
	private JTextField txtMaSach;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JTextField txtThanhTien;
	private JTextField txtTuGia;
	private JTextField txtDenGia;
	private JDateChooser dateTuNgay;
	private JDateChooser dateDenNgay;
}
