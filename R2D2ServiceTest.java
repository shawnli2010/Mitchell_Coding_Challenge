import java.util.*;

/*
 * Tests for R2D2Service implementation
 */
public class R2D2ServiceTest {
    private static R2D2Service service;
    private static TestUtil util;
    
    private static void testCase1() {
        System.out.print("Test Case 1, Testing Create: ");
        service.Clear();

        List<Vehicle> expect = new ArrayList<Vehicle>();
        List<Vehicle> actual;

        Vehicle v1 = new Vehicle(1960, "T-65B", "X-wing", "Starfighter");
        Vehicle v2 = new Vehicle(1965, "T-65C-A2", "X-wing", "Starfighter");

        expect.add(v1);
        service.Create(v1);

        expect.add(v2);
        service.Create(v2);

        actual = service.Get();

        if (util.isTwoListsEqual(expect,actual)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }

    }

    private static void testCase2() {
        System.out.print("Test Case 2, Testing Delete: ");
        service.Clear();

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

        if (util.isTwoListsEqual(expect,actual)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }

    }

    private static void testCase3() {
        System.out.print("Test Case 3, Testing Update: ");
        service.Clear();

        Vehicle v1 = new Vehicle(1960, "T-65B", "X-wing", "Starfighter");
        service.Create(v1);

        int v1_id = v1.getId();
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

        if(result_model.equals(new_model) && result_make.equals(new_make)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }

    private static void testCase4() {
        System.out.print("Test Case 4, Testing isValidVehicle: \n");
        service.Clear();
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

        if(result.isEmpty()) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }

    private static void testCase5(){
        System.out.print("Test Case 5, Testing GetWithFilter: ");
        service.Clear();

        Vehicle v1 = new Vehicle(1960, "T-65B", "X-wing", "Starfighter");
        Vehicle v2 = new Vehicle(1965, "T-65C-A2", "X-wing", "Starfighter");
        Vehicle v3 = new Vehicle(1990, "T-70", "X-wing", "Starfighter");
        Vehicle v4 = new Vehicle(1991, "T-85", "X-wing", "Starfighter");

        service.Create(v1);
        service.Create(v2);
        service.Create(v3);
        service.Create(v4);

        FilterQuery fq = new FilterQuery();
        int lowerBound = 1989;
        int upperBound = 1992;
        fq.setYearLowerBound(lowerBound);
        fq.setYearUpperBound(upperBound);

        List<Vehicle> filteredResult = service.GetWithFilter(fq);
        for(Vehicle v : filteredResult) {
            if(v.getYear() < lowerBound || v.getYear() > upperBound) {
                System.out.println("FAIL");                
                break;
            }
        }

        System.out.println("PASS");
    }

    public static void main(String args[]) {
        service = new R2D2Service();
        util = new TestUtil(service);

        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
    }
}