package com.pay.main.payment.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileIOUtils {
	private static Logger logger = LoggerFactory.getLogger(FileIOUtils.class);

	/**
	 * 将字符串写出txt文件
	 * 
	 * @param info 字符串内容
	 * @param filePath 写出地址
	 * @throws IOException
	 */
	public static void wirteTxtFile(String info, String filePath) {
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File(filePath);
			fw = new FileWriter(f, true);
		} catch (IOException ex) {
			logger.error("写入文件错误：", ex);
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(info);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
			logger.info("回调信息，写入文件");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}