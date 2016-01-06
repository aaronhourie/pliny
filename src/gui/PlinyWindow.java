package gui;

import engine.Ecosystem;

import javax.swing.*;
import java.awt.*;

public class PlinyWindow extends JFrame {

	Dimension screenSize;
	SimulationPane simulationPane;
	JPanel controlPanel;
	JPanel infoPanel;
	JPanel mainPanel;

	public PlinyWindow(Ecosystem ecosystem){

		super("pliny");

		simulationPane = new SimulationPane(ecosystem);
		controlPanel = new JPanel();
		infoPanel = new JPanel();
		mainPanel = new JPanel();

		mainPanel.setLayout(new BorderLayout());

		buildWindow();
	}

	private void buildWindow(){

		// Build up screen size.
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenSize);


		// Sim panel
		mainPanel.add(simulationPane, BorderLayout.CENTER);

		// Control panel.
		mainPanel.add(controlPanel, BorderLayout.EAST);

		// Info panel.
		mainPanel.add(infoPanel, BorderLayout.SOUTH);

		// Add main panel to window.
		add(mainPanel);

		setVisible(true);
	}

	public void update(){

	}
}


