package com.relewant.webinar.controllers;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    
    
    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<Resource> serveFile() {
//        Resource file = storageService.loadAsResource(filename);
//
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        return null;
    }
}
