package com.example.click.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.example.click.dao.ClickServletDao;
import com.example.click.entities.Media;

public class MediaDaoImpl implements ClickServletDao<Media> {

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

}
