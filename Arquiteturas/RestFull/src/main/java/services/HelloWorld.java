/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author clovis
 */
@Path("/helloworld")
public class HelloWorld {
    
        @GET
	@Path("hello")
        @Produces(MediaType.TEXT_PLAIN)
	public String signup() {
            return "Olá mundo REST!";
	}
}
