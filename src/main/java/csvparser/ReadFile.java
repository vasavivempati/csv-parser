package csvparser;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFile {
    public void readInFile(File inputFile, String name){
        if (!inputFile.exists()) {
            System.out.println("File doesn't exist");

        }
        String line;
        ReadInput processor = new ReadInput();
        if (inputFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(name));
                FileWriter outputFile = new FileWriter("output.csv");
                CSVWriter fileWriter = new CSVWriter(outputFile);
                int first_row = 0;
                while ((line = br.readLine()) != null) {
                    if (first_row > 0) {
                        String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))", -1);
                        List<String> fields = Arrays.stream(split).map(field -> field.replace("\"", "").trim()).collect(Collectors.toList());
                        processor.addData(fields);
                    }
                    first_row++;
                }
                processor.dataProcessor();
                for (ArrayList<String> row : processor.getFull()) {
                    String[] rowIn = new String[row.size()];
                    int i = 0;
                    for (String item : row) {
                        rowIn[i] = item;
                        i++;
                    }
                    fileWriter.writeNext(rowIn);
                }

                fileWriter.close();
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
