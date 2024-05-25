package com.example.videoBack.dao.model;

import com.example.videoBack.dao.model.enums.PostgreSQLEnumType;
import com.example.videoBack.dao.model.enums.PrivilegeType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name="privilege")
@Data
@TypeDef(name="pgsql_enum",typeClass = PostgreSQLEnumType.class)
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,name="type")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private PrivilegeType privilegeType;
}
