import java.util.TreeSet;

public class Parameters {
    public static void main(String[] args) {
      TreeSet<String> treeSet = new TreeSet<>();
      int lastRow = 0;
      int widthofSecondcol = 0;
      int widthofFirstcol = 0;
      int widthofTabl = 0;
      String outerBorder = "#";
      StringBuilder verticalBorder = new StringBuilder("#");
        for(String arg : args) {
          treeSet.add(arg);
          lastRow += 1;
          if (widthofSecondcol<arg.length()) {
            widthofSecondcol = arg.length();
          }
        }
        widthofSecondcol += 1;
        widthofFirstcol = String.valueOf(lastRow).length()+1;
        //Count width of Table
        widthofTabl = widthofFirstcol + widthofSecondcol;
        outerBorder = "#".repeat(widthofTabl+5);
        verticalBorder.append("-".repeat(widthofFirstcol));
        verticalBorder.append("-+-");
        verticalBorder.append("-".repeat(widthofSecondcol));
        verticalBorder.append("#");
        System.out.println(outerBorder);
        for(String s : treeSet) {
          int idex = treeSet.headSet(s).size();
          System.out.println("#" + String.format("%"+widthofFirstcol+"d",idex+1) + " | " + String.format("%-"+widthofSecondcol+"s",s)+"#");
          if (lastRow != idex+1) {
            System.out.println(verticalBorder);
        }
        }
        System.out.println(outerBorder);
    }
}