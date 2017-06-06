package app.domain.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

//this annotation is for XML
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressJsonDto implements Serializable{

    @XmlElement
    @Expose
    private String country;

    @XmlElement
    @Expose
    private String city;


    public AddressJsonDto() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
