package com.ump.commons.crawler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
	public static void main(String[] args) {
		String html = "";
		try {
			Document doc = Jsoup.parse(new File("E:\\crawler\\testjsoup.txt"), StandardCharsets.UTF_8.name());
			Elements bbsLists = doc.select("div[class=detaildiv]");
			for (Element el : bbsLists) {
				Elements liElements = el.getElementsByTag("li");
				for (int i = 0; i < liElements.size(); i++) {
					System.out.println(i + ". " + liElements.get(i).text());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
