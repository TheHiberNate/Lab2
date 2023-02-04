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

    // lowTraffic
    northAndSouthArrowLight, northAndSouthGreenLight, northAndSouthYellowLight,
    northAndSouthRedLight, westAndEastYellowLight,

    // moderateTraffic
    northAndLeftArrowGreenMod, northYellowMod, northRedMod, southYellowMod, southRedMod, westAndEastYellowMod,

    // highTraffic
    northGreenAndArrowHigh, northYellowHigh, SouthGreenAndArrowHigh, southYellowHigh, westGreenAndArrowHigh,
    westAndEastGreenHigh, westAndEastYellowHigh
  }

  private Status status;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------
  private TrafficLightManager trafficLightManager;
  private String trafficCondition;

  public TrafficLight(TrafficLightManager trafficLightManager, String trafficCondidtion) {
    this.trafficLightManager = trafficLightManager;
    this.trafficCondition = trafficCondidtion;

    if (trafficCondition.equals("lowTraffic")) {
      setStatus(Status.northAndSouthArrowLight); // initial state for low traffic
    } else if (trafficCondition.equals("moderateTraffic")) {
      setStatus(Status.northAndLeftArrowGreenMod); // initial state for moderate traffic
    } else if (trafficCondition.equals("highTraffic")) {
      setStatus(Status.northGreenAndArrowHigh); // initial state for high traffic
    } else {
      setStatus(Status.northAndSouthGreen); // default system state (no specified traffic)
    }

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
        if (this.trafficCondition.equals("lowTraffic")) {
          lowTraffic();
        } else if (this.trafficCondition.equals("moderateTraffic")) {
          moderateTraffic();
        } else if(this.trafficCondition.equals("highTraffic")){
          highTraffic();
        }

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
        if (this.trafficCondition.equals("lowTraffic")) {
          lowTraffic();
        } else if (this.trafficCondition.equals("moderateTraffic")) {
          moderateTraffic();
        } else if(this.trafficCondition.equals("highTraffic")){
          highTraffic();
        }
    }

    return wasEventProcessed;
  }

  @Override
  public boolean moderateTraffic() {
    boolean wasEventProcessed = false;

    Status aStatus = status;
    switch (aStatus) {
      case northAndLeftArrowGreenMod:
        setStatus(Status.northYellowMod);
        wasEventProcessed = true;
        break;
      case northYellowMod:
        setStatus(Status.northRedMod);
        wasEventProcessed = true;
        break;
      case northRedMod:
        setStatus(Status.southYellowMod);
        wasEventProcessed = true;
        break;
      case southYellowMod:
        setStatus(Status.southRedMod);
        wasEventProcessed = true;
        break;
      case southRedMod:
        setStatus(Status.westAndEastYellowMod);
        wasEventProcessed = true;
        break;
      case westAndEastYellowMod:
        setStatus(Status.northAndLeftArrowGreenMod);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  @Override
  public boolean lowTraffic() {
    boolean wasEventProcessed = false;

    Status aStatus = status;
    switch (aStatus) {
      case northAndSouthArrowLight:
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
        setStatus(Status.northAndSouthArrowLight);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  @Override
  public boolean highTraffic() {
    boolean wasEventProcessed = false;

    Status aStatus = status;
    switch (aStatus) {
      case northGreenAndArrowHigh:
        setStatus(Status.northYellowHigh);
        wasEventProcessed = true;
        break;
      case northYellowHigh:
        setStatus(Status.SouthGreenAndArrowHigh);
        wasEventProcessed = true;
        break;
      case SouthGreenAndArrowHigh:
        setStatus(Status.southYellowHigh);
        wasEventProcessed = true;
        break;
      case southYellowHigh:
        setStatus(Status.westGreenAndArrowHigh);
        wasEventProcessed = true;
        break;
      case westGreenAndArrowHigh:
        setStatus(Status.westAndEastGreenHigh);
        wasEventProcessed = true;
        break;
      case westAndEastGreenHigh:
        setStatus(Status.westAndEastYellowHigh);
        wasEventProcessed = true;
        break;
      case westAndEastYellowHigh:
        setStatus(Status.northGreenAndArrowHigh);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStatus(Status aStatus) {
    status = aStatus;

    // entry actions and do activities
    switch (status) {
      // Default case (no traffic)
      case northAndSouthGreen:
        // line 8 "model.ump"
        trafficLightManager.northGreen();
        // line 9 "model.ump"
        trafficLightManager.southGreen();
        // line 10 "model.ump"
        trafficLightManager.westRed();
        // line 11 "model.ump"
        trafficLightManager.eastRed();
        break;
      case northAndSouthYellow:
        // line 16 "model.ump"
        trafficLightManager.northYellow();
        // line 17 "model.ump"
        trafficLightManager.southYellow();
        // line 18 "model.ump"
        trafficLightManager.westRed();
        // line 19 "model.ump"
        trafficLightManager.eastRed();
        break;
      case northAndSouthRed:
        // line 24 "model.ump"
        trafficLightManager.northRed();
        // line 25 "model.ump"
        trafficLightManager.southRed();
        // line 26 "model.ump"
        trafficLightManager.westGreen();
        // line 27 "model.ump"
        trafficLightManager.eastGreen();
        break;
      case westAndEastYellow:
        // line 32 "model.ump"
        trafficLightManager.northRed();
        // line 33 "model.ump"
        trafficLightManager.southRed();
        // line 34 "model.ump"
        trafficLightManager.westYellow();
        // line 35 "model.ump"
        trafficLightManager.eastYellow();
        break;

      // low
      // case____________________________________________________________________
      case northAndSouthArrowLight:
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

      // Moderate traffic
      // cases____________________________________________________________________
      case northAndLeftArrowGreenMod:
        // line 6 "model.ump"
        trafficLightManager.northGreenAndArrow();
        // line 7 "model.ump"
        trafficLightManager.southRed();
        // line 8 "model.ump"
        trafficLightManager.westRed();
        // line 9 "model.ump"
        trafficLightManager.eastRed();
        break;
      case northYellowMod:
        // line 13 "model.ump"
        trafficLightManager.northYellow();
        // line 14 "model.ump"
        trafficLightManager.southRed();
        // line 15 "model.ump"
        trafficLightManager.westRed();
        // line 16 "model.ump"
        trafficLightManager.eastRed();
        break;
      case northRedMod:
        // line 20 "model.ump"
        trafficLightManager.northRed();
        // line 21 "model.ump"
        trafficLightManager.southGreenAndArrow();
        // line 22 "model.ump"
        trafficLightManager.westRed();
        // line 23 "model.ump"
        trafficLightManager.eastRed();
        break;
      case southYellowMod:
        // line 27 "model.ump"
        trafficLightManager.northRed();
        // line 28 "model.ump"
        trafficLightManager.southYellow();
        // line 29 "model.ump"
        trafficLightManager.westRed();
        // line 30 "model.ump"
        trafficLightManager.eastRed();
        break;
      case southRedMod:
        // line 34 "model.ump"
        trafficLightManager.northRed();
        // line 35 "model.ump"
        trafficLightManager.southRed();
        // line 36 "model.ump"
        trafficLightManager.westGreen();
        // line 37 "model.ump"
        trafficLightManager.eastGreen();
        break;
      case westAndEastYellowMod:
        // line 41 "model.ump"
        trafficLightManager.northRed();
        // line 42 "model.ump"
        trafficLightManager.southRed();
        // line 43 "model.ump"
        trafficLightManager.westYellow();
        // line 44 "model.ump"
        trafficLightManager.eastYellow();
        break;

      // High traffic
      // cases____________________________________________________________________
      case northGreenAndArrowHigh:
        // line 6 "model.ump"
        trafficLightManager.northGreenAndArrow();
        // line 7 "model.ump"
        trafficLightManager.southRed();
        // line 8 "model.ump"
        trafficLightManager.westRed();
        // line 9 "model.ump"
        trafficLightManager.eastRed();
        break;
      case northYellowHigh:
        // line 13 "model.ump"
        trafficLightManager.northYellow();
        // line 14 "model.ump"
        trafficLightManager.southRed();
        // line 15 "model.ump"
        trafficLightManager.westRed();
        // line 16 "model.ump"
        trafficLightManager.eastRed();
        break;
      case SouthGreenAndArrowHigh:
        // line 20 "model.ump"
        trafficLightManager.northRed();
        // line 21 "model.ump"
        trafficLightManager.southGreenAndArrow();
        // line 22 "model.ump"
        trafficLightManager.westRed();
        // line 23 "model.ump"
        trafficLightManager.eastRed();
        break;
      case southYellowHigh:
        // line 27 "model.ump"
        trafficLightManager.northRed();
        // line 28 "model.ump"
        trafficLightManager.southYellow();
        // line 29 "model.ump"
        trafficLightManager.westRed();
        // line 30 "model.ump"
        trafficLightManager.eastRed();
        break;
      case westGreenAndArrowHigh:
        // line 34 "model.ump"
        trafficLightManager.northRed();
        // line 35 "model.ump"
        trafficLightManager.southRed();
        // line 36 "model.ump"
        trafficLightManager.westGreenAndArrow();
        // line 37 "model.ump"
        trafficLightManager.eastRed();
        break;
      case westAndEastGreenHigh:
        // line 41 "model.ump"
        trafficLightManager.northRed();
        // line 42 "model.ump"
        trafficLightManager.southRed();
        // line 43 "model.ump"
        trafficLightManager.westGreen();
        // line 44 "model.ump"
        trafficLightManager.eastGreen();
        break;
      case westAndEastYellowHigh:
        // line 48 "model.ump"
        trafficLightManager.northRed();
        // line 49 "model.ump"
        trafficLightManager.southRed();
        // line 50 "model.ump"
        trafficLightManager.westYellow();
        // line 51 "model.ump"
        trafficLightManager.eastYellow();
        break;
    }
  }

  public void delete() {
  }

}