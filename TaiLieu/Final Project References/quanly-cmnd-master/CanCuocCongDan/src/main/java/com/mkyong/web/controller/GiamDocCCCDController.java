package com.mkyong.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.services.CCCDService;
import com.mkyong.services.QuyenService;
import com.mkyong.services.TTDKCCCDService;
import com.mkyong.services.ConfigCCCDService;
import com.mkyong.services.ConfigNgayNghiService;
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
import bean.An.SoHoKhau;
import bean.Chung.DanSoHuyen;
import bean.Chung.HonNhan;
import bean.Chung.Huyen;
import bean.Chung.KhaiSinh;
import bean.Chung.MD5;
import bean.Chung.NhomMau;
import bean.Chung.TaiKhoan;
import bean.Chung.Tinh;
import bean.Chung.Xa;
import bean.Chung.YeuCau;
import bean.Config.ConfigCCCD;
import bean.Vu.CCCD;
import bean.Vu.TTDKCCCD;
import bean.Vu.TheCMT;

@Controller
public class GiamDocCCCDController {
	@Autowired
	private CCCDService cccdService;
	@Autowired
	private TTDKCCCDService TTDKCCCDService;
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
	private FunctionService functionService;
	@Autowired
	private HonNhanService honNhanService;
	@Autowired
	private QuyenService quyenService;
	@RequestMapping (value = "/danh-sach-can-cuoc-cong-dan"  ,produces = "text/plain;charset=UTF-8")
	public ModelAndView dsCCCD(HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			List<CCCD> cccd = cccdService.getDSCCCDTheoTinh();
			String html = this.createBangCCCD(cccd, session);
			model.addObject("dsCCCD", html);
			model.setViewName("DanhSachCCCD");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/tim-kiem-can-cuoc-cong-dan"  ,produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemCCCD(String keySearch, HttpSession session){
		List<CCCD> cccd = cccdService.timKiemCCCD(keySearch);
		String html = this.createBangCCCD(cccd, session);
		return html;
	}
	
	public String createBangCCCD(List<CCCD> cccd, HttpSession session){
		String html = "";
		int i;
		try{
			if(cccd != null && cccd.size()>0){
				for(i = 0; i < cccd.size(); i++){
					String noiCap = "";
					String maNoiCap = cccd.get(i).getNoiCap();
					Tinh tinh = tinhService.getTinhBangMa(maNoiCap);
					noiCap = tinh.getTenTinh();
					String soCC = cccd.get(i).getSoCC();
					String hoTen = cccd.get(i).getHoTen();
					String ngayCap = cccd.get(i).getNgayCap();
					String tinhTrang = "Hoạt động";
					if(cccd.get(i).getTinhTrang().equals("0")){
						tinhTrang = "Bị chặn";
					}
					String colorMaSo = "ma-so-da-duyet";
					String iconMaSo = "fa-check check-ok";
					String colorRow = "";
					String colorLabel = "label-success";
					if(cccd.get(i).getHoTenKhac() == null){
						cccd.get(i).setHoTenKhac("");
					}
					html += "<div class='col-md-12'>"
							+"<div class='row-danh-sach'>"
							+"<a href='xem-thong-tin-cccd?soCC="+soCC+"' target=_blank >"
							+"<table class='table table-bordered table-striped'>"
							+"<tbody><tr "+colorRow+">"
	       					+"<td class='w40 "+colorMaSo+" text-center'>"
	       					+" 	<span class='mt5' style='color:#fff'>"+soCC+"</span><br>"
	                        +"  <span class='fa "+iconMaSo+"' style='color:#fff'></span></td>"
		                    +"<td style='border-right-color: transparent'>"
		                    +"  <b class=name >"+hoTen+" </b>"
		                    +"  <p class=time><i>Ngày cấp "+ngayCap+" (nơi cấp "+noiCap+")</i></p></td>"
		                    +"<td style='width: 101px;'>"
		                    +"  <p><span class='label "+colorLabel+"'>"+tinhTrang+"</span></p>"
		                    + " <p class='mt5 time' ></span></p>"
		                    +"</tr></tbody></table></a></div></div>";
				}
				html += "<input type='hidden' name='soDong' id='soDong' value="+i+" />";
			} else {
				html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có dữ liệu</div></div>";
			}
		} catch(Exception e){
			html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có dữ liệu</div></div>";
		}
		return html;
	}
	
	
	@RequestMapping (value = "/don-dang-ky-cccd-chua-duyet"  ,produces = "text/plain;charset=UTF-8")
	public ModelAndView donDKTTCCCDChuaDuyet(String dsDuyet[], HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			List<TTDKCCCD> TTDKCCCD = TTDKCCCDService.getDSGiamDocDuyet();
			String html = this.createBangCCCDChuaDuyet(TTDKCCCD);
			session.removeAttribute("ssGiamDocCCCD");
			
			model.addObject("dsChuaDuyet", html);
			model.setViewName("DonCCCDChuaDuyet");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/tim-kiem-don-ttdk-cccd-chua-duyet"  ,produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemDonDKTTCCCDChuaDuyet(String keySearch){
		String html = "";
		try{
			List<TTDKCCCD> TTDKCCCD = TTDKCCCDService.timKiemTTDKChuaDuyet(keySearch);
			html = this.createBangCCCDChuaDuyet(TTDKCCCD);
		} catch(Exception e){
			html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có đơn nào</div></div>";
		}
		return html;
	}
	
	
	public String createBangCCCDChuaDuyet(List<TTDKCCCD> TTDKCCCD){
		String html = "";
		int i;
		if(TTDKCCCD != null && TTDKCCCD.size()>0){
			for(i = 0; i < TTDKCCCD.size(); i++){
				String noiDKLV = "";
				String donMoi = "";
				String maNoiDKLV = TTDKCCCD.get(i).getNoiDKLamViec();
				if(maNoiDKLV.length() == 2){
					Tinh tinh = tinhService.getTinhBangMa(maNoiDKLV);
					noiDKLV = tinh.getTenTinh();
				} else {
					Huyen huyen = huyenService.getTinhHuyenBangMa(maNoiDKLV);
					noiDKLV = huyen.getTenHuyen()+", "+huyen.getTinh().getTenTinh();
				}
				if(TTDKCCCD.get(i).getDuyet() == 2){
					donMoi = "<sup style='color:red;'>Mới</sup>";
				}
				int maSo = TTDKCCCD.get(i).getMaSo();
				String hoTen = TTDKCCCD.get(i).getHoTen();
				String ngayXacNhan = TTDKCCCD.get(i).getNgayXacNhan();
				html += "<div class='col-md-12'>"
						+"<div class='row-danh-sach'>"
						+"<a href='chi-tiet-don-chua-duyet?maSo="+maSo+"' >"
						+"<table class='table table-bordered table-striped'>"
						+"<tbody><tr>"
       					+"<td class='w40 ma-so-chua-duyet text-center'>"
       					+" 	<span class='mt5' style='color:#fff'>"+maSo+"</span><br>"
                        +"  <span class='fa fa-exclamation' style='color:#fff'></span></td>"
	                    +"<td style='border-right-color: transparent'>"
	                    +"  <b class=name >"+hoTen+" "+donMoi+"</b>"
	                    +"  <p class=time><i>Được  nộp  vào ngày "+ngayXacNhan+" (tại ủy ban "+noiDKLV+")</i></p></td>"
	                    +"<td style='width: 101px;'>"
	                    +"  <p><span class='label label-default'>Chưa duyệt</span></p>"
	                    + " <p class='mt5 time' ></span></p>"
	                    +"</tr></tbody></table></a></div></div>";
			}
			html += "<input type='hidden' name='soDong' id='soDong' value="+i+" />";
		} else {
			html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có đơn nào</div></div>";
		}
		return html;
	}
	
	@RequestMapping (value = "/don-dang-ky-cccd-da-duyet"  ,produces = "text/plain;charset=UTF-8")
	public ModelAndView donTTDKCCCDDaDuyet(String dsDuyet[], HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			List<TTDKCCCD> TTDKCCCD = TTDKCCCDService.getDSGiamDocDaDuyet();
			String html = this.createBangCCCDDaDuyet(TTDKCCCD, session);
			session.removeAttribute("ssGiamDocCCCD");
			model.addObject("dsDaDuyet", html);
			model.setViewName("DonCCCDDaDuyet");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/tim-kiem-don-ttdk-cccd-da-duyet"  ,produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemDonDKTTCCCDDaDuyet(String keySearch, HttpSession session){
		String html = "";
		try{
			List<TTDKCCCD> TTDKCCCD = TTDKCCCDService.timKiemTTDKDaDuyet(keySearch);
			html = this.createBangCCCDDaDuyet(TTDKCCCD, session);
		} catch(Exception e){
			html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có đơn nào</div></div>";
		}
		return html;
	}
	
	public String createBangCCCDDaDuyet(List<TTDKCCCD> TTDKCCCD, HttpSession session){
		String html = "";
		int i;
		if(TTDKCCCD != null && TTDKCCCD.size()>0){
			for(i = 0; i < TTDKCCCD.size(); i++){
				String noiDKLV = "";
				String maNoiDKLV = TTDKCCCD.get(i).getNoiDKLamViec();
				if(maNoiDKLV.length() == 2){
					Tinh tinh = tinhService.getTinhBangMa(maNoiDKLV);
					noiDKLV = tinh.getTenTinh();
				} else {
					Huyen huyen = huyenService.getTinhHuyenBangMa(maNoiDKLV);
					noiDKLV = huyen.getTenHuyen()+", "+huyen.getTinh().getTenTinh();
				}
				int maSo = TTDKCCCD.get(i).getMaSo();
				String hoTen = TTDKCCCD.get(i).getHoTen();
				String ngayXacNhan = TTDKCCCD.get(i).getNgayXacNhan();
				// kiem tra don co bi sua trai phep khong
				String colorMaSo = "ma-so-da-duyet";
				String iconMaSo = "fa-check check-ok";
				String colorRow = "";
				String txt = "Đã duyệt";
				String colorLabel = "label-success";
				String maDuyet = functionService.taoMaDuyetTTDKCCCD(TTDKCCCD.get(i), session.getAttribute("ssMatKhau").toString());
				if(!maDuyet.equals(TTDKCCCD.get(i).getMaDuyet())){
					colorMaSo = "ma-so-bi-sua-doi";
					iconMaSo = "fa-exclamation-triangle";
					colorRow = "style='background-color:#a53939;'";
					txt = "Bị sửa đổi";
					colorLabel = "label-danger";
				}
				html += "<div class='col-md-12'>"
						+"<div class='row-danh-sach'>"
						+"<a href='chi-tiet-don-da-duyet?maSo="+maSo+"' >"
						+"<table class='table table-bordered table-striped'>"
						+"<tbody><tr "+colorRow+">"
       					+"<td class='w40 "+colorMaSo+" text-center'>"
       					+" 	<span class='mt5' style='color:#fff'>"+maSo+"</span>"
                        +"  <span class='fa "+iconMaSo+"' style='color:#fff'></span></td>"
	                    +"<td style='border-right-color: transparent'>"
	                    +"  <b class=name >"+hoTen+" </b>"
	                    +"  <p class=time><i>Được  nộp  vào ngày "+ngayXacNhan+" (tại ủy ban "+noiDKLV+")</i></p></td>"
	                    +"<td style='width: 101px;'>"
	                    +"  <p><span class='label "+colorLabel+"'>"+txt+"</span></p>"
	                    + " <p class='mt5 time' ></span></p>"
	                    +"</tr></tbody></table></a></div></div>";
			}
			html += "<input type='hidden' name='soDong' id='soDong' value="+i+" />";
		} else {
			html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có đơn nào</div></div>";
		}
		return html;
	}
	
	
	@RequestMapping (value = "/don-dang-ky-cccd-bi-tu-choi"  ,produces = "text/plain;charset=UTF-8")
	public ModelAndView donCCCDBiTuChoi(String dsDuyet[], HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			List<TTDKCCCD> TTDKCCCD = TTDKCCCDService.getDSGiamDocTuChoi();
			String html = this.createBangCCCDBiTuChoi(TTDKCCCD, session);
			session.removeAttribute("ssGiamDocCCCD");
			model.addObject("dsBiTuChoi", html);
			model.setViewName("DonCCCDBiTuChoi");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/tim-kiem-don-ttdk-cccd-bi-tu-choi"  ,produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemDonDKTTCCCDBiTuChoi(String keySearch, HttpSession session){
		String html = "";
		try{
			List<TTDKCCCD> TTDKCCCD = TTDKCCCDService.timKiemTTDKBiTuChoi(keySearch);
			html = this.createBangCCCDBiTuChoi(TTDKCCCD, session);
		} catch(Exception e){
			html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có đơn nào</div></div>";
		}
		return html;
	}
	
	public String createBangCCCDBiTuChoi(List<TTDKCCCD> TTDKCCCD, HttpSession session){
		String html = "";
		int i;
		if(TTDKCCCD != null && TTDKCCCD.size()>0){
			for(i = 0; i < TTDKCCCD.size(); i++){
				String noiDKLV = "";
				String maNoiDKLV = TTDKCCCD.get(i).getNoiDKLamViec();
				if(maNoiDKLV.length() == 2){
					Tinh tinh = tinhService.getTinhBangMa(maNoiDKLV);
					noiDKLV = tinh.getTenTinh();
				} else {
					Huyen huyen = huyenService.getTinhHuyenBangMa(maNoiDKLV);
					noiDKLV = huyen.getTenHuyen()+", "+huyen.getTinh().getTenTinh();
				}
				int maSo = TTDKCCCD.get(i).getMaSo();
				String hoTen = TTDKCCCD.get(i).getHoTen();
				String lyDo = TTDKCCCD.get(i).getLyDo();
				String ngayXacNhan = TTDKCCCD.get(i).getNgayXacNhan();
				// kiem tra don co bi sua trai phep khong
				String colorMaSo = "ma-so-bi-tu-choi";
				String iconMaSo = "fa-times";
				String colorRow = "";
				String txt = "Bị từ chối";
				String colorLabel = "label-danger";
				String maDuyet = functionService.taoMaDuyetTTDKCCCD(TTDKCCCD.get(i), session.getAttribute("ssMatKhau").toString());
				if(!maDuyet.equals(TTDKCCCD.get(i).getMaDuyet())){
					colorMaSo = "ma-so-bi-sua-doi";
					iconMaSo = "fa-exclamation-triangle";
					colorRow = "style='background-color:#a53939;'";
					txt = "Bị sửa đổi";
					colorLabel = "label-danger";
				}
				html += "<div class='col-md-12'>"
						+"<div class='row-danh-sach'>"
						+"<a href='chi-tiet-don-bi-tu-choi?maSo="+maSo+"' >"
						+"<table class='table table-bordered table-striped'>"
						+"<tbody><tr "+colorRow+">"
       					+"<td class='w40 "+colorMaSo+" text-center'>"
       					+" 	<span class='mt5' style='color:#fff'>"+maSo+"</span><br>"
                        +"  <span class='fa "+iconMaSo+"' style='color:#fff'></span></td>"
	                    +"<td style='border-right-color: transparent'>"
	                    +"  <b class=name >"+hoTen+" (Lý do từ chối: "+lyDo+")</b>"
	                    +"  <p class=time><i>Được  nộp  vào ngày "+ngayXacNhan+" (tại ủy ban "+noiDKLV+")</i></p></td>"
	                    +"<td style='width: 101px;'>"
	                    +"  <p><span class='label "+colorLabel+"'>"+txt+"</span></p>"
	                    + " <p class='mt5 time' ></span></p>"
	                    +"</tr></tbody></table></a></div></div>";
			}
			html += "<input type='hidden' name='soDong' id='soDong' value="+i+" />";
		} else {
			html = "<div class='col-md-12 text-center'><div class='row-danh-sach'>Không có đơn nào</div></div>";
		}
		return html;
	}
	
	@RequestMapping (value = "/duyet-don", method = RequestMethod.GET)
	public ModelAndView duyetDon(String maSo, HttpSession session){
		MD5 md5 = new MD5();
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "DUYET_TTDK_CCCD")){
				String hanSuDung = "";
				TTDKCCCD TTDKCCCD = TTDKCCCDService.getTTDKCCCDBangMa(maSo);
				String soCCCu = TTDKCCCD.getSoCC();
				int soLanCap = Integer.parseInt(TTDKCCCD.getLanCap())+1;
				SoHoKhau shk = shkService.getSoCCBangSoKS(TTDKCCCD.getSoKhaiSinh());
				// tao ma duyet - de sau nay kiem tra tinh chinh xac
				String maDuyet = functionService.taoMaDuyetTTDKCCCD(TTDKCCCD, session.getAttribute("ssMatKhau").toString());
				String set = "SET LAN_CAP = '"+soLanCap+"', DUYET = '3', NGUOI_DUYET = '" + session.getAttribute("ssHoTen") +"'"
						+ ", MA_DUYET = '"+maDuyet+"'";
				String where = "WHERE MA_SO = '"+maSo+"'";
				CCCD cccd = cccdService.getCCCDBangMa(shk.getSoCC());
				ConfigCCCD cc = ccService.getConfigCCCD();
				String ngayCap = functionService.getNgayHienTai();
				String password = functionService.taoPassword();
				String md5Password = md5.encryptMD5(md5.encryptMD5(password));
				TheCMT theCMT = new TheCMT();
				TTDKCCCD.setNgayCap(ngayCap);
				theCMT.setMaSo(maSo);
				theCMT.setSoCC(TTDKCCCD.getSoCC());
				//Xử lý hạn sử dụng
				if(cc.getHanSuDung() == 0){
					hanSuDung = "Không thời hạn";
				} else {
					String[] data = ngayCap.split("-"); 
					hanSuDung = data[0]+"-"+data[1]+"-"+(Integer.parseInt(data[2])+cc.getHanSuDung());
				}
				// thiết lập thông tin mặc định cho thẻ cmt
				theCMT.setHanSD(hanSuDung);
				theCMT.setPassword(password);
				theCMT.setNoiTra(TTDKCCCD.getNoiDKLamViec());
				if(cccd == null){
					// tạo số cmnd/cccd và số thẻ cmt
					String soCCMoi = cccdService.sinhMaCanCuoc(TTDKCCCD);
					TTDKCCCD.setSoCC(soCCMoi);
					theCMT.setSoCC(TTDKCCCD.getSoCC());
					//thêm dữ liệu vào cccd
					cccdService.insertCCCD(TTDKCCCD);
					// thiết lâp tài khoản cho số cmnd/cccd mới
					TaiKhoan tk = taiKhoanService.getTaiKhoan(TTDKCCCD.getSoCC());
					
					if(tk == null){
						taiKhoanService.addTaiKhoan(TTDKCCCD, md5Password, TTDKCCCD.getHoTen());
					} else {
						tk.setHoTen(TTDKCCCD.getHoTen());
						tk.setPassword(md5Password);
						taiKhoanService.updateTaiKhoan(tk);
					}
					// Làm thẻ mới
					theCMTService.insertThe(theCMT);
					// cập nhật số cc vào sổ hộ khẩu
					shk.setSoCC(soCCMoi);
					shkService.updateSoCC(shk);
				} else {
					// lam the moi
					theCMT.setSoCC(cccd.getSoCC());
					TTDKCCCD.setSoCC(cccd.getSoCC());
					// kiem tra cap doi
					if(TTDKCCCD.getMaYeuCau() == 2){
						if(soCCCu.length() != 12){
							String soCC12So = cccdService.sinhMaCanCuoc(TTDKCCCD);
							TTDKCCCD.setSoCC(soCC12So);
							theCMT.setSoCC(soCC12So);
							// cap nhat cc 12 so vao thay 9 so
							shk.setSoCC(soCC12So);
						} else if(TTDKCCCD.getMaYeuCau() == 3) {
							soCCCu = cccd.getSoCC();
						}
					} else {
						soCCCu = cccd.getSoCC();
					}
					// tang lan cap
					TTDKCCCD.setLanCap(""+soLanCap);
					// Cập nhật csdl cccd
					if(cccdService.updateCCCD(TTDKCCCD, soCCCu)){
						//cap nhat ho khau
						shkService.updateSoCC(shk);
						// Cập nhật tài khoản
						TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(TTDKCCCD.getSoCC());
						if(taiKhoan != null){
						taiKhoan.setHoTen(TTDKCCCD.getHoTen());
						taiKhoan.setPassword(md5Password);
						taiKhoanService.updateTaiKhoan(taiKhoan);
						
						} else {
							taiKhoanService.addTaiKhoan(TTDKCCCD, md5Password, TTDKCCCD.getHoTen());
						}
						// Làm thẻ mới
						theCMTService.insertThe(theCMT);
					}
				}
				TTDKCCCDService.updateTTDKCCCD(set, where);
				session.setAttribute("msg", "Đã duyệt đơn mã số: "+maSo + "<br> Số căn cước "+TTDKCCCD.getSoCC());
				model.setViewName("Temp");
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
	@RequestMapping (value = "/khong-duyet-don", method = RequestMethod.GET)
	public ModelAndView khongDuyetDon(String maSo, String lyDo, HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "DUYET_TTDK_CCCD")){
			// cú pháp chung cho hàm updateCCCD_TamThoi
			TTDKCCCD TTDKCCCD = TTDKCCCDService.getTTDKCCCDBangMa(maSo);
			TTDKCCCD.setLyDo(lyDo);
			String maDuyet = functionService.taoMaDuyetTTDKCCCD(TTDKCCCD, session.getAttribute("ssMatKhau").toString());
			String set = "SET DUYET = 4, LY_DO = '"+lyDo+"', NGUOI_DUYET = '" + session.getAttribute("ssHoTen") +"'"
					+ ", MA_DUYET = '"+maDuyet+"'";
			String where = "WHERE MA_SO = '"+maSo+"'";
			if(TTDKCCCDService.updateTTDKCCCD(set, where)){
				session.setAttribute("msg", "Đã từ chối đơn mã số: "+maSo);
			} else {
				session.setAttribute("error", "Bạn không có quyền từ chối đơn này");
			}
			// kết thúc cú pháp
			model.setViewName("Temp");
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
	
	@RequestMapping (value = "/chi-tiet-don-da-duyet")
	public ModelAndView chiTietdonDaCapPhep(String maSo, HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			TTDKCCCD TTDKCCCD = TTDKCCCDService.getTTDKCCCDBangMa(maSo);
			model = this.createChiTietTTDKCCCD(TTDKCCCD, session);
			model.addObject("msg", "Đã duyệt");
			model.setViewName("DaDuyetTTDKCCCD");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	
	@RequestMapping (value = "/chi-tiet-don-chua-duyet")
	public ModelAndView chiTietDonChuaDuyet(String maSo, HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			TTDKCCCD TTDKCCCD = TTDKCCCDService.getTTDKCCCDBangMa(maSo);
			model = this.createChiTietTTDKCCCD(TTDKCCCD, session);
			String set = "SET DUYET = 10 ";
			String where = "WHERE MA_SO = "+maSo;
			TTDKCCCDService.updateTTDKCCCD(set, where);
			model.addObject("msg", "Chờ duyệt");
			model.setViewName("DuyetTTDKCCCD");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/chi-tiet-don-bi-tu-choi")
	public ModelAndView chiTietDonBiTuChoi(String maSo, HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			TTDKCCCD TTDKCCCD = TTDKCCCDService.getTTDKCCCDBangMa(maSo);
			model = this.createChiTietTTDKCCCD(TTDKCCCD, session);
			model.addObject("error", "Bị từ chối");
			model.setViewName("BiTuChoiTTDKCCCD");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	public ModelAndView createChiTietTTDKCCCD(TTDKCCCD TTDKCCCD, HttpSession session){
		Xa xa;
		Tinh tinh;
		KhaiSinh khaiSinh = khaiSinhService.getKhaiSinhBangSoKS(TTDKCCCD.getSoKhaiSinh());
		ModelAndView model = new ModelAndView();
		try{
		model.addObject("nguoiXacNhan", TTDKCCCD.getNguoiKiemTra());
		
		model.addObject("maSo", TTDKCCCD.getMaSo());
		
		model.addObject("title",TTDKCCCD.getHoTen().toUpperCase()+" - MÃ SỐ " + TTDKCCCD.getMaSo());
		
		model.addObject("hoTen", TTDKCCCD.getHoTen());
		
		model.addObject("hoTenKhac", TTDKCCCD.getHoTenKhac());
		
		model.addObject("ngaySinh", TTDKCCCD.getNgaySinh());
		
		model.addObject("gioiTinh", TTDKCCCD.getGioiTinh());
		
		List<NhomMau> nhomMau = nhomMauService.getNhomMauBangMa(TTDKCCCD.getNhomMau());
		
		model.addObject("nhomMau", nhomMau.get(0).getTenNM());
		
		if(TTDKCCCD.getSoCC().equals("000000000000") || TTDKCCCD.getSoCC() == null || TTDKCCCD.getSoCC().equals("")){
			model.addObject("soCC", "Cấp mới");
		} else {
			model.addObject("soCC", TTDKCCCD.getSoCC());
		}
		
		YeuCau yeuCau = yeuCauService.getYeuCauID(TTDKCCCD.getMaYeuCau());
		model.addObject("yeuCau", yeuCau.getTenYeuCau());
		
		model.addObject("lanCap", TTDKCCCD.getLanCap());
		
		model.addObject("ngayDK", TTDKCCCD.getNgayDK());
		
		model.addObject("danToc", khaiSinh.getDanToc().getTenDT());
		
		model.addObject("tonGiao", TTDKCCCD.getTonGiao());
		
		model.addObject("quocTich", TTDKCCCD.getQuocTich());
		
		if(khaiSinh.getNoiCap().length() > 2){
			xa = xaService.getXaHuyenTinh(khaiSinh.getNoiCap());
			model.addObject("khaiSinh", xa.getTenXa()+", "+xa.getHuyen().getTenHuyen()+", "+xa.getHuyen().getTinh().getTenTinh());
		} else {
			tinh = tinhService.getTinhBangMa(khaiSinh.getNoiCap());
			model.addObject("khaiSinh", tinh.getTenTinh());
		}
		
		xa = xaService.getXaHuyenTinh(khaiSinh.getQueQuan());
		model.addObject("queQuan", xa.getTenXa()+", "+xa.getHuyen().getTenHuyen()+", "+xa.getHuyen().getTinh().getTenTinh());
	
		xa = xaService.getXaHuyenTinh(TTDKCCCD.getNoiOHienTai());
		model.addObject("noiOHienTai", xa.getTenXa()+", "+xa.getHuyen().getTenHuyen()+", "+xa.getHuyen().getTinh().getTenTinh());
	
		model.addObject("hocVan", TTDKCCCD.getTrinhDo());
		
		model.addObject("ngheNghiep", TTDKCCCD.getNgheNghiep());
		
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
		
		List<CCCD> cccdDD = cccdService.getCCCD(TTDKCCCD.getSoCCDD());
		if(!cccdDD.isEmpty()){
			model.addObject("hoTenDD", cccdDD.get(0).getHoTen());
			model.addObject("quocTichDD", cccdDD.get(0).getQuocTich());
			if(TTDKCCCD.getSoCCDD().equals("000000000000")){
				model.addObject("soCCDD", null);
			} else {
			model.addObject("soCCDD", TTDKCCCD.getSoCCDD());
			}
		}
		SoHoKhau shk = shkService.getSoHoKhau("SO_KS = " +TTDKCCCD.getSoKhaiSinh());
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
			if(hn.getSoCCChong().equals(TTDKCCCD.getSoCC())){
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
				if(TTDKCCCD.getChuyenPhat() == 1){
					session.setAttribute("chuyenPhat", "Có");
				} else {
					session.setAttribute("chuyenPhat", "0");
				}
				// kiem tra ket qua xac minh
				if(TTDKCCCD.getKetQuaXacMinh() != null){
					session.setAttribute("ketQuaXacMinh", TTDKCCCD.getKetQuaXacMinh());
				} else {
					session.setAttribute("ketQuaXacMinh", "0");
				}
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping (value = "/thong-ke-cccd-tong-quat")
	public ModelAndView thongKeCCCDTongQuat(String thang, String nam){
		ModelAndView model = new ModelAndView();
		String thongBao = "";
		String inHinhCot = "";
		String hinhCot = "";
		String hinhCotThongKe = "";
		String hinhTron = "";
		String hinhTronThongKe = "";
		String thangNam = thang+"-"+nam;
		String[] date = functionService.getNgayHienTai().split("-");
		if(thang == null){
			thangNam = nam;
		}
		if(thang == null && nam == null){
			thang = date[1];
			nam = date[2];
			thangNam = thang+"-"+nam;
		}
		float tongDon = 0;
		float donChuaDuyet = 0;
		float donDaCapPhep = 0;
		float donBiTuChoi = 0;
		float ptChuaDuyet = 0;
		float ptDaCapPhep = 0;
		float ptBiTuChoi = 0;
		try{
			List<TTDKCCCD> dsDonDK = TTDKCCCDService.thongKeDonDangKy(thangNam);
			String[] dsDon = this.getSoDonDK(dsDonDK).split("_");
			// dsDon[0] chua duyet, dsDon[1] da cap phep, dsDon[2] bi tu choi
			donChuaDuyet = Float.parseFloat(dsDon[0]);
			donDaCapPhep = Float.parseFloat(dsDon[1]);
			donBiTuChoi = Float.parseFloat(dsDon[2]);
			String[] ptDon = this.tinhPhanTramThongKeThang(donChuaDuyet, donDaCapPhep, donBiTuChoi).split("_");
			tongDon = Float.parseFloat(ptDon[3]);
			ptDaCapPhep = Float.parseFloat(ptDon[1]);
			ptBiTuChoi = Float.parseFloat(ptDon[2]);
			ptChuaDuyet = Float.parseFloat(ptDon[0]);
			if(dsDonDK.isEmpty()){
				thongBao = "Không có dữ liệu";
			}
			hinhTron = "data: ["
					+"{value: "+ptChuaDuyet+", label: 'Chưa duyệt'},"
			        +"{value: "+ptDaCapPhep+", label: 'Cấp phép'},"
			        +"{value: "+ptBiTuChoi+", label: 'Bị từ chối'},"
			        +"]";
			
			// kiem tra xem co phai thong ke thang hay khong
			if(thang != null){
				inHinhCot = "<table border=1>"
						+ "<tr>"
						+ "<th>Tháng</th>"
						+ "<th>Đơn chưa duyệt</th>"
						+ "<th>Đơn đã cấp phép</th>"
						+ "<th>Đơn bị từ chối</th>"
						+ "<th>Tổng</th>"
						+ "</tr>";
				hinhCot = "data: [";
				for(int i = 1; i <= 12; i++){
					String  t = ""+i;
					if(i < 10){
						t = "0"+i;
					}
					float donChuaDuyetThang = 0;
					float donDaCapPhepThang = 0;
					float donBiTuChoiThang = 0;		
					List<TTDKCCCD> thongKeThang = TTDKCCCDService.thongKeDonDangKy(t+"-"+nam);
					String[] dsDonThang = this.getSoDonDK(thongKeThang).split("_");
					donChuaDuyetThang = Float.parseFloat(dsDonThang[0]);
					donDaCapPhepThang = Float.parseFloat(dsDonThang[1]);
					donBiTuChoiThang = Float.parseFloat(dsDonThang[2]);
					if(i < 12){
					hinhCot	+="{x: 'T"+i+" "+nam+"', "
							+ "y: "+donDaCapPhepThang+", "
							+ "z: "+donBiTuChoiThang+", "
							+ "a: "+donChuaDuyetThang+"},";
					}
					if(i == 12){
						hinhCot	+="{x: 'T"+i+" "+nam+"',"
								+ " y: "+donDaCapPhepThang+","
								+ " z: "+donBiTuChoiThang+","
								+ " a: "+donChuaDuyetThang+"}";
					}
					inHinhCot += "<tr>"
							+ "<td>Tháng "+i+" năm "+nam+"</td>"
							+ "<td>"+(int)donChuaDuyetThang+"</td>"
							+ "<td>"+(int)donDaCapPhepThang+"</td>"
							+ "<td>"+(int)donBiTuChoiThang+"</td>"
							+ "<td>"+(int)(donChuaDuyetThang+donDaCapPhepThang+donBiTuChoiThang)+"</td>"
							+ "</tr>";
				}
				inHinhCot += "</table>";
				hinhCot +="]";
			} else {
				inHinhCot = "<table border=1>"
						+ "<tr>"
						+ "<th>Năm</th>"
						+ "<th>Đơn chưa duyệt</th>"
						+ "<th>Đơn đã cấp phép</th>"
						+ "<th>Đơn bị từ chối</th>"
						+ "<th>Tổng</th>"
						+ "</tr>";
				int forNam = Integer.parseInt(date[2].toString());
				hinhCot = "data: [";
				for(int n = 2014; n <= forNam; n++){
	
					float donChuaDuyetThang = 0;
					float donDaCapPhepThang = 0;
					float donBiTuChoiThang = 0;		
					List<TTDKCCCD> thongKeThang = TTDKCCCDService.thongKeDonDangKy(""+n);
					String[] dsDonThang = this.getSoDonDK(thongKeThang).split("_");
					donChuaDuyetThang = Float.parseFloat(dsDonThang[0]);
					donDaCapPhepThang = Float.parseFloat(dsDonThang[1]);
					donBiTuChoiThang = Float.parseFloat(dsDonThang[2]);
					if(n < forNam){
					hinhCot	+="{x: '"+n+"', "
							+ "y: "+donDaCapPhepThang+", "
							+ "z: "+donBiTuChoiThang+", "
							+ "a: "+donChuaDuyetThang+"},";
					}
					if(n == forNam){
						hinhCot	+="{x: '"+n+"',"
								+ " y: "+donDaCapPhepThang+","
								+ " z: "+donBiTuChoiThang+","
								+ " a: "+donChuaDuyetThang+"}";
					}
					inHinhCot += "<tr>"
							+ "<td>Năm "+n+"</td>"
							+ "<td>"+(int)donChuaDuyetThang+"</td>"
							+ "<td>"+(int)donDaCapPhepThang+"</td>"
							+ "<td>"+(int)donBiTuChoiThang+"</td>"
							+ "<td>"+(int)(donChuaDuyetThang+donDaCapPhepThang+donBiTuChoiThang)+"</td>"
							+ "</tr>";
				}
				inHinhCot += "</table>";
				hinhCot +="]";
			}
			hinhTronThongKe = functionService.getScriptHinhTronThongKe(hinhTron, "hinhTron");
			hinhCotThongKe = functionService.getScriptHinhCotThongKe(hinhCot, "hinhCot");
			
			String selectNam = "";
			for(int n = 2014; n <= Integer.parseInt(date[2].toString()); n++){
				selectNam += "<option value='"+n+"'>"+n+"</option>";
			}
			if(thongBao.equals("Không có dữ liệu")){
				hinhTronThongKe = "";
			}
			model.addObject("soDoHinhTron", hinhTronThongKe);
			model.addObject("soDoHinhCot", hinhCotThongKe);
			model.addObject("inHinhCot", inHinhCot);
			model.addObject("tong", (int)tongDon);
			model.addObject("donChuaDuyet", (int)donChuaDuyet);
			model.addObject("donDaCapPhep", (int)donDaCapPhep);
			model.addObject("donBiTuChoi", (int)donBiTuChoi);
			model.addObject("ptChuaDuyet", ptChuaDuyet);
			model.addObject("ptDaCapPhep", ptDaCapPhep);
			model.addObject("ptBiTuChoi", ptBiTuChoi);
			model.addObject("thoiGian", thangNam);
			model.addObject("thang", thang);
			model.addObject("nam", nam);
			model.addObject("selectNam", selectNam);
			model.addObject("error", thongBao);
			model.setViewName("ThongKeCCCDTongQuat");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	@RequestMapping (value = "/thong-ke-cccd-don-vi")
	public ModelAndView thongKeCCCDDonVi(String dv, String thang, String nam, HttpSession session){
		ModelAndView model = new ModelAndView();
		String thongBao = "";
		String inHinhCot = "";
		String hinhCot = "";
		String hinhCotThongKe = "";
		String hinhTron = "";
		String hinhTronThongKe = "";
		String thangNam = thang+"-"+nam;
		String donVi = "";
		String tenDonVi = "";
		float tongDon = 0;
		float donChuaDuyet = 0;
		float donDaCapPhep = 0;
		float donBiTuChoi = 0;
		float ptChuaDuyet = 0;
		float ptDaCapPhep = 0;
		float ptBiTuChoi = 0;
		try{
		List<Huyen> dsHuyen = huyenService.getDSHuyen(session.getAttribute("ssCoQuan").toString());
		Tinh tinh = tinhService.getTinhBangMa(session.getAttribute("ssCoQuan").toString());
		String[] date = functionService.getNgayHienTai().split("-");
		if(thang == null){
			thangNam = nam;
		}
		if(dv == null){
			donVi = dsHuyen.get(0).getMaHuyen();
		} else {
			donVi = dv;
		}
		if(donVi.length() == 3){
			Huyen huyen = huyenService.getHuyenBangMa(donVi);
			tenDonVi = huyen.getTenHuyen();
		} else if(donVi.length() == 2) {
			tenDonVi = tinh.getTenTinh();
		}
		if(thang == null && nam == null){
			thang = date[1];
			nam = date[2];
			thangNam = thang+"-"+nam;
		}
		List<TTDKCCCD> dsDonDK = TTDKCCCDService.thongKeDonDangKyDV(donVi, thangNam);
		String[] dsDon = this.getSoDonDK(dsDonDK).split("_");
		// dsDon[0] chua duyet, dsDon[1] da cap phep, dsDon[2] bi tu choi
		donChuaDuyet = Float.parseFloat(dsDon[0]);
		donDaCapPhep = Float.parseFloat(dsDon[1]);
		donBiTuChoi = Float.parseFloat(dsDon[2]);
		String[] ptDon = this.tinhPhanTramThongKeThang(donChuaDuyet, donDaCapPhep, donBiTuChoi).split("_");
		tongDon = Float.parseFloat(ptDon[3]);
		ptDaCapPhep = Float.parseFloat(ptDon[1]);
		ptBiTuChoi = Float.parseFloat(ptDon[2]);
		ptChuaDuyet = Float.parseFloat(ptDon[0]);
		if(dsDonDK.isEmpty()){
			thongBao = "Không có dữ liệu";
		}
		hinhTron = "data: ["
				+"{value: "+ptChuaDuyet+", label: 'Chưa duyệt'},"
		        +"{value: "+ptDaCapPhep+", label: 'Cấp phép'},"
		        +"{value: "+ptBiTuChoi+", label: 'Bị từ chối'},"
		        +"]";
		
		// kiem tra xem co phai thong ke thang hay khong
		if(thang != null){
			inHinhCot = "<table border=1>"
					+ "<tr>"
					+ "<th>Tháng</th>"
					+ "<th>Đơn chưa duyệt</th>"
					+ "<th>Đơn đã cấp phép</th>"
					+ "<th>Đơn bị từ chối</th>"
					+ "<th>Tổng</th>"
					+ "</tr>";
			hinhCot = "data: [";
			for(int i = 1; i <= 12; i++){
				String  t = ""+i;
				if(i < 10){
					t = "0"+i;
				}
				float donChuaDuyetThang = 0;
				float donDaCapPhepThang = 0;
				float donBiTuChoiThang = 0;		
				List<TTDKCCCD> thongKeThang = TTDKCCCDService.thongKeDonDangKyDV(donVi, t+"-"+nam);
				String[] dsDonThang = this.getSoDonDK(thongKeThang).split("_");
				donChuaDuyetThang = Float.parseFloat(dsDonThang[0]);
				donDaCapPhepThang = Float.parseFloat(dsDonThang[1]);
				donBiTuChoiThang = Float.parseFloat(dsDonThang[2]);
				if(i < 12){
				hinhCot	+="{x: 'T"+i+" "+nam+"', "
						+ "y: "+donDaCapPhepThang+", "
						+ "z: "+donBiTuChoiThang+", "
						+ "a: "+donChuaDuyetThang+"},";
				}
				if(i == 12){
					hinhCot	+="{x: 'T"+i+" "+nam+"',"
							+ " y: "+donDaCapPhepThang+","
							+ " z: "+donBiTuChoiThang+","
							+ " a: "+donChuaDuyetThang+"}";
				}
				inHinhCot += "<tr>"
						+ "<td>Tháng "+i+" năm "+nam+"</td>"
						+ "<td>"+(int)donChuaDuyetThang+"</td>"
						+ "<td>"+(int)donDaCapPhepThang+"</td>"
						+ "<td>"+(int)donBiTuChoiThang+"</td>"
						+ "<td>"+(int)(donChuaDuyetThang+donDaCapPhepThang+donBiTuChoiThang)+"</td>"
						+ "</tr>";
			}
			inHinhCot += "</table>";
			hinhCot +="]";
		} else {
			inHinhCot = "<table border=1>"
					+ "<tr>"
					+ "<th>Năm</th>"
					+ "<th>Đơn chưa duyệt</th>"
					+ "<th>Đơn đã cấp phép</th>"
					+ "<th>Đơn bị từ chối</th>"
					+ "<th>Tổng</th>"
					+ "</tr>";
			int forNam = Integer.parseInt(date[2].toString());
			hinhCot = "data: [";
			for(int n = 2014; n <= forNam; n++){

				float donChuaDuyetThang = 0;
				float donDaCapPhepThang = 0;
				float donBiTuChoiThang = 0;		
				List<TTDKCCCD> thongKeThang = TTDKCCCDService.thongKeDonDangKyDV(donVi, ""+n);
				String[] dsDonThang = this.getSoDonDK(thongKeThang).split("_");
				donChuaDuyetThang = Float.parseFloat(dsDonThang[0]);
				donDaCapPhepThang = Float.parseFloat(dsDonThang[1]);
				donBiTuChoiThang = Float.parseFloat(dsDonThang[2]);
				if(n < forNam){
				hinhCot	+="{x: '"+n+"', "
						+ "y: "+donDaCapPhepThang+", "
						+ "z: "+donBiTuChoiThang+", "
						+ "a: "+donChuaDuyetThang+"},";
				}
				if(n == forNam){
					hinhCot	+="{x: '"+n+"',"
							+ " y: "+donDaCapPhepThang+","
							+ " z: "+donBiTuChoiThang+","
							+ " a: "+donChuaDuyetThang+"}";
				}
				inHinhCot += "<tr>"
						+ "<td>Năm "+n+"</td>"
						+ "<td>"+(int)donChuaDuyetThang+"</td>"
						+ "<td>"+(int)donDaCapPhepThang+"</td>"
						+ "<td>"+(int)donBiTuChoiThang+"</td>"
						+ "<td>"+(int)(donChuaDuyetThang+donDaCapPhepThang+donBiTuChoiThang)+"</td>"
						+ "</tr>";
			}
			inHinhCot += "</table>";
			hinhCot +="]";
		}
		hinhTronThongKe = functionService.getScriptHinhTronThongKe(hinhTron, "hinhTron");
		hinhCotThongKe = functionService.getScriptHinhCotThongKe(hinhCot, "hinhCot");
		
		String selectNam = "";
		for(int n = 2014; n <= Integer.parseInt(date[2].toString()); n++){
			selectNam += "<option value='"+n+"'>"+n+"</option>";
		}
		if(thongBao.equals("Không có dữ liệu")){
			hinhTronThongKe = "";
		}
		model.addObject("soDoHinhTron", hinhTronThongKe);
		model.addObject("soDoHinhCot", hinhCotThongKe);
		model.addObject("inHinhCot", inHinhCot);
		model.addObject("tong", (int)tongDon);
		model.addObject("donChuaDuyet", (int)donChuaDuyet);
		model.addObject("donDaCapPhep", (int)donDaCapPhep);
		model.addObject("donBiTuChoi", (int)donBiTuChoi);
		model.addObject("ptChuaDuyet", ptChuaDuyet);
		model.addObject("ptDaCapPhep", ptDaCapPhep);
		model.addObject("ptBiTuChoi", ptBiTuChoi);
		model.addObject("thoiGian", thangNam);
		model.addObject("donVi", tenDonVi);
		model.addObject("maDonVi", dv);
		model.addObject("thang", thang);
		model.addObject("nam", nam);
		model.addObject("tinh", tinh);
		model.addObject("selectNam", selectNam);
		model.addObject("selectHuyen", dsHuyen);
		model.addObject("error", thongBao);
		model.setViewName("ThongKeCCCDDonVi");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	@RequestMapping (value = "/in-thong-ke-cccd-tong-quat")
	public ModelAndView inThongKeTongQuat(String thang, String nam){
		ModelAndView model = new ModelAndView();
		model = this.thongKeCCCDTongQuat(thang, nam);
		model.setViewName("InThongKeTTDKCCCDTongQuat");
		return model;
	}
	@RequestMapping (value = "/in-thong-ke-cccd-don-vi")
	public ModelAndView inThongKeDonVi(String dv, String thang, String nam, HttpSession session){
		ModelAndView model = new ModelAndView();
		model = this.thongKeCCCDDonVi(dv, thang, nam, session);
		model.setViewName("InThongKeTTDKCCCDDonVi");
		return model;
	}
	@RequestMapping (value = "/thong-ke-dan-so-huyen")
	public ModelAndView thongKeDanSoHuyen(HttpSession session, HttpServletResponse response, String nam){
		ModelAndView model = new ModelAndView();
		try{
			// kiem tra dang nhap
			if(session.getAttribute("ssTaiKhoan") == null || session.getAttribute("ssCoQuan") == null){
				try {
					response.sendRedirect("login");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// kiem tra quyen thong ke
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "THONG_KE_DAN_SO")){
				String maTinh = session.getAttribute("ssCoQuan").toString();
				if(maTinh.length() > 2){
					maTinh = "92";
				}
				String selectNam = "";
				String[] date = functionService.getNgayHienTai().split("-");
				for(int n = 2010; n <= Integer.parseInt(date[2].toString()); n++){
					selectNam += "<option value='"+n+"'>"+n+"</option>";
				}
				if(nam == null){
					nam = date[2].toString();
				}
				float tongDan = 0;
				String hinhTronThongKe = "";
				String hinhDuongThongKe = "";
				String dataHinhTron = "data: [";
				String dataHinhDuong = "";
				Tinh tinh = tinhService.getTinhBangMa(maTinh);
				//Tin sua cho nay, them nam 2016
				List<DanSoHuyen> danSoHuyen = khaiSinhService.getDanSoHuyen(tinh.getMaTinh(), Integer.parseInt(nam));
				List<Huyen> dsHuyen = huyenService.getDSHuyen(tinh.getMaTinh());
				int soHuyen = dsHuyen.size();
				DanSoHuyen danSo;
				// them huyen khong co du lieu
				for (int i = 0; i < soHuyen; i++) {
					if(danSoHuyen.size() > 0){
						Boolean coChua = false;
						for (int j = 0; j < danSoHuyen.size(); j++) {
							// kiem tra huyen nay co trung voi huyen da co du lieu hay khong
							if (danSoHuyen.get(j).getHuyen().getMaHuyen().equals(dsHuyen.get(i).getMaHuyen())) {
								coChua = true;
							}
						}
						// khong trung thi them huyen khong co du lieu
						if(!coChua){
							danSo = new DanSoHuyen();
							danSo.setHuyen(dsHuyen.get(i));
							danSo.setDanSo(0);
							danSoHuyen.add(danSoHuyen.size(), danSo);
						}
					} else {
						danSo = new DanSoHuyen();
						danSo.setHuyen(dsHuyen.get(i));
						danSo.setDanSo(0);
						danSoHuyen.add(danSo);
						
					}
					
				}
				// tinh tong dan so cua ca tinh
				for(int i = 0; i < danSoHuyen.size(); i++){
					tongDan += danSoHuyen.get(i).getDanSo();
				}
				// tao data cho do thi hinh tron
				for(int i = 0; i < danSoHuyen.size(); i++){
					float ptDanSo = (float) ((danSoHuyen.get(i).getDanSo()/tongDan) * 100);
					dataHinhTron += "{value: "+ptDanSo+", label: '"+danSoHuyen.get(i).getHuyen().getTenHuyen()+"'},";
				}
				dataHinhTron +="]";
				// goi ham tao data cho do thi hinh duong
				dataHinhDuong = this.createHinhDuongThongKeDanSo(tinh, Integer.parseInt(nam));
				// tao script inh tron va hinh duong
				hinhTronThongKe = functionService.getScriptHinhTronThongKe(dataHinhTron, "hinhTron");
				hinhDuongThongKe = functionService.getScriptHinhDuongThongKe(dataHinhDuong, "hinhDuong", "Đồ thị gia tăng dân số", 100);
				model.addObject("selectNam", selectNam);
				model.addObject("thoiGian", nam);
				model.addObject("tinh", tinh);
				model.addObject("danSoHuyen", danSoHuyen);
				model.addObject("hinhTronDanSo", hinhTronThongKe);
				model.addObject("hinhDuongDanSo", hinhDuongThongKe);
				model.addObject("tongDan",(int)tongDan);
				if(danSoHuyen.isEmpty()){
					model.addObject("thongBao", "Không có dữ liệu");
				}
				model.setViewName("ThongKeDanSoCapHuyen");
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
	public String createHinhDuongThongKeDanSo(Tinh tinh, int nam){
		String script = "";
		List<Huyen> dsHuyen = huyenService.getDSHuyen(tinh.getMaTinh());
		String[] namHienTai = functionService.getNgayHienTai().split("-");
		// tao script
		for(int i = 0; i < dsHuyen.size(); i++){
			script += "{"
					+"type: 'line',"
					+"lineThickness: 3,"
					+"axisYType: 'secondary',"
					+"showInLegend: true,"
					+"name: '"+dsHuyen.get(i).getTenHuyen()+"',"
					+"dataPoints: [";
			for(int n = 2010; n <= Integer.parseInt(namHienTai[2].toString()); n++ ){
				DanSoHuyen huyen = khaiSinhService.getDanSoHuyenBangMaHuyen(dsHuyen.get(i).getMaHuyen(), n);
				//Tin sua, thay doi DanSo tu kieu int sang long
				long danSo = 0;
				if(huyen != null){
					danSo = huyen.getDanSo();
				}
				script += "{ x: new Date("+n+", 0), y: "+danSo+" },";
			}
			DanSoHuyen huyen = khaiSinhService.getDanSoHuyenBangMaHuyen(dsHuyen.get(i).getMaHuyen(), nam);
				long danSo = 0;
				if(huyen != null){
					danSo = huyen.getDanSo();
				}
				script += "{ x: new Date("+nam+", 0), y: "+danSo+" }"
					   + "	]},";
		}
		return script;
	}
	public String getSoDonDK(List<TTDKCCCD> dsDonDK){
		int donChuaDuyet = 0;
		int donDaCapPhep = 0;
		int donBiTuChoi = 0;
		for(int i = 0; i < dsDonDK.size(); i++){
			int duyet = dsDonDK.get(i).getDuyet();
			// 2 la don moi chua xem, 10 la don moi da xem
			if(duyet == 2 || duyet == 10){
				donChuaDuyet++;
			} else if(duyet == 3){
				donDaCapPhep++;
			} else if(duyet == 4){
				donBiTuChoi++;
			}
		}
		return donChuaDuyet+"_"+donDaCapPhep+"_"+donBiTuChoi;
	}
	public String tinhPhanTramThongKeThang(float chuaDuyet, float daCapPhep, float biTuChoi){
		float tongDon = 0;
		float ptChuaDuyet = 0;
		float ptDaCapPhep = 0;
		float ptBiTuChoi = 0;
		tongDon = chuaDuyet+daCapPhep+biTuChoi;
		ptBiTuChoi = Math.round(((biTuChoi/tongDon) * 100)*100)/100;
		ptChuaDuyet = Math.round(((chuaDuyet/tongDon) * 100)*100)/100;
		ptDaCapPhep = 100 - (ptBiTuChoi+ ptChuaDuyet);
		return ptChuaDuyet+"_"+ptDaCapPhep+"_"+ptBiTuChoi+"_"+tongDon;
	}
	/**
	 * Tin them de thong ke dan so xa
	 * @param session
	 * @param response
	 * @param nam
	 * @return
	 */
	@RequestMapping (value = "/thong-ke-dan-so-xa", method = RequestMethod.POST)
	public ModelAndView thongKeDanSoXa(HttpSession session, String nam, String noiTKHuyen){
		ModelAndView model = new ModelAndView();
		String viewName = "ThongKeDanSoCapXa";
		String thongBaoLoi = "";
		String dataHinhTron = "data: [";
		String hinhTronThongKe = "";
		System.out.println("NUll roi:  " + noiTKHuyen + " " + nam);
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//Kiem tra da dang nhap
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				if (noiTKHuyen == null || nam == null) {
					
				} else {
					// kiem tra quyen thong ke
					if(quyenService.kiemTraQuyenBangTen((String)session.getAttribute("ssTaiKhoan"), "THONG_KE_DAN_SO")) {	
						List<bean.Chung.DanSoXa> dsDanSoXa = khaiSinhService.getDanSoXaBangMaHuyen(noiTKHuyen, Integer.valueOf(nam));
						int soXa = dsDanSoXa.size();
						// tao data cho do thi hinh tron
						for(int i = 0; i < soXa; i++){
							dataHinhTron += "{value: "+dsDanSoXa.get(i).getTyLePhanTram()+", label: '"+dsDanSoXa.get(i).getXa().getTenXa()+"'},";
						}
						dataHinhTron +="]";
						System.out.println(dataHinhTron);
						// tao script inh tron va hinh duong
						hinhTronThongKe = functionService.getScriptHinhTronThongKe(dataHinhTron, "hinhTron");
						model.addObject("hinhTronDanSo", hinhTronThongKe);
					//Neu khong co quyen thong ke dan so thi dua ra thong bao loi
					} else {
						thongBaoLoi = "Bạn không có quyền thống kê dân số";
						viewName = "ThongBaoLoi";
						model.addObject("thongBaoLoi", thongBaoLoi);
					}
				}
			//Neu chua dang nhap thi tro ve trang login
			} else {
				viewName = "login";
			}
		} catch (Exception e) {
			viewName = "ThongBaoLoi";
			thongBaoLoi = "Error " + e.getMessage() + ". Error code: " + e.hashCode();
			model.addObject("thongBaoLoi", thongBaoLoi);
			e.printStackTrace();
		}
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping (value = "/thong-ke-dan-so-xa", method = RequestMethod.GET)
	public ModelAndView trangThongKeDanSoXa(HttpSession session, String nam, String maHuyen){
		ModelAndView model = new ModelAndView();
		String viewName = "ThongKeDanSoCapXa";
		List<String> dsNam = new ArrayList<String>();
		try {
			//Lay nam hien tai
			Calendar calendar = GregorianCalendar.getInstance();
			int namHienTai = calendar.get(Calendar.YEAR);
			//Tao danh sach nam de nguoi dung chon
			for (int i = 1960; i < namHienTai; i ++) {
				dsNam.add(String.valueOf(i));
			}
			//Tao danh sach tinh
			List<Tinh> dsTinh = tinhService.getDSTinh();
			session.setAttribute("dsTinh", dsTinh);
			session.setAttribute("dsNam", dsNam);
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName(viewName);
		return model;
	}
}
