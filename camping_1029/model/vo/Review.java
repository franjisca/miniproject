package camping.review.model.vo;

public class Review {
	private int review_no; 
	private int user_no; 
	private int camp_no;
	public String star_score; 
	public String comment_review; 

	public Review(int review_no, int user_no, int camp_no, String star_score, String comment_review) {
		super();
		this.review_no = review_no;
		this.user_no = user_no;
		this.camp_no = camp_no;
		this.star_score = star_score;
		this.comment_review = comment_review;
	}

	public Review() {
		super();
	}

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public String getStar_score() {
		return star_score;
	}

	public void setStar_score(String star_score) {
		this.star_score = star_score;
	}

	public String getComment_review() {
		return comment_review;
	}

	public void setComment_review(String comment_review) {
		this.comment_review = comment_review;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getCamp_no() {
		return camp_no;
	}

	public void setCamp_no(int camp_no) {
		this.camp_no = camp_no;
	}

	@Override
	public String toString() {
		return "Review [리뷰 번호=" + review_no + ", 유저 번호=" + user_no + ", 캠핑장 번호=" + camp_no 
				+ ", 별점=" + star_score + ", 코멘트=" + comment_review + "]";
	}
}
