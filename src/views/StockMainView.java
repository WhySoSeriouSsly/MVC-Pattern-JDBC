package views;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class StockMainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JMenuItem mnýtmStokList;
	public JMenuItem mnýtmStokIslemleri;
	public JDesktopPane desktopPane;
	private JLabel lblNewLabel;
	public JButton btnNewButton;
	public JInternalFrame stockListFrame;
	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;

	public StockMainView() {
		Init();
		this.setVisible(false);
	}

	private void Init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1082, 780);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnStok = new JMenu("Stok");
		menuBar.add(mnStok);

		mnýtmStokList = new JMenuItem("Stok Listesi");
		mnStok.add(mnýtmStokList);

		mnýtmStokIslemleri = new JMenuItem("Stok Ýþlemleri");
		mnStok.add(mnýtmStokIslemleri);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.DARK_GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(desktopPane,
				GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE));

		lblNewLabel = new JLabel("HO\u015EGELD\u0130N\u0130Z");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(355, 259, 448, 73);
		desktopPane.add(lblNewLabel);

		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(158, 164, 89, 23);
		desktopPane.add(btnNewButton);

		stockListFrame = new JInternalFrame("Document #" + (++openFrameCount), true, true, true, true);
		stockListFrame.setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		stockListFrame.setBounds(26, 27, 192, 260);

		JInternalFrame internalFrame_1 = new JInternalFrame("New JInternalFrame");
		internalFrame_1.setBounds(89, 11, 338, 260);
		internalFrame_1.setVisible(true);
		stockListFrame.setVisible(true);
		contentPane.setLayout(gl_contentPane);
	}

	public void createFrame(JInternalFrame frame) {
		if (frame.isVisible() == false) {
			frame.setVisible(true);
			desktopPane.add(frame);
			try {
				frame.setSelected(true);
			} catch (java.beans.PropertyVetoException e) {
				System.out.println(e);
			}
			setContentPane(desktopPane);
			desktopPane.putClientProperty("JDesktopPane.dragMode", "outline");
		} else {
			return;
		}
	}
}
