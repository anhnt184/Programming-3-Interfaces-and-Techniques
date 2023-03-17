
package fi.tuni.prog3.junitattainment;
import java.util.Comparator;

/**
 *
 * @author Tuan Anh Nguyen <anh.5.nguyen@tuni.fi>
 */


public class Attainment implements Comparable<Attainment> {
    private String courseCode;
    private String studentNumber;
    private int grade;

    public Attainment(String courseCode, String studentNumber, int grade) {
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }
    // Get course code
    public String getCourseCode() {
        return courseCode;
    }
    // Get Student no
    public String getStudentNumber() {
        return studentNumber;
    }
    // Get Student grade
    public int getGrade() {
        return grade;
    }

    @Override
    public int compareTo(Attainment other) {
        int studentNumCompare = this.studentNumber.compareTo(other.studentNumber);
        if (studentNumCompare != 0) {
            return studentNumCompare;
        } else {
            return this.courseCode.compareTo(other.courseCode);
        }
    }

    @Override
    public String toString() {
        return courseCode + " " + studentNumber + " " + grade;
    }

    public static final Comparator<Attainment> CODE_STUDENT_CMP = new Comparator<Attainment>() {
        @Override
        public int compare(Attainment a1, Attainment a2) {
            int codeCompare = a1.courseCode.compareTo(a2.courseCode);
            if (codeCompare != 0) {
                return codeCompare;
            } else {
                return a1.studentNumber.compareTo(a2.studentNumber);
            }
        }
    };

    public static final Comparator<Attainment> CODE_GRADE_CMP = new Comparator<Attainment>() {
        @Override
        public int compare(Attainment a1, Attainment a2) {
            int codeCompare = a1.courseCode.compareTo(a2.courseCode);
            if (codeCompare != 0) {
                return codeCompare;
            } else {
                return Integer.compare(a2.grade, a1.grade);
            }
        }
    };
}