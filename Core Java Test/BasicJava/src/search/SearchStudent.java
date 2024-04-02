package search;

//import org.apache.commons.collections4.ListUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class SearchStudent {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Mock DB
        Student s1 = new Student("ChandraMohan", "Physics");
        Student s2 = new Student("Kowsalya", "Civics");
        Student s3 = new Student("Rifana", "History");
        Student s4 = new Student("ChandraMohan", "Chemistry");
        Student s5 = new Student("Vanitha", "Physics");
        Student s6 = new Student("Kavya", "Civics");
        Student s7 = new Student("Harini", "History");
        Student s8 = new Student("Prakash", "Chemistry");
        Student s9 = new Student("Arjun", "Physics");
        Student s10 = new Student("Preethi", "Civics");
        Student s11 = new Student("Ravi", "History");
        Student s12 = new Student("Priya", "Chemistry");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);
        students.add(s7);
        students.add(s8);
        students.add(s9);
        students.add(s10);
        students.add(s11);
        students.add(s12);


        Set<Student> duplicateNames = findDuplicatesByName(students);

        //Set<Student> duplicateSubjects = findDuplicatesBySub(students);

        Set<Student> duplicateObjects = new HashSet<>();
        duplicateObjects.addAll(duplicateNames);
        //duplicateObjects.addAll(duplicateSubjects);

        duplicateObjects.forEach(students::remove);

        printValidInvalidObject(duplicateObjects, students);

    }

    private static Set<Student> findDuplicatesByName(List<Student> students) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<String> names = students.stream().map(Student::getName).collect(Collectors.toList());
        //List<List<Student>> partitionedList = ListUtils.partition(students, 4);
        Set<Student> invalid = new HashSet<>();

        /*for (List<Student> list : partitionedList) {
            System.out.println("Partition");
            executorService.submit(() -> {
                        Set<Student> localDuplicates = findDuplicates(list, names);
                        synchronized (invalid) {
                            invalid.addAll(localDuplicates);
                        }
                    }
            );
        }

        //Shutdown
        executorService.shutdown();

        executorService.awaitTermination(10, TimeUnit.SECONDS);*/

        return invalid;

    }

    private static Set<Student> findDuplicates(List<Student> list, List<String> names) {
        return list.parallelStream()
                .filter(student -> Collections.frequency(names, student.getName()) > 1)
                .collect(Collectors.toMap(student -> student, Student::getName))
                .entrySet().parallelStream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    private static Set<Student> findDuplicatesBySub(List<Student> students) {

        List<String> names = students.stream().map(Student::getFavSubject).collect(Collectors.toList());

        return students.parallelStream()
                .filter(student -> Collections.frequency(names, student.getFavSubject()) > 1)
                .collect(Collectors.toMap(student -> student, Student::getFavSubject))
                .entrySet().parallelStream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }


    private static void printValidInvalidObject(Set<Student> invalidObjects, List<Student> validList) {
        System.out.println("Invalid*********");
        invalidObjects.forEach(System.out::println);
        System.out.println("Valid*********");
        validList.forEach(System.out::println);
    }

    public static String getTimeConsumed(long timeInMilliseconds) {
        String timeConsumed;
        if (timeInMilliseconds > 1000) {
            long hours = (timeInMilliseconds / 1000) / 60 / 60 % 24;
            long minutes = (timeInMilliseconds / 1000) / 60 % 60;
            long seconds = (timeInMilliseconds / 1000) % 60;
            timeConsumed = hours + ": h " + minutes + ": m " + seconds + ": s";
        } else {
            timeConsumed = timeInMilliseconds + "ms";
        }
        return timeConsumed;
    }
}
