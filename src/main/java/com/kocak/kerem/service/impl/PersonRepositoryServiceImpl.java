package com.kocak.kerem.service.impl;

import com.kocak.kerem.enums.PersonStatus;
import com.kocak.kerem.model.Person;
import com.kocak.kerem.repository.PersonRepository;
import com.kocak.kerem.repository.criteria.PersonCriteria;
import com.kocak.kerem.repository.spec.PersonSpec;
import com.kocak.kerem.service.PersonRepositoryService;
import com.kocak.kerem.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class PersonRepositoryServiceImpl implements PersonRepositoryService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    @Override
    public void saveOrUpdatePerson(Person person) {
        personRepository.save(person);
    }

    @Transactional
    @Override
    public int deletePerson(String deleteUserName, int id) {
        return personRepository.deletePerson(PersonStatus.DELETED, deleteUserName, DateUtils.getDateInstance(), id, PersonStatus.ACTIVE);
    }

    @Transactional
    @Override
    public int updatePerson(int age, String firstName, String lastName, String favouriteColour, String updateUserName, int id) {
        return personRepository.updatePerson(age, firstName, lastName, favouriteColour, updateUserName, DateUtils.getDateInstance(), id, PersonStatus.ACTIVE);
    }

    @Override
    public List<Person> getPerson(PersonCriteria personCriteria) {
        PersonSpec personSpec = new PersonSpec(personCriteria);
        return personRepository.findAll(personSpec);
    }

}
