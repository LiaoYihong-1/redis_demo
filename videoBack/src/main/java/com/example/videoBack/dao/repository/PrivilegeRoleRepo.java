package com.example.videoBack.dao.repository;

import com.example.videoBack.dao.model.PrivilegeRole;
import com.example.videoBack.dao.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrivilegeRoleRepo extends JpaRepository<PrivilegeRole, Integer>, JpaSpecificationExecutor<PrivilegeRole> {
}
