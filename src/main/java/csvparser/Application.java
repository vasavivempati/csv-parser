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

        File file = new File(name);
        ReadFile read = new ReadFile();
        read.readInFile(file,name);

    }
}
