import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PlinyWindow extends JFrame {

	Dimension screenSize;
	BorderLayout borderLayout;
	EcosystemPanel ecosystemPanel;
	EcosystemControl ecosystemControl;

	public PlinyWindow(Ecosystem ecosystem){

		super("pliny");
		ecosystemPanel = new EcosystemPanel(ecosystem);
		ecosystemControl = new EcosystemControl(ecosystem);
		new EcosystemControl(ecosystem);
		buildWindow();
	}

	private void buildWindow(){

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenSize);
		add(ecosystemControl, BorderLayout.SOUTH);
		add(ecosystemPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	private void frame(){

		repaint();
	}
}


