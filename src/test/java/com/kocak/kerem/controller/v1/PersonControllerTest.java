package com.kocak.kerem.controller.v1;

import com.kocak.kerem.domain.request.DeletePersonRequestDTO;
import com.kocak.kerem.domain.request.GetPersonRequestDTO;
import com.kocak.kerem.domain.request.PostPersonRequestDTO;
import com.kocak.kerem.domain.request.PutPersonRequestDTO;
import com.kocak.kerem.domain.response.GetPersonDetailResponseDTO;
import com.kocak.kerem.domain.response.GetPersonResponseDTO;
import com.kocak.kerem.enums.PersonStatus;
import com.kocak.kerem.exception.NoPersonFoundException;
import com.kocak.kerem.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonControllerTest {

    private static final int AGE = 38;
    private static final int ID = 1;
    private static final String FAVOURITE_COLOUR = "Blue";
    private static final String FIRST_NAME = "Kerem";
    private static final String LAST_NAME = "Kocak";
    private static final PersonStatus PERSON_STATUS = PersonStatus.ACTIVE;
    private static final String USER = "user";
    private static final String ROLE = "Manager";
    private static final HttpStatus STATUS_OK = HttpStatus.OK;
    private static final HttpStatus STATUS_CONFLICT = HttpStatus.NOT_FOUND;

    @InjectMocks
    private PersonController personController;
    @Mock
    private PersonService personService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private Principal principal;

    @Test
    public void getPerson_shouldReturnBasePersonResponseDTO() {
        GetPersonRequestDTO getPersonRequestDTO = new GetPersonRequestDTO();
        GetPersonResponseDTO getPersonResponseDTO = new GetPersonResponseDTO();
        List<GetPersonDetailResponseDTO> getPersonDetailResponseDTOS = new ArrayList<>();
        GetPersonDetailResponseDTO getPersonDetailResponseDTO = GetPersonDetailResponseDTO.builder().firstName(FIRST_NAME).age(AGE).personStatus(PERSON_STATUS).favouriteColour(FAVOURITE_COLOUR).id(ID).lastName(LAST_NAME).build();
        getPersonDetailResponseDTOS.add(getPersonDetailResponseDTO);
        getPersonResponseDTO.setPersons(getPersonDetailResponseDTOS);

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.when(personService.getPerson(Mockito.any())).thenReturn(getPersonResponseDTO);

        ResponseEntity<GetPersonResponseDTO> response = personController.getPerson(getPersonRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getPersons()).isNotNull();
        assertThat(response.getBody().getPersons()).isNotEmpty();
        assertThat(response.getBody().getPersons().get(0).getAge()).isEqualTo(AGE);
        assertThat(response.getBody().getPersons().get(0).getFavouriteColour()).isEqualTo(FAVOURITE_COLOUR);
        assertThat(response.getBody().getPersons().get(0).getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(response.getBody().getPersons().get(0).getLastName()).isEqualTo(LAST_NAME);
        assertThat(response.getBody().getPersons().get(0).getId()).isEqualTo(ID);
        assertThat(response.getBody().getPersons().get(0).getPersonStatus()).isEqualTo(PERSON_STATUS);
    }

    @Test
    public void getPerson_shouldReturnNotFoundHTTPCode_whenServiceReturnsEmptyPersonList() {
        GetPersonRequestDTO getPersonRequestDTO = new GetPersonRequestDTO();

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.when(personService.getPerson(Mockito.any())).thenReturn(null);

        ResponseEntity<GetPersonResponseDTO> response = personController.getPerson(getPersonRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_CONFLICT);
    }

    @Test
    public void postPerson_shouldReturnBasePersonResponseDTO() {
        PostPersonRequestDTO postUserRequestDTO = new PostPersonRequestDTO();

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.doNothing().when(personService).addPerson(Mockito.any(), Mockito.any());

        ResponseEntity<String> response = personController.postPerson(postUserRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
    }

    @Test
    public void putPerson_shouldReturnBasePersonResponseDTO() {
        PutPersonRequestDTO putPersonRequestDTO = new PutPersonRequestDTO();

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.when(personService.putPerson(Mockito.any(), Mockito.any())).thenReturn(1);

        ResponseEntity<String> response = personController.putPerson(putPersonRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
    }

    @Test
    public void putPerson_shouldThrowException_whenZeroRowsUpdated() {
        PutPersonRequestDTO putPersonRequestDTO = new PutPersonRequestDTO();

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.when(personService.putPerson(Mockito.any(), Mockito.any())).thenReturn(0);

        Assertions.assertThrows(NoPersonFoundException.class, () ->
                personController.putPerson(putPersonRequestDTO, request)
        );
    }

    @Test
    public void deletePerson_shouldReturnBasePersonResponseDTO() {
        DeletePersonRequestDTO deletePersonRequestDTO = new DeletePersonRequestDTO();

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.when(personService.deletePerson(Mockito.any(), Mockito.any())).thenReturn(1);

        ResponseEntity<String> response = personController.deletePerson(deletePersonRequestDTO, request);
        assertThat(response.getStatusCode()).isEqualTo(STATUS_OK);
    }

    @Test
    public void deletePerson_shouldThrowException_whenZeroRowsUpdated() {
        DeletePersonRequestDTO deletePersonRequestDTO = new DeletePersonRequestDTO();

        Mockito.when(principal.getName()).thenReturn(USER);
        Mockito.when(request.getUserPrincipal()).thenReturn(principal);
        Mockito.when(request.isUserInRole(ROLE)).thenReturn(true);
        Mockito.when(personService.deletePerson(Mockito.any(), Mockito.any())).thenReturn(0);

        Assertions.assertThrows(NoPersonFoundException.class, () ->
                personController.deletePerson(deletePersonRequestDTO, request)
        );

    }
}