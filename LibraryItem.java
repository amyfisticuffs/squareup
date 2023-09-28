
public class LibraryItem {
    int size;

    String token;
    String itemName;
    String variationName;
    String sku;
    String desc;
    String category;
    String gtin;
    String itemVis;
    String weight;
    String price;
    String sellable;
    String stockable;
    String optName1;
    String optVal1;
    String optName2;
    String optVal2;
    String optName3;
    String optVal3;
    String defUnitCost;
    String defVendorName;
    String defVendorCode;
    String quantity;
    String newQuantity;
    String alertEnable;
    String alertCount;
    String tax;

    public LibraryItem() {
        size = 26;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public void setItemVis(String itemVis) {
        this.itemVis = itemVis;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSellable(String sellable) {
        this.sellable = sellable;
    }

    public void setStockable(String stockable) {
        this.stockable = stockable;
    }

    public void setOptName1(String optName1) {
        this.optName1 = optName1;
    }

    public void setOptVal1(String optVal1) {
        this.optVal1 = optVal1;
    }

    public void setOptName2(String optName2) {
        this.optName2 = optName2;
    }

    public void setOptVal2(String optVal2) {
        this.optVal2 = optVal2;
    }

    public void setOptName3(String optName3) {
        this.optName3 = optName3;
    }

    public void setOptVal3(String optVal3) {
        this.optVal3 = optVal3;
    }

    public void setDefUnitCost(String defUnitCost) {
        this.defUnitCost = defUnitCost;
    }

    public void setDefVendorName(String defVendorName) {
        this.defVendorName = defVendorName;
    }

    public void setDefVendorCode(String defVendorCode) {
        this.defVendorCode = defVendorCode;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setNewQuantity(String newQuantity) {
        this.newQuantity = newQuantity;
    }

    public void setAlertEnable(String alertEnable) {
        this.alertEnable = alertEnable;
    }

    public void setAlertCount(String alertCount) {
        this.alertCount = alertCount;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }
    
    public String getToken() {
        return token;
    }

    public String getItemName() {
        return itemName;
    }

    public String getVariationName() {
        return variationName;
    }

    public String getSku() {
        return sku;
    }

    public String getDesc() {
        return desc;
    }

    public String getCategory() {
        return category;
    }

    public String getGtin() {
        return gtin;
    }

    public String getItemVis() {
        return itemVis;
    }

    public String getWeight() {
        return weight;
    }

    public String getPrice() {
        return price;
    }

    public String getSellable() {
        return sellable;
    }

    public String getStockable() {
        return stockable;
    }

    public String getOptName1() {
        return optName1;
    }

    public String getOptVal1() {
        return optVal1;
    }

    public String getOptName2() {
        return optName2;
    }

    public String getOptVal2() {
        return optVal2;
    }

    public String getOptName3() {
        return optName3;
    }

    public String getOptVal3() {
        return optVal3;
    }

    public String getDefUnitCost() {
        return defUnitCost;
    }

    public String getDefVendorName() {
        return defVendorName;
    }

    public String getDefVendorCode() {
        return defVendorCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getNewQuantity() {
        return newQuantity;
    }

    public String getAlertEnable() {
        return alertEnable;
    }

    public String getAlertCount() {
        return alertCount;
    }

    public String getTax() {
        return tax;
    }

    public int getSize() {
        return size;
    }

    public String[] toArray() {
        String[] item = new String[size];
        item[0] = token;
        item[1] = itemName;
        item[2] = variationName;
        item[3] = sku;
        item[4] = desc;
        item[5] = category;
        item[6] = gtin;
        item[7] = itemVis;
        item[8] = weight;
        item[9] = price;
        item[10] = sellable;
        item[11] = stockable;
        item[12] = optName1;
        item[13] = optVal1;
        item[14] = optName2;
        item[15] = optVal2;
        item[16] = optName3;
        item[17] = optVal3;
        item[18] = defUnitCost;
        item[19] = defVendorName;
        item[20] = defVendorCode;
        item[21] = quantity;
        item[22] = newQuantity;
        item[23] = alertEnable;
        item[24] = alertCount;
        item[25] = tax;

        return item;
    }

}
