public class Student implements Comparable<Student>{
    private int studentId;
    private String studentName;
    private String studentMajor;

    Student(){
        studentId = 0;
        studentName = "";
        studentMajor = "";
    }

    Student(int studentId){
        this.studentId = studentId;
        studentName = "";
        studentMajor = "";
    }

    Student(int id, String name, String major) throws Exception{
        setStudentId(id);
        setStudentName(name);
        setStudentMajor(major);
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName(){
        return studentName;
    }

    public String getStudentMajor(){
        return studentMajor;
    }

    public void setStudentId(int studentId) throws Exception{
        if(studentId <=0){
            throw new Exception("The student ID must be greater than 0");
        }else{
            this.studentId = studentId;
        }
    }

    public void setStudentName(String studentName) throws Exception{
        if(studentName.length() == 0){
            throw new Exception("The student name cannot be left blank.");
        }else{
            this.studentName = studentName;
        }
    }

    public void setStudentMajor(String studentMajor) throws Exception{
        if (studentMajor.length() == 0){
            this.studentMajor = "Undecided";
        }else if(studentMajor.length() == 3){
            studentMajor = studentMajor.toUpperCase();
            this.studentMajor = studentMajor;
        }else{
            throw new Exception("The student's major must be 3 characters long.");
        }
    }

    @Override
    public int compareTo(Student o) {
        return studentId - o.studentId;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %s",getStudentId(),getStudentName(),getStudentMajor());
    }
}
