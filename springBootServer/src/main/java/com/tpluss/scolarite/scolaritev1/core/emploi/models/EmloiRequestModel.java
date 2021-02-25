package com.tpluss.scolarite.scolaritev1.core.emploi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmloiRequestModel {
    int jourSemaine;

    String matinEntree;
    String matinSortie;
    String soirEntree;
    String soirSortie;
    Long classe;
}
