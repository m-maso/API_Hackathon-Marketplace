package com.activitiesApp.service;

import com.activitiesApp.domain.dto.ActivitiesDTO;
import com.activitiesApp.domain.dto.UserDTO;
import com.activitiesApp.domain.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user);
    void modifyUser(String id, UserDTO userDTO);
    void deleteUser(String id);
    UserDTO getOneUser(String id);
    List<UserDTO> getAllUsers();

    List<ActivitiesDTO> getAllActivitiesByUser(String id);
}
