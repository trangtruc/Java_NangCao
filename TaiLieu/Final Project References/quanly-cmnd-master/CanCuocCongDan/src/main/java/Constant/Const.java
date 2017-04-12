package Constant;

public class Const {
	
	public static final String EMAIL_GUI = "sa0kura01";
	public static final String MK_EMAIL_GUI = "@dmin1234@";
	
	private  Boolean AUTH = true;
	private  Boolean TLS_ENABLE = true;
	private  String HOST = "smtp.gmail.com";
	private  String PORT = "587";
	private  String SMTP_AUTH = "mail.smtp.auth";
	private  String SMTP_TLS_ENABLE = "mail.smtp.starttls.enable";
	private  String SMTP_HOST = "mail.smtp.host";
	private  String SMTP_PORT = "mail.smtp.port";
	public  static int tuoiKHNam = 20;
	public  static int tuoiKHNu = 19;
	private int lengthPassword = 12;
	private String keyPassword = "0123456789@*QWERTYUOPLKJHGFDSAZXCNMqwetilkjhgfdszxcvbnm";
	public static String linkHost = "http://localhost:8080/CanCuocCongDan/";
	public static final String linkXacNhan = "xac-nhan-dang-ky-ket-hon?maXacNhan=";
	public static final String DUYET_KHAI_SINH_1 = "DUYET_KHAI_SINH_1";
	public static final String DUYET_KHAI_SINH_2 = "DUYET_KHAI_SINH_2";
	public static final String DUYET_TACH_HO_KHAU = "DUYET_TACH_HO_KHAU";
	public static final String DUYET_THEM_NHAN_KHAU = "DUYET_THEM_NHAN_KHAU";
	public static final String DUYET_TTDK_CCCD = "DUYET_TTDK_CCCD";
	public static final String XAC_NHAN_TTDK_CCCD = "XAC_NHAN_TTDK_CCCD";
	public static final String XEM_DANH_SACH_TTDK_CCCD = "XEM_DANH_SACH_TTDK_CCCD";
	public static final String linkDanhSachDKKhaiSinh = "danh-sach-dk-khai-sinh";
	public static final String linkNhapKhaiSinh = "nhap-khai-sinh";
	public static final String linkDanhSachKhaiSinh = "danh-sach-khai-sinh";
	public static String loiQuyen = "Bạn không có quyền thực hiện thao tác này.";
	public static String loiDuLieu = "Đã xảy ra lỗi, hãy thử lại.";
	public static final String REDIRECT_DKKS = "redirect: dang-ky-khai-sinh";
	public static final String REDIRECT_CAP_NHAT_DKKS = "redirect:cap-nhat-dk-khai-sinh";
	public static final String REDIRECT_CT_DKKS = "redirect:chi-tiet-dk-khai-sinh";
	public static final String DAN_SO = "DAN_SO";
	public static final String TY_LE_PHAN_TRAM = "TY_LE_PHAN_TRAM";
	public static final String MA_TINH = "MA_TINH";
	public static final String TEN_TINH = "TEN_TINH";
	public static final String MA_HUYEN = "MA_HUYEN";
	public static final String TEN_HUYEN = "TEN_HUYEN";
	public static final String MA_XA = "MA_XA";
	public static final String TEN_XA = "TEN_XA";
	public static final String MA_VUNG = "MA_VUNG";
	public static final String TEN_VUNG = "TEN_VUNG";
	public static final String SO_DK = "SO_DK";
	public static final String HO_TEN = "HO_TEN";
	public static final String NGAY_SINH = "NGAY_SINH";
	public static final String GIOI_TINH = "GIOI_TINH";
	public static final String QUOC_TINH = "QUOC_TICH";
	public static final String MA_DT = "MA_DT";
	public static final String TEN_DT = "TEN_DT";
	public static final String BENH_VIEN = "BENH_VIEN";
	public static final String NOI_SINH = "NOI_SINH";
	public static final String QUE_QUAN = "QUE_QUAN";
	public static final String SO_CC_NGUOI_YEU_CAU = "SO_CC_NGUOI_YEU_CAU";
	public static final String QUAN_HE_VOI_NGUOI_KS = "QUAN_HE_VOI_NGUOI_KS";
	public static final String SO_CC_CHA = "SO_CC_CHA";
	public static final String SO_CC_ME = "SO_CC_ME";
	public static final String NGAY_DANG_KY = "NGAY_DANG_KY";
	public static final String NGAY_HEN_LAM_VIEC = "NGAY_HEN_LAM_VIEC";
	public static final String NOI_DKLV = "NOI_DKLV";
	public static final String SO_KS = "SO_KS";
	public static final String MA_YEU_CAU = "MA_YEU_CAU";
	public static final String TEN_YEU_CAU = "TEN_YEU_CAU";
	public static final String NOI_CAP = "NOI_CAP";
	public static final String DUYET_KET_HON_1 = "DUYET_KET_HON_1";
	public static final String DUYET_KET_HON_2 = "DUYET_KET_HON_2";
	public static final String REDIRECT_CAP_NHAT_DKKH = "redirect:cap-nhat-dk-ket-hon";
	public static final String REDIRECT_DSDKKH = "redirect:danh-sach-dk-ket-hon";
	public static final String VIEW_LOG_IN = "login";
	public static final int slDonTrongNgay = 200;
	public static final String giayToKhaiSinh = "CMND/Hộ chiếu, sổ hộ khẩu và giấy chứng sinh.";
	public static final String XEM_KHAI_SINH = "XEM_KHAI_SINH";
	public static final String XEM_HON_NHAN = "XEM_HON_NHAN";
	public static final String SO_KS_CU = "SO_KS_CU";
	public static final String TRANG_THAI = "TRANG_THAI";
	public static final String GHI_CHU = "GHI_CHU";
	public static final String REDIRECT_CT_DKKH = "redirect:chi-tiet-dkkh";
	
	public Const() {
		super();
	}
	
	public String getEMAIL_GUI() {
		return EMAIL_GUI;
	}
	public Boolean getAUTH() {
		return AUTH;
	}
	public void setAUTH(Boolean aUTH) {
		AUTH = aUTH;
	}
	public Boolean getTLS_ENABLE() {
		return TLS_ENABLE;
	}
	public void setTLS_ENABLE(Boolean tLS_ENABLE) {
		TLS_ENABLE = tLS_ENABLE;
	}
	public String getHOST() {
		return HOST;
	}
	public void setHOST(String hOST) {
		HOST = hOST;
	}
	public String getPORT() {
		return PORT;
	}
	public void setPORT(String pORT) {
		PORT = pORT;
	}
	public String getSMTP_AUTH() {
		return SMTP_AUTH;
	}
	public void setSMTP_AUTH(String sMTP_AUTH) {
		SMTP_AUTH = sMTP_AUTH;
	}
	public String getSMTP_TLS_ENABLE() {
		return SMTP_TLS_ENABLE;
	}
	public void setSMTP_TLS_ENABLE(String sMTP_TLS_ENABLE) {
		SMTP_TLS_ENABLE = sMTP_TLS_ENABLE;
	}
	public String getSMTP_HOST() {
		return SMTP_HOST;
	}
	public void setSMTP_HOST(String sMTP_HOST) {
		SMTP_HOST = sMTP_HOST;
	}
	public String getSMTP_PORT() {
		return SMTP_PORT;
	}	
	public void setSMTP_PORT(String sMTP_PORT) {
		SMTP_PORT = sMTP_PORT;
	}
	public int getTuoiKHNam() {
		return tuoiKHNam;
	}
	public int getTuoiKHNu() {
		return tuoiKHNu;
	}

	/**
	 * @return the lengthPassword
	 */
	public int getLengthPassword() {
		return lengthPassword;
	}

	/**
	 * @param lengthPassword the lengthPassword to set
	 */
	public void setLengthPassword(int lengthPassword) {
		this.lengthPassword = lengthPassword;
	}

	/**
	 * @return the keyPassword
	 */
	public String getKeyPassword() {
		return keyPassword;
	}

	/**
	 * @param keyPassword the keyPassword to set
	 */
	public void setKeyPassword(String keyPassword) {
		this.keyPassword = keyPassword;
	}

	/**
	 * @return the mK_EMAIL_GUI
	 */
	public String getMK_EMAIL_GUI() {
		return MK_EMAIL_GUI;
	}
	
}
