package com.ump.commons.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class PDFUtil {
	public static void main(String[] args) throws IOException {
		String docPath = "D:\\test\\aaaa.docx";
		String pdfPath = "D:\\test\\bbbb.pdf";
		XWPFDocument document;

//		InputStream doc = new FileInputStream(docPath);
//		document = new XWPFDocument(doc);
//		PdfOptions options = PdfOptions.create();
//		OutputStream out = new FileOutputStream(pdfPath);
//		PdfConverter.getInstance().convert(document, out, options);
//
//		doc.close();
//		out.close();
//		try {
//			docPath = "D:\\test\\aaaa.docx";
//			WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(new File(docPath));
//			Mapper fontMapper = new IdentityPlusMapper();
//			// fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
//
//			fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
//			fontMapper.put("宋体", PhysicalFonts.get("SimSun"));
//			fontMapper.put("微软雅黑", PhysicalFonts.get("Microsoft Yahei"));
//			fontMapper.put("黑体", PhysicalFonts.get("SimHei"));
//			fontMapper.put("楷体", PhysicalFonts.get("KaiTi"));
//			fontMapper.put("新宋体", PhysicalFonts.get("NSimSun"));
//			fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
//			fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
//			fontMapper.put("宋体扩展", PhysicalFonts.get("simsun-extB"));
//			fontMapper.put("仿宋", PhysicalFonts.get("FangSong"));
//			fontMapper.put("仿宋_GB2312", PhysicalFonts.get("FangSong_GB2312"));
//			fontMapper.put("幼圆", PhysicalFonts.get("YouYuan"));
//			fontMapper.put("华文宋体", PhysicalFonts.get("STSong"));
//			fontMapper.put("华文中宋", PhysicalFonts.get("STZhongsong"));
//			mlPackage.setFontMapper(fontMapper);
//
//			OutputStream os = new java.io.FileOutputStream("D:\\test\\abc.pdf");
//			FOSettings foSettings = Docx4J.createFOSettings();
//			foSettings.setWmlPackage(mlPackage);
//			Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
//
//			// convertPdf(docPath, pdfPath);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			convertDocxToPDF(new File(docPath), pdfPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * docx文档转换为PDF
	 * 
	 * @param docx    docx文档
	 * @param pdfPath PDF文档存储路径
	 * @throws Exception 可能为Docx4JException, FileNotFoundException, IOException等
	 */
	public static void convertDocxToPDF(File docx, String pdfPath) throws Exception {
		OutputStream os = null;
		try {
			WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(docx);
//				Mapper fontMapper = new BestMatchingMapper();
			Mapper fontMapper = new IdentityPlusMapper();
			fontMapper.getFontMappings().put("华文行楷", PhysicalFonts.getPhysicalFonts().get("STXingkai"));
			fontMapper.getFontMappings().put("华文仿宋", PhysicalFonts.getPhysicalFonts().get("STFangsong"));
			fontMapper.getFontMappings().put("隶书", PhysicalFonts.getPhysicalFonts().get("LiSu"));
			mlPackage.setFontMapper(fontMapper);

			PdfConversion conversion = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(mlPackage);
			os = new FileOutputStream(pdfPath);

			conversion.output(os, new PdfSettings());
		} finally {
			IOUtils.closeQuietly(os);
		}
	}

	public static void convertPdf(String docxFilePath, String pdfFilePath) throws Exception {
		File docxFile = new File(docxFilePath);
		File pdfFile = new File(pdfFilePath);
		InputStream inStream = new FileInputStream(docxFilePath);
		XWPFDocument document = new XWPFDocument(inStream);

		OutputStream out = new FileOutputStream(pdfFilePath);
		PdfOptions options = PdfOptions.create();
		// ExtITextFontRegistry fontProvider = ExtITextFontRegistry.getRegistry();
		// options.fontProvider(fontProvider);
		PdfConverter.getInstance().convert(document, out, options);
	}
}
