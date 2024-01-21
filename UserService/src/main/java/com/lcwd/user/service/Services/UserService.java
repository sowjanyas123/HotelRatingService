package com.lcwd.user.service.Services;

import com.lcwd.user.service.entity.User;

import java.util.List;

public interface UserService {

    //user operations

    //create
    User saveUser(User user);
    //get user
    List<User> getAllUser();
    //get single user by Id
    User getUserById(String userId);

    //delete user
void DeleteUserById(String userId);
    //update user

    User updateUser(User user, String userId);
}
