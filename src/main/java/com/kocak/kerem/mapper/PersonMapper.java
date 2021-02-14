package com.kocak.kerem.mapper;

import com.kocak.kerem.domain.request.GetPersonRequestDTO;
import com.kocak.kerem.domain.request.PostPersonRequestDTO;
import com.kocak.kerem.domain.response.GetPersonDetailResponseDTO;
import com.kocak.kerem.domain.response.GetPersonResponseDTO;
import com.kocak.kerem.enums.PersonStatus;
import com.kocak.kerem.model.Person;
import com.kocak.kerem.repository.criteria.PersonCriteria;
import com.kocak.kerem.util.DateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {

    private PersonMapper() {
    }

    public static Person addPersonRequestDTOToPerson(PostPersonRequestDTO postPersonRequestDTO, String userName) {
        return Person.builder().age(postPersonRequestDTO.getAge()).createTime(DateUtils.getDateInstance()).createUser(userName).favouriteColour(postPersonRequestDTO.getFavouriteColour()).lastName(postPersonRequestDTO.getLastName()).firstName(postPersonRequestDTO.getFirstName()).personStatus(PersonStatus.ACTIVE).build();
    }

    public static PersonCriteria getPersonRequestDTOToPersonCriteria(GetPersonRequestDTO getPersonRequestDTO) {
        PersonCriteria personCriteria = new PersonCriteria();
        personCriteria.setAge(getPersonRequestDTO.getAge());
        personCriteria.setFirstName(getPersonRequestDTO.getFirstName());
        personCriteria.setLastName(getPersonRequestDTO.getLastName());
        personCriteria.setFavouriteColour(getPersonRequestDTO.getFavouriteColour());
        personCriteria.setId(getPersonRequestDTO.getId());
        return personCriteria;
    }

    public static GetPersonResponseDTO getPersonListToRetrievePersonResponseDTO(List<Person> personList) {
        List<GetPersonDetailResponseDTO> getPersonDetailResponseDTOS = personList.stream().map(person -> {
            return GetPersonDetailResponseDTO.builder().age(person.getAge()).favouriteColour(person.getFavouriteColour()).id(person.getId()).lastName(person.getLastName()).personStatus(person.getPersonStatus()).firstName(person.getFirstName()).build();
        }).collect(Collectors.toList());
        GetPersonResponseDTO getPersonResponseDTO = new GetPersonResponseDTO();
        getPersonResponseDTO.setPersons(getPersonDetailResponseDTOS);
        return getPersonResponseDTO;
    }
}
