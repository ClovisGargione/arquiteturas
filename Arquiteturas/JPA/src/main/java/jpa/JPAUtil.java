/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author clovis
 */
public class JPAUtil {
    	private static final String PERSISTENCE_UNIT = "postgres";
	
	private static ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();
	private static EntityManagerFactory entityManagerFactory;
	
	
	private JPAUtil() {
	}
	
	public static EntityManager getEntityManager() {
	
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		}
		
		EntityManager entityManager = threadEntityManager.get();
		
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = entityManagerFactory.createEntityManager();
			JPAUtil.threadEntityManager.set(entityManager);
		}
	
		return entityManager;
	}
	
	public static void closeEntityManager() {
		
		EntityManager em = threadEntityManager.get();
		
		if (em != null) {
			
			EntityTransaction transaction = em.getTransaction();
		
			if (transaction.isActive()) {
				transaction.commit();
			}
			em.close();
			threadEntityManager.set(null);
		}
	}
	
	public static void closeEntityManagerFactory() {
		closeEntityManager();
		entityManagerFactory.close();
	}
}
