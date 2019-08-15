package com.vulenhtho.repository;

import com.vulenhtho.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password")
    User findUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    @Query("SELECT u.userName as userName, u.fullName as fullName, u.createdDate as createdDate FROM User u WHERE u.id = :id")
    CustomObjectResponse findUserNameById(@Param("id") Long id);

    @Query("SELECT u FROM User u WHERE (:fromDate IS NULL OR u.createdDate >:fromDate)" +
            "AND (:toDate IS NULL OR u.createdDate <:toDate)" +
            "AND (:fullName IS NULL OR u.fullName=:fullName)")
    List<CustomObjectResponse> findAllByProperties(@Param("fromDate") Date fromDate,@Param("toDate") Date toDate,@Param("fullName") String fullName);

    User findUserByUserName(String userName);

    interface CustomObjectResponse {
        String getUserName();
        String getFullName();
        Date getCreatedDate();
    }
}

