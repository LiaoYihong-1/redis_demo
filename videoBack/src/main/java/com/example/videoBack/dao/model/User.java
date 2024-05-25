package com.example.videoBack.dao.model;

import com.example.videoBack.dao.model.enums.PostgreSQLEnumType;
import com.example.videoBack.dao.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name="\"user\"")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,name="name")
    private String name;

    @Column(nullable = false,name="email")
    private String email;

    @Column(nullable = false,name="encrypted_password")
    private String encryptedPassword;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "role",referencedColumnName = "id")
    @JsonIgnore
    private Role role;

}
