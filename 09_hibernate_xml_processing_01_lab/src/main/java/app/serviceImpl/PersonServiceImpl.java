package app.serviceImpl;

import app.domain.dto.AddressDto;
import app.domain.dto.PersonDto;
import app.domain.dto.PhoneNumberDto;
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
    public void create(PersonDto personDto) {
        //we create a person from the data in personDto and persist it in the database
        Person person = convertToEntity(personDto);
        this.personRepository.saveAndFlush(person);
    }

    @Override
    public PersonDto findById(long id) {
        //we search for a person in the database by id and return it to PersonDto
        Person person = this.personRepository.findOne(id);
        PersonDto personDto = convertToDto(person);
        return personDto;
    }

    @Override
    public List<PersonDto> findByCountry(String country) {
        //we search for a list of person in the database by country, convert it to list of PersonDto
        //and return it
        List<Person> persons = this.personRepository.findByCountry(country);
        List<PersonDto> personDtos = new ArrayList<>();

        for (Person person : persons) {
            PersonDto personDto = convertToDto(person);
            personDtos.add(personDto);
        }

        return personDtos;
    }

    private PersonDto convertToDto(Person person){
        //instantiate PersonDto and assign all properties, taken from the Person that is passed
        PersonDto personDto = new PersonDto();
        personDto.setFirstName(person.getFirstName());

        //because PersonDto needs an AddressDto, we instantiate the class and get the property
        //from the Address in the Person that we passed
        AddressDto addressDto = new AddressDto();
        addressDto.setCountry(person.getAddress().getCountry());
        addressDto.setCountry(person.getAddress().getCountry());
        addressDto.setCity(person.getAddress().getCity());
        //set the Address in PersonDto
        personDto.setAddress(addressDto);

        //we get the set of phone numbers from the Person that we pass
        Set<PhoneNumber> phoneNumbers = person.getPhoneNumbers();

        //for each phone number we instantiate PhoneNumberDto and add it to the
        //Set<PhoneNumberDto> phoneNumbers in PersonDto
        for (PhoneNumber phoneNumber : phoneNumbers) {
            PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
            phoneNumberDto.setNumber(phoneNumber.getNumber());
            phoneNumberDto.setPerson(personDto);
            personDto.getPhoneNumbers().add(phoneNumberDto);
        }

        return personDto;
    }

    private Person convertToEntity(PersonDto personDto){

        //We do the same as in convertToDto(), but here we get the info from PersonDto
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());

        Address address = new Address();
        address.setCountry(personDto.getAddress().getCountry());
        address.setCountry(personDto.getAddress().getCountry());
        address.setCity(personDto.getAddress().getCity());
        person.setAddress(address);

        Set<PhoneNumberDto> phoneNumberDtos = personDto.getPhoneNumbers();

        for (PhoneNumberDto phoneNumberDto : phoneNumberDtos) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setNumber(phoneNumberDto.getNumber());
            phoneNumber.setPerson(person);
            person.getPhoneNumbers().add(phoneNumber);
        }

        return person;
    }
}
