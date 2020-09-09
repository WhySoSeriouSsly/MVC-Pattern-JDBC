package framecontrollers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.StockCopyController;
import views.CopyView;
import views.StockListView;

public class StockCartCopyFrameController implements ActionListener {
	private CopyView copyView;
	private StockCopyController copyController;
	StockListView listView;
	public StockCartCopyFrameController(StockListView listView) {
		copyView=new CopyView();
		this.listView=listView;
		copyController=new StockCopyController(copyView,listView);
		copyView.btnCopyButton.addActionListener(copyController);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		StockCartMainFrameController.mainView.createFrame(copyView);
	}
}
