import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryCSVWriter {
    private static final String SAMPLE_CSV_FILE = "result-catalog.csv";
    private static final String WORKSPACE_PATH = ".";


    String[] fileHeader;
    String filename;

    public LibraryCSVWriter(String[] fileHeader, String filename) {
        this.fileHeader = fileHeader;
        this.filename = filename;
    }

    public static String getTimestampString() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        return timeStamp;
    }

    public List<String[]> createDataList(HashMap<String, LibraryItem>  libraryItemsBySku) {
        List<String[]> data = new ArrayList<>();
        // Add header
        data.add(fileHeader);
        // recreate csv file in the data list
        for (LibraryItem value : libraryItemsBySku.values()) {
            data.add(value.toArray());
        }

        return data;
    }

    public void writeFile(HashMap<String, LibraryItem>  libraryItemsBySku) {
        try {
            List<String[]> csvDataList = createDataList(libraryItemsBySku);
            String ts = getTimestampString();
            String outputFile = ts+"-"+SAMPLE_CSV_FILE;
            CSVPrinter csvFilePrinter = null;
            CSVFormat csvFileFormat = CSVFormat.DEFAULT;
            File file = new File(WORKSPACE_PATH, outputFile);
            FileWriter fileWriter = new FileWriter(file);
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            csvFilePrinter.printRecords(csvDataList);

            fileWriter.flush();
            fileWriter.close();
            csvFilePrinter.close();
        } catch(Exception e) {
            System.out.println("Failed to write Library File"+e.toString());
        }
    }

    public String[] getFileHeader() {
        return fileHeader;
    }

    // Unit Test
    public static void main(String[] args) throws IOException {

        List<String[]> data = new ArrayList<>();
        data.add(new String[]{ "A", "B", "C" });
        data.add(new String[]{ "1", "2", "3" });
        data.add(new String[]{ "A1", "B2", "C3" });

        String ts = getTimestampString();
        String outputFile = ts+"-"+SAMPLE_CSV_FILE;
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT;
        File file = new File(WORKSPACE_PATH, outputFile);
        FileWriter fileWriter = new FileWriter(file);
        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

        csvFilePrinter.printRecords(data);


        fileWriter.flush();
        fileWriter.close();
        csvFilePrinter.close();
    }
}