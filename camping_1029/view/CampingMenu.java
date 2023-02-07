package camping.view;

import java.util.Scanner;

import camping.campingsite.model.vo.CampingSiteAPI;
import camping.facilityinfo.model.vo.FacilityInfoAPI;
import camping.facilitypublic.model.vo.FacilityPublicAPI;

public class CampingMenu {
	private Scanner sc = new Scanner(System.in);
	FacilityInfoAPI fia = new FacilityInfoAPI();
	CampingSiteAPI ca = new CampingSiteAPI();
	FacilityPublicAPI fpa = new FacilityPublicAPI();
	
	ReserveMenu rsm = new ReserveMenu();
	LoginMenu lm = new LoginMenu();
	ReviewMenu rvm = new ReviewMenu();

	public void mainMenu() {
//		ca.callcampsiteByXML();
//		fia.callcampsiteByXML();
//		fpa.callFacilityPublicByXML();

		while(true) {
			String menu = "========== 캠핑장 예약 어플 ==========\n"
					+ "1. 캠핑장 검색\n"
					+ "2. 캠핑장 예약\n" 
					+ "3. 로그인\n"
					+ "4. 캠핑장 리뷰/찜 확인\n"
					+ "9. 종료\n"
					+ "----------------------------------\n"
					+ "선택 : ";
			System.out.print(menu);
			int menuNum = sc.nextInt();

			switch(menuNum) {
			case 1:
				selectCamping();
				break;
			case 2:
				// 이거 왜 계속 생성함? 멤버 변수로 빼서 재사용하세요 모두
				rsm.mainMenu();
				break;
			case 3:
				try {
					lm.mainMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 4:
				rvm.mainMenu();
				break;
			case 9:
				System.out.print("캠핑장 예약 어플을 종료하시겠습니까?(y/n) : ");
				if (sc.next().charAt(0) == 'y') {
					System.out.println("프로그램을 종료합니다.");
					exit();
					return;
				}
				break;
			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			}
		}
	}

	private void exit() {
		exit();
	}

	//캠핑장 검색 기능
	public void selectCamping() {
		System.out.println("=======캠핑장 옵션별 검색=======");
		System.out.println("1. 상호명검색");
		System.out.println("2. 지역별검색");
		System.out.println("3. 야영장별 검색");
		System.out.print("번호를 입력하세요 : ");
		// 넵...
		int menuNum = sc.nextInt();
		System.out.println("==============================================================================");
		publicCamping(menuNum);
	}

	public static void publicCamping(int num) {
		CampSearch cp = new CampSearch();
		cp.cmpSearch(num);
	}
}