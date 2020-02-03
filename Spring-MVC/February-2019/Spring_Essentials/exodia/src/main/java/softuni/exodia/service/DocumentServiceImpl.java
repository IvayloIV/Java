package softuni.exodia.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import softuni.exodia.domain.entities.Document;
import softuni.exodia.domain.models.service.DocumentServiceModel;
import softuni.exodia.repository.DocumentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DocumentServiceImpl implements DocumentService {

    private ModelMapper modelMapper;
    private DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(ModelMapper modelMapper, DocumentRepository documentRepository) {
        this.modelMapper = modelMapper;
        this.documentRepository = documentRepository;
    }


    @Override
    public DocumentServiceModel createDocument(DocumentServiceModel documentServiceModel) {
        try {
            Document document = this.documentRepository
                    .saveAndFlush(this.modelMapper.map(documentServiceModel, Document.class));
            return this.modelMapper.map(document, DocumentServiceModel.class);
        } catch (Exception err) {
            return null;
        }
    }

    @Override
    public List<DocumentServiceModel> getAll() {
        return this.documentRepository.findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public DocumentServiceModel getById(String id) {
        return this.modelMapper
                .map(this.documentRepository.getOne(id), DocumentServiceModel.class);
    }

    @Override
    public boolean remove(String id) {
        try {
            this.documentRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
