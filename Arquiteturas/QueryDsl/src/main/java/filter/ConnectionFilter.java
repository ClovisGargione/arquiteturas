/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import jpa.JPAUtil;
import org.apache.log4j.Logger;

/**
 *
 * @author clovis
 */
public class ConnectionFilter implements Filter{
    private final static Logger logger = Logger.getLogger(ConnectionFilter.class);

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			chain.doFilter(request, response);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Erro: ",e);
			e.printStackTrace();
			if(entityManager.isOpen()){
				entityManager.getTransaction().rollback();
			}
		}
		finally {
			if(entityManager.isOpen()){
				entityManager.close();
			}
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
