import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 *
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;

	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		return students;
	}

	@Override
	public void setStudents(Student[] students) {
		if (students == null)
            throw new IllegalArgumentException("students cannot be null");

        this.students = students;
	}

	@Override
	public Student getStudent(int index) {
        if (index < 0)
            throw new IllegalArgumentException("Index cannot be less than 0");

        if (index >= students.length)
            throw new IllegalArgumentException("Index cannot be greater than " + students.length);

		return students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
        if (student == null)
            throw new IllegalArgumentException("student cannot be null");

        if (index < 0)
            throw new IllegalArgumentException("Index cannot be less than 0");

        if (index >= students.length)
            throw new IllegalArgumentException("Index cannot be greater than " + students.length);

        this.students[index] = student;
	}

	@Override
	public void addFirst(Student student) {
        if (student == null)
            throw new IllegalArgumentException("student cannot be null");

		// Resize the array first
        Student[] newArray = new Student[students.length + 1];
        System.arraycopy(students, 0, newArray, 1, students.length);

        newArray[0] = student;
        this.students = newArray;
	}

	@Override
	public void addLast(Student student) {
        if (student == null)
            throw new IllegalArgumentException("student cannot be null");

		// Resize the array first
        Student[] newArray = new Student[students.length + 1];
        System.arraycopy(students, 0, newArray, 0, students.length);

        newArray[students.length] = student;
        this.students = newArray;
	}

	@Override
	public void add(Student student, int index) {
        if (student == null)
            throw new IllegalArgumentException("student cannot be null");

        if (index < 0)
            throw new IllegalArgumentException("Index cannot be less than 0");

        if (index >= students.length)
            throw new IllegalArgumentException("Index cannot be greater than " + students.length);

        // Resize the array first
        Student[] newArray = new Student[students.length + 1];

        System.arraycopy(students, 0, newArray, 0, index);
        System.arraycopy(students, index, newArray, index + 1, students.length - index);

        newArray[index] = student;
        this.students = newArray;
	}

	@Override
	public void remove(int index) {
        if (index < 0)
            throw new IllegalArgumentException("Index cannot be less than 0");

        if (index >= students.length)
            throw new IllegalArgumentException("Index cannot be greater than " + students.length);

        Student[] newArray = new Student[students.length - 1];

        System.arraycopy(students, 0, newArray, 0, index);
        System.arraycopy(students, index, newArray, index - 1, students.length - index);

        this.students = newArray;
	}

	@Override
	public void remove(Student student) {
		if (student == null)
            throw new IllegalArgumentException("student cannot be null");

        for (int i = 0; i < students.length; i++)
            if (student.getId() == students[i].getId())
            {
                remove(i);
                return;
            }

        throw new IllegalArgumentException("Student not exist");
	}

	@Override
	public void removeFromIndex(int index) {
        if (index < 0)
            throw new IllegalArgumentException("Index cannot be less than 0");

        if (index >= students.length)
            throw new IllegalArgumentException("Index cannot be greater than " + students.length);

        Student[] newArray = new Student[students.length - index - 1];
        System.arraycopy(students, 0, newArray, 0, newArray.length);

        students = newArray;
	}

	@Override
	public void removeFromElement(Student student) {
        if (student == null)
            throw new IllegalArgumentException("student cannot be null");

        for (int i = 0; i < students.length; i++)
            if (student.getId() == students[i].getId())
            {
                removeFromIndex(i);
                return;
            }
	}

	@Override
	public void removeToIndex(int index) {
        if (index < 0)
            throw new IllegalArgumentException("Index cannot be less than 0");

        if (index >= students.length)
            throw new IllegalArgumentException("Index cannot be greater than " + students.length);

        Student[] newArray = new Student[students.length - index - 1];
        System.arraycopy(students, index, newArray, 0, newArray.length);

        students = newArray;
	}

	@Override
	public void removeToElement(Student student) {
        if (student == null)
            throw new IllegalArgumentException("student cannot be null");

        for (int i = 0; i < students.length; i++)
            if (student.getId() == students[i].getId())
            {
                removeToIndex(i);
                return;
            }
	}

	@Override
	public void bubbleSort() {
		for (int i = 0; i < students.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                Student ith = students[i];
                Student jth = students[j];

                if (jth.compareTo(ith) > 0)
                {
                    students[j] = ith;
                    students[i] = jth;
                }
            }
        }
	}

	@Override
	public Student[] getByBirthDate(Date date) {
        if (date == null)
            throw new IllegalArgumentException("date cannot be null");

        List<Student> datedStudents = new ArrayList<Student>();

        for (int i = 0; i < students.length; i++)
            if (students[i].getBirthDate().compareTo(date) == 0)
                datedStudents.add(students[i]);

		return datedStudents.toArray(new Student[datedStudents.size()]);
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
        if (firstDate == null || lastDate == null)
            throw new IllegalArgumentException("date cannot be null");

        List<Student> datedStudents = new ArrayList<Student>();

        for (int i = 0; i < students.length; i++)
        {
            Date birthDate = students[i].getBirthDate();

            if (birthDate.after(firstDate) && birthDate.before(lastDate))
                datedStudents.add(students[i]);
        }

		return datedStudents.toArray(new Student[datedStudents.size()]);
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
        if (date == null)
            throw new IllegalArgumentException("date cannot be null");

        List<Student> datedStudents = new ArrayList<Student>();

        for (int i = 0; i < students.length; i++)
        {
            Date birthDate = students[i].getBirthDate();
            long time = Math.abs(birthDate.getTime() - date.getTime());

            if (TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS) <= days)
                datedStudents.add(students[i]);
        }

		return datedStudents.toArray(new Student[datedStudents.size()]);
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
        if (indexOfStudent == 0)
            throw new IllegalArgumentException("indexOfStudent cannot be 0");

		return students[indexOfStudent].getAge();
	}

	@Override
	public Student[] getStudentsByAge(int age) {
        List<Student> agedStudents = new ArrayList<Student>();

        for (int i = 0; i < students.length; i++)
            if (students[i].getAge() == age)
                agedStudents.add(students[i]);

		return agedStudents.toArray(new Student[agedStudents.size()]);
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
        double maxAvgMark = 0;

        for (int i = 0; i < students.length; i++)
            maxAvgMark = Math.max(maxAvgMark, students[i].getAvgMark());

        List<Student> maxedStudents = new ArrayList<Student>();

        for (int i = 0; i < students.length; i++)
            if (students[i].getAvgMark() == maxAvgMark)
                maxedStudents.add(students[i]);

		return maxedStudents.toArray(new Student[maxedStudents.size()]);
	}

	@Override
	public Student getNextStudent(Student student) {
		// Add your implementation here
		return null;
	}
}
