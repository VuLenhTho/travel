package com.vulenhtho.repository;

import com.vulenhtho.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password")
    User findUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    @Query("SELECT u.userName FROM User u WHERE u.id = :id")
    String findUserNameById(@Param("id") Long id);
}
