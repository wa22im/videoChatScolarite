package com.tpluss.scolarite.scolaritev1.core.controller;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping( "/image")
public class Uploadcontroller {

    @Autowired
    UploadService uploadService;

    @GetMapping(
            value = "/{picture}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )


    public Resource loadFileAsResource(@PathVariable String picture ) throws MalformedURLException {


        return  uploadService.loadFileAsResource(picture) ;


    }




 @PostMapping
 public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) throws IOException {

        return uploadService.uploadeFile(file  );
    }
   /* @GetMapping
    public  ResponseEntity<?> getStudentImage ( )
    {
        return  uploadService.getStudentImage() ;
    }*/



}
