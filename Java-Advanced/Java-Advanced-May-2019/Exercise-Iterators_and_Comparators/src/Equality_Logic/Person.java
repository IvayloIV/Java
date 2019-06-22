package Equality_Logic;

public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        Person otherPerson = (Person)o;
        return this.name.equals(otherPerson.name) &&
                this.age == otherPerson.age;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.age;
        return result;
    }

    @Override
    public int compareTo(Person p) {
        if (this.name.compareTo(p.name) != 0) {
            return this.name.compareTo(p.name);
        }

        return this.age - p.age;
    }
}
