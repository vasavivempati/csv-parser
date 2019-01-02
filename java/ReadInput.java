/**
 * Created by vasavivempati on 12/27/18.
 */
import java.io.*;
import java.text.Normalizer;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.TimeZone;
import java.util.Date;
import static java.text.Normalizer.Form.NFD;

public class ReadInput {
    ArrayList<String[]> outputContainer = new ArrayList<String[]>();
    ArrayList<String> formattedOutputContainer = new ArrayList<String>();
    ArrayList<ArrayList<String>> full = new ArrayList<ArrayList<String>>();
    long fooDuration = 0;
    long barDuration = 0;
    long totalDuration = 0;
    public  void addData(String[] input) throws IOException{
        outputContainer.add(input);
    }
    public ArrayList<String[]> getOutputContainer(){
        return outputContainer;
    }
    public long getFooDuration()
    {
        return fooDuration;
    }
    public long getBarDuration()
    {
        return barDuration;
    }
    public long getTotalDurationDuration()
    {
        return totalDuration;
    }
    public  ArrayList<ArrayList<String>> getFull()
    {
        return full;
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
        for (String[] column:outputContainer) {
            rowProcessor(column);
        }
    }
    public void rowProcessor(String[] column ){
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM YYYY");
        formattedOutputContainer.add(sdf.format(normalizeTimestamp(column[0])));
        formattedOutputContainer.add(normalizeAddress(column[1]));
        formattedOutputContainer.add( normalizeZipCode(column[2]));
        formattedOutputContainer.add(normalizeFullName(column[3]));
        formattedOutputContainer.add(Long.toString(normalizeFooDuration(column[4])));
        formattedOutputContainer.add(Long.toString(normalizeBarDuration(column[5])));
        formattedOutputContainer.add(Long.toString(normalizeTotalDuration()));
        formattedOutputContainer.add(normalizeNotes(column[7]));
        full.add(formattedOutputContainer);
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
    private long normalizeFooDuration(String fooDurationInput){
        try {DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        Date date = dateFormat.parse((String.valueOf(fooDurationInput)));
       fooDuration = date.getTime() / 1000L;
       return fooDuration;}
        catch(ParseException e) {
            System.out.println(e.getMessage());
        }
        return fooDuration;

    }
    private long normalizeBarDuration(String barDurationInput){
        try {DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        Date date = dateFormat.parse((String.valueOf(barDurationInput)));
        barDuration = date.getTime() / 1000L;
        return barDuration;}
        catch(ParseException e){
            System.out.println(e.getMessage());
        }
        return barDuration;
    }
    private long normalizeTotalDuration(){
        totalDuration = fooDuration + barDuration;
        return totalDuration;
    }
    private String normalizeNotes(String normalizeNotes){
        return Normalizer.normalize(normalizeNotes,NFD);
    }
}
