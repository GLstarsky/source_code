package QuanLyCuaHangSach.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class TrangChuGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelThongke;
    private JPanel panelNhapHang;
    private JPanel panelNhanVien;
    private JPanel panelKhachHang;
    private JPanel panelSach;
    private JPanel panelGiamGia;
    private JPanel panelsanpham;



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TrangChuGUI frame = new TrangChuGUI();
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
    public TrangChuGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1300, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);
        
        
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 134, 108));
        panel.setBounds(0, 0, 210, 763);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(TrangChuGUI.class.getResource("/image/z5459891962307_b75b7dc3f327e351f544065cb77bb642.jpg")));
        lblNewLabel.setBounds(0, 0, 216, 160);
        panel.add(lblNewLabel);

        // Khởi tạo panelThongke và panelNhapHang
        panelThongke = new JPanel();
        panelThongke.setBackground(new Color(255, 255, 255));
        panelThongke.setBounds(209, 0, 1071, 763);
        panelThongke.setLayout(null);
        
        panelsanpham = new JPanel();
        panelsanpham.setBackground(new Color(255, 255, 255));
        panelsanpham.setBounds(209, 0, 1071, 763);
        panelsanpham.setLayout(null);
        
        panelNhapHang = new JPanel();
        panelNhapHang.setBounds(209, 0, 1071, 763);
        panelNhapHang.setLayout(null);
        
        panelNhanVien = new JPanel();
        panelNhanVien.setBounds(209, 0, 1071, 763);
        panelNhanVien.setLayout(null);
        
        panelKhachHang = new JPanel();
        panelKhachHang.setBounds(209, 0, 1071, 763);
        panelKhachHang.setLayout(null);
        
        panelSach = new JPanel();
        panelSach.setBounds(209, 0, 1071, 763);
        panelSach.setLayout(null);
        
        panelGiamGia = new JPanel();
        panelGiamGia.setBounds(209, 0, 1071, 763);
        panelGiamGia.setLayout(null);

        contentPane.add(panelThongke); // Bắt đầu với panelThongke được thêm vào contentPane

        JButton btnThongKe = new JButton("Thống kê");
        btnThongKe.setBackground(new Color(254, 242, 186));
        btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 23));
        btnThongKe.setIcon(new ImageIcon(TrangChuGUI.class.getResource("/icon/Icons8-Ios7-Data-Bar-Chart.32.png")));
        btnThongKe.setBounds(0, 692, 210, 43);
        btnThongKe.setBorder(null);
        panel.add(btnThongKe);
        btnThongKe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(panelNhapHang);
                contentPane.remove(panelNhanVien);
                contentPane.remove(panelKhachHang);
                contentPane.remove(panelSach);
                contentPane.remove(panelGiamGia);
                contentPane.remove(panelsanpham);
                contentPane.add(panelThongke);
                contentPane.revalidate();
                contentPane.repaint();

                panelThongke.removeAll();
                ThongKeGUI thongke = new ThongKeGUI();
                thongke.setBounds(0, 0, panelThongke.getWidth(), panelThongke.getHeight());
                panelThongke.add(thongke);
                panelThongke.revalidate();
                panelThongke.repaint();
            }
        });
        
        

        JButton btnNhapHang = new JButton("Nhập hàng");
        btnNhapHang.setBackground(new Color(254, 242, 186));
        btnNhapHang.setFont(new Font("Tahoma", Font.BOLD, 23));
        btnNhapHang.setIcon(new ImageIcon(TrangChuGUI.class.getResource("/icon/Pictogrammers-Material-Light-Truck.32.png")));
        btnNhapHang.setBounds(0, 605, 210, 43);
        btnNhapHang.setBorder(null);
        panel.add(btnNhapHang);
        btnNhapHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(panelThongke);
                contentPane.remove(panelNhanVien);
                contentPane.remove(panelKhachHang);
                contentPane.remove(panelSach);
                contentPane.remove(panelGiamGia);
                contentPane.remove(panelsanpham);
                contentPane.add(panelNhapHang);
                contentPane.revalidate();
                contentPane.repaint();
                panelNhapHang.removeAll();
                QuanLiNhapHangGUI nhaphang = new QuanLiNhapHangGUI();
                nhaphang.setBounds(0, 0, panelNhapHang.getWidth(), panelNhapHang.getHeight());
                panelNhapHang.add(nhaphang);
                panelNhapHang.revalidate();
                panelNhapHang.repaint();
            }
        });
 

    
        
        JButton btnBanHang = new JButton(" Bán hàng");
        btnBanHang.setIcon(new ImageIcon(TrangChuGUI.class.getResource("/icon/Iconsmind-Outline-Full-Cart.48.png")));
        btnBanHang.setFont(new Font("Tahoma", Font.BOLD, 23));
        btnBanHang.setBorder(null);
        btnBanHang.setBackground(new Color(254, 242, 186));
        btnBanHang.setBounds(0, 202, 210, 43);
        panel.add(btnBanHang);
        btnBanHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(panelThongke);
                contentPane.remove(panelNhapHang);
                contentPane.remove(panelNhanVien);
                contentPane.remove(panelSach);
                contentPane.remove(panelGiamGia);
                contentPane.add(panelsanpham);
                contentPane.remove(panelKhachHang);
                contentPane.revalidate();
                contentPane.repaint();

                panelsanpham.removeAll();
                BanHangGUI khachhang = new BanHangGUI();
                khachhang.setBounds(0, 0, panelsanpham.getWidth(), panelsanpham.getHeight());
                panelsanpham.add(khachhang);
                panelsanpham.revalidate();
                panelsanpham.repaint();
            }
        });
        
        JButton btnKhuyenMai = new JButton("Khuyến mãi");
        btnKhuyenMai.setIcon(new ImageIcon(TrangChuGUI.class.getResource("/icon/Icons8-Ios7-Ecommerce-Sale.32.png")));
        btnKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 23));
        btnKhuyenMai.setBorder(null);
        btnKhuyenMai.setBackground(new Color(254, 242, 186));
        btnKhuyenMai.setBounds(0, 281, 210, 43);
        panel.add(btnKhuyenMai);
        btnKhuyenMai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(panelThongke);
                contentPane.remove(panelNhapHang);
                contentPane.remove(panelNhanVien);
                contentPane.remove(panelSach);              
                contentPane.remove(panelsanpham);
                contentPane.remove(panelKhachHang);
                contentPane.add(panelGiamGia);
                contentPane.revalidate();
                contentPane.repaint();
                panelGiamGia.removeAll();
                GiamGiaGUI giamgia = new GiamGiaGUI();
                giamgia.setBounds(0, 0, panelGiamGia.getWidth(), panelGiamGia.getHeight());
                panelGiamGia.add(giamgia);
                panelGiamGia.revalidate();
                panelGiamGia.repaint();
            }
        });
        
        JButton btnSanPham = new JButton(" Sản phẩm");
        btnSanPham.setIcon(new ImageIcon(TrangChuGUI.class.getResource("/icon/Microsoft-Fluentui-Emoji-Mono-Books.32.png")));
        btnSanPham.setFont(new Font("Tahoma", Font.BOLD, 23));
        btnSanPham.setBorder(null);
        btnSanPham.setBackground(new Color(254, 242, 186));
        btnSanPham.setBounds(0, 360, 210, 43);
        panel.add(btnSanPham);
        btnSanPham.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(panelThongke);
                contentPane.remove(panelNhapHang);
                contentPane.remove(panelNhanVien);
                contentPane.remove(panelsanpham);
                contentPane.remove(panelGiamGia);
                contentPane.add(panelSach);
                contentPane.remove(panelKhachHang);
                contentPane.revalidate();
                contentPane.repaint();

                panelSach.removeAll();
                SachGUI sach = new SachGUI();
                sach.setBounds(0, 0, panelSach.getWidth(), panelSach.getHeight());
                panelSach.add(sach);
                panelSach.revalidate();
                panelSach.repaint();
            }
        });
        
        JButton btnNhanVien = new JButton("Nhân viên");
        btnNhanVien.setIcon(new ImageIcon(TrangChuGUI.class.getResource("/icon/Github-Octicons-People-16.32.png")));
        btnNhanVien.setFont(new Font("Tahoma", Font.BOLD, 23));
        btnNhanVien.setBorder(null);
        btnNhanVien.setBackground(new Color(254, 242, 186));
        btnNhanVien.setBounds(0, 447, 210, 43);
        panel.add(btnNhanVien);
        btnNhanVien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(panelThongke);
                contentPane.remove(panelNhapHang);
                contentPane.remove(panelKhachHang);
                contentPane.remove(panelGiamGia);
                contentPane.remove(panelSach);
                contentPane.remove(panelsanpham);
                contentPane.add(panelNhanVien);
                contentPane.revalidate();
                contentPane.repaint();

                panelNhanVien.removeAll();
                NhanVienGUI nhanvien = new NhanVienGUI();
                nhanvien.setBounds(0, 0, panelNhanVien.getWidth(), panelNhanVien.getHeight());
                panelNhanVien.add(nhanvien);
                panelNhanVien.revalidate();
                panelNhanVien.repaint();
            }
        });
        
        JButton btnKhachHang = new JButton("Khách hàng");
        btnKhachHang.setIcon(new ImageIcon(TrangChuGUI.class.getResource("/icon/Custom-Icon-Design-Silky-Line-User-User2-info-2.32.png")));
        btnKhachHang.setFont(new Font("Tahoma", Font.BOLD, 23));
        btnKhachHang.setBorder(null);
        btnKhachHang.setBackground(new Color(254, 242, 186));
        btnKhachHang.setBounds(0, 529, 210, 43);
        panel.add(btnKhachHang);
        btnKhachHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(panelThongke);
                contentPane.remove(panelNhapHang);
                contentPane.remove(panelNhanVien);
                contentPane.remove(panelSach);
                contentPane.remove(panelGiamGia);
                contentPane.remove(panelsanpham);
                contentPane.add(panelKhachHang);
                contentPane.revalidate();
                contentPane.repaint();

                panelKhachHang.removeAll();
                KhachHangGUI khachhang = new KhachHangGUI();
                khachhang.setBounds(0, 0, panelKhachHang.getWidth(), panelKhachHang.getHeight());
                panelKhachHang.add(khachhang);
                panelKhachHang.revalidate();
                panelKhachHang.repaint();
            }
        });
        
       
        
        
        
        


        
    }
}
