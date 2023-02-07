package camping.view;

import java.util.List;
import java.util.Scanner;

import camping.review.controller.ReviewController;
import camping.review.model.vo.Review;

public class ReviewMenu {
	private ReviewController reviewController = new ReviewController();
	private Scanner sc = new Scanner(System.in);

	public void mainMenu() {
		String menu = "====================\n"
				+ "1. 별점, 코멘트 작성하기\n"
				+ "2. 리뷰 조회\n"
				+ "0. 이전메뉴로 돌아가기\n"
				+ "----------------------------------\n"
				+ "선택 : "; 

		while(true) {
			System.out.print(menu);
			int choice = sc.nextInt();
			int result = 0;
			Review camp = null;
			String msg = null;

			switch(choice) {
			case 1: 
				camp = review();
				System.out.println("별점 및 코멘트 확인 : " + camp);
				result = reviewController.review(camp);
				msg = result > 0 ? "리뷰 입력 성공!" : "리뷰 입력 실패!";
				displayMsg(msg);
				break;
			case 2:
				camp = review_search();
				break;
			case 0:
				System.out.println("이전메뉴로 돌아갑니다.");
				return;

			default: 
				System.out.println("잘못 입력하셨습니다.");
			}	
		}
	}

	private Review review() {
		Review camp = new Review();
		String comment_review = "";
		String star_score = "";
		int choice = 0;

		String menu_score = "1점부터 5점까지 선택해주세요.\n"
				+ "1.★\n"
				+ "2.★★\n"
				+ "3.★★★\n"
				+ "4.★★★★\n"
				+ "5.★★★★★\n";

		String menu_comment = "1번부터 8번 코멘트를 선택해주세요.\n"
				+ "1.별로에요\n"
				+ "2.그저그래요\n"
				+ "3.보통이에요\n"
				+ "4.좋아요\n"
				+ "5.깨끗하고 좋아요!\n"
				+ "6.사장님이 친절해요\n"
				+ "7.시설이 신식이에요\n"
				+ "8.분위기가 좋아요\n";

		while (true) {
			System.out.print(menu_score);
			choice = sc.nextInt();
			if (choice > 0 && choice < 6) {
				for (int i = 0; i < choice; i++) {
					star_score = star_score + "★";
				}
				break;

			} else {
				System.out.println("숫자 입력이 잘못되었습니다.");
				System.out.println("====================");
			}
		}
		camp.setStar_score(star_score);

		while(true) {
			System.out.print(menu_comment);
			choice = sc.nextInt();

			switch (choice) {
			case 1: comment_review = "1.별로에요";			break;
			case 2: comment_review = "2.그저그래요";		break;
			case 3: comment_review = "3.보통이에요";		break;
			case 4: comment_review = "4.좋아요";			break;
			case 5: comment_review = "5.깨끗하고 좋아요!";	break;
			case 6: comment_review = "6.사장님이 친절해요";	break;
			case 7:	comment_review = "7.시설이 신식이에요";	break;
			case 8:	comment_review = "8.분위기가 좋아요";	break;
			default : System.out.println("숫자 입력이 잘못되었습니다.\n"
					+ "====================");
			} 
			break;
		} 
		camp.setComment_review(comment_review);
		return camp;
	}

	private Review review_search() {
		Review review = new Review();
		List<Review> list = null;
		int choice = 0;

		String menu_sort = "정렬기준을 선택해주세요.\n"
				+ "1.최신순 정렬\n"
				+ "2.별점순 정렬\n";

		while(true) {
			System.out.print(menu_sort);
			choice = sc.nextInt();

			switch (choice) {
			case 1: 	
				list = reviewController.review_searchLatest();
				displayReviewList(list);
				break;
			case 2: 
				list = reviewController.review_searchStar();
				displayReviewList(list);
				break;
			default : System.out.println("숫자 입력이 잘못되었습니다.\n"
					+ "====================");
			} 
			break;
		} 
		return review;

	}
	/**
	 * DB에서 조회된 회원객체 n개를 출력
	 * @param list
	 */
	private void displayReviewList(List<Review> list) {
		if(list == null || list.isEmpty()) {
			System.out.println(">> 조회된 행이 없습니다.");	
		}
		else {
			System.out.println("*********************************************************");
			for(Review m : list) {
				System.out.println(m);
			}
			System.out.println("*********************************************************");
		}
	}
	/**
	 * DML처리결과 통보용 
	 * @param msg
	 */
	private void displayMsg(String msg) {
		System.out.println(">> 처리결과 : " + msg);
	}


}
