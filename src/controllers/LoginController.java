package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import business.Validator;
import business.utilities.Messages;
import models.UserModel;
import views.LoginView;

public class LoginController implements ActionListener{
	private LoginView loginView;
	public LoginController(LoginView loginView) {
		this.loginView=loginView;
	}
	public Boolean login(UserModel user) {
		if (Validator.validate(user) == false) {
			JOptionPane.showMessageDialog(null, Messages.validation);
			return false;
		}
		UserModel userBool = user.getUser();
		if (userBool == null) {
			JOptionPane.showMessageDialog(null, Messages.userNotFound);
			return false;
		}
		String userName = userBool.getUserName().trim().toString();
		String password = userBool.getPassword().trim().toString();
		if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
			JOptionPane.showMessageDialog(null, Messages.loginSuccesful);
			return true;
		}
		JOptionPane.showMessageDialog(null, Messages.passwordInvalid);
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		login(loginView.getData());
	}

}
