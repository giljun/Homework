package com.ssafy;

import java.io.Serializable;

public class Refrigerator extends Product implements Serializable{
	private int capacity;
	// 기본 생성자
	public Refrigerator() {
		super();
	}
	
	public Refrigerator(int num, String name, int price, int stock, int capacity) {
		super(num, name, price, stock);
		this.capacity = capacity;
	}

	public Refrigerator(int num, String name, int price, int stock) {
		this(num, name, price, stock, 0);
		// TODO Auto-generated constructor stub
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Refrigerator [ " + super.toString() + ", capacity = " + capacity + " ]";
	}

}
