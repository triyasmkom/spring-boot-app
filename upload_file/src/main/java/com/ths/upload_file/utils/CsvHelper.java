package com.ths.upload_file.utils;

import com.ths.upload_file.entity.BookModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {

    private static final String TYPE = "text/csv";

    // cek format file csv
    public static boolean hasCsvFormat(MultipartFile file){
        if(!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    // konversi csv to list
    public static List<BookModel> csvToList(InputStream inputStream){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            // parser
            CSVParser parser = new CSVParser(bufferedReader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());

            List<BookModel> books =new ArrayList<>();
            Iterable<CSVRecord> csvRecords = parser.getRecords();

            for (CSVRecord csvRecord: csvRecords){
                BookModel book = new BookModel();
                book.setId(Long.parseLong(csvRecord.get("Id")));
                book.setTitle(csvRecord.get("Title"));
                book.setDescription(csvRecord.get("Description"));
                book.setPrice(Double.parseDouble(csvRecord.get("Price")));
                books.add(book);
            }

            parser.close();
            return books;

        } catch (IOException e){
            throw new RuntimeException("fail to parse CSV file: "+ e.getMessage());
        }
    }
}
