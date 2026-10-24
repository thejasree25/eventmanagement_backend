
package com.jsp.eventmanagement.service;

import com.jsp.eventmanagement.entity.User;

public interface UserService {

    User register(User user);

    User login(String email, String password);

    User googleLogin(String email, String name);
}
