package com.ssafy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ContactServer {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(3000);
		Socket s = null;
		ObjectInputStream ois = null;
		Customer cu = null;

		while (true) {
			s = ss.accept();
			ois = new ObjectInputStream(s.getInputStream());
			try {
				while (true) {
					cu = (Customer) ois.readObject();
					if (cu == null)
						break;
					System.out.println(cu);
				}
			} catch (Exception e) {
				System.out.println("전송 종료");
			} finally {
				ois.close();
				s.close();
			}
		}
	}
}
