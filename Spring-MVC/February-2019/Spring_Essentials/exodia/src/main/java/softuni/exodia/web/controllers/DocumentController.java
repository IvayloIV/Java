package softuni.exodia.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.exodia.domain.models.binding.DocumentScheduleBindingModel;
import softuni.exodia.domain.models.service.DocumentServiceModel;
import softuni.exodia.domain.models.view.DocumentDetailsViewModel;
import softuni.exodia.domain.models.view.DocumentHomeViewModel;
import softuni.exodia.domain.models.view.DocumentPrintViewModel;
import softuni.exodia.service.DocumentService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/document")
public class DocumentController extends BaseController {

    private ModelMapper modelMapper;
    private DocumentService documentService;

    @Autowired
    public DocumentController(ModelMapper modelMapper, DocumentService documentService) {
        this.modelMapper = modelMapper;
        this.documentService = documentService;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView) {
        List<DocumentHomeViewModel> documents = this.documentService.getAll()
                .stream()
                .map(d -> this.getTrimFunction().apply(d))
                .collect(Collectors.toList());

        modelAndView.addObject("documents", documents);
        return this.view("document/home", modelAndView);
    }

    @GetMapping("/schedule")
    public ModelAndView schedule(ModelAndView modelAndView) {
        return this.view("document/schedule", modelAndView);
    }

    @PostMapping("/schedule")
    public String scheduleConfirm(@ModelAttribute DocumentScheduleBindingModel documentModel) {
        DocumentServiceModel documentServiceModel = this.modelMapper
                .map(documentModel, DocumentServiceModel.class);

        DocumentServiceModel savedDocument = this.documentService
                .createDocument(documentServiceModel);
        if (savedDocument == null) {
            return this.redirect("/document/schedule");
        }

        return this.redirect("/document/details?documentId=" + savedDocument.getId());
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("documentId") String documentId,
                                ModelAndView modelAndView) {
        DocumentServiceModel document = this.documentService.getById(documentId);
        DocumentDetailsViewModel documentView = this.modelMapper
                .map(document, DocumentDetailsViewModel.class);

        modelAndView.addObject("document", documentView);
        return this.view("document/details", modelAndView);
    }

    @GetMapping("/print")
    public ModelAndView print(@RequestParam("documentId") String documentId,
                                ModelAndView modelAndView) {
        DocumentServiceModel document = this.documentService.getById(documentId);
        DocumentPrintViewModel documentView = this.modelMapper
                .map(document, DocumentPrintViewModel.class);

        modelAndView.addObject("document", documentView);
        return this.view("document/print", modelAndView);
    }

    @PostMapping("/print")
    public String printConfirm(@RequestParam("documentId") String documentId) {
        this.documentService.remove(documentId);
        return this.redirect("/document/home");
    }

    private Function<DocumentServiceModel, DocumentHomeViewModel> getTrimFunction() {
        return document -> {
            DocumentHomeViewModel documentModel = this.modelMapper
                    .map(document, DocumentHomeViewModel.class);

            String title = documentModel.getTitle();
            if (title.length() > 12) {
                documentModel.setTitle(title.substring(0, 12) + "...");
            }

            return documentModel;
        };
    }
}
