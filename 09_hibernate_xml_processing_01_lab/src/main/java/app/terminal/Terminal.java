package app.terminal;

import app.domain.dto.AddressDto;
import app.domain.dto.PersonDto;
import app.domain.dto.PhoneNumberDto;
import app.io.JSONParser;
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
    private JSONParser jsonParser;

    @Autowired
    private XMLParser xmlParser;

    @Override
    public void run(String... strings) throws Exception {
        this.writeSingleObjectToJson();
        this.writeSingleObjectsToXml();
//        this.writeManyObjectsToJson();
//        this.readSingleObjectFromJson();
//        this.readMultipleObjectsFromJson();
    }

    //we will be saving here DTOs
    private void writeSingleObjectToJson(){
        PersonDto personDto = this.seedData();

        try {
            //measure the execution time
            long startTime = System.currentTimeMillis();

            this.jsonParser.write(personDto, "src/main/resources/files/output/json/person.json");

            long endTime = System.currentTimeMillis();
            double result = endTime - startTime;

            System.out.println("JSON time: " + result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //write many objects
    private void writeManyObjectsToJson(){

        //we need to create a list of PersonDto and then add all objects to it
        List<PersonDto> personDtoList = new ArrayList<>();

        PersonDto personDto = this.seedData();
        PersonDto personDto2 = this.seedData();
        PersonDto personDto3 = this.seedData();

        personDtoList.add(personDto);
        personDtoList.add(personDto2);
        personDtoList.add(personDto3);

        try {
            //here we pass the list of objects, we can not pass personDtoList.class
            this.jsonParser.write(personDtoList, "src/main/resources/files/output/json/persons.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //create the data
    private PersonDto seedData(){
        PersonDto personDto = new PersonDto();
        personDto.setFirstName("Donyo");

        AddressDto addressDto = new AddressDto();
        addressDto.setCountry("Bulgaria");
        addressDto.setCity("Haskovo");

        personDto.setAddress(addressDto);

        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.setNumber("088888888");
        phoneNumberDto.setPerson(personDto);

        personDto.getPhoneNumbers().add(phoneNumberDto);

        return personDto;
    }


    //all exceptions are handled in the Terminal or at the frontend, so that the user can see the errors
    //read single object from Json file and store it in the database
    private void readSingleObjectFromJson(){
        try {
            //we can get the personDto from the parser
            //in the read method we pass the class we use - PersonDto
            PersonDto personDto = this.jsonParser.read(PersonDto.class, "src/main/resources/files/input/json/person.json");

            //now we can call our service and save the DTO object in the database
            //the service will take the DTO change it into an Entity and store it in the database
            this.personService.create(personDto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read from an array of DTO objects and save it in the database
    private void readMultipleObjectsFromJson(){

        //here we pass an array as class - PersonDto[].class
        try {
            PersonDto[] personDtos = this.jsonParser.read(PersonDto[].class, "src/main/resources/files/input/json/persons.json");

            //create all persons
            for (PersonDto personDto : personDtos) {
                this.personService.create(personDto);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeSingleObjectsToXml(){

        PersonDto personDto = this.seedData();

        try {
            //here we can record how much it took to create the xml file
            long startTime = System.currentTimeMillis();

            this.xmlParser.write(personDto, "src/main/resources/files/output/xml/person.xml");

            long endTime = System.currentTimeMillis();
            double result = endTime - startTime;

            System.out.println("XML time: " + result);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
