package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.MovieReviews;
import com.model.Movies;
import com.model.Status;

public class MovieReviewsDAOImpl implements MovieReviewsDAO {

	private Connection connection;
	
	public MovieReviewsDAOImpl() {
		connection = DBUtil.getConnection();
	}
	
	
	
	@Override
	public Status addNewReview(MovieReviews moviereviews) throws SQLException {
		// TODO Auto-generated method stub
		String sql ="insert into moviereviews(review_text,movie_id,user_id,username) values(?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		
		pst.setString(1,moviereviews.getReviewText());
		pst.setInt(2, moviereviews.getMovies().getMoviesId());
		pst.setInt(3, moviereviews.getMovieusers().getUserId());
		pst.setString(4, moviereviews.getMovieusers().getUserName());
		
		int res = pst.executeUpdate();
		
		return new Status((res == 1) ? true : false);
	}

	@Override
	public List<MovieReviews> viewAllReviews() throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT mr.review_id, mr.username,mr.review_text,m.movie_id, m.title FROM movies m JOIN moviereviews mr ON m.movie_id = mr.movie_id";
		Statement st = connection.createStatement();
		
		ResultSet rs = st.executeQuery(sql);
		
		List<MovieReviews> list = new ArrayList<>();
		
		
		while(rs.next()) {
			list.add(new MovieReviews(rs.getInt(1),rs.getString(2),rs.getString(3),new Movies(rs.getInt(4),rs.getString(5))));
		}
		
		return list;
	}

	@Override
	public List<MovieReviews> viewMyReviews(int userId) throws SQLException {
		// TODO Auto-generated method stub
		String sql ="SELECT mr.review_id, mr.username,mr.review_text,m.movie_id, m.title FROM movies m JOIN moviereviews mr ON m.movie_id = mr.movie_id where user_id=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		
		pst.setInt(1, userId);
		
		ResultSet rs = pst.executeQuery();
		
		List<MovieReviews> list = new ArrayList<>();
		
		while(rs.next()) {
			list.add(new MovieReviews(rs.getInt(1),rs.getString(2),rs.getString(3),new Movies(rs.getInt(4),rs.getString(5))));
		}
		
		return list;
	}



	@Override
	public Status deleteReview(int reviewId) throws SQLException {
		// TODO Auto-generated method stub
		String sql ="delete from moviereviews where review_id=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		
		pst.setInt(1, reviewId);
		
		int res = pst.executeUpdate();
		
		Status s = new Status();
		
		if(res != 0) {
			s.setQueryStatus(true);
			return s;
		}
		s.setQueryStatus(false);
		return s;
	}

}
