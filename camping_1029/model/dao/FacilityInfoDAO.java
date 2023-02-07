package camping.facilityinfo.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import camping.facilityinfo.model.vo.FacilityInfo;

public class FacilityInfoDAO {
	public final static String driverClass = "oracle.jdbc.OracleDriver";
	public final static String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public final static String user = "CAMP";
	public final static String password = "CAMP";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public FacilityInfoDAO() {
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FacilityInfo select(int num) {
		try {
			String sql = "SELECT * FROM TBL_FACILITY_INFO WHERE CAMP_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if(rs.next() == true) {
				int infoNo = rs.getInt(1);
				int campNo = rs.getInt(2);
				String campingEqRent = rs.getString(3);
				String petYN = rs.getString(4);
				String Amenities = rs.getString(5);
				String campingTheme = rs.getString(6);
				FacilityInfo facilityInfo = new FacilityInfo(infoNo, campNo, campingEqRent, petYN, Amenities, campingTheme);
				return facilityInfo;
			}

			pstmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public int insert(FacilityInfo facilityinfo) {
		try {
			String sql = "INSERT INTO TBL_FACILITY_INFO VALUES(SEQ_FACULTY_INFO.NEXTVAL, 1, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, facilityinfo.getCampingEqRent());
			pstmt.setString(2, facilityinfo.getPetYN());
			pstmt.setString(3, facilityinfo.getAmenities());
			pstmt.setString(4, facilityinfo.getCampingTheme());

			int result = pstmt.executeUpdate();			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int delete(int num) {
		try {
			String sql = "DELETE FROM TBL_FACILITY_INFO WHERE camp_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			int result = pstmt.executeUpdate();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
