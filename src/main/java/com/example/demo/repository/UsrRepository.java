package com.example.demo.repository;

import com.example.demo.entity.Usr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UsrRepository extends JpaRepository<Usr, String> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE usr SET email = :newEmail WHERE username = :username", nativeQuery = true)
    void updateUserEmail(@Param("username") String username, @Param("newEmail") String newEmail);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usr WHERE username = :username", nativeQuery = true)
    void deleteUserByUsername(@Param("username") String username);
}
