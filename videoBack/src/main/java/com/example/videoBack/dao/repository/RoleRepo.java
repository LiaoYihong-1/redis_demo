package com.example.videoBack.dao.repository;

import com.example.videoBack.dao.model.Role;
import com.example.videoBack.dao.model.User;
import com.example.videoBack.dao.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {
    @Query(value = "select * from role where name=:name limit 1",
            nativeQuery = true
    )
    Optional<Role> findRoleByName(@Param("name") String name);
}
