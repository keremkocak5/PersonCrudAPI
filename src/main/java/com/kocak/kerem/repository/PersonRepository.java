package com.kocak.kerem.repository;

import com.kocak.kerem.enums.PersonStatus;
import com.kocak.kerem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface PersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {

    @Modifying
    @Query("update Person p set p.personStatus = :passiveStatus,  p.deleteUser = :deleteUser, p.deleteTime = :deleteTime  where p.id  = :id and p.personStatus = :activeStatus ")
    int deletePerson(@Param("passiveStatus") PersonStatus passiveStatus, @Param("deleteUser") String deleteUser, @Param("deleteTime") Date deleteTime, @Param("id") Integer id, @Param("activeStatus") PersonStatus activeStatus);

    @Modifying
    @Query("update Person p set p.age = :age, p.firstName = :firstName, p.lastName = :lastName, p.favouriteColour = :favouriteColour,  p.lastUpdateUser = :updateUser, p.lastUpdateTime = :updateTime  where p.id  = :id and p.personStatus = :activeStatus ")
    int updatePerson(@Param("age") int age, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("favouriteColour") String favouriteColour, @Param("updateUser") String updateUser, @Param("updateTime") Date deleteTime, @Param("id") int id, @Param("activeStatus") PersonStatus activeStatus);

}