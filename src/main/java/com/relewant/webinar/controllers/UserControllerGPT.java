package com.relewant.webinar.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relewant.webinar.entities.UserGPT;
import com.relewant.webinar.services.UserServiceGPT;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllerGPT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private UserServiceGPT userServiceGPT;

    @GetMapping("/gpt")
    public ResponseEntity<byte[]> generateXLSX() throws IOException {
        // Leggi i dati JSON e convertili in una lista di utenti

        Resource resource = resourceLoader.getResource("classpath:users.json");
        List<UserGPT> users = userServiceGPT.getUsers();

        // Crea un documento XLSX
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // Crea l'intestazione
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("First Name");
        headerRow.createCell(1).setCellValue("Last Name");
        headerRow.createCell(2).setCellValue("Birthday");

        // Aggiungi dati agli utenti alla tabella
        int rowNum = 1;
        for (UserGPT user : users) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getFirst_name());
            row.createCell(1).setCellValue(user.getLast_name());
            row.createCell(2).setCellValue(user.getBirthday());
        }

        // Converti il documento in un array di byte
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();
        outputStream.close();

        // Imposta gli header HTTP per la risposta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentLength(bytes.length);
        headers.setContentDispositionFormData("attachment", "users.xlsx");

        // Restituisci il file XLSX come risposta
        return new ResponseEntity<>(bytes, headers, org.springframework.http.HttpStatus.OK);
    }
}
