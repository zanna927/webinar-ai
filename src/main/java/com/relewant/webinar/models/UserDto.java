package com.relewant.webinar.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@Builder
public class UserDto {

    private String firstName;
    private String lastName;
    private String birthday;
}
