package com.essential.exodia.web.controllers;

import com.essential.exodia.domain.entites.Document;
import com.essential.exodia.domain.models.DocumentCreateBindingModel;
import com.essential.exodia.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class DocumentController extends BaseController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/create")
    public ModelAndView createSchedule(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        modelAndView.addObject("session", httpSession);
        return super.view("document/schedule", modelAndView);
    }

    @PostMapping("/create")
    public String createScheduleConfirm(@ModelAttribute(name = "model") DocumentCreateBindingModel documentCreateBindingModel) {
        this.documentService.createDocument(documentCreateBindingModel);
        return super.redirect("/schedule/home");
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        List<Document> documents = this.documentService.getDocuments();
        documents.forEach(d -> {
            if (d.getTitle().length() > 12) {
                d.setTitle(d.getTitle().substring(0, 12) + "...");
            }
        });
        modelAndView.addObject("documents", documents);
        modelAndView.addObject("session", httpSession);
        return super.view("document/home", modelAndView);
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable String id, ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        Document document = this.documentService.getByDocumentId(id);
        modelAndView.addObject("document", document);
        modelAndView.addObject("session", httpSession);
        return super.view("document/details", modelAndView);
    }

    @GetMapping("/print/{id}")
    public ModelAndView print(@PathVariable String id, ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        Document document = this.documentService.getByDocumentId(id);
        modelAndView.addObject("document", document);
        modelAndView.addObject("session", httpSession);
        return super.view("document/print", modelAndView);
    }

    @PostMapping("/print/{id}")
    public String printConfirm(@PathVariable String id) {
        this.documentService.removeDocument(id);
        return super.redirect("/schedule/home");
    }
}
