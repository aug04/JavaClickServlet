package com.example.click.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.example.click.dao.ClickServletDao;
import com.example.click.entities.Media;

public class MediaDaoImpl implements ClickServletDao<Media> {

	 static final Logger _LOG = Logger.getLogger(MediaDaoImpl.class);
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public int create(Media obj, Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Media obj, Connection conn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id, Connection conn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Media get(int id, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Media> getAll(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Media> getAllByStatus(int status, Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#getAllByStatus(?, ?): kết nối null hoặc đang đóng!");
				return null;
			}
			
			pstmt = conn.prepareStatement("SELECT id, name, status FROM media WHERE status=? ORDER BY id DESC");
			pstmt.setInt(1, status);
			rs = pstmt.executeQuery();
			List<Media> allResult = new ArrayList<Media>();
			
			Media media = null;
			while (rs.next()) {
				media = new Media(rs.getInt("id"), rs.getString("name"), rs.getInt("status"));
				allResult.add(media);
			}
			
			return allResult.isEmpty() ? null : allResult;
		} catch (Exception e) {
			_LOG.error("#getAllByStatus(?, ?): " + e);
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				_LOG.error("#getAllByStatus(?, ?): " + e);
			}
		}
		
		return null;
	}
	
}
