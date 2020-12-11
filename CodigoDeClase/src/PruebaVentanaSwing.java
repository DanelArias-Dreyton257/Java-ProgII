import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.*;

public class PruebaVentanaSwing {
	private static JFrame win = new JFrame("");
	private static JPanel topPanel = new JPanel();
	private static JPanel botPanel = new JPanel();
	private static JPanel centralPanel = new JPanel(new GridLayout(1, 2));
	private static JPanel leftPanel = new JPanel();
	private static JPanel rightPanel = new JPanel(new GridLayout(2,1));
	private static JPanel rUpPanel = new JPanel();
	private static JPanel rBotPanel = new JPanel();
	static {
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setLocation(0, 0);
		win.add(botPanel,BorderLayout.SOUTH);
		win.add(topPanel,BorderLayout.NORTH);
		rightPanel.add(rUpPanel);
		rightPanel.add(rBotPanel);
		centralPanel.add(leftPanel);
		centralPanel.add(rightPanel);
		win.add(centralPanel,BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		
		setTitle("Pedro come ovejas");
		setSize(700, 700);
	
		JLabel label = new JLabel("VIVA ESPANIA");
		JTextField txtInput = new JTextField(10);
		
		addToTopPanel(label,txtInput);
		
		setVisible(true);
	}
	
	
	public static void addToPanel(JPanel panel,Component ...components) {
		for (Component c:components) panel.add(c);
	}
	public static void addToTopPanel(Component ...components) {
		addToPanel(topPanel, components);
	}
	public static void addToBotPanel(Component ...components) {
		addToPanel(botPanel, components);
	}
	public static void addToLeftPanel(Component ...components) {
		addToPanel(leftPanel, components);
	}
	public static void addToRightUpPanel(Component ...components) {
		addToPanel(rUpPanel, components);
	}
	public static void addToRightBottomPanel(Component ...components) {
		addToPanel(rBotPanel, components);
	}
	public static void setTitle(String title) {
		win.setTitle(title);
	}
	public static void setSize(int width,int height) {
		win.setSize(width,height);
	}
	public static void setVisible(boolean visible) {
		win.setVisible(visible);
	}
}
