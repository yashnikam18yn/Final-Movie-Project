package dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Movies;

public interface MoviesDAO {
	List<Movies> viewAllMovies() throws SQLException;
}
