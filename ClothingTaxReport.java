
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

public class ClothingTaxReport {

   HashMap<String, LibraryItem>  libraryItemsBySku;
   LibraryCSVReader libraryReader;
   HashMap<String, LibraryItem> taxedClothing;

   public ClothingTaxReport(String catalogFilename) {
      libraryReader = new LibraryCSVReader(catalogFilename);
      libraryItemsBySku = libraryReader.getItemsBySku();
      taxedClothing = new HashMap<String, LibraryItem>();
   }

   public void identifyTaxedClothing() {
      for (Map.Entry<String, LibraryItem> entry : libraryItemsBySku.entrySet()){
         LibraryItem item = entry.getValue();
         String skuString = entry.getKey();
         if ((item.getCategory().equals("CLOTHING")) && (item.getTax().equals("Y"))) {
            taxedClothing.put(skuString, item);
         }
      }
   }

   public void writeReport() {
      String[] header = libraryReader.getHeader();
      String filename = libraryReader.getFilenameString();
      LibraryCSVWriter writer = new LibraryCSVWriter(header, filename);
      writer.writeFile(taxedClothing);
   }

   public static void main(String[] args) {
		
      //if (args.length != 2) {
      //   System.err.println("Usage: ItemCountsMerge LibraryCatalogFilename InventoryCountsFilename");
      //   System.exit(1);
      //}
   
      for(int i = 0; i< args.length; i++) {
         System.out.println(String.format("Command Line Argument %d is %s", i, args[i]));
      }  

      ClothingTaxReport report = new ClothingTaxReport("./catalog-2023-09-28-2053.csv");

      report.identifyTaxedClothing();
      report.writeReport();
      
	}
}