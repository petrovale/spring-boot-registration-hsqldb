package com.company.webapp.service;

import com.company.webapp.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
