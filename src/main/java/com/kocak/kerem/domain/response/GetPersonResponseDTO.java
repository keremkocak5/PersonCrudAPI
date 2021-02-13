package com.kocak.kerem.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class GetPersonResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private List<GetPersonDetailResponseDTO> persons;

}
