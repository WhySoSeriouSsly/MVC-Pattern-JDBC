package framecontrollers;

import java.sql.SQLException;

import dataaccess.DbUtil;
import views.StockMainView;

public class StockCartMainFrameController {

	static StockMainView mainView;

	public static void main(String[] args) throws SQLException {
		DbUtil db = new DbUtil();
		db.baglanti();
		mainView = new StockMainView();
		mainView.setVisible(true);
		StockCartListFrameController listFrameController = new StockCartListFrameController(mainView);
		StockCartFrameController cartFrameController = new StockCartFrameController(mainView);
		mainView.mn�tmStokList.addActionListener(listFrameController);
		mainView.mn�tmStokIslemleri.addActionListener(cartFrameController);
	}

}
