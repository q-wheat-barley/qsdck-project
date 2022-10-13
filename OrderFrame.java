package mendan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OrderFrame extends JFrame {
	
	private JPanel[] namePanel = new JPanel[4];
	private JButton[] finishedButton = new JButton[namePanel.length];
	
	private String[] move = {"▲", "△", "▽", "▼"};
	private JButton[] moveButton = new JButton[move.length];
	
	private ArrayList<Waiting> schedule = new ArrayList<>();
	
	private int number = 0;
	
	public OrderFrame(int x, int y, int width, int height) {
		setBounds(x, y, width / 4 * 5, height / 4 * 5);
		setTitle("面談リスト");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		int fontSize = width / 16;
		Font font = new Font(Font.DIALOG, Font.PLAIN, fontSize);
		
		JPanel orderPanel = new JPanel();
		orderPanel.setLayout(new BorderLayout());
		orderPanel.setBackground(new Color(200, 200, 200));
		
		JPanel movePanel = new JPanel();
		movePanel.setLayout(new BorderLayout());
		orderPanel.add(movePanel, BorderLayout.CENTER);
		
		for (int i = 0; i < moveButton.length; i++) {
			moveButton[i] = new JButton(move[i]);
			moveButton[i].setFont(font);
		}
		
		orderPanel.add(moveButton[0], BorderLayout.NORTH);
		movePanel.add(moveButton[1], BorderLayout.NORTH);
		movePanel.add(moveButton[2], BorderLayout.SOUTH);
		orderPanel.add(moveButton[3], BorderLayout.SOUTH);
		
		getContentPane().add(orderPanel, BorderLayout.EAST);
		
		JPanel memberPanel = new JPanel();
		memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
		add(memberPanel, BorderLayout.CENTER);
		
		JLabel[] label = new JLabel[namePanel.length];
		for (int i = 0; i < namePanel.length; i++) {
			JPanel backPanel = new JPanel();
			namePanel[i] = new JPanel();
			namePanel[i].setBackground(new Color(200, 200, 200));
			namePanel[i].setPreferredSize(new Dimension(width / 4 * 5, height / 4));
			
			label[i] = new JLabel();
			label[i].setFont(font);
			
			finishedButton[i] = new JButton("完了");
			finishedButton[i].setFont(font);
			
			namePanel[i].add(label[i]);
			backPanel.add(namePanel[i], BorderLayout.WEST);
			memberPanel.add(backPanel);
		}
	}
	
	public void addMouseListener(
		MouseListener[] moveListenerArray,
		MouseListener[] finishedListenerArray) {
			for (int i = 0; i < moveButton.length; i++) {
				moveButton[i].addMouseListener(moveListenerArray[i]);
			}
			for (int i = 0; i < finishedButton.length; i++) {
				finishedButton[i].addMouseListener(finishedListenerArray[i]);
			}
	}
	
	public void sort() {
		for (int i = schedule.size() - 1; i > 0; i--) {
			if (schedule.get(i).getTime() < schedule.get(i - 1).getTime()) {
				Waiting np = schedule.get(i);
				schedule.set(i, schedule.get(i - 1));
				schedule.set(i - 1, np);
			} else {
				break;
			}
		}
	}
	
	public void display() {
		for (int i = 0; i < namePanel.length; i++) {
			namePanel[i].removeAll();
			namePanel[i].setBackground(Color.BLACK);
			namePanel[i].setBackground(new Color(200, 200, 200));
			if (i < schedule.size()) {
				namePanel[i].add(schedule.get(i + number));
				namePanel[i].add(finishedButton[i]);
			}
		}
		setVisible(true);
	}
	
	public void removeName(String name) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).getName().equals(name)) {
				schedule.remove(i);
				if (number > 0) {
					number--;
				}
				display();
				break;
			}
		}
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void addSchedule(Waiting waiting) {
		schedule.add(waiting);
	}
	
	public void removeSchedule(int index) {
		schedule.remove(index);
	}
	
	public int getScheduleSize() {
		return schedule.size();
	}
	
	public String getWaitingName(int index) {
		return schedule.get(index).getName();
	}
	
	public void addRemarks(String word) {
		for (int i = 0; i < schedule.size(); i++) {
			Waiting waiting = schedule.get(i);
			waiting.addRemarks(word);
			schedule.set(i, waiting);
		}
	}
	
	public void removeRemarks(String word) {
		for (int i = 0; i < schedule.size(); i++) {
			Waiting waiting = schedule.get(i);
			waiting.removeRemarks(word);
			schedule.set(i, waiting);
		}
	}
	
	public int getNamePanelSize() {
		return namePanel.length;
	}
	
}
