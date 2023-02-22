import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentRegister {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Attainment> attainments;

    public StudentRegister() {
        students = new ArrayList<Student>();
        courses = new ArrayList<Course>();
        attainments = new ArrayList<Attainment>();
    }

    public ArrayList<Student> getStudents() {
        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
        return students;
    }

    public ArrayList<Course> getCourses() {
        Collections.sort(courses, new Comparator<Course>() {
            public int compare(Course c1, Course c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
        return courses;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addAttainment(Attainment att) {
        attainments.add(att);
    }

    public void printStudentAttainments(String studentNumber, String order) {
        // Find the student with the specified student number
        Student student = null;
        List<Tuple> studentCourses = new ArrayList<>();
        ArrayList<Attainment> studentAttainments = new ArrayList<Attainment>();
        for (Student s : students) {
            if (s.getStudentNumber().equals(studentNumber)) {
                 student = s;
                 break;
             }
         }
        if (student == null) {
             System.out.println("Unknown student number: " + studentNumber);
             return;
         }
         System.out.println(student.getName() + " (" + student.getStudentNumber() + "):");

         for (Attainment attainment : attainments) {
            if (attainment.getStudentNumber().equals(studentNumber)) {
                String courseCode = attainment.getCourseCode();
                int grade = attainment.getGrade();
                for (Course course : courses) {
                    if (course.getCode().equals(courseCode)) {
                        studentCourses.add(new Tuple(course, grade));
                        break;
                    }
                }
            }
        }
       // Sort by the order
           if (order.equals("by name")) {
             studentCourses.sort(Comparator.comparing(t -> t.course.getName()));
           } else if (order.equals("by code")) {
             studentCourses.sort(Comparator.comparing(t -> t.course.getCode()));
            }
        // Print the results
         for (Tuple tuple : studentCourses) {
            Course course = tuple.course;
            int grade = tuple.grade;
            System.out.println("  " +course.getCode() + " " + course.getName() + ": " + grade);
         }
    }
    public void printStudentAttainments(String studentNumber) {
        printStudentAttainments(studentNumber, "default");
    }
    public class Tuple {
        Course course;
        int grade;
    
        public Tuple(Course course, int grade) {
            this.course = course;
            this.grade = grade;
        }
    }
}