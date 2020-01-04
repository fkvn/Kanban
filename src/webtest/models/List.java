package webtest.models;

import java.util.ArrayList;

public class List {
	private String title;
	private String desc;
	private int id;
	
	public List() {
		this(0, "", "");
	}
	
	public List(int id, String title, String desc) {
		this.title = title;
		this.id = id;
		this.desc = desc;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static int getIndex(ArrayList<List> lists, int id) {
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).getId() == id)
				return i;
		}	
		return -1;
	}

}
