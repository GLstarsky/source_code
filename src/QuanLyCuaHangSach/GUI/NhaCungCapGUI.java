package QuanLyCuaHangSach.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import QuanLyCuaHangSach.BUS.NhaCungCapBUS;
import QuanLyCuaHangSach.DTO.NhaCungCapDTO;

public class NhaCungCapGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultTableModel model = new DefaultTableModel();
	private NhaCungCapDTO selectedNhaCungCap;

    public NhaCungCapDTO getSelectedNhaCungCap() {
        return selectedNhaCungCap;
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhaCungCapGUI frame = new NhaCungCapGUI();
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
	public NhaCungCapGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 392);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhà cung cấp");
		lblNewLabel.setForeground(new Color(0, 217, 54));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(180, 10, 235, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã NCC");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 56, 107, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên NCC");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(20, 105, 107, 28);
		contentPane.add(lblNewLabel_1_1);
		
		txtMa = new JTextField();
		txtMa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMa.setBounds(148, 57, 216, 28);
		contentPane.add(txtMa);
		txtMa.setColumns(10);
		
		txtTenNCC = new JTextField();
		txtTenNCC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenNCC.setColumns(10);
		txtTenNCC.setBounds(148, 105, 216, 28);
		contentPane.add(txtTenNCC);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 194, 431, 104);
		contentPane.add(scrollPane);
		tblNCC = new JTable();
		tblNCC.setModel(new DefaultTableModel(
		        new Object[][] {
		        	{null,null},
		        	{null,null},
		        	{null,null},
		            
		        },
		        new String[] {
		            "Mã NCC", "Tên nhà cung cấp"
		        }
		    ));
		tblNCC.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            tblNCCMouseClicked(evt);
	        }
	    });
		scrollPane.setViewportView(tblNCC);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(254, 210, 156));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBounds(65, 159, 88, 25);
		contentPane.add(btnThem);
		btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	ThemNCCMouseClicke(evt);
            }
        });
		btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnThemNCCActionPerformed(evt);
            }

			
        });
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(254, 210, 156));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBounds(192, 159, 88, 25);
		contentPane.add(btnXoa);
		btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	XoaNCC(evt);
            }

			
        });
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(254, 210, 156));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setBounds(321, 159, 88, 25);
		contentPane.add(btnSua);
		btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	suaNCC(evt);
            }

			
        });
		
		btnChon = new JButton("Chọn");
		btnChon.setBackground(new Color(255, 128, 64));
		btnChon.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChon.setBounds(192, 308, 77, 37);
		contentPane.add(btnChon);
		btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnChonActionPerformed(evt);
            }

			
        });
		
		docdsNCC();
	}


		private void ThemNCCMouseClicke (java.awt.event.MouseEvent evt) {
			NhaCungCapDTO ncc = new NhaCungCapDTO();
			ncc.ma = Integer.parseInt(txtMa.getText());
			ncc.tenNCC=txtTenNCC.getText();
			
			NhaCungCapBUS bus = new NhaCungCapBUS();
			bus.themNCC(ncc);
			Vector header = new Vector();
			header.add("Mã NCC");
			header.add("Tên NCC");
			if (model.getRowCount()==0)
				model = new DefaultTableModel (header,0);
			Vector row = new Vector();
			row.add(ncc.ma);
			row.add(ncc.tenNCC);
			model.addRow(row);
			tblNCC.setModel(model);
			
			
		}
		private void btnThemNCCActionPerformed(ActionEvent evt) {
			// TODO Auto-generated method stub
			
		}
		private void docdsNCC () {
			NhaCungCapBUS bus = new NhaCungCapBUS();
			if (NhaCungCapBUS.dsncc == null)
				bus.docNCC(null);
			Vector header = new Vector();
			header.add("Mã NCC");
			header.add("Tên NCC");
			model = new DefaultTableModel (header,0);
			for (NhaCungCapDTO ncc : NhaCungCapBUS.dsncc) {
				Vector row = new Vector();
				row.add(ncc.ma);
				row.add(ncc.tenNCC);
				model.addRow(row);
			}
			tblNCC.setModel(model);
			
		}
		private void tblNCCMouseClicked(java.awt.event.MouseEvent evt) {
	        int  i=tblNCC.getSelectedRow();
	        txtMa.setText(tblNCC.getModel().getValueAt(i, 0).toString());
	        txtTenNCC.setText(tblNCC.getModel().getValueAt(i, 1).toString());
	       
		}
		
		private void XoaNCC(java.awt.event.ActionEvent evt) {
			String ma = txtMa.getText();
			int i = tblNCC.getSelectedRow();
			if (i>=0) {
				NhaCungCapDTO ncc = new NhaCungCapDTO();
				ncc.setMa(Integer.parseInt(ma));
				NhaCungCapBUS bus = new NhaCungCapBUS();
				bus.xoaNCC(ncc);
				NhaCungCapBUS.dsncc.remove(i);
				model.removeRow(i);
				tblNCC.setModel(model);
			}
		}
		
		private void suaNCC (java.awt.event.ActionEvent evt) {
			int i = tblNCC.getSelectedRow();
			if (i>=0) {
				NhaCungCapDTO ncc = new NhaCungCapDTO();
				ncc.ma = Integer.parseInt(txtMa.getText());
				ncc.tenNCC = txtTenNCC.getText();
				NhaCungCapBUS bus = new NhaCungCapBUS();
				bus.suaNCC(ncc);
				model.setValueAt(ncc.ma, i, 0);
				model.setValueAt(ncc.tenNCC, i, 1);
				tblNCC.setModel(model);
			}
		}

		
		private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {
	        // Lấy thông tin của nhà cung cấp được chọn
	        int maNCC = Integer.parseInt(txtMa.getText());
	        String tenNCC = txtTenNCC.getText();

	        // Tạo đối tượng NhaCungCapDTO
	        selectedNhaCungCap = new NhaCungCapDTO();
	        selectedNhaCungCap.setMa(maNCC);
	        selectedNhaCungCap.setTenNCC(tenNCC);

	        // Đóng frame hiện tại
	        this.dispose();
	    }

		
	private javax.swing.JButton btnChon;
	private JTextField txtMa;
	private JTextField txtTenNCC;
	private javax.swing.JTable tblNCC;
}


