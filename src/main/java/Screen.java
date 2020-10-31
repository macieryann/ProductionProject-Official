//------------------------------------------
//  Name: Macie Ryan
//Course: COP 3003
//  File: Screen.java
//------------------------------------------
public class Screen implements ScreenSpec{
  String resolution;
  int refreshRate;
  int responseTime;

  public Screen(){

  }

  public Screen(String resolution, int refreshRate, int responseTime){
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  public String getResolution(){
    return resolution;
  }

  public int getRefreshRate(){
    return refreshRate;
  }

  public int getResponseTime(){
    return responseTime;
  }

  public String toString(){
    return "Screen:" +
        "\nResolution: " + resolution +
        "\nRefresh rate: " + refreshRate +
        "\nResponse time: " + responseTime;
  }

}