package BillBoard.beans;

import java.util.Date;

public class Comment {
	private static final long serialVersionUID = 1L;

	private int id;
	private String text;
	private int user_id;
	private int contribution_id;
	private Date insert_date;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getContribution_id() {
		return contribution_id;
	}
	public void setContribution_id(int contribution_id) {
		this.contribution_id = contribution_id;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}


}
