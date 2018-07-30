package com.example.click.util;

import com.example.click.entities.Ads;
import com.example.click.entities.Media;
import com.example.click.servlet.LoadDataServlet;

public class Util {

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
}
