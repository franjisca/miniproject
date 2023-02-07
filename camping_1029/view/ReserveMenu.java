package camping.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import camping.reserve.controller.ReserveController;
import camping.reserve.model.vo.Reserve;

public class ReserveMenu {
	private ReserveController reserveController = new ReserveController();
	private Scanner sc = new Scanner(System.in);

	public void mainMenu() {
		String menu = "================ 예약 ================\n"
				+ "1. 예약하기\n"
				+ "2. 예약 취소\n" 
				+ "3. 예약번호로 예약 조회\n" 
				+ "4. 회원코드로 예약 조회\n" 
				+ "0. 이전메뉴로 돌아가기\n"
				+ "----------------------------------\n"
				+ "선택 : "; 

		while (true) {
			System.out.print(menu);
			int choice = sc.nextInt();
			Reserve reserve = null;
			int reserveNo = 0;
			int userNo = 0;
			int result = 0;
			String msg = null;
			List<Reserve> list = null;

			switch (choice) {
			case 1:
				// 1. 새로운 예약 입력 -> reserve 객체
				reserve = inputReserve();
				System.out.println(">>> 새로운 예약 확인 : " + reserve);
				// 2.controller에 회원가입 요청(메소드호출) -> int리턴(처리된 행의 개수)
				if(reserve == null) {
					displayMsg("데이터가 잘못되었습니다..");
					break;
				}
				result = reserveController.insertReserve(reserve);
				// 3.int에 따른 분기처리
				msg = result > 0 ? "예약 성공!" : "예약 실패!";
				displayMsg(msg);
				break;
			case 2:
				// 예약 취소
				reserveNo = inputreserveNo();
				result = reserveController.deleteReserve(reserveNo);
				msg = result > 0 ? "예약 취소 성공!" : "예약 취소 실패!";
				displayMsg(msg);
				break;
			case 3:
				reserveNo = inputreserveNo();
				list = reserveController.selectByReserveNo(reserveNo);
				displayReserveList(list);
				break;
			case 4:
				userNo = inputUserNo();
				list = reserveController.selectByUserNo(userNo);
				displayReserveList(list);
				break;
			case 0:
				System.out.println("이전메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

	private int inputreserveNo() {
		System.out.print("예약번호 입력 : ");
		return sc.nextInt();
	}

	private void displayReserveList(List<Reserve> list) {
		if(list == null || list.isEmpty()) {
			System.out.println(">>>> 조회된 예약이 없습니다.");	
		}
		else {
			System.out.println("*********************************************************");
			for(Reserve r : list) {
				System.out.println(r);
			}
			System.out.println("*********************************************************");
		}
	}

	private int inputUserNo() {
		System.out.print("회원번호 입력 : ");
		return sc.nextInt();
	}

	private void displayMsg(String msg) {
		System.out.println(">>> 처리결과 : " + msg);
	}

	private Reserve inputReserve() {
		System.out.println("예약정보를 입력하세요.");
		Reserve reserve = new Reserve();

		System.out.println("----------------------");
		System.out.println("예약자 정보를 입력하세요.");
		System.out.println("회원 번호 : ");
		reserve.setUserNo(sc.nextInt());
		System.out.println("캠핑장명을 입력하세요.");
		reserve.setCampNo(sc.nextInt());
		System.out.println("인원수를 입력하세요.");
		reserve.setNumOfPeople(sc.nextInt());
		System.out.println("예약 일자를 입력하세요. (yyyy-MM-dd)");
		String dateStr = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(dateStr);
			reserve.setReservationDate(new java.sql.Date(date.getTime()));
		} catch (ParseException e) {
			System.out.println("날짜를 잘못 입력하였습니다.");
			return null;
		}
		return reserve;
	}
}
