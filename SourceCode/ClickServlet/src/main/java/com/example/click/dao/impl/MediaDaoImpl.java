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
import com.mysql.jdbc.Statement;

public class MediaDaoImpl implements ClickServletDao<Media> {

	 static final Logger _LOG = Logger.getLogger(MediaDaoImpl.class);
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public int create(Media obj, Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#create(?, ?): kết nối null hoặc đang đóng!");
				return 0;
			}
			
			pstmt = conn.prepareStatement("INSERT INTO media (name, status) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, obj.getName());
			pstmt.setInt(2, obj.getStatus());
			
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
	public boolean update(Media obj, Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#update(?, ?): kết nối null hoặc đang đóng!");
				return false;
			}
			
			if (obj == null || obj.getId() == 0) {
				_LOG.error("#update(?, ?): cannot update with object is null or Id is zero!");
				return false;
			}
			
			pstmt = conn.prepareStatement("UPDATE media SET name=?, status=? WHERE id=?");
			pstmt.setString(1, obj.getName());
			pstmt.setInt(2, obj.getStatus());
			pstmt.setInt(3, obj.getId());
			
			return pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			_LOG.error("#update(?, ?): " + e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				_LOG.error("#update(?, ?): " + e);
			}
		}
		
		return false;
	}

	@Override
	public boolean delete(int id, Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#delete(?, ?): kết nối null hoặc đang đóng!");
				return false;
			}
			
			if (id == 0) {
				_LOG.error("#delete(?, ?): cannot update with Id is zero!");
				return false;
			}
			
			pstmt = conn.prepareStatement("DELETE FROM media WHERE id=?");
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			_LOG.error("#delete(?, ?): " + e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				_LOG.error("#delete(?, ?): " + e);
			}
		}
		
		return false;
	}

	@Override
	public Media get(int id, Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#get(?, ?): kết nối null hoặc đang đóng!");
				return null;
			}
			
			if (id == 0) {
				_LOG.error("#get(?, ?): cannot get object with Id is zero!");
				return null;
			}
			
			pstmt = conn.prepareStatement("SELECT id, name, status FROM media WHERE id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new Media(rs.getInt("id"), rs.getString("name"), rs.getInt("status"));
			}
		} catch (Exception e) {
			_LOG.error("#get(?, ?): " + e);
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				_LOG.error("#get(?, ?): " + e);
			}
		}
		
		return null;
	}

	@Override
	public List<Media> getAll(Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#getAll(?, ?): kết nối null hoặc đang đóng!");
				return null;
			}
			
			pstmt = conn.prepareStatement("SELECT id, name, status FROM media ORDER BY id DESC");
			rs = pstmt.executeQuery();
			List<Media> allResult = new ArrayList<Media>();
			
			Media media = null;
			while (rs.next()) {
				media = new Media(rs.getInt("id"), rs.getString("name"), rs.getInt("status"));
				allResult.add(media);
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
