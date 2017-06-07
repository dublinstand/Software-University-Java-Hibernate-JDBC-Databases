package app.io;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component
public class XMLParser {

    //we need to have JAXBContext to be initialized
    private JAXBContext jaxbContext;

    public <T> void write(T object, String fileName) throws JAXBException, IOException {
        //we initialize jaxbContext and pass the class of the object
        this.jaxbContext = JAXBContext.newInstance(object.getClass());

        try (
                OutputStream outputStream = new FileOutputStream(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        ) {
            //this is the library that we use for creating an XML file from an object
            Marshaller marshaller = this.jaxbContext.createMarshaller();

            //have a nicer output
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //here we write the object to an xml file with the buffered writer
            marshaller.marshal(object, bufferedWriter);
        }
    }

    public <T> T read(Class<T> tClass, String fileName) throws JAXBException, IOException {

        T object = null;

        //we initialize jaxbContext and pass the class from the method
        this.jaxbContext = JAXBContext.newInstance(tClass);

        try (
                //when we read from a file we can use this.getClass() - it will get us to the resources folder
                //now for a file we can pass - /files/input/xml/person.xml
                InputStream inputStream = this.getClass().getResourceAsStream(fileName);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            //here we use Unmarshaller library
            Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();

            //here we create our object from the file xml
            object = (T) unmarshaller.unmarshal(bufferedReader);
        }

        return object;
    }
}
