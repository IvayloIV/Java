package com.essential.exodia.repositories;

import com.essential.exodia.domain.entites.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
    Document getById(String id);

    void deleteById(String id);
}
