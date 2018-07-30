package com.example.click.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.click.dao.impl.DeliverLogDaoImpl;
import com.example.click.entities.DeliverLog;
import com.example.click.util.JdbcUtil;

@WebServlet(urlPatterns = {"/auditLog"})
public class AuditLogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger _LOG = Logger.getLogger(AuditLogServlet.class);
	DeliverLogDaoImpl deliverLogDao = new DeliverLogDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		List<DeliverLog> deliverLogList = null;
		Connection conn = JdbcUtil.getConnection();
		try {
			deliverLogList = deliverLogDao.getAll(conn);
			if (deliverLogList == null)
				deliverLogList = new ArrayList<DeliverLog>();
		} catch (Exception e) {
			_LOG.error(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		req.setAttribute("deliverLogList", deliverLogList);
		req.getRequestDispatcher("/auditLog.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doGet(req, resp);
	}

}
