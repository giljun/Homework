package com.ssafy;

public class TV extends Product{
	private int inch;
	private String type;
	
	// 기본 생성자.
	public TV() {
		super();
	}
	
	public TV(int num, String name, int price, int stock, int inch, String type) {
		super(num, name, price, stock);
		this.inch = inch;
		this.type = type;
	}

	public TV(int num, String name, int price, int stock) {
		this( num, name, price, stock, 0, "");
	}

	public int getInch() {
		return inch;
	}

	public void setInch(int inch) {
		this.inch = inch;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TV [ " + super.toString() + ", inch = " + inch + ", type = " + type + " ]";
	}
}
