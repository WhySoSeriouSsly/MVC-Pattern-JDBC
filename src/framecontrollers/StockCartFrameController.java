package framecontrollers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.StockDeleteController;
import controllers.StockSaveController;
import controllers.StockSearchController;
import views.StockListView;
import views.StockMainView;
import views.StockOperationView;

public class StockCartFrameController implements ActionListener {
	public static StockOperationView operationView;
	public StockSaveController saveController;
	public StockDeleteController deleteController;
	public StockSearchController searchController;
	private StockMainView mainView;

	public StockCartFrameController(StockMainView mainView) {
		this.mainView = mainView;
		operationView = new StockOperationView();
		searchController = new StockSearchController(operationView);
		deleteController = new StockDeleteController(operationView);
		saveController = new StockSaveController(operationView);
		operationView.btnDelete.addActionListener(deleteController);
		operationView.btnEmptyField.addActionListener(null);
		operationView.btnGet.addActionListener(searchController);
		operationView.btnSave.addActionListener(saveController);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainView.createFrame(operationView);
	}
}
