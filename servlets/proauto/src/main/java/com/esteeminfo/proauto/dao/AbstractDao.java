package com.esteeminfo.proauto.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao {
	
//	@Autowired
//	EntityManagerFactory entityManagerFactory;
//
//	protected EntityManager getEntityManager(){
//		return entityManagerFactory.createEntityManager( );
//	}

	 @PersistenceContext	
	 EntityManager entityManager;	
	 
	 
		public EntityManager getEntityManager() {
			return this.entityManager;
		}
}
