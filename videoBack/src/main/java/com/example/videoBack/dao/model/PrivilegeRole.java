package com.example.videoBack.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="privilege_role")
@Data
public class PrivilegeRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,name = "privilege")
    private Integer privilege;

    @Column(nullable = false,name = "role")
    private Integer role;

}
