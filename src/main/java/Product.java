public class Product implements Item{

  private int id;
  private ItemType type;
  private String manufacturer;
  private String name;

  public Product(String name, String manufacturer, ItemType type){
    this.id = id;
    this.type = type;
    this.manufacturer = manufacturer;
    this.name = name;
  }

  public String toString(){
    return "Name: " + getName() + "\nManufacturer: " + getManufacturer() + "\nType: " + type.code;
  }

  public int getId(){
    return id;
  }

  public void setName(String name){}

  public String getName(){
    return name;
  }

  public void setManufacturer(String manufacturer){}

  public String getManufacturer(){
    return manufacturer;
  }

}