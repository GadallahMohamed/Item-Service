package com.item.service;

import com.item.model.Users;

public interface UsersService {
	
	boolean saveUser(Users user);
    boolean updateUser(Users user);
    boolean removeUser(int id);
    Users   authenticate(String email, String password);

}
