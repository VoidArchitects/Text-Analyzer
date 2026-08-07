package io;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileReader {

    public String read(String path) throws IOException{
        Path filePath = Path.of(path);
        return Files.readString(filePath);
    }
}