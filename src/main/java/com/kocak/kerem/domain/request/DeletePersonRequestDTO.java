package com.kocak.kerem.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeletePersonRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    @NotNull
    private Integer id;

}
