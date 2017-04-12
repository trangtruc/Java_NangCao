package com.mkyong.services.function;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.dao.ConfigNgayNghiDao;
import com.mkyong.services.QuyenService;

import Constant.Const;
import bean.Config.ConfigNgayNghi;
import bean.Chung.Huyen;
import bean.Chung.MD5;
import bean.Chung.Mail;
import bean.Chung.Quyen;
import bean.Chung.TaiKhoan;
import bean.Chung.Tinh;
import bean.Chung.Xa;
import bean.Vu.CCCD;
import bean.Vu.TTDKCCCD;

public class FunctionServiceImpl implements FunctionService {
	
	@Autowired(required=true)
	private ConfigNgayNghiDao ngayNghiDao;
	@Autowired
	private QuyenService quyenService;
	
	public HttpSession setSession(HttpSession session, TaiKhoan taiKhoan){
			if(taiKhoan !=null){
				session.setAttribute("ssSoDienThoai", taiKhoan.getSoDienThoai());
				session.setAttribute("ssTaiKhoan", taiKhoan.getUsername());
				session.setAttribute("ssHoTen", taiKhoan.getHoTen());
				if(taiKhoan.getUsername().equals("admin")){
					session.setAttribute("ssHoTen", "Administrator");
				}
				session.setAttribute("ssEmail", taiKhoan.getEmail());
				session.setAttribute("ssTrangThai", taiKhoan.getTrangThai());
				session.setAttribute("ssCoQuan", taiKhoan.getCoQuan());
				List<Quyen> quyen = quyenService.getQuyenTaiKhoan(taiKhoan.getUsername());
				session.setAttribute("ssQuyen", quyen);
				session.setAttribute("ssSoQuyen", quyen.size());
			}
		return session;
	}
	public String getNgayHienTai() {
		
		Calendar calendar = GregorianCalendar.getInstance();
		String thangCap = ""+(calendar.get(Calendar.MONTH)+1);
		String ngayCap = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		//Tin them cho nay
		if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
			ngayCap = "0" + ngayCap;
		}
		if((calendar.get(Calendar.MONTH)+1) < 10){
			thangCap = "0"+(calendar.get(Calendar.MONTH)+1);
		}
		String ngay = ngayCap + "-" + thangCap + "-" + calendar.get(Calendar.YEAR);
		return ngay;
	}
	public long getMaXacNhan(){
		Random random = new Random();
		long range = 10000- 1000 + 1;
		long fraction = (long) (range * random.nextDouble());
		long maXacNhan = fraction + 1000;
		return maXacNhan;
	}
	
	public Calendar truThu7ChuNhat(Calendar ngay){
		Calendar calendar = ngay;
		if(calendar.get(Calendar.DAY_OF_WEEK) == 7){
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+2);
		}	
		if(calendar.get(Calendar.DAY_OF_WEEK) == 1){
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
		}
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.MONTH));
		return calendar;
	}
	
	//Kiem tra xem no co phai la Thu bay voi Chu nhat hay khong
	public Boolean ktT7CN(Calendar ngay) {
		if( (ngay.get(Calendar.DAY_OF_WEEK) == 7) || (ngay.get(Calendar.DAY_OF_WEEK) == 1) ) {
			return true;
		}
		return false;
	}
	/*Kiem tra xem ngay dau vao co phai la ngay nghi hay khong (Tin viet)
	 */
	public Boolean ktNgayNghi(Calendar ngay) {
		ConfigNgayNghi ngayNghi;
		if (ktT7CN(ngay)) {
			return true;
		} else {
			//kiem tra trong csdl
			ngayNghi = new ConfigNgayNghi();
			ngayNghi.setNgay(ngay.get(Calendar.DAY_OF_MONTH));
			ngayNghi.setThang(ngay.get(Calendar.MONTH) + 1);
			if(ngayNghiDao.ktNgayNghi(ngayNghi)) {
				return true;
			}
		}
		return false;
	}

	public String getNgayHen(int sz, int soDonTrongNgay, String ngayTruoc){
		String ngaySau = "";
		if(sz >= soDonTrongNgay){
			Date d = new Date();
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(d);
			String[] data =  ngayTruoc.split("-");
			if(calendar.get(Calendar.DAY_OF_MONTH) <= Integer.parseInt(data[0])
			  && calendar.get(Calendar.MONTH) <= (Integer.parseInt(data[1])-1)
			  && calendar.get(Calendar.YEAR) <= Integer.parseInt(data[2])){
				calendar.set(Calendar.DAY_OF_MONTH, (Integer.parseInt(data[0])+1));
				calendar.set(Calendar.MONTH, Integer.parseInt(data[1])-1);
				calendar.set(Calendar.YEAR, Integer.parseInt(data[2]));
				calendar = truThu7ChuNhat(calendar);
				System.out.println(sz);
			} else {
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
				calendar = truThu7ChuNhat(calendar);
			}
			ngaySau = calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
			} else  {
				Date d = new Date();
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(d);
				String[] data =  ngayTruoc.split("-");
				if(calendar.get(Calendar.DAY_OF_MONTH) <= Integer.parseInt(data[0])
				  && calendar.get(Calendar.MONTH) <= (Integer.parseInt(data[1])-1)
				  && calendar.get(Calendar.YEAR) <= Integer.parseInt(data[2])){
					calendar.set(Calendar.DAY_OF_MONTH, (Integer.parseInt(data[0])));
					calendar.set(Calendar.MONTH, Integer.parseInt(data[1])-1);
					calendar.set(Calendar.YEAR, Integer.parseInt(data[2]));
				} else {
					calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
					calendar = truThu7ChuNhat(calendar);
				}
				ngaySau = calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
			}
		return ngaySau;
	}
		public InputStream getPathImage(HttpSession session, HttpServletRequest request, String pathFile){
			File f = new File("upload");
			String fpath = f.getAbsolutePath()+"/";
			File f2 = new File(fpath);
			if(!f2.exists()){
				f2.mkdirs();
			}
			BufferedInputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(fpath+pathFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				inputStream = null;
			}
			return inputStream;
		}
	
	public String taoPassword(){
		Const c = new Const();
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder( c.getLengthPassword() );
	       for( int i = 0; i < c.getLengthPassword(); i++ ){ 
	          sb.append( c.getKeyPassword().charAt( rnd.nextInt(c.getKeyPassword().length()) ) );
	       }
	    return sb.toString();
	}
	
	public Boolean sendMail(Mail mail) {
		Constant.Const const1 = new Constant.Const();
		Properties props = new Properties();
		props.put(const1.getSMTP_AUTH(), const1.getAUTH());
		props.put(const1.getSMTP_TLS_ENABLE(), const1.getTLS_ENABLE());
		props.put(const1.getSMTP_HOST(), const1.getHOST());
		props.put(const1.getSMTP_PORT(), const1.getPORT());

        Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Const.EMAIL_GUI, Const.MK_EMAIL_GUI);
          }
        });
        
        try {
            //String body="Dear Renish Khunt Welcome";
            //String htmlBody = "<strong>This is an HTML Message</strong>";
            //String textBody = "This is a Text Message.";
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(Const.EMAIL_GUI));
            message.setRecipients(Message.RecipientType.TO, 
            						InternetAddress.parse(mail.getEmailNhan()));
	        message.setSubject(mail.getTieuDe());
	        MailcapCommandMap mc = 
	        		(MailcapCommandMap) CommandMap.getDefaultCommandMap();
	        mc.addMailcap("text/html;; "
	        		+ "x-java-content-handler = "
	        		+ "com.sun.mail.handlers.text_html");
	        mc.addMailcap("text/xml;; "
	        		+ "x-java-content-handler = "
	        		+ "com.sun.mail.handlers.text_xml");
	        mc.addMailcap("text/plain;; "
	        		+ "x-java-content-handler = "
	        		+ "com.sun.mail.handlers.text_plain");
	        mc.addMailcap("multipart/*;; "
	        		+ "x-java-content-handler = "
	        		+ "com.sun.mail.handlers.multipart_mixed");
	        mc.addMailcap("message/rfc822;; "
	        		+ "x-java-content-handler = "
	        		+ "com.sun.mail.handlers.message_rfc822");
	        CommandMap.setDefaultCommandMap(mc);
	        //message.setText(htmlBody);
	        message.setContent(mail.getNoiDung(), "text/html");
	        Transport.send(message);
	        System.out.println("Send Done");
	        return true;
	      
        } catch (MessagingException e) {
        	e.printStackTrace();
        }

		return false;
	}
	
	//Dinh dang lai ngay thanh ngay-thang-nam,
	public String dinhDangNgay(String namThangNgay) {
		String ngay = "";
		//Dinh dang lai ngay cap
		try {
			String[] ngayThangNam = namThangNgay.split("-");
			ngay =  ngayThangNam[2] + "-" + ngayThangNam[1] + "-" + ngayThangNam[0];
		} catch(ArrayIndexOutOfBoundsException e) {
			
		}
		return ngay;
	}
	public Boolean checkEmpty(String input) {
		return (input == null || "".equals(input) || "000000000000".equals(input));
	}
	public String getSelectTinh(String maTinh, List<Tinh> dsTinh){
		String tinh = "";
		for(int i = 0; i < dsTinh.size(); i++){
			if(maTinh.equals(dsTinh.get(i).getMaTinh())){
				tinh += "<option value='"+dsTinh.get(i).getMaTinh()+"' selected>";
			} else {
				tinh += "<option value='"+dsTinh.get(i).getMaTinh()+"'>";
			}
			tinh += dsTinh.get(i).getTenTinh()
				 + "</option>";
		}
		return tinh;
	}
	public String getSelectHuyen(String maHuyen, List<Huyen> dsHuyen){
		String huyen = "";
		for(int i = 0; i < dsHuyen.size(); i++){
			if(maHuyen.equals(dsHuyen.get(i).getMaHuyen())){
				huyen += "<option value='"+dsHuyen.get(i).getMaHuyen()+"' selected>";
			} else {
				huyen += "<option value='"+dsHuyen.get(i).getMaHuyen()+"'>";
			}
			huyen += dsHuyen.get(i).getTenHuyen()
				 + "</option>";
		}
		return huyen;
	}
	public String getSelectXa(String maXa, List<Xa> dsXa){
		String xa = "";
		for(int i = 0; i < dsXa.size(); i++){
			if(maXa.equals(dsXa.get(i).getMaXa())){
				xa += "<option value='"+dsXa.get(i).getMaXa()+"' selected>";
			} else {
				xa += "<option value='"+dsXa.get(i).getMaXa()+"'>";
			}
			xa += dsXa.get(i).getTenXa()
				 + "</option>";
		}
		return xa;
	}
	public String getScriptHinhTronThongKe(String data, String element){
		String script = "";
		script = "new Morris.Donut({element: '"+element+"',"
	        + data
	       +",formatter: function (x) { return x + '%'}"
	     +"}).on('click', function(i, row){"
	     +"console.log(i, row);});";
		return script;
	}
	@Override
	public String getScriptHinhCotThongKe(String data, String element) {
		String script = "";
		script = "Morris.Bar({"
	  +"element: '"+element+"',"
	  +data+","
	  +"xkey: 'x',"
	  +"ykeys: ['y', 'z', 'a'],"
	  +"labels: ['Y', 'Z', 'A'],"
	  +"stacked: true"
	+"});";
		return script;
	}
	@Override
	public String getScriptHinhDuongThongKe(String data, String element, String title, int maximum) {
		String script = "var chart = new CanvasJS.Chart('"+element+"', {"
						+"zoomEnabled: false,"
						+"animationEnabled: true,"
						+"title: {"
						+"text: '"+title+"'},"
						+"axisY2: {"
						+"valueFormatString: '0',"
						+"maximum: "+maximum+","
						+"interval: 0,"
						+"interlacedColor: '#F5F5F5',"
						+"gridColor: '#D7D7D7',"
						+"tickColor: '#D7D7D7'},"
						+"theme: 'theme2',"
						+"toolTip: {"
						+"shared: true},"
						+"legend: {"
						+"verticalAlign: 'bottom',"
						+"horizontalAlign: 'center',"
						+"fontSize: 14,"
						+"fontFamily: 'Arial'},"
							+"data: ["+data
							+"],"
							+"legend: {"
							+"	cursor: 'pointer',"
							+"	itemclick: function (e) {"
							+"		if (typeof (e.dataSeries.visible) === 'undefined' || e.dataSeries.visible) {"
							+"			e.dataSeries.visible = false;}"
							+"		else {"
							+"			e.dataSeries.visible = true;}"
							+"		chart.render();}"
							+"}"
						+"});"
						+"chart.render();";
		return script;
	}
	@Override
	public String taoMaDuyetTTDKCCCD(TTDKCCCD cccd, String matKhau) {
		MD5 md5 = new MD5();
		String noiDung = cccd.getMaSo()+cccd.getSoKhaiSinh()
				+cccd.getMaYeuCau()+cccd.getNhanDang()
				+cccd.getSoCC()
				+cccd.getNgheNghiep()
				+cccd.getTonGiao()
				+cccd.getNoiOHienTai()+cccd.getTrinhDo()
				+cccd.getNoiDKLamViec()
				+cccd.getNgayDK()+cccd.getNgayHen()
				+cccd.getNguoiKiemTra()+cccd.getLyDo()
				+matKhau;
		return md5.encryptMD5(noiDung);
	}
	@Override
	public String taoMaDuyetCCCD(CCCD cccd, String matKhau) {
		MD5 md5 = new MD5();
		if(cccd.getHoTenKhac() == null){
			cccd.setHoTenKhac("");
		}
		String noiDung = cccd.getSoCC()+cccd.getNhanDang()
				+cccd.getHoTenKhac()
				+cccd.getNgheNghiep()
				+cccd.getTonGiao()
				+cccd.getNhomMau()
				+cccd.getNoiOHienTai()+cccd.getTrinhDo();
		return md5.encryptMD5(noiDung);
	}
	@Override
	public String taoMaNhapCCCD(CCCD cccd) {
		MD5 md5 = new MD5();
		if(cccd.getHoTenKhac() == null){
			cccd.setHoTenKhac("");
		}
		String noiDung = cccd.getSoCC()+cccd.getNhanDang()
				+cccd.getHoTenKhac()
				+cccd.getNgheNghiep()
				+cccd.getTonGiao()
				+cccd.getNhomMau()
				+cccd.getNoiOHienTai()+cccd.getTrinhDo();
		return md5.encryptMD5(noiDung);
	}
	@Override
	public String docMotSo(int so) {
		final String MOT = "MỘT";
		final String HAI = "HAI";
		final String BA = "BA";
		final String BON = "BỐN";
		final String NAM = "NĂM";
		final String SAU = "SÁU";
		final String BAY = "BẢY";
		final String TAM = "TÁM";
		final String CHIN = "CHÍN";
		String [] number = {MOT, HAI, BA, BON, NAM, SAU, BAY, TAM, CHIN};
		return number[so - 1];
	}
	@Override
	public String docHaiSo(int so) {
		final String LAM = "LĂM";
		final String MUOI = "MƯƠI";
		final String MUOIF = "MƯỜI";
		final String MOTS = "MỐT";
		String number = MUOIF + " ";
		//Lay so dau
		int soDau = so/10;
		//Lay so cuoi
		int soSau = so%10;
		if (soDau == 1) {
			if (soSau > 0) {
				if (soSau == 5) {
					number += LAM;
				} else {
					number += docMotSo(soSau);
				}
			}
		} else if (soDau > 1) {
			number = docMotSo(soDau) + " " + MUOI + " ";
			if (soSau > 0) {
				if (soSau == 5){
					number += LAM;
				} else if (soSau == 1){
					number += MOTS;
				} else {
					number += docMotSo(soSau);
				}
			}
		}
		return number;
	}
	@Override
	public String docNam(int so) {
		String strSo = String.valueOf(so);
		List<String> daySo = new ArrayList<String>();
		int i = 1;
		int lenSo = strSo.length();
		final String NGHIN = " NGHÌN ";
		String docNam = "NĂM ";
		while (i <= lenSo) {
			if (i == 1) {
				daySo.add(strSo.substring(0, i));
			} else {
				daySo.add(strSo.substring(i - 1, i));
			}
			i++;
		}
		if (daySo.size() == 4) {
			docNam += docMotSo(Integer.valueOf(daySo.get(0))) + NGHIN;
			if (Integer.valueOf(daySo.get(1)) == 0) {
				docNam += "KHÔNG TRĂM ";
				if (Integer.valueOf(daySo.get(2)) == 0) {
					System.out.println("So cuoi: " + Integer.valueOf(daySo.get(3)));
					if (Integer.valueOf(daySo.get(3)) > 0) {
						docNam += "LINH ";
						docNam += docMotSo(Integer.valueOf(daySo.get(3)));
					}
				} else {
					docNam += docHaiSo(Integer.valueOf(daySo.get(2) + daySo.get(3)));
				}
			} else {
				docNam += docMotSo(Integer.valueOf(daySo.get(1)));
				docNam += " TRĂM ";
				if (Integer.valueOf(daySo.get(2)) == 0) {
					System.out.println("So cuoi: " + Integer.valueOf(daySo.get(3)));
					if (Integer.valueOf(daySo.get(3)) > 0) {
						docNam += "LINH ";
						docNam += docMotSo(Integer.valueOf(daySo.get(3)));
					}
				} else {
					docNam += docHaiSo(Integer.valueOf(daySo.get(2) + daySo.get(3)));
				}
			}
		}
		return docNam;
	}
	@Override
	public String docNgay(int ngay) {
		String strNgay = "NGÀY ";
		if (ngay < 10) {
			strNgay += docMotSo(ngay);
		} else {
			strNgay += docHaiSo(ngay);
		}
		return strNgay;
	}
	@Override
	public String docThang(int thang) {
		String strThang = "THÁNG ";
		if (thang < 10) {
			strThang += docMotSo(thang);
		} else {
			strThang += docHaiSo(thang);
		}
		return strThang;
	}
	@Override
	public String docNgayThangNam(String ngayThangNam) {
		String[] ngay = ngayThangNam.split("-");
		String docNgayThangNam = "";
		docNgayThangNam = docNgay(Integer.valueOf(ngay[0])) + ", ";
		docNgayThangNam += docThang(Integer.valueOf(ngay[1])) + ", ";
		docNgayThangNam += docNam(Integer.valueOf(ngay[2]));
		return docNgayThangNam;
	}
	
}
