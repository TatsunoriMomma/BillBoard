package BillBoard.service;

import static BillBoard.util.CloseableUtil.*;
import static BillBoard.util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BillBoard.beans.Comment;
import BillBoard.dao.CommentDao;

public class CommentService {

	public void register(Comment comment) {

		Connection connection = null;
		try{
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection,comment);

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

	public List<Comment> getAllComment() {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			List<Comment> ret = commentDao.getAllCommentList(connection);

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

	public void delete(int id) {

		Connection connection = null;
		try{
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.delete(connection, id);

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
	/*
	public List<Comment> getAllCommentOfContributionId(int contribution_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			List<Comment> ret = commentDao.getAllCommentOfContributionId(connection, contribution_id);

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
	*/

}