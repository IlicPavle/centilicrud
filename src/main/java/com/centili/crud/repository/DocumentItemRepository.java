package com.centili.crud.repository;

import com.centili.crud.model.DocumentItem;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class DocumentItemRepository {

    @Inject
    private EntityManager em;

    public DocumentItem findOne(Long id) {
        return em.find(DocumentItem.class, id);
    }

    public List<DocumentItem> findByDocumentId(Long documentId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DocumentItem> cq = cb.createQuery(DocumentItem.class);
        Root<DocumentItem> rootDocumentItem = cq.from(DocumentItem.class);
        CriteriaQuery<DocumentItem> finalQuery = cq.select(rootDocumentItem).where(cb.equal(rootDocumentItem.get("documentId"), documentId));
        return em.createQuery(finalQuery).getResultList();
    }

    public List<DocumentItem> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DocumentItem> cq = cb.createQuery(DocumentItem.class);
        Root<DocumentItem> rootDocumentItem = cq.from(DocumentItem.class);
        CriteriaQuery<DocumentItem> all = cq.select(rootDocumentItem);
        TypedQuery<DocumentItem> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public DocumentItem save(DocumentItem documentItem) {
        em.persist(documentItem);
        return documentItem;
    }

    public DocumentItem update(DocumentItem documentItem) {
        return em.merge(documentItem);
    }

    public void delete(Long id){
        DocumentItem documentItem = em.find(DocumentItem.class, id);
        em.remove(documentItem);
    }
}
