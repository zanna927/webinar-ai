package com.relewant.webinar.controllers;

import com.relewant.webinar.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("/users")
@Slf4j
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/old")
    @ResponseBody
    public ResponseEntity<byte[]> serveFile() throws IOException {
        byte[] content = usersService.buildExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "users.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(content);
    }
}
