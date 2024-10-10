package com.activitiesApp.domain.dto;

import lombok.*;

import java.util.List;


@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<ActivitiesDTO> userActivities;

}
