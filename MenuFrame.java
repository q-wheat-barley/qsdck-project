package mendan;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuFrame extends JFrame {
	
	private int fontSize;
	private Font font;
	
	private JPanel menuPanel;
	
	private JButton deleteMemberButton;
	private JButton entryMemberButton;
	
	private JButton deleteRemarksButton;
	private JButton entryRemarksButton;
	
	private JButton orderButton;
	
	private DefaultComboBoxModel<String> memberModel;
	private JComboBox<String> memberCombo;
	private JComboBox<String> hourCombo;
	private JComboBox<String> minuteCombo;
	private JButton additionButton;
	
	public MenuFrame(int x, int y, int width, int height, Setting setting) {
		setBounds(x, y, width, height);
		setTitle("予定追加画面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		fontSize = width / 16;
		font = new Font(Font.DIALOG, Font.PLAIN, fontSize);
		
		menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setBackground(new Color(220, 220, 220));
		getContentPane().add(menuPanel);
		
		JLabel nameLabel = new JLabel("名前");
		nameLabel.setFont(font);
		nameLabel.setBounds(width / 32 * 18, height / 16, fontSize * 6, fontSize / 2 * 3);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		menuPanel.add(nameLabel);
		deleteMemberButton = setButton("削除", width / 32 * 18, height / 16 * 3, new Color(255, 120, 120));
		entryMemberButton = setButton("登録", width / 32 * 24, height / 16 * 3, new Color(100, 100, 255));
		
		JLabel remarksLabel = new JLabel("備考");
		remarksLabel.setFont(font);
		remarksLabel.setBounds(width / 32 * 18, height / 8 * 3, fontSize * 6, fontSize / 2 * 3);
		remarksLabel.setHorizontalAlignment(JLabel.CENTER);
		menuPanel.add(remarksLabel);
		deleteRemarksButton = setButton("削除", width / 32 * 18, height / 2, new Color(255, 120, 120));
		entryRemarksButton = setButton("登録", width / 32 * 24, height / 2, new Color(100, 100, 255));
		
		orderButton = setButton("表示", width / 32 * 21, height / 16 * 11, new Color(200, 0, 200));
		orderButton.setSize(fontSize * 3, fontSize / 2 * 3);
		
		JPanel toSetPanel = new JPanel();
		toSetPanel.setLayout(null);
		toSetPanel.setBounds(0, 0, width / 2, height);
		menuPanel.add(toSetPanel);
		
		String[] names = new String[setting.memberSize()];
		for (int i = 0; i < setting.memberSize(); i++) {
			names[i] = setting.getMember(i);
		}
		memberModel = new DefaultComboBoxModel<>(names);
		memberCombo = new JComboBox<>(memberModel);
		memberCombo.setBounds(width / 32, height / 4, fontSize * 7, fontSize / 2 * 3);
		memberCombo.setFont(font);
		toSetPanel.add(memberCombo);
		
		String[] hour = new String[24];
		for (int i = 0; i < hour.length; i++) {
			hour[i] = String.format("%02d", i);
		}
		
		hourCombo = new JComboBox<>(hour);
		hourCombo.setBounds(width / 8, height / 16 * 7, fontSize / 4 * 7, fontSize / 2 * 3);
		hourCombo.setFont(font);
		toSetPanel.add(hourCombo);
		
		String[] minute = new String[12];
		for (int i = 0; i < minute.length; i++) {
			minute[i] = String.format("%02d", i * 5);
		}
		
		minuteCombo = new JComboBox<>(minute);
		minuteCombo.setBounds(width / 32 * 9, height / 16 * 7, fontSize / 4 * 7, fontSize / 2 * 3);
		minuteCombo.setFont(font);
		toSetPanel.add(minuteCombo);
		
		JLabel colonLabel = new JLabel(":");
		colonLabel.setBounds(width / 4, height / 64 * 29, fontSize, fontSize);
		colonLabel.setFont(font);
		toSetPanel.add(colonLabel);
		
		additionButton = new JButton("追加");
		additionButton.setBounds(width / 32 * 5, height / 16 * 11, fontSize * 3, fontSize / 2 * 3);
		additionButton.setBackground(new Color(255, 255, 150));
		additionButton.setFont(font);
		toSetPanel.add(additionButton);
	}
	
	public void addMouseListener(
		MouseListener deleteMemberListener,
		MouseListener entryMemberListener,
		MouseListener deleteRemarksListener,
		MouseListener entryRemarksListener,
		MouseListener additionListener,
		MouseListener orderListener) {
			deleteMemberButton.addMouseListener(deleteMemberListener);
			entryMemberButton.addMouseListener(entryMemberListener);
			deleteRemarksButton.addMouseListener(deleteRemarksListener);
			entryRemarksButton.addMouseListener(entryRemarksListener);
			additionButton.addMouseListener(additionListener);
			orderButton.addMouseListener(orderListener);
	}
	
	private JButton setButton(String text, int x, int y, Color color) {
		JButton button = new JButton(text);
		button.setFont(font);
		button.setBounds(x, y, fontSize * 3, fontSize / 2 * 3);
		button.setForeground(Color.WHITE);
		button.setBackground(color);
		menuPanel.add(button);
		return(button);
	}
	
	public void removeMember(String name) {
		memberModel.removeElement(name);
	}
	
	public void addMember(String name) {
		memberModel.addElement(name);
	}
	
	public int getMemberSize() {
		return memberModel.getSize();
	}
	
	public String getSelectedMember() {
		return (String)memberCombo.getSelectedItem();
	}
	
	public String getSelectedHour() {
		return (String)hourCombo.getSelectedItem();
	}
	
	public String getSelectedMinute() {
		return (String)minuteCombo.getSelectedItem();
	}
	
}
