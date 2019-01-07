import com.opencsv.CSVWriter;
import csvparser.ReadFile;
import csvparser.Transformer;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {
    public void createSampleCSV() throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter("example.csv"));
        csvWriter.writeNext(new String[]{"Timestamp", "Address", "ZIP", "FullName", "FooDuration", "BarDuration", "TotalDuration", "Notes"});
        csvWriter.writeNext(new String[]{"4/1/11 11:00:00 AM ", "\"123 4th St, Anywhere, AA\" ", "94121", "Monkey Alberto", "1:23:32.123", "1:32:33.123", "zzsasdfa", "I am the very model of a modern major general"});
        csvWriter.close();
    }

    @Test
    public void testFileWasReadInProperly() throws IOException {
        createSampleCSV();
        ReadFile file_reader = new ReadFile();
        File file = new File("example.csv");
        ArrayList<List<String>> input = file_reader.readInFile(file);
        List<String> row = input.get(0);
        assertEquals(row.get(0),"4/1/11 11:00:00 AM");
        assertEquals(row.get(1),"123 4th St, Anywhere, AA");
        assertEquals(row.get(2),"94121");
        assertEquals(row.get(3),"Monkey Alberto");
        assertEquals(row.get(4), "1:23:32.123");
        assertEquals(row.get(5), "1:32:33.123");
        assertEquals(row.get(6),"zzsasdfa");
        assertEquals(row.get(7), "I am the very model of a modern major general");

    }
    @Test
    public void testFileWasTransformedProperly() {
        ReadFile read = new ReadFile();
        Transformer transform = new Transformer();
        File file = new File("example.csv");
        ArrayList<List<String>> inputContainer = read.readInFile(file);
        ArrayList<List<String>> output = transform.dataProcessor(inputContainer);
        List<String> onlyRowInTestOutput = output.get(0);
        List<String> correctOutput = Arrays.asList("0011-01-04T03:00","123 4th St, Anywhere, AA","94121","MONKEY ALBERTO","5012.123","5553.123","10565.246","I am the very model of a modern major general");
        for(int i=0; i<onlyRowInTestOutput.size();i++){
            assertEquals(onlyRowInTestOutput.get(i),correctOutput.get(i));
        }
    }
    @Test
    public void testNormalizeTimestamp(){
        Transformer transform = new Transformer();
        String outputTime = transform.normalizeTimestamp("4/1/11 11:00:00 AM");
        assertEquals(outputTime,"0011-01-04T03:00");
    }
    @Test
    public void testNormalizeTime(){
        Transformer transform = new Transformer();
        String address = transform.normalizeAddress("123 4th St, Anywhere, AA");
        assertEquals(address,"123 4th St, Anywhere, AA");
    }
    @Test
    public void testNormalizeZipCode(){
        Transformer transform = new Transformer();
        String zip = transform.normalizeZipCode("1");
        assertEquals(zip,"00001");
    }
    @Test
    public void testNormalizeFullName(){
        Transformer transform = new Transformer();
        String name = transform.normalizeFullName("Monkey Alberto");
        assertEquals(name,"MONKEY ALBERTO");
    }
    @Test
    public void testNormalizeFooDuration(){
        Transformer transform = new Transformer();
        Float fooDuration = transform.normalizeFooDuration("1:23:32.123");
        Assert.assertEquals(Float.toString(fooDuration),Double.toString(5012.123));
    }
    @Test
    public void testNormalizeBarDuration(){
        Transformer transform = new Transformer();
        Float barDuration = transform.normalizeBarDuration("1:32:33.123");
        Assert.assertEquals(Float.toString(barDuration),Double.toString(5553.123));
    }
    @Test
    public void testNormalizeTotalDuration(){

        Transformer transform = new Transformer();
        Float totalDuration = transform.normalizeTotalDuration((float)5012.123,(float)5553.123);
        Assert.assertEquals(Float.toString(totalDuration),Double.toString(10565.246));
    }
    @Test
    public void testNormalizeTotalNotes(){
        Transformer transform = new Transformer();
        String notes = transform.normalizeNotes("I am the very model of a modern major general");
        assertEquals(notes,"I am the very model of a modern major general");
    }


}
