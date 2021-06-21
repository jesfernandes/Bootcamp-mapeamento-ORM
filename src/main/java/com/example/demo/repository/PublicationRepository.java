package com.example.demo.repository;

import com.example.demo.entities.PublicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<PublicationEntity, Long> {
}
