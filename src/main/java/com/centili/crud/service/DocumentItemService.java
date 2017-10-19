package com.centili.crud.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.centili.crud.model.DocumentItem;
import com.centili.crud.repository.DocumentItemRepository;

@Stateless
public class DocumentItemService {

    @Inject
    DocumentItemRepository documentItemRepository;

    public DocumentItem create(
            String name,
            String price,
            Long documentId
    ) {
        DocumentItem newDocumentItem = new DocumentItem();
        newDocumentItem.setName(name);
        newDocumentItem.setPrice(price);
        newDocumentItem.setDocumentId(documentId);
        documentItemRepository.save(newDocumentItem);
        return newDocumentItem;
    }

    public DocumentItem update(String name, String price, Long documentId, Long id){
        DocumentItem document = documentItemRepository.findOne(id);
        document.setName(name);
        document.setPrice(price);
        document.setDocumentId(documentId);
        return documentItemRepository.update(document);
    }

    public DocumentItem findOne(Long id) {
        return documentItemRepository.findOne(id);
    }

    public List<DocumentItem> findByDocumentId(Long id) {
        return documentItemRepository.findByDocumentId(id);
    }

    public void deleteDocumentItem(Long id) {
        documentItemRepository.delete(id);
    }

    public List<DocumentItem> findAll() {
        return documentItemRepository.findAll();
    }
}
