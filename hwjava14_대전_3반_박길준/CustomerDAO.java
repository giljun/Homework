package com.ssafy.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
	private List<Customer> list;

	// 1. 고객의 데이터를 관리 및 저장하는 생성자
	public CustomerDAO() {
		list = new ArrayList<Customer>();
		open();
	}

	// 2. 파일로부터 자료 읽어서 메모리에 저장하기.
	public void open() {
		ObjectInputStream ois = null;
		File file = new File("cust.dat");
		if (!file.exists()) {
			return;
		}
		list.clear();

		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			Customer cu = null;
			while (true) {
				cu = (Customer) ois.readObject();
				list.add(cu);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}

	// 3. 종료 시 메모리의 내용을 파일에 저장한다.
	public void close() throws IOException {
		File file = new File("cust.dat");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		for (Customer c : list) {
			oos.writeObject(c);
		}
		oos.close();
		fos.close();

	}

	// 4. 전달된 고객의 정보를 추가한다.
	public void addCust(String name, String phone, String address) throws ExistException {
		try {
			search(name);
			throw new ExistException();
		} catch (NotFoundException e) {
			Customer cu = new Customer(name, phone, address);
			list.add(cu);
		}
	}

	// 5. 고객의 모든 정보를 리턴한다.
	public List<Customer> allCust() {
		return list;
	}

	// 6. 이름으로 검색
	public Customer search(String name) throws NotFoundException {
		for (Customer cu : list) {
			if (name.equals(cu.getName())) {
				return cu;
			}
		}
		throw new NotFoundException();
	}

	// 7. 이름으로 삭제
	public void delete(String name) throws NotFoundException {
		Customer cu = search(name);
		list.remove(cu);
	}

	// 8. 이름으로 검색, 전화번호 수정
	public void updateCust(String name, String phone, String address) throws NotFoundException {
		Customer cu = search(name);
		cu.setPhone(phone);
		cu.setAddress(address);
	}

	// 9. 업로드 기능
	public void upload() {
		Thread t = new Thread() {
			public void run() {
				try {
					// 1. 서버 접속
					Socket s = new Socket("127.0.0.1", 3000);
					// 2. 출력스트림
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					// 3. 데이터 내보내기
					for (Customer cu : list) {
						oos.writeObject(cu);
					}
					// 4. 자원 반납
					oos.close();
					s.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		};
		t.start();
	}
}
