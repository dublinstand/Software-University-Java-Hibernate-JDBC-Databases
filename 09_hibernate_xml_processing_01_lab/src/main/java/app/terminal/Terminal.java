package app.terminal;

import app.domain.dto.AddressDto;
import app.domain.dto.PersonDto;
import app.domain.dto.PersonsDto;
import app.domain.dto.PhoneNumberDto;
import app.io.XMLParser;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    @Autowired
    private XMLParser xmlParser;

    @Override
    public void run(String... strings) throws Exception {
//        this.writeSingleObjectsToXml();
//        this.writeManyObjectsToXml();

//        this.readSingleObjectFromXML();
        this.readMultipleObjectFromXML();
    }

    //create the data
    private PersonDto seedData() {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName("Donyo");

        AddressDto addressDto = new AddressDto();
        addressDto.setCountry("Bulgaria");
        addressDto.setCity("Haskovo");

        personDto.setAddress(addressDto);

        PhoneNumberDto phoneNumberDto1 = new PhoneNumberDto();
        phoneNumberDto1.setNumber("088888888");
        phoneNumberDto1.setPerson(personDto);

        PhoneNumberDto phoneNumberDto2 = new PhoneNumberDto();
        phoneNumberDto2.setNumber("088997766");
        phoneNumberDto2.setPerson(personDto);

        personDto.getPhoneNumbers().add(phoneNumberDto1);
        personDto.getPhoneNumbers().add(phoneNumberDto2);

        return personDto;
    }

    //write single object to xml file
    private void writeSingleObjectsToXml() {

        PersonDto personDto = this.seedData();

        try {
            this.xmlParser.write(personDto, "src/main/resources/files/output/xml/person.xml");

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //write many objects to xml file
    private void writeManyObjectsToXml() {

        PersonDto personDto = this.seedData();
        PersonDto personDto2 = this.seedData();

        //initialize the list and add the two persons
        List<PersonDto> personDtos = new ArrayList();
        personDtos.add(personDto);
        personDtos.add(personDto2);

        //here we create our PersonsDto list which is annotated and in it we set the list of PersonDto that we have set
        PersonsDto personsDto = new PersonsDto();
        personsDto.setPersonDtos(personDtos);

        try {
            this.xmlParser.write(personsDto, "src/main/resources/files/output/xml/persons.xml");

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read an object from XML file and store it into the database
    private void readSingleObjectFromXML(){
        try {
            //here we get our PersonDto from the file
            PersonDto personDto = this.xmlParser.read(PersonDto.class, "/files/input/xml/person.xml");

            //save it in the database
            this.personService.create(personDto);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read an multiple objects from XML file and store it into the database
    private void readMultipleObjectFromXML(){
        try {
            //here we get our PersonsDto from the file
            PersonsDto personsDto = this.xmlParser.read(PersonsDto.class, "/files/input/xml/persons.xml");

            //because PersonsDto has a collection of PersonDto in it we need to create a List<PersonDto> from the PersonsDto and iterate it
            List<PersonDto> personDtos = personsDto.getPersonDtos();

            //we need to iterate it now
            for (PersonDto personDto : personDtos) {
                //save each object in the database
                this.personService.create(personDto);
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
