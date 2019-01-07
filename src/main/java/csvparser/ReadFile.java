package csvparser;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFile {
    public ArrayList<List<String>> readInFile(File inputFile) {
        if (!inputFile.exists()) {
            System.out.println("File doesn't exist");

        }
        String line;
        ArrayList<List<String>> inputContainer = new ArrayList<>();
        if (inputFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(inputFile.getName()));
                int first_row = 0;
                while ((line = br.readLine()) != null) {
                    if (first_row > 0) {
                        String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))", -1);
                        List<String> fields = Arrays.stream(split).map(field -> field.replace("\"", "").trim()).collect(Collectors.toList());
                        inputContainer.add(fields);
                    }
                    first_row++;
                }
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return inputContainer;
    }
}
