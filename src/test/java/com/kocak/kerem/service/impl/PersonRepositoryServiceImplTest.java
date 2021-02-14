package com.kocak.kerem.service.impl;

import com.kocak.kerem.enums.PersonStatus;
import com.kocak.kerem.model.Person;
import com.kocak.kerem.repository.PersonRepository;
import com.kocak.kerem.repository.criteria.PersonCriteria;
import com.kocak.kerem.repository.spec.PersonSpec;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class PersonRepositoryServiceImplTest {

    private static final int AGE = 38;
    private static final String FAVOURITE_COLOUR = "Blue";
    private static final String FIRST_NAME = "Kerem";
    private static final String LAST_NAME = "Kocak";
    private final String USER_NAME = "userName";
    private final int ID = 10;

    @InjectMocks
    private PersonRepositoryServiceImpl personRepositoryService;
    @Mock
    private PersonRepository personRepository;

    @Test
    public void saveOrUpdatePerson_shouldSaveOnce() {
        Person person = new Person();

        personRepositoryService.saveOrUpdatePerson(person);

        verify(personRepository, times(1)).save(Mockito.any(Person.class));
    }

    @Test
    public void deletePerson_shouldReturnNumberOfAffectedRows() {
        Mockito.when(personRepository.deletePerson(Mockito.any(PersonStatus.class), Mockito.any(), Mockito.any(Date.class), Mockito.anyInt(), Mockito.any(PersonStatus.class))).thenReturn(1);

        int result = personRepositoryService.deletePerson(USER_NAME, ID);

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void updatePerson_shouldReturnNumberOfAffectedRows() {
        Mockito.when(personRepository.updatePerson(Mockito.anyInt(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(Date.class), Mockito.anyInt(), Mockito.any(PersonStatus.class))).thenReturn(1);

        int result = personRepositoryService.updatePerson(AGE, FIRST_NAME, LAST_NAME, FAVOURITE_COLOUR, USER_NAME, ID);

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void getPerson_shouldReturnPersonList() {
        PersonCriteria personCriteria = new PersonCriteria();
        personCriteria.setId(ID);
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId(ID);
        person.setAge(AGE);
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setFavouriteColour(FAVOURITE_COLOUR);
        personList.add(person);
        PersonSpec personSpec = new PersonSpec(personCriteria);
        Mockito.when(personRepository.findAll(Mockito.any(PersonSpec.class))).thenReturn(personList);

        List<Person> result = personRepositoryService.getPerson(personCriteria);

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getId()).isEqualTo(ID);
        assertThat(result.get(0).getAge()).isEqualTo(AGE);
        assertThat(result.get(0).getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(result.get(0).getLastName()).isEqualTo(LAST_NAME);
        assertThat(result.get(0).getFavouriteColour()).isEqualTo(FAVOURITE_COLOUR);
    }
}