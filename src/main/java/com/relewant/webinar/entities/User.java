package com.relewant.webinar.entities;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class User {

    private String firstName;
    private String lastName;
    private String birthday;
}
