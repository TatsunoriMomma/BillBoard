package BillBoard.service;

import static BillBoard.util.CloseableUtil.*;
import static BillBoard.util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BillBoard.beans.UserComment;
import BillBoard.dao.UserCommentDao;

public class UserCommentService {

	public List<UserComment> getAllUserComment() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserCommentDao usercommentDao = new UserCommentDao();
			List<UserComment> ret = usercommentDao.getAllUserComment(connection);

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
