package Comparing_Objects;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private String town;

    public Person(String name, int age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
    }

    @Override
    public int compareTo(Person p) {
        if (this.name.compareTo(p.name) != 0) {
            return this.name.compareTo(p.name);
        }

        if (this.age != p.age) {
            return this.age - p.age;
        }

        return this.town.compareTo(p.town);
    }
}
