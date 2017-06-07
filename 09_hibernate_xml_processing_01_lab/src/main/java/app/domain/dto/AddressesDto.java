package app.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "addresses")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressesDto {

    @XmlElement(name = "address")
    private List<AddressesDto> addressesDto;

    public AddressesDto() {
        this.setAddressesDto(new ArrayList<>());
    }

    public List<AddressesDto> getAddressesDto() {
        return addressesDto;
    }

    public void setAddressesDto(List<AddressesDto> addressesDto) {
        this.addressesDto = addressesDto;
    }
}
