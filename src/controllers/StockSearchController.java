package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import business.Validator;
import business.utilities.Messages;
import models.StockModel;
import views.StockOperationView;

public class StockSearchController implements ActionListener {

	StockOperationView operationView;
	public StockSearchController(StockOperationView operationView) {
		this.operationView=operationView;
	}
	public StockModel searchStock(String code) {
		//StockModel stock=operationView.getData();
		if(Validator.validate(code)==false) {
			JOptionPane.showMessageDialog(null, Messages.validateStock);
			return null;
		}
		StockModel stock=new StockModel(code);
		if(stock.searchStock(stock.getStokCode())==null) {
			JOptionPane.showMessageDialog(null, Messages.stockNotFound);
			return null;
		}
		return stock.searchStock(stock.getStokCode());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		operationView.setData(searchStock(operationView.getTxtStokKodu()));
	}

}
