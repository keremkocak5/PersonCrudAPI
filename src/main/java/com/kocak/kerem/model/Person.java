package com.kocak.kerem.model;

import com.kocak.kerem.enums.PersonStatus;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PERSON")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    private String firstName;

    private String lastName;

    private int age;

    private String favouriteColour;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private PersonStatus personStatus;

    @NotNull
    @Type(type = "timestamp")
    private Date createTime;

    @Type(type = "timestamp")
    private Date lastUpdateTime;

    @Type(type = "timestamp")
    private Date deleteTime;

    @NotNull
    private String createUser;

    private String lastUpdateUser;

    private String deleteUser;
}
