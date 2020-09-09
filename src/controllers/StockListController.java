package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.StockModel;
import views.StockListView;

public class StockListController implements ActionListener {
	public StockModel stockModel;
	private StockListView listView;
	public StockListController(StockListView listView) {
		this.listView=listView;
		stockModel = new StockModel();
	}
	public void listStock() {
		listView.populateTable(stockModel.getAllStock(), stockModel.getAllHour());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		listStock();
	}
}
