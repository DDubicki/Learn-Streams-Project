package com.ddubicki.streams.stream_API;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsExample {
    List<Employee> employees = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee("Bradford", "Bell", 25, List.of("Java", "JavaScript", "Python"));
        Employee employee2 = new Employee("Charlie", "Fuller", 30, List.of("HTML", "JavaScript", "CSS"));
        Employee employee3 = new Employee("Billy", "Dunn", 45, List.of("C++", "C#"));
        Employee employee4 = new Employee("Edward", "Nixon", 18, List.of("Java", "Spring", "Hibernate"));
        Employee employee5 = new Employee("Darrell", "Waller", 30, List.of("PHP", "JavaScript", "Haskell"));
        Employee employee6 = new Employee("Orren", "Cooper", 39, List.of("Python", "C#", "JavaScript", "Angular"));
        Employee employee7 = new Employee("Fleming", "Brown", 28, List.of("Python", "HTML", "JavaScript", "CSS"));
        Employee employee8 = new Employee("Walker", "Carey", 33, List.of("Java", "Hibernate", "JavaScript", "React"));

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
        employees.add(employee7);
        employees.add(employee8);
    }

    @Test
    public void firstStream() {
        employees.forEach(System.out::println); // employees.stream().forEach(employee -> System.out.println(employee));
    }

    @Test
    public void mapOperation() {
        employees.stream()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName()) // .map(employee -> employee.getFirstName())
                .forEach(System.out::println); // .forEach(name -> System.out.println(name));
    }

    @Test
    public void flatMapOperation() {
        // Only with map -> List<List<String>>
        List<List<String>> allSkills = employees.stream()
                .map(Employee::getSkills)
                .collect(Collectors.toList());
        System.out.println(allSkills);

        // With flat map -> List<String>
        List<String> allSkills2 = employees.stream()
                .map(Employee::getSkills)
                .flatMap(strings -> strings.stream()) // .flatMap(Collection::stream)
                .distinct() // remove duplicates
                .collect(Collectors.toList());
        System.out.println(allSkills2);
    }

    @Test
    public void filterOperation() {
        employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .forEach(System.out::println);
    }

    @Test
    public void sortedOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .forEach(System.out::println);
    }

    @Test
    public void limitOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .limit(4)
                .forEach(System.out::println);
    }

    @Test
    public void skipOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .skip(3)
                .forEach(System.out::println);
    }

    @Test
    public void countOperation() {
        long numberOfEmployees = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .count();
        System.out.println(numberOfEmployees);
    }

    @Test
    public void minMaxOperations() {
        Employee youngestEmployee = employees.stream()
                .min(Comparator.comparing(Employee::getAge)).get();
        System.out.println(youngestEmployee);

        Employee oldestEmployee = employees.stream()
                .max(Comparator.comparing(Employee::getAge)).get();
        System.out.println(oldestEmployee);
    }

    @Test
    public void findAnyFindFirstOperations() {
        Employee findFirstEmployee = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .findFirst().get();
        System.out.println(findFirstEmployee);

        Employee findAnyEmployee = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("C"))
                .findAny().get();
        System.out.println(findAnyEmployee);
    }

    @Test
    public void matchOperations() {
        boolean allMatch = employees.stream()
                .allMatch(employee -> employee.getFirstName().contains("a"));
        System.out.println(allMatch);

        boolean anyMatch = employees.stream()
                .anyMatch(employee -> employee.getFirstName().startsWith("B"));
        System.out.println(anyMatch);

        boolean noneMatch = employees.stream()
                .noneMatch(employee -> employee.getFirstName().startsWith("X"));
        System.out.println(noneMatch);
    }

    @Test
    public void reduceOperation() {
        Integer sumOfAllAges = employees.stream()
                .map(Employee::getAge)
                .reduce((age1, age2) -> age1 + age2)
                .get();
        System.out.println(sumOfAllAges);


        Integer sumOfAllAges2 = employees.stream()
                .map(Employee::getAge)
                .reduce(0, (age1, age2) -> age1 + age2);
        System.out.println(sumOfAllAges2);


        Integer sumOfAllAges3 = employees.stream()
                .reduce(0, (age, employee) -> age + employee.getAge(), (age1, age2) -> age1 + age2);
        System.out.println(sumOfAllAges3);


        String allNames = employees.stream()
                .map(Employee::getFirstName)
                .reduce((name1, name2) -> name1 + ", " + name2)
                .get();
        System.out.println(allNames);
    }

    @Test
    public void takeWhileOperation() { // always should be sorted
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .takeWhile(employee -> employee.getAge() < 30)
                .forEach(System.out::println);
    }

    @Test
    public void dropWhileOperation() { // always should be sorted
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .dropWhile(employee -> employee.getAge() < 30)
                .forEach(System.out::println);
    }

    @Test
    public void forEachOrdered() {
        String sentence = "What if, then else";

        sentence.chars()
                .forEach(value -> System.out.print((char) value));
        System.out.println();

        sentence.chars()
                .parallel()
                .forEach(value -> System.out.print((char) value));
        System.out.println();

        sentence.chars()
                .parallel()
                .forEachOrdered(value -> System.out.print((char) value));
        System.out.println();
    }
}
