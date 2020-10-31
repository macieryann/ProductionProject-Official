public enum MonitorType {
  LCD("LCD"),
  LED("LED");

  private final String code;

  MonitorType(String code) {
    this.code = code;
  }

  public String getCode(){
    return code;
  }
}