/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.User;

/**
 *
 * @author clovis
 */
public interface IUserDao {
    User buscaPorId(Long id);
    List<User> listaUsuarios();
    User buscaPorLogin(String login);
    void altera(User user);
    void adiciona(User user);
    void remove(User user);
}
