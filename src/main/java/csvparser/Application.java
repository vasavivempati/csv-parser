package csvparser;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by vasavivempati on 12/27/18.
 */
public class Application {
    public static void main(String[] args) throws java.io.IOException {
        System.out.println("Input file name");
        Scanner inp = new Scanner(System.in);
        String name = inp.nextLine();
        String line = "";
        ReadInput processor = new ReadInput();

        File file = new File(name);


        if (!file.exists()) {
            System.out.println("hello");
            System.out.println("Working Directory = " +
                    System.getProperty("user.dir"));

        }
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(name));
                FileWriter outputFile = new FileWriter("output.csv");
                CSVWriter fileWriter = new CSVWriter(outputFile);
                int first_row = 0;
                while ((line = br.readLine()) != null) {
                    if (first_row > 0) {
                        String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))",-1);
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
