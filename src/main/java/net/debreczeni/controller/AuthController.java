package net.debreczeni.controller;

import net.debreczeni.exception.AuthException;
import net.debreczeni.model.Manager;
import net.debreczeni.model.Seller;
import net.debreczeni.model.User;

public class AuthController {

    public User authenticate(String username, char[] password) throws AuthException {
        if(username.equals("admin")){
            return new Manager();
        }
        if(username.equals("test")){
            return new Seller();
        }

        throw new AuthException("Invalid login details");
    }
}
