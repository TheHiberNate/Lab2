package SEG2106.core;
/*PLEASE DO NOT EDIT THIS CODE*/

/*This code was generated using the UMPLE 1.19.0.3369 modeling language!*/

// line 1 "model.ump"
public class TrafficLight implements EventHandler {
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile {
    int[] line();

    String[] file();

    int[] javaline();

    int[] length();
  }

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // TrafficLight State Machines
  public enum Status {
    // default 
    northAndSouthGreen, northAndSouthYellow, northAndSouthRed, westAndEastYellow,
    
    // lightTraffic
    northAndSouthGreenAndArrowLight, northAndSouthGreenLight, northAndSouthYellowLight, 
    northAndSouthRedLight, westAndEastYellowLight
  }

  private Status status;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------
  private TrafficLightManager trafficLightManager;

  public TrafficLight(TrafficLightManager trafficLightManager) {
    this.trafficLightManager = trafficLightManager;

    setStatus(Status.northAndSouthGreenAndArrowLight); // set status to green light for north/south lights

    trafficLightManager.addEventHandler(this);
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public String getStatusFullName() {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus() {
    return status;
  }

  public boolean timerGreen() { // want to switch either from north and south to green or from green to red
    boolean wasEventProcessed = false;

    Status aStatus = status;
    switch (aStatus) {
      case northAndSouthGreen:
        setStatus(Status.northAndSouthYellow);
        wasEventProcessed = true;
        break;
      case northAndSouthRed:
        setStatus(Status.westAndEastYellow);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
        lowTraffic();
    }

    return wasEventProcessed;
  }

  public boolean timerYellow() {
    boolean wasEventProcessed = false;

    Status aStatus = status;
    switch (aStatus) {
      case northAndSouthYellow:
        setStatus(Status.northAndSouthRed);
        wasEventProcessed = true;
        break;
      case westAndEastYellow:
        setStatus(Status.northAndSouthGreen);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
        lowTraffic();
    }

    return wasEventProcessed;
  }


  @Override
  public boolean moderateTraffic() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean lowTraffic() {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case northAndSouthGreenAndArrowLight:
        setStatus(Status.northAndSouthGreenLight);
        wasEventProcessed = true;
        break;
      case northAndSouthGreenLight:
        setStatus(Status.northAndSouthYellowLight);
        wasEventProcessed = true;
        break;
      case northAndSouthYellowLight:
        setStatus(Status.northAndSouthRedLight);
        wasEventProcessed = true;
        break;
      case northAndSouthRedLight:
        setStatus(Status.westAndEastYellowLight);
        wasEventProcessed = true;
        break;
      case westAndEastYellowLight:
        setStatus(Status.northAndSouthGreenAndArrowLight);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  @Override
  public boolean highTraffic() {
    // TODO Auto-generated method stub
    return false;
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case northAndSouthGreenAndArrowLight:
        trafficLightManager.northArrow();
        trafficLightManager.southArrow();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
      case northAndSouthGreenLight:
        trafficLightManager.northGreen();
        trafficLightManager.southGreen();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
      case northAndSouthYellowLight:
        trafficLightManager.northYellow();
        trafficLightManager.southYellow();
        trafficLightManager.westRed();
        trafficLightManager.eastRed();
        break;
      case northAndSouthRedLight:
        trafficLightManager.northRed();
        trafficLightManager.southRed();
        trafficLightManager.westGreen();
        trafficLightManager.eastGreen();
        break;
      case westAndEastYellowLight:
        trafficLightManager.northRed();
        trafficLightManager.southRed();
        trafficLightManager.westYellow();
        trafficLightManager.eastYellow();
        break;
    }
  }


  public void delete() {
  }

}