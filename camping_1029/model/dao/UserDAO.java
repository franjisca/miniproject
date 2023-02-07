package miniproject.camping.user.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import miniproject.camping.user.model.vo.User;
public class UserDAO {
	
String url = "jdbc:oracle:thin:@127.0.0.1:1521/XE";
String sql = null;
Connection connec = null;
Statement state = null;
PreparedStatement preState = null;
ResultSet resultS = null;
	
//출력메소드
public List<User> User_Info(){	
sql = "SELECT * FROM TBL_USER";
//배열생성
List<User> user_Info = new ArrayList<User>();
	try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	connec= DriverManager.getConnection(url, "CAMP", "CAMP");
	state = connec.createStatement();
	resultS = state.executeQuery(sql);
			
	while(resultS.next() == true) {
	int user_no = resultS.getInt("user_no");
	String user_id = resultS.getString("user_id");
	String user_pw = resultS.getString("user_pw");
	String user_name = resultS.getString("user_name");
	String user_email = resultS.getString("user_email");
	String user_phone = resultS.getString("user_phone");
	Date user_birthd = resultS.getDate("user_birth");
	User user_information = new User(user_no, user_id, user_name, user_email, user_phone, user_phone, (java.sql.Date) user_birthd);
	//배열에 넣어주기
	user_Info.add(user_information);
	//모든 회원정보 출력
	System.out.println("회원번호: " + user_no + ", 회원 ID: " + user_id + 
		", 회원이름: " + user_name + ", E-Mail: " 
		+ user_email + ", 번호:" + user_phone + ", 생년월일: " + user_birthd);
	}
	resultS.close();
	state.close();
	connec.close();	
		
	if(user_Info == null) {
	System.out.println("회원정보가 비어있습니다.");
	return null;
	}
	} catch (ClassNotFoundException | SQLException e) {
	e.printStackTrace();
	}		
	return user_Info;		
}
//한 사람만 조회
public User one_User(String id) {
	sql = "SELECT * FROM TBL_USER WHERE user_id =?";
	try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection connec = DriverManager.getConnection(url, "CAMP", "CAMP");
	PreparedStatement preState = connec.prepareStatement(sql);	
	preState.setString(1, id);
	ResultSet resultS = preState.executeQuery();

	resultS.next();
	if( resultS != null) {
	int no = resultS.getInt(1);
	String iden = resultS.getString(2);
	String pw = resultS.getString(3);
	String name = resultS.getString(4);
	String email = resultS.getString(5);
	String phone = resultS.getString(6);
	java.sql.Date birth = resultS.getDate(7);
	User one_user = new User(no, iden, pw, name, email, phone, birth);
			
	System.out.println("회원번호: " + no + ", 회원 ID: " + iden + 
		", 회원이름: " + name + ", E-Mail: " 
		+ email + ", 번호:" + phone + ", 생년월일: " + birth);
		return one_user;
		} else {
		System.out.println("일치하는 정보가 없습니다.");
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}
//insert
public int insertUser(User user) {
	String sql = "INSERT INTO TBL_USER VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, ?,?,?)";
	try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	connec = DriverManager.getConnection(url, "CAMP", "CAMP");
	preState = connec.prepareStatement(sql);
		
	preState.setString(1, user.getUser_ID());
	preState.setString(2, user.getUser_pw());
	preState.setString(3, user.getUser_Name());
	preState.setString(4, user.getUser_Email());
	preState.setString(5, user.getUser_Phone());
	preState.setDate(6, user.getUser_Birthd());
		
	int result = preState.executeUpdate();
	if(result > 0) {
	System.out.println("성공적으로 입력 되었습니다.");
	return 1;
	}
	preState.close();
	connec.close();
	} catch (ClassNotFoundException e) {
	e.printStackTrace();
	} catch (SQLException e) {
	e.printStackTrace();
	} 
	return -1;
}
//update메소드 - 객체를 받아서 업데이트 하기
public int update(User user_information) {
	sql = "UPDATE TBL_USER SET user_no =?, user_pw =?, user_name =?, user_email =?, user_phone =?, user_birth =? WHERE user_id =?";
	try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	connec = DriverManager.getConnection(url, "CAMP", "CAMP");
	preState = connec.prepareStatement(sql);
			
	preState.setInt(1, user_information.getUser_No());
	preState.setString(2, user_information.getUser_pw());
	preState.setString(3, user_information.getUser_Name());
	preState.setString(4, user_information.getUser_Email());
	preState.setString(5, user_information.getUser_Phone());		
	preState.setDate(6, user_information.getUser_Birthd());
	preState.setString(7, user_information.getUser_ID());
	int result = preState.executeUpdate();
	if(result > 0) {
	System.out.println("새로운 정보로 업데이트 완료되었습니다.");
	return 1;
	} else if(result <= 0) {
	System.out.println("업데이트 실패하였습니다.");
	}	
	} catch (ClassNotFoundException | SQLException e) {
	e.printStackTrace();
	}
	return -1;
	}
	
public int delete(String id){
	//아이디입력으로 삭제하기
	sql = "DELETE TBL_USER WHERE user_id =?";
		
	try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	connec = DriverManager.getConnection(url, "CAMP", "CAMP");
	preState = connec.prepareStatement(sql);
			
	preState.setString(1, id);
			
	int result = preState.executeUpdate();
	if(result > 0) {
	System.out.println(result + "행 삭제 되었습니다");
	return result;
	}
	System.out.println("삭제 실패하였습니다. 다시 실행하여주세요.");
	} catch (ClassNotFoundException | SQLException e) {
	e.printStackTrace();
	}
	return -1;
	}
	
public int logIn(String id, String pw) {
	sql = "SELECT * from TBL_USER WHERE user_id =? AND user_pw =?";
	try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection connec = DriverManager.getConnection(url, "CAMP", "CAMP");
	PreparedStatement preState = connec.prepareStatement(sql);
	preState.setString(1, id);
	preState.setString(2, pw);
	ResultSet resultS = preState.executeQuery();
	
	if(resultS.next()) {
	if(resultS.getString(2).equals(id) 
	&& resultS.getString(3).equals(pw)) {
	resultS.close();
	preState.close();
	connec.close();
	return 1;
	}
	}
	} catch(Exception e) {
	e.printStackTrace();
	}
	return -1;
}
public int dao_signUp(String id) {
	sql = "SELECT * FROM TBL_USER WHERE user_id =?";
		
	try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection connec = DriverManager.getConnection(url, "CAMP", "CAMP");
	PreparedStatement preState = connec.prepareStatement(sql);
	preState.setString(1, id);
	ResultSet resultS = preState.executeQuery();
	if(resultS.next()){
		if((resultS.getString(2)).equals(id)) {
		System.out.println("이미 존재하는 아이디입니다. 다시 입력해주세요.");
		return -1;
	}
	resultS.close();
	preState.close();
	connec.close();
	}
	} catch(SQLException e) {
	e.printStackTrace();
	} catch (ClassNotFoundException e) {
	e.printStackTrace();
	}
	return 1;
	}
}