package com.mkyong.web.controller;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.services.CCCDService;
import com.mkyong.services.QuyenService;
import com.mkyong.services.TTDKCCCDService;
import com.mkyong.services.ConfigCCCDService;
import com.mkyong.services.ConfigEmailService;
import com.mkyong.services.DanTocService;
import com.mkyong.services.HonNhanService;
import com.mkyong.services.HuyenService;
import com.mkyong.services.KhaiSinhService;
import com.mkyong.services.NhomMauService;
import com.mkyong.services.SoHoKhauService;
import com.mkyong.services.TaiKhoanService;
import com.mkyong.services.TheCMTService;
import com.mkyong.services.TinhService;
import com.mkyong.services.XaService;
import com.mkyong.services.YeuCauService;
import com.mkyong.services.function.FunctionService;

import Constant.Const;
import bean.An.DSLamHoKhau;
import bean.An.DSThemNhanKhau;
import bean.An.SoHoKhau;
import bean.Chung.DanToc;
import bean.Chung.HonNhan;
import bean.Chung.Huyen;
import bean.Chung.KhaiSinh;
import bean.Chung.MD5;
import bean.Chung.Mail;
import bean.Chung.NhomMau;
import bean.Chung.TTDKKetHon;
import bean.Chung.TTDKKhaiSinh;
import bean.Chung.TaiKhoan;
import bean.Chung.Tinh;
import bean.Chung.Xa;
import bean.Chung.YeuCau;
import bean.Config.ConfigCCCD;
import bean.Config.ConfigEmail;
import bean.Vu.CCCD;
import bean.Vu.TTDKCCCD;
import bean.Vu.TheCMT;

@Controller
public class MainController {
	/**.
	 *{@cccdService} CCCDService
	 */
	@Autowired
	private CCCDService cccdService;
	@Autowired
	private TTDKCCCDService cccdTamService;
	/**.
	 *{@tinhService} tinhService
	 */
	@Autowired
	private KhaiSinhService khaiSinhService;
	@Autowired
	private TinhService tinhService;
	@Autowired
	private HuyenService huyenService;
	@Autowired
	private XaService xaService;
	@Autowired
	private DanTocService danTocService;
	@Autowired
	private YeuCauService yeuCauService;
	@Autowired
	private NhomMauService nhomMauService;
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private SoHoKhauService shkService;
	@Autowired
	private TheCMTService theCMTService;
	@Autowired
	private ConfigCCCDService ccService;
	@Autowired
	private HonNhanService honNhanService;
	@Autowired
	private ConfigEmailService ceService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private TTDKCCCDService ttdkcccdService;
	@Autowired
	private QuyenService quyenService;
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try{
			List<bean.Vu.TTDKCCCD> thongTinDK = cccdTamService.getDSQuaHan();
			ConfigCCCD cc = ccService.getConfigCCCD();
			for(int i = 0; i < thongTinDK.size(); i++){
				String[] ngayHen = thongTinDK.get(i).getNgayHen().split("-");
				String[] ngayHienTai = functionService.getNgayHienTai().split("-");
				int ngayOfNgayHen = Integer.parseInt(ngayHen[0]);
				int thangOfNgayHen = Integer.parseInt(ngayHen[1]);
				int ngayOfNgayHienTai = Integer.parseInt(ngayHienTai[0]);
				int thangOfNgayHienTai = Integer.parseInt(ngayHienTai[1]);
				if((ngayOfNgayHienTai - ngayOfNgayHen > cc.getHanHoSo()) && (thangOfNgayHienTai >= thangOfNgayHen)){
					cccdTamService.deleteTTDKCCCD(thongTinDK.get(i).getMaSo());
				}
			}
			model.setViewName("index");
		} catch(Exception e){
			e.printStackTrace();
			model.addObject("thongBaoLoi", Const.loiDuLieu + ": " + e.getMessage() + ". " + e.hashCode());
			model.setViewName("ThongBaoLoi");
		}
		return model;

	}
	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage(HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			TaiKhoan taiKhoan;
			taiKhoan = taiKhoanService.getTaiKhoan(userDetail.getUsername());
			session = functionService.setSession(session, taiKhoan);
		}
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("index");
		return model;

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Tài khoản, mật khẩu không chính xác");
		}

		if (logout != null) {
			model.addObject("msg", "Đăng xuất thành công.");
		}
		model.setViewName("login");
		return model;

	}
	@RequestMapping(value = "/dang-xuat")
	public ModelAndView dangXuat(HttpSession session){
		session.removeAttribute("login");
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
	@RequestMapping(value = "/set-session-mat-khau")
	public @ResponseBody String setSessionMatKhau(String matKhau, HttpSession session){
		session.setAttribute("ssMatKhau", matKhau);
		System.out.println(matKhau);
		return "";
	}
	@RequestMapping(value = "/get-thong-tin", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getThongTin(String soCC){
		String result = "";
		try{
			List<bean.Vu.CCCD> cccd = cccdService.getCCCD(soCC);
			if(cccd.size() < 1){
				result = "Không tìm thấy";
			} else {
				result = cccd.get(0).getHoTen();
			}
		} catch(Exception e){
			result = Const.loiDuLieu;
		}
		return result;
	}
		
	
	@RequestMapping(value = "/get-thong-tin-cccd", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getThongTinCCCD(String soCC, HttpSession session){
		String result = "";
		try{
			bean.Chung.CCCD cccd = new bean.Chung.CCCD();
			cccd = cccdService.getCCCD1(soCC);
			SoHoKhau shk = shkService.getSoHoKhau("A.SO_CC = "+soCC);
			String tinNhan = "";
			final String charSplit = "@@";
			if(cccd == null || "".equals(cccd)) {
				tinNhan = "Không tìm thấy kết quả tìm kiếm.";
				session.setAttribute("tinNhan", tinNhan);
			} else {
				result += cccd.getHoTen() + charSplit;
				result += cccd.getThuongTru().getTenXa() + charSplit;
				result += cccd.getThuongTru().getHuyen().getTenHuyen() + charSplit;
				result += cccd.getThuongTru().getHuyen().getTinh().getTenTinh() + charSplit;
				result += cccd.getNoiCap().getTenTinh() + charSplit;
				result += cccd.getNgaySinh() + charSplit;
				result += cccd.getDanToc().getTenDT() + charSplit;
				result += cccd.getQuocTich() + charSplit;
				result += cccd.getGioiTinh();
				result += shk.getDiaChi() + charSplit;
			}
		} catch(Exception e){
		}
		return result;
	}
	@RequestMapping(value = "/get-huyen", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getHuyen(String tinh){
		List<Huyen> dsHuyen = huyenService.getDSHuyen(tinh);
		int len = dsHuyen.size();
		String result = "<option value=0 >Chọn Huyện</option>";
		for(int i = 0; i <= len - 1; i++){
			result += "<option value="+dsHuyen.get(i).getMaHuyen()+">"+dsHuyen.get(i).getTenHuyen()+"</option>";
		}
		return result;
	}
	
	@RequestMapping(value = "/get-xa", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getXa(String huyen){
		List<Xa> dsXa = xaService.getDSXa(huyen);
		int len = dsXa.size();
		String result = "<option value=0 >Chọn Xã</option>";
		for(int i = 0; i <= len - 1; i++){
			result += "<option value="+dsXa.get(i).getMaXa()+">"+dsXa.get(i).getTenXa()+"</option>";
		}
		return result;
	}
	
	@RequestMapping(value = "/dang-ky-lam-can-cuoc-cong-dan", method = RequestMethod.GET)
	public ModelAndView DKTTDKCCCD(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try{
			List<YeuCau> yeuCau = yeuCauService.getDSYeuCauHoatDong();
			model.addObject("dsYeuCau", yeuCau);
			List<Tinh> dsTinh = tinhService.getDSTinh();
			model.addObject("dsTinh", dsTinh);
			List<DanToc> dsDanToc = danTocService.getDSDanToc();
			model.addObject("dsDanToc", dsDanToc);
			List<NhomMau> dsNhomMau = nhomMauService.getDSNhomMau();
			model.addObject("dsNhomMau", dsNhomMau);
			long maXacNhan = functionService.getMaXacNhan();
			session.setAttribute("maXacNhan", maXacNhan);
			model.setViewName("DangKyCCCD");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	
	@RequestMapping(value = "/xoa-thong-tin-dang-ky", method = RequestMethod.GET)
	public ModelAndView xoaTTDKCCCD(int maSo, HttpSession session) {
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "CAP_NHAT_TTDK_CCCD")){
			cccdTamService.deleteTTDKCCCD(maSo);
			List<TTDKCCCD> dsTam = cccdTamService.getDSTTDKCCCD();
			model.addObject("dsTam", dsTam);
			model.setViewName("DanhSachTTDKCCCD");
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
	
	
	
	@RequestMapping(value = "/cap-nhat-thong-tin-dang-ky", method = RequestMethod.GET)
	public ModelAndView capNhatCCCDTam(String maSo, HttpSession session) {
		ModelAndView model = new ModelAndView();
		try{
			bean.Vu.TTDKCCCD cccdTam = cccdTamService.getTTDKCCCDBangMa(maSo);
			// kiem tra don da duoc xac nhan hay chua
			if(cccdTam.getDuyet() > 1){
				model.addObject("error", "Bạn không được cập nhật đơn đã xác nhận");
			} else {
				model.addObject("yes", "yes");
				KhaiSinh khaiSinh = khaiSinhService.getKhaiSinhBangSoKS(cccdTam.getSoKhaiSinh());
				//set chuyen phat
				if(cccdTam.getChuyenPhat() == 1){
					model.addObject("chuyenPhat", "checked");
				}
				Xa queQuan = xaService.getXaHuyenTinh(khaiSinh.getQueQuan());
				Xa noiDKKhaiSinh = xaService.getXaHuyenTinh(khaiSinh.getNoiCap());
				List<NhomMau> nhomMau = nhomMauService.getDSNhomMau();
				SoHoKhau shk = shkService.getSoCCBangSoKS(khaiSinh.getSoKS());
				String nMau = "";
				for(int t = 0; t < nhomMau.size(); t++){
					if(nhomMau.get(t).getMaNM().equals(cccdTam.getNhomMau())){
						nMau += "<option value='"+nhomMau.get(t).getMaNM()+"' selected>"+nhomMau.get(t).getTenNM()+"</option>";
					} else
					nMau += "<option value='"+nhomMau.get(t).getMaNM()+"'>"+nhomMau.get(t).getTenNM()+"</option>";
				}
				
				model.addObject("shk", shk);
				model.addObject("dsDanToc", khaiSinh.getDanToc());
				model.addObject("queQuan", queQuan);
				model.addObject("khaiSinh", noiDKKhaiSinh);
				// thuong tru
				List<Xa> dsXa = null;
				List<Huyen> dsHuyen = null;
				List<Tinh> dsTinh = null;
				
				// noi O hien tai
				Xa hienTaiXa = xaService.getXaHuyenTinh(cccdTam.getNoiOHienTai());
				Huyen hienTaiHuyen = huyenService.getTinhHuyenBangMa(hienTaiXa.getHuyen().getMaHuyen());
				dsXa = xaService.getDSXa(hienTaiXa.getHuyen().getMaHuyen());
				dsHuyen = huyenService.getDSHuyen(hienTaiHuyen.getTinh().getMaTinh());
				dsTinh = tinhService.getDSTinh();
				
				String hienTaiTinh = "";
				for(int t = 0; t < dsTinh.size(); t++){
					if(dsTinh.get(t).getMaTinh().equals(hienTaiHuyen.getTinh().getMaTinh())){
						hienTaiTinh += "<option value='"+dsTinh.get(t).getMaTinh()+"' selected>"+dsTinh.get(t).getTenTinh()+"</option>";
					} else
					hienTaiTinh += "<option value='"+dsTinh.get(t).getMaTinh()+"'>"+dsTinh.get(t).getTenTinh()+"</option>";
				}
				String strHienTaiHuyen = "";
				for(int t = 0; t < dsHuyen.size(); t++){
					if(dsHuyen.get(t).getMaHuyen().equals(hienTaiXa.getHuyen().getMaHuyen())){
						strHienTaiHuyen += "<option value='"+dsHuyen.get(t).getMaHuyen()+"' selected>"+dsHuyen.get(t).getTenHuyen()+"</option>";
					} else
					strHienTaiHuyen += "<option value='"+dsHuyen.get(t).getMaHuyen()+"'>"+dsHuyen.get(t).getTenHuyen()+"</option>";
				}
				String strHienTaiXa = "";
				for(int t = 0; t < dsXa.size(); t++){
					if(dsXa.get(t).getMaXa().equals(cccdTam.getNoiOHienTai())){
						strHienTaiXa += "<option value='"+dsXa.get(t).getMaXa()+"' selected>"+dsXa.get(t).getTenXa()+"</option>";
					} else
					strHienTaiXa += "<option value='"+dsXa.get(t).getMaXa()+"'>"+dsXa.get(t).getTenXa()+"</option>";
				}
				model.addObject("quanHeChuHo", shk.getQuanHe());
				if((cccdTam.getHinhThe() == null) || (cccdTam.getVanTayTroPhai() == null) || (cccdTam.getVanTayTroTrai() == null)){
					model.addObject("disabled", "disabled title='Thiếu thông tin'");
				}
				model.addObject("maSo", maSo);
				model.addObject("hienTaiTinh", hienTaiTinh);
				model.addObject("hienTaiHuyen", strHienTaiHuyen);
				model.addObject("hienTaiXa", strHienTaiXa);
				model.addObject("nm", nMau);
				session.setAttribute("cccdTam", cccdTam);
			}
			model.setViewName("CapNhatTTDKCCCD");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "/thong-tin-tai-khoan", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView  thongTinTaiKhoan(HttpSession session, HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		TaiKhoan taiKhoan = null;
		try{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				taiKhoan = taiKhoanService.getTaiKhoan(userDetail.getUsername());
				session = functionService.setSession(session, taiKhoan);
				if(taiKhoan.getEmail() == null){
					model.addObject("boSungThongTin", 1);
				}
					// kiem tra xem tai khoan co quyen xac nhan ttdk cccd hay khong
					if(quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.XAC_NHAN_TTDK_CCCD) ||
							quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.XEM_DANH_SACH_TTDK_CCCD)){
						// lay so don cho xac nhan ttdk cccd
						String ngayHienTai = functionService.getNgayHienTai();
						List<TTDKCCCD> ttdk = ttdkcccdService.getDSCanBoXacNhanTheoNgay(ngayHienTai);
						session.setAttribute("ssSoDonTTDKCCCDChoXacNhan", ttdk.size());
					}
					// kiem tra xem tai khoan co quyen duyet ttdk cccd hay khong
					if(quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_TTDK_CCCD)){
						// lay so don cho duyet ttdk cccd
						List<TTDKCCCD> choDuyet = ttdkcccdService.getDSGiamDocDuyet();
						session.setAttribute("ssSoDonTTDKCCCDChoDuyet", choDuyet.size());
					}	
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KHAI_SINH_1)) {
						List<TTDKKhaiSinh> dsDKKhaiSinh = khaiSinhService.getDSTTDKKhaiSinh(functionService.getNgayHienTai(), taiKhoan, Const.DUYET_KHAI_SINH_1 , 200);
						int soDonDKKSChoXacNhan = dsDKKhaiSinh.size();
						session.setAttribute("soDonDKKSChoXacNhan", soDonDKKSChoXacNhan);
					}
					
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KHAI_SINH_2)) {
						List<TTDKKhaiSinh> dsDKKhaiSinh = khaiSinhService.getDSTTDKKhaiSinh(functionService.getNgayHienTai(), taiKhoan, Const.DUYET_KHAI_SINH_2 , 200);
						int soDonDKKSChoDuyet = dsDKKhaiSinh.size();
						session.setAttribute("soDonDKKSChoDuyet", soDonDKKSChoDuyet);
					}
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_TACH_HO_KHAU)) {
						String coQuan = session.getAttribute("ssCoQuan").toString();
						List<DSLamHoKhau> dsDKHoKhau = shkService.dsLamHoKhauChuaDuyet(coQuan);
						int soDonLamHoKhau = dsDKHoKhau.size();
						session.setAttribute("soDonLamHoKhau", soDonLamHoKhau);
					}
					
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_THEM_NHAN_KHAU)) {
						String coQuan = session.getAttribute("ssCoQuan").toString();
						List<DSThemNhanKhau> dsThemNhanKhau = shkService.dsDangKyThemNhanKhau(coQuan);
						int sl = 0;
						for(int i = 0 ; i < dsThemNhanKhau.size() ; i++){
							System.out.println(dsThemNhanKhau.get(i).getDuyet());
							if(dsThemNhanKhau.get(i).getDuyet().equals("0") ){
								sl ++;
							}
						}
						session.setAttribute("soDonThemNhanKhau", sl);
					}
					
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_THEM_NHAN_KHAU)) {
						String coQuan = session.getAttribute("ssCoQuan").toString();
						List<DSThemNhanKhau> dsThemNhanKhau = shkService.dsDangKyThemNhanKhau(coQuan);
						int sl = 0;
						for(int i = 0 ; i < dsThemNhanKhau.size() ; i++){
							if(dsThemNhanKhau.get(i).getDuyet().equals("4") ){
								sl ++;
							}
						}
						session.setAttribute("soDonKhongDuyetThemNhanKhau", sl);
					}
					//Tin them
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KET_HON_1)) {
						System.out.println("Vao duyet kh 1");
						List<TTDKKetHon> dsTTDKKetHon = honNhanService.getDSTTDKKetHon(0, taiKhoan.getCoQuan(), functionService.getNgayHienTai(), 200);
						int soDonDKKHChuaXacNhan = dsTTDKKetHon.size();
						System.out.println("So don: " + soDonDKKHChuaXacNhan);
						session.setAttribute("soDonDKKHChuaXacNhan", soDonDKKHChuaXacNhan);
					}
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KET_HON_2)) {
						List<TTDKKetHon> dsTTDKKetHon = honNhanService.getDSTTDKKetHon(1, taiKhoan.getCoQuan(), functionService.getNgayHienTai(), 200);
						int soDonDKKHChuaDuyet = dsTTDKKetHon.size();
						session.setAttribute("soDonDKKHChuaDuyet", soDonDKKHChuaDuyet);
					}
					//Tin moi them vo
					String soCCNguoiDKKH = taiKhoan.getUsername();
					TTDKKetHon ttdkKetHon = new TTDKKetHon();
					List<TTDKKetHon> dsYeuCauKH = new java.util.ArrayList<TTDKKetHon>();
					try {
						ttdkKetHon = honNhanService.getDKKetHonBangSoCCNguoiDK(soCCNguoiDKKH);
						dsYeuCauKH = honNhanService.getDKKetHonBangSoCCVoHoacChong(soCCNguoiDKKH);
					} catch (Exception e) {
						System.out.println("3"+e.getMessage());
					}
					int trangThai = -2;
					String trangThaiDKKH = "Thông báo";
					String linkThongBao = "";
					try {
						if (ttdkKetHon != null) {
							trangThai = ttdkKetHon.getTrangThai();
							if (trangThai == -2) {
								trangThaiDKKH  = "Số " + ttdkKetHon.getSoCCVoHoacChong() + " đã từ chối yêu cầu kết hôn";
							} else if (trangThai == -1) {
								trangThaiDKKH  = "Số " + ttdkKetHon.getSoCCVoHoacChong() + " chưa xác nhận";
							} else if (trangThai == 0){
								trangThaiDKKH = "Số " + ttdkKetHon.getSoCCVoHoacChong() + " đã xác nhận yêu cầu kết hôn của bạn.";
								linkThongBao = "giay-hen-dkkh?soDK=" + ttdkKetHon.getSoDK();
							} else if (trangThai == 1){
								trangThaiDKKH = "Đơn đăng ký kết hôn của bạn đang đợi duyệt";
							} else if (trangThai == 2){
								trangThaiDKKH = "Đơn đăng ký kết hôn của bạn đã được duyệt";
							}
						}
						
						if (dsYeuCauKH != null) {
							trangThaiDKKH  = "Bạn nhận được " + dsYeuCauKH.size() + " yêu cầu kết hôn.";
							linkThongBao = "danh-sach-yeu-cau-ket-hon";
						}
					} catch (Exception e) {
						System.out.println("1"+e.getMessage());
					}
					model.addObject("linkThongBao", linkThongBao);
					model.addObject("trangThaiDKKH", trangThaiDKKH);
					
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.XEM_KHAI_SINH)) {
						List<KhaiSinh> dsKhaiSinh = khaiSinhService.getDSKhaiSinh(taiKhoan, functionService.getNgayHienTai());
						int soKSTRongNgay = dsKhaiSinh.size();
						session.setAttribute("soKSTRongNgay", soKSTRongNgay);
					}
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.XEM_HON_NHAN)) {
						List<HonNhan> dsHonNhan = honNhanService.getDSHonNhan(taiKhoan, functionService.getNgayHienTai(), Const.slDonTrongNgay);
						int soHonNhan = dsHonNhan.size();
						session.setAttribute("soHonNhan", soHonNhan);
					}
					//End Tin
				}
				//Tin them cho nay
				session.setAttribute("taiKhoan", taiKhoan);
				model.addObject("lienKet", "quan-tri");
			try{
				bean.Vu.CCCD cccd = cccdService.getCCCDBangMa(session.getAttribute("ssTaiKhoan").toString());
				if(cccd != null){
					session.setAttribute("ssHoTen", cccd.getHoTen());
					session.setAttribute("ssGioiTinh", cccd.getGioiTinh());
					session.setAttribute("ssNgaySinh", cccd.getNgaySinh());
					session.setAttribute("ssCoQuan", taiKhoan.getCoQuan());
				}
			} catch(Exception e){
				System.out.println("4"+e.getMessage());
				response.sendRedirect("login");
			}
			//Tin them cho nay de xac nhan dang ky ket hon
			String maXacNhanKH = (String) session.getAttribute("maXacNhanKH");
			String soCCNguoiDK  = (String) session.getAttribute("soCCNguoiDK");
			try {
				if (!(functionService.checkEmpty(maXacNhanKH) && functionService.checkEmpty(soCCNguoiDK))) {
					if (honNhanService.xacNhanDK(taiKhoan.getUsername(), maXacNhanKH)) {
						bean.Chung.TTDKKetHon ttdk = new bean.Chung.TTDKKetHon();
						ttdk.setMaXacNhan(Long.valueOf(maXacNhanKH));
						ttdk.setSoCCVoHoacChong(taiKhoan.getUsername());
						ttdk.setSoCCNguoiDK(soCCNguoiDK);
						if (honNhanService.capNhatTrangThai(ttdk, 0)) {
							String thongBao = "Bạn đã chấp nhận yêu cầu kết hôn từ: " + soCCNguoiDK;
							model.addObject("thongBao", thongBao);
							model.setViewName("GiayHenDKKH");
						}
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				model.addObject("thongBaoLoi", Const.loiDuLieu + ": " + e.getMessage() + ". " + e.hashCode());
				model.setViewName("ThongBaoLoi");
			}
			// An gan so ho khau vao session
			try {
				String soCC = session.getAttribute("ssTaiKhoan").toString();
				SoHoKhau shk = shkService.getSoHoKhau("so_cc ="+soCC);
				model.addObject("soHK", shk.getSoHK());
			} catch (Exception e) {
				// TODO: handle exception
			}
			model.setViewName("DangNhap");
		} catch(Exception e){
			System.out.println("2"+e.getMessage());
		}
		
		return model;
	}
	
	//
	@RequestMapping(value = "/doi-mat-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView doiMatKhau(HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			if(session.getAttribute("ssEmail")!= null){
				model.addObject("sendMail", "sendMail();");
			} else {
				session.setAttribute("ssOTP", functionService.getMaXacNhan());
			}
			model.setViewName("DoiMatKhau");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	@RequestMapping(value = "/doi-mat-khau", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView xuLyDoiMatKhau(String matKhauCu,
			String matKhauMoi,
			String xacNhanMatKhauMoi,
			String OTP,
			HttpSession session){
		MD5 md5 = new MD5();
		ModelAndView model = new ModelAndView();
		try{
			if(OTP.equals(session.getAttribute("ssOTP").toString())){
				if(matKhauMoi.equals(xacNhanMatKhauMoi)){
					TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(session.getAttribute("ssTaiKhoan").toString());
					if(taiKhoan.getPassword().equals(md5.encryptMD5(matKhauCu))){
						taiKhoan.setPassword(md5.encryptMD5(matKhauMoi));
						if(taiKhoanService.updateTaiKhoan(taiKhoan)){
							// set lai session mat khau
							session.setAttribute("ssMatKhau", matKhauMoi);
							// lam lai ma duyet moi voi mat khau moi
							if(quyenService.kiemTraQuyenBangTen(taiKhoan.getPassword(), "DUYET_TTDK_CCCD")){
								// cap nhat chu ky cac thong tin dang ky cccd
									//cap nhat don da duoc cap phep va bi tu choi
								List<TTDKCCCD> dsGDDaDuyet = ttdkcccdService.getDSGiamDocDaDuyet();
								List<TTDKCCCD> dsGDTuChoi = ttdkcccdService.getDSGiamDocTuChoi();
								for(int i = 0; i < dsGDDaDuyet.size(); i++){
									String maDuyetMoi = functionService.taoMaDuyetTTDKCCCD(dsGDDaDuyet.get(i), matKhauMoi);
									String set = "SET MA_DUYET = '"+maDuyetMoi+"'";
									String where = "WHERE NGUOI_DUYET = '"+session.getAttribute("ssTaiKhoan")+"'"
											+ " AND MA_SO = '"+dsGDDaDuyet.get(i).getMaSo()+"'";
									// kiem tra don cu co bi sua doi hay khong, neu khong thi doi ma duyet
									if(dsGDDaDuyet.get(i).getMaDuyet().equals(functionService.taoMaDuyetTTDKCCCD(dsGDDaDuyet.get(i), matKhauCu))){
										ttdkcccdService.updateTTDKCCCD(set, where);
									}
								}
								for(int i = 0; i < dsGDTuChoi.size(); i++){
									String maDuyetMoi = functionService.taoMaDuyetTTDKCCCD(dsGDTuChoi.get(i), matKhauMoi);
									String set = "SET MA_DUYET = '"+maDuyetMoi+"'";
									String where = "WHERE NGUOI_DUYET = '"+session.getAttribute("ssTaiKhoan")+"'"
											+ " AND MA_SO = '"+dsGDTuChoi.get(i).getMaSo()+"'";
									// kiem tra don cu co bi sua doi hay khong, neu khong thi doi ma duyet
									if(dsGDTuChoi.get(i).getMaDuyet().equals(functionService.taoMaDuyetTTDKCCCD(dsGDTuChoi.get(i), matKhauCu))){
										ttdkcccdService.updateTTDKCCCD(set, where);
									}
								}
								
								// cap nhat chu ky ben bang cccd
								List<CCCD> cccd = cccdService.getDSCCCDTheoNguoiCap(session.getAttribute("ssTaiKhoan").toString());
								for(int i = 0; i < cccd.size(); i++){
									String maDuyetMoi = functionService.taoMaDuyetCCCD(cccd.get(i), matKhauMoi);
									String set = "SET MA_DUYET = '"+maDuyetMoi+"' ";
									String where = "WHERE NGUOI_DUYET = '"+session.getAttribute("ssTaiKhoan")+"'"
											+ " AND SO_CC = '"+cccd.get(i).getSoCC()+"'";
									// kiem tra don cu co bi sua doi hay khong, neu khong thi doi ma duyet
									if(cccd.get(i).getMaDuyet().equals(functionService.taoMaDuyetCCCD(cccd.get(i), matKhauCu))){
										cccdService.updateCCCDSW(set, where);
									}
								}
							}
							model.addObject("msg", "Đổi mật khẩu thành công");
						} else {
							model.addObject("error", "Đổi mật khẩu thất bại");
						}
					} else {
						model.addObject("error", "Mật khẩu cũ không chính xác");
					}
				} else {
					model.addObject("error", "Xác nhận mật khẩu mới không trùng khớp");
				}
			} else {
				model.addObject("error", "Mã OTP không chính xác");
			}
			model.setViewName("DoiMatKhau");
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/lam-lai-mat-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView lamLaiMatKhau(HttpSession session){
		ModelAndView model = new ModelAndView();
		model.setViewName("QuenMatKhau");
		return model;
	}
	
	@RequestMapping(value = "/lam-lai-mat-khau", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView xuLyLamLaiMatKhau(String USER_NAME,
			 String OTP, HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			MD5 md5 = new MD5();
			if(OTP.equals(session.getAttribute("ssOTP").toString())){
				String taoPassword = functionService.taoPassword();
				String maHoaPassword = md5.encryptMD5(taoPassword);
				TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(USER_NAME);
				taiKhoan.setPassword(md5.encryptMD5(maHoaPassword));
				ConfigEmail ce = ceService.getConfigEmail("quen_mat_khau");
				Mail mail = new Mail();
				mail.setEmailGui(ce.getEmail());
				mail.setMatkhau(ce.getMatKhau());
				mail.setTieuDe(ce.getTieuDeGui());
				mail.setNoiDung(ce.getNoiDungGui()+taoPassword);
				mail.setEmailNhan(taiKhoan.getEmail());
				if(taiKhoanService.updateTaiKhoan(taiKhoan) && functionService.sendMail(mail)){
					model.addObject("msg", "Mật khẩu mới đã được gửi vào Email của bạn, hãy kiểm tra Email.");
				} else {
					model.addObject("error", "Tạo lại mật khẩu mới thất bại, hãy thử lại sau.");
				}
			} else {
				model.addObject("error", "Mã OTP không chính xác");
			}
			model.setViewName("QuenMatKhau");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "/bo-sung-email", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView boSungEmail(HttpSession session, HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(session.getAttribute("ssTaiKhoan").toString());
		if(taiKhoan.getEmail() != null){
			try {
				response.sendRedirect("thay-doi-email");
			} catch(Exception e){
				model.addObject("thongBaoLoi", Const.loiDuLieu);
				model.setViewName("ThongBaoLoi");
			}
		}
		model.addObject("soDienThoai", taiKhoan.getSoDienThoai());
		model.addObject("email", taiKhoan.getEmail());
		model.setViewName("BoSungEmail");
		return model;
	}
	
	@RequestMapping(value = "/bo-sung-email", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView xuLyBoSungEmail(String soDienThoai,
			String email,
			String OTP,
			String matKhau,
			HttpSession session){
		MD5 md5 = new MD5();
		ModelAndView model = new ModelAndView();
		try{
			String error = null;
			String msg = null;
			if(OTP.equals(session.getAttribute("ssOTP").toString())){
				TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(session.getAttribute("ssTaiKhoan").toString());
				if(taiKhoan.getPassword().equals(md5.encryptMD5(matKhau))){
					taiKhoan.setEmail(email);
					if(soDienThoai != null){
						taiKhoan.setSoDienThoai(soDienThoai);
					}
					if(taiKhoanService.updateTaiKhoan(taiKhoan)){
						msg = "Bổ sung Email thành công";
					} else {
						error = "Bổ sung Email thất bại, hãy thử lại";
					}
				} else {
					error = "Mật khẩu không chính xác";
				}
			} else {
				error = "Mã OTP không chính xác";
			}
			model.addObject("soDienThoai", soDienThoai);
			model.addObject("email", email);
			model.addObject("msg", msg);
			model.addObject("error", error);
			model.addObject("script", "$('#form-cap-nhat').addClass('display-none');$('#form-xac-nhan-mail').removeClass('display-none');");
			model.setViewName("BoSungEmail");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "/thay-doi-email", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView thayDoiEmail(HttpSession session, HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(session.getAttribute("ssTaiKhoan").toString());
			if(taiKhoan.getEmail() == null){
				try {
					response.sendRedirect("bo-sung-email");
				} catch(Exception e){
					model.addObject("thongBaoLoi", Const.loiDuLieu);
					model.setViewName("ThongBaoLoi");
				}
			}
			model.addObject("email", taiKhoan.getEmail());
			model.setViewName("ThayDoiEmail");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	
	@RequestMapping(value = "/thay-doi-email", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView xuLyThayDoiEmail(
			String email,
			String OTPXacNhan,
			String matKhau,
			HttpSession session){
		MD5 md5 = new MD5();
		ModelAndView model = new ModelAndView();
		try{
			String error = null;
			String msg = null;
			if(OTPXacNhan.equals(session.getAttribute("ssOTP").toString())){
				TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(session.getAttribute("ssTaiKhoan").toString());
				if(taiKhoan.getPassword().equals(md5.encryptMD5(matKhau))){
					if(email.equals("")){
						email = null;
					}
					taiKhoan.setEmail(email);
					if(taiKhoanService.updateTaiKhoan(taiKhoan)){
						msg = "Thay đổi Email thành công";
					} else {
						error = "Thay đổi Email thất bại, hãy thử lại";
					}
				} else {
					error = "Mật khẩu không chính xác";
				}
			} else {
				error = "Mã OTP không chính xác";
			}
			model.addObject("email", email);
			model.addObject("msg", msg);
			model.addObject("error", error);
			model.addObject("script", "$('#form-cap-nhat').addClass('display-none');"
					+ "$('#form-xac-thuc-mail').addClass('display-none');"
					+ "$('#form-xac-nhan-mail').removeClass('display-none');");
			model.setViewName("ThayDoiEmail");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	@RequestMapping(value = "/change-sdt", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String changeSDT(String sdt, HttpSession session){
		String result = "";
		System.out.println(sdt);
		try{
			TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(session.getAttribute("ssTaiKhoan").toString());
			taiKhoan.setSoDienThoai(sdt);
			if(taiKhoanService.updateTaiKhoan(taiKhoan)){
				result = "Số điện thoại đã được thay đổi";
			} else {
				result = "Thay đổi số điện thoại không thành công";
			}
		} catch(Exception e){
			result = "Lỗi, hãy thử lại";
		}
		return result;
	}
	
	@RequestMapping(value = "/tai-khoan-ton-tai", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String taiKhoanTonTai(String USER_NAME){
		String result = "";
		try{
			TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(USER_NAME);
			if(taiKhoan != null){
				if(taiKhoan.getEmail() == null){
					result = "-1";
				} else {
					result = taiKhoan.getEmail();
				}
			} else {
				result = "0";
			}
		} catch(Exception e){
			result = "0";
		}
		return result;
	}
	
	
	@RequestMapping(value = "/xac-thuc-otp", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String xacNhanOTP(HttpSession session, String OTP){
		String result = "";
		if(session.getAttribute("ssOTP").toString().equals(OTP)){
			result = "1";
		} else {
			result = "0";
		}
		return result;
	}
	
	@RequestMapping(value = "/email-ton-tai", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String emailTonTai(String email,HttpSession session){
		String result = "";
		TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanBangEmail(email);
		if(taiKhoan != null){
			result = "1";
		} else {
			result = "0";
		}
		return result;
	}
	
	
	@RequestMapping(value = "/get-otp", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public long getOTP(HttpSession session){
		long otp = functionService.getMaXacNhan();
		session.setAttribute("ssOTP", otp);
		return otp;
	}
	@RequestMapping(value = "/send-otp", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String sendOTP(String mailNhan,HttpSession session){
		String result = "";
		try{
			ConfigEmail ce = ceService.getConfigEmail("cap_nhat");
			Mail mail = new Mail();
			mail.setEmailGui(ce.getEmail());
			mail.setMatkhau(ce.getMatKhau());
			mail.setTieuDe(ce.getTieuDeGui());
			mail.setNoiDung(ce.getNoiDungGui()+this.getOTP(session));
			mail.setEmailNhan(mailNhan);
			if(functionService.sendMail(mail)){
				result = "Đã gửi mail thành công";
			} else {
				result = "Đã gửi mail thất bại";
			}
		} catch(Exception e){
			System.out.println("Gửi Email thất bại");
		}
		return result;
	}
	@RequestMapping(value = "/the", method = RequestMethod.GET)
	public ModelAndView theCCCD(String soCC){
		ModelAndView model = new ModelAndView();
		try{
			CCCD cccd = cccdService.getCCCDBangMa(soCC);
			SoHoKhau shk = shkService.getSoKSBangSoCC(soCC);
			KhaiSinh khaiSinh = khaiSinhService.getKhaiSinhBangSoKS(shk.getSoKS());
			TheCMT the = theCMTService.getTheBangSoCC(soCC);
			String tinhTrangThe = "";
			String buttonTinhTrang = "";
			String buttonTraThe = "";
			String nguoiNhan = "Chưa có";
			String ngayTra = "Chưa có";
			String nguoiTra = "Chưa có";
			if(the.getDaLam() == 0){
				tinhTrangThe = "<b><font color='#d9534f'>Thẻ chưa sẵn sàng</font></b>";
				buttonTinhTrang = "<button class='btn btn-primary' onclick='daLamThe("+the.getStt()+");'>Sẵn sàng</button>";
				buttonTraThe = "<button class='btn btn-primary' disabled>Trả thẻ</button>";
			} else if(the.getDaTra() == 0){
				tinhTrangThe = "<b><font color='#5cb85c'>Thẻ đã sẵn sàng</font></b>";
				buttonTinhTrang = "";
				buttonTraThe = "<button class='btn btn-primary' onclick='moThongBao("+the.getStt()+")'>Trả thẻ</button>";
			} else {
				tinhTrangThe = "Đã trả thẻ";
				nguoiNhan = the.getNguoiNhan();
				nguoiTra = the.getNguoiTra();
				ngayTra = the.getNgayTra();
			}
			String[] ngaySinh = cccd.getNgaySinh().split("-");
			String[] ngayCap = cccd.getNgayCap().split("-");
			Xa queQuanXa = xaService.getXaHuyenTinh(khaiSinh.getQueQuan());
			Tinh noiCap = tinhService.getTinhBangMa(cccd.getNoiCap());
			String queQuan = queQuanXa.getHuyen().getTinh().getTenTinh();
			DanToc danToc = danTocService.getDanTocID(cccd.getMaDT());
			model.addObject("cccd", cccd);
			model.addObject("matKhau", the.getPassword());
			model.addObject("ngaySinh", ngaySinh[0]+"/"+ngaySinh[1]+"/"+ngaySinh[2]);
			model.addObject("noiCap", noiCap);
			model.addObject("queQuan", queQuan);
			model.addObject("the", the.getHanSD());
			model.addObject("thuongTru", shk.getDiaChi());
			model.addObject("danToc", danToc);
			model.addObject("ngayCap", ngayCap[0]);
			model.addObject("thangCap", ngayCap[1]);
			model.addObject("namCap", ngayCap[2]);
			model.addObject("nguoiCap", cccd.getNguoiDuyet());
			model.addObject("tinhTrangThe", tinhTrangThe);
			model.addObject("btnTinhTrang", buttonTinhTrang);
			model.addObject("btnTraThe", buttonTraThe);
			model.addObject("nguoiNhan", nguoiNhan);
			model.addObject("nguoiTra", nguoiTra);
			model.addObject("ngayTra", ngayTra);
		} catch(Exception e){
			model.addObject("error", "Thẻ này đang trong quá trình xử lý.");
		}
		model.setViewName("TheCCCD");
		return model;
	}
	@RequestMapping(value = "/xay-ra-loi", method = RequestMethod.GET)
	public ModelAndView Error(){
		ModelAndView model = new ModelAndView();
		model.setViewName("ThongBaoLoi");
		return model;
	}
	@RequestMapping(value = "/update-code", method = RequestMethod.GET)
	public ModelAndView updateCode(){
		ModelAndView model = new ModelAndView();
		MD5 md5 = new MD5();
		List<TTDKCCCD> ttdk = ttdkcccdService.getDSTTDKCCCD();
		for(int i = 0; i < ttdk.size(); i++){
			String set = " SET MA_DUYET = '" + functionService.taoMaDuyetTTDKCCCD(ttdk.get(i), md5.encryptMD5("123456")) + "'";
			String where = " WHERE MA_SO = '"+ttdk.get(i).getMaSo() + "'";
			if(ttdk.get(i).getMaSo() != 3){
				ttdkcccdService.updateTTDKCCCD(set, where);
			}
		}
		List<CCCD> cccd = cccdService.getDSCCCD();
		for(int i = 0; i < cccd.size(); i++){
			String maDuyet = "";
			maDuyet = functionService.taoMaNhapCCCD(cccd.get(i));
			String set = " SET MA_DUYET = '" + maDuyet +"'";
			String where = " WHERE SO_CC = '" + cccd.get(i).getSoCC() + "'";
			cccdService.updateCCCDSW(set, where);
		}
		model.addObject("thongBaoLoi", "Updated");
		model.setViewName("ThongBaoLoi");
		return model;
	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}
		
		model.setViewName("403");
		return model;

	}

}