public class Mean {

     /**
      * @param args the command line arguments1
      */
    public static void main(String[] args) {
        double sum = 0;
        double mean =0;
        for(int i = 0; i < args.length; i++) {
            double d = Double.parseDouble(args[i]);
            sum += d;
        }
        mean = sum/args.length;
        System.out.println("Mean: " + mean);
    }
}
