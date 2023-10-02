
import java.lang.String;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class ItemCountsMerge {

   HashMap<String, String> itemCountsBySku;
   HashMap<String, LibraryItem>  libraryItemsBySku;
   InventoryCSVReader inventoryReader;
   LibraryCSVReader libraryReader;

   public ItemCountsMerge(String catalogFilename, String inventoryFilename, String pathString) {
      inventoryReader = new InventoryCSVReader(inventoryFilename, pathString);
      itemCountsBySku = inventoryReader.getCountsBySku();
      libraryReader = new LibraryCSVReader(catalogFilename, pathString);
      libraryItemsBySku = libraryReader.getItemsBySku();

   }

   public void updateLibraryWithInventoryCounts() {
      for (Map.Entry<String, String> entry : itemCountsBySku.entrySet()) {
         LibraryItem item = libraryItemsBySku.get(entry.getKey());
         item.setQuantity(entry.getValue());
      }
   }

   public void removeItemsNotInInventory() {
      List<String> removeThese = new ArrayList<String>();
      for (Map.Entry<String, LibraryItem> entry : libraryItemsBySku.entrySet()){
         String key = entry.getKey();
         if (!itemCountsBySku.containsKey(key)) {
            removeThese.add(key);
         }
      }

      Iterator<String> it = removeThese.iterator();
      while (it.hasNext()) {
         String key = it.next();
         libraryItemsBySku.remove(key);
      }
   }

   public void writeLibraryFile() {
      String[] header = libraryReader.getHeader();
      String filename = libraryReader.getFilenameString();
      String pathString = libraryReader.getPathString();
      LibraryCSVWriter writer = new LibraryCSVWriter(header, filename, pathString);
      writer.writeFile(libraryItemsBySku);
   }

   public void printItemCounts() {
      // for (Map.Entry<String, String> entry : itemCountsBySku.entrySet()) {
      //     System.out.println(entry.getKey() + " : " + entry.getValue());
      // }  

      System.out.println("Size of inventory :"+itemCountsBySku.size());
      System.out.println("Size of Catalog : "+libraryItemsBySku.size());
   }

   public static void main(String[] args) {
		
      if (args.length != 3) {
         System.err.println("Usage: ItemCountsMerge FilePath LibraryCatalogFilename InventoryCountsFilename");
         System.exit(1);
      }
   
      for(int i = 0; i< args.length; i++) {
         System.out.println(String.format("Command Line Argument %d is %s", i, args[i]));
      }  

      ItemCountsMerge merge = new ItemCountsMerge("catalog-2023-09-08-2336.csv", "7September2023-01-combined-itemcounts.csv", ".");

      merge.updateLibraryWithInventoryCounts();
      merge.removeItemsNotInInventory();
      merge.printItemCounts();
      merge.writeLibraryFile();
      
	}
}