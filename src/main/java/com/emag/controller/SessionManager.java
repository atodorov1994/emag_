package com.emag.controller;

import com.emag.exception.AuthenticationException;
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
        if (session.getAttribute(LOGGED_USER_ID) == null ){
            throw new AuthenticationException("Please log in!");
        }
    }
}
