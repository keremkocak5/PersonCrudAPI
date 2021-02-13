package com.kocak.kerem.repository.criteria;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PersonCriteria implements Serializable {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String favouriteColour;
}
