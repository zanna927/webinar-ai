package com.relewant.webinar.models;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJsonDto {
    
    private List<UserDto> users;
}
