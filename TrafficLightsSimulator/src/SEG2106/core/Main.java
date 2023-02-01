package SEG2106.core;

public class Main {

	public static void main(String [] args){
		// get the singleton object of the traffic light manager
		TrafficLightManager trafficLightManager = TrafficLightManager.getTrafficManager();
		
		// String trafficCondition = "none";

		// if (args.length > 0) {
		// 	trafficCondition = args[0];
		// } 
		// // Create the traffic light state machine
		// new TrafficLight(trafficLightManager, trafficCondition);
		
		// // Set the traffic condition
		// if (args.length > 0){
		// 	trafficLightManager.setTrafficCondition(args[0]);
		// }

		//Modifications to main in order to remove bug
		if (args.length > 0){
			String trafCondition = args[0];
			new TrafficLight(trafficLightManager,trafCondition);
		}else{
			new TrafficLight(trafficLightManager,"null");
		}

	}
}
