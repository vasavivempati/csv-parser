import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

/**
 * Created by vasavivempati on 12/27/18.
 */
public class Application {
    public static void main(String[] args) throws java.io.IOException{
        System.out.println("Input file name");
        Scanner inp=new Scanner(System.in);
        String name=inp.nextLine();
        String line = "";
        ReadInput processor = new ReadInput();

        File file = new File(name);


        if(!file.exists()){
            System.out.println("hello");
            System.out.println("Working Directory = " +
                    System.getProperty("user.dir"));

        }
        if(file.exists()) {
            try {
                //FileReader file = new FileReader(name);
                BufferedReader br=new BufferedReader(new FileReader(name));
                ////BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"));
                ////FileWriter outputFile = new FileWriter("output.csv");
                //CSVWriter fileWriter = new CSVWriter(outputFile);
                //ArrayList<String[]> data = new ArrayList<>();

                while((line =br.readLine())!=null){
                    //System.out.println(line);
                    //processor.addData(line);
                    String[] split = line.split(",");
                    processor.addData(split);
                    //fileWriter.writeNext(split);

                }

                //fileWriter.close();
                br.close();
            }
            catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}