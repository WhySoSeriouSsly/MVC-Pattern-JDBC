package framecontrollers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.StockListController;
import controllers.StockSelectDeleteController;
import views.StockListView;
import views.StockMainView;

public class StockCartListFrameController implements ActionListener {
	public static StockListView listView;
	private StockCartCopyFrameController copyFrameController;
	private StockListController listController;
	private StockSelectDeleteController selectDeleteController;
	private StockMainView mainView;
	private StockCartListPopupController popUpController;
	public StockCartListFrameController(StockMainView mainView) {
		listView=new StockListView();
		this.mainView=mainView;
		selectDeleteController=new StockSelectDeleteController(listView);
		copyFrameController=new StockCartCopyFrameController(listView);
		listController=new StockListController(listView);
		popUpController=new StockCartListPopupController(listView);
		listView.btnSearch.addActionListener(null);
		listView.tblStocks.addMouseListener(popUpController);
		listView.mntmDelete.addActionListener(selectDeleteController);
		listView.mntmCopy.addActionListener(copyFrameController);
		listView.btnGetStock.addActionListener(listController);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		mainView.createFrame(listView);
	}

}
