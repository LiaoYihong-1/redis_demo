package com.example.videoBack.dao.repository;

import com.example.videoBack.dao.model.Privilege;
import com.example.videoBack.dao.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrivilegeRepo extends JpaRepository<Privilege, Integer>, JpaSpecificationExecutor<Privilege> {

}
