package views;

import javax.swing.JInternalFrame;

import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.StockModel;
import models.StockRowModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.Color;

public class StockListView extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	public JTable tblStocks;
	private JTextField txtSearchStock;
	private DefaultTableModel model;
	public JButton btnSearch;

	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
	public JPopupMenu popupMenu;
	public JMenuItem mntmDelete;
	public JMenuItem mntmCopy;
	public JButton btnGetStock;

	public StockListView() {
		super("Document #" + (++openFrameCount), true, true, true, true);
		getContentPane().setBackground(Color.WHITE);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		Init();
		try {
			this.setClosed(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void populateTable(ArrayList<StockRowModel> stockss, ArrayList<String> hours1) {
		model = (DefaultTableModel) tblStocks.getModel();
		model.setRowCount(0);
		ArrayList<StockRowModel> stocks = stockss;
		ArrayList<String> hours = hours1;
		int i = 0;
		for (StockModel stock : stocks) {
			Object[] row = { stock.getStokCode(), stock.getStockName(),
					stock.getBarcode(), stock.getDateOfCreation(),
					hours.get(i)};
			i++;
			model.addRow(row);
		}
		tblStocks.setModel(model);
	}

	public String getData() {
		return txtSearchStock.getText().toString();
	}

	public String selectItem() {
		int row = tblStocks.getSelectedRow();
		if (row == -1) {
			if (tblStocks.getRowCount() == 0) {
				return null;
			} else {
				return null;
			}
		}
		String codeValue = tblStocks.getModel().getValueAt(row, 0).toString();
		return codeValue;
	}

	private void Init() {

		setBounds(100, 100, 817, 390);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblSearchKey = new JLabel("Aranacak Kelime");

		txtSearchStock = new JTextField();
		txtSearchStock.setColumns(10);

		btnSearch = new JButton("Ara");

		btnGetStock = new JButton("Stoklar\u0131 Listele");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 780, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSearchKey)
									.addGap(18)
									.addComponent(txtSearchStock, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 417, Short.MAX_VALUE)
									.addComponent(btnGetStock)
									.addGap(9))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(128)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnGetStock))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSearchKey)
								.addComponent(txtSearchStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearch)))
					.addContainerGap(61, Short.MAX_VALUE))
		);

		tblStocks = new JTable();
		tblStocks.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Stok Kodu", "Stok Ad\u0131", "Barkod", "Tarih", "Saat"
			}
		));

		popupMenu = new JPopupMenu();

		mntmDelete = new JMenuItem("Sil");
		popupMenu.add(mntmDelete);

		mntmCopy = new JMenuItem("Kopyala");
		popupMenu.add(mntmCopy);
		scrollPane.setViewportView(tblStocks);
		getContentPane().setLayout(groupLayout);
	}
}
