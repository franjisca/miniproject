package camping.reserve.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import camping.reserve.model.vo.Reserve;

public class ReserveDAO {
	public final static String driverClass = "oracle.jdbc.OracleDriver";
	public final static String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public final static String user = "CAMP";
	public final static String password = "CAMP";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public ReserveDAO() {
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs != null && rs.isClosed() == false) {
				rs.close();
			}
			if(pstmt != null && pstmt.isClosed() == false) {
				pstmt.close();
			}
			if(conn != null && conn.isClosed() == false) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insert(Reserve reserve) {
		try {
		String sql = "INSERT INTO TBL_RESERVE VALUES(SEQ_RESERVE_NO.NEXTVAL, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql); 

		pstmt.setInt(1, reserve.getUserNo());
		pstmt.setString(2, reserve.getCampName());
		pstmt.setInt(3, reserve.getNumOfPeople());
		pstmt.setDate(4, reserve.getReservationDate());
		
		int result = pstmt.executeUpdate();
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return -1;
	}

	public int delete(int reserve_No) {
		try {
			String sql = "DELETE FROM TBL_RESERVE WHERE RESERVE_NO = ?";
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, reserve_No);
//			pstmt.setInt(2, reserve.getUserNo());
//			pstmt.setInt(3, reserve.getCampNo());
//			pstmt.setInt(4, reserve.getNumOfPeople());
//			pstmt.setDate(5, reserve.getReservationDate());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<Reserve> selectByUserNo(int user_No) {
		try {
			String sql = "SELECT * FROM TBL_RESERVE WHERE USER_NO like ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,user_No);
			rs = pstmt.executeQuery();

			List<Reserve> reserveList = new ArrayList<>();
			
			while(rs.next()) {
			int reserveNo = rs.getInt(1); 
			int userNo = rs.getInt(2);
			String campName = rs.getString(3);
			int numOfPeople = rs.getInt(4);
			Date reservationDate = rs.getDate(5);
				Reserve reserve = new Reserve(reserveNo, userNo, campName, numOfPeople, reservationDate);
				reserveList.add(reserve);
			}
			 
			pstmt.close();
			rs.close();
			return reserveList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Reserve> selectByReserveNo(int reserve_No) {
		try {
			String sql = "SELECT * FROM TBL_RESERVE WHERE RESERVE_NO like ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,reserve_No);
			rs = pstmt.executeQuery();

			List<Reserve> reserveList = new ArrayList<>();
			
			while(rs.next()) {
			int reserveNo = rs.getInt(1); 
			int userNo = rs.getInt(2);
			String campName = rs.getString(3);
			int numOfPeople = rs.getInt(4);
			Date reservationDate = rs.getDate(5);
				Reserve reserve = new Reserve(reserveNo, userNo, campName, numOfPeople, reservationDate);
				reserveList.add(reserve);
			}
			 
			pstmt.close();
			rs.close();
			return reserveList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}