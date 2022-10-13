package mendan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DeleteRemarksFrame extends JFrame {
	
	private JButton deleteButton;
	
	private DefaultComboBoxModel<String> remarksModel;
	private JComboBox<String> remarksCombo;
	
	public DeleteRemarksFrame(int x, int y, int width, int height, Setting setting) {
		setBounds(x, y, width, height);
		setTitle("備考削除");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		int fontSize = width / 16;
		
		JPanel deletePanel = new JPanel();
		deletePanel.setLayout(null);
		getContentPane().add(deletePanel, BorderLayout.CENTER);
		
		String[] words = new String[setting.remarksSize()];
		for (int i = 0; i < words.length; i++) {
			words[i] = setting.getRemarks(i);
		}
		remarksModel = new DefaultComboBoxModel<>(words);
		remarksCombo = new JComboBox<>(remarksModel);
		remarksCombo.setBounds((width - fontSize * 10) / 2, height / 8, fontSize * 10, fontSize * 3);
		remarksCombo.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize * 2));
		deletePanel.add(remarksCombo);
		
		deleteButton = new JButton("削除");
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setBackground(Color.RED);
		deleteButton.setBounds(width / 8 * 3, height / 8 * 5, width / 4, fontSize / 2 * 3);
		deleteButton.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize));
		deletePanel.add(deleteButton);
		
	}
	
	public void addMouseListener(MouseListener listener) {
		deleteButton.addMouseListener(listener);
	}
	
	public String getSelectedRemarks() {
		return (String)remarksCombo.getSelectedItem();
	}
	
	public void addRemarks(String word) {
		remarksModel.addElement(word);
	}
	
	public void removeRemarks(String word) {
		remarksModel.removeElement(word);
	}
	
}
