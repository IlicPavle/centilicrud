package com.centili.crud.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;

import com.centili.crud.model.DocumentItem;

@Stateless
public abstract class DocumentItemViewDtoAssembler {
	
	public static DocumentItemViewDto assembleDocumentItemViewDto(DocumentItem documentItem) {
		DocumentItemViewDto documentItemViewDto = new DocumentItemViewDto();
		documentItemViewDto.setId(documentItem.getId());
		documentItemViewDto.setName(documentItem.getName());
		documentItemViewDto.setPrice(documentItem.getPrice());
		documentItemViewDto.setDocumentId(documentItem.getDocumentId());
		return documentItemViewDto;
	}
	
	public static List<DocumentItemViewDto> assembleDocumentITemViewDtoList(List<DocumentItem> documentItems) {
		return documentItems.stream().map(item -> assembleDocumentItemViewDto(item)).collect(Collectors.toList());
	}

}
