import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class LibraryCSVReader {
    String filenameString;
    String pathString;
    String[] header;
    HashMap<String, LibraryItem> itemsBySku;

    public LibraryCSVReader(String filenameString, String pathString) {
        this.filenameString = filenameString;
        this.pathString = pathString;
        this.itemsBySku = new HashMap<String, LibraryItem>();
        readInventoryFile(filenameString, pathString);
    }

    public String readInventoryFile(String filename, String pathString) {
        String headerLine = new String();
        Path filepath = Paths.get(pathString, filename);
        try (
            Reader reader = Files.newBufferedReader(filepath);
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
                // System.out.println("Found Library Header\n"+ Arrays.toString(header));
            }
            while (it.hasNext()) {
                csvRecord = it.next();
                LibraryItem item = new LibraryItem();
                String token = csvRecord.get(0);
                item.setToken(token);
                String itemName = csvRecord.get(1);
                item.setItemName(itemName);
                String variationName = csvRecord.get(2);
                item.setVariationName(variationName);
                String sku = csvRecord.get(3);
                item.setSku(sku);
                String desc = csvRecord.get(4);
                item.setDesc(desc);
                String category = csvRecord.get(5);
                item.setCategory(category);
                String gtin = csvRecord.get(6);
                item.setGtin(gtin);
                String itemVis = csvRecord.get(7);
                item.setItemVis(itemVis);
                String weight = csvRecord.get(8);
                item.setWeight(weight);
                String price = csvRecord.get(9);
                item.setPrice(price);
                String sellable = csvRecord.get(10);
                item.setSellable(sellable);
                String stockable = csvRecord.get(11);
                item.setStockable(stockable);
                String optName1 = csvRecord.get(12);
                item.setOptName1(optName1);
                String optVal1 = csvRecord.get(13);
                item.setOptVal1(optVal1);
                String optName2 = csvRecord.get(14);
                item.setOptName2(optName2);
                String optVal2 = csvRecord.get(15);
                item.setOptVal2(optVal2);
                String optName3 = csvRecord.get(16);
                item.setOptName3(optName3);
                String optVal3 = csvRecord.get(17);
                item.setOptVal3(optVal3);
                String defUnitCost = csvRecord.get(18);
                item.setDefUnitCost(defUnitCost);
                String defVendorName = csvRecord.get(19);
                item.setDefVendorName(defVendorName);
                String defVendorCode = csvRecord.get(20);
                item.setDefVendorCode(defVendorCode);
                String quantity = csvRecord.get(21);
                item.setQuantity(quantity);
                String newQuantity = csvRecord.get(22);
                item.setNewQuantity(newQuantity);
                String alertEnable = csvRecord.get(23);
                item.setAlertEnable(alertEnable);
                String alertCount = csvRecord.get(24);
                item.setAlertCount(alertCount);
                String tax = csvRecord.get(25);
                item.setTax(tax);
                itemsBySku.put(sku, item);
            }
        } catch(Exception e) {
            System.out.println("An exception was thrown while reading Library Item Catalog File: "+ filenameString);
            System.out.println(e.getMessage());
        }
        // System.out.println("Found "+itemsBySku.size()+" in "+filepath.toString());
        return headerLine;
    }

    public HashMap<String, LibraryItem> getItemsBySku() {
        return itemsBySku;
    }

    public String[] getHeader() {
        return header;
    }

    public String getFilenameString() {
        return filenameString;
    }

    public String getPathString() {
        return pathString;
    }

    // Unit Test
    public static void main(String[] args) throws IOException {

        final String pathString = ".";
        final String filename = "test-catalog.csv";
        
        LibraryCSVReader reader = new LibraryCSVReader(filename, pathString);
        HashMap<String, LibraryItem> items = reader.getItemsBySku();
        int count = items.size();
        if (count == 14) {
            System.out.println("Succes: "+filename+" loaded successfully");
            System.exit(0);
        }
        System.out.println("Error reading "+filename+": Expected 5 items and saw "+count);
        System.exit(1);
    }
}