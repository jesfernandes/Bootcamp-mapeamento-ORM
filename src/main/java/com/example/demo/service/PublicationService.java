package com.example.demo.service;


import com.example.demo.entities.PublicationEntity;

import java.util.Optional;

public interface PublicationService {

    Optional<PublicationEntity> getPublicationById(Long publicationId);

    PublicationEntity addPublication(PublicationEntity publicationEntity);
    
    PublicationEntity updatePublication(Long publicationId, PublicationEntity publicationEntity);

    boolean deletePublicationById(Long publicationId);
}
