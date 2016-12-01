package java_scanner.delimiter_parser.persons;

public class Person {

    private final String firstName;
    private final String lastName;
    private final int age;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Person(String firstName, String lastName, int age) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("\t%s:%n" +
                             "First name: %s%n" +
                             "Last name: %s%n" +
                             "Age: %s%n",
                             getClass().getSimpleName(),
                             firstName, lastName, age);
    }
}
