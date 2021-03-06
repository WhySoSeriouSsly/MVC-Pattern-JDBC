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

public class StockSaveController implements ActionListener {
	private StockOperationView operationView;
	private StockListView listView;
	private StockListController listController;
	public StockSaveController(StockOperationView operationView) {
		listView=StockCartListFrameController.listView;
		listController=new StockListController(listView);
		this.operationView = operationView;
	}

	public void stockSave(StockModel stock) {
		//StockModel stock = operationView.getData();
		if (Validator.validate(stock) == false) {
			JOptionPane.showMessageDialog(null, Messages.validation);
			return;
		}
		if (Validator.sizeValidation(stock) == false) {
			JOptionPane.showMessageDialog(null, Messages.sizeValidation);
			return;
		}
		if (stock.searchStock(stock.getStokCode()) == null) {
			stock.insertStock();
			JOptionPane.showMessageDialog(null, "Stok Eklendi");
			return;
		}
		JOptionPane.showMessageDialog(null, "Stok GŁncellendi");
		stock.updateStock();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			stockSave(operationView.getData());
			listController.listStock();
	}

}
