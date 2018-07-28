package com.example.click.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			_LOG.error("#create(?): " + e);
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				_LOG.error("#create(?): " + e);
			}
		}
		
		return 0;
	}

	@Override
	public boolean update(Ads obj, Connection conn) {
		try {
			
			
		} catch (Exception e) {
			_LOG.error("#update(?): " + e);
		}
		
		return false;
	}

	@Override
	public boolean delete(int id, Connection conn) {
		try {
			
			
		} catch (Exception e) {
			_LOG.error("#delete(?): " + e);
		}
		
		return false;
	}

	@Override
	public Ads get(int id, Connection conn) {
		try {
			
			
		} catch (Exception e) {
			_LOG.error("#get(?): " + e);
		}
		
		return null;
	}

	@Override
	public List<Ads> getAll(Connection conn) {
		try {
			
			
		} catch (Exception e) {
			_LOG.error("#getAll(): " + e);
		}
		
		return null;
	}

}
