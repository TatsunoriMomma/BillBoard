package BillBoard.dao;

import static BillBoard.util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BillBoard.beans.Department;
import BillBoard.exception.SQLRuntimeException;

public class DepartmentDao {

	public Department getDepartment(Connection connection, int departmentId) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM departments WHERE id = ? ";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, departmentId);

			ResultSet rs = ps.executeQuery();
			List<Department> departmentList = toDepartmentList(rs);
			if (departmentList.isEmpty() == true) {
				return null;
			} else if (2 <= departmentList.size()) {
				throw new IllegalStateException("2 <= departmentList.size()");
			} else {
				return departmentList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Department> toDepartmentList(ResultSet rs) throws SQLException {
		List<Department> ret = new ArrayList<Department>();
		try {
			while(rs.next()) {
				int department_id = rs.getInt("id");
				String name = rs.getString("name");

				Department department = new Department();
				department.setId(department_id);
				department.setName(name);

				ret.add(department);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Department> getAllDepartmentList(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM departments ";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Department> departmentList = toDepartmentList(rs);
			if (departmentList.isEmpty() == true) {
				return null;
			}
			return departmentList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}