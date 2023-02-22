package fi.tuni.prog3.jsoncountries;
import java.text.DecimalFormat;
/**
 *
 * @author Tuan Anh Nguyen <anh.5.nguyen@tuni.fi>
 */



public class Country implements Comparable<Country> {
    private String name;
    private double area;
    private long population;
    private double gdp;

    public Country(String name, double area, long population, double gdp) {
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }
    
    public long getPopulation() {
        return population;
    }

    public double getGdp() {
        return gdp;
    }

    @Override
    public int compareTo(Country other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
    DecimalFormat df = new DecimalFormat("#.0");
    StringBuilder sb = new StringBuilder();
    sb.append(name).append("\n");
    sb.append("  Area: ").append(df.format(area)).append(" km2\n");
    sb.append("  Population: ").append(population).append("\n");
    sb.append("  GDP: ").append(df.format(gdp)).append(" (2015 USD)\n");
    return sb.toString();
    }
}


