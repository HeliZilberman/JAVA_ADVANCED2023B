import java.util.Iterator;

public class Main {

	//actions on sorted group
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortedGroup<Student> students = newStudentsGroup();
		printStudents(students);
		removeAndPrint(students,new Student("Drogo","092637845",60));
		printStudents(students);
		SortedGroup<Student> above = GenericMathods.reduce(students,new Student("","",60));
		printStudents(above);
	}
	
	//creates a sorted group of students
	private static SortedGroup<Student> newStudentsGroup(){
		SortedGroup<Student> students = new SortedGroup<Student>();
		students.add(new Student("Bran","678534829",79));
        students.add(new Student("John","9284657368",40));
        students.add(new Student("Arya","304763524",75));
        students.add(new Student("Drogo","092637845",60));
        students.add(new Student("Halisi","678534829",100));
        students.add(new Student("Arya","304763524",75));
        students.add(new Student("Drogo","092637845",60));
        students.add(new Student("Devos","254377192",61));
        students.add(new Student("Drogo","092637845",60));
        students.add(new Student("Drogo","092637845",60));
        students.add(new Student("Drogo","092637845",60));
        students.add(new Student("Sansa","87543564",96));
        students.add(new Student("Bran","678534829",79));
        students.add(new Student("Halisi","678534829",100));
        students.add(new Student("John","9284657368",40));
        students.add(new Student("Tyrell","9284657368",55));
        return students;
    }
	//prints sorted group
	private static void printStudents(SortedGroup<Student> students) {
		System.out.println("Students");
		Iterator<Student> it = students.iterator();
		while(it.hasNext()) 
			System.out.println(it.next());
	}
	//removes student and prints number of times 
	private static void removeAndPrint(SortedGroup<Student> students ,Student s) {
		System.out.println("number of times student: " +s + " was in SortedGroup is: " + students.remove(s));
	}
}
