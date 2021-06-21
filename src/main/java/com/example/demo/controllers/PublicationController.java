package com.example.demo.controllers;

import com.example.demo.entities.PublicationEntity;
import com.example.demo.service.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/publication")
public class PublicationController {

    private final PublicationService publicationService;

    @GetMapping("/{publicationId}")
    public ResponseEntity<Optional<PublicationEntity>> getPublication(@RequestHeader("publicationId") @Validated Long publicationId) {
        Optional<PublicationEntity> getPublication = publicationService.getPublicationById(publicationId);
        return new ResponseEntity<Optional<PublicationEntity>>(getPublication, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PublicationEntity> addPublication(@RequestBody @Validated PublicationEntity publicationEntity) {
        PublicationEntity newPublication = publicationService.addPublication(publicationEntity);
        return new ResponseEntity<>(newPublication, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PublicationEntity> updatePublication(@RequestHeader("publicationId") @Validated Long publicationId, @RequestBody @Validated PublicationEntity publicationEntity){
        Optional<PublicationEntity> publicationIn = Optional.ofNullable(publicationService.updatePublication(publicationId, publicationEntity));
        if (!publicationIn.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            publicationEntity.setId(publicationIn.get().getId());
            return new ResponseEntity<PublicationEntity>(publicationService.updatePublication(publicationId, publicationEntity), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{publicationId}")
    public ResponseEntity<Optional<PublicationEntity>> deletePublication(@RequestHeader("publicationId") @Validated Long publicationId) {
        Optional<PublicationEntity> publicationIn = publicationService.getPublicationById(publicationId);
        if (!publicationIn.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            publicationService.deletePublicationById(publicationId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
