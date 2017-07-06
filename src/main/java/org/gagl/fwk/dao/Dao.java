package org.gagl.fwk.dao;

public interface Dao {
	public void persist(Object pEntity) throws Exception;
	public void save(Object pEntity) throws Exception;
	public void update(Object pEntity) throws Exception;
	public void delete(Object pEntity) throws Exception;
	
}
