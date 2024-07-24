package com.sekolah.app.repository;

import com.sekolah.app.dto.SubjectDto;
import com.sekolah.app.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s FROM Subject s WHERE (:subjectName IS NULL OR s.subjectName LIKE %:subjectName%) " +
            "AND (:major IS NULL OR s.major = :major) " +
            "AND (:grade IS NULL OR s.grade = :grade)")
    List<Subject> findByFilters(@Param("subjectName") String subjectName,
                                @Param("major") String major,
                                @Param("grade") String grade);
}
