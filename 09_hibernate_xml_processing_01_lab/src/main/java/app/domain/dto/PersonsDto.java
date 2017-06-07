package app.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonsDto {

    @XmlElement(name = "person")
    private List<PersonDto> personDtos;

    public PersonsDto() {
        this.setPersonDtos(new ArrayList<>());
    }

    public List<PersonDto> getPersonDtos() {
        return personDtos;
    }

    public void setPersonDtos(List<PersonDto> personDtos) {
        this.personDtos = personDtos;
    }
}
