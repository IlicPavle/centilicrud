package com.centili.crud.repository;

import com.centili.crud.model.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class DocumentRepository {

    @Inject
    private EntityManager em;

    public Document findOne(Long id) {
        return em.find(Document.class, id);
    }

    public List<Document> findAll(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Document> cq = cb.createQuery(Document.class);
        Root<Document> rootDocument = cq.from(Document.class);
        CriteriaQuery<Document> all = cq.select(rootDocument);
        TypedQuery<Document> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public Document save(Document document){
        em.persist(document);
        return document;
    }

    public void delete(Long id){
        Document document = em.find(Document.class, id);
        em.remove(document);
    }

    public Document update(Document document) {
        return em.merge(document);
    }
}