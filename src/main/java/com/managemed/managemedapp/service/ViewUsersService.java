package com.managemed.managemedapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.repository.UserRepository;

@Service
public class ViewUsersService {

    @Autowired
    UserRepository userRepository;
    
    public List<User> viewUsers() {
        return userRepository.findAllExceptRole("Customer");
    }

}
