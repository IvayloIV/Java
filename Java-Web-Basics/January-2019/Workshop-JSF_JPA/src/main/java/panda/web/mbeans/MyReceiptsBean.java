package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.models.views.MyReceiptModelView;
import panda.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class MyReceiptsBean {

    private List<MyReceiptModelView> myReceiptVM;

    private ModelMapper modelMapper;
    private UserService userService;

    public MyReceiptsBean() {
    }

    @Inject
    public MyReceiptsBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void load() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        String username = (String) session.getAttribute("username");

        this.myReceiptVM = this.userService.getByUsername(username)
                .getReceipts()
                .stream()
                .map(r -> this.modelMapper.map(r, MyReceiptModelView.class))
                .collect(Collectors.toList());
    }

    public List<MyReceiptModelView> getMyReceiptVM() {
        return myReceiptVM;
    }
}
