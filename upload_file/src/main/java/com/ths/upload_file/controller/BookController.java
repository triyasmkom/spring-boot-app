package com.ths.upload_file.controller;

import com.ths.upload_file.dto.ResponseData;
import com.ths.upload_file.entity.BookModel;
import com.ths.upload_file.service.BookService;
import com.ths.upload_file.utils.CsvHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired private BookService bookService;

    @GetMapping
    public ResponseEntity<?> findAllBook(){
        ResponseData responseData = new ResponseData();
        try {
            List<BookModel> books = bookService.findAll();
            responseData.setStatus(true);
            responseData.getMessage().add("Get All Books");
            responseData.setPayload(books);
            return ResponseEntity.ok(responseData);
        }catch (Exception e){
            responseData.setStatus(false);
            responseData.getMessage().add("Could not get book "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @PostMapping("/upload")
    private ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        ResponseData responseData = new ResponseData();

        if (!CsvHelper.hasCsvFormat(file)){
            responseData.setStatus(false);
            responseData.getMessage().add("Please csv upload csv file ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        try{
            List<BookModel> books = bookService.save(file);
            responseData.getMessage().add("Uploaded the file succcesfully: "+file.getOriginalFilename());
            responseData.setStatus(false);
            responseData.setPayload(books);
            return ResponseEntity.ok(responseData);

        }catch (Exception e){
            responseData.setStatus(false);
            responseData.getMessage().add("Could not upload the file "+ file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseData);
        }
    }
}
