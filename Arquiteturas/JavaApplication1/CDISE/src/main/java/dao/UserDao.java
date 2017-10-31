/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.IUserDao;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author clovis
 */
public class UserDao implements IUserDao{
    
    @Inject
    private EntityManager entityManager;
    private final static Logger logger = Logger.getLogger(UserDao.class);

    public UserDao() {
    }

    @Override
    public User buscaPorId(Long id) {
        if(id == null){
            return null;
        }
        User model = entityManager.find(User.class, id);
        return model;
    }

    @Override
    public List<User> listaUsuarios() {
        TypedQuery<User> usuarios = entityManager
                .createQuery("select u from User u", User.class);
        return usuarios.getResultList();
    }

    @Override
    public User buscaPorLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", login);
        User usuario = null;
        try {
            usuario = query.getSingleResult();
        } catch (Exception e) {
            //nao faz nada.. nao achou o usuario, retorna null
        }
        return usuario;
    }

    @Override
    public void altera(User user) {
        try {
            entityManager.merge(user);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public void adiciona(User user) {
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User user) {
        entityManager.remove(user);
    }
}
