package org.gagl.fwk.dao;

import java.util.List;
import java.util.Map;

public interface DaoFinder {
	
	public <T> List<T> findByQueryName(String pQueryName,@SuppressWarnings("rawtypes") Class pEntity);
	public <T> List<T> findByQueryName(String pSqlQuery, Map<Integer,Object> pParams);
	public <T> List<T> findByQueryName(String pSqlQuery, Map<Integer,Object> pParams,@SuppressWarnings("rawtypes") Class pEntity);
	public <T> List<T> findBySqlQuery(String pSqlQuery, @SuppressWarnings("rawtypes") Class pEntity);
	public <T> List<T> findBySqlQuery(String pSqlQuery, Map<Integer,Object> pParams,@SuppressWarnings("rawtypes") Class pEntity);		

}
