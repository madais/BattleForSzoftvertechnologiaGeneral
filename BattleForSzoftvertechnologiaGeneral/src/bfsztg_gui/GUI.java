package bfsztg_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class GUI {

	private JFrame frmBattleForSzoftvertechnolgia;
	private JTextArea txtrChattextarea;
	private JTextField chatTextField;
	private GameCanvasPanel gameCanvasPanel;
	JButton btnInfantry;
	JButton btnArcher;
	JButton btnCavalry;
	JButton btnEndTurn;
	JButton btnSend;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmBattleForSzoftvertechnolgia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getFrmBattleForSzoftvertechnolgia() {
		return frmBattleForSzoftvertechnolgia;
	}

	public void setFrmBattleForSzoftvertechnolgia(
			JFrame frmBattleForSzoftvertechnolgia) {
		this.frmBattleForSzoftvertechnolgia = frmBattleForSzoftvertechnolgia;
	}

	public JTextArea getTxtrChattextarea() {
		return txtrChattextarea;
	}

	public void setTxtrChattextarea(JTextArea txtrChattextarea) {
		this.txtrChattextarea = txtrChattextarea;
	}

	public JTextField getChatTextField() {
		return chatTextField;
	}

	public void setChatTextField(JTextField chatTextField) {
		this.chatTextField = chatTextField;
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final GUI thisGUI = this;
		frmBattleForSzoftvertechnolgia = new JFrame();
		frmBattleForSzoftvertechnolgia
				.setTitle("Battle for Szoftvertechnologia Generals");
		frmBattleForSzoftvertechnolgia.setBounds(100, 100, 648, 390);
		frmBattleForSzoftvertechnolgia
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBattleForSzoftvertechnolgia.getContentPane().setLayout(
				new BorderLayout(0, 0));
		frmBattleForSzoftvertechnolgia.setExtendedState(JFrame.MAXIMIZED_BOTH);

		JMenuBar menuBar = new JMenuBar();
		frmBattleForSzoftvertechnolgia.getContentPane().add(menuBar,
				BorderLayout.NORTH);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ExitMenuAction());
		mnFile.add(mntmExit);

		JMenu mnConnection = new JMenu("Connection");
		menuBar.add(mnConnection);

		JMenuItem mntmHost = new JMenuItem("Host");
		mntmHost.addActionListener(new HostMenuAction());
		mnConnection.add(mntmHost);

		JMenuItem mntmConnect = new JMenuItem("Connect");
		mntmConnect.addActionListener(new ConnectMenuAction());
		mnConnection.add(mntmConnect);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new HelpMenuAction());
		mnHelp.add(mntmHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new AboutMenuAction());
		mnHelp.add(mntmAbout);

		JPanel chatPanel = new JPanel();
		chatPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		frmBattleForSzoftvertechnolgia.getContentPane().add(chatPanel,
				BorderLayout.SOUTH);
		chatPanel.setLayout(new BorderLayout(0, 0));

		txtrChattextarea = new JTextArea();
		txtrChattextarea.setEditable(false);
		txtrChattextarea.setRows(5);
		txtrChattextarea.setText("initializing...\n");
		JScrollPane txtrChattextareaScroll = new JScrollPane(txtrChattextarea);
		txtrChattextareaScroll
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatPanel.add(txtrChattextareaScroll, BorderLayout.NORTH);

		JSeparator chatSeparator = new JSeparator();
		chatPanel.add(chatSeparator, BorderLayout.WEST);

		JPanel chatInputPanel = new JPanel();
		chatPanel.add(chatInputPanel, BorderLayout.SOUTH);
		chatInputPanel.setLayout(new BorderLayout(0, 0));

		chatTextField = new JTextField();
		chatTextField.setText("Chat");
		chatTextField.setColumns(100);
		chatInputPanel.add(chatTextField, BorderLayout.WEST);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thisGUI.sendChatText();//DANI
			}

		});
		chatInputPanel.add(btnSend, BorderLayout.CENTER);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thisGUI.clearChat();
			}
		});
		chatInputPanel.add(btnClear, BorderLayout.EAST);

		gameCanvasPanel = new GameCanvasPanel(this);
		gameCanvasPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		frmBattleForSzoftvertechnolgia.getContentPane().add(gameCanvasPanel,
				BorderLayout.CENTER);
		Dimension mapPeferredSize = new Dimension(1200,600);
		gameCanvasPanel.setPreferredSize(mapPeferredSize);//1200x600

		
		JPanel sideBarPanel = new JPanel();
		sideBarPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		frmBattleForSzoftvertechnolgia.getContentPane().add(sideBarPanel,
				BorderLayout.EAST);
		sideBarPanel.setLayout(new BorderLayout(0, 0));

		JPanel newUnitsPanel = new JPanel();
		sideBarPanel.add(newUnitsPanel, BorderLayout.NORTH);
		GridBagLayout gbl_newUnitsPanel = new GridBagLayout();
		gbl_newUnitsPanel.columnWidths = new int[] { 0, 0 };
		gbl_newUnitsPanel.rowHeights = new int[] { 0, 0, 0, 0, 20, 0 };
		gbl_newUnitsPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_newUnitsPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		newUnitsPanel.setLayout(gbl_newUnitsPanel);

		JLabel lblRecruit = new JLabel("Recruit:");
		GridBagConstraints gbc_lblRecruit = new GridBagConstraints();
		gbc_lblRecruit.insets = new Insets(0, 0, 5, 0);
		gbc_lblRecruit.gridx = 0;
		gbc_lblRecruit.gridy = 0;
		newUnitsPanel.add(lblRecruit, gbc_lblRecruit);

		btnInfantry = new JButton("Infantry");
		GridBagConstraints gbc_btnInfantry = new GridBagConstraints();
		gbc_btnInfantry.insets = new Insets(0, 0, 5, 0);
		gbc_btnInfantry.gridx = 0;
		gbc_btnInfantry.gridy = 1;
		btnInfantry.setPreferredSize(new Dimension(100, 50));
		newUnitsPanel.add(btnInfantry, gbc_btnInfantry);

		btnArcher = new JButton("Archers");
		GridBagConstraints gbc_btnArcher = new GridBagConstraints();
		gbc_btnArcher.insets = new Insets(0, 0, 5, 0);
		gbc_btnArcher.gridx = 0;
		gbc_btnArcher.gridy = 2;
		btnArcher.setPreferredSize(new Dimension(100, 50));
		
		newUnitsPanel.add(btnArcher, gbc_btnArcher);

		btnCavalry = new JButton("Cavalry");
		GridBagConstraints gbc_btnCavalry = new GridBagConstraints();
		gbc_btnCavalry.insets = new Insets(0, 0, 5, 0);
		gbc_btnCavalry.gridx = 0;
		gbc_btnCavalry.gridy = 3;
		btnCavalry.setPreferredSize(new Dimension(100, 50));
		newUnitsPanel.add(btnCavalry, gbc_btnCavalry);

		JPanel endWaitDefendPanel = new JPanel();
		sideBarPanel.add(endWaitDefendPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_endWaitDefendPanel = new GridBagLayout();
		gbl_endWaitDefendPanel.columnWidths = new int[] { 0, 0 };
		gbl_endWaitDefendPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_endWaitDefendPanel.columnWeights = new double[] { 0.0,
				Double.MIN_VALUE };
		gbl_endWaitDefendPanel.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		endWaitDefendPanel.setLayout(gbl_endWaitDefendPanel);

		JButton btnWait = new JButton("Wait");
		GridBagConstraints gbc_btnWait = new GridBagConstraints();
		gbc_btnWait.insets = new Insets(0, 0, 5, 0);
		gbc_btnWait.gridx = 0;
		gbc_btnWait.gridy = 0;
		btnWait.setPreferredSize(new Dimension(100, 50));
		btnWait.addActionListener(new WaitActionListener());
		endWaitDefendPanel.add(btnWait, gbc_btnWait);

		JButton btnDefend = new JButton("Defend");
		GridBagConstraints gbc_btnDefend = new GridBagConstraints();
		gbc_btnDefend.insets = new Insets(0, 0, 5, 0);
		gbc_btnDefend.gridx = 0;
		gbc_btnDefend.gridy = 1;
		btnDefend.setPreferredSize(new Dimension(100, 50));
		btnDefend.addActionListener(new DefendActionListener());
		endWaitDefendPanel.add(btnDefend, gbc_btnDefend);

		btnEndTurn = new JButton("End turn");
		GridBagConstraints gbc_btnEndTurn = new GridBagConstraints();
		gbc_btnEndTurn.gridx = 0;
		gbc_btnEndTurn.gridy = 2;
		btnEndTurn.setPreferredSize(new Dimension(100, 50));
		endWaitDefendPanel.add(btnEndTurn, gbc_btnEndTurn);		
	}

	public void clearChat() {
		if (getTxtrChattextarea() != null) {
			getTxtrChattextarea().setText("");
		}
	}

	public void sendChatText() {
		if (getTxtrChattextarea() != null) {
			if (getChatTextField() != null) {
				getTxtrChattextarea().append(
						getChatTextField().getText() + "\n");
				getChatTextField().setText("");
			}
		}
	}

	public void appendToChat(String string) {
		if (getTxtrChattextarea() != null) {
			getTxtrChattextarea().append(string);
		}
	}

	public GameCanvasPanel getGameCanvasPanel() {
		return gameCanvasPanel;
	}

	public void setGameCanvasPanel(GameCanvasPanel gameCanvasPanel) {
		this.gameCanvasPanel = gameCanvasPanel;
	}
	
	public void enableUnitRecruiting(){
		btnInfantry.setEnabled(true);
		btnArcher.setEnabled(true);
		btnCavalry.setEnabled(true);
	}
	
	public void disableUnitRecruiting(){
		btnInfantry.setEnabled(false);
		btnArcher.setEnabled(false);
		btnCavalry.setEnabled(false);
	}

	public JButton getBtnInfantry() {
		return btnInfantry;
	}

	public void setBtnInfantry(JButton btnInfantry) {
		this.btnInfantry = btnInfantry;
	}

	public JButton getBtnArcher() {
		return btnArcher;
	}

	public void setBtnArcher(JButton btnArcher) {
		this.btnArcher = btnArcher;
	}

	public JButton getBtnCavalry() {
		return btnCavalry;
	}

	public void setBtnCavalry(JButton btnCavalry) {
		this.btnCavalry = btnCavalry;
	}

	public JButton getBtnEndTurn() {
		return btnEndTurn;
	}

	public void setBtnEndTurn(JButton btnEndTurn) {
		this.btnEndTurn = btnEndTurn;
	}
	
	
}