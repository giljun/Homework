package com.ssafy;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient extends Frame implements ActionListener {

	private TextArea ta = new TextArea();
	private TextField tf = new TextField();
	private Button b1 = new Button("send");
	private Button b2 = new Button("exit");
	private Panel p = new Panel();
	private Socket s = new Socket();
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	private String ip;
	private int port;
	private String name;

	public ChatClient() {
		createGUI();
	}

	// GUI를 생성하고 Event등록, 처리
	public void createGUI() {
		p.add(b1);
		p.add(b2);
		add(ta, "West");
		add(p, "Center");
		add(tf, "South");

		tf.addActionListener(this);// event 등록
		setBounds(200, 200, 500, 400);
		setVisible(true);

		b1.addActionListener(this);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	public void go(String ip, int port, String name) {
		this.ip = ip;
		this.port = port;
		this.name = name;

		// 1. socket 생성
		try {
			s = new Socket(ip, port);
			ta.append("서버접속 성공\n");
			// 2. I/O stream 생성
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			ta.append("스트림생성 성공 \n");

			// thread 만들기
			new ChatClientThread(ois).start();
			ta.append("스레드 생성 성공\n");
			tf.requestFocus();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("서버가 시작 중인지, ip와 port가 맞는지 확인 바랍니다.");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String msg = tf.getText();

		try {
			oos.writeObject("[" + name + "] " + msg);
		} catch (IOException e1) {
			System.out.println("메세지 전송중 오류 발생 : " + e1.getMessage());
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		String ip = "127.0.0.1";
		int port = 8000;
		String name = "박길준";

		new ChatClient().go(ip, port, name);
	}

	class ChatClientThread extends Thread {
		private ObjectInputStream ois = null;

		private ChatClientThread(ObjectInputStream ois) {
			this.ois = ois;
		}

		public void run() {
			try {
				while (true) {
					String msg = (String) ois.readObject();
					ta.append(msg + "\n");
				}
			} catch (Exception e) {
				ta.append("readObject()시 오류 발생 : " + e.getMessage());
			}
		}
	}
}
