package eu.evrin.gameoflife;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;

public class SettingPanel extends JPanel implements ActionListener, ChangeListener, CellsListener {

	private CellController cc;
	private DrawPanel dp;
	private ControlThread ct;
	private JButton btnPlay;
	private JSpinner spinner;
	private JSlider sliderSize;
	private JLabel lblGridSize;
	private JCheckBox chckbxGrid;
	private JLabel lblGeneration;
	/**
	 * Create the panel.
	 */
	public SettingPanel(CellController cc,DrawPanel dp,ControlThread ct) {
		this.cc=cc;
		this.dp=dp;
		this.ct=ct;
		this.setPreferredSize(new Dimension(200,800));
		
		btnPlay = new JButton("PLAY");
		btnPlay.setBounds(10, 5, 80, 23);
		btnPlay.addActionListener(this);
		setLayout(null);
		add(btnPlay);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setBounds(110, 5, 80, 23);
		btnReset.addActionListener(this);
		add(btnReset);
		
		JLabel lblSimulationSpeed = new JLabel("Simulation speed:");
		lblSimulationSpeed.setBounds(10, 42, 108, 14);
		add(lblSimulationSpeed);
		
		spinner = new JSpinner();
		spinner.setBounds(126, 39, 64, 20);
		spinner.addChangeListener(this);
		spinner.setModel(new SpinnerNumberModel(new Long(1000), new Long(0), null, new Long(1)));
		add(spinner);
		
		lblGridSize = new JLabel("Grid size: 10");
		lblGridSize.setBounds(10, 67, 80, 14);
		add(lblGridSize);
		
		sliderSize = new JSlider();
		sliderSize.setSnapToTicks(true);
		sliderSize.setMaximum(50);
		sliderSize.addChangeListener(this);
		sliderSize.setMinimum(1);
		sliderSize.setValue(10);
		sliderSize.setBounds(10, 86, 180, 26);
		add(sliderSize);
		
		chckbxGrid = new JCheckBox("Grid");
		chckbxGrid.addActionListener(this);
		chckbxGrid.setActionCommand("GRID");
		chckbxGrid.setSelected(true);
		chckbxGrid.setBounds(136, 63, 54, 23);
		add(chckbxGrid);
		
		lblGeneration = new JLabel("Generation: 0");
		lblGeneration.setBounds(10, 123, 180, 14);
		add(lblGeneration);

		cc.addListener(this);
	}
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
			case"PLAY":
				ct.running=true;
				btnPlay.setText("STOP");
				break;
			case"STOP":
				ct.running=false;
				btnPlay.setText("PLAY");
				break;
			case"RESET":
				cc.reset();
				break;
			case"GRID":
				dp.grid=chckbxGrid.isSelected();
				dp.repaint();
				break;
		}
	}

	public void stateChanged(ChangeEvent e) {
		if(e.getSource()==spinner){
			ct.sleepTime=(long) spinner.getValue();
			return;
		}

		if(e.getSource()==sliderSize){
			dp.size=sliderSize.getValue();
			dp.changeSize();
			lblGridSize.setText("Grid size: "+String.valueOf(sliderSize.getValue()));
			return;
		}
	}
	@Override
	public void onCellsChange() {
		lblGeneration.setText("Generation: "+cc.getGeneration());
	}
}
