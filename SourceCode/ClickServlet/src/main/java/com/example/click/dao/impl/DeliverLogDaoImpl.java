package com.example.click.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.example.click.dao.ClickServletDao;
import com.example.click.entities.DeliverLog;
import com.mysql.jdbc.Statement;

public class DeliverLogDaoImpl implements ClickServletDao<DeliverLog> {

	private static final Logger _LOG = Logger.getLogger(DeliverLog.class);

	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public int create(DeliverLog obj, Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#create(?, ?): connection is null or not open!");
				return 0;
			}

			pstmt = conn.prepareStatement("INSERT INTO deliver_log (date, ad_id, media_id, user_agent, "
					+ "query_string, error_type, error_msg) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setTimestamp(1, new Timestamp(obj.getDate().getTime()));
			pstmt.setInt(2, obj.getAdId());
			pstmt.setInt(3, obj.getMediaId());
			pstmt.setString(4, obj.getUserAgent());
			pstmt.setString(5, obj.getQueryString());
			pstmt.setInt(6, obj.getErrorType());
			pstmt.setString(7, obj.getErrorMsg());

			int created = pstmt.executeUpdate();
			if (created > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (Exception e) {
			_LOG.error("#create(?, ?): " + e);
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				_LOG.error("#create(?, ?): " + e);
			}
		}

		return 0;
	}

	@Override
	public boolean update(DeliverLog obj, Connection conn) {
		// TODO cap nhat code vao day neu su dung ham nay
		
		return false;
	}

	@Override
	public boolean delete(int id, Connection conn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DeliverLog get(int id, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeliverLog> getAll(Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#getAll(?, ?): kết nối null hoặc đang đóng!");
				return null;
			}
			
			pstmt = conn.prepareStatement("SELECT id, date, ad_id, media_id, user_agent, query_string, error_type, error_msg FROM deliver_log ORDER BY id DESC");
			rs = pstmt.executeQuery();
			List<DeliverLog> allResult = new ArrayList<DeliverLog>();
			
			DeliverLog deliverLog = null;
			while (rs.next()) {
				deliverLog = new DeliverLog(rs.getInt("id"), rs.getTimestamp("date"), rs.getInt("ad_id"), 
						rs.getInt("media_id"), rs.getString("user_agent"), rs.getString("query_string"), rs.getInt("error_type"), rs.getString("error_msg"));
				allResult.add(deliverLog);
			}
			
			return allResult.isEmpty() ? null : allResult;
		} catch (Exception e) {
			_LOG.error("#getAll(?, ?): " + e);
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				_LOG.error("#getAll(?, ?): " + e);
			}
		}
		
		return null;
	}

}
