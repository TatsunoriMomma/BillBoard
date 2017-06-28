package BillBoard.dao;

import static BillBoard.util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BillBoard.beans.User;
import BillBoard.exception.SQLRuntimeException;

public class UserDao {

	public User getUser(Connection connection, String loginId, String password) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM user WHERE (login_id = ?) AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {
		List<User> ret = new ArrayList<User>();
		try {
			while(rs.next()) {
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				int is_working = rs.getInt("is_working");

				User user = new User();
				user.setLogin_id(login_id);
				user.setPassword(password);
				user.setName(name);
				user.setBranch_id(branch_id);
				user.setDepartment_id(department_id);
				user.setIs_working(is_working);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO user( ");
			sql.append("login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", is_working");
			sql.append(") VALUES (");
			sql.append("?");  //login_id
			sql.append(", ?"); //password
			sql.append(", ?"); //name
			sql.append(", ?"); //branch_id
			sql.append(", ?"); //department_id
			sql.append(", ?"); //is_working
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_id());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranch_id());
			ps.setInt(5, user.getDepartment_id());
			ps.setInt(6, user.getIs_working());

			System.out.println(ps);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
