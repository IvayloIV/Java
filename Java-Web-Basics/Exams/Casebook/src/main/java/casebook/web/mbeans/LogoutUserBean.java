package casebook.web.mbeans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class LogoutUserBean {

    public LogoutUserBean() {
    }

    public String logout() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.invalidateSession();
		return "pretty:index";
    }
}
