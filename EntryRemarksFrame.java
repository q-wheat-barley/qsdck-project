package mendan;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntryRemarksFrame extends JFrame {
	
	private JButton entryButton;
	
	private JTextField wordField;
	private JLabel message;
	
	public EntryRemarksFrame(int x, int y, int width, int height) {
		setBounds(x, y, width, height);
		setTitle("備考登録");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		int fontSize = width / 16;
		Font font = new Font(Font.DIALOG, Font.PLAIN, fontSize);
		
		JPanel entryPanel = new JPanel();
		getContentPane().add(entryPanel);
		entryPanel.setLayout(null);
		
		entryButton = new JButton("登録");
		entryButton.setBounds(width / 8 * 3, height / 8 * 5, width / 4, fontSize * 3 / 2);
		entryButton.setFont(font);
		entryButton.setForeground(Color.WHITE);
		entryButton.setBackground(Color.RED);
		entryPanel.add(entryButton);
		
		wordField = new JTextField();
		wordField.setBounds((width - fontSize * 8) / 2, height / 8, fontSize * 8, fontSize * 3);
		wordField.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize * 2));
		entryPanel.add(wordField);
		
		message = new JLabel();
		message.setBounds(0, height * 15 / 32, width, fontSize);
		message.setFont(font);
		message.setHorizontalAlignment(JLabel.CENTER);
		entryPanel.add(message);
	}
	
	public void addMouseListener(MouseListener listener) {
		entryButton.addMouseListener(listener);
	}
	
	public String getWord() {
		return wordField.getText();
	}
	
	public void setMessage(String text) {
		message.setText(text);
	}
	
	public void setWordField(String text) {
		wordField.setText(text);
	}
	
}
