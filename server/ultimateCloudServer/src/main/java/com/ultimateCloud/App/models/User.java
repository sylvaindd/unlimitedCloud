package com.ultimateCloud.App.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sylvain on 20/05/2016.
 */
public class User {
	private int		id;
	private String	username;
	private String	mail;
	private String	phone;
	private int		idLevel;

	public User() {
	}

	private String	token;

	public User(ResultSet resultSet) throws SQLException {
		id = resultSet.getInt("id");
		username = resultSet.getString("username");
		mail = resultSet.getString("mail");
		phone = resultSet.getString("phone");
		idLevel = resultSet.getInt("idLevel");
        token = resultSet.getString("tokenLeBonNuage");
	}

	public User(int id, String username, String mail, String phone, int idLevel, String token) {
		this.id = id;
		this.username = username;
		this.mail = mail;
		this.phone = phone;
		this.idLevel = idLevel;
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
