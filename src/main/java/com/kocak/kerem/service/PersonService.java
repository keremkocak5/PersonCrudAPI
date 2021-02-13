package com.kocak.kerem.service;

import com.kocak.kerem.domain.request.DeletePersonRequestDTO;
import com.kocak.kerem.domain.request.GetPersonRequestDTO;
import com.kocak.kerem.domain.request.PostPersonRequestDTO;
import com.kocak.kerem.domain.request.PutPersonRequestDTO;
import com.kocak.kerem.domain.response.GetPersonResponseDTO;

public interface PersonService {

    void addPerson(PostPersonRequestDTO postPersonRequestDTO, String userName);

    int deletePerson(DeletePersonRequestDTO deletePersonRequestDTO, String userName);

    int putPerson(PutPersonRequestDTO putPersonRequestDTO, String userName);

    GetPersonResponseDTO getPerson(GetPersonRequestDTO getPersonRequestDTO);


}
