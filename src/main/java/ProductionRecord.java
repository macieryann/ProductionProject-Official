import java.util.Date;

public class ProductionRecord {
  int productionNumber;
  int productID;
  String serialNumber;
  Date dateProduced;
  int num;
  String serial;

  public ProductionRecord(int productID){
    this.productID = productID;
    this.serialNumber = "0";
    this.productionNumber = 0;
    this.dateProduced = new Date();

  }

  public ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced){
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date(dateProduced.getTime());

  }

  public ProductionRecord(Product product, int productionAmt){
    this.serialNumber = createSerialNumber(product.getManufacturer(), product, productionAmt);
  }

  public static String createSerialNumber(String manufacturer, Product product, int productionAmt){
    return manufacturer.substring(0,2).toUpperCase()
        + product.getType()
        + String.format("%05d", productionAmt);
  }

  public void setProductionNum(int num){
    productionNumber = num;
  }

  public int getProductionNum(){
    return productionNumber;
  }

  public void setProductID(int num){
    productID = num;
  }

  public int getProductID(){
    return productID;
  }

  public void setSerialNum(String serial){
    serialNumber = serial;
  }

  public String getSerialNum(){
    return serialNumber;
  }

  public void setProdDate(Date dateProduced){
    this.dateProduced = new Date(dateProduced.getTime());
  }

  public Date getProdDate(){
    return new Date(dateProduced.getTime());
  }

  public String toString(){
    return "Prod. Num: " + getProductionNum()
        + " Product ID: " + getProductID()
        + " Serial Num: " + getSerialNum()
        + " Date: " + getProdDate();
  }

}