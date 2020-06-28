package com.example.dao;

import com.example.model.User;


public interface UserCustomDao {

    User findByEmail(String email);

    User findByUsername(String username);
}
