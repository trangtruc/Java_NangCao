package bean.Chung;

/**
 * 
 * @author TienHX
 * @version 1.0 May 5, 2016
 */
public class Mail {
	
	/**.
	 * constructor
	 */
	public Mail() {
		super();
	}
	private String emailGui;
	private String matkhau;
	private String emailNhan;
	private String tieuDe;
	private String noiDung;
	
	public String getEmailNhan() {
		return emailNhan;
	}
	public void setEmailNhan(String emailNhan) {
		this.emailNhan = emailNhan;
	}
	public String getTieuDe() {
		return tieuDe;
	}
	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	/**
	 * @return the emailGui
	 */
	public String getEmailGui() {
		return emailGui;
	}
	/**
	 * @param emailGui the emailGui to set
	 */
	public void setEmailGui(String emailGui) {
		this.emailGui = emailGui;
	}
	/**
	 * @return the matkhau
	 */
	public String getMatkhau() {
		return matkhau;
	}
	/**
	 * @param matkhau the matkhau to set
	 */
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
}
