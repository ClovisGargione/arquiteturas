/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import interfaces.IUserDao;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author clovis
 */
public class UserFacade {
    private final static Logger logger = Logger.getLogger(UserFacade.class);
    @Inject
    private IUserDao usuarioDao;

    public UserFacade() {
    }
    
    @Transactional
    public void adiciona(User u) throws Exception {

        User usuario = usuarioDao.buscaPorLogin(u.getEmail());
        if (usuario != null) {
            throw new Exception("Login já cadastrado");
        }
        usuarioDao.adiciona(u);

    }
    
    @Transactional
    public void adicionaTeste(User u) throws Exception {
        
        User usuario = usuarioDao.buscaPorLogin(u.getEmail());
        
        if (usuario != null) {
            throw new Exception("Login já cadastrado");
        }
        usuarioDao.adiciona(u);
      
    }
    
    @Transactional
    public void altera(User usuario) throws Exception {

        //deve retornar o mesmo usuário
        User usuarioBd = usuarioDao.buscaPorId(usuario.getID());
        //verificando se já existe outro usuário com o mesmo login
        if (usuarioBd == null) {
            throw new Exception("Login não cadastrado");
        }
        usuarioBd.setEmail(usuario.getEmail());
        usuarioDao.altera(usuarioBd);
    }

    public User obterPorId(Long id) throws Exception {
        try {
            User usuario = usuarioDao.buscaPorId(id);
            return usuario;
        } catch (Exception e) {
            logger.error("Nao foi possivel localizar o usuario com Id " + id);
            throw e;
        }

    }

    public User obterPorLogin(String login) throws Exception {
        try {
            User usuario = usuarioDao.buscaPorLogin(login);
            return usuario;
        } catch (Exception e) {
            logger.error("Nao foi possivel localizar o usuario com login " + login);
            throw e;
        }

    }
    
    @Transactional
    public void remove(User usuario) throws Exception {
        try {
            usuarioDao.remove(usuario);
        } catch (Exception e) {
            logger.error("Nao foi possivel remover o usuario " + usuario.getEmail());
        }
        logger.info("Removido o usuario " + usuario.getEmail());
    }

    public List<User> lista() throws Exception {

        try {
            List<User> listaUsuarios = usuarioDao.listaUsuarios();
            return listaUsuarios;
        } catch (Exception e) {
            logger.error("Erro na listagem de usuarios:" + e);
            throw e;
        }

    }
}
