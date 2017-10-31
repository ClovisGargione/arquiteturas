/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import core.PasswordService;
import core.Token;
import dto.RetornoLoginDTO;
import facade.UserFacade;
import filter.AuthUtils;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import model.User;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author clovis
 */
@Path("/auth")
public class Authentication {
    
    private final static Logger logger = Logger.getLogger(Authentication.class);

    public static final String LOGING_ERROR_MSG = "Login ou senha incorretos";
    public static final String REGISTER_ERROR_MSG = "Usuário já cadastrado";

    @Inject
    UserFacade usuarioFacade;
    
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String informacoesLogin, @Context HttpServletRequest request) throws Exception {
            JSONObject json = new JSONObject(informacoesLogin);
            User user = new User();
            user.setEmail(json.getString("email"));
            user.setPassword(json.getString("password"));
            try {

                    Optional<User> foundUser = Optional.ofNullable(usuarioFacade.obterPorLogin(user.getEmail()));

                    if (foundUser.isPresent()
                                    && PasswordService.checkPassword(user.getPassword(), foundUser.get().getPassword())) {

                            User usuario = foundUser.get();

                            Token token = AuthUtils.createToken(request.getRemoteHost(), usuario.getID(), usuario.getEmail(), usuario.getEmail());

                            RetornoLoginDTO retorno = new RetornoLoginDTO();
                            retorno.setToken(token.getToken());
                            retorno.setIdUsuario(foundUser.get().getID());
                            retorno.setLogin(foundUser.get().getEmail());
                            Response resp = Response.ok().entity(retorno).build();
                            logger.info("Usuario "+user.getEmail()+" efetuou o login");
                            return resp;
                    }
            } catch (Exception e) {
                    logger.error("Nao foi possivel fazer o login do usuario "+user.getEmail()+":"+e);
            }

            logger.info("Usuario "+user.getEmail()+" tentou efetuar o login");

            return Response.status(Status.UNAUTHORIZED).entity(LOGING_ERROR_MSG).build();

    }
    
    @POST
	@Path("signup")
	public Response signup(@Valid String informacoesLogin, @Context HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject(informacoesLogin);
		User user = new User();
		user.setEmail(json.getString("email"));
		user.setPassword(json.getString("senha"));
		UserFacade facade = new UserFacade();
		user.setPassword(PasswordService.hashPassword(user.getPassword()));
		facade.adiciona(user);
		User savedUser = user;
		Token token = AuthUtils.createToken(request.getRemoteHost(), savedUser.getID(), savedUser.getEmail(), savedUser.getEmail());
		RetornoLoginDTO retorno = new RetornoLoginDTO();
		retorno.setToken(token.getToken());
		retorno.setIdUsuario(user.getID());
		retorno.setLogin(user.getEmail());
		Response resp = Response.ok().entity(retorno).build();
		logger.info("Usuario "+user.getEmail()+" efetuou o login");
		return resp;
	}
    
    
    
}
