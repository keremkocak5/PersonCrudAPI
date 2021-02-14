package com.kocak.kerem.service.impl;

import com.kocak.kerem.enums.PersonStatus;
import com.kocak.kerem.model.Person;
import com.kocak.kerem.repository.criteria.PersonCriteria;
import com.kocak.kerem.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "test")
public class PersonRepositoryServiceImplIntegrationTest {

    private static final int AGE = 20;
    private static final String FAVOURITE_COLOUR = "Blue";
    private static final String FIRST_NAME = "Ali";
    private static final String LAST_NAME = "Veli";
    private static final int AGE2 = 30;
    private static final String FAVOURITE_COLOUR2 = "Green";
    private static final String FIRST_NAME2 = "Cemil";
    private static final String LAST_NAME2 = "Sinan";
    private final String USER_NAME = "userName";

    @Autowired
    private PersonRepositoryServiceImpl personRepositoryService;

    @Test
    public void h2IntegrationTest() {
        Person person = new Person();
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setAge(AGE);
        person.setFavouriteColour(FAVOURITE_COLOUR);
        person.setCreateTime(DateUtils.getDateInstance());
        person.setCreateUser(USER_NAME);
        person.setPersonStatus(PersonStatus.ACTIVE);

        Person resultPerson = personRepositoryService.saveOrUpdatePerson(person);

        assertThat(resultPerson).isNotNull();
        assertThat(resultPerson.getId()).isGreaterThan(0);
        assertThat(resultPerson.getAge()).isEqualTo(AGE);
        assertThat(resultPerson.getLastName()).isEqualTo(LAST_NAME);
        assertThat(resultPerson.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(resultPerson.getFavouriteColour()).isEqualTo(FAVOURITE_COLOUR);
        assertThat(resultPerson.getCreateTime()).isNotNull();
        assertThat(resultPerson.getCreateUser()).isEqualTo(USER_NAME);
        assertThat(resultPerson.getDeleteTime()).isNull();
        assertThat(resultPerson.getDeleteUser()).isNull();
        assertThat(resultPerson.getLastUpdateTime()).isNull();
        assertThat(resultPerson.getLastUpdateUser()).isNull();
        assertThat(resultPerson.getPersonStatus()).isEqualTo(PersonStatus.ACTIVE);

        PersonCriteria personCriteria = new PersonCriteria();
        personCriteria.setId(resultPerson.getId());
        personCriteria.setAge(AGE);
        personCriteria.setFirstName(FIRST_NAME);
        personCriteria.setLastName(LAST_NAME);
        personCriteria.setFavouriteColour(FAVOURITE_COLOUR);

        List<Person> resultPersons = personRepositoryService.getPerson(personCriteria);
        assertThat(resultPersons).isNotNull();
        assertThat(resultPersons).isNotEmpty();
        assertThat(resultPersons.get(0).getId()).isEqualTo(resultPerson.getId());
        assertThat(resultPersons.get(0).getAge()).isEqualTo(AGE);
        assertThat(resultPersons.get(0).getLastName()).isEqualTo(LAST_NAME);
        assertThat(resultPersons.get(0).getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(resultPersons.get(0).getFavouriteColour()).isEqualTo(FAVOURITE_COLOUR);
        assertThat(resultPersons.get(0).getCreateTime()).isNotNull();
        assertThat(resultPersons.get(0).getCreateUser()).isEqualTo(USER_NAME);
        assertThat(resultPersons.get(0).getDeleteTime()).isNull();
        assertThat(resultPersons.get(0).getDeleteUser()).isNull();
        assertThat(resultPersons.get(0).getLastUpdateTime()).isNull();
        assertThat(resultPersons.get(0).getLastUpdateUser()).isNull();
        assertThat(resultPersons.get(0).getPersonStatus()).isEqualTo(PersonStatus.ACTIVE);

        int affectedRows = personRepositoryService.updatePerson(AGE2, FIRST_NAME2, LAST_NAME2, FAVOURITE_COLOUR2, USER_NAME, resultPerson.getId());
        assertThat(affectedRows).isEqualTo(1);

        personCriteria = new PersonCriteria();
        personCriteria.setId(resultPerson.getId());

        resultPersons = personRepositoryService.getPerson(personCriteria);
        assertThat(resultPersons).isNotNull();
        assertThat(resultPersons).isNotEmpty();
        assertThat(resultPersons.get(0).getId()).isEqualTo(resultPerson.getId());
        assertThat(resultPersons.get(0).getAge()).isEqualTo(AGE2);
        assertThat(resultPersons.get(0).getLastName()).isEqualTo(LAST_NAME2);
        assertThat(resultPersons.get(0).getFirstName()).isEqualTo(FIRST_NAME2);
        assertThat(resultPersons.get(0).getFavouriteColour()).isEqualTo(FAVOURITE_COLOUR2);
        assertThat(resultPersons.get(0).getCreateTime()).isNotNull();
        assertThat(resultPersons.get(0).getCreateUser()).isEqualTo(USER_NAME);
        assertThat(resultPersons.get(0).getDeleteTime()).isNull();
        assertThat(resultPersons.get(0).getDeleteUser()).isNull();
        assertThat(resultPersons.get(0).getLastUpdateUser()).isEqualTo(USER_NAME);
        assertThat(resultPersons.get(0).getLastUpdateTime()).isNotNull();
        assertThat(resultPersons.get(0).getPersonStatus()).isEqualTo(PersonStatus.ACTIVE);

        affectedRows = personRepositoryService.deletePerson(USER_NAME, resultPerson.getId());
        assertThat(affectedRows).isEqualTo(1);

        resultPersons = personRepositoryService.getPerson(personCriteria);
        assertThat(resultPersons).isNotNull();
        assertThat(resultPersons).isNotEmpty();
        assertThat(resultPersons.get(0).getId()).isEqualTo(resultPerson.getId());
        assertThat(resultPersons.get(0).getAge()).isEqualTo(AGE2);
        assertThat(resultPersons.get(0).getLastName()).isEqualTo(LAST_NAME2);
        assertThat(resultPersons.get(0).getFirstName()).isEqualTo(FIRST_NAME2);
        assertThat(resultPersons.get(0).getFavouriteColour()).isEqualTo(FAVOURITE_COLOUR2);
        assertThat(resultPersons.get(0).getCreateTime()).isNotNull();
        assertThat(resultPersons.get(0).getCreateUser()).isEqualTo(USER_NAME);
        assertThat(resultPersons.get(0).getDeleteTime()).isNotNull();
        assertThat(resultPersons.get(0).getDeleteUser()).isEqualTo(USER_NAME);
        assertThat(resultPersons.get(0).getLastUpdateUser()).isEqualTo(USER_NAME);
        assertThat(resultPersons.get(0).getLastUpdateTime()).isNotNull();
        assertThat(resultPersons.get(0).getPersonStatus()).isEqualTo(PersonStatus.DELETED);
    }

}