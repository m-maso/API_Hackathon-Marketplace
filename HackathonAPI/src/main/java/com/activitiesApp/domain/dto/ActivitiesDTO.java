package com.activitiesApp.domain.dto;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivitiesDTO {

    private String activityName;
    private String description;
    private int maxCapacity;

}
