import java.util.*;
import java.lang.*;

/*
 * Utility class to test and print for R2D2Service implementation
 */
public class TestUtil {
    R2D2Service service;

    public TestUtil(R2D2Service service) {
        this.service = service;
    }

    /************************ Printing Methods ************************/
    public void printAllVehicles() {
        System.out.println("All Vehicles in memory store: ");

        for(Vehicle vehicle : service.Get()) {
            printOneVehicle(vehicle);
        }
    }

    public void printFromResult(List<Vehicle> vehicles) {
        System.out.println("Vehicles in result: ");        

        for(Vehicle vehicle : vehicles) {
            printOneVehicle(vehicle);
        }
    }

    public void printOneVehicle(Vehicle vehicle) {
        int id = vehicle.getId();
        int year = vehicle.getYear();
        String make = vehicle.getMake();
        String model = vehicle.getModel();
        String vclass = vehicle.getVClass();

        String output = String.format("Id: %d, Year: %d, Make: %s, Model: %s, Class: %s"
                                     , id, year, make, model, vclass);
        System.out.println(output);
    }

    /************************ Comparing Methods ************************/
    public boolean isTwoListsEqual(List<Vehicle> expected, List<Vehicle> actual){
        for(Vehicle vehicle : expected) {
            if(!actual.contains(vehicle)) {
                return false;
            }
        }

        return true;
    }
}