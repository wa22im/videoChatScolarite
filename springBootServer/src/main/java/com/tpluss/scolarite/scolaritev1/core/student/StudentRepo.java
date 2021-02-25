package com.tpluss.scolarite.scolaritev1.core.student;

import com.tpluss.scolarite.scolaritev1.core.faceid.faceidentity.FaceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity , Long> {


}
