package com.tpluss.scolarite.scolaritev1.core.classe;



import com.tpluss.scolarite.scolaritev1.core.classe.models.GetAllClasses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classe")
public class ClasseController {

    @Autowired
    ClasseRepo classeRepo;

    @PostMapping
    ResponseEntity postClasse(@RequestBody ClasseEntity newClasse){

        classeRepo. save(newClasse);
        return null;
    }

    @GetMapping
    ResponseEntity<?> getAllClasss (  ) {

        GetAllClasses getAllClasses = new GetAllClasses() ;
        getAllClasses.setClasseEntities(classeRepo.findAll() );


        return   new ResponseEntity<>( getAllClasses , HttpStatus.OK) ;
    }
}
