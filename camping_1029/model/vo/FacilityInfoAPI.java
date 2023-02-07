package camping.facilityinfo.model.vo;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import camping.facilityinfo.controller.FacilityInfoController;

public class FacilityInfoAPI {
	public static String key = "8eh5SVS%2FKHMaAJWwhnhrvZeGRSIB3x%2FiOen7uCnZt%2F%2BZWmI5Z7qCTBzX4VBcEi7AFovuhQK4wB1hxiEZop1llg%3D%3D";

	public static String CAMPSITE_XML_URL = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/locationBasedList";
	public static String WEEKLY_BOXOFFICE_JSON_URL = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json";

	private FacilityInfoController facilityInfoController = new FacilityInfoController();

	public FacilityInfo callcampsiteByXML() {
		StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append(CAMPSITE_XML_URL);
		urlBuffer.append("?" + "ServiceKey=" + key);
		urlBuffer.append("&" + "numOfRows=" + "1000");
		urlBuffer.append("&" + "MobileOS=" + "ETC");
		urlBuffer.append("&" + "MobileApp=" + "AppTest");
		urlBuffer.append("&" + "mapX=" + "127");
		urlBuffer.append("&" + "mapY=" + "37");
		urlBuffer.append("&" + "radius=" + "10000");	//설정값이 높으면 데이터 확인이 오래걸림 (100000정도가 적당)

		try {
			URL url = new URL(urlBuffer.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");
			int code = conn.getResponseCode();				

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(conn.getInputStream());
			doc.getDocumentElement().normalize();

			System.out.println("===================================================================");
			NodeList nList = doc.getElementsByTagName("item");
			int count = 1;
			FacilityInfo facilityInfo = new FacilityInfo();

			for(int i=0; i<nList.getLength(); i++) {
				Node node = nList.item(i);
				System.out.println("\nCurrent Element : "+ node.getNodeName());
				System.out.println(count + "번");
				count++;

				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					System.out.println(getItem("animalCmgCl", eElement));

					facilityInfo.setCampingEqRent(getItem("eqpmnLendCl", eElement));
					facilityInfo.setPetYN(getItem("animalCmgCl", eElement));
					facilityInfo.setAmenities(getItem("sbrsCl", eElement));
					facilityInfo.setCampingTheme(getItem("themaEnvrnCl", eElement));

					int result = 0;
					String msg = null;
					result = facilityInfoController.insertInfo(facilityInfo);
					msg = result > 0 ? "성공" : "실패";
					displayMsg(msg);
				}
			}
			if(code < 200 || code > 300) {
				System.out.println("페이지가 잘못되었습니다.");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void displayMsg(String msg) {
		System.out.println(">>> 처리결과 : " + msg);
	}

	public static String getItem(String itemName, Element eElement) {

		try {
			String value = eElement.getElementsByTagName(itemName).item(0).getTextContent();
			return value;
		} catch (Exception e) {
			return "-";
		}
	}
}
