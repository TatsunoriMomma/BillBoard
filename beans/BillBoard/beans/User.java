package BillBoard.beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private int user_id;
	private String login_id;
	private String password;
	private String name;
	private int branch_id;
	private int department_id;
	private Date update_date;
	private int is_working;

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getIs_working() {
		return is_working;
	}
	public void setIs_working(int is_working) {
		this.is_working = is_working;
	}
	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdateDate(Date update_date) {
		this.update_date = update_date;
	}


}
