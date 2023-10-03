import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class PurchaseOrderCSVReader {
    String[] header;
    HashMap<String, PurchaseOrderItem> ordersBySku;

    public PurchaseOrderCSVReader(String filenameString, String pathString) {
        this.ordersBySku = new HashMap<String, PurchaseOrderItem>();
        readPurchaseOrderFile(filenameString, pathString);
    }

    public void readPurchaseOrderFile(String filename, String pathString) {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(pathString, filename));
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
                System.out.println("Found PO Header\n"+ Arrays.toString(header));
            }
            while (it.hasNext()) {
                csvRecord = it.next();
                // Accessing SKU and Actual by Column Index
                PurchaseOrderItem item = new PurchaseOrderItem();
                item.setItemName(csvRecord.get(0));
                item.setVariationName(csvRecord.get(1));
                item.setSkuString(csvRecord.get(2));
                item.setGtin(csvRecord.get(3));
                item.setVendorCode(csvRecord.get(4));
                item.setNotes(csvRecord.get(5));
                item.setQty(csvRecord.get(6));
                item.setUnitCost(csvRecord.get(7));

                ordersBySku.put(item.getSkuString(), item);
            }
        } catch(Exception e) {
            System.out.println("An exception was thrown while reading Inventory Item Counts. File: "+ filename);
            System.out.println(e.getMessage());
         }
    }

    public HashMap<String, PurchaseOrderItem> getOrdersBySku() {
        return ordersBySku;
    }

    public String[] getHeader() {
        return header;
    }

    // Unit Test
    public static void main(String[] args) throws IOException {
        final String pathString = ".";
        final String filename = "test-purchaseorder.csv";
        
        PurchaseOrderCSVReader reader = new PurchaseOrderCSVReader(filename, pathString);
        HashMap<String, PurchaseOrderItem> items = reader.getOrdersBySku();
        int count = items.size();
        if (count == 5) {
            System.out.println("Succes: "+filename+" loaded successfully");
            System.exit(0);
        }
        System.out.println("Error reading "+filename+": Expected 5 items and saw "+count);
        System.exit(1);

    }
}
