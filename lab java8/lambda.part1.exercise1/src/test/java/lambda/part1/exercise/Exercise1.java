package lambda.part1.exercise;

import com.google.common.collect.FluentIterable;
import com.google.common.base.Predicate;
import lambda.data.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;

class Exercise1 {

    @Test
    void sortPersonsByAgeUsingArraysSortLocalComparator() {
        Person[] persons = getPersons();

        // TODO use Arrays.sort
        class ComparatorByAge implements Comparator<Person> {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        }

        Arrays.sort(persons, new ComparatorByAge());

        assertThat(persons, is(arrayContaining(
                new Person("Иван", "Мельников", 20),
                new Person("Николай", "Зимов", 30),
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45)
        )));
    }

    @Test
    void sortPersonsByAgeUsingArraysSortAnonymousComparator() {
        Person[] persons = getPersons();

        // TODO use Arrays.sort
        //Arrays.sort(persons, Comparator.comparing(Person::getAge));
        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });

        assertThat(persons, is(arrayContaining(
                new Person("Иван", "Мельников", 20),
                new Person("Николай", "Зимов", 30),
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45)
        )));
    }

    @Test
    void sortPersonsByLastNameThenFirstNameUsingArraysSortAnonymousComparator() {
        Person[] persons = getPersons();

        // TODO use Arrays.sort
            Arrays.sort(persons, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    int res = o1.getLastName().compareTo(o2.getLastName());
                    if (res == 0)
                        res = o1.getFirstName().compareTo(o2.getFirstName());
                    return res;
                }
            });

        assertThat(persons, is(arrayContaining(
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45),
                new Person("Николай", "Зимов", 30),
                new Person("Иван", "Мельников", 20)
        )));
    }

    @Test
    void findFirstWithAge30UsingGuavaPredicate() {
        List<Person> persons = Arrays.asList(getPersons());

        // TODO use FluentIterable
//        Person person = null;

        Predicate<Person> personHasAge30 = p -> p.getAge()==30;
        Person person = FluentIterable.from(persons).firstMatch(personHasAge30).get();

        assertThat(person, is(new Person("Николай", "Зимов", 30)));
    }

    @Test
    void findFirstWithAge30UsingGuavaAnonymousPredicate() {
        List<Person> persons = Arrays.asList(getPersons());

        // TODO use FluentIterable
//        Person person = null;
        Person person = FluentIterable.from(persons).firstMatch(  p -> p.getAge()==30).get();

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
