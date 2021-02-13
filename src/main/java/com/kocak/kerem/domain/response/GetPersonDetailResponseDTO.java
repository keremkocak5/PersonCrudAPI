package com.kocak.kerem.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kocak.kerem.enums.PersonStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class GetPersonDetailResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private int id;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private int age;

    @JsonProperty
    private String favouriteColour;

    @JsonProperty
    private PersonStatus personStatus;

}
