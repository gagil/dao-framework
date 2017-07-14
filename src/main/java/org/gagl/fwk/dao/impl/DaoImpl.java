package org.gagl.fwk.dao.impl;

import java.io.Serializable;

import org.gagl.fwk.dao.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl extends DaoAbstractImpl implements Dao,Serializable {

	private static final long serialVersionUID = 652819785887026302L;

	public void persist(Object pEntity) throws Exception {
		this.getCurrentSession().persist(pEntity);
	}

	public void save(Object pEntity) throws Exception {
		this.getCurrentSession().save(pEntity);
	}

	public void update(Object pEntity) throws Exception {
		this.getCurrentSession().update(pEntity);
	}

	public void delete(Object pEntity) throws Exception {
		this.getCurrentSession().delete(pEntity);			
	}
		

}
