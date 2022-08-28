import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long minor = persons.stream()
                .filter(age -> age.getAge() <= 18)
                .count();
        System.out.println(minor);

        Stream<Person> stream = persons.stream();
        List<String> listConscripts = stream.filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily).toList();
        //System.out.println(listConscripts);

        Stream<Person> stream2 = persons.stream();
        List<String> education = stream2.filter((p) -> p.getAge() >= 18)
                .filter((p) -> (p.getSex() == Sex.WOMAN && p.getAge() < 55) || (p.getSex() == Sex.MAN && p.getAge() < 60))
                .map(Person::getFamily)
                .sorted()
                .toList();
        //System.out.println(education);

    }
}