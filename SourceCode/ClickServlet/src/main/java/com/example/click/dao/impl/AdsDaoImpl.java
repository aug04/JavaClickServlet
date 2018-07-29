package com.example.click.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.example.click.dao.ClickServletDao;
import com.example.click.entities.Ads;

public class AdsDaoImpl implements ClickServletDao<Ads> {

	private static final Logger _LOG = Logger.getLogger(AdsDaoImpl.class);
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public int create(Ads obj, Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#create(?, ?): connection is null or not open!");
				return 0;
			}
			
			pstmt = conn.prepareStatement("INSERT INTO ads (name, status, url) VALUES (?, ?, ?)");
			pstmt.setString(1, obj.getName());
			pstmt.setInt(2, obj.getStatus());
			pstmt.setString(3, obj.getUrl());
			
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
	public boolean update(Ads obj, Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#update(?, ?): connection is null or not open!");
				return false;
			}
			
			if (obj == null || obj.getId() == 0) {
				_LOG.error("#update(?, ?): cannot update with object is null or Id is zero!");
				return false;
			}
			
			pstmt = conn.prepareStatement("UPDATE ads SET name=?, status=?, url=? WHERE id=?");
			pstmt.setString(1, obj.getName());
			pstmt.setInt(2, obj.getStatus());
			pstmt.setString(3, obj.getUrl());
			pstmt.setInt(4, obj.getId());
			
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
				_LOG.error("#delete(?, ?): connection is null or not open!");
				return false;
			}
			
			if (id == 0) {
				_LOG.error("#delete(?, ?): cannot update with Id is zero!");
				return false;
			}
			
			pstmt = conn.prepareStatement("DELETE FROM ads WHERE id=?");
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
	public Ads get(int id, Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#get(?, ?): connection is null or not open!");
				return null;
			}
			
			if (id == 0) {
				_LOG.error("#get(?, ?): cannot get object with Id is zero!");
				return null;
			}
			
			pstmt = conn.prepareStatement("SELECT id, name, status, url FROM ads WHERE id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new Ads(rs.getInt("id"), rs.getString("name"), rs.getInt("status"), rs.getString("url"));
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
	public List<Ads> getAll(Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				_LOG.error("#getAll(?): connection is null or not open!");
				return null;
			}
			
			pstmt = conn.prepareStatement("SELECT id, name, status, url FROM ads ORDER BY id DESC");
			rs = pstmt.executeQuery();
			List<Ads> allResult = new ArrayList<Ads>();
			
			Ads ads = null;
			while (rs.next()) {
				ads = new Ads(rs.getInt("id"), rs.getString("name"), rs.getInt("status"), rs.getString("url"));
				allResult.add(ads);
			}
			
			return allResult.isEmpty() ? null : allResult;
		} catch (Exception e) {
			_LOG.error("#getAll(?): " + e);
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
