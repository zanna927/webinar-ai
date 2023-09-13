package com.relewant.webinar.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class UserJsonDto {
    
    private List<UserDto> users;
}
