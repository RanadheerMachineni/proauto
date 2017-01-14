package com.esteeminfo.proauto.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao {

	@PersistenceContext
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return this.entityManager;
	}
}
