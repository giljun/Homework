package com.ssafy;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		ProductMgr p_manager = ProductMgr.getInstance();

		ArrayList<Product> products = new ArrayList<Product>();

		while (true) {
			// 키보드 입력
			System.out.println("<<< 제품 재고 관리 프로그램 >>>");
			System.out.println("1. 상품정보 입력");
			System.out.println("2. 저장된 상품정보 출력");
			System.out.println("3. 상품번호로 상품 검색");
			System.out.println("4. 상품명으로 상품 검색");
			System.out.println("5. TV정보만 검색");
			System.out.println("6. 냉장고 정보만 검색");
			System.out.println("7. 400L 이상의 냉장고 검색");
			System.out.println("8. 50inch 이상의 TV 검색");
			System.out.println("9. 상품 가격 변경");
			System.out.println("10. 상품번호로 상품을 삭제");
			System.out.println("11. 전체 재고 상품 총 금액");
			System.out.println("0. 종료");

			System.out.println("원하시는 기능을 선택해주세요.");
			int k_num = sc.nextInt();

			switch (k_num) {
			case 0:
				p_manager.close();
				System.out.println("프로그램을 저장하고 종료합니다.");
				break;
			case 1:
				System.out.println("상품을 선택해주세요.(1. TV, 2. 냉장고)");
				k_num = sc.nextInt();

				if (k_num == 1) {
					TV t = new TV();
					System.out.println("상품번호를 입력해주세요.");
					t.setNum(sc.nextInt());
					System.out.println("상품명을 입력해주세요.");
					t.setName(sc.next());
					System.out.println("상품가격을 입력해주세요.");
					t.setPrice(sc.nextInt());
					System.out.println("재고수량을 입력해주세요.");
					t.setStock(sc.nextInt());
					System.out.println("상품의 인치를 입력해주세요.");
					t.setInch(sc.nextInt());
					System.out.println("상품의 타입을 입력해주세요.");
					t.setType(sc.next());

					try {
						p_manager.add(t);
					} catch (DuplicateException e) {
						System.out.println("이미 존재하는 상품입니다.");
					}

				} else {
					Refrigerator r = new Refrigerator();
					System.out.println("상품번호를 입력해주세요.");
					r.setNum(sc.nextInt());
					System.out.println("상품명을 입력해주세요.");
					r.setName(sc.next());
					System.out.println("상품가격을 입력해주세요.");
					r.setPrice(sc.nextInt());
					System.out.println("재고수량을 입력해주세요.");
					r.setStock(sc.nextInt());
					System.out.println("상품의 용량을 입력해주세요.");
					r.setCapacity(sc.nextInt());

					try {
						p_manager.add(r);
					} catch (DuplicateException e) {
						System.out.println("이미 존재하는 상품입니다.");
					}
				}
				break;
			case 2:
				p_manager.open();
				products = p_manager.searchAll();
				for (int i = 0; i < products.size(); i++) {
					System.out.println(products.get(i));
				}
				break;
			case 3:
				System.out.println("상품번호를 입력해주세요.");
				int num = sc.nextInt();
				try {
					Product temp = p_manager.searchByNum(num);
					System.out.println(temp);
				} catch (CodeNotFoundException e) {
					System.out.println("존재하지 않는 상품입니다.");
				}

				break;
			case 4:
				System.out.println("상품명을 입력해주세요.");
				String name = sc.next();
				ArrayList<Product> p_temp = p_manager.searchByName(name);
				if (p_temp != null) {
					for (int i = 0; i < p_temp.size(); i++) {
						System.out.println(p_temp.get(i));
					}
				} else {
					break;
				}
				break;
			case 5:
				ArrayList<TV> t_temp = p_manager.searchOnlyTV();
				if (t_temp != null) {
					for (int i = 0; i < t_temp.size(); i++) {
						System.out.println(t_temp.get(i));
					}
				}
				break;
			case 6:
				ArrayList<Refrigerator> r_temp = p_manager.searchOnlyRefigerators();
				if (r_temp != null) {
					for (int i = 0; i < r_temp.size(); i++) {
						System.out.println(r_temp.get(i));
					}
				}
				break;
			case 7:
				ArrayList<Refrigerator> rr_temp;
				try {
					rr_temp = p_manager.searchByAboveRefrirator(400);
					for (int i = 0; i < rr_temp.size(); i++) {
						System.out.println(rr_temp.get(i));
					}
				} catch (ProductNotFoundException e) {
					System.out.println("400리터 이상의 상품이 존재하지 않습니다.");
				}

				break;
			case 8:
				ArrayList<TV> tt_temp;
				try {
					tt_temp = p_manager.searchByAboveTV(50);
					for (int j = 0; j < tt_temp.size(); j++) {
						System.out.println(tt_temp.get(j));
					}
				} catch (ProductNotFoundException e) {
					System.out.println("50인치 이상의 상품이 존재하지 않습니다.");
				}
				break;
			case 9:
				System.out.println("찾으시는 상품번호와 변경할 금액을 입력해주세요.");
				num = sc.nextInt();
				int price = sc.nextInt();
				p_manager.updateProduct(num, price);
				;
				break;
			case 10:
				System.out.println("삭제하실 상품의 번호를 입력해주세요.");
				num = sc.nextInt();
				p_manager.deleteProduct(num);
				break;
			case 11:
				p_manager.TotalPriceOfStock();
				break;
			}
			if (k_num == 0) {
				break;
			}
		}
	}
}
