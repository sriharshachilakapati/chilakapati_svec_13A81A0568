import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) throws Exception {

		//You may test that your code works find here
		//Please check that your code works and has no
		//compilation problems before to submit

        System.out.println("Hello World");

        StudentGroup group = new StudentGroup(5);
        Student[] students = new Student[] {
            new Student(1, "SHC", getDate("1995-12-14"), 64),
            new Student(2, "AKH", getDate("1995-12-14"), 64),
            new Student(3, "STC", getDate("1998-09-25"), 84),
        };

        group.setStudents(students);

        Student[] dated = group.getByBirthDate(getDate("1995-12-14"));

        for (Student s : dated)
            System.out.println(s.getFullName());

        System.out.println(students[0].getAge());
	}

    private static Date getDate(String dt) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dt);
    }

}
