package java_scanner.delimiter_parser;

import java_scanner.delimiter_parser.persons.Person;

import java.util.Scanner;
import java.util.function.Function;

public class Parser {

    private static Scanner delimiterParser;
    private static String delimiters = ":|,";
    private static Function<String, String> getSource = (queryTarget) -> {
        System.out.format("Please, enter %s:%n", queryTarget);
        delimiterParser = new Scanner(System.in);
        return delimiterParser.nextLine();
    };

    public static void main(String[] args) {
        scannerDelimiterParsingTest();
        delimiterParser.close();
    }

    private static void scannerDelimiterParsingTest() {

        directStringArgumentTest();
        operationStringArgumentTest();
        methodReferenceArgumentTest();
        functionalInterfaceReferenceArgumentTest();
        lambdaArgumentTest();
    }

    /*------ Parsing testing methods -----*/
    private static void directStringArgumentTest() {
        Person person = personParser("Yevgen:Zhukov,34");
        System.out.println(person);
    }

    private static void operationStringArgumentTest() {
        Person person = personParser(getInput("your personal data"));
        System.out.println(person);
    }

    private static void methodReferenceArgumentTest() {
        Person person = personParser("your personal data", Parser::getInput);
        System.out.println(person);
    }

    private static void functionalInterfaceReferenceArgumentTest() {
        Person person = personParser("your personal data", getSource);
        System.out.println(person);
    }

    private static void lambdaArgumentTest() {
        Person person = personParser("your personal data", (queryTarget) -> {
            System.out.format("Please, enter %s:%n", queryTarget);
            delimiterParser = new Scanner(System.in);
            return delimiterParser.nextLine();
        });
        System.out.println(person);
    }

    /*------ Parsing overloaded methods -----*/
    private static Person personParser(String source) {
        delimiterParser = new Scanner(source).useDelimiter(delimiters);
        String firstName = delimiterParser.next();
        String lastName = delimiterParser.next();
        int age = delimiterParser.nextInt();
        return new Person(firstName, lastName, age);
    }

    private static Person personParser(String queryTarget, Function<String, String> sourceGetter) {
        delimiterParser = new Scanner(sourceGetter.apply(queryTarget))
                              .useDelimiter(delimiters);
        String firstName = delimiterParser.next();
        String lastName = delimiterParser.next();
        int age = delimiterParser.nextInt();
        return new Person(firstName, lastName, age);
    }

    /*------ Console Input method -----*/
    private static String getInput(String queryTarget) {
        System.out.format("Please, enter %s:%n", queryTarget);
        delimiterParser = new Scanner(System.in);
        return delimiterParser.nextLine();
    }
}
