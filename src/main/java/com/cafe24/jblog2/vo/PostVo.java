package com.cafe24.jblog2.vo;

public class PostVo {
	private int no;
	private String title;
	private String contents;
	private String write_date;
	private int category_no;
	
	public PostVo() {
	}

	public PostVo(int no, int category_no) {
		this.no = no;
		this.category_no = category_no;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", contents=" + contents + ", write_date=" + write_date
				+ ", category_no=" + category_no + "]";
	}
	
}