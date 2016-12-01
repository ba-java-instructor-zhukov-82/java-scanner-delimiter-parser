<strong style="color:#AAEEEE;font-size: 120%;">java-scanner-delimiter-parser</strong>
=====================================================================================

<strong>"java-scanner-delimiter-parser"</strong> is a project, 
which demonstrates different ways of using java.util.Scanner class useDelimiter() method.  
<br>

<strong>Main semantic topics</strong>:
1. Delimiters formatting.
2. Uses of java.util.Scanner class in context of its predefined delimiter.
3. Uses of parsed source String instances as operand and function based arguments.  
<br>

####1. Delimiters formatting  
<div style="text-indent:25px;">Delimiter used in methods like useDelimiter() of java.util.Scanner class have to be separated 
with column symbol '|'.</div>
<div style="text-indent:25px;">For example:</div>

```java
private static String delimiters = ":|,";
```

<div style="text-indent:25px;">Here, delimiters class field sets as delimiters two kinds of symbols: 
',' (a coma) and ':' (a colon)</div><br>

####2. Uses of java.util.Scanner class in context of its predefined delimiter  
<div style="text-indent:25px;">java.util.Scanner class methods acts like iterators, 
walking though source String and searching related values between its predefined delimiter</div>
<div style="text-indent:25px;">For example:</div>

```java
private static Person personParser(String source) {
    delimiterParser = new Scanner(source).useDelimiter(delimiters);
    String firstName = delimiterParser.next();
    String lastName = delimiterParser.next();
    int age = delimiterParser.nextInt();
    return new Person(firstName, lastName, age);
}
```

<div style="text-indent:25px;">Here, java.util.Scanner instance nex() method returns tokens of
each String value between delimeters (coma and colon in this case).</div><br>

####3. Uses of parsed source String instances as operand and function based arguments  
<div style="text-indent:25px;">We can use not only direct operand based argument to parse it.
Java 8 allows to use method references and lambda expassions to make our code more flexible and 
readable</div>
<div style="text-indent:25px;">For example, parser method above overloaded in functional context 
in next way:</div>

```java
private static Person personParser(String queryTarget, Function<String, String> sourceGetter) {
    delimiterParser = new Scanner(sourceGetter.apply(queryTarget))
                          .useDelimiter(delimiters);
    String firstName = delimiterParser.next();
    String lastName = delimiterParser.next();
    int age = delimiterParser.nextInt();
    return new Person(firstName, lastName, age);
}
```

<div style="text-indent:25px;">Were <strong>sourceGetter</strong> as reference based on 
Function<String, String> parametrized functional interface allows to pass methods as argument
to method <strong>personParser()</strong></div>

<div style="text-indent:25px;">Now, we can invoke this methos in different ways.</div><br>

<div style="text-indent:25px;">With lambda:</div>

```java
Person person = personParser("your personal data", (queryTarget) -> {
           System.out.format("Please, enter %s:%n", queryTarget);
           delimiterParser = new Scanner(System.in);
           return delimiterParser.nextLine();
});
```
<div style="text-indent:25px;">With reference of functional interface:</div>

```java
private static Function<String, String> getSource = (queryTarget) -> {
    System.out.format("Please, enter %s:%n", queryTarget);
    delimiterParser = new Scanner(System.in);
    return delimiterParser.nextLine();
};
/*...*/
Person person = personParser("your personal data", getSource);
```

<div style="text-indent:25px;">With method reference:</div>

```java
Person person = personParser("your personal data", Parser::getInput);
```

###Conclusion

<div style="text-indent:25px;">Parsing String formatted with delimiters is simple alternative 
for regex in some cases.</div>
<div style="text-indent:25px;">We can use it in different ways according to concrete task, 
by passing source String instance as predefined operand or as a return value of class member method, 
functional interface reference, lambda expression, making our code more readable, flexible and
with more high perfomance in some situations.</div>