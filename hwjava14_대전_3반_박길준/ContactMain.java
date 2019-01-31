package com.ssafy.edu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ContactMain implements ActionListener, ItemListener, ListSelectionListener {
	JFrame f = new JFrame("Phone Book");
	JLabel custl = new JLabel("Phone Book", JLabel.CENTER);
	JLabel namel = new JLabel("이  름", JLabel.CENTER);
	JLabel phonel = new JLabel("핸드폰", JLabel.CENTER);
	JLabel addressl = new JLabel("주  소", JLabel.CENTER);
	JLabel msg = new JLabel("           ");
	JButton insertb = new JButton("INSERT");
	JButton deleteb = new JButton("DELETE");
	JButton updateb = new JButton("UPDATE");
	JButton searchb = new JButton("SEARCH");
	JButton clearb = new JButton("CLEAR");
	JButton exitb = new JButton("EXIT");
	JButton uploadb = new JButton("UPLOAD");

	JList<Customer> li=new JList<Customer>();
	JTextField nametf = new JTextField();
	JTextField phonetf = new JTextField();
	JTextField addresstf = new JTextField();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p2n = new JPanel();
	JPanel p2c = new JPanel();
	JPanel p2s = new JPanel();

	CustomerDAO dao;
	MessageDialog md;

	public ContactMain() {
		dao = new CustomerDAO();
		md = new MessageDialog(f, "경 고");
		createGUI();
		addEvent();
		showList();
	}

	public void createGUI() {
		f.setLayout(new GridLayout(2, 1, 5, 5));
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());

		li.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		p1.add(custl, BorderLayout.NORTH);
		p1.add(li);
		p1.add(msg, BorderLayout.SOUTH);

		p2n.add(insertb);
		p2n.add(deleteb);
		p2n.add(updateb);
		p2n.add(searchb);

		p2c.setLayout(new GridLayout(3, 2, 5, 7));
		p2c.add(namel);
		p2c.add(nametf);
		p2c.add(phonel);
		p2c.add(phonetf);
		p2c.add(addressl);
		p2c.add(addresstf);

		p2s.add(uploadb);
		p2s.add(clearb);
		p2s.add(exitb);

		p2.add(p2n, "North");
		p2.add(p2c);
		p2.add(p2s, "South");

		f.add(p1);
		f.add(p2);

		f.setSize(500, 500);
		f.setVisible(true);
	}

	// 이벤트 등록 및 처리한다.
	public void addEvent() {
		uploadb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dao.upload();
				} catch (Exception e2) {
					msg.setText(e2.getMessage());
				}
			}
		});
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		insertb.addActionListener(this);
		deleteb.addActionListener(this);
		updateb.addActionListener(this);
		searchb.addActionListener(this);
		clearb.addActionListener(this);
		exitb.addActionListener(this);
		li.addListSelectionListener(this);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ContactMain();
	}

//	// list의 항목에서 선택되었을때, itemEvent가 발생
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Customer c = li.getSelectedValue();
		//String[] ss = str.split("   ");
		nametf.setText(c.getName());
		phonetf.setText(c.getPhone());
		addresstf.setText(c.getAddress());
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertb) {
			insertRecord();
		} else if (e.getSource() == deleteb) {
			deleteRecord();
		} else if (e.getSource() == updateb) {
			updateRecord();
		} else if (e.getSource() == searchb) {
			searchRecord();
		} else if (e.getSource() == clearb) {
			clearText();
		} else {
			try {
				dao.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			System.exit(0);
		}
	}


	public void clearText() {
		phonetf.setText("");
		nametf.setText("");
		addresstf.setText("");
	}

	public void insertRecord() {
		String phone = phonetf.getText().trim();
		String name = nametf.getText().trim();
		String address = addresstf.getText().trim();

		if (phone.equals("") || name.equals("") || address.equals("")) {
			msg.setText("비어있는 항목이 있습니다.");
			return;
		}
		try {
			dao.addCust(name, phone, address);
			showList();
			clearText();
		} catch (Exception e) {
			msg.setText(e.getMessage());
		}
	}

	public void deleteRecord() {
		String name = nametf.getText().trim();
		if (!name.equals("")) {
			try {
				dao.delete(name);
				showList();
				clearText();
			} catch (Exception e) {
				msg.setText(e.getMessage());
			}
		} else {
			msg.setText("이름을 먼저 입력하세요.");
		}

	}

	public void updateRecord() {
		String phone = phonetf.getText().trim();
		String name = nametf.getText().trim();
		String address = addresstf.getText().trim();

		if (phone.equals("") || name.equals("") || address.equals("")) {
			msg.setText("비어있는 항목이 있습니다.");
			return;
		}
		try {
			dao.updateCust(name, phone, address);
			showList();
			clearText();
		} catch (Exception e) {
			msg.setText(e.getMessage());
		}
	}

	public void searchRecord() {
		Customer c = null;
		String name = nametf.getText().trim();
		if (!name.equals("")) {
			try {
				c = dao.search(name);
				phonetf.setText(c.getPhone());
				nametf.setText(c.getName());
				addresstf.setText(c.getAddress());
			} catch (Exception e) {
				msg.setText(e.getMessage());
			}
		} else {
			msg.setText("이름을 먼저 입력하세요.");
		}
	}

	public void showList() {
		java.util.List<Customer> v = dao.allCust();
		Vector<Customer> vec = new Vector<Customer>();
		vec.addAll(v);
		li.removeAll();
		li.setListData(vec);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
