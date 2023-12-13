CREATE DATABASE DB_karaoke;
go

go
USE DB_karaoke;
go

CREATE TABLE NhanVien (
   maNV VARCHAR(10) PRIMARY KEY,
   CCCD varchar(13) not null,
   hoNV NVARCHAR(50) not null,
   tenNV NVARCHAR(50) not null,
   namSinhNV Date not null,
   gioiTinhNV bit not null,
   sdtNV VARCHAR(12) not null,
   emailNV VARCHAR(30) not null,
   diaChiNV Nvarchar(70) not null,
   chucvu NVARCHAR(50) not null,
   matkhau VARCHAR(30),
   trangThaiLamViec bit not null
);
go

CREATE TABLE KhachHang (
	maKH VARCHAR(10) PRIMARY KEY,
	CCCD Varchar(13) NOT NULL,
	hoKH NVARCHAR(50) NOT NULL,
	tenKH NVARCHAR(50) NOT NULL,
	namSinhKH Date NOT NULL,
	gioitinh bit NOT NULL,
	sdtKH VARCHAR(12) NOT NULL,
	emailKH NVARCHAR(30)
);
GO

Create table LoaiPhong(
	maLP varchar(10) primary key,
	tenLoaiPhong nvarchar(50) NOT NULL,
	giaTien float not null
);
go

Create table Phong(
	maPhong varchar(10) primary key,
	maLP varchar(10) Not null,
	sucNguoi int NOT NULL,
	trangThai nvarchar(30) not null,
	tinhTrang bit NOT NULL

	CONSTRAINT F_P_LP FOREIGN KEY (maLP) REFERENCES LoaiPhong(maLP)
);
go

CREATE TABLE DichVu (
	maDV VARCHAR(10) PRIMARY KEY,
	tenDV NVARCHAR(50) NOT NULL,
	donViBan NVARCHAR(30) NOT NULL,
	soLuongTon INT NOT NULL,
	donGia FLOAT NOT NULL,
	hsd Date,
	xuatXu NVARCHAR(50) NOT NULL,
	tinhTrang bit NOT NULL
);
go

CREATE TABLE HoaDon (
	maHD VARCHAR(10) PRIMARY KEY,
	maKH VARCHAR(10) NOT NULL,
	maNV VARCHAR(10) NOT NULL,
	NgayLap Datetime not null,
	VAT float not null,
	tongTien float not null,
	trangThai bit NOT NULL,

	CONSTRAINT F_HD_KH FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
	CONSTRAINT F_HD_NV FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
);
GO

CREATE TABLE ChiTietHoaDon (
	maHD VARCHAR(10),
	gioNhanPhong Datetime NOT NULL,
	gioKetThuc Datetime NOT NULL,
	maPhong VARCHAR(10)

	PRIMARY KEY (maHD, maPhong),

	CONSTRAINT F_CTHD_HD FOREIGN KEY (maHD) REFERENCES HoaDon(maHD),
	CONSTRAINT F_CTHD_Phong FOREIGN KEY (maPhong) REFERENCES Phong(maPhong)
);
GO

Create table ChiTietDichVu(
	maDV varchar(10),
	soLuong int not null,
	maHD varchar(10),
	maPhong varchar(10),
	PRIMARY KEY (maHD, maDV, maPhong),
	CONSTRAINT F_CTDV_DV FOREIGN KEY (maDV) REFERENCES DichVu(maDV),
	CONSTRAINT F_CTDV_HD FOREIGN KEY (maHD) REFERENCES HoaDon(maHD),
	CONSTRAINT F_CTDV_Phong FOREIGN KEY (maPhong) REFERENCES Phong(maPhong)
);
go

CREATE TABLE PhieuDatPhong (
	maPhieu VARCHAR(10) PRIMARY KEY,
	maKH VARCHAR(10) NOT NULL,
	maNV VARCHAR(10) NOT NULL,
	maPhong Varchar(10) not null,
	thoiGianDat Datetime NOT NULL,
	thoiGianNhan Datetime NOT NULL,
	trangThai bit not null

	CONSTRAINT F_phieuDat_KH FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
	CONSTRAINT F_phieuDat_NV FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
	CONSTRAINT F_phieuDat_Phong FOREIGN KEY (maPhong) REFERENCES Phong(maPhong)
);
GO

/** Thêm dữ liệu nhân viên **/
INSERT INTO NhanVien VALUES
('NV001', '083403000213',N'Phạm Lê Thanh',N'Nhiệt', '2003-nov-15', 1, '0834258511', 'nhiethiz@gmail.com', N'Vĩnh Lộc B, Bình Chánh, TPHCM', N'Quản lý', N'123', 1),
('NV002', '080302005522',N'Đặng Bảo',N'Thông', '2003-aug-15', 1, '0767058459', 'baothong15082003@gmail.com', N'1B, Long Sơn, Cần Đước, Long An', N'Lễ tân', N'vcl', 1),
('NV003', '081403002205',N'Trần Hữu',N'Tài', '2003-may-22', 1, '0343138016', 'huutaia33@gmail.com', N'Nhơn Phúc, An Nhơn, Bình Định', N'Quản lý', N'123', 1),
('NV004', '087202009551',N'Nguyễn Trọng',N'Khang', '2002-Feb-22', 1, '0773941311', 'trongkhang620@gmail.com', N' Thành A, Bình Thạnh, TP.Hồng Ngự, Đồng Tháp', N'Lễ tân', N'777', 1),
('NV005', '040499008112', N'Đặng Thị Lan', N'Anh', '1999-nov-11', 0, '0906789123', 'lananh@gmail.com', N'86b/34/11, đường Võ Văn Kiệt, Bình Chánh, TPHCM', N'Phục vụ', null, 1),
('NV006', '091303083301', N'Mai Vân', N'Trang', '2003-feb-1', 0, '0128849121', 'vantrang36@gmail.com', N'hẻm 23a, Võ Văn Vân, xã Vĩnh Lộc B, huyện Bình Chánh, TPHCM', N'Phục vụ', null, 1),
('NV007', '021498628344', N'Lê Thị Yến', N'Nhung', '1998-may-7', 0, '0605892315', 'nhungyen@gmail.com', N'1a/86, phường 4, Gò Vấp, tphcm', N'Phục vụ', null, 1),
('NV008', '031400123324', N'Phạm Trung', N'Nguyên', '2000-sep-17', 1, '0707923741', 'nguyentrungb@gmail.com', N'77/76/75, Tân Kỳ tân Quý, tphcm', N'Phục vụ', null, 1),
('NV009', '087388037249', N'Đoàn Ngọc', N'Phượng', '1988-dec-15', 0, '0538971982', 'phuongda@gmail.com', N'hẻm 44, Lê Trọng Tấn, tphcm', N'Lao công', null, 1),
('NV010', '092286483904', N'Quan Đặng', N'Phương', '1986-jul-20', 0, '0938724675', 'phuongdang@gmail.com', N'a4/31/11, liên khu 5-6, Bình Chánh, tphcm', N'Lao công', null, 1),
('NV011', '083270983243', N'Đăng Quang', N'Út', '1970-mar-3', 1, '0172777654', 'utzt@gmail.com', N'91/31/2, khu phố 5, quận 4, tphcm', N'Lao công', null, 1),
('NV012', '041180000945', N'Đoàn Thế Lại', N'Kiệt', '1980-jun-19', 1, '0803456789', 'kietlaithe@gmail.com', N'b56/22/124, tỉnh lộ 10, Lê Minh Xuân, tphcm', N'Lao công', null, 1),
('NV013', '011196034831', N'Nguyễn Văn Thành', N'Nam', '1996-apr-6', 1, '0125555667', 'namnhok@gmail.com', N'a3/11, Cây Cám, Bình Chánh, tphcm', N'Đầu bếp', null, 1),
('NV014', '060493000875', N'Cao Văn', N'Minh', '1993-aug-4', 1, '0300389441', N'minhvjp@gmail.com', N'a32/12, hẻm 34, hương lộ 2, Bình Tân, tphcm', N'Đầu bếp', null, 1)
go

Insert into DichVu values
('DV001',N'Ngũ quả dĩa lớn', N'Dĩa', 150, 110000, null, N'Việt Nam', 1),
('DV002',N'Xoài chín dĩa nhỏ', N'Dĩa', 250, 40000, null, N'Việt Nam', 1),
('DV003',N'Lê dĩa nhỏ', N'Dĩa', 250, 40000, null, N'Việt Nam', 1),
('DV004',N'Dưa hấu dĩa vừa', N'Dĩa', 250, 60000, null, N'Việt Nam', 1),
('DV005',N'Ổi dĩa nhỏ', N'Dĩa', 250, 40000, null, N'Việt Nam', 1),
('DV006',N'Nho xanh dĩa nhỏ', N'Dĩa', 250, 50000, null, N'Việt Nam', 1),
('DV007',N'Khăn lạnh', N'Bịch', 2000, 0, null, N'Việt Nam', 1),
('DV008',N'Snack đậu phộng Pinattsu Oishi vị mực 95g', N'Bịch', 500, 20000, '2024-nov-20', N'Việt Nam', 1),
('DV009',N'Snack đậu phộng Pinattsu Oishi vị rong biển 95g', N'Bịch', 500, 20000, '2024-nov-20', N'Việt Nam', 1),
('DV010',N'Khô bò Zon Zon BZZ 100g', N'Hộp', 700, 100000, '2025-dec-24', N'Việt Nam', 1),
('DV011',N'Khô mực ống nướng', N'Dĩa', 500, 50000, null, N'Việt Nam', 1),
('DV012',N'Cam ép twister tropicacna 320ml', N'Lon', 700, 16000, '2025-nov-11', N'Việt Nam', 1),
('DV013',N'La vie 500ml', N'Chai', 700, 10000, '2026-dec-30', N'Việt Nam', 1),
('DV014',N'Coca cola 320ml', N'Lon', 700, 16000, '2025-may-2', N'Việt Nam', 1),
('DV015',N'Sprite hương chanh 320ml', N'Lon', 700, 16000, '2025-sep-10', N'Việt Nam', 1),
('DV016',N'Sting hương dâu 320ml', N'Chai', 800, 16000, '2025-oct-15', N'Thái Lan', 1),
('DV017',N'Trà ô long Tea Plus 450ml', N'Chai', 500, 16000, '2026-apr-6', N'Thái Lan', 1),
('DV018',N'Bia Budweiser 320ml', N'Lon', 800, 30000, '2026-nov-12', N'Hoa Kỳ', 1),
('DV019',N'Bia Heinken 330ml', N'Lon', 800, 30000, '2026-feb-20', N'Việt Nam', 1),
('DV020',N'Bia Tiger 330ml', N'Lon', 800, 30000, '2026-jul-30', N'Việt Nam', 1),
('DV021',N'Que cay bò tứ xuyên', N'Bịch', 200, 100000, '2026-nov-12', N'Trung Quốc', 1),
('DV022',N'Que cay latiao Dong Dong', N'Bịch', 200, 100000, '2026-feb-20', N'Trung Quốc', 1),
('DV023',N'Bia đen Sinkiang', N'Chai', 800, 30000, '2026-jul-30', N'Trung Quốc', 1),
('DV024',N'Vang Pour Le Vin Tout Un Fromage Merlot', N'Chai', 300, 450000, null, N'Đức', 1),
('DV025', N'Vang Orby Syrah-Grenache AOC Cotes du Rhone BIO', N'Chai', 200, 800000, null, N'Hoa Kỳ', 0)
go

Insert into KhachHang values
('KH001', '083193000287', N'Nguyễn Lê Hồng',N'Thái', '1993-jan-15', 1, '0974867268',N'thainguyen27@gmail.com'),
('KH002', '023901923184', N'Choi Song',N'Vong', '2001-mar-20', 1, '0846381902',N'vong123@gmail.com'),
('KH003', '060200932410', N'Lê Hải',N'Phong', '2000-apr-30', 1,'0146945291',N'phong@gmail.com'),
('KH004', '830299823499', N'Dương',N'Quá','1999-jun-1', 1, '0763198452',N'qua@gmail.com'),
('KH005', '627368999123', N'Trung Thảo',N'Hoa', '1968-aug-9', 0, '0874867666',N'hoa27@gmail.com'),
('KH006', '650884000234', N'Trung Thảo',N'Cháy', '1984-sep-7', 1, '0906867266',N'chay@gmail.com'),
('KH007', '890903005985', N'Trung Thanh',N'Hoa', '2003-may-24', 0,'0938867266',N'be@gmail.com'),
('KH008', '089291008006', N'Trung Thảo',N'Đa', '1991-nov-22', 1, '0834867266',N'da@gmail.com'),
('KH009', '015400230001', N'Hoa Lang',N'Vắng', '2000-oct-13', 0, '0202486726',N'vang@gmail.com'),
('KH010', '056389319341', N'Phạm Thùy',N'Nhi', '1989-may-24', 0, '0927191123',N'nhi11@gmail.com'),
('KH011', '093396999831', N'Lương Thế', N'Tùng', '1996-dec-31', 1, '0908518123', N'thetung@gmail.com'),
('KH012', '083277000876', N'Mã Văn', N'Mai', '1977-jan-24', 1, '0938410344', N'maizz@gmail.com'),
('KH013', '023269887123', N'Trương Vân', N'Tấn', '1969-feb-22', 1, '0648826391', N'tanbru@gmail.com'),
('KH014', '087403000295', N'Lê Thị Tuyết', N'Vân', '2003-jul-30', 0, '0357294781', N'van0306@gmail.com'),
('KH015', '078902008987', N'Nguyễn Văn', N'Bá', '2002-sep-14', 1, '0708569438', N'badas@gmail.com'),
('KH016', '014299384598', N'Đào Lê', N'Hoa', '1999-aug-3', 0, '0101637842', N'hoahh@gmail.com'),
('KH017', '098295091248', N'Mai Lan', N'Nhớ', '1995-mar-9', 1, '0908046103', N'nhonhung@gmail.com'),
('KH018', '048301002934', N'Trần Thị Vang', N'Lan', '2001-jun-10', 0, '0987000438', N'lanuwu@gmail.com')
go

insert into LoaiPhong values
('PT001', N'Phòng thường', 180000),
('PV001', N'Phòng VIP', 280000)
go

insert into Phong values
('P001', 'PV001', 15, N'Trống', 1),
('P002', 'PV001', 15, N'Trống', 1),
('P003', 'PV001', 15, N'Trống', 1),
('P004', 'PV001', 15, N'Trống', 1),
('P005', 'PV001', 15, N'Trống', 1),
('P006', 'PV001', 15, N'Trống', 1),
('P007', 'PV001', 15, N'Trống', 1),
('P008', 'PV001', 15, N'Trống', 1),
('P009', 'PV001', 15, N'Trống', 1),
('P010', 'PT001', 10, N'Trống', 1),
('P011', 'PT001', 10, N'Trống', 1),
('P012', 'PT001', 10, N'Trống', 1),
('P013', 'PT001', 10, N'Trống', 1),
('P014', 'PT001', 10, N'Trống', 1),
('P015', 'PT001', 10, N'Trống', 1),
('P016', 'PT001', 10, N'Trống', 1),
('P017', 'PT001', 20, N'Trống', 1),
('P018', 'PT001', 20, N'Trống', 1),
('P019', 'PT001', 20, N'Trống', 1),
('P020', 'PT001', 20, N'Trống', 1),
('P021', 'PT001', 20, N'Trống', 1),
('P022', 'PT001', 20, N'Trống', 1)
go
Insert into HoaDon values
('HD001', 'KH001', 'NV002', '2023-oct-14 21:30:00 ', 0.1, 352000, 1),
('HD002', 'KH001', 'NV004', '2023-nov-20 21:30:00 ', 0.1, 869000, 1),
('HD003', 'KH002', 'NV002', '2023-oct-20 19:30:00 ', 0.1, 2145000, 1),
('HD004', 'KH003', 'NV002', '2023-dec-10 18:30:00 ', 0.1, 976800, 1),
('HD005', 'KH004', 'NV002', '2023-sep-2 17:00:00 ', 0.1, 1797400, 1),
('HD006', 'KH005', 'NV004', '2023-oct-11 20:00:00 ', 0.1, 2992000, 1),
('HD007', 'KH006', 'NV002', '2022-sep-2 17:00:00 ', 0.1, 1797400, 1),
('HD008', 'KH007', 'NV004', '2022-oct-11 20:00:00 ', 0.1, 2992000, 1)
go

Insert into ChiTietHoaDon values
('HD001', '2023-oct-14 21:30:00 ', '2023-oct-14 22:30:00','P010'),
('HD002', '2023-nov-20 21:30:00 ', '2023-nov-20 22:30:00','P003'),
('HD003', '2023-oct-20 19:30:00 ', '2023-oct-20 21:30:00','P001'),
('HD003', '2023-oct-20 19:30:00 ', '2023-oct-20 21:30:00','P002'),
('HD004', '2023-dec-25 18:30:00 ', '2023-dec-25 21:30:00','P015'),
('HD005', '2023-sep-2 17:00:00 ', '2023-sep-2 20:00:00','P019'),
('HD006', '2023-oct-11 20:00:00 ', '2023-oct-12 00:00:00','P020'),
('HD006', '2023-oct-11 20:00:00 ', '2023-oct-12 00:00:00','P012'),
('HD007', '2022-sep-2 17:00:00 ', '2022-sep-2 20:00:00','P019'),
('HD008', '2022-oct-11 20:00:00 ', '2022-oct-12 00:00:00','P020'),
('HD008', '2022-oct-11 20:00:00 ', '2022-oct-12 00:00:00','P012')
go

Insert into ChiTietDichVu values
('DV001', 1, 'HD001', 'P010'),
('DV019', 1, 'HD001', 'P010'),
('DV007', 2, 'HD002','P003'),
('DV008',4,'HD002','P003'),
('DV001',1,'HD002', 'P003'),
('DV011',4,'HD002', 'P003'),
('DV020',4,'HD002', 'P003'),
('DV024',1,'HD003','P001'),
('DV013',3,'HD003', 'P001'),
('DV010',2,'HD003', 'P002'),
('DV006',3,'HD003', 'P002'),
('DV005',2,'HD004','P015'),
('DV003',3,'HD004','P015'),
('DV008',3,'HD004','P015'),
('DV015',1,'HD004','P015'),
('DV017',2,'HD004','P015'),
('DV002',1,'HD004','P015'),
('DV012',3,'HD005','P019'),
('DV014',6,'HD005','P019'),
('DV013',6,'HD005','P019'),
('DV010',4,'HD005','P019'),
('DV001',3,'HD005','P019'),
('DV009',8,'HD005','P019'),
('DV007',8,'HD006','P020'),
('DV008',10,'HD006','P020'),
('DV009',5,'HD006','P020'),
('DV022',1,'HD006','P020'),
('DV013',4,'HD006','P012'),
('DV001',4,'HD006','P012'),
('DV011',8,'HD006','P012'),
('DV012',3,'HD007','P019'),
('DV014',6,'HD007','P019'),
('DV013',6,'HD007','P019'),
('DV010',4,'HD007','P019'),
('DV001',3,'HD007','P019'),
('DV009',8,'HD007','P019'),
('DV007',8,'HD008','P020'),
('DV008',10,'HD008','P020'),
('DV009',5,'HD008','P020'),
('DV022',1,'HD008','P020'),
('DV013',4,'HD008','P012'),
('DV001',4,'HD008','P012'),
('DV011',8,'HD008','P012')
go

Insert into PhieuDatPhong values
	('PDP001', 'KH001', 'NV002', 'P010', '2023-oct-14 21:20:00', '2023-oct-14 21:30:00', 1),
	('PDP002', 'KH001', 'NV004', 'P003', '2023-nov-20 21:20:00', '2023-nov-20 21:30:00', 1),
	('PDP003', 'KH002', 'NV002', 'P001', '2023-oct-20 19:20:00', '2023-oct-20 19:30:00', 1),
	('PDP004', 'KH002', 'NV002', 'P002', '2023-oct-20 19:20:00', '2023-oct-20 19:30:00', 1),
	('PDP005', 'KH003', 'NV002', 'P015', '2023-dec-25 18:20:00', '2023-dec-25 18:30:00', 1),
	('PDP006', 'KH004', 'NV002', 'P019', '2023-sep-2 16:50:00', '2023-sep-2 17:00:00', 1),
	('PDP007', 'KH005', 'NV004', 'P020', '2023-oct-11 19:50:00', '2023-oct-11 20:00:00', 1),
	('PDP008', 'KH005', 'NV004', 'P012', '2023-oct-11 19:50:00', '2023-oct-11 20:00:00', 1),
	('PDP009', 'KH006', 'NV002', 'P019', '2022-sep-2 16:50:00', '2022-sep-2 17:00:00', 1),
	('PDP010', 'KH007', 'NV004', 'P020', '2022-oct-11 19:50:00', '2022-oct-11 20:00:00', 1),
	('PDP011', 'KH007', 'NV004', 'P012', '2022-oct-11 19:50:00', '2022-oct-11 20:00:00', 1)
go

--select * from NhanVien
--select * from DichVu
--select * from KhachHang
--select * from phong
--select * from LoaiPhong
--select * from phieuDatPhong
--select * from HoaDon
--select * from ChiTietHoaDon
--select * from ChiTietDichVu

/*ct dich vu 1 hoa don*/
--select dv.tenDV as 'Dịch vụ/Phòng', donViBan as 'Đơn vị tính', donGia as 'Đơn giá', ctdv.soLuong as 'Số lượng/Giờ', dv.donGia * ctdv.soLuong as 'Thành tiền'
--from ChiTietHoaDon cthd join HoaDon hd on cthd.maHD = hd.maHD join ChiTietDichVu ctdv on hd.maHD = ctdv.mahd join DichVu dv on ctdv.maDV = dv.maDV
--where hd.maHD = 'HD008'
--Union
--select cthd.maPhong as 'Dịch vụ/Phòng', 'Đơn vị tính'= N'giờ', giaTien as 'Đơn giá', cthd.thoiLuong, giaTien*CONVERT(float,  REPLACE(CONVERT(VARCHAR(2), hd.gioKetThuc - hd.gioNhanPhong, 108),':','')) AS 'Thành tiền'
--from ChiTietHoaDon cthd join HoaDon hd on cthd.maHD = hd.maHD join Phong p on cthd.maPhong = p.maPhong join LoaiPhong lp on p.maLP = lp.maLP
--where hd.maHD = 'HD008'
--go

/*phong trong 1 hoa don*/
--select hd.maHD, cthd.maPhong, cthd.thoiLuong, giaTien as gia1Phong, giaTien*CONVERT(float,  REPLACE(CONVERT(VARCHAR(2), hd.gioKetThuc - hd.gioNhanPhong, 108),':','')) AS TongTienPhong
--from ChiTietHoaDon cthd join HoaDon hd on cthd.maHD = hd.maHD join Phong p on cthd.maPhong = p.maPhong join LoaiPhong lp on p.maLP = lp.maLP
--where hd.maHD = 'HD003'
--go
--REPLACE(CONVERT(VARCHAR(8), GETDATE(), 108),':','') AS [StringVersion], CONVERT(INT, REPLACE( CONVERT(VARCHAR(8), GETDATE(), 108),':','')) AS [IntVersion]

/*Phong, Loai phong*/
--select maPhong, tenLoaiPhong, sucNguoi, giaTien, trangThai, tinhTrang
--from Phong p join LoaiPhong lp on p.maLP = lp.maLP
--go

/*Thong ke theo khach hang*/
--select k.maKH, k.hoKH, k.tenKH, k.namSinhKH, count(hd.maHD) as soLuongHoaDon, sum(tongTien) as tongTien
--from  KhachHang k join HoaDon hd on k.maKH = hd.maKH
--where year(hd.gioKetThuc) = '2023'
----where month(hd.gioKetThuc) = '10' and year(hd.gioKetThuc) = '2023'
--group by hd.maKH, k.maKH, k.hoKH, k.tenKH, k.namSinhKH
--order by tongTien desc
--go

/*Thống kê theo ngày trong tuần. vd: Các ngày trong tuần t2 của tháng 10 năm 2023*/
--select format(gioKetThuc, 'dd/MM/yyyy') as Ngay, COUNT(maHD) as soLuongHD, SUM(tongTien) as DoanhThu
--from  HoaDon
--where FORMAT(gioKetThuc, 'MM/yyyy') = '10/2023' and (day(datediff(d,0,gioKetThuc)/7*7)-1)/7+1 = 2
--group by format(gioKetThuc, 'dd/MM/yyyy')
----where DATENAME(dw, hd.gioKetThuc)+', '+FORMAT(hd.gioKetThuc, 'dd/MM/yyyy') = 'Monday, 25/12/2023'
--go

/* Thống kê theo tuần trong tháng. VD: Các tuần trong tháng 10 năm 2023*/
--select (day(datediff(d,0,gioKetThuc)/7*7)-1)/7+1 as Tuan, COUNT(maHD) as soLuongHD, SUM(tongTien) as DoanhThu
--from  HoaDon
--where FORMAT(gioKetThuc, 'MM/yyyy') = '10/2023'
--group by (day(datediff(d,0,gioKetThuc)/7*7)-1)/7+1
--go

/*Thống kê theo tháng trong năm. VD: các tháng trong năm 2023*/
--select FORMAT(gioKetThuc, 'MM/yyyy') as Tháng, COUNT(maHD) as soLuongHD, SUM(tongTien) as DoanhThu
--from  HoaDon
--where FORMAT(gioKetThuc, 'yyyy') = '2023'
--group by FORMAT(gioKetThuc, 'MM/yyyy')
--go

/*Thống kê theo các năm*/
--select FORMAT(gioKetThuc, 'yyyy') as Năm, COUNT(maHD) as soLuongHD, SUM(tongTien) as DoanhThu
--from  HoaDon
--group by FORMAT(gioKetThuc, 'yyyy')
--go
