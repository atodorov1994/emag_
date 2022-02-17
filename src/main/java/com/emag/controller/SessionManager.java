package com.emag.controller;

import com.emag.exception.AuthenticationException;
import com.emag.exception.BadRequestException;
import com.emag.model.pojo.User;
import com.emag.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionManager {

    private static final String LOGGED_USER_ID = "logged_user_id";

    @Autowired
    private UserRepository userRepository;

    public void isLoggedVerification(HttpSession session) {
        if (session.getAttribute(LOGGED_USER_ID) != null ){
            throw new AuthenticationException("You are already logged in!");
        }
    }

    public void loginUser(HttpSession session, long id) {
        session.setAttribute(LOGGED_USER_ID , id);
    }

    public void logoutUser(HttpSession session) {
        if (session.getAttribute(LOGGED_USER_ID) == null){
            throw new BadRequestException("You have to be logged in to logout!");
        }
        session.invalidate();
    }

    public boolean userHasPrivileges(HttpSession session, long id) {
        User user = getLoggedUser(session);
        if (user.isAdmin()){
            return true;
        }
        return id == user.getId();
    }

    public boolean userHasPrivileges(HttpSession session) {
        User user = getLoggedUser(session);
        return user.isAdmin();
    }

    private User getLoggedUser(HttpSession session) {
        if (session.getAttribute(LOGGED_USER_ID) == null) {
            throw new AuthenticationException("You have to be logged in!");
        }
        long userId = (long) session.getAttribute(LOGGED_USER_ID);
        return userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("The user does not exist!"));
    }
}
