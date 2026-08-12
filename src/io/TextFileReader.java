package io;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileReader {

    public String read(Path filePath) throws IOException{
        return Files.readString(filePath);
    }
}