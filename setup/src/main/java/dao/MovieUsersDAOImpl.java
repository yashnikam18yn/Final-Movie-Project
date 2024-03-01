package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.MovieUsers;
import com.model.Status;

public class MovieUsersDAOImpl implements MovieUsersDAO {
	
	private Connection connection;
	
	public MovieUsersDAOImpl() {
		connection = DBUtil.getConnection();
	}

	@Override
	public Status signUp(MovieUsers movieusers) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into movieusers(username,email,password) values(?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, movieusers.getUserName());
		pst.setString(2, movieusers.getUserEmail());
		pst.setString(3, movieusers.getUserPassword());
		
		pst.executeUpdate();		
		Status s = new Status();
		
		s.setQueryStatus(true);
		
		return s;
		
	}

	@Override
	public MovieUsers signIn(MovieUsers movieusers) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "select * from movieusers where email=? AND password=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		
		pst.setString(1,movieusers.getUserEmail());
		pst.setString(2, movieusers.getUserPassword());
		
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			movieusers = new MovieUsers(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
			
			return movieusers;
		}
		
		return null;
	}

}
