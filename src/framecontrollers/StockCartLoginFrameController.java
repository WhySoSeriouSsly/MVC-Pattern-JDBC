package framecontrollers;

import controllers.LoginController;
import views.LoginView;

public class StockCartLoginFrameController  {
	private LoginView loginView;
	private LoginController loginController;
	public StockCartLoginFrameController(LoginView loginView) {
		this.loginView=loginView;
		loginController=new LoginController(loginView);
		loginView.btnLogin.addActionListener(loginController);
	}
	
}
