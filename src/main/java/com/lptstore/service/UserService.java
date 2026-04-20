

package com.lptstore.service;

import java.util.List;


import com.lptstore.entity.user;

public interface UserService {
    void saveUsers(user user);
    List<user> getAllUsersList();
    boolean checkLogin(String email, String password);
    user getUserById(Long id);
    void deleteUser(Long id);
    String resetPassword(String email);
	
}
