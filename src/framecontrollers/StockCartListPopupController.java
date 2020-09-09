package framecontrollers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import views.StockListView;
import views.StockOperationView;

public class StockCartListPopupController implements MouseListener {
	public StockListView listView;

	public StockCartListPopupController(StockListView listView) {
		this.listView = listView;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton() == java.awt.event.MouseEvent.BUTTON3) {
			listView.popupMenu.show(listView.tblStocks, arg0.getX(), arg0.getY());
			return;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
