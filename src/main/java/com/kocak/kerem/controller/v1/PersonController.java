package com.kocak.kerem.controller.v1;

import com.kocak.kerem.domain.request.DeletePersonRequestDTO;
import com.kocak.kerem.domain.request.GetPersonRequestDTO;
import com.kocak.kerem.domain.request.PostPersonRequestDTO;
import com.kocak.kerem.domain.request.PutPersonRequestDTO;
import com.kocak.kerem.domain.response.GetPersonResponseDTO;
import com.kocak.kerem.exception.NoPersonFoundException;
import com.kocak.kerem.service.PersonService;
import com.kocak.kerem.util.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

/**
 * CRUD API for Person objects.
 *
 * @author: Kerem Kocak
 **/
@RestController
@RequestMapping(ApiConstants.PERSON_ENDPOINT)
@Api(value = "CRUD API for Person objects.")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "Creates a Person entity in the storage", notes = "All fields are mandatory", response = ResponseEntity.class)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Validated
    public ResponseEntity<String> postPerson(@Valid @RequestBody PostPersonRequestDTO postPersonRequestDTO, HttpServletRequest request) {
        personService.addPerson(postPersonRequestDTO, request.getUserPrincipal().getName());
        return ResponseEntity.ok("Ok");
    }

    @ApiOperation(value = "Retrieves Person entities from the storage", notes = "All fields optional", response = GetPersonResponseDTO.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Validated
    public ResponseEntity<GetPersonResponseDTO> getPerson(@Valid @RequestBody GetPersonRequestDTO getPersonRequestDTO, HttpServletRequest request) {
        GetPersonResponseDTO getPersonResponseDTO = personService.getPerson(getPersonRequestDTO);
        if (Objects.isNull(getPersonResponseDTO))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(getPersonResponseDTO);
    }

    @ApiOperation(value = "Deletes a Person entity from the storage", notes = "ID Number of the record should be supplied as a parameter", response = ResponseEntity.class)
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Validated
    public ResponseEntity<String> deletePerson(@Valid @RequestBody DeletePersonRequestDTO deletePersonRequestDTO, HttpServletRequest request) {
        int affectedRows = personService.deletePerson(deletePersonRequestDTO, request.getUserPrincipal().getName());
        if (affectedRows < 1) {
            throw new NoPersonFoundException();
        }
        return ResponseEntity.ok("Ok");
    }

    @ApiOperation(value = "Updates a Person entity in the storage", notes = "ID Number of the record to be updates be supplied as a parameter", response = ResponseEntity.class)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Validated
    public ResponseEntity<String> putPerson(@Valid @RequestBody PutPersonRequestDTO putPersonRequestDTO, HttpServletRequest request) {
        int affectedRows = personService.putPerson(putPersonRequestDTO, request.getUserPrincipal().getName());
        if (affectedRows < 1) {
            throw new NoPersonFoundException();
        }
        return ResponseEntity.ok("Ok");
    }

}