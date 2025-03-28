package org.Employees.Analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVParser implements Parser {

    @Override
    public List<String> parse(String Path){
        try {
            return Files.readAllLines(Paths.get(Path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
