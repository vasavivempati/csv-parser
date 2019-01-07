package csvparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        Transformer transform = new Transformer();
        ArrayList<List<String>> inputContainer = read.readInFile(file);
        ArrayList<List<String>> outputContainer = transform.dataProcessor(inputContainer);
        WriteFile write = new WriteFile();
        write.writeNormalizedCSV(outputContainer);

    }
}
