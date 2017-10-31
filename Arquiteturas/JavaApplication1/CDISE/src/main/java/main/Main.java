/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.enterprise.context.ApplicationScoped;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 *
 * @author clovis
 */
@ApplicationScoped
public class Main {
    public static void main(String[] args) throws IOException, TimeoutException, Exception {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        TestMain application = container.instance().select(TestMain.class).get();
        application.run();
        weld.shutdown();

    }
}
