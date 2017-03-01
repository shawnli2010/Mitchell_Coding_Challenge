import java.util.*;
import java.lang.*;

/*
 * Concrete implementation of VehicleService CRUD API
 */
public class R2D2Service implements VehicleService {
    // In memory persistance of created vehicle objects
    private List<Vehicle> vehicleList;

    // HashMap: Id -> Vehicle, to improve the speed of 
    // getting a vehicle
    private Map<Integer, Vehicle> idToVehicleMap;

    // To determine what new id should be assigned for the next
    // created Vehicle object
    private int idCounter;

    public R2D2Service() {
        vehicleList = new ArrayList<Vehicle>();       
        idToVehicleMap = new HashMap<Integer, Vehicle>(); 
        idCounter = 0;
    }

    /*
     * Get():
     * return a list of Vehicle objects, which represents all
     * the currently existing vehicle objects stored in memory persistance
     */
    public List<Vehicle> Get(){
        return vehicleList;
    }

    /*
     * GetWithFilter(FilterQuery fq):
     * given a FilterQuery object which specifies the condition,
     * it will return all the vehicles that satisfy the condition
     */
    public List<Vehicle> GetWithFilter(FilterQuery fq) {
        List<Vehicle> result = new ArrayList<Vehicle>();

        int[] year_range    = fq.getYearRange();
        String make_query   = fq.getMake();
        String model_query  = fq.getModel();
        String vclass_query = fq.getVClass();

        for(Vehicle vehicle : vehicleList) {
            int year      = vehicle.getYear();
            String make   = vehicle.getMake();
            String model  = vehicle.getModel();
            String vclass = vehicle.getVClass();

            boolean year_cond   = (year >= year_range[0]) && (year <= year_range[1]);
            boolean make_cond   = (make_query.length() == 0) || (make.equals(make_query));
            boolean model_cond  = (model_query.length() == 0) || (model.equals(model_query));
            boolean vclass_cond = (vclass_query.length() == 0) || (vclass.equals(vclass_query));

            if(year_cond && make_cond && model_cond && vclass_cond) {
                result.add(vehicle);
            }
        }

        return result;
    }

    /*
     * Get(int Id):
     * return a single Vehicle object with a given Id.
     * Client is supposed to pass in a valid vehicle id; 
     * Otherwise an exception will be thrown
     */
    public Vehicle Get(int Id) throws VehicleNotFoundException{
        if(idToVehicleMap.containsKey(Id)) {
            return idToVehicleMap.get(Id);
        } else {
            String exceptionMessage = String.format("No vehicle is found with id: %d", Id);
            throw new VehicleNotFoundException(exceptionMessage);
        }
    }

    /*
     * Create(Vehicle vehicle): 
     * Insert a new vehicle into the in memory persistance.
     */
    public void Create(Vehicle vehicle){
        if(!isValidVehicle(vehicle)) {
            System.err.println("Failed to create: the vehicle is not valid");
            return;
        }

        // Assign a new id to the vehicle
        vehicle.setId(++idCounter);
        
        // Add the vehicle to both the list and the map
        vehicleList.add(vehicle);
        idToVehicleMap.put(vehicle.getId(), vehicle);
    }

    /*
     * Update(Vehicle vehicle): 
     * replace the existing vehicle object with a newly 
     * passed in vehicle object.
     */
    public void Update(Vehicle vehicle){
        if(!isInMemoryStore(vehicle)) {
            System.err.println("Failed to update: vehicle does not exist");
            return;
        }

        if(!isValidVehicle(vehicle)) {
            System.err.println("Failed to update: the vehicle is not valid");
            return;
        }

        // Delete(vehicle) will delete the old vehicle that 
        // has the same id as vehicle from the memory store
        Delete(vehicle);
        
        // add the new vehicle to the list and the hashmap
        vehicleList.add(vehicle);
        idToVehicleMap.put(vehicle.getId(), vehicle);
    }

    /*
     * Delete(Vehicle vehicle):
     * delete an existing vehicle from in memeory store 
     */
    public void Delete(Vehicle vehicle){
        if(!isInMemoryStore(vehicle)) {
            System.err.println("Failed to delete: vehicle does not exist");
            return;
        }

        // retrive the old vehicle with the same id with
        // the newly passed in vehicle, and then remove the 
        // old vehicle from the list and the hashmap
        int id = vehicle.getId();
        Vehicle old_vehicle = Get(id);
        
        vehicleList.remove(old_vehicle);
        idToVehicleMap.remove(id);
    }

    /*
     * Clear():
     * delete all the vehicles from the memeory store
     */
    public void Clear() {
        vehicleList.clear();
        idToVehicleMap.clear();
    }

    /*
     * isInMemoryStore(Vehicle vehicle): 
     * helper method to check whether a vehicle exists
     * in the memory store or not, if it does not find 
     * the vehicle, it will throw an exception
     */
    private boolean isInMemoryStore(Vehicle vehicle) {
        try {
            Get(vehicle.getId());
            return true;
        }
        catch (VehicleNotFoundException e) {
            return false;
        }
    }

    /*
     * isValidVehicle(Vehicle vehicle): 
     * helper method to validate a vehicle to check
     * 1. The passed in vehicle object has 
     *    non-null / non-empty make, model and class specified
     * 2. The passed in vehicle object has the year
     *    between 1950 and 2050.
     */
    private boolean isValidVehicle(Vehicle vehicle) {
        boolean make_cond  = (vehicle.getMake() != null) && (vehicle.getMake().length() != 0);
        boolean model_cond = (vehicle.getModel() != null) && (vehicle.getModel().length() != 0);
        boolean class_cond = (vehicle.getVClass() != null) && (vehicle.getVClass().length() != 0);
        boolean year_cond  = (vehicle.getYear() >= 1950) && (vehicle.getYear() <= 2050);

        return make_cond && model_cond && class_cond && year_cond;
    }

    /*
     * Customized exception class:
     * this exception should be thrown when the client is trying
     * to retrive a vehicle that does not exist
     */
    class VehicleNotFoundException extends RuntimeException {
        public VehicleNotFoundException(String message) {
            super(message);
        }
    }
}