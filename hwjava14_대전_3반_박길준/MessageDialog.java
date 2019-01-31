package com.ssafy.edu;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessageDialog extends JDialog {
	JLabel mel;
	JButton okb;
	JPanel mep, okp;

	// 1. 다이얼로그 gui 생성
	public MessageDialog(JFrame f, String title) {
		super(f, title);
		mel = new JLabel();
		okb = new JButton("O K");
		mep = new JPanel();
		okp = new JPanel();
		okp.add(okb);
		mep.add(mel);
		setLayout(new GridLayout(2, 1));
		add(mep);
		add(okp);

		addEvent();

	}

	// 2. 이벤트 등록 및 핸들링
	public void addEvent() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
		
		okb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

	// 3. 다이얼로그 보여주기
	public void show(String message) {
		mel.setText(message);
		setSize(200, 150);
		show();
	}
}
