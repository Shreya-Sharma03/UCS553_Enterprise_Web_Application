import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
class Course{
    private String courseCode;
    private String courseName;
    private int capacity;
    private HashSet<String> enrolledStudentIds=new HashSet<>();
    private ArrayList<String> prerequisiteCodes=new ArrayList<>();
    private LinkedList<String> waitlist=new LinkedList<>();
    public Course(String courseCode, String courseName, int capacity){
        this.courseCode=courseCode;
        this.courseName=courseName;
        this.capacity=capacity;
    }
    public String getCourseCode(){
        return courseCode;
    }
    public String getCourseName(){
        return courseName;
    }
    public int getCapacity(){
        return capacity;
    }
    public HashSet<String> getEnrolledStudentIds(){
        return enrolledStudentIds;
    }
    public ArrayList<String> getPrerequisiteCodes(){
        return prerequisiteCodes;
    }
    public LinkedList<String> getWaitlist() {
        return waitlist;
    }

    public boolean isEnrolled(String studentId) {
        return enrolledStudentIds.contains(studentId);
    }

    public boolean isWaitlisted(String studentId) {
        return waitlist.contains(studentId);
    }

    public boolean hasAvailableSeat() {
        return enrolledStudentIds.size() < capacity;
    }

    public void enrollStudent(String studentId) {
        enrolledStudentIds.add(studentId);
    }

    public boolean dropEnrolledStudent(String studentId) {
        return enrolledStudentIds.remove(studentId);
    }

    public boolean addPrerequisite(String prerequisiteCode) {
        if (prerequisiteCodes.contains(prerequisiteCode)) {
            return false;
        }

        prerequisiteCodes.add(prerequisiteCode);
        return true;
    }

    public void addToWaitlist(String studentId) {
        waitlist.addLast(studentId);
    }

    public boolean removeFromWaitlist(String studentId) {
        return waitlist.remove(studentId);
    }

    public String promoteNextStudent() {
        return waitlist.pollFirst();
    }
}

public class CourseRegistrationPortal {

    private HashMap<String, Course> courses = new HashMap<>();

    // Student ID -> set of completed course codes
    private HashMap<String, HashSet<String>>
            completedCoursesByStudent = new HashMap<>();

    private String normalizeCode(String code) {
        return code.trim().toUpperCase();
    }

    public void addCourse(String code, String name, int capacity) {
        code = normalizeCode(code);
        name = name.trim();

        if (code.isEmpty() || name.isEmpty()) {
            System.out.println("Code and name cannot be empty.");
            return;
        }

        if (capacity <= 0) {
            System.out.println("Capacity must be positive.");
            return;
        }

        if (courses.containsKey(code)) {
            System.out.println("Course already exists.");
            return;
        }

        Course course = new Course(code, name, capacity);
        courses.put(code, course);

        System.out.println("Course added successfully.");
    }

    public void addPrerequisite(String courseCode,
                                String prerequisiteCode) {
        courseCode = normalizeCode(courseCode);
        prerequisiteCode = normalizeCode(prerequisiteCode);

        Course course = courses.get(courseCode);

        if (course == null || !courses.containsKey(prerequisiteCode)) {
            System.out.println("Course or prerequisite not found.");
            return;
        }

        if (courseCode.equals(prerequisiteCode)) {
            System.out.println("A course cannot require itself.");
            return;
        }

        if (course.addPrerequisite(prerequisiteCode)) {
            System.out.println("Prerequisite added successfully.");
        } else {
            System.out.println("Prerequisite already exists.");
        }
    }

    public void markCourseCompleted(String studentId, String courseCode) {
        studentId = studentId.trim();
        courseCode = normalizeCode(courseCode);

        Course course = courses.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (!course.isEnrolled(studentId)) {
            System.out.println("Student is not enrolled in this course.");
            return;
        }

        if (!completedCoursesByStudent.containsKey(studentId)) {
            completedCoursesByStudent.put(studentId, new HashSet<>());
        }

        HashSet<String> completed =
                completedCoursesByStudent.get(studentId);

        if (completed.add(courseCode)) {
            System.out.println("Course marked as completed.");
        } else {
            System.out.println("Course was already marked completed.");
        }
    }

    public void registerStudent(String studentId, String courseCode) {
        studentId = studentId.trim();
        courseCode = normalizeCode(courseCode);

        if (studentId.isEmpty()) {
            System.out.println("Student ID cannot be empty.");
            return;
        }

        Course course = courses.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.isEnrolled(studentId)) {
            System.out.println("Student is already enrolled.");
            return;
        }

        if (course.isWaitlisted(studentId)) {
            System.out.println("Student is already on the waitlist.");
            return;
        }

        HashSet<String> completed =
                completedCoursesByStudent.get(studentId);

        for (String prerequisite : course.getPrerequisiteCodes()) {
            if (completed == null || !completed.contains(prerequisite)) {
                System.out.println(
                    "Registration denied. Complete prerequisite: "
                    + prerequisite
                );
                return;
            }
        }

        if (course.hasAvailableSeat()) {
            course.enrollStudent(studentId);
            System.out.println("Student successfully enrolled.");
        } else {
            course.addToWaitlist(studentId);
            System.out.println(
                "Course full. Student added to waitlist at position "
                + course.getWaitlist().size()
            );
        }
    }

    public void dropCourse(String studentId, String courseCode) {
        studentId = studentId.trim();
        courseCode = normalizeCode(courseCode);

        Course course = courses.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.dropEnrolledStudent(studentId)) {
            System.out.println("Student dropped the course.");

            String nextStudent = course.promoteNextStudent();

            if (nextStudent != null) {
                course.enrollStudent(nextStudent);
                System.out.println(
                    "Student " + nextStudent
                    + " automatically promoted from waitlist."
                );
            } else {
                System.out.println("No student is waiting for this course.");
            }

        } else if (course.removeFromWaitlist(studentId)) {
            System.out.println("Student removed from waitlist.");

        } else {
            System.out.println("Student is not enrolled or waitlisted.");
        }
    }

    public void viewCourse(String courseCode) {
        courseCode = normalizeCode(courseCode);

        Course course = courses.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.println("\nCourse Code: " + course.getCourseCode());
        System.out.println("Course Name: " + course.getCourseName());
        System.out.println("Capacity: " + course.getCapacity());

        System.out.println(
            "Enrolled Students: "
            + course.getEnrolledStudentIds().size()
            + "/" + course.getCapacity()
        );

        System.out.println(
            "Student IDs: " + course.getEnrolledStudentIds()
        );

        System.out.println(
            "Prerequisites: " + course.getPrerequisiteCodes()
        );

        System.out.println(
            "Waitlist: " + course.getWaitlist()
        );
    }

    public void listAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        for (Course course : courses.values()) {
            System.out.println(
                course.getCourseCode() + " - "
                + course.getCourseName()
                + " | Enrolled: "
                + course.getEnrolledStudentIds().size()
                + "/" + course.getCapacity()
                + " | Waitlist: "
                + course.getWaitlist().size()
            );
        }
    }

    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseRegistrationPortal portal = new CourseRegistrationPortal();

        boolean running = true;

        while (running) {
            System.out.println("\n===== COURSE REGISTRATION PORTAL =====");
            System.out.println("1. Add Course");
            System.out.println("2. Add Prerequisite");
            System.out.println("3. Mark Course Completed");
            System.out.println("4. Register Student");
            System.out.println("5. Drop Course");
            System.out.println("6. View Course");
            System.out.println("7. List All Courses");
            System.out.println("0. Exit");

            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1: {
                    String code = readText(scanner, "Course code: ");
                    String name = readText(scanner, "Course name: ");
                    int capacity = readInt(scanner, "Capacity: ");

                    portal.addCourse(code, name, capacity);
                    break;
                }

                case 2: {
                    String code = readText(scanner, "Course code: ");
                    String prerequisite =
                            readText(scanner, "Prerequisite course code: ");

                    portal.addPrerequisite(code, prerequisite);
                    break;
                }

                case 3: {
                    String studentId = readText(scanner, "Student ID: ");
                    String code = readText(scanner, "Completed course code: ");

                    portal.markCourseCompleted(studentId, code);
                    break;
                }

                case 4: {
                    String studentId = readText(scanner, "Student ID: ");
                    String code = readText(scanner, "Course code: ");

                    portal.registerStudent(studentId, code);
                    break;
                }

                case 5: {
                    String studentId = readText(scanner, "Student ID: ");
                    String code = readText(scanner, "Course code: ");

                    portal.dropCourse(studentId, code);
                    break;
                }

                case 6: {
                    String code = readText(scanner, "Course code: ");
                    portal.viewCourse(code);
                    break;
                }

                case 7:
                    portal.listAllCourses();
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting portal.");
                    break;

                default:
                    System.out.println("Invalid menu choice.");
            }
        }

        scanner.close();
    }
}