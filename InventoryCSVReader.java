import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class InventoryCSVReader {

    String[] header;
    HashMap<String, String> countsBySku;

    public InventoryCSVReader(String filenameString, String pathString) {
        this.countsBySku = new HashMap<String, String>();
        readInventoryFile(filenameString);
    }

    public void readInventoryFile(String filename) {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) { 
            Iterator<CSVRecord> it = csvParser.iterator();
            CSVRecord csvRecord;
            // Grab the header
            if (it.hasNext()) {
                csvRecord = it.next();
                Iterable<String> source = csvRecord;
                ArrayList<String> target = new ArrayList<String>();
                source.forEach(target::add);
                header = new String[csvRecord.size()];
                header = target.toArray(header);
                System.out.println("Found Inventory Header\n"+ Arrays.toString(header));
            }
            while (it.hasNext()) {
                csvRecord = it.next();
                // Accessing SKU and Actual by Column Index
                String sku = csvRecord.get(2);
                String actual = csvRecord.get(7);

                countsBySku.put(sku, actual);
            }
        } catch(Exception e) {
            System.out.println("An exception was thrown while reading Inventory Item Counts. File: "+ filename);
            System.out.println(e.getMessage());
         }
    }

    public HashMap<String, String> getCountsBySku() {
        return countsBySku;
    }

    public String[] getHeader() {
        return header;
    }

    // Unit Test
    public static void main(String[] args) throws IOException {
    
        final String pathString = ".";
        final String filename = "test-itemcounts.csv";
        
        InventoryCSVReader reader = new InventoryCSVReader(filename, pathString);
        HashMap<String, String> items = reader.getCountsBySku();
        int count = items.size();
        if (count == 6) {
            System.out.println("Succes: "+filename+" loaded successfully");
            System.exit(0);
        }
        System.out.println("Error reading "+filename+": Expected 5 items and saw "+count);
        System.exit(1);
    }
}