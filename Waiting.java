package mendan;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Waiting extends JPanel {
	
	private String name;
	private int time;
	
	private DefaultComboBoxModel<String> model;
	
	public Waiting(String n, String h, String m, int fontSize, Setting setting) {
		name = n;
		time = Integer.parseInt(h) * 100 + Integer.parseInt(m);
		setBackground(new Color(200, 200, 200));
		
		Font font = new Font(Font.DIALOG, Font.PLAIN, fontSize);
		
		JLabel label = new JLabel(name + " " + h + ":" + m + " ");
		label.setFont(font);
		add(label);
		
		String[] array = new String[setting.remarksSize()];
		for (int i = 0; i < array.length; i++) {
			array[i] = setting.getRemarks(i);
		}
		
		model = new DefaultComboBoxModel<>(array);
		JComboBox<String> remarks = new JComboBox<>(model);
		remarks.setFont(font);
		add(remarks);
	}
	
	public String getName() {
		return name;
	}
	
	public int getTime() {
		return time;
	}
	
	public void addRemarks(String word) {
		model.addElement(word);
	}
	
	public void removeRemarks(String word) {
		model.removeElement(word);
	}
	
}
