package com.centili.crud.dto;

import java.util.List;

public class DocumentViewDto {

    private Long id;
    private String code;
    private String name;
    private String date;
    private List<DocumentItemViewDto> documentItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DocumentItemViewDto> getDocumentItems() {
        return documentItems;
    }

    public void setDocumentItems(List<DocumentItemViewDto> documentItems) {
        this.documentItems = documentItems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
