package com.emag.service;

import com.emag.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService {

    @Autowired
    protected UserRepository userRepository;
}
