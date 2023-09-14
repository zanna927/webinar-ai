package com.relewant.webinar.controllers;

import com.relewant.webinar.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class UsersController {
    
    @Autowired
    private UsersService usersService;
    
    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<Resource> serveFile() throws IOException {
            log.debug("get users");
            usersService.buildExcel();
            log.debug("end users");
        return null;
    }
}
