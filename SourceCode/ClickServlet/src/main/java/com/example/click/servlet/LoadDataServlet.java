package com.example.click.servlet;

import java.sql.Connection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.example.click.dao.impl.AdsDaoImpl;
import com.example.click.dao.impl.MediaDaoImpl;
import com.example.click.entities.Ads;
import com.example.click.entities.Media;
import com.example.click.util.Constants;
import com.example.click.util.JdbcUtil;

@WebListener
public class LoadDataServlet implements ServletContextListener {

	private static final Logger _LOG = Logger.getLogger(LoadDataServlet.class);
	
	/**
	 * Thời gian chạy schedule mặc định mỗi 1 phút
	 */
	private static final int PERIOT = 60 * 1000;
	
	public static List<Ads> adsList = null;
	public static List<Media> mediaList = null;
	
	AdsDaoImpl adsDao = new AdsDaoImpl();
	MediaDaoImpl mediaDao = new MediaDaoImpl();

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
		_LOG.info("**********************************************************");
		_LOG.info("*** LoadDataServlet Is Destroyed *************************");
		_LOG.info("**********************************************************");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		_LOG.info("**********************************************************");
		_LOG.info("*** LoadDataServlet Initialized Successfully *************");
		_LOG.info("**********************************************************");
		
		Timer timer = new Timer();
		timer.schedule(new LoadDataTask(), 1000, PERIOT);
	}
	
	class LoadDataTask extends TimerTask {
		
		@Override
		public void run() {

			Thread task = new Thread() {

				@Override
				public void run() {
					_LOG.info("********** LoadDataTask is running... **********");
					try {
						Connection conn = JdbcUtil.getConnection();
						adsList = adsDao.getAllByStatus(Constants.Status.ON, conn);
						mediaList = mediaDao.getAllByStatus(Constants.Status.ON, conn);
						_LOG.info("DATA IS LOADED.");
					} catch (Exception e) {
						_LOG.error("#LoadDataTask: " + e);
					}
					_LOG.info("********** LoadDataTask is stoped... ***********");
				}
			};
			
			task.start();
		}
		
	}

}
