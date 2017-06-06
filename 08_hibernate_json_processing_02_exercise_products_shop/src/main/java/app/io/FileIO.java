package app.io;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIO {

    public String reader(String fileName) throws IOException {
        StringBuilder fileContent = new StringBuilder();

        try (
                InputStream inputStream = new FileInputStream(fileName);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        }

        return fileContent.toString();
    }

    public void writer(String content, String fileName) throws IOException {
        try (
                OutputStream outputStream = new FileOutputStream(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        )
        {
            bufferedWriter.write(content);
        }
    }
}
