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

public class LibraryCSVReader {
    private static final String SAMPLE_CSV_FILE_PATH = "./test-catalog.csv";
    String filenameString;
    String[] header;
    HashMap<String, LibraryItem> itemsBySku;

    public LibraryCSVReader(String filenameString) {
        this.filenameString = filenameString;
        this.itemsBySku = new HashMap<String, LibraryItem>();
        readInventoryFile(filenameString);
    }

    public String readInventoryFile(String filename) {
        String headerLine = new String();
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
                System.out.println("Found Library Header\n"+ Arrays.toString(header));
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

    // Unit Test
    public static void main(String[] args) throws IOException {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            // Token,Item Name,Variation Name,SKU,Description,Category,GTIN,Square Online Item Visibility,
            // Weight (lb),Price,Sellable,Stockable,Option Name 1,Option Value 1,Option Name 2,Option Value 2,
            // Option Name 3,Option Value 3,Default Unit Cost,Default Vendor Name,Default Vendor Code,
            // Current Quantity Femmebot Fashion,New Quantity Femmebot Fashion,Stock Alert Enabled Femmebot Fashion,
            // Stock Alert Count Femmebot Fashion,Tax - NJ Sales Tax (6.625%)

            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                String token = csvRecord.get(0);
                String itemName = csvRecord.get(1);
                String variationName = csvRecord.get(2);
                String sku = csvRecord.get(3);
                String desc = csvRecord.get(4);
                String category = csvRecord.get(5);
                String gtin = csvRecord.get(6);
                String itemVis = csvRecord.get(7);
                String weight = csvRecord.get(8);
                String price = csvRecord.get(9);
                String sellable = csvRecord.get(10);
                String stockable = csvRecord.get(11);
                String optName1 = csvRecord.get(12);
                String optVal1 = csvRecord.get(13);
                String optName2 = csvRecord.get(14);
                String optVal2 = csvRecord.get(15);
                String optName3 = csvRecord.get(16);
                String optVal3 = csvRecord.get(17);
                String defUnitCost = csvRecord.get(18);
                String defVendorName = csvRecord.get(19);
                String defVendorCode = csvRecord.get(20);
                String quantity = csvRecord.get(21);
                String newQuantity = csvRecord.get(22);
                String alertEnable = csvRecord.get(23);
                String alertCount = csvRecord.get(24);
                String tax = csvRecord.get(25);


                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Token : " + token);
                System.out.println("Item Name : " + itemName);
                System.out.println("Variation Name : " + variationName);
                System.out.println("SKU : " + sku);
                System.out.println("Description : " + desc);
                System.out.println("Category : " + category);
                System.out.println("GTIN : " + gtin);
                System.out.println("Weight : " + weight);
                System.out.println("Item Visibility : " + itemVis);
                System.out.println("Price : " + price);
                System.out.println("Sellable : " + sellable);
                System.out.println("Stockable : " + stockable);
                System.out.println("Option Name 1 : " + optName1);
                System.out.println("Option Value 1 : " + optVal1);
                System.out.println("Option Name 2 : " + optName2);
                System.out.println("Option Value 2 : " + optVal2);
                System.out.println("Option Name 3 : " + optName3);
                System.out.println("Option Value 3 : " + optVal3);
                System.out.println("Default Unt Cost : " + defUnitCost);
                System.out.println("Default Vender Name : " + defVendorName);
                System.out.println("Default Vender Code : " + defVendorCode);
                System.out.println("Quantity : " + quantity);
                System.out.println("New Quantity : " + newQuantity);
                System.out.println("Alert Enabled : " + alertEnable);
                System.out.println("Alert Count : " + alertCount);
                System.out.println("Tax : " + tax);
                System.out.println("---------------\n\n");
            }
        } 
    }
}