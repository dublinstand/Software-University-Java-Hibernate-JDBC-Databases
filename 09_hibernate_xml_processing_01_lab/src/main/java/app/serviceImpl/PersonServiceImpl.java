package app.serviceImpl;

import app.domain.dto.AddressJsonDto;
import app.domain.dto.PersonJsonDto;
import app.domain.dto.PhoneNumberJsonDto;
import app.domain.model.Address;
import app.domain.model.Person;
import app.domain.model.PhoneNumber;
import app.repository.PersonRepository;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;


    @Override
    public void create(PersonJsonDto personJsonDto) {
        //we create a person from the data in personJsonDto and persist it in the database
        Person person = convertToEntity(personJsonDto);
        this.personRepository.saveAndFlush(person);
    }

    @Override
    public PersonJsonDto findById(long id) {
        //we search for a person in the database by id and return it to PersonJsonDto
        Person person = this.personRepository.findOne(id);
        PersonJsonDto personJsonDto = convertToDto(person);
        return personJsonDto;
    }

    @Override
    public List<PersonJsonDto> findByCountry(String country) {
        //we search for a list of person in the database by country, convert it to list of PersonJsonDto
        //and return it
        List<Person> persons = this.personRepository.findByCountry(country);
        List<PersonJsonDto> personJsonDtos = new ArrayList<>();

        for (Person person : persons) {
            PersonJsonDto personJsonDto = convertToDto(person);
            personJsonDtos.add(personJsonDto);
        }

        return personJsonDtos;
    }

    private PersonJsonDto convertToDto(Person person){
        //instantiate PersonJsonDto and assign all properties, taken from the Person that is passed
        PersonJsonDto personJsonDto = new PersonJsonDto();
        personJsonDto.setFirstName(person.getFirstName());

        //because PersonJsonDto needs an AddressJsonDto, we instantiate the class and get the property
        //from the Address in the Person that we passed
        AddressJsonDto addressJsonDto = new AddressJsonDto();
        addressJsonDto.setCountry(person.getAddress().getCountry());
        addressJsonDto.setCountry(person.getAddress().getCountry());
        addressJsonDto.setCity(person.getAddress().getCity());
        //set the Address in PersonJsonDto
        personJsonDto.setAddress(addressJsonDto);

        //we get the set of phone numbers from the Person that we pass
        Set<PhoneNumber> phoneNumbers = person.getPhoneNumbers();

        //for each phone number we instantiate PhoneNumberJsonDto and add it to the
        //Set<PhoneNumberJsonDto> phoneNumbers in PersonJsonDto
        for (PhoneNumber phoneNumber : phoneNumbers) {
            PhoneNumberJsonDto phoneNumberJsonDto = new PhoneNumberJsonDto();
            phoneNumberJsonDto.setNumber(phoneNumber.getNumber());
            phoneNumberJsonDto.setPerson(personJsonDto);
            personJsonDto.getPhoneNumbers().add(phoneNumberJsonDto);
        }

        return personJsonDto;
    }

    private Person convertToEntity(PersonJsonDto personJsonDto){

        //We do the same as in convertToDto(), but here we get the info from PersonJsonDto
        Person person = new Person();
        person.setFirstName(personJsonDto.getFirstName());

        Address address = new Address();
        address.setCountry(personJsonDto.getAddress().getCountry());
        address.setCountry(personJsonDto.getAddress().getCountry());
        address.setCity(personJsonDto.getAddress().getCity());
        person.setAddress(address);

        Set<PhoneNumberJsonDto> phoneNumberJsonDtos = personJsonDto.getPhoneNumbers();

        for (PhoneNumberJsonDto phoneNumberJsonDto : phoneNumberJsonDtos) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setNumber(phoneNumberJsonDto.getNumber());
            phoneNumber.setPerson(person);
            person.getPhoneNumbers().add(phoneNumber);
        }

        return person;
    }
}
