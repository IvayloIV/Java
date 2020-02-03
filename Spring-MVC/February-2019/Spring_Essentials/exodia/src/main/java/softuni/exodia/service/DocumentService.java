package softuni.exodia.service;

import softuni.exodia.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {

    public DocumentServiceModel createDocument(DocumentServiceModel documentServiceModel);

    public List<DocumentServiceModel> getAll();

    public DocumentServiceModel getById(String id);

    public boolean remove(String id);
}
