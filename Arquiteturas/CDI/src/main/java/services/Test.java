/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author clovis
 */
@Path("/olaMundo")
public class Test {
    
    @GET
    @Path("teste")
    @Produces("text/plain")
    @Transactional
    public String testeConexao(){
    	return "Sucesso";
    }
    
}
