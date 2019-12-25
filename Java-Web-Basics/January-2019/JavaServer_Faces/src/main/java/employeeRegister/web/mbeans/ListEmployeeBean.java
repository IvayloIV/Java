package employeeRegister.web.mbeans;

import employeeRegister.domain.models.view.EmployeeListViewModel;
import employeeRegister.service.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ListEmployeeBean {
    private List<EmployeeListViewModel> employeeListViewModels;
    private Double totalSalary;

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public ListEmployeeBean() {
    }

    @Inject
    public ListEmployeeBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.loadEmployeeList();
        this.totalSalary = this.employeeService.getTotalSalary();
    }

    private void loadEmployeeList() {
        this.employeeListViewModels = this.employeeService.getAll()
                .stream()
                .map(emp -> this.modelMapper.map(emp, EmployeeListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EmployeeListViewModel> getEmployeeListViewModels() {
        return employeeListViewModels;
    }

    public Double getTotalSalary() {
        if (totalSalary == null) {
            return 0d;
        }
        return totalSalary;
    }

    public String getAverageSalary() {
        if (this.employeeListViewModels.size() == 0) {
            return "0.00";
        }
        return String.format("%.2f", this.totalSalary / this.employeeListViewModels.size())
                .replace(",", ".");
    }
}
