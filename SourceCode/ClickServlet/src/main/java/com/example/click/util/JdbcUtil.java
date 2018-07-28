package com.example.click.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Thực hiện kết nối/ngắt kết nối đến cơ sở dữ liệu
 * 
 * @author AUG
 *
 */
public class JdbcUtil {
	
	public static final String CONFIG_FILE_NAME = "config.properties";
	private static Connection conn = null;
	private static final Logger _LOG = Logger.getLogger(JdbcUtil.class);
	
	/**
	 * Nhận một kết nối đến cơ sở dữ liệu
	 * 
	 * @author AUG
	 */
	public static Connection getConnection() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		InputStream is = null;
		
		try {
			is = loader.getResourceAsStream(CONFIG_FILE_NAME);
			props.load(is);
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + props.getProperty("ip") 
					+ ":" + props.getProperty("port") + "/" + props.getProperty("dbname"), 
					props.getProperty("username"), props.getProperty("password"));
			
			return conn;
		} catch (Exception e) {
			_LOG.error("#getConnection(): " + e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					_LOG.error("#getConnection(): " + e);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Đóng/ngắt kết nối đến cơ sở dữ liệu
	 * 
	 * @author AUG
	 */
	public static void close(Connection conn) {
		try {
			if (!conn.isClosed() && conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			_LOG.error("#close(): " + e);
		}
	}
	
}
