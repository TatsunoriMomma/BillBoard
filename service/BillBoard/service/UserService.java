package BillBoard.service;

import static BillBoard.util.CloseableUtil.*;
import static BillBoard.util.DBUtil.*;

import java.sql.Connection;

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
}
