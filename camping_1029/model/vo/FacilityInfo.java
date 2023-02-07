package camping.facilityinfo.model.vo;

public class FacilityInfo {
	private int infoNo;
	private int campNo;
	private String campingEqRent;
	private String petYN;
	private String amenities;
	private String campingTheme;

	public FacilityInfo() {
		super();
	}

	public FacilityInfo(int infoNo, int campNo, String campingEqRent, String petYN, String amenities, String campingTheme) {
		super();
		this.infoNo = infoNo;
		this.campNo = campNo;
		this.campingEqRent = campingEqRent;
		this.petYN = petYN;
		this.amenities = amenities;
		this.campingTheme = campingTheme;
	}

	public int getinfoNo() {
		return infoNo;
	}

	public void setinfoNo(int infoNo) {
		this.infoNo = infoNo;
	}

	public int getCampNo() {
		return campNo;
	}

	public void setCampNo(int campNo) {
		this.campNo = campNo;
	}

	public String getCampingEqRent() {
		return campingEqRent;
	}

	public void setCampingEqRent(String campingEqRent) {
		this.campingEqRent = campingEqRent;
	}

	public String getPetYN() {
		return petYN;
	}

	public void setPetYN(String petYN) {
		this.petYN = petYN;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getCampingTheme() {
		return campingTheme;
	}

	public void setCampingTheme(String campingTheme) {
		this.campingTheme = campingTheme;
	}

	@Override
	public String toString() {
		return campNo + "번 캠핑장의 시설정보 : \n - 캠핑장비대여 : " + campingEqRent + "\n - 애완동물 출입여부 : " + petYN
				+ "\n - 부대시설 : " + amenities + "\n - 캠핑장 테마 : " + campingTheme;
	}

}
