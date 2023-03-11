package com.optum.portal.api.repository;

import com.optum.portal.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("Select u from User u WHERE u.username=:username and u.password=:password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("Select u from User u WHERE u.role=:role")
    List<User> findUsers(@Param("role") String role);
}