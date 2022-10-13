package mendan;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntryMemberFrame extends JFrame {
	
	private JButton entryButton;
	
	private JTextField nameField;
	private JLabel message;
	
	public EntryMemberFrame(int x, int y, int width, int height) {
		setBounds(x, y, width, height);
		setTitle("名前登録");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		int fontSize = width / 16;
		Font font = new Font(Font.DIALOG, Font.PLAIN, fontSize);
		
		JPanel entryPanel = new JPanel();
		getContentPane().add(entryPanel);
		entryPanel.setLayout(null);
		
		entryButton = new JButton("登録");
		entryButton.setSize(width / 4, fontSize / 2 * 3);
		entryButton.setLocation(width / 8 * 3, height / 8 * 5);
		entryButton.setFont(font);
		entryButton.setForeground(Color.WHITE);
		entryButton.setBackground(Color.RED);
		entryPanel.add(entryButton);
		
		nameField = new JTextField();
		nameField.setBounds((width - fontSize * 12) / 2, height / 8, fontSize * 12, fontSize * 3);
		nameField.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize * 2));
		entryPanel.add(nameField);
		
		message = new JLabel();
		message.setBounds(0, height / 32 * 15, width, fontSize);
		message.setFont(font);
		message.setHorizontalAlignment(JLabel.CENTER);
		entryPanel.add(message);
		
	}
	
	public void addMouseListener(MouseListener listener) {
		entryButton.addMouseListener(listener);
	}
	
	public String getName() {
		return nameField.getText();
	}
	
	public void setMessage(String text) {
		message.setText(text);
	}
	
	public void setNameField(String name) {
		nameField.setText(name);
	}
	
}
