package com.relewant.webinar.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relewant.webinar.models.UserDto;
import com.relewant.webinar.models.UserJsonDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;

@Service
@Slf4j
public class UsersService {

    private static final String FILE_PATH = "src/main/resources/";
    private static final String FILE_NAME = "users.json";
    private static final String EXPORT_FILE_NAME = "users.xlsx";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ResourceLoader resourceLoader;

    public byte[] buildExcel() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + FILE_NAME);
        UserJsonDto userJsonDto = objectMapper.readValue(resource.getFile(), UserJsonDto.class);
        log.info("users: {}", userJsonDto);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Users");

        int rowCount = 0;
        rowCount = createHeader(sheet, rowCount);

        for (UserDto user : userJsonDto.getUsers()) {
            rowCount = generateRow(sheet, rowCount, user.getFirstName(), user.getLastName(), user.getBirthday());
        }

        try (FileOutputStream outputStream = new FileOutputStream(FILE_PATH + EXPORT_FILE_NAME)) {
            workbook.write(outputStream);
        }
        log.debug("file excel generato");

        Resource resourceOutput = resourceLoader.getResource("classpath:"  + EXPORT_FILE_NAME);

        byte[] content = new byte[]{};

        if (resourceOutput.exists()) {
            try (InputStream inputStream = resourceOutput.getInputStream()) {
                content = FileCopyUtils.copyToByteArray(inputStream);
            }
        }

        return content;
    }

    private static int generateRow(XSSFSheet sheet, int rowCount, String field1, String field2, String field3) {
        Row rowData = sheet.createRow(rowCount++);
        rowData.createCell(0).setCellValue(field1);
        rowData.createCell(1).setCellValue(field2);
        rowData.createCell(2).setCellValue(field3);
        return rowCount;
    }

    private static int createHeader(XSSFSheet sheet, int rowCount) {
        rowCount = generateRow(sheet, rowCount, "FIRST NAME", "LAST NAME", "BIRTHDAY");
        return rowCount;
    }
}
