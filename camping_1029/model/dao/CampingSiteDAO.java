package camping.campingsite.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import camping.campingsite.model.vo.CampingSite;

public class CampingSiteDAO {
	public final static String driverClass = "oracle.jdbc.OracleDriver";
	public final static String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public final static String user = "CAMP";
	public final static String password = "CAMP";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public CampingSiteDAO() {
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<CampingSite> selectAll() {
		List<CampingSite> list = new ArrayList<>();

		try {
			String sql = "SELECT * FROM GO_CAMP";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next() == true) {
				int camp_no = rs.getInt("camp_no");
				String facltNm = rs.getString("facltNm");
				String address = rs.getString("address");
				String address_detail = rs.getString("address_detail");
				String homepage = rs.getString("homepage");
				String camping_kind = rs.getString("camping_kind");
				String camp_tel = rs.getString("camp_tel");
				String lineIntro = rs.getString("lineIntro");
				String camp_imageUrl = rs.getString("camp_imageUrl");
				String operDay = rs.getString("operDay");
				CampingSite campingsite = new CampingSite(camp_no, facltNm, address, address_detail, homepage, camping_kind, camp_tel, lineIntro, camp_imageUrl, operDay);

				list.add(campingsite);
			}
			pstmt.close();
			rs.close();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	public int insertCampingsite(CampingSite campingSite) {
		try {
			String sql = "INSERT INTO TBL_CAMPINGSITE VALUES(SEQ_CAMP_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, campingSite.getFacltNm());
			pstmt.setString(2, campingSite.getAddress());
			pstmt.setString(3, campingSite.getAddress_detail());
			pstmt.setString(4, campingSite.getHomepage());
			pstmt.setString(5, campingSite.getCamping_kind());
			pstmt.setString(6, campingSite.getCamp_tel());
			pstmt.setString(7, campingSite.getLineIntro());
			pstmt.setString(8, campingSite.getCamp_imageUrl());
			pstmt.setString(9, campingSite.getOperDay());

			int result = pstmt.executeUpdate();			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
