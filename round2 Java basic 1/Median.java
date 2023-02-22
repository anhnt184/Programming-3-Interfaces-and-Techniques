import java.util.ArrayList;
import java.util.Collections;

public class Median {
    public static void main(String[] args) {
        ArrayList<Double> sets = new ArrayList<Double>();
        for(int i = 0; i < args.length; i++){
            double d = Double.parseDouble(args[i]);
            sets.add(d);
        }
        Collections.sort(sets);
        
        double middle = 0;
        //Mode 2
        if (sets.size()%2 == 0) {
           middle = (sets.get(sets.size()/2) + sets.get(sets.size()/2 - 1))/2;
        } else {
            middle = sets.get(sets.size() / 2);
        }
        System.out.println("Median: " + middle);
    }
}
