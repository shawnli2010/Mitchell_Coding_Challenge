import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class R2D2JunitTest{
    
    private R2D2Service service;

    @Before
    public void setUp() {
        this.service = new R2D2Service();
    }

    @After
    public void tearDown() {
        service = null;
    }

    @Test
    public void testCreate() {
        System.out.println("Testing Create");

        List<Vehicle> expect = new ArrayList<Vehicle>();
        List<Vehicle> actual;

        Vehicle v1 = new Vehicle(1960, "T-65B", "X-wing", "Starfighter");
        Vehicle v2 = new Vehicle(1965, "T-65C-A2", "X-wing", "Starfighter");

        expect.add(v1);
        service.Create(v1);

        expect.add(v2);
        service.Create(v2);

        actual = service.Get();

        Vehicle[] expectArray = expect.toArray(new Vehicle[0]);
        Vehicle[] actualArray = actual.toArray(new Vehicle[0]);

        assertArrayEquals(expectArray, actualArray);
    }

    @Test
    public void testDelete() {
        System.out.println("Testing Delete: ");

        List<Vehicle> expect = new ArrayList<Vehicle>();
        List<Vehicle> actual;

        Vehicle v1 = new Vehicle(1960, "T-65B", "X-wing", "Starfighter");
        Vehicle v2 = new Vehicle(1965, "T-65C-A2", "X-wing", "Starfighter");
        Vehicle v3 = new Vehicle(1990, "T-70", "X-wing", "Starfighter");

        expect.add(v1);
        service.Create(v1);
        
        expect.add(v2);
        service.Create(v2);
        
        expect.add(v3);
        service.Create(v3);
        
        expect.remove(v2);
        service.Delete(v2);
        
        actual = service.Get();

        Vehicle[] expectArray = expect.toArray(new Vehicle[0]);
        Vehicle[] actualArray = actual.toArray(new Vehicle[0]);

        assertArrayEquals(expectArray, actualArray);
    }

    @Test
    public void testUpdate() {    
        System.out.println("Testing Update: ");

        Vehicle v1 = new Vehicle(1960, "T-65B", "X-wing", "Starfighter");
        service.Create(v1);

        int v1_id         = v1.getId();
        int old_year      = v1.getYear();
        String old_vclass = v1.getVClass();
        String new_model  = "T-95C";
        String new_make   = "W-wing";

        Vehicle new_v1 = new Vehicle(old_year, new_model, new_make, old_vclass);
        new_v1.setId(v1_id);

        service.Update(new_v1);

        Vehicle result_v1 = service.Get(v1_id);
        String result_model = result_v1.getModel();
        String result_make = result_v1.getMake();

        assertEquals(result_model, new_model);
        assertEquals(result_make, new_make);
    }

    @Test
    public void testIsValidate() {    
        System.out.println("Testing isValidVehicle:");

        // Invalid Vehicles
        Vehicle v5 = new Vehicle(1960, "", "Model5", "Class5");
        Vehicle v5n = new Vehicle(1960, null, "Model5", "Class5");
        Vehicle v6 = new Vehicle(1965, "Make6", "", "Class6");
        Vehicle v6n = new Vehicle(1965, "Make6", null, "Class6");
        Vehicle v7 = new Vehicle(1990, "Make7", "Model7", "");
        Vehicle v7n = new Vehicle(1990, "Make7", "Model7", null);
        Vehicle v8 = new Vehicle(1949, "Make8", "Model8", "Class8");
        Vehicle v9 = new Vehicle(2051, "Make9", "Model9", "Class9");

        service.Create(v5);
        service.Create(v5n);
        service.Create(v6);
        service.Create(v6n);
        service.Create(v7);
        service.Create(v7n);
        service.Create(v8);
        service.Create(v9);

        List<Vehicle> result = service.Get();

        assertFalse(result.size() != 0);
    }

    @Test
    public void testGetWithFilter() {    
        System.out.println("Testing GetWithFilter");

        Vehicle v1 = new Vehicle(1960, "T-65B", "W-wing", "Starfighter");
        Vehicle v2 = new Vehicle(1965, "T-65C-A2", "X-wing", "Starfighter");
        Vehicle v3 = new Vehicle(1990, "T-70", "Y-wing", "Starfighter");
        Vehicle v4 = new Vehicle(1991, "T-85", "X-wing", "Starfighter");
        Vehicle v5 = new Vehicle(1960, "T-65B", "W-wing", "Starfighter");
        Vehicle v6 = new Vehicle(1965, "T-65C-A2", "X-wing", "PlanetWalker");
        Vehicle v7 = new Vehicle(1990, "T-70", "X-wing", "PlanetWalker");
        Vehicle v8 = new Vehicle(1991, "T-85", "X-wing", "PlanetWalker");

        service.Create(v1);
        service.Create(v2);
        service.Create(v3);
        service.Create(v4);
        service.Create(v5);
        service.Create(v6);
        service.Create(v7);
        service.Create(v8);

        FilterQuery fq = new FilterQuery();

        int lowerBound = 1989;
        int upperBound = 1992;
        fq.setYearLowerBound(lowerBound);
        fq.setYearUpperBound(upperBound);

        String modelFilter = "X-wing";
        String vclassFilter = "PlanetWalker";
        fq.setModelFilter(modelFilter);
        fq.setVClassFilter(vclassFilter);

        List<Vehicle> filteredResult = service.GetWithFilter(fq);
        for(Vehicle v : filteredResult) {
            assertFalse(v.getYear() < lowerBound);
            assertFalse(v.getYear() > upperBound);
            assertEquals(v.getModel(), modelFilter);
            assertEquals(v.getVClass(), vclassFilter);
        }

    }    

}