package employeeRegister.service;

import employeeRegister.domain.entities.Employee;
import employeeRegister.domain.models.service.EmployeeServiceModel;
import employeeRegister.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeServiceModel> getAll() {
        return this.employeeRepository.getAll()
                .stream()
                .map(emp -> this.modelMapper.map(emp, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeEmployee(String id) {
        this.employeeRepository.removeById(id);
    }

    @Override
    public boolean saveEmployee(EmployeeServiceModel employeeServiceModel) {
        try {
            this.employeeRepository.save(this.modelMapper.map(employeeServiceModel, Employee.class));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Double getTotalSalary() {
        return this.employeeRepository.getTotalSalary();
    }
}
