package com.tpluss.scolarite.scolaritev1.core.student;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpluss.scolarite.scolaritev1.core.classe.ClasseEntity;
import com.tpluss.scolarite.scolaritev1.core.faceid.faceidentity.FaceId;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;

    private String nom;



    @OneToMany(fetch = FetchType.LAZY   , mappedBy = "studentId")
    @JsonIgnore
    private List<FaceId> faceIds;



    @JsonIgnore
    String picsName ;






}
