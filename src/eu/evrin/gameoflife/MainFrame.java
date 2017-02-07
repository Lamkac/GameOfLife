package eu.evrin.gameoflife;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MainFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		CellController cc = new CellController(10,10);
		
		ControlThread ct = new ControlThread(cc);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DrawPanel drawPanel = new DrawPanel(cc);
		frame.getContentPane().add(drawPanel, BorderLayout.CENTER);
		
		SettingPanel settingPanel = new SettingPanel(cc,drawPanel,ct);
		frame.getContentPane().add(settingPanel, BorderLayout.EAST);
	}

}
