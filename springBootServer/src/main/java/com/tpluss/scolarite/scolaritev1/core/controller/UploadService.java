package com.tpluss.scolarite.scolaritev1.core.controller;

import com.tpluss.scolarite.scolaritev1.core.student.StudentEntity;
import com.tpluss.scolarite.scolaritev1.core.student.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class UploadService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    StudentRepo studentRepo ;

    private Path fileStorageLocation = Paths.get("C:\\Users\\EliteBook\\uploadefiles\\");

   public ResponseEntity<?> uploadeFile(MultipartFile file) throws IOException {
        System.out.println(
                "'getting file"
        );

        String  pictureLocationOnServer   = "C:\\Users\\EliteBook\\uploadefiles\\";
        File convetedFile = new File( pictureLocationOnServer+file.getOriginalFilename());

       convetedFile.createNewFile();

        FileOutputStream fout  = new FileOutputStream(convetedFile);

        fout.write(file.getBytes()) ;

        fout.close();

       // String namesTable  = pytest(pictureLocationOnServer+file.getOriginalFilename()) ;
       String fileName = file.getOriginalFilename();
       Long idfile = extractIdFromString(fileName) ;

       Optional<StudentEntity> studentEntityOptional = studentRepo.findById(idfile);
       StudentEntity studentEntity = studentEntityOptional.get();
       studentEntity.setPicsName(fileName);
       studentRepo.save(studentEntity);

       System.out.println(fileName);


       return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /*public String  uploadeFile(MultipartFile file ) throws IOException {
        System.out.println(
                "'getting file"
        );
        String fileName = file.getOriginalFilename();
        String  pictureLocationOnServer   = "C:\\Users\\EliteBook\\uploadefiles\\";
        File convetedFile = new File( pictureLocationOnServer+fileName);

        convetedFile.createNewFile();

        FileOutputStream fout  = new FileOutputStream(convetedFile);

        fout.write(file.getBytes()) ;

        fout.close();

        System.out.println();
        Long idfile = extractIdFromString(fileName) ;

        Optional<StudentEntity> studentEntityOptional = studentRepo.findById(idfile);
        StudentEntity studentEntity = studentEntityOptional.get();
        studentEntity.setPicsName(fileName);
        studentRepo.save(studentEntity);
        return fileName;
    }

*/

    public Resource loadFileAsResource(String picsName) throws MalformedURLException {


        Path filePath = this.fileStorageLocation.resolve(picsName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            return resource;

        }
        else
            return  null ;
    }


    Long extractIdFromString (String m) {
        Long id ;


       int index = m.indexOf(".") ;

       String mm = m.substring(0,index) ;
        return  Long.valueOf(mm) ;       }
    }


