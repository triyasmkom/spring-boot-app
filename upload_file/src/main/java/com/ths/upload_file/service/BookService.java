package com.ths.upload_file.service;

import com.ths.upload_file.entity.BookModel;
import com.ths.upload_file.repository.BookRepo;
import com.ths.upload_file.utils.CsvHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service("bookService")
@Transactional
public class BookService {

    @Autowired private BookRepo bookRepo;

    public List<BookModel> save(MultipartFile file){
        try {
            List<BookModel> books = CsvHelper.csvToList(file.getInputStream());
            return bookRepo.saveAll(books);
        } catch (IOException e){
            throw new RuntimeException("fail to store csv data: "+e.getMessage());
        }
    }

    public List<BookModel> findAll(){
        return bookRepo.findAll();
    }
}
