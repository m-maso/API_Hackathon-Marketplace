package com.activitiesApp.mapper;

import com.activitiesApp.domain.dto.UsersDTO;
import com.activitiesApp.domain.dto.UserDTO;
import com.activitiesApp.domain.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public static UserDTO toDTO(User user)
    {
        return UserDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .userActivities(user.getUserActivities())
                .build();
    }

    public static UsersDTO toSimpleUserDTO(User user)
    {
        return UsersDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

}
