package camping.review.controller;

import java.util.List;

import camping.review.model.dao.ReviewDAO;
import camping.review.model.vo.Review;

public class ReviewController {

	ReviewDAO reviewDao = new ReviewDAO(); 
	Review review = new Review();

	public int review(Review review) {
		return reviewDao.review_insert(review);
	}

	public List<Review> review_searchLatest(){
		return reviewDao.review_searchLatest();
	}

	public List<Review> review_searchStar(){
		return reviewDao.review_searchStar();
	}
}
