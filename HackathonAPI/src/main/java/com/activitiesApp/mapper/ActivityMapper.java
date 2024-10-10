package com.activitiesApp.mapper;

import com.activitiesApp.domain.dto.ActivitiesDTO;
import com.activitiesApp.domain.dto.ActivityDTO;
import com.activitiesApp.domain.entity.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public static ActivityDTO toDto(Activity activity)
    {
        return ActivityDTO.builder()
                .activityId(activity.getActivityId())
                .activityName(activity.getActivityName())
                .description(activity.getDescription())
                .maxCapacity(activity.getMaxCapacity())
                .registeredUsersInActivity(activity.getRegisteredUsersInActivity())
                .build();
    }


    public static ActivitiesDTO entityToActivitiesDTO(Activity activity)
    {
        return ActivitiesDTO.builder()
                .activityName(activity.getActivityName())
                .description(activity.getDescription())
                .maxCapacity(activity.getMaxCapacity())
                .build();
    }
}
