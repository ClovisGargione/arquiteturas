/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import jpa.Facade.UserFacade;
import jpa.JPAUtil;
import model.User;

/**
 *
 * @author clovis
 */
public class TestMain {
    
    public static void main(String[] args){
        User user = new User();
        UserFacade facade = new UserFacade();
         user.setEmail("clovis@gmail.com");
        user.setPassword("123456");
        try {
            EntityManager em = JPAUtil.getEntityManager();
            em.getTransaction().begin();
            facade.adicionaTeste(user);
            em.getTransaction().commit();
            for(User u : facade.lista()){
                System.out.println(u.getID() + " " + u.getEmail() + " " + u.getPassword() ); 
            }
            em.close();
        } catch (Exception ex) {
            System.out.println("Ero ao adicionar " + ex);
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            System.out.println("Conexão fechada");
        }
        
    }
    
}
