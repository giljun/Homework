package com.ssafy.news;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NewsDao {
	private ArrayList<News> news = new ArrayList<News>();
	
	public ArrayList<News> getNews(String url){
		/*
		 *  1. 뉴스를 가지고 오기 전에 기존에 있을 수 있는 내용을 모두 지워준다.
		 *  2. 해당 url을 매개변수로 뉴스와 연결시켜준다.
		 */
		news.removeAll(news); 
		connectNews(url);
		return news;
	}
	
	private void connectNews(String url) {
		/*
		 *  1. 인스턴스를 통해 객체를 생성시켜준다.
		 */
		SAXParserFactory spf = SAXParserFactory.newInstance();
		
		try {
			SAXParser parser = spf.newSAXParser();
			NewsSaxHandler handler = new NewsSaxHandler();
			parser.parse(new URL(url).openConnection().getInputStream(), handler);
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public class NewsSaxHandler extends DefaultHandler{
		private StringBuilder sb;
		News newsitem;
		
		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.startDocument();
			news = new ArrayList<News>();
			sb = new StringBuilder();
		}

		@Override
		public void startElement(String uri, String localName, String name, Attributes attributes)
				throws SAXException {
			super.startElement(uri, localName, name, attributes);
			if(name.equalsIgnoreCase("item")) {
				newsitem = new News();
			}
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			// TODO Auto-generated method stub
			super.characters(ch, start, length);
			sb.append(ch, start, length);
		}
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if(newsitem != null) {
				if(qName.equalsIgnoreCase("title")) {
					newsitem.setTitle(sb.toString().trim());
				} else if(qName.equalsIgnoreCase("link")) {
					newsitem.setLink(sb.toString().trim());
				} else if(qName.equalsIgnoreCase("description")) {
					newsitem.setDesc(sb.toString().trim());
				} else if(qName.equalsIgnoreCase("image")) {
					newsitem.setImage(sb.toString().trim());
				} else if(qName.equalsIgnoreCase("author")) {
					newsitem.setAuthor(sb.toString().trim());
				} else if(qName.equalsIgnoreCase("pubdata")) {
					newsitem.setPubData(sb.toString().trim());
				} else if(qName.equalsIgnoreCase("item")) {
					news.add(newsitem);
				} 
			}
			sb.setLength(0);
		}
		
		
	}
	
}
