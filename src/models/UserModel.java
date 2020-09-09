package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business.utilities.SqlCommand;
import dataaccess.DbUtil;

public class UserModel {

	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public UserModel(String userName) {
		this.userName=userName;
	}
	
	public UserModel(String userName,String password) {
		this.userName=userName;
		this.password=password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserAll() {
		return getUserName().toString()+" "+getPassword().toString();
	}
	
	private Connection conn = DbUtil.connection;
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public UserModel getUser() {
		UserModel user = null;
		try {
			pstmt = conn.prepareStatement(SqlCommand.getByUserName);
			pstmt.setString(1, getUserName());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new UserModel(rs.getString("UserName"), rs.getString("Password"));
			}
		} catch (SQLException exception) {
			DbUtil.showErrorMessage(exception);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
}
