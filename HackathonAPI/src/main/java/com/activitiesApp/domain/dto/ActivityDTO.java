package com.activitiesApp.domain.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {

    private String activityId;
    private String activityName;
    private String description;
    private int maxCapacity;
    private List<UsersDTO> registeredUsersInActivity;
}
