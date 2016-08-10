package com.gigaspaces.settlement.web.blotter.client.layoutmanager;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class LoginForm extends DynamicForm {
	private TextItem username;
	private PasswordItem password;
	
	public LoginForm() {
		username = new TextItem("username", "User Name");
		password = new PasswordItem("password", "Password");
		setItems(username, password);
	}

	public TextItem getUsername() {
		return username;
	}

	public void setUsername(TextItem username) {
		this.username = username;
	}

	public TextItem getPassword() {
		return password;
	}

	public void setPassword(PasswordItem password) {
		this.password = password;
	}
}
