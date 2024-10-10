package com.activitiesApp.controller;

import com.activitiesApp.domain.dto.ActivityDTO;
import com.activitiesApp.domain.dto.UsersDTO;
import com.activitiesApp.domain.entity.Activity;
import com.activitiesApp.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/activitiesApp/v1")
@RestController
public class ActivityController {

    private final ActivityService activityService;


    @Operation(summary = "Create a new activity",
            description = "Write only Name, Description and Max Capacity of the Activity")
    @PostMapping("/activity")
    public ResponseEntity<String> newActivity(@RequestBody Activity activity)
    {
        this.activityService.createActivity(activity);

        return new ResponseEntity<>("Activity created successfully", HttpStatus.CREATED);
    }


    @Operation(summary = "Update an activity information (only Activity name and Description fields)",
        description = "Sending the Activity ID through the URI to update it from the database")
    @PutMapping("/activities/{id}")
    public ResponseEntity<String> updateActivity(@PathVariable("id") String id, @RequestBody ActivityDTO activityDTO)
    {
        this.activityService.modifyActivity(id, activityDTO);

        return new ResponseEntity<>("Activity updated successfully", HttpStatus.OK);
    }


    @Operation(summary = "Delete an activity",
            description = "Sending the Activity ID through the URI to delete it from the database")
    @DeleteMapping("/activities/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable("id") String id)
    {
        this.activityService.deleteActivity(id);

        return new ResponseEntity<>("Activity deleted successfully", HttpStatus.OK);
    }


    @Operation(summary = "Get one activity",
            description = "Sending the Activity ID through the URI to retrieve it from the database")
    @GetMapping("/activities/{id}")
    public ResponseEntity<ActivityDTO> getActivity(@PathVariable("id") String id)
    {
        ActivityDTO foundedActivity = this.activityService.getOneActivity(id);

        return new ResponseEntity<>(foundedActivity, HttpStatus.OK);
    }


    @Operation(summary = "Show all activities")
    @GetMapping("/allActivities")
    public ResponseEntity<List<ActivityDTO>> getAllActivities()
    {
        List<ActivityDTO> foundedActivities = this.activityService.getAllActivities();

        return new ResponseEntity<>(foundedActivities, HttpStatus.OK);
    }



    @Operation(summary = "Register a user into an activity")
    @PutMapping("/activities/{id}/user/{userId}")
    public ResponseEntity<String> registrationIntoActivity(@PathVariable("id") String id, @PathVariable("userId") String userId)
    {
        this.activityService.registerUserIntoActivity(id, userId);

        return new ResponseEntity<>("User registered into an activity successfully", HttpStatus.OK);
    }

    @Operation(summary = "Show all users for one activity")
    @GetMapping("/activities/{id}/users")
    public ResponseEntity<List<UsersDTO>> getAllUsersInTheActivity(@PathVariable("id") String id)
    {
        List<UsersDTO> foundedUsers = this.activityService.getAllUsersByActivity(id);

        return new ResponseEntity<>(foundedUsers, HttpStatus.OK);
    }



    @Operation(summary = "Import activities from Json File",
        description = "Save list of activities from JSON file")
    @PostMapping("/activities/importfile")
    public ResponseEntity<String> importActivitiesFromJsonFile(@RequestBody String jsonFilepath)
    {
        this.activityService.importActivitiesFromFile(jsonFilepath);

        return new ResponseEntity<>("Activities imported successfully", HttpStatus.OK);
    }


    @Operation(summary = "Export activities",
        description = "Export list of activities in JSON format")
    @GetMapping("/activities/export")
    public ResponseEntity<String> exportActivities()
    {
        this.activityService.exportActivities();

        return new ResponseEntity<>("Activities exported successfully", HttpStatus.OK);
    }

}
