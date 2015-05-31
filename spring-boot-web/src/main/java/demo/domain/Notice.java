package demo.domain;

import java.io.Serializable;

public class Notice implements Serializable {
	public static final String OBJECT_KEY = "notice";
	private int id;
	private String title;
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static String getObjectKey() {
		return OBJECT_KEY;
	}

}
