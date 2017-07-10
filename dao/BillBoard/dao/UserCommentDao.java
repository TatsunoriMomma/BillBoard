package BillBoard.dao;

import static BillBoard.util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import BillBoard.beans.UserComment;
import BillBoard.exception.SQLRuntimeException;

public class UserCommentDao {

	public List<UserComment> getAllUserComment(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT comments.* ,"
					+ " users.id AS users_id, users.name"
					+ ", users.branch_id, users.department_id"
					+ " FROM comments LEFT OUTER JOIN users "
					+ "ON comments.user_id = users.id "
					+ "ORDER BY insert_date DESC;";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<UserComment> commentList = toCommentList(rs);
			if (commentList.isEmpty() == true) {
				return null;
			}
			return commentList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserComment> toCommentList(ResultSet rs) throws SQLException {
		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while(rs.next()) {
				int comment_id = rs.getInt("id");
				String text = rs.getString("text");
				int user_id = rs.getInt("user_id");
				int contribution_id = rs.getInt("contribution_id");
				Timestamp insert_date = rs.getTimestamp("insert_date");
				int users_id = rs.getInt("users_id");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");

				UserComment comment = new UserComment();
				comment.setId(comment_id);
				comment.setText(text);
				comment.setUser_id(user_id);
				comment.setContribution_id(contribution_id);
				comment.setInsert_date(insert_date);
				comment.setUsers_id(users_id);
				comment.setName(name);
				comment.setBranch_id(branch_id);
				comment.setDepartment_id(department_id);

				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
