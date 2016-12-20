package com.esteeminfo.proauto.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDao {
	
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "proauto" );;

	protected EntityManager getEntityManager(){
		return emfactory.createEntityManager( );
	}
}
