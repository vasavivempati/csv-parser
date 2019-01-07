import org.junit.Test;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
public class ApplicationTest {
    public void createSampleCSV() throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter("example.csv"));
        csvWriter.writeNext(new String[]{"Timestamp", "Address", "ZIP", "FullName","FooDuration","BarDuration","TotalDuration","Notes"});
        csvWriter.writeNext(new String[]{"4/1/11 11:00:00 AM ", "\"123 4th St, Anywhere, AA\" ", "94121", "Monkey Alberto","1:23:32.123","1:32:33.123","zzsasdfa","I am the very model of a modern major general"});
        csvWriter.close();
    }
    @Test
    public void testTransformCSVLine() throws IOException {
        createSampleCSV();

    }
}
