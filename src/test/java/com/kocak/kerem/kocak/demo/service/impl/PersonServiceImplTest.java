package com.kocak.kerem.kocak.demo.service.impl;

import com.kocak.kerem.domain.request.DeletePersonRequestDTO;
import com.kocak.kerem.domain.request.GetPersonRequestDTO;
import com.kocak.kerem.domain.request.PostPersonRequestDTO;
import com.kocak.kerem.domain.request.PutPersonRequestDTO;
import com.kocak.kerem.domain.response.GetPersonResponseDTO;
import com.kocak.kerem.mapper.PersonMapper;
import com.kocak.kerem.model.Person;
import com.kocak.kerem.repository.criteria.PersonCriteria;
import com.kocak.kerem.service.PersonRepositoryService;
import com.kocak.kerem.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class PersonServiceImplTest {

    private static final int AGE = 38;
    private static final String FAVOURITE_COLOUR = "Blue";
    private static final String FIRST_NAME = "Kerem";
    private static final String LAST_NAME = "Kocak";
    private final String USER_NAME = "userName";
    private final int ID = 10;

    @InjectMocks
    private PersonServiceImpl personServiceImpl;
    @Mock
    private PersonRepositoryService personRepositoryService;

    @Test
    public void addPerson_shouldSaveOnce() {
        PostPersonRequestDTO postPersonRequestDTO = new PostPersonRequestDTO();
        postPersonRequestDTO.setAge(AGE);
        postPersonRequestDTO.setFavouriteColour(FAVOURITE_COLOUR);
        postPersonRequestDTO.setFirstName(FIRST_NAME);
        postPersonRequestDTO.setLastName(LAST_NAME);
        personServiceImpl.addPerson(postPersonRequestDTO, USER_NAME);
        verify(personRepositoryService, times(1)).saveOrUpdatePerson(Mockito.any(Person.class));
    }


    @Test
    public void putPerson_shouldReturnNumberOfRowsAffected() {
        PutPersonRequestDTO putPersonRequestDTO = new PutPersonRequestDTO();
        putPersonRequestDTO.setAge(AGE);
        putPersonRequestDTO.setFavouriteColour(FAVOURITE_COLOUR);
        putPersonRequestDTO.setFirstName(FIRST_NAME);
        putPersonRequestDTO.setLastName(LAST_NAME);
        putPersonRequestDTO.setId(ID);
        Mockito.when(personRepositoryService.updatePerson(AGE, FIRST_NAME, LAST_NAME, FAVOURITE_COLOUR, USER_NAME, ID)).thenReturn(1);
        int result = personServiceImpl.putPerson(putPersonRequestDTO, USER_NAME);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void deletePerson_shouldReturnNumberOfRowsAffected() {
        DeletePersonRequestDTO deletePersonRequestDTO = new DeletePersonRequestDTO();
        deletePersonRequestDTO.setId(ID);
        Mockito.when(personRepositoryService.deletePerson(USER_NAME, ID)).thenReturn(1);
        int result = personServiceImpl.deletePerson(deletePersonRequestDTO, USER_NAME);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void getPerson_shouldReturnPerson() {
        GetPersonRequestDTO getPersonRequestDTO = new GetPersonRequestDTO();
        getPersonRequestDTO.setAge(AGE);
        getPersonRequestDTO.setFavouriteColour(FAVOURITE_COLOUR);
        getPersonRequestDTO.setFirstName(FIRST_NAME);
        getPersonRequestDTO.setLastName(LAST_NAME);
        getPersonRequestDTO.setId(ID);
        PersonCriteria personCriteria = PersonMapper.getPersonRequestDTOToPersonCriteria(getPersonRequestDTO);
        Person person = new Person();
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        Mockito.when(personRepositoryService.getPerson(personCriteria)).thenReturn(personList);
        GetPersonResponseDTO getPersonResponseDTO = personServiceImpl.getPerson(getPersonRequestDTO);
        assertThat(getPersonResponseDTO).isNotNull();
    }
}
