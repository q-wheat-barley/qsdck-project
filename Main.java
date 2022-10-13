package mendan;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
	
	//default = 512, 768, 128, 128
	final int HEIGHT = 512, WIDTH = HEIGHT / 2 * 3, X = HEIGHT / 4, Y = HEIGHT / 4;
	
	private MenuFrame menuFrame;
	private DeleteMemberFrame deleteMemberFrame;
	private EntryMemberFrame entryMemberFrame;
	private DeleteRemarksFrame deleteRemarksFrame;
	private EntryRemarksFrame entryRemarksFrame;
	private OrderFrame orderFrame;
	
	private Setting setting;
	private String fileName = "setting";
	
	public static void main(String[] args) {
		Main main = new Main();
		main.menuFrame.setVisible(true);
	}
	
	public Main() {
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
			setting = (Setting)input.readObject();
		} catch (FileNotFoundException e) {
			setting = new Setting();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		menuFrame = new MenuFrame(X, Y, WIDTH, HEIGHT, setting);
		deleteMemberFrame = new DeleteMemberFrame(HEIGHT * 2, Y * 2, WIDTH / 2, HEIGHT / 2, setting);
		entryMemberFrame = new EntryMemberFrame(HEIGHT * 2, Y * 2, WIDTH / 2, HEIGHT / 2);
		deleteRemarksFrame = new DeleteRemarksFrame(HEIGHT * 2, Y * 2, WIDTH / 2, HEIGHT / 2, setting);
		entryRemarksFrame = new EntryRemarksFrame(HEIGHT * 2, Y * 2, WIDTH / 2, HEIGHT / 2);
		orderFrame = new OrderFrame(X * 4, Y, WIDTH, HEIGHT);
		
		menuFrame.addMouseListener(
			new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					deleteMemberFrame.setVisible(true);
				}
			},
			new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					entryMemberFrame.setVisible(true);
				}
			},
			new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					deleteRemarksFrame.setVisible(true);
				}
			},
			new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					entryRemarksFrame.setVisible(true);
				}
			},
			new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (menuFrame.getMemberSize() > 0) {
						orderFrame.addSchedule(new Waiting(menuFrame.getSelectedMember(), (String)menuFrame.getSelectedHour(), (String)menuFrame.getSelectedMinute(), WIDTH / 16, setting));
						menuFrame.removeMember(menuFrame.getSelectedMember());
						orderFrame.sort();
						orderFrame.display();
					}
				}
			},
			new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					orderFrame.setVisible(true);
				}
			});
		deleteMemberFrame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = deleteMemberFrame.getSelectedMember();
				setting.removeMember(name);
				menuFrame.removeMember(name);
				deleteMemberFrame.removeMember(name);
				orderFrame.removeName(name);
				save();
			}
		});
		entryMemberFrame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = entryMemberFrame.getName();
				if (name.length() > 0) {
					if (name.length() < 7) {
						if (!(setting.containsMember(name))) {
							setting.addMember(name);
							menuFrame.addMember(name);
							deleteMemberFrame.addMember(name);
							entryMemberFrame.setMessage("登録しました");
							save();
						} else {
							entryMemberFrame.setMessage("同名は登録できません");
						}
					} else {
						entryMemberFrame.setMessage("7文字以上は登録できません");
					}
				} else {
					entryMemberFrame.setMessage("入力されていません");
				}
				
				entryMemberFrame.setNameField("");
			}
		});
		deleteRemarksFrame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String word = deleteRemarksFrame.getSelectedRemarks();
				setting.removeRemarks(word);
				deleteRemarksFrame.removeRemarks(word);
				orderFrame.removeRemarks(word);
				save();
			}
		});
		entryRemarksFrame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String word = entryRemarksFrame.getWord();
				if (word.length() > 0) {
					if (word.length() < 5) {
						if (!(setting.containsRemarks(word))) {
							setting.addRemarks(word);
							deleteRemarksFrame.addRemarks(word);
							orderFrame.addRemarks(word);
							save();
							entryRemarksFrame.setMessage("登録しました");
						} else {
							entryRemarksFrame.setMessage("その言葉は登録されています");
						}
					} else {
						entryRemarksFrame.setMessage("5文字以上は登録できません");
					}
				} else {
					entryRemarksFrame.setMessage("入力されていません");
				}
				entryRemarksFrame.setWordField("");
			}
		});
		MouseAdapter[] moveAdapterArray = {
			new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					orderFrame.setNumber(0);
					orderFrame.display();
				}
			},
			new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (orderFrame.getNumber() > 0) {
						orderFrame.setNumber(orderFrame.getNumber() - 1);
						orderFrame.display();
					}
				}
			},
			new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (orderFrame.getNumber() < orderFrame.getScheduleSize() - orderFrame.getNamePanelSize()) {
						orderFrame.setNumber(orderFrame.getNumber() + 1);
						orderFrame.display();
					}
				}
			},
			new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (orderFrame.getScheduleSize() > orderFrame.getNamePanelSize()) {
						orderFrame.setNumber(orderFrame.getScheduleSize() - orderFrame.getNamePanelSize());
						orderFrame.display();
					}
				}
			}
		};
		MouseAdapter[] finishedAdapterArray = new MouseAdapter[orderFrame.getNamePanelSize()];
		for (int i = 0; i < finishedAdapterArray.length; i++) {
			int j = i;
			finishedAdapterArray[i] = new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					completion(j);
				}
			};
		}
		orderFrame.addMouseListener(moveAdapterArray, finishedAdapterArray);
	}
	
	public void save() {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
			output.writeObject(setting);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void completion(int n) {
		if (orderFrame.getScheduleSize() > 0) {
			int index = n + orderFrame.getNumber();
			menuFrame.addMember(orderFrame.getWaitingName(index));
			orderFrame.removeSchedule(index);
			if (orderFrame.getNumber() > 0) {
				orderFrame.setNumber(orderFrame.getNumber() - 1);
			}
			orderFrame.display();
		}
	}

}
