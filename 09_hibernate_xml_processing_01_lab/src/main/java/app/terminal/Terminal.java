package app.terminal;

import app.domain.dto.AddressJsonDto;
import app.domain.dto.PersonJsonDto;
import app.domain.dto.PhoneNumberJsonDto;
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
        PersonJsonDto personJsonDto = this.seedData();

        try {
            //measure the execution time
            long startTime = System.currentTimeMillis();

            this.jsonParser.write(personJsonDto, "src/main/resources/files/output/json/person.json");

            long endTime = System.currentTimeMillis();
            double result = endTime - startTime;

            System.out.println("JSON time: " + result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //write many objects
    private void writeManyObjectsToJson(){

        //we need to create a list of PersonJsonDto and then add all objects to it
        List<PersonJsonDto> personJsonDtoList = new ArrayList<>();

        PersonJsonDto personJsonDto = this.seedData();
        PersonJsonDto personJsonDto2 = this.seedData();
        PersonJsonDto personJsonDto3 = this.seedData();

        personJsonDtoList.add(personJsonDto);
        personJsonDtoList.add(personJsonDto2);
        personJsonDtoList.add(personJsonDto3);

        try {
            //here we pass the list of objects, we can not pass personJsonDtoList.class
            this.jsonParser.write(personJsonDtoList, "src/main/resources/files/output/json/persons.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //create the data
    private PersonJsonDto seedData(){
        PersonJsonDto personJsonDto = new PersonJsonDto();
        personJsonDto.setFirstName("Donyo");

        AddressJsonDto addressJsonDto = new AddressJsonDto();
        addressJsonDto.setCountry("Bulgaria");
        addressJsonDto.setCity("Haskovo");

        personJsonDto.setAddress(addressJsonDto);

        PhoneNumberJsonDto phoneNumberJsonDto = new PhoneNumberJsonDto();
        phoneNumberJsonDto.setNumber("088888888");
        phoneNumberJsonDto.setPerson(personJsonDto);

        personJsonDto.getPhoneNumbers().add(phoneNumberJsonDto);

        return personJsonDto;
    }


    //all exceptions are handled in the Terminal or at the frontend, so that the user can see the errors
    //read single object from Json file and store it in the database
    private void readSingleObjectFromJson(){
        try {
            //we can get the personJsonDto from the parser
            //in the read method we pass the class we use - PersonJsonDto
            PersonJsonDto personJsonDto = this.jsonParser.read(PersonJsonDto.class, "src/main/resources/files/input/json/person.json");

            //now we can call our service and save the DTO object in the database
            //the service will take the DTO change it into an Entity and store it in the database
            this.personService.create(personJsonDto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read from an array of DTO objects and save it in the database
    private void readMultipleObjectsFromJson(){

        //here we pass an array as class - PersonJsonDto[].class
        try {
            PersonJsonDto[] personJsonDtos = this.jsonParser.read(PersonJsonDto[].class, "src/main/resources/files/input/json/persons.json");

            //create all persons
            for (PersonJsonDto personJsonDto : personJsonDtos) {
                this.personService.create(personJsonDto);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeSingleObjectsToXml(){

        PersonJsonDto personJsonDto = this.seedData();

        try {
            //here we can record how much it took to create the xml file
            long startTime = System.currentTimeMillis();

            this.xmlParser.write(personJsonDto, "src/main/resources/files/output/xml/person.xml");

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
