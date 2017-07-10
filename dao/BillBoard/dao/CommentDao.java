package BillBoard.dao;

import static BillBoard.util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import BillBoard.beans.Comment;
import BillBoard.exception.SQLRuntimeException;

public class CommentDao{

	public Comment getComment(Connection connection, int commentId) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM comments WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, commentId);

			ResultSet rs = ps.executeQuery();
			List<Comment> commentList = toCommentList(rs);
			if (commentList.isEmpty() == true) {
				return null;
			} else if (2 <= commentList.size()) {
				throw new IllegalStateException("2 <= contributionList.size()");
			} else {
				return commentList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Comment> toCommentList(ResultSet rs) throws SQLException {
		List<Comment> ret = new ArrayList<Comment>();
		try {
			while(rs.next()) {
				int comment_id = rs.getInt("id");
				String text = rs.getString("text");
				int user_id = rs.getInt("user_id");
				int contribution_id = rs.getInt("contribution_id");
				Timestamp insert_date = rs.getTimestamp("insert_date");

				Comment comment = new Comment();
				comment.setId(comment_id);
				comment.setText(text);
				comment.setUser_id(user_id);
				comment.setContribution_id(contribution_id);
				comment.setInsert_date(insert_date);

				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments( ");
			sql.append("text");
			sql.append(", user_id");
			sql.append(", contribution_id");
			sql.append(") VALUES (");
			sql.append("?"); //text
			sql.append(", ?"); //user_id
			sql.append(", ?"); //contribution_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUser_id());
			ps.setInt(3, comment.getContribution_id());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void delete(Connection connection, int id) {

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments WHERE id = ?;");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public List<Comment> getAllCommentList(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM comments ";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Comment> commentList = toCommentList(rs);
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
	/*
	public List<Comment> getAllCommentOfContributionId(Connection connection,int contribution_id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM comments ");
			sql.append("WHERE contribution_id = ? ");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, contribution_id);

			ResultSet rs = ps.executeQuery();
			List<Comment> commentList = toCommentList(rs);
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
	*/

}
