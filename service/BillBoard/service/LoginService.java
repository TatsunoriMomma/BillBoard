package BillBoard.service;

import static BillBoard.util.CloseableUtil.*;
import static BillBoard.util.DBUtil.*;

import java.sql.Connection;

import BillBoard.beans.User;
import BillBoard.dao.UserDao;
import BillBoard.util.CipherUtil;

public class LoginService {

	public User login(String loginId, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			User user = userDao.getUser(connection,loginId,encPassword);

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
}
