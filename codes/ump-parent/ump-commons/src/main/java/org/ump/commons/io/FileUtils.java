package org.ump.commons.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ump.commons.CommUtils;

/**
 * 
 * @author fangyh
 * @since 2018-03-22 22:25:30
 * @version 1.0
 */
public class FileUtils {
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * 
	 * @param imgPath
	 * @return
	 * @throws IOException
	 */
	public static byte[] image2Bytes(String imgPath) throws IOException {
		if (CommUtils.isEmpty(imgPath)) {
			throw new NullPointerException("imgPath must not be null");
		}
		byte[] data = null;
		FileImageInputStream input = null;
		ByteArrayOutputStream output = null;
		try {
			input = new FileImageInputStream(new File(imgPath));
			output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
		} catch (IOException ex) {
			logger.error("write byte error from image:", ex);
			throw ex;
		} finally {
			try {
				if (null != output) {
					output.close();
				}
				if (null != input) {
					input.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return data;
	}

	/**
	 * 
	 * @param data
	 * @param path
	 */
	public void byte2image(byte[] data, String path) {
		if (data.length < 3 || CommUtils.isEmpty(path)) {
			return;
		}
		try {
			FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
		} catch (Exception ex) {
			logger.error("Exception: ", ex);
			ex.printStackTrace();
		}
	}
}
