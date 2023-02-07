package miniproject.camping.view;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import miniproject.camping.user.controller.UserController;
import miniproject.camping.user.model.dao.UserDAO;
import miniproject.camping.user.model.vo.User;

public class LoginMenu {
	Scanner sc = new Scanner(System.in);
	UserController uc = new UserController();

public void mainMenu(){
		System.out.println("=======로그인=======");
		System.out.println("1. 로그인");
		System.out.println("2. 회원정보 변경");
		System.out.println("3. 회원가입");
		System.out.println("4. 탈퇴하기");
		System.out.println("5. 종료하기");
		System.out.print("숫자를 입력하세요: ");
		int select = Integer.parseInt(sc.nextLine());
	switch(select) {
	case 1:
		logIn();
		return;
	case 2:
		changeInfo();
		break;
	case 3:
		signUp();
		break;
	case 4:
		deleteUser();
		break;
	case 5:
		System.out.println("프로그램을 종료합니다.");
		break;
	default:
		System.out.println("1, 2, 3, 4만 입력해주세요.");
		mainMenu();
	}
}

public void logIn(){			
	System.out.println("======로그인하기======");
	while(true) {
	System.out.print("아이디: ");
	String log_id = sc.nextLine();
	System.out.print("비밀번호: ");
	String log_pw = sc.nextLine();
	uc.logIn(log_id, log_pw);
	
	if(uc.logIn(log_id, log_pw) >0 ) {
	System.out.println("로그인 성공!");
	uc.one_User(log_id);
	mainMenu();
	} else {
	System.out.println("다시 입력해주세요.");
	continue;
	}
	}
}
//----------------------------------------------------
public void changeInfo(){
	System.out.println("회원정보를 변경하시겠습니까?(y/n):");
	char upInfo = sc.nextLine().charAt(0);
		
	switch(upInfo) {
	case 'y':
	case 'Y':
		System.out.println("아이디는 변경할 수 없습니다. 가입시 입력한 아이디를 입력바랍니다.");
		System.out.print("아이디: ");
		String up_id = sc.nextLine();
		System.out.print("비밀번호: ");
		String up_pw = sc.nextLine();
		System.out.print("이름: ");
		String up_name = sc.nextLine();
		System.out.print("이메일: ");
		String up_email = sc.nextLine();
		System.out.print("핸드폰 번호: ");
		String up_phone = sc.nextLine();
		System.out.print("생년월일(ex:2021-01-01): ");	
		String up_birth = sc.nextLine();
		java.sql.Date up_birthd = java.sql.Date.valueOf(up_birth);
		System.out.println("아래의 이전 정보는 폐기됩니다.");
					
		int up_no = (uc.one_User(up_id)).getUser_No();
		User upUser = new User(up_no, up_id, up_pw, up_name, up_email, up_phone, up_birthd);
		uc.updateUser(upUser);	
		mainMenu();
		
	case 'n':
	case 'N':
		System.out.println("메뉴로 돌아갑니다.");
		mainMenu();
	default:
		System.out.println("y와n만 입력해주세요.");
		mainMenu();
	}
}
//-----------------------------------------------------------------
public void signUp() {
	System.out.println("회원가입을 하시겠습니까?(y/n):");
	char selectChar = sc.nextLine().charAt(0);
	
	switch(selectChar) {
	case 'y' :
	case 'Y' :
	while(true) {
	System.out.println("특수문자는 사용할 수 없습니다.");
	System.out.print("아이디: ");
	String sign_id = sc.nextLine();
			
	if(uc.signUp(sign_id)<0) {
		signUp();
	} else {
		System.out.println("사용가능한 아이디입니다. 계속입력해주세요.");
	}
	System.out.print("비밀번호(8글자 이상): ");
	String sign_pw = sc.nextLine();
	if(sign_pw.length() < 8) {
	System.out.println("비밀번호가 짧습니다. 다시 입력해주세요.");
	continue;
	} else {
	System.out.println("입력 되었습니다.");
	}
	System.out.println();
	System.out.print("이름: ");
	String name = sc.nextLine();
	System.out.print("이메일: ");
	String email = sc.nextLine();
	System.out.print("핸드폰 번호: ");
	String phone = sc.nextLine();
	System.out.print("생년월일(ex:2021-10-26): ");
	String birth = sc.nextLine();
	java.sql.Date birthd = java.sql.Date.valueOf(birth);

	User newUser = new User(sign_id,sign_pw, name, email, phone,birthd);
	uc.insertUser(newUser);
	break;
}
	case 'n':
	case 'N':
		mainMenu();
	}
}
//------------------------	
public void deleteUser(){
	//회원탈퇴 하기
	System.out.print("탈퇴할 아이디 입력: ");
	String deleteUser = sc.nextLine();
	System.out.println("삭제하려는 아이디는 " + deleteUser +"입니다. y를 누르면 삭제됩니다.");
	char delselect = sc.nextLine().charAt(0);
	if(delselect == 'y'|| delselect == 'Y') {
	try {
		uc.deleteUser(deleteUser);
		mainMenu();	
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
		} else {
		System.out.println(deleteUser + "삭제를 보류합니다.");
		mainMenu();
	}
}
}