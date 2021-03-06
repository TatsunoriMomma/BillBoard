package BillBoard.dao;

import static BillBoard.util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import BillBoard.beans.UserContribution;
import BillBoard.exception.SQLRuntimeException;

public class UserContributionDao {

	public List<UserContribution> getAllUserContribution(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT contributions.* ,"
					+ " users.id AS users_id, users.name"
					+ ", users.branch_id, users.department_id"
					+ " FROM contributions LEFT OUTER JOIN users"
					+ " ON contributions.user_id = users.id"
					+ " ORDER BY insert_date DESC;";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<UserContribution> contributionList = toContributionList(rs);
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

	private List<UserContribution> toContributionList(ResultSet rs) throws SQLException {
		List<UserContribution> ret = new ArrayList<UserContribution>();
		try {
			while(rs.next()) {
				int contribution_id = rs.getInt("id");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				int user_id = rs.getInt("user_id");
				Timestamp insert_date = rs.getTimestamp("insert_date");
				int users_id = rs.getInt("users_id");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");

				UserContribution contribution = new UserContribution();
				contribution.setId(contribution_id);
				contribution.setSubject(subject);
				contribution.setText(text);
				contribution.setCategory(category);
				contribution.setUser_id(user_id);
				contribution.setInsert_date(insert_date);
				contribution.setUsers_id(users_id);
				contribution.setName(name);
				contribution.setBranch_id(branch_id);
				contribution.setDepartment_id(department_id);

				ret.add(contribution);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<UserContribution> getNarrowUserContribution(Connection connection, String category, String firstDate, String lastDate) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT contributions.* ,");
			sql.append(" users.id AS users_id, users.name");
			sql.append(", users.branch_id, users.department_id");
			sql.append(" FROM contributions LEFT OUTER JOIN users");
			sql.append(" ON contributions.user_id = users.id");

			//要変更
			if(firstDate == null || firstDate.isEmpty()){
				firstDate = "2017/07/01";
			}
			if(lastDate == null || lastDate.isEmpty()){
				lastDate = "2018/06/30";
			}
			int deff = firstDate.compareTo(lastDate);
			if(deff > 0){
				String temp = firstDate;
				firstDate = lastDate;
				lastDate = temp;
			}
			lastDate += " 23:59:59";

			sql.append(" WHERE contributions.insert_date BETWEEN ? AND ?");
			if(category != null && !category.isEmpty()){
				sql.append(" AND category = ? ");
			}
			sql.append(" ORDER BY insert_date DESC");
			ps = connection.prepareStatement(sql.toString());

			if(category != null && !category.isEmpty()){
				ps.setString(1, firstDate);
				ps.setString(2, lastDate);
				ps.setString(3, category);
			} else {
				ps.setString(1, firstDate);
				ps.setString(2, lastDate);}

			ResultSet rs = ps.executeQuery();
			List<UserContribution> contributionList = toContributionList(rs);
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
