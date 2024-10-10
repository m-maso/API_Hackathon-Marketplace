package com.activitiesApp.service.impl;

import com.activitiesApp.domain.dto.ActivitiesDTO;
import com.activitiesApp.domain.dto.UserDTO;
import com.activitiesApp.domain.entity.User;
import com.activitiesApp.exception.UserAlreadyExistsException;
import com.activitiesApp.exception.UserNotFoundException;
import com.activitiesApp.mapper.UserMapper;
import com.activitiesApp.repository.UserRepository;
import com.activitiesApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND_ERROR = "User with id %s not found";

    private final UserRepository userRepository;


    @Override
    public void addUser(User user)
    {
        if(this.userRepository.findByEmail(user.getEmail()).isPresent())
        {
          throw new UserAlreadyExistsException("This user " + user.getFirstName() + " " + user.getLastName() +
                  " has already been registered");
        }

        this.userRepository.save(user);
    }


    @Override
    public void modifyUser(String id, UserDTO updatedUser)
    {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR, id))
        );

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());

        this.userRepository.save(user);
    }


    @Override
    public void deleteUser(String id)
    {
        this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR, id))
        );

        this.userRepository.deleteById(id);
    }


    @Override
    public UserDTO getOneUser(String id)
    {
        return this.userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR, id))
                );
    }


    @Override
    public List<UserDTO> getAllUsers()
    {
        return this.userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }


    @Override
    public List<ActivitiesDTO> getAllActivitiesByUser(String id)
    {
        User searchedUserDB = this.userRepository.findById(id)
            .orElseThrow(
                () -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR, id))
        );

        return searchedUserDB.getUserActivities();
    }
}
