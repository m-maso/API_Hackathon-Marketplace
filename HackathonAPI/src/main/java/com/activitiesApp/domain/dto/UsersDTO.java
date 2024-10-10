package com.activitiesApp.domain.dto;

import lombok.*;


@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private String firstName;
    private String lastName;
    private String email;
}
