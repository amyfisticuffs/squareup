
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

public class ClothingTaxReport {

   HashMap<String, LibraryItem>  libraryItemsBySku;
   LibraryCSVReader libraryReader;
   HashMap<String, LibraryItem> taxedClothing;

   public ClothingTaxReport(String catalogFilename, String pathString) {
      libraryReader = new LibraryCSVReader(catalogFilename, pathString);
      libraryItemsBySku = libraryReader.getItemsBySku();
      System.out.println("Found "+libraryItemsBySku.size()+" in "+catalogFilename);
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
      System.out.println("Total number of taxed clothing items: "+taxedClothing.size());
   }

   public void writeReport() {
      String[] header = libraryReader.getHeader();
      String filename = libraryReader.getFilenameString();
      String pathString = libraryReader.getPathString();
      LibraryCSVWriter writer = new LibraryCSVWriter(header, filename, pathString);
      writer.writeFile(taxedClothing);
   }

   public static void main(String[] args) {
		
      if (args.length != 2) {
         System.err.println("Usage: ItemCountsMerge LibraryCatalogFilename LibraryCatalogPath");
         System.exit(1);
      }
   
      for(int i = 0; i< args.length; i++) {
         System.out.println(String.format("Command Line Argument %d is %s", i, args[i]));
      }  

      ClothingTaxReport report = new ClothingTaxReport(args[0], args[1]);

      report.identifyTaxedClothing();
      report.writeReport();
      
	}
}