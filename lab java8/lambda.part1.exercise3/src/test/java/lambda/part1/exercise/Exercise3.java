package lambda.part1.exercise;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import lambda.data.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.arrayContaining;

@SuppressWarnings({"ConstantConditions", "unused"})
class Exercise3 {

    @Test
    void sortPersonsByAgeUsingArraysSortExpressionLambda() {
        Person[] persons = getPersons();

        // TODO use Arrays.sort + expression-lambda
        Arrays.sort(persons, Comparator.comparing(person -> person.getAge()));

        assertThat(persons, is(arrayContaining(
                new Person("Иван", "Мельников", 20),
                new Person("Николай", "Зимов", 30),
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45)
        )));
    }

    @Test
    void sortPersonsByLastNameThenFirstNameUsingArraysSortStatementLambda() {
        Person[] persons = getPersons();

        // TODO use Arrays.sort + statement-lambda
        Arrays.sort(persons, Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName));

        assertThat(persons, is(arrayContaining(
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45),
                new Person("Николай", "Зимов", 30),
                new Person("Иван", "Мельников", 20)
        )));
    }

    @Test
    void findFirstWithAge30UsingGuavaPredicateLambda() {
        List<Person> persons = Arrays.asList(getPersons());

        // TODO use FluentIterable
//        Person person = null;
        Predicate<Person> isFirstWithAge30Checker = person -> 30 == person.getAge();
        Person person = FluentIterable.from(persons).firstMatch(isFirstWithAge30Checker).get();

        assertThat(person, is(new Person("Николай", "Зимов", 30)));
    }

    private Person[] getPersons() {
        return new Person[]{
                new Person("Иван", "Мельников", 20),
                new Person("Алексей", "Доренко", 40),
                new Person("Николай", "Зимов", 30),
                new Person("Артем", "Зимов", 45)
        };
    }
}
