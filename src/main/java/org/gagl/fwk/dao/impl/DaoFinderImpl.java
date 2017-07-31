package org.gagl.fwk.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gagl.fwk.dao.DaoFinder;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.Transformers;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class DaoFinderImpl extends DaoAbstractImpl implements DaoFinder, Serializable {
	
	private static final long serialVersionUID = 5895787748189673722L;

	/*
	 * Ejecuta una consulta almacenada en el repositorio de hibernate.queries.xml
	 * @param pQueryName nombre del query
	 * @return List<T> lista de objetos
	 * @see org.gagl.fwk.dao.DaoFinder#findByQueryName(java.lang.String)
	 */
	public <T> List<T> findByQueryName(String pQueryName,@SuppressWarnings("rawtypes") Class pEntity) {
		Query query= this.getCurrentSession().getNamedQuery(pQueryName);
		query.setResultTransformer(Transformers.aliasToBean((pEntity)));
		@SuppressWarnings("unchecked")
		List<T> listaObjeto=query.list();
		return listaObjeto;
	}
	
	/*
	 * Ejecuta una consulta almacenada en el repositorio de hibernate.queries.xml
	 * @param pQueryName nombre del query
	 * @param pParams map con los parametros
	 * @return List<T> lista de objetos
	 * @see org.gagl.fwk.dao.DaoFinder#findByQueryName(java.lang.String, java.util.Map)
	 */
	public <T> List<T> findByQueryName(String pSqlQuery, Map<Integer,Object> pParams) {
		Query query= this.getCurrentSession().getNamedQuery(pSqlQuery);
		this.setQueryParameters(query, pParams);
		@SuppressWarnings("unchecked")
		List<T> list=query.list();
		return list;
	}

	/*
	 * Ejecuta una consulta almacenada en el repositorio de hibernate.queries.xml
	 * @param pQueryName nombre del query
	 * @param pParams map con los parametros
	 * @param pEntity nombre de la entidad
	 * @return List<T> lista de entidades 
	 * @see org.gagl.fwk.dao.DaoFinder#findByQueryName(java.lang.String, java.util.Map, java.lang.Class)
	 */
	public <T> List<T> findByQueryName(String pSqlQuery, Map<Integer,Object> pParams,@SuppressWarnings("rawtypes") Class pEntity) {
		Query query= this.getCurrentSession().getNamedQuery(pSqlQuery);		
		this.setQueryParameters(query, pParams);
		query.setResultTransformer(Transformers.aliasToBean((pEntity)));
		@SuppressWarnings("unchecked")
		List<T> list=query.list();
		return list;
	}	
	
	/*
	 * Ejecuta una consulta en sql puro
	 * @param pSqlQuery query a ejecutar
	 * @param map de parametros de la consulta
	 * @param pEntity tipo de la entidad que retorna
	 * @return List<T> lista de entidades que retornara  
	 * @see org.gagl.fwk.dao.DaoFinder#findBySqlQuery(java.lang.String, java.lang.Class)
	 */
	public <T> List<T> findBySqlQuery(String pSqlQuery, Map<Integer,Object> pParams,@SuppressWarnings("rawtypes") Class pEntity) {
		Query query= this.getCurrentSession().createSQLQuery(pSqlQuery);
		this.setQueryParameters(query, pParams);
		query.setResultTransformer(Transformers.aliasToBean((pEntity)));
		@SuppressWarnings("unchecked")
		List<T> list=query.list();
		return list;
	}
	
	/*
	 * Ejecuta una consulta en sql puro
	 * @param pSqlQuery query a ejecutar
	 * @param pEntity tipo de la entidad que retorna
	 * @return List<T> lista de entidades que retornara
	 */
	public <T> List<T> findBySqlQuery(String pSqlQuery, @SuppressWarnings("rawtypes") Class pEntity) {
		Query query= this.getCurrentSession().createSQLQuery(pSqlQuery);
		query.setResultTransformer(Transformers.aliasToBean((pEntity)));
		@SuppressWarnings("unchecked")
		List<T> list=query.list();
		return list;
	}
	
	/*
	 * Ejecuta una consulta con detached criteria
	 * @param DetachedCriteria pCriteria criteria a ejecutar
	 * @return List<T> lista de entidades que retornara
	 */
	public <T> List<T> findByDetachedCriteria(DetachedCriteria pCriteria){
		
		Session session=this.getCurrentSession();
		Criteria cr=pCriteria.getExecutableCriteria(session);
		
		return cr.list();		
		
	}
	
	/*
	 * Ejecuta una consulta con detached criteria
	 * @param Collection<T> pList lista que contiene el objeto entidad
	 * @return T entidad que retornara como unico resultado
	 */		
	public <T> T uniqueResult(Collection<T> pList){
		return (T) DataAccessUtils.uniqueResult(pList);
	}
	
	
	public void setQueryParameters(Query pQuery,Map<Integer,Object> pParams){
		for (Map.Entry<Integer,Object> entry : pParams.entrySet()) {
		    Integer key = entry.getKey();
		    Object value = entry.getValue();
		    pQuery.setParameter(key,value);
		}
	}

}
