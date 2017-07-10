package BillBoard.service;

import static BillBoard.util.CloseableUtil.*;
import static BillBoard.util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BillBoard.beans.Department;
import BillBoard.dao.DepartmentDao;

public class DepartmentService {

	public Department getDepartment(int departmentId) {

		Connection connection = null;
		try {
			connection = getConnection();

			DepartmentDao departmentDao = new DepartmentDao();
			Department department = departmentDao.getDepartment(connection, departmentId);

			commit(connection);

			return department;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Department> getAllDepartment() {

		Connection connection = null;
		try {
			connection = getConnection();

			DepartmentDao departmentDao = new DepartmentDao();
			List<Department> ret = departmentDao.getAllDepartmentList(connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
