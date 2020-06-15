
public final class Student {

	private final int marks;
	private final String name;

	public Student(int marks, String name) {
		super();
		this.marks = marks;
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public String getName() {
		return name;
	}

//	@Override
//	public int compareTo(Student o) {
//
//		if (this.marks == o.marks) {
//			return this.name.compareTo(o.name);
//		}
//
//		return this.marks - o.marks;
//
//	}

	@Override
	public String toString() {
		return "Student [marks=" + marks + ", name=" + name + "]";
	}

}
