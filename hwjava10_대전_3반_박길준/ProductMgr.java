package com.ssafy;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ProductMgr implements IProductMgr {
	// 1. 하나의 인스턴스만을 유지하기 위해 인스턴스 생성에 특별한 제약을 걸어두어야된다.
	// 2. new를 실행할 수 없도록, 생성자에 private 접근 제어자를 지정한다.
	// 3. 유일한 단일 객체를 반환할 수 있도록 정적 메소드를 지원해야 한다.
	// 4. 유일한 단일 객체를 참조할 정적 참조변수가 필요하다.
	private static ProductMgr productMgr;

	private ProductMgr() {
	}

	public static ProductMgr getInstance() {
		if (productMgr == null) {
			productMgr = new ProductMgr();
		}
		return productMgr;
	}

	// ------ Singleton Pattern-----------
	private ArrayList<Product> products = new ArrayList<>();

	// 상품정보를 객체 배열을 활용하여 저장
	@Override
	public void add(Product product) throws DuplicateException {
		// TODO Auto-generated method stub
		if (products.isEmpty()) {
			products.add(product);
		} else {
			for (int i = 0; i < products.size(); i++) {
				if (products.get(i).getName().equals(product.getName())) {
					throw new DuplicateException();
				} else {
					products.add(product);
					break;
				}
			}
		}
	}

	@Override
	public ArrayList<Product> searchAll() {
		// TODO Auto-generated method stub
		ArrayList<Product> temp = new ArrayList<>();
		if (products.isEmpty()) {
			System.out.println("등록된 상품이 없습니다.");
			return null;
		} else {
			for (int i = 0; i < products.size(); i++) {
				temp.add(products.get(i));
			}
		}
		return temp;
	}

	@Override
	public Product searchByNum(int num) throws CodeNotFoundException {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i) != null) {
				if (products.get(i).getNum() == num) {
					return products.get(i);
				}
			}
		}
		throw new CodeNotFoundException();
	}

	@Override
	public ArrayList<Product> searchByName(String name) {
		ArrayList<Product> temp = new ArrayList<>();
		if (products.isEmpty()) {
			System.out.println("현재 등록되어 있는 상품이 없습니다.");
			return null;
		} else {
			for (int i = 0; i < products.size(); i++) {
				if (products.get(i).getName().contains(name)) {
					temp.add(products.get(i));
				}
			}
		}
		return temp;
	}

	@Override
	public void updateProduct(int num, int price) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getNum() == num) {
				products.get(i).setPrice(price);
				System.out.println(products.get(i));
				break;
			}
		}
	}

	// 삭제 메소드 있음. 확인해서 구현할 것.
	public void deleteProduct(int num) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getNum() == num) {
				products.remove(i);
				break;
			}
		}
	}

	@Override
	public ArrayList<TV> searchOnlyTV() {
		// TODO Auto-generated method stub
		ArrayList<TV> temp = new ArrayList<TV>();
		int count = 0;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i) instanceof TV) {
				temp.add((TV) products.get(i));
			}
		}
		return temp;
	}

	@Override
	public ArrayList<Refrigerator> searchOnlyRefigerators() {
		// TODO Auto-generated method stub
		ArrayList<Refrigerator> temp = new ArrayList<>();

		for (int i = 0; i < products.size(); i++) {
			if (products.get(i) instanceof Refrigerator) {
				temp.add((Refrigerator) products.get(i));
			}
		}
		return temp;
	}

	@Override
	public ArrayList<Refrigerator> searchByAboveRefrirator(int capacity) throws ProductNotFoundException {
		ArrayList<Refrigerator> temp = new ArrayList<Refrigerator>();
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i) instanceof Refrigerator && ((Refrigerator) products.get(i)).getCapacity() >= capacity) {
				temp.add((Refrigerator) products.get(i));
			}
		}
		if (temp.size() == 0) {
			throw new ProductNotFoundException();
		} else {
			return temp;
		}
	}

	@Override
	public ArrayList<TV> searchByAboveTV(int inch) throws ProductNotFoundException {
		ArrayList<TV> temp = new ArrayList<TV>();
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i) instanceof TV && ((TV) products.get(i)).getInch() >= inch) {
				temp.add((TV) products.get(i));
			}
		}
		if (temp.size() == 0) {
			throw new ProductNotFoundException();
		} else {
			return temp;
		}
	}

	@Override
	public void TotalPriceOfStock() {
		// TODO Auto-generated method stub
		int tot = 0;
		int all_tot = 0;
		for (int i = 0; i < products.size(); i++) {
			tot = products.get(i).getPrice() * products.get(i).getStock();
			System.out.println(" name = " + products.get(i).getName() + ", price = " + products.get(i).getPrice()
					+ ", stock = " + products.get(i).getStock() + ", TotalPriceOfStock = " + tot);
			all_tot += tot;
		}
		System.out.println("전체 재고 상품의 총 금액 : " + all_tot);
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		String fname = "product.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fname));
			String msg = "";
			while ((msg = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(msg, "|");
				int kind = Integer.parseInt(st.nextToken());
				if (kind == 1) {
					products.add(new TV(Integer.parseInt(st.nextToken().trim()), st.nextToken().trim(),
							Integer.parseInt(st.nextToken().trim()), Integer.parseInt(st.nextToken().trim()),
							Integer.parseInt(st.nextToken().trim()), st.nextToken().trim()));
				} else {
					products.add(new Refrigerator(Integer.parseInt(st.nextToken().trim()), st.nextToken().trim(),
							Integer.parseInt(st.nextToken().trim()), Integer.parseInt(st.nextToken().trim()),
							Integer.parseInt(st.nextToken().trim())));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
		} catch (IOException e) {
			System.out.println("파일을 읽는 도중에 예외가 발생하였습니다.");
		}
	}

	@Override
	public void close() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("product.txt", false), true);
			for (int i = 0; i < products.size(); i++) {
				String msg = "";
				if (products.get(i) instanceof TV) {
					TV t = (TV) products.get(i);
					msg = String.format("1|%d|%s|%d|%d|%d|%s", t.getNum(), t.getName(), t.getPrice(), t.getStock(),
							t.getInch(), t.getType());
				} else {
					Refrigerator r = (Refrigerator) products.get(i);
					msg = String.format("2|%d|%s|%d|%d|%d", r.getNum(), r.getName(), r.getPrice(), r.getStock(),
							r.getCapacity());
				}
				pw.println(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} finally {
			if(pw!=null) {
				pw.close();	
			}
		}

	}
}
