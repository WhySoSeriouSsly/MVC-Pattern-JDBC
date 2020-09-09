package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import business.Validator;
import business.utilities.Messages;
import framecontrollers.StockCartListFrameController;
import models.StockModel;
import views.StockListView;
import views.StockOperationView;

public class StockDeleteController implements ActionListener {
	private StockListController listController;
	private StockOperationView operationView;
	private StockListView listView;
	public StockDeleteController(StockOperationView operationView) {
		listView=StockCartListFrameController.listView;
		listController=new StockListController(listView);
		this.operationView = operationView;
	}
	public void deleteStock(String stockCode) {
		if(Validator.validate(stockCode)==false) {
			JOptionPane.showMessageDialog(null, Messages.validateStock);
			return;
		}
		StockModel stock = new StockModel(stockCode);
		if (stock.searchStock(stock.getStokCode()) == null) {
			JOptionPane.showMessageDialog(null, Messages.stockNotFound);
			return;
		}
		stock.deleteStock();
		JOptionPane.showMessageDialog(null, Messages.stockDeleted);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		deleteStock(operationView.getTxtStokKodu());
		listController.listStock();
	}
}
