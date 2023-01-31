package SEG2106.core;

public class Main {

	public static void main(String [] args){
		// get the singleton object of the traffic light manager
		TrafficLightManager trafficLightManager = TrafficLightManager.getTrafficManager();
		
		String trafficCondition = "none";

		if (args.length > 0) {
			trafficCondition = args[0];
		} 
		// Create the traffic light state machine
		new TrafficLight(trafficLightManager, trafficCondition);
		
		// Set the traffic condition
		if (args.length > 0){
			trafficLightManager.setTrafficCondition(args[0]);
		}

	}
}
