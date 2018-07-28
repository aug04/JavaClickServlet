package com.example.click.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.example.click.dao.ClickServletDao;
import com.example.click.entities.DeliverLog;

public class DeliverLogDaoImpl implements ClickServletDao<DeliverLog> {

	@Override
	public int create(DeliverLog obj, Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(DeliverLog obj, Connection conn) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

}
