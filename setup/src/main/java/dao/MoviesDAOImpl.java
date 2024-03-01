package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Movies;

public class MoviesDAOImpl implements MoviesDAO {
	
	private Connection connection;
	
	public MoviesDAOImpl() {
		connection = DBUtil.getConnection();
	}

	@Override
	public List<Movies> viewAllMovies() throws SQLException {
		// TODO Auto-generated method stub
		String sql ="select * from movies";
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<Movies> list = new ArrayList<>();
		
		while(rs.next()) {
			list.add(new Movies(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
		}
		
		return list;
	}
	
}
