package app.ip;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JSONParser {

    private Gson gson;

    //we use our FileIO class for input/output. We can use it as @Autowired, because the class has
    //@Component annotation
    @Autowired
    private FileIO fileIO;

    public static JSONParser jsonParser;

    //here we set our gson to exclude fields in our entities that don't have @Expose annotation
    //have pretty printing and create it
    public JSONParser() {
        this.gson = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .setPrettyPrinting()
        .create();
    }

    //<T> write (T object, String fileName) - means it takes and returns generic object
    //also we pass the path to the file we will write
    public <T> void write(T object, String fileName) throws IOException {
        String content = this.gson.toJson(object);
        //our files will be in resources/files/input or in resources/files/output
        this.fileIO.write(content, fileName);
    }

    //Again we have a generic method - <T> T - it will return the same generic object
    //it will get the Class<T> clazz - a class of the generic method and will have the file path
    public <T> T read (Class<T> clazz, String fileName) throws IOException {
        //we create the variable for the generic object
        T object = null;

        //read the content from a file, all exception go into the method
        String content = this.fileIO.read(fileName);

        //we pass the content and the class type
        object = gson.fromJson(content, clazz);

        //return the object
        return object;
    }
}
