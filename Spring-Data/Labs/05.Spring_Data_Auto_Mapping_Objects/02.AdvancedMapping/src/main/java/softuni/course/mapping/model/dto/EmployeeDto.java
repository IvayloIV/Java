package softuni.course.mapping.model.dto;

import lombok.Data;

@Data
public class EmployeeDto {

    private String firstName;

    private String lastName;

    private Double salary;

    @Override
    public String toString() {
        return String.format("   - %s %s %.2f", firstName, lastName, salary);
    }
}
