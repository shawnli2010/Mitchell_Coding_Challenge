/*
 * FilterQuery:
 * This class is used to wrap the condition information when
 * the client wants to specify a filter on its query to the 
 * list of Vehicles in R2D2Service
 */
public class FilterQuery {
    private int[] year_range;
    private String make;
    private String model;
    private String vclass;

    public FilterQuery() {
        this.year_range = new int[2];
        this.year_range[0] = Integer.MAX_VALUE;
        this.year_range[0] = Integer.MIN_VALUE;

        this.make  = "";
        this.model = "";
        this.vclass = "";
    }

    public int[] getYearRange() {
        return this.year_range;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVClass() {
        return vclass;
    }

    public void setYearLowerBound(int lower) {
        this.year_range[0] = lower;
    }

    public void setYearUpperBound(int upper) {
        this.year_range[1] = upper;
    }

    public void setMakeFilter(String make) {
        this.make = make;
    }

    public void setModelFilter(String model) {
        this.model = model;
    }

    public void setVClassFilter(String vclass) {
        this.vclass = vclass;
    }
}