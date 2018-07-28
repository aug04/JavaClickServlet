package com.example.click.entities;

import java.io.Serializable;
import java.util.Date;

public class DeliverLog implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Date date;
	private int adId;
	private int mediaId;
	private String userAgent;
	private String queryString;
	private int errorType;
	private String errorMsg;
	
	public DeliverLog(int id, Date date, int adId, int mediaId,
			String userAgent, String queryString, int errorType, String errorMsg) {
		super();
		this.id = id;
		this.date = date;
		this.adId = adId;
		this.mediaId = mediaId;
		this.userAgent = userAgent;
		this.queryString = queryString;
		this.errorType = errorType;
		this.errorMsg = errorMsg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
