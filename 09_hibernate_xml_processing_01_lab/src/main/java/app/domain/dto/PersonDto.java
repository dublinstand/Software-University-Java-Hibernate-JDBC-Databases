package app.domain.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


//this is the root of our xml structure and we give the name = person
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDto implements Serializable{

    @XmlElement(name = "first_name")
    @Expose
    private String firstName;

    @XmlElement(name = "address")
    @Expose
    private AddressDto address;

    //because we have a Set of PhoneNumberDto we use the annotation @XmlElementWrapper to say it is an array
    @XmlElementWrapper(name = "phone_numbers")
    //in order to use @XmlElement we need to use @XmlElement(name = "phone_number")
    //the name value should be equal to the value of the root in PhoneNumberDto - @XmlRootElement(name = "phone_number")
    @XmlElement(name = "phone_number")
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
