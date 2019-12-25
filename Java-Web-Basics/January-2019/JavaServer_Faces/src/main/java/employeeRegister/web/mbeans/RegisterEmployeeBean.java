package employeeRegister.web.mbeans;

import employeeRegister.domain.models.binding.EmployeeRegisterBindingModel;
import employeeRegister.domain.models.service.EmployeeServiceModel;
import employeeRegister.service.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class RegisterEmployeeBean {
    private EmployeeRegisterBindingModel employeeRegisterBindingModel;

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public RegisterEmployeeBean() {
        this.employeeRegisterBindingModel = new EmployeeRegisterBindingModel();
    }

    @Inject
    public RegisterEmployeeBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    public void registerEmployee() throws IOException {
        this.employeeService
                .saveEmployee(this.modelMapper.map(this.employeeRegisterBindingModel, EmployeeServiceModel.class));

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/");
    }

    public EmployeeRegisterBindingModel getEmployeeRegisterBindingModel() {
        return employeeRegisterBindingModel;
    }
}
