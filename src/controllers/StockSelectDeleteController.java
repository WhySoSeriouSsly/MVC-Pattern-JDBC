package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import business.utilities.Messages;
import models.StockModel;
import views.StockListView;

public class StockSelectDeleteController implements ActionListener{
	private StockListView listView;
	private StockListController listController;
	public StockSelectDeleteController(StockListView listView) {
		this.listView=listView;
		listController=new StockListController(listView);
	}
	public void selectDeleteStock() {
		StockModel stock=new StockModel();
		stock.setStokCode(listView.selectItem());
		stock.deleteStock();
		JOptionPane.showMessageDialog(null, Messages.stockDeleted);
		listController.listStock();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		selectDeleteStock();
	}
}
