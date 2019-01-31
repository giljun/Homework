package com.ssafy.edu;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ContactServer {

	public static void main(String[] args) throws IOException {
		// 1. 서버 소켓 생성
		ServerSocket ss = new ServerSocket(3000);
		System.out.println("서버소켓 생성 완료!!");

		Socket s = null;
		ObjectInputStream ois = null;
		Customer cu = null;

		// 2. 클라이언트 접속
		// 클라이언트가 접속할 때까지 대기
		while (true) {
			s = ss.accept();
			System.out.println("클라이언트가 접속하였습니다.!!");
			ois = new ObjectInputStream(s.getInputStream());

			try {
				while (true) {
					cu = (Customer) ois.readObject();
					if( cu == null ) {
						break;
					}
					System.out.println(cu);
				}
			} catch( EOFException e) {
				System.out.println("전송종료");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			} finally {
				ois.close();
				s.close();
			}

		}

	}

}
