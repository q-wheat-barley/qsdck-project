package mendan;

import java.io.Serializable;
import java.util.ArrayList;

public class Setting implements Serializable {
	
	private ArrayList<String> memberList;
	private ArrayList<String> remarksList;
	
	public Setting() {
		memberList = new ArrayList<>();
		remarksList = new ArrayList<>();
		
	}
	
	public void addMember(String member) {
		memberList.add(member);
	}
	
	public void removeMember(String member) {
		memberList.remove(member);
	}
	
	public boolean containsMember(String member) {
		return memberList.contains(member);
	}
	
	public String getMember(int index) {
		return memberList.get(index);
	}
	
	public int memberSize() {
		return memberList.size();
	}
	
	public void addRemarks(String remarks) {
		remarksList.add(remarks);
	}
	
	public void removeRemarks(String remarks) {
		remarksList.remove(remarks);
	}
	
	public boolean containsRemarks(String remarks) {
		return remarksList.contains(remarks);
	}
	
	public String getRemarks(int index) {
		return remarksList.get(index);
	}
	
	public int remarksSize() {
		return remarksList.size();
	}
	
}
