package BillBoard.service;

import static BillBoard.util.CloseableUtil.*;
import static BillBoard.util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BillBoard.beans.Branch;
import BillBoard.dao.BranchDao;

public class BranchService {

	public Branch getBranch(int branchId) {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao branchDao = new BranchDao();
			Branch branch = branchDao.getBranch(connection, branchId);

			commit(connection);

			return branch;
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

	public List<Branch> getAllBranch() {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao branchDao = new BranchDao();
			List<Branch> ret = branchDao.getAllBranchList(connection);

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
