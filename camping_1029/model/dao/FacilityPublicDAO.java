package camping.facilitypublic.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import camping.facilitypublic.model.vo.FacilityPublic;

public class FacilityPublicDAO {
	public final static String driverClass = "oracle.jdbc.OracleDriver";
	public final static String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public final static String user = "CAMP";
	public final static String password = "CAMP";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public FacilityPublicDAO() {
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FacilityPublic select(int num) {
		try {
			String sql = "SELECT * FROM TBL_FACILITY_Public WHERE CAMP_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if(rs.next() == true) {
				int publicNo = rs.getInt(1);
				int campNo = rs.getInt(2);
				String toiletNo = rs.getString(3);
				String swrmNo = rs.getString(4);
				String wtrpNo = rs.getString(5);
				String posblFcltyClNo = rs.getString(6);

				FacilityPublic facilitypublic = new FacilityPublic(publicNo, campNo, toiletNo, swrmNo, wtrpNo, posblFcltyClNo);

				return facilitypublic;
			}

			pstmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int insert(FacilityPublic facilitypublic) {
		try {
			String sql = "INSERT INTO TBL_FACILITY_PUBLIC VALUES(SEQ_PUBLIC_NO.NEXTVAL, 1, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, facilitypublic.gettoiletNo());
			pstmt.setString(2, facilitypublic.getswrmNo());
			pstmt.setString(3, facilitypublic.getwtrpNo());
			pstmt.setString(4, facilitypublic.getposblFcltyClNo());

			int result = pstmt.executeUpdate();		
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int delete(int num) {
		try {
			String sql = "DELETE FROM TBL_FACILITY_PUBLIC WHERE camp_no = ?";

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

