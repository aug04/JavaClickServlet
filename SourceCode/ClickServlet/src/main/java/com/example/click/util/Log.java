package com.example.click.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Properties;

public class Log {

	private static String logPath = "";
	
	static {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		InputStream is = null;
		
		try {
			is = loader.getResourceAsStream(JdbcUtil.CONFIG_FILE_NAME);
			props.load(is);
			
			logPath = props.getProperty("logpath");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Ghi log ra file theo yêu cầu của đề bài
	 * 
	 * @param message thông điệp cần ghi
	 * @param logFileName đường dẫn file log
	 */
	public synchronized static void  write(String message, String logFileName) {
		try {
			String path = "";
			String catalinaHome = System.getenv("CATALINA_HOME");
			if (!logPath.isEmpty())
				path = logPath + logFileName;
			else if (catalinaHome != null && catalinaHome.isEmpty())
				path = catalinaHome + File.separator + "logs" + File.separator + "ad" + File.separator + logFileName;
			else {
				String userDir = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length() - 3);
				path = userDir + "logs" + File.separator + "ad" + File.separator + logFileName;
			}
			
			File file = new File(path.substring(0, path.lastIndexOf(File.separator)));
			if (!file.exists()) {
				file.mkdirs();
			}
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(message + "\r\n");
			
			fw.flush();
			bw.flush();
			fw.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
