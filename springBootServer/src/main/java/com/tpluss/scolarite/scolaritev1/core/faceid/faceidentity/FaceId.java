package com.tpluss.scolarite.scolaritev1.core.faceid.faceidentity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpluss.scolarite.scolaritev1.core.classe.ClasseEntity;
import com.tpluss.scolarite.scolaritev1.core.student.StudentEntity;
import lombok.*;


import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.List;


@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FaceId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="studentId")
    StudentEntity studentId;


    private Double e0 ;
    private Double e1 ;
    private Double e2 ;
    private Double e3 ;
    private Double e4 ;
    private Double e5 ;
    private Double e6 ;
    private Double e7 ;
    private Double e8 ;
    private Double e9 ;
    private Double e10 ;
    private Double e11 ;
    private Double e12 ;
    private Double e13 ;
    private Double e14 ;
    private Double e15 ;
    private Double e16 ;
    private Double e17 ;
    private Double e18 ;
    private Double e19 ;
    private Double e20 ;
    private Double e21 ;
    private Double e22 ;
    private Double e23 ;
    private Double e24 ;
    private Double e25 ;
    private Double e26 ;
    private Double e27 ;
    private Double e28 ;
    private Double e29 ;
    private Double e30 ;
    private Double e31 ;
    private Double e32 ;
    private Double e33 ;
    private Double e34 ;
    private Double e35 ;
    private Double e36 ;
    private Double e37 ;
    private Double e38 ;
    private Double e39 ;
    private Double e40 ;
    private Double e41 ;
    private Double e42 ;
    private Double e43 ;
    private Double e44 ;
    private Double e45 ;
    private Double e46 ;
    private Double e47 ;
    private Double e48 ;
    private Double e49 ;
    private Double e50 ;
    private Double e51 ;
    private Double e52 ;
    private Double e53 ;
    private Double e54 ;
    private Double e55 ;
    private Double e56 ;
    private Double e57 ;
    private Double e58 ;
    private Double e59 ;
    private Double e60 ;
    private Double e61 ;
    private Double e62 ;
    private Double e63 ;
    private Double e64 ;
    private Double e65 ;
    private Double e66 ;
    private Double e67 ;
    private Double e68 ;
    private Double e69 ;
    private Double e70 ;
    private Double e71 ;
    private Double e72 ;
    private Double e73 ;
    private Double e74 ;
    private Double e75 ;
    private Double e76 ;
    private Double e77 ;
    private Double e78 ;
    private Double e79 ;
    private Double e80 ;
    private Double e81 ;
    private Double e82 ;
    private Double e83 ;
    private Double e84 ;
    private Double e85 ;
    private Double e86 ;
    private Double e87 ;
    private Double e88 ;
    private Double e89 ;
    private Double e90 ;
    private Double e91 ;
    private Double e92 ;
    private Double e93 ;
    private Double e94 ;
    private Double e95 ;
    private Double e96 ;
    private Double e97 ;
    private Double e98 ;
    private Double e99 ;
    private Double e100 ;
    private Double e101 ;
    private Double e102 ;
    private Double e103 ;
    private Double e104 ;
    private Double e105 ;
    private Double e106 ;
    private Double e107 ;
    private Double e108 ;
    private Double e109 ;
    private Double e110 ;
    private Double e111 ;
    private Double e112 ;
    private Double e113 ;
    private Double e114 ;
    private Double e115 ;
    private Double e116 ;
    private Double e117 ;
    private Double e118 ;
    private Double e119 ;
    private Double e120 ;
    private Double e121 ;
    private Double e122 ;
    private Double e123 ;
    private Double e124 ;
    private Double e125 ;
    private Double e126 ;
    private Double e127 ;
    private Double e128 ;
    private Double e129 ;
    private Double e130 ;
    private Double e131 ;
    private Double e132 ;
    private Double e133 ;
    private Double e134 ;
    private Double e135 ;
    private Double e136 ;
    private Double e137 ;
    private Double e138 ;
    private Double e139 ;
    private Double e140 ;
    private Double e141 ;
    private Double e142 ;
    private Double e143 ;
    private Double e144 ;
    private Double e145 ;
    private Double e146 ;
    private Double e147 ;
    private Double e148 ;
    private Double e149 ;
    private Double e150 ;
    private Double e151 ;
    private Double e152 ;
    private Double e153 ;
    private Double e154 ;
    private Double e155 ;
    private Double e156 ;
    private Double e157 ;
    private Double e158 ;
    private Double e159 ;
    private Double e160 ;
    private Double e161 ;
    private Double e162 ;
    private Double e163 ;
    private Double e164 ;
    private Double e165 ;
    private Double e166 ;
    private Double e167 ;
    private Double e168 ;
    private Double e169 ;
    private Double e170 ;
    private Double e171 ;
    private Double e172 ;
    private Double e173 ;
    private Double e174 ;
    private Double e175 ;
    private Double e176 ;
    private Double e177 ;
    private Double e178 ;
    private Double e179 ;
    private Double e180 ;
    private Double e181 ;
    private Double e182 ;
    private Double e183 ;
    private Double e184 ;
    private Double e185 ;
    private Double e186 ;
    private Double e187 ;
    private Double e188 ;
    private Double e189 ;
    private Double e190 ;
    private Double e191 ;



}
