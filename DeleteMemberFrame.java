package mendan;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DeleteMemberFrame extends JFrame {
	
	private JButton deleteButton;
	
	private DefaultComboBoxModel<String> memberModel;
	private JComboBox<String> memberCombo;
	
	public DeleteMemberFrame(int x, int y, int width, int height, Setting setting) {
		setBounds(x, y, width, height);
		setTitle("名前削除");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		int fontSize = width / 16;
		
		JPanel deletePanel = new JPanel();
		deletePanel.setLayout(null);
		
		getContentPane().add(deletePanel);
		
		deleteButton = new JButton("削除");
		deleteButton.setSize(width / 4, fontSize / 2 * 3);
		deleteButton.setLocation(width / 8 * 3, height / 8 * 5);
		deleteButton.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize));
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setBackground(Color.RED);
		deletePanel.add(deleteButton);
		
		String[] names = new String[setting.memberSize()];
		for (int i = 0; i < setting.memberSize(); i++) {
			names[i] = setting.getMember(i);
		}
		memberModel = new DefaultComboBoxModel<>(names);
		memberCombo = new JComboBox<>(memberModel);
		memberCombo.setBounds((width - fontSize * 14) / 2, height / 8, fontSize * 14, fontSize * 3);
		memberCombo.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize * 2));
		deletePanel.add(memberCombo);
		
	}
	
	public void addMouseListener(MouseListener listener) {
		deleteButton.addMouseListener(listener);
	}
	
	public String getSelectedMember() {
		return (String)memberCombo.getSelectedItem();
	}
	
	public void addMember(String member) {
		memberModel.addElement(member);
	}
	
	public void removeMember(String member) {
		memberModel.removeElement(member);
	}
	
}
