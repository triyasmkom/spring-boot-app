package com.ths.upload_file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    private static String UPLOADED_PATH = "C:\\Users\\triyas\\Downloads\\upload\\";

    @GetMapping("/")
    private String index(){
        return "index"; // ke halaman index.html
    }

    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes // flash atribut (object yang dikirimkan ke front end)
    ){
        // cek file
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:status"; // redirect ke halaman status (mencari endpoint /status)
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_PATH+file.getOriginalFilename());
            Files.write(path,bytes);

            // berikan pesan ke front end
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '"+file.getOriginalFilename()+"'");
        } catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:status";
    }

    @GetMapping("/status")
    public String uploadStatus(){
        return "status";
    }
}
