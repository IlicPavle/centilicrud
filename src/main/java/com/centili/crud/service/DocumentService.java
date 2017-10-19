package com.centili.crud.service;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.centili.crud.model.Document;
import com.centili.crud.model.DocumentItem;
import com.centili.crud.repository.DocumentItemRepository;
import com.centili.crud.repository.DocumentRepository;

@Stateless
public class DocumentService {

    @Inject
    DocumentRepository documentRepository;

    @Inject
    DocumentItemRepository documentItemRepository;

    public Document create(
            String code,
            String name,
            Date date
    ) {
        Document newDocument = new Document();
        newDocument.setCode(code);
        newDocument.setName(name);
        newDocument.setDate(date);
        documentRepository.save(newDocument);
        return newDocument;
    }

    public Document update(String code, String name, Date date, Long id){
        Document document = documentRepository.findOne(id);
        document.setName(name);
        document.setCode(code);
        document.setDate(date);
        return documentRepository.update(document);
    }

    public Document findOne(Long id) {
        return documentRepository.findOne(id);
    }

    public void deleteDocument(Long id) {
        documentRepository.delete(id);
        List<DocumentItem> documentItemList = documentItemRepository.findByDocumentId(id);
        documentItemList.forEach((docItem)->{
            documentItemRepository.delete(docItem.getId());
        });
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }
}
