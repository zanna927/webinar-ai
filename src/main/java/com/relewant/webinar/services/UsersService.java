package com.relewant.webinar.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relewant.webinar.models.UserJsonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class UsersService {
    
    private static final String FILE_PATH = "src/main/resources/";
    private static final String FILE_NAME = "users.json";

    @Autowired
    private ObjectMapper objectMapper;
    
    public Resource buildExcel() throws IOException {
        
        UserJsonDto userJsonDto = objectMapper.readValue(new File(FILE_PATH + FILE_NAME), UserJsonDto.class);
        log.info("users: {}", userJsonDto);
        return null;
    }
}
