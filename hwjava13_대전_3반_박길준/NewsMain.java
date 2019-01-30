package com.ssafy.news;

import java.util.ArrayList;

public class NewsMain {

	public static void main(String[] args) {
		NewsDao dao = new NewsDao();
		String fname = "https://rss.joins.com/joins_it_list.xml";
		
		ArrayList<News> newslist = dao.getNews(fname);
		
		for (int i = 0; i < newslist.size(); i++) {
			System.out.println(newslist.get(i));
		}
				
	}

}
