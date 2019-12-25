package employeeRegister.web.mbeans;

import employeeRegister.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class RemoveEmployeeBean {
    private EmployeeService employeeService;

    public RemoveEmployeeBean() {
    }

    @Inject
    public RemoveEmployeeBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void removeEmployee(String id) throws IOException {
        this.employeeService.removeEmployee(id);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/");
    }
}
