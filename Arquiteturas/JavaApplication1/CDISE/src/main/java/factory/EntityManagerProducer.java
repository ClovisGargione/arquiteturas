/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author clovis
 */
public class EntityManagerProducer {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("postgres");

    @Produces
    @ApplicationScoped
    public EntityManager criaEntityManager() {
        return FACTORY.createEntityManager();
    }

    public void finaliza(@Disposes EntityManager manager) {
        manager.close();
    }
}
