import java.lang.StringBuilder;
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

    @Override // Force the compiler to check I'm really overriding something
    public boolean equals(Object other) {
        Vehicle v_object = (Vehicle)other;
        boolean id_cond    = (this.Id == v_object.getId());
        boolean year_cond  = (this.Year == v_object.getYear());
        boolean make_cond  = (this.Make.equals(v_object.getMake()));
        boolean model_cond = (this.Model.equals(v_object.getModel()));
        boolean class_cond = (this.Class.equals(v_object.getVClass()));

        return id_cond && year_cond && make_cond && model_cond && class_cond;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(this.Id);
        sb.append(", ");

        sb.append("year: ");
        sb.append(this.Year);
        sb.append(", ");

        sb.append("make: ");
        sb.append(this.Make);
        sb.append(", ");

        sb.append("model: ");
        sb.append(this.Model);
        sb.append(", ");

        sb.append("class: ");
        sb.append(this.Class);
        sb.append(", ");

        return sb.toString();
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