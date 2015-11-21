import javax.swing.*;
import java.awt.*;

public class EcosystemControl extends JPanel {

	private Ecosystem ecosystem;
	private JLabel l_temperature;
	private JTextField temperatureField;
	private JButton applyTemperature;
	private FlowLayout layout;

	public EcosystemControl(Ecosystem ecosystem){

		l_temperature = new JLabel();
		temperatureField = new JTextField(10);
		applyTemperature = new JButton("Apply Temperature");
		layout = new FlowLayout();
		this.ecosystem = ecosystem;

		build();
	}

	public void build(){

		l_temperature.setText("Temperature: ");
		temperatureField.setText(ecosystem.getTemperature()+"");

		add(l_temperature, layout);
		add(temperatureField, layout);
		add(applyTemperature, layout);
	}
}
