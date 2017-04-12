package com.mkyong.web.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.services.CCCDService;
import com.mkyong.services.DanTocService;
import com.mkyong.services.HonNhanService;
import com.mkyong.services.HuyenService;
import com.mkyong.services.KhaiSinhService;
import com.mkyong.services.QuyenService;
import com.mkyong.services.SoHoKhauService;
import com.mkyong.services.TinhService;
import com.mkyong.services.XaService;
import com.mkyong.services.function.FunctionService;

import bean.An.DSLamHoKhau;
import bean.An.DSQuanHe;
import bean.An.DSThemNhanKhau;
import bean.An.LienKetDuLieu;
import bean.An.SoHoKhau;
import bean.Chung.DanToc;
import bean.Chung.HonNhan;
import bean.Chung.Huyen;
import bean.Chung.KhaiSinh;
import bean.Chung.TaiKhoan;
import bean.Chung.Tinh;
import bean.Vu.CCCD;

@Controller
public class AnController {
	
	
	@Autowired
	private CCCDService cccdService;
	@Autowired
	private SoHoKhauService shkService;
	@Autowired
	private KhaiSinhService ksService;
	@Autowired
	private XaService xaService; 
	@Autowired
	private DanTocService danTocService;
	@Autowired
	private TinhService tinhService;
	@Autowired
	private HuyenService huyenService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private QuyenService quyenService;
	@Autowired
	private HonNhanService honNhanService;
	
	@RequestMapping(value = "/nhap-ho-khau")
	public ModelAndView themHoKhau(HttpSession session){
		TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
		String viewName = "NhapHoKhau";
		try {
			String taiKhoan = session.getAttribute("ssTaiKhoan").toString();
			if(quyenService.kiemTraQuyenBangTen(taiKhoan, "NHAP_HO_KHAU")){
			String noiLV = tk.getCoQuan();
			}else{
				viewName = "403";
			}
		} catch (Exception e) {
			viewName = "login";
		}
		List<DSQuanHe> dsQuanHe =  shkService.dsQuanHe();
		String html ="";
		for(int i = 0; i < dsQuanHe.size(); i++){
			if(!dsQuanHe.get(i).getQuanHe().equals("chuho")){
			html += "<option value = "+dsQuanHe.get(i).getQuanHe()+">"+dsQuanHe.get(i).getTenQuanHe()+"</option>";
			}
		}
		ModelAndView model = new ModelAndView();
		model.addObject("DSQuanHe",html);
		model.setViewName(viewName);
		return model;
	}

		
		@RequestMapping(value = "/ket-qua-nhap-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView test(int soNhanKhau, HttpServletRequest request, HttpServletResponse response, HttpSession session){
				//SoHoKhau shk = new SoHoKhau();
			int sl = soNhanKhau;
			String coQuan = session.getAttribute("ssCoQuan").toString();
			String maSoHoKhau = shkService.taoMaSoHK();
			String soKSChuHo = request.getParameter("soKSChuHo");
			String soCCChuHo = request.getParameter("soCCChuHo");
			String[] soCCThanhVien = new String[sl];
			String[] soKSThanhVien = new String[sl];
			String[] quanHe = new String[sl];
			String paramSoCCThanhVien = "soCCThanhVien";
			String paramSoKSThanhVien = "soKSThanhVien"; // loại số căn cước hoặc là khai sinh
			String paramQuanHe = "quanHe";
			String diaChi = request.getParameter("diaChi"); // lấy địa chỉ
			String ngayChuyenDen = functionService.getNgayHienTai();
			soKSThanhVien[0] = soKSChuHo.replaceAll("-","");
			soCCThanhVien[0] = soCCChuHo.replaceAll("-","");
			quanHe[0] = "chuho";
			for(int i = 1; i < sl; i++) {
				soKSThanhVien[i] = request.getParameter(paramSoKSThanhVien + i).replaceAll("-", ""); //lấy số khai sinh của thành viên thứ i
				soCCThanhVien[i] = request.getParameter(paramSoCCThanhVien + i).replaceAll("-", ""); //lấy số căn cước của thành viên thứ i
				quanHe[i] = request.getParameter(paramQuanHe + i);
			}
			
			Boolean a = shkService.themSoHoKhau(maSoHoKhau, diaChi, coQuan);
			if(a== true){
				System.out.println("da insert : "+maSoHoKhau+ diaChi);
			for(int i = 0; i < sl ; i++){
				shkService.themChiTietSoHoKhau(maSoHoKhau,soCCThanhVien[i], soKSThanhVien[i], quanHe[i], ngayChuyenDen);
			}
			}
		
			ModelAndView model = new ModelAndView();
			//model.setViewName("DanhSachSoHoKhau");
			try {
				response.sendRedirect("danh-sach-so-ho-khau");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.setViewName("DanhSachSoHoKhau");
			return model;
		}
		
		@RequestMapping(value = "/danh-sach-so-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView dsSHK(HttpSession session){
			ModelAndView model = new ModelAndView();
			String viewName = "DanhSachSoHoKhau";
			try {
				String coQuan = session.getAttribute("ssCoQuan").toString();
				String taiKhoan = session.getAttribute("ssTaiKhoan").toString();
				if(quyenService.kiemTraQuyenBangTen(taiKhoan, "XEM_HO_KHAU")){
					List<SoHoKhau> dsSHK = shkService.dsSoHoKhau(coQuan);
					List<DSThemNhanKhau> dsThemNhanKhau = new ArrayList<DSThemNhanKhau>();
					model.addObject("dsSHK", dsSHK);
				}else{
					viewName="403";
				}
			} catch (Exception e) {
				viewName = "login";
			}
			model.setViewName(viewName);
			return model;
		}
		
		@RequestMapping(value = "/tim-kiem-danh-sach-so-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public @ResponseBody String timKiemDSSHK(String tuKhoa, HttpSession session){
			String noiDKLV = session.getAttribute("ssCoQuan").toString();
			System.out.println(tuKhoa+noiDKLV);
			List<SoHoKhau> dsSoHoKhau = shkService.dsSoHoKhauBangTuKhoa(noiDKLV, tuKhoa);
			String html = "";
			String soHK;
			String diaChi;
			int i;
			if(dsSoHoKhau.size() > 0 && tuKhoa !=""){
				for(i = 0; i < dsSoHoKhau.size(); i++){
					soHK=dsSoHoKhau.get(i).getSoHK();
					diaChi= dsSoHoKhau.get(i).getDiaChi();
						html += "<tr>"
								+ "<td>"+soHK+"</td>"
								+ "<td>"+diaChi+"</td>"
								+ "<td><a href='chi-tiet-so-ho-khau?soHK="+soHK+"' >Chi tiết</a></td>"
								+ "</tr>";
					
				}
			}
			return html;
		}
		
		@RequestMapping(value = "/chi-tiet-so-ho-khau")
		public ModelAndView chiTietSHK(String soHK, HttpSession session){
			ModelAndView model = new ModelAndView();
			CCCD cccd = new CCCD();
			List<KhaiSinh> ks = null;
			String hoTen;
			String soCC;
			String soKS;
			String quanHe;
			String tenQuanHe;
			String tinhTrang;
			try {	
				String taiKhoan = session.getAttribute("ssTaiKhoan").toString();
			List<SoHoKhau> chiTietSHK = shkService.getSoHoKhauBangMa(soHK);
			String html = "<tr><th colspan='6' >Số hộ khẩu:"+soHK+"</th></tr>";
			html += "<input type='hidden' id='soHK' value='"+chiTietSHK.get(0).getSoHK()+"' />";
				for(int i = 0 ; i < chiTietSHK.size(); i++){
					ks = ksService.DSKhaiSinhBangMaHK(soHK);
					hoTen = ks.get(i).getHoTen();
					soCC = chiTietSHK.get(i).getSoCC();
					soKS = chiTietSHK.get(i).getSoKS();
					quanHe = chiTietSHK.get(i).getQuanHe();
					tenQuanHe = shkService.layTenQuanHe(quanHe);
					tinhTrang = chiTietSHK.get(i).getTinhTrang();
					if(tinhTrang==null){
						tinhTrang="";
					}
					html += "<tr>"
							+"<td><a href='trang-chi-tiet-khai-sinh?soKS="+soKS+"' target='_blank'>"+soKS+"</a></td>"
							+"<td><a href='xem-thong-tin-cccd?soCC="+soCC+"' target='_blank' >"+soCC+"</a></td>"
							+"<td>"+hoTen+"</td>";
							if(quyenService.kiemTraQuyenBangTen(taiKhoan, "XEM_HO_KHAU")){
								html +="<td id='tinhTrang"+i+"'>"+tinhTrang+"<br><a onclick=capNhat("+i+")>[Cập nhật]</a></td>";
							}else{
								html +="<td >"+tinhTrang+"</td>";
							}
							html +="<td>"+tenQuanHe+"</td>"
							+"<td>"+chiTietSHK.get(i).getNgayChuyenDen()+"</td>"
							+"</tr>"
							+"<input type='hidden' id='soKS"+i+"' value='"+soKS+"' />"
							+"<input type='hidden' id='backupTT"+i+"' value='"+tinhTrang+"' />";
					System.out.println("so khai sinh thu "+i+chiTietSHK.get(i).getSoKS());
					model.addObject("chiTietSoHoKhau", html);
					model.setViewName("ChiTietSoHoKhau");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return model;
		}
		
		
		
		@RequestMapping(value = "/dang-ky-so-ho-khau")
		public ModelAndView dkSHK(HttpSession session){
			ModelAndView model = new ModelAndView();
			String viewName = "DangKyHoKhau";
			String html ="";
			try {
				String taiKhoan = session.getAttribute("ssTaiKhoan").toString();
				if(quyenService.kiemTraQuyenBangTen(taiKhoan, "CONG_DAN")){
					CCCD cccd = cccdService.getCCCDBangMa(taiKhoan);
					try {
						SoHoKhau shk = shkService.getSoHoKhau("A.so_cc ="+cccd.getSoCC());
						if(!shk.getQuanHe().equals("chuho")){
							viewName = "403";
							model.addObject("thongBao","Chỉ có chủ hộ mới được quyền đăng ký tách hộ khẩu");
						}
					} catch (Exception e) {
						viewName = "403";
					}
					
					String hoTen = cccd.getHoTen();
					String gioiTinh = cccd.getGioiTinh();
					List<Tinh> dsTinh = tinhService.getDSTinh();
					List<DSQuanHe> dsQuanHe = shkService.dsQuanHe();
					for(int i = 0; i < dsQuanHe.size(); i++){
						html += "<option value = "+dsQuanHe.get(i).getQuanHe()+">"+dsQuanHe.get(i).getTenQuanHe()+"</option>";
					}
					model.addObject("hoTen", hoTen);
					model.addObject("gioiTinh", gioiTinh);
					model.addObject("dsTinh", dsTinh);
					model.addObject("DSQuanHe", html);
					model.addObject("soCC", taiKhoan);
				}else{
					viewName = "403";
				}
				
			} catch (Exception e) {
				viewName = "login";
			}
			
			model.setViewName(viewName);
			return model;
		}
		
		@RequestMapping(value = "/lay-thong-tin-ks", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public @ResponseBody String getThongTinKS(String soKS, HttpSession session){
			KhaiSinh khaiSinh = new KhaiSinh();
			SoHoKhau shk = new SoHoKhau();
			String result = "";
			String tinhTrang = "";
			String soCC ="";
			try {
				khaiSinh = ksService.getKhaiSinhBangSoKS(soKS);
				System.out.println(khaiSinh.getHoTen());
				//System.out.println("Vao get thong tin." + soKS);
				tinhTrang ="";
				try {
					shk = shkService.getSoCCBangSoKS(soKS);
					tinhTrang = shk.getTinhTrang();
					soCC = shk.getSoCC();
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					soCC= shkService.thongTinCaNhan(soKS).getSoCC();
				} catch (Exception e) {
					// TODO: handle exception
				}
					result += khaiSinh.getHoTen()
							+"soCC"+ soCC
							+"tinhTrang"+tinhTrang;
			System.out.println(result);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}
		
		@RequestMapping(value = "/lay-thong-tin-ks-nhap-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public @ResponseBody String getThongTinKSnhaphk(String soKS, HttpSession session){
			KhaiSinh khaiSinh = new KhaiSinh();
			khaiSinh = ksService.getKhaiSinhBangSoKS(soKS);
			String soCC = "";
			try {
				LienKetDuLieu thongTinCaNhan = new LienKetDuLieu();
				thongTinCaNhan = shkService.thongTinCaNhan(soKS);
				soCC = thongTinCaNhan.getSoCC();
				System.out.println(thongTinCaNhan.getSoCC()+"   "+thongTinCaNhan.getMaSo());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			SoHoKhau ds  = shkService.getSoHoKhau("so_ks = "+soKS);
			String result = "";
			//System.out.println("Vao get thong tin." + soKS);
			String tinNhan = "";
			if(khaiSinh == null || "".equals(khaiSinh)) {
				tinNhan = "Không tìm thấy kết quả tìm kiếm.";
				session.setAttribute("tinNhan", tinNhan);
			} else if(ds != null){
				result = "datontai";
			}else{
				String chuoi ="@@";
				result += khaiSinh.getHoTen()+chuoi+ soCC;
			}
			
			return result;
		}

		@RequestMapping(value = "/xu-ly-dang-ky-ho-khau", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public ModelAndView kqDKSHK(String soLuongDK,  HttpServletRequest request, HttpSession session, HttpServletResponse response){
			ModelAndView model = new ModelAndView();
				int sl = Integer.valueOf(soLuongDK);
				KhaiSinh khaiSinh = new KhaiSinh();
				String[] soCCThanhVienDK = new String[sl];
				String[] soKSThanhVienDK = new String[sl];
				String[] quanHeDK = new String[sl];
				String[] tinhTrangDK = new String[sl];
				String paramSoCCThanhVien = "soCCThanhVienDK";
				String paramSoKSThanhVien = "soKSThanhVienDK"; // loại số căn cước hoặc là khai sinh
				String paramQuanHe = "quanHeDK";
				String paramTinhTrangDK = "tinhTrangDK";
				String noiDKLamViec;
				String ngayDK = functionService.getNgayHienTai();
				String ngayHen = "";
				try {
					ngayHen = shkService.taoNgayHen(ngayDK);
				} catch (Exception e) {
					// TODO: handle exception
				}
				String noiDKLVTinh = request.getParameter("noiDKLVTinh");
				String noiDKLVHuyen = request.getParameter("noiDKLVHuyen"); 
				if(noiDKLVHuyen.equals("0")){
					noiDKLamViec = noiDKLVTinh;
				}else{
					noiDKLamViec = noiDKLVHuyen;
				}
				
				String soCCNguoiDK = request.getParameter("soCCNguoiDK").replaceAll("-", ""); // lay so cc nguoi dang ky
				String diaChi = request.getParameter("diaChiDK"); // lấy địa chỉ
				String soHKCu = request.getParameter("soHKCu");
				soHKCu = soHKCu.replaceAll("-", "");
				model.addObject("soLuong",sl);
				try {
					for(int i = 0; i < sl; i++) {
							soKSThanhVienDK[i] = request.getParameter(paramSoKSThanhVien + i); //lấy số khai sinh của thành viên thứ i
							soCCThanhVienDK[i] = request.getParameter(paramSoCCThanhVien + i); //lấy số căn cước của thành viên thứ i
							quanHeDK[i] = request.getParameter(paramQuanHe + i);
							tinhTrangDK[i] = request.getParameter(paramTinhTrangDK + i);
							khaiSinh = ksService.getKhaiSinhBangSoKS(soKSThanhVienDK[i]);
					}
					// bắt đầu nhập dk sổ hộ khẩu vào CSDL
					
					for(int i = 0; i < sl ; i++){
						Boolean a = shkService.themDangKySoHoKhau(soHKCu,soCCNguoiDK, diaChi, soCCThanhVienDK[i], soKSThanhVienDK[i], quanHeDK[i], noiDKLamViec, "0", ngayDK, ngayHen, tinhTrangDK[i]);
						if(a == true){
							System.out.println("da insert: "+quanHeDK[i]+"  "+soCCThanhVienDK[i]+"  "+soKSThanhVienDK[i]+ " "+tinhTrangDK[i]);
						}
					}
					// bat dau tao giay hen
					List<DSLamHoKhau> a = shkService.dsLamHoKhauBangMa(soCCNguoiDK);
					String maSo = a.get(a.size()-1).getMaSo();
					String hoTenNguoiDK = cccdService.getCCCDBangMa(soCCNguoiDK).getHoTen();
					String diaDiem="";
					Tinh tinh = tinhService.getTinhBangMa(noiDKLVTinh);
					Huyen huyen = huyenService.getHuyenBangMa(noiDKLVHuyen);
					if(noiDKLVHuyen.equals("0")){
						noiDKLamViec = noiDKLVTinh;
						diaDiem += tinh.getTenTinh();
					} else {
						noiDKLamViec = noiDKLVHuyen;
						diaDiem += huyen.getTenHuyen() + " - " + tinh.getTenTinh();
					}
					model.addObject("maSo", maSo);
					model.addObject("hoTenNguoiDK", hoTenNguoiDK);
					model.addObject("ngayHen",ngayHen);
					model.addObject("diaDiem", diaDiem);
					model.setViewName("GiayHenDangKyTachSoHoKhau");
				} catch (Exception e) {
					System.out.println(e);
				}
			return model;
		}
		
		@RequestMapping(value = "/lay-thong-tin-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public @ResponseBody String layThongTinHK(String soHK, String soCCNguoiDK,  HttpSession session){
			List<SoHoKhau> shk ;
			String result ="" ;
			//System.out.println(soHK);
			try {
			shk = shkService.getSoHoKhauBangMa(soHK);
			if(shk == null){
				return null;
			}else{
				int sl = shk.size();
				int kq =0;
				for(int i = 0; i < sl; i++ ){
					//System.out.println(i);
					if(soCCNguoiDK.equals(shk.get(i).getSoCC())){
						kq = 1;
					}
				}
				if(kq==0){
					return "khongcoquyendangky";
				}
			}
			
			int sl = shk.size();
			String soKS;
			KhaiSinh KS= new KhaiSinh();
			soKS = shk.get(0).getSoKS();					//lay lan luoc cac so khai sinh ra
			KS = ksService.getKhaiSinhBangSoKS(soKS);		//lay thông tin cua tung cai khai sinh ra
			String tenQuanHe;
				for(int i = 0; i < sl; i++){
					tenQuanHe = shkService.layTenQuanHe(shk.get(i).getQuanHe());
					String thanhvien = "Thành viên thứ "+(i+1);
					soKS = shk.get(i).getSoKS();//lay lan luoc cac so khai sinh ra	
					System.out.println("thanh vien "+thanhvien+" : "+soKS);
					KS = ksService.getKhaiSinhBangSoKS(soKS);		//lay thông tin cua tung cai khai sinh ra
					//System.out.println(KS.getHoTen());
					
					result += "<tr>"
							+ "<td style='width:8%'>"+thanhvien+"</td>"
							+ "<td style='width:20%'><input type=text class='form-control' value='"+KS.getHoTen()+"' disabled></td>"
							+ "<td style='width:25%'><input type=text class='form-control' value='"+shk.get(i).getTinhTrang()+"' disabled></td>"
							+ "<td style='width:15%'><input type=text class='form-control' value='"+KS.getSoKS()+"' disabled></td>"
							+ "<td style='width:15%'><input type=text class='form-control' value='"+shk.get(i).getSoCC()+"' disabled></td>"
							+ "<td style='width:10%'><input type=text class='form-control' value='"+tenQuanHe+"' disabled></td>"
							
							
							+ "<input type=text name = hoTen"+(i)+" id = hoTen"+(i)+"  value='"+KS.getHoTen()+"' hidden />"
							+ "<input type=text name = tinhTrang"+(i)+" id = tinhTrang"+(i)+"  value='"+shk.get(i).getTinhTrang()+"' hidden />"
							+ "<input type=text name = soKSThanhVien"+(i)+" id = soKSThanhVien"+(i)+"  value='"+KS.getSoKS()+"' hidden />"
							+ "<input type=text name = soCCThanhVien"+(i)+" id = soCCThanhVien"+(i)+"  value='"+shk.get(i).getSoCC()+"' hidden />"
							+ "<input type=text name = quanHe"+(i)+" id = quanHe"+(i)+"  value='"+shk.get(i).getQuanHe()+"' hidden />";
							if(!shk.get(i).getQuanHe().equals("chuho")){
							result += "<td><input type=checkbox name=chuyen"+i+" id=chuyen"+(i)+" style='margin-top: 3px;' /> Chọn</td>";
							}else{
								result += "<td><input type=checkbox name=chuyen"+i+" id=chuyen"+(i)+" style='margin-top: 3px;' hidden/></td>";
							}
							result += "</tr>";
							
					   
				}
			result += "<input type=text name=soLuong id=soLuong value="+sl+" hidden/>"
					+"</table>";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
					
			
			
		return result;
		}
		
		@RequestMapping(value = "/danh-sach-lam-so-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView dsLamSHK(String ktra, HttpSession session){
			ModelAndView model = new ModelAndView();
			
			String viewName ="DanhSachTachSoHoKhau";
			String html = "";
			try {
				String taiKhoan = session.getAttribute("ssTaiKhoan").toString();
				TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
				if(quyenService.kiemTraQuyenBangTen(taiKhoan, "DUYET_TACH_HO_KHAU")){
					String noiLV = tk.getCoQuan();
					List<DSLamHoKhau> dsLamHoKhau = shkService.dsLamHoKhauChuaDuyet(noiLV);
					int i;
					if(dsLamHoKhau.size() > 0){
						for(i = 0; i < dsLamHoKhau.size(); i++){
							String soCCNguoiDK = dsLamHoKhau.get(i).getSoCCNguoiDK();
							String diaChi = dsLamHoKhau.get(i).getDiaChi();
							CCCD cccd = cccdService.getCCCDBangMa(soCCNguoiDK);
							// vu doi ham lay dan toc
							KhaiSinh khaiSinh = ksService.getKhaiSinhBangSoKS(dsLamHoKhau.get(i).getSoKSThanhVien());
							String hoTen = cccd.getHoTen();
							//System.out.println("so CC: "+soCCNguoiDK+ "ho ten: "+hoTen+"dia chi: "+diaChi);
							html += "<tr>"
									+ "<td>"+dsLamHoKhau.get(i).getMaSo()+"</td>"
									+ "<td>"+dsLamHoKhau.get(i).getSoHKCu()+"</td>"
									+ "<td>"+soCCNguoiDK+"</td>"
									+ "<td>"+hoTen+"</td>"
									+ "<td>"+cccd.getGioiTinh()+"</td>"
									+ "<td>"+khaiSinh.getDanToc().getTenDT()+"</td>"
									+ "<td>"+diaChi+"</td>"
									+ "<td><a href='chi-tiet-lam-so-ho-khau?soCC="+soCCNguoiDK+"' >Xem</a></td>"
									+ "</tr>";
						}
					} else{
						html = "<td colspan='8'>Không có thẻ cần làm</td>";
					}
					model.addObject("dsLamHoKhau", html);
				}else{
					viewName = "403";
				}
			} catch (Exception e) {
				viewName = "login";
			}	
			model.setViewName(viewName);
			return model;
		}
		
		@RequestMapping(value = "danh-sach-lam-so-ho-khau-chua-duyet", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView dsLamSHKDuyet(String ktra, HttpSession session){
				TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
				List<DSLamHoKhau> dsLamHoKhau = new ArrayList<DSLamHoKhau>();
				String viewName = "DanhSachTachSoHoKhau";
				try {
					if(quyenService.kiemTraQuyenBangTen(tk.getUsername().toString(), "DUYET_TACH_HO_KHAU")){
						String noiLV = tk.getCoQuan();
						dsLamHoKhau = shkService.dsLamHoKhauChuaDuyet(noiLV);
					}else{
						viewName = "403";
					}
				} catch (Exception e) {
					viewName = "login";
				}
			
			String html = "";
			int i;
			if(dsLamHoKhau.size() > 0){
				System.out.println(dsLamHoKhau.size());
				for(i = 0; i < dsLamHoKhau.size(); i++){
					String soCCNguoiDK = dsLamHoKhau.get(i).getSoCCNguoiDK();
					String diaChi = dsLamHoKhau.get(i).getDiaChi();
					CCCD cccd = cccdService.getCCCDBangMa(soCCNguoiDK);
					// vu doi ham lay dan toc
					KhaiSinh khaiSinh = ksService.getKhaiSinhBangSoKS(dsLamHoKhau.get(i).getSoKSThanhVien());
					String hoTen = cccd.getHoTen();
					//System.out.println("so CC: "+soCCNguoiDK+ "ho ten: "+hoTen+"dia chi: "+diaChi);
					html += "<tr>"
							+ "<td>"+dsLamHoKhau.get(i).getMaSo()+"</td>"
							+ "<td>"+dsLamHoKhau.get(i).getSoHKCu()+"</td>"
							+ "<td>"+soCCNguoiDK+"</td>"
							+ "<td>"+hoTen+"</td>"
							+ "<td>"+cccd.getGioiTinh()+"</td>"
							+ "<td>"+khaiSinh.getDanToc().getTenDT()+"</td>"
							+ "<td>"+diaChi+"</td>"
							+ "<td><a href='chi-tiet-lam-so-ho-khau?soCC="+soCCNguoiDK+"' >Xem</a></td>"
							+ "</tr>";
				}
			} else {
				html = "<td colspan='8'>Không có</td>";
			}
			ModelAndView model = new ModelAndView();
			model.addObject("dsLamHoKhau", html);	
			model.addObject("title","DANH SÁCH CHƯA DUYỆT");
			model.setViewName(viewName);
			return model;
		}
		
		@RequestMapping(value = "danh-sach-lam-so-ho-khau-khong-duyet", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView dsLamSHKKhongDuyet(String ktra, HttpSession session){
			TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
			List<DSLamHoKhau> dsLamHoKhau = null;
				
			String html = "";
			try {
					String noiLV = tk.getCoQuan();
					dsLamHoKhau = shkService.dsLamHoKhauKhongDuyet(noiLV);
				
			} catch (Exception e) {
				
			}
			int i;
			if(dsLamHoKhau.size() > 0){
				System.out.println(dsLamHoKhau.size());
				for(i = 0; i < dsLamHoKhau.size(); i++){
					String soCCNguoiDK = dsLamHoKhau.get(i).getSoCCNguoiDK();
					String diaChi = dsLamHoKhau.get(i).getDiaChi();
					CCCD cccd = cccdService.getCCCDBangMa(soCCNguoiDK);
					// vu doi ham lay dan toc
					KhaiSinh khaiSinh = ksService.getKhaiSinhBangSoKS(dsLamHoKhau.get(i).getSoKSThanhVien());
					String hoTen = cccd.getHoTen();
					//System.out.println("so CC: "+soCCNguoiDK+ "ho ten: "+hoTen+"dia chi: "+diaChi);
					html += "<tr>"
							+ "<td>"+dsLamHoKhau.get(i).getMaSo()+"</td>"
							+ "<td>"+dsLamHoKhau.get(i).getSoHKCu()+"</td>"
							+ "<td>"+soCCNguoiDK+"</td>"
							+ "<td>"+hoTen+"</td>"
							+ "<td>"+cccd.getGioiTinh()+"</td>"
							+ "<td>"+khaiSinh.getDanToc().getTenDT()+"</td>"
							+ "<td>"+diaChi+"</td>"
							+ "<td><a href='chi-tiet-lam-so-ho-khau?soCC="+soCCNguoiDK+"' >Xem</a></td>"
							+ "</tr>";
				}
			} else {
				html = "<td colspan='8'>Không có</td>";
			}
			 ModelAndView model = new ModelAndView();
			 model.addObject("dsLamHoKhau", html);	
			 model.addObject("title","DANH SÁCH KHÔNG DUYỆT");
			 model.setViewName("DanhSachTachSoHoKhau");
			 return model;
			
		}
		
		@RequestMapping(value = "tim-kiem-ds-lam-so-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public @ResponseBody String timKiemDSLamSHK(String tuKhoa){
			List<DSLamHoKhau> dsLamHoKhau = shkService.dsLamHoKhauBangTuKhoa(tuKhoa);
			String html = "";
			int i;
			if(dsLamHoKhau.size() > 0){
				System.out.println(dsLamHoKhau.size());
				System.out.println(tuKhoa);
				for(i = 0; i < dsLamHoKhau.size(); i++){
					String soCCNguoiDK = dsLamHoKhau.get(i).getSoCCNguoiDK();
					String diaChi = dsLamHoKhau.get(i).getDiaChi();
					CCCD cccd = cccdService.getCCCDBangMa(soCCNguoiDK);
					// vu doi ham lay dan toc
					KhaiSinh khaiSinh = ksService.getKhaiSinhBangSoKS(dsLamHoKhau.get(i).getSoKSThanhVien());
					String hoTen = cccd.getHoTen();
					//System.out.println("so CC: "+soCCNguoiDK+ "ho ten: "+hoTen+"dia chi: "+diaChi);
					html += "<tr>"
							+ "<td>"+dsLamHoKhau.get(i).getMaSo()+"</td>"
							+ "<td>"+dsLamHoKhau.get(i).getSoHKCu()+"</td>"
							+ "<td>"+soCCNguoiDK+"</td>"
							+ "<td>"+hoTen+"</td>"
							+ "<td>"+cccd.getGioiTinh()+"</td>"
							+ "<td>"+khaiSinh.getDanToc().getTenDT()+"</td>"
							+ "<td>"+diaChi+"</td>"
							+ "<td><a href='chi-tiet-lam-so-ho-khau?soCC="+soCCNguoiDK+"' >Xem</a></td>"
							+ "</tr>";
				}
			} else {
				html = "<td colspan='8'>Không có</td>";
			}
			
			return html;
		}
		
		@RequestMapping(value = "/chi-tiet-lam-so-ho-khau")
		public ModelAndView chiTietLamSHK(String soCC){
			List<DSLamHoKhau> dsLamHoKhau = shkService.dsLamHoKhauBangMa(soCC);
			String html = "";
			String htmlNguoiDK = "";
			int i;
			String soCCNguoiDK = soCC;
			String tenDanTocNguoiDK = "";
			int soLuong = dsLamHoKhau.size();
			String diaChi = dsLamHoKhau.get(0).getDiaChi();
			CCCD cccdNguoiDK = cccdService.getCCCDBangMa(soCCNguoiDK);
			// vu doi ham lay dan toc
			SoHoKhau shk = shkService.getSoKSBangSoCC(soCCNguoiDK);
			KhaiSinh khaiSinhNguoiDK = ksService.getKhaiSinhBangSoKS(shk.getSoKS());
			
			String hoTenNguoiDK = cccdNguoiDK.getHoTen();
			String gioiTinh = cccdNguoiDK.getGioiTinh();
			htmlNguoiDK += "<tr>"
					+ "<td><a href='xem-thong-tin-cccd?soCC="+soCCNguoiDK+"' target='_blank'>"+soCCNguoiDK+"</a></td>"
					+ "<td>"+hoTenNguoiDK+"</td>"
					+ "<td>"+gioiTinh+"</td>"
					+ "<td>"+khaiSinhNguoiDK.getDanToc().getTenDT()+"</td>"
					+ "<td>"+diaChi+"</td>"
					+ "</tr>"
					+ "<input type=hidden id=soLuong name=soLuong value="+soLuong+" /> "
					+ "<input type=hidden id=diaChi name=diaChi value='"+diaChi+"' /> "
					+ "<input type=hidden id=soCCNguoiDK name=soCCNguoiDK value='"+soCCNguoiDK+"' /> ";
	
			if(dsLamHoKhau.size() > 0){
				for(i = 0; i < dsLamHoKhau.size(); i++){
					String soHKCu = shkService.dsLamHoKhauBangMa(soCC).get(i).getSoHKCu();
					String soKSThanhVien = shkService.dsLamHoKhauBangMa(soCC).get(i).getSoKSThanhVien();
					String soCCThanhVien = shkService.dsLamHoKhauBangMa(soCC).get(i).getSoCCThanhVien();
					String quanHe = shkService.dsLamHoKhauBangMa(soCC).get(i).getQuanHe();	
					String tenQuanHe = shkService.layTenQuanHe(quanHe);
					System.out.println(soCCThanhVien);
					CCCD cccd = cccdService.getCCCDBangMa(soCCThanhVien);
					KhaiSinh ks = ksService.getKhaiSinhBangSoKS(soKSThanhVien);
					String hoTen = ks.getHoTen();
					String tinhTrang = shkService.dsLamHoKhauBangMa(soCC).get(i).getTinhTrang();
					html += "<tr>"
							+ "<td><a href='chi-tiet-so-ho-khau?soHK="+soHKCu+"'>"+soHKCu+"</a></td>"
							+ "<td><a href='trang-chi-tiet-khai-sinh?soKS="+soKSThanhVien+"' target='_blank'>"+soKSThanhVien+"</a></td>"
							+ "<td><a href='xem-thong-tin-cccd?soCC="+soCCThanhVien+"' target='_blank'>"+soCCThanhVien+"</a></td>"
							+ "<td>"+hoTen+"</td>"
							+ "<td>"+tinhTrang+"</td>"
							+ "<td>"+tenQuanHe+"</td>"
							+ "</tr>"
							+ "<input type=hidden id=soKSThanhVien"+i+" name=soKSThanhVien"+i+" value='"+soKSThanhVien+"' /> "	
							+ "<input type=hidden id=quanHe"+i+" name=quanHe"+i+" value='"+quanHe+"' /> "
							+ "<input type=hidden id=tinhTrang"+i+" name=tinhTrang"+i+" value='"+tinhTrang+"' /> ";
				}
			} else {
				html = "<td colspan='8'>Không Có Đăng Ký Mới</td>";
			}
			ModelAndView model = new ModelAndView();
			model.addObject("lyDo", dsLamHoKhau.get(0).getLydo());
			model.addObject("NguoiDK", htmlNguoiDK);
			model.addObject("chiTietLamHoKhau", html);	
			model.setViewName("ChiTietTachSoHoKhau");
			return model;
		}
		
		@RequestMapping (value = "/duyet-lam-so-ho-khau", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public ModelAndView duyetLamSHK(String soLuong, String soCCNguoiDK, HttpSession session
										, HttpServletResponse response, HttpServletRequest request){
			shkService.duyetLamHoKhau(soCCNguoiDK); // chinh sua cot DA_KIEM_DUYET trong bang dang ky shk thanh 1
			//System.out.println(soCCNguoiDK);
			String coQuan = session.getAttribute("ssCoQuan").toString();
			String soHKMoi = shkService.taoMaSoHK();
			String diaChi = request.getParameter("diaChi");
			String ngayChuyenDen = functionService.getNgayHienTai();
			shkService.themSoHoKhau(soHKMoi, diaChi, coQuan); //cap so ho khau moi cho nguoi duoc duyet
			System.out.println("so ho khau moi: "+soHKMoi +" dia chi: "+ diaChi);
			int sl = Integer.valueOf(soLuong);
			String[] soKSThanhVien = new String[sl];
			String[] quanHe = new String[sl];
			String[] tinhTrang = new String[sl];
			String paramSoKSThanhVien = "soKSThanhVien";
			for(int i = 0; i < sl; i++) {
				soKSThanhVien[i] = request.getParameter(paramSoKSThanhVien + i); //lấy số căn cước của thành viên thứ i
				quanHe[i] = request.getParameter("quanHe" + i);
				tinhTrang[i] = request.getParameter("tinhTrang"+i);
				shkService.chuyenHoKhau(soHKMoi, soKSThanhVien[i], quanHe[i], ngayChuyenDen, tinhTrang[i]);
				System.out.println("da chuyen thanh cong thanh vien  "+soKSThanhVien[i]+ quanHe[i]);
			}
			
			try {
				response.sendRedirect("danh-sach-lam-so-ho-khau");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ModelAndView model = new ModelAndView();
			model.setViewName("Temp");
			return model;
		}
		
		@RequestMapping (value = "/khong-duyet-ho-khau", method = RequestMethod.GET)
		public ModelAndView khongDuyetDon(String soCCNguoiDK, String lyDo, HttpSession session){
			//String nguoiDuyet = session.getAttribute("ssTaiKhoan");
			shkService.khongDuyet(soCCNguoiDK, lyDo);
			
			ModelAndView model = new ModelAndView();
			model.setViewName("DanhSachTachSoHoKhau");
			return model;
		}
		
		@RequestMapping(value = "/dang-ky-them-nhan-khau", produces = "text/plain;charset=UTF-8")
		public ModelAndView dkThemNhanKhau(HttpSession session){
			List<Tinh> dsTinh = tinhService.getDSTinh();
			ModelAndView model = new ModelAndView();
			String viewName = "DangKyThemThanhVienHoKhau";
			String html ="";
			try {
				String taiKhoan = session.getAttribute("ssTaiKhoan").toString();
				if(quyenService.kiemTraQuyenBangTen(taiKhoan, "CONG_DAN")){
					try {
						SoHoKhau shk = shkService.getSoHoKhau("A.so_cc ="+taiKhoan);
						if(!shk.getQuanHe().equals("chuho")){
							viewName = "403";
							model.addObject("thongBao","Chỉ có chủ hộ mới được quyền đăng ký thêm nhân khẩu");
						}
					} catch (Exception e) {
						viewName = "403";
					}
					List<DSQuanHe> dsQuanHe =  shkService.dsQuanHe();
					for(int i = 0; i < dsQuanHe.size(); i++){
						if(!dsQuanHe.get(i).getQuanHe().equals("chuho")){
							html += "<option value = "+dsQuanHe.get(i).getQuanHe()+">"+dsQuanHe.get(i).getTenQuanHe()+"</option>";
						}
					}
					model.addObject("DSQuanHe", html);
					model.addObject("dsTinh", dsTinh);
				}else{
					viewName ="403";
				}
			} catch (Exception e) {
				viewName ="login";
			}
			model.setViewName(viewName);
			return model;
		}
		
		@RequestMapping(value = "/string-chi-tiet-so-ho-khau" , method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public @ResponseBody String chiTietSHK2(String soHK, HttpSession session){
			List<SoHoKhau> chiTietSHK = shkService.getSoHoKhauBangMa(soHK);
			if(chiTietSHK == null){
				return null;
			}else{
				List<KhaiSinh> ks = null;
				String hoTen;
				String soCC;
				String soKS;
				String gioiTinh;
				String diaChi;
				String quanHe;
				String tinhTrang;
				String html = "";
				diaChi = chiTietSHK.get(0).getDiaChi();
				String taiKhoan = session.getAttribute("ssTaiKhoan").toString();
				int found = 0;
				for(int i = 0 ; i < chiTietSHK.size(); i++){
					if(chiTietSHK.get(i).getSoCC().equals(taiKhoan)){
						found = 1;
					}
				}
				if(found == 0){
					return "Không thuộc hộ khẩu";
				}
				
				for(int i = 0 ; i < chiTietSHK.size(); i++){
					ks = ksService.DSKhaiSinhBangMaHK(soHK);
					hoTen = ks.get(i).getHoTen();
					soCC = chiTietSHK.get(i).getSoCC();
					soKS = chiTietSHK.get(i).getSoKS();
					diaChi = chiTietSHK.get(i).getDiaChi();
					quanHe = chiTietSHK.get(i).getQuanHe();
					tinhTrang = chiTietSHK.get(i).getTinhTrang();
					String tenQuanHe = shkService.layTenQuanHe(quanHe);
					html += "<tr>"
							+"<td>"+soHK+"</td>"
							+"<td><a href='trang-chi-tiet-khai-sinh?soKS="+soKS+"' target='_blank'>"+soKS+"</a></td>"
							+"<td><a href='xem-thong-tin-cccd?soCC="+soCC+"' target='_blank' >"+soCC+"</a></td>"
							+"<td>"+hoTen+"</td>"
							+"<td>"+tinhTrang+"</td>"
							+"<td>"+tenQuanHe+"</td>"
							+"</tr>"
							+"<input type=hidden id='soKSTrongHK"+i+"' name='soKSTrongHK"+i+"' value='"+soKS+"'>"
							+"<input type=hidden id='soCCTrongHK"+i+"' name='soCCTrongHK"+i+"' value='"+soCC+"'>"
							+"<input type=hidden id='quanHeTV"+i+"' value='"+quanHe+"'>"
							+"<input type=hidden id='soHK' name='soHK' value='"+soHK+"'>";
					
				}
				html +="<input type=hidden id='soLuongTV' name='soLuongTV' value='"+chiTietSHK.size()+"'>";
				return html;
			}
		}
		
		@RequestMapping(value = "/lay-thong-tin-hon-nhan", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public @ResponseBody String thongtinhonnhan(String soCCChuHo, HttpSession session){
				String result = null;
				System.out.println("so CC cua chong duoc gui qua nek: "+soCCChuHo);
				try {
					HonNhan hn = honNhanService.getHonNhanBangSoCC(soCCChuHo);
					if(hn.getSoCCChong().equals(soCCChuHo)){
						result = "soCCVo"+hn.getSoCCVo();
					}else{
						result = "soCCChong"+hn.getSoCCChong();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return result;
		}
		
		@RequestMapping(value = "/ket-qua-dang-ky-nhan-khau", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public ModelAndView ketQuaDangKyNhanKhau(String soLuongDK,  HttpServletRequest request, HttpSession session, HttpServletResponse response){
			ModelAndView model = new ModelAndView();
			int sl = Integer.valueOf(soLuongDK);
			KhaiSinh khaiSinh = new KhaiSinh();
			SoHoKhau shk = new SoHoKhau();
			List<SoHoKhau> shk1 ;
			String noiDKLVTinh;
			String noiDKLVHuyen;
			String noiDKLV;
			String soHKCu;//so ho khau hien tai cua nguoi duoc dang ky
			String soHKDK;//so ho khau duoc dang ky them vao
			String hoTenchuHo;
			String diaDiem;
			String[] soCCThanhVien = new String[sl];
			String[] soKSThanhVien = new String[sl];
			String[] quanHeDK = new String[sl];
			String[] tinhTrang = new String[sl];
			noiDKLVTinh = request.getParameter("noiDKLVTinh");
			noiDKLVHuyen = request.getParameter("noiDKLVHuyen");
			if(noiDKLVHuyen.equals("0")){
				noiDKLV = noiDKLVTinh;
				diaDiem = tinhService.getTinhBangMa(noiDKLVTinh).getTenTinh();
			}else{
				noiDKLV = noiDKLVHuyen;
				diaDiem = huyenService.getHuyenBangMa(noiDKLVHuyen).getTenHuyen();
			}
			soHKDK =request.getParameter("soHK");
			shk = shkService.getSoHoKhau("B.SO_HK = '"+soHKDK+"'");		
			String soKSchuHo = shk.getSoKS();
			hoTenchuHo = ksService.getKhaiSinhBangSoKS(soKSchuHo).getHoTen();
			String ngayDK = functionService.getNgayHienTai();
			String ngayHen = "";
			for(int i = 0; i < sl; i++) {
				soKSThanhVien[i] = request.getParameter("soKSThanhVien" + i); //lấy số khai sinh của thành viên thứ i
				soKSThanhVien[i] = soKSThanhVien[i].replaceAll("-", "");
				soCCThanhVien[i] = request.getParameter("soCCTV" + i); //lấy số căn cước của thành viên thứ i
				quanHeDK[i] = request.getParameter("quanHeDK" + i);
				tinhTrang[i] = request.getParameter("tinhTrangThanhVien"+i);
				shk = shkService.getSoHoKhau("SO_KS = "+soKSThanhVien[i]);
				ngayDK = functionService.getNgayHienTai();
				
				try {
					ngayHen = shkService.taoNgayHen(ngayDK);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(shk == null){
					soHKCu = "";
				}else{
					soHKCu = shk.getSoHK();
				}
					shkService.dkThemThanhVien(soHKCu, soHKDK, soKSThanhVien[i], soCCThanhVien[i], quanHeDK[i], ngayDK, ngayHen, noiDKLV, tinhTrang[i]);
				System.out.println(soCCThanhVien[i]+"   "+soKSThanhVien[i]+"    " +quanHeDK[i]);
			}
			
			model.addObject("ngayHen", ngayHen);
			model.addObject("diaDiem", diaDiem);
			model.addObject("soHKDK", soHKDK);
			model.addObject("hoTenNguoiDK", hoTenchuHo);
			model.setViewName("GiayHenThemNhanKhauSoHoKhau");
			return model;
		}
		
		@RequestMapping (value = "/danh-sach-them-nhan-khau-so-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView DanhSachThemNhanKhauSoHoKhau(HttpSession session){
			TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
			List<DSThemNhanKhau> dsThemNhanKhau = new ArrayList<DSThemNhanKhau>();
			String viewName = "DanhSachThemNhanKhauSoHoKhau";
			try {
				if(quyenService.kiemTraQuyenBangTen(tk.getUsername().toString(), "DUYET_THEM_NHAN_KHAU")){
					String noiLV = tk.getCoQuan();
					dsThemNhanKhau = shkService.dsDangKyThemNhanKhau(noiLV);
				}else{
					viewName = "403";
				}
			} catch (Exception e) {
				viewName = "login";
			}
			String hoTen;
			String soHKMoi;
			SoHoKhau shk;
			String diaChi;
			String soKSChuHo;
			String soCCChuHo;
			String duyet;
			String tinhTrang;
			String ngayHienTai = functionService.getNgayHienTai();
			String html = "";
			int i;
			
			if(dsThemNhanKhau.size() > 0){
				for(i = 0; i < dsThemNhanKhau.size(); i++){
					duyet = dsThemNhanKhau.get(i).getDuyet();
					soHKMoi = dsThemNhanKhau.get(i).getSoHKMoi();
					shk = shkService.getSoHoKhau("B.SO_HK="+soHKMoi+" AND QUAN_HE = 'chuho'");
					soKSChuHo = shk.getSoKS();
					soCCChuHo = shk.getSoCC();
					diaChi = shk.getDiaChi();
					KhaiSinh ks = ksService.getKhaiSinhBangSoKS(soKSChuHo);
					hoTen = ks.getHoTen();
					//System.out.println("so CC: "+soCCNguoiDK+ "ho ten: "+hoTen+"dia chi: "+diaChi);
					String tenDanToc = "Chưa rõ";
					DanToc dt =ks.getDanToc();
					tenDanToc = dt.getTenDT();
					tinhTrang=shk.getTinhTrang();
					if(duyet.equals("0") && ngayHienTai.equals(dsThemNhanKhau.get(i).getNgayHen())){
						html += "<tr>"
								+ "<td>"+soHKMoi+"</td>"
								+ "<td>"+diaChi+"</td>"
								+ "<td>"+soCCChuHo+"</td>"
								+ "<td>"+hoTen+"</td>"
								+ "<td>"+ks.getGioiTinh()+"</td>"
								+ "<td>"+tenDanToc+"</td>";
						html +="<td><a href='chi-tiet-dang-ky-them-nhan-khau-so-ho-khau?soHKMoi="+soHKMoi+"' title='Xem chi tiết'>Xem</a></td>";
						html += 	"</tr>";
					}
				}
			} else {
				html = "<td colspan='8'>Không có đơn cần duyệt</td>";
			}
			ModelAndView model = new ModelAndView();
			model.addObject("dsThemNhanKhau", html);	
			model.setViewName(viewName);
			return model;
		}
		
		@RequestMapping (value = "/chi-tiet-dang-ky-them-nhan-khau-so-ho-khau" , method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView chitietdangkythemnhankhausohokhau(String soHKMoi, HttpServletRequest request, HttpSession session, HttpServletResponse response){
			List<SoHoKhau> chiTietSHK = shkService.getSoHoKhauBangMa(soHKMoi);
			if(chiTietSHK == null){
				return null;
			}else{
				List<KhaiSinh> ks = null;
				String soHKCu;
				String dsNhanKhauDK = "";
				String hoTen;
				String soCC;
				String soKS;
				String gioiTinh;
				String quanHe;
				String tenQuanHe;
				String tinhTrang;
				String html = "";
				for(int i = 0 ; i < chiTietSHK.size(); i++){
					ks = ksService.DSKhaiSinhBangMaHK(soHKMoi);
					hoTen = ks.get(i).getHoTen();
					soCC = chiTietSHK.get(i).getSoCC();
					soKS = chiTietSHK.get(i).getSoKS();
					gioiTinh = ks.get(i).getGioiTinh();
					quanHe = chiTietSHK.get(i).getQuanHe();
					tenQuanHe = shkService.layTenQuanHe(quanHe);
					tinhTrang = chiTietSHK.get(i).getTinhTrang();
					html += "<tr>"
							+"<td>"+soHKMoi+"</td>"
							+"<td><a href='trang-chi-tiet-khai-sinh?soKS="+soKS+"' title='Xem thông tin' target='_blank'>"+soKS+"</a></td>"
							+"<td><a href='xem-thong-tin-cccd?soCC="+soCC+"' title='Xem thông tin' target='_blank' >"+soCC+"</a></td>"
							+"<td>"+hoTen+"</td>"
							+"<td>"+tinhTrang+"</td>"
							+"<td>"+tenQuanHe+"</td>"
							+"</tr>";
						
				}
				html +="<input type=hidden id='soLuongTV' name='soLuongTV' value='"+chiTietSHK.size()+"'>"
						+ "<input type=hidden id='soHKMoi' name='soHKMoi' value='"+soHKMoi+"'>";
				
				List<DSThemNhanKhau> dsNhanKhauDuocDK;
				dsNhanKhauDuocDK = shkService.dsNhanKhauDK(soHKMoi);
				KhaiSinh KhaiSinh;
				for(int i = 0 ; i < dsNhanKhauDuocDK.size(); i++){
					soHKCu = dsNhanKhauDuocDK.get(i).getSoHKCU();
					soKS = dsNhanKhauDuocDK.get(i).getSoKS();
					KhaiSinh = ksService.getKhaiSinhBangSoKS(soKS);
					System.out.println(soKS);
					DanToc danToc = KhaiSinh.getDanToc();
					String TenDT = danToc.getTenDT();
					hoTen = KhaiSinh.getHoTen();
					soCC = dsNhanKhauDuocDK.get(i).getSoCC();
					soKS = dsNhanKhauDuocDK.get(i).getSoKS();
					gioiTinh = KhaiSinh.getGioiTinh();
					quanHe = dsNhanKhauDuocDK.get(i).getQuanHe();
					tenQuanHe = shkService.layTenQuanHe(quanHe);
					tinhTrang = dsNhanKhauDuocDK.get(i).getTinhTrang();
					dsNhanKhauDK += "<tr>"
							+"<td>"+soHKCu+"</td>"
							+"<td><a href='trang-chi-tiet-khai-sinh?soKS="+soKS+"' title='Xem thông tin' target='_blank'>"+soKS+"</a></td>"
							+"<td><a href='xem-thong-tin-cccd?soCC="+soCC+"' title='Xem thông tin' target='_blank' >"+soCC+"</a></td>"
							+"<td>"+hoTen+"</td>"
							+"<td>"+tinhTrang+"</td>"
							+"<td>"+tenQuanHe+"</td>"
							+"</tr>"
							+ "<input type=hidden id='soKS"+i+"' name='soKS"+i+"' value='"+soKS+"'>"
							+ "<input type=hidden id='soCC"+i+"' name='soCC"+i+"' value='"+soCC+"'>"
							+ "<input type=hidden id='quanHeDK"+i+"' name='quanHeDK"+i+"' value='"+quanHe+"'>"
							+ "<input type=hidden id='soHKCu"+i+"' name='soHKCu"+i+"' value='"+soHKCu+"'>"
							+ "<input type=hidden id='tinhTrang"+i+"' name='tinhTrang"+i+"' value='"+tinhTrang+"'>";
				}
				dsNhanKhauDK +="<input type=hidden id='soLuongTVDK' name='soLuongTVDK' value='"+dsNhanKhauDuocDK.size()+"'>";
							
				ModelAndView model = new ModelAndView();
				model.addObject("dsNhanKhauDK", dsNhanKhauDK);
				model.addObject("dsNhanKhau", html);	
				model.setViewName("ChiTietDangKyThemNhanKhauSoHoKkhau");
				return model;
			}
		}
		
		@RequestMapping(value = "/duyet-dang-ky-them-nhan-khau", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
		public ModelAndView ketQuaDuyetKyThemNhanKhau(String soLuongTVDK,  HttpServletRequest request, HttpSession session, HttpServletResponse response){	
			int sl = Integer.valueOf(soLuongTVDK);
			//System.out.println("so luong tvdk: "+sl);
			String ngayDuyet;
			String soHKMoi;
			String nguoiDuyet = "";
			String ngayChuyenDen = functionService.getNgayHienTai();
			nguoiDuyet = session.getAttribute("ssTaiKhoan").toString();
			String[] soKS = new String[sl];
			String[] soCC = new String[sl];
			String[] quanHeDK = new String[sl];
			String[] soHKCu = new String[sl];
			String[] tinhTrang = new String[sl];
			ngayDuyet = functionService.getNgayHienTai();
			soHKMoi = request.getParameter("soHKMoi");
			shkService.duyetThemNhanKhau("3", soHKMoi, ngayDuyet, nguoiDuyet, "");// chuyen duyet thanh 1
			for(int i = 0; i < sl; i++) {
				soKS[i] = request.getParameter("soKS" + i); //lấy số khai sinh của thành viên thứ i
				soKS[i] = soKS[i].replaceAll("-", "");
				soCC[i] = request.getParameter("soCC"+i);
				quanHeDK[i] = request.getParameter("quanHeDK" + i);
				soHKCu[i] = request.getParameter("soHKCu"+i); 
				tinhTrang[i] = request.getParameter("tinhTrang"+i);
				if(!soHKCu[i].equals("")){
					shkService.chuyenHoKhau(soHKMoi, soKS[i],quanHeDK[i], ngayChuyenDen, tinhTrang[i]);
					SoHoKhau soLuongNhanKhauHoKhauCu = shkService.getSoHoKhau("B.so_hk = "+soHKCu[i]);
					try {
						if(soLuongNhanKhauHoKhauCu.getSoKS() == null){
							System.out.println("chua co rong");
						}
					} catch (Exception e) {
						System.out.println("sổ hộ khẩu "+soHKCu[i]+" bị rỗng");
						shkService.xoaHoKhau(soHKCu[i]);
					}	
				}else{
					shkService.themChiTietSoHoKhau(soHKMoi, soCC[i], soKS[i], quanHeDK[i], ngayChuyenDen);
					
					
				}
				
			}
			try {
				response.sendRedirect("danh-sach-them-nhan-khau-so-ho-khau");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ModelAndView model = new ModelAndView();
			model.setViewName("Temp");
			return model;
		}
		
		@RequestMapping (value = "/khong-duyet-dang-ky-them-nhan-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView khongDuyet(String lyDo, String soHKMoi, HttpSession session){
			//String nguoiDuyet = session.getAttribute("ssTaiKhoan");
			String ngayDuyet;
			String nguoiDuyet = "";
			nguoiDuyet = session.getAttribute("ssTaiKhoan").toString();
			ngayDuyet = functionService.getNgayHienTai();
				shkService.duyetThemNhanKhau("4", soHKMoi, ngayDuyet, nguoiDuyet, lyDo);
			ModelAndView model = new ModelAndView();
			model.setViewName("DanhSachThemNhanKhauSoHoKhau");
			return model;
		}
		
		@RequestMapping(value = "/tim-kiem-danh-sach-them-nhan-khau-so-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public @ResponseBody String timKiemDSTNKSHK(String tuKhoa, HttpSession session){
			String noiDKLV = session.getAttribute("ssCoQuan").toString();
			System.out.println(tuKhoa+noiDKLV);
			List<DSThemNhanKhau> dsThemNhanKhau = shkService.dsDangKyThemNhanKhauBangTuKhoa(noiDKLV, tuKhoa);
			String html = "";
			String hoTen;
			String soHKMoi;
			SoHoKhau shk;
			String diaChi;
			String soKSChuHo;
			String soCCChuHo;
			String duyet;
			int i;
			if(dsThemNhanKhau.size() > 0 && tuKhoa!=""){
				for(i = 0; i < dsThemNhanKhau.size(); i++){
					duyet = dsThemNhanKhau.get(i).getDuyet();
					soHKMoi = dsThemNhanKhau.get(i).getSoHKMoi();
					shk = shkService.getSoHoKhau("B.SO_HK="+soHKMoi+" AND QUAN_HE = 'chuho'");
					soKSChuHo = shk.getSoKS();
					soCCChuHo = shk.getSoCC();
					diaChi = shk.getDiaChi();
					KhaiSinh ks = ksService.getKhaiSinhBangSoKS(soKSChuHo);
					hoTen = ks.getHoTen();
					//System.out.println("so CC: "+soCCNguoiDK+ "ho ten: "+hoTen+"dia chi: "+diaChi);
					String tenDanToc = "Chưa rõ";
					DanToc danToc = ks.getDanToc();
					danToc.getTenDT();
					if(danToc != null){
						tenDanToc = danToc.getTenDT();
					}
					if(duyet.equals("0")){
						html += "<tr>"
								+ "<td>"+soHKMoi+"</td>"
								+ "<td>"+diaChi+"</td>"
								+ "<td>"+soCCChuHo+"</td>"
								+ "<td>"+hoTen+"</td>"
								+ "<td>"+ks.getGioiTinh()+"</td>"
								+ "<td>"+tenDanToc+"</td>";
						html +="<td><a href='chi-tiet-dang-ky-them-nhan-khau-so-ho-khau?soHKMoi="+soHKMoi+"' title='Xem thông tin'>Xem</a></td>";
						html += 	"</tr>";
					}
				}
			} else if(tuKhoa ==""){
				dsThemNhanKhau = shkService.dsDangKyThemNhanKhau(noiDKLV);
				for(i = 0; i < dsThemNhanKhau.size(); i++){
					duyet = dsThemNhanKhau.get(i).getDuyet();
					soHKMoi = dsThemNhanKhau.get(i).getSoHKMoi();
					shk = shkService.getSoHoKhau("B.SO_HK="+soHKMoi+" AND QUAN_HE = 'chuho'");
					soKSChuHo = shk.getSoKS();
					soCCChuHo = shk.getSoCC();
					diaChi = shk.getDiaChi();
					KhaiSinh ks = ksService.getKhaiSinhBangSoKS(soKSChuHo);
					hoTen = ks.getHoTen();
					//System.out.println("so CC: "+soCCNguoiDK+ "ho ten: "+hoTen+"dia chi: "+diaChi);
					String tenDanToc = "Chưa rõ";
					DanToc danToc = ks.getDanToc();
					danToc.getTenDT();
					if(danToc != null){
						tenDanToc = danToc.getTenDT();
					}
					if(duyet.equals("0")){
						html += "<tr>"
								+ "<td>"+soHKMoi+"</td>"
								+ "<td>"+diaChi+"</td>"
								+ "<td>"+soCCChuHo+"</td>"
								+ "<td>"+hoTen+"</td>"
								+ "<td>"+ks.getGioiTinh()+"</td>"
								+ "<td>"+tenDanToc+"</td>";
						html +="<td><a href='chi-tiet-dang-ky-them-nhan-khau-so-ho-khau?soHKMoi="+soHKMoi+"' title='Xem chi tiết'>Xem</a></td>";
						html += 	"</tr>";
					}
				}
			}else{
				html = "<td colspan='8'>Không có</td>";
			}
			System.out.println(html);
			return html;
		}
		
		@RequestMapping(value = "/tim-kiem-danh-sach-khong-duyet-them-nhan-khau-so-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public @ResponseBody String timKiemDSKDTNKSHK(String tuKhoa, HttpSession session){
			String noiDKLV = session.getAttribute("ssCoQuan").toString();
			System.out.println(tuKhoa+noiDKLV);
			List<DSThemNhanKhau> dsThemNhanKhau = shkService.dsDangKyThemNhanKhauBangTuKhoa(noiDKLV, tuKhoa);
			String html = "";
			String hoTen;
			String soHKMoi;
			SoHoKhau shk;
			String diaChi;
			String soKSChuHo;
			String soCCChuHo;
			String duyet;
			String lyDo;
			int i;
			if(dsThemNhanKhau.size() > 0 && tuKhoa!=""){
				for(i = 0; i < dsThemNhanKhau.size(); i++){
					duyet = dsThemNhanKhau.get(i).getDuyet();
					soHKMoi = dsThemNhanKhau.get(i).getSoHKMoi();
					shk = shkService.getSoHoKhau("B.SO_HK="+soHKMoi+" AND QUAN_HE = 'chuho'");
					soKSChuHo = shk.getSoKS();
					soCCChuHo = shk.getSoCC();
					diaChi = shk.getDiaChi();
					lyDo = dsThemNhanKhau.get(i).getLyDo();
					KhaiSinh ks = ksService.getKhaiSinhBangSoKS(soKSChuHo);
					hoTen = ks.getHoTen();
					if(duyet.equals("4")){
						html += "<tr>"
								+ "<td>"+soHKMoi+"</td>"
								+ "<td>"+diaChi+"</td>"
								+ "<td>"+soCCChuHo+"</td>"
								+ "<td>"+hoTen+"</td>"
								+ "<td>"+lyDo+"</td>";
						html +="<td><a href='chi-tiet-dang-ky-them-nhan-khau-so-ho-khau?soHKMoi="+soHKMoi+"' title='Xem thông tin'>Xem</a></td>";
						html += 	"</tr>";
					}
				}
			} else if(tuKhoa ==""){
				dsThemNhanKhau = shkService.dsDangKyThemNhanKhau(noiDKLV);
				if(dsThemNhanKhau.size() > 0){
					for(i = 0; i < dsThemNhanKhau.size(); i++){
						lyDo = dsThemNhanKhau.get(i).getLyDo();
						duyet = dsThemNhanKhau.get(i).getDuyet();
						soHKMoi = dsThemNhanKhau.get(i).getSoHKMoi();
						shk = shkService.getSoHoKhau("B.SO_HK="+soHKMoi+" AND QUAN_HE = 'chuho'");
						soKSChuHo = shk.getSoKS();
						soCCChuHo = shk.getSoCC();
						diaChi = shk.getDiaChi();
						KhaiSinh ks = ksService.getKhaiSinhBangSoKS(soKSChuHo);
						hoTen = ks.getHoTen();
						//System.out.println("so CC: "+soCCNguoiDK+ "ho ten: "+hoTen+"dia chi: "+diaChi);
						System.out.println("sohkmoi: "+soHKMoi+" duyet= "+duyet);
						if(duyet.equals("4")){
							html += "<tr>"
									+ "<td>"+soHKMoi+"</td>"
									+ "<td>"+diaChi+"</td>"
									+ "<td>"+soCCChuHo+"</td>"
									+ "<td>"+hoTen+"</td>"
									+ "<td>"+lyDo+"</td>";
							html +="<td><a href='chi-tiet-dang-ky-them-nhan-khau-so-ho-khau?soHKMoi="+soHKMoi+"' title='Xem thông tin'>Xem</a></td>";
							html += 	"</tr>";
						}
					}
				}				
			}else {
				html = "<td colspan='8'>Không có</td>";
			}
			System.out.println(html);
			return html;
		}
		
		
		@RequestMapping (value = "/danh-sach-khong-duyet-them-nhan-khau-so-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView DanhSachKoDuyetThemNhanKhauSoHoKhau(HttpSession session){
			TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
			List<DSThemNhanKhau> dsThemNhanKhau = new ArrayList<DSThemNhanKhau>();
			String viewName = "DanhSachKhongDuyetThemNhanKhauSoHoKhau";
			try {
				String noiLV = tk.getCoQuan();
				dsThemNhanKhau = shkService.dsDangKyThemNhanKhau(noiLV);
			} catch (Exception e) {
				viewName = "login";
			}
			String hoTen;
			String soHKMoi;
			SoHoKhau shk;
			String diaChi;
			String soKSChuHo;
			String soCCChuHo;
			String lyDo;
			String duyet;
			String html = "";
			int i;
			if(dsThemNhanKhau.size() > 0){
				for(i = 0; i < dsThemNhanKhau.size(); i++){
					lyDo = dsThemNhanKhau.get(i).getLyDo();
					duyet = dsThemNhanKhau.get(i).getDuyet();
					soHKMoi = dsThemNhanKhau.get(i).getSoHKMoi();
					shk = shkService.getSoHoKhau("B.SO_HK="+soHKMoi+" AND QUAN_HE = 'chuho'");
					soKSChuHo = shk.getSoKS();
					soCCChuHo = shk.getSoCC();
					diaChi = shk.getDiaChi();
					KhaiSinh ks = ksService.getKhaiSinhBangSoKS(soKSChuHo);
					hoTen = ks.getHoTen();
					//System.out.println("so CC: "+soCCNguoiDK+ "ho ten: "+hoTen+"dia chi: "+diaChi);
					System.out.println("sohkmoi: "+soHKMoi+" duyet= "+duyet);
					if(duyet.equals("4")){
						html += "<tr>"
								+ "<td>"+soHKMoi+"</td>"
								+ "<td>"+diaChi+"</td>"
								+ "<td>"+soCCChuHo+"</td>"
								+ "<td>"+hoTen+"</td>"
								+ "<td>"+lyDo+"</td>";
						html +="<td><a href='chi-tiet-dang-ky-them-nhan-khau-so-ho-khau?soHKMoi="+soHKMoi+"' title='Xem thông tin'>Xem</a></td>";
						html += 	"</tr>";
					}
				}
			} else {
				html = "<td colspan='8'>Không có thẻ cần làm</td>";
			}
			ModelAndView model = new ModelAndView();
			model.addObject("dsThemNhanKhau", html);	
			model.setViewName(viewName);
			return model;
		}
		
		@RequestMapping (value = "/danh-sach-da-duyet-them-nhan-khau-so-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView DanhSachdaDuyetThemNhanKhauSoHoKhau(HttpSession session){
			TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
			List<DSThemNhanKhau> dsThemNhanKhau = new ArrayList<DSThemNhanKhau>();
			String viewName = "DanhSachDaDuyetThemNhanKhauSoHoKhau";
			try {
				String noiLV = tk.getCoQuan();
				dsThemNhanKhau = shkService.dsDangKyThemNhanKhau(noiLV);
			} catch (Exception e) {
				viewName = "login";
			}
			String hoTen;
			String soHKMoi;
			SoHoKhau shk;
			String diaChi;
			String soKSChuHo;
			String soCCChuHo;
			String lyDo;
			String duyet;
			String html = "";
			int i;
			if(dsThemNhanKhau.size() > 0){
				for(i = 0; i < dsThemNhanKhau.size(); i++){
					lyDo = dsThemNhanKhau.get(i).getLyDo();
					duyet = dsThemNhanKhau.get(i).getDuyet();
					soHKMoi = dsThemNhanKhau.get(i).getSoHKMoi();
					shk = shkService.getSoHoKhau("B.SO_HK="+soHKMoi+" AND QUAN_HE = 'chuho'");
					soKSChuHo = shk.getSoKS();
					soCCChuHo = shk.getSoCC();
					diaChi = shk.getDiaChi();
					KhaiSinh ks = ksService.getKhaiSinhBangSoKS(soKSChuHo);
					hoTen = ks.getHoTen();
					//System.out.println("so CC: "+soCCNguoiDK+ "ho ten: "+hoTen+"dia chi: "+diaChi);
					System.out.println("sohkmoi: "+soHKMoi+" duyet= "+duyet);
					if(duyet.equals("3")){
						html += "<tr>"
								+ "<td>"+soHKMoi+"</td>"
								+ "<td>"+diaChi+"</td>"
								+ "<td>"+soCCChuHo+"</td>"
								+ "<td>"+hoTen+"</td>"
								+ "<td>"+lyDo+"</td>";
						html +="<td><a href='chi-tiet-da-duyet-them-nhan-khau-so-ho-khau?soHKMoi="+soHKMoi+"' title='Xem thông tin'>Xem</a></td>";
						html += 	"</tr>";
					}
				}
			} else {
				html = "<td colspan='8'>Không có thẻ cần làm</td>";
			}
			ModelAndView model = new ModelAndView();
			model.addObject("dsThemNhanKhau", html);	
			model.setViewName(viewName);
			return model;
		}
		
		@RequestMapping(value = "/tim-kiem-danh-sach-da-duyet-them-nhan-khau-so-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public @ResponseBody String timKiemdsdaKDTNKSHK(String tuKhoa, HttpSession session){
			String noiDKLV = session.getAttribute("ssCoQuan").toString();
			System.out.println(tuKhoa+noiDKLV);
			List<DSThemNhanKhau> dsThemNhanKhau = shkService.dsDangKyThemNhanKhauBangTuKhoa(noiDKLV, tuKhoa);
			String html = "";
			String hoTen;
			String soHKMoi;
			SoHoKhau shk;
			String diaChi;
			String soKSChuHo;
			String soCCChuHo;
			String duyet;
			String lyDo;
			int i;
			if(dsThemNhanKhau.size() > 0 && tuKhoa!=""){
				for(i = 0; i < dsThemNhanKhau.size(); i++){
					duyet = dsThemNhanKhau.get(i).getDuyet();
					soHKMoi = dsThemNhanKhau.get(i).getSoHKMoi();
					shk = shkService.getSoHoKhau("B.SO_HK="+soHKMoi+" AND QUAN_HE = 'chuho'");
					soKSChuHo = shk.getSoKS();
					soCCChuHo = shk.getSoCC();
					diaChi = shk.getDiaChi();
					lyDo = dsThemNhanKhau.get(i).getLyDo();
					KhaiSinh ks = ksService.getKhaiSinhBangSoKS(soKSChuHo);
					hoTen = ks.getHoTen();
					if(duyet.equals("3")){
						html += "<tr>"
								+ "<td>"+soHKMoi+"</td>"
								+ "<td>"+diaChi+"</td>"
								+ "<td>"+soCCChuHo+"</td>"
								+ "<td>"+hoTen+"</td>"
								+ "<td>"+lyDo+"</td>";
						html +="<td><a href='chi-tiet-da-duyet-them-nhan-khau-so-ho-khau?soHKMoi="+soHKMoi+"' title='Xem thông tin'>Xem</a></td>";
						html += 	"</tr>";
					}
				}
			} else if(tuKhoa ==""){
				dsThemNhanKhau = shkService.dsDangKyThemNhanKhau(noiDKLV);
				if(dsThemNhanKhau.size() > 0){
					for(i = 0; i < dsThemNhanKhau.size(); i++){
						lyDo = dsThemNhanKhau.get(i).getLyDo();
						duyet = dsThemNhanKhau.get(i).getDuyet();
						soHKMoi = dsThemNhanKhau.get(i).getSoHKMoi();
						shk = shkService.getSoHoKhau("B.SO_HK="+soHKMoi+" AND QUAN_HE = 'chuho'");
						soKSChuHo = shk.getSoKS();
						soCCChuHo = shk.getSoCC();
						diaChi = shk.getDiaChi();
						KhaiSinh ks = ksService.getKhaiSinhBangSoKS(soKSChuHo);
						hoTen = ks.getHoTen();
						//System.out.println("so CC: "+soCCNguoiDK+ "ho ten: "+hoTen+"dia chi: "+diaChi);
						System.out.println("sohkmoi: "+soHKMoi+" duyet= "+duyet);
						if(duyet.equals("3")){
							html += "<tr>"
									+ "<td>"+soHKMoi+"</td>"
									+ "<td>"+diaChi+"</td>"
									+ "<td>"+soCCChuHo+"</td>"
									+ "<td>"+hoTen+"</td>"
									+ "<td>"+lyDo+"</td>";
							html +="<td><a href='chi-tiet-da-duyet-them-nhan-khau-so-ho-khau?soHKMoi="+soHKMoi+"' title='Xem thông tin'>Xem</a></td>";
							html += 	"</tr>";
						}
					}
				}				
			}else {
				html = "<td colspan='8'>Không có</td>";
			}
			System.out.println(html);
			return html;
		}
		
		@RequestMapping (value = "/chi-tiet-da-duyet-them-nhan-khau-so-ho-khau" , method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView chitietđauyetthemnhankhausohokhau(String soHKMoi, HttpServletRequest request, HttpSession session, HttpServletResponse response){
			List<SoHoKhau> chiTietSHK = shkService.getSoHoKhauBangMa(soHKMoi);
			if(chiTietSHK == null){
				return null;
			}else{
				List<KhaiSinh> ks = null;
				String soHKCu;
				String dsNhanKhauDK = "";
				String hoTen;
				String soCC;
				String soKS;
				String quanHe;
				String tenQuanHe;
				String tinhTrang;
				String html = "";
				for(int i = 0 ; i < chiTietSHK.size(); i++){
					ks = ksService.DSKhaiSinhBangMaHK(soHKMoi);
					hoTen = ks.get(i).getHoTen();
					soCC = chiTietSHK.get(i).getSoCC();
					soKS = chiTietSHK.get(i).getSoKS();
					quanHe = chiTietSHK.get(i).getQuanHe();
					tenQuanHe = shkService.layTenQuanHe(quanHe);
					tinhTrang = chiTietSHK.get(i).getTinhTrang();
					html += "<tr>"
							+"<td>"+soHKMoi+"</td>"
							+"<td><a href='trang-chi-tiet-khai-sinh?soKS="+soKS+"' title='Xem thông tin' target='_blank'>"+soKS+"</a></td>"
							+"<td><a href='xem-thong-tin-cccd?soCC="+soCC+"' title='Xem thông tin' target='_blank' >"+soCC+"</a></td>"
							+"<td>"+hoTen+"</td>"
							+"<td>"+tinhTrang+"</td>"
							+"<td>"+tenQuanHe+"</td>"
							+"</tr>";
				}				
				List<DSThemNhanKhau> dsNhanKhauDuocDK;
				dsNhanKhauDuocDK = shkService.dsNhanKhauDK(soHKMoi);
				KhaiSinh KhaiSinh;
				for(int i = 0 ; i < dsNhanKhauDuocDK.size(); i++){
					soHKCu = dsNhanKhauDuocDK.get(i).getSoHKCU();
					soKS = dsNhanKhauDuocDK.get(i).getSoKS();
					KhaiSinh = ksService.getKhaiSinhBangSoKS(soKS);
					System.out.println(soKS);
					DanToc danToc = KhaiSinh.getDanToc();
					String TenDT = danToc.getTenDT();
					hoTen = KhaiSinh.getHoTen();
					soCC = dsNhanKhauDuocDK.get(i).getSoCC();
					soKS = dsNhanKhauDuocDK.get(i).getSoKS();
					quanHe = dsNhanKhauDuocDK.get(i).getQuanHe();
					tenQuanHe = shkService.layTenQuanHe(quanHe);
					tinhTrang = dsNhanKhauDuocDK.get(i).getTinhTrang();
					dsNhanKhauDK += "<tr>"
							+"<td>"+soHKCu+"</td>"
							+"<td><a href='trang-chi-tiet-khai-sinh?soKS="+soKS+"' title='Xem thông tin' target='_blank'>"+soKS+"</a></td>"
							+"<td><a href='xem-thong-tin-cccd?soCC="+soCC+"' title='Xem thông tin' target='_blank' >"+soCC+"</a></td>"
							+"<td>"+hoTen+"</td>"
							+"<td>"+tinhTrang+"</td>"
							+"<td>"+tenQuanHe+"</td>"
							+"</tr>"
							+ "<input type=hidden id='soKS"+i+"' name='soKS"+i+"' value='"+soKS+"'>"
							+ "<input type=hidden id='soCC"+i+"' name='soCC"+i+"' value='"+soCC+"'>"
							+ "<input type=hidden id='quanHeDK"+i+"' name='quanHeDK"+i+"' value='"+quanHe+"'>"
							+ "<input type=hidden id='soHKCu"+i+"' name='soHKCu"+i+"' value='"+soHKCu+"'>"
							+ "<input type=hidden id='tinhTrang"+i+"' name='tinhTrang"+i+"' value='"+tinhTrang+"'>";
				}
							
				ModelAndView model = new ModelAndView();
				model.addObject("dsNhanKhauDK", dsNhanKhauDK);
				model.addObject("dsNhanKhau", html);	
				model.setViewName("ChiTietDaDuyetThemNhanKhauSoHoKhau");
				return model;
			}
		}
		
		@RequestMapping(value = "/update-tinh-trang-chi-tiet-ho-khau", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
		public ModelAndView updatetinhtrangchitietshk(String soKS,
				String tinhTrang,
				HttpSession session,
				HttpServletResponse response){
			
			
			Boolean i =shkService.capNhatTinhTrangSHK(soKS, tinhTrang);
			if(i){
					session.setAttribute("msg","Lưu cấu hình thành công");
				} else {
					session.setAttribute("error","Lưu cấu hình thất bại");
			}
			ModelAndView model = new ModelAndView();
			model.setViewName("Temp");
			return model;
		}
		
}