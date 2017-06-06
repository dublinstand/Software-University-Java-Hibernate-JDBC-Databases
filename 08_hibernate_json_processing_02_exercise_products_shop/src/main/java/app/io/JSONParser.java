package app.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JSONParser {

    private Gson gson;

    @Autowired
    private FileIO fileIO;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    public <T> void write(T object, String fileName) throws IOException {
        //create a Json string from the object we pass
        String content = this.gson.toJson(object);

        //create the file as json
        this.fileIO.writer(content, fileName);
    }

    public <T> T read(Class<T> tClass, String fileName) throws IOException {
        T object = null;

        //get the Json String from the file
        String content = this.fileIO.reader(fileName);

        //create the object from the class we pass and the Json content from the file we read
        object = this.gson.fromJson(content, tClass);

        return object;
    }
}
