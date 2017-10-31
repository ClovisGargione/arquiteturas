/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import facade.UserFacade;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import javax.inject.Inject;
import javax.inject.Singleton;
import model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author clovis
 */
@Singleton
public class TestMain {
    @Inject
    UserFacade facade;
    private static final Logger logger = Logger.getLogger(TestMain.class);
    
    public void run() throws IOException, TimeoutException, Exception {
        logger.debug("application initialized");
        for(User u : facade.lista()){
                System.out.println(u.getID() + " " + u.getEmail() + " " + u.getPassword() ); 
        }
    }

}
