package com.example.click.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.click.dao.ClickServletDao;
import com.example.click.dao.impl.DeliverLogDaoImpl;
import com.example.click.entities.Ads;
import com.example.click.entities.DeliverLog;
import com.example.click.entities.Media;
import com.example.click.util.Constants;
import com.example.click.util.JdbcUtil;
import com.example.click.util.Log;
import com.example.click.util.Util;

@WebServlet(urlPatterns = {"/p/click"})
public class ClickServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger _LOG = Logger.getLogger(ClickServlet.class);
	ClickServletDao<DeliverLog> deliverLogDao = new DeliverLogDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String userAgent = req.getHeader("User-Agent");
		String queryString = req.getQueryString();
		
		int mediaId = 0, adId = 0;
		String _media = req.getParameter("_media");
		String _ad = req.getParameter("_ad");
		
		Connection conn = JdbcUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			
			// Kiểm tra dữ liệu đầu vào _media và _ad, do trường error_type chỉ lưu được 1 giá trị int
			// nên sẽ kiểm tra lần lượt, theo thứ tự từ _media đến _ad, nếu _media lỗi thì ghi log 
			// và ngừng xử lý, ngược lại kiểm tra đến _ad
			
			DeliverLog deliverLog = null;
			if (_media == null) {
				_LOG.error("_media không có giá trị!");
				deliverLog = new DeliverLog(0, new Date(), adId, mediaId, userAgent, 
						queryString, Constants.ErrorType.ERROR_MEDIA, "Tham số _media không có giá trị!");
				_saveDeliverLog(deliverLog, conn);
				
				return;
			} else {
				try {
					mediaId = Integer.parseInt(_media);
				} catch (NumberFormatException e) {
					_LOG.error("Không thể parse giá trị _media thành Integer! chi tiết: " + e);
					deliverLog = new DeliverLog(0, new Date(), adId, mediaId, userAgent, 
							queryString, Constants.ErrorType.ERROR_MEDIA, "Dữ liệu _media không đúng định dạng!");
					_saveDeliverLog(deliverLog, conn);
					
					return;
				}
			}
			
			if (_ad == null) {
				_LOG.error("_ad is null!");
				deliverLog = new DeliverLog(0, new Date(), adId, mediaId, userAgent, 
						queryString, Constants.ErrorType.ERROR_AD, "Tham số _ad không có giá trị!");
				_saveDeliverLog(deliverLog, conn);
				
				return;
			} else {
				try {
					adId = Integer.parseInt(_ad);
				} catch (NumberFormatException e) {
					_LOG.error("Không thể parse giá trị _ad thành Integer! chi tiết: " + e);
					deliverLog = new DeliverLog(0, new Date(), adId, mediaId, userAgent, 
							queryString, Constants.ErrorType.ERROR_AD, "Dữ liệu _ad không đúng định dạng!");
					_saveDeliverLog(deliverLog, conn);
					
					return;
				}
			}
			
			if (mediaId == 0) {  // lỗi media, lưu lỗi vào db
				deliverLog = new DeliverLog(0, new Date(), adId, mediaId, userAgent, 
						queryString, Constants.ErrorType.ERROR_MEDIA, "_media = 0 không tồn tại trong cơ sở dữ liệu!");
				_saveDeliverLog(deliverLog, conn);
				
				return;
			}
			
			if (adId == 0) {  // lỗi ads, lưu lỗi vào db
				deliverLog = new DeliverLog(0, new Date(), adId, mediaId, userAgent, 
						queryString, Constants.ErrorType.ERROR_AD, "_ad = 0 không tồn tại trong cơ sở dữ liệu!");
				_saveDeliverLog(deliverLog, conn);
				
				return;
			}
			
			Media media = Util.getMediaFromMemoryById(mediaId);
			if (media == null) {
				deliverLog = new DeliverLog(0, new Date(), adId, mediaId, userAgent, 
						queryString, Constants.ErrorType.ERROR_MEDIA, "_media = " + mediaId + " không tồn tại trong cơ sở dữ liệu!");
				_saveDeliverLog(deliverLog, conn);
				
				return;
			}
			
			Ads ad = Util.getAdsFromMemoryById(adId);
			if (ad == null) {
				deliverLog = new DeliverLog(0, new Date(), adId, mediaId, userAgent, 
						queryString, Constants.ErrorType.ERROR_MEDIA, "_ad = " + adId + " không tồn tại trong cơ sở dữ liệu!");
				_saveDeliverLog(deliverLog, conn);
				
				return;
			}
			
			// Hợp lệ
			deliverLog = new DeliverLog(0, new Date(), adId, mediaId, userAgent, 
					queryString, Constants.ErrorType.SUCCESS, "");
			int idNew = deliverLogDao.create(deliverLog, conn);
			conn.commit();
			
			if (idNew == 0)
				_LOG.info("Không thể ghi dữ liệu vào bảng delever_log!");
			
			// Ghi log và chuyển hướng trang
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_H_mm");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String logFileName = adId + "_" + mediaId + "_" + sdf.format(date) + ".log";
			
			String ip = req.getRemoteAddr();
			if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
			    InetAddress inetAddress = InetAddress.getLocalHost();
			    String ipAddress = inetAddress.getHostAddress();
			    ip = ipAddress;
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(adId).append("\t").append(mediaId).append("\t").append(sdf2.format(date));
			sb.append("\t").append(userAgent).append("\t").append(ip);
			sb.append("\t").append(queryString);
			Log.write(sb.toString(), logFileName);
			
			resp.sendRedirect(ad.getUrl());
		} catch (SQLException e1) {
			_LOG.error("Lỗi khi tương tác với CSDL: " + e1);
			try {
				conn.rollback();
			} catch (SQLException e) {
				_LOG.error("Lỗi khi tương tác với CSDL: " + e1);
			}
		} finally {
			JdbcUtil.close(conn);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doGet(req, resp);
	}
	
	private void _saveDeliverLog(DeliverLog deliverLog, Connection conn) throws SQLException {
		int idNew = deliverLogDao.create(deliverLog, conn);
		conn.commit();
		
		if (idNew == 0)
			_LOG.info("Không thể ghi dữ liệu vào bảng delever_log!");
	}
	
}
