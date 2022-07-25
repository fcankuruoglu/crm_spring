package com.example.crm.business.abstracts;

import com.example.crm.dataTransferObjects.UserDto;
import com.example.crm.entities.concretes.User;

import java.util.List;

public interface UserService {

    void save(User user);
    void delete(Integer id);
    void activate(Integer id);
    void disable(Integer id);

    void saveRole(String roleName);
    void addRoleToUser(String username, String roleName);

    User getUserWithPassword(String username);
    List<User> getUsersWithPassword();

    UserDto getUser(String username);
    List<UserDto> getUsers();

}
