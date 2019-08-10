package Custom_Exception;

public class Student {
    private String name;
    private String email;

    public Student(String name, String email) throws InvalidPersonNameException {
        this.setName(name);
        this.setEmail(email);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) throws InvalidPersonNameException {
        if (!name.matches("[A-Za-z]?")) {
            throw new InvalidPersonNameException("Name cannot contains special characters or number value.");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }
}
