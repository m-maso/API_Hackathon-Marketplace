package com.activitiesApp.service.impl;

import com.activitiesApp.domain.dto.ActivitiesDTO;
import com.activitiesApp.domain.dto.ActivityDTO;
import com.activitiesApp.domain.dto.UsersDTO;
import com.activitiesApp.domain.entity.Activity;
import com.activitiesApp.domain.entity.User;
import com.activitiesApp.exception.ActivityNotFoundException;
import com.activitiesApp.exception.UserNotFoundException;
import com.activitiesApp.mapper.ActivityMapper;
import com.activitiesApp.mapper.UserMapper;
import com.activitiesApp.repository.ActivityRepository;
import com.activitiesApp.repository.UserRepository;
import com.activitiesApp.service.ActivityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private static final String ACTIVITY_NOT_FOUND_ERROR = "Activity with id %s not found";
    private static final String USER_NOT_FOUND_ERROR = "User with id %s not found";

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;


    @Override
    public void createActivity(Activity activity)
    {
        this.activityRepository.save(activity);
    }


    @Override
    public void modifyActivity(String id, ActivityDTO updatedActivity)
    {
        Activity activity = this.activityRepository.findById(id).orElseThrow(
                () -> new ActivityNotFoundException(String.format(ACTIVITY_NOT_FOUND_ERROR, id))
        );

        activity.setActivityName(updatedActivity.getActivityName());
        activity.setDescription(updatedActivity.getDescription());

        this.activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(String id)
    {
        this.activityRepository.findById(id).orElseThrow(
                () -> new ActivityNotFoundException(String.format(ACTIVITY_NOT_FOUND_ERROR, id))
        );

        this.activityRepository.deleteById(id);
    }

    @Override
    public ActivityDTO getOneActivity(String id)
    {
        return this.activityRepository.findById(id)
                .map(ActivityMapper::toDto)
                .orElseThrow(
                        () -> new ActivityNotFoundException(String.format(ACTIVITY_NOT_FOUND_ERROR, id))
                );
    }

    @Override
    public List<ActivityDTO> getAllActivities()
    {
        return this.activityRepository.findAll()
                .stream()
                .map(ActivityMapper::toDto)
                .toList();
    }

    @Override
    public void registerUserIntoActivity(String id, String userId) {

        Activity foundedActivity = this.activityRepository.findById(id).orElseThrow(
                () -> new ActivityNotFoundException(String.format(ACTIVITY_NOT_FOUND_ERROR, id))
        );
        ActivitiesDTO foundedActivityDTO = this.activityRepository.findById(id)
                .map(ActivityMapper::entityToActivitiesDTO)
                .orElseThrow(
                () -> new ActivityNotFoundException(String.format(ACTIVITY_NOT_FOUND_ERROR, id))
        );
        User foundedUser = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR, id))
        );
        UsersDTO foundedUsersDTO = this.userRepository.findById(userId)
                .map(UserMapper::toSimpleUserDTO).orElseThrow(
                () -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR, id))
        );

        foundedActivity.getRegisteredUsersInActivity().add(foundedUsersDTO);
        this.activityRepository.save(foundedActivity);

        foundedUser.getUserActivities().add(foundedActivityDTO);
        this.userRepository.save(foundedUser);

    }


    @Override
    public List<UsersDTO> getAllUsersByActivity(String id)
    {
        Activity searchedActivityDB = this.activityRepository.findById(id)
                .orElseThrow(
                        () -> new ActivityNotFoundException(String.format(ACTIVITY_NOT_FOUND_ERROR, id))
                );

        return searchedActivityDB.getRegisteredUsersInActivity();
    }


    @Override
    public void importActivitiesFromFile(String jsonFilepath)
    {
        File jsonFile = new File("src/main/resources/json/Activities.json");

        if(jsonFile.exists())
        {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Activity> activitiesFromFile;

            try {
                activitiesFromFile = objectMapper.readValue(jsonFile, new TypeReference<>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            this.activityRepository.saveAll(activitiesFromFile);
        }
        else {
            throw new RuntimeException("File not found");
        }
    }


    @Override
    public String exportActivities() {
        List<Activity> searchedActivitiesDB = this.activityRepository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            return objectMapper.writeValueAsString(searchedActivitiesDB) ;
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException("Couldn't export activities list", e);
        }
    }
}
