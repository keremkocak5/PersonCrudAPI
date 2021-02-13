package com.kocak.kerem.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostPersonRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    @NonNull
    @NotNull
    @Pattern(regexp = "[\\w]*")
    @Size(min = 3, max = 250)
    private String firstName;

    @JsonProperty
    @NonNull
    @NotNull
    @Pattern(regexp = "[\\w]*")
    @Size(min = 3, max = 250)
    private String lastName;

    @JsonProperty
    @NonNull
    @NotNull
    @Min(0)
    @Max(140)
    private Integer age;

    @JsonProperty
    @NonNull
    @NotNull
    @Pattern(regexp = "[\\w]*")
    @Size(min = 2, max = 250)
    private String favouriteColour;
}
