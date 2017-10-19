package com.centili.crud;

import com.centili.crud.dto.*;
import com.centili.crud.model.Document;
import com.centili.crud.model.DocumentItem;
import com.centili.crud.service.DocumentItemService;
import com.centili.crud.service.DocumentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.sql.Date;
import java.util.List;

@Path("/")
public class UiRestApi {

    @Inject
    DocumentService documentService;

    @Inject
    DocumentItemService documentItemService;

    @GET
    @Path("/{id}/document")
    @Produces({ "application/json" })
    public DocumentViewDto getDocument(@PathParam("id") Long id) {
        Document document = documentService.findOne(id);
        DocumentViewDto returnDto = new DocumentViewDto();
        returnDto.setCode(document.getCode());
        returnDto.setId(document.getId());
        returnDto.setDate(document.getDate().toString());
        returnDto.setName(document.getName());
        List<DocumentItem> documentItems = documentItemService.findByDocumentId(document.getId());
        List<DocumentItemViewDto> documentItemList = DocumentItemViewDtoAssembler.assembleDocumentITemViewDtoList(documentItems);
        returnDto.setDocumentItems(documentItemList);
        return  returnDto;
    }

    @POST
    @Path("/document")
    @Produces({ "application/json" })
    @Consumes({ "application/json" })
    public DocumentViewDto postDocument(DocumentCreateDto updateDocument) {
        Document document = documentService.create(updateDocument.getCode(),updateDocument.getName(),Date.valueOf(updateDocument.getDate()));
        DocumentViewDto returnDto = new DocumentViewDto();
        returnDto.setCode(document.getCode());
        returnDto.setId(document.getId());
        returnDto.setDate(document.getDate().toString());
        returnDto.setName(document.getName());
        return  returnDto;
    }

    @DELETE
    @Path("/{id}/document")
    @Produces({ "application/json" })
    public String deleteDocument(@PathParam("id") Long id) {
        documentService.deleteDocument(id);
        return "Document deleted!";
    }

    @PUT
    @Path("/document")
    @Produces({ "application/json" })
    @Consumes({ "application/json" })
    public DocumentViewDto updateDocument(DocumentViewDto updateDocument) {
        Document document = documentService.update(updateDocument.getCode(),updateDocument.getName(),Date.valueOf(updateDocument.getDate()),updateDocument.getId());
        DocumentViewDto returnDto = new DocumentViewDto();
        returnDto.setCode(document.getCode());
        returnDto.setId(document.getId());
        returnDto.setDate(document.getDate().toString());
        returnDto.setName(document.getName());
        List<DocumentItem> documentItems = documentItemService.findByDocumentId(document.getId());
        List<DocumentItemViewDto> documentItemList = DocumentItemViewDtoAssembler.assembleDocumentITemViewDtoList(documentItems);
        returnDto.setDocumentItems(documentItemList);
        return  returnDto;
    }

    @GET
    @Path("/document/all")
    @Produces({ "application/json" })
    public List<Document> getAllDocuments() {
        List<Document> documents = documentService.findAll();
        return documents;
    }

    @GET
    @Path("/{id}/docitem")
    @Produces({ "application/json" })
    public DocumentItemViewDto getDocumentItem(@PathParam("id") Long id) {
        DocumentItem documentItem = documentItemService.findOne(id);
        DocumentItemViewDto returnDto = new DocumentItemViewDto();
        returnDto.setPrice(documentItem.getPrice());
        returnDto.setName(documentItem.getName());
        returnDto.setId(documentItem.getId());
        returnDto.setDocumentId(documentItem.getDocumentId());
        return returnDto;
    }

    @POST
    @Path("/docitem")
    @Produces({ "application/json" })
    @Consumes({ "application/json" })
    public DocumentItemViewDto createDocumentItem(DocumentItemCreateDto createDocumentItem) {
        DocumentItem documentItem = documentItemService.create(createDocumentItem.getName(), createDocumentItem.getPrice(), createDocumentItem.getDocumentId());
        DocumentItemViewDto returnDto = new DocumentItemViewDto();
        returnDto.setPrice(documentItem.getPrice());
        returnDto.setName(documentItem.getName());
        returnDto.setId(documentItem.getId());
        returnDto.setDocumentId(documentItem.getDocumentId());
        return returnDto;
    }

    @DELETE
    @Path("/{id}/docitem")
    @Produces({ "application/json" })
    public String deleteDocumentItem(@PathParam("id") Long id) {
        documentItemService.deleteDocumentItem(id);
        return "DocumentItem deleted!";
    }

    @PUT
    @Path("/docitem")
    @Produces({ "application/json" })
    @Consumes({ "application/json" })
    public DocumentItemViewDto updateDocumentItem(DocumentItemViewDto updateDocumentItem) {
        DocumentItem documentItem = documentItemService.update(updateDocumentItem.getName(), updateDocumentItem.getPrice(), updateDocumentItem.getDocumentId(), updateDocumentItem.getId());
        DocumentItemViewDto returnDto = new DocumentItemViewDto();
        returnDto.setPrice(documentItem.getPrice());
        returnDto.setName(documentItem.getName());
        returnDto.setId(documentItem.getId());
        returnDto.setDocumentId(documentItem.getDocumentId());
        return returnDto;
    }

    @GET
    @Path("/docitem/all")
    @Produces({ "application/json" })
    public List<DocumentItemViewDto> getAllDocumentItems() {
        List<DocumentItem> documentItems = documentItemService.findAll();
        return DocumentItemViewDtoAssembler.assembleDocumentITemViewDtoList(documentItems);
    }

}
