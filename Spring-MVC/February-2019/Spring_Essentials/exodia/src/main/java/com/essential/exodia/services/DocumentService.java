package com.essential.exodia.services;

import com.essential.exodia.domain.entites.Document;
import com.essential.exodia.domain.models.DocumentCreateBindingModel;

import java.util.List;

public interface DocumentService {
    void createDocument(DocumentCreateBindingModel documentCreateBindingModel);

    List<Document> getDocuments();

    Document getByDocumentId(String id);

    void removeDocument(String id);
}
