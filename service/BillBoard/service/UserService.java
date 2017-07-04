package BillBoard.service;

import static BillBoard.util.CloseableUtil.*;
import static BillBoard.util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BillBoard.beans.User;
import BillBoard.dao.UserDao;
import BillBoard.util.CipherUtil;

public class UserService {

	public void register(User user) {

		Connection connection = null;
		try{
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert(connection,user);

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

	public List<User> getAllUser() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> ret = userDao.getAllUserList(connection);

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

	public void update(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			if(user.getPassword() != null){
				String encPassword = CipherUtil.encrypt(user.getPassword());
				user.setPassword(encPassword);
			}
			UserDao userDao = new UserDao();
			userDao.update(connection, user);

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

	public User getUser(int loginId) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUser(connection, loginId);

			commit(connection);

			return user;
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

	public void isWorkingSwitch(int userId, int status) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.isWorkingSwitch(connection, userId, status);

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
}
