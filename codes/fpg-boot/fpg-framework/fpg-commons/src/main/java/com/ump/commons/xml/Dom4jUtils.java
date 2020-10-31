package com.ump.commons.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;

public class Dom4jUtils {
	/**
	 * 解析远程 XML 文件
	 * 
	 * @return Document xml 文档
	 */
	public static Document parse(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		return document;
	}

	/**
	 * 创建一个 xml 文档
	 */
	public static Document createDocument() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");

		Element author1 = root.addElement("author").addAttribute("name", "James").addAttribute("location", "UK")
				.addText("James Strachan");

		Element author2 = root.addElement("author").addAttribute("name", "Bob").addAttribute("location", "US")
				.addText("Bob McWhirter");

		return document;
	}

	public void save() throws IOException {
		Document document1 = Dom4jUtils.createDocument();
		FileWriter out = new FileWriter("foo.xml");
		document1.write(out);
		out.close();
	}

	public void par(Document document) throws DocumentException {
		System.out.println("====遍历================================");
		// 获取根节点
		Element root = document.getRootElement();

		// 遍历根节点下的子节点
		for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
			Element element = it.next();
			// do something
			System.out.println("根节下子节点名称：" + element.getName());
		}
		// 遍历子节点 author 下的子节点
		for (Iterator<Element> it = root.elementIterator("feed"); it.hasNext();) {
			Element foo = it.next();
			// do something
			System.out.println("author节点下节点名称：" + foo.getName());
		}
		// 后去根节点的属性
		for (Iterator<Attribute> it = root.attributeIterator(); it.hasNext();) {
			Attribute attribute = it.next();
			// do something
			System.out.println("根节下子节点属性：" + attribute.getName());
		}
		String text = "<person> <name>James</name> </person>";
		Document document1 = DocumentHelper.parseText(text);
	}

	/**
	 * 使用XSLT转换XML
	 */
	public static Document styleDocument(Document document, String stylesheet) throws Exception {

		TransformerFactory factory = TransformerFactory.newInstance();// 实例化转换器工厂 TransformerFactory
		Transformer transformer = factory.newTransformer(new StreamSource(stylesheet));// 创建一个转化格式对象

		DocumentSource source = new DocumentSource(document); // XML 源对象
		DocumentResult result = new DocumentResult(); // 转换结果对象
		transformer.transform(source, result);// 转换操作

		Document transformedDoc = result.getDocument();// 获取转换后的文档
		return transformedDoc;
	}
}
