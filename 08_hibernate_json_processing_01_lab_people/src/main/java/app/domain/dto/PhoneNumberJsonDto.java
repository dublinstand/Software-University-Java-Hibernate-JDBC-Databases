package app.domain.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

//this annotation is for XML
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneNumberJsonDto implements Serializable{

    @XmlElement
    @Expose
    private String number;

    //to miss the file
    @XmlTransient
    //After we run the MainApplication.class we get an error - java.lang.StackOverflowError: null.
    // This is because one person can have a lot of phone numbers and one phone number belongs to a person and we get Stack overflow.
    // We need to remove the @Expose annoation from PhonNumberJsonDto –> person;
    private PersonJsonDto person;

    public PhoneNumberJsonDto() {
    }

    public PersonJsonDto getPerson() {
        return person;
    }

    public void setPerson(PersonJsonDto person) {
        this.person = person;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
