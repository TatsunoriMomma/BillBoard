package BillBoard.dao;

import static BillBoard.util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import BillBoard.beans.Contribution;
import BillBoard.exception.SQLRuntimeException;

public class ContributionDao{

	public Contribution getContribution(Connection connection, int contributionId) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM contributions WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, contributionId);

			ResultSet rs = ps.executeQuery();
			List<Contribution> contributionList = toContributionList(rs);
			if (contributionList.isEmpty() == true) {
				return null;
			} else if (2 <= contributionList.size()) {
				throw new IllegalStateException("2 <= contributionList.size()");
			} else {
				return contributionList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Contribution> toContributionList(ResultSet rs) throws SQLException {
		List<Contribution> ret = new ArrayList<Contribution>();
		try {
			while(rs.next()) {
				int contribution_id = rs.getInt("id");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				int user_id = rs.getInt("user_id");
				Timestamp insert_date = rs.getTimestamp("insert_date");

				Contribution contribution = new Contribution();
				contribution.setId(contribution_id);
				contribution.setSubject(subject);
				contribution.setText(text);
				contribution.setCategory(category);
				contribution.setUser_id(user_id);
				contribution.setInsertDate(insert_date);

				ret.add(contribution);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, Contribution contribution) {

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO contributions( ");
			sql.append("subject");
			sql.append(", text");
			sql.append(", category");
			sql.append(", user_id");
			sql.append(") VALUES (");
			sql.append("?"); //subject
			sql.append(", ?"); //text
			sql.append(", ?"); //category
			sql.append(", ?"); //user_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, contribution.getSubject());
			ps.setString(2, contribution.getText());
			ps.setString(3, contribution.getCategory());
			ps.setInt(4, contribution.getUser_id());

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
			sql.append("DELETE FROM contributions WHERE id = ?;");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public List<String> getAllCategory(Connection connection) {
		PreparedStatement ps = null;
		List<String> categoryList = new ArrayList<String>();
		try {
			String sql = "SELECT DISTINCT category FROM contributions ORDER BY category ASC";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String category = rs.getString("category");
				categoryList.add(category);
			}
			return categoryList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	/*
	private List<String> toCommentList(ResultSet rs) throws SQLException {
		List<String> ret = new ArrayList<String>();
		try {
			while(rs.next()) {
				String category = rs.getString("category");

				ret.add(category);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	*/


	public List<Contribution> getAllContribution(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM contributions ";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Contribution> contributionList = toContributionList(rs);
			if (contributionList.isEmpty() == true) {
				return null;
			}
			return contributionList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
