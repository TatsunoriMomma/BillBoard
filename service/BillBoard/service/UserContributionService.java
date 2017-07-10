package BillBoard.service;

import static BillBoard.util.CloseableUtil.*;
import static BillBoard.util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BillBoard.beans.UserContribution;
import BillBoard.dao.UserContributionDao;

public class UserContributionService {

	public List<UserContribution> getAllUserContribution() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserContributionDao usercontributionDao = new UserContributionDao();
			List<UserContribution> ret = usercontributionDao.getAllUserContribution(connection);

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

	public List<UserContribution> getNarrowUserContribution(String category) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserContributionDao usercontributionDao = new UserContributionDao();
			List<UserContribution> ret = usercontributionDao.getNarrowUserContribution(connection, category);

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
