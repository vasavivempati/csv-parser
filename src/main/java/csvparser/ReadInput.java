package csvparser; /**
 * Created by vasavivempati on 12/27/18.
 */

import java.io.IOException;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.text.Normalizer.Form.NFD;

public class ReadInput {
    ArrayList<List<String>> outputContainer = new ArrayList<>();
    ArrayList<ArrayList<String>> full = new ArrayList<ArrayList<String>>();
    float fooDuration = 0;
    float barDuration = 0;
    float totalDuration = 0;

    public void addData(List<String> input) throws IOException {
        outputContainer.add(input);
    }

    public ArrayList<List<String>> getOutputContainer() {
        return outputContainer;
    }

    public void setOutputContainer(ArrayList<List<String>> outputContainerIn) {
        this.outputContainer = outputContainerIn;
    }

    public float getFooDuration() {
        return fooDuration;
    }

    public void setFooDuration(float fooDurationIn) {
        this.fooDuration = fooDurationIn;
    }

    public float getBarDuration() {
        return barDuration;
    }

    public void setBarDuration(float barDurationIn) {
        this.barDuration = barDurationIn;
    }

    public float getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(long totalDuration) {
        this.totalDuration = totalDuration;
    }

    public ArrayList<ArrayList<String>> getFull() {
        return full;
    }

    public void setFull(ArrayList<ArrayList<String>> fullIn) {
        this.full = fullIn;
    }

    //call timestamp
    // call zip_code
    // call names
    //call address
    // call FooDuration and BarDuration
    // call TotalDuration
    // call Notes
    //using output container gotta normalize the data
    public void dataProcessor() {
        for (List<String> row : outputContainer) {
            rowProcessor(row);
        }
    }

    public void rowProcessor(List<String> column) {
        ArrayList<String> formattedOutputContainer = new ArrayList<String>();
        formattedOutputContainer.add(normalizeTimestamp(column.get(0)));
        formattedOutputContainer.add(normalizeAddress(column.get(1)));
        formattedOutputContainer.add(normalizeZipCode(column.get(2)));
        formattedOutputContainer.add(normalizeFullName(column.get(3)));
        formattedOutputContainer.add(Float.toString(normalizeFooDuration(column.get(4))));
        formattedOutputContainer.add(Float.toString(normalizeBarDuration(column.get(5))));
        formattedOutputContainer.add(Float.toString(normalizeTotalDuration()));
        formattedOutputContainer.add(normalizeNotes(column.get(7)));
        full.add(formattedOutputContainer);
    }

    private String normalizeTimestamp(String inputDate) {
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US);
        DateFormat inputFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss", Locale.US);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC-4"));
        String outputText = "";
        try {
            Date date = inputFormat.parse(inputDate);
            outputText = outputFormat.format(date);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return outputText;
    }

    private String normalizeAddress(String inputAddress) {
        return Normalizer.normalize(inputAddress, NFD);
    }

    private String normalizeZipCode(String zipCode) {
        while (zipCode.length() < 5)
            zipCode = "0" + zipCode;
        return zipCode;
    }

    private String normalizeFullName(String fullName) {
        return fullName.toUpperCase(Locale.ENGLISH);
    }

    private float normalizeFooDuration(String fooDurationInput) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date time = sdf.parse(fooDurationInput);
            Long timeInMilliseconds = time.getTime();
            Float timeInFloatingPointSeconds = timeInMilliseconds.floatValue() / 1000L;
            this.setFooDuration(timeInFloatingPointSeconds);
            return timeInFloatingPointSeconds;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return fooDuration;

    }

    private float normalizeBarDuration(String barDurationInput) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date time = sdf.parse(barDurationInput);
            Long timeInMilliseconds = time.getTime();
            Float timeInFloatingPointSeconds = timeInMilliseconds.floatValue() / 1000L;
            this.setBarDuration(timeInFloatingPointSeconds);
            return timeInFloatingPointSeconds;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return barDuration;
    }

    private float normalizeTotalDuration() {
        totalDuration = this.getFooDuration() + this.getBarDuration();
        return totalDuration;
    }

    private String normalizeNotes(String normalizeNotes) {
        return Normalizer.normalize(normalizeNotes, NFD);
    }
}
