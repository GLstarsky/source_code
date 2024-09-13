-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 16, 2024 lúc 03:32 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlycuahangsach`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietchuongtrinhkm`
--

CREATE TABLE `chitietchuongtrinhkm` (
  `MaChuongTrinh` int(11) NOT NULL,
  `MaSanPham` int(11) NOT NULL,
  `PhanTramKM` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHoaDon` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` int(11) NOT NULL,
  `ThanhTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `Ma` int(11) NOT NULL,
  `MaPhieuNhap` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `DonGia` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `ThanhTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chuongtrinhkhuyenmai`
--

CREATE TABLE `chuongtrinhkhuyenmai` (
  `Ma` int(11) NOT NULL,
  `Ten` varchar(100) NOT NULL,
  `NgayBD` date NOT NULL,
  `NgayKT` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `Ma` int(11) NOT NULL,
  `NgayTao` date NOT NULL,
  `MaNV` int(11) NOT NULL,
  `MaKH` int(11) NOT NULL,
  `TongTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `Ma` int(11) NOT NULL,
  `Ten` varchar(100) NOT NULL,
  `DiaChi` varchar(100) NOT NULL,
  `SoDienThoai` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaChuongTrinh` int(11) NOT NULL,
  `TongTienHoaDon` int(11) NOT NULL,
  `PhanTramKM` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `Ma` int(11) NOT NULL,
  `Ten` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `Ma` int(11) NOT NULL,
  `Ten` varchar(100) NOT NULL,
  `DiaChi` varchar(100) NOT NULL,
  `GioiTinh` varchar(100) NOT NULL,
  `ChucVu` varchar(100) NOT NULL,
  `SoDienThoai` varchar(100) NOT NULL,
  `MucLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhaxuatban`
--

CREATE TABLE `nhaxuatban` (
  `Ma` int(11) NOT NULL,
  `Ten` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhaxuatban`
--

INSERT INTO `nhaxuatban` (`Ma`, `Ten`) VALUES
(1, 'Kim Đồng'),
(2, 'Lao Động'),
(3, 'Hội Nhà văn'),
(4, 'Chính trị quốc gia Sự thật'),
(5, 'Thanh Niên'),
(6, 'Nhã Nam'),
(7, 'Tổng hợp TP.Hồ Chí Minh'),
(8, 'Hồng Đức'),
(9, 'Nhiều NXB'),
(10, 'Quân Đội Nhân Dân'),
(11, 'Dân trí');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanloai`
--

CREATE TABLE `phanloai` (
  `Ma` int(11) NOT NULL,
  `Ten` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phanloai`
--

INSERT INTO `phanloai` (`Ma`, `Ten`) VALUES
(1, 'Tiểu thuyết'),
(2, 'Văn học'),
(3, 'Lịch sử'),
(4, 'Trinh thám - Hình sự'),
(5, 'Sách tự lực'),
(6, 'Tâm lý - Tình cảm'),
(7, 'Ngoại ngữ'),
(8, 'Học thuật'),
(9, 'Khác');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `Ma` int(11) NOT NULL,
  `MaNCC` int(11) NOT NULL,
  `MaNV` int(11) NOT NULL,
  `NgayTao` date NOT NULL,
  `TongTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sach`
--

CREATE TABLE `sach` (
  `Ma` int(11) NOT NULL,
  `Ten` varchar(100) NOT NULL,
  `TacGia` varchar(100) NOT NULL,
  `MaLoai` int(11) NOT NULL,
  `MaNXB` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` int(11) NOT NULL,
  `HinhAnh` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sach`
--

INSERT INTO `sach` (`Ma`, `Ten`, `TacGia`, `MaLoai`, `MaNXB`, `SoLuong`, `DonGia`, `HinhAnh`) VALUES
(1, 'Nhà giả kim', 'Paulo Coelho', 1, 9, 100, 60000, 'Sach1.png'),
(2, 'Quyền lực hướng thiện', 'Trương Quốc Ký', 1, 5, 100, 230000, ''),
(3, 'Giải mã thuật tiên tri', 'Elsie Wild', 9, 8, 50, 76000, 'Sach3.png'),
(4, 'Giữa bóng tối và ánh sáng', 'Joan Chittister', 1, 8, 30, 64000, 'Sach4.png'),
(5, 'Đường Xa Nắng Mới', 'Nguyễn Tường Bách', 2, 3, 30, 180000, 'Sach5.png'),
(6, 'Cơm Bắc Giặc Nam', 'Phùng Phương Quý', 2, 10, 100, 120000, 'Sach6.png'),
(7, 'Bình Minh Phía Trước', 'Nguyễn Trọng Luân', 2, 10, 20, 120000, 'Sach7.png'),
(8, 'Linh Hồn Báo Thù', 'Joe Hill', 2, 5, 30, 167000, 'Sach8.png'),
(9, 'Nhân Gian Tiểu Mãn', 'Cô Tô A Tiêu', 2, 11, 40, 76000, 'Sach9.png'),
(10, 'Phan Huy Lê Di Cảo - Nhận Thức Lịch Sử Việt Nam', 'Phan Huy Lê', 3, 11, 100, 392000, 'Sach10.png'),
(11, 'Điện Biên Phủ - Những Trang Vàng Lịch Sử', 'Hoàng Minh Phương', 3, 5, 100, 108000, 'Sach11.png'),
(12, 'Miền Bắc - Một Thời Chiến Tranh, Một Thời Hòa Bình', 'Folke Isaksson', 3, 5, 100, 228000, 'Sach12.png'),
(13, 'Sự im lặng của bầy cừu', 'Thomas Harris', 4, 9, 100, 109000, 'Sach13.png'),
(14, 'Án mạng trên sông Nile', 'Agatha Christie', 4, 9, 50, 125000, 'Sach14.png'),
(15, 'Hỏa ngục', 'Dan Brown', 4, 9, 100, 153000, 'Sach15.png'),
(16, 'Tâm Lý Học Tội Phạm', 'Stanton E.Samenow', 4, 9, 100, 94000, 'Sach16.png'),
(17, 'Thái Nhân Cách Phía Sau Tội Ác', 'James Fallon', 4, 9, 100, 132000, 'Sach17.png'),
(18, 'Tư duy nhanh và chậm', 'Daniel Kahneman', 5, 9, 40, 201000, 'Sach18.png'),
(19, 'Mình là cá, việc của mình là bơi', 'Takeshi Furukawa', 5, 3, 50, 90000, 'Sach19.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tacgia`
--

CREATE TABLE `tacgia` (
  `Ma` int(11) NOT NULL,
  `Ten` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tacgia`
--

INSERT INTO `tacgia` (`Ma`, `Ten`) VALUES
(1, 'Nguyễn Nhật Ánh'),
(2, 'Lê Linh'),
(3, 'Jack London'),
(4, 'Joan Chittister'),
(5, 'Trương Quốc Ký'),
(6, 'Tô Hoài'),
(7, 'Elsie Wild'),
(8, 'Shakti Gawain\r\n'),
(9, 'José Mauro de Vasconcelos'),
(10, 'Antoine de Saint-Exupéry');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietchuongtrinhkm`
--
ALTER TABLE `chitietchuongtrinhkm`
  ADD KEY `MaChuongTrinh` (`MaChuongTrinh`),
  ADD KEY `MaSanPham` (`MaSanPham`);

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD KEY `MaHoaDon` (`MaHoaDon`),
  ADD KEY `MaSach` (`MaSach`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`Ma`);

--
-- Chỉ mục cho bảng `chuongtrinhkhuyenmai`
--
ALTER TABLE `chuongtrinhkhuyenmai`
  ADD PRIMARY KEY (`Ma`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`Ma`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`Ma`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD KEY `MaChuongTrinh` (`MaChuongTrinh`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`Ma`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`Ma`);

--
-- Chỉ mục cho bảng `nhaxuatban`
--
ALTER TABLE `nhaxuatban`
  ADD PRIMARY KEY (`Ma`);

--
-- Chỉ mục cho bảng `phanloai`
--
ALTER TABLE `phanloai`
  ADD PRIMARY KEY (`Ma`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`Ma`),
  ADD KEY `MaNCC` (`MaNCC`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Chỉ mục cho bảng `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`Ma`),
  ADD KEY `MaNXB` (`MaNXB`),
  ADD KEY `MaLoai` (`MaLoai`);

--
-- Chỉ mục cho bảng `tacgia`
--
ALTER TABLE `tacgia`
  ADD PRIMARY KEY (`Ma`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  MODIFY `Ma` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `chuongtrinhkhuyenmai`
--
ALTER TABLE `chuongtrinhkhuyenmai`
  MODIFY `Ma` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `Ma` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `Ma` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `Ma` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `Ma` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `nhaxuatban`
--
ALTER TABLE `nhaxuatban`
  MODIFY `Ma` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `phanloai`
--
ALTER TABLE `phanloai`
  MODIFY `Ma` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `Ma` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `sach`
--
ALTER TABLE `sach`
  MODIFY `Ma` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `tacgia`
--
ALTER TABLE `tacgia`
  MODIFY `Ma` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietchuongtrinhkm`
--
ALTER TABLE `chitietchuongtrinhkm`
  ADD CONSTRAINT `chitietchuongtrinhkm_ibfk_1` FOREIGN KEY (`MaChuongTrinh`) REFERENCES `chuongtrinhkhuyenmai` (`Ma`),
  ADD CONSTRAINT `chitietchuongtrinhkm_ibfk_2` FOREIGN KEY (`MaSanPham`) REFERENCES `sach` (`Ma`);

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`Ma`),
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`Ma`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`Ma`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`Ma`);

--
-- Các ràng buộc cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD CONSTRAINT `khuyenmai_ibfk_1` FOREIGN KEY (`MaChuongTrinh`) REFERENCES `chuongtrinhkhuyenmai` (`Ma`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`Ma`),
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`Ma`);

--
-- Các ràng buộc cho bảng `sach`
--
ALTER TABLE `sach`
  ADD CONSTRAINT `sach_ibfk_1` FOREIGN KEY (`MaNXB`) REFERENCES `nhaxuatban` (`Ma`),
  ADD CONSTRAINT `sach_ibfk_2` FOREIGN KEY (`MaLoai`) REFERENCES `phanloai` (`Ma`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
