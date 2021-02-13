package com.kocak.kerem.service.impl;

import com.kocak.kerem.domain.request.DeletePersonRequestDTO;
import com.kocak.kerem.domain.request.GetPersonRequestDTO;
import com.kocak.kerem.domain.request.PostPersonRequestDTO;
import com.kocak.kerem.domain.request.PutPersonRequestDTO;
import com.kocak.kerem.domain.response.GetPersonResponseDTO;
import com.kocak.kerem.mapper.PersonMapper;
import com.kocak.kerem.model.Person;
import com.kocak.kerem.repository.criteria.PersonCriteria;
import com.kocak.kerem.service.PersonRepositoryService;
import com.kocak.kerem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepositoryService personRepositoryService;

    @Override
    public void addPerson(PostPersonRequestDTO postPersonRequestDTO, String userName) {
        Person person = PersonMapper.addPersonRequestDTOToPerson(postPersonRequestDTO, userName);
        personRepositoryService.saveOrUpdatePerson(person);
    }

    @Override
    public int deletePerson(DeletePersonRequestDTO deletePersonRequestDTO, String userName) {
        return personRepositoryService.deletePerson(userName, deletePersonRequestDTO.getId());
    }

    @Override
    public int putPerson(PutPersonRequestDTO putPersonRequestDTO, String userName) {
        return personRepositoryService.updatePerson(putPersonRequestDTO.getAge(), putPersonRequestDTO.getFirstName(), putPersonRequestDTO.getLastName(), putPersonRequestDTO.getFavouriteColour(), userName, putPersonRequestDTO.getId());
    }

    @Override
    public GetPersonResponseDTO getPerson(GetPersonRequestDTO getPersonRequestDTO) {
        PersonCriteria personCriteria = PersonMapper.getPersonRequestDTOToPersonCriteria(getPersonRequestDTO);
        List<Person> persons = personRepositoryService.getPerson(personCriteria);
        return PersonMapper.getPersonListToRetrievePersonResponseDTO(persons);
    }

}