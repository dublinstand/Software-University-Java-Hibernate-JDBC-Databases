package app.domain.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


@XmlRootElement(name = "phone_number")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneNumberDto implements Serializable{

    @XmlElement
    @Expose
    private String number;

    //to miss the file as part of the XML - not to have StackOverflow error
    @XmlTransient
    //After we run the MainApplication.class we get an error - java.lang.StackOverflowError: null.
    // This is because one person can have a lot of phone numbers and one phone number belongs to a person and we get Stack overflow.
    // We need to remove the @Expose annoation from PhonNumberJsonDto –> person;
    private PersonDto person;

    public PhoneNumberDto() {
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
