package com.example.click.dao;

import java.sql.Connection;
import java.util.List;

public interface ClickServletDao<T> {

	int create(T obj, Connection conn);
	
	boolean update(T obj, Connection conn);
	
	boolean delete(int id, Connection conn);
	
	T get(int id, Connection conn);
	
	List<T> getAll(Connection conn);
	
}
