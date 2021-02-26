package HW9;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

interface Student {

    String getName();
    List<Course> getAllCourses();
}

interface Course {
    String getName();
}

class Courses implements Course {
    private String name;

    Courses(String name){
        this.name = name;
    }


    public String getName(){ return name; }
}

public class Main {

    public static void main(String args[]) {


        List<Students> students = new ArrayList<Students>();

        for (int i = 0; i < 8; i++) {
            Students student = new Students();
            student.setName("Vasya" + (i + 1));
                for (int j = 0; j <= i; j++) {
                    Course course = new Courses("additionalTrainingCourse" + (j + 1));
                    student.setCourse(course);
                }
//            }
            students.add(student);
        }

        // Task 1
        List<String> uniqueCourses = uniqCoursesNames(students);
        System.out.println("\nСписок уникальных курсов, на которые подписаны студенты");
        uniqueCourses.forEach(System.out::println);
        System.out.println();

        //Task 2
        System.out.println("Тройка самых любознательных студентов");
        threeMostCuriousStudents(students).stream()
                .map(Student::getName)
                .forEach(System.out::println);
        System.out.println();

        // Task 3
        Course course = new Courses("additionalTrainingCourse4");
        System.out.printf("Список студентов, посещающих курс %s\n", course.getName());
        studentsOnCourse(students, course).stream()
                .map(Student::getName)
                .forEach(System.out::println);
    }

    // Task 1
    static List<String> uniqCoursesNames(List<Students> students) {
        List<String> uniqueCoursesNames =
                students.stream()
                        .map(Students::getAllCourses)
                        .flatMap(Collection::stream)
                        .map(Course::getName)
                        .distinct()
                        .collect(Collectors.toList());
        return uniqueCoursesNames;
    }

    // Task 2
    static List<Student> threeMostCuriousStudents(List<Students> students) {
        List<Student> threeMostCuriousStudents =
                students.stream()
                        .sorted((student1, student2) -> student2.getAllCourses().size() - student1.getAllCourses().size())
                        .limit(3)
                        .collect(Collectors.toList());
        return threeMostCuriousStudents;
    }

    // Task 3
    static List<Student> studentsOnCourse(List<Students> students, Course course) {
        String courseName = course.getName();
        List<Student> studentsOnCourse =
                students.stream()
                        .filter(student -> student.getAllCourses().stream().anyMatch(a -> a.getName().equals(courseName)))
                        .collect(Collectors.toList());
        return studentsOnCourse;
    }
}
