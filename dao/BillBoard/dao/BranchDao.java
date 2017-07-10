package BillBoard.dao;

import static BillBoard.util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BillBoard.beans.Branch;
import BillBoard.exception.SQLRuntimeException;

public class BranchDao {

	public Branch getBranch(Connection connection, int branchId) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM branches WHERE id = ? ";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, branchId);

			ResultSet rs = ps.executeQuery();
			List<Branch> branchList = toBranchList(rs);
			if (branchList.isEmpty() == true) {
				return null;
			} else if (2 <= branchList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return branchList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Branch> toBranchList(ResultSet rs) throws SQLException {
		List<Branch> ret = new ArrayList<Branch>();
		try {
			while(rs.next()) {
				int branch_id = rs.getInt("id");
				String name = rs.getString("name");

				Branch branch = new Branch();
				branch.setId(branch_id);
				branch.setName(name);

				ret.add(branch);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Branch> getAllBranchList(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM branches ";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Branch> branchList = toBranchList(rs);
			if (branchList.isEmpty() == true) {
				return null;
			}
			return branchList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}