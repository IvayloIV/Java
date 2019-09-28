package com.essential.exodia.services;

import com.essential.exodia.domain.entites.Document;
import com.essential.exodia.domain.models.DocumentCreateBindingModel;
import com.essential.exodia.repositories.DocumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createDocument(DocumentCreateBindingModel documentCreateBindingModel) {
        Document document = this.modelMapper.map(documentCreateBindingModel, Document.class);
        this.documentRepository.save(document);
    }

    @Override
    public List<Document> getDocuments() {
        return this.documentRepository.findAll();
    }

    @Override
    public Document getByDocumentId(String id) {
        return this.documentRepository.getById(id);
    }

    @Override
    public void removeDocument(String id) {
        this.documentRepository.deleteById(id);
    }
}
