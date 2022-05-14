package com.laptrinhjava.project.repositories;

import com.laptrinhjava.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * from user where username =:username",nativeQuery = true)
    User findOneByUsername(@Param("username") String username);
    User findOneById(Long id);
}
