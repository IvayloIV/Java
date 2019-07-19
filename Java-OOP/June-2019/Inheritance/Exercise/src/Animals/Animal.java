package Animals;

public abstract class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) throws IllegalArgumentException {
        this.name = name;
        this.setAge(age);
        this.gender = gender;
    }

    public abstract String produceSound();

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName())
            .append(System.lineSeparator())
            .append(String.format("%s %d %s", this.name, this.age, this.gender))
            .append(System.lineSeparator())
            .append(this.produceSound());

        return stringBuilder.toString().trim();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    private void setAge(int age) throws IllegalArgumentException {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }
}
