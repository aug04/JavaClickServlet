package com.example.click.util;

/**
 * Chứa tất cả các hằng số dùng chung cho toàn bộ ứng dụng
 *
 */
public class Constants {

	/**
	 * Các trạng thái của Ads và Media
	 *
	 */
	public static final class Status {
		
		public static final int ON = 1;
		public static final int OFF = 2;
		public static final int DELETED = -1;
	}
	
	/**
	 * Trạng thái lỗi của DeliverLog
	 *
	 */
	public static final class ErrorType {
		
		public static final int SUCCESS = 1;
		public static final int ERROR_AD = 2;
		public static final int ERROR_MEDIA = 3;
	}
}
