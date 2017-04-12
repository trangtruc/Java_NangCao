package com.mkyong.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Constant.Const;
import bean.An.DSQuanHe;
import bean.Chung.DanToc;
import bean.Chung.MD5;
import bean.Chung.Quyen;
import bean.Chung.TaiKhoan;
import bean.Chung.Tinh;
import bean.Chung.Huyen;
import bean.Chung.Xa;
import bean.Chung.YeuCau;
import bean.Config.ConfigCCCD;
import bean.Config.ConfigEmail;
import bean.Config.ConfigKetHon;
import bean.Config.ConfigNgayNghi;
import bean.Config.ConfigSoCC;

import com.mkyong.services.ConfigCCCDService;
import com.mkyong.services.ConfigEmailService;
import com.mkyong.services.ConfigKetHonService;
import com.mkyong.services.ConfigNgayNghiService;
import com.mkyong.services.ConfigSoCCService;
import com.mkyong.services.DanTocService;
import com.mkyong.services.HuyenService;
import com.mkyong.services.QuyenService;
import com.mkyong.services.SoHoKhauService;
import com.mkyong.services.TaiKhoanService;
import com.mkyong.services.TinhService;
import com.mkyong.services.XaService;
import com.mkyong.services.YeuCauService;
import com.mkyong.services.function.FunctionService;

@Controller
public class AdminController {
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private TinhService tinhService;
	@Autowired
	private HuyenService huyenService;
	@Autowired
	private XaService xaService;
	@Autowired
	private QuyenService quyenService;
	@Autowired
	private DanTocService danTocService;
	@Autowired
	private YeuCauService yeuCauService;
	@Autowired
	private ConfigNgayNghiService ngayNghiService;
	@Autowired
	private ConfigCCCDService ccService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private ConfigKetHonService ckService;
	@Autowired
	private ConfigEmailService cmService;
	@Autowired
	private ConfigSoCCService cscService;
	@Autowired
	private SoHoKhauService shkService;
	
	
	@RequestMapping(value = "phan-quyen")
	public ModelAndView phanQuyen(String tk, HttpSession session, HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_TAI_KHOAN")){
				TaiKhoan taiKhoan = null;
				if(tk == null || tk.equals(session.getAttribute("ssTaiKhoan").toString()) || tk.equals("admin")){
					session.setAttribute("error", "Bạn không thể thay đổi quyền của tài khoản này");
					response.sendRedirect("quan-ly-tai-khoan");
					model.setViewName("Temp");
					return model;
				} else {
					taiKhoan = taiKhoanService.getTaiKhoan(tk);
				}
				List<Quyen> quyen = quyenService.getDSQuyen();
				try{
					String[] tableQuyen = this.get5BangQuyen(quyen, taiKhoan.getUsername(), session).split("_");
					model.addObject("taiKhoan", taiKhoan.getUsername());
					model.addObject("hoTen", taiKhoan.getHoTen());
					model.addObject("tableQuyen1", tableQuyen[0]);
					model.addObject("tableQuyen2", tableQuyen[1]);
					model.addObject("tableQuyen3", tableQuyen[2]);
					model.addObject("tableQuyen4", tableQuyen[3]);
					model.addObject("tableQuyen5", tableQuyen[4]);
					model.setViewName("PhanQuyen");
				} catch(Exception e){
					model.addObject("thongBaoLoi", e.getMessage());
					model.setViewName("ThongBaoLoi");
					return model;
				}
			} else {
				model.addObject("thongBaoLoi", Const.loiQuyen);
				model.setViewName("ThongBaoLoi");
			}
		} catch(Exception e){
			model.addObject("error", "Lỗi: Bạn chưa đăng nhập");
			model.setViewName("login");
		}
		return model;
	}
	
	@RequestMapping(value = "phan-quyen", method = RequestMethod.POST)
	public ModelAndView capNhatQuyenTaiKhoan(String taiKhoan, HttpSession session, HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_TAI_KHOAN")){
				TaiKhoan tk = taiKhoanService.getTaiKhoan(taiKhoan);
				List<Quyen> quyen = quyenService.getDSQuyen();
				for(int i =0; i < quyen.size(); i++){
					String quyenCapNhat = request.getParameter(""+quyen.get(i).getMaQuyen());
					if(quyenCapNhat == null){
						quyenService.deleteQuyen(taiKhoan, quyen.get(i).getMaQuyen());
					} else {
						if(!quyenService.kiemTraQuyen(taiKhoan, quyen.get(i).getMaQuyen())){
							quyenService.addQuyen(taiKhoan, quyen.get(i).getMaQuyen());
						}
					}
				}
				try{
					String[] tableQuyen = this.get5BangQuyen(quyen, taiKhoan, session).split("_");
					model.addObject("taiKhoan", taiKhoan);
					model.addObject("hoTen", tk.getHoTen());
					model.addObject("tableQuyen1", tableQuyen[0]);
					model.addObject("tableQuyen2", tableQuyen[1]);
					model.addObject("tableQuyen3", tableQuyen[2]);
					model.addObject("tableQuyen4", tableQuyen[3]);
					model.addObject("tableQuyen5", tableQuyen[4]);
					model.addObject("msg", "Quyền của tài khoản đã được thay đổi");
					model.setViewName("PhanQuyen");
				} catch(Exception e){
					model.addObject("thongBaoLoi", e.getMessage());
					model.setViewName("ThongBaoLoi");
				}
			} else {
				model.addObject("thongBaoLoi", Const.loiQuyen);
				model.setViewName("ThongBaoLoi");
			}
		} catch(Exception e){
			model.addObject("error", "Lỗi: Bạn chưa đăng nhập");
			model.setViewName("login");
		}
		return model;
	}
	
	
	public String get5BangQuyen(List<Quyen> quyen, String taiKhoan, HttpSession session){
		String bangQuyen1 = "<table border=1>";
		String bangQuyen2 = "<table border=1>";
		String bangQuyen3 = "<table border=1>";
		String bangQuyen4 = "<table border=1>";
		String bangQuyen5 = "<table border=1>";
		for(int i = 0; i < quyen.size(); i++){
			int maQuyen = quyen.get(i).getMaQuyen();
			if(maQuyen <= 9){
				bangQuyen1 += "<tr id='row"+maQuyen+"'>";
				if(quyenService.kiemTraQuyen(taiKhoan, maQuyen)){
					bangQuyen1 += "<td>"+quyen.get(i).getMoTaQuyen()+"</td>"
								+ "<td><input type=checkbox name='"+maQuyen+"' id='"+maQuyen+"' value='1' checked /></td>";
				} else {
					bangQuyen1 += "<td>"+quyen.get(i).getMoTaQuyen()+"</td>"
							+ "<td><input type=checkbox name='"+maQuyen+"' id='"+maQuyen+"' value='1' /></td>";
				}
				bangQuyen1 += "</tr>";
			}
			if(maQuyen > 10 && maQuyen < 20){
				bangQuyen2 += "<tr id='row"+maQuyen+"'>";
				if(quyenService.kiemTraQuyen(taiKhoan, maQuyen)){
					bangQuyen2 += "<td>"+quyen.get(i).getMoTaQuyen()+"</td>"
								+ "<td><input type=checkbox name='"+maQuyen+"' id='"+maQuyen+"' value='1' checked /></td>";
				} else {
					bangQuyen2 += "<td>"+quyen.get(i).getMoTaQuyen()+"</td>"
							+ "<td><input type=checkbox name='"+maQuyen+"' id='"+maQuyen+"' value='1' /></td>";
				}
				bangQuyen2 += "</tr>";
			}
			if(maQuyen > 20 && maQuyen < 30){
				bangQuyen3 += "<tr id='row"+maQuyen+"'>";
				if(quyenService.kiemTraQuyen(taiKhoan, maQuyen)){
					bangQuyen3 += "<td>"+quyen.get(i).getMoTaQuyen()+"</td>"
								+ "<td><input type=checkbox name='"+maQuyen+"' id='"+maQuyen+"' value='1' checked /></td>";
				} else {
					bangQuyen3 += "<td>"+quyen.get(i).getMoTaQuyen()+"</td>"
							+ "<td><input type=checkbox name='"+maQuyen+"' id='"+maQuyen+"' value='1' /></td>";
				}
				bangQuyen3 += "</tr>";
			}
			if(maQuyen > 30 && maQuyen < 40){
				bangQuyen4 += "<tr id='row"+maQuyen+"'>";
				if(quyenService.kiemTraQuyen(taiKhoan, maQuyen)){
					bangQuyen4 += "<td>"+quyen.get(i).getMoTaQuyen()+"</td>"
								+ "<td><input type=checkbox name='"+maQuyen+"' id='"+maQuyen+"' value='1' checked /></td>";
				} else {
					bangQuyen4 += "<td>"+quyen.get(i).getMoTaQuyen()+"</td>"
							+ "<td><input type=checkbox name='"+maQuyen+"' id='"+maQuyen+"' value='1' /></td>";
				}
				bangQuyen4 += "</tr>";
			}
			if(maQuyen > 40){
				bangQuyen5 += "<tr id='row"+maQuyen+"'>";
				if(quyenService.kiemTraQuyen(taiKhoan, maQuyen)){
					bangQuyen5 += "<td>"+quyen.get(i).getMoTaQuyen()+"</td>"
								+ "<td><input type=checkbox name='"+maQuyen+"' id='"+maQuyen+"' value='1' checked /></td>";
				} else {
					bangQuyen5 += "<td>"+quyen.get(i).getMoTaQuyen()+"</td>"
							+ "<td><input type=checkbox name='"+maQuyen+"' id='"+maQuyen+"' value='1' /></td>";
				}
				bangQuyen5 += "</tr>";
			}
		}
		bangQuyen1 += "</table>";
		bangQuyen2 += "</table>";
		bangQuyen3 += "</table>";
		bangQuyen4 += "</table>";
		bangQuyen5 += "</table>";
		return bangQuyen1+"_"+bangQuyen2+"_"+bangQuyen3+"_"+bangQuyen4+"_"+bangQuyen5;
	}
	
	
	@RequestMapping(value = "quan-ly-tai-khoan")
	public ModelAndView quanLyTaiKhoan(){
		ModelAndView model = new ModelAndView();
		try{
			List<TaiKhoan> taiKhoan = taiKhoanService.getDSTaiKhoan();
			String html = "";
			List<Tinh> dsTinh = tinhService.getDSTinh();
			List<Huyen> dsHuyen = null;
			List<Xa> dsXa = null;
			String tinh = "";
			String huyen = "";
			String xa = "";
			if(!taiKhoan.isEmpty()){
				html = this.createBangTaiKhoan(taiKhoan);
				String[]nLamViec = taiKhoan.get(0).getCoQuan().split("-");
				if(nLamViec[0].equals("0")){
					tinh += "<option value='0' selected>Không có</option>";
					huyen += "<option value='0' selected>Không có</option>";
					xa += "<option value='0' selected>Không có</option>";
				}
				if(nLamViec.length == 1){
					tinh += functionService.getSelectTinh(nLamViec[0],dsTinh);
				}
				if(nLamViec.length == 2){
					dsHuyen = huyenService.getDSHuyen(nLamViec[0]);
					tinh += functionService.getSelectTinh(nLamViec[0], dsTinh);
					huyen += functionService.getSelectHuyen(nLamViec[1], dsHuyen);
				}
				if(nLamViec.length == 3){
					dsHuyen = huyenService.getDSHuyen(nLamViec[0]);
					dsXa = xaService.getDSXa(nLamViec[1]);
					tinh += functionService.getSelectTinh(nLamViec[0], dsTinh);
					huyen += functionService.getSelectHuyen(nLamViec[1], dsHuyen);
					xa += functionService.getSelectXa(nLamViec[0], dsXa);
				}
			} else {
				html = "<tr><td colspan=7>Không có tài khoản nào</td></tr>";
			}
			List<Quyen> quyen = quyenService.getDSQuyen();
			model.addObject("dsTinh", dsTinh);
			model.addObject("updateTinh", tinh);
			model.addObject("updateHuyen", huyen);
			model.addObject("updateXa", xa);
			model.addObject("dsQuyen", quyen);
			model.addObject("dsTaiKhoan", html);
			model.addObject("updateTaiKhoan", taiKhoan);
			model.setViewName("TaiKhoan");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	@RequestMapping(value = "quan-ly-tai-khoan-hoat-dong")
	public ModelAndView quanLyTaiKhoanHoatDong(){
		ModelAndView model = new ModelAndView();
		try{
			List<TaiKhoan> taiKhoan = taiKhoanService.getDSTaiKhoanHoatDong();
			String html = "";
			List<Tinh> dsTinh = tinhService.getDSTinh();
			List<Huyen> dsHuyen = null;
			List<Xa> dsXa = null;
			String tinh = "";
			String huyen = "";
			String xa = "";
			if(!taiKhoan.isEmpty()){
				html = this.createBangTaiKhoan(taiKhoan);
				String[]nLamViec = taiKhoan.get(0).getCoQuan().split("-");
				if(nLamViec[0].equals("0")){
					tinh += "<option value='0' selected>Không có</option>";
					huyen += "<option value='0' selected>Không có</option>";
					xa += "<option value='0' selected>Không có</option>";
				}
				if(nLamViec.length == 1){
					tinh += functionService.getSelectTinh(nLamViec[0],dsTinh);
				}
				if(nLamViec.length == 2){
					dsHuyen = huyenService.getDSHuyen(nLamViec[0]);
					tinh += functionService.getSelectTinh(nLamViec[0], dsTinh);
					huyen += functionService.getSelectHuyen(nLamViec[1], dsHuyen);
				}
				if(nLamViec.length == 3){
					dsHuyen = huyenService.getDSHuyen(nLamViec[0]);
					dsXa = xaService.getDSXa(nLamViec[1]);
					tinh += functionService.getSelectTinh(nLamViec[0], dsTinh);
					huyen += functionService.getSelectHuyen(nLamViec[1], dsHuyen);
					xa += functionService.getSelectXa(nLamViec[0], dsXa);
				}
			} else {
				html = "<tr><td colspan=7>Không có tài khoản nào</td></tr>";
			}
			List<Quyen> quyen = quyenService.getDSQuyen();
			model.addObject("dsTinh", dsTinh);
			model.addObject("updateTinh", tinh);
			model.addObject("updateHuyen", huyen);
			model.addObject("updateXa", xa);
			model.addObject("dsQuyen", quyen);
			model.addObject("dsTaiKhoan", html);
			model.addObject("updateTaiKhoan", taiKhoan);
			model.setViewName("TaiKhoanHoatDong");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "quan-ly-tai-khoan-bi-khoa")
	public ModelAndView quanLyTaiKhoanBiKhoa(){
		ModelAndView model = new ModelAndView();
		try{
			List<TaiKhoan> taiKhoan = taiKhoanService.getDSTaiKhoanBiKhoa();
			String html = "";
			List<Tinh> dsTinh = tinhService.getDSTinh();
			List<Huyen> dsHuyen = null;
			List<Xa> dsXa = null;
			String tinh = "";
			String huyen = "";
			String xa = "";
			if(!taiKhoan.isEmpty()){
				html = this.createBangTaiKhoan(taiKhoan);
				String[]nLamViec = taiKhoan.get(0).getCoQuan().split("-");
				if(nLamViec[0].equals("0")){
					tinh += "<option value='0' selected>Không có</option>";
					huyen += "<option value='0' selected>Không có</option>";
					xa += "<option value='0' selected>Không có</option>";
				}
				if(nLamViec.length == 1){
					tinh += functionService.getSelectTinh(nLamViec[0],dsTinh);
				}
				if(nLamViec.length == 2){
					dsHuyen = huyenService.getDSHuyen(nLamViec[0]);
					tinh += functionService.getSelectTinh(nLamViec[0], dsTinh);
					huyen += functionService.getSelectHuyen(nLamViec[1], dsHuyen);
				}
				if(nLamViec.length == 3){
					dsHuyen = huyenService.getDSHuyen(nLamViec[0]);
					dsXa = xaService.getDSXa(nLamViec[1]);
					tinh += functionService.getSelectTinh(nLamViec[0], dsTinh);
					huyen += functionService.getSelectHuyen(nLamViec[1], dsHuyen);
					xa += functionService.getSelectXa(nLamViec[0], dsXa);
				}
			} else {
				html = "<tr><td colspan=7>Không có tài khoản nào bị khóa</td></tr>";
			}
			List<Quyen> quyen = quyenService.getDSQuyen();
			model.addObject("dsTinh", dsTinh);
			model.addObject("updateTinh", tinh);
			model.addObject("updateHuyen", huyen);
			model.addObject("updateXa", xa);
			model.addObject("dsQuyen", quyen);
			model.addObject("dsTaiKhoan", html);
			model.addObject("updateTaiKhoan", taiKhoan);
			model.setViewName("TaiKhoanBiKhoa");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	
	public String createBangTaiKhoan(List<TaiKhoan> taiKhoan){
		String table="";
		if(!taiKhoan.isEmpty()){
			for(int i = 0; i< taiKhoan.size(); i++){
				String strEmail = "";
				String strSDT = "";
				if(taiKhoan.get(i).getEmail() != null){
					strEmail = taiKhoan.get(i).getEmail();
				}
				if(taiKhoan.get(i).getSoDienThoai() != null){
					strSDT = taiKhoan.get(i).getSoDienThoai();
				}
				table += "<tr>"
						+ "<td>"+taiKhoan.get(i).getUsername()+"</td>"
						+ "<td>"+taiKhoan.get(i).getHoTen()+"</td>"
						+ "<td>"+strEmail+"</td>"
						+ "<td>"+strSDT+"</td>"
						+ "<td><a href=phan-quyen?tk="+taiKhoan.get(i).getUsername()+">Thiết lập</a></td>"
						+ "<td><a onclick=capNhat('"+taiKhoan.get(i).getUsername()+"') >Cập nhật</a></td>";
				String tinhTrang = "<a onclick=khoaUser('"+taiKhoan.get(i).getUsername()+"')><img src='resources/image/bullet-green.png' width=10 height=10 /> Hoạt động</a>";
				if(taiKhoan.get(i).getTrangThai().equals("0")){
					tinhTrang = "<a onclick=moUser('"+taiKhoan.get(i).getUsername()+"')><img src='resources/image/bullet-red.png' width=10 height=10 /> Bị khóa</a>";
				}
				table += "<td>"+tinhTrang+"</td>"
					 + "</tr>";
			}
		} else {
			table = "<tr><td colspan=7>Không có tài khoản nào</td></tr>";
		}
		return table;
	}
	
	@RequestMapping(value = "/khoa-user", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView khoaUser(String taiKhoan, HttpSession session, HttpServletResponse response){
		if(taiKhoanService.khoaUser(taiKhoan)){
			session.setAttribute("msg", "Khóa tài khoản "+taiKhoan+" thành công");
		} else {
			session.setAttribute("error", "Khóa tài khoản "+taiKhoan+" thất bại");
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("Temp");
		return model;
	}
	@RequestMapping(value = "/mo-khoa-user", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView moKhoaUser(String taiKhoan, HttpSession session, HttpServletResponse response){
		if(taiKhoanService.moKhoaUser(taiKhoan)){
			session.setAttribute("msg", "Mở khóa tài khoản "+taiKhoan+" thành công");
		} else {
			session.setAttribute("error", "Mở khóa tài khoản "+taiKhoan+" thất bại");
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("Temp");
		return model;
	}
	@RequestMapping(value = "/them-tai-khoan", method = RequestMethod.GET)
	public ModelAndView formThemTaiKhoan(){
		List<Tinh> dsTinh = tinhService.getDSTinh();
		List<Quyen> quyen = quyenService.getDSQuyen();
		ModelAndView model = new ModelAndView();
		model.addObject("dsQuyen", quyen);
		model.addObject("dsTinh", dsTinh);
		model.setViewName("ThemTaiKhoan");
		return model;
	}
	@RequestMapping(value = "/them-tai-khoan", method = RequestMethod.POST)
	public ModelAndView xuLyThemTaiKhoan(String themTaiKhoan,
			String password1,
			String coQuanTinh,
			String coQuanHuyen,
			String coQuanXa,
			HttpSession session, HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_TAI_KHOAN")){
				String coQuan = "0";
				try{
					if(coQuanXa != null && (!coQuanXa.equals("0"))){
						coQuan = coQuanXa;
					} else if((!coQuanHuyen.equals("0")) && (coQuanHuyen != null)){
							coQuan = coQuanHuyen;
						} else if((!coQuanTinh.equals("0")) && (coQuanTinh != null)){	
							coQuan = coQuanTinh;
						} else {
							coQuan = "0";
						}
				} catch (Exception e){
					coQuan = "0";
				}
				TaiKhoan tonTai = taiKhoanService.getTaiKhoan(themTaiKhoan);
				if(tonTai == null){
					TaiKhoan taiKhoan = new TaiKhoan();
					taiKhoan.setUsername(themTaiKhoan);
					MD5 md5 = new MD5();
					String encodemd5 = md5.encryptMD5(password1);
					taiKhoan.setPassword(md5.encryptMD5(encodemd5));
					taiKhoan.setCoQuan(coQuan);
					if(taiKhoanService.themTaiKhoan(taiKhoan)){
						session.setAttribute("msg", "<div class='success'>Thêm tài khoản "+themTaiKhoan+" thành công</div>");
					} else {
						session.setAttribute("error", "<div class='error'>Thêm tài khoản "+themTaiKhoan+" thất bại</div>");
					}
					try {
						response.sendRedirect("quan-ly-tai-khoan");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.setViewName("Temp");
				} else {
					session.setAttribute("error", "Tài khoản đã tồn tại");
					try {
						response.sendRedirect("them-tai-khoan");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.setViewName("Temp");
				}
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
	@RequestMapping(value = "/cap-nhat-tai-khoan", method = RequestMethod.POST ,produces = "text/plain;charset=UTF-8")
	public ModelAndView xuLyCapNhatTaiKhoan(String updateTaiKhoan,
			String updateTinh,
			String updateHuyen,
			String updateXa,
			HttpSession session, HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_TAI_KHOAN")){
				String coQuan = updateXa;
				TaiKhoan taiKhoan = taiKhoanService.getTaiKhoan(updateTaiKhoan);
				try{
					if(updateXa.equals("0") || updateXa == null){
						if((!updateHuyen.equals("0")) && (updateHuyen != null)){
							coQuan = updateHuyen;
						} else {
								coQuan = updateTinh;
						}
					}
					taiKhoan.setCoQuan(coQuan);
					System.out.println(updateTinh);
					System.out.println(coQuan);
				} catch(Exception e){
					taiKhoan.setCoQuan("0");
				}
				
				if(taiKhoanService.updateTaiKhoan(taiKhoan)){
					session.setAttribute("msg", "Cập nhật tài khoản "+updateTaiKhoan+" thành công");
				} else {
					session.setAttribute("error", "Cập nhật tài khoản "+updateTaiKhoan+" thất bại");
				}
				try {
					response.sendRedirect("quan-ly-tai-khoan");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	@RequestMapping (value = "/tim-kiem-tai-khoan", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemTaiKhoan(String keySearch){
		String html = "";
		List<TaiKhoan> taiKhoan = taiKhoanService.timKiemTaiKhoan(keySearch);
		if(taiKhoan.size() > 0){
			html = this.createBangTaiKhoan(taiKhoan);
		} else {
			html = "<tr><td colspan='7'>Không tìm thấy kết quả nào với từ khóa: '"+keySearch+"'</td></tr>";
		}
		return html;
	}
	@RequestMapping (value = "/tim-kiem-tai-khoan-hoat-dong", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemTaiKhoanHoatDong(String keySearch){
		String html = "";
		List<TaiKhoan> taiKhoan = taiKhoanService.timKiemTaiKhoanHoatDong(keySearch);
		if(taiKhoan.size() > 0){
			html = this.createBangTaiKhoan(taiKhoan);
		} else {
			html = "<tr><td colspan='7'>Không tìm thấy kết quả nào với từ khóa: '"+keySearch+"'</td></tr>";
		}
		return html;
	}
	@RequestMapping (value = "/tim-kiem-tai-khoan-bi-khoa", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemTaiKhoanBiKhoa(String keySearch){
		String html = "";
		List<TaiKhoan> taiKhoan = taiKhoanService.timKiemTaiKhoanBiKhoa(keySearch);
		if(taiKhoan.size() > 0){
			html = this.createBangTaiKhoan(taiKhoan);
		} else {
			html = "<tr><td colspan='7'>Không tìm thấy kết quả nào với từ khóa: '"+keySearch+"'</td></tr>";
		}
		return html;
	}
	
	
	@RequestMapping (value = "/them-tinh", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView themTinh(String autoMaTinh,
			String themMaTinh,
			String themTenTinh,
			HttpSession session, 
			HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_TINH")){
				List<Tinh> dsTinh = tinhService.getDSTinh();
				Tinh themTinh = new Tinh();
				try{
					if(autoMaTinh.equals("1")){
						String maxMaTinh = dsTinh.get(dsTinh.size()-1).getMaTinh();
						String nextMaTinh = (Integer.parseInt(maxMaTinh)+1) +"";
						if(nextMaTinh.length() < 2){
							nextMaTinh = "0"+nextMaTinh;
						}
						themTinh.setMaTinh(nextMaTinh);
					} else{
						themTinh.setMaTinh(themMaTinh);
					}
				} catch(NullPointerException e){
					themTinh.setMaTinh(themMaTinh);
				}
				themTinh.setTenTinh(themTenTinh);
				if(tinhService.insertTinh(themTinh)){
					session.setAttribute("msg", "Đã thêm "+themTenTinh+" thành công");
				} else {
					session.setAttribute("error", "Thêm "+themTenTinh+" thất bại");
				}
				try {
					response.sendRedirect("quan-ly-tinh");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	
	
	@RequestMapping (value = "/them-dan-toc", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView themDanToc(String autoMaDanToc,
			String themMaDanToc,
			String themTenDanToc,
			HttpSession session, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_DAN_TOC")){
				List<DanToc> dsDanToc = danTocService.getDSDanToc();
				DanToc themDanToc = new DanToc();
				try{
					if(autoMaDanToc.equals("1")){
						String maxMaDanToc = dsDanToc.get(dsDanToc.size()-1).getMaDT();
						String nextMaDanToc = (Integer.parseInt(maxMaDanToc)+1) +"";
						if(nextMaDanToc.length() < 2){
							nextMaDanToc = "0"+nextMaDanToc;
						}
						themDanToc.setMaDT(nextMaDanToc);
					} else{
						themDanToc.setMaDT(themMaDanToc);
					}
				} catch(NullPointerException e){
					themDanToc.setMaDT(themMaDanToc);
				}
				themDanToc.setTenDT(themTenDanToc);
				if(danTocService.insertDanToc(themDanToc)){
					session.setAttribute("msg", "Đã thêm "+themTenDanToc+" thành công");
				} else {
					session.setAttribute("error", "Thêm "+themTenDanToc+" thất bại");
				}
				try {
					response.sendRedirect("quan-ly-dan-toc");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	
	
	
	
	@RequestMapping (value = "/update-tinh", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView updateTinh(String maTinh,
			String tenTinh,
			HttpSession session, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_TINH")){
				Tinh tinh = tinhService.getTinhBangMa(maTinh);
				Tinh updateTinh = new Tinh();
				updateTinh.setMaTinh(maTinh);
				updateTinh.setTenTinh(tenTinh);
				if(tinhService.updateTinh(updateTinh)){
					session.setAttribute("msg",
							"Đã đổi "+tinh.getTenTinh()+" thành "+updateTinh.getTenTinh()+" thành công");
				} else {
					session.setAttribute("error", "Đổi "+tinh.getTenTinh()+" thành "+tenTinh+" thất bại");
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
	
	@RequestMapping (value = "/update-huyen", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView updateHuyen(String maHuyen,
			String tenHuyen,
			String maTinh,
			HttpSession session, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_HUYEN")){
				Huyen huyen = huyenService.getHuyenBangMa(maHuyen);
				Huyen updateHuyen = new Huyen();
				updateHuyen.setMaHuyen(maHuyen);
				updateHuyen.setTenHuyen(tenHuyen);
				Tinh tinh = new Tinh();
				tinh.setMaTinh(maTinh);
				updateHuyen.setTinh(tinh);
				if(huyenService.updateHuyen(updateHuyen)){
					session.setAttribute("msg",
							"Đã đổi "+huyen.getTenHuyen()+" thành "+updateHuyen.getTenHuyen()+" thành công");
				} else {
					session.setAttribute("error", "Đổi "+huyen.getTenHuyen()+" thành "+tenHuyen+" thất bại");
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
	
	
	@RequestMapping (value = "/update-xa", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView updateXa(String maXa,
			String tenXa,
			String maHuyen,
			HttpSession session, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_XA")){
				Xa xa = xaService.getXaBangMa(maXa);
				Xa updateXa = new Xa();
				updateXa.setMaXa(maXa);
				updateXa.setTenXa(tenXa);
				Huyen huyen = new Huyen();
				huyen.setMaHuyen(maHuyen);
				updateXa.setHuyen(huyen);
				if(xaService.updateXa(updateXa)){
					session.setAttribute("msg",
							"Đã đổi "+xa.getTenXa()+" thành "+updateXa.getTenXa()+" thành công");
				} else {
					session.setAttribute("error", "Đổi "+xa.getTenXa()+" thành "+tenXa+" thất bại");
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
	
	@RequestMapping (value = "/update-dan-toc", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView updateDanToc(String maDanToc,
			String tenDanToc,
			HttpSession session, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_DAN_TOC")){
				DanToc danToc = danTocService.getDanTocID(maDanToc);
				DanToc updateDanToc = new DanToc();
				updateDanToc.setMaDT(maDanToc);
				updateDanToc.setTenDT(tenDanToc);
				if(danTocService.updateDanToc(updateDanToc)){
					session.setAttribute("msg",
							"Đã đổi "+danToc.getTenDT()+" thành "+updateDanToc.getTenDT()+" thành công");
				} else {
					session.setAttribute("error", "Đổi "+danToc.getTenDT()+" thành "+tenDanToc+" thất bại");
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
	
	@RequestMapping (value = "/update-yeu-cau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView updateYeuCau(int maYC,
			String updateTenYC,
			String updateMoTa,
			String updateGiayTo,
			int updateLePhi,
			HttpSession session, 
			HttpServletResponse response){
		
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_YEU_CAU_CCCD")){
				YeuCau updateYeuCau = new YeuCau();
				updateYeuCau.setMaYeuCau(maYC);
				updateYeuCau.setTenYeuCau(updateTenYC);
				updateYeuCau.setMoTa(updateMoTa);
				updateYeuCau.setGiayTo(updateGiayTo);
				updateYeuCau.setLePhi(updateLePhi);
				if(yeuCauService.updateYeuCau(updateYeuCau)){
					session.setAttribute("msg",
							"Cập nhật thành công");
				} else {
					session.setAttribute("error", "Cập nhật thất bại");
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
	
	@RequestMapping (value = "/tam-dung-yeu-cau-cccd", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView tamDungYeuCauCCCD(int maYeuCau,
			HttpSession session, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_YEU_CAU_CCCD")){
				YeuCau updateYeuCau = yeuCauService.getYeuCauID(maYeuCau);
				if(yeuCauService.tamDungYeuCau(updateYeuCau)){
					session.setAttribute("msg",
							"Đã tạm dừng yêu cầu "+updateYeuCau.getTenYeuCau()+" thành công");
				} else {
					session.setAttribute("error", "Tạm dừng yêu cầu "+updateYeuCau.getTenYeuCau()+" thất bại");
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
	
	@RequestMapping (value = "/tiep-tuc-yeu-cau-cccd", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView tiepTucYeuCauCCCD(int maYeuCau,
			HttpSession session, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_YEU_CAU_CCCD")){
				YeuCau updateYeuCau = yeuCauService.getYeuCauID(maYeuCau);
				if(yeuCauService.tiepTucYeuCau(updateYeuCau)){
					session.setAttribute("msg",
							"Đã tiếp tục yêu cầu "+updateYeuCau.getTenYeuCau()+" thành công");
				} else {
					session.setAttribute("error", "Tiếp tục yêu cầu "+updateYeuCau.getTenYeuCau()+" thất bại");
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
	
	
	@RequestMapping (value = "/check-ma-tinh", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String checkMaTinh(String maTinh){
		Tinh tinh = tinhService.getTinhBangMa(maTinh);
		String result = "";
		if(tinh != null){
			result = "Mã tỉnh đã tồn tại, hãy nhập mã khác";
		}
		return result;
	}
	@RequestMapping (value = "/check-ma-huyen", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String checkMaHuyen(String maHuyen){
		Huyen huyen = huyenService.getHuyenBangMa(maHuyen);
		String result = "";
		if(huyen != null){
			result = "Mã huyện đã tồn tại, hãy nhập mã khác";
		}
		return result;
	}
	@RequestMapping (value = "/check-ma-xa", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String checkMaXa(String maXa){
		Xa xa = xaService.getXaBangMa(maXa);
		String result = "";
		if(xa != null){
			result = "Mã xã đã tồn tại, hãy nhập mã khác";
		}
		return result;
	}
	
	
	@RequestMapping (value = "/check-ma-dan-toc", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String checkMaDanToc(String maDT){
		DanToc danToc = danTocService.getDanTocID(maDT);
		String result = "";
		if(danToc != null){
			result = "Mã dân tộc đã tồn tại, hãy nhập mã khác";
		}
		return result;
	}
	
	@RequestMapping (value = "/tim-kiem-tinh", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemTinh(String keySearch){
		List<Tinh> dsTinh = tinhService.timKiemTinh(keySearch);
		String dsTinh1 = "",dsTinh2 = "";
		if(dsTinh.size() > 0){
			String[] tinh = this.get2BangTinh(dsTinh).split("_");
			dsTinh1 = tinh[0];
			try{
			if(tinh[1].length()>0){
				dsTinh2 = tinh[1];
			}
			} catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Danh Sách tỉnh 2 rỗng");
			}
		} else {
			dsTinh1 = dsTinh2 = "<tr><td colspan=3>Không tìm thấy kết quả</td></tr>";
		}
		return dsTinh1+"_"+dsTinh2;
	}
	@RequestMapping (value = "/tim-kiem-huyen", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemHuyen(String keySearch){
		List<Huyen> dsHuyen = huyenService.timKiemHuyen(keySearch);
		String dsHuyen1 = "",dsHuyen2 = "";
		if(dsHuyen.size() > 0){
			String[] huyen = this.get2BangHuyen(dsHuyen).split("_");
			dsHuyen1 = huyen[0];
			try{
			if(huyen[1].length()>0){
				dsHuyen2 = huyen[1];
			}
			} catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Danh Sách Huyện 2 rỗng");
			}
		} else {
			dsHuyen1 = dsHuyen2 = "<tr><td colspan=3>Không tìm thấy kết quả</td></tr>";
		}
		return dsHuyen1+"_"+dsHuyen2;
	}
	
	@RequestMapping (value = "/tim-kiem-xa", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemXa(String keySearch){
		List<Xa> dsXa = xaService.timKiemXa(keySearch);
		String dsXa1 = "",dsXa2 = "";
		if(dsXa.size() > 0){
			try{
			String[] xa = this.get2BangXa(dsXa).split("_");
			dsXa1 = xa[0];
			if(xa[1].length()>0){
				dsXa2 = xa[1];
			}
			} catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Danh Sách Huyện 2 rỗng");
			} catch(NullPointerException e){
				System.out.println("Danh Sách Huyện rỗng");
			}
		} else {
			dsXa1 = dsXa2 = "<tr><td colspan=3>Không tìm thấy kết quả</td></tr>";
		}
		return dsXa1+"_"+dsXa2;
	}
	
	@RequestMapping (value = "/tim-kiem-dan-toc", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String timKiemDanToc(String keySearch){
		List<DanToc> dsDanToc = danTocService.timKiemDanToc(keySearch);
		String dsDanToc1 = "",dsDanToc2 = "";
		if(dsDanToc.size() > 0){
			String[] danToc = this.get2BangDanToc(dsDanToc).split("_");
			dsDanToc1 = danToc[0];
			try{
			if(danToc[1].length()>0){
				dsDanToc2 = danToc[1];
			}
			} catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Danh Sách tỉnh 2 rỗng");
			}
		} else {
			dsDanToc1 = dsDanToc2 = "<tr><td colspan=3>Không tìm thấy kết quả</td></tr>";
		}
		return dsDanToc1+"_"+dsDanToc2;
	}
	
	
	
	@RequestMapping (value = "/them-huyen", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView themHuyen(String autoMaHuyen,
			String themVaoTinh,
			String themMaHuyen,
			String themTenHuyen,
			HttpSession session, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_HUYEN")){
				List<Huyen> dsHuyen = huyenService.getDSHuyenAll();
				Huyen themHuyen = new Huyen();
				try{
					if(autoMaHuyen.equals("1")){
						String maxMaHuyen = dsHuyen.get(dsHuyen.size()-1).getMaHuyen();
						String nextMaHuyen = (Integer.parseInt(maxMaHuyen)+1) +"";
						if(nextMaHuyen.length() < 3){
							for(int i = 0; i < (3-nextMaHuyen.length()); i++){
								nextMaHuyen += "0"+nextMaHuyen;
							}
						}
						themHuyen.setMaHuyen(nextMaHuyen);
					} else{
						themHuyen.setMaHuyen(themMaHuyen);
					}
				} catch(NullPointerException e){
					themHuyen.setMaHuyen(themMaHuyen);
				}
				themHuyen.setTenHuyen(themTenHuyen);
				Tinh tinh = new Tinh();
				tinh.setMaTinh(themVaoTinh);
				themHuyen.setTinh(tinh);
				if(huyenService.insertHuyen(themHuyen)){
					session.setAttribute("msg", "Đã thêm "+themTenHuyen+" thành công");
				} else {
					session.setAttribute("error", "Thêm "+themTenHuyen+" thất bại");
				}
				try {
					response.sendRedirect("quan-ly-huyen");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	
	@RequestMapping (value = "/them-xa", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView themXa(String autoMaXa,
			String themVaoHuyen,
			String themMaXa,
			String themTenXa,
			HttpSession session, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_XA")){
				List<Xa> dsXa = xaService.getDSXaAll();
				Xa themXa = new Xa();
				try{
					if(autoMaXa.equals("1")){
						String maxMaXa = dsXa.get(dsXa.size()-1).getMaXa();
						String nextMaXa = (Integer.parseInt(maxMaXa)+1) +"";
						if(nextMaXa.length() < 5){
							for(int i = 0; i < (5-nextMaXa.length()); i++){
								nextMaXa += "0"+nextMaXa;
							}
						}
						themXa.setMaXa(nextMaXa);
					} else{
						themXa.setMaXa(themMaXa);
					}
				} catch(NullPointerException e){
					themXa.setMaXa(themMaXa);
				}
				themXa.setTenXa(themTenXa);
				Huyen huyen = new Huyen();
				huyen.setMaHuyen(themVaoHuyen);
				themXa.setHuyen(huyen);
				if(xaService.insertXa(themXa)){
					session.setAttribute("msg", "Đã thêm "+themTenXa+" thành công");
				} else {
					session.setAttribute("error", "Thêm "+themTenXa+" thất bại");
				}
				try {
					response.sendRedirect("quan-ly-xa");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	
	
	@RequestMapping (value = "/them-yeu-cau", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView themYeuCau(
			String themTenYeuCau,
			String themMoTa,
			String themGiayTo,
			int themLePhi,
			HttpSession session, 
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_YEU_CAU_CCCD")){
				YeuCau themYeuCau = new YeuCau();
				themYeuCau.setTenYeuCau(themTenYeuCau);
				themYeuCau.setMoTa(themMoTa);
				themYeuCau.setGiayTo(themGiayTo);
				themYeuCau.setLePhi(themLePhi);
				if(yeuCauService.insertYeuCau(themYeuCau)){
					session.setAttribute("msg", "Đã thêm yêu cầu "+themTenYeuCau+" thành công");
				} else {
					session.setAttribute("error", "Thêm yêu cầu "+themTenYeuCau+" thất bại");
				}
				try {
					response.sendRedirect("quan-ly-yeu-cau-dang-ky-cmnd-cccd");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	
	@RequestMapping (value = "/get-quyen-user", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getQuyenUser(String taiKhoan){
		String html = "";
		List<Quyen> quyen = quyenService.getDSQuyen();
		if(taiKhoan != null){
			for(int i = 0; i < quyen.size(); i++){
				if("maQuyen".equals(quyen.get(i).getTenQuyen())){
					 html += "<option value='"+quyen.get(i).getMaQuyen()+"' selected>";
				} else {
					html += "<option value='"+quyen.get(i).getMaQuyen()+"'>";
				}
				html += quyen.get(i).getMoTaQuyen()
					 + "</option>";
			}
		} else {
			html = "<option>Không tìm thấy</option>";
		}
		return html;
	}
	@RequestMapping (value = "/get-co-quan-user", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getCoQuanUser(String taiKhoan){
		String html = "";
		TaiKhoan tk = taiKhoanService.getTaiKhoan(taiKhoan);
		String maCoQuan = "";
		try{
			if(tk.getCoQuan().equals("0")){
				maCoQuan = "0";
			} else {
				maCoQuan = tk.getCoQuan();
			}
		} catch (Exception e){
			maCoQuan = "0";
		}
		
		String maHuyen = "";
		String maTinh = "";
		List<Tinh> dsTinh = tinhService.getDSTinh();
		List<Huyen> dsHuyen = null;
		List<Xa> dsXa = null;
		String tinh = "<option value=0>Không có</option>";
		String huyen = "<option value=0>Không có</option>";
		String xa = "<option value=0>Không có</option>";
		if(maCoQuan.equals("0")){
			tinh = "<option value='0' selected>Không có</option>";
			huyen = "<option value='0' selected>Không có</option>";
			xa = "<option value='0' selected>Không có</option>";
		}
		if(maCoQuan.length() <= 2 ){
			tinh += functionService.getSelectTinh(maCoQuan,dsTinh);
		}
		if(maCoQuan.length() == 3){
			maTinh = huyenService.getTinhHuyenBangMa(maCoQuan).getTinh().getMaTinh();
			dsHuyen = huyenService.getDSHuyen(maTinh);
			tinh += functionService.getSelectTinh(maTinh, dsTinh);
			huyen += functionService.getSelectHuyen(maCoQuan, dsHuyen);
		}
		if(maCoQuan.length() == 5){
			Xa xaHuyenTinh = xaService.getXaHuyenTinh(maCoQuan);
			maHuyen = xaHuyenTinh.getHuyen().getMaHuyen();
			maTinh = xaHuyenTinh.getHuyen().getTinh().getMaTinh();
			dsXa = xaService.getDSXa(maHuyen);
			dsHuyen = huyenService.getDSHuyen(maTinh);
			tinh += functionService.getSelectTinh(maTinh, dsTinh);
			huyen += functionService.getSelectHuyen(maHuyen, dsHuyen);
			xa += functionService.getSelectXa(maCoQuan, dsXa);
		}
		html = tinh+"_"+huyen+"_"+xa;
		return html;
	}
	
	
	@RequestMapping(value = "/quan-ly-tinh", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quanLyTinh(){
		ModelAndView model = new ModelAndView();
		try{
			List<Tinh> dsTinh = tinhService.getDSTinh();
			String[] tinh = this.get2BangTinh(dsTinh).split("_");
			model.addObject("dsTinh1", tinh[0]);
			model.addObject("dsTinh2", tinh[1]);
			model.setViewName("Tinh");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	@RequestMapping(value = "/quan-ly-huyen", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quanLyHuyen(){
		List<Tinh> dsTinh = tinhService.getDSTinh();
		List<Huyen> dsHuyen = huyenService.getDSHuyen("01");
		String dsHuyen1 = "",dsHuyen2 = "";
		String[] huyen = this.get2BangHuyen(dsHuyen).split("_");
		dsHuyen1 = huyen[0];
		dsHuyen2 = huyen[1];
		ModelAndView model = new ModelAndView();
		model.addObject("dsTinh", dsTinh);
		model.addObject("dsHuyen1", dsHuyen1);
		model.addObject("dsHuyen2", dsHuyen2);
		model.setViewName("Huyen");
		return model;
	}
	@RequestMapping(value = "/quan-ly-xa", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quanLyXa(){
		ModelAndView model = new ModelAndView();
		try{
			List<Tinh> dsTinh = tinhService.getDSTinh();
			List<Huyen> dsHuyen = huyenService.getDSHuyen("01");
			List<Xa> dsXa = xaService.getDSXa(dsHuyen.get(0).getMaHuyen());
			String dsXa1 = "",dsXa2 = "";
			String[] xa = this.get2BangXa(dsXa).split("_");
			dsXa1 = xa[0];
			dsXa2 = xa[1];
			model.addObject("dsTinh", dsTinh);
			model.addObject("dsHuyen", dsHuyen);
			model.addObject("dsXa1", dsXa1);
			model.addObject("dsXa2", dsXa2);
			model.setViewName("Xa");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "/quan-ly-dan-toc", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quanLyDanToc(){
		ModelAndView model = new ModelAndView();
		try{
			List<DanToc> dsDanToc = danTocService.getDSDanToc();
			String[] danToc = this.get2BangDanToc(dsDanToc).split("_");
			model.addObject("dsDanToc1", danToc[0]);
			model.addObject("dsDanToc2", danToc[1]);
			model.setViewName("DanToc");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	
	@RequestMapping(value = "/quan-ly-yeu-cau-dang-ky-cmnd-cccd", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quanLyYeuCauDangKyCMNDCCCD(){
		ModelAndView model = new ModelAndView();
		try{
			List<YeuCau> dsYeuCau = yeuCauService.getDSYeuCau();
			String html = "";
			if(dsYeuCau.size()>0){
				for(int i = 0; i < dsYeuCau.size(); i++){
					int maYeuCau = dsYeuCau.get(i).getMaYeuCau();
					String tinhTrang = "<a onclick=tamDungYeuCau('"+maYeuCau+"')><img src='resources/image/bullet-green.png' width=10 height=10 /> Hoạt động</a>";
					if(dsYeuCau.get(i).getTinhTrang().equals("0")){
						tinhTrang = "<a onclick=tiepTucYeuCau('"+maYeuCau+"')><img src='resources/image/bullet-red.png' width=10 height=10 /> Tạm dừng</a>";
					}
					html += "<tr>"
							+ ""
							+ "<input type=hidden id=txtTen"+maYeuCau+" value='"+dsYeuCau.get(i).getTenYeuCau()+"' />"
							+ "<input type=hidden id=txtMoTa"+maYeuCau+" value='"+dsYeuCau.get(i).getMoTa()+"' />"
							+ "<input type=hidden id=txtGiayTo"+maYeuCau+" value='"+dsYeuCau.get(i).getGiayTo()+"' />"
							+ "<input type=number hidden id=txtLePhi"+maYeuCau+" value="+dsYeuCau.get(i).getLePhi()+" />"
							+ "<td id='inputTen"+maYeuCau+"'>"+dsYeuCau.get(i).getTenYeuCau()+"</td>"
							+ "<td id='inputMoTa"+maYeuCau+"'>"+dsYeuCau.get(i).getMoTa()+"</td>"
							+ "<td id='inputGiayTo"+maYeuCau+"'>"+dsYeuCau.get(i).getGiayTo()+"</td>"
							+ "<td id='inputLePhi"+maYeuCau+"'>"+dsYeuCau.get(i).getLePhi()+"</td>"
							+ "<td id='button"+maYeuCau+"'><a onclick=capNhatYeuCau('"+maYeuCau+"')>Cập nhật</a></td>"
							+ "<td>"+tinhTrang+"</td>"
							+ "</tr>";
				}
			} else {
				html += "<tr>"
						+ "<td colspan=6>Không có yêu cầu nào</td>"
						+ "</tr>";
			}
			model.addObject("dsYeuCau", html);
			model.setViewName("YeuCau");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "/get-danh-sach-huyen", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getDanhSachHuyen(String tinh){
		List<Huyen> dsHuyen = huyenService.getDSHuyen(tinh);
		String dsHuyen1 = "",dsHuyen2 = "";
		String[] huyen = this.get2BangHuyen(dsHuyen).split("_");
		dsHuyen1 = huyen[0];
		dsHuyen2 = huyen[1];
		return dsHuyen1+"_"+dsHuyen2;
	}
	
	@RequestMapping(value = "/get-danh-sach-tinh-update-huyen", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getDanhSachTinhUpdateHuyen(String maHuyen){
		Huyen huyen = huyenService.getHuyenBangMa(maHuyen);
		List<Tinh> dsTinh = tinhService.getDSTinh();
		String html = functionService.getSelectTinh(huyen.getTinh().getMaTinh(), dsTinh);
		return html;
	}
	@RequestMapping(value = "/get-danh-sach-huyen-tinh-update-xa", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getDanhSachHuyenUpdateXa(String maXa){
		Xa xa = xaService.getXaHuyenTinh(maXa);
		List<Huyen> dsHuyen = huyenService.getDSHuyen(xa.getHuyen().getTinh().getMaTinh());
		List<Tinh> dsTinh = tinhService.getDSTinh();
		String html1 = "", html2 = ""  ;
		html1 += functionService.getSelectHuyen(xa.getHuyen().getMaHuyen(), dsHuyen);
		html2 += functionService.getSelectTinh(xa.getHuyen().getTinh().getMaTinh(), dsTinh);
		return html1+"_"+html2;
	}
	@RequestMapping(value = "/get-danh-sach-xa", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getDanhSachXa(String huyen){
		List<Xa> dsXa = xaService.getDSXa(huyen);
		String dsXa1 = "",dsXa2 = "";
		String[] xa = this.get2BangXa(dsXa).split("_");
		dsXa1 = xa[0];
		dsXa2 = xa[1];
		return dsXa1+"_"+dsXa2;
	}
	public String get2BangTinh(List<Tinh> dsTinh){
		String dsTinh1 = "",dsTinh2 = "";
		for(int i = 0; i < dsTinh.size(); i +=2){
			dsTinh1 += "<tr>"
					+ "<td><input type=hidden id=txt"+dsTinh.get(i).getMaTinh()+" value='"+dsTinh.get(i).getTenTinh()+"' />"+dsTinh.get(i).getMaTinh()+"</td>"
					+ "<td id='input"+dsTinh.get(i).getMaTinh()+"'>"+dsTinh.get(i).getTenTinh()+"</td>"
					+ "<td id='button"+dsTinh.get(i).getMaTinh()+"'><a onclick=capNhatTinh('"+dsTinh.get(i).getMaTinh()+"') >Cập nhật</a></td>"
					+ "</tr>";
			if(i+1 <= dsTinh.size()-1){
				dsTinh2 += "<tr>"
						+ "<td><input type=hidden id=txt"+dsTinh.get(i+1).getMaTinh()+" value='"+dsTinh.get(i+1).getTenTinh()+"' />"+dsTinh.get(i+1).getMaTinh()+"</td>"
						+ "<td id='input"+dsTinh.get(i+1).getMaTinh()+"'>"+dsTinh.get(i+1).getTenTinh()+"</td>"
						+ "<td id='button"+dsTinh.get(i+1).getMaTinh()+"'><a onclick=capNhatTinh('"+dsTinh.get(i+1).getMaTinh()+"') >Cập nhật</a></td>"
						+ "</tr>";
			}
		}
		return dsTinh1+"_"+dsTinh2;
	}
	public String get2BangHuyen(List<Huyen> dsHuyen){
		String dsHuyen1 = "",dsHuyen2 = "";
		for(int i = 0; i < dsHuyen.size(); i +=2){
			dsHuyen1 += "<tr>"
					+ "<td><input type=hidden id=txt"+dsHuyen.get(i).getMaHuyen()+" value='"+dsHuyen.get(i).getTenHuyen()+"' />"
					+ "<input type=hidden id='maTinh"+dsHuyen.get(i).getMaHuyen()+"' value='"+dsHuyen.get(i).getTinh().getMaTinh()+"' />"
					+ "<input type=hidden id='tenTinh"+dsHuyen.get(i).getMaHuyen()+"' value='"+tinhService.getTinhBangMa(dsHuyen.get(i).getTinh().getMaTinh()).getTenTinh()+"' />"+dsHuyen.get(i).getMaHuyen()+"</td>"
					+ "<td id='input"+dsHuyen.get(i).getMaHuyen()+"'>"+dsHuyen.get(i).getTenHuyen()+"</td>"
					+ "<td id='select"+dsHuyen.get(i).getMaHuyen()+"'>"+tinhService.getTinhBangMa(dsHuyen.get(i).getTinh().getMaTinh()).getTenTinh()+"</td>"
					+ "<td id='button"+dsHuyen.get(i).getMaHuyen()+"'><a onclick=capNhatHuyen('"+dsHuyen.get(i).getMaHuyen()+"') >Cập nhật</a></td>"
					+ "</tr>";
			if(i+1 <= dsHuyen.size()-1){
				dsHuyen2 += "<tr>"
						+ "<td><input type=hidden id=txt"+dsHuyen.get(i+1).getMaHuyen()+" value='"+dsHuyen.get(i+1).getTenHuyen()+"' />"
						+ "<input type=hidden id='maTinh"+dsHuyen.get(i+1).getMaHuyen()+"' value='"+dsHuyen.get(i+1).getTinh().getMaTinh()+"' />"
						+ "<input type=hidden id='tenTinh"+dsHuyen.get(i+1).getMaHuyen()+"' value='"+tinhService.getTinhBangMa(dsHuyen.get(i+1).getTinh().getMaTinh()).getTenTinh()+"' />"+dsHuyen.get(i+1).getMaHuyen()+"</td>"
						+ "<td id='input"+dsHuyen.get(i+1).getMaHuyen()+"'>"+dsHuyen.get(i+1).getTenHuyen()+"</td>"
						+ "<td id='select"+dsHuyen.get(i+1).getMaHuyen()+"'>"+tinhService.getTinhBangMa(dsHuyen.get(i+1).getTinh().getMaTinh()).getTenTinh()+"</td>"
						+ "<td id='button"+dsHuyen.get(i+1).getMaHuyen()+"'><a onclick=capNhatHuyen('"+dsHuyen.get(i+1).getMaHuyen()+"') >Cập nhật</a></td>"
						+ "</tr>";
			}
		}
		return dsHuyen1+"_"+dsHuyen2;
	}
	public String get2BangXa(List<Xa> dsXa){
		String dsXa1 = "",dsXa2 = "";
		for(int i = 0; i < dsXa.size(); i +=2){
			String maXa1 = dsXa.get(i).getMaXa();
			dsXa1 += "<tr>"
					+ "<td><input type=hidden id=txt"+maXa1+" value='"+dsXa.get(i).getTenXa()+"' />"
					+ "<input type=hidden id='maTinh"+maXa1+"' value='"+xaService.getXaHuyenTinh(maXa1).getHuyen().getTinh().getMaTinh()+"' />"
					+ "<input type=hidden id='tenTinh"+maXa1+"' value='"+xaService.getXaHuyenTinh(maXa1).getHuyen().getTinh().getTenTinh()+"' />"
					+ "<input type=hidden id='maHuyen"+maXa1+"' value='"+xaService.getXaHuyenTinh(maXa1).getHuyen().getMaHuyen()+"' />"
					+ "<input type=hidden id='tenHuyen"+maXa1+"' value='"+xaService.getXaHuyenTinh(maXa1).getHuyen().getTenHuyen()+"' />"
					+ maXa1+"</td>"
					+ "<td id='input"+maXa1+"'>"+dsXa.get(i).getTenXa()+"</td>"
					+ "<td id='selectHuyen"+maXa1+"'>"+xaService.getXaHuyenTinh(maXa1).getHuyen().getTenHuyen()+"</td>"
					+ "<td id='selectTinh"+maXa1+"'>"+xaService.getXaHuyenTinh(maXa1).getHuyen().getTinh().getTenTinh()+"</td>"
					+ "<td id='button"+maXa1+"'><a onclick=capNhatXa('"+maXa1+"') >Cập nhật</a></td>"
					+ "</tr>";
			if(i+1 <= dsXa.size()-1){
				String maXa2 = dsXa.get(i+1).getMaXa();
				dsXa2 += "<tr>"
						+ "<td><input type=hidden id=txt"+maXa2+" value='"+dsXa.get(i+1).getTenXa()+"' />"
						+ "<input type=hidden id='maTinh"+maXa2+"' value='"+xaService.getXaHuyenTinh(maXa2).getHuyen().getTinh().getMaTinh()+"' />"
						+ "<input type=hidden id='tenTinh"+maXa2+"' value='"+xaService.getXaHuyenTinh(maXa2).getHuyen().getTinh().getTenTinh()+"' />"
						+ "<input type=hidden id='maHuyen"+maXa2+"' value='"+xaService.getXaHuyenTinh(maXa2).getHuyen().getMaHuyen()+"' />"
						+ "<input type=hidden id='tenHuyen"+maXa2+"' value='"+xaService.getXaHuyenTinh(maXa2).getHuyen().getTenHuyen()+"' />"
						+ maXa2+"</td>"
						+ "<td id='input"+maXa2+"'>"+dsXa.get(i+1).getTenXa()+"</td>"
						+ "<td id='selectHuyen"+maXa2+"'>"+xaService.getXaHuyenTinh(maXa2).getHuyen().getTenHuyen()+"</td>"
						+ "<td id='selectTinh"+maXa2+"'>"+xaService.getXaHuyenTinh(maXa2).getHuyen().getTinh().getTenTinh()+"</td>"
						+ "<td id='button"+maXa2+"'><a onclick=capNhatXa('"+maXa2+"') >Cập nhật</a></td>"
						+ "</tr>";
			}
		}
		return dsXa1+"_"+dsXa2;
	}
	public String get2BangDanToc(List<DanToc> dsDanToc){
		String dsDanToc1 = "", dsDanToc2 = "";
		for(int i = 0; i < dsDanToc.size(); i +=2){
			String maDanToc1 = dsDanToc.get(i).getMaDT();
			String tenDanToc1 = dsDanToc.get(i).getTenDT();
			dsDanToc1 += "<tr>"
					+ "<td><input type=hidden id=txt"+maDanToc1+" value='"+tenDanToc1+"' />"+maDanToc1
					+ "<td id='input"+maDanToc1+"'>"+tenDanToc1+"</td>"
					+ "<td id='button"+maDanToc1+"'><a onclick=capNhatDanToc('"+maDanToc1+"') >Cập nhật</a></td>"
					+ "</tr>";
			if(i+1 <= dsDanToc.size()-1){
				String maDanToc2 = dsDanToc.get(i+1).getMaDT();
				String tenDanToc2 = dsDanToc.get(i+1).getTenDT();
				dsDanToc2 += "<tr>"
						+ "<td><input type=hidden id=txt"+maDanToc2+" value='"+tenDanToc2+"' />"+maDanToc2
						+ "<td id='input"+maDanToc2+"'>"+tenDanToc2+"</td>"
						+ "<td id='button"+maDanToc2+"'><a onclick=capNhatHuyen('"+maDanToc2+"') >Cập nhật</a></td>"
						+ "</tr>";
			}
		}
		return dsDanToc1+"_"+dsDanToc2;
	}
	
	@RequestMapping(value = "/quan-ly-cau-hinh-dang-ky-cmnd-cccd", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quanLyCauHinhCCCD(){
		ModelAndView model = new ModelAndView();
		try{
			ConfigCCCD cc = ccService.getConfigCCCD();
			ConfigSoCC csc = cscService.getConfigSoCC();
			model.addObject("csc", csc);
			model.addObject("configCCCD", cc);
			model.setViewName("CauHinhCCCD");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "/quan-ly-cau-hinh-so-tuoi-ket-hon", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quanLyCauHinhSoTuoiKH(){
		ModelAndView model = new ModelAndView();
		try{
			ConfigKetHon ck = ckService.getConfigKetHon();
			model.addObject("configKetHon", ck);
			model.setViewName("CauHinhKetHon");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "/update-cau-hinh-cmnd-cccd", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView updateCauHinhCMNDCCCD(int thoiHanHoSo,
			int hanSuDung,
			int gioiHanHoSo,
			int tuoi,
			int nam,
			String moTa,
			int namTruoc,
			int nuTruoc,
			int namSau,
			int nuSau,
			HttpSession session,
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_CAU_HINH_CCCD")){
				ConfigCCCD cc = new ConfigCCCD();
				ConfigSoCC csc = new ConfigSoCC();
				cc.setHanHoSo(thoiHanHoSo);
				cc.setHanSuDung(hanSuDung);
				cc.setSoHoSo1Ngay(gioiHanHoSo);
				cc.setTuoi(tuoi);
				csc.setNam(nam);
				csc.setMoTa(moTa);
				csc.setGiaTriNamTruoc(namTruoc);
				csc.setGiaTriNuTruoc(nuTruoc);
				csc.setGiaTriNamSau(namSau);
				csc.setGiaTriNuSau(nuSau);
				if(ccService.updateConfigCCCD(cc) && cscService.updateConfigSoCC(csc)){
					session.setAttribute("msg", "Lưu cấu hình thành công");
				} else {
					session.setAttribute("error", "Lưu cấu hình thất bại");
				}
				model.addObject("csc", csc);
				model.addObject("configCCCD", cc);
				model.setViewName("CauHinhCCCD");
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
	
	@RequestMapping(value = "/update-cau-hinh-so-tuoi-ket-hon", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView updateCauHinhSoTuoiKH(int tuoiNam,
			int tuoiNu,
			HttpSession session,
			HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try{
			ConfigKetHon ck = new ConfigKetHon();
			ck.setTuoiNam(tuoiNam);
			ck.setTuoiNu(tuoiNu);
			if(ckService.updateConfigKetHon(ck)){
				session.setAttribute("msg", "Lưu cấu hình thành công");
			} else {
				session.setAttribute("error", "Lưu cấu hình thất bại");
			}
			model.addObject("configKetHon", ck);
			model.setViewName("CauHinhKetHon");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "/quan-ly-lich-lam-viec", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quanLyLichLamViec(){
		ModelAndView model = new ModelAndView();
		try{
			List<ConfigNgayNghi> ngayNghi = ngayNghiService.getDSNgayNghi();
			String dsNgayNghi = this.createBangNgayNghi(ngayNghi);
			model.addObject("dsNgayNghi", dsNgayNghi);
			model.setViewName("CauHinhNgayLamViec");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	
	@RequestMapping(value = "/quan-ly-cau-hinh-email", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quanLyCauHinhEmail(){
		ModelAndView model = new ModelAndView();
		try{
			List<ConfigEmail> cmail = cmService.getDSConfigEmail();
			String dsEmail = "";
			
			for(int i = 0; i < cmail.size(); i++){
				String maMail = cmail.get(i).getMaEmail();
				String email = cmail.get(i).getEmail();
				String matKhauShow = "********";
				if(email.length() < 1){
					matKhauShow = "";
				}
				String tieuDe = cmail.get(i).getTieuDeGui();
				String noiDung = cmail.get(i).getNoiDungGui();
				dsEmail += "<tr>"
						+ "<td>"
						+ "<input type=hidden id='txtEmail"+maMail+"' value='"+email+"' />"
						+ "<input type=hidden id='txtMatKhau"+maMail+"' value='"+matKhauShow+"' />"
						+ "<input type=hidden id='txtTieuDe"+maMail+"' value='"+tieuDe+"' />"
						+ "<input type=hidden id='txtNoiDung"+maMail+"' value='"+noiDung+"' />"
						+ maMail
						+ "</td>"
						+ "<td id='inputEmail"+maMail+"'>"+email+"</td>"
						+ "<td id='inputMatKhau"+maMail+"'>"+matKhauShow+"</td>"
						+ "<td id='inputTieuDe"+maMail+"'>"+tieuDe+"</td>"
						+ "<td id='inputNoiDung"+maMail+"'>"+noiDung+"</td>"
						+ "<td id='button"+maMail+"'><a onclick=capNhatEmail('"+maMail+"')>Cập nhật</a></td>"
						+ "</tr>";
			}
			
			
			model.addObject("dsEmail", dsEmail);
			model.setViewName("CauHinhEmail");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "/update-cau-hinh-email", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView updateCauHinhEmail(String maMail,
			String updateEmail,
			String updateMatKhau,
			String updateTieuDe,
			String updateNoiDung,
			HttpSession session,
			HttpServletResponse response){
		ConfigEmail cm = new ConfigEmail();
		cm.setMaEmail(maMail);
		cm.setEmail(updateEmail);
		cm.setMatKhau(updateMatKhau);
		cm.setTieuDeGui(updateTieuDe);
		cm.setNoiDungGui(updateNoiDung);
		if(cmService.updateConfigEmail(cm)){
			session.setAttribute("msg","Lưu cấu hình mail "+maMail+" thành công");
		} else {
			session.setAttribute("error","Lưu cấu hình mail "+maMail+" thất bại");
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("Temp");
		return model;
	}
	
	@RequestMapping(value = "/quan-ly-cau-hinh-quan-he-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quanLyCauHinhQuanHeHoKhau(){
		ModelAndView model = new ModelAndView();
		String html = "";
		String maQuanHe;
		String tenQuanHe;
		int i = 0;
		try{
			List<DSQuanHe> dsQuanHe = shkService.dsQuanHe();
			for(i=0 ; i < dsQuanHe.size(); i++){
				maQuanHe = dsQuanHe.get(i).getQuanHe(); 
				tenQuanHe = dsQuanHe.get(i).getTenQuanHe();
				html += "<tr>"
						+ "<td id='inputMaQuanHe"+i+"'>"+maQuanHe+"</td>"
						+ "<td id='inputTenQuanHe"+i+"'>"+tenQuanHe+"</td>";
						if(!maQuanHe.equals("vo") && !maQuanHe.equals("chong") && !maQuanHe.equals("chuho")){
							html+= "<td id='suaQuanHe"+i+"'><a onclick=capNhat("+i+")>Cập nhật</a>||<a onclick=xoa("+i+")>Xóa</a></td>";
						}else{
							html += "<td></td> ";
						}
				html +=  "</tr>"
						+ "<input type=hidden id='maQuanHe"+i+"' value='"+maQuanHe+"' />"
						+ "<input type=hidden id='tenQuanHe"+i+"' value='"+tenQuanHe+"' />";
			}
			html +="<tr>"
				+ "<td id='inputMaQuanHe"+(i+1)+"'></td>"
				+ "<td id='inputTenQuanHe"+(i+1)+"'></td>"
				+ "<td id='suaQuanHe"+(i+1)+"'><a onclick=Them("+(i+1)+")>Thêm</a></td>"
				+ "<input type=hidden id='maQuanHe"+(i+1)+"' />"
				+ "<input type=hidden id='tenQuanHe"+(i+1)+"'/>";
			
			
			model.addObject("dsQuanHe", html);
			model.setViewName("CauHinhQuanHe");
		} catch(Exception e){
			model.addObject("thongBaoLoi", Const.loiDuLieu);
			model.setViewName("ThongBaoLoi");
		}
		return model;
	}
	
	@RequestMapping(value = "/update-cau-hinh-quan-he", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView updateCauHinhquanhe(String updateMaQuanHe,
			String updateTenQuanHe, 
			String maQuanHe,
			HttpSession session,
			HttpServletResponse response){
		
		DSQuanHe quanHe = new DSQuanHe();
		quanHe.setQuanHe(updateMaQuanHe);
		quanHe.setTenQuanHe(updateTenQuanHe);
		System.out.println("ma can doi: "+updateMaQuanHe+" ten can doi: "+updateTenQuanHe+" ma~ doi?: "+maQuanHe);
		Boolean i =shkService.capNhatQuanHe(quanHe, maQuanHe);
		if(i){
				session.setAttribute("msg","Lưu cấu hình thành công");
			} else {
				session.setAttribute("error","Lưu cấu hình thất bại");
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("Temp");
		return model;
	}
	
	@RequestMapping(value = "/them-cau-hinh-quan-he", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView themCauHinhquanhe(String themMaQuanHe,
			String themTenQuanHe, 
			HttpSession session,
			HttpServletResponse response){
		DSQuanHe quanHe = new DSQuanHe();
		quanHe.setQuanHe(themMaQuanHe);
		quanHe.setTenQuanHe(themTenQuanHe);
		
		Boolean i =shkService.themQuanHe(quanHe);
		if(i){
				session.setAttribute("msg","Lưu cấu hình thành công");
			} else {
				session.setAttribute("error","Lưu cấu hình thất bại");
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("Temp");
		return model;
	}
	
	@RequestMapping(value = "/xoa-cau-hinh-quan-he", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView xoaCauHinhquanhe(String maQuanHe,
			HttpSession session,
			HttpServletResponse response){
		
		Boolean i =shkService.xoaQuanHe(maQuanHe);
		if(i){
				session.setAttribute("msg","Xóa thành công quan hệ có mã '"+maQuanHe+"'");
			} else {
				session.setAttribute("error","Xóa cấu hình thất bại");
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("Temp");
		return model;
	}
	
	@RequestMapping(value = "/them-ngay-nghi", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String themNgayNghi(String ngayThang, HttpSession session){
		String[] data = ngayThang.split("-");
		String result = "";
		if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_CAU_HINH_CCCD")){
			if(ngayNghiService.insertNgayNghi(Integer.parseInt(data[2]), Integer.parseInt(data[1]))){
				result = "<div class='success'>Thêm ngày "+data[2]+" tháng "+data[1]+" thành công";
			} else {
				result = "<div class='error'>Thêm ngày "+data[2]+" tháng "+data[1]+" thất bại";
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/xoa-ngay-nghi", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String xoaNgayNghi(String ngayThang, HttpSession session){
		String[] data = ngayThang.split("/");
		String result = "";
		if(quyenService.kiemTraQuyenBangTen(session.getAttribute("ssTaiKhoan").toString(), "QUAN_LY_CAU_HINH_CCCD")){
			if(ngayNghiService.deleteNgayNghi(Integer.parseInt(data[0]), Integer.parseInt(data[1]))){
				result = "<div class='success'>Xóa ngày "+data[0]+" tháng "+data[1]+" thành công";
			} else {
				result = "<div class='error'>Xóa ngày "+data[0]+" tháng "+data[1]+" thất bại";
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/get-danh-sach-ngay-nghi", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getDanhSachNgayNghi(){
		List<ConfigNgayNghi> ngayNghi = ngayNghiService.getDSNgayNghi();
		return this.createBangNgayNghi(ngayNghi);
	}
	
	public String createBangNgayNghi(List<ConfigNgayNghi> ngayNghi){
		String result = "";
		for(int i=0; i < ngayNghi.size(); i++){
			int ngay = ngayNghi.get(i).getNgay();
			int thang = ngayNghi.get(i).getThang();
			result += "<tr>"
						+ "<td>"+ngay+"/"+thang+"</td>"
						+ "<td><a onclick=deleteNgayNghi('"+ngay+"/"+thang+"') >Xóa</a></td>"
						+ "</tr>";
	
		}
		return result;
	}
	
	public String createBangEmail(List<ConfigEmail> e){
		String result = "";
		for(int i=0; i < e.size(); i++){
			String maEmail = e.get(i).getMaEmail();
			String email = e.get(i).getEmail();
			String tieuDe = e.get(i).getTieuDeGui();
			String noiDung = e.get(i).getNoiDungGui();
			result += "<tr>"
						+ "<td>"+maEmail+"</td>"
						+ "<td>"+email+"</td>"
						+ "<td>********</td>"
						+ "<td>"+tieuDe+"</td>"
						+ "<td>"+noiDung+"</td>"
						+ "<td><a onclick=updateEmail('"+maEmail+"')>Cập nhật</a></td>"
						+ "<td><a onclick=deleteEmail('"+maEmail+"') >Xóa</a></td>"
						+ "</tr>";
	
		}
		return result;
	}
}
