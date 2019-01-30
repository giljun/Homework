package com.ssafy.news;

import java.io.Serializable;

public class News implements Serializable{
	private String title;
	private String link;
	private String desc;
	private String image;
	private String author;
	private String pubData;
	
	public News() {
		
	}

	public News(String title, String desc, String link, String image, String author, String pubData) {
		super();
		this.title = title;
		this.desc = desc;
		this.link = link;
		this.image = image;
		this.author = author;
		this.pubData = pubData;
	}


	@Override
	public String toString() {
		return "News [title=" + title + ", link=" + link + ", desc=" + desc + ", image=" + image + ", author=" + author
				+ ", pubData=" + pubData + "]";
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPubData() {
		return pubData;
	}

	public void setPubData(String pubData) {
		this.pubData = pubData;
	}
}
