package BillBoard.service;

import static BillBoard.util.CloseableUtil.*;
import static BillBoard.util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BillBoard.beans.Contribution;
import BillBoard.dao.ContributionDao;

public class ContributionService {

	public void register(Contribution contribution) {

		Connection connection = null;
		try{
			connection = getConnection();

			ContributionDao contributionDao = new ContributionDao();
			contributionDao.insert(connection,contribution);

			commit(connection);
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

	public void delete(int id) {

		Connection connection = null;
		try{
			connection = getConnection();

			ContributionDao contributionDao = new ContributionDao();
			contributionDao.delete(connection, id);

			commit(connection);
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

	public List<Contribution> getAllContribution() {

		Connection connection = null;
		try {
			connection = getConnection();

			ContributionDao contributionDao = new ContributionDao();
			List<Contribution> ret = contributionDao.getAllContribution(connection);

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

	public List<String> getAllCategory() {

		Connection connection = null;
		try {
			connection = getConnection();

			ContributionDao contributionDao = new ContributionDao();
			List<String> ret = contributionDao.getAllCategory(connection);

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
