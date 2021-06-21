package com.example.demo.service.impl;

import com.example.demo.entities.PublicationEntity;
import com.example.demo.exceptions.NotFoundIdException;
import com.example.demo.exceptions.UserEntityNotNullException;
import com.example.demo.repository.PublicationRepository;
import com.example.demo.service.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    @Override
    public PublicationEntity addPublication(PublicationEntity publicationEntity) {
        return publicationRepository.save(publicationEntity);
    }

    @Override
    public Optional<PublicationEntity> getPublicationById(Long publicationId){
        Optional<PublicationEntity> publication = publicationRepository.findById(publicationId);
        if(!publication.isPresent()){
            throw new NotFoundIdException("Não encontrado registro de publicacao correspondente.");
        }
        return publication;
    }

    @Override
    public boolean deletePublicationById(Long publicationId) {
        getPublicationById(publicationId);
        publicationRepository.deleteById(publicationId);
        return true;
    }

    @Override
    public PublicationEntity updatePublication(Long publicationId, PublicationEntity publicationEntity) {
        getPublicationById(publicationId);
        validPublication(publicationEntity);
        return publicationRepository.save(publicationEntity);
    }

    private Boolean validPublication(PublicationEntity publicationEntity) {
        if (Objects.isNull(publicationEntity)){
            throw new UserEntityNotNullException("Publicacao não pode estar vazia");
        }
        return true;
    }

}
