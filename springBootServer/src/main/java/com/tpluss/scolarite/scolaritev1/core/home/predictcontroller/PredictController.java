package com.tpluss.scolarite.scolaritev1.core.home.predictcontroller;

import com.tpluss.scolarite.scolaritev1.communmodel.predictdata.Getpredictdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;


@RestController
@RequestMapping("/predict")
public class PredictController {


    @Autowired
    PredictService predictService ;

    @PostMapping("/getid")
    ResponseEntity<?> predictedUser (@RequestBody Getpredictdata getpredictdata) throws MalformedURLException {
        return  predictService.predictedUser(getpredictdata) ;
    };
}
