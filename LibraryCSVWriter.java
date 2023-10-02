import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryCSVWriter {
    private static final String RESULT_CSV_FILE = "result-catalog.csv";

    String[] fileHeader;
    String filename;
    String pathString;

    public LibraryCSVWriter(String[] fileHeader, String filename, String pathString) {
        this.fileHeader = fileHeader;
        this.filename = filename;
        this.pathString = pathString;
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
            String outputFile = ts+"-"+RESULT_CSV_FILE;
            CSVPrinter csvFilePrinter = null;
            CSVFormat csvFileFormat = CSVFormat.DEFAULT;
            File file = new File(pathString, outputFile);
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

        if (args.length != 1) {
            System.err.println("Usage: LibraryCSVWriter LibraryCatalogPath");
            System.exit(1);
         }
      
         for(int i = 0; i< args.length; i++) {
            System.out.println(String.format("Command Line Argument %d is %s", i, args[i]));
         }  

        List<String[]> data = new ArrayList<>();
        data.add(new String[]{ "A", "B", "C" });
        data.add(new String[]{ "1", "2", "3" });
        data.add(new String[]{ "A1", "B2", "C3" });

        String ts = getTimestampString();
        String outputFile = ts+"-"+RESULT_CSV_FILE;
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT;
        File file = new File(args[0], outputFile);
        FileWriter fileWriter = new FileWriter(file);
        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

        csvFilePrinter.printRecords(data);


        fileWriter.flush();
        fileWriter.close();
        csvFilePrinter.close();
    }
}