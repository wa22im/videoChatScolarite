package com.tpluss.scolarite.scolaritev1.core.home;

import com.tpluss.scolarite.scolaritev1.communmodel.errormodel.WelcomeModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class homeController {

    @GetMapping
    ResponseEntity Welcome (){
        System.out.println("bonjour");
        return new  ResponseEntity(new WelcomeModel("welcome") , HttpStatus.OK);
    }


}
