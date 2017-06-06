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
public class PersonJsonDto implements Serializable{

    @XmlElement
    @Expose
    private String firstName;

    @XmlElement
    @Expose
    private AddressJsonDto address;

    @XmlElement
    @Expose
    private Set<PhoneNumberJsonDto> phoneNumbers;

    public PersonJsonDto() {
        this.setPhoneNumbers(new HashSet<>());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public AddressJsonDto getAddress() {
        return address;
    }

    public void setAddress(AddressJsonDto address) {
        this.address = address;
    }

    public Set<PhoneNumberJsonDto> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumberJsonDto> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
