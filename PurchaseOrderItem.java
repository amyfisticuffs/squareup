public class PurchaseOrderItem {
    
    String itemName;
    String variationName;
    String skuString;
    String gtin;
    String vendorCode;
    String notes;
    String qty;
    String unitCost;

    public PurchaseOrderItem() {
        itemName = new String();
        variationName = new String();
        skuString = new String();
        gtin = new String();
        vendorCode = new String();
        notes = new String();
        qty = new String();
        unitCost = new String();
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getVariationName() {
        return variationName;
    }
    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }
    public String getSkuString() {
        return skuString;
    }
    public void setSkuString(String skuString) {
        this.skuString = skuString;
    }
    public String getGtin() {
        return gtin;
    }
    public void setGtin(String gtin) {
        this.gtin = gtin;
    }
    public String getVendorCode() {
        return vendorCode;
    }
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getQty() {
        return qty;
    }
    public void setQty(String qty) {
        this.qty = qty;
    }
    public String getUnitCost() {
        return unitCost;
    }
    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }

}
