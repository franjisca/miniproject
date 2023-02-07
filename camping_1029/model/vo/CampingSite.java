package camping.campingsite.model.vo;

public class CampingSite {
	public int camp_no;
	private String facltNm;
	private String address;
	private String address_detail;
	private String homepage;
	private String camping_kind;
	private String camp_tel;
	private String lineIntro;
	private String camp_imageUrl;
	private String operDay;

	public CampingSite(int camp_no, String facltNm, String address, String address_detail, String homepage,
			String camping_kind, String camp_tel, String lineIntro, String camp_imageUrl, String operDay) {
		super();
		this.camp_no = camp_no;
		this.facltNm = facltNm;
		this.address = address;
		this.address_detail = address_detail;
		this.homepage = homepage;
		this.camping_kind = camping_kind;
		this.camp_tel = camp_tel;
		this.lineIntro = lineIntro;
		this.camp_imageUrl = camp_imageUrl;
		this.operDay = operDay;
	}

	public CampingSite() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CampingSite [camp_no=" + camp_no + ", facltNm=" + facltNm + ", address=" + address + ", address_detail="
				+ address_detail + ", homepage=" + homepage + ", camping_kind=" + camping_kind + ", camp_tel="
				+ camp_tel + ", lineIntro=" + lineIntro + ", camp_imageUrl=" + camp_imageUrl + ", operDay=" + operDay
				+ "]";
	}

	public int getCamp_no() {
		return camp_no;
	}

	public void setCamp_no(int camp_no) {
		this.camp_no = camp_no;
	}

	public String getFacltNm() {
		return facltNm;
	}

	public void setFacltNm(String facltNm) {
		this.facltNm = facltNm;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getCamping_kind() {
		return camping_kind;
	}

	public void setCamping_kind(String camping_kind) {
		this.camping_kind = camping_kind;
	}

	public String getCamp_tel() {
		return camp_tel;
	}

	public void setCamp_tel(String camp_tel) {
		this.camp_tel = camp_tel;
	}

	public String getLineIntro() {
		return lineIntro;
	}

	public void setLineIntro(String lineIntro) {
		this.lineIntro = lineIntro;
	}

	public String getCamp_imageUrl() {
		return camp_imageUrl;
	}

	public void setCamp_imageUrl(String camp_imageUrl) {
		this.camp_imageUrl = camp_imageUrl;
	}

	public String getOperDay() {
		return operDay;
	}

	public void setOperDay(String operDay) {
		this.operDay = operDay;
	}

}
