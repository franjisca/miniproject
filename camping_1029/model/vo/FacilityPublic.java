package camping.facilitypublic.model.vo;

public class FacilityPublic {
	private int publicNo;
	private int campNo;
	private String toiletNo;
	private String swrmNo;
	private String wtrpNo;
	private String posblFcltyClNo;

	public FacilityPublic() {
		super();
	}

	public FacilityPublic(int publicNo, int campNo, String toiletNo, String swrmNo, String wtrpNo, String posblFcltyClNo) {
		super();
		this.publicNo = publicNo;
		this.campNo = campNo;
		this.toiletNo = toiletNo;
		this.swrmNo = swrmNo;
		this.wtrpNo = wtrpNo;
		this.posblFcltyClNo = posblFcltyClNo;
	}

	public int getpublicNo() {
		return publicNo;
	}

	public void setpublicNo(int publicNo) {
		this.publicNo = publicNo;
	}

	public int getCampNo() {
		return campNo;
	}

	public void setCampNo(int campNo) {
		this.campNo = campNo;
	}

	public String gettoiletNo() {
		return toiletNo;
	}

	public void settoiletNo(String toiletNo) {
		this.toiletNo = toiletNo;
	}

	public String getswrmNo() {
		return swrmNo;
	}

	public void setswrmNo(String swrmNo) {
		this.swrmNo = swrmNo;
	}

	public String getwtrpNo() {
		return wtrpNo;
	}

	public void setwtrpNo(String wtrpNo) {
		this.wtrpNo = wtrpNo;
	}

	public String getposblFcltyClNo() {
		return posblFcltyClNo;
	}

	public void setposblFcltyClNo(String posblFcltyClNo) {
		this.posblFcltyClNo = posblFcltyClNo;
	}

	@Override
	public String toString() {
		return publicNo + "번 공용시설번호 : campNo + \"번 캠핑장의 시설정보 : " +  "\n - 화장실의 수 : " + toiletNo +  "\n - 샤워실의 수 : "  
				+ swrmNo + "\n - 개수대의 수 : " + wtrpNo + "\n - 주변이용가능시설 : " + posblFcltyClNo;
	}

}
