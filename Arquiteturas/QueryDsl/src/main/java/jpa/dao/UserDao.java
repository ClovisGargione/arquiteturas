/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.dao;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import jpa.interfaces.IUserDao;
import model.QUser;
import model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author clovis
 */
public class UserDao implements IUserDao {

    private final EntityManager entityManager;
    private final static Logger logger = Logger.getLogger(UserDao.class);

    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User buscaPorId(Long id) {
        JPQLQuery<User> query = new JPAQuery<>(entityManager);
        QUser qUser = QUser.user;
        User user = null;
        try {
            user = query.from(qUser).where(qUser.ID.eq(id)).fetchFirst();
        } catch (Exception e) {
           //nao faz nada.. nao achou o usuario, retorna null
        }
        return user;
    }

    @Override
    public List<User> listaUsuarios() {
        JPQLQuery<User> query = new JPAQuery<>(entityManager);
        QUser qUser = QUser.user;
        List<User> users = query.from(qUser).fetch();
        return users;
    }

    @Override
    public User buscaPorLogin(String login){
        JPQLQuery<User> query = new JPAQuery<>(entityManager);
        QUser qUsuario = QUser.user;
        User usuario = null;
        try {
            usuario = query.from(qUsuario).where(qUsuario.email.eq(login)).fetchFirst();
        } catch (Exception e) {
             logger.error(e);
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
