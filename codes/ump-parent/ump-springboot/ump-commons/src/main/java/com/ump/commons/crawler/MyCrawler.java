package com.ump.commons.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.csvreader.CsvWriter;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {
	/**
	 * 正则表达式匹配指定的后缀文件
	 */
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp4|zip|gz))$");
	private final static String CSV_PATH = "E:\\crawler\\data.csv";
	private CsvWriter cw;
	private File csv;

	public MyCrawler() throws IOException {
		csv = new File(CSV_PATH);

		if (csv.isFile()) {
			csv.delete();
		}
		cw = new CsvWriter(new FileWriter(csv, true), ',');
		cw.write("title");
		cw.write("brand");
		cw.write("newPrice");
		cw.write("oldPrice");
		cw.write("mileage");
		cw.write("age");
		cw.write("stage");
		cw.endRecord();
		cw.close();
	}

	/**
	 * 这个方法主要是决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
	 * 第一个参数referringPage封装了当前爬取的页面信息 第二个参数url封装了当前爬取的页面url信息
	 * 在这个例子中，我们指定爬虫忽略具有css，js，git，...扩展名的url，只接受以“http://www.ics.uci.edu/”开头的url。
	 * 在这种情况下，我们不需要referringPage参数来做出决定。
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		// String href = url.getURL().toLowerCase();// 得到小写的url
		// return !FILTERS.matcher(href).matches() // 正则匹配，过滤掉我们不需要的后缀文件
		// && href.startsWith("http://www.ics.uci.edu/");//
		// 只接受以“http://www.ics.uci.edu/”开头的url
		return super.shouldVisit(referringPage, url);
	}

	/**
	 * 当一个页面被提取并准备好被你的程序处理时，这个函数被调用。
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();// 获取url
		System.out.println("URL: " + url);

		if (page.getParseData() instanceof HtmlParseData) {// 判断是否是html数据
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();//// 强制类型转换，获取html数据对象
			String text = htmlParseData.getText();// 获取页面纯文本（无html标签）
			String html = htmlParseData.getHtml();// 获取页面Html
			Set<WebURL> links = htmlParseData.getOutgoingUrls();// 获取页面输出链接

			System.out.println("纯文本长度: " + text.length());
			System.out.println("html长度: " + html.length());
			System.out.println("链接个数 " + links.size());
			Document doc = Jsoup.parse(html);
			Elements bbsLists = doc.select("div[class=detaildiv]");
			for (Element el : bbsLists) {
				StringBuilder sb = new StringBuilder();
				Elements liElements = el.getElementsByTag("li");
				for (int i = 0; i < liElements.size(); i++) {
					//System.out.println(i + ". " + liElements.get(i).text());
					sb.append(liElements.get(i).text());
					writeTxt(html, url);
				}
			}
			
		}

	}

	private void writeTxt(String html, String url) {
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter("E:\\crawler\\pages\\" + DigestUtils.sha256Hex(url) + ".txt"))) {
			bw.write(html);
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}
}
