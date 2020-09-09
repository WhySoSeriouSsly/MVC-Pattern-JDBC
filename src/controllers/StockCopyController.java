package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import business.utilities.Messages;
import models.StockModel;
import views.CopyView;
import views.StockListView;

public class StockCopyController implements ActionListener {
	private CopyView copyView;
	private StockListView listView;
	private StockListController listController;

	public StockCopyController(CopyView copyView, StockListView listView) {
		this.copyView = copyView;
		this.listView = listView;
		listController = new StockListController(listView);
	}

	public void copyStock() {
		StockModel stock = new StockModel();
		StockModel oldStock = stock.searchStock(listView.selectItem());
		if (stock.searchStock(copyView.getData()) == null) {
			oldStock.setStokCode(copyView.getData());
			oldStock.insertStock();
			JOptionPane.showMessageDialog(null, Messages.stockAdded);
			listController.listStock();
			return;
		}
		JOptionPane.showMessageDialog(null, Messages.stockAlreadyHave);
		return;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		copyStock();
	}
}
