//------------------------------------------
//  Name: Macie Ryan
//Course: COP 3003
//  File: Product.java
//------------------------------------------
public class Product implements Item{

  int id;
  ItemType type;
  String manufacturer;
  String name;

  public Product(String name, String manufacturer, ItemType type){
    this.type = type;
    this.manufacturer = manufacturer;
    this.name = name;
  }

  public Product(int id, String name, String manufacturer, ItemType type){
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  public String toString(){
    return "Name: " + getName() + "\nManufacturer: " + getManufacturer() + "\nType: " + type.code;
  }

  public void setType(ItemType type){
    this.type = type;
  }

  public ItemType getType(){
    return type;
  }

  public void setId(){
    this.id = id;
  }

  public int getId(){
    return id;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public void setManufacturer(String manufacturer){
    this.manufacturer = manufacturer;
  }

  public String getManufacturer(){
    return manufacturer;
  }

}

class Widget extends Product{
  Widget(String name, String manufacturer, ItemType type){
    super(name, manufacturer, type);
  }
}