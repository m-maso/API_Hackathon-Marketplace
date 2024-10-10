package com.activitiesApp.service;

import com.activitiesApp.domain.dto.ActivityDTO;
import com.activitiesApp.domain.dto.UsersDTO;
import com.activitiesApp.domain.entity.Activity;

import java.util.List;

public interface ActivityService {

    void createActivity(Activity activity);
    void modifyActivity(String id, ActivityDTO activityDTO);
    void deleteActivity(String id);
    ActivityDTO getOneActivity(String id);
    List<ActivityDTO> getAllActivities();

    void registerUserIntoActivity(String id, String userId);
    List<UsersDTO> getAllUsersByActivity(String id);

    void importActivitiesFromFile(String jsonFilepath);
    String exportActivities();
}
