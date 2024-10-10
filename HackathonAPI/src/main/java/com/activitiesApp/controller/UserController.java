package com.activitiesApp.controller;

import com.activitiesApp.domain.dto.ActivitiesDTO;
import com.activitiesApp.domain.dto.UserDTO;
import com.activitiesApp.domain.entity.User;
import com.activitiesApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/activitiesApp/v1")
@RestController
public class UserController {

    private final UserService userService;


    @Operation(summary = "Create a new user",
            description = "Write only First name, Last name and Email fields")
    @PostMapping("/user")
    public ResponseEntity<String> newUser(@RequestBody User user)
    {
        this.userService.addUser(user);

        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }


    @Operation(summary = "Update a user personal information (only First name, Last name and Email fields)",
            description = "Sending the User ID through the URI to update it from the database")
    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") String id, @RequestBody UserDTO userDTO)
    {
        this.userService.modifyUser(id, userDTO);

        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }


    @Operation(summary = "Delete a user",
            description = "Sending the User ID through the URI to delete it from the database")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id)
    {
        this.userService.deleteUser(id);

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }


    @Operation(summary = "Get one user's information",
            description = "Sending the User ID through the URI to retrieve it from the database")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") String id)
    {
        UserDTO foundedUser = this.userService.getOneUser(id);

        return new ResponseEntity<>(foundedUser, HttpStatus.OK);
    }


    @Operation(summary = "Show all users")
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        List<UserDTO> foundedUsers = this.userService.getAllUsers();

        return new ResponseEntity<>(foundedUsers, HttpStatus.OK);
    }


    @Operation(summary = "Show all activities for one user")
    @GetMapping("/users/{id}/activities")
    public ResponseEntity<List<ActivitiesDTO>> getAllActivitiesOfUser(@PathVariable("id") String id)
    {
        List<ActivitiesDTO> foundedActivities = this.userService.getAllActivitiesByUser(id);

        return new ResponseEntity<>(foundedActivities, HttpStatus.OK);
    }

}
