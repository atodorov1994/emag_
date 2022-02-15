package com.emag.service;

import com.emag.model.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class AbstractService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;
}
