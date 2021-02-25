package com.tpluss.scolarite.scolaritev1.communmodel.errormodel;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WelcomeModel {
    String message ;

    public WelcomeModel(String message){
        this.message = message ;


    }

}
