public class Circle implements IShapeMetrics {
    private double radius;
  
    public Circle(double radius) {
      this.radius = radius;
    }
  
    @Override
    public String toString() {
      return "Circle with radius: " + String.format("%.2f", radius);
    }
  
    @Override
    public String name() {
      return "circle";
    }
  
    @Override
    public double area() {
      return PI * radius * radius;
    }
  
    @Override
    public double circumference() {
      return 2 * PI * radius;
    }
  }