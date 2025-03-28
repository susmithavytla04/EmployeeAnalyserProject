package org.Employees.Analysis;

import org.junit.gen5.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.gen5.api.Assertions.assertEquals;

public class CSVParserTest {


        @Test
        void testReadData(@TempDir Path tempDir) throws IOException {
            // Create a temporary file
            Path tempFile = tempDir.resolve("test.csv");
            Files.write(tempFile, List.of("id,firstname,lastname,salary,managerid", "1,John,Doe,100000,"));

            // Lambda implementation of EmployeeDataReader
            Parser parser = new CSVParser();

            // Read data from the file
            List<String> lines = parser.parse(tempFile.toString());

            // Assertions
            assertEquals(2, lines.size());
            assertEquals("id,firstname,lastname,salary,managerid", lines.get(0));
            assertEquals("1,John,Doe,100000,", lines.get(1));
        }


}
