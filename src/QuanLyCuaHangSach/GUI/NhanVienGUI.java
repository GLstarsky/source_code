package QuanLyCuaHangSach.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import QuanLyCuaHangSach.BUS.NhanVienBUS;
import QuanLyCuaHangSach.DTO.NhanVienDTO;

import javax.swing.ImageIcon;

//import DoAn_QuanLiCuaHangSach.BUS.NhanVienBUS;

public class NhanVienGUI extends JPanel {
    DefaultTableModel model;

    private static final long serialVersionUID = 1L;

    private JTextField txtLastName;
    private JTextField txtPhoneNumber;
    private JTextField txtWage;
    private JTextField txtAddress;
    private JTable table;
    private JTextField txtFirstName;
    private JTextField txtFind;
    private JTextField txtIdEmployee;
    private JComboBox<String> cbxPosition;
    private JRadioButton rdbtnMale;
    private JRadioButton rdbtnFemale;
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
//    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private JLabel lblPhoneNumber;
    private JLabel lblGender;
  
    public NhanVienGUI() {
    	setBackground(new Color(255, 255, 255));
        
        
       setLayout(null);

        JLabel lblListEmployee = new JLabel("DANH SÁCH NHÂN VIÊN");
        lblListEmployee.setForeground(new Color(94, 228, 65));
        lblListEmployee.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblListEmployee.setBounds(393, 311, 262, 49);
        add(lblListEmployee);

        JLabel lblLastName = new JLabel("Tên");
        lblLastName.setFont(new Font("Arial", Font.BOLD, 18));
        lblLastName.setBounds(76, 169, 113, 34);
        add(lblLastName);

        txtLastName = new JTextField();
        txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtLastName.setBounds(216, 170, 247, 33);
        add(txtLastName);
        txtLastName.setColumns(10);

        JLabel lblPosition = new JLabel("Chức vụ");
        lblPosition.setFont(new Font("Arial", Font.BOLD, 20));
        lblPosition.setBounds(580, 211, 120, 37);
        add(lblPosition);

        DefaultComboBoxModel<String> modelPosition = new DefaultComboBoxModel<>();
        modelPosition.addElement("Quản lý");
        modelPosition.addElement("Bán hàng");
        modelPosition.addElement("Quản lý kho");
        this.cbxPosition = new JComboBox<>(modelPosition);
        this.cbxPosition.setBounds(738, 211, 247, 34);
        add(this.cbxPosition);

        this.rdbtnMale = new JRadioButton("Nam");
        rdbtnMale.setBackground(new Color(255, 255, 255));
        this.rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.rdbtnMale.setBounds(775, 264, 85, 30);
        add(this.rdbtnMale);

        this.rdbtnFemale = new JRadioButton("Nữ");
        rdbtnFemale.setBackground(new Color(255, 255, 255));
        this.rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.rdbtnFemale.setBounds(889, 264, 69, 30);
        add(this.rdbtnFemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rdbtnFemale);
        bg.add(rdbtnMale);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtPhoneNumber.setColumns(10);
        txtPhoneNumber.setBounds(216, 213, 247, 34);
        add(txtPhoneNumber);

        JLabel lblWage = new JLabel("Lương");
        lblWage.setFont(new Font("Arial", Font.BOLD, 20));
        lblWage.setBounds(580, 168, 113, 34);
        add(lblWage);

        txtWage = new JTextField();
        txtWage.setColumns(10);
        txtWage.setBounds(738, 172, 247, 33);
        add(txtWage);

        JLabel lblAddress = new JLabel("Địa chỉ");
        lblAddress.setFont(new Font("Arial", Font.BOLD, 18));
        lblAddress.setBounds(76, 263, 120, 37);
        add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtAddress.setColumns(10);
        txtAddress.setBounds(216, 259, 247, 33);
        add(txtAddress);

        JButton btnAdd = new JButton("Thêm");
        btnAdd.setIcon(new ImageIcon(NhanVienGUI.class.getResource("/icon/Custom-Icon-Design-Flatastic-1-Add.16.png")));
        btnAdd.setBackground(new Color(252, 252, 137));
        btnAdd.setForeground(Color.BLACK);
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnAdd.setBounds(76, 70, 120, 26);
        add(btnAdd); 

        JButton btnChange = new JButton("Sửa");
        btnChange.setIcon(new ImageIcon(NhanVienGUI.class.getResource("/icon/Custom-Icon-Design-Pretty-Office-10-Pencil.24.png")));
        btnChange.setBackground(new Color(252, 252, 137));
        btnChange.setForeground(Color.BLACK);
        btnChange.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnChange.setBounds(220, 68, 103, 30);
        add(btnChange);

        JButton btnDelete = new JButton("Xóa");
        btnDelete.setIcon(new ImageIcon(NhanVienGUI.class.getResource("/icon/Awicons-Vista-Artistic-Delete.24.png")));
        btnDelete.setBackground(new Color(252, 252, 137));
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnDelete.setBounds(365, 68, 103, 30);
        add(btnDelete);

        JButton btnFind = new JButton("");
        btnFind.setIcon(new ImageIcon(NhanVienGUI.class.getResource("/icon/Custom-Icon-Design-Flatastic-1-Search.24.png")));
        btnFind.setForeground(Color.BLACK);
        btnFind.setBackground(new Color(252, 252, 137));
        btnFind.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnFind.setBounds(970, 68, 51, 28);
        add(btnFind);

        table = new JTable();
        model = new DefaultTableModel();
        model.addColumn("Họ");
        model.addColumn("Tên");
        model.addColumn("Mã nhân viên");
        model.addColumn("Chức vụ");
        model.addColumn("Giới tính");
        model.addColumn("Tiền lương");
        model.addColumn("Số điện thoại");
        model.addColumn("Địa chỉ");

        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(76, 370, 909, 217);
        add(scrollPane);

        JLabel lblFirstName = new JLabel("Họ");
        lblFirstName.setFont(new Font("Arial", Font.BOLD, 18));
        lblFirstName.setBounds(76, 125, 85, 34);
        add(lblFirstName);

        txtFirstName = new JTextField();
        txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtFirstName.setColumns(10);
        txtFirstName.setBounds(216, 126, 247, 37);
        add(txtFirstName);

        txtFind = new JTextField();
        txtFind.setBounds(633, 73, 303, 29);
        add(txtFind);
        txtFind.setColumns(10);

        txtIdEmployee = new JTextField();
        txtIdEmployee.setColumns(10);
        txtIdEmployee.setBounds(738, 126, 247, 37);
        add(txtIdEmployee);

        JLabel lblIdEmployee = new JLabel("Mã nhân viên");
        lblIdEmployee.setFont(new Font("Arial", Font.BOLD, 20));
        lblIdEmployee.setBounds(580, 124, 130, 34);
        add(lblIdEmployee);
        txtIdEmployee.setEditable(false);
        
        lblPhoneNumber = new JLabel("Số điện thoại");
        lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 18));
        lblPhoneNumber.setBounds(76, 213, 136, 34);
        add(lblPhoneNumber);
        
        lblGender = new JLabel("Giới tính");
        lblGender.setFont(new Font("Arial", Font.BOLD, 20));
        lblGender.setBounds(580, 263, 120, 37);
        add(lblGender);
        
        JLabel lblNewLabel = new JLabel("QUẢN LÍ NHÂN VIÊN");
        lblNewLabel.setForeground(new Color(249, 129, 40));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel.setBounds(360, 10, 267, 32);
        add(lblNewLabel);

        loadData();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemNhanVien();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaNhanVien();
            }
        });

        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaNhanVien();
            }
        });

        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	xuLyTimKiemNhanVien();
            }
        });



        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                txtFirstName.setText(model.getValueAt(selectedRow, 0).toString());
                txtLastName.setText(model.getValueAt(selectedRow, 1).toString());
                txtIdEmployee.setText(model.getValueAt(selectedRow, 2).toString());
                cbxPosition.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
                if (model.getValueAt(selectedRow, 4).toString().equals("Nam")) {
                    rdbtnMale.setSelected(true);
                } else {
                    rdbtnFemale.setSelected(true);
                }
                txtWage.setText(model.getValueAt(selectedRow, 5).toString());
                txtPhoneNumber.setText(model.getValueAt(selectedRow, 6).toString());
                txtAddress.setText(model.getValueAt(selectedRow, 7).toString());
            }
        });
        
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                sortTable(col);
            }
        });
    }
    private void loadData() {
        model.setRowCount(0);  // Xóa tất cả các hàng hiện tại trong bảng
        ArrayList<NhanVienDTO> dsnv = nhanVienBUS.getDanhSachNhanVien();  // Lấy danh sách nhân viên từ lớp BUS

        for (NhanVienDTO nv : dsnv) {
            Vector<Object> row = new Vector<>();
            row.add(nv.getho());
            row.add(nv.getten());
            row.add(nv.getmaNV());
            row.add(nv.getchucVu());
            row.add(nv.getgioiTinh());
            row.add(nv.getluong());
            row.add(nv.getsdt());
            row.add(nv.getdiaChi());

            model.addRow(row);  // Thêm hàng mới vào mô hình của bảng
        }
    }
    private void xuLySuaNhanVien() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String idEmployee = txtIdEmployee.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();
        int wage = Integer.parseInt(txtWage.getText());
        String position = (String) cbxPosition.getSelectedItem();
        String gender = rdbtnMale.isSelected() ? "Nam" : "Nữ";
        nhanVienBUS.capNhatNhanVien(idEmployee, firstName, lastName, gender, position, phoneNumber, wage, address);
            nhanVienBUS.docDanhSach();
            loadData();
        
    }

    private void xuLyThemNhanVien() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();
        int wage = 0;
        if(!txtWage.getText().equals("")) {
        	wage = Integer.parseInt(txtWage.getText());
        }else {
        	wage = 0;
        }
        
        String position = (String) cbxPosition.getSelectedItem();
        String gender = rdbtnMale.isSelected() ? "Nam" : "Nữ";
       nhanVienBUS.themNhanVien(firstName, lastName, gender, position, phoneNumber, wage, address);
            nhanVienBUS.docDanhSach();
            loadData();
            
        
    }

    private void xuLyXoaNhanVien() {
        String ma = txtIdEmployee.getText();
        nhanVienBUS.xoaNhanVien(ma);
            nhanVienBUS.docDanhSach();
            loadData();
        
    }
    private void xuLyTimKiemNhanVien() {
        ArrayList<NhanVienDTO> dsnv = nhanVienBUS.timNhanVien(txtFind.getText());
        model.setRowCount(0);
        for (NhanVienDTO nv : dsnv) {
            Vector<Object> row = new Vector<>();
            row.add(nv.getho());
            row.add(nv.getten());
            row.add(nv.getmaNV());
            row.add(nv.getchucVu());
            row.add(nv.getgioiTinh());
            row.add(nv.getluong());
            row.add(nv.getsdt());
            row.add(nv.getdiaChi());

            model.addRow(row);  // Thêm hàng mới vào mô hình của bảng
        }
    }
    
    private void sortTable(int col) {
        ArrayList<NhanVienDTO> dsnv = nhanVienBUS.getDanhSachNhanVien();
        Comparator<NhanVienDTO> comparator;

        switch (col) {
            case 0: // Họ
                comparator = Comparator.comparing(NhanVienDTO::getho);
                break;
            case 1: // Tên
                comparator = Comparator.comparing(NhanVienDTO::getten);
                break;
            case 2: // Mã nhân viên
                comparator = Comparator.comparing(NhanVienDTO::getmaNV);
                break;
            case 3: // Chức vụ
                comparator = Comparator.comparing(NhanVienDTO::getchucVu);
                break;
            case 4: // Giới tính
                comparator = Comparator.comparing(NhanVienDTO::getgioiTinh);
                break;
            case 5: // Tiền lương
                comparator = Comparator.comparing(NhanVienDTO::getluong);
                break;
            case 6: // Số điện thoại
                comparator = Comparator.comparing(NhanVienDTO::getsdt);
                break;
            case 7: // Địa chỉ
                comparator = Comparator.comparing(NhanVienDTO::getdiaChi);
                break;
            default:
                return;
        }

        Collections.sort(dsnv, comparator);

        model.setRowCount(0);
        for (NhanVienDTO nv : dsnv) {
            Vector<Object> row = new Vector<>();
            row.add(nv.getho());
            row.add(nv.getten());
            row.add(nv.getmaNV());
            row.add(nv.getchucVu());
            row.add(nv.getgioiTinh());
            row.add(nv.getluong());
            row.add(nv.getsdt());
            row.add(nv.getdiaChi());

            model.addRow(row);
        }
    }


}
