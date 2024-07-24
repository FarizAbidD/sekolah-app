package com.sekolah.app.repository;

import com.sekolah.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE (:name IS NULL OR u.name ilike %:name%) AND (:email IS NULL OR u.email = :email) AND (:major IS NULL OR u.major = :major) AND (:grade IS NULL OR u.grade = :grade)")
    List<User> findByFilters(@Param("name") String name, @Param("email") String email, @Param("major") String major, @Param("grade") String grade);
    User findByEmailAndPassword(String email, String password);
}
