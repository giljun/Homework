package com.ssafy;

public class Customer {
	private String name;
	private String phone;
	private String address;

	public Customer(String name, String phone, String address) {
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public Customer(String name, String phone) {
		this(name, phone, "");
	}

	public Customer() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name);
		sb.append("   ");
		sb.append(phone);
		sb.append("   ");
		sb.append(address);

		return sb.toString();
	}
}
