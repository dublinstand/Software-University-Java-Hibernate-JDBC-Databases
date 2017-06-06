package app.io;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

@Component
public class XMLParser {

    //we need to have JAXBContext to be initialized
    private JAXBContext jaxbContext;

    public <T> void write(T object, String fileName) throws JAXBException, IOException {
        //we initialize jaxbContext
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

    public <T> T read(Class<T> tClass, String fileName){

        return null;
    }
}
