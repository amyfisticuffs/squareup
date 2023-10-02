import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class InventoryCSVReader {
    String filenameString;
    String pathString;
    Path filePath;
    String[] header;
    HashMap<String, String> countsBySku;

    public InventoryCSVReader(String filenameString, String pathString) {
        this.filenameString = filenameString;
        this.pathString = pathString;
        this.filePath = Paths.get(pathString, filenameString);
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
            System.out.println("An exception was thrown while reading Inventory Item Counts. File: "+ filenameString);
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

        if (args.length != 2) {
            System.err.println("Usage: InventoryCSVReader InventoryFile FilePath");
            System.exit(1);
         }
      
         for(int i = 0; i< args.length; i++) {
            System.out.println(String.format("Command Line Argument %d is %s", i, args[i]));
         }  

        try (
            Reader reader = Files.newBufferedReader(Paths.get(args[1], args[0]));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            // Item Name,Item Variation,SKU,GTIN,Unit,Unit cost,Expected,Actual,Variance (count),Variance (cost),Counters
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                String name = csvRecord.get(0);
                String itemVariation = csvRecord.get(1);
                String sku = csvRecord.get(2);
                String gtin = csvRecord.get(3);
                String unit = csvRecord.get(4);
                String unitCost = csvRecord.get(5);
                String expected = csvRecord.get(6);
                String actual = csvRecord.get(7);
                String varianceCount = csvRecord.get(8);
                String varianceCost = csvRecord.get(9);
                String counters = csvRecord.get(10);

                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Name : " + name);
                System.out.println("Item Variation : " + itemVariation);
                System.out.println("SKU : " + sku);
                System.out.println("GTIN : " + gtin);
                System.out.println("Unit : " + unit);
                System.out.println("Unit Cost : " + unitCost);
                System.out.println("Expected : " + expected);
                System.out.println("Actual : " + actual);
                System.out.println("Variance (Count) : " + varianceCount);
                System.out.println("Variance (Cost) : " + varianceCost);
                System.out.println("Counter : " + counters);
                System.out.println("---------------\n\n");
            }
        } 
    }
}