/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CDI;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

/**
 *
 * @author clovis
 */
@Interceptor
@Transactional
public class TransacionalInterceptor {

    @Inject
    private EntityManager entityManager;

    private EntityTransaction entityTransaction;

    @AroundInvoke
    public Object interceptor(InvocationContext context) throws Exception {
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Object result = context.proceed();
            entityTransaction.commit();
            return result;
        } catch (RuntimeException e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            throw e;
        }
    }
}
