package com.ssafy;

public class ExistException extends Exception {
	public ExistException() {
		super("이미 존재함.");
	}
}
