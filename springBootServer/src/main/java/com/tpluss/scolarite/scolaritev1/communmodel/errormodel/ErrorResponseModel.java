package com.tpluss.scolarite.scolaritev1.communmodel.errormodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseModel {
    String errorMessage ;
    public  ErrorResponseModel(String message) {
         this.errorMessage= message ;
    }
}
