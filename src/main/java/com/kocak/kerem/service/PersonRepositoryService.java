package com.kocak.kerem.service;

import com.kocak.kerem.model.Person;
import com.kocak.kerem.repository.criteria.PersonCriteria;

import java.util.List;

public interface PersonRepositoryService {

    void saveOrUpdatePerson(Person person);

    int deletePerson(String deleteUserName, int id);

    int updatePerson(int age, String firstName, String lastName, String favouriteColour, String deleteUserName, int id);

    List<Person> getPerson(PersonCriteria personCriteria);
}
