package com.activitiesApp.domain.entity;

import com.activitiesApp.domain.dto.ActivitiesDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Date registerDate;

    @Builder.Default
    private List<ActivitiesDTO> userActivities = new ArrayList<>();


    public User(String firstName, String lastName, String email)
    {
        this.userId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registerDate = new Date();
    }

}
