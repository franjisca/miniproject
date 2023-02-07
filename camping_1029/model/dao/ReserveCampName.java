package camping.reserve.model.dao;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import camping.facilityinfo.model.vo.FacilityInfo;

public class ReserveCampName {
	public static String key = "8eh5SVS%2FKHMaAJWwhnhrvZeGRSIB3x%2FiOen7uCnZt%2F%2BZWmI5Z7qCTBzX4VBcEi7AFovuhQK4wB1hxiEZop1llg%3D%3D";

	public static String CAMPSITE_XML_URL = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/locationBasedList";
	public static String WEEKLY_BOXOFFICE_JSON_URL = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json";
	
//	public static void main(String[] args) {
//		callcampsiteByXML();
//		System.out.println("===================================================================");
//	}
	
	public String callcampsiteByXML() {
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
			int code = conn.getResponseCode();				//실제 호출하는 부분
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(conn.getInputStream());	//xml부를 파싱하여 객체화
			
			doc.getDocumentElement().normalize();
			
			System.out.println("===================================================================");
			NodeList nList = doc.getElementsByTagName("item");
			
			for(int i=0; i<nList.getLength(); i++) {
				Node node = nList.item(i);
				System.out.println("\nCurrent Element : "+ node.getNodeName());
				
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String str = getItem("facltNm", eElement);
					return str;
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
	
	public static String getItem(String itemName, Element eElement) {
		
		try {
			String value = eElement.getElementsByTagName(itemName).item(0).getTextContent();
			return value;
		} catch (Exception e) {
			return "-";
		}
	}
}
