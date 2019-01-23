package com.ssafy;

import java.util.ArrayList;

public interface IProductMgr {
	void add(Product product) throws DuplicateException;

	ArrayList<Product> searchAll();

	Product searchByNum(int num) throws CodeNotFoundException;

	ArrayList<Product> searchByName(String name);

	ArrayList<TV> searchOnlyTV();

	ArrayList<Refrigerator> searchOnlyRefigerators();

	ArrayList<Refrigerator> searchByAboveRefrirator(int capacity) throws ProductNotFoundException;

	ArrayList<TV> searchByAboveTV(int inch) throws ProductNotFoundException;

	void updateProduct(int num, int price);

	void deleteProduct(int num);

	void TotalPriceOfStock();
	
	// 파일을 오픈한다.
	void open();
	
	// 파일을 저장한다.
	void close();
	
	
}
