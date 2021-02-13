package com.kocak.kerem.kocak.demo.mapper;

import com.kocak.kerem.domain.request.GetPersonRequestDTO;
import com.kocak.kerem.domain.request.PostPersonRequestDTO;
import com.kocak.kerem.domain.response.GetPersonResponseDTO;
import com.kocak.kerem.enums.PersonStatus;
import com.kocak.kerem.mapper.PersonMapper;
import com.kocak.kerem.model.Person;
import com.kocak.kerem.repository.criteria.PersonCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonMapperTest {

    private static final int AGE = 38;
    private static final String FAVOURITE_COLOUR = "Blue";
    private static final String FIRST_NAME = "Kerem";
    private static final String LAST_NAME = "Kocak";
    private final String USER_NAME = "userName";
    private final PersonStatus PERSON_STATUS = PersonStatus.ACTIVE;
    private final int ID = 10;

    @Test
    public void addPersonRequestDTOToPerson_shouldReturnPerson() {
        PostPersonRequestDTO postPersonRequestDTO = new PostPersonRequestDTO();
        postPersonRequestDTO.setAge(AGE);
        postPersonRequestDTO.setFirstName(FIRST_NAME);
        postPersonRequestDTO.setLastName(LAST_NAME);
        postPersonRequestDTO.setFavouriteColour(FAVOURITE_COLOUR);
        Person person = PersonMapper.addPersonRequestDTOToPerson(postPersonRequestDTO, USER_NAME);

        assertThat(person).isNotNull();
        assertThat(person.getFavouriteColour()).isEqualTo(FAVOURITE_COLOUR);
        assertThat(person.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(person.getLastName()).isEqualTo(LAST_NAME);
        assertThat(person.getAge()).isEqualTo(AGE);
    }

    @Test
    public void getPersonRequestDTOToPersonCriteria_shouldReturnPersonCriteria() {
        GetPersonRequestDTO getPersonRequestDTO = new GetPersonRequestDTO();
        getPersonRequestDTO.setAge(AGE);
        getPersonRequestDTO.setFirstName(FIRST_NAME);
        getPersonRequestDTO.setLastName(LAST_NAME);
        getPersonRequestDTO.setFavouriteColour(FAVOURITE_COLOUR);
        getPersonRequestDTO.setId(ID);
        PersonCriteria personCriteria = PersonMapper.getPersonRequestDTOToPersonCriteria(getPersonRequestDTO);

        assertThat(personCriteria).isNotNull();
        assertThat(personCriteria.getFavouriteColour()).isEqualTo(FAVOURITE_COLOUR);
        assertThat(personCriteria.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(personCriteria.getLastName()).isEqualTo(LAST_NAME);
        assertThat(personCriteria.getAge()).isEqualTo(AGE);
        assertThat(personCriteria.getId()).isEqualTo(ID);
    }

    @Test
    public void getPersonListToRetrievePersonResponseDTO_shouldReturnPersonCriteria() {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setAge(AGE);
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setFavouriteColour(FAVOURITE_COLOUR);
        person.setPersonStatus(PERSON_STATUS);
        person.setId(ID);
        personList.add(person);
        GetPersonResponseDTO getPersonResponseDTO = PersonMapper.getPersonListToRetrievePersonResponseDTO(personList);

        assertThat(getPersonResponseDTO).isNotNull();
        assertThat(getPersonResponseDTO.getPersons()).isNotNull();
        assertThat(getPersonResponseDTO.getPersons()).isNotEmpty();
        assertThat(getPersonResponseDTO.getPersons().get(0).getPersonStatus()).isEqualTo(PERSON_STATUS);
        assertThat(getPersonResponseDTO.getPersons().get(0).getAge()).isEqualTo(AGE);
        assertThat(getPersonResponseDTO.getPersons().get(0).getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(getPersonResponseDTO.getPersons().get(0).getLastName()).isEqualTo(LAST_NAME);
        assertThat(getPersonResponseDTO.getPersons().get(0).getFavouriteColour()).isEqualTo(FAVOURITE_COLOUR);
    }
}
