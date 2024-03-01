package com.model;

public class MovieReviews {
	
	private int reviewId;
	private Movies movies;
	private MovieUsers movieusers;
	private String username;
	private String reviewText;
	
	
	public MovieReviews() {
		
		
	}
	
	public MovieReviews(int reviewId, Movies movies, MovieUsers movieusers, String username, String reviewText) {
		
		this.reviewId = reviewId;
		this.movies = movies;
		this.movieusers = movieusers;
		this.username = username;
		this.reviewText = reviewText;
	}
	
	public MovieReviews(int reviewId,String username,String reviewText,Movies movies) {
		this.reviewId = reviewId;
		this.username = username;
		this.reviewText = reviewText;
		this.movies = movies;
	}
	
//	public MovieReviews(String username,String reviewText,Movies movies) {
//		
//		this.username=username;
//		this.reviewText=reviewText;
//		this.movies=movies;
//	}
//	

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Movies getMovies() {
		return movies;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}

	public MovieUsers getMovieusers() {
		return movieusers;
	}

	public void setMovieusers(MovieUsers movieusers) {
		this.movieusers = movieusers;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	@Override
	public String toString() {
		return "MovieReviews [reviewId=" + reviewId + ", movies=" + movies + ", movieusers=" + movieusers
				+ ", username=" + username + ", reviewText=" + reviewText + "]";
	}

	
	
	
}
