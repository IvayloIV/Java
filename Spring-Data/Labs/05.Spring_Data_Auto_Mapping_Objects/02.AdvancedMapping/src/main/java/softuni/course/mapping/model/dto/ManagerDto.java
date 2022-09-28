package softuni.course.mapping.model.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ManagerDto {

    private String firstName;

    private String lastName;

    private List<EmployeeDto> subordinates;

    private int getSubordinatesCount() {
        return subordinates.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName)
            .append(" ")
            .append(lastName)
            .append(" | Employees: ")
            .append(getSubordinatesCount())
            .append(System.lineSeparator());

        sb.append(subordinates.stream()
            .map(EmployeeDto::toString)
            .collect(Collectors.joining(System.lineSeparator())));

        return sb.toString();
    }
}
