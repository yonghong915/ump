//package com.commons.test;
//
//import java.awt.AWTException;
//import java.awt.Desktop;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Rectangle;
//import java.awt.Robot;
//import java.awt.Toolkit;
//import java.awt.event.KeyEvent;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.net.URL;
//
//import javax.imageio.ImageIO;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//
//public class TesseractExample {
//	public void cutPicture() throws IOException, URISyntaxException, AWTException {
//		Desktop.getDesktop().browse(new URL("http://open-open.com/").toURI());
//		Robot robot = new Robot();
//		robot.delay(10000);
//		Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
//		int width = (int) d.getWidth();
//		int height = (int) d.getHeight();
//		// 最大化浏览器
//		robot.keyRelease(KeyEvent.VK_F11);
//		robot.delay(2000);
//		Image image = robot.createScreenCapture(new Rectangle(0, 0, width, height));
//		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		Graphics g = bi.createGraphics();
//		g.drawImage(image, 0, 0, width, height, null);
//		// 保存图片
//		ImageIO.write(bi, "jpg", new File("d:/open.jpg"));
//
//	}
//
//	private String fileName; // 文件的前缀
//	private String defaultName = "GuiCamera";
//	static int serialNum = 0;
//	private String imageFormat; // 图像文件的格式
//	private String defaultImageFormat = "png";
//
//	public void captureScreen(String fileName, String folder) throws Exception {
//		fileName = defaultName;
//		imageFormat = defaultImageFormat;
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		Rectangle screenRectangle = new Rectangle(screenSize);
//		Robot robot = new Robot();
//		BufferedImage image = robot.createScreenCapture(screenRectangle);
//
//		serialNum++;
//		// 根据文件前缀变量和文件格式变量，自动生成文件名
//		String name = fileName + String.valueOf(serialNum) + "." + imageFormat;
//
//		// 保存路径
//		File screenFile = new File(fileName);
//		if (!screenFile.exists()) {
//			screenFile.mkdir();
//		}
//		File f = new File(screenFile, folder);
//		ImageIO.write(image, "png", f);
//
//		// 将screenshot对象写入图像文件
//		// ImageIO.write(screenshot, imageFormat, f);
//
//		// 自动打开
//		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
//			Desktop.getDesktop().open(f);
//	}
//
//	public byte[] takeScreenshot(WebDriver driver) throws IOException {
//		byte[] screenshot = null;
//		screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);// 得到截图
//		return screenshot;
//	}
//
////	public BufferedImage createElementImage(WebDriver driver, WebElement webElement, int x, int y, int width,
////			int heigth)// 开始裁剪的位置和截图的宽和高
////			throws IOException {
////		Dimension size = webElement.getSize();
////		BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(takeScreenshot(driver)));
////		BufferedImage croppedImage = originalImage.getSubimage(x, y, size.getWidth() + width,
////				size.getHeight() + heigth);// 进行裁剪
////		return croppedImage;
////	}
//
//	// https://github.com/tesseract-ocr/tessdata
//	public static void main(String[] args) throws Exception {
//		TesseractExample test = new TesseractExample();
//		// test.captureScreen("d:/capture", "test.jpg");
//		test.testTakesScreenshot();
//	}
//
//	public void testTakesScreenshot() {
//
//		// 设置chrome.exe和chromedriver.exe的系统参数
//		System.setProperty("webdriver.chrome.bin", "C:\\Users\\fangyh\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
//		System.setProperty("webdriver.chrome.driver", "D:\\test\\testvue\\my-project\\node_modules\\chromedriver\\lib\\chromedriver\\chromedriver.exe");
//
//		//WebDriver driver = new InternetExplorerDriver();
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://www.baidu.com");
//		try {
//			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(srcFile, new File("d:\\screenshot.png"));
//			//关闭浏览器
//	        driver.quit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void test4j() {
//		File imageFile = new File("D:\\tess4j\\IMG_20190308_161406.jpg");
//		ITesseract instance = new Tesseract(); // JNA Interface Mapping
//		// ITesseract instance = new Tesseract1(); // JNA Direct Mapping
//		URL url = ClassLoader.getSystemResource("tessdata");// 获得Tesseract的文字库
//		String tesspath = url.getPath().substring(1);
//		instance.setDatapath(tesspath); // path to tessdata directory
//		instance.setLanguage("chi_sim");// chi_sim eng
//		try {
//			long startTime = System.currentTimeMillis();
//			String result = instance.doOCR(imageFile);
//			long endTime = System.currentTimeMillis();
//			System.out.println(result);
//			System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
//		} catch (TesseractException e) {
//			System.err.println(e.getMessage());
//		}
//	}
//}
