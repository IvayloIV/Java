package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.enums.Status;
import panda.domain.models.services.ReceiptServiceModel;
import panda.domain.models.views.ReceiptDetailsViewModel;
import panda.service.ReceiptService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ReceiptDetailsBean {

    private ReceiptDetailsViewModel receiptDetailsVM;

    private ModelMapper modelMapper;
    private ReceiptService receiptService;

    public ReceiptDetailsBean() {
    }

    @Inject
    public ReceiptDetailsBean(ModelMapper modelMapper, ReceiptService receiptService) {
        this.modelMapper = modelMapper;
        this.receiptService = receiptService;
    }

    @PostConstruct
    public void load() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String receiptId = context.getRequestParameterMap().get("receiptId");

        this.receiptDetailsVM = this.modelMapper
                .map(this.receiptService.getById(receiptId), ReceiptDetailsViewModel.class);
    }

    public ReceiptDetailsViewModel getReceiptDetailsVM() {
        return receiptDetailsVM;
    }
}
