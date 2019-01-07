package csvparser;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteFile {
    public void writeNormalizedCSV(ArrayList<List<String>> outputContainer) throws IOException {
        FileWriter outputFile = new FileWriter("output.csv");
        CSVWriter fileWriter = new CSVWriter(outputFile);
        for (List<String> row : outputContainer) {
            fileWriter.writeNext(row.stream().toArray(String[]::new));
        }

        fileWriter.close();
    }
}
