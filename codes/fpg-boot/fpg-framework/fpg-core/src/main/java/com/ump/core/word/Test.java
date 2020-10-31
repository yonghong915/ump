/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.word;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-07-25 19:52:14
 *
 */
public class Test {

	public static void main(String[] args) {
		String filePath = "d:/test.docx";
		try {
			FileInputStream in = new FileInputStream(filePath);
			if (filePath.toLowerCase().endsWith("docx")) {
				XWPFDocument xwpf = new XWPFDocument(in);
				List<XWPFParagraph> listParagraphs = xwpf.getParagraphs();// 得到段落信息
				Iterator<XWPFTable> it = xwpf.getTablesIterator();// 得到word中的表格

				while (it.hasNext()) {

					XWPFTable table = it.next();
					List<XWPFTableRow> rows = table.getRows();
					// 读取每一行数据
					for (int i = 0; i < rows.size(); i++) {
						XWPFTableRow row = rows.get(i);
						// 读取每一列数据
						List<XWPFTableCell> cells = row.getTableCells();
						for (int j = 0; j < cells.size(); j++) {
							XWPFTableCell cell = cells.get(j);
							// 输出当前的单元格的数据
							System.out.print(cell.getText());
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}

	}

}
