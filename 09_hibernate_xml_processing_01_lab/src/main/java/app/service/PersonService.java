package app.service;

import app.domain.dto.PersonDto;

import java.util.List;

public interface PersonService {

    void create(PersonDto person);

    PersonDto findById(long id);

    List<PersonDto> findByCountry(String country);
}
