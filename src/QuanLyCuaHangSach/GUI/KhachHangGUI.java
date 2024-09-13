package QuanLyCuaHangSach.GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import QuanLyCuaHangSach.BUS.KhachHangBUS;
import QuanLyCuaHangSach.DTO.KhachHangDTO;

import javax.swing.ImageIcon;


public class KhachHangGUI extends JPanel {
    DefaultTableModel model;

    private static final long serialVersionUID = 1L;
    private JTextField txtLastName;
    private JTextField txtPhoneNumber;
    private JTextField txtAddress;
    private JTable table;
    private JTextField txtFirstName;
    private JTextField txtFind;
    private JTextField txtIdCustomer;
    private KhachHangBUS khachHangBUS = new KhachHangBUS();

    public KhachHangGUI() {
    	setBackground(new Color(255, 255, 255));
 

       setLayout(null);

        JLabel lblListCustomer = new JLabel("DANH SÁCH KHÁCH HÀNG");
        lblListCustomer.setForeground(new Color(94, 228, 65));
        lblListCustomer.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblListCustomer.setBounds(426, 297, 262, 49);
        add(lblListCustomer);

        JLabel lblLastName = new JLabel("Tên");
        lblLastName.setFont(new Font("Arial", Font.BOLD, 18));
        lblLastName.setBounds(88, 189, 87, 34);
        add(lblLastName);

        txtLastName = new JTextField();
        txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtLastName.setBounds(201, 191, 247, 32);
        add(txtLastName);
        txtLastName.setColumns(10);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtPhoneNumber.setColumns(10);
        txtPhoneNumber.setBounds(739, 206, 247, 34);
        add(txtPhoneNumber);

        JLabel lblAddress = new JLabel("Địa chỉ");
        lblAddress.setFont(new Font("Arial", Font.BOLD, 18));
        lblAddress.setBounds(88, 233, 97, 37);
        add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtAddress.setColumns(10);
        txtAddress.setBounds(201, 233, 247, 32);
        add(txtAddress);

        JButton btnAdd = new JButton("Thêm");
        btnAdd.setIcon(new ImageIcon(KhachHangGUI.class.getResource("/icon/Custom-Icon-Design-Flatastic-1-Add.16.png")));
        btnAdd.setBackground(new Color(252, 250, 205));
        btnAdd.setForeground(Color.BLACK);
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnAdd.setBounds(62, 92, 122, 28);
        add(btnAdd);

        JButton btnChange = new JButton("Sửa");
        btnChange.setIcon(new ImageIcon(KhachHangGUI.class.getResource("/icon/Custom-Icon-Design-Pretty-Office-10-Pencil.24.png")));
        btnChange.setBackground(new Color(252, 250, 205));
        btnChange.setForeground(Color.BLACK);
        btnChange.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnChange.setBounds(215, 92, 97, 28);
        add(btnChange);

        JButton btnDelete = new JButton("Xóa");
        btnDelete.setIcon(new ImageIcon(KhachHangGUI.class.getResource("/icon/Awicons-Vista-Artistic-Delete.24.png")));
        btnDelete.setBackground(new Color(252, 250, 205));
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnDelete.setBounds(344, 92, 104, 28);
        add(btnDelete);

        JButton btnFind = new JButton("");
        btnFind.setIcon(new ImageIcon(KhachHangGUI.class.getResource("/icon/Custom-Icon-Design-Flatastic-1-Search.24.png")));
        btnFind.setForeground(Color.BLACK);
        btnFind.setBackground(new Color(252, 250, 205));
        btnFind.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnFind.setBounds(993, 92, 47, 28);
        add(btnFind);

        table = new JTable();
        model = new DefaultTableModel();
        model.addColumn("Họ");
        model.addColumn("Tên");
        model.addColumn("Mã khách hàng");
        model.addColumn("Số điện thoại");
        model.addColumn("Địa chỉ");

        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(88, 356, 898, 240);
        add(scrollPane);

        JLabel lblFirstName = new JLabel("Họ");
        lblFirstName.setFont(new Font("Arial", Font.BOLD, 18));
        lblFirstName.setBounds(87, 146, 77, 34);
        add(lblFirstName);

        txtFirstName = new JTextField();
        txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtFirstName.setColumns(10);
        txtFirstName.setBounds(201, 148, 247, 32);
        add(txtFirstName);

        txtFind = new JTextField();
        txtFind.setBounds(670, 96, 302, 29);
        add(txtFind);
        txtFind.setColumns(10);

        txtIdCustomer = new JTextField();
        txtIdCustomer.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtIdCustomer.setColumns(10);
        txtIdCustomer.setBounds(739, 148, 247, 32);
        add(txtIdCustomer);

        JLabel lblIdCustomer = new JLabel("Mã khách hàng");
        lblIdCustomer.setFont(new Font("Arial", Font.BOLD, 18));
        lblIdCustomer.setBounds(562, 146, 144, 34);
        add(lblIdCustomer);
        txtIdCustomer.setEditable(false);
        
        JLabel lblPhoneNumer = new JLabel("Số điện thoại");
        lblPhoneNumer.setFont(new Font("Arial", Font.BOLD, 18));
        lblPhoneNumer.setBounds(562, 205, 130, 34);
        add(lblPhoneNumer);
        
        JLabel lblNewLabel = new JLabel("QUẢN LÍ KHÁCH HÀNG");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel.setForeground(new Color(252, 132, 67));
        lblNewLabel.setBounds(377, 10, 292, 36);
        add(lblNewLabel);

        loadData();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhachHang();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaKhachHang();
            }
        });

        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaKhachHang();
            }
        });

        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	xuLyTimKiemKhachHang();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                txtFirstName.setText(model.getValueAt(selectedRow, 0).toString());
                txtLastName.setText(model.getValueAt(selectedRow, 1).toString());
                txtIdCustomer.setText(model.getValueAt(selectedRow, 2).toString());
                txtPhoneNumber.setText(model.getValueAt(selectedRow, 3).toString());
                txtAddress.setText(model.getValueAt(selectedRow, 4).toString());
            }
        });
        
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                sortTable(col);
            }
        });
    }

    private void loadData() {
        model.setRowCount(0);  // Xóa tất cả các hàng hiện tại trong bảng

        ArrayList<KhachHangDTO> dskh = khachHangBUS.getDanhSachKhachHang();  // Lấy danh sách khách hàng từ lớp BUS

        for (KhachHangDTO kh : dskh) {
            Vector<Object> row = new Vector<>();
            row.add(kh.getho());
            row.add(kh.getten());
            row.add(kh.getmaKH());
            row.add(kh.getsdt());
            row.add(kh.getdiaChi());

            model.addRow(row);  // Thêm hàng mới vào mô hình của bảng
        }
    }

    private void xuLySuaKhachHang() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String idCustomer = txtIdCustomer.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();


        khachHangBUS.suaKhachHang(idCustomer,firstName, lastName,address,phoneNumber);
        khachHangBUS.docDanhSach();
        loadData();  
    }

    private void xuLyXoaKhachHang() {
        String idCustomer = txtIdCustomer.getText();
        khachHangBUS.xoaKhachHang(idCustomer);
        khachHangBUS.docDanhSach();
        loadData();  
    }

    private void xuLyThemKhachHang() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();

        KhachHangDTO kh = new KhachHangDTO();
        kh.setho(firstName);
        kh.setten(lastName);
        kh.setsdt(phoneNumber);
        kh.setdiaChi(address);

        khachHangBUS.themKhachHang(firstName,lastName,address,phoneNumber);
        khachHangBUS.docDanhSach();
        loadData(); 
    }
    private void xuLyTimKiemKhachHang() {
    	
    	String tuKhoa = txtFind.getText();
    	if(!tuKhoa.equals("")) {
    		ArrayList<KhachHangDTO> dskh = khachHangBUS.timKiemKhachHang(tuKhoa);
            model.setRowCount(0);
            for (KhachHangDTO kh : dskh) {
            	Vector<Object> row = new Vector<>();
                row.add(kh.getho());
                row.add(kh.getten());
                row.add(kh.getmaKH());
                row.add(kh.getsdt());
                row.add(kh.getdiaChi());
                model.addRow(row);
            }
    	}else {
    		loadData();
    	}
        
    }
    
    private void sortTable(int col) {
        ArrayList<KhachHangDTO> dskh = khachHangBUS.getDanhSachKhachHang();
        Comparator<KhachHangDTO> comparator;

        switch (col) {
            case 0: // Họ
                comparator = Comparator.comparing(KhachHangDTO::getho);
                break;
            case 1: // Tên
                comparator = Comparator.comparing(KhachHangDTO::getten);
                break;
            case 2: // Mã khách hàng
                comparator = Comparator.comparing(KhachHangDTO::getmaKH);
                break;
            case 3: // Số điện thoại
                comparator = Comparator.comparing(KhachHangDTO::getsdt);
                break;
            case 4: // Địa chỉ
                comparator = Comparator.comparing(KhachHangDTO::getdiaChi);
                break;
            default:
                return; // Nếu cột không hợp lệ, thoát khỏi hàm
        }

        Collections.sort(dskh, comparator);

        model.setRowCount(0);

        for (KhachHangDTO kh : dskh) {
            Vector<Object> row = new Vector<>();
            row.add(kh.getho());
            row.add(kh.getten());
            row.add(kh.getmaKH());
            row.add(kh.getsdt());
            row.add(kh.getdiaChi());

            model.addRow(row);
        }
    }

    
}