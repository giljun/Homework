package com.ssafy;

public class NotFoundException extends Exception {
	public NotFoundException() {
		super("존재하지 않음");
	}
}
