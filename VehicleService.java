import java.util.*;

/*
 * VehicleService CRUD interface, required by the picky R2-D2
 */
public interface VehicleService {
    public List<Vehicle> Get();
    public Vehicle Get(int Id);
    public void Create(Vehicle vehicle);
    public void Update(Vehicle vehicle);
    public void Delete(Vehicle vehicle);
}