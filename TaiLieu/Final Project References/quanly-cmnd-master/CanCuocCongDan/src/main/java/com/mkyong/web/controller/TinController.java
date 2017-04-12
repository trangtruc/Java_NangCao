
package com.mkyong.web.controller;

import java.util.ArrayList;
import java.util.List;






import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bean.An.SoHoKhau;
import bean.Chung.CCCD;
import bean.Chung.DanToc;
import bean.Chung.DuyetDKKH;
import bean.Chung.DuyetDKKS;
import bean.Chung.HonNhan;
import bean.Chung.KhaiSinh;
import bean.Chung.TTDKKetHon;
import bean.Chung.TTDKKhaiSinh;
import bean.Chung.TaiKhoan;
import bean.Chung.Tinh;
import bean.Chung.TinhTrangHN;
import bean.Chung.Xa;
import bean.Chung.YeuCau;
import Constant.Const;

import com.mkyong.services.CCCDService;
import com.mkyong.services.ConfigCCCDService;
import com.mkyong.services.DanTocService;
import com.mkyong.services.HonNhanService;
import com.mkyong.services.HuyenService;
import com.mkyong.services.KhaiSinhService;
import com.mkyong.services.QuyenService;
import com.mkyong.services.SoHoKhauService;
import com.mkyong.services.TaiKhoanService;
import com.mkyong.services.TinhService;
import com.mkyong.services.XaService;
import com.mkyong.services.YeuCauService;
import com.mkyong.services.function.FunctionService;

@Controller
public class TinController {
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
	private CCCDService cccdService;
	@Autowired
	private HonNhanService honNhanService;
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private YeuCauService yeuCauService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private ConfigCCCDService configCCCDService;
	@Autowired
	private SoHoKhauService shkService;
	@Autowired
	private QuyenService quyenService;
	
	@RequestMapping(value = "/dang-ky-khai-sinh", method = RequestMethod.GET)
	public ModelAndView trangDangKyKhaiSinh(HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "DangKyKhaiSinh";
		long maXacNhan = 0;
		List<Tinh> dsTinh = new ArrayList<Tinh>();
		List<YeuCau> dsYeuCau = new ArrayList<YeuCau>();
		List<DanToc> dsDanToc = new ArrayList<DanToc>();
		TaiKhoan taiKhoan = new TaiKhoan();
		bean.Vu.CCCD cccdNguoiYeuCau = new bean.Vu.CCCD();
		Tinh noiCap = new Tinh();
		try {
			maXacNhan = functionService.getMaXacNhan();
			dsTinh = tinhService.getDSTinh();
			dsYeuCau = yeuCauService.getDSYeuCau();
			dsDanToc = danTocService.getDSDanToc();
			try {
				taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
				cccdNguoiYeuCau = cccdService.getCCCDBangMa(taiKhoan.getUsername());
			} catch (Exception e) {
				
			}
//			// Vu lay dia chi thuong tru = ho khau
//			try {
//				SoHoKhau shk = shkService.getSoHoKhau("A.SO_CC = '" + cccdNguoiYeuCau.getSoCC() + "' ");
//				session.setAttribute("thuongTru", shk.getDiaChi());
//				noiCap = tinhService.getTinhBangMa(cccdNguoiYeuCau.getNoiCap());
//			} catch (Exception e) {
//				
//			}
		} catch (Exception e) {
			viewName = "ThongBaoLoi";
			String thongBaoLoi = "Lỗi " + e.getMessage();
			model.addObject("thongBaoLoi", thongBaoLoi);
		}
		session.setAttribute("noiCap", noiCap);
		session.setAttribute("maXacNhan", maXacNhan);
		model.addObject("dsYeuCau", dsYeuCau);
		model.addObject("dsTinh", dsTinh);
		model.addObject("dsDanToc", dsDanToc);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/dang-ky-khai-sinh", method = RequestMethod.POST)
	public ModelAndView dangKyKhaiSinh(
			String yeuCau, String noiDKLVXa, 
			String hoTen, String ngaySinh,  
			String gioiTinh, String quocTich, 
			String maDT, String noiSinhXa, String benhVien, 
			String queQuanXa, String soCCNguoiYeuCau, 
			String quanHeVoiNguoiKS, String soCCCha, 
			String soCCMe, long maXacNhan,
			String soKS, String soKSCu, HttpSession session) {
		Boolean kqDangKy = false;
		ModelAndView model = new ModelAndView();
		TTDKKhaiSinh ttdk = new TTDKKhaiSinh();
		String viewName = Const.REDIRECT_DKKS;
		try {
			if ((long)session.getAttribute("maXacNhan") == maXacNhan) {
					ttdk.setHoTen(hoTen);
					ttdk.setNgaySinh(functionService.dinhDangNgay(ngaySinh));
					ttdk.setGioiTinh(gioiTinh);
					ttdk.setQuocTich(quocTich);
					//Set danToc
					DanToc danToc = new DanToc();
					danToc.setMaDT(maDT);
					ttdk.setDanToc(danToc);
					//Set noiSinh
					ttdk.setNoiSinh(noiSinhXa);
					ttdk.setBenhVien(benhVien);
					ttdk.setQueQuan(queQuanXa);
					ttdk.setSoCCNguoiYeuCau(soCCNguoiYeuCau.replaceAll("-", ""));
					ttdk.setQuanHeVoiNguoiKS(quanHeVoiNguoiKS);
					ttdk.setSoCCCha(soCCCha.replaceAll("-", ""));
					ttdk.setSoCCMe(soCCMe.replaceAll("-", ""));
					//Lay ngay hien tai
					String ngayDangKy = functionService.getNgayHienTai();
					//Set ngayDangKy khai sinh
					ttdk.setNgayDangKy(ngayDangKy);
					//Set ngayHenLamViec
					String ngayHen = "";
					try {
						ngayHen = khaiSinhService.taoNgayHen(ngayDangKy, noiDKLVXa, Const.slDonTrongNgay);
					} catch (Exception e) {
					}
					ttdk.setNgayHenLamViec(ngayHen);
					ttdk.setNoiDKLV(noiDKLVXa);
					bean.Chung.YeuCau yc = new bean.Chung.YeuCau();
					yc.setMaYeuCau(Integer.valueOf(yeuCau));
					ttdk.setYeuCau(yc);
					if (soKSCu != null) {
						ttdk.setSoKSCu(soKSCu.replace("-", ""));
					}
					soKS = khaiSinhService.taoSoKSChoTTDK();
					//System.out.println("So khai sinh cu: " + soKSCu);
					//System.out.println("So khai sinh ttdk: " + soKS);
					ttdk.setSoKS(soKS);
					kqDangKy = khaiSinhService.themTTDKKhaiSinh(ttdk);
					if (kqDangKy) {
						String tieuDe = "GIẤY HẸN ĐĂNG KÝ KHAI SINH";
						TTDKKhaiSinh ttdkKhaiSinh = new TTDKKhaiSinh();
						try {
							ttdkKhaiSinh = khaiSinhService.getHangCuoi();
						} catch (Exception e) {
						}
						String giayTo = Const.giayToKhaiSinh;
						model.addObject("giayTo", giayTo);
						model.addObject("ttdkKhaiSinh", ttdkKhaiSinh);
						model.addObject("tieuDe", tieuDe);
						viewName = "GiayHenKhaiSinh";
					}
			} else {
				viewName = "ThongBaoLoi";
				String thongBaoLoi = "Mã xác nhận không chính xác";
				model.addObject("thongBaoLoi", thongBaoLoi);
			}
		} catch (Exception e) {
			viewName = "ThongBaoLoi";
			String thongBaoLoi = "Unknown error. Error code: " + e.hashCode();
			model.addObject("thongBaoLoi", thongBaoLoi);
			e.printStackTrace();
		}
		session.setAttribute("kqDanKy", kqDangKy);
		model.setViewName(viewName);
		return model;
	}
	/**
	 * HARD CODE soLuongTrongNgay = Const.slDonTrongNgay
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/danh-sach-dk-khai-sinh", method = RequestMethod.GET)
	public ModelAndView dsDKKhaiSinh(HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "DanhSachDKKhaiSinh";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<TTDKKhaiSinh> dsDKKhaiSinh = new ArrayList<TTDKKhaiSinh>();
		try {
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan taiKhoan = (TaiKhoan)session.getAttribute("taiKhoan");
				String ngayHen = functionService.getNgayHienTai();
				String tenQuyen = Const.DUYET_KHAI_SINH_1;
				if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KHAI_SINH_2)) {
					tenQuyen = Const.DUYET_KHAI_SINH_2;
				}
				dsDKKhaiSinh = khaiSinhService.getDSTTDKKhaiSinh(ngayHen, taiKhoan, tenQuyen, Const.slDonTrongNgay);
				int soDonDKKhaiSinh = dsDKKhaiSinh.size();
				model.addObject("soDonDKKhaiSinh", soDonDKKhaiSinh);
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (NullPointerException e) {
			viewName = Const.VIEW_LOG_IN;
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addObject("dsDKKhaiSinh", dsDKKhaiSinh);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/chi-tiet-dk-khai-sinh", method = RequestMethod.GET)
	public ModelAndView chiTietDKKhaiSinh(String soKS, HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "ChiTietDKKhaiSinh";
		DanToc dtCha = new DanToc();
		DanToc dtMe = new DanToc();
		bean.Vu.CCCD cccdNguoiYeuCau = new bean.Vu.CCCD();
		bean.Vu.CCCD cccdCha = new bean.Vu.CCCD();
		bean.Vu.CCCD cccdMe = new bean.Vu.CCCD();
		try {
			TTDKKhaiSinh ttdkKhaiSinh = khaiSinhService.getTTDKKhaiSinhBangSoKS(soKS);
			String noiSinhXa = ttdkKhaiSinh.getNoiSinh();
			String queQuanXa = ttdkKhaiSinh.getQueQuan();
			Xa noiSinh = xaService.getXaHuyenTinh(noiSinhXa);
			Xa queQuan = xaService.getXaHuyenTinh(queQuanXa);
			Xa noiDKLV = xaService.getXaHuyenTinh(ttdkKhaiSinh.getNoiDKLV());
			try {
				cccdNguoiYeuCau = cccdService.getCCCDBangMa(ttdkKhaiSinh.getSoCCNguoiYeuCau());
			} catch (Exception e) {
			}
			try {
				cccdCha = cccdService.getCCCDBangMa(ttdkKhaiSinh.getSoCCCha());
			} catch (Exception e) {
			}
			try {
				cccdMe = cccdService.getCCCDBangMa(ttdkKhaiSinh.getSoCCMe());
			} catch (Exception e) {
			}
			String ngaySinh = ttdkKhaiSinh.getNgaySinh();
			String ngaySinhChu = "";
			try {
				ngaySinhChu = functionService.docNgayThangNam(ngaySinh);
			} catch (Exception e) {
				
			}
			Xa queQuanNguoiYeuCau = new Xa();
			try {
				queQuanNguoiYeuCau = xaService.getXaHuyenTinh(cccdNguoiYeuCau.getNoiOHienTai());
			} catch (Exception e) {
				
			}
			try {
				dtCha = danTocService.getDanTocID(cccdCha.getMaDT());
				dtMe = danTocService.getDanTocID(cccdMe.getMaDT());
			} catch (Exception e) {
				
			}
			model.addObject("noiSinh", noiSinh);
			model.addObject("queQuan", queQuan);
			model.addObject("ttdkKhaiSinh", ttdkKhaiSinh);
			model.addObject("noiDKLV", noiDKLV);
			model.addObject("ngaySinhChu", ngaySinhChu);
			model.addObject("queQuanNguoiYeuCau", queQuanNguoiYeuCau);
		} catch (Exception e) {
			
		}
		model.addObject("cccdNguoiYeuCau", cccdNguoiYeuCau);
		model.addObject("cccdCha", cccdCha);
		model.addObject("cccdMe", cccdMe);
		model.addObject("dtCha", dtCha);
		model.addObject("dtMe", dtMe);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/cap-nhat-dk-khai-sinh", method = RequestMethod.GET)
	public ModelAndView capNhatDKKhaiSinh(String soKS, HttpSession session) {
		ModelAndView model = new ModelAndView();
		String noiSinh = "";
		String soCCNguoiYeuCau = "";
		String soCCCha = "";
		String soCCMe = "";
		String ngaySinh = "";
		String maQueQuanXa = "";
		CCCD cccdNguoiYeuCau = new CCCD();
		CCCD cccdCha = new CCCD();
		CCCD cccdMe = new CCCD();
		Xa noiSinhXa = new Xa();
		Xa queQuanXa = new Xa();
		List<Tinh> dsTinh = tinhService.getDSTinh();
		List<DanToc> dsDanToc = danTocService.getDSDanToc();
		
		TTDKKhaiSinh ttdkKhaiSinh = khaiSinhService.getTTDKKhaiSinhBangSoKS(soKS);
		soCCNguoiYeuCau = ttdkKhaiSinh.getSoCCNguoiYeuCau();
		soCCCha = ttdkKhaiSinh.getSoCCCha();
		soCCMe = ttdkKhaiSinh.getSoCCMe();
		noiSinh = ttdkKhaiSinh.getNoiSinh();
		maQueQuanXa = ttdkKhaiSinh.getQueQuan();
		
		//Dinh dang lai ngaySinh(Nam-Thang-Ngay) de hien thi len trang jsp
		ngaySinh = ttdkKhaiSinh.getNgaySinh();
		ttdkKhaiSinh.setNgaySinh(functionService.dinhDangNgay(ngaySinh));
		cccdNguoiYeuCau = cccdService.getCCCD1(soCCNguoiYeuCau);
		cccdCha = cccdService.getCCCD1(soCCCha);
		cccdMe = cccdService.getCCCD1(soCCMe);
		queQuanXa = xaService.getXaHuyenTinh(maQueQuanXa);
		noiSinhXa = xaService.getXaHuyenTinh(noiSinh);
		
		session.setAttribute("noiSinhXa", noiSinhXa);
		session.setAttribute("queQuanXa", queQuanXa);
		session.setAttribute("cccdNguoiYeuCau", cccdNguoiYeuCau);
		session.setAttribute("cccdCha", cccdCha);
		session.setAttribute("cccdMe", cccdMe);
		model.addObject("ttdkKhaiSinh", ttdkKhaiSinh);
		model.addObject("dsTinh", dsTinh);
		model.addObject("dsDanToc", dsDanToc);
		model.setViewName("CapNhatDKKhaiSinh");
		return model;
	}
	@RequestMapping(value = "/cap-nhat-dk-khai-sinh" , method=RequestMethod.POST)
	public ModelAndView capNhatDKKhaiSinh(String soKS, String benhVien, 
			String hoTen, String ngaySinh, String gioiTinh, 
			String quocTich, String danToc, String noiSinhXa, String queQuanXa, 
			String soCCNguoiYeuCau, String quanHeVoiNguoiKS, String soCCCha, 
			String soCCMe, HttpSession session
		) {
		//Bo dau gach ngang
		soCCNguoiYeuCau = soCCNguoiYeuCau.replaceAll("-", "");
		soCCCha = soCCCha.replaceAll("-", "");
		soCCMe = soCCMe.replaceAll("-", "");
		ModelAndView model = new ModelAndView();
		
		TTDKKhaiSinh ttdkKhaiSinh = new TTDKKhaiSinh();
		DanToc dt = new DanToc();
		/*CAN SUA LAI
		 */
		ttdkKhaiSinh.setSoKS(soKS);
		ttdkKhaiSinh.setHoTen(hoTen);
		ttdkKhaiSinh.setGioiTinh(gioiTinh);
		ttdkKhaiSinh.setNgaySinh(functionService.dinhDangNgay(ngaySinh));
		ttdkKhaiSinh.setQuocTich(quocTich);
		//Set dan toc
		dt.setMaDT(danToc);
		ttdkKhaiSinh.setDanToc(dt);
		ttdkKhaiSinh.setBenhVien(benhVien);
		//Noi sinh tinh khong null, da kiem tra ben js
		String noiSinh = noiSinhXa;
		
		ttdkKhaiSinh.setNoiSinh(noiSinh);
		ttdkKhaiSinh.setQueQuan(queQuanXa);
		ttdkKhaiSinh.setSoCCNguoiYeuCau(soCCNguoiYeuCau);
		ttdkKhaiSinh.setQuanHeVoiNguoiKS(quanHeVoiNguoiKS);
		ttdkKhaiSinh.setSoCCCha(soCCCha);
		ttdkKhaiSinh.setSoCCMe(soCCMe);
		Boolean kqCapNhat = khaiSinhService.capNhatTTDKKhaiSinh(ttdkKhaiSinh);
		session.setAttribute("kqCapNhat", kqCapNhat);
		model.addObject("soKS", soKS);
		
		model.setViewName(Const.REDIRECT_CAP_NHAT_DKKS);
		return model;
	}
	@RequestMapping(value = "/duyet-dk-khai-sinh" , method=RequestMethod.GET)
	public ModelAndView duyetDKKhaiSinh(String soKS, String ghiChu, HttpSession session) {
		ModelAndView model = new ModelAndView();
		session.removeAttribute("kqDuyetDKKS");
		String viewName = Const.REDIRECT_CT_DKKS;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		DuyetDKKS duyetDKKS = new DuyetDKKS();
		Boolean kqDuyetDKKS = false;
		try {
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
				duyetDKKS.setSoDK(0);
				duyetDKKS.setSoKS(soKS);
				duyetDKKS.setNgayDuocDuyet(functionService.getNgayHienTai());
				duyetDKKS.setNguoiDuyet(taiKhoan.getUsername());
				duyetDKKS.setGhiChu(ghiChu);
				if(khaiSinhService.themDuyetDKKS(duyetDKKS)) {
					int trangThai = 0;
					if(quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KHAI_SINH_1)) {
						trangThai = 1;
					} else if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KHAI_SINH_2)){
						trangThai = 2;
					}
					//Da xac nhan dung thong tin khai bao (Trang_Thai = 1)
					kqDuyetDKKS = khaiSinhService.capNhatTrangThai(soKS, trangThai, "");
					//neu capNhat thanh cong va intTrangThai == 2 (Trang thai duyet cua Chu tich UBND xa)
					//Thi them mot hang moi vao KHAI_SINH
					if (kqDuyetDKKS && (trangThai == 2)) {
						KhaiSinh ks = new KhaiSinh();
						TTDKKhaiSinh ttdk = khaiSinhService.getTTDKKhaiSinhBangSoKS(soKS);
						//Neu so khai sinh da ton tai thi tao so khai sinh moi
						KhaiSinh ktKhaiSinh = khaiSinhService.getKhaiSinhBangSoKS(soKS);
						if (ktKhaiSinh != null) {
							soKS = khaiSinhService.taoSoKS();
						}
//						khaiSinhService.capNhatSoKS(khaiSinhService.taoSoKS(), "0", 
//								taiKhoan.getUsername(), functionService.getNgayHienTai());
						ks.setSoKS(soKS);
						ks.setHoTen(ttdk.getHoTen());
						ks.setNgaySinh(ttdk.getNgaySinh());
						ks.setGioiTinh(ttdk.getGioiTinh());
						ks.setQuocTich(ttdk.getQuocTich());
						ks.setDanToc(ttdk.getDanToc());
						ks.setBenhVien(ttdk.getBenhVien());
						ks.setNoiSinh(ttdk.getNoiSinh());
						ks.setQueQuan(ttdk.getQueQuan());
						ks.setSoCCNguoiYeuCau(ttdk.getSoCCNguoiYeuCau());
						ks.setQuanHeVoiNguoiKS(ttdk.getQuanHeVoiNguoiKS());
						ks.setSoCCCha(ttdk.getSoCCCha());
						ks.setSoCCMe(ttdk.getSoCCMe());
						ks.setNoiCap(taiKhoan.getCoQuan());
						kqDuyetDKKS = khaiSinhService.themGiayKhaiSinh(ks);
					}
				}
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (NullPointerException e) {
			viewName = Const.VIEW_LOG_IN;
		} catch (Exception e) {
			viewName = "ThongBaoLoi";
			String thongBaoLoi = "Lỗi " + e.getMessage();
			model.addObject("thongBaoLoi", thongBaoLoi);
		}
		model.addObject("soKS", soKS);
		session.setAttribute("kqDuyetDKKS", kqDuyetDKKS);
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value = "/danh-sach-khai-sinh", method = RequestMethod.GET)
	public ModelAndView dsKhaiSinh(HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "DanhSachKhaiSinh";
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				try {
					TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
					List<KhaiSinh> dsKhaiSinh = khaiSinhService.getDSKhaiSinh(taiKhoan, functionService.getNgayHienTai());
					model.addObject("dsKhaiSinh", dsKhaiSinh);
				} catch (NullPointerException e) {
					viewName = Const.VIEW_LOG_IN;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value = "/cap-nhat-khai-sinh", method = RequestMethod.GET)
	public ModelAndView capNhatKhaiSinh(String soKS, HttpSession session) {	
		ModelAndView model = new ModelAndView();
		String noiSinh = "";
		String soCCNguoiDuyet = "";
		String soCCNguoiYeuCau = "";
		String soCCCha = "";
		String soCCMe = "";
		String ngaySinh = "";
		String maQueQuanXa = "";
		CCCD cccdNguoiDuyet = new CCCD();
		CCCD cccdNguoiYeuCau = new CCCD();
		CCCD cccdCha = new CCCD();
		CCCD cccdMe = new CCCD();
		Xa noiSinhXa = new Xa();
		Xa queQuanXa = new Xa();
		List<Tinh> dsTinh = tinhService.getDSTinh();
		List<DanToc> dsDanToc = danTocService.getDSDanToc();
		
		KhaiSinh khaiSinh = khaiSinhService.getKhaiSinhBangSoKS(soKS);
		soCCNguoiYeuCau = khaiSinh.getSoCCNguoiYeuCau();
		soCCCha = khaiSinh.getSoCCCha();
		soCCMe = khaiSinh.getSoCCMe();
		noiSinh = khaiSinh.getNoiSinh();
		maQueQuanXa = khaiSinh.getQueQuan();
		
		//Dinh dang lai ngaySinh(Nam-Thang-Ngay) de hien thi len trang jsp
		ngaySinh = khaiSinh.getNgaySinh();
		khaiSinh.setNgaySinh(functionService.dinhDangNgay(ngaySinh));
		
		cccdNguoiDuyet = cccdService.getCCCD1(soCCNguoiDuyet);
		cccdNguoiYeuCau = cccdService.getCCCD1(soCCNguoiYeuCau);
		cccdCha = cccdService.getCCCD1(soCCCha);
		cccdMe = cccdService.getCCCD1(soCCMe);
		queQuanXa = xaService.getXaHuyenTinh(maQueQuanXa);
		noiSinhXa = xaService.getXaHuyenTinh(noiSinh);
		
		session.setAttribute("noiSinhXa", noiSinhXa);
		session.setAttribute("queQuanXa", queQuanXa);
		session.setAttribute("cccdNguoiDuyet", cccdNguoiDuyet);
		session.setAttribute("cccdNguoiYeuCau", cccdNguoiYeuCau);
		session.setAttribute("cccdCha", cccdCha);
		session.setAttribute("cccdMe", cccdMe);
		session.setAttribute("khaiSinh", khaiSinh);
		model.addObject("dsTinh", dsTinh);
		model.addObject("dsDanToc", dsDanToc);
		model.setViewName("CapNhatKhaiSinh");
		return model;
	}
	@RequestMapping(value = "/trang-chi-tiet-khai-sinh", method = RequestMethod.GET)
	public ModelAndView chiTietKhaiSinh(String soKS, HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "ChiTietKhaiSinh";
		try {
			KhaiSinh khaiSinh = khaiSinhService.getKhaiSinhBangSoKS(soKS);
			DuyetDKKS duyetDKKS = new DuyetDKKS();
			try {
				duyetDKKS = khaiSinhService.getDuyetDKKS(soKS);
			} catch (IndexOutOfBoundsException e) {
				
			}
			String noiCap = khaiSinh.getNoiCap();
			Xa xaNoiCap = xaService.getXaBangMa(noiCap);
			String maXaNoiSinh = khaiSinh.getNoiSinh();
			String maXaQueQuan = khaiSinh.getQueQuan();
			Xa noiSinh = xaService.getXaHuyenTinh(maXaNoiSinh);
			Xa queQuan = xaService.getXaHuyenTinh(maXaQueQuan);
			
			final String title = "CHI TIẾT KHAI SINH";
			model.addObject("title", title);
			model.addObject("khaiSinh", khaiSinh);
			model.addObject("noiSinh", noiSinh);
			model.addObject("queQuan", queQuan);
			model.addObject("duyetDKKS", duyetDKKS);
			model.addObject("xaNoiCap", xaNoiCap);
		} catch(Exception e) {
		}
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value = "/nhap-khai-sinh", method = RequestMethod.GET)
	public ModelAndView trangNhapKhaiSinh(HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "NhapKhaiSinh";
		try {
			
			List<DanToc> dsDanToc = danTocService.getDSDanToc();
			List<Tinh> dsTinh = tinhService.getDSTinh();
			//Vu them
			List<SoHoKhau> danhSachCCCDCanNhapKhaiSinh = shkService.getDSNhapKhaiSinh();
			model.addObject("danhSachCCCD", danhSachCCCDCanNhapKhaiSinh);
			// end vu Them
			session.setAttribute("dsDanToc", dsDanToc);
			session.setAttribute("dsTinh", dsTinh);
		} catch (Exception e) {
		}
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/nhap-khai-sinh", method=RequestMethod.POST)
	public ModelAndView themGiayKhaiSinh(
				String hoTen, String ngaySinh, String gioiTinh, 
				String quocTich, String maDT, String noiSinhXa, String queQuanXa, 
				String ngayCap, String noiCapXa, String soCCNguoiDuyet, 
				String soCCNguoiYeuCau, String quanHeVoiNguoiKS, String soCCCha, 
				String soCCMe, String soKS, String soCC, 
				HttpSession session
			) {
		ModelAndView model = new ModelAndView();
		KhaiSinh ks = new KhaiSinh();
		//SoHoKhau shk = new SoHoKhau();
		Boolean kqThemKS = false;
		String viewName = "NhapKhaiSinh";
		try {
			List<DanToc> dsDanToc = danTocService.getDSDanToc();
			List<Tinh> dsTinh = tinhService.getDSTinh();
			session.setAttribute("dsDanToc", dsDanToc);
			session.setAttribute("dsTinh", dsTinh);
			
			DanToc dt = new DanToc();
			//Bo dau gach ngang
			soKS = soKS.replaceAll("-", "");
			soCCNguoiYeuCau = soCCNguoiYeuCau.replaceAll("-", "");
			if (soCCNguoiDuyet != null) {
				soCCNguoiDuyet = soCCNguoiDuyet.replaceAll("-", "");
			}
			soCCCha = soCCCha.replaceAll("-", "");
			soCCMe = soCCMe.replaceAll("-", "");
			//soKS = khaiSinhService.taoSoKS();
			//Set so khai sinh
			ks.setSoKS(soKS);
			ks.setHoTen(hoTen);
			ks.setGioiTinh(gioiTinh);
			ks.setNgaySinh(functionService.dinhDangNgay(ngaySinh));
			ks.setQuocTich(quocTich);
			//Set dan toc
			dt.setMaDT(maDT);
			ks.setDanToc(dt);
			ks.setNoiSinh(noiSinhXa);
			ks.setQueQuan(queQuanXa);
			ks.setSoCCNguoiYeuCau(soCCNguoiYeuCau);
			ks.setQuanHeVoiNguoiKS(quanHeVoiNguoiKS);
			ks.setSoCCCha(soCCCha);
			ks.setSoCCMe(soCCMe);
			ks.setNoiCap(noiCapXa);
			//Insert vao bang duyet_dkks
			DuyetDKKS duyetDKKS = new DuyetDKKS();
			duyetDKKS.setSoDK(0);
			duyetDKKS.setSoKS(soKS);
			duyetDKKS.setNgayDuocDuyet(functionService.dinhDangNgay(ngayCap));
			duyetDKKS.setNguoiDuyet(soCCNguoiDuyet);
			kqThemKS = khaiSinhService.themGiayKhaiSinh(ks);
			khaiSinhService.themDuyetDKKS(duyetDKKS);
			
		} catch (Exception e) {
			viewName = "ThongBaoLoi";
			String thongBaoLoi = "Lỗi " + e.getMessage();
			model.addObject("thongBaoLoi", thongBaoLoi);
			e.printStackTrace();
		}

		session.setAttribute("kqThemKS", kqThemKS);
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value = "/tim-kiem-khai-sinh")
	public ModelAndView timKiemKhaiSinh(String inputTimKiem, HttpSession session) {
		ModelAndView model = new ModelAndView();
		List<KhaiSinh> dsKhaiSinh = new ArrayList<KhaiSinh>();
		String viewName = "TimKiemKhaiSinh";
		String thongBaoLoi = "Unknow error";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			TaiKhoan taiKhoan = (TaiKhoan)session.getAttribute("taiKhoan");
			try {
				Long.valueOf(inputTimKiem);
				dsKhaiSinh = khaiSinhService.tkKhaiSinhBangSoKS(taiKhoan, inputTimKiem);
			} catch (NumberFormatException e) {
				String[] ngayDuyet = inputTimKiem.split("-");
				if (ngayDuyet.length == 3) {
					dsKhaiSinh = khaiSinhService.tkKhaiSinhBangNgayDuocDuyet(taiKhoan, inputTimKiem);
				} else {
					dsKhaiSinh = khaiSinhService.tkKhaiSinhBangHoTen(taiKhoan, inputTimKiem);
				}
			} catch (NullPointerException e) {
				viewName = "ThongBaoLoi";
				model.addObject("thongBaoLoi", thongBaoLoi);
			}
		} else {
			viewName = Const.VIEW_LOG_IN;
		}
		model.addObject("dsKhaiSinh", dsKhaiSinh);
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value = "/cap-nhat-khai-sinh" , method=RequestMethod.POST)
	public ModelAndView capNhatKhaiSinh(String soKS,
			String hoTen, String ngaySinh, String gioiTinh, 
			String quocTich, String danToc, String noiSinhXa, String queQuanXa, 
			String ngayCap, String noiCap, String soCCNguoiDuyet, 
			String soCCNguoiYeuCau, String quanHeVoiNguoiKS, String soCCCha, 
			String soCCMe, HttpSession session
		) {
		//Bo dau gach ngang
		soKS = soKS.replaceAll("-", "");
		soCCNguoiYeuCau = soCCNguoiYeuCau.replaceAll("-", "");
		soCCNguoiDuyet = soCCNguoiDuyet.replaceAll("-", "");
		soCCCha = soCCCha.replaceAll("-", "");
		soCCMe = soCCMe.replaceAll("-", "");
		ModelAndView model = new ModelAndView();
		
		KhaiSinh ks = new KhaiSinh();
		DanToc dt = new DanToc();
		//Set so khai sinh
		ks.setSoKS(soKS);
		
		ks.setHoTen(hoTen);
		ks.setGioiTinh(gioiTinh);
		ks.setNgaySinh(functionService.dinhDangNgay(ngaySinh));
		ks.setQuocTich(quocTich);
		//Set dan toc
		dt.setMaDT(danToc);
		ks.setDanToc(dt);
		//Noi sinh tinh khong null, da kiem tra ben js
		String noiSinh = noiSinhXa;
		
		ks.setNoiSinh(noiSinh);
		ks.setQueQuan(queQuanXa);
		ks.setSoCCNguoiYeuCau(soCCNguoiYeuCau);
		ks.setQuanHeVoiNguoiKS(quanHeVoiNguoiKS);
		ks.setSoCCCha(soCCCha);
		ks.setSoCCMe(soCCMe);
		Boolean kqCapNhat = khaiSinhService.capNhatKhaiSinh(ks);
		session.setAttribute("kqCapNhat", kqCapNhat);
		model.addObject("soKS", soKS);
		
		model.setViewName("redirect:cap-nhat-khai-sinh");
		return model;
	}
	@RequestMapping(value = "/tim-kiem-dk-khai-sinh")
	public ModelAndView timKiemDKKhaiSinh(
									String inputTimKiem,
									HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName="TimKiemDKKhaiSinh";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<TTDKKhaiSinh> dsDKKhaiSinh = new ArrayList<TTDKKhaiSinh>();
		try {
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan taiKhoan = (TaiKhoan)session.getAttribute("taiKhoan");
				//Lay vi tri cuoi cung CAN_BO_KS_1
				//Kq tra ve trangThai = 0
//				int trangThai = 0;
//				if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KHAI_SINH_2)){
//					trangThai = 1;
//				}
				try {
					Long.valueOf(inputTimKiem);
					dsDKKhaiSinh = khaiSinhService.getDSTTDKKhaiSinhBangSoCC(taiKhoan, inputTimKiem);
				} catch (NumberFormatException e) {
					String[] ngaySinh = inputTimKiem.split("-");
					if (ngaySinh.length == 3) {
						
					} else {
						dsDKKhaiSinh = khaiSinhService.getDSTTDKKhaiSinhBangHoTen(taiKhoan, inputTimKiem);
					}
				}
				model.addObject("dsDKKhaiSinh", dsDKKhaiSinh);
			} else {
				viewName = Const.VIEW_LOG_IN;
				
			}
		} catch (Exception e) {
		}
		model.setViewName(viewName);
		return model;
	}
	/**
	 * CHUA HOAN THANH
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/danh-sach-ttdk-khai-sinh-da-duyet")
	public ModelAndView danhSachKhaiSinhDaDuuyet (HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "DanhSachTTDKKhaiSinhDaDuyet";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<TTDKKhaiSinh> dsDKKhaiSinh;
		try {
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan taiKhoan = (TaiKhoan)session.getAttribute("taiKhoan");
				try {
					dsDKKhaiSinh = khaiSinhService.getDSTTDKKhaiSinhDaDuyet(functionService.getNgayHienTai(), taiKhoan, Const.slDonTrongNgay);
					model.addObject("dsDKKhaiSinh", dsDKKhaiSinh);
				} catch (NullPointerException e) {
					viewName = Const.VIEW_LOG_IN;
				}
			}
		} catch (Exception e) {
		}
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/danh-sach-ttdk-khai-sinh-bi-tu-choi")
	public ModelAndView danhSachKhaiSinhBiTuChoi (HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "DanhSachDKKSBiTuChoi";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<TTDKKhaiSinh> dsDKKhaiSinh;
		try {
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan taiKhoan = (TaiKhoan)session.getAttribute("taiKhoan");
				try {
					dsDKKhaiSinh = khaiSinhService.getDSTTDKKhaiSinhBiTuChoi(taiKhoan, Const.slDonTrongNgay);
					model.addObject("dsDKKhaiSinh", dsDKKhaiSinh);
				} catch (NullPointerException e) {
					viewName = Const.VIEW_LOG_IN;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName(viewName);
		return model;
	}
	
	/**
	 * CHUA HOAN THANH
	 * @param soDK
	 * @param lyDo
	 * @param session
	 * @return
	 */
	
	
	@RequestMapping(value = "/bo-qua-ttdk-khai-sinh")
	public ModelAndView boQuaTTDKKhaiSinh (String soKS, String lyDo, HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "redirect:danh-sach-dk-khai-sinh";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Boolean kq = false;
		try {
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan taiKhoan = (TaiKhoan)session.getAttribute("taiKhoan");
				try {
					int trangThai = 3;
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KHAI_SINH_2)){
						trangThai = 4;
						kq = khaiSinhService.xoaDuyetDKKSBangSoKS(soKS);
					}
					khaiSinhService.capNhatTrangThai(soKS, trangThai, lyDo);
				} catch (NullPointerException e) {
					viewName = Const.VIEW_LOG_IN;
				}
			}
		} catch (Exception e) {
		}
		session.setAttribute("kq", kq);
		model.setViewName(viewName);
		return model;
	}
	
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/dang-ky-ket-hon", method = RequestMethod.GET)
	public ModelAndView trangDKKetHon() {
		ModelAndView model = new ModelAndView();
		String soCCNguoiDangKy = "";
		String viewName = "DangKyKetHon";
		String thongBaoLoi = "";
		bean.Vu.CCCD cccdNguoiDangKy;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {
			if (!(auth instanceof AnonymousAuthenticationToken)) {
					UserDetails userDetail = (UserDetails) auth.getPrincipal();
					try {
						soCCNguoiDangKy = userDetail.getUsername();
					} catch (NullPointerException e) {
						viewName = Const.VIEW_LOG_IN;
					}
					//Neu username la mo so
					try {
						Long.parseLong(soCCNguoiDangKy);
						cccdNguoiDangKy = cccdService.getCCCDBangMa(soCCNguoiDangKy);
						Boolean ktTuoiKetHon;
						//Kiem tra tuoi ket hon doi voi nam/nu
						if(cccdNguoiDangKy.getGioiTinh().equals("Nam")) {
							ktTuoiKetHon = honNhanService.ktTuoiKH(cccdNguoiDangKy.getNgaySinh(), Const.tuoiKHNam);
						} else {
							ktTuoiKetHon = honNhanService.ktTuoiKH(cccdNguoiDangKy.getNgaySinh(), Const.tuoiKHNu);
						}
						if(!ktTuoiKetHon) {
							thongBaoLoi = "Bạn chưa đủ tuổi để kết hôn";
							viewName = "ThongBaoLoi";
						}
						//Kiem tra tinh trang hon nhan hien tai cua nguoi dang ky ket hon
						//System.out.println("So cc dang ky: " + cccdNguoiDangKy.getSoCC());
						if (honNhanService.ktDaKetHon(cccdNguoiDangKy.getSoCC(), cccdNguoiDangKy.getGioiTinh())) {
							thongBaoLoi = "Bạn không có quyền đăng ký kết hôn khi đang có vợ hoặc chồng.";
							viewName = "ThongBaoLoi";
						}
						List<Tinh> dsTinh = tinhService.getDSTinh();
						model.addObject("dsTinh", dsTinh);
					//Neu username khong phai la mot so	
					} catch (NumberFormatException e) {
						viewName = "ThongBaoLoi";
						thongBaoLoi = "Vui lòng đăng nhập với quyền công dân để thực hiện tác vụ này";
					}	
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
			thongBaoLoi = "Error " + e.getMessage() + " " + e.hashCode();
			viewName = "ThongBaoLoi";
		}
		model.addObject("thongBaoLoi", thongBaoLoi);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/danh-sach-dk-ket-hon", method = RequestMethod.GET)
	public ModelAndView dsTTDKKetHon(HttpSession session) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String viewName = "DanhSachDKKetHon";
		try {
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
				int trangThai = 0;
				if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KET_HON_2)) {
					trangThai = 1;
				}
				try {
					List<TTDKKetHon> dsDKKetHon = honNhanService.getDSTTDKKetHon(trangThai, taiKhoan.getCoQuan(), functionService.getNgayHienTai(), Const.slDonTrongNgay);
					model.addObject("dsDKKetHon", dsDKKetHon);
				} catch (NullPointerException e) {
					viewName = Const.VIEW_LOG_IN;
				}
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
			String thongBaoLoi = "Error " + e.getMessage() + " " + e.hashCode();
			model.addObject("thongBaoLoi", thongBaoLoi);
			viewName = "ThongBaoLoi";
		}
		model.setViewName(viewName);
		return model;
	}
	/**
	 * @param soCCVoHoacChong
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/dang-ky-ket-hon", method = RequestMethod.POST)
	public ModelAndView dkKetHon(String soCCVoHoacChong, String noiDKLVXa, HttpSession session) {
		String soCCNguoiDangKy = "";
		String viewName = "GiayHenDKKH";
		String thongBaoLoi = "";
		ModelAndView model = new ModelAndView();
		try {
			soCCVoHoacChong = soCCVoHoacChong.replaceAll("-", "");
			//check if user is login
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
					UserDetails userDetail = (UserDetails) auth.getPrincipal();
					try {
						soCCNguoiDangKy = userDetail.getUsername();
					} catch (NullPointerException e) {
						viewName = Const.VIEW_LOG_IN;
					}
					long maXacNhan = functionService.getMaXacNhan();
					try {
						TTDKKetHon ttdkKetHon = new TTDKKetHon();
						ttdkKetHon.setSoCCNguoiDK(soCCNguoiDangKy);
						ttdkKetHon.setSoCCVoHoacChong(soCCVoHoacChong);
						ttdkKetHon.setNgayDangKy(functionService.getNgayHienTai());
						ttdkKetHon.setNgayHenLamViec(khaiSinhService.taoNgayHen(functionService.getNgayHienTai(), noiDKLVXa, Const.slDonTrongNgay));
						ttdkKetHon.setNoiDKLV(noiDKLVXa);	
						ttdkKetHon.setMaXacNhan(maXacNhan);
						if(honNhanService.themTTDKKetHon(ttdkKetHon)) {
							String thongBao = "Bạn đã đăng ký kêt hôn thành công.\n Vui lòng chờ xác nhận từ " + soCCVoHoacChong;
							model.addObject("thongBao", thongBao);
						}
					} catch (NullPointerException e) {
						thongBaoLoi = "Vui lòng cập nhật email trước khi đăng ký";
						viewName = "ThongBaoLoi";
						model.addObject("thongBaoLoi", thongBaoLoi);
					}
			} else {
				viewName = "ThongBaoLoi";
				thongBaoLoi = "Bạn phải đăng nhập để thực hiện tác vụ này";
				
			}
		} catch (Exception e) {
			thongBaoLoi = "Error " + e.getMessage() + " " + e.hashCode();
			model.addObject("thongBaoLoi", thongBaoLoi);
			viewName = "ThongBaoLoi";
			model.addObject("thongBaoLoi", thongBaoLoi);
		}
		
		
		model.setViewName(viewName);
		return model;
	}
	
	/**
	 * @param soCCB
	 * @return String
	 */
	@RequestMapping(value = "/kt-dang-ky-ket-hon", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String ktDangKyKetHon(String soCCNguoiDK, String soCCB) {
		Boolean ktGioiTinh = false;
		Boolean ktHuyetThong = false;
		Boolean ktTuoiKH = false;
		Boolean ktDaKetHonA = false;
		Boolean ktDaKetHonB = false;
		String kq = "";
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				//Mac dinh se lay so cc nguoi dang ky ket hon tu Username khi dang nhap
				String soCCA  = userDetail.getUsername();
				//Neu soCCNguoiDK khong rong thi so cc nguoi dang ky se la soCCNguoiDK
				if (!functionService.checkEmpty(soCCNguoiDK)) {
					soCCA = soCCNguoiDK;
				}
				bean.Vu.CCCD cccdA = cccdService.getCCCDBangMa(soCCA);
				bean.Vu.CCCD cccdB = cccdService.getCCCDBangMa(soCCB);
				//Kiem tra a va b khac gioi tinh
				ktGioiTinh = honNhanService.ktGioiTinh(cccdA.getGioiTinh(), cccdB.getGioiTinh());
				//Kiem tra a va b cung huyet thong
				ktHuyetThong = cccdService.ktHuyetThong(soCCA, soCCB);
				//Kiem tra tuoi ket hon doi voi nam/nu
				if(cccdB.getGioiTinh().equals("Nam")) {
					ktTuoiKH = honNhanService.ktTuoiKH(cccdB.getNgaySinh(), Const.tuoiKHNam);
				} else {
					ktTuoiKH = honNhanService.ktTuoiKH(cccdB.getNgaySinh(), Const.tuoiKHNu);	
				}
				System.out.println("Ho ten: " + cccdB.getHoTen());
				System.out.println("Ngay sinh B: " + cccdB.getNgaySinh());
				
				//Kiem tra tinh trang hon nhan hien tai
				ktDaKetHonA = honNhanService.ktDaKetHon(cccdA.getSoCC(), cccdA.getGioiTinh());
				ktDaKetHonB = honNhanService.ktDaKetHon(cccdB.getSoCC(), cccdB.getGioiTinh());
				//Kiem tra soCCA equals soCCB
				if (soCCA.equals(soCCB)) {
					kq = "Bên A và B cùng số căn cước công dân.";
				//Neu a va b dong gioi
				} else if (!ktGioiTinh) {
					kq = "Không thể kết hôn với người đồng giới.";
				//Neu a va b cung huyet thong
				} else if (ktHuyetThong){
					kq = "Không thể kết hôn với người cùng huyết thống.";
				//Neu b chua du tuoi ket hon
				} else if (!ktTuoiKH) {
					kq = "Đối tượng chưa đủ tuổi để kết hôn";
				} else if (ktDaKetHonA) {
					kq = cccdA.getHoTen() + " đã kết hôn.";
				} else if (ktDaKetHonB) {
					kq = cccdB.getHoTen() + " đã kết hôn.";
				}
			}
		} catch (NullPointerException e) {
		}
		return kq;
	}
	
	@RequestMapping(value = "/dong-y-dang-ky-ket-hon", method = RequestMethod.GET)
	public ModelAndView xacNhanDKKetHon(String soDK, HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "ThongBaoLoi";
		String thongBaoLoi = "";
		Boolean ktXacNhan = false;
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
				ktXacNhan = honNhanService.ktXacNhan(tk.getUsername(), 0);
				//Neu nguoi dung chua dong y
				if (!ktXacNhan) {
					honNhanService.capNhatTrangThai(soDK, 0, "");
					viewName = "redirect:danh-sach-yeu-cau-ket-hon";
				//Neu nguoi dung da dong y
				} else {
					viewName = "redirect:danh-sach-yeu-cau-ket-hon";
					
				}
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
			thongBaoLoi = "Error " + e.getMessage() + " " + e.hashCode();
			model.addObject("thongBaoLoi", thongBaoLoi);
		}
		System.out.println("kt: " + ktXacNhan);
		session.setAttribute("ktXacNhan", ktXacNhan);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/tu-choi-dang-ky-ket-hon", method = RequestMethod.GET)
	public ModelAndView tuChoiDKKH(String soDK, HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "ThongBaoLoi";
		String thongBaoLoi = "";
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				honNhanService.capNhatTrangThai(soDK, -2, "");
				viewName = "redirect:danh-sach-yeu-cau-ket-hon";
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
			thongBaoLoi = "Error " + e.getMessage() + " " + e.hashCode();
			model.addObject("thongBaoLoi", thongBaoLoi);
		}
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value = "/cap-nhat-dk-ket-hon", method = RequestMethod.GET)
	public ModelAndView capNhatDKKetHon(String soDK) {
		ModelAndView model = new ModelAndView();
		String viewName = "CapNhatDKKetHon";
		TTDKKetHon ttdk = new TTDKKetHon();
		bean.Vu.CCCD cccdNguoiDK = new bean.Vu.CCCD();
		bean.Vu.CCCD cccdVoHoacChong = new bean.Vu.CCCD();
		try {
			if (!functionService.checkEmpty(soDK)) {
				ttdk = honNhanService.getTTTDKKetHon(soDK);
				//Lay hoTen nguoi dang ky
				String soCCNguoiDK = ttdk.getSoCCNguoiDK();
				cccdNguoiDK = cccdService.getCCCDBangMa(soCCNguoiDK);
				//Lay hoTen vo hoac chong cua nguoi dang ky
				String soCCVoHoacChong = ttdk.getSoCCVoHoacChong();
				cccdVoHoacChong = cccdService.getCCCDBangMa(soCCVoHoacChong);
			}
			model.addObject("cccdNguoiDK", cccdNguoiDK);
			model.addObject("cccdVoHoacChong", cccdVoHoacChong);
			model.addObject("ttdk", ttdk);
		} catch (Exception e) {
			
		}
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value = "/cap-nhat-dk-ket-hon", method = RequestMethod.POST)
	public ModelAndView capNhatDKKH(
			String soDK, 
			String soCCNguoiDangKy, 
			String soCCVoHoacChong, 
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = Const.REDIRECT_CAP_NHAT_DKKH;
		try {
			soCCNguoiDangKy = soCCNguoiDangKy.replaceAll("-", "");
			soCCVoHoacChong = soCCVoHoacChong.replaceAll("-", "");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//Da dang nhap
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TTDKKetHon ttdk = new TTDKKetHon();
				ttdk.setSoDK(soDK);
				ttdk.setSoCCNguoiDK(soCCNguoiDangKy);
				ttdk.setSoCCVoHoacChong(soCCVoHoacChong);
				Boolean kqCapNhat = false;
				kqCapNhat = honNhanService.capNhatTTDKKetHon(ttdk);
				session.setAttribute("kqCapNhat", kqCapNhat);
				model.addObject("soDK", soDK);
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
			viewName = "ThongBaoLoi";
			String thongBaoLoi = "Error " + e.getMessage() + ". Code " + e.hashCode();
			model.addObject("thongBaoLoi", thongBaoLoi);
		}
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value = "/tim-kiem-dk-ket-hon")
	public ModelAndView timKiemDKKetHon(
									String inputTimKiem,
									String optionTimKiem, 
									HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName="TimKiemDKKetHon";
		List<TTDKKetHon> dsDKKetHon = new ArrayList<TTDKKetHon>();
		//Dinh dang lai ngay thanh (ngay-thang-nam) 
		String ngay = "";
		try {
			if ("soDK".equals(optionTimKiem)) {
				dsDKKetHon = honNhanService.getDSTTTDKKetHon(inputTimKiem);
			} else if ("soCC".equals(optionTimKiem)) {
				dsDKKetHon = honNhanService.getDSTTDKKetHonBangSoCC(inputTimKiem);	
			} else if ("ngayDangKy".equals(optionTimKiem)) {
				ngay = functionService.dinhDangNgay(inputTimKiem);
				dsDKKetHon = honNhanService.getDSDKKHTheoNgayDK(ngay);
			} else if ("ngayHen".equals(optionTimKiem)) {
				ngay = functionService.dinhDangNgay(inputTimKiem);
				dsDKKetHon = honNhanService.getDSDKKHTheoNgayHen(ngay);
			} else if ("ngayNhanGiay".equals(optionTimKiem)) {
				ngay = functionService.dinhDangNgay(inputTimKiem);
				dsDKKetHon = honNhanService.getDSDKKHTheoNgayNhanGiay(ngay);
			}
		} catch (NullPointerException e) {
			
		} catch (Exception e) {
			
		}
		model.addObject("dsDKKetHon", dsDKKetHon);
		model.setViewName(viewName);
		return model;
	}
	/**
	 * @param soDK
	 * @param mucDuyet
	 * @param ghiChu
	 * @param session
	 * @return model
	 */
	@RequestMapping(value = "/duyet-dk-ket-hon", method = RequestMethod.GET)
	public ModelAndView duyetDKKetHon(
									String soDK,
									String ghiChu, 
									HttpSession session) {
		ModelAndView model = new ModelAndView();
		DuyetDKKH duyetDKKH = new DuyetDKKH();
		Boolean kqDuyet = false;
		String viewName = Const.REDIRECT_CT_DKKH;
		String thongBaoLoi = "";
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
				duyetDKKH.setSoDK(Integer.valueOf(soDK));
				//Ngay duyet
				String ngayDuocDuyet = functionService.getNgayHienTai();
				duyetDKKH.setNgayDuocDuyet(ngayDuocDuyet);
				duyetDKKH.setNguoiDuyet(taiKhoan.getUsername());
				duyetDKKH.setGhiChu(ghiChu); 
				if (honNhanService.themDuyetDKKH(duyetDKKH)) {
					int trangThai = 1;
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KET_HON_2)) {
						trangThai = 2;
						TTDKKetHon ttdk = honNhanService.getTTTDKKetHon(soDK);
						HonNhan hn = new HonNhan();
						hn.setSoDK(String.valueOf(honNhanService.taoSoDK()));
						TinhTrangHN tinhTrangHN = new TinhTrangHN();
						tinhTrangHN.setMaTinhTrang("1");
						hn.setTinhTrangHN(tinhTrangHN);
						String soCCChong = "";
						String soCCVo = "";
						bean.Vu.CCCD cccd = cccdService.getCCCDBangMa(ttdk.getSoCCNguoiDK());
						if ("Nam".equals(cccd.getGioiTinh())) {
							soCCChong = cccd.getSoCC();
							soCCVo = ttdk.getSoCCVoHoacChong();
						} else {
							soCCVo = cccd.getSoCC();
							soCCChong = ttdk.getSoCCVoHoacChong();
						}
						hn.setSoCCChong(soCCChong);
						hn.setSoCCVo(soCCVo);
						honNhanService.themHonNhan(hn);
					}
					kqDuyet = honNhanService.capNhatTrangThai(soDK, trangThai, "");
					
				}
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
			thongBaoLoi = "Error " + e.getMessage() + " " + e.hashCode();
			viewName = "ThongBaoLoi";
		}
		model.addObject("soDK", soDK);
		session.setAttribute("kqDuyet", kqDuyet);
		model.addObject("thongBaoLoi", thongBaoLoi);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/get-thong-tin-ks", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getThongTinKS(String soKS){
		KhaiSinh ks = new KhaiSinh();
		String data = "";
		try {
			ks = khaiSinhService.getKhaiSinhBangSoKS(soKS);
			final String charSplit = "@@";
			if (ks != null) {
				data += ks.getHoTen() + charSplit;
				data += functionService.dinhDangNgay(ks.getNgaySinh()) + charSplit;
				data += ks.getGioiTinh() + charSplit;
				data += ks.getQuocTich() + charSplit;
				data += ks.getDanToc().getMaDT() + charSplit;
				data += ks.getBenhVien() + charSplit;
			}
		} catch (Exception e) {
			
		}
		return data;
	}
	@RequestMapping(value = "/kt-ton-tai-so-ks", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String ktTonTaiSoKS(String soKS){
		Boolean data = false;
		try {
			data = khaiSinhService.ktTonTaiSoKS(soKS);
		} catch (Exception e) {
			
		}
		return data.toString();
	}
	@RequestMapping(value = "/tu-sinh-so-ks", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String tuSinhSoKS(){
		String soKS1 = "";
		String soKS2 = "";
		long longSoKS1 = 0;
		long longSoKS2 = 0;
		long soKS = 0;
		String strSoKS = "";
		try {
			soKS1 = khaiSinhService.taoSoKSChoTTDK();
			soKS2 = khaiSinhService.taoSoKS();
			longSoKS1 = Long.valueOf(soKS1);
			longSoKS2 = Long.valueOf(soKS2);
			soKS = longSoKS1;
			if (longSoKS2 > longSoKS1) {
				soKS = longSoKS2;
			}
			strSoKS = String.valueOf(soKS);
			int lenSoKS = strSoKS.length();
			if (lenSoKS < 12) {
				for(int i = 0; i < (12 - lenSoKS); i++) {
					strSoKS = "0" + strSoKS;
				}
			}
		} catch (Exception e) {
			
		}
		return strSoKS;
	}
	@RequestMapping(value = "/bo-qua-dk-ket-hon", method = RequestMethod.GET)
	public ModelAndView boQuanDKKetHon(
									String soDK,
									String lyDo, 
									HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = Const.REDIRECT_DSDKKH;
		String thongBaoLoi;
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan taiKhoan = (TaiKhoan)session.getAttribute("taiKhoan");
				int trangThai = 3;
				if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KET_HON_2)) {
					trangThai = 4;
					honNhanService.xoaDuyetDKKHBangSoDK(soDK);
				}
				honNhanService.capNhatTrangThai(soDK, trangThai, lyDo);
//				DuyetDKKH duyetDKKH = new DuyetDKKH();
//				duyetDKKH.setNgayDuocDuyet(functionService.getNgayHienTai());
//				duyetDKKH.setNguoiDuyet(taiKhoan.getUsername());
//				duyetDKKH.setSoDK(Integer.valueOf(soDK));
//				duyetDKKH.setGhiChu(lyDo);
			}
		} catch (Exception e) {
			viewName = "ThongBaoLoi";
			thongBaoLoi = "Error " + e.getMessage() + ". Error code: " + e.hashCode();
			model.addObject("thongBaoLoi", thongBaoLoi);
		}
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/giay-khai-sinh", method = RequestMethod.GET)
	public ModelAndView giayKhaiSinh(String soKS) {
		ModelAndView model = new ModelAndView();
		String viewName = "GiayKhaiSinh";
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				KhaiSinh khaiSinh = khaiSinhService.getKhaiSinhBangSoKS(soKS);
				String soCCCha = "";
				bean.Vu.CCCD cccdCha = new bean.Vu.CCCD();
				String soCCMe = "";
				bean.Vu.CCCD cccdMe = new bean.Vu.CCCD();
				String noiSinhXa = "";
				String maDTCha = "";
				String maDTMe = "";
				DanToc danTocCha = new DanToc();
				DanToc danTocMe = new DanToc();
				Xa thuongTruCha = new Xa();
				Xa thuongTruMe = new Xa();
				Xa noiCap = new Xa();
				bean.Vu.CCCD cccdNguoiDK = new bean.Vu.CCCD();
				String ngaySinhBangChu =  "";
				//Truong hop soCCCha = null
				try  {
					//Lay thong tin cccd cua cha
					soCCCha = khaiSinh.getSoCCCha();
					//Lay thong tin cccd cua me
					soCCMe = khaiSinh.getSoCCMe();
					cccdCha = cccdService.getCCCDBangMa(soCCCha);
					cccdMe = cccdService.getCCCDBangMa(soCCMe);
					noiSinhXa = khaiSinh.getNoiSinh();
					try {
						maDTCha = cccdCha.getMaDT();
						maDTMe = cccdMe.getMaDT();
						danTocCha = danTocService.getDanTocID(maDTCha);
						danTocMe = danTocService.getDanTocID(maDTMe);
						thuongTruCha = xaService.getXaHuyenTinh(cccdCha.getNoiOHienTai());
						thuongTruMe = xaService.getXaHuyenTinh(cccdMe.getNoiOHienTai());
						String ngaySinhCha = cccdCha.getNgaySinh();
						String ngaySinhMe = cccdMe.getNgaySinh();
						String[] ngaySinhCuaCha = ngaySinhCha.split("-");
						String[] ngaySinhCuaMe = ngaySinhMe.split("-");
						//Lay nam sinh cua cha, ngaySinhCuaCha[2] = nam sinh cua cha
						cccdCha.setNgaySinh(ngaySinhCuaCha[2]);
						//Lay nam sinh cua me, ngaySinhCuaMe[2] = nam sinh cua me
						cccdMe.setNgaySinh(ngaySinhCuaMe[2]);
					} catch (NullPointerException e) {
					}
					noiCap = xaService.getXaHuyenTinh(khaiSinh.getNoiCap());
					cccdNguoiDK = cccdService.getCCCDBangMa(khaiSinh.getSoCCNguoiYeuCau());
					ngaySinhBangChu = functionService.docNgayThangNam(khaiSinh.getNgaySinh());
				} catch (NullPointerException e) {
				}
				Xa noiSinh = xaService.getXaHuyenTinh(noiSinhXa);
				model.addObject("noiSinh", noiSinh);
				model.addObject("cccdCha", cccdCha);
				model.addObject("cccdMe", cccdMe);
				model.addObject("danTocCha", danTocCha);
				model.addObject("danTocMe", danTocMe);
				model.addObject("thuongTruCha", thuongTruCha);
				model.addObject("thuongTruMe", thuongTruMe);
				model.addObject("noiCap", noiCap);
				model.addObject("cccdNguoiDK", cccdNguoiDK);
				model.addObject("ngaySinhBangChu", ngaySinhBangChu);
				model.addObject("khaiSinh", khaiSinh);
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
		}
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value = "/chi-tiet-dkkh", method = RequestMethod.GET)
	public ModelAndView chiTietDKKH(String soDK) {
		ModelAndView model = new ModelAndView();
		String viewName = "ChiTietDKKH";
		final String title = "Chi tiết đăng ký kết hôn";
		TTDKKetHon ttdkKetHon = new TTDKKetHon();
		String maNoiDKLV = "";
		Xa noiDKLV = new Xa();
		Xa noiCuTruChong = new Xa();
		Xa noiCuTruVo = new Xa();
		bean.Vu.CCCD cccdChong = new bean.Vu.CCCD();
		bean.Vu.CCCD cccdVo = new bean.Vu.CCCD();
		bean.Vu.CCCD cccdTemp = new bean.Vu.CCCD();
		DanToc dtChong = new DanToc();
		DanToc dtVo = new DanToc();
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//User da dang nhap
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				try {
					ttdkKetHon = honNhanService.getTTTDKKetHon(soDK);
				} catch (Exception e) {
					
				}
				try {
					maNoiDKLV = ttdkKetHon.getNoiDKLV();
					noiDKLV = xaService.getXaHuyenTinh(maNoiDKLV);
					cccdTemp = cccdService.getCCCDBangMa(ttdkKetHon.getSoCCNguoiDK());
					if ("Nam".equals(cccdTemp.getGioiTinh())) {
						cccdChong = cccdTemp;
						cccdVo = cccdService.getCCCDBangMa(ttdkKetHon.getSoCCVoHoacChong());
					} else {
						cccdVo = cccdTemp;
						cccdChong = cccdService.getCCCDBangMa(ttdkKetHon.getSoCCVoHoacChong());
					}
					dtChong = danTocService.getDanTocID(cccdChong.getMaDT());
					dtVo = danTocService.getDanTocID(cccdVo.getMaDT());
					noiCuTruChong = xaService.getXaHuyenTinh(cccdChong.getNoiOHienTai());
					noiCuTruVo = xaService.getXaHuyenTinh(cccdVo.getNoiOHienTai());
				} catch (Exception e) {
					
				}
			//User chua dang nhap
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
			
		}
		model.addObject("ttdkKetHon", ttdkKetHon);
		model.addObject("noiDKLV", noiDKLV);
		model.addObject("cccdChong", cccdChong);
		model.addObject("cccdVo", cccdVo);
		model.addObject("dtChong", dtChong);
		model.addObject("dtVo", dtVo);
		model.addObject("noiCuTruChong", noiCuTruChong);
		model.addObject("noiCuTruVo", noiCuTruVo);
		model.addObject("title", title);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/giay-hen-dkkh", method = RequestMethod.GET)
	public ModelAndView giayHenDKKH(String soDK) {
		ModelAndView model = new ModelAndView();
		String viewName = "GiayHenDKKH";
		TTDKKetHon ttdkKetHon = new TTDKKetHon();
		String soCCNguoiDK = "";
		bean.Vu.CCCD cccdNguoiDK = new bean.Vu.CCCD();
		Xa diaDiem = new Xa();
		String giayTo = "";
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//User da dang nhap
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				ttdkKetHon = honNhanService.getTTTDKKetHon(soDK);
				soCCNguoiDK = ttdkKetHon.getSoCCNguoiDK();
				try {
					cccdNguoiDK = cccdService.getCCCDBangMa(soCCNguoiDK);
				} catch (Exception e) {
					
				}
				try {
					diaDiem = xaService.getXaHuyenTinh(ttdkKetHon.getNoiDKLV());
				} catch (Exception e) {
					
				}
				giayTo = "CCCD/CMND vợ và chồng, sổ hộ khẩu, giấy chứng nhận độc thân";
			//User chua dang nhap
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
			
		}
		model.addObject("giayTo", giayTo);
		model.addObject("diaDiem", diaDiem);
		model.addObject("cccdNguoiDK", cccdNguoiDK);
		model.addObject("ttdkKetHon", ttdkKetHon);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/danh-sach-hon-nhan", method = RequestMethod.GET)
	public ModelAndView getDSHonNhan(HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "DanhSachHonNhan";
		List<HonNhan> dsHonNhan = new ArrayList<HonNhan>();
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//User da dang nhap
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
				String ngayHienTai = functionService.getNgayHienTai();
				dsHonNhan = honNhanService.getDSHonNhan(tk, ngayHienTai, Const.slDonTrongNgay);
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
		}
		model.addObject("dsHonNhan", dsHonNhan);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/danh-sach-dk-ket-hon-da-duyet", method = RequestMethod.GET)
	public ModelAndView dsKetHonDaDuyet(HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "DanhSachDKKHBiTuChoi";
		List<TTDKKetHon> dsDKKetHon = new ArrayList<TTDKKetHon>();
		String title = "DANH SÁCH ĐĂNG KÝ KẾT HÔN ĐÃ XÁC NHẬN";
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//User da dang nhap
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
				String ngayHienTai = functionService.getNgayHienTai();
				dsDKKetHon = honNhanService.getDSTTDKKetHonDaDuyet(ngayHienTai, tk, Const.slDonTrongNgay);
				if (quyenService.kiemTraQuyenBangTen(tk.getUsername(), Const.DUYET_KET_HON_2)) {
					title = "DANH SÁCH ĐĂNG KÝ KẾT HÔN ĐÃ DUYỆT";
				}
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
		}
		
		model.addObject("title", title);
		model.addObject("dsDKKetHon", dsDKKetHon);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/danh-sach-dk-ket-hon-bi-tu-choi", method = RequestMethod.GET)
	public ModelAndView dsKetHonBiTuChoi(HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "DanhSachDKKHBiTuChoi";
		List<TTDKKetHon> dsDKKetHon = new ArrayList<TTDKKetHon>();
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//User da dang nhap
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
				dsDKKetHon = honNhanService.getDSTTDKKetHonBiTuChoi(tk, Const.slDonTrongNgay);
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
		}
		String title = "DANH SÁCH ĐĂNG KÝ KẾT HÔN BỊ TỪ CHỐI";
		model.addObject("title",title);
		model.addObject("dsDKKetHon", dsDKKetHon);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/tim-kiem-hon-nhan", method = RequestMethod.POST)
	public ModelAndView timKiemHonNhan(String inputTimKiem) {
		ModelAndView model = new ModelAndView();
		String viewName = "DanhSachHonNhan";
		List<HonNhan> dsHonNhan = new ArrayList<HonNhan>();
		try {
			dsHonNhan = honNhanService.getHonNhan(inputTimKiem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addObject("dsHonNhan", dsHonNhan);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/giay-chung-nhan-ket-hon", method = RequestMethod.GET)
	public ModelAndView giayChungNhanKetHon(String soDK) {
		ModelAndView model = new ModelAndView();
		String viewName = "ChiTietHonNhan";
		HonNhan honNhan = new HonNhan();
		String soCCChong = "";
		String soCCVo = "";
		bean.Vu.CCCD cccdChong = new bean.Vu.CCCD();
		bean.Vu.CCCD cccdVo = new bean.Vu.CCCD();
		Xa thuongTruChong = new Xa();
		Xa thuongTruVo = new Xa();
		DanToc dtChong = new DanToc();
		DanToc dtVo = new DanToc();
		try {
			honNhan = honNhanService.getHonNhanBangSoDK(Integer.valueOf(soDK));
			soCCChong = honNhan.getSoCCChong();
			soCCVo = honNhan.getSoCCVo();
			cccdChong = cccdService.getCCCDBangMa(soCCChong);
			cccdVo = cccdService.getCCCDBangMa(soCCVo);
			try {
				thuongTruChong = xaService.getXaHuyenTinh(cccdChong.getNoiOHienTai());
				thuongTruVo = xaService.getXaHuyenTinh(cccdVo.getNoiOHienTai());
			} catch (Exception e) {
				
			}
			try {
				dtChong = danTocService.getDanTocID(cccdChong.getMaDT());
				dtVo = danTocService.getDanTocID(cccdVo.getMaDT());
			} catch (Exception e) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addObject("thuongTruChong", thuongTruChong);
		model.addObject("thuongTruVo", thuongTruVo);
		model.addObject("dtChong", dtChong);
		model.addObject("dtVo", dtVo);
		model.addObject("honNhan", honNhan);
		model.addObject("cccdChong", cccdChong);
		model.addObject("cccdVo", cccdVo);
		model.setViewName(viewName);
		return model;
	}
	
	@RequestMapping(value = "/danh-sach-yeu-cau-ket-hon", method = RequestMethod.GET)
	public ModelAndView dsYeuCauKH(HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "DanhSachYeuCauKH";
		List<TTDKKetHon> dsYeuCauKH = new java.util.ArrayList<TTDKKetHon>();
//		bean.Vu.CCCD cccdNguoiDK = new bean.Vu.CCCD();
//		List<bean.Vu.CCCD> dsCCCDNguoiDK = new java.util.ArrayList<bean.Vu.CCCD>();
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//User da dang nhap
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
				dsYeuCauKH = honNhanService.getDKKetHonBangSoCCVoHoacChong(tk.getUsername());
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//model.addObject("dsCCCDNguoiDK", dsCCCDNguoiDK);
		model.addObject("dsYeuCauKH", dsYeuCauKH);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/get-ly-do-tu-choi", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getThongTin(String soKS) {
		String lyDo = "";
		System.out.println("Ly do: " + soKS);
		try {
			TTDKKhaiSinh ttdk = khaiSinhService.getTTDKKhaiSinhBangSoKS(soKS);
			lyDo = ttdk.getGhiChu();
			System.out.println("Ghi chu: " + lyDo);
		} catch (Exception e) {
			
		}
		return lyDo;
	}
	@RequestMapping(value = "/huy-xac-nhan-ttdk-khai-sinh", method = RequestMethod.GET)
	public ModelAndView huyXacNhanTTDKKhaiSinh(String soKS, String lyDo, HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = "redirect:chi-tiet-dk-khai-sinh";
		Boolean kqHuy = false;
		int trangThai = 0;
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//User da dang nhap
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
				if(khaiSinhService.xoaDuyetDKKSBangSoKS1(soKS, taiKhoan.getUsername())) {
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KHAI_SINH_2)) {
						trangThai = 1;
						khaiSinhService.xoaKhaiSinh(soKS);
					}
					kqHuy = khaiSinhService.capNhatTrangThai(soKS, trangThai, lyDo);
				}
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addObject("soKS", soKS);
		session.setAttribute("kqHuy", kqHuy);
		model.setViewName(viewName);
		return model;
	}
	@RequestMapping(value = "/huy-xac-nhan-ttdk-ket-hon", method = RequestMethod.GET)
	public ModelAndView huyXacNhanTTDKKetHon(String soDK, String lyDo, HttpSession session) {
		ModelAndView model = new ModelAndView();
		String viewName = Const.REDIRECT_CT_DKKH;
		Boolean kqHuy = false;
		int trangThai = 0;
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//User da dang nhap
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
				TTDKKetHon ttdkKetHon = new TTDKKetHon();
				ttdkKetHon = honNhanService.getTTTDKKetHon(soDK);
				String soCC = ttdkKetHon.getSoCCNguoiDK();
				if(honNhanService.xoaDuyetDKKHBangSoDK(soDK, taiKhoan.getUsername())) {
					if (quyenService.kiemTraQuyenBangTen(taiKhoan.getUsername(), Const.DUYET_KET_HON_2)) {
						trangThai = 1;
						honNhanService.xoaHonNhan(soCC);
					}
					kqHuy = honNhanService.capNhatTrangThai(soDK, trangThai, lyDo);
				}
			} else {
				viewName = Const.VIEW_LOG_IN;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addObject("soDK", soDK);
		session.setAttribute("kqHuy", kqHuy);
		model.setViewName(viewName);
		return model;
	}
}
