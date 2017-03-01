/*
 * Vehicle model class
 */
public class Vehicle{
    private int Id;
    private int Year;
    private String Make;
    private String Model;
    private String Class;

    public Vehicle(int Year, String Model, String Make, String Class) {
        this.Year = Year;
        this.Make = Make;
        this.Model = Model;
        this.Class = Class;
    }

    // Setter and getter methods for all the attributes of Vehicle class
    public int getId() {
        return this.Id;
    }

    public Vehicle setId(int Id) {
        this.Id = Id;
        return this;
    }

    public int getYear() {
        return this.Year;
    }

    public Vehicle setYear(int Year) {
        this.Year = Year;
        return this;
    }

    public String getMake() {
        return this.Make;
    }

    public Vehicle setMake(String Make) {
        this.Make = Make;
        return this;
    }

    public String getModel() {
        return this.Model;
    }

    public Vehicle setModel(String Model) {
        this.Model = Model;
        return this;
    }

    public String getVClass() {
        return this.Class;
    }

    public Vehicle setVClass(String Class) {
        this.Class = Class;
        return this;
    }
}