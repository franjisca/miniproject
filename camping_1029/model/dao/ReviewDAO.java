package camping.review.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import camping.review.model.vo.Review;

public class ReviewDAO {
	//	private final static String driverClass = "oracle.jdbc.OracleDriver";
	private final static String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private final static String user = "CAMP";
	private final static String password = "CAMP";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public ReviewDAO() {
		try {
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

	public int review_insert(Review review) {
		try {
			String sql = "INSERT INTO TBL_REVIEW VALUES(SEQ_REVIEW.NEXTVAL, 101, 1, ?, ?)";
			// 리뷰 번호, 유저 번호, 캠핑장 번호 
			//			+ 로그인한 유저번호 입력되게 할것 
			//			+ 캠핑장 번호 입력되게 할것 
			pstmt = conn.prepareStatement(sql);
			//			pstmt.setInt(1, camp.getReview_no());
			//			pstmt.setInt(2, camp.getCamp_no());
			pstmt.setString(1, review.getStar_score());
			pstmt.setString(2, review.getComment_review());

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<Review> review_searchLatest() {
		List<Review> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM TBL_REVIEW ORDER BY REVIEW_NO DESC";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next() == true) {
				int review_no = rs.getInt("review_no");
				int user_no = rs.getInt("user_no");
				int camp_no = rs.getInt("camp_no");
				String star_score = rs.getString("star_score");
				String comment_review = rs.getString("comment_review");
				Review camp1 = new Review(review_no, user_no, camp_no, star_score, comment_review);

				list.add(camp1);
			}

			pstmt.close();
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Review> review_searchStar() {
		List<Review> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM TBL_REVIEW ORDER BY STAR_SCORE";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next() == true) {
				int review_no = rs.getInt("review_no");
				int user_no = rs.getInt("user_no");
				int camp_no = rs.getInt("camp_no");
				String star_score = rs.getString("star_score");
				String comment_review = rs.getString("comment_review");
				Review camp1 = new Review(review_no, user_no, camp_no, star_score, comment_review);

				list.add(camp1);
			}

			pstmt.close();
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
