import java.util.Objects;

// student class represents a student by name, id and grade
public class Student implements Comparable<Student> {
    
    private String name;
    private String id;
    private int grade;
    
    // constructor
    public Student(String name,  String id, int grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    // the compare method for student. it performs comparison on the grade field
    @Override
    public int compareTo(Student student) {
    	if(this.grade == student.grade)
    		return 0;
        return (this.grade > student.grade )?1:-1;
    }

    @Override
    public String toString() {
        return "name: " + name + ", id: " + id + ", grade: " + grade;
    }

 // the equal method for student. it performs check equal on all students fields
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		return grade == other.grade && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
    

}