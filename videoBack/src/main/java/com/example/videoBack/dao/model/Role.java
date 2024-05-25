package com.example.videoBack.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import com.example.videoBack.dao.model.Privilege;
@Entity
@Table(name="role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,name="name")
    private String name;

    //会创建一个man_hobby的维护表，关联man和hobby的id关系
    @ManyToMany
    @JoinTable(name = "privilege_role",
            joinColumns = {@JoinColumn(name = "role", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "privilege", referencedColumnName ="id")})
    private List<Privilege> privileges;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "id",referencedColumnName = "role")
    @JsonIgnore
    private User role;
}
