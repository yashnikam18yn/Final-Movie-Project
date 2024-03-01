package dao;

import java.sql.SQLException;

import com.model.MovieUsers;
import com.model.Status;

public interface MovieUsersDAO {
	Status signUp(MovieUsers movieusers) throws SQLException, Exception;
	MovieUsers signIn(MovieUsers movieusers) throws SQLException;
}
