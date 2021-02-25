package com.tpluss.scolarite.scolaritev1.communmodel.predictdata;

import lombok.*;

import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PredictDataModel {
    public   ArrayList<Double> predictedData ;
    public   String user;
}
