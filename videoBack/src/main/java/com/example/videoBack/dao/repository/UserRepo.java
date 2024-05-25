package com.example.videoBack.dao.repository;

import com.example.videoBack.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    @Query(value = "select * from \"user\" where email=:email limit 1",
            nativeQuery = true
    )
    Optional<User> findUserByEmail(@Param("email") String email);
}
