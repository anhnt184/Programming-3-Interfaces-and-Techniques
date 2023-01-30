public class Attainment {
    private String courseCode;
    private String studentNumber;
    private int grade;

    public Attainment(String courseCode, String studentNumber, int grade) {
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    public String getCourseCode() {
        //Get course code
        return this.courseCode;
    }

    public String getStudentNumber() {
        return this.studentNumber;
    }

    public int getGrade() {
        return this.grade;
    }
}