package com.kocak.kerem.repository.spec;

import com.kocak.kerem.model.Person;
import com.kocak.kerem.repository.criteria.PersonCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonSpec implements Specification<Person> {

    private final PersonCriteria personCriteria;

    public PersonSpec(PersonCriteria personCriteria) {
        this.personCriteria = personCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();

        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.asc(root.get("id")));
        criteriaQuery.orderBy(orderList);

        if (Objects.nonNull(personCriteria.getId())) {
            predicateList.add(criteriaBuilder.equal(root.get("id"), personCriteria.getId()));
        }
        if (Objects.nonNull(personCriteria.getAge())) {
            predicateList.add(criteriaBuilder.equal(root.get("age"), personCriteria.getAge()));
        }
        if (Objects.nonNull(personCriteria.getFirstName())) {
            predicateList.add(criteriaBuilder.equal(root.get("firstName"), personCriteria.getFirstName()));
        }
        if (Objects.nonNull(personCriteria.getLastName())) {
            predicateList.add(criteriaBuilder.equal(root.get("lastName"), personCriteria.getLastName()));
        }
        if (Objects.nonNull(personCriteria.getFavouriteColour())) {
            predicateList.add(criteriaBuilder.equal(root.get("favouriteColour"), personCriteria.getFavouriteColour()));
        }

        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
}
