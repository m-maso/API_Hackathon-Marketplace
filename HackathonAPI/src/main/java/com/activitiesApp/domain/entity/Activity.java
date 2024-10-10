package com.activitiesApp.domain.entity;

import com.activitiesApp.domain.dto.UsersDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "activities")
public class Activity {

    @Id
    private String activityId;
    private String activityName;
    private String description;
    private int maxCapacity;

    @Builder.Default
    private List<UsersDTO> registeredUsersInActivity = new ArrayList<>();


    public Activity(String activityName, String description, int maxCapacity)
    {
        this.activityId = UUID.randomUUID().toString();
        this.activityName = activityName;
        this.description = description;
        this.maxCapacity = maxCapacity;
    }

}
