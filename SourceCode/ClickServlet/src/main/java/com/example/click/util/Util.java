package com.example.click.util;

import com.example.click.entities.Ads;
import com.example.click.entities.Media;
import com.example.click.servlet.LoadDataServlet;

public class Util {

	/**
	 * Lấy một đối tượng Ads từ bộ nhớ (lấy trong danh sách đã được tải lên bộ nhớ) theo ID
	 * 
	 * @param id
	 * @return một đối tượng Ads nếu tìm thấy, ngược lại trả về null
	 */
	public static Ads getAdsFromMemoryById(int id) {

		if (LoadDataServlet.adsList != null
				&& !LoadDataServlet.adsList.isEmpty()) {
			for (Ads ads : LoadDataServlet.adsList) {
				if (ads.getId() == id) {
					return ads;
				}
			}
		}

		return null;
	}

	/**
	 * Lấy một đối tượng Media từ bộ nhớ (lấy trong danh sách đã được tải lên bộ nhớ) theo ID
	 * 
	 * @param id
	 * @return một đối tượng Media nếu tìm thấy, ngược lại trả về null
	 */
	public static Media getMediaFromMemoryById(int id) {

		if (LoadDataServlet.mediaList != null
				&& !LoadDataServlet.mediaList.isEmpty()) {
			for (Media media : LoadDataServlet.mediaList) {
				if (media.getId() == id) {
					return media;
				}
			}
		}

		return null;
	}
	
	/**
	 * Kiểm tra một chuỗi là null hoặc rỗng
	 * 
	 * @param str
	 * @return một true nếu chuỗi đầu vào null hoặc rỗng, ngược lại trả về false
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
}
