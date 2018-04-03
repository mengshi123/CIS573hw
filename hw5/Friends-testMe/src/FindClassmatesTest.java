import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class FindClassmatesTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Case: Student is null
	 */
	@Test
	public void testNullStudent() {
		
		Student Yuchong = new Student("Yuchong", "3") ;
		ClassesDataSource cds = new ClassesDataSource() {
			public List<String> getClasses(String studentName){
				List<String> myClassesList = new ArrayList<String>();
				myClassesList.add("CIS 573");
				return  myClassesList;
			}
		} ;
		StudentsDataSource  sds = new StudentsDataSource() {
			public List<Student> getStudents(String myClass){
				List<Student> myClassMatesList = new ArrayList<Student>();
				myClassMatesList.add(Yuchong);
				return  myClassMatesList;
			}
		} ;
		FriendFinder ff = new FriendFinder(cds, sds) ;
		
		Student Meng = null;

		try {
			ff.findClassmates(Meng);
			fail("Student is null");
		} catch (IllegalArgumentException ecp) {
		}
	}
	
	/**
	 * Case: ClassDataSource is null
	 */
	@Test
	public void testNullClassDataSource() {

		Student Yuchong = new Student("Yuchong", "3") ;
		StudentsDataSource  sds = new StudentsDataSource() {
			public List<Student> getStudents(String myClass){
				List<Student> myClassMatesList = new ArrayList<Student>();
				myClassMatesList.add(Yuchong);
				return  myClassMatesList;
			}
		} ;
		FriendFinder ff = new FriendFinder(null, sds) ;

		Student Meng = new Student("Meng", "1") ;

		try {
			ff.findClassmates(Meng);
			fail("ClassDataSource is null");
		} catch (IllegalStateException ecp) {
		}
	}
	
	/**
	 * Case: StudentDataSource is null
	 */
	@Test
	public void testNullStudentDataSource() {
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName){
				List<String> myClassesList = new ArrayList<String>();
				myClassesList.add("CIS 573");
				return  myClassesList;
			}
		} ;
		FriendFinder ff = new FriendFinder(cds, null) ;
		Student Meng = new Student("Meng", "1") ;

		try {
			ff.findClassmates(Meng);
			fail("StudentDataSource is null");
		} catch (IllegalStateException ecp) {
		}
	}

	/**
	 * Case: My Name is null
	 */
	@Test
	public void testNullName() {
		
		Student Yuchong = new Student("Yuchong", "3");
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName){
				List<String> myClassesList = new ArrayList<String>();
				myClassesList.add("CIS 573");
				return  myClassesList;
			}
		} ;
		StudentsDataSource  sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass){
				List<Student> myClassMatesList = new ArrayList<Student>();
				myClassMatesList.add(Yuchong);
				return  myClassMatesList;
			}
		} ;
		FriendFinder ff = new FriendFinder(cds, sds) ;
		Student Meng = new Student(null, "1") ;
		
		try {
			ff.findClassmates(Meng);
			fail("Student's name is null");
		} catch (IllegalArgumentException ecp) {
		}
	}

	/**
	 * Case: My class is null
	 */
	@Test
	public void testMyClassIsNull() {
		
		Student Yuchong = new Student("Yuchong", "3");
		Student Tiancheng = new Student("Tiancheng", "2");
		Student Meng = new Student("Meng", "1");
		
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName) {
				List<String> myClassesList = new ArrayList<String>();
				if (studentName.equals("Tiancheng")) {
					myClassesList.add("CIS573");
					myClassesList.add("CIS502");
				} else if (studentName.equals("Yuchong")) {
					myClassesList.add("CIS573");
					myClassesList.add("CIS500");
				} else if (studentName.equals("Meng")) {
				}
				return myClassesList;
			}
		};
		StudentsDataSource sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass) {
		
				List<Student> myClassMatesList = new ArrayList<Student>();
				if (myClass.equals("CIS573")) {
					myClassMatesList.add(Yuchong);
					myClassMatesList.add(Tiancheng);
				} else if (myClass.equals("CIS500")) {
					myClassMatesList.add(Yuchong);
				} else if (myClass.equals("CIS502")) {
					myClassMatesList.add(Tiancheng);
				}
				return myClassMatesList;
			}
		};
		FriendFinder ff = new FriendFinder(cds, sds);

		Set<String> result = ff.findClassmates(Meng);
		assertEquals(result, null);
	}
	
	/**
	 * Case: My class contains null
	 */
	@Test
	public void testMyClassContainsNull() {
		
		Student Yuchong = new Student("Yuchong", "3");
		Student Tiancheng = new Student("Tiancheng", "2");
		Student Meng = new Student("Meng", "1");
		
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName) {
				List<String> myClassesList = new ArrayList<String>();
				if (studentName.equals("Tiancheng")) {
					myClassesList.add("CIS573");
					myClassesList.add("CIS502");
				} else if (studentName.equals("Yuchong")) {
					myClassesList.add("CIS573");
					myClassesList.add("CIS500");
				} else if (studentName.equals("Meng")) {
					myClassesList.add("CIS573");
					String Null = null;
					myClassesList.add(Null);
					myClassesList.add("CIS500");
				}
				return myClassesList;
			}
		};
		StudentsDataSource sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass) {
		
				List<Student> myClassMatesList = new ArrayList<Student>();
				if (myClass.equals("CIS573")) {
					myClassMatesList.add(Yuchong);
					myClassMatesList.add(Tiancheng);
				} else if (myClass.equals("CIS500")) {
					myClassMatesList.add(Yuchong);
				} else if (myClass.equals("CIS502")) {
					myClassMatesList.add(Tiancheng);
				}
				return myClassMatesList;
			}
		};
		FriendFinder ff = new FriendFinder(cds, sds);

		Set<String> result = ff.findClassmates(Meng);
		assertTrue(result.contains("Yuchong"));
		assertTrue(result.size() == 1);
	}
	/**
	 * Case: My classmate'name contains null
	 */
	@Test
	public void testMyClassmateNameContainsNull() {
		Student Yuchong = new Student("Yuchong", "3");
		Student Tiancheng = new Student("Tiancheng", "2");
		Student Meng = new Student("Meng", "1");
		
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName) {
				List<String> myClassesList = new ArrayList<String>();
				if (studentName.equals("Tiancheng")) {
					myClassesList.add("CIS573");
					myClassesList.add("CIS502");
				} else if (studentName.equals("Yuchong")) {
					myClassesList.add("CIS573");
					myClassesList.add("CIS500");
				} else if (studentName.equals("Meng")) {
					myClassesList.add("CIS573");
					myClassesList.add("CIS500");
				}
				return myClassesList;
			}
		};
		StudentsDataSource sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass) {
		
				List<Student> myClassMatesList = new ArrayList<Student>();
				if (myClass.equals("CIS573")) {
					myClassMatesList.add(Yuchong);
					myClassMatesList.add(Tiancheng);
					myClassMatesList.add(Meng);
				} else if (myClass.equals("CIS500")) {
					myClassMatesList.add(Yuchong);
					myClassMatesList.add(Tiancheng);
				} else if (myClass.equals("CIS502")) {
					myClassMatesList.add(Tiancheng);
				}
				return myClassMatesList;
			}
		};
		FriendFinder ff = new FriendFinder(cds, sds);
		Set<String> result = ff.findClassmates(Meng);
		assertTrue(result.contains("Yuchong"));
		assertTrue(result.size()==1);
	}
	
	/**
	 * Case: My classmate contains null
	 */
	@Test
	public void testMyClassmateContainsNull() {
		Student Yuchong = null;
		Student Tiancheng = new Student("Tiancheng", "2");
		Student Meng = new Student("Meng", "1");
		
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName) {
				List<String> myClassesList = new ArrayList<String>();
				if (studentName.equals("Tiancheng")) {
					myClassesList.add("CIS573");
					myClassesList.add("CIS502");
				}else if (studentName.equals("Meng")) {
					myClassesList.add("CIS573");
					myClassesList.add("CIS500");
				}
				return myClassesList;
			}
		};
		StudentsDataSource sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass) {
		
				List<Student> myClassMatesList = new ArrayList<Student>();
				if (myClass.equals("CIS573")) {
					myClassMatesList.add(Yuchong);
					myClassMatesList.add(Tiancheng);
					myClassMatesList.add(Meng);
				} else if (myClass.equals("CIS500")) {
					myClassMatesList.add(Yuchong);
					myClassMatesList.add(Tiancheng);
				} else if (myClass.equals("CIS502")) {
					myClassMatesList.add(Tiancheng);
				}
				return myClassMatesList;
			}
		};
		FriendFinder ff = new FriendFinder(cds, sds);
		Set<String> result = ff.findClassmates(Meng);
		assertEquals(result,null);
	}
	
	/**
	 * Case: TheirClasses contains null
	 */
	@Test
	public void testTheirClassesContainsNull() {
		Student Yuchong = new Student("Yuchong", "3");
		Student Tiancheng = new Student("Tiancheng", "2");
		Student Meng = new Student("Meng", "1");
		
		ClassesDataSource cds = new ClassesDataSource() {
			public java.util.List<String> getClasses(String studentName) {
				List<String> myClassesList = new ArrayList<String>();
				if (studentName.equals("Tiancheng")) {
					
				} else if (studentName.equals("Yuchong")) {
					myClassesList.add("CIS573");
					myClassesList.add("CIS500");
				} else if (studentName.equals("Meng")) {
					myClassesList.add("CIS573");
					myClassesList.add("CIS500");
				}
				return myClassesList;
			}
		};
		StudentsDataSource sds = new StudentsDataSource() {
			public java.util.List<Student> getStudents(String myClass) {
		
				List<Student> myClassMatesList = new ArrayList<Student>();
				if (myClass.equals("CIS573")) {
					myClassMatesList.add(Yuchong);
					myClassMatesList.add(Meng);
				} else if (myClass.equals("CIS500")) {
					myClassMatesList.add(Yuchong);
					myClassMatesList.add(Tiancheng);
				} else if (myClass.equals("CIS502")) {
				}
				return myClassMatesList;
			}
		};
		FriendFinder ff = new FriendFinder(cds, sds);
		Set<String> result = ff.findClassmates(Meng);
		assertTrue(result.contains("Yuchong"));
		assertTrue(result.size()==1);

	}
}

