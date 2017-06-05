package app.ip;

import org.springframework.stereotype.Component;

import java.io.*;

//in order to use it with @Autowired we set it as @Componenet
@Component
public class FileIO {

    public void write(String content, String fileName) throws IOException {
        //we throw the exception to the Controller, so that it can be returned to the View
        //put all resources in the try wit resources part, so that they will be closed
        try (
                OutputStream outputStream = new FileOutputStream(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        ) {
            //write the content to the file
            bufferedWriter.write(content);
        }
    }

    public String read(String fileName) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        //get class will put us into the resources folder and after that we can give whatever after resources folder is as path
        //again we have try with resources
        try (
                InputStream inputStream = new FileInputStream(fileName);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            //while there is a new line (the buffered reader is not null, append it tou our string builder
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        }
        //we return our string builder as string
        return fileContent.toString();
    }
}
