package camping.facilityinfo.controller;

import camping.campingsite.model.dao.CampingSiteDAO;
import camping.campingsite.model.vo.CampingSite;
import camping.facilityinfo.model.dao.FacilityInfoDAO;
import camping.facilityinfo.model.vo.FacilityInfo;
import camping.facilitypublic.model.dao.FacilityPublicDAO;
import camping.facilitypublic.model.vo.FacilityPublic;

public class FacilityInfoController {
	private CampingSiteDAO campingSiteDAO = new CampingSiteDAO();
	private FacilityInfoDAO facilityInfoDao = new FacilityInfoDAO();
	private FacilityPublicDAO facilityPublicDao = new FacilityPublicDAO();

	public FacilityInfo select(int num) {
		return facilityInfoDao.select(num);
	}

	public int insertInfo(FacilityInfo facilityInfo) {
		return facilityInfoDao.insert(facilityInfo);
	}

	public int insertCampingsite(CampingSite campingSite) {
		return campingSiteDAO.insertCampingsite(campingSite);
	}

	public int insertPublic(FacilityPublic facilityPublic) {
		return facilityPublicDao.insert(facilityPublic);
	}
	public int delete(int num) {
		return facilityInfoDao.delete(num);
	}
}
