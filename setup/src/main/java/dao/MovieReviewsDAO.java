package dao;

import java.sql.SQLException;
import java.util.List;

import com.model.MovieReviews;
import com.model.Status;

public interface MovieReviewsDAO {
	Status addNewReview(MovieReviews moviereviews) throws SQLException;
	List<MovieReviews> viewAllReviews() throws SQLException, Exception;
	List<MovieReviews> viewMyReviews(int userId) throws SQLException;
	public Status deleteReview(int reviewId) throws SQLException;
}
