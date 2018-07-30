package com.example.click.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.example.click.dao.impl.MediaDaoImpl;
import com.example.click.entities.Media;
import com.example.click.util.JdbcUtil;
import com.example.click.util.Util;

@WebServlet(urlPatterns = {"/mediaManagement"})
public class MediaManagementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger _LOG = Logger.getLogger(MediaManagementServlet.class);
	private static final String MESSAGE = "message";
	
	MediaDaoImpl mediaDao = new MediaDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String status = req.getParameter("status");
		String actionType = req.getParameter("actionType");
		Media media = null;
		
		if (!Util.isNullOrEmpty(actionType) 
				&& (actionType.equals("create") || actionType.equals("edit"))) {
			Connection conn = JdbcUtil.getConnection();
			try {
				conn.setAutoCommit(false);
				
				if (Util.isNullOrEmpty(name) 
						|| Util.isNullOrEmpty(status))
					req.setAttribute(MESSAGE, "Vui lòng điền đầy đủ thông tin trước khi ghi lại!");
				else if (Util.isNullOrEmpty(id)) {  // Thêm mới nếu giá trị thuộc tính id null
					media = new Media(0, name, Integer.valueOf(status));
					int added = mediaDao.create(media, conn);
					
					if (added > 0) {
						req.setAttribute(MESSAGE, "Thêm mới thành công, dữ liệu đã được ghi vào CSDL.");
						conn.commit();
					} else {
						req.setAttribute(MESSAGE, "Thêm mới thất bại, vui lòng thử lại sau!");
						conn.rollback();
					}
				} else {  // Cập nhật
					media = new Media(Integer.valueOf(id), name, Integer.valueOf(status));
					boolean updated = mediaDao.update(media, conn);
					
					if (updated) {
						req.setAttribute(MESSAGE, "Cập nhật thành công, dữ liệu đã được ghi vào CSDL.");
						conn.commit();
					} else {
						req.setAttribute(MESSAGE, "Cập nhật thất bại, vui lòng thử lại sau!");
						conn.rollback();
					}
				}
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					_LOG.error(e1);
				}
				_LOG.error(e);
			} finally {
				JdbcUtil.close(conn);
			}
		}
		
		req.setAttribute("mediaList", LoadDataServlet.mediaList);
		req.getRequestDispatcher("/mediaManagement.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doGet(req, resp);
	}

	
}
