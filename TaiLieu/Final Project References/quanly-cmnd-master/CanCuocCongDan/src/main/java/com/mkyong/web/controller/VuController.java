package com.mkyong.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Constant.Const;
import bean.An.SoHoKhau;
import bean.Chung.DanToc;
import bean.Chung.HonNhan;
import bean.Chung.Huyen;
import bean.Chung.KhaiSinh;
import bean.Chung.NhomMau;
import bean.Chung.TaiKhoan;
import bean.Chung.Tinh;
import bean.Chung.Xa;
import bean.Chung.YeuCau;
import bean.Config.ConfigCCCD;
import bean.Config.ConfigNgayNghi;
import bean.Vu.CCCD;
import bean.Vu.TTDKCCCD;
import bean.Vu.TheCMT;

import com.mkyong.services.CCCDService;
import com.mkyong.services.QuyenService;
import com.mkyong.services.TTDKCCCDService;
import com.mkyong.services.ConfigCCCDService;
import com.mkyong.services.ConfigNgayNghiService;
import com.mkyong.services.DanTocService;
import com.mkyong.services.HonNhanService;
import com.mkyong.services.TaiKhoanService;
import com.mkyong.services.TheCMTService;
import com.mkyong.services.HuyenService;
import com.mkyong.services.KhaiSinhService;
import com.mkyong.services.NhomMauService;
import com.mkyong.services.SoHoKhauService;
import com.mkyong.services.TinhService;
import com.mkyong.services.XaService;
import com.mkyong.services.YeuCauService;
import com.mkyong.services.function.FunctionService;



@Controller
public class VuController {
	@Autowired
	private CCCDService cccdService;
	@Autowired
	private TTDKCCCDService ttdkCCCDService;
	@Autowired
	private KhaiSinhService khaiSinhService;
	@Autowired
	private DanTocService danTocService;
	@Autowired
	private TinhService tinhService;
	@Autowired
	private HuyenService huyenService;
	@Autowired
	private XaService xaService;
	@Autowired
	private YeuCauService yeuCauService;
	@Autowired
	private NhomMauService nhomMauService;
	@Autowired
	private SoHoKhauService shkService;
	@Autowired
	private TheCMTService theCMTService;
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private ConfigCCCDService ccService;
	@Autowired
	private ConfigNgayNghiService ngayNghiService;
	@Autowired
	private HonNhanService honNhanService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private KhaiSinhService ksService;
	@Autowired
	private QuyenService quyenService;
	
	@RequestMapping(value = "/ket-qua-dang-ky", method = RequestMethod.POST)
	public ModelAndView dangkyCCCD(Integer yeuCau, String maSoKhaiSinh, String hoTenKhac,
		 String tonGiao, String nhomMau,
		 String thuongTruTinh, String thuongTruHuyen,
		 String oTinh, String oHuyen, String oXa,
		 String ngheNghiep, String trinhDoHocVan,
		 String soCC,
		 String maXacNhan, String noiDKLVTinh, String noiDKLVHuyen,
		 HttpSession session, HttpServletRequest request,
		 HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		try{
			if(soCC == null){
				soCC = "000000000000";
			}
			soCC = soCC.replaceAll("-", "");
			if(session.getAttribute("maXacNhan").toString().equals(maXacNhan)){
			int maSo = 0;
			List<TTDKCCCD> ttdkCCCD = ttdkCCCDService.getDSTTDKCCCD();
			TTDKCCCD cccd = new TTDKCCCD();
			int maxMaSo = ttdkCCCDService.getMaxMaSo();
			if(maxMaSo > 0){
				maSo = maxMaSo + 1;
			} else {
				maSo = 1;
			}
			try{
				
			cccd.setHinhAnh(null);
			} catch(NullPointerException e){
				cccd.setHinhAnh(null);
			}
			cccd.setMaSo(maSo);
			
			cccd.setSoKhaiSinh(maSoKhaiSinh.replaceAll("-", ""));
			
			cccd.setMaYeuCau(yeuCau);
			
			cccd.setSoCC(soCC);
			
			KhaiSinh khaiSinh = khaiSinhService.getKhaiSinhBangSoKS(maSoKhaiSinh.replaceAll("-", ""));
			cccd.setHoTen(khaiSinh.getHoTen());
			
			cccd.setHoTenKhac(hoTenKhac);
			
			cccd.setNgaySinh(khaiSinh.getNgaySinh());
			
			cccd.setGioiTinh(khaiSinh.getGioiTinh());
			
			if(tonGiao.equals("") || tonGiao == null){
				tonGiao = "Không";
			}
			cccd.setMaDT(khaiSinh.getDanToc().getMaDT());
			
			cccd.setTonGiao(tonGiao);
			
			cccd.setQuocTich(khaiSinh.getQuocTich());
			
			cccd.setNhomMau(nhomMau);
			
			cccd.setNoiOHienTai(oXa);
			
			cccd.setNgheNghiep(ngheNghiep);
			
			cccd.setTrinhDo(trinhDoHocVan);		
			
			cccd.setSoCCDD(khaiSinh.getSoCCNguoiYeuCau());
			
			
			if(yeuCau == 1){
				cccd.setLanCap("0");
			} else {
				List<CCCD> cmnd = cccdService.getCCCD(soCC);
				int n = Integer.parseInt(cmnd.get(0).getLanCap())+1;
				String lanCap = ""+n;
				cccd.setLanCap(lanCap);
			}
			
			// Khai báo cơ quan đăng ký làm việc
			String noiDKLamViec = "";
				// biến sử dụng cho giấy hẹn
			String diaDiem = "Tại Ủy Ban ";
			String giayTo = "";
			String thoiGian = "";
			// lấy Tỉnh và Huyện trong CSDL
			Tinh tinh = tinhService.getTinhBangMa(noiDKLVTinh);
			Huyen huyen = huyenService.getHuyenBangMa(noiDKLVHuyen);
			// Xử lý dữ liệu địa Điểm
			if(noiDKLVHuyen.equals("0")){
				noiDKLamViec = noiDKLVTinh;
				diaDiem += tinh.getTenTinh();
			} else {
				noiDKLamViec = noiDKLVHuyen;
				diaDiem += huyen.getTenHuyen() + " - " + tinh.getTenTinh();
			}
			cccd.setNoiDKLamViec(noiDKLamViec);
			// Xử lý dữ liệu giấy Tờ
			YeuCau dsYeuCau = yeuCauService.getYeuCauID(yeuCau);
			giayTo = dsYeuCau.getGiayTo();
			// Xử lý ngày hẹn
				// lay gioi han don trong ngay
			ConfigCCCD cc = ccService.getConfigCCCD();
			Date d = new Date();
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(d);
			int namDK = calendar.get(Calendar.YEAR);
			int thangDK = calendar.get(Calendar.MONTH)+1;
			String thangDangKy = ""+thangDK;
			if(thangDK < 10){
				thangDangKy = "0"+thangDK;
			}
			int ngayDK = calendar.get(Calendar.DAY_OF_MONTH);
			int gioDK = calendar.get(Calendar.HOUR_OF_DAY);
			int phutDK = calendar.get(Calendar.MINUTE);
			int giayDK = calendar.get(Calendar.SECOND);
			String checkNgayDK = ""+ngayDK;
			if(checkNgayDK.length() < 2){
				checkNgayDK = "0"+ngayDK;
			}
			String thoiGianDK = checkNgayDK+"-"+thangDangKy+"-"+namDK+" "+gioDK+":"+phutDK+":"+giayDK;
			cccd.setNgayDK(thoiGianDK);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
			if(calendar.get(Calendar.DAY_OF_WEEK) == 7){
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+2);
			}
			if(calendar.get(Calendar.DAY_OF_WEEK) == 1){
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
			}
			thoiGian = calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
			if(ttdkCCCD.size() > 0){
				List<TTDKCCCD> ngayHen = ttdkCCCDService.getDSNgayHen(noiDKLamViec,ttdkCCCD.get(ttdkCCCD.size()-1).getNgayHen());
				if (ngayHen.size() > 0){
					thoiGian = functionService.getNgayHen(ngayHen.size(), cc.getSoHoSo1Ngay(), ngayHen.get(0).getNgayHen());
					} 
			}
			thoiGian = this.truNgayNghi(thoiGian);
			String[] xThoiGian = thoiGian.split("-");
			String ngayHen = xThoiGian[0];
			if(Integer.parseInt(xThoiGian[0]) < 10){
				if(ngayHen.length() < 2){
					ngayHen = "0"+xThoiGian[0];
				}
			}
			String thangHen = xThoiGian[1];
			if(Integer.parseInt(xThoiGian[1]) < 10){
				if(thangHen.length() < 2){
					thangHen = "0"+xThoiGian[1];
				}
			}
			cccd.setHinhAnh(functionService.getPathImage(session, request, "dang-ky/"+session.getAttribute("maXacNhan")+"hinhAnh.png"));
			cccd.setNgayHen(ngayHen+"-"+thangHen+"-"+xThoiGian[2]);
			// thiết lập các biến cho giấy hẹn
			session.setAttribute("maSo", maSo);
			session.setAttribute("hoTen", cccd.getHoTen());
			session.setAttribute("ngaySinh", cccd.getNgaySinh());
			session.setAttribute("gioiTinh", cccd.getGioiTinh());
			session.setAttribute("thoiGian", ngayHen+"-"+thangHen+"-"+xThoiGian[2]);
			session.setAttribute("hetHan", cc.getHanHoSo());
			session.setAttribute("diaDiem", diaDiem);
			session.setAttribute("giayTo", giayTo);
			
			ttdkCCCDService.insertTTDKCCCD(cccd);
			session.setAttribute("maXacNhan", functionService.getMaXacNhan());
			model.setViewName("GiayHenCCCD");
			return model;
			} else {
				model.setViewName("DangKyCCCD");
				model.addObject("resultCaptcha", "<script>alert('Mã xác nhận không chính xác');</script>");
				return model;
			}
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu + "loi: "+ e.getMessage());
			model.setViewName("ThongBaoLoi");
			return model;
		}

	}
	public String truNgayNghi(String ngayThangNam){
		String[] data = ngayThangNam.split("-");
		Calendar calendar = GregorianCalendar.getInstance();
		String ketQua = "";
		calendar.set(Calendar.DAY_OF_MONTH, (Integer.parseInt(data[0])));
		calendar.set(Calendar.MONTH, Integer.parseInt(data[1])-1);
		calendar.set(Calendar.YEAR, Integer.parseInt(data[2]));
		List<ConfigNgayNghi> ngayNghi = ngayNghiService.getDSNgayNghi();
		for(int i =0; i < ngayNghi.size(); i++){
			if(calendar.get(Calendar.DAY_OF_MONTH) == ngayNghi.get(i).getNgay() &&
					calendar.get(Calendar.MONTH) == (ngayNghi.get(i).getThang()-1)){
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
			}
		}
		ketQua = calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
		return ketQua;
	}
	
	
	@RequestMapping (value = "/quan-tri"  ,produces = "text/plain;charset=UTF-8")
	public ModelAndView quanTri(HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(session.getAttribute("ssTaiKhoan").toString());
			String coQuan = "";
			int lengthCoQuan = taiKhoan.getCoQuan().length();
			if( lengthCoQuan == 2){
				coQuan = tinhService.getTinhBangMa(taiKhoan.getCoQuan()).getTenTinh();
			} else if(lengthCoQuan == 3) {
				Huyen h = huyenService.getTinhHuyenBangMa(taiKhoan.getCoQuan());
				coQuan = h.getTenHuyen()+", "+h.getTinh().getTenTinh();
			} else if(lengthCoQuan == 5){
				Xa x = xaService.getXaHuyenTinh(taiKhoan.getCoQuan());
				coQuan = x.getTenXa()+", "+x.getHuyen().getTenHuyen()+", "+x.getHuyen().getTinh().getTenTinh();
			} else {
				coQuan = "Chưa có nơi làm việc";
			}
			model.addObject("coQuan", coQuan);
			model.addObject("taiKhoan", taiKhoan);
			model.setViewName("QuanTri");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/danh-sach-the-can-cuoc-cong-dan"  ,produces = "text/plain;charset=UTF-8")
	public ModelAndView dsTheCMTCCCD(){
		ModelAndView model = new ModelAndView();
		try{
			List<TheCMT> theCMT = theCMTService.getDSThe();
			String html  = this.createTableTheCMT(theCMT);
			model.addObject("dsTheCC", html);
			model.setViewName("DanhSachTheCanCuoc");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/danh-sach-lam-the-can-cuoc-cong-dan"  ,produces = "text/plain;charset=UTF-8")
	public ModelAndView lamTheCMTCCCD(){
		ModelAndView model = new ModelAndView();
		try{
			List<TheCMT> theCMT = theCMTService.getDSChuaLamThe();
			String html  = this.createTableTheCMT(theCMT);
			model.addObject("dsLamTheCCCD", html);
			model.setViewName("DanhSachLamThe");
		} catch(Exception e){
			System.out.println(e.getMessage());
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/danh-sach-tra-the-can-cuoc-cong-dan"  ,produces = "text/plain;charset=UTF-8")
	public ModelAndView traTheCMTCCCD(){
		ModelAndView model = new ModelAndView();
	
			List<TheCMT> theCMT = theCMTService.getDSTheChuaTra();
			String html = this.createTableTheCMT(theCMT);
			model.addObject("dsChuaTraTheCCCD", html);
			model.setViewName("DanhSachTraThe");
		
		return model;
	}
	
	@RequestMapping (value = "/da-lam-the", method = RequestMethod.GET)
	public ModelAndView daLamThe(String stt){
		ModelAndView model = new ModelAndView();
		try{
			String set = " SET DA_LAM = 1";
			String where = " WHERE STT = "+stt;
			theCMTService.updateThe(set, where);
			model.setViewName("DanhSachTraThe");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/tra-the-cmt", method = RequestMethod.GET)
	public ModelAndView  traTheCMT(String stt, String nguoiNhan, HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "IN_TRA_THE")){
				String set = " SET DA_TRA = 1, NGUOI_NHAN = '"+nguoiNhan+"',"
						+ " NGUOI_TRA = '" + session.getAttribute("ssHoTen") + "',"
						+ " NGAY_TRA = '"+functionService.getNgayHienTai()+"'";
				String where = " WHERE STT = "+stt;
				theCMTService.updateThe(set, where);
			} else {
				
			}
		} catch(Exception e){
			
		}
		return model;
	}
	@RequestMapping(value = "/tim-kiem-the-cmt", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemThe(String tuKhoa, HttpSession session){
		String html = "";
		if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "IN_TRA_THE")){
			List<TheCMT> theCMT = theCMTService.timKiemThe(tuKhoa);
			html = this.createTableTheCMT(theCMT);
		} else {
			html = Const.loiQuyen;
		}
		return html;
	}
	
	
	@RequestMapping(value = "/tim-kiem-the-cmt-chua-lam", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemTheChuaLam(String tuKhoa, HttpSession session){
		String html = "";
		if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "IN_TRA_THE")){
			List<TheCMT> theCMT = theCMTService.timKiemTheChuaLam(tuKhoa);
			html = this.createTableTheCMT(theCMT);
		} else {
			html = Const.loiQuyen;
		}
		return html;
	}
	
	@RequestMapping(value = "/tim-kiem-the-cmt-chua-tra", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemTheChuaTra(String tuKhoa, HttpSession session){
		String html = "";
		if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "IN_TRA_THE")){
			List<TheCMT> theCMT = theCMTService.timKiemTheChuaTra(tuKhoa);
			html = this.createTableTheCMT(theCMT);
		} else {
			html = Const.loiQuyen;
		}
		return html;
	}
	
	public String createTableTheCMT(List<TheCMT> theCMT){
		String html = "";
		int i;
		
			if(theCMT.size() > 0){
				for(i = 0; i < theCMT.size(); i++){
					CCCD cccd = cccdService.getCCCDBangMa(theCMT.get(i).getSoCC());
					SoHoKhau shk = shkService.getSoKSBangSoCC(cccd.getSoCC());
					KhaiSinh khaiSinh = khaiSinhService.getKhaiSinhBangSoKS(shk.getSoKS());
					html += "<tr>"
							+ "<td>"+theCMT.get(i).getMaSo()+"</td>"
							+ "<td>"+cccd.getSoCC()+"</td>"
							+ "<td>"+cccd.getHoTen()+"</td>"
							+ "<td>"+cccd.getGioiTinh()+"</td>"
							+ "<td>"+cccd.getNgaySinh()+"</td>"
							+ "<td>"+khaiSinh.getDanToc().getTenDT()+"</td>"
							+ "<td>"+theCMT.get(i).getHanSD()+"</td>"
							+ "<td><a href='the?soCC="+cccd.getSoCC()+"' target=_blank >Xem</a></td>"
							+ "</tr>";
				}
			} else {
				html += "<td colspan='9'>Không có thẻ</td>";
			}
		
		return html;
	}
	
	@RequestMapping (value = "/open-webcam")
	public ModelAndView webCam(){
		ModelAndView model = new ModelAndView();
		model.setViewName("webcam");
		return model;
	}
	
	
	@RequestMapping (value = "/xem-thong-tin-cccd")
	public ModelAndView xemHoSoCCCD(String soCC){
		ModelAndView model = new ModelAndView();
		model.setViewName("ThongTinCCCD");
		try{
			CCCD cccd = cccdService.getCCCDBangMa(soCC);
			HonNhan honNhan = honNhanService.getHonNhanBangSoCC(soCC);
			String tinhTrangHN = "Chưa kết hôn";
			if(honNhan != null){
				tinhTrangHN = honNhan.getTinhTrangHN().getTenTinhTrang();
			}
			SoHoKhau shk = shkService.getSoKSBangSoCC(cccd.getSoCC());
			KhaiSinh khaiSinh = khaiSinhService.getKhaiSinhBangSoKS(shk.getSoKS());
			Xa xa = xaService.getXaHuyenTinh(khaiSinh.getQueQuan());
			Tinh noiCap = tinhService.getTinhBangMa(cccd.getNoiCap());
			model.addObject("title", "THÔNG TIN CMND/CCCD - "+soCC);
			model.addObject("cccd", cccd);
			model.addObject("honNhan", tinhTrangHN);
			model.addObject("danToc", khaiSinh.getDanToc());
			model.addObject("queQuan", xa.getTenXa()+", "+xa.getHuyen().getTenHuyen()+", "+xa.getHuyen().getTinh().getTenTinh());
			model.addObject("noiCap", noiCap);
		} catch(NullPointerException e){
			model.addObject("title", "LỖI");
			model.addObject("thongBaoLoi", "Số CMND/CCCD không tồn tại hoặc chưa nhập khai sinh");
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	
	@RequestMapping (value = "/nhap-can-cuoc-cong-dan", method = RequestMethod.GET)
	public ModelAndView nhapCanCuoc(){
		ModelAndView model = new ModelAndView();
		try{
			List<DanToc> dsDanToc = danTocService.getDSDanToc();
			model.addObject("dsDanToc", dsDanToc);
			
			List<Tinh> dsTinh = tinhService.getDSTinh();
			model.addObject("dsTinh", dsTinh);
			
			List<NhomMau> dsNhomMau = nhomMauService.getDSNhomMau();
			model.addObject("dsNhomMau", dsNhomMau);
			model.setViewName("NhapCanCuoc");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	@RequestMapping (value = "/nhap-can-cuoc-cong-dan", method = RequestMethod.POST)
	public ModelAndView xuLyNhapCanCuoc(
			HttpSession session, HttpServletRequest request, HttpServletResponse response,
			String soCC, String soKhaiSinh, String hoTenKhac,
			String tonGiao, String nhomMau,
			String trinhDo, String ngheNghiep, String dacDiem,
			String hienTaiXa,
			String noiCapTinh, String ngayCap,
			String nguoiCap, String lanCap, String tinhTrang,
			@ModelAttribute("hinhThe") MultipartFile hinhThe,
			@ModelAttribute("vanTayTroTrai") MultipartFile vanTayTroTrai,
			@ModelAttribute("vanTayTroPhai") MultipartFile vanTayTroPhai
			){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "NHAP_CCCD")){
				try {
					uploadFile( hinhThe.getBytes(), "cap-nhat/",soCC+"hinhThe.png");
					uploadFile( vanTayTroTrai.getBytes(), "cap-nhat/",soCC+"vanTayTroTrai.png");
					uploadFile( vanTayTroPhai.getBytes(), "cap-nhat/",soCC+"vanTayTroPhai.png");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TTDKCCCD cccd = new TTDKCCCD();
				int maSo;
				int maxMaSo = ttdkCCCDService.getMaxMaSo();
				if(maxMaSo > 0){
					maSo = maxMaSo + 1;
				} else {
					maSo = 1;
				}
				cccd.setMaSo(maSo);
				cccd.setSoKhaiSinh(soKhaiSinh.replaceAll("-", ""));
				cccd.setHinhThe(null);
				cccd.setVanTayTroTrai(null);
				cccd.setVanTayTroPhai(null);
				if(!hinhThe.isEmpty()){
					cccd.setHinhThe(functionService.getPathImage(session, request, "cap-nhat/"+soCC+"hinhThe.png"));
				}
				if(!vanTayTroTrai.isEmpty()){
					cccd.setVanTayTroTrai(functionService.getPathImage(session, request, "cap-nhat/"+soCC+"vanTayTroTrai.png"));
				}
				if(!vanTayTroPhai.isEmpty()){
					cccd.setVanTayTroPhai(functionService.getPathImage(session, request, "cap-nhat/"+soCC+"vanTayTroPhai.png"));
				}
				cccd.setSoCC(soCC.replaceAll("-", ""));
				
				cccd.setHoTenKhac(hoTenKhac);
				
				cccd.setNhanDang(dacDiem);
				
				cccd.setTonGiao(tonGiao);
				cccd.setNhomMau(nhomMau);
				cccd.setTrinhDo(trinhDo);
				cccd.setNgheNghiep(ngheNghiep);
				
				String[] ncap = ngayCap.split("-");
				cccd.setLanCap(lanCap);
				cccd.setNgayCap(ncap[2]+"-"+ncap[1]+"-"+ncap[0]);
				cccd.setNoiCap(noiCapTinh);
				cccd.setNguoiDuyet(nguoiCap.replaceAll("-", ""));
				cccd.setTinhTrang(tinhTrang);
				cccd.setNoiOHienTai(hienTaiXa);
				cccd.setMaDuyet(functionService.taoMaDuyetTTDKCCCD(cccd, session.getAttribute("ssMatKhau").toString()));
				if(cccdService.nhapCCCD(cccd)){
					SoHoKhau shk = shkService.getSoCCBangSoKS(soKhaiSinh.replaceAll("-", ""));
					shk.setSoCC(soCC.replaceAll("-", ""));
					if(shkService.updateSoCC(shk)){
						model.addObject("msg", "Nhập thành công số CMND/CCCD: "+soCC);
					} else {
						model.addObject("error", "Lỗi thêm vào hộ khẩu");
					}
					
				} else {
					model.addObject("error", "Nhập thất bại");
				}
				List<DanToc> dsDanToc = danTocService.getDSDanToc();
				model.addObject("dsDanToc", dsDanToc);
				
				List<Tinh> dsTinh = tinhService.getDSTinh();
				model.addObject("dsTinh", dsTinh);
				
				List<NhomMau> dsNhomMau = nhomMauService.getDSNhomMau();
				model.addObject("dsNhomMau", dsNhomMau);
				model.setViewName("NhapCanCuoc");
			} else {
				model.addObject("thongBaoLoi", Const.loiQuyen);
				model.setViewName("ThongBaoLoi");
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "/get-so-cccd", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getSoCC(String soCC){
		List<CCCD> cccd = cccdService.getCCCD(soCC.replaceAll("-", ""));
		String ketQua = "1";
		if(!cccd.isEmpty()){
			ketQua = "0";
		}
		return ketQua;
	}
	@RequestMapping(value = "/get-so-khai-sinh-dang-ky", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getSoKS(String soKS){
		List<TTDKCCCD> cccdTam = ttdkCCCDService.getThongTinDangKy();
		String ketQua = "1";
		for(int i = 0; i < cccdTam.size(); i++){
			if(soKS.replaceAll("-", "").equals(cccdTam.get(i).getSoKhaiSinh())){
				ketQua = "0";
			}
		}
		return ketQua;
	}
	@RequestMapping(value = "/get-socc-dang-ky", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getSoCCDangKy(String soKS){
		SoHoKhau shk = shkService.getSoCCBangSoKS(soKS.replaceAll("-", ""));
		String ketQua = "1";
		if(shk !=null){
			if((shk.getSoCC().equals("")) || shk.getSoCC() == null){
				ketQua = "1";
			} else {
				ketQua = "0";
			}
		} else if(shk == null){
			ketQua = "-1";
		}
		return ketQua;
	}
	
	@RequestMapping(value = "/kiem-tra-cc-ks", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String kiemTraCCKS(String soKS, String soCC){
		SoHoKhau shk = shkService.getSoCCBangSoKS(soKS.replaceAll("-", ""));
		String ketQua = "1";
		if(shk !=null){
			if(!soCC.replaceAll("-", "").equals(shk.getSoCC())){
				ketQua = "0";
			}
		} else {
			ketQua = "0";
		}
		return ketQua;
	}
	
	@RequestMapping(value = "/get-thong-tin-khai-sinh", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getThongTinKhaiSinh(String soKS){
		KhaiSinh khaiSinh = khaiSinhService.getKhaiSinhBangSoKS(soKS);
		System.out.println("So ks: " + soKS);
		String result = "";
		try{
			bean.Vu.CCCD cha = cccdService.getCCCDBangMa(khaiSinh.getSoCCCha());
			bean.Vu.CCCD me = cccdService.getCCCDBangMa(khaiSinh.getSoCCMe());
			SoHoKhau shk = shkService.getSoHoKhau("A.SO_KS = '" +soKS+"'");
			System.out.println("so ho khau: " + shk.getSoHK());
			SoHoKhau ch = shkService.getSoHoKhau("A.QUAN_HE = 'chuho' AND A.SO_HK = "+shk.getSoHK());
			bean.Vu.CCCD chuHo = cccdService.getCCCDBangMa(ch.getSoCC()); 
			String hoTenCha = " ", hoTenMe = " ";
			if(cha != null){
				hoTenCha  = cha.getHoTen();
			}
			if(me != null){
				hoTenMe = me.getHoTen();
			}
			result += khaiSinh.getHoTen()+"_"+khaiSinh.getNgaySinh()+"_"+khaiSinh.getGioiTinh()+"_"+khaiSinh.getQuocTich()
					+ "_"+khaiSinh.getDanToc().getTenDT()
					+ "_"+hoTenCha
					+ "_"+hoTenMe
					+ "_"+chuHo.getHoTen()
					+ "_"+shk.getSoHK();
		} catch(NullPointerException e){
			result = e.getMessage();
		}
		return result;
	}
	
	@RequestMapping(value = "/get-thong-tin-cccd-nhap-khai-sinh", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getThongTinCCCDNhapKhaiSinh(String soCC){
		CCCD cccd = cccdService.getCCCDBangMa(soCC);
		String result = "";
		try{
			result = cccd.getHoTen()+"_"
					+cccd.getNgaySinh()+"_"
					+cccd.getGioiTinh()+"_"
					+cccd.getQuocTich();
		} catch(NullPointerException e){
		}
		return result;
	}
	
	
	@RequestMapping (value = "/in-giay-hen", method = RequestMethod.GET)
	public ModelAndView inGiayHenCCCD( HttpSession session, HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		model.addObject("print", "print");
		model.setViewName("GiayHenCCCD");
		return model;
	}
	@RequestMapping (value = "/test", method = RequestMethod.GET)
	public ModelAndView test( HttpSession session, HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		String html = "<option>1</option><option>2</option>";
		session.setAttribute("html", html);
		model.setViewName("test");
		return model;
	}
	@RequestMapping (value = "/test1")
	public ModelAndView xuLyTest(String testChoi){
		ModelAndView model = new ModelAndView();
		System.out.println("Du lieu" + testChoi);
		String data = "data: [";
		//List<bean.Chung.DanSoVung> dsDanSoVung = khaiSinhService.getDSDanSoVung(2016, 7);
//		for (int i = 0; i < dsDanSoVung.size(); i++) {
//			data += "{value: "+dsDanSoVung.get(i).getDanSo()+", label: '"+dsDanSoVung.get(i).getVung().getTenVung()+"'},";
//		}
		data += "]";
		String hinhTronThongKe = "";
		hinhTronThongKe = functionService.getScriptHinhTronThongKe(data, "hinhTron");
		model.addObject("hinhTronThongKe", hinhTronThongKe);
		model.setViewName("test");
		return model;
	}
	@RequestMapping(value = "/danh-sach-dang-ky", method = RequestMethod.GET)
	public ModelAndView dsttdkCCCD(HttpSession session, String ngay) {
		ModelAndView model = new ModelAndView();
		try{
			List<bean.Vu.TTDKCCCD> ttdkCCCD = null;
			if(ngay == null){
				ngay = functionService.getNgayHienTai();
			}
			ttdkCCCD = ttdkCCCDService.getDSCanBoXacNhanTheoNgay(ngay);
			String html = this.createBangCCCDChuaXacNhan(ttdkCCCD, session);
			model.addObject("ngayHen", ngay);
			model.addObject("dsLamCCCD", html);
			model.setViewName("DanhSachTTDKCCCD");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	@RequestMapping (value = "/tim-kiem-don-dang-ky", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemDonDangKy(String tuKhoa, HttpSession session){
		List<bean.Vu.TTDKCCCD> cccdTam = ttdkCCCDService.timkiemTTDKCCCD(tuKhoa);
		String html = this.createBangCCCDChuaXacNhan(cccdTam, session);
		return html;
	}
	
	
	public String createBangCCCDChuaXacNhan(List<TTDKCCCD> ttdkCCCD, HttpSession session){
		String html = "";
		int i;
		try{
			String taiKhoan = session.getAttribute("ssTaiKhoan").toString();
			if(ttdkCCCD != null && ttdkCCCD.size()>0){
				for(i = 0; i < ttdkCCCD.size(); i++){
					String noiDKLV = "";
					String maNoiDKLV = ttdkCCCD.get(i).getNoiDKLamViec();
					String donMoi = "";
					if(maNoiDKLV.length() == 2){
						Tinh tinh = tinhService.getTinhBangMa(maNoiDKLV);
						noiDKLV = tinh.getTenTinh();
					} else {
						Huyen huyen = huyenService.getTinhHuyenBangMa(maNoiDKLV);
						noiDKLV = huyen.getTenHuyen()+", "+huyen.getTinh().getTenTinh();
					}
					if(!ttdkCCCDService.xemTTDKCCCD(ttdkCCCD.get(i).getMaSo(), taiKhoan)){
						donMoi = "<sup style='color:red;'>Mới</sup>";
					}
					int maSo = ttdkCCCD.get(i).getMaSo();
					String hoTen = ttdkCCCD.get(i).getHoTen();
					String ngayDK = ttdkCCCD.get(i).getNgayDK();
					html += "<div class='col-md-12'>"
							+"<div class='row-danh-sach'>"
							+"<a href='xac-nhan-thong-tin-dang-ky-cccd?maSo="+maSo+"' >"
							+"<table class='table table-bordered table-striped'>"
							+"<tbody><tr>"
	       					+"<td class='w40 ma-so-chua-duyet text-center'>"
	       					+" 	<span class='mt5' style='color:#fff'>"+maSo+"</span><br>"
	                        +"  <span class='fa fa-exclamation' style='color:#fff'></span></td>"
		                    +"<td style='border-right-color: transparent'>"
		                    +"  <b class=name >"+hoTen+" "+donMoi+"</b>"
		                    +"  <p class=time><i>Được  nộp  vào ngày "+ngayDK+" (tại ủy ban "+noiDKLV+")</i></p></td>"
		                    +"<td style='width: 101px;'>"
		                    +"  <p><span class='label label-default'>Chưa xác nhận </span></p>"
		                    + " <p class='mt5 time' ></span></p>"
		                    +"</tr></tbody></table></a></div></div>";
				}
				html += "<input type='hidden' name='soDong' id='soDong' value="+i+" />";
			} else {
				html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có đơn nào</div></div>";
			}
		} catch(Exception e){
			html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có đơn nào</div></div>";
		}
		return html;
	}
	
	@RequestMapping(value = "/danh-sach-dang-ky-da-xac-nhan", method = RequestMethod.GET)
	public ModelAndView dsTKDKDaXacNhan(HttpSession session, String ngay) {
		ModelAndView model = new ModelAndView();
		try{
			List<bean.Vu.TTDKCCCD> ttdkCCCD = null;
			if(ngay == null){
				ngay = functionService.getNgayHienTai();
			}
			ttdkCCCD = ttdkCCCDService.getDSCanBoDaXacNhan();
			String html = this.createBangCCCDDaXacNhan(ttdkCCCD, session);
			model.addObject("ngayHen", ngay);
			model.addObject("dsCCCDDaXacNhan", html);
			model.setViewName("DanhSachTTDKCCCDDaXacNhan");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu + " - " +e.getMessage());
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	@RequestMapping (value = "/tim-kiem-don-dang-ky-da-xac-nhan", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemDonDangKyDaXacNhan(String tuKhoa, HttpSession session){
		List<bean.Vu.TTDKCCCD> cccdTam = ttdkCCCDService.timkiemTTDKCCCDDaXacNhan(tuKhoa);
		String html = this.createBangCCCDDaXacNhan(cccdTam, session);
		return html;
	}
	
	
	public String createBangCCCDDaXacNhan(List<TTDKCCCD> ttdkCCCD, HttpSession session){
		String html = "";
		int i;
		try{
			if(ttdkCCCD != null && ttdkCCCD.size()>0){
				for(i = 0; i < ttdkCCCD.size(); i++){
					String noiDKLV = "";
					String maNoiDKLV = ttdkCCCD.get(i).getNoiDKLamViec();
					if(maNoiDKLV.length() == 2){
						Tinh tinh = tinhService.getTinhBangMa(maNoiDKLV);
						noiDKLV = tinh.getTenTinh();
					} else {
						Huyen huyen = huyenService.getTinhHuyenBangMa(maNoiDKLV);
						noiDKLV = huyen.getTenHuyen()+", "+huyen.getTinh().getTenTinh();
					}
					int maSo = ttdkCCCD.get(i).getMaSo();
					String hoTen = ttdkCCCD.get(i).getHoTen();
					String ngayDK = ttdkCCCD.get(i).getNgayDK();
					String colorMaSo = "ma-so-chua-duyet";
					String iconMaSo = "fa-exclamation";
					String colorRow = "";
					String colorLabel = "label-default";
					String txt = "Chờ duyệt";
					String underTXT = "fa fa-undo";
					String lyDo = "";
					if(ttdkCCCD.get(i).getHoTenKhac() == null){
						ttdkCCCD.get(i).setHoTenKhac("");
					}
					if(ttdkCCCD.get(i).getDuyet() == 3){
						colorMaSo = "ma-so-da-duyet";
						iconMaSo = "fa-check";
						colorRow = "";
						colorLabel = "label-success";
						txt = "Đã duyệt";
						underTXT = "time";
					}
					if(ttdkCCCD.get(i).getDuyet() == 4){
						colorMaSo = "ma-so-bi-tu-choi";
						iconMaSo = "fa-times";
						colorLabel = "label-danger";
						txt = "Bị từ chối";
						underTXT = "time";
						lyDo = " (Lý do từ chối: "+ttdkCCCD.get(i).getLyDo()+")";
					}
					html += "<div class='col-md-12'>"
							+"<div class='row-danh-sach'>"
							+"<a href='xem-thong-tin-dang-ky-can-cuoc-cong-dan?maSo="+maSo+"' >"
							+"<table class='table table-bordered table-striped'>"
							+"<tbody><tr "+colorRow+">"
	       					+"<td class='w40 "+colorMaSo+" text-center'>"
	       					+" 	<span class='mt5' style='color:#fff'>"+maSo+"</span><br>"
	                        +"  <span class='fa "+iconMaSo+"' style='color:#fff'></span></td>"
		                    +"<td style='border-right-color: transparent'>"
		                    +"  <b class=name >"+hoTen+lyDo+" </b>"
		                    +"  <p class=time><i>Được  nộp  vào ngày "+ngayDK+" (tại ủy ban "+noiDKLV+")</i></p></td>"
		                    +"<td style='width:95px' class='text-center'>"
		                    +"  <p><span class='label "+colorLabel+"'>"+txt+"</span></p>"
		                    +""
		                    +"</tr></tbody></table></a>"
		                    + "<p style='position: relative;margin-top: -30px;width: 60px;float:right;'>"
		                    + "<a title='Rút đơn' class='fa-rut-don' onclick='rutDon("+maSo+")'><span class='mt5 "+underTXT+"' ></span></a>"
		                    + "</p>"
		                    + "</div></div>";
				}
				html += "<input type='hidden' name='soDong' id='soDong' value="+i+" />";
			} else {
				html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có đơn nào</div></div>";
			}
		} catch(Exception e){
			html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có đơn nào</div></div>";
		}
		return html;
	}
	@RequestMapping (value = "/xem-thong-tin-dang-ky-can-cuoc-cong-dan", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView  xemDon(int maSo, HttpSession session){
		ModelAndView model = new ModelAndView();
		model = this.createChiTietDon(maSo,session);
		model.addObject("msg", "Đã xác nhận");
		model.setViewName("XemTTDKCCCD");
		return model;
	}
	@RequestMapping(value = "/in-thong-tin-dang-ky-can-cuoc-cong-dan", method = RequestMethod.GET)
	public ModelAndView inTTDKCCCD(int maSo, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model = this.createChiTietDon(maSo,session);
		model.setViewName("InTTDKCCCD");
		return model;
	}
	
	@RequestMapping(value = "/xac-nhan-thong-tin-dang-ky-cccd", method = RequestMethod.GET)
	public ModelAndView trangCTCCCDTam(int maSo, HttpSession session) {
		ModelAndView model = new ModelAndView();
		try{
			model = this.createChiTietDon(maSo,session);
			String taiKhoan = session.getAttribute("ssTaiKhoan").toString();
			if(ttdkCCCDService.xemTTDKCCCD(maSo, taiKhoan)){
				
			} else {
				ttdkCCCDService.daXemTTDKCCCD(maSo, taiKhoan);
			}
			model.addObject("msg", "Chờ xác nhận");
			model.setViewName("XacNhanTTDKCCCD");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	public ModelAndView createChiTietDon(int maSo, HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			Xa xa;
			Tinh tinh;
			String maSoTTDK = ""+maSo;
			bean.Vu.TTDKCCCD ttdkCCCD = ttdkCCCDService.getTTDKCCCDBangMa(maSoTTDK);
			KhaiSinh khaiSinh = khaiSinhService.getKhaiSinhBangSoKS(ttdkCCCD.getSoKhaiSinh());
			model.addObject("nguoiXacNhan", ttdkCCCD.getNguoiKiemTra());
			
			model.addObject("maSo", maSo);
			
			model.addObject("title",ttdkCCCD.getHoTen().toUpperCase()+" - MÃ SỐ " + maSo);
			
			model.addObject("hoTen", ttdkCCCD.getHoTen());
			
			model.addObject("hoTenKhac", ttdkCCCD.getHoTenKhac());
			
			model.addObject("ngaySinh", ttdkCCCD.getNgaySinh());
			
			model.addObject("gioiTinh", ttdkCCCD.getGioiTinh());
			
			List<NhomMau> nhomMau = nhomMauService.getNhomMauBangMa(ttdkCCCD.getNhomMau());
			
			model.addObject("nhomMau", nhomMau.get(0).getTenNM());
			
			if(ttdkCCCD.getSoCC().equals("000000000000") || ttdkCCCD.getSoCC() == null || ttdkCCCD.getSoCC().equals("")){
				model.addObject("soCC", "Cấp mới");
			} else {
				model.addObject("soCC", ttdkCCCD.getSoCC());
			}
			
			YeuCau yeuCau = yeuCauService.getYeuCauID(ttdkCCCD.getMaYeuCau());
			model.addObject("yeuCau", yeuCau.getTenYeuCau());
			
			model.addObject("ngayDK", ttdkCCCD.getNgayDK());
			
			model.addObject("danToc", khaiSinh.getDanToc().getTenDT());
			
			model.addObject("tonGiao", ttdkCCCD.getTonGiao());
			
			model.addObject("quocTich", ttdkCCCD.getQuocTich());
			
			if(khaiSinh.getNoiCap().length() > 2){
				xa = xaService.getXaHuyenTinh(khaiSinh.getNoiCap());
				model.addObject("khaiSinh", xa.getTenXa()+", "+xa.getHuyen().getTenHuyen()+", "+xa.getHuyen().getTinh().getTenTinh());
			} else {
				tinh = tinhService.getTinhBangMa(khaiSinh.getNoiCap());
				model.addObject("khaiSinh", tinh.getTenTinh());
			}
			xa = xaService.getXaHuyenTinh(khaiSinh.getQueQuan());
			model.addObject("queQuan", xa.getTenXa()+", "+xa.getHuyen().getTenHuyen()+", "+xa.getHuyen().getTinh().getTenTinh());
		
			xa = xaService.getXaHuyenTinh(ttdkCCCD.getNoiOHienTai());
			model.addObject("noiOHienTai", xa.getTenXa()+", "+xa.getHuyen().getTenHuyen()+", "+xa.getHuyen().getTinh().getTenTinh());
		
			model.addObject("hocVan", ttdkCCCD.getTrinhDo());
			
			model.addObject("ngheNghiep", ttdkCCCD.getNgheNghiep());
			CCCD cccdCha = cccdService.getCCCDBangMa(khaiSinh.getSoCCCha());
			CCCD cccdMe = cccdService.getCCCDBangMa(khaiSinh.getSoCCMe());
			
			if(cccdCha != null){
				model.addObject("hoTenCha", cccdCha.getHoTen());
				model.addObject("quocTichCha", cccdCha.getQuocTich());
				if(khaiSinh.getSoCCCha().equals("000000000000") || khaiSinh.getSoCCCha().equals("")){
					model.addObject("soCCCha", null);
				} else {
				model.addObject("soCCCha", khaiSinh.getSoCCCha());
				}
			}
			
			if(cccdMe != null){
				model.addObject("hoTenMe", cccdMe.getHoTen());
				model.addObject("quocTichMe", cccdMe.getQuocTich());
				if(khaiSinh.getSoCCMe().equals("000000000000") || khaiSinh.getSoCCMe().equals("")){
					model.addObject("soCCMe", null);
				} else {
				model.addObject("soCCMe", khaiSinh.getSoCCMe());
				}
			}
			
			if((!ttdkCCCD.getSoCCDD().equals("000000000000")) && (ttdkCCCD.getSoCCDD() != null)){
				List<bean.Vu.CCCD> cccdDD = cccdService.getCCCD(ttdkCCCD.getSoCCDD());
				if((!cccdDD.isEmpty())){
					model.addObject("hoTenDD", cccdDD.get(0).getHoTen());
					model.addObject("quocTichDD", cccdDD.get(0).getQuocTich());
					if(ttdkCCCD.getSoCCDD().equals("000000000000")){
						model.addObject("soCCDD", null);
					} else {
					model.addObject("soCCDD", ttdkCCCD.getSoCCDD());
					}
				}
			}
			SoHoKhau shk = shkService.getSoHoKhau("SO_KS = " +ttdkCCCD.getSoKhaiSinh());
			SoHoKhau ch = shkService.getSoHoKhau("QUAN_HE = 'chuho' AND A.SO_HK = "+shk.getSoHK());
			
			model.addObject("thuongTru", shk.getDiaChi());
		
			CCCD cccChuHo = cccdService.getCCCDBangMa(ch.getSoCC());
			if(cccChuHo != null){
				model.addObject("hoTenChuHo", cccChuHo.getHoTen());
				model.addObject("quocTichChuHo", cccChuHo.getQuocTich());
				model.addObject("soCCChuHo", ch.getSoCC());
			}
			model.addObject("quanHeChuHo", shkService.layTenQuanHe(shk.getQuanHe()));
			
			HonNhan hn = honNhanService.getHonNhanBangSoCC(shk.getSoCC());
			String tinhTrangHN = "Chưa kết hôn";
			if(hn != null){
				tinhTrangHN = hn.getTinhTrangHN().getTenTinhTrang();
				CCCD voChong = null;
				if(hn.getSoCCChong().equals(ttdkCCCD.getSoCC())){
					voChong = cccdService.getCCCDBangMa(hn.getSoCCVo());
				} else {
					voChong = cccdService.getCCCDBangMa(hn.getSoCCChong());
				}
				model.addObject("soCCVoChong", voChong.getSoCC());
				model.addObject("hoTenVoChong", voChong.getHoTen());
				model.addObject("quocTichVoChong", voChong.getQuocTich());
			} else {
					model.addObject("soCCVoChong", null);
			}
			model.addObject("tinhTrangHonNhan", tinhTrangHN);
			
			// kiem tra co chuyen phat khong
			if(ttdkCCCD.getChuyenPhat() == 1){
				session.setAttribute("chuyenPhat", "Có");
			} else {
				session.setAttribute("chuyenPhat", "0");
			}
			// kiem tra ket qua xac minh
			if(ttdkCCCD.getKetQuaXacMinh() != null){
				session.setAttribute("ketQuaXacMinh", ttdkCCCD.getKetQuaXacMinh());
			} else {
				session.setAttribute("ketQuaXacMinh", "0");
			}
			// kiem tra xem day du thong tin chua, du thi cho xac nhan
			if((ttdkCCCD.getHinhThe() == null) || (ttdkCCCD.getVanTayTroPhai() == null) || (ttdkCCCD.getVanTayTroTrai() == null)){
				model.addObject("disabled", "disabled title='Thiếu thông tin'");
			}
			
			model.addObject("btnDuyet", maSo);
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/duyet-thong-tin-dang-ky", method = RequestMethod.GET)
	public ModelAndView duyetDon(String maSo, HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
		String ngayXacNhan = functionService.getNgayHienTai();
		String set = "SET DUYET = '2', NGAY_XAC_NHAN = '"+ngayXacNhan+"' , NGUOI_KIEM_TRA = '" + session.getAttribute("ssHoTen") +"'";
		String where = "WHERE MA_SO = '"+maSo+"'";
		if(ttdkCCCDService.updateTTDKCCCD(set, where)){
			session.setAttribute("msg", "Đã xác nhận thành công đơn đăng ký, mã số: "+maSo);
		} else {
			session.setAttribute("error", "Xác nhận thất bại, hãy thử lại");
		}
		model.setViewName("Temp");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/rut-don-cho-duyet", method = RequestMethod.GET)
	public @ResponseBody String rutDon(String maSo, HttpSession session){
		if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "XAC_NHAN_TTDK_CCCD")){
			String ngayXacNhan = functionService.getNgayHienTai();
			String set = "SET DUYET = '1', NGAY_XAC_NHAN = '"+ngayXacNhan+"' , NGUOI_KIEM_TRA = '" + session.getAttribute("ssHoTen") +"'";
			String where = "WHERE MA_SO = '"+maSo+"'";
			if(ttdkCCCDService.updateTTDKCCCD(set, where)){
				session.setAttribute("msg", "Đã rút thành công đơn đăng ký, mã số: "+maSo);
			} else {
				session.setAttribute("error", "Rút đơn thất bại, hãy thử lại");
			}
		} else {
			session.setAttribute("error", Const.loiQuyen);
		}
		return "1";
	}
	
	@RequestMapping (value = "/cap-nhat-thong-tin-dang-ky", method = RequestMethod.POST ,produces = "text/plain;charset=UTF-8")
	public ModelAndView capNhatTTDK(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
			String maSoTTDK, 
			@ModelAttribute("hinhThe") MultipartFile hinhThe,
			@ModelAttribute("vanTayTroTrai") MultipartFile vanTayTroTrai,
			@ModelAttribute("vanTayTroPhai") MultipartFile vanTayTroPhai,
			String hoTenKhac,
			String dacDiem,
			String maDT,
			String tonGiao,
			String nhomMau,
			String trinhDo,
			String ngheNghiep,
			String noiOHienTaiXa,
			String chuyenPhat,
			String ketQuaXacMinh){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "CAP_NHAT_TTDK_CCCD")){
				try {
					uploadFile( hinhThe.getBytes(), "cap-nhat/",maSoTTDK+"hinhThe.png");
					uploadFile( vanTayTroTrai.getBytes(), "cap-nhat/",maSoTTDK+"vanTayTroTrai.png");
					uploadFile( vanTayTroPhai.getBytes(), "cap-nhat/",maSoTTDK+"vanTayTroPhai.png");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TTDKCCCD cccdTam = ttdkCCCDService.getTTDKCCCDBangMa(maSoTTDK);
				if(!hinhThe.isEmpty()){
					cccdTam.setHinhThe(functionService.getPathImage(session, request, "cap-nhat/"+maSoTTDK+"hinhThe.png"));
				}
				if(!vanTayTroTrai.isEmpty()){
					cccdTam.setVanTayTroTrai(functionService.getPathImage(session, request, "cap-nhat/"+maSoTTDK+"vanTayTroTrai.png"));
				}
				if(!vanTayTroPhai.isEmpty()){
					cccdTam.setVanTayTroPhai(functionService.getPathImage(session, request, "cap-nhat/"+maSoTTDK+"vanTayTroPhai.png"));
				}
				cccdTam.setHoTenKhac(hoTenKhac);
				cccdTam.setNhanDang(dacDiem);
				cccdTam.setTonGiao(tonGiao);
				cccdTam.setNhomMau(nhomMau);
				cccdTam.setTrinhDo(trinhDo);
				cccdTam.setNgheNghiep(ngheNghiep);
				cccdTam.setNoiOHienTai(noiOHienTaiXa);
				cccdTam.setChuyenPhat(0);
				if(chuyenPhat != null){
					cccdTam.setChuyenPhat(1);
				}
				cccdTam.setKetQuaXacMinh(ketQuaXacMinh);
				if(ttdkCCCDService.capNhatTTDKCCCD(cccdTam)){
					session.setAttribute("msg", "Cập nhật thành công ");
				} else {
					session.setAttribute("error", "Cập nhật thất bại");
				}
				try {
					response.sendRedirect("cap-nhat-thong-tin-dang-ky?maSo="+maSoTTDK);
				} catch (IOException e) {
				}
				model.setViewName("Temp");
			} else {
				model.addObject("thongBaoLoi", Const.loiQuyen);
				model.setViewName("ThongBaoLoi");
			}
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/hinh-the-cccd")
	public void ImageCCCD(HttpServletResponse response, String id){  
		CCCD cccd = cccdService.getCCCDBangMa(id); 
		InputStream img;  
		img = cccd.getHinhThe();
		Image(response,img);
	}
	@RequestMapping (value = "/hinh-anh-ttdk")
	public void ImageTTDK(HttpServletResponse response, String id){  
		TTDKCCCD cccd = ttdkCCCDService.getTTDKCCCDBangMa(id);
		InputStream img;  
		img = cccd.getHinhAnh();      
		Image(response,img);
	}
	@RequestMapping (value = "/hinh-the-ttdk")
	public void hinhTheTTDK(HttpServletResponse response, String id){  
		TTDKCCCD cccd = ttdkCCCDService.getTTDKCCCDBangMa(id);
		InputStream img;  
		img = cccd.getHinhThe();      
		Image(response,img);
	}
	@RequestMapping (value = "/van-tay-tro-trai-cccd")
	public void vanTayTroTraiCCCD(HttpServletResponse response, String id){  
		CCCD cccd = cccdService.getCCCDBangMa(id); 
		InputStream img;  
		img = cccd.getVanTayTroTrai();
		Image(response,img);
	}
	@RequestMapping (value = "/van-tay-tro-phai-cccd")
	public void vanTayTroPhaiCCCD(HttpServletResponse response, String id){  
		CCCD cccd = cccdService.getCCCDBangMa(id); 
		InputStream img;  
		img = cccd.getVanTayTroPhai();
		Image(response,img);
	}
	@RequestMapping (value = "/van-tay-tro-trai-ttdk")
	public void vanTayTroTraiTTDK(HttpServletResponse response, String id){  
		TTDKCCCD cccd = ttdkCCCDService.getTTDKCCCDBangMa(id);
		InputStream img;  
		img = cccd.getVanTayTroTrai();
		Image(response,img);
	}
	@RequestMapping (value = "/van-tay-tro-phai-ttdk")
	public void vanTayTroPhaiTTDK(HttpServletResponse response, String id){  
		TTDKCCCD cccd = ttdkCCCDService.getTTDKCCCDBangMa(id);
		InputStream img;  
		img = cccd.getVanTayTroPhai();
		Image(response,img);
	}
	public void Image(HttpServletResponse response, InputStream fileImg){
		 try{
			    //PrintWriter out=response.getWriter();
			 byte[] b = new byte[60000];  
			 int i=0;  
			 InputStream img;  
			 img = fileImg;
			 response.reset();  
			 response.setContentType("image/jpeg");  
			 response.addHeader("Content-Disposition","filename=logo.jpg");  
			 while((i=img.read(b))!= -1 )  
			     {  
			        response.getOutputStream().write(b,0,i);  
			     }  
			        response.flushBuffer(); 
			        img.close();  
			  }
			 catch (Exception e){
			     System.out.println(e.getMessage());
			 }
	}
	// Đưa hình ảnh lên máy chủ
	public @ResponseBody String uploadFile(byte[] data, String mkdir, String tenFile){
		File f = new File("upload");
		String fpath = f.getAbsolutePath()+"/";
		String pathFile = fpath+mkdir;
		File f2 = new File(pathFile);
		if(!f2.exists()){
			f2.mkdirs();
		}
		System.out.println(fpath+mkdir+tenFile);
		 try {
			// Create the file on server
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(pathFile+tenFile));
			stream.write(data);
            stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fpath+mkdir+tenFile;
	}
}
