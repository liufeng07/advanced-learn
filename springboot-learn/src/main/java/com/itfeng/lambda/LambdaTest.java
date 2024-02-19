package com.itfeng.lambda;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author: lf
 * @creat: 2023/9/25 10:32
 * @describe:
 */
public class LambdaTest {


    public static void checkAndExecute(List<Person> personList, NameChecker nameChecker, Executor executor) {
        for (Person person : personList) {
            if (nameChecker.check(person)) {
                executor.execute(person);
            }
        }
    }

    public static void checkAndExecute2(List<Person> personList, Predicate<Person> nameChecker, Consumer<Person> executor) {
        for (Person person : personList) {
            if (nameChecker.test(person)) {
                executor.accept(person);
            }
        }
        //简化 - 用Iterable.forEach()取代foreach loop：
        personList.forEach(p -> {
            if (nameChecker.test(p)) {
                executor.accept(p);
            }
        });

    }

    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(new Person("zhan", "san", 30),
                new Person("li", "si", 23),
                new Person("wang", "wu", 45)
        );

        //写在前面：
        //parameters 是Lambda表达式的参数列表。
        //-> 是箭头符号，用于将参数列表与Lambda表达式的主体分隔开。
        //expression 是Lambda表达式的主体，通常是一个表达式，它会被执行并返回结果。

        //1.原生态Lambda写法，定义两个函数式接口，定义一个静态函数，调用静态函数并给参数赋值Lambda表达式
        checkAndExecute(personList, p -> p.getLastName().startsWith("w"), p -> System.out.println(p.getFirstName()));
        //2.简化利用java8的函数式接口包，Predicate<T>和Consumer<T>
        checkAndExecute2(personList, p -> p.getLastName().startsWith("w"), p -> System.out.println(p.getFirstName()));
        //3.利用stream()替代静态函数(由于静态函数只是对List进行操作) stream 方法是接受 Predicate<T>和Consumer<T> 等参数的
        personList.stream().filter(person -> person.getLastName().startsWith("w")).forEach(person -> System.out.println(person.getFirstName()));
        //4.引入Method referent继续简化
        personList.stream().filter(person -> person.getLastName().startsWith("w")).forEach(System.out::println);
        //tips: Lambda配合Optional<T>可以使Java对于null的处理变的异常优雅,只有当Optional<T>结合Lambda一起使用的时候，才能发挥出其真正的威力！⭐️否则跟普通写法没啥区别⭐️

    }


}


@AllArgsConstructor
class Person {
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private int age;
}


@FunctionalInterface
interface NameChecker {
    boolean check(Person p);
}

@FunctionalInterface
interface Executor {
    void execute(Person p);
}