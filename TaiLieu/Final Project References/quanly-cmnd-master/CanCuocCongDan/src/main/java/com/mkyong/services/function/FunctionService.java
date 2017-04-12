package com.mkyong.services.function;


import java.io.InputStream;
import java.util.Calendar;
import java.util.List;







import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Chung.Huyen;
import bean.Chung.Mail;
import bean.Chung.TaiKhoan;
import bean.Chung.Tinh;
import bean.Chung.Xa;
import bean.Vu.CCCD;
import bean.Vu.TTDKCCCD;



public interface FunctionService {
	
	HttpSession setSession(HttpSession session, TaiKhoan taiKhoan);
	String getNgayHienTai();
	long getMaXacNhan(); 
	Calendar truThu7ChuNhat(Calendar ngay);
	//Kiem tra xem no co phai la Thu bay voi Chu nhat hay khong (Tin viet)
	Boolean ktT7CN(Calendar ngay) ;
	//Kiem tra xem ngay dau vao co phai la ngay nghi hay khong (Tin viet)
	Boolean ktNgayNghi(Calendar ngay);
	String getNgayHen(int sz, int soDonTrongNgay, String ngayTruoc);
	InputStream getPathImage(HttpSession session, HttpServletRequest request, String pathFile);
	String taoPassword();
	Boolean sendMail(Mail mail) ;
	//Dinh dang lai ngay thanh ngay-thang-nam,

	String dinhDangNgay(String namThangNgay);
	Boolean checkEmpty(String input);
	String getSelectTinh(String maTinh, List<Tinh> dsTinh);
	String getSelectHuyen(String maHuyen, List<Huyen> dsHuyen);
	String getSelectXa(String maXa, List<Xa> dsXa);
	String getScriptHinhTronThongKe(String data, String element);
	String getScriptHinhCotThongKe(String data, String element);
	String getScriptHinhDuongThongKe(String data, String element, String title, int maximum);
	String taoMaDuyetTTDKCCCD(TTDKCCCD cccd, String matKhau);
	String taoMaDuyetCCCD(CCCD cccd, String matKhau);
	String taoMaNhapCCCD(CCCD cccd);
	String docMotSo(int so);
	String docHaiSo(int so);
	String docNgay(int ngay);
	String docThang(int thang);
	String docNam(int so);
	String docNgayThangNam(String ngayThangNam);
}
