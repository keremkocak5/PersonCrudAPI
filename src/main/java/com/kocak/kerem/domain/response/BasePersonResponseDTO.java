package com.kocak.kerem.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.io.Serializable;

@Setter
public class BasePersonResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String message;

}
