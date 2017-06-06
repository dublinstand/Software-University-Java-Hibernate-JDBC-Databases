package app.domain.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//this annotation is for XML
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDto implements Serializable{

    @XmlElement
    @Expose
    private String firstName;

    @XmlElement
    @Expose
    private AddressDto address;

    @XmlElement
    @Expose
    private Set<PhoneNumberDto> phoneNumbers;

    public PersonDto() {
        this.setPhoneNumbers(new HashSet<>());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Set<PhoneNumberDto> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumberDto> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
