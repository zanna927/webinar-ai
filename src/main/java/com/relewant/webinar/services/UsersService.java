package com.relewant.webinar.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relewant.webinar.models.UserJsonDto;
import lombok.extern.slf4j.Slf4j;
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
    private static final String FILE_NAME = "user.json";
    
    public Resource buildExcel() throws IOException {
        
        ObjectMapper  objectMapper = getObjectMapper();
        objectMapper.readValue(new File(FILE_PATH + FILE_NAME), UserJsonDto.class);
        return null;
    }
    
    private ObjectMapper getObjectMapper(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.relewant.webinar.configuration");
        context.refresh();

        ObjectMapper objectMapper = context.getBean(ObjectMapper.class);
        return objectMapper;
    }
}
