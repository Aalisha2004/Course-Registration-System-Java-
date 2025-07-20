import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Class to represent a Course
class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolled;

    Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    boolean isFull() {
        return enrolled >= capacity;
    }

    void enrollStudent() {
        if (!isFull()) {
            enrolled++;
        }
    }

    void dropStudent() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + ", Title: " + title + ", Description: " + description +
               ", Capacity: " + capacity + ", Enrolled: " + enrolled;
    }
}

// Class to represent a Student
class Student {
    String studentID;
    String name;
    List<Course> registeredCourses;

    Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    void registerCourse(Course course) {
        if (!registeredCourses.contains(course) && !course.isFull()) {
            registeredCourses.add(course);
            course.enrollStudent();
        } else {
            System.out.println("Cannot register for this course.");
        }
    }

    void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name + ", Registered Courses: " + registeredCourses;
    }
}

public class CourseRegistrationSystem {
    private static Map<String, Course> courses = new HashMap<>();
    private static Map<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        // Initialize courses
        initializeCourses();

        // Initialize students
        initializeStudents();

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Display Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    registerForCourse(studentID, courseCode);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    dropCourse(studentID, courseCode);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void initializeCourses() {
        courses.put("CS101", new Course("CS101", "Introduction to Programming", "Learn the basics of programming.", 30));
        courses.put("CS102", new Course("CS102", "Data Structures", "Introduction to data structures.", 25));
        courses.put("CS103", new Course("CS103", "Algorithms", "Study algorithms and their complexities.", 20));
    }

    private static void initializeStudents() {
        students.put("S001", new Student("S001", "Alice"));
        students.put("S002", new Student("S002", "Bob"));
    }

    private static void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }

    private static void registerForCourse(String studentID, String courseCode) {
        Student student = students.get(studentID);
        Course course = courses.get(courseCode);

        if (student != null && course != null) {
            student.registerCourse(course);
            System.out.println("Registered successfully.");
        } else {
            System.out.println("Student or Course not found.");
        }
    }

    private static void dropCourse(String studentID, String courseCode) {
        Student student = students.get(studentID);
        Course course = courses.get(courseCode);

        if (student != null && course != null) {
            student.dropCourse(course);
            System.out.println("Dropped successfully.");
        } else {
            System.out.println("Student or Course not found.");
        }
    }
}
