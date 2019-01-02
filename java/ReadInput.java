/**
 * Created by vasavivempati on 12/27/18.
 */
import java.io.*;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import static java.text.Normalizer.Form.NFD;

public class ReadInput {
    ArrayList<String[]> outputContainer = new ArrayList<String[]>();
    ArrayList<String[]> formattedOutputContainer = new ArrayList<String[]>();
    public  void addData(String[] input) throws IOException{
        outputContainer.add(input);
    }
    //call timestamp
    // call zip_code
    // call names
    //call address
    // call FooDuration and BarDuration
    // call TotalDuration
    // call Notes
    //using output container gotta normalize the data
    public void dataProcessor(){
        for (String[] row:outputContainer) {

        }
    }
    public void rowProcessor(String[] column ){
        normalizeTimestamp(column[0]);
        normalizeAddress(column[1]);
        normalizeZipCode(column[2]);
        //normalizeFullName(column[3]);
       // normalizeFooDuration(column[4]);
        //normalizeBarDuration(column[5]);
        //normalizeTotalDuration(column[6]);
        //normalizeNotes(column[7]);
    }
    private LocalDateTime normalizeTimestamp(String inputDate){

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime localDateTime = LocalDateTime.parse(inputDate, format);
        return localDateTime;
    }
    private String normalizeAddress(String inputAddress){
         return Normalizer.normalize(inputAddress,NFD);
    }
    private String normalizeZipCode(String zipCode){
       while (zipCode.length() < 5)
           zipCode = "0" + zipCode;
       return zipCode;
    }
    private String normalizeFullName(String fullName){
        return fullName.toUpperCase(Locale.ENGLISH);
    }
    private void normalizeFooDuration(String foodDuration){
        

    }
    private void normalizeBarDuration(String barDuration){

    }
    private void normalizeTotalDuration(String totalDuration){

    }
    private void normalizeNotes(String normalizeNotes){

    }
}
