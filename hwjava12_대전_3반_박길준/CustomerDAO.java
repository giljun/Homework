package com.ssafy;

import java.util.*;
import java.io.*;
import java.net.Socket;
/** 고객의 데이타를 관리, 저장하는 클래스 */
public class  CustomerDAO{
	private List<Customer> list;

	public CustomerDAO(){
		list=new ArrayList<Customer>(); 
		open();
	}
/** 파일로 부터 자료 읽어서 메모리(ArrayList)에 저장하기*/
	public void open(){
		ObjectInputStream ois=null;
		File file=new File("cust.dat");
		if (!file.exists()) { return; }
		list.clear();
		try{
			ois=new ObjectInputStream(new FileInputStream(file));
			Customer cu=null;
			while(true){
				cu=(Customer)ois.readObject();
				list.add(cu);
			}
		}catch(EOFException e){//End Of File
			System.out.println("읽기 종료");
		}catch(Exception e){
			System.out.println("open():"+e);
		}finally{
			try{
				if(ois != null) ois.close();
			}catch(Exception e){e.printStackTrace();}
		}	
	}
/** 종료하기 전에 메모리의 내용을 파일에 저장하기 
 * @throws Exception */
	public void close() throws Exception{
		File file=new File("cust.dat");
		FileOutputStream fos=new FileOutputStream(file);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		for(Customer c: list){
			oos.writeObject(c);
		}
		oos.close();
		fos.close();	
	}

/** 전달된 고객의 정보를 추가한다.
 * @throws ExistException */
	public void addCust( String name,String phone,String address) throws ExistException{
		try{
			search(name);
			throw new ExistException(); 
		}catch(NotFoundException e) {
			Customer cu=new Customer( name,phone,address);
			list.add(cu);			
		}
	}
/** 고객의 모든 정보를 리턴한다.*/
	public List<Customer> allCust(){
		return list;
	}
	/** 이름 검색 메소드
	 * @throws NotFoundException */
	public Customer search(String name) throws NotFoundException{
		for(Customer cu: list){
			if(name.equals(cu.getName()))
				return cu;
		}
		throw new NotFoundException();
	}
	
	/** 이름 검색, 제거 메소드 
	 * @throws NotFoundException */
	public void delete(String name) throws NotFoundException{
		Customer cu=search(name);
		list.remove(cu);
	}
	
	/** 이름 검색하여 번호, 주소를 수정한다. 동명이인 없음 가정
	 * @throws NotFoundException */
	public void updateCust( String name,String phone,String address) throws NotFoundException{
	 Customer cu=search(name);
	 cu.setPhone(phone);
	 cu.setAddress(address);
	}
	
	public void upload() throws Exception{
		Thread t=new Thread(){
			public void run(){
					try {
						//1.서버접속
						Socket s=new Socket("127.0.0.1",3000);		
						//2. 출력스트림
						ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
						//3. data 보내기
						for(Customer c: list){
							oos.writeObject(c);
						}
						//4. 자원반납
						oos.close();
						s.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		};
		t.start();
	}
}
