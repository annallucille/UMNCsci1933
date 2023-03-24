import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class VehicleSorter {
	public static void main(String[] args) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();

		Car c1 = new Car(20.0);
		Car c2 = new Car(40.0);
		Car c3 = new Car(15.0);
		Train t1 = new Train(380.0);
		Train t2 = new Train(490.0);
		Train t3 = new Train(290.0);


		vehicles.add(c1);
		vehicles.add(c2);
		vehicles.add(c3);
		vehicles.add(t2);
		vehicles.add(t1);
		vehicles.add(t3);




		System.out.println("Total Number of Vehicles: " + Vehicle.getNumVehicles());
		Collections.sort(vehicles);
		for (Vehicle v : vehicles) {
			System.out.println(v);
		}
	}
}
