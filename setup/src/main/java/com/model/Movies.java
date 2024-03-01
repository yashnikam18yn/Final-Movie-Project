package com.model;

public class Movies {
	private int moviesId;
	private String movieTitle;
	private String movieGenere;
	private String movieDirector;
	private String movieDescription;
	private String imageUrl;
	
	public Movies() {
		
	}


	public Movies(int moviesId, String movieTitle, String movieGenere, String movieDirector,String movieDescription,String imageUrl) {
		super();
		this.moviesId = moviesId;
		this.movieTitle = movieTitle;
		this.movieGenere = movieGenere;
		this.movieDirector = movieDirector;
		this.setMovieDescription(movieDescription);
		this.setImageUrl(imageUrl);
	}


	public Movies(int moviesId,String movieTitle) {
		this.moviesId=moviesId;
		this.movieTitle = movieTitle;
	}
	
	
	public int getMoviesId() {
		return moviesId;
	}


	public void setMoviesId(int moviesId) {
		this.moviesId = moviesId;
	}


	public String getMovieTitle() {
		return movieTitle;
	}


	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}


	public String getMovieGenere() {
		return movieGenere;
	}


	public void setMovieGenere(String movieGenere) {
		this.movieGenere = movieGenere;
	}


	public String getMovieDirector() {
		return movieDirector;
	}


	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}


	public String getMovieDescription() {
		return movieDescription;
	}


	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	@Override
	public String toString() {
		return "Movies [moviesId=" + moviesId + ", movieTitle=" + movieTitle + ", movieGenere=" + movieGenere
				+ ", movieDirector=" + movieDirector + ", movieDescription=" + movieDescription + ", imageUrl="
				+ imageUrl + "]";
	}
	
	
	
}
