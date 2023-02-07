package camping.view;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CampSearch {
	public static String key = "OniG7a8aDrQJXgp4WhK09%2B8gUbrrjAKond%2F1zcK0OKXQk0LFXjr3FkGcJRhBgIMn5QrjHGQdnkbg8DiKY5d4TQ%3D%3D";
	public static String CAMPSITE_XML_URL = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/basedList";

	public void cmpSearch(int num) {
		System.out.println("선택한 번호 : " + num);
		System.out.print("검색어를 입력하세요 : ");

		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();

		publicPlace(str, num);
	}

	public static void publicPlace(String str, int num) {
		// url 담는 부분
		StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append(CAMPSITE_XML_URL);
		urlBuffer.append("?" + "ServiceKey=" + key); // 서비스키
		urlBuffer.append("&" + "pageNo=" + "1"); // 페이지번호
		urlBuffer.append("&" + "numOfRows=" + "200"); // 한 페이지 결과 수
		urlBuffer.append("&" + "MobileOS=" + "ETC"); // IOS(아이폰),AND(안드로이드),WIN(윈도우폰),ETC
		urlBuffer.append("&" + "MobileApp=" + "AppTest"); // 서비스명=어플명
		urlBuffer.append("&" + "radius=" + "5000"); // 설정값이 높으면 데이터 확인이 오래걸림 (100000정도가 적당)

		// 선택한 메뉴 번호에 따른 검색조건 변경
		String menuStr = "";
		if(num == 1) {
			menuStr = "facltNm";
		}else if(num == 2) {
			menuStr = "addr1";
		}else if(num == 3) {
			menuStr = "induty";
		}

		try {
			URL url = new URL(urlBuffer.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");
			int code = conn.getResponseCode();

			if (code < 200 || code > 300) {
				System.out.println("페이지가 잘못되었습니다.");
				return;
			}

			// document 빌드 팩토리에 변서 담음
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(conn.getInputStream());

			doc.getDocumentElement().normalize();

			// 노드리스트에 item에 해당하는 string 담음
			NodeList nList = doc.getElementsByTagName("item");

			int count = 1;
			int nCount = 0; // 검색된 node에 따른 번호 부여
			List<String> test1 = new ArrayList<>();

			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					// 검색어에 임력한 string이 포함되는 element를 찾음
					if(itemValidation(menuStr, eElement, str) == 1) {
						System.out.println(nCount + ". " + getItem("facltNm", eElement));

						test1.add(nCount,getItem("facltNm", eElement));

						nCount ++;
					}
					else if(itemValidation("facltNm", eElement, str) == 200) {
						System.out.println("에러남");
					}
				}
			}
			// System.out.println("\n메인메뉴로 돌아가기 : ");

			Scanner sc = new Scanner(System.in);

			System.out.println("-------상세정보보기-------");
			System.out.print("상세정보를 보고싶은 캠핑장 번호를 선택해주세요 : ");
			int menuNum2 = sc.nextInt();
			String campStr = test1.get(menuNum2); 

			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					// 검색어에 임력한 string이 포함되는 element를 찾음
					if(itemValidation("facltNm", eElement, campStr) == 1) {
						System.out.println("캠핑장명 : " + getItem("facltNm", eElement));
						System.out.println("주소 : " + getItem("addr1", eElement));
						System.out.println("상세 주소 : " + getItem("addr2", eElement));
						System.out.println("업종 : " + getItem("induty", eElement));
						System.out.println("전화번호 : " + getItem("tel", eElement));
						System.out.println("운영기간 : " + getItem("operPdCl", eElement));
						System.out.println("대표이미지 : " + getItem("firstimageUrl", eElement));
						System.out.println("캠핑장비 대여 : " + getItem("eqpmnLendCl", eElement));
						System.out.println("애완동물 출입정보 : " + getItem("animalCmgCl", eElement));
						System.out.println("부대시설 : " + getItem("sbrsCl", eElement));
						System.out.println("캠핑장테마 : " + getItem("themaEnvrnCl", eElement));
						System.out.println("주변이용가능시설 : " + getItem("posblFcltyCl", eElement));

					}else if(itemValidation("facltNm", eElement, campStr) == 200) {
						System.out.println("에러남");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int itemValidation(String itemName, Element eElement, String str) {
		try {
			int count = 0;
			//         String aa = eElement.getElementsByTagName(itemName).item(0).getTextContent();
			if(eElement.getElementsByTagName(itemName).item(0).getTextContent().contains(str)) {
				count ++;
			}
			return count;
		} catch (Exception e) {
			return 200;
		}
	}

	private static String getItem(String itemName, Element eElement) {
		try {
			String value = eElement.getElementsByTagName(itemName).item(0).getTextContent();
			return value;
		} catch (Exception e) {
			return "-";
		}
	}
}